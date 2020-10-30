package dev.victorman.connectfour;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GameBoardDrawable extends Drawable {
    private final Paint paint;
    private Rect rect;
    private Path path;
    private float cellWidth;
    public float cellPadding;

    public GameBoardDrawable(float left, float top, float width, float height, Paint paint) {

        rect = new Rect((int)left, (int)top, (int)(left + width), (int)(top + height));
        setBounds(rect);
        this.paint = paint;
        path = new Path();
        cellWidth = 0f;
        cellPadding = 10f;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        path.rewind();
        if (cellWidth == 0f) {
            cellWidth = getBounds().width() / 7f;
        }

        float radius = (cellWidth / 2f) - (cellPadding);
        for (int i = 0; i<6; i++) {

            for (int j = 0; j < 7; j++) {
                path.addCircle(rect.left + cellPadding + (j * cellWidth) + radius,
                        rect.top + cellPadding + (i * cellWidth) + radius,
                        radius,
                        Path.Direction.CCW);
            }
        }

        canvas.clipOutPath(path);
        canvas.drawRect(rect, paint);

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
