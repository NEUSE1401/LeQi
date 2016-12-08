package la.neu.leqi.customview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * @author hxs
 */

public class PullToRefreshRecycleView extends PullToRefreshBase<RecyclerView>{
    public PullToRefreshRecycleView(Context context) {
        super(context);
    }

    public PullToRefreshRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecycleView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecycleView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        return new RecyclerView(context,attrs);
    }

    @Override
    protected boolean isReadyForPullEnd() {
        View view = getRefreshableView().getChildAt(getRefreshableView().getChildCount() - 1);
        return null != view && getRefreshableView().getBottom() >= view.getBottom();

    }

    @Override
    protected boolean isReadyForPullStart() {
        View view = getRefreshableView().getChildAt(0);

        return view != null && view.getTop() >= getRefreshableView().getTop();

    }
}
