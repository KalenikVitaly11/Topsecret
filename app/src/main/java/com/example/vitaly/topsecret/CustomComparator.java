package com.example.vitaly.topsecret;

import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by Vitaly on 15.02.2018.
 */

public class CustomComparator implements Comparator<PasswordElement> {
    @Override
    public int compare(@NonNull PasswordElement passwordElement1, @NonNull PasswordElement passwordElement2) {
        return passwordElement1.getOrganisation().compareToIgnoreCase(passwordElement2.getOrganisation());
    }
}
