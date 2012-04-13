package voter.main;

import java.util.Map;

import org.jsoup.Jsoup;

import android.util.Log;

/** Wrapper for JSON */
public final class JsonConnection {

	private final String url;
	
	public JsonConnection(String connectionURL) {
		this.url = connectionURL;
	}
	
	// Post map to server
	public JsonResult post(Map<String, String> data) {
		try {
			return new JsonResult(Jsoup.connect(this.url).data(data).post().text());
		}
		catch(Exception e) {
			Log.e("", e.getMessage());
			return null;
		}
		
	}
	
	// Get data from server 
	public JsonResult get() {
		try {
			return new JsonResult(Jsoup.connect(this.url).get().text());
		}
		catch(Exception e) {
			return null;
		}
	}
}
