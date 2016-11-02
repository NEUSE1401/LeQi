package la.neu.leqi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by lenovo on 2016/11/2.
 */

public class PersonalInformationActivity extends Activity{
    private LinearLayout changeNickName;
    private LinearLayout changeAge;
    private LinearLayout changeSex;
    private LinearLayout changeDistrict;
    private LinearLayout changeContactWay;

    private TextView nickName;
    private TextView sex;
    private TextView age;
    private TextView district;
    private TextView contactWay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        changeAge= (LinearLayout) findViewById(R.id.change_age);
        changeContactWay= (LinearLayout) findViewById(R.id.change_contact);
        changeDistrict= (LinearLayout) findViewById(R.id.change_district);
        changeSex= (LinearLayout) findViewById(R.id.change_sex);
        changeNickName= (LinearLayout) findViewById(R.id.change_nickname);

        nickName= (TextView) findViewById(R.id.nickname);
        age= (TextView) findViewById(R.id.age);
        contactWay= (TextView) findViewById(R.id.contact_way);
        district= (TextView) findViewById(R.id.district);
        sex= (TextView) findViewById(R.id.sex);

    }

    class ChangePersonalInfoListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

        }
    }

}
