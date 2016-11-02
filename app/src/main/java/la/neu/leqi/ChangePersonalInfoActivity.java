package la.neu.leqi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChangePersonalInfoActivity extends AppCompatActivity {

    private LinearLayout changeArea;
    private TextView changeRemind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_personal_info);

        changeArea= (LinearLayout) findViewById(R.id.change_area);
        changeRemind= (TextView) findViewById(R.id.change_remind);
    }
}
