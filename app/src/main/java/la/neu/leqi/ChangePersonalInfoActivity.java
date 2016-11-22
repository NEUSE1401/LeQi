package la.neu.leqi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePersonalInfoActivity extends Activity {

    private LinearLayout changeArea;
    private LinearLayout changeSexArea;
    private Button changeCommit;
    private TextView back_title;
    private ImageView imageView;


    private String contentAfterChange;
    private int id;
    private String contentBeforChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_change_personal_info);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.back_title);
        back_title = (TextView)findViewById(R.id.back_title);

        imageView  =(ImageView)findViewById(R.id.back_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        changeArea= (LinearLayout) findViewById(R.id.change_area);
        changeSexArea= (LinearLayout) findViewById(R.id.change_sex_area);
        changeCommit= (Button) findViewById(R.id.change_sure);

        Intent intent=getIntent();
        id=intent.getIntExtra("viewId",0);
        contentBeforChange=intent.getStringExtra("contentBefore");

        if(id==R.id.change_nickname){
            back_title.setText("修改昵称");
            setChangeEditText( contentBeforChange);
        }else if(id==R.id.change_contact){
            back_title.setText("修改联系方式");
            setChangeEditText( contentBeforChange);
        }else if(id==R.id.change_sex){
            back_title.setText("修改性别");
            setSexChooseRadioButton( contentBeforChange);
        }

        changeCommit.setOnClickListener(new View.OnClickListener() {

            private String changeRemind="";
            @Override
            public void onClick(View view) {
                if(contentAfterChange.equals(contentBeforChange)){
                    changeRemind="未改变";
                    Toast toast=Toast.makeText(ChangePersonalInfoActivity.this,changeRemind,Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                if(id==R.id.change_nickname){
                     changeRemind="昵称已改为"+contentAfterChange;
                }else if(id==R.id.change_contact){
                    changeRemind="联系方式已改为"+contentAfterChange;
                }else if(id==R.id.change_sex){
                    changeRemind="性别已改为"+contentAfterChange;
                }
                Toast toast=Toast.makeText(ChangePersonalInfoActivity.this,changeRemind,Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }

    public void setChangeEditText(String remind){
        changeSexArea.setVisibility(LinearLayout.GONE);
        EditText changeEditText= (EditText) findViewById(R.id.change_editText);
        changeEditText.setText(remind);
        contentAfterChange=changeEditText.getText().toString();
    }

    public void setSexChooseRadioButton(String content){
        changeArea.setVisibility(LinearLayout.GONE);
        RadioGroup radioGroup= (RadioGroup) findViewById(R.id.sex_radiogroup);
        RadioButton man= (RadioButton) findViewById(R.id.man);
        RadioButton women= (RadioButton) findViewById(R.id.women);
        if(content.endsWith("男")){
            man.setChecked(true);
            contentAfterChange="男";
        }else {
            women.setChecked(true);
            contentAfterChange="女";
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                contentAfterChange=i==R.id.man?"男":"女";
            }
        });
    }

}
