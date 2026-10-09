package ru.iqwanoino.elicia;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import org.json.JSONObject;

public class ApiConfig {
	private static final String PREF = "EliciaApi";
	private static final String KEY_API = "api_key";
	private static final String KEY_MODEL = "model";
	private static final String KEY_BASE = "base_url";

	public static class Provider {
		public final String name;
		public final String baseUrl;
		public final String defaultModel;

		Provider(String name, String baseUrl, String defaultModel) {
			this.name = name;
			this.baseUrl = baseUrl;
			this.defaultModel = defaultModel;
		}
	}

	// Deteksi provider otomatis dari format API key.
	public static Provider detect(String key) {
		String k = key == null ? "" : key.trim();
		if (k.startsWith("sk-ant-")) return new Provider("Anthropic", "https://api.anthropic.com/v1", "claude-sonnet-4-5");
		if (k.startsWith("sk-or-")) return new Provider("OpenRouter", "https://openrouter.ai/api/v1", "openai/gpt-4o-mini");
		if (k.startsWith("sk-")) return new Provider("OpenAI", "https://api.openai.com/v1", "gpt-4o-mini");
		if (k.startsWith("AIza")) return new Provider("Gemini", "https://generativelanguage.googleapis.com/v1beta/openai", "gemini-2.0-flash");
		if (k.startsWith("gsk_")) return new Provider("Groq", "https://api.groq.com/openai/v1", "llama-3.3-70b-versatile");
		if (k.startsWith("xai-")) return new Provider("xAI", "https://api.x.ai/v1", "grok-3-mini");
		if (k.startsWith("pplx-")) return new Provider("Perplexity", "https://api.perplexity.ai", "sonar");
		return new Provider("Tidak dikenali (pakai OpenAI-compatible)", "https://api.openai.com/v1", "gpt-4o-mini");
	}

	public static void save(Context c, String key, String model, String baseUrl) {
		prefs(c).edit()
			.putString(KEY_API, key.trim())
			.putString(KEY_MODEL, model.trim())
			.putString(KEY_BASE, baseUrl.trim())
			.apply();
	}

	public static String getKey(Context c) {
		return prefs(c).getString(KEY_API, "");
	}

	public static String getSavedModel(Context c) {
		return prefs(c).getString(KEY_MODEL, "");
	}

	public static String getSavedBase(Context c) {
		return prefs(c).getString(KEY_BASE, "");
	}

	public static String getModel(Context c) {
		String m = getSavedModel(c);
		return m.isEmpty() ? detect(getKey(c)).defaultModel : m;
	}

	public static String getBase(Context c) {
		String b = getSavedBase(c);
		return b.isEmpty() ? detect(getKey(c)).baseUrl : b;
	}

	public static String endpoint(Context c) {
		String b = getBase(c);
		if (b.endsWith("/")) b = b.substring(0, b.length() - 1);
		return b + "/chat/completions";
	}

	public static HashMap<String, Object> headers(Context c) {
		HashMap<String, Object> h = new HashMap<>();
		h.put("Authorization", "Bearer " + getKey(c));
		return h;
	}

	public static String errorOf(JSONObject o, String fallback) {
		JSONObject err = o.optJSONObject("error");
		if (err != null) return err.optString("message", fallback);
		return o.optString("message", fallback);
	}

	private static SharedPreferences prefs(Context c) {
		return c.getSharedPreferences(PREF, Context.MODE_PRIVATE);
	}
}
