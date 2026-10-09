package ru.iqwanoino.elicia;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import android.view.animation.DecelerateInterpolator;

public class UiHelper {
	
	public static void setupTextWatcher(final EditText editText, final View clearButton, 
	final View directQuestionButton, final View micQuestionButton, final View sendQuestionButton) {
		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				boolean isEmpty = TextUtils.isEmpty(s);
				
                // Visibilitas Clear button
				if (isEmpty) {
                    if (clearButton.getVisibility() == View.VISIBLE) {
						clearButton.setScaleX(0.8f);
						clearButton.setScaleY(0.8f);
						clearButton.setAlpha(0f);
						
						clearButton.animate()
						.scaleX(1f)
						.scaleY(1f)
						.alpha(1f)
						.setDuration(250)
						.setInterpolator(new DecelerateInterpolator())
						.start();
                        clearButton.setVisibility(View.GONE);
					}
				} else {
					if (clearButton.getVisibility() == View.GONE) {
						clearButton.setScaleX(0.8f);
						clearButton.setScaleY(0.8f);
						clearButton.setAlpha(0f);
						clearButton.setVisibility(View.VISIBLE);
						
						clearButton.animate()
						.scaleX(1f)
						.scaleY(1f)
						.alpha(1f)
						.setDuration(250)
						.setInterpolator(new DecelerateInterpolator())
						.start();
					}
				}
				
                // Vis8vilitas Directquestion button
				if (isEmpty) {
					if (directQuestionButton.getVisibility() == View.GONE) {
						directQuestionButton.setScaleX(0.8f);
						directQuestionButton.setScaleY(0.8f);
						directQuestionButton.setAlpha(0f);
						directQuestionButton.setVisibility(View.VISIBLE);
						
						directQuestionButton.animate()
						.scaleX(1f)
						.scaleY(1f)
						.alpha(1f)
						.setDuration(250)
						.setInterpolator(new DecelerateInterpolator())
						.start();
					}
				} else {
					if (directQuestionButton.getVisibility() == View.VISIBLE) {
						directQuestionButton.setScaleX(1.0f);
						directQuestionButton.setScaleY(1.0f);
						directQuestionButton.setAlpha(1f);
						
						directQuestionButton.animate()
						.scaleX(0.8f)
						.scaleY(0.8f)
						.alpha(0f)
						.setDuration(250)
						.setInterpolator(new DecelerateInterpolator())
						.start();
                        directQuestionButton.setVisibility(View.GONE);
					}
				}
                
                // Visibilitas Micquestion button
                if (isEmpty) {
					if (micQuestionButton.getVisibility() == View.GONE) {
						micQuestionButton.setScaleX(0.8f);
						micQuestionButton.setScaleY(0.8f);
						micQuestionButton.setAlpha(0f);
						micQuestionButton.setVisibility(View.VISIBLE);
						
						micQuestionButton.animate()
						.scaleX(1f)
						.scaleY(1f)
						.alpha(1f)
						.setDuration(250)
						.setInterpolator(new DecelerateInterpolator())
						.start();
					}
				} else {
					if (micQuestionButton.getVisibility() == View.VISIBLE) {
						micQuestionButton.setScaleX(1.0f);
						micQuestionButton.setScaleY(1.0f);
						micQuestionButton.setAlpha(1f);
						
						micQuestionButton.animate()
						.scaleX(0.8f)
						.scaleY(0.8f)
						.alpha(0f)
						.setDuration(250)
						.setInterpolator(new DecelerateInterpolator())
						.start();
                        micQuestionButton.setVisibility(View.GONE);
					}
				}
				
                // Visibilitas Sendquestion button
				if (isEmpty) {
					if (sendQuestionButton.getVisibility() == View.VISIBLE) {
						sendQuestionButton.setScaleX(1.0f);
						sendQuestionButton.setScaleY(1.0f);
						sendQuestionButton.setAlpha(1f);
						
						sendQuestionButton.animate()
						.scaleX(0.8f)
						.scaleY(0.8f)
						.alpha(0f)
						.setDuration(250)
						.setInterpolator(new DecelerateInterpolator())
						.start();
						sendQuestionButton.setVisibility(View.GONE);
					}
				} else {
					if (sendQuestionButton.getVisibility() == View.GONE) {
						sendQuestionButton.setScaleX(0.8f);
						sendQuestionButton.setScaleY(0.8f);
						sendQuestionButton.setAlpha(0f);
						sendQuestionButton.setVisibility(View.VISIBLE);
						
						sendQuestionButton.animate()
						.scaleX(1f)
						.scaleY(1f)
						.alpha(1f)
						.setDuration(250)
						.setInterpolator(new DecelerateInterpolator())
						.start();
					}
				}
			}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
	}
	
	public static void setupInputField(final EditText editText, final String hintText) {
		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				editText.setHint(TextUtils.isEmpty(s) ? hintText : " ");
			}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
	}
	
	public static void hideKeyboard(Activity activity) {
		if (activity != null) {
			View view = activity.getCurrentFocus();
			if (view != null) {
				InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
			}
		}
	}
}
