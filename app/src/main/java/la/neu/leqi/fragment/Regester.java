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

import la.neu.leqi.MainActivity;
import la.neu.leqi.R;

/**
 * Created by lenovo on 2016/11/20.
 */

public class Regester extends Fragment {
    private EditText name;
    private EditText pass;
    private EditText repetPass;
    private Button regester;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.regester, null);
        name= (EditText) view.findViewById(R.id.regest_user_name);
        pass= (EditText) view.findViewById(R.id.regest_user_pass);
        repetPass= (EditText) view.findViewById(R.id.regest_repet_user_pass);
        regester= (Button) view.findViewById(R.id.regester);


        regester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
