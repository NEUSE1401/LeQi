package la.neu.leqi.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * @author hxs
 */

public class PullToRefreshSwipeMenuListView extends PullToRefreshBase<SwipeMenuListView> {
    public PullToRefreshSwipeMenuListView(Context context) {
        super(context);
    }

    public PullToRefreshSwipeMenuListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshSwipeMenuListView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshSwipeMenuListView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected SwipeMenuListView createRefreshableView(Context context, AttributeSet attrs) {
        return new SwipeMenuListView(context,attrs);
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
