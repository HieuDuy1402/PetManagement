package com.ddb_nhdd.quanlythucung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ddb_nhdd.quanlythucung.model.Subject;

public class ActivitySubjectInformation extends AppCompatActivity {

    ImageView imageViewInforSubject;
    Button btnTroLai;
    TextView tvMaPhanLoai, tvPhanLoai, tvNgayThem, tvGhiChu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_information);

        btnTroLai = findViewById(R.id.btnTroLai);
        imageViewInforSubject = findViewById(R.id.ivInforSubject);
        tvMaPhanLoai = findViewById(R.id.tvMaPhanLoai);
        tvPhanLoai = findViewById(R.id.tvPhanLoai);
        tvNgayThem = findViewById(R.id.tvNgayThem);
        tvGhiChu = findViewById(R.id.tvGhiChu);

        btnTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // Lấy dữ liệu
        Intent intent = getIntent();
        byte[] avatar = intent.getByteArrayExtra("subjectavatar");
        String maphanloai = intent.getStringExtra("subjectmpl");
        String phanloai = intent.getStringExtra("subjectpl");
        String ngaythem = intent.getStringExtra("subjectngaythem");
        String ghichu = intent.getStringExtra("subjectghichu");

        // Gán giá trị

        Bitmap bitmap = BitmapFactory.decodeByteArray(avatar, 0, avatar.length);
        imageViewInforSubject.setImageBitmap(bitmap);
        tvMaPhanLoai.setText(maphanloai);
        tvPhanLoai.setText(phanloai);
        tvNgayThem.setText(ngaythem);
        tvGhiChu.setText(ghichu);
    }

}