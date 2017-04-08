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
        initialInstructions(xg);
        int player = 1;
        char move = selectPlayerOneChar();
        
        do {
            try {
                System.out.println("Player "+player+" turn to play");
                
                int row = in.nextInt();
                if (row == -1) {
                    System.exit(0);
                }
                int col = in.nextInt();
    
                // Note: Currently there is no use of passing move as parameter from here as I am not taking character input from user, but this can be future enhancement and hence not changed the way this API behaves
                int rc = xg.playerMove(player, row-1, col-1, move);
                if (rc == -1) {
                    System.out.println("Enter valid input row and column numbers");
                }
                else if (rc == -11) {
                    xg.printBoard();
                    System.out.println("Its a draw.\n Thank you for playing the game!");
                    break;
                }
                else if (rc == player) {
                    xg.printBoard();
                    System.out.println("$ Player "+ player +" wins $.\nThank you for playing the game!");
                    break;
                }
                else {
                    xg.printBoard();
                    player = switchPlayer(player);
                    move = switchMove(move);
                }
            }
            catch (InputMismatchException excep) {
                System.out.println("Enter valid input row and column numbers");
                in.next();
            }
        } while (true);
    }

    private static int switchPlayer(int player) {
        return (player == 1) ? 2 : 1;
    }

    private static char switchMove(char move) {
        return (move == 'X') ? 'O' : 'X';
    }

    private static char selectPlayerOneChar() {
        do {
            try {
                int select = in.nextInt();

                if (select == 1) {
                    return 'X';
                }
                else if (select == 2) {
                    return 'O';
                }
                else if (select == -1) {
                    System.exit(0);
                }
                else {
                    System.out.println ("Invalid input!\nEnter \"1\" for X, \"2\" for O");
                    in.next();
                }
            }
            catch (InputMismatchException excep) {
                System.out.println ("Invalid input!\nEnter \"1\" for X, \"2\" for O");
                in.next();
            }
        } while (true);
    }

    private static void initialInstructions(XandO_Game xg) {
        System.out.println("Let the Game begin!");
        System.out.println("Sample valid inputs are :-");
        System.out.println("2 2 - to place a X/O on 2nd row, 2nd column of table");
        System.out.println("1 2 - to place a X/O on 1st row, 2nd column of table");
        System.out.println("0 1 - Invalid Input as there is no 0th row");

        System.out.println("To exit from the Game at any time, enter -1");
        
        xg.printBoard();

        System.out.println("Player 1 starts the game, Player 1 enter \n1) to choose X,\n2) for O");
        
    }
}
