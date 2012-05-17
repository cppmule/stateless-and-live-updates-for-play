package models;

import play.data.validation.Constraints.Required;

public class KeyValueModel {

	@Required
	public String key;
    public String value;
	
}
