package eu.sordiddev.sudoku;

import javax.swing.*;
import java.awt.*;

import static eu.sordiddev.sudoku.CheckNumber.clearColor;

public class PrintScreen {

    public PrintScreen(int key, JLabel currentlabel, int currenti, int currentj, int[][] riddle) {

        /*
        Handelt nur, wenn der Vordergrund eines Feldes nicht schwarz oder rot ist.
        Somit können die fest gesetzten Felder nicht überschrieben werden
        Setzt den Text des Feldes anschließend auf die eingegebe Zahl.
        Speichert den eingegebenen Wert im globalen Array.
        Ein eingegebener Backspace wird mit -1 ausgegeben und somit durch einen Space ersetzt.
        Im Anschluss werden die Felder mit der Checknumber.java überprüft
         */

    if (currentlabel.getForeground() == Color.BLACK) {
            return;
        }

        if (key == 0) {
            return;
        }

        currentlabel.setText(String.valueOf(key));
        riddle[currenti][currentj] = key;

        if (key == -1) {
            currentlabel.setText("");
            riddle[currenti][currentj] = 0;
        }

        CheckNumber.clearColor();

        CheckNumber.check(riddle, currenti, currentj, key);
        FinalCheck.check();

    }

}
