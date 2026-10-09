package ru.iqwanoino.elicia;

import android.app.Activity;
import android.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ApiSettingsDialog {

	public static void show(final Activity activity) {
		int pad = dp(activity, 20);
		LinearLayout root = new LinearLayout(activity);
		root.setOrientation(LinearLayout.VERTICAL);
		root.setPadding(pad, pad, pad, 0);

		final EditText keyInput = field(activity, "API key (OpenAI, Claude, Gemini, Groq, xAI, dll)", ApiConfig.getKey(activity), true);
		final TextView detected = new TextView(activity);
		detected.setPadding(0, dp(activity, 6), 0, dp(activity, 6));
		final EditText modelInput = field(activity, "Model (kosongkan untuk default)", ApiConfig.getSavedModel(activity), false);
		final EditText baseInput = field(activity, "Base URL (opsional, kosongkan untuk otomatis)", ApiConfig.getSavedBase(activity), false);

		root.addView(keyInput);
		root.addView(detected);
		root.addView(modelInput);
		root.addView(baseInput);

		updateDetected(detected, keyInput.getText().toString());
		keyInput.addTextChangedListener(new TextWatcher() {
			@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override public void onTextChanged(CharSequence s, int start, int before, int count) {
				updateDetected(detected, s.toString());
			}
			@Override public void afterTextChanged(Editable s) {}
		});

		new AlertDialog.Builder(activity)
			.setTitle("API Settings")
			.setView(root)
			.setPositiveButton("Simpan", (d, w) -> {
				ApiConfig.save(activity,
					keyInput.getText().toString(),
					modelInput.getText().toString(),
					baseInput.getText().toString());
				Toast.makeText(activity, "API settings disimpan", Toast.LENGTH_SHORT).show();
			})
			.setNegativeButton("Batal", null)
			.show();
	}

	private static void updateDetected(TextView view, String key) {
		ApiConfig.Provider p = ApiConfig.detect(key);
		view.setText("Terdeteksi: " + p.name + " · model default: " + p.defaultModel);
	}

	private static EditText field(Activity a, String hint, String value, boolean password) {
		EditText e = new EditText(a);
		e.setHint(hint);
		e.setText(value);
		e.setSingleLine(true);
		if (password) e.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		return e;
	}

	private static int dp(Activity a, int v) {
		return (int) (v * a.getResources().getDisplayMetrics().density);
	}
}
