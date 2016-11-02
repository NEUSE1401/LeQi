package la.neu.leqi;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePersonalInfoActivity extends Activity {

    private LinearLayout changeArea;
    private LinearLayout changeSexArea;
    private Button changeCommit;


    private String contentAfterChange;
    private int id;
    private String contentBeforChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_personal_info);

        changeArea= (LinearLayout) findViewById(R.id.change_area);
        changeSexArea= (LinearLayout) findViewById(R.id.change_sex_area);
        changeCommit= (Button) findViewById(R.id.change_sure);

        Intent intent=getIntent();
        id=intent.getIntExtra("viewId",0);
        contentBeforChange=intent.getStringExtra("contentBefore");

        if(id==R.id.change_nickname){
            setChangeEditText( contentBeforChange);
        }else if(id==R.id.change_contact){
            setChangeEditText( contentBeforChange);
        }else if(id==R.id.change_sex){
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
