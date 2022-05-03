package eu.sordiddev.sudoku;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

import static eu.sordiddev.sudoku.checkNumber.check;

public class gui {

    //Initialisierung der beiden ojecte currentframe und currentlabel - sie repäsentieren das jeweils ausgewählte und den dazugehörogen text
    public static JLabel currentlabel;
    private static JPanel currentpanel;
    public static JLabel[][] alllabels = new JLabel[9][9];

    //initialisierung von curreni und currentj als zwischenspeicher
    private static int currenti;
    private static int currentj;

    //initialisierung von rätsel, lösung und lösungsmatrix
    private static int[][] riddle = riddles.riddle1();
    private static int[][] matrix = riddles.riddle1matrix();




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

                //tastatureingabe abfangen und konvertieren
                int keychar = (e.getKeyChar());
                int key = convertKey.convert(keychar);

                if (currentlabel.getForeground() != Color.BLACK && currentlabel.getForeground()!= Color.red) {
                    if (key != 0) {


                        currentlabel.setText(String.valueOf(key));
                        riddle[currenti][currentj] = key;

                        if (key == -1) {
                            currentlabel.setText("");
                        }
                        //überprüfung ob die Zahl mehr als ein Mal vorhanden ist

                        boolean check = checkNumber.check(riddle, currenti, currentj, key);

                        if (!check) {
                            currentlabel.setForeground(Color.ORANGE);
                            System.out.println(check);
                        } else {
                            currentlabel.setForeground(Color.BLUE);
                            System.out.println(check);
                        }


                    }
                }


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

        //schaltfläche fürs überprüfen - als jpanel, weils schöner aussieht

        JPanel check = new JPanel();
        check.setBackground(Color.WHITE);

        JLabel checktext = new JLabel("Überprüfen");
        checktext.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));

        MouseListener checklistener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            //TODO Überprüfung der Hilfsmatrix
                int check = 0;
                for (int i = 0; i<9; i++){
                    for (int j = 0; j<9; j++ ){
                        if (matrix[i][j] == 1){
                            check++;
                            System.out.println(check);
                        }
                    }
                }

                if (check <=81 ){
                    JOptionPane.showMessageDialog(null,"Schade, das ist leider nicht ganz richtig. Probiere es doch noch ein Mal");
                } else {
                    JOptionPane.showMessageDialog(null, "Herzlichen Glückwunsch, du hast das Sudoku gelöst. Deine Zeit ist:"); //TODO Zeit einfügen
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
            checktext.setForeground(Color.BLUE);

            }

            @Override
            public void mouseExited(MouseEvent e) {
            checktext.setForeground(Color.BLACK);
            }
        };


        check.add (checktext);
        check.addMouseListener(checklistener);

        window.add(check,BorderLayout.SOUTH);




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
                 text.setForeground(Color.BLUE);
                 if ( (int) riddle[i][j] != 0 ) {

                     text.setForeground(Color.BLACK);
                     text.setText(String.valueOf(riddle[i][j]));
                 }
                 //zum globalen speicher hinzufügen

                 System.out.println(i + " " + j);
                 alllabels[i][j] = text;
                 gridsmall.add(text, BorderLayout.CENTER);



                 //mouse listener für hover-effekte und auswahl
                 int finalI = i;
                 int finalJ = j;
                 gridsmall.addMouseListener(new MouseListener() {
                     @Override
                     public void mouseClicked(MouseEvent e) {


                     }

                     @Override
                     public void mousePressed(MouseEvent e) {

                         //färbt das vormals ausgewählte Feld weiß und das aktuelle blau


                         if (currentpanel != null) {
                             currentpanel.setBackground(Color.WHITE);

                             //TODO: timer starten
                         }

                         gridsmall.setBackground(Color.cyan);
                         //setzt die globalen objekte auf die des aktuellen feldes
                         currentlabel = text;
                         currentpanel = gridsmall;
                         currenti = finalI;
                         currentj = finalJ;

                         if (!currentlabel.getText().isEmpty() &&currentlabel.getForeground() != Color.BLACK && currentlabel.getForeground()!= Color.red) {
                             int key = Integer.parseInt(currentlabel.getText());
                             boolean check = checkNumber.check(riddle, currenti, currentj, key);
                             if (!check) {
                                 currentlabel.setForeground(Color.ORANGE);
                             } else {
                                 currentlabel.setForeground(Color.BLUE);
                             }
                         }else{
                             //wenn es orange ist wieder blau machen
                             for (int i=0; i<9; i++) {
                                for (int j = 0; j<9; j++){
                                    if (gui.alllabels[i][j].getForeground() == Color.ORANGE) {
                                        gui.alllabels[i][j].setForeground(Color.BLUE);
                                    } else if(gui.alllabels[i][j].getForeground() == Color.RED) {
                                        gui.alllabels[i][j].setForeground(Color.BLACK);
                                    }

                             }

                             }
                         }
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
