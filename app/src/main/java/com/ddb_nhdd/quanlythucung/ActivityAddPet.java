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
import com.ddb_nhdd.quanlythucung.model.Pet;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ActivityAddPet extends AppCompatActivity {

    Button buttonAddPet, buttonULImgPet, btnTroLai;
    ImageView imageviewAddImgPet;
    EditText edtSoId, edtTen, edtNgaySinh, edtNoiCungCap, edtGhiChu;
    RadioButton radiobtnMale, radiobtnFemale;
    public static Database database;
    int REQUEST_CODE_FOLDER = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        btnTroLai = findViewById(R.id.btnTroLai);
        imageviewAddImgPet = findViewById(R.id.ivAddPet);
        buttonULImgPet = findViewById(R.id.btnULImgPet);
        buttonAddPet = findViewById(R.id.buttonAddPet);
        edtSoId = findViewById(R.id.edittextSoId);
        edtTen = findViewById(R.id.edittextTenPet);
        edtNgaySinh = findViewById(R.id.edittextNgaySinh);
        edtNoiCungCap = findViewById(R.id.edittextNoiCungCap);
        edtGhiChu = findViewById(R.id.edittextGhiChu);

        radiobtnFemale = findViewById(R.id.radiobtnFemale);
        radiobtnMale = findViewById(R.id.radiobtnMale);

        Intent intent = getIntent();
        int id_subject = intent.getIntExtra("id_subject", 0);

        database = new Database(this);

        buttonAddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd(id_subject);
            }
        });

        btnTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        buttonULImgPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
    }

    private void DialogAdd(int id_subject) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogadd);
        dialog.setCanceledOnTouchOutside(false);

        Button btnThem = dialog.findViewById(R.id.btnCoAdd);
        Button btnKhongThem = dialog.findViewById(R.id.btnKhongAdd);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imageviewAddImgPet.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                    byte[] avatar = byteArray.toByteArray();

                    String giong = "";
                    if (radiobtnMale.isChecked()) {
                        giong = "Đực";
                    } else if (radiobtnFemale.isChecked()) {
                        giong = "Cái";
                    }
                    if (avatar.equals("") || edtSoId.getText().toString().trim().isEmpty() || edtTen.getText().toString().trim().isEmpty() || edtNgaySinh.getText().toString().trim().isEmpty() || giong.isEmpty() || edtNoiCungCap.getText().toString().trim().isEmpty()) {
                        Toast.makeText(ActivityAddPet.this, "Bạn chưa điền đủ thông tin thú cưng!", Toast.LENGTH_SHORT).show();
                    } else {
                        database.Insert_Pet(
                                avatar,
                                edtSoId.getText().toString().trim(),
                                edtTen.getText().toString().trim(),
                                giong,
                                edtNgaySinh.getText().toString().trim(),
                                edtNoiCungCap.getText().toString().trim(),
                                edtGhiChu.getText().toString().trim(),
                                id_subject
                        );
                        Intent intent = new Intent(ActivityAddPet.this, ActivityPet.class);
                        intent.putExtra("id_subject", id_subject);
                        startActivity(intent);
                        Toast.makeText(ActivityAddPet.this, "Thêm thú cưng thành công!", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(ActivityAddPet.this, "Bạn cần thêm ảnh cho thú cưng!", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageviewAddImgPet.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}