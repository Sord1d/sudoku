package eu.sordiddev.sudoku;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static eu.sordiddev.sudoku.checkNumber.*;

public class gui {

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
    private static int[][] riddle = riddles.riddle();

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
                int key = convertKey.convert(keychar);

                new printScreen(key, currentlabel, currentpanel, currenti, currentj, riddle);

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

        JLabel name = new JLabel();
        name.setText("Sudoku");
        name.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        name.setBackground(Color.WHITE);
        name.setHorizontalAlignment(SwingConstants.CENTER);

        window.add(name, BorderLayout.NORTH);

        JPanel background = new JPanel();
        background.setLayout(new GridLayout(3, 3));

        window.add(background);

        Border borderlarge = BorderFactory.createLineBorder(Color.DARK_GRAY, 3);
        Border bordersmall = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);

         /*
        Zeichnen des Rasters: Den Start macht eine For-Schleife, die passend zum 3*3 Layout des Hintergrundpanels 9 kleinere Panels erzeugt.
          */

        for (int i = 0; i < 9; i++) {

            JPanel gridlarge = new JPanel();
            gridlarge.setBorder(borderlarge);
            gridlarge.setLayout(new GridLayout(3, 3));

         /*
         Nun wird das kleine Raster ebenfalls in einer For-Schleife gezeichnet. Jedes Panel des kleinen Rasters wird mit einem Label ausgestattet.
        Anschließend wird der entsprechende Buchstabe des Rätsels geladen, ins Label geschrieben und das Label ins globale Array eingefügt
          */

            for (int j = 0; j < 9; j++) {

                JPanel gridsmall = new JPanel();
                gridsmall.setBorder(bordersmall);
                gridsmall.setBackground(Color.WHITE);
                gridsmall.setLayout(new BorderLayout());

                JLabel text = new JLabel();
                text.setHorizontalAlignment(SwingConstants.CENTER);
                text.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
                text.setForeground(Color.BLUE);

                if ((int) riddle[i][j] != 0) {

                    text.setForeground(Color.BLACK);
                    text.setText(String.valueOf(riddle[i][j]));
                }

                alllabels[i][j] = text;
                gridsmall.add(text, BorderLayout.CENTER);

                  /*
                  Mouse Listener im kleinen Raster:
                  Verantwortlich für Hover-Effekte und Auswahl des Feldes
                  */

                int finalI = i;
                int finalJ = j;

                gridsmall.addMouseListener(new MouseListener() {
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

                        gridsmall.setBackground(Color.cyan);

                        currentlabel = text;
                        currentpanel = gridsmall;
                        currenti = finalI;
                        currentj = finalJ;

                        if (!currentlabel.getText().isEmpty() && currentlabel.getForeground() != Color.BLACK && currentlabel.getForeground() != Color.red) {
                            int key = Integer.parseInt(currentlabel.getText());
                            clearcolor();
                            new printScreen(key, currentlabel, currentpanel, currenti, currentj, riddle);

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
                        if (gridsmall.getBackground() == Color.WHITE) {
                            gridsmall.setBackground(Color.LIGHT_GRAY);
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (gridsmall.getBackground() == Color.LIGHT_GRAY) {
                            gridsmall.setBackground(Color.WHITE);
                        }

                    }
                });

                 /*
                 Rest vom Raster wird zu den Panels, bzw. dem Frame hinzugefügt.
                 Die Panels werden im gobalen Array gespeichert.
                  */

                gridlarge.add(gridsmall);
                allpanels[i][j] = gridsmall;

            }

            background.add(gridlarge);
        }

        /*
        Button für ein neues Sudoku.
        Holt sich ein rätsel aus der riddles.java und printet es ins Feld.
         */

        JPanel newriddle = new JPanel();
        newriddle.setBackground(Color.WHITE);

        JLabel newtext = new JLabel("Neues Sudoku");
        newtext.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));

        MouseListener newlistener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                riddle = riddles.riddle();
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
