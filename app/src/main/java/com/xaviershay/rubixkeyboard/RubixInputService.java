package com.xaviershay.rubixkeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;

import java.util.Arrays;


public class RubixInputService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    @Override
    public View onCreateInputView() {
        KeyboardView keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(this, R.xml.rubix_pad);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection inputConnection = getCurrentInputConnection();

        if (inputConnection != null) {
            switch(primaryCode) {
                case Keyboard.KEYCODE_DELETE :
                    CharSequence selectedText = inputConnection.getSelectedText(0);

                    if (TextUtils.isEmpty(selectedText)) {
                        inputConnection.deleteSurroundingText(1, 0);
                    } else {
                        inputConnection.commitText("", 1);
                    }

                    break;
                default:
                    char code = (char) primaryCode;
                    CharSequence before = inputConnection.getTextBeforeCursor(1, 0);
                    String modifiers = "23'";

                    if (modifiers.indexOf(code) < 0 && !("".contentEquals(before) || " ".contentEquals(before))) {
                        inputConnection.commitText(" ", 1);
                    }
                    inputConnection.commitText(String.valueOf(code), 1);
                    break;
            }
        }
    }

    @Override
    public void onText(CharSequence charSequence) {
        InputConnection inputConnection = getCurrentInputConnection();

        if (inputConnection != null) {
            inputConnection.commitText(charSequence, charSequence.length());
        }
    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
