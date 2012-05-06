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


public class CacheDemo extends Controller {

	private static final String pageTitle = "Distributed cache demo - Node 1";
	private static Form<GlobalCacheValue> gcForm = form(GlobalCacheValue.class);
	private static Form<UUIDCacheValue> uuidForm = form(UUIDCacheValue.class);
	
	public static String getCurrentTime(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(new Date());
	}

	public static Result index() {
		return ok(cachedemo.render(pageTitle, getCurrentTime(), gcForm,
				"Nothing new, just call the new page"));
	}
	
	public static Result uuidhome() {
		return ok(cacheuuid.render(pageTitle, getCurrentTime(), uuidForm,
				"Nothing new, just call the new page"));
	}

	public static Result getUUID() {
		String uuid = session("uuid");
		if (uuid == null) {
			uuid = java.util.UUID.randomUUID().toString();
			session("uuid", uuid);
		}
		return ok(cacheuuid.render(pageTitle, getCurrentTime(), uuidForm,
				"this is the generated uuid: " + uuid));
	}

	public static Result readUUIDFromSession() {
		String uuid = session("uuid");
		if (uuid == null) {
			return ok(cacheuuid.render(pageTitle, getCurrentTime(), uuidForm,
					"No UUID has been found on the session"));
		} else {
			return ok(cacheuuid.render(pageTitle, getCurrentTime(), uuidForm,
					"the UUID found on the session was: " + uuid));
		}

	}

	public static Result destroySession() {
		session().clear();
		return ok(cacheuuid
				.render(pageTitle, getCurrentTime(), uuidForm,
						"The session has been destroyed for the current user (or browser)"));
	}
	
	public static Result destroyCache() {
		//WHAT TO DO HERE?
		return ok(cachedemo
				.render(pageTitle, getCurrentTime(), gcForm,
						"The cache has been destroyed (NOTE REALLY...)"));
	}

	public static Result setValueInGlobalCache(){
		
		Form<GlobalCacheValue> form = form(GlobalCacheValue.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(cachedemo.render(pageTitle, getCurrentTime(), form,
					"The form contains mistakes"));
		}

		// the value that needs to be set in the cache
		String value = form.get().value;
		// the key to use
		String key = form.get().key;

		if (value==null || key==null) {
			return ok(cachedemo
				.render(pageTitle, getCurrentTime(), gcForm,
						"A key and a value are needed to set something in the global cache!"));
		}

		// set in the cache
		Cache.set(key, value);

		return ok(cachedemo.render(pageTitle, getCurrentTime(), gcForm, "The value " + value
				+ " has been set in the global cache, for the key: " + key));

		
	}
	

	public static Result setValueInCacheWithUUID() {

		Form<UUIDCacheValue> form = form(UUIDCacheValue.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(cacheuuid.render(pageTitle, getCurrentTime(), form,
					"The value form contains mistakes"));
		}

		// the value that needs to be set in the cache
		String value = form.get().value;
		// the uuid use to differenciate elements in the global cache
		String uuidToUse = form.get().uuid;

		if (uuidToUse==null) {
			String uuid = session("uuid");
			if (uuid == null || uuid.equals("")) {
				return ok(cacheuuid
						.render(pageTitle, getCurrentTime(), form,
								"For this applicaiton, an UUID is mandatory to set something in the cache"));
			} else {
				uuidToUse = uuid;
			}
		}
		
		if (uuidToUse.equals("")) {
			return ok(cacheuuid
					.render(pageTitle, getCurrentTime(), uuidForm,
							"blaaaaaaaaaaaaaah!"));
		}

		// set in the cache
		Cache.set(uuidToUse + "testkey", value);

		// access the cache to check the result
		String cachedValue = (String) Cache.get(uuidToUse + "testkey");
		if (cachedValue == null) {
			return ok(cacheuuid.render(pageTitle, getCurrentTime(), uuidForm, "The value "
					+ value + " was not set successfully in the cache"));
		}

		return ok(cacheuuid.render(pageTitle, getCurrentTime(), uuidForm, "The value " + value
				+ " has been set in the cache, for the UUID: " + uuidToUse));

	}

	public static Result getValueInCacheWithUUID() {
		
		// is there currently an opened session?
		String uuid = session("uuid");
		if (uuid == null) {
			return ok(cacheuuid
					.render(pageTitle, getCurrentTime(), uuidForm,
							"No UUID has been found on the session, you need an UUID to work with the cache"));
		}
		// access the cache to check the result
		String cachedValue = (String) Cache.get(uuid + "testkey");
		if (cachedValue == null) {
			return ok(cacheuuid.render(pageTitle, getCurrentTime(), uuidForm, "There is no value in the cache for the UUID" + uuid));
		}

		return ok(cacheuuid.render(pageTitle, getCurrentTime(), uuidForm, "The value " + cachedValue
				+ " has been found in the cache, for the UUID: " + uuid));

	}
	
	public static Result getValueInGlobalCache() {
			
		Form<GlobalCacheValue> form = form(GlobalCacheValue.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(cachedemo.render(pageTitle, getCurrentTime(), form,
					"The form contains mistakes"));
		}

		// the key for the element in the cache
		String key = form.get().key;
		
		// access the cache to check the result
		String cachedValue = (String) Cache.get(key);
		if (cachedValue == null) {
			return ok(cachedemo.render(pageTitle, getCurrentTime(), form, "There is no value in the cache for the key" + key));
		}

		return ok(cachedemo.render(pageTitle, getCurrentTime(), form, "The value " + cachedValue
				+ " has been found in the cache, for the key: " + key));
	}
}
