import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int num1 = 0;
        int num2 = 0;
        int result = 0;
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        Board x = new Board(16); //create Board object
        x.addBombs(16, 40); //add the bombs and numbers
        while(result == 0){
            x.printBoardWithShowing(); 
            System.out.println("Enter 2 Numbers (For (x,y) Ex | x y | )");

            boolean repeat;
            do{
                try{
                    repeat = false;
                    num1 = scanner.nextInt();
                    num2 = scanner.nextInt();
                } catch(Exception e){
                    scanner.next();
                    repeat = true;
                    System.out.println("You entered the wrong inputs");
                }

                if(repeat == false){ //this is to avoid any colition if the exeption is detected
                    if(num1 < 0 || num1 > 16){
                        repeat = true;
                        System.out.println("You entered number x beyond the borders");
                    }
                    if(num2 < 0 || num2 > 16){
                        repeat = true;
                        System.out.println("You entered number y beyond the borders");
                    }
                }

            }while(repeat == true); // is only true

            result = x.selectOnBoard(num2, num1); //select the input; if 1(successful), 0(repeat), -1(failer)
            if(result == 1){ //if 1(successful) check if finished
                result = x.isItFinished();//check if Finish; if 0(not finish so repeat), 1(success finish)
            }else if(result == 0){
                System.out.println("You entered numbers already shown (board will be re-printed)");
                result = x.isItFinished();
            }
        }
        if(result == -1){
            x.printBoard();
            System.out.println("You Died");
        }else{
            x.printBoard();
            System.out.println("You Did it, you saved America.");
        }

        scanner.close();
        }
    }
