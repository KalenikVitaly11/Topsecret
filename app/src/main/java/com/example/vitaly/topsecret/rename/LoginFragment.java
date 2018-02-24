package com.example.vitaly.topsecret.rename;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vitaly.topsecret.storage.MainActivity;
import com.example.vitaly.topsecret.PasswordModel;
import com.example.vitaly.topsecret.R;


public class LoginFragment extends Fragment implements LoginView {
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

    Vibrator vibe;

    PasswordModel model;
    LoginPresenter presenter;

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

        if(getActivity() != null)
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

        number1.setOnClickListener(clickedView ->
            presenter.clicked(loginPin.getText().toString() + "1")
        );
        number2.setOnClickListener(clickedView ->
            presenter.clicked(loginPin.getText().toString() + "2")
        );
        number3.setOnClickListener(clickedView ->
            presenter.clicked(loginPin.getText().toString() + "3")
        );
        number4.setOnClickListener(clickedView ->
            presenter.clicked(loginPin.getText().toString() + "4")
        );
        number5.setOnClickListener(clickedView ->
            presenter.clicked(loginPin.getText().toString() + "5")
        );
        number6.setOnClickListener(clickedView ->
            presenter.clicked(loginPin.getText().toString() + "6")
        );
        number7.setOnClickListener(clickedView ->
            presenter.clicked(loginPin.getText().toString() + "7")
        );
        number8.setOnClickListener(clickedView ->
            presenter.clicked(loginPin.getText().toString() + "8")
        );
        number9.setOnClickListener(clickedView ->
            presenter.clicked(loginPin.getText().toString() + "9")
        );
        number0.setOnClickListener(clickedView ->
            presenter.clicked(loginPin.getText().toString() + "0")
        );
        backSpace.setOnClickListener(clickedView ->
            presenter.clickedBackspace(loginPin.getText().toString())
        );

        model = new PasswordModel(getContext());
        presenter = new LoginPresenter(model, this);

        vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        return view;
    }


    @Override
    public void setPinText(String text) {
        vibe.vibrate(25);
        loginPin.setText(text);
    }

    @Override
    public void goToStorage() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void clearPin() {
        loginPin.setText("");
        vibe.vibrate(60);
    }

    @Override
    public void showHint() {
        Toast.makeText(getContext(), "Подсказка: " + model.getHint(), Toast.LENGTH_LONG).show();
    }
}
