import java.io.*;
import java.util.*;

public class Tetris {

    enum Tetromino {
        Q(new char[][]{{'Y', 'Y'}, {'Y', 'Y'}}),
        Z(new char[][]{{'Y', 'Y', 'N'},{'N', 'Y', 'Y'}}),
        S(new char[][]{{'N', 'Y', 'Y'},{'Y', 'Y', 'N'}}),
        T(new char[][]{{'Y', 'Y', 'Y'},{'N', 'Y', 'N'}}),
        I(new char[][]{{'Y', 'Y', 'Y', 'Y'}}),
        L(new char[][]{{'Y', 'N'},{'Y', 'N'},{'Y', 'Y'}}),
        J(new char[][]{{'N', 'Y'},{'N', 'Y'},{'Y', 'Y'}});

        private final char[][] shape;

        Tetromino(char[][] shape) {
            this.shape = shape;
        }

        public char[][] getShape() {
            return shape;
        }

        public int getWidth() {
            return shape[0].length;
        }

        public int getHeight() {
            return shape.length;
        }
    }

    static class Board {
        int numRows;
        int numCols;
        List<char[]> board;

        public Board(int cols, int rows) {
            this.numCols = cols;
            this.numRows = rows;
            board = new ArrayList<>();
            for (int i = 0; i < rows; i++) {
                char[] line = new char[cols];
                Arrays.fill(line, 'N');
                board.add(line);
            }
        }

        public List<Integer> getCompletedLines() {
            List<Integer> completedLines = new ArrayList<>();
            for (int i = 0; i < board.size(); i++) {
                char[] line = board.get(i);
                boolean isComplete = true;
                for (char c : line) {
                    if (c == 'N') {
                        isComplete = false;
                        break;
                    }
                }
                if (isComplete) {
                    completedLines.add(i);
                }
            }
            return completedLines;
        }

        public int getEmptyLines() {
            int emptyLines = 0;
            for (char[] line : board) {
                boolean isEmpty = true;
                for (char c : line) {
                    if (c == 'Y') {
                        isEmpty = false;
                        break;
                    }
                }
                if (isEmpty) {
                    emptyLines++;
                }
            }
            return emptyLines;
        }

        public void removeLine(int index) {
            board.remove(index);
            char[] newLine = new char[numCols];
            Arrays.fill(newLine, 'N');
            board.add(0, newLine);
        }

        public int calculateDropPosition(Tetromino tetromino, int startCol, int maxHeight) {
            int lastPossibleRow = maxHeight - tetromino.getHeight() + 1;
            for (int row = 0; row < lastPossibleRow; row++) {
                for (int i = 0; i < tetromino.getHeight(); i++) {
                    for (int j = 0; j < tetromino.getWidth(); j++) {
                        if (board.get(row + i)[startCol + j] == 'Y' && tetromino.getShape()[i][j] == 'Y') {
                            return row - 1;
                        }
                    }
                }
            }
            return lastPossibleRow - 1;
        }

        public void placeTetromino(Tetromino tetromino, int row, int startCol) {
            for (int i = 0; i < tetromino.getHeight(); i++) {
                for (int j = 0; j < tetromino.getWidth(); j++) {
                    if (tetromino.getShape()[i][j] == 'Y') {
                        board.get(row + i)[startCol + j] = 'Y';
                    }
                }
            }
        }
    }

    public static int[] findBestPosition(Board board, int startCol, Tetromino tetromino, int maxHeight) {
        List<int[]> positions = new ArrayList<>();
        int row = board.calculateDropPosition(tetromino, startCol, maxHeight);
        int blockCount = 0;
        for (int i = row; i < row + tetromino.getHeight(); i++) {
            for (char c : board.board.get(i)) {
                if (c == 'Y') {
                    blockCount++;
                }
            }
        }
        positions.add(new int[]{blockCount, startCol, row});

        for (int i = 0; i < 3; i++) {
            final int index = i;
            int key = positions.stream().mapToInt(arr -> arr[index]).max().orElse(0);
            positions.removeIf(arr -> arr[index] != key);
        }

        return positions.get(0);
    }

    public static int tetrisEngine(String inputLine) {
        String[] moves = inputLine.split(",");
        Board gameBoard = new Board(10, 100); //Program Assumptions
        int currentHeight = 0;

        for (String move : moves) {
            String tetrominoType = move.substring(0, 1);
            int startCol = Integer.parseInt(move.substring(1));

            Tetromino tetromino = Tetromino.valueOf(tetrominoType);

            for (int i = 0; i < tetromino.getHeight(); i++) {
                char[] newLine = new char[10];
                Arrays.fill(newLine, 'N');
                gameBoard.board.add(0, newLine);
            }

            int maxHeight = gameBoard.board.size();
            int[] bestPosition = findBestPosition(gameBoard, startCol, tetromino, maxHeight);
            int row = bestPosition[2];
            gameBoard.placeTetromino(tetromino, row, startCol);

            List<Integer> completedLines = gameBoard.getCompletedLines();
            for (int index : completedLines) {
                gameBoard.removeLine(index);
            }
            currentHeight = gameBoard.board.size() - gameBoard.getEmptyLines();
        }
        return currentHeight;
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                int result = tetrisEngine(line.trim());
                writer.write(String.valueOf(result));
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}