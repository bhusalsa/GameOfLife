package org.bhusalsa;

public class test {
        // Method to compute the next board state based on the current board state
        public static int[][] nextBoardState(int[][] board) {
            int rows = board.length;
            int cols = board[0].length;
            int[][] nextBoard = new int[rows][cols];

            // Check each cell and apply the Game of Life rules
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int liveNeighbors = countLiveNeighbors(board, i, j, rows, cols);

                    // Apply rules: Cell becomes alive or dead
                    if (board[i][j] == 1) {
                        if (liveNeighbors < 2 || liveNeighbors > 3) {
                            nextBoard[i][j] = 0; // Cell dies
                        } else {
                            nextBoard[i][j] = 1; // Cell stays alive
                        }
                    } else {
                        if (liveNeighbors == 3) {
                            nextBoard[i][j] = 1; // Cell becomes alive
                        }
                    }
                }
            }

            return nextBoard;
        }

        // Helper method to count the number of live neighbors around a cell
        private static int countLiveNeighbors(int[][] board, int x, int y, int rows, int cols) {
            int liveNeighbors = 0;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue; // Skip the cell itself
                    int nx = x + i;
                    int ny = y + j;
                    if (nx >= 0 && nx < rows && ny >= 0 && ny < cols) {
                        liveNeighbors += board[nx][ny];
                    }
                }
            }
            return liveNeighbors;
        }

        // Method to print a board for debugging
        private static void printBoard(int[][] board) {
            for (int[] row : board) {
                for (int cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        }

        // Main method to run the tests
        public static void main(String[] args) {
            // TEST 1: dead cells with no live neighbors should stay dead
            int[][] initState1 = {
                    {0, 0, 0},
                    {0, 0, 0},
                    {0, 0, 0}
            };
            int[][] expectedNextState1 = {
                    {0, 0, 0},
                    {0, 0, 0},
                    {0, 0, 0}
            };
            int[][] actualNextState1 = GameofLife.next_board_state(initState1);

            if (java.util.Arrays.deepEquals(expectedNextState1, actualNextState1)) {
                System.out.println("PASSED 1");
            } else {
                System.out.println("FAILED 1!");
                System.out.println("Expected:");
                printBoard(expectedNextState1);
                System.out.println("Actual:");
                printBoard(actualNextState1);
            }

            // TEST 2: dead cells with exactly 3 neighbors should come alive
            int[][] initState2 = {
                    {0, 0, 1},
                    {0, 1, 1},
                    {0, 0, 0}
            };
            int[][] expectedNextState2 = {
                    {0, 1, 1},
                    {0, 1, 1},
                    {0, 0, 0}
            };
            int[][] actualNextState2 = GameofLife.next_board_state(initState2);

            if (java.util.Arrays.deepEquals(expectedNextState2, actualNextState2)) {
                System.out.println("PASSED 2");
            } else {
                System.out.println("FAILED 2!");
                System.out.println("Expected:");
                printBoard(expectedNextState2);
                System.out.println("Actual:");
                printBoard(actualNextState2);
            }
        }
}

