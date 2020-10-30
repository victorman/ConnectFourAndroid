package dev.victorman.connectfour;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GamePieceDrawable extends Drawable {
    private final float width;
    private float x;
    private Paint paint;

    public GamePieceDrawable(float width) {
        this.width = width;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
    @Override
    public void draw(@NonNull Canvas canvas) {
        float pieceRadius = width / 14f;
        canvas.drawCircle(x, pieceRadius, pieceRadius, paint);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
