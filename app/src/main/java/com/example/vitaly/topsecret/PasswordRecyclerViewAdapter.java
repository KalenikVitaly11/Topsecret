package com.example.vitaly.topsecret;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.gesture.Gesture;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thesurix.gesturerecycler.GestureAdapter;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmResults;


public class PasswordRecyclerViewAdapter extends RecyclerView.Adapter<PasswordRecyclerViewAdapter.ViewHolder> {
    private ArrayList<PasswordElement> dataPassword;
    private ArrayList<PasswordElement> selectedItems;
    private Context mContext;
    int rotationAngle = 0;
    private boolean multiSelect = false;

    public PasswordRecyclerViewAdapter(Context context) {
        mContext = context;
        dataPassword = new ArrayList<PasswordElement>();
        selectedItems = new ArrayList<PasswordElement>();
        updateData();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_password_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.organisation.setText(dataPassword.get(position).getOrganisation());
        holder.orgLogin.setText(dataPassword.get(position).getLogin());
        holder.login.setText(dataPassword.get(position).getLogin());
        holder.password.setText(dataPassword.get(position).getPassword());
        holder.extraInfo.setText(dataPassword.get(position).getExtraInfo());
        holder.link.setText(dataPassword.get(position).getLink());
        if (dataPassword.get(position).getLink().isEmpty()) {
            holder.link.setVisibility(View.GONE);
        }
        if (dataPassword.get(position).getExtraInfo().isEmpty()) {
            holder.extraInfo.setVisibility(View.GONE);
        }
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                holder.expandableLayout.toggle();
                ObjectAnimator anim = ObjectAnimator.ofFloat(holder.arrowIcon, "rotation", rotationAngle, rotationAngle + 180);
                anim.setDuration(300);
                anim.start();
                rotationAngle += 180;
                rotationAngle = rotationAngle % 360;
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });

    }

    public ArrayList<PasswordElement> getDataPassword() {
        return dataPassword;
    }

    public void updateData() {
        dataPassword.clear();
        Realm.init(mContext);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<PasswordElement> results = realm.where(PasswordElement.class).findAll();
        dataPassword.addAll(realm.copyFromRealm(results));
        Collections.sort(dataPassword, new CustomComparator());
        notifyDataSetChanged();
    }



    public void removeElement(int position){
        dataPassword.remove(position);
    }

    @Override
    public int getItemCount() {
        return dataPassword.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ExpandableLayout expandableLayout;
        private RelativeLayout header;
        private TextView organisation;
        private TextView orgLogin;
        private TextView login;
        private TextView password;
        private TextView extraInfo;
        private TextView link;
        private ImageView arrowIcon;
        private ItemClickListener clickListener;
        private LinearLayout cardViewLayout;
        private ImageView editIcon;
        private ImageView deleteIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            expandableLayout = (ExpandableLayout) itemView.findViewById(R.id.password_expandable);
            header = (RelativeLayout) itemView.findViewById(R.id.password_header);
            organisation = (TextView) itemView.findViewById(R.id.org_name);
            orgLogin = (TextView) itemView.findViewById(R.id.org_login);
            login = (TextView) itemView.findViewById(R.id.login);
            password = (TextView) itemView.findViewById(R.id.password);
            extraInfo = (TextView) itemView.findViewById(R.id.extra_info);
            link = (TextView) itemView.findViewById(R.id.link);
            arrowIcon = (ImageView) itemView.findViewById(R.id.arrow_icon);
            cardViewLayout = (LinearLayout) itemView.findViewById(R.id.cardview_layout);
            editIcon = (ImageView) itemView.findViewById(R.id.edit_icon);
            deleteIcon = (ImageView) itemView.findViewById(R.id.delete_icon);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public TextView getExtraInfo() {
            return extraInfo;
        }

        public TextView getLink() {
            return link;
        }

        public TextView getLogin() {
            return login;
        }

        public TextView getOrgLogin() {
            return orgLogin;
        }

        public TextView getOrganisation() {
            return organisation;
        }

        public TextView getPassword() {
            return password;
        }

        public ImageView getEditIcon(){
            return editIcon;
        }

        public ImageView getDeleteIcon(){
            return deleteIcon;
        }

        public LinearLayout getLayout(){
            return this.cardViewLayout;
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onLongClick(view, getPosition());
            return true;
        }
    }
}
