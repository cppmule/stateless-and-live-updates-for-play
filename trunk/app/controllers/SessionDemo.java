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


public class SessionDemo extends Controller {

	private static final String pageTitle = "Play framework session demo";
	private static Form<KeyValueModel> defaultForm = form(KeyValueModel.class);
	

	public static Result index() {
		return ok(sessiondemo.render(pageTitle, TimeHelper.getCurrentTime(), defaultForm,
				"Nothing new, just call the new page"));
	}

	public static Result setValueInSession(){
		
		Form<KeyValueModel> form = form(KeyValueModel.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(sessiondemo.render(pageTitle, TimeHelper.getCurrentTime(), form,
					"The form contains mistakes"));
		}

		// the value that needs to be set in the session
		String value = form.get().value;
		// the key to use
		String key = form.get().key;

		if (value==null || key==null) {
			return ok(sessiondemo
				.render(pageTitle, TimeHelper.getCurrentTime(), form,
						"A key and a value are needed to set something in the session!"));
		}

		// set in the session
		session(key, value);
		
		return ok(sessiondemo.render(pageTitle, TimeHelper.getCurrentTime(), form, "The value " + value
				+ " has been set in the session, for the key: " + key));

		
	}

	public static Result getValueInSession(){
	
		Form<KeyValueModel> form = form(KeyValueModel.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(sessiondemo.render(pageTitle, TimeHelper.getCurrentTime(), form,
					"The form contains mistakes"));
		}
		
		// the key to use
		String key = form.get().key;
	
		if (key==null) {
			return ok(sessiondemo
				.render(pageTitle, TimeHelper.getCurrentTime(), form,
						"A key is needed to get something in the session!"));
		}
	
		// get in the session
		String value = session(key);
		
		return ok(sessiondemo.render(pageTitle, TimeHelper.getCurrentTime(), form, "The value " + value
				+ " has been found in the session for the key: " + key));
	
	}
	
	public static Result destroySession() {
		session().clear();
		return ok(sessiondemo
				.render(pageTitle, TimeHelper.getCurrentTime(), defaultForm,
						"The session has been destroyed for the current user (and browser)"));
	}

}
