package com.ddb_nhdd.quanlythucung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityInformationPet extends AppCompatActivity {
    ImageView imageViewInforPet;
    Button btnTroLai;
    TextView tvSoID, tvTen, tvNgaySinh, tvGiong,tvNoiCungCap, tvGhiChu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_pet);

        btnTroLai = findViewById(R.id.btnTroLai);
        imageViewInforPet = findViewById(R.id.ivInforPet);
        tvSoID = findViewById(R.id.textviewSoId);
        tvTen = findViewById(R.id.textviewTen);
        tvNgaySinh = findViewById(R.id.textviewNgaySinh);
        tvGiong = findViewById(R.id.textviewGiong);
        tvNoiCungCap = findViewById(R.id.textviewNoiCungCap);
        tvGhiChu = findViewById(R.id.textviewGhiChu);

        btnTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();

        byte[] avatar = intent.getByteArrayExtra("petavatar");
        String soid = intent.getStringExtra("petsoid");
        String ten = intent.getStringExtra("petten");
        String giong = intent.getStringExtra("giong");
        String ngaysinh = intent.getStringExtra("petngaysinh");
        String noicungcap = intent.getStringExtra("noicungcap");
        String ghichu = intent.getStringExtra("petghichu");

        Bitmap bitmap = BitmapFactory.decodeByteArray(avatar, 0, avatar.length);
        imageViewInforPet.setImageBitmap(bitmap);
        tvSoID.setText(soid);
        tvTen.setText(ten);
        tvGiong.setText(giong);
        tvNgaySinh.setText(ngaysinh);
        tvNoiCungCap.setText(noicungcap);
        tvGhiChu.setText(ghichu);
    }
}