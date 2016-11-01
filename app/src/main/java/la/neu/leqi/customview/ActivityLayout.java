package la.neu.leqi.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ActivityLayout  extends ImageView{
    public ActivityLayout(Context context) {
        super(context);
    }

    public ActivityLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        heightMeasureSpec =(int)(((double)widthMeasureSpec/3)*1);
        height = (int)(((double)width/3)*1);
        setMeasuredDimension(width, height);
        super.onMeasure(MeasureSpec.makeMeasureSpec(width, widthMode), MeasureSpec.makeMeasureSpec(height, widthMode));
    }
}
