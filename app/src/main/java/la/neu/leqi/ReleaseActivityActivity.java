package la.neu.leqi;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;


public class ReleaseActivityActivity extends Activity {

    private String activity_Title;
    private String activity_Description;
    private String activity_startTime;
    private String activity_endTime;
    private String activity_startAddress;
    private String activity_endAddress;
    private String activity_Addtion;
    private String activity_Style;

    private EditText ET_title;
    private EditText ET_Description;
    private EditText ET_startTime;
    private EditText ET_endTime;
    private EditText ET_startAddress;
    private EditText ET_endAddress;
    private EditText ET_Addtion;
    private EditText ET_Style;
    private Button Btn_release;

    private TextView back_title;
    private ImageView imageView;
    private DatePickerDialog datePickerDialog;
    private int year;
    private int month;
    private int day;
    private String date;


    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker data_picker, int c_year, int c_month, int c_day){
            year = c_year;
            month = c_month;
            day = c_day;

            date = year+"年"+month+"月"+day+"日";
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_release_activity);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.back_title);

//        back_title = (TextView)findViewById(R.id.back_title);
//        back_title.setText("我的发布");
//        imageView  =(ImageView)findViewById(R.id.back_icon);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });

        ET_title = (EditText)findViewById(R.id.activity_release_activity_title);
        ET_Description = (EditText)findViewById(R.id.activity_release_activity_description);
        ET_startTime = (EditText)findViewById(R.id.activity_release_activity_starttime);
        ET_endTime = (EditText)findViewById(R.id.activity_release_activity_endtime);
        ET_startAddress = (EditText)findViewById(R.id.activity_release_activity_startaddress);
        ET_endAddress = (EditText)findViewById(R.id.activity_release_activity_endaddress);
        ET_Addtion = (EditText)findViewById(R.id.activity_release_activity_addtion);
        ET_Style = (EditText)findViewById(R.id.activity_release_activity_style);


        Calendar mycalendar=Calendar.getInstance();
        year=mycalendar.get(Calendar.YEAR);
        month=mycalendar.get(Calendar.MONTH);
        day=mycalendar.get(Calendar.DAY_OF_MONTH);


        ET_startTime.setFocusable(false);
        ET_startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog=new DatePickerDialog(ReleaseActivityActivity.this,dateSetListener,year,month,day);
                datePickerDialog.show();
                ET_startTime.setText(date);
            }
        });


        ET_endTime.setFocusable(false);
        ET_endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog=new DatePickerDialog(ReleaseActivityActivity.this,dateSetListener,year,month,day);
                datePickerDialog.show();
                ET_endTime.setText(date);
            }
        });

        Btn_release = (Button)findViewById(R.id.activity_release_activity_button);
        Btn_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attempRelease();
            }
        });


    }

    private void attempRelease(){
        ET_title.setError(null);
        ET_Description.setError(null);
        ET_startTime.setError(null);
        ET_endTime.setError(null);
        ET_startAddress.setError(null);
        ET_endAddress.setError(null);
        ET_Addtion.setError(null);
        ET_Style.setError(null);

        activity_Title = ET_title.getText().toString();
        activity_Description = ET_Description.getText().toString();
        activity_startTime = ET_startTime.getText().toString();
        activity_endTime = ET_endTime.getText().toString();
        activity_startAddress = ET_startAddress.getText().toString();
        activity_endAddress = ET_endAddress.getText().toString();
        activity_Addtion = ET_Addtion.getText().toString();
        activity_Style = ET_Style.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(activity_endAddress)){
            ET_startTime.setError("结束地点不能为空");
            focusView = ET_endAddress;
            cancel = true;
        }

        if (TextUtils.isEmpty(activity_startAddress)){
            ET_startTime.setError("起始地点不能为空");
            focusView = ET_startAddress;
            cancel = true;
        }

        if (TextUtils.isEmpty(activity_endTime)){
            ET_startTime.setError("结束时间不能为空");
            focusView = ET_endTime;
            cancel = true;
        }

        if (TextUtils.isEmpty(activity_startTime)){
            ET_startTime.setError("发起时间不能为空");
            focusView = ET_startTime;
            cancel = true;
        }
        if(TextUtils.isEmpty(activity_Description)){
            ET_Description.setError("活动介绍不能为空");
            focusView = ET_Description;
            cancel = true;
        }

        if (TextUtils.isEmpty(activity_Title)){
            ET_title.setError("标题不能为空");
            focusView = ET_title;
            cancel = true;
        }

        if(cancel){
            //发布活动不成功
            focusView.requestFocus();
        }
        else{


            //活动发布成功
            //数据传入数据库的操作


        }
    }
}
