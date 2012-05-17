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


public class RoutesUpdateDemo extends Controller {

	private static final String pageTitle = "Live update of the routes demo";

	public static Result index() {
		return ok(routesindex.render(pageTitle, TimeHelper.getCurrentTime(), "Nothing new, just call the new page"));
	}
	
	public static Result stateA() {
		return ok(statea.render(pageTitle, TimeHelper.getCurrentTime(), "We are now in state A"));
	}
	
	public static Result stateB() {
		return ok(stateb.render(pageTitle, TimeHelper.getCurrentTime(), "We are now in state B"));
	}
	
	public static Result stateC() {
		return ok(statec.render(pageTitle, TimeHelper.getCurrentTime(), "We are now in state C"));
	}
	
	
}
