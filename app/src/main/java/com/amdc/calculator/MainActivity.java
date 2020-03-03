package com.amdc.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.amdc.calculator.Arithmetic.DIVISION;
import static com.amdc.calculator.Arithmetic.MINUS;
import static com.amdc.calculator.Arithmetic.MULTIPLY;
import static com.amdc.calculator.Arithmetic.PLUS;


public class MainActivity extends AppCompatActivity {
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
            buttonPlus, buttonMinus, buttonDivision, buttonMultiply, buttonPoint, buttonC, buttonEqual,
            buttonNegative, buttonDel, buttonPercent, buttonMC, buttonMAdd, buttonMSub, buttonMRecall,
            buttonPI, buttonSquare, buttonSquareRoot, buttonOneDev;
    @SuppressLint("StaticFieldLeak")
    static TextView editText, textArithmetic, textMemory;
    static String orientation;
    static long backPressureTime;
    static Toast backToast;
    static BigDecimal valueOne = new BigDecimal(0);
    static BigDecimal valueTwo = new BigDecimal(0);
    static BigDecimal valueMemory = new BigDecimal(0);
    static final BigDecimal one = new BigDecimal(1);
    static int height;
    static boolean newLine;
    static Vibrator vibrator;
    static Window window;
    static Display display;
    static Arithmetic arithmetic = null;
    //MediaPlayer sound;

    public static void displayMessage(String message) {
        textArithmetic.setSelected(true);
        textArithmetic.setText(message);
    }

    public static void displayMemory(String message) {
        textMemory.setSelected(true);
        textMemory.setText(message);
    }

