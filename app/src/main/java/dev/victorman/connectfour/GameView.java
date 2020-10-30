package dev.victorman.connectfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class GameView extends View {
    private final Context context;
    private final Paint boardPaint;
    private final Paint redPiecePaint;
    private final Paint blackPiecePaint;
    private GameBoardDrawable boardDrawable;
    private GamePieceDrawable gamePiece;
    private int player;
    private GameBoard gameBoard;

    private int winner;

    public GameView(Context context, GameBoard gameBoard) {
        super(context);
        this.context = context;
        this.boardPaint = new Paint();
        this.boardPaint.setColor(Color.BLUE);
        this.boardPaint.setStrokeWidth(1f);
        this.boardPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        redPiecePaint = new Paint();
        redPiecePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        redPiecePaint.setColor(Color.RED);

        blackPiecePaint = new Paint();
        blackPiecePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        blackPiecePaint.setColor(Color.BLACK);
        newGame(gameBoard);
    }

    public void newGame(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        player = 0;
        winner = -1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(boardDrawable == null) {
            boardDrawable = new GameBoardDrawable(0f,
                    getWidth() / 7f,
                    getWidth(),
                    (getHeight()/0.5f),
                    boardPaint);
        }

        gameBoard.draw(canvas, getWidth(), redPiecePaint, blackPiecePaint);

        if(gamePiece != null) {
            gamePiece.draw(canvas);
        }
        boardDrawable.draw(canvas);
    }

    public void update() {}

    public void addPiece(final float x) {
        gamePiece = new GamePieceDrawable(getWidth());
        gamePiece.setX(x);
        if (player == 0) {
            gamePiece.setPaint(redPiecePaint);
            gamePiece.setPaint(redPiecePaint);
        } else {
            gamePiece.setPaint(blackPiecePaint);
        }
    }

    public void playPiece(float x) {
        int col = (int)(x / (getWidth() / 7));

        gameBoard.playPiece(col);
        gamePiece = null;
        player = (player + 1) % 2;
        gameBoard.setCurrentPlayer(player);

        winner = gameBoard.checkHasWinner();
    }

    public void movePiece(float x) {
        gamePiece.setX(x);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }



    public int getWinner() {
        return winner;
    }
}
