package voter.main;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonResult {
	
	private JSONObject jsonObject;
	private JSONArray jsonArray;
	
	public enum JSONValueType {
		OBJECT, STRING, INTEGER, BOOLEAN, DOUBLE, ARRAYLIST, LONG
	}
	
	public JsonResult(String json) throws JSONException {
		
		char first = json.charAt(0);
		
		try {
			if(first == '{') {
				this.jsonObject = new JSONObject(json);
			}
			else if(first == '[') {
				this.jsonArray = new JSONArray(json);
			}
			else {
				throw new JSONException("Malformed JSON");
			}
		} catch (JSONException e) {
			Log.e("Voter", e.getMessage());
			throw new JSONException(e.getMessage());
		}
				
	}
	
	public int length() {
		return (this.jsonArray == null) ? 0 : this.jsonArray.length();
	}
	
	public String getObject(int index) {
		try {
			return (this.jsonArray == null) ? null : this.jsonArray.getJSONObject(index).toString();
		} catch (Exception e) {
			return null;
		}
	}
	
	public String valueForKey(String key) {
		return (String)valueForKey(key, JSONValueType.STRING);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T valueForKey(String key, JSONValueType type) {
		try {
			if(type == JSONValueType.OBJECT) {
				Class<T> t = (Class<T>)Object.class;
				return t.cast(this.jsonObject.opt(key));
			}
			else if(type == JSONValueType.STRING) {
				Class<T> t = (Class<T>)String.class;
				return t.cast(this.jsonObject.optString(key));
			}
			else if(type == JSONValueType.INTEGER) {
				Class<T> t = (Class<T>)Integer.class;
				return t.cast(Integer.parseInt(this.jsonObject.optString(key)));
			}
			else if(type == JSONValueType.DOUBLE) {
				Class<T> t = (Class<T>)Double.class;
				return t.cast(Double.parseDouble(this.jsonObject.optString(key)));
			}
			else if(type == JSONValueType.LONG) {
				Class<T> t = (Class<T>)Long.class;
				return t.cast(Long.parseLong(this.jsonObject.optString(key)));
			}
			else if(type == JSONValueType.BOOLEAN) {
				Class<T> t = (Class<T>)Boolean.class;
				try {
					Integer b = Integer.parseInt(this.jsonObject.optString(key));
					return (b == 1) ? t.cast(Boolean.TRUE) : t.cast(Boolean.FALSE);
				} catch (Exception e) {
					return t.cast(Boolean.valueOf(this.jsonObject.optString(key)));
				}
			}
			else if(type == JSONValueType.ARRAYLIST) {
				Class<T> t = (Class<T>)ArrayList.class;
				
				ArrayList<String> list = new ArrayList<String>();
				JSONArray arr = this.jsonObject.optJSONArray(key);
				for(int i = 0; i < arr.length(); i++) {
					list.add(arr.getString(i));
				}
				
				return t.cast(list);
			}
			else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

}
