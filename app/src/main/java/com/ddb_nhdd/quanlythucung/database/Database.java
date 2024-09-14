package com.ddb_nhdd.quanlythucung.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.ddb_nhdd.quanlythucung.model.Bill;
import com.ddb_nhdd.quanlythucung.model.Pet;
import com.ddb_nhdd.quanlythucung.model.Subject;

public class Database extends SQLiteOpenHelper {


    private static String DATABASE_NAME = "quanlythucung"; // Ten Database

    // Bang subject
    private static String TABLE_SUBJECT = "subject";
    private static String ID_SUBJECT = "idsubject";
    private static String SUBJECT_AVATAR = "subjectavatar";
    private static String SUBJECT_MAPHANLOAI = "subjectmpl";
    private static String SUBJECT_PHANLOAI = "subjectpl";
    private static String SUBJECT_NGAYTHEM = "subjectngaythem";
    private static String SUBJECT_GHICHU = "subjectghichu";
    private static int VERSION = 1;

    //Bang Pet
    private static String TABLE_PET = "pet";
    private static String ID_PET = "idpet";
    private static String PET_AVATAR = "petavatar";
    private static String PET_SOID = "petsoid";
    private static String PET_TEN = "petten";
    private static String PET_NGAYSINH = "petngaysinh";
    private static String PET_GIONG = "giong";
    private static String PET_NOICUNGCAP = "noicungcap";
    private static String PET_GHICHU = "petghichu";

    //Bảng Bill
    private static String TABLE_BILL = "bill";
    private static String ID_BILL = "idbill";
    private static String BILL_ID = "billid";
    private static String BILL_KIND = "billkind";
    private static String BILL_PERSON = "billperson";
    private static String BILL_DATE = "billdate";
    private static String BILL_TOTAL = "billtotal";
    private static String BILL_NOTE = "billnote";


    private String SQLQuery1 = "CREATE TABLE "+ TABLE_SUBJECT +" ( "+ID_SUBJECT+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +SUBJECT_AVATAR+" BLOB, "
            +SUBJECT_MAPHANLOAI+" TEXT, "
            +SUBJECT_PHANLOAI+" TEXT, "
            +SUBJECT_NGAYTHEM+" TEXT, "
            +SUBJECT_GHICHU+" TEXT) ";
    private String SQLQuery2 = "CREATE TABLE "+ TABLE_PET +" ( "+ID_PET+" integer primary key AUTOINCREMENT, "
            +PET_AVATAR+" BLOB, "
            +PET_SOID+" TEXT, "
            +PET_TEN+" TEXT, "
            +PET_GIONG+" TEXT, "
            +PET_NGAYSINH+" TEXT, "
            +PET_NOICUNGCAP+" TEXT, "
            +PET_GHICHU+" TEXT, "
            +ID_SUBJECT+" INTEGER , FOREIGN KEY ( "+ ID_SUBJECT +" ) REFERENCES "+
            TABLE_SUBJECT+"("+ID_SUBJECT+"))";
    private String SQLQuery3 = "CREATE TABLE "+ TABLE_BILL +" ( "+ID_BILL+" integer primary key AUTOINCREMENT, "
            +BILL_ID+" TEXT, "
            +BILL_KIND+" TEXT, "
            +BILL_PERSON+" TEXT, "
            +BILL_DATE+" TEXT, "
            +BILL_TOTAL+" TEXT, "
            +BILL_NOTE+" TEXT) ";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery1);
        db.execSQL(SQLQuery2);
        db.execSQL(SQLQuery3);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void Insert_Subject(byte[] subjectavatar, String maphanloai, String phanloai, String ngaythem, String ghichu) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO " + TABLE_SUBJECT + " VALUES(null, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1, subjectavatar);
        statement.bindString(2, maphanloai);
        statement.bindString(3, phanloai);
        statement.bindString(4, ngaythem);
        statement.bindString(5, ghichu);
        statement.executeInsert();
    }
    public void Update_Subject(byte[] subjectavatar, String maphanloai, String phanloai, String ngaythem, String ghichu, int id) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE " + TABLE_SUBJECT + " SET " +
                SUBJECT_AVATAR + " = ?, " +
                SUBJECT_MAPHANLOAI + " = ?, " +
                SUBJECT_PHANLOAI + " = ?, " +
                SUBJECT_NGAYTHEM + " = ?, " +
                SUBJECT_GHICHU + " = ? WHERE " + ID_SUBJECT + " = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1, subjectavatar);
        statement.bindString(2, maphanloai);
        statement.bindString(3, phanloai);
        statement.bindString(4, ngaythem);
        statement.bindString(5, ghichu);
        statement.bindLong(6, id);
        statement.executeUpdateDelete();
    }

    public Cursor getDataSubject() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_SUBJECT, null);
        return cursor;
    }
    public int DeleteSubject(int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_SUBJECT, ID_SUBJECT+" = "+i, null);
        return res;
    }
    public int DeleteSubjectPet(int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_PET, ID_SUBJECT+" = "+i, null);
        return res;
    }

    public void Insert_Pet(byte[] petavatar, String petsoid, String petten, String petngaysinh, String giong, String noicungcap, String petghichu, int id_subject) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO " + TABLE_PET + " VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1, petavatar);
        statement.bindString(2, petsoid);
        statement.bindString(3, petten);
        statement.bindString(4, petngaysinh);
        statement.bindString(5, giong);
        statement.bindString(6, noicungcap);
        statement.bindString(7, petghichu);
        statement.bindLong(8, id_subject);
        statement.executeInsert();
    }
    public void Update_Pet(byte[] petavatar, String petsoid, String petten, String petngaysinh, String giong, String noicungcap, String petghichu, int id) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE " + TABLE_PET + " SET " +
                PET_AVATAR + " = ?, " +
                PET_SOID + " = ?, " +
                PET_TEN + " = ?, " +
                PET_NGAYSINH + " = ?, " +
                PET_GIONG + " = ?, " +
                PET_NOICUNGCAP + " = ?, " +
                PET_GHICHU + " = ? WHERE " + ID_PET + " = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1, petavatar);
        statement.bindString(2, petsoid);
        statement.bindString(3, petten);
        statement.bindString(4, petngaysinh);
        statement.bindString(5, giong);
        statement.bindString(6, noicungcap);
        statement.bindString(7, petghichu);
        statement.bindLong(8, id);
        statement.executeUpdateDelete();
    }

    public Cursor getDataPet(int id_subject) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_PET+" WHERE "+ID_SUBJECT+" = "+id_subject, null);
        return res;
    }
    public int DeletePet(int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_PET, ID_PET+" = "+i, null);
        return res;
    }

    public void Insert_Bill(String billid, String billkind, String billperson, String billdate, String billtotal, String billnote) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO " + TABLE_BILL + " VALUES(null, ?, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, billid);
        statement.bindString(2, billkind);
        statement.bindString(3, billperson);
        statement.bindString(4, billdate);
        statement.bindString(5, billtotal);
        statement.bindString(6, billnote);
        statement.executeInsert();
    }

    public Cursor getDataBill() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_BILL, null);
        return cursor;
    }
    public int DeleteBill(int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_BILL, ID_BILL+" = "+i, null);
        return res;
    }
}
