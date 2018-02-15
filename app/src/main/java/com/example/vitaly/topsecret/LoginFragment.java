package com.example.vitaly.topsecret;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class LoginFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    Button number1;
    Button number2;
    Button number3;
    Button number4;
    Button number5;
    Button number6;
    Button number7;
    Button number8;
    Button number9;
    Button number0;
    ImageButton backSpace;
    EditText loginPin;
    TextView loginHint;

    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        getActivity().setTitle("Авторизация");
        number1 = (Button) view.findViewById(R.id.button_1);
        number2 = (Button) view.findViewById(R.id.button_2);
        number3 = (Button) view.findViewById(R.id.button_3);
        number4 = (Button) view.findViewById(R.id.button_4);
        number5 = (Button) view.findViewById(R.id.button_5);
        number6 = (Button) view.findViewById(R.id.button_6);
        number7 = (Button) view.findViewById(R.id.button_7);
        number8 = (Button) view.findViewById(R.id.button_8);
        number9 = (Button) view.findViewById(R.id.button_9);
        number0 = (Button) view.findViewById(R.id.button_0);
        backSpace = (ImageButton) view.findViewById(R.id.button_backspace);
        loginPin = (EditText) view.findViewById(R.id.login_pin);
        loginHint = (TextView) view.findViewById(R.id.login_hint);

        number1.setOnClickListener(this);
        number2.setOnClickListener(this);
        number3.setOnClickListener(this);
        number4.setOnClickListener(this);
        number5.setOnClickListener(this);
        number6.setOnClickListener(this);
        number7.setOnClickListener(this);
        number8.setOnClickListener(this);
        number9.setOnClickListener(this);
        number0.setOnClickListener(this);
        backSpace.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        String result;
        Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(25);

        switch (view.getId()) {
            case R.id.button_0:
                result = loginPin.getText().toString() + "0";
                loginPin.setText(result);
                break;
            case R.id.button_1:
                result = loginPin.getText().toString() + "1";
                loginPin.setText(result);
                break;
            case R.id.button_2:
                result = loginPin.getText().toString() + "2";
                loginPin.setText(result);
                break;
            case R.id.button_3:
                result = loginPin.getText().toString() + "3";
                loginPin.setText(result);
                break;
            case R.id.button_4:
                result = loginPin.getText().toString() + "4";
                loginPin.setText(result);
                break;
            case R.id.button_5:
                result = loginPin.getText().toString() + "5";
                loginPin.setText(result);
                break;
            case R.id.button_6:
                result = loginPin.getText().toString() + "6";
                loginPin.setText(result);
                break;
            case R.id.button_7:
                result = loginPin.getText().toString() + "7";
                loginPin.setText(result);
                break;
            case R.id.button_8:
                result = loginPin.getText().toString() + "8";
                loginPin.setText(result);
                break;
            case R.id.button_9:
                result = loginPin.getText().toString() + "9";
                loginPin.setText(result);
                break;
            case R.id.button_backspace:
                result = loginPin.getText().toString();
                if(!result.isEmpty()) {
                    result = result.substring(0, result.length() - 1);
                    loginPin.setText(result);
                }
                break;
        }
        if(loginPin.getText().length() == 4){
            if(loginPin.getText().toString().equals(PreferencesHelper.getPin(getActivity()))){
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                loginPin.setText("");
                vibe.vibrate(60);
            }
        }
    }
}
