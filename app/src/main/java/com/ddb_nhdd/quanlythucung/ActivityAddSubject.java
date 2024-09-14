package com.ddb_nhdd.quanlythucung;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ddb_nhdd.quanlythucung.database.Database;
import com.ddb_nhdd.quanlythucung.model.Subject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.sql.Blob;
import java.util.BitSet;

public class ActivityAddSubject extends AppCompatActivity {

    Button btnAddSubject, buttonULImg, btnTroLai;
    ImageView imageviewAddImgSubject;
    EditText edtMaPhanLoai, edtPhanLoai, edtNgayThem, edtGhiChu;
    public static Database database;

    int REQUEST_CODE_FOLDER = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        btnTroLai = findViewById(R.id.btnTroLai);
        imageviewAddImgSubject = findViewById(R.id.ivAddImgSubject);
        buttonULImg = findViewById(R.id.btnULImg);
        btnAddSubject = findViewById(R.id.buttonAddSubject);
        edtMaPhanLoai = findViewById(R.id.edittextMaPhanLoai);
        edtPhanLoai = findViewById(R.id.edittextPhanLoai);
        edtNgayThem = findViewById(R.id.edittextNgayThem);
        edtGhiChu = findViewById(R.id.edittextGhiChu);

        database = new Database(this);

        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd();
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

    private void DialogAdd() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogadd);
        dialog.setCanceledOnTouchOutside(false);

        Button btnCo = dialog.findViewById(R.id.btnCoAdd);
        Button btnKhong = dialog.findViewById(R.id.btnKhongAdd);

        btnCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageviewAddImgSubject.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] avatar = byteArray.toByteArray();

                if(edtMaPhanLoai.getText().toString().trim().isEmpty() || edtPhanLoai.getText().toString().trim().isEmpty() || edtNgayThem.getText().toString().trim().isEmpty()) {
                    Toast.makeText(ActivityAddSubject.this, "Bạn chưa nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
                else {
                    database.Insert_Subject(

                            avatar,
                            edtMaPhanLoai.getText().toString().trim(),
                            edtPhanLoai.getText().toString().trim(),
                            edtNgayThem.getText().toString().trim(),
                            edtGhiChu.getText().toString().trim()
                    );
                    Intent intent = new Intent(ActivityAddSubject.this, ActivitySubject.class);
                    startActivity(intent);
                    Toast.makeText(ActivityAddSubject.this, "Đã thêm thành công!", Toast.LENGTH_SHORT).show();
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
                imageviewAddImgSubject.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}