package models;

import play.data.validation.Constraints;

public class CacheValues {
	
	@Constraints.Required
    public String value;
	@Constraints.Required
    public String uuid;

}
