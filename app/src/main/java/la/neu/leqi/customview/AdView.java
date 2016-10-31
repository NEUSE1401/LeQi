package la.neu.leqi.customview;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class AdView extends LinearLayout {

    public AdView(Context paramContext) {
        super(paramContext);
    }

    public AdView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public AdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec=(int)(((double)widthMeasureSpec/21)*9);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        super.onMeasure(MeasureSpec.makeMeasureSpec(widthMeasureSpec, mode), MeasureSpec.makeMeasureSpec(heightMeasureSpec, mode));
    }
}
