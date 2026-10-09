package ru.iqwanoino.elicia;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Random;

public class ChatbotProcessor {
    private JSONObject jsonObject;

    public ChatbotProcessor(Context context, String fileName) {
        loadJson(context, fileName);
    }

    private void loadJson(Context context, String fileName) {
        try {
            String json;
            if (fileName.startsWith("/")) {
                File file = new File(fileName);
                if (!file.exists()) {
                    Log.e("ChatbotProcessor", "File tidak ditemukan: " + fileName);
                    return;
                }

                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();

                json = new String(buffer, StandardCharsets.UTF_8);
            } else {
                InputStream is = context.getAssets().open(fileName);
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                is.close();

                json = new String(buffer, StandardCharsets.UTF_8);
            }

            jsonObject = new JSONObject(json);
            Log.d("ChatbotProcessor", "File JSON berhasil dimuat!");
        } catch (IOException | JSONException e) {
            Log.e("ChatbotProcessor", "Gagal memuat JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getResponse(String userInput) {
        if (jsonObject == null) {
            return "Error: JSON tidak tersedia.";
        }

        userInput = userInput.trim().toLowerCase();

        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (StringSimilarity.isSimilar(userInput, key)) {
                try {
                    JSONArray answers = jsonObject.getJSONArray(key);
                    return answers.getString(new Random().nextInt(answers.length()));
                } catch (JSONException e) {
                    Log.e("ChatbotProcessor", "Error membaca JSON untuk: " + key);
                    e.printStackTrace();
                }
            }
        }

        // Tidak ada match
        return "```Not Found```";
    }
}