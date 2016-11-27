package la.neu.leqi.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import la.neu.leqi.MainActivity;
import la.neu.leqi.R;

/**
 * Created by lenovo on 2016/11/20.
 */

public class Register extends Fragment {
    private EditText name;
    private EditText pass;
    private EditText repetPass;
    private Button register;
    private LinearLayout registerLinear;
    private boolean isCreate=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isCreate = true;
        View view=inflater.inflate(R.layout.register, null);
        name= (EditText) view.findViewById(R.id.regist_user_name);
        pass= (EditText) view.findViewById(R.id.regist_user_pass);
        repetPass= (EditText) view.findViewById(R.id.regist_repet_user_pass);
        register= (Button) view.findViewById(R.id.register);
        registerLinear = (LinearLayout) view.findViewById(R.id.register_linear);
        registerLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(),
                            0);
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isVisibleToUser&&isCreate){
            name.setText("");
            pass.setText("");
            repetPass.setText("");
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
            }
        }
    }
}
