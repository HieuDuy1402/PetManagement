package com.ddb_nhdd.quanlythucung;

import static com.ddb_nhdd.quanlythucung.R.id.ivPL;
import static com.ddb_nhdd.quanlythucung.R.id.menuAdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.ddb_nhdd.quanlythucung.adapter.AdapterSubject;
import com.ddb_nhdd.quanlythucung.database.Database;
import com.ddb_nhdd.quanlythucung.model.Subject;

import java.sql.Blob;
import java.util.ArrayList;

public class ActivitySubject extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewSubject;
    ArrayList<Subject> ArrayListSubject;
    com.ddb_nhdd.quanlythucung.database.Database database;
    com.ddb_nhdd.quanlythucung.adapter.AdapterSubject adapterSubject;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        toolbar = findViewById(R.id.toolbarSubject);
        listViewSubject = findViewById(R.id.listviewSubject);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new Database(this);

        ArrayListSubject = new ArrayList<>();

        Cursor cursor = database.getDataSubject();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            byte[] avatar = cursor.getBlob(1);
            String maphanloai = cursor.getString(2);
            String phanloai = cursor.getString(3);
            String ngaythem = cursor.getString(4);
            String ghichu = cursor.getString(5);

            ArrayListSubject.add(new Subject(id, avatar, maphanloai, phanloai, ngaythem, ghichu));
        }

        adapterSubject = new AdapterSubject(ActivitySubject.this, ArrayListSubject);
        listViewSubject.setAdapter(adapterSubject);
        cursor.moveToFirst();
        cursor.close();



        listViewSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivitySubject.this, ActivityPet.class);
                int id_subject = ArrayListSubject.get(position).getId();
                // truyền dữ liệu id subject qua activity pet
                intent.putExtra("id_subject", id_subject);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            Intent intent1 = new Intent(ActivitySubject.this, ActivityAddSubject.class);
            startActivity(intent1);
        } else {
            Intent intent = new Intent(ActivitySubject.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        count++;
        if (count>=1) {
            Intent intent = new Intent(ActivitySubject.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void information(final int pos) {
        try {
            Cursor cursor = database.getDataSubject();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                if (id == pos) {
                    Intent intent = new Intent(ActivitySubject.this, ActivitySubjectInformation.class);
                    intent.putExtra("id", id);

                    byte[] avatar = cursor.getBlob(1);
                    String maphanloai = cursor.getString(2);
                    String phanloai = cursor.getString(3);
                    String ngaythem = cursor.getString(4);
                    String ghichu = cursor.getString(5);

                    intent.putExtra("subjectavatar", avatar);
                    intent.putExtra("subjectmpl", maphanloai);
                    intent.putExtra("subjectpl", phanloai);
                    intent.putExtra("subjectngaythem", ngaythem);
                    intent.putExtra("subjectghichu", ghichu);

                    startActivity(intent);
                }
            }
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
                database.DeleteSubject(position);
                database.DeleteSubjectPet(position);

                Intent intent = new Intent(ActivitySubject.this, ActivitySubject.class);
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

    public void update(final int pos) {
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);

            if (id == pos) {
                Intent intent = new Intent(ActivitySubject.this, ActivityUpdateSubject.class);
                intent.putExtra("idsubject", id);

                byte[] avatar = cursor.getBlob(1);
                String maphanloai = cursor.getString(2);
                String phanloai = cursor.getString(3);
                String ngaythem = cursor.getString(4);
                String ghichu = cursor.getString(5);

                intent.putExtra("subjectavatar", avatar);
                intent.putExtra("subjectmpl", maphanloai);
                intent.putExtra("subjectpl", phanloai);
                intent.putExtra("subjectngaythem", ngaythem);
                intent.putExtra("subjectghichu", ghichu);

                startActivity(intent);
            }
        }
    }
}