package ru.iqwanoino.elicia;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import android.view.Window;
import android.view.WindowManager;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Paint;
import android.graphics.Color;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.provider.Settings;
import android.content.Context;
import android.widget.Toast;
import android.database.Cursor;
import android.os.AsyncTask;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import android.speech.tts.UtteranceProgressListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;

public class MainActivity extends Activity {
	
	public final int REQ_CD_PROFILE = 101;
	
	private String data = "";
	private HashMap<String, Object> request = new HashMap<>();
	private HashMap<String, Object> resource = new HashMap<>();
	private boolean micdata = false;
	private double r = 0;
	private double g = 0;
	private double b = 0;
	private String colorPicked = "";
	
	private ArrayList<String> question = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> answer = new ArrayList<>();
	
	private LinearLayout splashscreen;
	private LinearLayout background;
	private ImageView splashicon;
	private TextView appname;
	private TextView appinfo;
	private LinearLayout elicia_screen;
	private LinearLayout account_screen;
	private LinearLayout textselection_screen;
	private LinearLayout livechat_screen;
	private LinearLayout toolbar;
	private TextView system_account;
	private TextView system_model;
	private TextView apptheme_system;
	private FrameLayout listchat_screen;
	private LinearLayout input_background;
	private TextView accpreview_system;
	private LinearLayout accountbg;
	private LinearLayout appbeta_namelayout;
	private ImageView newconversation_button;
	private ImageView account_button;
	private LinearLayout acctext_button;
	private TextView acctext;
	private TextView elicia_data;
	private TextView app_name;
	private LinearLayout previewmodel_background;
	private TextView preview_model;
	private LinearLayout welcome_background;
	private ListView flowchat_background;
	private LinearLayout scrolldown_button;
	private LinearLayout inoring;
	private TextView welcome_user;
	private TextView welcome_message;
	private ImageView scrolldwon_icon;
	private EditText input_question;
	private LinearLayout inputquestion_background;
	private ImageView clear_button;
	private TextView mic_system;
	private ImageView micquestion_button;
	private ImageView directquestion_button;
	private ImageView sendquestion_button;
	private LinearLayout progress_background;
	private ImageView shutup_button;
	private ProgressBar progress;
	private TextView system_image;
	private TextView language_system;
	private LinearLayout userpreview_background;
	private ScrollView setting_scrollbackground;
	private ImageView accimage_preview;
	private LinearLayout acctext_background;
	private TextView username_preview;
	private TextView userinfo_preview;
	private TextView acctext_preview;
	private LinearLayout setting_background;
	private TextView title_settingscreen;
	private TextView subtitle_settingscreen;
	private LinearLayout language_setting_background;
	private LinearLayout theme_setting_background;
	private TextView spacebar_settingscreen;
	private TextView settitle_language;
	private TextView lang_selected;
	private TextView settitle_theme;
	private TextView subtitle_theme;
	private LinearLayout switch_theme_background;
	private LinearLayout switch_account_background;
	private LinearLayout setbackground_theme;
	private Switch switch_theme;
	private TextView setname_theme;
	private TextView subname_theme;
	private LinearLayout setbackground_account;
	private Switch switch_account;
	private TextView setname_account;
	private TextView subname_account;
	private TextView system_pertanyaan;
	private LinearLayout toolbar_textselection_screen;
	private ScrollView textselection_vscrollbackground;
	private ImageView goback_button_textselectionscreen;
	private TextView textseletction_title;
	private LinearLayout textselection_basebackground;
	private TextView system_jawaban;
	private LinearLayout liverespone_background;
	private LinearLayout control_livechat_button_background;
	private LinearLayout liveresponse_animation;
	private LinearLayout livepaused_background;
	private TextView titlepaused;
	private TextView subtitlepaused;
	private TextView messagepaused;
	private LinearLayout control_startlivechat_background;
	private LinearLayout control_closelivechat_background;
	private LinearLayout button_startlivechat_background;
	private TextView indicator_startlivechat;
	private ProgressBar progress_livechat;
	private ImageView start_button;
	private LinearLayout button_closelivechat_background;
	private TextView indicator_close;
	private ImageView close_button;
	
