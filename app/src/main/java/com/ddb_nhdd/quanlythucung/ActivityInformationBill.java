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

public class ActivityInformationBill extends AppCompatActivity {
    Button btnTroLai;
    TextView tvBillID, tvBillKind, tvBillPerson, tvBillDate, tvBillTotal,tvBillNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_bill);


        btnTroLai = findViewById(R.id.btnTroLai);
        tvBillID = findViewById(R.id.tvBillID);
        tvBillKind = findViewById(R.id.tvBillKind);
        tvBillPerson = findViewById(R.id.tvAddPerson);
        tvBillDate = findViewById(R.id.tvAddDate);
        tvBillTotal = findViewById(R.id.tvBillTotal);
        tvBillNote = findViewById(R.id.tvNote);

            btnTroLai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            Intent intent = getIntent();
            String billid = intent.getStringExtra("billid");
            String billkind = intent.getStringExtra("billkind");
            String billperson = intent.getStringExtra("billperson");
            String billdate = intent.getStringExtra("billdate");
            String billtotal = intent.getStringExtra("billtotal");
            String billnote = intent.getStringExtra("billnote");

        tvBillID.setText(billid);
        tvBillKind.setText(billkind);
        tvBillPerson.setText(billperson);
        tvBillDate.setText(billdate);
        tvBillTotal.setText(billtotal);
        tvBillNote.setText(billnote);

    }
}