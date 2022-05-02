package eu.sordiddev.sudoku;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class gui {

    //Initialissierung der beiden ojecte currentframe und currentlabel - sie repäsentieren das jeweils ausgewählte und den dazugehörogen text
    private static JLabel currentlabel;
    private static JPanel currentpanel;




    public static void window() {

        //JFrame als GUI generieren

        JFrame window = new JFrame();




        window.setTitle("Sudoku");
        window.setBackground(Color.WHITE);
        window.setSize(600,650);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        //listener für die usereingabe
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String key = String.valueOf(e.getKeyChar());

                currentpanel.setBackground(Color.cyan);
                currentlabel.setText(key);

                return;
            }
        };
        window.addKeyListener(listener);

        //spielname anzeigen

        JLabel name = new JLabel();
        name.setText("Sudoku");
        name.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        name.setBackground(Color.WHITE);
        name.setHorizontalAlignment(SwingConstants.CENTER);
        window.add(name, BorderLayout.NORTH);

        //hintergrund
        JPanel background = new JPanel();
        background.setLayout(new GridLayout(3,3));
        window.add(background);



        //border definieren
        Border borderlarge = BorderFactory.createLineBorder(Color.DARK_GRAY, 3);
        Border bordersmall = BorderFactory.createLineBorder(Color.DARK_GRAY,1);


        //raster zeichnen
        //großes raster
        for (int i = 0; i < 9; i++){

            JPanel gridlarge = new JPanel();
            gridlarge.setBorder(borderlarge);
            gridlarge.setLayout(new GridLayout(3,3));

            //kleines raster
             for (int j = 0; j <9; j++) {

                 JPanel gridsmall = new JPanel();
                 gridsmall.setBorder(bordersmall);
                 gridsmall.setBackground(Color.WHITE);
                 gridsmall.setLayout(new BorderLayout());

                 //text im label
                 JLabel text= new JLabel();
                 text.setHorizontalAlignment(SwingConstants.CENTER);
                 text.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
                 int [][] riddle = riddles.riddles();
                 text.setName(String.valueOf(riddle[i][j]));
                 text.setText(text.getName());
                 gridsmall.add(text, BorderLayout.CENTER);

                 //mouse listener für hover-effekte und auswahl
                 gridsmall.addMouseListener(new MouseListener() {
                     @Override
                     public void mouseClicked(MouseEvent e) {

                         //färbt das vormals ausgewählte Feld weiß und das aktuelle blau

                         try {
                             currentpanel.setBackground(Color.WHITE);
                         } catch (Exception exception){
                             //damit kein nullpointer das spiel zerlegt, wenn noch kein Feld ausgewählt war
                         }

                        gridsmall.setBackground(Color.cyan);
                        //setzt die globalen objekte auf die des aktuellen feldes
                         currentlabel = text;
                         currentpanel = gridsmall;
                     }

                     @Override
                     public void mousePressed(MouseEvent e) {


                     }

                     @Override
                     public void mouseReleased(MouseEvent e) {


                     }

                     @Override
                     public void mouseEntered(MouseEvent e) {
                         //setzt den hintergrund auf grau, wenn er weiß ist
                         if (gridsmall.getBackground()==Color.WHITE) {
                             gridsmall.setBackground(Color.LIGHT_GRAY);
                         }

                     }

                     @Override
                     public void mouseExited(MouseEvent e) {

                         //setzt den hintergrund auf weiß, wenn er hellgrau ist (hover effekt)
                         if (gridsmall.getBackground()== Color.LIGHT_GRAY) {
                             gridsmall.setBackground(Color.WHITE);
                         }

                     }
                 });


                 gridlarge.add(gridsmall);
             }


            background.add(gridlarge);
        }







        window.setVisible(true);
    }



}
