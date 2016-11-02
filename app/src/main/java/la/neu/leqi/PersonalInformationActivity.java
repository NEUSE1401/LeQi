package la.neu.leqi;

import android.app.Activity;
import android.content.Intent;
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
        setContentView(R.layout.activity_personal_information);
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


        changeNickName.setOnClickListener(new ChangePersonalInfoListener(nickName.getText().toString()));
        changeSex.setOnClickListener(new ChangePersonalInfoListener(sex.getText().toString()));
        changeContactWay.setOnClickListener(new ChangePersonalInfoListener(contactWay.getText().toString()));

    }

    class ChangePersonalInfoListener implements View.OnClickListener{
        private String contentBefor;
        ChangePersonalInfoListener(String content){
            contentBefor=content;
        }
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(PersonalInformationActivity.this,ChangePersonalInfoActivity.class);
            intent.putExtra("viewId",view.getId());
            intent.putExtra("contentBefore",contentBefor);
            startActivity(intent);
        }
    }



}
