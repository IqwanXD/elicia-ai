package ru.iqwanoino.elicia;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;

public class MainActivity extends Activity {

	private static final String SYSTEM_PROMPT =
		"Kamu adalah Elicia, asisten AI yang ramah. Jawab dalam bahasa yang sama dengan pengguna.";
	private static final int HISTORY_PAIRS = 10;

	private final ArrayList<String> questions = new ArrayList<>();
	private final ArrayList<HashMap<String, Object>> answers = new ArrayList<>();

	private ListView chatList;
	private TextView emptyText;
	private EditText inputQuestion;
	private Button sendButton;
	private ChatAdapter adapter;
	private RequestNetwork network;
	private RequestNetwork.RequestListener listener;
	private int pendingIndex = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		chatList = findViewById(R.id.chat_list);
		emptyText = findViewById(R.id.empty_text);
		inputQuestion = findViewById(R.id.input_question);
		sendButton = findViewById(R.id.send_button);
		ImageButton menuButton = findViewById(R.id.menu_button);

		ChatHistory.loadChat(this, questions, answers);
		adapter = new ChatAdapter();
		chatList.setAdapter(adapter);
		chatList.setEmptyView(emptyText);
		adapter.notifyDataSetChanged();

		network = new RequestNetwork(this);
		listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String tag, String response, HashMap<String, Object> headers) {
				String text;
				try {
					JSONObject object = new JSONObject(response);
					if (object.has("choices")) {
						text = object.getJSONArray("choices").getJSONObject(0)
							.getJSONObject("message").getString("content");
					} else {
						text = "Gagal: " + ApiConfig.errorOf(object, "Tidak diketahui");
					}
				} catch (Exception e) {
					text = "Maaf, respons dari server tidak bisa dibaca.";
				}
				finishAnswer(text);
			}

			@Override
			public void onErrorResponse(String tag, String message) {
				finishAnswer("Gagal terhubung: " + message);
			}
		};

		sendButton.setOnClickListener(v -> send());
		menuButton.setOnClickListener(v -> showMenu());
	}

	private void send() {
		if (pendingIndex >= 0) return;

		String text = inputQuestion.getText().toString().trim();
		if (text.isEmpty()) return;

		if (ApiConfig.getKey(this).isEmpty()) {
			Toast.makeText(this, "Isi API key dulu di menu API Settings", Toast.LENGTH_SHORT).show();
			ApiSettingsDialog.show(this);
			return;
		}

		questions.add(text);
		HashMap<String, Object> placeholder = new HashMap<>();
		placeholder.put("jawaban", "Sedang berpikir...");
		answers.add(placeholder);
		pendingIndex = answers.size() - 1;

		inputQuestion.setText("");
		setBusy(true);
		refreshChat();

		ArrayList<HashMap<String, Object>> messages = new ArrayList<>();
		messages.add(message("system", SYSTEM_PROMPT));
		int start = Math.max(0, pendingIndex - HISTORY_PAIRS);
		for (int i = start; i < pendingIndex; i++) {
			messages.add(message("user", questions.get(i)));
			messages.add(message("assistant", String.valueOf(answers.get(i).get("jawaban"))));
		}
		messages.add(message("user", text));

		HashMap<String, Object> request = new HashMap<>();
		request.put("model", ApiConfig.getModel(this));
		request.put("messages", messages);

		network.setHeaders(ApiConfig.headers(this));
		network.setParams(request, RequestNetworkController.REQUEST_BODY);
		network.startRequestNetwork(RequestNetworkController.POST, ApiConfig.endpoint(this), "", listener);
	}

	private void finishAnswer(String text) {
		if (pendingIndex >= 0 && pendingIndex < answers.size()) {
			answers.get(pendingIndex).put("jawaban", text);
		}
		pendingIndex = -1;
		ChatHistory.saveChat(this, questions, answers);
		setBusy(false);
		refreshChat();
	}

	private void setBusy(boolean busy) {
		sendButton.setEnabled(!busy);
		inputQuestion.setEnabled(!busy);
	}

	private void refreshChat() {
		adapter.notifyDataSetChanged();
		chatList.setSelection(adapter.getCount() - 1);
	}

	private void showMenu() {
		new AlertDialog.Builder(this)
			.setItems(new String[] {"API Settings", "Hapus riwayat chat"}, (d, which) -> {
				if (which == 0) {
					ApiSettingsDialog.show(this);
				} else {
					confirmClearHistory();
				}
			})
			.show();
	}

	private void confirmClearHistory() {
		new AlertDialog.Builder(this)
			.setTitle("Hapus riwayat?")
			.setMessage("Semua chat akan dihapus.")
			.setPositiveButton("Hapus", (d, w) -> {
				if (pendingIndex >= 0) return;
				questions.clear();
				answers.clear();
				ChatHistory.saveChat(this, questions, answers);
				refreshChat();
			})
			.setNegativeButton("Batal", null)
			.show();
	}

	private static HashMap<String, Object> message(String role, String content) {
		HashMap<String, Object> m = new HashMap<>();
		m.put("role", role);
		m.put("content", content);
		return m;
	}

	private int dp(int v) {
		return (int) (v * getResources().getDisplayMetrics().density);
	}

	private class ChatAdapter extends BaseAdapter {
		private int pairs() {
			return Math.min(questions.size(), answers.size());
		}

		@Override
		public int getCount() {
			return pairs() * 2;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			int index = position / 2;
			boolean isUser = position % 2 == 0;
			String text = isUser
				? questions.get(index)
				: String.valueOf(answers.get(index).get("jawaban"));

			LinearLayout row = new LinearLayout(MainActivity.this);
			row.setOrientation(LinearLayout.HORIZONTAL);
			row.setGravity(isUser ? Gravity.END : Gravity.START);
			row.setPadding(0, dp(4), 0, dp(4));

			TextView bubble = new TextView(MainActivity.this);
			bubble.setText(text);
			bubble.setTextColor(0xFFFFFFFF);
			bubble.setTextSize(15);
			bubble.setPadding(dp(12), dp(10), dp(12), dp(10));

			GradientDrawable bg = new GradientDrawable();
			bg.setCornerRadius(dp(14));
			bg.setColor(isUser ? 0xFF2A3B5C : 0xFF1C2028);
			bubble.setBackground(bg);

			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			lp.setMargins(isUser ? dp(48) : 0, 0, isUser ? 0 : dp(48), 0);
			row.addView(bubble, lp);
			return row;
		}
	}
}
