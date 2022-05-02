package eu.sordiddev.sudoku;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class gui {

    public static void window() {

        //JFrame als GUI generieren

        JFrame window = new JFrame();
        window.setSize(600,600);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //hintergrund
        JPanel background = new JPanel();
        background.setBackground(Color.BLACK);
        background.setLayout(new GridLayout(3,3));
        window.add(background);


        //border definieren
        Border borderlarge = BorderFactory.createLineBorder(Color.DARK_GRAY, 3);
        Border bordersmall = BorderFactory.createLineBorder(Color.DARK_GRAY,1);

        //spielname anzeigen


        //raster zeichnen
        //großes raster
        for (int i = 0; i < 9; i++){

            JPanel gridlage = new JPanel();
            gridlage.setBorder(borderlarge);
            gridlage.setLayout(new GridLayout(3,3));

            //kleines raster
             for (int j = 0; j <9; j++) {

                 JPanel gridsmall = new JPanel();
                 gridsmall.setBorder(bordersmall);
                 gridsmall.setBackground(Color.WHITE);
                 gridlage.add(gridsmall);
             }


            background.add(gridlage);
        }







        window.setVisible(true);
    }



}
