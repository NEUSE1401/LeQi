package la.neu.leqi;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_and_register);
        logLinear= (LinearLayout) findViewById(R.id.log_linear);
        registerLinear= (LinearLayout) findViewById(R.id.register_linear);
        content= (LinearLayout) findViewById(R.id.log_and_register_content);
        back= (ImageView) findViewById(R.id.back);

        logLinear.setOnClickListener(this);
        registerLinear.setOnClickListener(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        fr=getFragmentManager();
        ft=fr.beginTransaction();

        ft.replace(R.id.log_and_register_content,new Log());
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        ft=fr.beginTransaction();
        switch (view.getId()){
            case R.id.log_linear:
                ft.replace(R.id.log_and_register_content,new Log());
                break;
            case R.id.register_linear:
                ft.replace(R.id.log_and_register_content,new Register());
                break;
        }
        ft.commit();
    }


}
