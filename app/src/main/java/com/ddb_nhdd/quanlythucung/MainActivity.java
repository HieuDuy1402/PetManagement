package com.ddb_nhdd.quanlythucung;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final com.google.android.material.card.MaterialCardView infoMaterialCardView = findViewById(R.id.btnInfo);
        infoMaterialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAuthor();
            }
        });


        final com.google.android.material.card.MaterialCardView petMaterialCardView = findViewById(R.id.btnPet);
        petMaterialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivitySubject.class);
                startActivity(intent);
            }
        });

        final com.google.android.material.card.MaterialCardView billMaterialCardView = findViewById(R.id.btnBill);
        billMaterialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityBill.class);
                startActivity(intent);
            }
        });

        final com.google.android.material.card.MaterialCardView exitMaterialCardView = findViewById(R.id.btnExit);
        exitMaterialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogExit();
            }
        });
    }
    // Hàm hiển thị cửa sổ dialog Exit
    private void DialogExit() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogexit);

        dialog.setCanceledOnTouchOutside(false);

        Button btnCo = dialog.findViewById(R.id.btnCoEx);
        Button btnKhong = dialog.findViewById(R.id.btnKhongEx);

        btnCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);

                Intent intent1 = new Intent(Intent.ACTION_MAIN);
                intent1.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent1);
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

    private void DialogAuthor() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialoginformation);
        dialog.show();
    }
}