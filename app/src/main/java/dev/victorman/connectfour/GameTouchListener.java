package dev.victorman.connectfour;

import android.view.MotionEvent;
import android.view.View;

public class GameTouchListener implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        GameView gameView = (GameView) view;

        float x = motionEvent.getX();
        float y = motionEvent.getY();

        if (gameView.getWinner() == -1) {

            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    gameView.addPiece(x);
                    break;
                case MotionEvent.ACTION_UP:
                    gameView.playPiece(x);
                    break;
                case MotionEvent.ACTION_MOVE:
                    gameView.movePiece(x);
                    break;
            }

        } else {
            if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                gameView.newGame(new GameBoard());
            }
        }
        return true;
    }
}
