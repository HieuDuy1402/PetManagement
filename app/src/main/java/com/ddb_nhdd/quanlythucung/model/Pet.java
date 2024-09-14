package com.ddb_nhdd.quanlythucung.model;

public class Pet {
    private int id_pet;
    private byte[] pet_avatar;
    private String pet_soid;
    private String pet_ten;
    private String pet_ngaysinh;
    private String pet_giong;
    private String pet_noicungcap;
    private String pet_ghichu;
    private int id_subject;

    public Pet(byte[] pet_avatar ,String pet_soid, String pet_ten, String pet_ngaysinh, String pet_giong, String pet_noicungcap, String pet_ghichu) {
        this.pet_avatar = pet_avatar;
        this.pet_soid = pet_soid;
        this.pet_ten = pet_ten;
        this.pet_ngaysinh = pet_ngaysinh;
        this.pet_giong = pet_giong;
        this.pet_noicungcap = pet_noicungcap;
        this.pet_ghichu = pet_ghichu;
    }

    public Pet(byte[] pet_avatar ,String pet_soid, String pet_ten, String pet_ngaysinh, String pet_giong, String pet_noicungcap, String pet_ghichu, int id_subject) {
        this.pet_avatar = pet_avatar;
        this.pet_soid = pet_soid;
        this.pet_ten = pet_ten;
        this.pet_ngaysinh = pet_ngaysinh;
        this.pet_giong = pet_giong;
        this.pet_noicungcap = pet_noicungcap;
        this.pet_ghichu = pet_ghichu;
        this.id_subject = id_subject;
    }

    public Pet(int id_pet, byte[] pet_avatar, String pet_soid, String pet_ten, String pet_ngaysinh, String pet_giong, String pet_noicungcap, String pet_ghichu, int id_subject) {
        this.id_pet = id_pet;
        this.pet_avatar = pet_avatar;
        this.pet_soid = pet_soid;
        this.pet_ten = pet_ten;
        this.pet_ngaysinh = pet_ngaysinh;
        this.pet_giong = pet_giong;
        this.pet_noicungcap = pet_noicungcap;
        this.pet_ghichu = pet_ghichu;
        this.id_subject = id_subject;
    }

    public int getId_pet() {
        return id_pet;
    }

    public void setId_pet(int id_pet) {
        this.id_pet = id_pet;
    }

    public byte[] getPet_avatar() {
        return pet_avatar;
    }

    public void setPet_avatar(byte[] pet_avatar) {
        this.pet_avatar = pet_avatar;
    }

    public String getPet_soid() {
        return pet_soid;
    }

    public void setPet_soid(String pet_soid) {
        this.pet_soid = pet_soid;
    }

    public String getPet_ten() {
        return pet_ten;
    }

    public void setPet_ten(String pet_ten) {
        this.pet_ten = pet_ten;
    }

    public String getPet_ngaysinh() {
        return pet_ngaysinh;
    }

    public void setPet_ngaysinh(String pet_ngaysinh) {
        this.pet_ngaysinh = pet_ngaysinh;
    }

    public String getPet_giong() {
        return pet_giong;
    }

    public void setPet_giong(String pet_giong) {
        this.pet_giong = pet_giong;
    }

    public String getPet_noicungcap() {
        return pet_noicungcap;
    }

    public void setPet_noicungcap(String pet_noicungcap) {
        this.pet_noicungcap = pet_noicungcap;
    }

    public String getPet_ghichu() {
        return pet_ghichu;
    }

    public void setPet_ghichu(String pet_ghichu) {
        this.pet_ghichu = pet_ghichu;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }
}


