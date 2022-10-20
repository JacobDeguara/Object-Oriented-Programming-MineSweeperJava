import java.util.Random;
public class Board {

    private int[][] board;
    private boolean[][] showing;

    public Board(int x){ //will set up the board for the game
        //Create the board
        board = new int[x][x];
        showing = new boolean[x][x];

        //will fill the board with '0's representing (nothing), and showing with 'false' representing (dont show)
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = 0;
                showing[i][j] = false;
            }
        }
    }

    //getters
    public int[][] getBoard(){
        return board;
    }

    //getters
    public boolean[][] getShowing(){
        return showing;
    }

    //add the bombs to the board represented as '-1'
    public void addBombs(int x,int b){
        Random rand = new Random();
        int upperbound = x;

        //create random bombs
        for (int i = 0; i < b; i++) {
            int rand1 =rand.nextInt(upperbound);
            int rand2 =rand.nextInt(upperbound);

            if(board[rand1][rand2] >= 0){ // if not -1 create bomb
                board[rand1][rand2] = -1;
                incrementBoard(rand1, rand2);
            }else{ //else try again
                i--;
            }
        }
    }

    //increments all areas around the '(x,y)cord' in board excluding any bombs '-1'
    public void incrementBoard(int x,int y){
        //look around the number
        for (int i = x-1; i < x+2; i++) {
            for (int j = y-1; j < y+2; j++) {
                //check for out of bounce (-1) or (greater then x size)
                if(i >= 0 && j >= 0 && i < board.length && j < board[0].length){
                    //check for bomb
                    if(board[i][j] != -1){
                        board[i][j] += 1;
                    }
                }
            }
        }
    }

    //Prints board
    public void printBoard(){
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] != -1){
                    System.out.print(board[i][j]+ " | ");
                }else{
                    System.out.print( "* | ");
                }
            }
            System.out.print("\n");
        }
    }

    /* a more special version of printboard().
     * It uses 'showing' boolean array to determine what to show or not. (true == show)
     * Additonally it will provide a more readable board for the user*/
    public void printBoardWithShowing(){
        // print letters on top
        System.out.print("     ");
        for (int i = 0; i < board.length; i++){
            if(i<10){
                System.out.print((i)+"   ");
            }else{
                System.out.print((i)+"  ");
            }
        }
        System.out.print("\n");

        for (int i = 0; i < board.length; i++) {
            //wall
            if(i<10){
                System.out.print(" "+(i)+" | ");
            }else{
                System.out.print((i)+" | ");
            }
            for (int j = 0; j < board[0].length; j++) {
                //hidden or number
                if(showing[i][j] == true){
                    if(board[i][j] != -1){
                        System.out.print(board[i][j]);
                    }else{
                        System.out.print("*");
                    }
                }else{
                    System.out.print("-");
                }
                //wall
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }

    //the actual game part

    /* input: x,y cords (1,a)-(16,p)
     * to play minesweeper
     * returns; 1 if selected successfully, 0 if selected unsuccessfully, -1 if selected mine
     */
    public int selectOnBoard(int x,int y){

        boolean Test;
        Test = x >= 0 && y >= 0 && x < board.length && y < board[0].length; //out of bounce
        if(Test != true){
            return 0; //unsucessfull if out of bounce
        }

        if(showing[x][y] == true){
            return 0; //unsuccessfull if selected an already showing selection
        }

        if(board[x][y] == -1){
            return -1; //failer case if selected a bomb
        }

        if(board[x][y] > 0){
            showing[x][y] = true;
            return 1;
        }

        if(board[x][y] == 0){
            showing[x][y] = true;
            selectOnBoard(x+1, y); //recursive checking
            selectOnBoard(x, y+1);
            selectOnBoard(x+1, y+1);
            selectOnBoard(x-1, y);
            selectOnBoard(x, y-1);
            selectOnBoard(x-1, y-1);
            return 1;
        }

        return 0; // unknown unsuccessful case 
    }

    /* input: void
     * checks if board finished by checking if not a single false(in showing) is a number greater then 1(in board)
     * returns; 0 for failer case, 1 for success case    (considering we havent selected a bomb)
     */
    public int isItFinished(){
        boolean success =true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] != -1){ //if not bomb
                    success = showing[i][j] == true; //is showing true?
                }
                if(success == false){ //if showing is not true 
                    return 0; //failer case
                }
            }
        }
        //if whole board is considered true
        return 1; //success case
    }

}
