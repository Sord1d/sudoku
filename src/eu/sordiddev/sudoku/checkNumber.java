package eu.sordiddev.sudoku;

import java.awt.*;

//Überprüft, ob die zahl vorhanden ist
public class checkNumber {

    public static boolean check(int[][] riddle, int currenti, int key) {

        //im kästchen
        int keys = 0;
        boolean check = false;
        for (int j = 0; j<9; j++){
            if (riddle [currenti][j] == key) {
                keys ++;
                if (keys > 1) {
                    check = false;
                } else {
                    check= true;
                }
            }
        }
    return check;
    }
}
