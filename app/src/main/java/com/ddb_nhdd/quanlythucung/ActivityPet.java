package com.ddb_nhdd.quanlythucung;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.ddb_nhdd.quanlythucung.adapter.AdapterPet;
import com.ddb_nhdd.quanlythucung.database.Database;
import com.ddb_nhdd.quanlythucung.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class ActivityPet extends AppCompatActivity {

    Toolbar toolbar;
    ListView lvpet;

    ArrayList<Pet> ArrayListPet;
    Database database;
    AdapterPet adapterPet;

    int id_subject = 0;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        toolbar = findViewById(R.id.toolbarPet);
        lvpet = findViewById(R.id.listviewPet);

        Intent intent = getIntent();
        id_subject = intent.getIntExtra("id_subject", 0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new Database(this);

        ArrayListPet = new ArrayList<>();
        ArrayListPet.clear();

        Cursor cursor = database.getDataPet(id_subject);
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            byte[] avatar = cursor.getBlob(1);
            String soid = cursor.getString(2);
            String ten = cursor.getString(3);
            String giong = cursor.getString(4);
            String ngaysinh = cursor.getString(5);
            String noicungcap = cursor.getString(6);
            String ghichu = cursor.getString(7);
            int id_subject = cursor.getInt(8);

            ArrayListPet.add(new Pet(id, avatar, soid, ten, giong, ngaysinh, noicungcap, ghichu, id_subject));
        }
        adapterPet = new AdapterPet(ActivityPet.this, ArrayListPet);

        // Hiển thị listview
        lvpet.setAdapter(adapterPet);
        cursor.moveToFirst();
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddpet,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(R.id.menuAddPet == item.getItemId()) {
            Intent intent = new Intent(ActivityPet.this, ActivityAddPet.class);
            intent.putExtra("id_subject",id_subject);
            startActivity(intent);
        } else {
            Intent intent1 = new Intent(ActivityPet.this, ActivitySubject.class);
            startActivity(intent1);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        counter++;
        if(counter>=1) {
            Intent intent = new Intent(this, ActivitySubject.class);
            startActivity(intent);
            finish();
        }
    }

    public void information(final int pos) {
        try {
            Cursor cursor = database.getDataPet(id_subject);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                if (id == pos) {
                    Intent intent = new Intent(ActivityPet.this, ActivityInformationPet.class);
                    intent.putExtra("id", pos);

                    byte[] avatar = cursor.getBlob(1);
                    String soid = cursor.getString(2);
                    String ten =  cursor.getString(3);
                    String giong = cursor.getString(4);
                    String ngaysinh = cursor.getString(5);
                    String noicungcap = cursor.getString(6);
                    String ghichu = cursor.getString(7);
                    int id_subject = cursor.getInt(8);

                    intent.putExtra("petavatar", avatar);
                    intent.putExtra("petsoid", soid);
                    intent.putExtra("petten", ten);
                    intent.putExtra("giong", giong);
                    intent.putExtra("petngaysinh", ngaysinh);
                    intent.putExtra("noicungcap", noicungcap);
                    intent.putExtra("petghichu", ghichu);
                    intent.putExtra("id_subject", id_subject);

                    startActivity(intent);
                }
            }
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void update(final int id_pet) {

        try {
            Cursor cursor = database.getDataPet(id_subject);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                if (id == id_pet) {
                    Intent intent = new Intent(ActivityPet.this, ActivityUpdatePet.class);
                    intent.putExtra("idpet", id);

                    byte[] avatar = cursor.getBlob(1);
                    String soid = cursor.getString(2);
                    String tenpet =  cursor.getString(3);
                    String giong = cursor.getString(4);
                    String ngaysinh = cursor.getString(5);
                    String noicungcap = cursor.getString(6);
                    String ghichu = cursor.getString(7);
                    int id_subject = cursor.getInt(8);

                    intent.putExtra("petavatar", avatar);
                    intent.putExtra("petsoid", soid);
                    intent.putExtra("petten", tenpet);
                    intent.putExtra("giong", giong);
                    intent.putExtra("petngaysinh", ngaysinh);
                    intent.putExtra("noicungcap", noicungcap);
                    intent.putExtra("petghichu", ghichu);
                    intent.putExtra("id_subject", id_subject);

                    startActivity(intent);
                }
            }
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void delete(final int id_pet) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogdeletesubject);
        dialog.setCanceledOnTouchOutside(false);

        Button btnCo = dialog.findViewById(R.id.btnCoDelete);
        Button btnKhong = dialog.findViewById(R.id.btnKhongDelete);

        btnCo .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.DeletePet(id_pet);

                Intent intent = new Intent(ActivityPet.this, ActivityPet.class);
                intent.putExtra("id_subject", id_subject);
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