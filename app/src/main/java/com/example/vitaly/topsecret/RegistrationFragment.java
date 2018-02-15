package com.example.vitaly.topsecret;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.rengwuxian.materialedittext.MaterialEditText;

import static android.content.Context.MODE_PRIVATE;


public class RegistrationFragment extends Fragment  implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    MaterialEditText pin;
    MaterialEditText hint;
    ImageButton done;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    public static RegistrationFragment newInstance(String param1, String param2) {
        RegistrationFragment fragment = new RegistrationFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        getActivity().setTitle(R.string.login_title);
        pin = (MaterialEditText) view.findViewById(R.id.pin);
        hint = (MaterialEditText) view.findViewById(R.id.pin_hint);
        done = (ImageButton) view.findViewById(R.id.login_done);
        done.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_done:
                if(pin.getText().toString().isEmpty()){
                    pin.setError("Надо заполнить!");
                } else if (hint.getText().toString().isEmpty()){
                    hint.setError("Надо заполнить!");
                } else if(pin.isCharactersCountValid()){
                    PreferencesHelper.setPin(getActivity(), pin.getText().toString());
                    PreferencesHelper.setHint(getActivity(), hint.getText().toString());

                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                break;
        }
    }
}
