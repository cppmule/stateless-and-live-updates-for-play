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
	private static Form<KeyValueModel> defaultForm = form(KeyValueModel.class);

	public static Result index() {
		return ok(cachedemo.render(pageTitle, TimeHelper.getCurrentTime(), defaultForm,
				"Nothing new, just call the new page"));
	}
	
	public static Result destroyCache() {
		//WHAT TO DO HERE?
		return ok(cachedemo
				.render(pageTitle, TimeHelper.getCurrentTime(), defaultForm,
						"The cache has not been destroyed... It is not possible with the default Play interface used for the cache."));
	}

	public static Result setValueInGlobalCache(){
		
		Form<KeyValueModel> form = form(KeyValueModel.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(cachedemo.render(pageTitle, TimeHelper.getCurrentTime(), form,
					"The form contains mistakes"));
		}

		// the value that needs to be set in the cache
		String value = form.get().value;
		// the key to use
		String key = form.get().key;

		if (value==null || key==null) {
			return ok(cachedemo
				.render(pageTitle, TimeHelper.getCurrentTime(), form,
						"A key and a value are needed to set something in the global cache!"));
		}

		// set in the cache
		Cache.set(key, value);

		return ok(cachedemo.render(pageTitle, TimeHelper.getCurrentTime(), form, "The value " + value
				+ " has been set in the global cache, for the key: " + key));
		
	}
	
	public static Result getValueInGlobalCache() {
			
		Form<KeyValueModel> form = form(KeyValueModel.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(cachedemo.render(pageTitle, TimeHelper.getCurrentTime(), form,
					"The form contains mistakes"));
		}

		// the key for the element in the cache
		String key = form.get().key;
		
		// access the cache to check the result
		String cachedValue = (String) Cache.get(key);
		if (cachedValue == null) {
			return ok(cachedemo.render(pageTitle, TimeHelper.getCurrentTime(), form, "There is no value in the cache for the key " + key));
		}

		return ok(cachedemo.render(pageTitle, TimeHelper.getCurrentTime(), form, "The value " + cachedValue
				+ " has been found in the cache, for the key: " + key));
	}
}
