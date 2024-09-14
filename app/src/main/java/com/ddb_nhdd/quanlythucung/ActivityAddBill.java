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
import android.widget.RadioButton;
import android.widget.Toast;

import com.ddb_nhdd.quanlythucung.database.Database;
import com.ddb_nhdd.quanlythucung.model.Bill;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ActivityAddBill extends AppCompatActivity {
    Button btnAddBill, btnTroLai;
    EditText edtBillID, edtBillKind, edtBillPerson, edtBillDate, edtBillTotal, edtBillNote;
    public static Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        edtBillID = findViewById(R.id.edtBillID);
        edtBillKind = findViewById(R.id.edtBillKind);
        edtBillPerson = findViewById(R.id.edtAddPerson);
        edtBillDate = findViewById(R.id.edtDateAdd);
        edtBillTotal = findViewById(R.id.edtBillTotal);
        edtBillNote = findViewById(R.id.edtNote);

        btnAddBill = findViewById(R.id.btnAddBill);
        btnTroLai = findViewById(R.id.btnTroLai);

        database = new Database(this);

        btnAddBill.setOnClickListener(new View.OnClickListener() {
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

    }

    private void DialogAdd() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogadd);
        dialog.setCanceledOnTouchOutside(false);

        Button btnThem = dialog.findViewById(R.id.btnCoAdd);
        Button btnKhongThem = dialog.findViewById(R.id.btnKhongAdd);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (edtBillID.getText().toString().trim().isEmpty() || edtBillKind.getText().toString().trim().isEmpty() || edtBillPerson.getText().toString().trim().isEmpty() || edtBillDate.getText().toString().trim().isEmpty() || edtBillTotal.getText().toString().trim().isEmpty()) {
                        Toast.makeText(ActivityAddBill.this, "Bạn chưa nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    } else {

                        database.Insert_Bill(
                                edtBillID.getText().toString().trim(),
                                edtBillKind.getText().toString().trim(),
                                edtBillPerson.getText().toString().trim(),
                                edtBillDate.getText().toString().trim(),
                                edtBillTotal.getText().toString().trim(),
                                edtBillNote.getText().toString().trim()
                        );
                        Intent intent = new Intent(ActivityAddBill.this, ActivityBill.class);
                        startActivity(intent);
                        Toast.makeText(ActivityAddBill.this, "Đã thêm thành công!", Toast.LENGTH_SHORT).show();
                    }
            }
        });
        btnKhongThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

}
