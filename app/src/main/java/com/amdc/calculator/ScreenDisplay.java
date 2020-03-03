package com.amdc.calculator;

import android.annotation.SuppressLint;
import java.math.BigDecimal;

@SuppressLint("Registered")
public class ScreenDisplay extends MainActivity{

    public void outputDisplay(StringBuilder stBu) {
        StringBuilder stBld = new StringBuilder();
        stBld.append(stBu);
        if (stBld.lastIndexOf(".") >= 0 && stBld.length() > 25)
            stBld.setLength(25);

        if (stBld.lastIndexOf(".") >= 0 && stBld.charAt(stBld.length() - 1) == '0') {
            int b = stBld.length();
            for (int i = 0; i < b; i++) { // цикл удаления лишних нулей после запятой
                if (stBld.charAt(stBld.length() - 1) == '0') stBld.setLength(stBld.length() - 1);
            }
            if (stBld.charAt(stBld.length() - 1) == '.')
                stBld.setLength(stBld.length() - 1); // удаление запятой, если она последняя в строке
        }

        if (stBld.lastIndexOf(".") >= 0 && (stBld.length() - stBld.lastIndexOf(".")) > 5) { // если в строке более 5-ти цифр после запятой
            char[] chr = new char[stBld.length() - stBld.lastIndexOf(".")]; // создание архива цифр после запятой
            char[] cha = new char[1];
            stBld.getChars(stBld.lastIndexOf("."), stBld.length(), chr, 0); // заполнение архива chr с запятой и последующих всех цифр
            stBld.getChars(0, 1, cha, 0);

            if (cha[0] != '0' && chr[1] == '0' && chr[2] == '0' && chr[3] == '0' && chr[4] == '0' && chr[5] == '0' && chr.length < 8)
                stBld.setLength(stBld.lastIndexOf(".")); // удаление символов в строке после запятой и самой запятой
            if (cha[0] != '0' && chr[1] == '0' && chr[2] == '0' && chr[3] == '0' && chr[4] == '0' &&
                    chr[5] == '0' && chr[6] == '0' && chr[7] == '0' && chr.length < 12)
                stBld.setLength(stBld.lastIndexOf(".")); // удаление символов в строке после запятой и самой запятой
            if (cha[0] != '0' && chr[1] == '0' && chr[2] == '0' && chr[3] == '0' && chr[4] == '0' &&
                    chr[5] == '0' && chr[6] == '0' && chr[7] == '0' && chr[8] == '0' && chr[9] == '0' && chr.length < 15)
                stBld.setLength(stBld.lastIndexOf(".")); // удаление символов в строке после запятой и самой запятой
            if (cha[0] != '0' && chr[1] == '0' && chr[2] == '0' && chr[3] == '0' && chr[4] == '0' && chr[5] == '0' && chr[6] == '0' &&
                    chr[7] == '0' && chr[8] == '0' && chr[9] == '0' && chr[10] == '0' && chr[11] == '0' && chr[12] == '0' && chr.length < 18)
                stBld.setLength(stBld.lastIndexOf(".")); // удаление символов в строке после запятой и самой запятой
            if (cha[0] != '0' && chr[1] == '0' && chr[2] == '0' && chr[3] == '0' && chr[4] == '0' && chr[5] == '0' && chr[6] == '0' &&
                    chr[7] == '0' && chr[8] == '0' && chr[9] == '0' && chr[10] == '0' && chr[11] == '0' && chr[12] == '0' && chr[13] == '0' && chr.length >= 18)
                stBld.setLength(stBld.lastIndexOf(".")); // удаление символов в строке после запятой и самой запятой

            if (chr[1] == '9' && chr[2] == '9' && chr[3] == '9' && chr[4] == '9' && chr[5] == '9') {
                stBld.setLength(stBld.lastIndexOf(".")); // удаление символов в строке после запятой и самой запятой
                valueOne = new BigDecimal(stBld + "");
                stBld.delete(0, stBld.length());
                stBld.append(valueOne.add(one));
            }
        }
        editText.setText(stBld);
        new ScreenSize().displayNum();
    }
}
