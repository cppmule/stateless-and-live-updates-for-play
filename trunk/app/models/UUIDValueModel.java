package models;

import play.data.validation.Constraints.Required;


public class UUIDValueModel {
	
	@Required
    public String value;
	public String uuid;
	
}
