package ru.iqwanoino.elicia;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ChatHistory {
	private static final String PREF_NAME = "ChatHistory";
	private static final String KEY_QUESTIONS = "questions";
	private static final String KEY_ANSWERS = "answers";
	
	public static void saveChat(Context context, ArrayList<String> questions, ArrayList<HashMap<String, Object>> answers) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		JSONArray questionArray = new JSONArray(questions);
		JSONArray answerArray = new JSONArray();
		
		for (HashMap<String, Object> item : answers) {
			JSONObject jsonObject = new JSONObject(item);
			answerArray.put(jsonObject);
		}
		
		editor.putString(KEY_QUESTIONS, questionArray.toString());
		editor.putString(KEY_ANSWERS, answerArray.toString());
		editor.apply();
	}
	
	public static void loadChat(Context context, ArrayList<String> questions, ArrayList<HashMap<String, Object>> answers) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		questions.clear();
		answers.clear();
		
		String questionJson = sharedPreferences.getString(KEY_QUESTIONS, "[]");
		String answerJson = sharedPreferences.getString(KEY_ANSWERS, "[]");
		
		try {
			JSONArray questionArray = new JSONArray(questionJson);
			JSONArray answerArray = new JSONArray(answerJson);
			
			for (int i = 0; i < questionArray.length(); i++) {
				questions.add(questionArray.getString(i));
			}
			
			for (int i = 0; i < answerArray.length(); i++) {
				JSONObject jsonObject = answerArray.getJSONObject(i);
				HashMap<String, Object> item = new HashMap<>();
				
				item.put("jawaban", jsonObject.getString("jawaban"));
				item.put("thumb_up", jsonObject.optBoolean("thumb_up", false));
				item.put("thumb_down", jsonObject.optBoolean("thumb_down", false));
				
				answers.add(item);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public static void exportToJsonFile(Context context, ArrayList<String> questions, ArrayList<HashMap<String, Object>> answers) {
		try {
			JSONArray chatArray = new JSONArray();
			
			int size = Math.min(questions.size(), answers.size());
			for (int i = 0; i < size; i++) {
				// User
				JSONObject userObj = new JSONObject();
				userObj.put("User", questions.get(i));
				chatArray.put(userObj);
				
				// Elicia
				JSONObject eliciaObj = new JSONObject();
				eliciaObj.put("Elicia", String.valueOf(answers.get(i).get("jawaban")));
				chatArray.put(eliciaObj);
			}
			
			// Format nama file
			String timeStamp = new SimpleDateFormat("HH.mm.ss_dd-MMM-yyyy").format(new Date());
			String fileName = "EliciaAIChat_" + timeStamp + ".json";
			
			// Lokasi folder
			File folder = new File("/storage/emulated/0/Download/EliciaAI/SavedChats/");
			if (!folder.exists()) folder.mkdirs();
			
			File file = new File(folder, fileName);
			FileWriter writer = new FileWriter(file);
			writer.write(chatArray.toString(4));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}