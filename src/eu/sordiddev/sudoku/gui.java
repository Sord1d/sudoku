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

    private static int[][] riddle = riddles.riddle1();



    /*
    Beginn der eigentlichen GUI-Generierung
     */

    public static void window() {

        /*
        Erzeugen und kofigurieren des JFrame (1)
        Konifguriert wird ebenfalls ein KeyListener, um Usereingaben zu verarbeiten
        Der Listener konvertiert die KeyChars mit Hilfe der convertKey.java in die eingegebene Zahl.
        Diese wird gemeinsam mit anderen Infos an die printScreen.java übergeben und dort weiter verarbeitet
         */

        JFrame window = new JFrame();
        window.setTitle("Sudoku");
        window.setBackground(Color.WHITE);
        window.setSize(600,650);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        KeyListener listener = new KeyListener() {

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

                new printScreen(key, currentlabel, currentpanel,currenti, currentj, riddle);

            }
        };
        window.addKeyListener(listener);

         /*
        Erzeugen und kofigurieren des JFrame (2)
        Das Label mit dem Namen wird erstellt un konifguriert.
        Ein neues JPanel wird als hintergrund angelegt und mit einem Layout ausgestattet.
        Zwei Borders werden erstellt.
          */

        JLabel name = new JLabel();
        name.setText("Sudoku");
        name.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        name.setBackground(Color.WHITE);
        name.setHorizontalAlignment(SwingConstants.CENTER);
        window.add(name, BorderLayout.NORTH);
        JPanel background = new JPanel();
        background.setLayout(new GridLayout(3,3));
        window.add(background);

        Border borderlarge = BorderFactory.createLineBorder(Color.DARK_GRAY, 3);
        Border bordersmall = BorderFactory.createLineBorder(Color.DARK_GRAY,1);


         /*
        Erzeugen und kofigurieren des JFrame (3)
        Zeichnen des Rasters: Den Start macht eine For-Schleife, die passend zum 3*3 Layout des Hintergrundpanels 9 kleinere Panels erzeugt.
          */

        for (int i = 0; i < 9; i++){
            

            JPanel gridlarge = new JPanel();
            gridlarge.setBorder(borderlarge);
            gridlarge.setLayout(new GridLayout(3,3));

         /*
        Erzeugen und kofigurieren des JFrame (3)
        Zeichnen des Rasters (2): Nun wird das kleine Raster ebenfalls in einer For-Schleife gezeichnet.
        Jedes Panel des kleinen Rasters wird mit einem Label ausgestattet.
        Anschließend wird der entsprechende Buchstabe des Rätsels geladen und ins Label geschrieben und das Label ins globale Array eingefügt
          */

             for (int j = 0; j <9; j++) {

                 JPanel gridsmall = new JPanel();
                 gridsmall.setBorder(bordersmall);
                 gridsmall.setBackground(Color.WHITE);
                 gridsmall.setLayout(new BorderLayout());

                 JLabel text= new JLabel();
                 text.setHorizontalAlignment(SwingConstants.CENTER);
                 text.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
                 text.setForeground(Color.BLUE);
                 if ( (int) riddle[i][j] != 0 ) {

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

                         //Design
                         if (currentpanel != null) {
                             currentpanel.setBackground(Color.WHITE);
                         }
                         gridsmall.setBackground(Color.cyan);

                         //Setzt die globalen Werte auf die des aktuellen Feldes
                         currentlabel = text;
                         currentpanel = gridsmall;
                         currenti = finalI;
                         currentj = finalJ;

                         //Design - Werte werden sowohl in der printScreen.java als auch in der checkNumber.clearcolor verarbeitet.
                         if (!currentlabel.getText().isEmpty() &&currentlabel.getForeground() != Color.BLACK && currentlabel.getForeground()!= Color.red) {
                             int key = Integer.parseInt(currentlabel.getText());
                             clearcolor();
                             new printScreen(key, currentlabel, currentpanel,currenti, currentj, riddle);

                         }

                     }

                     //Mehr Design: Hover-Effekt
                     @Override
                     public void mouseReleased(MouseEvent e) {
                     }
                     @Override
                     public void mouseEntered(MouseEvent e) {
                         if (gridsmall.getBackground()==Color.WHITE) {
                             gridsmall.setBackground(Color.LIGHT_GRAY);
                         }
                     }
                     @Override
                     public void mouseExited(MouseEvent e) {
                         if (gridsmall.getBackground()== Color.LIGHT_GRAY) {
                             gridsmall.setBackground(Color.WHITE);
                         }

                     }
                 });
                 /*
                 Rest vom Raster wird zu den Panels, bzw. dem Frame hinzugefügt.
                 Die Panels werden im gobalen Array gespeichert.
                  */
                 gridlarge.add(gridsmall);
                 allpanels[i][j]=gridsmall;
             }

            background.add(gridlarge);
        }

        window.setVisible(true);
    }



}
