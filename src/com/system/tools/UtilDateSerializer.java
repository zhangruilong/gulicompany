/**
 * 
 */
package com.system.tools;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author xiongmingwei
 *
 * 2014年4月12日
 */
public class UtilDateSerializer implements JsonSerializer<Date>,JsonDeserializer<Date>{
	private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Date deserialize(JsonElement element, Type type,
			JsonDeserializationContext context) throws JsonParseException {
		try {
			return df.parse(element.getAsString());
		} catch (Exception e) {
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(Date arg0, Type arg1,
			JsonSerializationContext arg2) {
		// TODO Auto-generated method stub
		return null;
	}
}
