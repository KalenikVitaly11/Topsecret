package com.example.vitaly.topsecret.edit;


import android.util.Log;

import com.example.vitaly.topsecret.PasswordModel;

class EditPresenter{
        private EditView view;
        private PasswordModel model;

        EditPresenter(PasswordModel model, EditView view){
            this.view = view;
            this.model = model;
        }

        void done(){
            if(view.checkData()) {
                model.removePassword(view.getEditablePassword());
                model.addPassword(view.getPassword());
                Log.d("myLogs", "Added");
                view.completed();
            }
        }

        void loadFromIntent(){
            view.loadFromIntent();
        }
}
