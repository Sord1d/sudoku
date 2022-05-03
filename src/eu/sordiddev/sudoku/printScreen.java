package eu.sordiddev.sudoku;

import javax.swing.*;
import java.awt.*;

import static eu.sordiddev.sudoku.checkNumber.BRED;
import static eu.sordiddev.sudoku.checkNumber.clearcolor;

public class printScreen {


    public printScreen(int key, JLabel currentlabel, JPanel currentpanel, int currenti, int currentj, int[][] riddle) {
        if (currentlabel.getForeground() != Color.BLACK && currentlabel.getForeground()!= Color.red) {
            if (key != 0) {


                currentlabel.setText(String.valueOf(key));
                riddle[currenti][currentj] = key;

                if (key == -1) {
                    currentlabel.setText("");
                }
                //überprüfung ob die Zahl mehr als ein Mal vorhanden ist
                clearcolor();
                boolean check = checkNumber.check(riddle, currenti, currentj, key);
                System.out.println("times: "+ checkNumber.timescombined);

                //wenn es orange ist wieder blau machen

                for (int i=0; i<9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (gui.alllabels[i][j].getForeground() == BRED) {
                            gui.alllabels[i][j].setForeground(Color.RED);
                        }

                    }


                }

            }
        }
    }
    }
