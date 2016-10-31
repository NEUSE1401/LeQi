package la.neu.leqi.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareImageView extends ImageView {

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (widthMeasureSpec > heightMeasureSpec)
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
        else
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
