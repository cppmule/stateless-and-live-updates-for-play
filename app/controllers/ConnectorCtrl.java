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


public class ConnectorCtrl extends Controller {

	private static final String pageTitle = "Connector EJB Demo";

	public static Result callWithSOAP() {
		return ok();
	}
	
	public static Result callWithREST() {
		return ok();
	}
	
	public static Result callWithRMI() {
		return ok();
	}
}
