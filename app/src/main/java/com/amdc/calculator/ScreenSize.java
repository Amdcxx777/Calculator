package com.amdc.calculator;

import android.annotation.SuppressLint;
import android.graphics.Color;

@SuppressLint("Registered")
 public class ScreenSize extends MainActivity {

    public void displayNum(){
        editText.setTextColor(Color.parseColor("#4F6CFF"));
        if(orientation.equals("Portrait")) { // определение ориентации экрана
            if (textArithmetic.getText().equals("Value exceeded 25! To calculate large values, switch to landscape orientation")) {
                displayMessage("");
            }
            editText.setTextSize(58);
            if (editText.getText().length() > 10)
                editText.setTextSize(48);
            if (editText.getText().length() > 12)
                editText.setTextSize(40);
            if (editText.getText().length() > 15)
                editText.setTextSize(32);
            if (editText.getText().length() > 19)
                editText.setTextSize(26);
            if (editText.getText().length() > 25) {
                displayMessage("Value exceeded 25! To calculate large values, switch to landscape orientation");
                editText.setText(editText.getText().toString().substring(0, 25));
                editText.setTextColor(Color.RED);
            }
        }
        else if (orientation.equals("Landscape")) { // определение ориентации экрана
            if (textArithmetic.getText().equals("Value exceeded 50 characters! The result may be incorrect")) {
                displayMessage("");
            }
            editText.setTextSize(58);
            if (editText.getText().length() > 18 || (editText.getText().length() > 16 && height < 800))
                editText.setTextSize(48);
            if (editText.getText().length() > 22 || (editText.getText().length() > 20 && height < 800))
                editText.setTextSize(40);
            if (editText.getText().length() > 26 || (editText.getText().length() > 24 && height < 800))
                editText.setTextSize(32);
            if (editText.getText().length() > 33 || (editText.getText().length() > 30 && height < 800) || height < 480)
                editText.setTextSize(26);
            if (editText.getText().length() > 50) {
                displayMessage("Value exceeded 50 characters! The result may be incorrect");
                editText.setText(editText.getText().toString().substring(0, 50));
                editText.setTextColor(Color.RED);
                //newLine = true;
            }
        }
    }
}
