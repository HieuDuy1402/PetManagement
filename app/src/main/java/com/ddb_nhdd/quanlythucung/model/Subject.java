package com.ddb_nhdd.quanlythucung.model;

import android.graphics.Bitmap;

public class Subject {

    private int id;
    private byte[] subject_avatar;
    private String subject_mpl;
    private String subject_pl;
    private String subject_ngaythem;
    private String subject_ghichu;

    public Subject(byte[] subject_avatar, String subject_mpl, String subject_pl, String subject_ngaythem, String subject_ghichu) {
        this.subject_avatar = subject_avatar;
        this.subject_mpl = subject_mpl;
        this.subject_pl = subject_pl;
        this.subject_ngaythem = subject_ngaythem;
        this.subject_ghichu = subject_ghichu;
    }

    public Subject(int id, byte[] subject_avatar, String subject_mpl, String subject_pl, String subject_ngaythem, String subject_ghichu) {
        this.id = id;
        this.subject_avatar = subject_avatar;
        this.subject_mpl = subject_mpl;
        this.subject_pl = subject_pl;
        this.subject_ngaythem = subject_ngaythem;
        this.subject_ghichu = subject_ghichu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getSubject_avatar() {
        return subject_avatar;
    }

    public void setSubject_avatar(byte[] subject_avatar) {
        this.subject_avatar = subject_avatar;
    }

    public String getSubject_mpl() {
        return subject_mpl;
    }

    public void setSubject_mpl(String subject_mpl) {
        this.subject_mpl = subject_mpl;
    }

    public String getSubject_pl() {
        return subject_pl;
    }

    public void setSubject_pl(String subject_pl) {
        this.subject_pl = subject_pl;
    }

    public String getSubject_ngaythem() {
        return subject_ngaythem;
    }

    public void setSubject_ngaythem(String subject_ngaythem) {
        this.subject_ngaythem = subject_ngaythem;
    }

    public String getSubject_ghichu() {
        return subject_ghichu;
    }

    public void setSubject_ghichu(String subject_ghichu) {
        this.subject_ghichu = subject_ghichu;
    }
}

