package com.ddb_nhdd.quanlythucung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ddb_nhdd.quanlythucung.adapter.AdapterBill;
import com.ddb_nhdd.quanlythucung.adapter.AdapterPet;
import com.ddb_nhdd.quanlythucung.adapter.AdapterSubject;
import com.ddb_nhdd.quanlythucung.database.Database;
import com.ddb_nhdd.quanlythucung.model.Bill;
import com.ddb_nhdd.quanlythucung.model.Pet;
import com.ddb_nhdd.quanlythucung.model.Subject;

import java.util.ArrayList;

public class ActivityBill extends AppCompatActivity {

    Toolbar tlbBill;
    ListView lvBill;
    ArrayList<Bill> ArrayListBill;
    Database database;
    AdapterBill adapterBill;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        tlbBill = findViewById(R.id.tlbBill);
        lvBill = findViewById(R.id.lvBill);


        setSupportActionBar(tlbBill);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new Database(this);

        ArrayListBill = new ArrayList<>();

        Cursor cursor = database.getDataBill();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String billid = cursor.getString(1);
            String billkind = cursor.getString(2);
            String billperson = cursor.getString(3);
            String billdate = cursor.getString(4);
            String billtotal = cursor.getString(5);
            String billnote = cursor.getString(6);

            ArrayListBill.add(new Bill(id, billid, billkind, billperson, billdate, billtotal, billnote));
        }

        adapterBill = new AdapterBill(ActivityBill.this, ArrayListBill);
        lvBill.setAdapter(adapterBill);
        cursor.moveToFirst();
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(R.id.menuAdd == item.getItemId()) {
            Intent intent1 = new Intent(ActivityBill.this, ActivityAddBill.class);
            startActivity(intent1);
        } else {
            Intent intent = new Intent(ActivityBill.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        counter++;
        if(counter>=1) {
            Intent intent = new Intent(ActivityBill.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void information(final int pos) {
        try {
            Cursor cursor = database.getDataBill();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                if (id == pos) {
                    Intent intent = new Intent(ActivityBill.this, ActivityInformationBill.class);
                    intent.putExtra("id", id);

                    String billid = cursor.getString(1);
                    String billkind = cursor.getString(2);
                    String billperson = cursor.getString(3);
                    String billdate = cursor.getString(4);
                    String billtotal = cursor.getString(5);
                    String billnote = cursor.getString(6);

                    intent.putExtra("billid", billid);
                    intent.putExtra("billkind", billkind);
                    intent.putExtra("billperson", billperson);
                    intent.putExtra("billdate", billdate);
                    intent.putExtra("billtotal", billtotal);
                    intent.putExtra("billnote", billnote);

                    startActivity(intent);
                }
            }
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void delete(final int position) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogdeletesubject);

        dialog.setCanceledOnTouchOutside(false);

        Button btnCo = dialog.findViewById(R.id.btnCoDelete);
        Button btnKhong = dialog.findViewById(R.id.btnKhongDelete);

        btnCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.DeleteBill(position);

                Intent intent = new Intent(ActivityBill.this, ActivityBill.class);
                startActivity(intent);
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
}