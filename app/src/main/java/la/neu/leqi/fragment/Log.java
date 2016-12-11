package la.neu.leqi.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import la.neu.leqi.MainActivity;
import la.neu.leqi.R;
import la.neu.leqi.thread.LoginWebThread;

import static android.content.Context.MODE_PRIVATE;

public class Log extends Fragment {
    private EditText name;
    private EditText pass;
    private Button log;
    private LinearLayout loginLinear;
    private boolean isCreate=false;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        isCreate = true;
        View view = inflater.inflate(R.layout.log, null);
        name = (EditText) view.findViewById(R.id.user_name);
        pass = (EditText) view.findViewById(R.id.user_pass);
        log = (Button) view.findViewById(R.id.user_log);
        loginLinear = (LinearLayout)view.findViewById(R.id.login_linear);
        loginLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                }
            }
        });
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    final SharedPreferences user = getActivity().getSharedPreferences("user", MODE_PRIVATE);
                    user.edit().putString("username",msg.getData().getString("username")).apply();
                    user.edit().putString("token",msg.getData().getString("token")).apply();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    Toast.makeText(container.getContext(), "账号密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        };
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nameS = name.getText().toString();
                final String passS = pass.getText().toString();
                final LoginWebThread loginWebThread = new LoginWebThread(getString(R.string.WEB_LOGIN),handler, nameS, passS);
                loginWebThread.start();
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
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
            }
        }
    }
}
