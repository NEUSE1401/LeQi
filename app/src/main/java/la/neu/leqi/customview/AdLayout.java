package la.neu.leqi.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class AdLayout extends LinearLayout {
    public AdLayout(Context context) {
        super(context);
    }

    public AdLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        heightMeasureSpec =(int)(((double)widthMeasureSpec/21)*9);
        height = (int)(((double)width/21)*9);
        setMeasuredDimension(width, height);
        super.onMeasure(MeasureSpec.makeMeasureSpec(width,widthMode),MeasureSpec.makeMeasureSpec(height,widthMode));
    }
}
