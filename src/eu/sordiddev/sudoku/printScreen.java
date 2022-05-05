package eu.sordiddev.sudoku;

import javax.swing.*;
import java.awt.*;

import static eu.sordiddev.sudoku.checkNumber.clearcolor;

public class printScreen {

    public printScreen(int key, JLabel currentlabel, JPanel currentpanel, int currenti, int currentj, int[][] riddle) {

        /*
        Handelt nur, wenn der Vordergrund eines Feldes nicht schwarz oder rot ist.
        Somit können die fest gesetzten Felder nicht überschrieben werden
        Setzt den Text des Feldes anschließend auf die eingegebe Zahl.
        Speichert den eingegebenen Wert im globalen Array.
        Ein eingegebener Backspace wird mit -1 ausgegeben und somit durch einen Space ersetzt.
        Im Anschluss werden die Felder mit der Checknumber.java überprüft
         */

        if (currentlabel.getForeground() != Color.BLACK && currentlabel.getForeground()!= Color.red) {

            if (key != 0) {

                currentlabel.setText(String.valueOf(key));

                riddle[currenti][currentj] = key;

                if (key == -1) {
                    currentlabel.setText("");
                }

                clearcolor();

                boolean check = checkNumber.check(riddle, currenti, currentj, key);

            }
        }
    }
    }
