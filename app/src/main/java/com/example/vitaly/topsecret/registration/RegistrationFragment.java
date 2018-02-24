package com.example.vitaly.topsecret.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.vitaly.topsecret.storage.MainActivity;
import com.example.vitaly.topsecret.PasswordModel;
import com.example.vitaly.topsecret.R;
import com.rengwuxian.materialedittext.MaterialEditText;


public class RegistrationFragment extends Fragment implements RegistrationView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    MaterialEditText pin;
    MaterialEditText hint;
    ImageButton done;

    PasswordModel model;
    RegistrationPresenter presenter;

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
        if (getActivity() != null)
            getActivity().setTitle(R.string.login_title);
        pin = (MaterialEditText) view.findViewById(R.id.pin);
        hint = (MaterialEditText) view.findViewById(R.id.pin_hint);
        done = (ImageButton) view.findViewById(R.id.login_done);

        model = new PasswordModel(getContext());
        presenter = new RegistrationPresenter(this, model);

        done.setOnClickListener(clickedView ->
            presenter.goToStorage()
        );
        return view;
    }

    @Override
    public String getHint() {
        return hint.getText().toString();
    }

    @Override
    public String getPin() {
        return pin.getText().toString();
    }


    @Override
    public boolean checkData() {
        if (pin.getText().toString().isEmpty()) {
            pin.setError("Надо заполнить!");
            return false;
        }

        if (hint.getText().toString().isEmpty()) {
            hint.setError("Надо заполнить!");
            return false;
        }

        if (pin.length() == 4) {
            return true;
        } else {
            pin.setError("Необходимо 4 символа");
            return false;
        }
    }

    @Override
    public void openStorageScreen() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