    @SuppressLint("SetTextI18n")
    public void allClick(String btn) { vibrator.vibrate(20);
        if ((editText.getText() + "").equals("0") || newLine) { // eql != true
            editText.setText(btn);
            newLine = false;
            new ScreenSize().displayNum();
        } else editText.setText(editText.getText() + btn);
        new ScreenSize().displayNum();
        editText.setSelected(true); // scrolling string
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        //sound = MediaPlayer.create(this, R.raw.watter);
        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //полноэкранный режим
        display = getWindowManager().getDefaultDisplay(); // разрешение экрана
        height = display.getHeight();  // высота экрана

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) { orientation = "Portrait"; }  // ориентация экрана
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){ orientation = "Landscape";}

        textArithmetic = findViewById(R.id.edt2);
        textMemory = findViewById(R.id.edt3);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonPoint = findViewById(R.id.buttonPoint);
        buttonPlus = findViewById(R.id.buttonAdd);
        buttonMinus = findViewById(R.id.buttonSub);
        buttonMultiply = findViewById(R.id.buttonMul);
        buttonDivision = findViewById(R.id.buttonDev);
        buttonPercent = findViewById(R.id.buttonPer);
        buttonC = findViewById(R.id.buttonAC);
        buttonNegative = findViewById(R.id.buttonChange);
        buttonDel = findViewById(R.id.buttonDel);
        buttonEqual = findViewById(R.id.buttonEql);
        buttonMC = findViewById(R.id.buttonMC);
        buttonMAdd = findViewById(R.id.buttonMAdd);
        buttonMSub = findViewById(R.id.buttonMSub);
        buttonMRecall = findViewById(R.id.buttonMRecall);
        buttonPI = findViewById(R.id.buttonPi);
        buttonSquare = findViewById(R.id.buttonSqu);
        buttonOneDev = findViewById(R.id.buttonOne);
        buttonSquareRoot = findViewById(R.id.buttonRoot);
        editText = findViewById(R.id.edt1);

        button0.setOnClickListener(v -> allClick(button0.getText().toString()));
        button1.setOnClickListener(v -> allClick(button1.getText().toString()));
        button2.setOnClickListener(v -> allClick(button2.getText().toString()));
        button3.setOnClickListener(v -> allClick(button3.getText().toString()));
        button4.setOnClickListener(v -> allClick(button4.getText().toString()));
        button5.setOnClickListener(v -> allClick(button5.getText().toString()));
        button6.setOnClickListener(v -> allClick(button6.getText().toString()));
        button7.setOnClickListener(v -> allClick(button7.getText().toString()));
        button8.setOnClickListener(v -> allClick(button8.getText().toString()));
        button9.setOnClickListener(v -> allClick(button9.getText().toString()));

        buttonPlus.setOnClickListener(v -> { vibrator.vibrate(20);
            try {
                if (editText.getText().length() > 0) {
                    arithmeticOperations(arithmetic);
                    valueOne = new BigDecimal(editText.getText() + "");
                    arithmetic = PLUS;
                    displayMessage(editText.getText() + " +");
                    editText.setText("");
                } else displayMessage(textArithmetic.getText().toString().substring(0, textArithmetic.getText().length() - 1) + "+");
                arithmetic = PLUS;
            } catch (Exception e) {
                resetAll();
            }
        });

        buttonMinus.setOnClickListener(v -> { vibrator.vibrate(20);
            try {
                if (editText.getText().length() > 0) {
                    arithmeticOperations(arithmetic);
                    valueOne = new BigDecimal(editText.getText() + "");
                    arithmetic = MINUS;
                    displayMessage(editText.getText() + " -");
                    editText.setText("");
                } else displayMessage(textArithmetic.getText().toString().substring(0, textArithmetic.getText().length() - 1) + "-");
                arithmetic = MINUS;
            } catch (Exception e) { resetAll(); }
        });

        buttonMultiply.setOnClickListener(v -> { vibrator.vibrate(20);
            try {
                if (editText.getText().length() > 0) {
                    arithmeticOperations(arithmetic);
                    valueOne = new BigDecimal(editText.getText() + "");
                    arithmetic = MULTIPLY;
                    displayMessage(editText.getText() + " *");
                    editText.setText("");
                } else displayMessage(textArithmetic.getText().toString().substring(0, textArithmetic.getText().length() - 1) + "*");
                arithmetic = MULTIPLY;
            } catch (Exception e) { resetAll(); }
        });

        buttonDivision.setOnClickListener(v -> { vibrator.vibrate(20);
            try {
                if (editText.getText().length() > 0) {
                    arithmeticOperations(arithmetic);
                    valueOne = new BigDecimal(editText.getText() + "");
                    arithmetic = DIVISION;
                    displayMessage(editText.getText() + " /");
                    editText.setText("");
                } else displayMessage(textArithmetic.getText().toString().substring(0, textArithmetic.getText().length() - 1) + "/");
                arithmetic = DIVISION;
            } catch (Exception e) { resetAll(); }
        });

        buttonPercent.setOnClickListener(v -> { vibrator.vibrate(20);
            try {
                StringBuilder stBu = new StringBuilder();
                if (arithmetic != null) {
                    valueTwo = new BigDecimal(editText.getText() + "");
                    displayMessage(textArithmetic.getText().toString() + " (" + valueTwo + "% from " + valueOne + ")");
                    BigDecimal f = valueOne.multiply(valueTwo);
                    new ScreenDisplay().outputDisplay(stBu.append(f.divide(BigDecimal.valueOf(100), 20, RoundingMode.HALF_EVEN)));
                }
            } catch (Exception e) { resetAll(); }
        });

        buttonEqual.setOnClickListener(v -> { vibrator.vibrate(20);
            try {
                if (editText.getText().length() > 0) {
                    valueTwo = new BigDecimal(editText.getText() + "");
                    //displayMessage(textArithmetic.getText().toString() + " " + editText.getText());
                    arithmeticOperations(arithmetic);
                    arithmetic = null;
                    newLine = true;
                }
            } catch (Exception e) { resetAll(); }
        });

        buttonC.setOnClickListener(v -> { vibrator.vibrate(20);
            if ((editText.getText() + "").equals("0")) {
                valueOne = null;
                textArithmetic.setText("");
                arithmetic = null;
                displayMessage("");
                new ScreenSize().displayNum();
            } else editText.setText("0");
            editText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            new ScreenSize().displayNum();
        });

        buttonPoint.setOnClickListener(v -> { vibrator.vibrate(20);
            if(editText.getText().equals("") || newLine) {
                editText.setText("0.");
                newLine = false;
            }
            else if(editText.getText().toString().contains(".")) {
                buttonPoint.setEnabled(false);
            }
            else editText.setText(editText.getText() + ".");
            buttonPoint.setEnabled(true);
            newLine = false;
        });

        buttonNegative.setOnClickListener(v -> { vibrator.vibrate(20);
            if (editText.getText().length() > 0) {
                double a = Double.parseDouble(editText.getText() + "");
                if (a < 0) editText.setText((editText.getText() + "").replace("-", ""));
                if (a > 0) editText.setText("-" + editText.getText());
            }
        });

        buttonDel.setOnClickListener(v -> { vibrator.vibrate(20);
            if (editText.getText().length() > 1) {
                StringBuilder stBld = new StringBuilder(editText.getText());
                stBld.setLength(stBld.length() - 1);
                new ScreenDisplay().outputDisplay(stBld);
            }
            else editText.setText("0");
        });

        buttonMC.setOnClickListener(v -> { vibrator.vibrate(20);
            displayMemory("");
            valueMemory = new BigDecimal(0);
        });

        buttonMAdd.setOnClickListener(v -> { vibrator.vibrate(20);
            if(editText.getText() != "") {
                valueMemory = valueMemory.add(new BigDecimal(editText.getText() + ""));
                displayMemory("Mem: " + valueMemory);
            }
        });

        buttonMSub.setOnClickListener(v -> { vibrator.vibrate(20);
            if(editText.getText() != ""){
                valueMemory = valueMemory.subtract(new BigDecimal(editText.getText() + ""));
                displayMemory("Mem: " + valueMemory);
            }
        });

        buttonMRecall.setOnClickListener(v -> { vibrator.vibrate(20);
            editText.setText(String.valueOf(valueMemory));
            new ScreenSize().displayNum();
        });

        if (buttonPI != null && buttonSquare != null && buttonSquareRoot != null && buttonOneDev != null) {
            try {
                buttonPI.setOnClickListener(v -> { vibrator.vibrate(20);
                    editText.setText("3.14159265358979323846");
                    newLine = true;
                    new ScreenSize().displayNum();
                });

                buttonSquare.setOnClickListener(v -> { vibrator.vibrate(20);
                    valueOne = new BigDecimal(editText.getText() + "");
                    valueOne = valueOne.multiply(valueOne);
                    new ScreenDisplay().outputDisplay(new StringBuilder().append(valueOne));
                });

                buttonSquareRoot.setOnClickListener(v -> { vibrator.vibrate(20);
                    StringBuilder stBu = new StringBuilder();
                    valueOne = new BigDecimal(editText.getText() + "");
                    BigDecimal x = new BigDecimal(Math.sqrt(valueOne.doubleValue()));
                    stBu.append(x.add(new BigDecimal(valueOne.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0))));
                    new ScreenDisplay().outputDisplay(stBu);
                });

                buttonOneDev.setOnClickListener(v -> { vibrator.vibrate(20);
                    StringBuilder stBu = new StringBuilder();
                    valueOne = new BigDecimal(editText.getText() + "");
                    new ScreenDisplay().outputDisplay(stBu.append(one.divide(valueOne, 20, RoundingMode.HALF_EVEN)));
                });
            } catch (Exception e) { resetAll(); }
        }
    }

    public void arithmeticOperations(Arithmetic arithmetic) {
        StringBuilder stBu = new StringBuilder();
        try {
            if (editText.getText().length() > 0) {
                valueTwo = new BigDecimal(editText.getText() + "");
                displayMessage(textArithmetic.getText() + " " + editText.getText());
                if (arithmetic == PLUS) new ScreenDisplay().outputDisplay(stBu.append(valueOne.add(valueTwo)));
                if (arithmetic == MINUS) new ScreenDisplay().outputDisplay(stBu.append(valueOne.subtract(valueTwo)));
                if (arithmetic == MULTIPLY) new ScreenDisplay().outputDisplay(stBu.append(valueOne.multiply(valueTwo)));
                if (arithmetic == DIVISION) new ScreenDisplay().outputDisplay(stBu.append(valueOne.divide(valueTwo, 20, RoundingMode.HALF_EVEN)));
                valueOne = new BigDecimal(0);
            }
        } catch (Exception e) {
            resetAll();
        }
    }

    private static void resetAll() {
        editText.setText("0");
        displayMessage("");
    }

    @Override
    public void onBackPressed() { // exit program
        if(backPressureTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Click again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressureTime = System.currentTimeMillis();
    }
}