	private Intent profile = new Intent(Intent.ACTION_GET_CONTENT);
	private SharedPreferences user;
	private SpeechRecognizer suaraketeks;
	private TextToSpeech tekskesuara;
	private RequestNetwork openai;
	private RequestNetwork.RequestListener _openai_request_listener;
	private SpeechRecognizer micquestion;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
			||checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
				requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
			} else {
				initializeLogic();
			}
		} else {
			initializeLogic();
			
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		splashscreen = findViewById(R.id.splashscreen);
		background = findViewById(R.id.background);
		splashicon = findViewById(R.id.splashicon);
		appname = findViewById(R.id.appname);
		appinfo = findViewById(R.id.appinfo);
		elicia_screen = findViewById(R.id.elicia_screen);
		account_screen = findViewById(R.id.account_screen);
		textselection_screen = findViewById(R.id.textselection_screen);
		livechat_screen = findViewById(R.id.livechat_screen);
		toolbar = findViewById(R.id.toolbar);
		system_account = findViewById(R.id.system_account);
		system_model = findViewById(R.id.system_model);
		apptheme_system = findViewById(R.id.apptheme_system);
		listchat_screen = findViewById(R.id.listchat_screen);
		input_background = findViewById(R.id.input_background);
		accpreview_system = findViewById(R.id.accpreview_system);
		accountbg = findViewById(R.id.accountbg);
		appbeta_namelayout = findViewById(R.id.appbeta_namelayout);
		newconversation_button = findViewById(R.id.newconversation_button);
		account_button = findViewById(R.id.account_button);
		acctext_button = findViewById(R.id.acctext_button);
		acctext = findViewById(R.id.acctext);
		elicia_data = findViewById(R.id.elicia_data);
		app_name = findViewById(R.id.app_name);
		previewmodel_background = findViewById(R.id.previewmodel_background);
		preview_model = findViewById(R.id.preview_model);
		welcome_background = findViewById(R.id.welcome_background);
		flowchat_background = findViewById(R.id.flowchat_background);
		scrolldown_button = findViewById(R.id.scrolldown_button);
		inoring = findViewById(R.id.inoring);
		welcome_user = findViewById(R.id.welcome_user);
		welcome_message = findViewById(R.id.welcome_message);
		scrolldwon_icon = findViewById(R.id.scrolldwon_icon);
		input_question = findViewById(R.id.input_question);
		inputquestion_background = findViewById(R.id.inputquestion_background);
		clear_button = findViewById(R.id.clear_button);
		mic_system = findViewById(R.id.mic_system);
		micquestion_button = findViewById(R.id.micquestion_button);
		directquestion_button = findViewById(R.id.directquestion_button);
		sendquestion_button = findViewById(R.id.sendquestion_button);
		progress_background = findViewById(R.id.progress_background);
		shutup_button = findViewById(R.id.shutup_button);
		progress = findViewById(R.id.progress);
		system_image = findViewById(R.id.system_image);
		language_system = findViewById(R.id.language_system);
		userpreview_background = findViewById(R.id.userpreview_background);
		setting_scrollbackground = findViewById(R.id.setting_scrollbackground);
		accimage_preview = findViewById(R.id.accimage_preview);
		acctext_background = findViewById(R.id.acctext_background);
		username_preview = findViewById(R.id.username_preview);
		userinfo_preview = findViewById(R.id.userinfo_preview);
		acctext_preview = findViewById(R.id.acctext_preview);
		setting_background = findViewById(R.id.setting_background);
		title_settingscreen = findViewById(R.id.title_settingscreen);
		subtitle_settingscreen = findViewById(R.id.subtitle_settingscreen);
		language_setting_background = findViewById(R.id.language_setting_background);
		theme_setting_background = findViewById(R.id.theme_setting_background);
		spacebar_settingscreen = findViewById(R.id.spacebar_settingscreen);
		settitle_language = findViewById(R.id.settitle_language);
		lang_selected = findViewById(R.id.lang_selected);
		settitle_theme = findViewById(R.id.settitle_theme);
		subtitle_theme = findViewById(R.id.subtitle_theme);
		switch_theme_background = findViewById(R.id.switch_theme_background);
		switch_account_background = findViewById(R.id.switch_account_background);
		setbackground_theme = findViewById(R.id.setbackground_theme);
		switch_theme = findViewById(R.id.switch_theme);
		setname_theme = findViewById(R.id.setname_theme);
		subname_theme = findViewById(R.id.subname_theme);
		setbackground_account = findViewById(R.id.setbackground_account);
		switch_account = findViewById(R.id.switch_account);
		setname_account = findViewById(R.id.setname_account);
		subname_account = findViewById(R.id.subname_account);
		system_pertanyaan = findViewById(R.id.system_pertanyaan);
		toolbar_textselection_screen = findViewById(R.id.toolbar_textselection_screen);
		textselection_vscrollbackground = findViewById(R.id.textselection_vscrollbackground);
		goback_button_textselectionscreen = findViewById(R.id.goback_button_textselectionscreen);
		textseletction_title = findViewById(R.id.textseletction_title);
		textselection_basebackground = findViewById(R.id.textselection_basebackground);
		system_jawaban = findViewById(R.id.system_jawaban);
		liverespone_background = findViewById(R.id.liverespone_background);
		control_livechat_button_background = findViewById(R.id.control_livechat_button_background);
		liveresponse_animation = findViewById(R.id.liveresponse_animation);
		livepaused_background = findViewById(R.id.livepaused_background);
		titlepaused = findViewById(R.id.titlepaused);
		subtitlepaused = findViewById(R.id.subtitlepaused);
		messagepaused = findViewById(R.id.messagepaused);
		control_startlivechat_background = findViewById(R.id.control_startlivechat_background);
		control_closelivechat_background = findViewById(R.id.control_closelivechat_background);
		button_startlivechat_background = findViewById(R.id.button_startlivechat_background);
		indicator_startlivechat = findViewById(R.id.indicator_startlivechat);
		progress_livechat = findViewById(R.id.progress_livechat);
		start_button = findViewById(R.id.start_button);
		button_closelivechat_background = findViewById(R.id.button_closelivechat_background);
		indicator_close = findViewById(R.id.indicator_close);
		close_button = findViewById(R.id.close_button);
		profile.setType("image/*");
		profile.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		user = getSharedPreferences("data", Activity.MODE_PRIVATE);
		suaraketeks = SpeechRecognizer.createSpeechRecognizer(this);
		tekskesuara = new TextToSpeech(getApplicationContext(), null);
		openai = new RequestNetwork(this);
		micquestion = SpeechRecognizer.createSpeechRecognizer(this);
		
		account_screen.setOnClickListener(_v -> {
			UiHelper.hideKeyboard(MainActivity.this);
			input_question.requestFocus();
		});
		
		newconversation_button.setOnClickListener(_v -> {
			ChatHistory.exportToJsonFile(MainActivity.this, question, answer);
			
			((BaseAdapter) flowchat_background.getAdapter()).notifyDataSetChanged();
			SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("ChatHistory", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.clear();
			editor.apply();
			question.clear();
			answer.clear();
			
			if (flowchat_background.getVisibility() == View.VISIBLE) {
				input_question.clearFocus();
				flowchat_background.setVisibility(View.GONE);
				welcome_background.setScaleX(0.8f);
				welcome_background.setScaleY(0.8f);
				welcome_background.setAlpha(0f);
				welcome_background.setVisibility(View.VISIBLE);
				welcome_background.animate()
				.scaleX(1f)
				.scaleY(1f)
				.alpha(1f)
				.setDuration(250)
				.setInterpolator(new DecelerateInterpolator())
				.start();
				flowchat_background.smoothScrollToPosition(flowchat_background.getCount() - 1);
				scrolldown_button.setScaleX(1f);
				scrolldown_button.setScaleY(1f);
				scrolldown_button.setAlpha(0f);
				scrolldown_button.setVisibility(View.GONE);
				scrolldown_button.animate()
				.scaleX(0.80f)
				.scaleY(0.80f)
				.alpha(0f)
				.setDuration(250)
				.setInterpolator(new DecelerateInterpolator())
				.start();
			}
		});
		
		account_button.setOnClickListener(_v -> showAccountScreen());
		
		acctext_button.setOnClickListener(_v -> showAccountScreen());
		
		previewmodel_background.setOnClickListener(_v -> {
			// Ambil bahasa sistem
			String lang = language_system.getText().toString();
			
			// SharedPreferences
			SharedPreferences eliciaPrefs = getSharedPreferences("EliciaSettings", MODE_PRIVATE);
			final SharedPreferences.Editor editor = eliciaPrefs.edit();
			
			// Label tampilan
			Map<String, String[]> displayLabels = new HashMap<>();
			displayLabels.put("id", new String[]{"Model Dasar", "Model Daring"});
			displayLabels.put("en", new String[]{"Basic Model", "Online Model"});
			displayLabels.put("pt", new String[]{"Modelo Básico", "Modelo Online"});
			displayLabels.put("ru", new String[]{"Базовая модель", "Онлайн-модель"});
			displayLabels.put("ch", new String[]{"基础模型", "在线模型"});
			displayLabels.put("ar", new String[]{"النموذج الأساسي", "النموذج عبر الإنترنت"});
			displayLabels.put("tr", new String[]{"Temel Model", "Çevrimiçi Model"});
			
			// Pesan toast per bahasa
			Map<String, String> toastTexts = new HashMap<>();
			toastTexts.put("id", "Model diganti");
			toastTexts.put("en", "Model changed");
			toastTexts.put("pt", "Modelo alterado");
			toastTexts.put("ru", "Модель изменена");
			toastTexts.put("ch", "模型已更改");
			toastTexts.put("ar", "تم تغيير النموذج");
			toastTexts.put("tr", "Model değiştirildi");
			
			String[] display;
			if (lang != null && displayLabels.containsKey(lang)) {
				display = displayLabels.get(lang);
			} else {
				display = displayLabels.get("en");
			}
			
			String toastMessage;
			if (lang != null && toastTexts.containsKey(lang)) {
				toastMessage = toastTexts.get(lang);
			} else {
				toastMessage = toastTexts.get("en");
			}
			
			String ttlText;
			if ("id".equals(lang)) {
				ttlText = "Pilih model";
			} else if ("en".equals(lang)) {
				ttlText = "Choose model";
			} else if ("pt".equals(lang)) {
				ttlText = "Escolha o modelo";
			} else if ("ru".equals(lang)) {
				ttlText = "Выберите модель";
			} else if ("ch".equals(lang)) {
				ttlText = "选择模型";
			} else if ("ar".equals(lang)) {
				ttlText = "اختر النموذج";
			} else if ("tr".equals(lang)) {
				ttlText = "Model seç";
			} else {
				ttlText = "Choose model";
			}
			
			Dialog dialog = new Dialog(MainActivity.this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setCancelable(true);
			
			float dp = getResources().getDisplayMetrics().density;
			
			LinearLayout root = new LinearLayout(MainActivity.this);
			root.setOrientation(LinearLayout.VERTICAL);
			root.setPadding((int)(24 * dp), (int)(24 * dp), (int)(24 * dp), (int)(16 * dp));
			root.setBackground(new GradientDrawable() {{
					setColor(Color.parseColor("#1C1C1E"));
					setCornerRadius(32 * dp);
				}});
			
			TextView title = new TextView(MainActivity.this);
			title.setText(ttlText);
			title.setTextSize(20);
			title.setTextColor(Color.parseColor("#FFFFFF"));
			title.setPadding(0, 0, 0, (int)(12 * dp));
			root.addView(title);
			
			TextView msg = new TextView(MainActivity.this);
			if ("id".equals(lang)) {
				msg.setText("Pilih model elicia di sini");
			} else if ("en".equals(lang)) {
				msg.setText("Choose elicia model here");
			} else if ("pt".equals(lang)) {
				msg.setText("Escolha o modelo elicia aqui");
			} else if ("ru".equals(lang)) {
				msg.setText("Выберите модель elicia здесь");
			} else if ("ch".equals(lang)) {
				msg.setText("在这里选择elicia模型");
			} else if ("ar".equals(lang)) {
				msg.setText("اختر نموذج إليسيا هنا");
			} else if ("tr".equals(lang)) {
				msg.setText("Elicia modelini burada seçin");
			} else {
				msg.setText("Choose elicia model here");
			}
			msg.setTextSize(14);
			msg.setTextColor(Color.parseColor("#B0B0B0")); 
			msg.setPadding(0, 0, 0, (int)(24 * dp));
			root.addView(msg);
			
			LinearLayout.LayoutParams paramsCancel = new LinearLayout.LayoutParams(
			ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			paramsCancel.setMargins(0, (int)(8 * dp), 0, (int)(8 * dp));
			
			TextView btnOk = new TextView(MainActivity.this);
			btnOk.setText(display[0]);
			btnOk.setTextSize(14);
			btnOk.setTypeface(null, Typeface.BOLD);
			btnOk.setGravity(Gravity.CENTER);
			btnOk.setTextColor(Color.parseColor("#FFFFFF"));
			btnOk.setBackground(new GradientDrawable() {{
					setColor(Color.parseColor("#3F51B5"));
					setCornerRadii(new float[]{
						18 * dp, 18 * dp,
						18 * dp, 18 * dp,
						6 * dp, 6 * dp,
						6 * dp, 6 * dp
					});
				}});
			btnOk.setPadding((int)(16 * dp), (int)(16 * dp), (int)(16 * dp), (int)(16 * dp));
			btnOk.setOnClickListener(v -> {
				editor.putString("model", "basic");
				editor.apply();
				
				system_model.setText("basic");
				preview_model.setText(display[0]);
				dialog.dismiss();
			});
			root.addView(btnOk);
			
			TextView btnCancel = new TextView(MainActivity.this);
			btnCancel.setText(display[1]);
			btnCancel.setTextSize(14);
			btnCancel.setTypeface(null, Typeface.BOLD);
			btnCancel.setGravity(Gravity.CENTER);
			btnCancel.setTextColor(Color.parseColor("#FFFFFF"));
			btnCancel.setBackground(new GradientDrawable() {{
					setColor(Color.parseColor("#3F51B5"));
					setCornerRadii(new float[]{
						6 * dp, 6 * dp,  
						6 * dp, 6 * dp,   
						18 * dp, 18 * dp,
						18 * dp, 18 * dp 
					});
				}});
			btnCancel.setPadding((int)(16 * dp), (int)(16 * dp), (int)(16 * dp), (int)(16 * dp));
			btnCancel.setOnClickListener(v -> {
				editor.putString("model", "online");
				editor.apply();
				
				system_model.setText("online");
				preview_model.setText(display[1]);
				dialog.dismiss();
			});
			btnCancel.setLayoutParams(paramsCancel);
			root.addView(btnCancel);
			
			dialog.setContentView(root);
			Window w = dialog.getWindow();
			if (w != null) {
				int width = (int)(getResources().getDisplayMetrics().widthPixels * 0.9f);
				w.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
				w.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				w.setGravity(Gravity.CENTER);
			}
			dialog.show();
			
		});
		
		clear_button.setOnClickListener(_v -> {
			clear_button.animate()
			.scaleX(0.8f)
			.scaleY(0.8f)
			.setDuration(100);
			clear_button.setVisibility(View.GONE);
			input_question.setText("");
			directquestion_button.setScaleX(0.8f);
			directquestion_button.setScaleY(0.8f);
			directquestion_button.setAlpha(0f);
			directquestion_button.setVisibility(View.VISIBLE);
			
			directquestion_button.animate()
			.scaleX(1f)
			.scaleY(1f)
			.alpha(1f)
			.setDuration(250)
			.setInterpolator(new DecelerateInterpolator())
			.start();
			clear_button.animate()
			.scaleX(1f)
			.scaleY(1f)
			.setDuration(100);
			
		});
		
		micquestion_button.setOnClickListener(_v -> {
			micquestion_button.animate()
			.scaleX(0.8f)
			.scaleY(0.8f)
			.setDuration(100);
			if (mic_system.getText().toString().equals("0")) {
				micquestion_button.setColorFilter(0xFF3F51B5, PorterDuff.Mode.MULTIPLY);
				Intent _intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				_intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
				_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
				_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
				micquestion.startListening(_intent);
				mic_system.setText("1");
				directquestion_button.setEnabled(false);
				input_question.setEnabled(false);
				if (tekskesuara.isSpeaking()) {
					tekskesuara.shutdown();
				}
			} else {
				micquestion_button.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				micquestion.stopListening();
				mic_system.setText("0");
				directquestion_button.setEnabled(true);
				input_question.setEnabled(true);
			}
			micquestion_button.animate()
			.scaleX(1f)
			.scaleY(1f)
			.setDuration(100);
		});
		
		directquestion_button.setOnClickListener(_v -> {
			directquestion_button.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100);
			UiHelper.hideKeyboard(MainActivity.this);
			directquestion_button.animate().scaleX(1f).scaleY(1f).setDuration(100);
			AnimatorSet animatorSet = new AnimatorSet();
			ObjectAnimator slideIn = ObjectAnimator.ofFloat(livechat_screen, "translationY", 500f, 0f);
			slideIn.setDuration(300);
			ObjectAnimator fadeIn = ObjectAnimator.ofFloat(livechat_screen, "alpha", 0f, 1f);
			fadeIn.setDuration(300);
			animatorSet.playTogether(slideIn, fadeIn);
			animatorSet.start();
			livechat_screen.setVisibility(View.VISIBLE);
			elicia_screen.setVisibility(View.GONE);
			account_screen.setVisibility(View.GONE);
		});
		
		sendquestion_button.setOnClickListener(_v -> {
			String lang = language_system.getText().toString();
			languageSet();
			
			if (system_model.getText().toString().equals("basic")) {
				String userInput = ((EditText) findViewById(R.id.input_question)).getText().toString().trim();
				
				if (userInput.isEmpty()) {
					showCustomToast(getApplicationContext(), toastMessage, false,  "#50212121" , 21 , "#FFFFFF", 15, 1);
					return;
				}
				
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
				SimpleDateFormat hourFormat = new SimpleDateFormat("HH", Locale.getDefault());
				SimpleDateFormat minuteFormat = new SimpleDateFormat("mm", Locale.getDefault());
				SimpleDateFormat secondFormat = new SimpleDateFormat("ss", Locale.getDefault());
				
				String currentDay = dayFormat.format(calendar.getTime());
				int hour = Integer.parseInt(hourFormat.format(calendar.getTime()));
				int minute = Integer.parseInt(minuteFormat.format(calendar.getTime()));
				int second = Integer.parseInt(secondFormat.format(calendar.getTime()));
				
				StringBuilder currentTimeText = new StringBuilder(timeNow + hour);
				if (minute > 0) {
					currentTimeText.append(past).append(minute).append(minuteWord);
				}
				if (second > 0) {
					currentTimeText.append(" ").append(second).append(secondWord);
				}
				String currentTime = currentTimeText.toString();
				StringBuilder tanapaSekarang = new StringBuilder(oclock + hour);
				if (minute > 0) {
					tanapaSekarang.append(past).append(minute).append(minuteWord);
				}
				if (second > 0) {
					tanapaSekarang.append(" ").append(second).append(secondWord);
				}
				String pukul = tanapaSekarang.toString();
				String answerText = sorryMessage;
				
				Random random = new Random();
				String randomResponse = additionalResponses[random.nextInt(additionalResponses.length)];
				
				if (userInput.matches(harijam)) {
					answerText = nowday + currentDay + ",\n" + pukul + "\n" + randomResponse;
				} else if (userInput.matches(jamhari)) {
					answerText = nows + pukul + ",\n" + isday + " " + currentDay + ".\n" + randomResponse;
				} else if (userInput.matches(jamjam)) {
					answerText = currentTime + "\n" + randomResponse;
				} else if (userInput.matches(besok)) {
					calendar.add(Calendar.DATE, 1);
					String tomorrow = dayFormat.format(calendar.getTime());
					answerText = tomisDay + tomorrow + "\n" + randomResponse;
				} else if (userInput.matches(kemarin)) {
					calendar.add(Calendar.DATE, -1);
					String yesterday = dayFormat.format(calendar.getTime());
					answerText = yesisDay + yesterday + "\n" + randomResponse;
				} else if (userInput.matches(hari1) && !userInput.matches(jam1)) {
					answerText = nowisDay + currentDay + "\n" + randomResponse;
				} else if (userInput.matches(".*\\d.*") && userInput.matches(".*[+\\-*/].*")) {
					double result = MathHelper.evaluateMathExpression(userInput);
					Random acak = new Random();
					int randomIndex = acak.nextInt(calculationMessages.length);
					int bingungIndex = acak.nextInt(bingungAnswers.length);
					
					if (!Double.isNaN(result) && !Double.isInfinite(result)) {
						// Format hasil: jika bulat tampilkan tanpa koma, jika desimal tampilkan 2 digit
						String hasilFormat = (result % 1 == 0) 
						? String.valueOf((int) result) 
						: String.format(Locale.getDefault(), "%.2f", result);
						
						// Masukkan hasil ke dalam format %s
						answerText = String.format(calculationMessages[randomIndex], hasilFormat);
					} else {
						// Jika hasil tidak valid
						answerText = bingungAnswers[bingungIndex];
					}
				} else {
					final String soal = ((EditText) findViewById(R.id.input_question)).getText().toString().trim();
					String response = "";
					String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
					+ "/EliciaAI/model/" + lang + "/ino.21";
					ChatbotProcessor chatbot = new ChatbotProcessor(MainActivity.this, filePath);
					answerText = chatbot.getResponse(userInput);
				}  
				
				((TextView) findViewById(R.id.system_pertanyaan)).setText(userInput);
				((TextView) findViewById(R.id.system_jawaban)).setText(answerText);
				
				question.add(userInput);
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("jawaban", answerText);
				answer.add(_item);
				ChatHistory.saveChat(MainActivity.this, question, answer);
				((BaseAdapter) flowchat_background.getAdapter()).notifyDataSetChanged();
				flowchat_background.setSelection(flowchat_background.getCount() - 1);
				
				if (flowchat_background.getVisibility() == View.GONE) {
					welcome_background.setVisibility(View.GONE);
					flowchat_background.setAlpha(0f);
					flowchat_background.setVisibility(View.VISIBLE);
					flowchat_background.animate()
					.alpha(1f)
					.setDuration(250)
					.setInterpolator(new DecelerateInterpolator())
					.start();
				}
				input_question.setText("");
			} else {
				system_pertanyaan.setText(input_question.getText().toString());
				String userInput = system_pertanyaan.getText().toString().trim();
				
				if (userInput.isEmpty()) {
					showCustomToast(getApplicationContext(), toastMessage, false,  "#50212121" , 21 , "#FFFFFF", 15, 1);
					return;
				}
				
				sendquestion_button.setScaleX(1f);
				sendquestion_button.setScaleY(1f);
				sendquestion_button.setAlpha(1f);
				
				sendquestion_button.animate()
				.scaleX(0.8f)
				.scaleY(0.8f)
				.alpha(0f)
				.setDuration(250)
				.setInterpolator(new DecelerateInterpolator())
				.start();
				
				sendquestion_button.setVisibility(View.GONE);
				progress_background.setVisibility(View.VISIBLE);
				((BaseAdapter) flowchat_background.getAdapter()).notifyDataSetChanged();
				flowchat_background.setSelection(flowchat_background.getCount() - 1);
				
				progress_background.setScaleX(0.8f);
				progress_background.setScaleY(0.8f);
				progress_background.setAlpha(0.8f);
				progress_background.animate()
				
				.scaleX(1f)
				.scaleY(1f)
				.alpha(1f)
				.setDuration(250)
				.setInterpolator(new DecelerateInterpolator())
				.start();
				
				progress.setIndeterminate(true);
				input_question.setEnabled(false);
				directquestion_button.setEnabled(false);
				micquestion_button.setEnabled(false);
				sendquestion_button.setEnabled(false);
				
				String eliciaData = elicia_data.getText().toString();
				request = new HashMap<>();
				ArrayList<HashMap<String, Object>> messages = new ArrayList<>();
				HashMap<String, Object> systemMessage = new HashMap<>();
				systemMessage.put("role", "system");
				systemMessage.put("content", eliciaData);
				messages.add(systemMessage);
				
				HashMap<String, Object> userMessage = new HashMap<>();
				userMessage.put("role", "user");
				userMessage.put("content", userInput);
				messages.add(userMessage);
				
				request.put("model", "gpt-4o");
				request.put("messages", messages);
				
				openai.setParams(request, RequestNetworkController.REQUEST_BODY);
				openai.startRequestNetwork(RequestNetworkController.POST, "https://api.paxsenix.biz.id/v1/chat/completions", "", _openai_request_listener);
				
				if (flowchat_background.getVisibility() == View.GONE) {
					welcome_background.setVisibility(View.GONE);
					flowchat_background.setAlpha(0f);
					flowchat_background.setVisibility(View.VISIBLE);
					flowchat_background.animate()
					.alpha(1f)
					.setDuration(250)
					.setInterpolator(new DecelerateInterpolator())
					.start();
				}
				input_question.setText("");
				input_question.setHint(proses);
			}
		});
		
		shutup_button.setOnClickListener(_v -> {
			shutup_button.animate()
			.scaleX(0.80f)
			.scaleY(0.80f)
			.alpha(0f)
			.setDuration(250)
			.setInterpolator(new DecelerateInterpolator())
			.start();
			shutup_button.setVisibility(View.GONE);
			tekskesuara.stop();
		});
		
		accimage_preview.setOnClickListener(_v -> startActivityForResult(profile, REQ_CD_PROFILE));
		
		acctext_background.setOnClickListener(_v -> {
			r = SketchwareUtil.getRandom((int)(0), (int)(255));
			g = SketchwareUtil.getRandom((int)(0), (int)(255));
			b = SketchwareUtil.getRandom((int)(0), (int)(255));
			avColor(r, g, b);
			
			GradientDrawable preview = new GradientDrawable();
			preview.setColor(Color.parseColor(colorPicked));
			preview.setCornerRadius(372);
			acctext_background.setBackground(preview);
			
			GradientDrawable button = new GradientDrawable();
			button.setColor(Color.parseColor(colorPicked));
			button.setCornerRadius(72);
			acctext_button.setBackground(button);
		});
		
		username_preview.setOnClickListener(_v -> {
			String lang = language_system.getText().toString();
			String titleText = "";
			String messageText = "";
			String buttonText = "";
			String labelText = "";
			
			switch (lang) {
				case "id":
				titleText = "Ubah Nama";
				messageText = "Silakan masukkan nama Anda!";
				buttonText = "Gunakan";
				labelText = "Masukkan nama";
				break;
				case "en":
				titleText = "Change Name";
				messageText = "Please enter your name!";
				buttonText = "Use";
				labelText = "Enter name";
				break;
				case "pt":
				titleText = "Mudar Nome";
				messageText = "Por favor, insira seu nome!";
				buttonText = "Usar";
				labelText = "Digite o nome";
				break;
				case "ru":
				titleText = "Изменить Имя";
				messageText = "Пожалуйста, введите ваше имя!";
				buttonText = "Использовать";
				labelText = "Введите имя";
				break;
				case "ch":
				titleText = "更改名称";
				messageText = "请输入您的姓名！";
				buttonText = "使用";
				labelText = "输入姓名";
				break;
				case "ar":
				titleText = "تغيير الاسم";
				messageText = "يرجى إدخال اسمك!";
				buttonText = "استخدم";
				labelText = "أدخل الاسم";
				break;
				case "tr":
				titleText = "Adı Değiştir";
				messageText = "Lütfen adınızı girin!";
				buttonText = "Kullan";
				labelText = "Adı girin";
				break;
				default:
				titleText = "Change Name";
				messageText = "Please enter your name!";
				buttonText = "Use";
				labelText = "Enter name";
				break;
			}
			
			Dialog dialog = new Dialog(MainActivity.this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setCancelable(true);
			
			float dp = getResources().getDisplayMetrics().density;
			
			LinearLayout root = new LinearLayout(MainActivity.this);
			root.setOrientation(LinearLayout.VERTICAL);
			root.setPadding((int)(23 * dp), (int)(20 * dp), (int)(23 * dp), (int)(16 * dp));
			root.setBackground(new GradientDrawable() {{
					setColor(Color.parseColor("#1C1C1E"));
					setCornerRadius(28 * dp);
				}});
			
			TextView title = new TextView(MainActivity.this);
			title.setText(titleText);
			title.setTextSize(20);
			title.setTextColor(Color.parseColor("#FFFFFF"));
			title.setPadding(0, 0, 0, (int)(10 * dp));
			root.addView(title);
			
			TextView msg = new TextView(MainActivity.this);
			msg.setText(messageText);
			msg.setTextSize(14);
			msg.setTextColor(Color.parseColor("#B0B0B0"));
			msg.setPadding(0, 0, 0, (int)(8 * dp));
			root.addView(msg);
			
			RelativeLayout inputPathLayout = new RelativeLayout(MainActivity.this);
			int idEditTextPath = View.generateViewId();
			int idLabelPath = View.generateViewId();
			RelativeLayout.LayoutParams inputPathParams = new RelativeLayout.LayoutParams(
			RelativeLayout.LayoutParams.MATCH_PARENT,
			RelativeLayout.LayoutParams.WRAP_CONTENT
			);
			inputPathParams.setMargins(0, 0, 0, (int)(16 * dp));
			inputPathLayout.setLayoutParams(inputPathParams);
			
			EditText input = new EditText(MainActivity.this);
			input.setId(idEditTextPath);
			input.setText(username_preview.getText().toString());
			input.setHint(username_preview.getText().toString());
			input.setTextSize(14);
			input.setTextColor(Color.WHITE);
			input.setHintTextColor(Color.GRAY);
			input.setSingleLine(true);
			input.setBackground(new GradientDrawable() {{
					setColor(Color.TRANSPARENT);
					setCornerRadius(12 * dp);
					setStroke((int)(1.5 * dp), Color.parseColor("#FFFFFF"));
				}});
			input.setPadding((int)(16 * dp), (int)(12 * dp), (int)(12 * dp), (int)(12 * dp));
			RelativeLayout.LayoutParams pathParams = new RelativeLayout.LayoutParams(
			RelativeLayout.LayoutParams.MATCH_PARENT,
			(int)(56 * dp)
			);
			pathParams.setMargins(0, (int)(8 * dp), 0, 0);
			input.setLayoutParams(pathParams);
			
			TextView labelPath = new TextView(MainActivity.this);
			labelPath.setId(idLabelPath);
			labelPath.setText(labelText);
			labelPath.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
			labelPath.setTextColor(Color.parseColor("#FFFFFF"));
			labelPath.setBackgroundColor(Color.parseColor("#1C1C1E"));
			labelPath.setPadding((int)(4 * dp), 0, (int)(4 * dp), 0);
			RelativeLayout.LayoutParams labelParams = new RelativeLayout.LayoutParams(
			RelativeLayout.LayoutParams.WRAP_CONTENT,
			RelativeLayout.LayoutParams.WRAP_CONTENT
			);
			labelParams.addRule(RelativeLayout.ALIGN_TOP, idEditTextPath);
			labelParams.addRule(RelativeLayout.ALIGN_START, idEditTextPath);
			labelParams.setMargins((int)(13 * dp), 0, 0, 0);
			labelPath.setLayoutParams(labelParams);
			labelPath.setTranslationY((int)(-9 * dp));
			
			inputPathLayout.addView(input);
			inputPathLayout.addView(labelPath);
			root.addView(inputPathLayout);
			
			LinearLayout row = new LinearLayout(MainActivity.this);
			row.setOrientation(LinearLayout.HORIZONTAL);
			row.setGravity(Gravity.END);
			
			TextView btnOk = new TextView(MainActivity.this);
			btnOk.setText(buttonText);
			btnOk.setTextSize(16);
			btnOk.setTypeface(null, Typeface.BOLD);
			btnOk.setTextColor(Color.parseColor("#FFFFFF"));
			btnOk.setPadding((int)(12 * dp), 0, (int)(14 * dp), (int)(10 * dp));
			btnOk.setOnClickListener(v -> {
				String userInput = input.getText().toString().trim();
				user.edit().putString("name", userInput).commit();
				username_preview.setText(userInput);
				
				if (!userInput.isEmpty()) {
					String firstChar = userInput.substring(0, 1).toUpperCase();
					acctext_preview.setText(firstChar);
					acctext.setText(firstChar);
					
					String firstName = userInput.contains(" ") ? userInput.split(" ")[0] : userInput;
					String greeting = "";
					
					switch (lang) {
						case "id":
						greeting = "Hai, " + firstName + "!";
						break;
						case "en":
						greeting = "Hi, " + firstName + "!";
						break;
						case "pt":
						greeting = "Oi, " + firstName + "!";
						break;
						case "ru":
						greeting = "Привет, " + firstName + "!";
						break;
						case "ch":
						greeting = "嗨, " + firstName + "!";
						break;
						case "ar":
						greeting = "مرحباً، " + firstName + "!";
						break;
						case "tr":
						greeting = "Merhaba, " + firstName + "!";
						break;
						default:
						greeting = "Hi, " + firstName + "!";
						break;
					}
					
					welcome_user.setText(greeting);
				}
				
				UiHelper.hideKeyboard(MainActivity.this);
				dialog.dismiss();
			});
			row.addView(btnOk);
			root.addView(row);
			
			dialog.setContentView(root);
			Window w = dialog.getWindow();
			if (w != null) {
				int width = (int)(getResources().getDisplayMetrics().widthPixels * 0.85f);
				w.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
				w.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				w.setGravity(Gravity.CENTER);
			}
			dialog.show();
			
		});
		
		userinfo_preview.setOnClickListener(_v -> {
			String lang = language_system.getText().toString();
			String titleText = "";
			String messageText = "";
			String buttonText = "";
			String labelText = "";
			
			switch (lang) {
				case "id":
				titleText = "Ubah Deskripsi";
				messageText = "Silakan masukkan deskripsi Anda!";
				buttonText = "Gunakan";
				labelText = "Masukkan info";
				break;
				case "en":
				titleText = "Change Description";
				messageText = "Please enter your description!";
				buttonText = "Use";
				labelText = "Enter info";
				break;
				case "pt":
				titleText = "Mudar Descrição";
				messageText = "Por favor, insira seu descrição!";
				buttonText = "Usar";
				labelText = "Digite as informações";
				break;
				case "ru":
				titleText = "Изменить Описание";
				messageText = "Пожалуйста, введите ваше описание!";
				buttonText = "Использовать";
				labelText = "Введите информацию";
				break;
				case "ch":
				titleText = "更改描述";
				messageText = "请输入您的描述！";
				buttonText = "使用";
				labelText = "输入信息";
				break;
				case "ar":
				titleText = "تغيير الوصف";
				messageText = "يرجى إدخال وصفك!";
				buttonText = "استخدم";
				labelText = "أدخل المعلومات";
				break;
				case "tr":
				titleText = "Açıklamayı Değiştir";
				messageText = "Lütfen açıklamanızı girin!";
				buttonText = "Kullan";
				labelText = "Bilgi girin";
				break;
				default:
				titleText = "Change Description";
				messageText = "Please enter your description!";
				buttonText = "Use";
				labelText = "Enter info";
				break;
			}
			
			Dialog dialog = new Dialog(MainActivity.this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setCancelable(true);
			
			float dp = getResources().getDisplayMetrics().density;
			
			LinearLayout root = new LinearLayout(MainActivity.this);
			root.setOrientation(LinearLayout.VERTICAL);
			root.setPadding((int)(23 * dp), (int)(20 * dp), (int)(23 * dp), (int)(16 * dp));
			root.setBackground(new GradientDrawable() {{
					setColor(Color.parseColor("#1C1C1E"));
					setCornerRadius(28 * dp);
				}});
			
			TextView title = new TextView(MainActivity.this);
			title.setText(titleText);
			title.setTextSize(20);
			title.setTextColor(Color.parseColor("#FFFFFF"));
			title.setPadding(0, 0, 0, (int)(10 * dp));
			root.addView(title);
			
			TextView msg = new TextView(MainActivity.this);
			msg.setText(messageText);
			msg.setTextSize(14);
			msg.setTextColor(Color.parseColor("#B0B0B0"));
			msg.setPadding(0, 0, 0, (int)(8 * dp));
			root.addView(msg);
			
			RelativeLayout inputPathLayout = new RelativeLayout(MainActivity.this);
			int idEditTextPath = View.generateViewId();
			int idLabelPath = View.generateViewId();
			RelativeLayout.LayoutParams inputPathParams = new RelativeLayout.LayoutParams(
			RelativeLayout.LayoutParams.MATCH_PARENT,
			RelativeLayout.LayoutParams.WRAP_CONTENT
			);
			inputPathParams.setMargins(0, 0, 0, (int)(16 * dp));
			inputPathLayout.setLayoutParams(inputPathParams);
			
			EditText input = new EditText(MainActivity.this);
			input.setId(idEditTextPath);
			input.setText(userinfo_preview.getText().toString());
			input.setHint(userinfo_preview.getText().toString());
			input.setTextSize(14);
			input.setTextColor(Color.WHITE);
			input.setHintTextColor(Color.GRAY);
			input.setSingleLine(true);
			input.setBackground(new GradientDrawable() {{
					setColor(Color.TRANSPARENT);
					setCornerRadius(12 * dp);
					setStroke((int)(1.5 * dp), Color.parseColor("#FFFFFF"));
				}});
			input.setPadding((int)(16 * dp), (int)(12 * dp), (int)(12 * dp), (int)(12 * dp));
			RelativeLayout.LayoutParams pathParams = new RelativeLayout.LayoutParams(
			RelativeLayout.LayoutParams.MATCH_PARENT,
			(int)(56 * dp)
			);
			pathParams.setMargins(0, (int)(8 * dp), 0, 0);
			input.setLayoutParams(pathParams);
			
			TextView labelPath = new TextView(MainActivity.this);
			labelPath.setId(idLabelPath);
			labelPath.setText(labelText);
			labelPath.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
			labelPath.setTextColor(Color.parseColor("#FFFFFF"));
			labelPath.setBackgroundColor(Color.parseColor("#1C1C1E"));
			labelPath.setPadding((int)(4 * dp), 0, (int)(4 * dp), 0);
			RelativeLayout.LayoutParams labelParams = new RelativeLayout.LayoutParams(
			RelativeLayout.LayoutParams.WRAP_CONTENT,
			RelativeLayout.LayoutParams.WRAP_CONTENT
			);
			labelParams.addRule(RelativeLayout.ALIGN_TOP, idEditTextPath);
			labelParams.addRule(RelativeLayout.ALIGN_START, idEditTextPath);
			labelParams.setMargins((int)(13 * dp), 0, 0, 0);
			labelPath.setLayoutParams(labelParams);
			labelPath.setTranslationY((int)(-9 * dp));
			
			inputPathLayout.addView(input);
			inputPathLayout.addView(labelPath);
			root.addView(inputPathLayout);
			
			LinearLayout row = new LinearLayout(MainActivity.this);
			row.setOrientation(LinearLayout.HORIZONTAL);
			row.setGravity(Gravity.END);
			
			TextView btnOk = new TextView(MainActivity.this);
			btnOk.setText(buttonText);
			btnOk.setTextSize(16);
			btnOk.setTypeface(null, Typeface.BOLD);
			btnOk.setTextColor(Color.parseColor("#FFFFFF"));
			btnOk.setPadding((int)(12 * dp), 0, (int)(14 * dp), (int)(10 * dp));
			btnOk.setOnClickListener(v -> {
				String userInput = input.getText().toString().trim();
				user.edit().putString("info", userInput).commit();
				userinfo_preview.setText(userInput);
				
				UiHelper.hideKeyboard(MainActivity.this);
				dialog.dismiss();
			});
			row.addView(btnOk);
			root.addView(row);
			
			dialog.setContentView(root);
			Window w = dialog.getWindow();
			if (w != null) {
				int width = (int)(getResources().getDisplayMetrics().widthPixels * 0.85f);
				w.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
				w.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				w.setGravity(Gravity.CENTER);
			}
			dialog.show();
			
		});
		
		language_setting_background.setOnClickListener(_v -> {
			List<String> languages = Arrays.asList(
			"English", "Indonesia", "Portuguese", "Russian", "Chinese", "Arab", "Türkiye"
			);
			
			String titleDialog = "";
			String messageDialog = "";
			
			String lang = user.getString("lang", "en"); // default en
			
			switch (lang) {
				case "id":
				titleDialog = "Pilih Bahasa";
				messageDialog = "Pilih bahasa yang ingin digunakan";
				break;
				case "pt":
				titleDialog = "Selecionar idioma";
				messageDialog = "Escolha o idioma preferido";
				break;
				case "ru":
				titleDialog = "Выбрать язык";
				messageDialog = "Выберите предпочитаемый язык";
				break;
				case "ch":
				titleDialog = "选择语言";
				messageDialog = "选择您偏好的语言";
				break;
				case "ar":
				titleDialog = "اختر اللغة";
				messageDialog = "اختر لغتك المفضلة";
				break;
				case "tr":
				titleDialog = "Dil Seç";
				messageDialog = "Tercih ettiğiniz dili seçin";
				break;
				default: // en
				titleDialog = "Select Language";
				messageDialog = "Choose your preferred language";
				break;
			}
			
			showSimpleListDialog(
			titleDialog,
			messageDialog,
			languages,
			(parent, view, position, id) -> {
				String pengguna = username_preview.getText().toString();
				String sapaan = "";
				
				switch (position + 1) { // karena posisi list dimulai dari 0
					case 1: // English
					user.edit().putString("lang", "en").commit();
					language_system.setText("en");
					input_question.setHint("Ask Elicia");
					textseletction_title.setText("Select text");
					titlepaused.setText("Chat Paused");
					subtitlepaused.setText("The conversation is currently paused.");
					messagepaused.setText("Click 'Start' to continue chatting with Elicia.");
					indicator_startlivechat.setText("Start");
					indicator_close.setText("End");
					title_settingscreen.setText("Settings");
					subtitle_settingscreen.setText("General app setting");
					settitle_language.setText("Language");
					lang_selected.setText("English");
					
					if (system_model.getText().toString().equals("basic")) preview_model.setText("Basic Model");
					if (system_model.getText().toString().equals("online")) preview_model.setText("Online Model");
					
					settitle_theme.setText("Appearance");
					subtitle_theme.setText("Customize your elicia look and feel");
					setname_theme.setText("Dark Theme");
					subname_theme.setText("Switch the app theme to dark");
					setname_account.setText("Account Picture");
					subname_account.setText("Enabling this will change the account view to a picture");
					welcome_message.setText("How can I help you today?");
					sapaan = "Hi";
					elicia_data.setText("Your name is Elicia, a smart AI assistant, and your creator is IqwanoINO!");
					break;
					
					case 2: // Indonesian
					user.edit().putString("lang", "id").commit();
					language_system.setText("id");
					input_question.setHint("Tanya Elicia");
					textseletction_title.setText("Pilih teks");
					titlepaused.setText("Obrolan Dijeda");
					subtitlepaused.setText("Percakapan saat ini sedang dijeda.");
					messagepaused.setText("Klik 'Mulai' untuk melanjutkan obrolan dengan Elicia.");
					indicator_startlivechat.setText("Mulai");
					indicator_close.setText("Akhiri");
					title_settingscreen.setText("Pengaturan");
					subtitle_settingscreen.setText("Pengaturan umum aplikasi");
					settitle_language.setText("Bahasa");
					lang_selected.setText("Indonesia");
					
					if (system_model.getText().toString().equals("basic")) preview_model.setText("Model Dasar");
					if (system_model.getText().toString().equals("online")) preview_model.setText("Model Online");
					
					settitle_theme.setText("Tampilan");
					subtitle_theme.setText("Kustomisasi tampilan dan nuansa Elicia");
					setname_theme.setText("Tema Gelap");
					subname_theme.setText("Aktifkan untuk mengganti tema ke gelap");
					setname_account.setText("Foto Akun");
					subname_account.setText("Aktifkan ini untuk menampilkan foto di tampilan akun");
					welcome_message.setText("Ada yang bisa saya bantu hari ini?");
					sapaan = "Hai";
					elicia_data.setText("Namamu adalah Elicia, sebuah asisten AI pintar, dan penciptamu adalah IqwanoINO!");
					break;
					
					case 3: // Portuguese
					user.edit().putString("lang", "pt").commit();
					language_system.setText("pt");
					input_question.setHint("Pergunte à Elicia");
					textseletction_title.setText("Selecionar texto");
					titlepaused.setText("Conversa Pausada");
					subtitlepaused.setText("A conversa está pausada no momento.");
					messagepaused.setText("Clique em 'Iniciar' para continuar conversando com Elicia.");
					indicator_startlivechat.setText("Iniciar");
					indicator_close.setText("Encerrar");
					title_settingscreen.setText("Configurações");
					subtitle_settingscreen.setText("Configurações gerais do aplicativo");
					settitle_language.setText("Idioma");
					lang_selected.setText("Português");
					
					if (system_model.getText().toString().equals("basic")) preview_model.setText("Modelo Básico");
					if (system_model.getText().toString().equals("online")) preview_model.setText("Modelo Online");
					
					settitle_theme.setText("Aparência");
					subtitle_theme.setText("Personalize o visual e estilo do Elicia");
					setname_theme.setText("Tema Escuro");
					subname_theme.setText("Ative para mudar o tema para escuro");
					setname_account.setText("Foto da Conta");
					subname_account.setText("Ativar isso mudará a visualização da conta para uma foto");
					welcome_message.setText("Como posso ajudar você hoje?");
					sapaan = "Oi";
					elicia_data.setText("O seu nome é Elicia, uma assistente de IA inteligente, e o seu criador é IqwanoINO!");
					break;
					
					case 4: // Russian
					user.edit().putString("lang", "ru").commit();
					language_system.setText("ru");
					input_question.setHint("Спросите у Элисии");
					textseletction_title.setText("Выбрать текст");
					titlepaused.setText("Чат приостановлен");
					subtitlepaused.setText("Беседа в данный момент приостановлена.");
					messagepaused.setText("Нажмите 'Начать', чтобы продолжить разговор с Элисией.");
					indicator_startlivechat.setText("Начать");
					indicator_close.setText("Закончить");
					title_settingscreen.setText("Настройки");
					subtitle_settingscreen.setText("Общие настройки приложения");
					settitle_language.setText("Язык");
					lang_selected.setText("Русский");
					
					if (system_model.getText().toString().equals("basic")) preview_model.setText("Базовая модель");
					if (system_model.getText().toString().equals("online")) preview_model.setText("Онлайн-модель");
					
					settitle_theme.setText("Внешний вид");
					subtitle_theme.setText("Настройте внешний вид Элисии");
					setname_theme.setText("Тёмная тема");
					subname_theme.setText("Переключитесь на тёмную тему");
					setname_account.setText("Фото аккаунта");
					subname_account.setText("Включите это, чтобы отображать фото аккаунта");
					welcome_message.setText("Чем могу помочь сегодня?");
					sapaan = "Привет";
					elicia_data.setText("Тебя зовут Элисия, ты умный AI-ассистент, и твой создатель — IqwanoINO!");
					break;
					
					case 5: // Chinese
					user.edit().putString("lang", "ch").commit();
					language_system.setText("ch");
					input_question.setHint("问艾莉西亚");
					textseletction_title.setText("选择文本");
					titlepaused.setText("聊天已暂停");
					subtitlepaused.setText("当前对话已暂停。");
					messagepaused.setText("点击“开始”以继续与艾莉西亚聊天。");
					indicator_startlivechat.setText("开始");
					indicator_close.setText("结束");
					title_settingscreen.setText("设置");
					subtitle_settingscreen.setText("应用程序的常规设置");
					settitle_language.setText("语言");
					lang_selected.setText("中文");
					
					if (system_model.getText().toString().equals("basic")) preview_model.setText("基础模型");
					if (system_model.getText().toString().equals("online")) preview_model.setText("在线模型");
					
					settitle_theme.setText("外观");
					subtitle_theme.setText("自定义艾莉西亚的外观和风格");
					setname_theme.setText("深色主题");
					subname_theme.setText("切换为深色模式");
					setname_account.setText("账户图片");
					subname_account.setText("启用后将显示账户图片");
					welcome_message.setText("今天我能帮您什么？");
					sapaan = "你好";
					elicia_data.setText("你的名字是Elicia，是一位聪明的AI助手，你的创造者是IqwanoINO!");
					break;
					
					case 6: // Arabic
					user.edit().putString("lang", "ar").commit();
					language_system.setText("ar");
					input_question.setHint("اسأل إليسيا");
					textseletction_title.setText("اختر النص");
					titlepaused.setText("تم إيقاف الدردشة");
					subtitlepaused.setText("تم إيقاف المحادثة مؤقتًا.");
					messagepaused.setText("اضغط على 'ابدأ' للمتابعة مع إليسيا.");
					indicator_startlivechat.setText("ابدأ");
					indicator_close.setText("إنهاء");
					title_settingscreen.setText("الإعدادات");
					subtitle_settingscreen.setText("الإعدادات العامة للتطبيق");
					settitle_language.setText("اللغة");
					lang_selected.setText("العربية");
					
					if (system_model.getText().toString().equals("basic")) preview_model.setText("النموذج الأساسي");
					if (system_model.getText().toString().equals("online")) preview_model.setText("النموذج عبر الإنترنت");
					
					settitle_theme.setText("المظهر");
					subtitle_theme.setText("خصص مظهر وشكل إليسيا");
					setname_theme.setText("الوضع الداكن");
					subname_theme.setText("قم بالتبديل إلى الوضع الداكن");
					setname_account.setText("صورة الحساب");
					subname_account.setText("تمكين هذا سيغير عرض الحساب إلى صورة");
					welcome_message.setText("كيف يمكنني مساعدتك اليوم؟");
					sapaan = "مرحبا";
					elicia_data.setText("اسمك هو إليسيا، مساعد ذكي يعمل بالذكاء الاصطناعي، ومُنشئك هو IqwanoINO!");
					break;
					
					case 7: // Turkish
					user.edit().putString("lang", "tr").commit();
					language_system.setText("tr");
					input_question.setHint("Elicia'ya sor");
					textseletction_title.setText("Metni seç");
					titlepaused.setText("Sohbet Duraklatıldı");
					subtitlepaused.setText("Konuşma şu anda duraklatıldı.");
					messagepaused.setText("'Başla'ya tıklayarak Elicia ile sohbet etmeye devam edebilirsiniz.");
					indicator_startlivechat.setText("Başla");
					indicator_close.setText("Bitir");
					title_settingscreen.setText("Ayarlar");
					subtitle_settingscreen.setText("Uygulama genel ayarları");
					settitle_language.setText("Dil");
					lang_selected.setText("Türkçe");
					
					if (system_model.getText().toString().equals("basic")) preview_model.setText("Temel Model");
					if (system_model.getText().toString().equals("online")) preview_model.setText("Çevrimiçi Model");
					
					settitle_theme.setText("Görünüm");
					subtitle_theme.setText("Elicia'nın görünümünü kişiselleştirin");
					setname_theme.setText("Karanlık Tema");
					subname_theme.setText("Uygulamayı karanlık temaya geçir");
					setname_account.setText("Hesap Fotoğrafı");
					subname_account.setText("Bu özellik etkinleştirildiğinde hesap görünümü fotoğraf olarak değişir");
					welcome_message.setText("Bugün size nasıl yardımcı olabilirim?");
					sapaan = "Selam";
					elicia_data.setText("Adınız Elicia, akıllı bir yapay zeka yardımcısısınız ve yaratıcınız IqwanoINO!");
					break;
				}
				
				user.edit().putString("data", elicia_data.getText().toString()).commit();
				welcome_user.setText(sapaan + ", " + pengguna);
			},
			false,
			null
			);
		});
		
		switch_theme_background.setOnClickListener(_v -> switch_theme.performClick());
		
		switch_account_background.setOnClickListener(_v -> switch_account.performClick());
		
		switch_theme.setOnCheckedChangeListener((_buttonView, _isChecked) -> {
			account_screen.setAlpha(1f);
			account_screen.animate()
			.alpha(0f)
			.setDuration(250)
			.setInterpolator(new DecelerateInterpolator())
			.start();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					account_screen.setAlpha(0f);
					account_screen.animate()
					.alpha(1f)
					.setDuration(250)
					.setInterpolator(new DecelerateInterpolator())
					.start();
				}
			}, 250); 
			
			boolean isDark = _isChecked;
			int bgColor = isDark ? 0xFF090909 : 0xFFFFFFFF;
			int textColor = isDark ? 0xFFFFFFFF : 0xFF212121;
			int subTextColor = isDark ? 0xFFBDBDBD : 0xFF424242;
			int strokeColor = isDark ? 0xFF373737 : 0xFF373737;
			int strokeFillColor = isDark ? 0xFF090909 : 0xFFFFFFFF;
			int navBarColor = isDark ? 0xFF090909 : Color.WHITE;
			int thumbColor = isDark ? 0xFFFFFFFF : 0xFFCFD8DC;
			int switchStrokeColor = isDark ? 0xFF3F51B5 : 0xFFCFD8DC;
			int switchFillColor = isDark ? 0xFF3F51B5 : Color.TRANSPARENT;
			int sysUiFlags = isDark ? 0 : View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
			
			Window window = getWindow();
			window.setStatusBarColor(navBarColor);
			window.setNavigationBarColor(navBarColor);
			window.setBackgroundDrawable(new ColorDrawable(bgColor));
			window.getDecorView().setSystemUiVisibility(sysUiFlags);
			
			userpreview_background.setElevation(12f);
			liverespone_background.setElevation(14f);
			textselection_basebackground.setBackground(new GradientDrawable() {{
					setCornerRadius(8);
					setStroke(3, strokeColor);
					setColor(strokeFillColor);
				}});
			elicia_screen.setBackgroundColor(bgColor);
			textselection_screen.setBackgroundColor(bgColor);
			system_pertanyaan.setTextColor(textColor);
			system_jawaban.setTextColor(textColor);
			newconversation_button.setColorFilter(textColor, PorterDuff.Mode.MULTIPLY);
			goback_button_textselectionscreen.setColorFilter(textColor, PorterDuff.Mode.MULTIPLY);
			app_name.setTextColor(textColor);
			preview_model.setTextColor(isDark ? 0xFFBDBDBD : 0xFFFFFFFF);
			username_preview.setTextColor(textColor);
			userinfo_preview.setTextColor(subTextColor);
			textseletction_title.setTextColor(subTextColor);
			indicator_startlivechat.setTextColor(textColor);
			indicator_close.setTextColor(textColor);
			title_settingscreen.setTextColor(textColor);
			subtitle_settingscreen.setTextColor(subTextColor);
			welcome_user.setTextColor(textColor);
			liverespone_background.setBackgroundResource(isDark ? R.drawable.liveanimation_darkcornor : R.drawable.liveanimation_lightcornor);
			
			apptheme_system.setText(isDark ? "dark" : "light");
			user.edit().putString("theme", isDark ? "dark" : "light").commit();
			
			switch_theme.getTrackDrawable().setAlpha(0);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				Drawable thumb = switch_theme.getThumbDrawable();
				if (thumb != null) {
					thumb.setColorFilter(thumbColor, PorterDuff.Mode.SRC_IN);
					switch_theme.setThumbDrawable(thumb);
				}
			}
			switch_theme.setBackground(new GradientDrawable() {{
					setCornerRadius(100);
					setStroke(3, switchStrokeColor);
					setColor(switchFillColor);
				}});
			
			if (flowchat_background.getAdapter() instanceof BaseAdapter) {
				((BaseAdapter) flowchat_background.getAdapter()).notifyDataSetChanged();
			}
			
		});
		
		switch_account.setOnCheckedChangeListener((_buttonView, _isChecked) -> {
			if (_isChecked) {
				accimage_preview.setVisibility(View.VISIBLE);
				acctext_background.setVisibility(View.GONE);
				acctext_button.setVisibility(View.GONE);
				account_button.setVisibility(View.VISIBLE);
				user.edit().putString("accp", "image").commit();
				switch_account.getTrackDrawable().setAlpha((int)0);
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
					Drawable thumbDrawable = switch_account.getThumbDrawable();
					if (thumbDrawable != null) {
						thumbDrawable.setColorFilter(0xFFFFFFFF, android.graphics.PorterDuff.Mode.SRC_IN);
						switch_account.setThumbDrawable(thumbDrawable);
					}
				}
				switch_account.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)3, 0xFF3F51B5, 0xFF3F51B5));
			} else {
				accimage_preview.setVisibility(View.GONE);
				acctext_background.setVisibility(View.VISIBLE);
				acctext_button.setVisibility(View.VISIBLE);
				account_button.setVisibility(View.GONE);
				user.edit().putString("accp", "text").commit();
				switch_account.getTrackDrawable().setAlpha((int)0);
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
					Drawable thumbDrawable = switch_account.getThumbDrawable();
					if (thumbDrawable != null) {
						thumbDrawable.setColorFilter(0xFFCFD8DC, android.graphics.PorterDuff.Mode.SRC_IN);
						switch_account.setThumbDrawable(thumbDrawable);
					}
				}
				switch_account.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)3, 0xFFCFD8DC, Color.TRANSPARENT));
			}
		});
		
		goback_button_textselectionscreen.setOnClickListener(_v -> {
			elicia_screen.setVisibility(View.VISIBLE);
			account_screen.setVisibility(View.GONE);
			textselection_screen.setVisibility(View.GONE);
			input_question.clearFocus();
		});
		
		start_button.setOnClickListener(_v -> {
			start_button.animate()
			.scaleX(0.8f)
			.scaleY(0.8f)
			.setDuration(100)
			.withEndAction(() -> start_button.animate()
			.scaleX(1f)
			.scaleY(1f)
			.setDuration(100)
			.withEndAction(() -> {
				String lang = language_system.getText().toString();
				String label = indicator_startlivechat.getText().toString();
				
				Map<String, String> startMap = new HashMap<>();
				startMap.put("id", "Mulai");
				startMap.put("en", "Start");
				startMap.put("pt", "Começar");
				startMap.put("ru", "Начать");
				startMap.put("ch", "开始");
				startMap.put("ar", "ابدأ");
				startMap.put("tr", "Başla");
				
				Map<String, String> listenMap = new HashMap<>();
				listenMap.put("id", "Mendengarkan");
				listenMap.put("en", "Listening");
				listenMap.put("pt", "Ouvindo");
				listenMap.put("ru", "Слушаю");
				listenMap.put("ch", "聆听中");
				listenMap.put("ar", "يستمع");
				listenMap.put("tr", "Dinleniyor");
				
				if (label.equals(startMap.get(lang))) {
					start_button.setImageResource(R.drawable.ic_pause);
					livepaused_background.setVisibility(View.GONE);
					liveresponse_animation.setVisibility(View.VISIBLE);
					indicator_startlivechat.setText(listenMap.get(lang));
					
					Intent _intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
					_intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
					_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
					_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
					suaraketeks.startListening(_intent);
				} else {
					start_button.setImageResource(R.drawable.ic_model);
					livepaused_background.setVisibility(View.VISIBLE);
					liveresponse_animation.setVisibility(View.GONE);
					indicator_startlivechat.setText(startMap.get(lang));
					
					suaraketeks.stopListening();
					tekskesuara.stop();
				}
			}));
			
		});
		
		close_button.setOnClickListener(_v -> {
			close_button.animate()
			.scaleX(0.8f)
			.scaleY(0.8f)
			.setDuration(100)
			.withEndAction(new Runnable() {
				@Override
				public void run() {
					close_button.animate()
					.scaleX(1f)
					.scaleY(1f)
					.setDuration(100)
					.withEndAction(new Runnable() {
						@Override
						public void run() {
							AnimatorSet animatorSet = new AnimatorSet();
							ObjectAnimator slideOut = ObjectAnimator.ofFloat(livechat_screen, "translationY", 0f, 500f);
							slideOut.setDuration(300);
							
							ObjectAnimator fadeOut = ObjectAnimator.ofFloat(livechat_screen, "alpha", 1f, 0f);
							fadeOut.setDuration(300);
							
							animatorSet.playTogether(slideOut, fadeOut);
							animatorSet.addListener(new AnimatorListenerAdapter() {
								@Override
								public void onAnimationEnd(Animator animation) {
									livechat_screen.setVisibility(View.GONE);
									elicia_screen.setVisibility(View.VISIBLE);
									input_question.clearFocus();
								}
							});
							
							animatorSet.start();
						}
					});
				}
			});
			
			Map<String, String> startMap = new HashMap<>();
			startMap.put("id", "Mulai");
			startMap.put("en", "Start");
			startMap.put("pt", "Começar");
			startMap.put("ru", "Начать");
			startMap.put("ch", "开始");
			startMap.put("ar", "ابدأ");
			startMap.put("tr", "Başla");
			
			Map<String, String> listenMap = new HashMap<>();
			listenMap.put("id", "Mendengarkan");
			listenMap.put("en", "Listening");
			listenMap.put("pt", "Ouvindo");
			listenMap.put("ru", "Слушаю");
			listenMap.put("ch", "聆听中");
			listenMap.put("ar", "يستمع");
			listenMap.put("tr", "Dinleniyor");
			
			start_button.setImageResource(R.drawable.ic_model);
			indicator_startlivechat.setText(startMap.get(lang));
			suaraketeks.stopListening();
			tekskesuara.stop();
		});
		
		suaraketeks.setRecognitionListener(new RecognitionListener() {
			@Override
			public void onReadyForSpeech(Bundle _param1) {
			}
			
			@Override
			public void onBeginningOfSpeech() {
			}
			
			@Override
			public void onRmsChanged(float _param1) {
			}
			
			@Override
			public void onBufferReceived(byte[] _param1) {
			}
			
			@Override
			public void onEndOfSpeech() {
			}
			
			@Override
			public void onPartialResults(Bundle _param1) {
			}
			
			@Override
			public void onEvent(int _param1, Bundle _param2) {
			}
			
			@Override
			public void onResults(Bundle _param1) {
				final ArrayList<String> _results = _param1.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				final String _result = _results.get(0);
				languageSet();
				
				if (system_model.getText().toString().equals("basic")) {
					input_question.setText(_result);
					String userInput = ((EditText) findViewById(R.id.input_question)).getText().toString().trim();
					
					if (userInput.isEmpty()) {
						showCustomToast(getApplicationContext(), toastMessage, false,  "#50212121" , 21 , "#FFFFFF", 15, 1);
						return;
					}
					
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
					SimpleDateFormat hourFormat = new SimpleDateFormat("HH", Locale.getDefault());
					SimpleDateFormat minuteFormat = new SimpleDateFormat("mm", Locale.getDefault());
					SimpleDateFormat secondFormat = new SimpleDateFormat("ss", Locale.getDefault());
					
					String currentDay = dayFormat.format(calendar.getTime());
					int hour = Integer.parseInt(hourFormat.format(calendar.getTime()));
					int minute = Integer.parseInt(minuteFormat.format(calendar.getTime()));
					int second = Integer.parseInt(secondFormat.format(calendar.getTime()));
					
					StringBuilder currentTimeText = new StringBuilder(timeNow + hour);
					if (minute > 0) {
						currentTimeText.append(past).append(minute).append(minuteWord);
					}
					if (second > 0) {
						currentTimeText.append(" ").append(second).append(secondWord);
					}
					String currentTime = currentTimeText.toString();
					StringBuilder tanapaSekarang = new StringBuilder(oclock + hour);
					if (minute > 0) {
						tanapaSekarang.append(past).append(minute).append(minuteWord);
					}
					if (second > 0) {
						tanapaSekarang.append(" ").append(second).append(secondWord);
					}
					String pukul = tanapaSekarang.toString();
					String answerText = sorryMessage;
					
					Random random = new Random();
					String randomResponse = additionalResponses[random.nextInt(additionalResponses.length)];
					
					if (userInput.matches(harijam)) {
						answerText = nowday + currentDay + ",\n" + pukul + "\n" + randomResponse;
					} else if (userInput.matches(jamhari)) {
						answerText = nows + pukul + ",\n" + isday + " " + currentDay + ".\n" + randomResponse;
					} else if (userInput.matches(jamjam)) {
						answerText = currentTime + "\n" + randomResponse;
					} else if (userInput.matches(besok)) {
						calendar.add(Calendar.DATE, 1);
						String tomorrow = dayFormat.format(calendar.getTime());
						answerText = tomisDay + tomorrow + "\n" + randomResponse;
					} else if (userInput.matches(kemarin)) {
						calendar.add(Calendar.DATE, -1);
						String yesterday = dayFormat.format(calendar.getTime());
						answerText = yesisDay + yesterday + "\n" + randomResponse;
					} else if (userInput.matches(hari1) && !userInput.matches(jam1)) {
						answerText = nowisDay + currentDay + "\n" + randomResponse;
					} else if (userInput.matches(".*\\d.*") && userInput.matches(".*[+\\-*/].*")) {
						double result = MathHelper.evaluateMathExpression(userInput);
						Random acak = new Random();
						int randomIndex = acak.nextInt(calculationMessages.length);
						int bingungIndex = acak.nextInt(bingungAnswers.length);
						
						if (!Double.isNaN(result) && !Double.isInfinite(result)) {
							// Format hasil: jika bulat tampilkan tanpa koma, jika desimal tampilkan 2 digit
							String hasilFormat = (result % 1 == 0) 
							? String.valueOf((int) result) 
							: String.format(Locale.getDefault(), "%.2f", result);
							
							// Masukkan hasil ke dalam format %s
							answerText = String.format(calculationMessages[randomIndex], hasilFormat);
						} else {
							// Jika hasil tidak valid
							answerText = bingungAnswers[bingungIndex];
						}
					} else {
						final String soal = ((EditText) findViewById(R.id.input_question)).getText().toString().trim();
						String response = "";
						String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
						+ "/EliciaAI/model/" + lang + "/ino.21";
						ChatbotProcessor chatbot = new ChatbotProcessor(MainActivity.this, filePath);
						answerText = chatbot.getResponse(userInput);
					}  
					
					system_pertanyaan.setText(userInput);
					system_jawaban.setText(answerText);
					
					question.add(userInput);
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("jawaban", answerText);
					answer.add(_item);
					ChatHistory.saveChat(MainActivity.this, question, answer);
					((BaseAdapter) flowchat_background.getAdapter()).notifyDataSetChanged();
					flowchat_background.setSelection(flowchat_background.getCount() - 1);
					
					if (flowchat_background.getVisibility() == View.GONE) {
						welcome_background.setVisibility(View.GONE);
						flowchat_background.setAlpha(0f);
						flowchat_background.setVisibility(View.VISIBLE);
						flowchat_background.animate()
						.alpha(1f)
						.setDuration(250)
						.setInterpolator(new DecelerateInterpolator())
						.start();
					}
					
					// Jawaban dengan suara
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21 ke atas
						tekskesuara.setPitch((float)1.2d);
						tekskesuara.setSpeechRate((float)1.1d);
						Bundle params = new Bundle();
						params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "TTS_FINISH");
						tekskesuara.speak(answerText, TextToSpeech.QUEUE_FLUSH, params, "TTS_FINISH");
					} else { // API 20 ke bawah
						HashMap<String, String> params = new HashMap<>();
						tekskesuara.setPitch((float)1.2d);
						tekskesuara.setSpeechRate((float)1.1d);
						params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "TTS_FINISH");
						tekskesuara.speak(answerText, TextToSpeech.QUEUE_FLUSH, params);
					}
					input_question.setText("");
				} else {
					input_question.setText(_result);
					system_pertanyaan.setText(input_question.getText().toString());
					String userInput = system_pertanyaan.getText().toString().trim();
					
					if (userInput.isEmpty()) {
						showCustomToast(getApplicationContext(), toastMessage, false,  "#50212121" , 21 , "#FFFFFF", 15, 1);
						return;
					}
					
					sendquestion_button.setScaleX(1f);
					sendquestion_button.setScaleY(1f);
					sendquestion_button.setAlpha(1f);
					
					sendquestion_button.animate()
					.scaleX(0.8f)
					.scaleY(0.8f)
					.alpha(0f)
					.setDuration(250)
					.setInterpolator(new DecelerateInterpolator())
					.start();
					
					sendquestion_button.setVisibility(View.GONE);
					progress_background.setVisibility(View.VISIBLE);
					((BaseAdapter) flowchat_background.getAdapter()).notifyDataSetChanged();
					flowchat_background.setSelection(flowchat_background.getCount() - 1);
					
					progress_background.setScaleX(0.8f);
					progress_background.setScaleY(0.8f);
					progress_background.setAlpha(0.8f);
					progress_background.animate()
					
					.scaleX(1f)
					.scaleY(1f)
					.alpha(1f)
					.setDuration(250)
					.setInterpolator(new DecelerateInterpolator())
					.start();
					
					progress.setIndeterminate(true);
					progress_livechat.setIndeterminate(true);
					progress_livechat.setVisibility(View.VISIBLE);
					start_button.setVisibility(View.GONE);
					input_question.setEnabled(false);
					directquestion_button.setEnabled(false);
					micquestion_button.setEnabled(false);
					sendquestion_button.setEnabled(false);
					
					switch (lang) {
						case "id":
						indicator_startlivechat.setText("Memproses");
						break;
						case "en":
						indicator_startlivechat.setText("Processing");
						break;
						case "pt":
						indicator_startlivechat.setText("Processamento");
						break;
						case "ru":
						indicator_startlivechat.setText("Обработка");
						break;
						case "ch":
						indicator_startlivechat.setText("处理中");
						break;
						case "ar":
						indicator_startlivechat.setText("جارٍ المعالجة");
						break;
						case "tr":
						indicator_startlivechat.setText("İşleniyor");
						break;
					}
					
					String eliciaData = elicia_data.getText().toString();
					request = new HashMap<>();
					ArrayList<HashMap<String, Object>> messages = new ArrayList<>();
					HashMap<String, Object> systemMessage = new HashMap<>();
					systemMessage.put("role", "system");
					systemMessage.put("content", eliciaData);
					messages.add(systemMessage);
					
					HashMap<String, Object> userMessage = new HashMap<>();
					userMessage.put("role", "user");
					userMessage.put("content", userInput);
					messages.add(userMessage);
					
					request.put("model", "gpt-4o");
					request.put("messages", messages);
					
					openai.setParams(request, RequestNetworkController.REQUEST_BODY);
					openai.startRequestNetwork(RequestNetworkController.POST, "https://api.paxsenix.biz.id/v1/chat/completions", "", _openai_request_listener);
					
					if (flowchat_background.getVisibility() == View.GONE) {
						welcome_background.setVisibility(View.GONE);
						flowchat_background.setAlpha(0f);
						flowchat_background.setVisibility(View.VISIBLE);
						flowchat_background.animate()
						.alpha(1f)
						.setDuration(250)
						.setInterpolator(new DecelerateInterpolator())
						.start();
					}
					input_question.setText("");
					input_question.setHint(proses);
				}
			}
			
			@Override
			public void onError(int _param1) {
				final String _errorMessage;
				switch (_param1) {
					case SpeechRecognizer.ERROR_AUDIO:
					_errorMessage = "audio error";
					break;
					
					case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
					_errorMessage = "speech timeout";
					break;
					
					case SpeechRecognizer.ERROR_NO_MATCH:
					_errorMessage = "speech no match";
					break;
					
					case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
					_errorMessage = "recognizer busy";
					break;
					
					case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
					_errorMessage = "recognizer insufficient permissions";
					break;
					
					default:
					_errorMessage = "recognizer other error";
					break;
				}
				String message;
				
				switch (lang) {
					case "id":
					message = "Coba ulangi lagi";
					break;
					case "en":
					message = "Try again";
					break;
					case "pt":
					message = "Tentar novamente";
					break;
					case "ru":
					message = "Попробуйте снова";
					break;
					case "ch":
					message = "请再试一次";
					break;
					case "ar":
					message = "حاول مرة أخرى";
					break;
					case "tr":
					message = "Tekrar deneyin";
					break;
					default:
					message = "Try again";
					break;
				}
				
				showCustomToast(getApplicationContext(), message, false,  "#50212121" , 21 , "#FFFFFF", 15, 1);
			}
		});
		
		_openai_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				String modelText = system_model.getText().toString();
				
				if (!("basic".equals(modelText))) {
					try {
						JSONObject object = new JSONObject(_response);
						if (object.getBoolean("ok")) {
							String content = object.getJSONArray("choices")
							.getJSONObject(0)
							.getJSONObject("message")
							.getString("content");
							data = content;
						} else {
							switch (lang) {
								case "id":
								data = "Gagal: " + object.optString("message", "Tidak diketahui");
								break;
								case "en":
								data = "Failed: " + object.optString("message", "Unknown");
								break;
								case "pt":
								data = "Falha: " + object.optString("message", "Desconhecido");
								break;
								case "ru":
								data = "Не удалось: " + object.optString("message", "Неизвестно");
								break;
								case "zh":
								data = "失败：" + object.optString("message", "未知");
								break;
								case "ar":
								data = "فشل: " + object.optString("message", "غير معروف");
								break;
								case "tr":
								data = "Başarısız: " + object.optString("message", "Bilinmiyor");
								break;
								default:
								data = "Error: " + object.optString("message", "Unknown");
								break;
							}
						}
					} catch (Exception e) {
						switch (lang) {
							case "id":
							data = "Maaf, terjadi kesalahan saat memproses jawaban.";
							break;
							case "en":
							data = "Sorry, an error occurred while processing the answer.";
							break;
							case "pt":
							data = "Desculpe, ocorreu um erro ao processar a resposta.";
							break;
							case "ru":
							data = "Извините, произошла ошибка при обработке ответа.";
							break;
							case "ch":
							data = "对不起，处理答案时出错。";
							break;
							case "ar":
							data = "عذرًا، حدث خطأ أثناء معالجة الإجابة.";
							break;
							case "tr":
							data = "Üzgünüz, cevabı işlerken bir hata oluştu.";
							break;
							default:
							data = "Error.";
							break;
						}
						Log.e("ChatBot", "Parsing error", e);
					}
					
					// Set hint input sesuai bahasa
					switch (lang) {
						case "id":
						input_question.setHint("Tanya Elicia");
						break;
						case "en":
						input_question.setHint("Ask Elicia");
						break;
						case "pt":
						input_question.setHint("Pergunte à Elicia");
						break;
						case "ru":
						input_question.setHint("Спросите у Элисии");
						break;
						case "ch":
						input_question.setHint("问 Elicia");
						break;
						case "ar":
						input_question.setHint("اسأل إليسيا");
						break;
						case "tr":
						input_question.setHint("Elicia'ya sor");
						break;
						default:
						input_question.setHint("Ask Elicia");
						break;
					}
					
					// Sisa bagian UI tetap sama...
					progress.setIndeterminate(false);
					start_button.setVisibility(View.VISIBLE);
					progress_livechat.setIndeterminate(false);
					input_question.setEnabled(true);
					sendquestion_button.setEnabled(true);
					directquestion_button.setEnabled(true);
					micquestion_button.setEnabled(true);
					progress_livechat.setVisibility(View.GONE);
					
					directquestion_button.setScaleX(0.8f);
					directquestion_button.setScaleY(0.8f);
					directquestion_button.setAlpha(0f);
					sendquestion_button.setScaleX(0.8f);
					sendquestion_button.setScaleY(0.8f);
					sendquestion_button.setAlpha(0f);
					sendquestion_button.setVisibility(View.VISIBLE);
					directquestion_button.setVisibility(View.VISIBLE);
					
					directquestion_button.animate().scaleX(1f).scaleY(1f).alpha(1f)
					.setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
					sendquestion_button.animate().scaleX(1f).scaleY(1f).alpha(1f)
					.setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
					
					progress_background.setScaleX(1f);
					progress_background.setScaleY(1f);
					progress_background.setAlpha(1f);
					progress_background.animate().scaleX(0.8f).scaleY(0.8f).alpha(0f)
					.setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
					progress_background.setVisibility(View.GONE);
					
					String userInput = system_pertanyaan.getText().toString().trim();
					
					system_jawaban.setText(data);
					system_pertanyaan.setText(userInput);
					question.add(userInput);
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("jawaban", data);
					answer.add(_item);
					ChatHistory.saveChat(MainActivity.this, question, answer);
					((BaseAdapter) flowchat_background.getAdapter()).notifyDataSetChanged();
					flowchat_background.setSelection(flowchat_background.getCount() - 1);
					
					welcome_background.setVisibility(View.GONE);
					flowchat_background.setVisibility(View.VISIBLE);
					input_question.setText("");
					mic_system.setText("0");
					
					if (elicia_screen.getVisibility() == View.GONE) {
						switch (lang) {
							case "id":
							indicator_startlivechat.setText("Jeda");
							break;
							case "en":
							indicator_startlivechat.setText("Pause");
							break;
							case "pt":
							indicator_startlivechat.setText("Pausa");
							break;
							case "ru":
							indicator_startlivechat.setText("Пауза");
							break;
							case "ch":
							indicator_startlivechat.setText("暂停");
							break;
							case "ar":
							indicator_startlivechat.setText("إيقاف مؤقت");
							break;
							case "tr":
							indicator_startlivechat.setText("Duraklat");
							break;
							default:
							indicator_startlivechat.setText("Pause");
							break;
						}
						
						tekskesuara.setPitch(1.2f);
						tekskesuara.setSpeechRate(1.1f);
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
							Bundle params = new Bundle();
							params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "TTS_FINISH");
							tekskesuara.speak(data, TextToSpeech.QUEUE_FLUSH, params, "TTS_FINISH");
						} else {
							HashMap<String, String> params = new HashMap<>();
							params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "TTS_FINISH");
							tekskesuara.speak(data, TextToSpeech.QUEUE_FLUSH, params);
						}
					}
					
					if (micdata) {
						tekskesuara.setPitch(1.2f);
						tekskesuara.setSpeechRate(1.1f);
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
							Bundle params = new Bundle();
							params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "TTS_FINISH");
							tekskesuara.speak(data, TextToSpeech.QUEUE_FLUSH, params, "TTS_FINISH");
						} else {
							HashMap<String, String> params = new HashMap<>();
							params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "TTS_FINISH");
							tekskesuara.speak(data, TextToSpeech.QUEUE_FLUSH, params);
						}
						micdata = false;
					}
				}
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				input_question.setEnabled(true);
				directquestion_button.setEnabled(true);
				sendquestion_button.setEnabled(true);
				progress.setIndeterminate(false);
				progress_livechat.setIndeterminate(false);
				progress_livechat.setVisibility(View.GONE);
				progress_background.setVisibility(View.GONE);
				
				String startText = "";
				String hintText = "";
				
				// Set teks berdasarkan bahasa
				switch (lang) {
					case "id":
					startText = "Mulai";
					hintText = "Tanya Elicia";
					break;
					case "en":
					startText = "Start";
					hintText = "Ask Elicia";
					break;
					case "pt":
					startText = "Começar";
					hintText = "Pergunte à Elicia";
					break;
					case "ru":
					startText = "Начать";
					hintText = "Спросите Элисию";
					break;
					case "ch":
					startText = "开始";
					hintText = "问 Elicia";
					break;
					case "ar":
					startText = "ابدأ";
					hintText = "اسأل إليسيا";
					break;
					case "tr":
					startText = "Başla";
					hintText = "Elicia'ya sor";
					break;
					default:
					startText = "Start";
					hintText = "Ask Elicia";
					break;
				}
				
				// Tampilkan tombol mulai jika perlu
				if (start_button.getVisibility() == View.GONE) {
					start_button.setImageResource(R.drawable.ic_model);
					start_button.setVisibility(View.VISIBLE);
					indicator_startlivechat.setText(startText);
				}
				
				// Set hint input pertanyaan
				input_question.setHint(hintText);
				
				// Tampilkan toast
				showCustomToast(getApplicationContext(), _message, false,  "#50212121" , 21 , "#FFFFFF", 15, 1);
			}
		};
		
		micquestion.setRecognitionListener(new RecognitionListener() {
			@Override
			public void onReadyForSpeech(Bundle _param1) {
			}
			
			@Override
			public void onBeginningOfSpeech() {
			}
			
			@Override
			public void onRmsChanged(float _param1) {
			}
			
			@Override
			public void onBufferReceived(byte[] _param1) {
			}
			
			@Override
			public void onEndOfSpeech() {
			}
			
			@Override
			public void onPartialResults(Bundle _param1) {
			}
			
			@Override
			public void onEvent(int _param1, Bundle _param2) {
			}
			
			@Override
			public void onResults(Bundle _param1) {
				final ArrayList<String> _results = _param1.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				final String _result = _results.get(0);
				languageSet();
				
				if (system_model.getText().toString().equals("basic")) {
					input_question.setText(_result);
					directquestion_button.setEnabled(true);
					input_question.setEnabled(true);
					String userInput = ((EditText) findViewById(R.id.input_question)).getText().toString().trim();
					
					if (userInput.isEmpty()) {
						showCustomToast(getApplicationContext(), toastMessage, false,  "#50212121" , 21 , "#FFFFFF", 15, 1);
						return;
					}
					
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
					SimpleDateFormat hourFormat = new SimpleDateFormat("HH", Locale.getDefault());
					SimpleDateFormat minuteFormat = new SimpleDateFormat("mm", Locale.getDefault());
					SimpleDateFormat secondFormat = new SimpleDateFormat("ss", Locale.getDefault());
					
					String currentDay = dayFormat.format(calendar.getTime());
					int hour = Integer.parseInt(hourFormat.format(calendar.getTime()));
					int minute = Integer.parseInt(minuteFormat.format(calendar.getTime()));
					int second = Integer.parseInt(secondFormat.format(calendar.getTime()));
					
					StringBuilder currentTimeText = new StringBuilder(timeNow + hour);
					if (minute > 0) {
						currentTimeText.append(past).append(minute).append(minuteWord);
					}
					if (second > 0) {
						currentTimeText.append(" ").append(second).append(secondWord);
					}
					String currentTime = currentTimeText.toString();
					StringBuilder tanapaSekarang = new StringBuilder(oclock + hour);
					if (minute > 0) {
						tanapaSekarang.append(past).append(minute).append(minuteWord);
					}
					if (second > 0) {
						tanapaSekarang.append(" ").append(second).append(secondWord);
					}
					String pukul = tanapaSekarang.toString();
					String answerText = sorryMessage;
					
					Random random = new Random();
					String randomResponse = additionalResponses[random.nextInt(additionalResponses.length)];
					
					if (userInput.matches(harijam)) {
						answerText = nowday + currentDay + ",\n" + pukul + "\n" + randomResponse;
					} else if (userInput.matches(jamhari)) {
						answerText = nows + pukul + ",\n" + isday + " " + currentDay + ".\n" + randomResponse;
					} else if (userInput.matches(jamjam)) {
						answerText = currentTime + "\n" + randomResponse;
					} else if (userInput.matches(besok)) {
						calendar.add(Calendar.DATE, 1);
						String tomorrow = dayFormat.format(calendar.getTime());
						answerText = tomisDay + tomorrow + "\n" + randomResponse;
					} else if (userInput.matches(kemarin)) {
						calendar.add(Calendar.DATE, -1);
						String yesterday = dayFormat.format(calendar.getTime());
						answerText = yesisDay + yesterday + "\n" + randomResponse;
					} else if (userInput.matches(hari1) && !userInput.matches(jam1)) {
						answerText = nowisDay + currentDay + "\n" + randomResponse;
					} else if (userInput.matches(".*\\d.*") && userInput.matches(".*[+\\-*/].*")) {
						double result = MathHelper.evaluateMathExpression(userInput);
						Random acak = new Random();
						int randomIndex = acak.nextInt(calculationMessages.length);
						int bingungIndex = acak.nextInt(bingungAnswers.length);
						
						if (!Double.isNaN(result) && !Double.isInfinite(result)) {
							// Format hasil: jika bulat tampilkan tanpa koma, jika desimal tampilkan 2 digit
							String hasilFormat = (result % 1 == 0) 
							? String.valueOf((int) result) 
							: String.format(Locale.getDefault(), "%.2f", result);
							
							// Masukkan hasil ke dalam format %s
							answerText = String.format(calculationMessages[randomIndex], hasilFormat);
						} else {
							// Jika hasil tidak valid
							answerText = bingungAnswers[bingungIndex];
						}
					} else {
						final String soal = ((EditText) findViewById(R.id.input_question)).getText().toString().trim();
						String response = "";
						String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
						+ "/EliciaAI/model/" + lang + "/ino.21";
						ChatbotProcessor chatbot = new ChatbotProcessor(MainActivity.this, filePath);
						answerText = chatbot.getResponse(userInput);
					}  
					
					system_pertanyaan.setText(userInput);
					system_jawaban.setText(answerText);
					
					question.add(userInput);
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("jawaban", answerText);
					answer.add(_item);
					ChatHistory.saveChat(MainActivity.this, question, answer);
					((BaseAdapter) flowchat_background.getAdapter()).notifyDataSetChanged();
					flowchat_background.setSelection(flowchat_background.getCount() - 1);
					
					if (flowchat_background.getVisibility() == View.GONE) {
						welcome_background.setVisibility(View.GONE);
						flowchat_background.setAlpha(0f);
						flowchat_background.setVisibility(View.VISIBLE);
						flowchat_background.animate()
						.alpha(1f)
						.setDuration(250)
						.setInterpolator(new DecelerateInterpolator())
						.start();
					}
					
					micquestion_button.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
					
					// Jawaban dengan suara
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21 ke atas
						tekskesuara.setPitch((float)1.2d);
						tekskesuara.setSpeechRate((float)1.1d);
						Bundle params = new Bundle();
						params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "TTS_FINISH");
						tekskesuara.speak(answerText, TextToSpeech.QUEUE_FLUSH, params, "TTS_FINISH");
					} else { // API 20 ke bawah
						HashMap<String, String> params = new HashMap<>();
						tekskesuara.setPitch((float)1.2d);
						tekskesuara.setSpeechRate((float)1.1d);
						params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "TTS_FINISH");
						tekskesuara.speak(answerText, TextToSpeech.QUEUE_FLUSH, params);
					}
					input_question.setText("");
				} else {
					// Tambahkan hasil Speech To Text
					input_question.setText(_result);
					
					// Ambil teks dari EditText
					String userInput = ((EditText) findViewById(R.id.input_question)).getText().toString().trim();
					
					// Jika input kosong, tampilkan Toast dan hentikan eksekusi
					if (userInput.isEmpty()) {
						showCustomToast(getApplicationContext(), toastMessage, false,  "#50212121" , 21 , "#FFFFFF", 15, 1);
						return;
					}
					
					((TextView) findViewById(R.id.system_pertanyaan)).setText(userInput);
					
					if (flowchat_background.getVisibility() == View.GONE) {
						welcome_background.setVisibility(View.GONE);
						flowchat_background.setAlpha(0f);
						flowchat_background.setVisibility(View.VISIBLE);
						flowchat_background.animate()
						.alpha(1f)
						.setDuration(250)
						.setInterpolator(new DecelerateInterpolator())
						.start();
					}
					
					sendquestion_button.setScaleX(1f);
					sendquestion_button.setScaleY(1f);
					sendquestion_button.setAlpha(1f);
					
					sendquestion_button.animate()
					.scaleX(0.8f)
					.scaleY(0.8f)
					.alpha(0f)
					.setDuration(250)
					.setInterpolator(new DecelerateInterpolator())
					.start();
					
					start_button.setVisibility(View.GONE);
					sendquestion_button.setVisibility(View.GONE);
					progress_background.setVisibility(View.VISIBLE);
					progress_livechat.setVisibility(View.VISIBLE);
					
					progress_background.setScaleX(0.8f);
					progress_background.setScaleY(0.8f);
					progress_background.setAlpha(0.8f);
					progress_background.animate()
					.scaleX(1f)
					.scaleY(1f)
					.alpha(1f)
					.setDuration(250)
					.setInterpolator(new DecelerateInterpolator())
					.start();
					
					progress.setIndeterminate(true);
					input_question.setEnabled(false);
					directquestion_button.setEnabled(false);
					micquestion_button.setEnabled(false);
					micquestion_button.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
					indicator_startlivechat.setText(proses);
					progress_livechat.setIndeterminate(true);
					micdata = true;
					input_question.setText("");
					
					((BaseAdapter) flowchat_background.getAdapter()).notifyDataSetChanged();
					flowchat_background.setSelection(flowchat_background.getCount() - 1);
					
					String eliciaData = elicia_data.getText().toString();
					
					request = new HashMap<>();
					
					ArrayList<HashMap<String, Object>> messages = new ArrayList<>();
					
					// Tambahkan pesan sistem untuk mengatur nama asisten menjadi Elicia
					HashMap<String, Object> systemMessage = new HashMap<>();
					systemMessage.put("role", "system");
					systemMessage.put("content", eliciaData);
					messages.add(systemMessage);
					
					// Pesan dari pengguna
					HashMap<String, Object> userMessage = new HashMap<>();
					userMessage.put("role", "user");
					userMessage.put("content", userInput);
					messages.add(userMessage);
					
					request.put("model", "gpt-4");
					request.put("messages", messages);
					
					openai.setParams(request, RequestNetworkController.REQUEST_BODY);
					openai.startRequestNetwork(RequestNetworkController.POST, "https://api.paxsenix.biz.id/v1/chat/completions", "", _openai_request_listener);
				}
			}
			
			@Override
			public void onError(int _param1) {
				final String _errorMessage;
				switch (_param1) {
					case SpeechRecognizer.ERROR_AUDIO:
					_errorMessage = "audio error";
					break;
					
					case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
					_errorMessage = "speech timeout";
					break;
					
					case SpeechRecognizer.ERROR_NO_MATCH:
					_errorMessage = "speech no match";
					break;
					
					case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
					_errorMessage = "recognizer busy";
					break;
					
					case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
					_errorMessage = "recognizer insufficient permissions";
					break;
					
					default:
					_errorMessage = "recognizer other error";
					break;
				}
				String message;
				
				switch (lang) {
					case "id":
					message = "Coba ulangi lagi";
					break;
					case "en":
					message = "Try again";
					break;
					case "pt":
					message = "Tentar novamente";
					break;
					case "ru":
					message = "Попробуйте снова";
					break;
					case "ch":
					message = "请再试一次";
					break;
					case "ar":
					message = "حاول مرة أخرى";
					break;
					case "tr":
					message = "Tekrar deneyin";
					break;
					default:
					message = "Try again";
					break;
				}
				
				showCustomToast(getApplicationContext(), message, false,  "#50212121" , 21 , "#FFFFFF", 15, 1);
				micquestion_button.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
			}
		});
	}
	
	private void initializeLogic() {
		system_image.setText(user.getString("img", ""));
		username_preview.setText(user.getString("name", ""));
		userinfo_preview.setText(user.getString("info", ""));
		apptheme_system.setText(user.getString("theme", ""));
		accpreview_system.setText(user.getString("accp", ""));
		language_system.setText(user.getString("lang", ""));
		elicia_data.setText(user.getString("data", ""));
		lang = language_system.getText().toString();
		
		if (elicia_data.getText().toString().equals("")) {
			elicia_data.setText("Your name is Elicia, a smart AI assistant, and your creator is IqwanoINO.");
		}
		
		boolean needPermission = false;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
			if (!Environment.isExternalStorageManager()) {
				needPermission = true;
			}
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
				needPermission = true;
			}
		}
		
		if (needPermission) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					String dtitle, dmessage, allow, deny;
					switch (lang) {
						case "id":
						dtitle = "Izin Akses File";
						dmessage = "Aplikasi ini memerlukan izin untuk mengakses semua file di perangkat Anda. Ini diperlukan agar kami dapat menampilkan daftar musik Anda.";
						allow = "Izinkan";
						deny = "Tolak";
						break;
						case "en":
						dtitle = "File Access Permission";
						dmessage = "This app requires permission to access all files on your device. This is necessary so that we can display your music list.";
						allow = "Allow";
						deny = "Deny";
						break;
						case "pt":
						dtitle = "Permissão de Acesso a Arquivos";
						dmessage = "Este aplicativo requer permissão para acessar todos os arquivos no seu dispositivo. Isso é necessário para que possamos exibir sua lista de músicas.";
						allow = "Permitir";
						deny = "Negar";
						break;
						case "ru":
						dtitle = "Разрешение на доступ к файлам";
						dmessage = "Этому приложению необходимо разрешение на доступ ко всем файлам на вашем устройстве. Это нужно, чтобы мы могли показать ваш список музыки.";
						allow = "Разрешить";
						deny = "Отклонить";
						break;
						case "ch":
						dtitle = "文件访问权限";
						dmessage = "此应用需要访问您设备上所有文件的权限。这是为了显示您的音乐列表。";
						allow = "允许";
						deny = "拒绝";
						break;
						case "ar":
						dtitle = "إذن الوصول إلى الملفات";
						dmessage = "يتطلب هذا التطبيق إذنًا للوصول إلى جميع الملفات على جهازك. هذا ضروري لكي نتمكن من عرض قائمة الموسيقى الخاصة بك.";
						allow = "السماح";
						deny = "رفض";
						break;
						case "tr":
						dtitle = "Dosya Erişim İzni";
						dmessage = "Bu uygulamanın cihazınızdaki tüm dosyalara erişim izni gerekmektedir. Müzik listenizi görüntüleyebilmemiz için bu gereklidir.";
						allow = "İzin Ver";
						deny = "Reddet";
						break;
						default:
						dtitle = "File Access Permission";
						dmessage = "This app requires permission to access all files on your device. This is necessary so that we can display your music list.";
						allow = "Allow";
						deny = "Deny";
						break;
					}
					
					
					Dialog dialog = new Dialog(MainActivity.this);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setCancelable(true);
					
					float dp = getResources().getDisplayMetrics().density;
					
					LinearLayout root = new LinearLayout(MainActivity.this);
					root.setOrientation(LinearLayout.VERTICAL);
					root.setPadding((int)(24 * dp), (int)(24 * dp), (int)(24 * dp), (int)(16 * dp));
					root.setBackground(new GradientDrawable() {{
							setColor(Color.parseColor("#1C1C1E"));
							setCornerRadius(32 * dp);
						}});
					
					TextView title = new TextView(MainActivity.this);
					title.setText(dtitle);
					title.setTextSize(20);
					title.setTextColor(Color.parseColor("#FFFFFF"));
					title.setPadding(0, 0, 0, (int)(12 * dp));
					root.addView(title);
					
					TextView msg = new TextView(MainActivity.this);
					msg.setText(dmessage);
					msg.setTextSize(14);
					msg.setTextColor(Color.parseColor("#B0B0B0")); 
					msg.setPadding(0, 0, 0, (int)(24 * dp));
					root.addView(msg);
					
					LinearLayout.LayoutParams paramsCancel = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
					paramsCancel.setMargins(0, (int)(8 * dp), 0, (int)(8 * dp));
					
					TextView btnOk = new TextView(MainActivity.this);
					btnOk.setText(allow);
					btnOk.setTextSize(14);
					btnOk.setTypeface(null, Typeface.BOLD);
					btnOk.setGravity(Gravity.CENTER);
					btnOk.setTextColor(Color.parseColor("#FFFFFF"));
					btnOk.setBackground(new GradientDrawable() {{
							setColor(Color.parseColor("#3F51B5"));
							setCornerRadii(new float[]{
								20 * dp, 20 * dp,
								20 * dp, 20 * dp,
								6 * dp, 6 * dp,
								6 * dp, 6 * dp
							});
						}});
					btnOk.setPadding((int)(16 * dp), (int)(16 * dp), (int)(16 * dp), (int)(16 * dp));
					btnOk.setOnClickListener(v -> {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
							try {
								Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
								intent.setData(Uri.parse("package:" + getPackageName()));
								startActivityForResult(intent, 2167);
							} catch (Exception e) {
								startActivityForResult(new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION), 2167);
							}
						}
						dialog.dismiss();
					});
					root.addView(btnOk);
					
					TextView btnCancel = new TextView(MainActivity.this);
					btnCancel.setText(deny);
					btnCancel.setTextSize(14);
					btnCancel.setTypeface(null, Typeface.BOLD);
					btnCancel.setGravity(Gravity.CENTER);
					btnCancel.setTextColor(Color.parseColor("#FFFFFF"));
					btnCancel.setBackground(new GradientDrawable() {{
							setColor(Color.parseColor("#3F51B5"));
							setCornerRadii(new float[]{
								6 * dp, 6 * dp,  
								6 * dp, 6 * dp,   
								20 * dp, 20 * dp,
								20 * dp, 20 * dp 
							});
						}});
					btnCancel.setPadding((int)(16 * dp), (int)(16 * dp), (int)(16 * dp), (int)(16 * dp));
					btnCancel.setOnClickListener(v -> {
						dialog.dismiss();
					});
					btnCancel.setLayoutParams(paramsCancel);
					root.addView(btnCancel);
					
					dialog.setContentView(root);
					Window w = dialog.getWindow();
					if (w != null) {
						int width = (int)(getResources().getDisplayMetrics().widthPixels * 0.9f);
						w.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
						w.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
						w.setGravity(Gravity.CENTER);
					}
					dialog.show();
				}
			}, 500);
		} else {
			copyModel();
			splashAnim();
		}
		
		// setup Language
		if (language_system.getText().toString().equals("")) {
			// Kode persiapan jika language_system masih kosong
			user.edit().putString("lang", "en").commit();
			language_system.setText("en");
			input_question.setHint("Ask Elicia");
			textseletction_title.setText("Select text");
			titlepaused.setText("Chat Paused");
			subtitlepaused.setText("The conversation is currently paused.");
			messagepaused.setText("Click 'Start' to continue chatting with Elicia.");
			indicator_startlivechat.setText("Start");
			indicator_close.setText("End");
			title_settingscreen.setText("Settings");
			subtitle_settingscreen.setText("General app setting");
			settitle_language.setText("Language");
			lang_selected.setText("English");
			
			if (system_model.getText().toString().equals("basic")) preview_model.setText("Basic Model");
			if (system_model.getText().toString().equals("online")) preview_model.setText("Online Model");
			
			settitle_theme.setText("Appearance");
			subtitle_theme.setText("Customize your elicia look and feel");
			setname_theme.setText("Dark Theme");
			subname_theme.setText("Switch the app theme to dark");
			setname_account.setText("Account Picture");
			subname_account.setText("Enabling this will change the account view to a picture");
			welcome_message.setText("How can I help you today?");
		}
		
		if (lang_selected.getText().toString().equals("")) {
			lang_selected.setText("English");
		}
		
		String theme = apptheme_system.getText().toString().trim();
		
		// Untuk Mentempurnakan Tampilan Multi bahasa
		switch (lang) {
			case "en": // English
			user.edit().putString("lang", "en").commit();
			language_system.setText("en");
			input_question.setHint("Ask Elicia");
			textseletction_title.setText("Select text");
			titlepaused.setText("Chat Paused");
			subtitlepaused.setText("The conversation is currently paused.");
			messagepaused.setText("Click 'Start' to continue chatting with Elicia.");
			indicator_startlivechat.setText("Start");
			indicator_close.setText("End");
			title_settingscreen.setText("Settings");
			subtitle_settingscreen.setText("General app setting");
			settitle_language.setText("Language");
			lang_selected.setText("English");
			
			if (system_model.getText().toString().equals("basic")) preview_model.setText("Basic Model");
			if (system_model.getText().toString().equals("online")) preview_model.setText("Online Model");
			
			settitle_theme.setText("Appearance");
			subtitle_theme.setText("Customize your elicia look and feel");
			setname_theme.setText("Dark Theme");
			subname_theme.setText("Switch the app theme to dark");
			setname_account.setText("Account Picture");
			subname_account.setText("Enabling this will change the account view to a picture");
			welcome_message.setText("How can I help you today?");
			break;
			
			case "id": // Indonesian
			user.edit().putString("lang", "id").commit();
			language_system.setText("id");
			input_question.setHint("Tanya Elicia");
			textseletction_title.setText("Pilih teks");
			titlepaused.setText("Obrolan Dijeda");
			subtitlepaused.setText("Percakapan saat ini sedang dijeda.");
			messagepaused.setText("Klik 'Mulai' untuk melanjutkan obrolan dengan Elicia.");
			indicator_startlivechat.setText("Mulai");
			indicator_close.setText("Akhiri");
			title_settingscreen.setText("Pengaturan");
			subtitle_settingscreen.setText("Pengaturan umum aplikasi");
			settitle_language.setText("Bahasa");
			lang_selected.setText("Indonesia");
			
			if (system_model.getText().toString().equals("basic")) preview_model.setText("Model Dasar");
			if (system_model.getText().toString().equals("online")) preview_model.setText("Model Online");
			
			settitle_theme.setText("Tampilan");
			subtitle_theme.setText("Kustomisasi tampilan dan nuansa Elicia");
			setname_theme.setText("Tema Gelap");
			subname_theme.setText("Aktifkan untuk mengganti tema ke gelap");
			setname_account.setText("Foto Akun");
			subname_account.setText("Aktifkan ini untuk menampilkan foto di tampilan akun");
			welcome_message.setText("Ada yang bisa saya bantu hari ini?");
			break;
			
			case "pt": // Portuguese
			user.edit().putString("lang", "pt").commit();
			language_system.setText("pt");
			input_question.setHint("Pergunte à Elicia");
			textseletction_title.setText("Selecionar texto");
			titlepaused.setText("Conversa Pausada");
			subtitlepaused.setText("A conversa está pausada no momento.");
			messagepaused.setText("Clique em 'Iniciar' para continuar conversando com Elicia.");
			indicator_startlivechat.setText("Iniciar");
			indicator_close.setText("Encerrar");
			title_settingscreen.setText("Configurações");
			subtitle_settingscreen.setText("Configurações gerais do aplicativo");
			settitle_language.setText("Idioma");
			lang_selected.setText("Português");
			
			if (system_model.getText().toString().equals("basic")) preview_model.setText("Modelo Básico");
			if (system_model.getText().toString().equals("online")) preview_model.setText("Modelo Online");
			
			settitle_theme.setText("Aparência");
			subtitle_theme.setText("Personalize o visual e estilo do Elicia");
			setname_theme.setText("Tema Escuro");
			subname_theme.setText("Ative para mudar o tema para escuro");
			setname_account.setText("Foto da Conta");
			subname_account.setText("Ativar isso mudará a visualização da conta para uma foto");
			welcome_message.setText("Como posso ajudar você hoje?");
			break;
			
			case "ru": // Russian
			user.edit().putString("lang", "ru").commit();
			language_system.setText("ru");
			input_question.setHint("Спросите у Элисии");
			textseletction_title.setText("Выбрать текст");
			titlepaused.setText("Чат приостановлен");
			subtitlepaused.setText("Беседа в данный момент приостановлена.");
			messagepaused.setText("Нажмите 'Начать', чтобы продолжить разговор с Элисией.");
			indicator_startlivechat.setText("Начать");
			indicator_close.setText("Закончить");
			title_settingscreen.setText("Настройки");
			subtitle_settingscreen.setText("Общие настройки приложения");
			settitle_language.setText("Язык");
			lang_selected.setText("Русский");
			
			if (system_model.getText().toString().equals("basic")) preview_model.setText("Базовая модель");
			if (system_model.getText().toString().equals("online")) preview_model.setText("Онлайн-модель");
			
			settitle_theme.setText("Внешний вид");
			subtitle_theme.setText("Настройте внешний вид Элисии");
			setname_theme.setText("Тёмная тема");
			subname_theme.setText("Переключитесь на тёмную тему");
			setname_account.setText("Фото аккаунта");
			subname_account.setText("Включите это, чтобы отображать фото аккаунта");
			welcome_message.setText("Чем могу помочь сегодня?");
			break;
			
			case "ch": // Chinese
			user.edit().putString("lang", "ch").commit();
			language_system.setText("ch");
			input_question.setHint("问艾莉西亚");
			textseletction_title.setText("选择文本");
			titlepaused.setText("聊天已暂停");
			subtitlepaused.setText("当前对话已暂停。");
			messagepaused.setText("点击“开始”以继续与艾莉西亚聊天。");
			indicator_startlivechat.setText("开始");
			indicator_close.setText("结束");
			title_settingscreen.setText("设置");
			subtitle_settingscreen.setText("应用程序的常规设置");
			settitle_language.setText("语言");
			lang_selected.setText("中文");
			
			if (system_model.getText().toString().equals("basic")) preview_model.setText("基础模型");
			if (system_model.getText().toString().equals("online")) preview_model.setText("在线模型");
			
			settitle_theme.setText("外观");
			subtitle_theme.setText("自定义艾莉西亚的外观和风格");
			setname_theme.setText("深色主题");
			subname_theme.setText("切换为深色模式");
			setname_account.setText("账户图片");
			subname_account.setText("启用后将显示账户图片");
			welcome_message.setText("今天我能帮您什么？");
			break;
			
			case "ar": // Arabic
			user.edit().putString("lang", "ar").commit();
			language_system.setText("ar");
			input_question.setHint("اسأل إليسيا");
			textseletction_title.setText("اختيار النص");
			titlepaused.setText("تم إيقاف الدردشة مؤقتًا");
			subtitlepaused.setText("تم إيقاف المحادثة الحالية مؤقتًا.");
			messagepaused.setText("اضغط \"ابدأ\" للمتابعة في الدردشة مع إليسيا.");
			indicator_startlivechat.setText("ابدأ");
			indicator_close.setText("إنهاء");
			title_settingscreen.setText("الإعدادات");
			subtitle_settingscreen.setText("الإعدادات العامة للتطبيق");
			settitle_language.setText("اللغة");
			lang_selected.setText("العربية");
			
			if (system_model.getText().toString().equals("basic")) preview_model.setText("النموذج الأساسي");
			if (system_model.getText().toString().equals("online")) preview_model.setText("النموذج عبر الإنترنت");
			
			settitle_theme.setText("المظهر");
			subtitle_theme.setText("تخصيص مظهر وأسلوب إليسيا");
			setname_theme.setText("الوضع الداكن");
			subname_theme.setText("تبديل إلى الوضع الداكن");
			setname_account.setText("صورة الحساب");
			subname_account.setText("عرض صورة الحساب عند التفعيل");
			welcome_message.setText("بماذا يمكنني مساعدتك اليوم؟");
			break;
			
			case "tr": // Turkish
			user.edit().putString("lang", "tr").commit();
			language_system.setText("tr");
			input_question.setHint("Elicia'ya sor");
			textseletction_title.setText("Metin Seçimi");
			titlepaused.setText("Sohbet Duraklatıldı");
			subtitlepaused.setText("Mevcut sohbet duraklatıldı.");
			messagepaused.setText("\"Başlat\" düğmesine basarak Elicia ile sohbete devam edin.");
			indicator_startlivechat.setText("Başlat");
			indicator_close.setText("Bitir");
			title_settingscreen.setText("Ayarlar");
			subtitle_settingscreen.setText("Uygulamanın genel ayarları");
			settitle_language.setText("Dil");
			lang_selected.setText("Türkçe");
			
			if (system_model.getText().toString().equals("basic")) preview_model.setText("Temel model");
			if (system_model.getText().toString().equals("online")) preview_model.setText("Çevrimiçi model");
			
			settitle_theme.setText("Görünüm");
			subtitle_theme.setText("Elicia'nın görünümünü ve stilini özelleştir");
			setname_theme.setText("Koyu Tema");
			subname_theme.setText("Koyu moda geçiş yap");
			setname_account.setText("Hesap Resmi");
			subname_account.setText("Etkinleştirildiğinde hesap resmini göster");
			welcome_message.setText("Bugün size nasıl yardımcı olabilirim?");
			break;
		}
		
		// Atur default ke "dark" jika kosong
		if (theme.isEmpty()) {
			theme = "dark";
		}
		
		// Simpan kembali ke apptheme_system
		apptheme_system.setText(theme);
		
		// Gunakan untuk deteksi light/dark
		boolean isLight = theme.equals("light");
		Window window = getWindow();
		
		switch_theme.setChecked(!isLight);
		
		Map<String, String[]> langText = new HashMap<>();
		langText.put("id", new String[]{"Sesuaikan tampilan dan nuansa Elicia Anda", "Penampilan"});
		langText.put("en", new String[]{"Customize your elicia look and feel", "Appearance"});
		langText.put("pt", new String[]{"Personalize a aparência e o estilo do seu elicia", "Aparência"});
		langText.put("ru", new String[]{"Настройте внешний вид и ощущения от Elicia", "Внешний вид"});
		langText.put("ch", new String[]{"自定义你的 Elicia 外观和感觉", "外观"});
		langText.put("ar", new String[]{"قم بتخصيص مظهر وإحساس إليسيا الخاص بك", "المظهر"});
		langText.put("tr", new String[]{"Elicia'nın görünümünü ve hissini özelleştir", "Görünüm"});
		
		String key = (lang != null && langText.containsKey(lang)) ? lang : "en";
		String[] texts = langText.get(key);
		subtitle_theme.setText(texts[0]);
		settitle_theme.setText(texts[1]);
		liverespone_background.setElevation(14f);
		
		int textColor = isLight ? 0xFF000000 : 0xFFFFFFFF;
		int subTextColor = isLight ? 0xFF424242 : 0xFFBDBDBD;
		int welcomeColor = isLight ? 0xFF212121 : 0xFFFFFFFF;
		
		welcome_user.setTextColor(welcomeColor);
		system_pertanyaan.setTextColor(textColor);
		system_jawaban.setTextColor(textColor);
		newconversation_button.setColorFilter(textColor, PorterDuff.Mode.MULTIPLY);
		goback_button_textselectionscreen.setColorFilter(textColor, PorterDuff.Mode.MULTIPLY);
		app_name.setTextColor(textColor);
		preview_model.setTextColor(isLight ? 0xFFFFFFFF : 0xFFBDBDBD);
		username_preview.setTextColor(textColor);
		userinfo_preview.setTextColor(subTextColor);
		textseletction_title.setTextColor(subTextColor);
		indicator_startlivechat.setTextColor(textColor);
		indicator_close.setTextColor(textColor);
		title_settingscreen.setTextColor(textColor);
		subtitle_settingscreen.setTextColor(subTextColor);
		
		textselection_basebackground.setBackground(new GradientDrawable() {{
				setCornerRadius(8);
				setStroke(3, 0xFF373737);
				setColor(isLight ? 0xFFFFFFFF : 0xFF090909);
			}});
		
		int liveRes = isLight ? R.drawable.liveanimation_lightcornor : R.drawable.liveanimation_darkcornor;
		
		liverespone_background.setBackgroundResource(liveRes);
		
		apptheme_system.setText(theme);
		user.edit().putString("theme", theme).apply();
		
		if (flowchat_background.getAdapter() instanceof BaseAdapter) {
			((BaseAdapter) flowchat_background.getAdapter()).notifyDataSetChanged();
		}
		
		switch_theme.getTrackDrawable().setAlpha(0);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			Drawable thumb = switch_theme.getThumbDrawable();
			if (thumb != null) {
				thumb.setColorFilter(isLight ? 0xFFCFD8DC : 0xFFFFFFFF, PorterDuff.Mode.SRC_IN);
				switch_theme.setThumbDrawable(thumb);
			}
		}
		
		switch_theme.setBackground(new GradientDrawable() {{
				setCornerRadius(100);
				setStroke(3, isLight ? 0xFFCFD8DC : 0xFF3F51B5);
				setColor(isLight ? Color.TRANSPARENT : 0xFF3F51B5);
			}});
		
		String imagePath = system_image.getText().toString();
		String newPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
		+ "/EliciaAI/account/user.png";
		File userFile = new File(imagePath);
		
		if (imagePath.equals(newPath) && userFile.exists()) {
			Bitmap userBitmap = FileUtil.decodeSampleBitmapFromPath(imagePath, 1024, 1024);
			accimage_preview.setImageBitmap(userBitmap);
			account_button.setImageBitmap(userBitmap);
		} else if (imagePath.isEmpty() || !userFile.exists()) {
			accimage_preview.setImageResource(R.drawable.ic_account);
			account_button.setImageResource(R.drawable.ic_account);
		}
		
		// Pengecekan model pertama kali
		SharedPreferences eliciaPrefs = getSharedPreferences("EliciaSettings", MODE_PRIVATE);
		SharedPreferences.Editor editor = eliciaPrefs.edit();
		
		// Default model jika belum tersimpan
		if (!eliciaPrefs.contains("model")) {
			editor.putString("model", "basic");
			editor.apply();
		}
		
		String model = eliciaPrefs.getString("model", "basic");
		
		// Label tampilan berdasarkan bahasa
		Map<String, String[]> displayLabels = new HashMap<>();
		displayLabels.put("id", new String[]{"Model Dasar", "Model Daring"});
		displayLabels.put("en", new String[]{"Basic Model", "Online Model"});
		displayLabels.put("pt", new String[]{"Modelo Básico", "Modelo Online"});
		displayLabels.put("ru", new String[]{"Базовая модель", "Онлайн-модель"});
		displayLabels.put("ch", new String[]{"基础模型", "在线模型"});
		displayLabels.put("ar", new String[]{"النموذج الأساسي", "النموذج عبر الإنترنت"});
		displayLabels.put("tr", new String[]{"Temel Model", "Çevrimiçi Model"});
		
		String[] display = displayLabels.containsKey(lang) ? displayLabels.get(lang) : displayLabels.get("en");
		String displayModel = model.equals("basic") ? display[0] : display[1];
		
		system_model.setText(model);
		preview_model.setText(displayModel);
		
		String usernameText = username_preview.getText().toString();
		String fullName = user.getString("name", "");
		String firstName = fullName.split(" ")[0];
		String nameToShow = usernameText.equals("") ? "Elicia" : fullName;
		String infoToShow = usernameText.equals("") ? "" : user.getString("info", "");
		
		// Setup TextWatcher tetap dilakukan
		UiHelper.setupTextWatcher(input_question, clear_button, directquestion_button, micquestion_button, sendquestion_button);
		
		// Default values
		String hint = "Ask Elicia";
		String greeting = "Hi, Iam Elicia!";
		String defaultInfo = "I am your personal assistant";
		String welcomeMsg = "Is there anything I can help\nyou with today?";
		
		// Ganti konten berdasarkan bahasa
		if (lang.equals("id")) {
			hint = "Tanya Elicia";
			greeting = usernameText.equals("") ? "Hai, Saya Elicia!" : "Hai, " + firstName + "!";
			defaultInfo = "Saya adalah asisten pribadi Anda";
			welcomeMsg = "Apakah ada yang dapat\nsaya bantu hari ini?";
		} else if (lang.equals("pt")) {
			hint = "Pergunte à Elicia";
			greeting = usernameText.equals("") ? "Oi, EU Elicia!" : "Oi, " + firstName + "!";
			defaultInfo = "Eu sou seu assistente pessoal";
			welcomeMsg = "Há algo em que eu possa\najudar você hoje?";
		} else if (lang.equals("ru")) {
			hint = "Спросите Элисию";
			greeting = usernameText.equals("") ? "Привет, я Элисия.!" : "Привет, " + firstName + "!";
			defaultInfo = "Я ваш личный помощник";
			welcomeMsg = "Могу ли я чем-то\nпомочь вам сегодня?";
		} else if (lang.equals("ch")) {
			hint = "问 Elicia";
			greeting = usernameText.equals("") ? "你好，我是 Elicia！" : "你好，" + firstName + "！";
			defaultInfo = "我是你的个人助理";
			welcomeMsg = "今天有什么我可以帮忙的吗？";
		} else if (lang.equals("ar")) {
			hint = "اسأل إليسيا";
			greeting = usernameText.equals("") ? "مرحبًا، أنا إليسيا!" : "مرحبًا، " + firstName + "!";
			defaultInfo = "أنا مساعدك الشخصي";
			welcomeMsg = "هل يمكنني مساعدتك اليوم؟";
		} else if (lang.equals("tr")) {
			hint = "Elicia'ya sor";
			greeting = usernameText.equals("") ? "Merhaba, ben Elicia!" : "Merhaba, " + firstName + "!";
			defaultInfo = "Ben sizin kişisel asistanınızım";
			welcomeMsg = "Bugün size nasıl yardımcı olabilirim?";
		} else {
			greeting = usernameText.equals("") ? "Hi, Iam Elicia!" : "Hi, " + firstName + "!";
		}
		
		input_question.setHint(hint);
		welcome_user.setText(greeting);
		username_preview.setText(nameToShow);
		userinfo_preview.setText(usernameText.equals("") ? defaultInfo : infoToShow);
		
		String accType = accpreview_system.getText().toString();
		
		boolean isImage = accType.equals("") || accType.equals("image");
		boolean isText = accType.equals("text");
		
		// Set switch state dan visibilitas
		switch_account.setChecked(isImage);
		accimage_preview.setVisibility(isImage ? View.VISIBLE : View.GONE);
		acctext_background.setVisibility(isImage ? View.GONE : View.VISIBLE);
		account_button.setVisibility(isImage ? View.VISIBLE : View.GONE);
		acctext_button.setVisibility(isImage ? View.GONE : View.VISIBLE);
		
		// Track transparan
		switch_account.getTrackDrawable().setAlpha(0);
		
		// Thumb color
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			Drawable thumbDrawable = switch_account.getThumbDrawable();
			if (thumbDrawable != null) {
				int thumbColor = isText ? 0xFFCFD8DC : 0xFFFFFFFF;
				thumbDrawable.setColorFilter(thumbColor, android.graphics.PorterDuff.Mode.SRC_IN);
				switch_account.setThumbDrawable(thumbDrawable);
			}
		}
		
		// Background gradient
		int strokeColor = isText ? 0xFFCFD8DC : 0xFF3F51B5;
		int fillColor = isText ? Color.TRANSPARENT : 0xFF3F51B5;
		
		GradientDrawable bgDrawable = new GradientDrawable();
		bgDrawable.setCornerRadius(100);
		bgDrawable.setStroke(3, strokeColor);
		bgDrawable.setColor(fillColor);
		
		switch_account.setBackground(bgDrawable);
		
		// Ubah agar Hscroll Dan Vscroll tidak di tampilkan
		flowchat_background.setHorizontalScrollBarEnabled(false); 
		flowchat_background.setVerticalScrollBarEnabled(false);
		input_question.setHorizontalScrollBarEnabled(false);
		input_question.setVerticalScrollBarEnabled(false);  
		setting_scrollbackground.setHorizontalScrollBarEnabled(false); 
		setting_scrollbackground.setVerticalScrollBarEnabled(false); 
		
		// Buat agar Listview tidak memiliki efek ketika di klik
		flowchat_background.setSelector(new ColorDrawable(Color.TRANSPARENT));
		
		// Kode kode UI Tambahan
		r = SketchwareUtil.getRandom((int)(0), (int)(255));
		g = SketchwareUtil.getRandom((int)(0), (int)(255));
		b = SketchwareUtil.getRandom((int)(0), (int)(255));
		avColor(r, g, b);
		
		input_question.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
				}
			}
		});
		
		if (username_preview.getText().toString().equals("")) {
			acctext_preview.setText("Elicia".trim().substring((int)(0), (int)(1)).toUpperCase());
			acctext.setText("Elicia".trim().substring((int)(0), (int)(1)).toUpperCase());
		} else {
			acctext_preview.setText(username_preview.getText().toString().trim().substring((int)(0), (int)(1)).toUpperCase());
			acctext.setText(username_preview.getText().toString().trim().substring((int)(0), (int)(1)).toUpperCase());
		}
		
		// Bagian OnInitListener
		tekskesuara = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				if (status == TextToSpeech.SUCCESS) {
					// Set bahasa Indonesia
					int result = tekskesuara.setLanguage(Locale.getDefault());
					
					if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
						Log.e("TTS", "Bahasa tidak didukung!");
						return; // Keluar jika bahasa tidak didukung
					}
					
					// Set listener untuk progress ucapan
					tekskesuara.setOnUtteranceProgressListener(new UtteranceProgressListener() {
						@Override
						public void onStart(String utteranceId) {
							runOnUiThread(() -> {
								shutup_button.setScaleX(0.80f);
								shutup_button.setScaleY(0.80f);
								shutup_button.setAlpha(0f);
								shutup_button.setVisibility(View.VISIBLE);
								shutup_button.animate()
								.scaleX(1f)
								.scaleY(1f)
								.alpha(1f)
								.setDuration(250)
								.setInterpolator(new DecelerateInterpolator())
								.start();
								
								start_button.setImageResource(R.drawable.ic_pause);
								switch (language_system.getText().toString()) {
									case "id":
									indicator_startlivechat.setText("Jeda");
									break;
									case "en":
									indicator_startlivechat.setText("Pause");
									break;
									case "pt":
									indicator_startlivechat.setText("Pausa");
									break;
									case "ru":
									indicator_startlivechat.setText("Пауза");
									break;
									case "ch":
									indicator_startlivechat.setText("暂停");
									break;
									case "ar":
									indicator_startlivechat.setText("إيقاف مؤقت");
									break;
									case "tr":
									indicator_startlivechat.setText("Duraklat");
									break;
									default:
									indicator_startlivechat.setText("Pause");
									break;
								}
							});
						}
						
						@Override
						public void onDone(String utteranceId) {
							runOnUiThread(() -> {
								shutup_button.animate()
								.scaleX(0.80f)
								.scaleY(0.80f)
								.alpha(0f)
								.setDuration(250)
								.setInterpolator(new DecelerateInterpolator())
								.withEndAction(() -> shutup_button.setVisibility(View.GONE))
								.start();
								
								start_button.setImageResource(R.drawable.ic_model);
								mic_system.setText("0");
								switch (language_system.getText().toString()) {
									case "id":
									indicator_startlivechat.setText("Mulai");
									break;
									case "en":
									indicator_startlivechat.setText("Start");
									break;
									case "pt":
									indicator_startlivechat.setText("Começar");
									break;
									case "ru":
									indicator_startlivechat.setText("Начать");
									break;
									case "ch":
									indicator_startlivechat.setText("开始");
									break;
									case "ar":
									indicator_startlivechat.setText("ابدأ");
									break;
									case "tr":
									indicator_startlivechat.setText("Başla");
									break;
									default:
									indicator_startlivechat.setText("Start");
									break;
								}
							});
						}
						
						@Override
						public void onError(String utteranceId) {
							Log.e("TTS", "Terjadi kesalahan saat berbicara.");
						}
					});
				}
			}
		});
		
		liveresponse_animation.post(new Runnable() {
			@Override
			public void run() {
				// Urutan warna gradasi: dari biru terang ke biru gelap lalu kembali
				final int[] colors = new int[]{
					0xFF8C9EFF,
					0xFF536DFE,
					0xFF3F51B5,
					0xFF303F9F,
					0xFF3F51B5,
					0xFF536DFE,
				};
				
				final Handler handler = new Handler();
				final long colorChangeDuration = 800;
				
				final GradientDrawable gradient = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors);
				int size = Math.min(liveresponse_animation.getWidth(), liveresponse_animation.getHeight());
				gradient.setCornerRadius(size / 2f);
				liveresponse_animation.setBackground(gradient);
				
				handler.postDelayed(new Runnable() {
					int colorIndex = 0;
					long startTime = System.currentTimeMillis();
					
					@Override
					public void run() {
						if (tekskesuara.isSpeaking()) {
							float scale = 221f + (float) Math.sin(System.currentTimeMillis() / 100.0) * 6.5f;
							liveresponse_animation.setScaleX(scale / 221f);
							liveresponse_animation.setScaleY(scale / 221f);
						} else {
							liveresponse_animation.setScaleX(1);
							liveresponse_animation.setScaleY(1);
						}
						
						long elapsedTime = System.currentTimeMillis() - startTime;
						if (elapsedTime >= colorChangeDuration) {
							startTime = System.currentTimeMillis();
							colorIndex = (colorIndex + 1) % colors.length;
						}
						
						float ratio = (float) (elapsedTime % colorChangeDuration) / colorChangeDuration;
						int nextColor = colors[(colorIndex + 1) % colors.length];
						int blendedColor = blendColors(colors[colorIndex], nextColor, ratio);
						gradient.setColor(blendedColor);
						liveresponse_animation.setBackground(gradient);
						
						handler.postDelayed(this, 16);
					}
				}, 16);
			}
			
			private int blendColors(int color1, int color2, float ratio) {
				int alpha1 = (color1 >> 24) & 0xff;
				int red1 = (color1 >> 16) & 0xff;
				int green1 = (color1 >> 8) & 0xff;
				int blue1 = color1 & 0xff;
				
				int alpha2 = (color2 >> 24) & 0xff;
				int red2 = (color2 >> 16) & 0xff;
				int green2 = (color2 >> 8) & 0xff;
				int blue2 = color2 & 0xff;
				
				int alpha = (int) (alpha1 + ratio * (alpha2 - alpha1));
				int red = (int) (red1 + ratio * (red2 - red1));
				int green = (int) (green1 + ratio * (green2 - green1));
				int blue = (int) (blue1 + ratio * (blue2 - blue1));
				
				return (alpha << 24) | (red << 16) | (green << 8) | blue;
			}
		});
		
		// Kode supaya textview bisa dipilih
		system_jawaban.setTextIsSelectable(true);
		userinfo_preview.setTextIsSelectable(true);
		
		// Inisialisasi SharedPreferences
		SharedPreferences user = getSharedPreferences("user_prefs", MODE_PRIVATE);
		// Kode latar accounttext
		GradientDrawable preview = new GradientDrawable();
		preview.setColor(Color.parseColor(colorPicked));
		preview.setCornerRadius(372);
		acctext_background.setBackground(preview);
		
		GradientDrawable button = new GradientDrawable();
		button.setColor(Color.parseColor(colorPicked));
		button.setCornerRadius(72);
		acctext_button.setBackground(button);
		// Cek apakah eliciascreen atau accountscreen terlihat
		SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
		boolean isAccountScreenVisible = prefs.getBoolean("isAccountScreenVisible", false);
		
		if (isAccountScreenVisible) {
			getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		} else {
			getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		}
		
		// Muat ChatHistory
		ChatHistory.loadChat(this, question, answer);
		
		// Listener scroll down
		flowchat_background.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
		flowchat_background.setStackFromBottom(true); // Untuk mulai dari bawah
		
		scrolldown_button.setOnClickListener(v -> {
			flowchat_background.smoothScrollToPosition(flowchat_background.getCount() - 1);
		});
		
		flowchat_background.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				if (totalItemCount == 0) return;
				
				int lastVisibleItem = firstVisibleItem + visibleItemCount;
				boolean isAtBottom = (lastVisibleItem >= totalItemCount);
				
				if (flowchat_background.getVisibility() == View.VISIBLE) {
					if (isAtBottom) {
						scrolldown_button.setScaleX(1f);
						scrolldown_button.setScaleY(1f);
						scrolldown_button.setAlpha(0f);
						scrolldown_button.setVisibility(View.GONE);
						scrolldown_button.animate()
						.scaleX(0.80f)
						.scaleY(0.80f)
						.alpha(0f)
						.setDuration(250)
						.setInterpolator(new DecelerateInterpolator())
						.start();
					} else {
						scrolldown_button.setScaleX(0.80f);
						scrolldown_button.setScaleY(0.80f);
						scrolldown_button.setAlpha(0f);
						scrolldown_button.setVisibility(View.VISIBLE);
						scrolldown_button.animate()
						.scaleX(1f)
						.scaleY(1f)
						.alpha(1f)
						.setDuration(250)
						.setInterpolator(new DecelerateInterpolator())
						.start();
					}
				}
			}
		});
		
		GradientRingView ringView = new GradientRingView(this);
		inoring.addView(ringView);
		flowchat_background.setAdapter(new Flowchat_backgroundAdapter(answer));
		((EditText)input_question).setMaxLines((int)6);
		((BaseAdapter)flowchat_background.getAdapter()).notifyDataSetChanged();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		if (_requestCode == 2167) {
			copyModel();
			splashAnim();
		}
		switch (_requestCode) {
			case REQ_CD_PROFILE:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				String selectedImagePath = _filePath.get(0);
				Bitmap originalBitmap = BitmapFactory.decodeFile(selectedImagePath);
				Bitmap circularBitmap = ImageHelper.cropAndCircleBitmap(originalBitmap, 512, 512);
				accimage_preview.setImageBitmap(circularBitmap);
				
				String message;
				switch (lang) {
					case "id": message = "Gambar dipilih"; break;
					case "en": message = "Picture selected"; break;
					case "pt": message = "Imagem selecionada"; break;
					case "ru": message = "Изображение выбрано"; break;
					case "ch": message = "选择的图片"; break;
					case "ar": message = "تم اختيار الصورة"; break;
					case "tr": message = "Resim seçildi"; break;
					default: message = "Picture selected"; break;
				}
				showCustomToast(getApplicationContext(), message, false, "#50212121", 21, "#FFFFFF", 15, 1);
				
				File publicDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "EliciaAI/account");
				if (!publicDir.exists()) {
					publicDir.mkdirs();
				}
				File userImageFile = new File(publicDir, "user.png");
				system_image.setText(userImageFile.getAbsolutePath());
				
				if (system_image.getText().toString().isEmpty()) {
					user.edit().putString("img", "").commit();
				} else {
					user.edit().putString("img", userImageFile.getAbsolutePath()).apply();
					Bitmap bitmapToSave = ((BitmapDrawable) accimage_preview.getDrawable()).getBitmap();
					try {
						FileOutputStream outputStream = new FileOutputStream(userImageFile);
						bitmapToSave.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
						outputStream.flush();
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			else {
				String message;
				
				// Tentukan teks berdasarkan bahasa
				switch (lang) {
					case "id":
					message = "Batal mengambil gambar";
					break;
					case "en":
					message = "Cancelled taking picture";
					break;
					case "pt":
					message = "Cancelado tirar foto";
					break;
					case "ru":
					message = "Отмена захвата изображения";
					break;
					case "ch":
					message = "取消拍照";
					break;
					case "ar":
					message = "تم إلغاء التقاط الصورة";
					break;
					case "tr":
					message = "Fotoğraf çekme iptal edildi";
					break;
					default:
					message = "Cancelled taking picture";
					break;
				}
				
				// Tampilkan toast
				showCustomToast(getApplicationContext(), message, false,  "#50212121" , 21 , "#FFFFFF", 15, 1);
				
				// Set ulang teks jika perlu
				system_image.setText(system_image.getText().toString());
			}
			break;
			default:
			break;
		}
	}
	
	
	@Override
	public void onBackPressed() {
		if (elicia_screen.getVisibility() == View.GONE && (account_screen.getVisibility() == View.VISIBLE || (textselection_screen.getVisibility() == View.VISIBLE || livechat_screen.getVisibility() == View.VISIBLE))) {
			if (account_screen.getVisibility() == View.VISIBLE) {
				// System kembali account_screen
				
				File file = new File(Environment.getExternalStorageDirectory(), "Download/EliciaAI/account/user.png");
				
				if (system_image.getText().toString().equals(file.getAbsolutePath())) {
					accimage_preview.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(file.getAbsolutePath(), 1024, 1024));
					account_button.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(file.getAbsolutePath(), 1024, 1024));
				}
				if (system_image.getText().toString().equals("")) {
					accimage_preview.setImageResource(R.drawable.ic_account);
					account_button.setImageResource(R.drawable.ic_account);
				}
				
				if (username_preview.getText().toString().equals(user.getString("name", "")) || userinfo_preview.getText().toString().equals(user.getString("info", ""))) {
					username_preview.setText(username_preview.getText().toString());
					userinfo_preview.setText(userinfo_preview.getText().toString());
				}
				
				// Animasi untuk setting_background
				AnimatorSet settingBackgroundAnimator = new AnimatorSet();
				ObjectAnimator slideOut1 = ObjectAnimator.ofFloat(setting_background, "translationY", 0f, 500f);
				slideOut1.setDuration(300);
				
				ObjectAnimator fadeOut1 = ObjectAnimator.ofFloat(setting_background, "alpha", 1f, 0f);
				fadeOut1.setDuration(300);
				
				settingBackgroundAnimator.playTogether(slideOut1, fadeOut1);
				settingBackgroundAnimator.addListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						setting_background.setVisibility(View.GONE);
					}
				});
				settingBackgroundAnimator.start();
				
				// Animasi untuk account_screen
				AnimatorSet accountScreenAnimator = new AnimatorSet();
				ObjectAnimator slideOut2 = ObjectAnimator.ofFloat(account_screen, "translationY", 0f, 500f);
				slideOut2.setDuration(300);
				
				ObjectAnimator fadeOut2 = ObjectAnimator.ofFloat(account_screen, "alpha", 1f, 0f);
				fadeOut2.setDuration(300);
				
				accountScreenAnimator.playTogether(slideOut2, fadeOut2);
				accountScreenAnimator.addListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						account_screen.setVisibility(View.GONE);
						elicia_screen.setVisibility(View.VISIBLE);
						account_screen.setTranslationY((float)(0));
						input_question.clearFocus();
					}
				});
				
				accountScreenAnimator.start();
				
			}
			if (textselection_screen.getVisibility() == View.VISIBLE) {
				// System kembali textselection_screen
				
				elicia_screen.setScaleX(0.97f);
				elicia_screen.setScaleY(0.97f);
				elicia_screen.setAlpha(0f);
				elicia_screen.setVisibility(View.VISIBLE);
				textselection_screen.setVisibility(View.GONE);
				account_screen.setVisibility(View.GONE);
				
				elicia_screen.animate()
				.scaleX(1f)
				.scaleY(1f)
				.alpha(1f)
				.setDuration(250)
				.setInterpolator(new DecelerateInterpolator())
				.start();
				
				input_question.clearFocus();
			}
			if (livechat_screen.getVisibility() == View.VISIBLE) {
				hideScreenWithAnimation(livechat_screen, "Y");
			}
			
		} else {
			String lang = language_system.getText().toString();
			String titleText = "Exit Application";
			String msgText = "Are you sure you want to exit the app now? Any ongoing process will be stopped.";
			String cancelText = "Cancel";
			String closeText = "Close";
			
			switch (lang) {
				case "id":
				titleText = "Keluar Aplikasi";
				msgText = "Apakah Anda yakin ingin keluar dari aplikasi sekarang? Semua proses yang sedang berjalan akan dihentikan.";
				cancelText = "Batal";
				closeText = "Tutup";
				break;
				case "en":
				titleText = "Exit App";
				msgText = "Are you sure you want to exit the app now? All running processes will be stopped.";
				cancelText = "Cancel";
				closeText = "Close";
				break;
				case "pt":
				titleText = "Sair do Aplicativo";
				msgText = "Tem certeza de que deseja sair do aplicativo agora? Todos os processos em andamento serão interrompidos.";
				cancelText = "Cancelar";
				closeText = "Fechar";
				break;
				case "ru":
				titleText = "Выход из приложения";
				msgText = "Вы уверены, что хотите выйти из приложения сейчас? Все выполняемые процессы будут остановлены.";
				cancelText = "Отмена";
				closeText = "Закрыть";
				break;
				case "ch":
				titleText = "退出应用程序";
				msgText = "您确定现在要退出应用程序吗？所有正在进行的操作将被终止。";
				cancelText = "取消";
				closeText = "关闭";
				break;
				case "ar":
				titleText = "الخروج من التطبيق";
				msgText = "هل أنت متأكد أنك تريد الخروج من التطبيق الآن؟ سيتم إيقاف جميع العمليات الجارية.";
				cancelText = "إلغاء";
				closeText = "إغلاق";
				break;
				case "tr":
				titleText = "Uygulamadan Çık";
				msgText = "Uygulamadan şimdi çıkmak istediğinizden emin misiniz? Tüm çalışan işlemler durdurulacaktır.";
				cancelText = "İptal";
				closeText = "Kapat";
				break;
				default:
				titleText = "Exit App";
				msgText = "Are you sure you want to exit the app now? All running processes will be stopped.";
				cancelText = "Cancel";
				closeText = "Close";
				break;
			}
			
			if (flowchat_background.getVisibility() == View.VISIBLE) {
				input_question.clearFocus();
				flowchat_background.setVisibility(View.GONE);
				welcome_background.setScaleX(0.8f);
				welcome_background.setScaleY(0.8f);
				welcome_background.setAlpha(0f);
				welcome_background.setVisibility(View.VISIBLE);
				welcome_background.animate()
				.scaleX(1f)
				.scaleY(1f)
				.alpha(1f)
				.setDuration(250)
				.setInterpolator(new DecelerateInterpolator())
				.start();
				flowchat_background.smoothScrollToPosition(flowchat_background.getCount() - 1);
				scrolldown_button.setScaleX(1f);
				scrolldown_button.setScaleY(1f);
				scrolldown_button.setAlpha(0f);
				scrolldown_button.setVisibility(View.GONE);
				scrolldown_button.animate()
				.scaleX(0.80f)
				.scaleY(0.80f)
				.alpha(0f)
				.setDuration(250)
				.setInterpolator(new DecelerateInterpolator())
				.start();
			} else {
				Dialog dialog = new Dialog(MainActivity.this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setCancelable(true);
				
				float dp = getResources().getDisplayMetrics().density;
				
				LinearLayout root = new LinearLayout(MainActivity.this);
				root.setOrientation(LinearLayout.VERTICAL);
				root.setPadding((int)(23 * dp), (int)(20 * dp), (int)(23 * dp), (int)(16 * dp));
				root.setBackground(new GradientDrawable() {{
						setColor(Color.parseColor("#1C1C1E"));
						setCornerRadius(28 * dp);
					}});
				
				TextView title = new TextView(MainActivity.this);
				title.setText(titleText);
				title.setTextSize(20);
				title.setTextColor(Color.parseColor("#FFFFFF"));
				title.setPadding(0, 0, 0, (int)(14 * dp));
				root.addView(title);
				
				TextView msg = new TextView(MainActivity.this);
				msg.setText(msgText);
				msg.setTextSize(14);
				msg.setTextColor(Color.parseColor("#B0B0B0"));
				msg.setPadding(0, 0, 0, (int)(9 * dp));
				root.addView(msg);
				
				LinearLayout row = new LinearLayout(MainActivity.this);
				row.setOrientation(LinearLayout.HORIZONTAL);
				row.setGravity(Gravity.END);
				
				TextView btnCancel = new TextView(MainActivity.this);
				btnCancel.setText(cancelText);
				btnCancel.setTextSize(14);
				btnCancel.setTypeface(null, Typeface.BOLD);
				btnCancel.setTextColor(Color.parseColor("#3F51B5"));
				btnCancel.setPadding((int)(16 * dp), (int)(12 * dp), (int)(24 * dp), (int)(12 * dp));
				btnCancel.setOnClickListener(v -> {
					dialog.dismiss();
				});
				row.addView(btnCancel);
				
				TextView btnOk = new TextView(MainActivity.this);
				btnOk.setText(closeText);
				btnOk.setTextSize(14);
				btnOk.setTypeface(null, Typeface.BOLD);
				btnOk.setTextColor(Color.parseColor("#3F51B5"));
				btnOk.setPadding((int)(16 * dp), (int)(12 * dp), (int)(18 * dp), (int)(12 * dp));
				btnOk.setOnClickListener(v -> {
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
						finishAndRemoveTask();
					} else {
						finish();
					}
					dialog.dismiss();
				});
				row.addView(btnOk);
				root.addView(row);
				
				dialog.setContentView(root);
				Window w = dialog.getWindow();
				if (w != null) {
					int width = (int)(getResources().getDisplayMetrics().widthPixels * 0.85f);
					w.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
					w.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
					w.setGravity(Gravity.CENTER);
				}
				dialog.show();
			}
			
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		// Cek apakah eliciascreen atau accountscreen terlihat
		SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
		boolean isAccountScreenVisible = prefs.getBoolean("isAccountScreenVisible", false);
		
		if (isAccountScreenVisible) {
			getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
		} else {
			getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		}
		
		if (apptheme_system.getText().toString().equals("light")) {
			getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
		} else {
			if (apptheme_system.getText().toString().equals("dark")) {
				getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#090909")));
			}
		}
	}
	
	@Override
	protected void onPostCreate(Bundle _savedInstanceState) {
		super.onPostCreate(_savedInstanceState);
		// Hilangkan underline EditText
		input_question.setBackground(null);
		
		// Tema ProgressBar
		ColorStateList whiteTint = ColorStateList.valueOf(Color.WHITE);
		progress_livechat.setIndeterminateTintList(whiteTint);
		progress.setIndeterminateTintList(whiteTint);
		
		// Background UI (dengan radius seragam)
		progress_livechat.setBackground(makeDrawable(360, 0xFF363637));
		start_button.setBackground(makeDrawable(360, 0xFF363637));
		livepaused_background.setBackground(makeDrawable(50, 0x80363637));
		directquestion_button.setBackground(makeDrawable(360, 0xFF363637));
		language_setting_background.setBackground(makeDrawable(40, 0xFF363637));
		theme_setting_background.setBackground(makeDrawable(40, 0xFF363637));
		language_setting_background.setElevation(8f);
		theme_setting_background.setElevation(8f);
		progress_background.setBackground(makeDrawable(360, 0xFF363637));
		shutup_button.setBackground(makeDrawable(360, 0xFF363637));
		sendquestion_button.setBackground(makeDrawable(360, 0xFF363637));
		scrolldown_button.setBackground(makeDrawable(21, 0xFF212121));
		input_background.setBackground(makeDrawable(60, 0xFF212121));
		previewmodel_background.setBackground(makeDrawable(21, 0x85424242));
	}
	
	@Override
	public void onPause() {
		super.onPause();
		// Simpan keadaan layar
		SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean("isAccountScreenVisible", account_screen.getVisibility() == View.VISIBLE);
		editor.apply();
		
	}
	public void _EliciaAI() {
		// Thank you for using my project as learning
	}
	
	private String jam1, hari1, nows, jamhari, harijam, jamjam,
	kemarin, isday, tomisDay, yesisDay, besok, nowday, proses,
	toastMessage, timeNow, oclock, past, secondWord, minuteWord,
	nowisDay, sorryMessage;
	
	private String lang;
	private String[] additionalResponses, calculationMessages, bingungAnswers;
	
	public void avColor(final double r, final double g, final double b) {
		colorPicked = "#".concat(String.format("%02X%02X%02X", new Object[]{
			Integer.valueOf((int)r), Integer.valueOf((int)g), Integer.valueOf((int)b)
		}));
	}
	
	private void showSimpleListDialog(String titleText, String msgText, List<String> items, AdapterView.OnItemClickListener onItemClick, boolean withReset, Runnable resetAction) {
		Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setCancelable(true);
		
		float dp = getResources().getDisplayMetrics().density;
		
		LinearLayout root = new LinearLayout(this);
		root.setOrientation(LinearLayout.VERTICAL);
		root.setPadding((int)(20 * dp), (int)(20 * dp), (int)(20 * dp), (int)(16 * dp));
		root.setBackground(new GradientDrawable() {{
				setColor(Color.parseColor("#1C1C1E"));
				setCornerRadius(28 * dp);
			}});
		
		// Judul
		TextView title = new TextView(this);
		title.setText(titleText);
		title.setTextSize(20);
		title.setTextColor(Color.WHITE);
		title.setPadding(0, 0, 0, (int)(8 * dp));
		root.addView(title);
		
		TextView msg = new TextView(this);
		msg.setText(msgText);
		msg.setTextSize(14);
		msg.setTextColor(Color.parseColor("#B0B0B0"));
		msg.setPadding(0, 0, 0, (int)(24 * dp));
		root.addView(msg);
		
		// ListView
		final int maxHeight = (int)(200 * dp);
		ListView listView = new ListView(this) {
			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
				int maxHeightSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
				super.onMeasure(widthMeasureSpec, maxHeightSpec);
			}
		};
		listView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		listView.setDivider(new ColorDrawable(Color.TRANSPARENT));
		listView.setDividerHeight((int)(6 * dp));
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_1, items) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView tv = (TextView) super.getView(position, convertView, parent);
				tv.setTextColor(Color.WHITE);
				tv.setPadding((int)(13 * dp), (int)(13 * dp), (int)(13 * dp), (int)(13 * dp));
				tv.setTextSize(15);
				final float radius = 16 * dp;
				GradientDrawable bg = new GradientDrawable();
				bg.setColor(Color.TRANSPARENT);
				bg.setCornerRadius(radius);
				bg.setStroke((int)(1 * dp), Color.WHITE);
				tv.setBackground(bg);
				return tv;
			}
		};
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener((p, v, pos, id) -> {
			onItemClick.onItemClick(p, v, pos, id);
			dialog.dismiss();
		});
		root.addView(listView);
		
		// Baris tombol
		LinearLayout buttonRow = new LinearLayout(this);
		buttonRow.setOrientation(LinearLayout.HORIZONTAL);
		buttonRow.setGravity(Gravity.END);
		buttonRow.setPadding(0, (int)(12 * dp), 0, 0);
		
		if (withReset) {
			TextView btnReset = new TextView(this);
			btnReset.setText("Reset");
			btnReset.setTextColor(Color.WHITE);
			btnReset.setGravity(Gravity.CENTER);
			btnReset.setTextSize(16);
			btnReset.setPadding(32,16,32,16);
			btnReset.setOnClickListener(v -> {
				if (resetAction != null) resetAction.run();
				dialog.dismiss();
			});
			buttonRow.addView(btnReset);
		}
		
		TextView btnClose = new TextView(this);
		btnClose.setText("Close");
		btnClose.setTextColor(Color.WHITE);
		btnClose.setGravity(Gravity.CENTER);
		btnClose.setTextSize(16);
		btnClose.setPadding(32,16,32,16);
		btnClose.setOnClickListener(v -> dialog.dismiss());
		buttonRow.addView(btnClose);
		
		root.addView(buttonRow);
		
		dialog.setContentView(root);
		Window w = dialog.getWindow();
		if (w != null) {
			int width = (int)(getResources().getDisplayMetrics().widthPixels * 0.9f);
			w.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
			w.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			w.setGravity(Gravity.CENTER);
		}
		dialog.show();
	}
	
	private void setCircularImage(ImageView imageView) {
		imageView.post(() -> {
			BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
			if (drawable == null) return;
			Bitmap bitmap = drawable.getBitmap();
			if (bitmap == null) return;
			
			int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
			Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(output);
			
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setShader(new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
			
			float radius = size / 2f;
			canvas.drawCircle(radius, radius, radius, paint);
			
			imageView.setImageBitmap(output);
		});
	}
	
	static class GradientRingView extends View {
		private Paint paint;
		private int[] colors = {0xFF00BCD4, 0xFF3F51B5, 0xFF00BCD4};
		private final int[] startColors = {0xFF00BCD4, 0xFF3F51B5, 0xFF00BCD4};
		private final int[] endColors   = {0xFF4CAF50, 0xFFFF5722, 0xFF4CAF50};
		private final ArgbEvaluator evaluator = new ArgbEvaluator();
		
		public GradientRingView(Context context) {
			super(context);
			
			paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(40f);
			ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
			animator.setDuration(2000);
			animator.setRepeatCount(ValueAnimator.INFINITE);
			animator.setRepeatMode(ValueAnimator.REVERSE);
			
			animator.addUpdateListener(animation -> {
				float fraction = (float) animation.getAnimatedValue();
				for (int i = 0; i < colors.length; i++) {
					colors[i] = (int) evaluator.evaluate(fraction, startColors[i], endColors[i]);
				}
				invalidate();
			});
			
			animator.start();
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			float radius = Math.min(getWidth(), getHeight()) / 2f - paint.getStrokeWidth() / 2f;
			Shader shader = new SweepGradient(getWidth() / 2f, getHeight() / 2f, colors, null);
			paint.setShader(shader);
			canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, radius, paint);
		}
	}
	
	public void showAccountScreen() {
		if (apptheme_system.getText().toString().equals("light")) {
			getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
		} else {
			if (apptheme_system.getText().toString().equals("dark")) {
				getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#090909")));
			}
		}
		
		UiHelper.hideKeyboard(MainActivity.this);
		
		// Reset posisi dan visibilitas
		account_screen.setScaleX(0.97f);
		account_screen.setScaleY(0.97f);
		account_screen.setAlpha(0f);
		account_screen.setVisibility(View.VISIBLE);
		elicia_screen.setVisibility(View.GONE);
		
		// Animasi popup: scale + fade in
		account_screen.animate()
		.scaleX(1f)
		.scaleY(1f)
		.alpha(1f)
		.setDuration(250)
		.setInterpolator(new DecelerateInterpolator())
		.start();
		
		AnimatorSet animatorSetin = new AnimatorSet();
		ObjectAnimator slideIn = ObjectAnimator.ofFloat(setting_background, "translationY", 500f, 0f);
		slideIn.setDuration(300);
		
		ObjectAnimator fadeIn = ObjectAnimator.ofFloat(setting_background, "alpha", 0f, 1f);
		fadeIn.setDuration(300);
		
		animatorSetin.playTogether(slideIn, fadeIn);
		animatorSetin.start();
		
		setting_background.setVisibility(View.VISIBLE);
	}
	
	public void hideScreenWithAnimation(View screenToHide, String direction) {
		AnimatorSet animatorSet = new AnimatorSet();
		
		ObjectAnimator slideOut;
		if (direction.equals("Y")) {
			slideOut = ObjectAnimator.ofFloat(screenToHide, "translationY", 0f, 500f);
		} else {
			slideOut = ObjectAnimator.ofFloat(screenToHide, "translationX", 0f, -500f);
		}
		slideOut.setDuration(300);
		
		ObjectAnimator fadeOut = ObjectAnimator.ofFloat(screenToHide, "alpha", 1f, 0f);
		fadeOut.setDuration(300);
		
		animatorSet.playTogether(slideOut, fadeOut);
		animatorSet.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				screenToHide.setVisibility(View.GONE);
				elicia_screen.setVisibility(View.VISIBLE);
				input_question.clearFocus();
			}
		});
		
		animatorSet.start();
	}
	
	public GradientDrawable makeDrawable(float radius, int color) {
		GradientDrawable drawable = new GradientDrawable();
		drawable.setCornerRadius(radius);
		drawable.setColor(color);
		return drawable;
	}
	
	public void splashAnim() {
		String theme = apptheme_system.getText().toString().trim();
		apptheme_system.setText(theme);
		boolean isLight = theme.equals("light");
		
		int darkColor = Color.parseColor("#090909");
		getWindow().setStatusBarColor(darkColor);
		getWindow().setNavigationBarColor(darkColor);
		getWindow().setBackgroundDrawable(new ColorDrawable(darkColor));
		splashscreen.setBackgroundColor(darkColor);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			getWindow().getDecorView().setSystemUiVisibility(0); // UI gelap
		}
		
		new Handler().postDelayed(() -> {
			if (isLight) {
				ValueAnimator colorAnim = ValueAnimator.ofArgb(darkColor, Color.WHITE);
				colorAnim.setDuration(400);
				colorAnim.setInterpolator(new DecelerateInterpolator());
				colorAnim.addUpdateListener(anim -> {
					int color = (int) anim.getAnimatedValue();
					getWindow().setStatusBarColor(color);
					getWindow().setNavigationBarColor(color);
					getWindow().setBackgroundDrawable(new ColorDrawable(color));
					splashscreen.setBackgroundColor(color);
				});
				colorAnim.start();
				
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
					getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
					);
				}
			}
			
			splashscreen.animate()
			.alpha(0f)
			.setDuration(350)
			.setInterpolator(new DecelerateInterpolator())
			.withEndAction(() -> splashscreen.setVisibility(View.GONE))
			.start();
			
		}, 500);
	}
	
	public void languageSet() {
		String lang = language_system.getText().toString();
		if (lang == null || lang.isEmpty()) {
			lang = "en";
		}
		
		switch (lang) {
			case "id":
			jam1 = "(?i).*\\bjam\\b.*";
			hari1 = "(?i).*\\bhari\\b.*";
			nows = "Sekarang ";
			jamhari = "(?i).*((jam|pukul).*dan.*hari).*";
			harijam = "(?i).*(hari.*dan.*(jam|pukul)).*";
			jamjam = "(?i).*(pukul berapa|jam berapa|jam|sekarang jam).*";
			kemarin = "(?i).*\\b(hari )?kemarin\\b.*";
			isday = "Hari";
			tomisDay = "Besok adalah hari ";
			yesisDay = "Kemarin adalah hari ";
			besok = "(?i).*\\b(hari )?besok\\b.*";
			nowday = "Sekarang hari ";
			proses = "Memproses...";
			toastMessage = "Pertanyaan tidak boleh kosong.";
			timeNow = "Sekarang pukul ";
			oclock = "Pukul ";
			past = " lewat ";
			secondWord = " detik";
			minuteWord = " menit";
			nowisDay = "Hari ini adalah hari ";
			sorryMessage = "Maaf, saya tidak mengerti pertanyaan Anda.";
			additionalResponses = new String[]{
				"Ada hal lain yang bisa saya bantu?",
				"Silakan tanyakan sesuatu yang lain, saya siap membantu.",
				"Jika ada pertanyaan lain, jangan ragu untuk bertanya!",
				"Mungkin ada hal lain yang ingin Anda ketahui?",
				"Masih ada yang ingin Anda tanyakan?"
			};
			calculationMessages = new String[]{
				"Hasil perhitungan: %s\nPerhitungan ini mengikuti aturan *BODMAS*.\nJika Anda punya pertanyaan lain, jangan sungkan untuk bertanya ya?",
				"Hasilnya adalah: %s\nSaya menggunakan aturan *BODMAS* agar hasil lebih tepat.\nPerlu bantuan lainnya?",
				"Inilah hasilnya: %s\nDengan aturan *BODMAS*, perhitungan jadi akurat.\nAda yang ingin ditanyakan lagi?",
				"Jawabannya adalah: %s\nSaya mengikuti urutan operasi matematika.\nMau tanya hal lain juga boleh kok~"
			};
			bingungAnswers = new String[]{
				"Maaf, saya hanya bisa memproses angka.",
				"Saya tidak bisa menghitungnya. Harap masukkan angka yang valid.",
				"Bisa diperjelas? Saya tidak mengerti maksud Anda.",
				"Saya tidak dapat menghitungnya. Silakan coba lagi."
			};
			break;
			
			case "en":
			jam1 = "(?i).*\\boclock\\b.*";
			hari1 = "(?i).*\\bday\\b.*";
			nows = "Now ";
			jamhari = "(?i).*((time|hour|o'clock).*and.*day).*";
			harijam = "(?i).*(day.*and.*(time|o'clock|hour)).*";
			jamjam = "(?i).*(what time|what's the time|now at|o'clock).*";
			kemarin = "(?i).*\\b(day )?yesterday\\b.*";
			isday = "Day";
			tomisDay = "Tomorrow is ";
			yesisDay = "Yesterday was ";
			besok = "(?i).*\\b(day )?tomorrow\\b.*";
			nowday = "Today is ";
			proses = "Processing...";
			toastMessage = "The question cannot be empty.";
			timeNow = "The current time is ";
			oclock = "O'clock ";
			past = " past ";
			secondWord = " second";
			minuteWord = " minute";
			nowisDay = "Today is ";
			sorryMessage = "Sorry, I do not understand your question.";
			additionalResponses = new String[]{
				"Is there anything else I can help with?",
				"Feel free to ask something else. I'm here to help.",
				"Have more questions? Just ask!",
				"Is there anything else you'd like to know?",
				"Do you have any other questions?"
			};
			calculationMessages = new String[]{
				"Calculation result: %s\nThis calculation follows the *BODMAS* rule.\nFeel free to ask if you have another question!",
				"The result is: %s\nI used the *BODMAS* rule for better accuracy.\nNeed help with something else?",
				"Here is the answer: %s\nUsing *BODMAS* ensures the result is precise.\nWould you like to ask something else?",
				"Your answer is: %s\nMath operations were ordered by *BODMAS*.\nLet me know if there's more I can help with!"
			};
			bingungAnswers = new String[]{
				"Sorry, I can only process numbers.",
				"I can't calculate that. Please enter a valid number.",
				"Could you clarify? I can't understand your question.",
				"I can't compute that. Please try again."
			};
			break;
			
			case "pt":
			jam1 = "(?i).*\\bhoras\\b.*";
			hari1 = "(?i).*\\bdia\\b.*";
			nows = "Agora ";
			jamhari = "(?i).*((hora|horas).*e.*dia).*";
			harijam = "(?i).*(dia.*e.*(hora|horas)).*";
			jamjam = "(?i).*(que horas são|agora são|horas).*";
			kemarin = "(?i).*\\b(ontem)\\b.*";
			isday = "Dia";
			tomisDay = "Amanhã é ";
			yesisDay = "Ontem foi ";
			besok = "(?i).*\\b(amanhã)\\b.*";
			nowday = "Hoje é ";
			proses = "Processando...";
			toastMessage = "A pergunta não pode estar vazia.";
			timeNow = "Agora são ";
			oclock = "Horas ";
			past = " e ";
			secondWord = " segundo";
			minuteWord = " minuto";
			nowisDay = "Hoje é ";
			sorryMessage = "Desculpe, não entendi sua pergunta.";
			additionalResponses = new String[]{
				"Há algo mais que posso ajudar?",
				"Pode perguntar outra coisa, estou pronto para ajudar.",
				"Se tiver outras dúvidas, não hesite em perguntar!",
				"Talvez haja algo mais que queira saber?",
				"Tem mais alguma pergunta?"
			};
			calculationMessages = new String[]{
				"Resultado do cálculo: %s\nEste cálculo segue a regra *BODMAS*.\nSe tiver outra dúvida, é só perguntar!",
				"O resultado é: %s\nUsei a regra *BODMAS* para maior precisão.\nPosso te ajudar com mais alguma coisa?",
				"Aqui está o resultado: %s\nCom *BODMAS*, o cálculo fica mais confiável.\nGostaria de saber algo mais?",
				"A resposta é: %s\nSegui a ordem correta das operações.\nFique à vontade para perguntar outra coisa!"
			};
			bingungAnswers = new String[]{
				"Desculpe, só posso processar números.",
				"Não consigo calcular isso. Por favor, insira um número válido.",
				"Poderia esclarecer? Não entendi sua pergunta.",
				"Não consigo calcular isso. Tente novamente."
			};
			break;
			
			case "ru":
			jam1 = "(?i).*\\bвремя\\b.*";
			hari1 = "(?i).*\\bдень\\b.*";
			nows = "Сейчас ";
			jamhari = "(?i).*((время.*и.*день)|(день.*и.*время)).*";
			harijam = "(?i).*((день.*и.*время)|(время.*и.*день)).*";
			jamjam = "(?i).*(сколько времени|который час|время|сейчас время).*";
			kemarin = "(?i).*\\b(вчера)\\b.*";
			isday = "День";
			tomisDay = "Завтра будет ";
			yesisDay = "Вчера был ";
			besok = "(?i).*\\b(завтра)\\b.*";
			nowday = "Сегодня ";
			proses = "Обработка...";
			toastMessage = "Вопрос не может быть пустым.";
			timeNow = "Сейчас время ";
			oclock = "Время ";
			past = " спустя ";
			secondWord = " секунд";
			minuteWord = " минут";
			nowisDay = "Сегодня ";
			sorryMessage = "Извините, я не понимаю ваш вопрос.";
			additionalResponses = new String[]{
				"Чем ещё могу помочь?",
				"Задайте другой вопрос, я помогу.",
				"Если есть ещё вопросы — не стесняйтесь!",
				"Возможно, вы хотите узнать что-то ещё?",
				"У вас есть ещё вопросы?"
			};
			calculationMessages = new String[]{
				"Результат вычисления: %s\nИспользовано правило *BODMAS* для точности.\nЕсли есть ещё вопросы — не стесняйтесь!",
				"Ответ: %s\nЯ использовал *BODMAS* для точного результата.\nМогу помочь с чем-то ещё?",
				"Вот ваш результат: %s\nСледуя правилам *BODMAS*, всё точно.\nХотите узнать что-то ещё?",
				"Рассчитано: %s\nВсё по математическим правилам.\nЗадайте следующий вопрос, если хотите!"
			};
			bingungAnswers = new String[]{
				"Извините, я понимаю только числа.",
				"Я не могу это вычислить. Пожалуйста, введите корректные данные.",
				"Не могли бы вы уточнить? Я не понимаю.",
				"Не удалось рассчитать. Попробуйте снова."
			};
			break;
			
			case "ch":
			jam1 = "(?i).*\\b时间\\b.*";
			hari1 = "(?i).*\\b(星期|日子|天)\\b.*";
			nows = "现在是 ";
			jamhari = "(?i).*((时间.*和.*(星期|日子|天))|((星期|日子|天).*和.*时间)).*";
			harijam = "(?i).*((星期|日子|天).*和.*时间)|(时间.*和.*(星期|日子|天)).*";
			jamjam = "(?i).*(几点了|现在几点|时间|现在时间).*";
			kemarin = "(?i).*\\b昨天\\b.*";
			isday = "星期";
			tomisDay = "明天是 ";
			yesisDay = "昨天是 ";
			besok = "(?i).*\\b明天\\b.*";
			nowday = "今天是 ";
			proses = "处理中...";
			toastMessage = "问题不能为空。";
			timeNow = "现在时间是 ";
			oclock = "时间 ";
			past = " 过了 ";
			secondWord = " 秒";
			minuteWord = " 分钟";
			nowisDay = "今天是 ";
			sorryMessage = "对不起，我不明白您的问题。";
			additionalResponses = new String[]{
				"还有什么我可以帮您的吗？",
				"请问其他问题，我很乐意帮助。",
				"如果还有问题，请随时提问！",
				"也许您还有其他想知道的？",
				"您还有其他疑问吗？"
			};
			calculationMessages = new String[]{
				"计算结果是：%s\n计算遵循了 *BODMAS* 规则。\n还有其他问题想问的吗？",
				"答案是：%s\n我使用 *BODMAS* 法则确保准确性。\n如果还有问题，欢迎继续提问～",
				"这是结果：%s\n根据 *BODMAS* 原则进行计算。\n我可以继续帮您哦！",
				"结果为：%s\n采用标准运算顺序。\n需要了解更多吗？"
			};
			bingungAnswers = new String[]{
				"对不起，我只能处理数字。",
				"我无法计算这个，请输入有效的数字。",
				"您可以再说清楚一些吗？我不太明白。",
				"无法计算，请再试一次。"
			};
			break;
			
			case "ar":
			jam1 = "(?i).*\\bساعة\\b.*";
			hari1 = "(?i).*\\bيوم\\b.*";
			nows = "الآن ";
			jamhari = "(?i).*((الساعة).*و.*اليوم).*";
			harijam = "(?i).*(اليوم.*و.*الساعة).*";
			jamjam = "(?i).*(كم الساعة|ما الوقت|الساعة|الآن الساعة).*";
			kemarin = "(?i).*\\b(أمس)\\b.*";
			isday = "يوم";
			tomisDay = "غدًا هو يوم ";
			yesisDay = "أمس كان يوم ";
			besok = "(?i).*\\b(غدًا)\\b.*";
			nowday = "اليوم هو يوم ";
			proses = "جارٍ المعالجة...";
			toastMessage = "لا يمكن أن يكون السؤال فارغًا.";
			timeNow = "الآن الساعة ";
			oclock = "الساعة ";
			past = " و ";
			secondWord = " ثانية";
			minuteWord = " دقيقة";
			nowisDay = "اليوم هو يوم ";
			sorryMessage = "عذرًا، لم أفهم سؤالك.";
			additionalResponses = new String[]{
				"هل هناك شيء آخر يمكنني مساعدتك به؟",
				"يرجى طرح سؤال آخر، أنا هنا للمساعدة.",
				"إذا كان لديك أي سؤال آخر، لا تتردد!",
				"ربما هناك شيء آخر تريد معرفته؟",
				"هل لا يزال لديك استفسار آخر؟"
			};
			calculationMessages = new String[]{
				"نتيجة الحساب: %s\nتم اتباع قاعدة *BODMAS*.\nهل لديك سؤال آخر؟",
				"النتيجة هي: %s\nاستخدمت قاعدة *BODMAS* للحصول على نتيجة دقيقة.\nهل أساعدك بشيء آخر؟",
				"هذه هي النتيجة: %s\nبقاعدة *BODMAS* تصبح الحسابات أدق.\nهل لديك استفسار آخر؟",
				"الإجابة هي: %s\nاتبعت ترتيب العمليات الحسابية.\nيمكنك طرح أي سؤال آخر أيضًا~"
			};
			bingungAnswers = new String[]{
				"عذرًا، يمكنني فقط معالجة الأرقام.",
				"لا يمكنني حساب ذلك. الرجاء إدخال أرقام صحيحة.",
				"هل يمكنك التوضيح؟ لم أفهم قصدك.",
				"لا يمكنني إجراء هذا الحساب. حاول مرة أخرى."
			};
			break;
			
			case "tr":
			jam1 = "(?i).*\\bsaat\\b.*";
			hari1 = "(?i).*\\bgün\\b.*";
			nows = "Şu anda ";
			jamhari = "(?i).*((saat).*ve.*gün).*";
			harijam = "(?i).*(gün.*ve.*saat).*";
			jamjam = "(?i).*(saat kaç|şu an saat kaç|saat|şimdi saat).*";
			kemarin = "(?i).*\\b(dün)\\b.*";
			isday = "Gün";
			tomisDay = "Yarın günlerden ";
			yesisDay = "Dün günlerden ";
			besok = "(?i).*\\b(yarın)\\b.*";
			nowday = "Bugün günlerden ";
			proses = "İşleniyor...";
			toastMessage = "Soru boş olamaz.";
			timeNow = "Şimdi saat ";
			oclock = "Saat ";
			past = " geçiyor ";
			secondWord = " saniye";
			minuteWord = " dakika";
			nowisDay = "Bugün günlerden ";
			sorryMessage = "Üzgünüm, sorunuzu anlayamadım.";
			additionalResponses = new String[]{
				"Size başka nasıl yardımcı olabilirim?",
				"Başka bir şey sormak ister misiniz? Yardımcı olmaktan memnuniyet duyarım.",
				"Eğer başka sorunuz varsa, sormaktan çekinmeyin!",
				"Belki öğrenmek istediğiniz başka bir şey vardır?",
				"Başka bir şey sormak ister misiniz?"
			};
			calculationMessages = new String[]{
				"Hesaplama sonucu: %s\nBu hesaplama *BODMAS* kuralına göre yapılmıştır.\nBaşka bir sorunuz var mı?",
				"Sonuç: %s\nDaha doğru sonuç için *BODMAS* kuralı kullanıldı.\nBaşka bir yardıma ihtiyacınız var mı?",
				"İşte sonuç: %s\n*BODMAS* kuralıyla hesaplama daha kesin oldu.\nBaşka bir sorunuz var mı?",
				"Cevap: %s\nMatematiksel işlem sırasına göre hesapladım.\nİsterseniz başka bir şey sorabilirsiniz~"
			};
			bingungAnswers = new String[]{
				"Üzgünüm, sadece sayılarla işlem yapabilirim.",
				"Hesaplayamıyorum. Lütfen geçerli bir sayı girin.",
				"Açıklayabilir misiniz? Ne demek istediğinizi anlayamadım.",
				"Bunu hesaplayamıyorum. Lütfen tekrar deneyin."
			};
			break;
		}
	}
	
	public void copyModel() {
		String[] languages = {"id", "en", "pt", "ru", "ch", "ar", "tr"};
		
		for (String lang : languages) {
			File langDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "EliciaAI/model/" + lang);
			if (!langDir.exists() && !langDir.mkdirs()) {
				Log.e("copyModel", "Gagal membuat folder: " + langDir.getAbsolutePath());
				continue;
			}
			
			try {
				String[] fileList = getAssets().list("model/" + lang);
				if (fileList == null) continue;
				
				for (String filename : fileList) {
					File targetFile = new File(langDir, filename);
					
					if (targetFile.exists()) {
						Log.d("copyModel", "Lewati (sudah ada): " + targetFile.getName());
						continue;
					}
					
					try (InputStream in = getAssets().open("model/" + lang + "/" + filename);
					OutputStream out = new FileOutputStream(targetFile)) {
						
						byte[] buffer = new byte[4096];
						int length;
						while ((length = in.read(buffer)) > 0) {
							out.write(buffer, 0, length);
						}
						
						Log.d("copyModel", "Disalin: " + filename + " ke " + targetFile.getAbsolutePath());
						
					} catch (IOException e) {
						Log.e("copyModel", "Gagal menyalin file: " + filename, e);
					}
				}
			} catch (IOException e) {
				Log.e("copyModel", "Gagal membaca assets untuk bahasa: " + lang, e);
			}
		}
	}
	
	public void showCustomToast(Context context, String text, boolean isLong, String color, int radius, String textcolor, int textsize, int gravity) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View layout = inflater.inflate(R.layout.toast_design, null);
		
		TextView toast = layout.findViewById(R.id.toast);
		LinearLayout background = layout.findViewById(R.id.background);
		
		GradientDrawable bgDrawable = new GradientDrawable();
		bgDrawable.setColor(Color.parseColor(color));
		bgDrawable.setCornerRadius(radius);
		background.setBackground(bgDrawable);
		background.setElevation(6);
		
		toast.setTextSize(textsize);
		toast.setText(text);
		toast.setTextColor(Color.parseColor(textcolor));
		
		Toast customToast = new Toast(context);
		switch (gravity) {
			case 1:
			customToast.setGravity(Gravity.BOTTOM, 0, 100);
			break;
			case 2:
			customToast.setGravity(Gravity.TOP, 0, 0);
			break;
			case 3:
			customToast.setGravity(Gravity.CENTER, 0, 0);
			break;
			default:
			customToast.setGravity(Gravity.BOTTOM, 0, 100);
			break;
		}
		customToast.setDuration(isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
		customToast.setView(layout);
		customToast.show();
	}
	
	public class Flowchat_backgroundAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Flowchat_backgroundAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.flowchat_design, null);
			}
			
			final LinearLayout background = _view.findViewById(R.id.background);
			final TextView onoff_system_thumbdown = _view.findViewById(R.id.onoff_system_thumbdown);
			final TextView onoff_system_thumbup = _view.findViewById(R.id.onoff_system_thumbup);
			final LinearLayout userquestion_background = _view.findViewById(R.id.userquestion_background);
			final LinearLayout eliciaanswer_background = _view.findViewById(R.id.eliciaanswer_background);
			final TextView user_question = _view.findViewById(R.id.user_question);
			final TextView elicia_answer = _view.findViewById(R.id.elicia_answer);
			final LinearLayout eliciaanswer_tooltip_background = _view.findViewById(R.id.eliciaanswer_tooltip_background);
			final ImageView button_thumb_up = _view.findViewById(R.id.button_thumb_up);
			final ImageView button_thumb_down = _view.findViewById(R.id.button_thumb_down);
			final ImageView button_content_share = _view.findViewById(R.id.button_content_share);
			final ImageView button_content_copy = _view.findViewById(R.id.button_content_copy);
			final ImageView button_content_morevert = _view.findViewById(R.id.button_content_morevert);
			
			if (answer.get(_position).containsKey("jawaban")) {
				user_question.setText(question.get(_position));
				String inputText = answer.get(_position).get("jawaban").toString();
				
				StringBuilder cleanedTextBuilder = new StringBuilder();
				ArrayList<int[]> boldSpans = new ArrayList<>();
				ArrayList<int[]> monoSpans = new ArrayList<>();
				
				int i = 0;
				int boldStart = -1;
				int monoStart = -1;
				
				while (i < inputText.length()) {
					if (inputText.startsWith("```", i)) {
						if (monoStart == -1) monoStart = cleanedTextBuilder.length();
						else {
							monoSpans.add(new int[]{monoStart, cleanedTextBuilder.length()});
							monoStart = -1;
						}
						i += 3;
					} else if (inputText.startsWith("**", i)) {
						if (boldStart == -1) boldStart = cleanedTextBuilder.length();
						else {
							boldSpans.add(new int[]{boldStart, cleanedTextBuilder.length()});
							boldStart = -1;
						}
						i += 2;
					} else if (inputText.charAt(i) == '*' || inputText.charAt(i) == '`') {
						if (boldStart == -1) boldStart = cleanedTextBuilder.length();
						else {
							boldSpans.add(new int[]{boldStart, cleanedTextBuilder.length()});
							boldStart = -1;
						}
						i++;
					} else {
						cleanedTextBuilder.append(inputText.charAt(i));
						i++;
					}
				}
				
				SpannableString spannableString = new SpannableString(cleanedTextBuilder.toString());
				
				for (int[] span : boldSpans) {
					spannableString.setSpan(new StyleSpan(Typeface.BOLD), span[0], span[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
				
				for (int[] span : monoSpans) {
					spannableString.setSpan(new TypefaceSpan("monospace"), span[0], span[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
				
				elicia_answer.setText(spannableString);
				
				int textColor = apptheme_system.getText().toString().equals("dark") ? 0xFFFFFFFF : 0xFF000000;
				elicia_answer.setTextColor(0xFFFFFFFF);
				user_question.setTextColor(textColor);
				
				android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
				int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
				SketchUi.setColor(apptheme_system.getText().toString().equals("dark") ? 0xFF373737 : 0xFFE0E0E0);
				SketchUi.setCornerRadii(new float[]{d * 16, d * 16, d * 3, d * 3, d * 16, d * 16, d * 16, d * 16});
				userquestion_background.setBackground(SketchUi);
				
				android.graphics.drawable.GradientDrawable Elicia = new android.graphics.drawable.GradientDrawable();
				Elicia.setColor(0xFF3F51B5);
				Elicia.setCornerRadii(new float[]{d * 3, d * 3, d * 16, d * 16, d * 16, d * 16, d * 16, d * 16});
				elicia_answer.setBackground(Elicia);
			}
			
			if (!answer.get(_position).containsKey("thumb_up")) {
				answer.get(_position).put("thumb_up", false);
			}
			if (!answer.get(_position).containsKey("thumb_down")) {
				answer.get(_position).put("thumb_down", false);
			}
			
			if ((boolean) answer.get(_position).get("thumb_up")) {
				button_thumb_up.setImageResource(R.drawable.thumb_up_pressed);
			} else {
				button_thumb_up.setImageResource(R.drawable.thumb_up);
			}
			
			if ((boolean) answer.get(_position).get("thumb_down")) {
				button_thumb_down.setImageResource(R.drawable.thumb_down_pressed);
			} else {
				button_thumb_down.setImageResource(R.drawable.thumb_down);
			}
			
			button_thumb_up.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					boolean isThumbUp = (boolean) answer.get(_position).get("thumb_up");
					answer.get(_position).put("thumb_up", !isThumbUp);
					answer.get(_position).put("thumb_down", false);
					ChatHistory.saveChat(getApplicationContext(), question, answer);
					notifyDataSetChanged();
				}
			});
			
			button_thumb_down.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					boolean isThumbDown = (boolean) answer.get(_position).get("thumb_down");
					answer.get(_position).put("thumb_down", !isThumbDown);
					answer.get(_position).put("thumb_up", false);
					ChatHistory.saveChat(getApplicationContext(), question, answer);
					notifyDataSetChanged();
				}
			});
			
			button_content_share.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					String text = elicia_answer.getText().toString();
					Intent shareIntent = new Intent(Intent.ACTION_SEND);
					shareIntent.setType("text/plain");
					shareIntent.putExtra(Intent.EXTRA_TEXT, text);
					startActivity(Intent.createChooser(shareIntent, ""));
				}
			});
			
			button_content_copy.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
					if (clipboard != null) {
						ClipData clip = ClipData.newPlainText("Copied Text", elicia_answer.getText().toString());
						clipboard.setPrimaryClip(clip);
					}
				}
			});
			
			button_content_morevert.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					ContextThemeWrapper ctw = new ContextThemeWrapper(MainActivity.this, android.R.style.Theme_DeviceDefault);
					PopupMenu popup = new PopupMenu(ctw, _view);
					
					String lang = language_system.getText().toString();
					
					switch (lang) {
						case "id":
						popup.getMenu().add(0, 1, 0, "Bagikan Teks");
						popup.getMenu().add(0, 2, 1, "Pilih Teks");
						popup.getMenu().add(0, 3, 2, "Dengarkan");
						popup.getMenu().add(0, 4, 3, "Salin");
						popup.getMenu().add(0, 5, 4, "Hapus");
						break;
						
						case "pt":
						popup.getMenu().add(0, 1, 0, "Compartilhar Texto");
						popup.getMenu().add(0, 2, 1, "Selecionar Texto");
						popup.getMenu().add(0, 3, 2, "Ouvir");
						popup.getMenu().add(0, 4, 3, "Copiar");
						popup.getMenu().add(0, 5, 4, "Excluir");
						break;
						
						case "ru":
						popup.getMenu().add(0, 1, 0, "Поделиться текстом");
						popup.getMenu().add(0, 2, 1, "Выделить текст");
						popup.getMenu().add(0, 3, 2, "Прослушать");
						popup.getMenu().add(0, 4, 3, "Копировать");
						popup.getMenu().add(0, 5, 4, "Удалить");
						break;
						
						case "ch":
						popup.getMenu().add(0, 1, 0, "分享文字");
						popup.getMenu().add(0, 2, 1, "选择文本");
						popup.getMenu().add(0, 3, 2, "朗读");
						popup.getMenu().add(0, 4, 3, "复制");
						popup.getMenu().add(0, 5, 4, "删除");
						break;
						
						case "ar":
						popup.getMenu().add(0, 1, 0, "مشاركة النص");
						popup.getMenu().add(0, 2, 1, "تحديد النص");
						popup.getMenu().add(0, 3, 2, "استمع");
						popup.getMenu().add(0, 4, 3, "نسخ");
						popup.getMenu().add(0, 5, 4, "حذف");
						break;
						
						case "tr":
						popup.getMenu().add(0, 1, 0, "Metni Paylaş");
						popup.getMenu().add(0, 2, 1, "Metni Seç");
						popup.getMenu().add(0, 3, 2, "Dinle");
						popup.getMenu().add(0, 4, 3, "Kopyala");
						popup.getMenu().add(0, 5, 4, "Sil");
						break;
						
						default:
						popup.getMenu().add(0, 1, 0, "Share Text");
						popup.getMenu().add(0, 2, 1, "Select Text");
						popup.getMenu().add(0, 3, 2, "Listen");
						popup.getMenu().add(0, 4, 3, "Copy");
						popup.getMenu().add(0, 5, 4, "Delete");
						break;
					}
					
					popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
						@Override
						public boolean onMenuItemClick(MenuItem item) {
							int itemId = item.getItemId();
							String lang = language_system.getText().toString();
							
							String share_via, delete_message_title, delete_message_text, cancel, delete;
							
							switch (lang) {
								case "en":
								share_via = "Share via";
								delete_message_title = "Delete Message?";
								delete_message_text = "Deleted messages cannot be restored.";
								cancel = "Cancel";
								delete = "Delete";
								break;
								case "pt":
								share_via = "Compartilhar via";
								delete_message_title = "Excluir mensagem?";
								delete_message_text = "Mensagens excluídas não podem ser recuperadas.";
								cancel = "Cancelar";
								delete = "Excluir";
								break;
								case "ru":
								share_via = "Поделиться через";
								delete_message_title = "Удалить сообщение?";
								delete_message_text = "Удалённые сообщения восстановить нельзя.";
								cancel = "Отмена";
								delete = "Удалить";
								break;
								case "ch":
								share_via = "通过…分享";
								delete_message_title = "删除消息？";
								delete_message_text = "删除的消息无法恢复。";
								cancel = "取消";
								delete = "删除";
								break;
								case "ar":
								share_via = "مشاركة عبر";
								delete_message_title = "حذف الرسالة؟";
								delete_message_text = "لا يمكن استعادة الرسائل المحذوفة.";
								cancel = "إلغاء";
								delete = "حذف";
								break;
								case "tr":
								share_via = "Paylaşım yolu";
								delete_message_title = "Mesaj silinsin mi?";
								delete_message_text = "Silinen mesajlar geri getirilemez.";
								cancel = "İptal";
								delete = "Sil";
								break;
								default:
								share_via = "Bagikan melalui";
								delete_message_title = "Hapus Pesan?";
								delete_message_text = "Pesan yang dihapus tidak dapat dikembalikan.";
								cancel = "Batal";
								delete = "Hapus";
								break;
							}
							
							if (itemId == 1) {
								String text = elicia_answer.getText().toString();
								Intent shareIntent = new Intent(Intent.ACTION_SEND);
								shareIntent.setType("text/plain");
								shareIntent.putExtra(Intent.EXTRA_TEXT, text);
								startActivity(Intent.createChooser(shareIntent, share_via));
							} else if (itemId == 2) {
								String textJawaban = elicia_answer.getText().toString();
								system_jawaban.setText(textJawaban);
								
								if (apptheme_system.getText().toString().equals("light")) {
									getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
								} else if (apptheme_system.getText().toString().equals("dark")) {
									getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#090909")));
								}
								
								UiHelper.hideKeyboard(MainActivity.this);
								
								textselection_screen.setScaleX(0.97f);
								textselection_screen.setScaleY(0.97f);
								textselection_screen.setAlpha(0f);
								textselection_screen.setVisibility(View.VISIBLE);
								account_screen.setVisibility(View.GONE);
								elicia_screen.setVisibility(View.GONE);
								
								textselection_screen.animate()
								.scaleX(1f)
								.scaleY(1f)
								.alpha(1f)
								.setDuration(250)
								.setInterpolator(new DecelerateInterpolator())
								.start();
							} else if (itemId == 3) {
								if (_position >= 0 && _position < answer.size()) {
									Locale ttsLocale;
									
									switch (lang) {
										case "id":
										ttsLocale = new Locale("id", "ID"); // Bahasa Indonesia
										break;
										case "pt":
										ttsLocale = new Locale("pt", "PT"); // Portugis
										break;
										case "ru":
										ttsLocale = new Locale("ru", "RU"); // Rusia
										break;
										case "ch":
										ttsLocale = Locale.SIMPLIFIED_CHINESE; // Mandarin
										break;
										case "ar":
										ttsLocale = new Locale("ar", "SA"); // Arab (Arab Saudi)
										break;
										case "tr":
										ttsLocale = new Locale("tr", "TR"); // Turki
										break;
										case "en":
										default:
										ttsLocale = Locale.ENGLISH; // Default ke Inggris
										break;
									}
									
									// Set Locale ke TTS
									int resultLang = tekskesuara.setLanguage(ttsLocale);
									String textToSpeak = answer.get(_position).get("jawaban").toString();
									if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // API 21 ke atas
										tekskesuara.setPitch((float)1.2d);
										tekskesuara.setSpeechRate((float)1.1d);
										Bundle params = new Bundle();
										params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "TTS_FINISH");
										tekskesuara.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, params, "TTS_FINISH");
									} else { // API 20 ke bawah
										HashMap<String, String> params = new HashMap<>();
										tekskesuara.setPitch((float)1.2d);
										tekskesuara.setSpeechRate((float)1.1d);
										params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "TTS_FINISH");
										tekskesuara.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, params);
									}
								}
							} else if (itemId == 4) {
								ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
								if (clipboard != null) {
									ClipData clip = ClipData.newPlainText("Copied Text", elicia_answer.getText().toString());
									clipboard.setPrimaryClip(clip);
								}
							} else if (itemId == 5) {
								Dialog dialog = new Dialog(MainActivity.this);
								dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
								dialog.setCancelable(true);
								
								float dp = getResources().getDisplayMetrics().density;
								
								LinearLayout root = new LinearLayout(MainActivity.this);
								root.setOrientation(LinearLayout.VERTICAL);
								root.setPadding((int)(23 * dp), (int)(20 * dp), (int)(23 * dp), (int)(16 * dp));
								root.setBackground(new GradientDrawable() {{
										setColor(Color.parseColor("#1C1C1E"));
										setCornerRadius(28 * dp);
									}});
								
								TextView title = new TextView(MainActivity.this);
								title.setText(delete_message_title);
								title.setTextSize(20);
								title.setTextColor(Color.parseColor("#FFFFFF"));
								title.setPadding(0, 0, 0, (int)(14 * dp));
								root.addView(title);
								
								TextView msg = new TextView(MainActivity.this);
								msg.setText(delete_message_text);
								msg.setTextSize(14);
								msg.setTextColor(Color.parseColor("#B0B0B0"));
								msg.setPadding(0, 0, 0, (int)(9 * dp));
								root.addView(msg);
								
								LinearLayout row = new LinearLayout(MainActivity.this);
								row.setOrientation(LinearLayout.HORIZONTAL);
								row.setGravity(Gravity.END);
								
								TextView btnCancel = new TextView(MainActivity.this);
								btnCancel.setText(cancel);
								btnCancel.setTextSize(14);
								btnCancel.setTypeface(null, Typeface.BOLD);
								btnCancel.setTextColor(Color.parseColor("#3F51B5"));
								btnCancel.setPadding((int)(16 * dp), (int)(12 * dp), (int)(24 * dp), (int)(12 * dp));
								btnCancel.setOnClickListener(v -> {
									dialog.dismiss();
								});
								row.addView(btnCancel);
								
								TextView btnOk = new TextView(MainActivity.this);
								btnOk.setText(delete);
								btnOk.setTextSize(14);
								btnOk.setTypeface(null, Typeface.BOLD);
								btnOk.setTextColor(Color.parseColor("#3F51B5"));
								btnOk.setPadding((int)(16 * dp), (int)(12 * dp), (int)(18 * dp), (int)(12 * dp));
								btnOk.setOnClickListener(v -> {
									if (_position >= 0 && _position < answer.size()) {
										question.remove(_position);
										answer.remove(_position);
										notifyDataSetChanged();
										ChatHistory.saveChat(getApplicationContext(), question, answer);
									}
									dialog.dismiss();
								});
								row.addView(btnOk);
								root.addView(row);
								
								dialog.setContentView(root);
								Window w = dialog.getWindow();
								if (w != null) {
									int width = (int)(getResources().getDisplayMetrics().widthPixels * 0.85f);
									w.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
									w.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
									w.setGravity(Gravity.CENTER);
								}
								dialog.show();
							}
							return true;
						}
					});
					popup.show();
				}
			});
			
			int buttonColor = apptheme_system.getText().toString().equals("dark") ? 0xFFBDBDBD : 0xFF7A7A7A;
			int pressedColor = 0xFF3F51B5;
			
			button_thumb_up.setColorFilter((boolean) answer.get(_position).get("thumb_up") ? pressedColor : buttonColor, PorterDuff.Mode.MULTIPLY);
			button_thumb_down.setColorFilter((boolean) answer.get(_position).get("thumb_down") ? pressedColor : buttonColor, PorterDuff.Mode.MULTIPLY);
			button_content_share.setColorFilter(buttonColor, PorterDuff.Mode.MULTIPLY);
			button_content_copy.setColorFilter(buttonColor, PorterDuff.Mode.MULTIPLY);
			button_content_morevert.setColorFilter(buttonColor, PorterDuff.Mode.MULTIPLY);
			
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx)) _result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}