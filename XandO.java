import java.util.*;

class PlayGame {
    private static Scanner in = new Scanner(System.in);
    public static void main (String args[]) {
        XandO_Game xg = new XandO_Game();
        startGame(xg);
    }


    // TODO: Move this into api class better better encapsulation.
    // TODO: Break below function into more smaller logical components for simplicity
    // TODO: Currently it is single time play per run, and can be played on single machine. Change that in future.
    private static void startGame(XandO_Game xg) {
        System.out.println("Let the Game begin!");
        System.out.println("Sample valid inputs are :-");
        System.out.println("2 2 - to place a X/O on 2nd row, 2nd column of table");
        System.out.println("1 2 - to place a X/O on 1st row, 2nd column of table");
        System.out.println("0 1 - Invalid Input as there is no 0th row");

        xg.printBoard();
        int player = 0;
        char player1Char = 'X';
        char player2Char = 'O';
        char move = 0;
        System.out.println("Player 1 starts the game, Player 1 enter \n1) to choose X,\n2) for O");
        int select = in.nextInt();

        // TODO: Give the user another option to enter in case he/she wants to exit the game at any stage
        do {
            if (select == 1 || select == 2) {
                if (select == 2) {
                    player1Char = 'O';
                    player2Char = 'X';
                }
                break;
            }
            else {
                System.out.println ("Invalid input!\nEnter \"1\" for X, \"2\" for O");
            }
        } while (true);

        do {
            player = (player == 1) ? 2 : 1;
            move = (move == player1Char) ? player2Char : player1Char;
            
            System.out.println("Player "+player+" turn to play");
            
            int row = in.nextInt();
            int col = in.nextInt();

            int rc = xg.playerMove(player, row, col, move);
            if (rc == -1) {
                System.out.println("Enter valid input row and column numbers");
                player = (player == 1) ? 2 : 1;
                move = (move == player1Char) ? player2Char : player1Char;
            }
            else if (rc == -11) {
                System.out.println("Its a draw.\n Thank you for playing the game!");
                break;
            }
            else if (rc == player) {
                System.out.println("$ Player "+ player +" wins $.\nThank you for playing the game!");
                break;
            }
            xg.printBoard();
        } while (true);

    }
}
