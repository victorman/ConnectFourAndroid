package dev.victorman.connectfour;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Arrays;

public class GameBoard {
    private int[][] board;

    private int currentPlayer;

    public GameBoard() {
        board = new int[6][7];
        for (int i=0;i<6;i++) {
            for(int j=0;j<7;j++) {
                board[i][j] = -1;
            }
        }
    }

    public boolean playPiece(int col) {
        int row = 0;
        for(; row < 6; row++) {
            if (board[row][col] != -1) {
                break;
            }
        }
        row -= 1;
        if (row < 0) {
            return false;
        }

        board[row][col] = currentPlayer;
        return true;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int checkHasWinner() {
        int winner = -1;
        winner = checkVertical();
        if(winner != -1) {
            return winner;
        }
        winner = checkHorizontal();
        if(winner!= -1)
            return winner;
        winner = checkDiagonal();
        return winner;
//        int[] subset = new int[4];
//        int winner = checkArrayWinner(subset);
    }

    private int checkDiagonal() {
        int row = 0;
        int col = 0;
        int[] slice = null;
        int winner = -1;
        while (row < board.length) {
            slice = leftDiagonalSlice(row, col);
            if (slice.length > 3) {
                for (int i = 0; i < slice.length - 3; i++) {
                    winner = checkArrayWinner(Arrays.copyOfRange(slice, i, i + 4));
                    if (winner >= 0)
                        return winner;
                }
            }

            slice = rightDiagonalSlice(row, col);
            if(slice.length > 3) {
                for (int i = 0; i < slice.length - 3; i++) {
                    winner = checkArrayWinner(Arrays.copyOfRange(slice, i, i + 4));
                    if (winner >= 0)
                        return winner;
                }
            }
            row++;
        }

        row= board.length-1;

        while (col < board[row].length) {

            slice = leftDiagonalSlice(row, col);
            if(slice.length > 3) {
                for (int i = 0; i < slice.length - 3; i++) {
                    winner = checkArrayWinner(Arrays.copyOfRange(slice, i, i + 4));
                    if (winner >= 0)
                        return winner;
                }
            }

            slice = rightDiagonalSlice(row, col);
            if(slice.length > 3) {
                for (int i = 0; i < slice.length - 3; i++) {
                    winner = checkArrayWinner(Arrays.copyOfRange(slice, i, i + 4));
                    if (winner >= 0)
                        return winner;
                }
            }
            col++;
        }

        col = board[0].length;
        while (row  > 2) {

            slice = leftDiagonalSlice(row, col);
            if(slice.length > 3) {
                for (int i = 0; i < slice.length - 3; i++) {
                    winner = checkArrayWinner(Arrays.copyOfRange(slice, i, i + 4));
                    if (winner >= 0)
                        return winner;
                }
            }

            slice = rightDiagonalSlice(row, col);
            if(slice.length > 3) {
                for (int i = 0; i < slice.length - 3; i++) {
                    winner = checkArrayWinner(Arrays.copyOfRange(slice, i, i + 4));
                    if (winner >= 0)
                        return winner;
                }
            }
            row--;
        }

        return -1;
    }

    private int checkHorizontal() {
        int winner = -1;
        for (int i = 0; i < board.length; i++) {
            int[] slice = board[i];
            for(int j=0;j<4; j++) {
                winner = checkArrayWinner(Arrays.copyOfRange(slice, j, j+4));
                if(winner >= 0)
                    return winner;
            }
        }
        return winner;
    }

    private int checkVertical() {
        int winner = -1;
        for(int j = 0; j< board[0].length; j++) {
            int[] slice = slice(j);
            for(int i = 0;i<3;i++) {
                winner = checkArrayWinner(Arrays.copyOfRange(slice, i, i+4));
                if(winner >= 0)
                    return winner;
            }
        }
        return winner;
    }

    private int[] slice(int col) {
        int[] s = new int[board.length];
        for (int i = 0; i<board.length; i++) {
            s[i] = board[i][col];
        }
        return s;
    }
    private int[] leftDiagonalSlice(int row, int col) { // up col up row, down col down row
        int len = Math.min(row+1,col+1)+Math.min(board.length-row-1,board[0].length-col-1);
        int[] s = new int[len];
        int trow = row;
        int tcol = col;
        while (inBounds(trow, tcol)) {
            s[Math.min(trow,tcol)] = board[trow][tcol];
            trow++;
            tcol++;
        }
        trow = row -1;
        tcol = col -1;
        while(inBounds(trow, tcol)) {
            s[Math.min(trow,tcol)] = board[trow][tcol];
            trow--;
            tcol--;
        }

        return s;
    }
    private int[] rightDiagonalSlice(int row, int col) { // up col, down row | down col up row
        int len = Math.min(board.length - row,col+1)+Math.min(row,board[row].length-col-1);
        int[] s = new int[len];
        int trow = row;
        int tcol = col;
        while (inBounds(trow, tcol)) {
            s[Math.min(board.length - trow-1,tcol)] = board[trow][tcol];
            trow--;
            tcol++;
        }
        trow = row +1;
        tcol = col -1;
        while(inBounds(trow, tcol)) {
            s[Math.min(board.length - trow-1,tcol)] = board[trow][tcol];
            trow++;
            tcol--;
        }

        return s;
    }

    private boolean inBounds(int row, int col) {
        if (row < 0)
            return false;
        if ( row >= board.length)
            return false;
        if (col < 0)
            return false;
        if (col >= board[0].length)
            return false;
        return true;
    }

    private int checkArrayWinner(int[] subarray) {
        int winner = -1;
        for(int i = 0; i<subarray.length; i++) {
            if(i == 0)
                winner = subarray[i];
            else {
                if (subarray[i] != winner)
                    return -1;
            }
        }
        return winner;
    }

    public int[][] getBoard() {
        return board;
    }

    public void draw(Canvas canvas, float width, Paint red, Paint black) {
        float cellWidth = width/7f;
        float radius = cellWidth / 2f;
        for (int i=0;i<6;i++) {
            for (int j=0;j<7;j++) {
                if(board[i][j] != -1) {
                    Paint paint = null;
                    if (board[i][j] == 0) {
                        paint = red;
                    } else {
                        paint = black;
                    }
                    canvas.drawCircle(j*cellWidth + radius, (i+1) * cellWidth + radius, radius, paint);
                }
            }
        }
    }
}
