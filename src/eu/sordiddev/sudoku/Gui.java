package eu.sordiddev.sudoku;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static eu.sordiddev.sudoku.CheckNumber.*;

public class Gui {
//TODO Translate
    /*
    Initialisiert diverse Objekte.
    Diese dienen als Zwischenspeicher
    Rätsel werden aus der riddles.java geladen
     */

    public static JLabel currentlabel;
    public static JPanel currentpanel;
    public static JLabel[][] alllabels = new JLabel[9][9];
    public static JPanel[][] allpanels = new JPanel[9][9];

    private static int currenti;
    private static int currentj;
    public static int[][] riddle = Riddles.riddle();

    /*
    Beginn der eigentlichen GUI-Generierung
     */

    public static void window() {

        /*
        Definition der Listener:
        Der KeyListener- ist für die Usereingabe verantwortlich:
        Er konvertiert die KeyChars mit Hilfe der convertKey.java in die eingegebene Zahl, die anschließend ins Feld eingetragen wird.
         */

        KeyListener keylistener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                int keychar = (e.getKeyChar());
                int key = ConvertKey.convert(keychar);

                new PrintScreen(key, currentlabel, currenti, currentj, riddle);

            }
        };

        /*
        Erzeugen und kofigurieren des JFrame
        Label mit dem Namen wird erstellt und konifguriert.
        Ein neues JPanel wird als hintergrund angelegt und mit einem Layout ausgestattet.
        Zwei Borders werden erstellt.
          */

        JFrame window = new JFrame();
        window.setTitle("Sudoku");
        window.setBackground(Color.WHITE);
        window.setSize(600, 650);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.addKeyListener(keylistener);

        JPanel up = new JPanel();
        up.setBackground(Color.WHITE);

        JLabel name = new JLabel();
        name.setText("Sudoku");
        name.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        name.setHorizontalAlignment(SwingConstants.CENTER);

        up.add(name);
        window.add(up, BorderLayout.NORTH);

        JPanel background = new JPanel();
        background.setBackground(Color.WHITE);
        background.setLayout(new GridLayout(3, 3));

        window.add(background);

        Border borderlarge = BorderFactory.createLineBorder(Color.DARK_GRAY, 3);
        Border bordersmall = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);

         /*
        Zeichnen des Rasters: Den Start macht eine For-Schleife, die passend zum 3*3 Layout des Hintergrundpanels 9 kleinere Panels erzeugt.
          */

        for (int i = 0; i < 9; i++) {

            JPanel grid = new JPanel();
            grid.setBorder(borderlarge);
            grid.setLayout(new GridLayout(3, 3));
            grid.setBackground(Color.WHITE);

         /*
         Nun wird das kleine Raster ebenfalls in einer For-Schleife gezeichnet. Jedes Panel des kleinen Rasters wird mit einem Label ausgestattet.
        Anschließend wird der entsprechende Buchstabe des Rätsels geladen, ins Label geschrieben und das Label ins globale Array eingefügt
          */

            for (int j = 0; j < 9; j++) {

                JPanel field = new JPanel();
                field.setBorder(bordersmall);
                field.setBackground(Color.WHITE);
                field.setLayout(new BorderLayout());

                JLabel text = new JLabel();
                text.setHorizontalAlignment(SwingConstants.CENTER);
                text.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
                text.setForeground(Color.BLUE);

                if (riddle[i][j] != 0) {

                    text.setForeground(Color.BLACK);
                    text.setText(String.valueOf(riddle[i][j]));
                }

                alllabels[i][j] = text;
                field.add(text, BorderLayout.CENTER);

                  /*
                  Mouse Listener im kleinen Raster:
                  Verantwortlich für Hover-Effekte und Auswahl des Feldes
                  */

                int finalI = i;
                int finalJ = j;

                field.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                        /*
                        Ablauf für die Feldauswahl:
                        Falls vorhanden wird das vorherige Feld auf weiß gefärbt und das aktuelle  cyan.
                        Anschließend wird das neu ausgewählte Feld und dessen Werte in die globalen Variablen geschrieben.
                        Zuletzt wird die überprüfung der Zahl mithilfe der printScreen durchgeführt.
                         */

                        if (currentpanel != null) {
                            currentpanel.setBackground(Color.WHITE);
                        }

                        field.setBackground(Color.cyan);

                        currentlabel = text;
                        currentpanel = field;
                        currenti = finalI;
                        currentj = finalJ;

                        if (!currentlabel.getText().isEmpty() && currentlabel.getForeground() != Color.BLACK) {
                            int key = Integer.parseInt(currentlabel.getText());
                            clearColor();
                            new PrintScreen(key, currentlabel, currenti, currentj, riddle);

                        }

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    /*
                    Hier folgt die Hover-Animation
                     */

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (field.getBackground() == Color.WHITE) {
                            field.setBackground(Color.LIGHT_GRAY);
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (field.getBackground() == Color.LIGHT_GRAY) {
                            field.setBackground(Color.WHITE);
                        }

                    }
                });

                 /*
                 Rest vom Raster wird zu den Panels, bzw. dem Frame hinzugefügt.
                 Die Panels werden im gobalen Array gespeichert.
                  */

                grid.add(field);
                allpanels[i][j] = field;

            }

            background.add(grid);
        }

        /*
        Button für ein neues Sudoku.
        Holt sich ein rätsel aus der riddles.java und printet es ins Feld.
         */

        JPanel newriddle = new JPanel();
        newriddle.setBackground(Color.WHITE);

        JLabel newtext = new JLabel("New Sudoku");
        newtext.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));

        MouseListener newlistener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                riddle = Riddles.riddle();
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (riddle[i][j] != 0) {
                            alllabels[i][j].setText(String.valueOf(riddle[i][j]));
                            alllabels[i][j].setForeground(Color.BLACK);
                        } else {
                            alllabels[i][j].setText("");
                            alllabels[i][j].setForeground(Color.BLUE);
                        }
                        allpanels[i][j].setBackground(Color.WHITE);
                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                newtext.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newtext.setForeground(Color.BLACK);
            }
        };

        newriddle.add(newtext);
        newriddle.addMouseListener(newlistener);

        window.add(newriddle, BorderLayout.SOUTH);
        window.setVisible(true);
    }


}
