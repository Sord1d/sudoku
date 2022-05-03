package eu.sordiddev.sudoku;

public class convertKey {

    public static int convert(int keychar){

        //konvertiert die keychars in zahlen
        int converted = 0;

        if (keychar==49){
            converted = 1;
        } else if (keychar==50){
            converted = 2;
        } else if (keychar==51){
            converted = 3;
        } else if (keychar==52){
            converted = 4;
        }  else if (keychar==53){
            converted = 5;
        } else if (keychar==54){
            converted = 6;
        } else if (keychar==55){
            converted = 7;
        } else if (keychar==56){
            converted = 8;
        } else if (keychar==57){
            converted = 9;
        } else if (keychar==8){
            converted = -1;
        }

        return converted;

    }
}
