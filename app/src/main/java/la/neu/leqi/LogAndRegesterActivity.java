package la.neu.leqi;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import la.neu.leqi.fragment.Log;
import la.neu.leqi.fragment.Regester;

public class LogAndRegesterActivity extends Activity implements View.OnClickListener {
    private LinearLayout logLinear;
    private LinearLayout regesterLinear;
    private LinearLayout content;
    private ImageView back;
    private FragmentManager fr;
    private FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_and_regester);
        logLinear= (LinearLayout) findViewById(R.id.log_linear);
        regesterLinear= (LinearLayout) findViewById(R.id.regester_linear);
        content= (LinearLayout) findViewById(R.id.log_and_regester_content);
        back= (ImageView) findViewById(R.id.back);

        logLinear.setOnClickListener(this);
        regesterLinear.setOnClickListener(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        fr=getFragmentManager();
        ft=fr.beginTransaction();

        ft.replace(R.id.log_and_regester_content,new Log());
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        ft=fr.beginTransaction();
        switch (view.getId()){
            case R.id.log_linear:
                ft.replace(R.id.log_and_regester_content,new Log());
                break;
            case R.id.regester_linear:
                ft.replace(R.id.log_and_regester_content,new Regester());
                break;
        }
        ft.commit();
    }


}
