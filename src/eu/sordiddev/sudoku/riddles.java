package eu.sordiddev.sudoku;

public class riddles {


    public static int[][] riddle1() {

        //rätsel 1 - jede zeile ist ein kästchen
        int[][] riddle1 = {
                {4,1,0,0,0,6,2,0,7},
                {0,6,5,0,0,7,4,9,0},
                {0,0,7,4,8,0,0,0,6},
                {0,6,0,3,0,1,0,9,0},
                {0,7,0,5,0,0,0,4,2},
                {1,0,0,0,7,2,3,0,8},
                {1,0,8,0,2,0,6,0,0},
                {6,0,0,0,1,8,3,0,0},
                {0,2,9,6,4,0,0,1,0}
        };

        return riddle1;
    }

    public static int[][] riddle1solution() {

        //rätsel 1 - jede zeile ist ein kästchen
        int[][] riddle1solution = {
                {4,1,3,9,5,6,2,8,7},
                {8,6,5,2,3,7,4,9,1},
                {2,9,7,4,8,1,5,3,6},
                {8,6,2,3,4,1,7,9,5},
                {9,7,3,5,8,6,1,4,2},
                {1,5,4,9,7,2,3,6,8},
                {1,3,8,5,2,9,6,7,4},
                {6,5,4,7,1,8,3,2,9},
                {7,2,9,6,4,3,8,1,5}
        };

        return riddle1solution;
    }
}
