package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.CacheValues;
import play.cache.Cache;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class CacheDemo extends Controller {

	private static final String pageTitle = "Distributed cache demo";
	private static Form<CacheValues> valueForm = form(CacheValues.class);
	
	public static String getCurrentTime(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(new Date());
	}

	public static Result index() {
		return ok(cachedemo.render(pageTitle, getCurrentTime(), valueForm,
				"Nothing new, just call the new page"));
	}

	public static Result getUUID() {
		String uuid = session("uuid");
		if (uuid == null) {
			uuid = java.util.UUID.randomUUID().toString();
			session("uuid", uuid);
		}
		return ok(cachedemo.render(pageTitle, getCurrentTime(), valueForm,
				"this is the generated uuid: " + uuid));
	}

	public static Result readUUIDFromSession() {
		String uuid = session("uuid");
		if (uuid == null) {
			return ok(cachedemo.render(pageTitle, getCurrentTime(), valueForm,
					"No UUID has been found on the session"));
		} else {
			return ok(cachedemo.render(pageTitle, getCurrentTime(), valueForm,
					"the UUID found on the session was: " + uuid));
		}

	}

	public static Result destroySession() {
		session().clear();
		return ok(cachedemo
				.render(pageTitle, getCurrentTime(), valueForm,
						"The session has been destroyed for the current user (or browser)"));
	}

	public static Result setValueInCache() {

		Form<CacheValues> valueForm = form(CacheValues.class).bindFromRequest();
		if (valueForm.hasErrors()) {
			return badRequest(cachedemo.render(pageTitle, getCurrentTime(), valueForm,
					"The value form contains mistakes"));
		}

		// the value that needs to be set in the cache
		String value = valueForm.get().value;
		// the uuid use to differenciate elements in the global cache
		String uuidToUse = valueForm.get().uuid;

		if (uuidToUse==null) {
			return ok(cachedemo
					.render(pageTitle, getCurrentTime(), valueForm,
							"For this applicaiton, an UUID is mandatory to set something in the cache"));
		}

		// set in the cache
		Cache.set(uuidToUse + "testkey", value);

		// access the cache to check the result
		String cachedValue = (String) Cache.get(uuidToUse + "testkey");
		if (cachedValue == null) {
			return ok(cachedemo.render(pageTitle, getCurrentTime(), valueForm, "The value "
					+ value + " was not set successfully in the cache"));
		}

		return ok(cachedemo.render(pageTitle, getCurrentTime(), valueForm, "The value " + value
				+ " has been set in the cache, for the UUID: " + uuidToUse));

	}

	public static Result getValueInCache() {
	
		// is there currently an opened session?
		String uuid = session("uuid");
		if (uuid == null) {
			return ok(cachedemo
					.render(pageTitle, getCurrentTime(), valueForm,
							"No UUID has been found on the session, you need an UUID to work with the cache"));
		}
		// access the cache to check the result
		String cachedValue = (String) Cache.get(uuid + "testkey");
		if (cachedValue == null) {
			return ok(cachedemo.render(pageTitle, getCurrentTime(), valueForm, "There is no value in the cache for the UUID" + uuid));
		}

		return ok(cachedemo.render(pageTitle, getCurrentTime(), valueForm, "The value " + cachedValue
				+ " has been found in the cache, for the UUID: " + uuid));

	}
	
}
