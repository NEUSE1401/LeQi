package la.neu.leqi;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import la.neu.leqi.fragment.Log;
import la.neu.leqi.fragment.Register;

public class LogAndRegisterActivity extends Activity implements View.OnClickListener {
    private LinearLayout logLinear;
    private LinearLayout registerLinear;
    private LinearLayout content;
    private ImageView back;
    private FragmentManager fr;
    private FragmentTransaction ft;
    private ImageView arrow;
    private int bmpW;// 动画图片宽度
    private int currIndex;
    private int one;
    private int two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_and_register);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.parseColor("#e18080"));
        }
        logLinear = (LinearLayout) findViewById(R.id.log_linear);
        registerLinear = (LinearLayout) findViewById(R.id.register_linear);
        content = (LinearLayout) findViewById(R.id.log_and_register_content);
        arrow = (ImageView) findViewById(R.id.log_arrow);
        back = (ImageView) findViewById(R.id.back);
        initImageView();
        logLinear.setOnClickListener(this);
        registerLinear.setOnClickListener(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        fr = getFragmentManager();
        ft = fr.beginTransaction();
        ft.replace(R.id.log_and_register_content, new Log());
        ft.commit();
    }

    private void initImageView() {
        arrow = (ImageView) findViewById(R.id.log_arrow);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.arrow)
                .getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        one = 0;
        two = screenW / 2 ;
        currIndex = 1;
        final Matrix matrix = new Matrix();
        matrix.postTranslate(screenW / 4 - bmpW / 2,0);
        arrow.setImageMatrix(matrix);
    }

    @Override
    public void onClick(View view) {
        ft = fr.beginTransaction();
        Animation animation;
        switch (view.getId()) {
            case R.id.log_linear:
                if (currIndex == 2) {
                    currIndex =1;
                    animation = new TranslateAnimation(two, one, 0, 0);
                    animation.setFillAfter(true);// True:图片停在动画结束位置
                    animation.setDuration(300);
                    arrow.startAnimation(animation);
                    ft.replace(R.id.log_and_register_content, new Log());
                }
                break;
            case R.id.register_linear:
                if (currIndex == 1) {
                    currIndex=2;
                    animation = new TranslateAnimation(one, two, 0, 0);
                    animation.setFillAfter(true);// True:图片停在动画结束位置
                    animation.setDuration(300);
                    arrow.startAnimation(animation);
                    ft.replace(R.id.log_and_register_content, new Register());
                }
                break;
        }
        ft.commit();
    }


}
