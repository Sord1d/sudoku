package eu.sordiddev.sudoku;

import java.awt.*;


public class CheckNumber {


    /*
    Weil das normale Blau keinen guten Farbton hat
     */

    public static Color NOTBLUE = new Color(70, 78, 255, 255);

    

    /*
    Die Funktion clearcolor ist für den Reset der Farben nach der Überprüfung verantwortlich:
    Mithilfe einer For-Schleife werden alle Felder geprüft.
    Ist eines eingefärbt, werden das Feld und das zugehörige Panel, je nach Art des Feldes zurückgesetzt:
    Cyan / Blau -> Dynamische Zahlen
    Schwarz / Orange -> Vorgegebene Zahlen
     */

    public static void clearColor(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Gui.alllabels[i][j].getForeground() == Color.CYAN) {
                    Gui.alllabels[i][j].setForeground(Color.BLUE);
                    Gui.allpanels[i][j].setBackground(Color.WHITE);
                } else if(Gui.alllabels[i][j].getForeground() == Color.ORANGE) {
                    Gui.alllabels[i][j].setForeground(Color.BLACK);
                    Gui.allpanels[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    public static void check(int[][] riddle, int currenti, int currentj, int key) {

        /*
        Überprüfung auf Konflikt im Kästchen:
        Hierzu wird die Häufigkeit der eingegeben Zahl geprüft im aktuellen rätsel-array.
        Kommt sie öfter als ein Mal vor, wird das Feld an die paint-funktion übergeben.
         */

        int keys = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (riddle[currenti][j] == key) {
                    keys++;
                    if (keys > 1) {
                        paint(currenti, j, key, riddle);
                    }
                }

            }
        }

        /*
        Zeilenprüfung (0-3)
        Gleicht hierzu Labels von vordefinierten Koordinaten mit der Eingabe ab
        Beginnt mit den ersten Zeilen 0-3. Geht anschließend in die zur Zeile gehörnden Spalten
         */

        if (currenti == 0 || currenti == 1 || currenti == 2) {

            if (currentj <= 2) {

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        paint(i, j, key, riddle);

                    }
                }

            } else if ( currentj <= 5) {

                for (int i = 0; i < 3; i++) {
                    for (int j = 3; j < 6; j++) {

                        paint(i, j, key, riddle);
                    }
                }

            } else {

                for (int i = 0; i < 3; i++) {
                    for (int j = 6; j < 9; j++) {

                        paint(i, j, key, riddle);

                    }
                }
            }


        }

        /*
        Zeilenprüfung (3-5)
         */

        else if (currenti == 3 || currenti == 4 || currenti == 5) {

            if (currentj <= 2) {

                for (int i = 3; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        paint(i, j, key, riddle);
                    }
                }

            }else if ( currentj <= 5) {

                for (int i = 3; i < 6; i++) {
                    for (int j = 3; j < 6; j++) {
                        paint(i, j, key, riddle);
                    }
                }

            } else {

                for (int i = 3; i < 6; i++) {
                    for (int j = 6; j < 9; j++) {
                        paint(i, j, key, riddle);
                    }
                }

            }

        }

        /*
        Zeilenprüfung (6-8)
        */

        else if (currenti == 6 || currenti == 7 || currenti == 8) {

            if (currentj <= 2) {

                for (int i = 6; i < 9; i++) {
                    for (int j = 0; j < 3; j++) {
                        paint(i, j, key, riddle);

                    }
                }

            } else if (currentj <= 5) {

                for (int i = 6; i < 9; i++) {
                    for (int j = 3; j < 6; j++) {
                        paint(i, j, key, riddle);

                    }
                }
            } else {

                for (int i = 6; i < 9; i++) {
                    for (int j = 6; j < 9; j++) {
                        paint(i, j, key, riddle);

                    }
                }
            }


        }


         /*
        Spaltenprüfung (0,3,6)
        Gleicht hierzu Labels von vordefinierten Koordinaten von Spalten / zeilen des Arrays mit der Eingabe ab
         */

        if (currenti == 0 || currenti == 3 || currenti == 6) {

            if (currentj == 0 || currentj == 3 || currentj == 6) {

                for (int i = 0; i <= 6; i = i+3) {
                    for (int j = 0; j <= 6; j = j+3) {
                        paint(i, j, key, riddle);

                    }
                }

            }

            if (currentj == 1 || currentj == 4 || currentj == 7) {

                for (int i = 0; i <= 6; i = i+3) {
                    for (int j = 1; j <= 7; j = j+3) {
                        paint(i, j, key, riddle);

                    }
                }

            }

            if (currentj == 2 || currentj == 5 || currentj == 8) {

                for (int i = 0; i <= 6; i = i+3) {
                    for (int j = 2; j <= 8; j = j+3) {
                        paint(i, j, key, riddle);

                    }
                }

            }
        }

         /*
        Spaltenprüfung (1,4,7)
        Gleicht hierzu Labels von vordefinierten Koordinaten von Spalten / zeilen des Arrays mit der Eingabe ab
         */
        if (currenti == 1 || currenti == 4 || currenti == 7) {

            if (currentj == 0 || currentj == 3 || currentj == 6) {

                for (int i = 1; i <= 7; i = i+3) {
                    for (int j = 0; j <= 6; j = j+3) {
                        paint(i, j, key, riddle);
                    }
                }

            }

            if (currentj == 1 || currentj == 4 || currentj == 7) {

                for (int i = 1; i <= 7; i = i+3) {
                    for (int j = 1; j <= 7; j = j+3) {
                        paint(i, j, key, riddle);

                    }
                }

            }

            if (currentj == 2 || currentj == 5 || currentj == 8) {

                for (int i = 1; i <= 7; i = i+3) {
                    for (int j = 2; j <= 8; j = j+3) {
                        paint(i, j, key, riddle);

                    }
                }

            }
        }

         /*
        Spaltenprüfung (2,8,8)
        Gleicht hierzu Labels von vordefinierten Koordinaten von Spalten / zeilen des Arrays mit der Eingabe ab
         */
        if (currenti == 2 || currenti == 5 || currenti == 8) {

            if (currentj == 0 || currentj == 3 || currentj == 6) {

                for (int i = 2; i <= 8; i = i+3) {
                    for (int j = 0; j <= 6; j = j+3) {
                        paint(i, j, key, riddle);

                    }
                }

            }

            if (currentj == 1 || currentj == 4 || currentj == 7) {

                for (int i = 2; i <= 8; i = i+3) {
                    for (int j = 1; j <= 7; j = j+3) {
                        paint(i, j, key, riddle);
                    }
                }

            }

            if (currentj == 2 || currentj == 5 || currentj == 8) {

                for (int i = 2; i <= 8; i = i+3) {
                    for (int j = 2; j <= 8; j = j+3) {
                        paint(i, j, key, riddle);
                    }
                }

            }
        }

    }





    /*
   Design der Prüfung:
   Das übermittelte Label wird, falls die Schriftfarbe blau ist, cyan/Blau (NotBlue) eingefärbt.
   Das aktuelle Feld bekommt einen cyan hintergrund.
   Anschließend werden die schwarzen Felder in Orange / Blau eingefärbt
    */

    private static void paint(int i, int j, int key, int[][] riddle) {

        if (Gui.alllabels[i][j].getForeground() == Color.BLUE && riddle[i][j] == key && key != -1) {

            Gui.alllabels[i][j].setForeground(Color.CYAN);
            Gui.allpanels[i][j].setBackground(NOTBLUE);
            Gui.currentlabel.setForeground(Color.BLUE);
            Gui.currentpanel.setBackground(Color.CYAN);


        } else if (Gui.alllabels[i][j].getForeground() == Color.BLACK && riddle[i][j] == key) {

            Gui.alllabels[i][j].setForeground(Color.ORANGE);
            Gui.allpanels[i][j].setBackground(NOTBLUE);

        }

    }
}


