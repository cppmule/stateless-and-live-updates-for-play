package controllers;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import models.*;
import play.cache.Cache;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.*;
import play.mvc.*;


public class CacheAsSessionDemo extends Controller {

	private static final String pageTitle = "Cache as session demo - Node 1";
	private static final String uuidCacheKey = "testkey";
	private static Form<UUIDValueModel> defaultForm = form(UUIDValueModel.class);
	
	
	public static String getCurrentTime(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(new Date());
	}

	public static Result index() {
		return ok(cacheassessiondemo.render(pageTitle, getCurrentTime(), defaultForm,
				"Nothing new, just call the new page"));
	}
	
	public static Result uuidhome() {
		return ok(cacheassessiondemo.render(pageTitle, getCurrentTime(), defaultForm,
				"Nothing new, just call the new page"));
	}

	public static Result getUUID() {
		String uuid = session("uuid");
		if (uuid == null) {
			uuid = java.util.UUID.randomUUID().toString();
			session("uuid", uuid);
		}
		return ok(cacheassessiondemo.render(pageTitle, getCurrentTime(), defaultForm,
				"this is the generated uuid: " + uuid));
	}

	public static Result readUUIDFromSession() {
		String uuid = session("uuid");
		if (uuid == null) {
			return ok(cacheassessiondemo.render(pageTitle, getCurrentTime(), defaultForm,
					"No UUID has been found on the session"));
		} else {
			return ok(cacheassessiondemo.render(pageTitle, getCurrentTime(), defaultForm,
					"the UUID found on the session was: " + uuid));
		}

	}

	public static Result destroySession() {
		session().clear();
		return ok(cacheassessiondemo
				.render(pageTitle, getCurrentTime(), defaultForm,
						"The session has been destroyed for the current user (and browser)"));
	}

	public static Result setValueInCacheWithUUID() {

		Form<UUIDValueModel> form = form(UUIDValueModel.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(cacheassessiondemo.render(pageTitle, getCurrentTime(), form,
					"The value form contains mistakes"));
		}

		// the value that needs to be set in the cache
		String value = form.get().value;
		// the uuid use to differenciate elements in the global cache
		String uuidToUse = form.get().uuid;

		if (uuidToUse == null || uuidToUse.equals("")) {
			String uuid = session("uuid");
			if (uuid == null || uuid.equals("")) { 
				return ok(cacheassessiondemo
						.render(pageTitle, getCurrentTime(), form,
								"For this applicaiton, an UUID is mandatory to set something in the cache"));
			} else {
				uuidToUse = uuid;
			}
		}

		// set in the cache
		Cache.set(uuidToUse + uuidCacheKey, value);

		// access the cache to check the result
		String cachedValue = (String) Cache.get(uuidToUse + uuidCacheKey);
		if (cachedValue == null) {
			return ok(cacheassessiondemo.render(pageTitle, getCurrentTime(), defaultForm, "The value "
					+ value + " was not set successfully in the cache"));
		}

		return ok(cacheassessiondemo.render(pageTitle, getCurrentTime(), defaultForm, "The value " + value
				+ " has been set in the cache, for the UUID: " + uuidToUse + " and the key " + uuidCacheKey));

	}

	public static Result getValueInCacheWithUUID() {
		
		// is there currently an opened session?
		String uuid = session("uuid");
		if (uuid == null) {
			return ok(cacheassessiondemo
					.render(pageTitle, getCurrentTime(), defaultForm,
							"No UUID has been found on the session, you need an UUID to work with the cache"));
		}
		// access the cache to check the result
		String cachedValue = (String) Cache.get(uuid + uuidCacheKey);
		if (cachedValue == null) {
			return ok(cacheassessiondemo.render(pageTitle, getCurrentTime(), defaultForm, "There is no value in the cache for the UUID " + uuid));
		}

		return ok(cacheassessiondemo.render(pageTitle, getCurrentTime(), defaultForm, "The value " + cachedValue
				+ " has been found in the cache, for the UUID: " + uuid));

	}
}
