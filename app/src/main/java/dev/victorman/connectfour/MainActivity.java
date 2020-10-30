package dev.victorman.connectfour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ConstraintLayout constraintLayout;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();
        constraintLayout = new ConstraintLayout(context);

        GameBoard gameBoard = new GameBoard();
        gameView = new GameView(context, gameBoard);
        gameView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        gameView.setOnTouchListener(new GameTouchListener());

        constraintLayout.addView(gameView);

        setContentView(constraintLayout);

        Runnable runnable = new Runnable() {
            long elapsedTime = 0L;
            long fps = 30L;
            long frameDuration = 1000L / fps;
            long lastTime = 0L;
            @Override
            public void run() {
                while(true) {
                    elapsedTime = System.currentTimeMillis() - lastTime;
                    if (elapsedTime > frameDuration) {
                        gameView.update();
                        gameView.invalidate();
                        lastTime = System.currentTimeMillis();
                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}