package eu.sordiddev.sudoku;

import java.awt.*;

//Überprüft, ob die zahl vorhanden ist
public class checkNumber {


    private static boolean check = true;
    public static int times = 0;
    public static int timescombined =0;


    //Farbe für differenzierung
    public static Color BRED = new Color(70, 78, 255, 255);

    

    public static void clearcolor(){

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (gui.alllabels[i][j].getForeground() == Color.CYAN) {
                    gui.alllabels[i][j].setForeground(Color.BLUE);
                    gui.allpanels[i][j].setBackground(Color.WHITE);
                } else if(gui.alllabels[i][j].getForeground() == Color.ORANGE) {
                    gui.alllabels[i][j].setForeground(Color.BLACK);
                    gui.allpanels[i][j].setBackground(Color.WHITE);
                }


            }
        }
    }

    public static boolean check(int[][] riddle, int currenti, int currentj, int key) {

        //wenn es orange ist wieder blau machen
        //bzw schwarz wenn es rot ist



        //im kästchen
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


        //Zeilenprüfung

        //obere 3

        if (currenti == 0 || currenti == 1 || currenti == 2) {

            if (currentj <= 2) {
            times = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        paint(i, j, key, riddle);

                    }
                }

            } else if (currentj > 2 && currentj <= 5) {
                times = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 3; j < 6; j++) {

                        paint(i, j, key, riddle);

                    }
                }
            }else if (currentj > 5) {
                times = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 6; j < 9; j++) {

                        paint(i, j, key, riddle);

                    }
                }
            }else{
                check = true;
            }
        }

        //mittlere 3 Zeilen
        else if (currenti == 3 || currenti == 4 || currenti == 5) {

            if (currentj <= 2) {

                for (int i = 3; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        paint(i, j, key, riddle);
                    }
                }

            }else if (currentj > 2 && currentj <= 5) {

                for (int i = 3; i < 6; i++) {
                    for (int j = 3; j < 6; j++) {
                        paint(i, j, key, riddle);
                    }
                }
            } else if (currentj > 5) {

                for (int i = 3; i < 6; i++) {
                    for (int j = 6; j < 9; j++) {
                        paint(i, j, key, riddle);
                    }
                }
            } else {
                check = true;
            }
        }

        //letzte 3 Zeilen
        else if (currenti == 6 || currenti == 7 || currenti == 8) {

            if (currentj <= 2) {

                for (int i = 6; i < 9; i++) {
                    for (int j = 0; j < 3; j++) {
                        paint(i, j, key, riddle);

                    }
                }

            } else if (currentj > 2 && currentj <= 5) {

                for (int i = 6; i < 9; i++) {
                    for (int j = 3; j < 6; j++) {
                        paint(i, j, key, riddle);

                    }
                }
            } else if (currentj > 5) {

                for (int i = 6; i < 9; i++) {
                    for (int j = 6; j < 9; j++) {
                        paint(i, j, key, riddle);

                    }
                }
            } else {
                check = true;
            }
        }

        //Spaltencheck


//linke 3

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

        //mittlere drei
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

        //rechte drei
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

        //todo in der Zeile

        //todo in der spalte

return check;
    }






    private static void paint(int i, int j, int key, int[][] riddle) {
        //blaue kästchen umfärben llerzeichen aussortieren -> -1
        if (gui.alllabels[i][j].getForeground() == Color.BLUE && riddle[i][j] == key && key != -1) {
            gui.alllabels[i][j].setForeground(Color.CYAN);

            gui.allpanels[i][j].setBackground(BRED);
            gui.currentlabel.setForeground(Color.BLUE);
            gui.currentpanel.setBackground(Color.CYAN);

            timescombined = times;

        }else if (gui.alllabels[i][j].getForeground() == Color.CYAN && riddle[i][j] == key) {
            gui.allpanels[i][j].setBackground(BRED);


        }
        //Schwarze kästchen umfärben
        if (gui.alllabels[i][j].getForeground() == Color.BLACK && riddle[i][j] == key) {
            gui.alllabels[i][j].setForeground(Color.ORANGE);
            gui.allpanels[i][j].setBackground(BRED);
        }

    }
}


