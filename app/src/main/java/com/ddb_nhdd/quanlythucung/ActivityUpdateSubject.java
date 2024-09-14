package com.ddb_nhdd.quanlythucung;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ddb_nhdd.quanlythucung.database.Database;
import com.ddb_nhdd.quanlythucung.model.Subject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ActivityUpdateSubject extends AppCompatActivity {

    EditText edtUpdateMaPhanLoai, edtUpdatePhanLoai, edtUpdateNgayThem, edtUpdateGhiChu;
    ImageView imageviewUpdateSubject;
    Button btnCapNhat, buttonULImg, btnTroLai;

    com.ddb_nhdd.quanlythucung.database.Database database;
    int REQUEST_CODE_FOLDER = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);

        btnCapNhat = findViewById(R.id.buttonUpdateSubject);
        btnTroLai = findViewById(R.id.btnTroLai);
        buttonULImg = findViewById(R.id.btnULImg);

        imageviewUpdateSubject = findViewById(R.id.ivUpdateSubject);
        edtUpdateMaPhanLoai = findViewById(R.id.edittextUpdateMaPhanLoai);
        edtUpdatePhanLoai = findViewById(R.id.edittextUpdatePhanLoai);
        edtUpdateNgayThem = findViewById(R.id.edittextUpdateNgayThem);
        edtUpdateGhiChu = findViewById(R.id.edittextUpdateGhiChu);


        Intent intent = getIntent();
        int id = intent.getIntExtra("idsubject", 0);

        byte[] avatar = intent.getByteArrayExtra("subjectavatar");
        String maphanloai = intent.getStringExtra("subjectmpl");
        String phanloai = intent.getStringExtra("subjectpl");
        String ngaythem = intent.getStringExtra("subjectngaythem");
        String ghichu = intent.getStringExtra("subjectghichu");

        Bitmap bitmap = BitmapFactory.decodeByteArray(avatar, 0, avatar.length);
        imageviewUpdateSubject.setImageBitmap(bitmap);
        edtUpdateMaPhanLoai.setText(maphanloai);
        edtUpdatePhanLoai.setText(phanloai);
        edtUpdateNgayThem.setText(ngaythem);
        edtUpdateGhiChu.setText(ghichu);

        database = new Database(this);

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpDate(id);
            }
        });

        btnTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        buttonULImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });

    }

    private void DialogUpDate(int id) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogupdatesubject);
        dialog.setCanceledOnTouchOutside(false);

        Button btnCo = dialog.findViewById(R.id.btnCoUp);
        Button btnKhong = dialog.findViewById(R.id.btnKhongUp);

        btnCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imageviewUpdateSubject.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                    byte[] avatar = byteArray.toByteArray();

                    if (edtUpdateMaPhanLoai.getText().toString().trim().isEmpty() || edtUpdatePhanLoai.getText().toString().trim().isEmpty() || edtUpdateNgayThem.getText().toString().trim().isEmpty()) {
                        Toast.makeText(ActivityUpdateSubject.this, "Bạn chưa nhập đủ dữ liệu!", Toast.LENGTH_SHORT).show();
                    } else {
                        database.Update_Subject(
                                avatar,
                                edtUpdateMaPhanLoai.getText().toString().trim(),
                                edtUpdatePhanLoai.getText().toString().trim(),
                                edtUpdateNgayThem.getText().toString().trim(),
                                edtUpdateGhiChu.getText().toString().trim(),
                                id
                        );
                        Intent intent = new Intent(ActivityUpdateSubject.this, ActivitySubject.class);
                        startActivity(intent);
                        Toast.makeText(ActivityUpdateSubject.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        btnKhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageviewUpdateSubject.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}