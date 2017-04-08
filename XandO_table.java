class XandO_Game {
    private static final int ROW_SIZE=3;
    private static final int COLUMN_SIZE=3;
    private int moves;
    private byte [][]tableXO;

    public XandO_Game() {
        tableXO = new byte[ROW_SIZE][COLUMN_SIZE];
        moves = 0;
    }
   
    public int playerMove(int player, int row, int col, char val) {
        byte move = validChar(val);
        
        if (move != -1 && updateCell(row, col, move)) {
            if (checkForWinner(row, col, move)) {
                return player; // return code if player number then the player won
            }
            else if (checkForDraw()) {
                return -11; // return code for draw match
            }
            return 0; // return code to signal game is still ON
        }
        // Error detected in input, send error code
        return -1; // return code for input error
    }

    public static byte validChar(char val) {
        if (val == 'X' || val == 'x') {
            return 1;
        }
        else if (val == 'O' || val == 'o') {
            return 2;
        }
        return -1;
    }

    private boolean updateCell(int row, int col, byte val) {
        if (row >= 0 && row < ROW_SIZE && col >= 0 && col < COLUMN_SIZE) {
            if (!isCellOccupied(row, col)) {
                return false; // If a move has already been played on this cell then return false
            }
            tableXO[row][col] = val;
            moves++;
            return true;
        }
        return false; // If row or col out of range, fail
    }

    private boolean isCellOccupied(int row, int col) {
        return tableXO[row][col] == 0;
    }

    private boolean checkForWinner(int curr_row, int curr_col, byte val) {
        if ( 
            // Row win
            (tableXO[curr_row][0] == val &&
             tableXO[curr_row][1] == val &&
             tableXO[curr_row][2] == val
            ) ||
            // Column win
            (tableXO[0][curr_col] == val &&
             tableXO[1][curr_col] == val &&
             tableXO[2][curr_col] == val
            ) ||
            // Top left to bottom right diagonal win
            (curr_row == curr_col && tableXO[0][0] == val &&
             tableXO[1][1] == val &&
             tableXO[2][2] == val
            ) ||
            // Top right to Bottom left diagonal win
            (curr_row+curr_col == ROW_SIZE-1 && tableXO[2][0] == val &&
             tableXO[1][1] == val &&
             tableXO[0][2] == val
            )                              
        ) {
            return true;
        }
        return false;
    }
    
    private boolean checkForDraw() {
        if (moves < ROW_SIZE*COLUMN_SIZE) {
            return false;
        }
        return true;
    }

    public void printBoard() {
        for (int i=0; i<ROW_SIZE; ++i) {
            for (int j=0; j<COLUMN_SIZE; ++j) {
                if (tableXO[i][j] == 0) {
                    System.out.print("   ");
                }
                else if (tableXO[i][j] == 1) {
                    System.out.print(" X ");
                }
                else if (tableXO[i][j] == 2) {
                    System.out.print(" O ");
                }
                if (j<COLUMN_SIZE-1) {
                    System.out.print("|");
                }
            }
            System.out.print("\n");
            if (i<ROW_SIZE-1) {
                System.out.print("===========\n");
            }
        }
    }
}
