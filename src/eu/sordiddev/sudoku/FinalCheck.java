package eu.sordiddev.sudoku;

import javax.swing.*;

public class FinalCheck {

    public static void check(){
        int sum;

        /*
        Abbruchbedingung:
        Wenn ein Feld 0 ist, wird die Methode nicht weiter ausgeführt
         */

        for (int i = 0; i< 9; i++){
            for (int j = 0; j<9; j++){
                if (Gui.riddle[i][j] == 0){
                    return;
                }
            }
        }

        /*
        Rechnet die Prüfsumme der Kästchen aus:
        Da in jedem Kästchen jede Zahl von 1-9 vorhanden sein muss, ist die Prüfsumme 45
        Wenn die Prüfsumme für jedes Kästchen 45 ist, wird der Gewinn-Dialog ausgegeben
         */

        sum = 0;
        for (int i = 0; i< 9; i++){
            sum = sum + Gui.riddle[0][i];
        }
        if ( sum != 45){
            JOptionPane.showMessageDialog(null,"Deine Lösung ist falsch, probiere es doch noch einmal");
            return;
        }

        sum = 0;
        for (int i = 0; i< 9; i++){
            sum = sum + Gui.riddle[1][i];
        }
        if ( sum != 45){
            JOptionPane.showMessageDialog(null,"Deine Lösung ist falsch, probiere es doch noch einmal");
            return;
        }

        sum = 0;
        for (int i = 0; i< 9; i++){
            sum = sum + Gui.riddle[2][i];
        }
        if ( sum != 45){
            JOptionPane.showMessageDialog(null,"Deine Lösung ist falsch, probiere es doch noch einmal");
            return;
        }

        sum = 0;
        for (int i = 0; i< 9; i++){
            sum = sum + Gui.riddle[3][i];
        }
        if ( sum != 45){
            JOptionPane.showMessageDialog(null,"Deine Lösung ist falsch, probiere es doch noch einmal");
            return;
        }

        sum = 0;
        for (int i = 0; i< 9; i++){
            sum = sum + Gui.riddle[4][i];
        }
        if ( sum != 45){
            JOptionPane.showMessageDialog(null,"Deine Lösung ist falsch, probiere es doch noch einmal");
            return;
        }

        sum = 0;
        for (int i = 0; i< 9; i++){
            sum = sum + Gui.riddle[5][i];
        }
        if ( sum != 45){
            JOptionPane.showMessageDialog(null,"Deine Lösung ist falsch, probiere es doch noch einmal");
            return;
        }

        sum = 0;
        for (int i = 0; i< 9; i++){
            sum = sum + Gui.riddle[6][i];
        }
        if ( sum != 45){
            JOptionPane.showMessageDialog(null,"Deine Lösung ist falsch, probiere es doch noch einmal");
            return;
        }

        sum = 0;
        for (int i = 0; i< 9; i++){
            sum = sum + Gui.riddle[7][i];
        }
        if ( sum != 45){
            JOptionPane.showMessageDialog(null,"Deine Lösung ist falsch, probiere es doch noch einmal");
            return;
        }

        sum = 0;
        for (int i = 0; i< 9; i++){
            sum = sum + Gui.riddle[8][i];
        }
        if ( sum != 45){
            JOptionPane.showMessageDialog(null,"Deine Lösung ist falsch, probiere es doch noch einmal");
            return;
        }

       JOptionPane.showMessageDialog(null,"Herzlichen Glückwunsch, du hast das Sudoku erfolgreich gelöst");


    }

}
