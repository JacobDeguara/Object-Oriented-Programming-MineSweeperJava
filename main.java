import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        int result = 0;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        Board x = new Board(16); //create Board object
        x.addBombs(16, 40); //add the bombs and numbers
        while(result == 0){
            x.printBoardWithShowing(); 
            System.out.println("Enter 2 Numbers (For (x,y) Ex | x y | )");

            int num1 = myObj.nextInt();
            int num2 = myObj.nextInt();
            result = x.selectOnBoard(num1, num2); //select the input; if 1(successful), 0(repeat), -1(failer)
            if(result == 1){ //if 1(successful) check if finished
                result = x.isItFinished();//check if Finish; if 0(not finish so repeat), 1(success finish)
            }
        }
        if(result == -1){
            x.printBoard();
            System.out.println("You Died");
        }else{
            x.printBoard();
            System.out.println("You Did it, you saved America.");
        }
        }
    }