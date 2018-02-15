package com.example.vitaly.topsecret;

import io.realm.RealmObject;

/**
 * Created by Vitaly on 09.02.2018.
 */

public class PasswordElement extends RealmObject{
    private String organisation;
    private String login;
    private String password;
    private String extraInfo;
    private String link;

    public PasswordElement(){}

    public PasswordElement(String org, String log, String pass, String info, String link){
        organisation = org;
        login = log;
        password = pass;
        extraInfo = info;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public String getLogin() {
        return login;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }
}
