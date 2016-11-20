package la.neu.leqi.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import la.neu.leqi.MainActivity;
import la.neu.leqi.R;

/**
 * Created by lenovo on 2016/11/20.
 */

public class Log extends Fragment {
    private EditText name;
    private EditText pass;
    private Button log;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.log, null);
        name= (EditText) view.findViewById(R.id.user_name);
        pass= (EditText) view.findViewById(R.id.user_pass);
        log= (Button) view.findViewById(R.id.user_log);

        String nameS=name.getText().toString();
        String passS=pass.getText().toString();

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        return view ;
    }
}
