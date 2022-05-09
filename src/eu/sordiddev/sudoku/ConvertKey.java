package eu.sordiddev.sudoku;

public class ConvertKey {

    /*
    Diese Klasse konvertiert die KeyChars aus der Usereingabe in die zugehörigen Zahlen.
    Ein Backspace wird mit -1 angegeben.
    Nicht erwünschte Eingaben werden mit 0 zurückgegeben und somit in der printcreen ignoniert
     */

    public static int convert(int keychar){

        return switch (keychar){
            case 8 -> -1;
            case 49 -> 1;
            case 50 -> 2;
            case 51 -> 3;
            case 52 -> 4;
            case 53 -> 5;
            case 54 -> 6;
            case 55 -> 7;
            case 56 -> 8;
            case 57 -> 9;
            default -> 0;
        };
    }
}
