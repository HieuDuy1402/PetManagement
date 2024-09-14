package com.ddb_nhdd.quanlythucung;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
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

public class ActivityUpdatePet extends AppCompatActivity {

    EditText edtUpdateSoId, edtUpdateTen, edtUpdateNgaySinh, edtUpdateNoiCungCap, edtUpdateGhiChu;
    RadioButton rdMale, rdFemale;
    ImageView imageviewUpdatePet;
    Button btnUpdatePet, buttonULImg, btnTroLai;
    Database database;
    int REQUEST_CODE_FOLDER = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pet);

        imageviewUpdatePet = findViewById(R.id.ivUpdateImgPet);
        btnUpdatePet = findViewById(R.id.buttonUpdatePet);
        btnTroLai = findViewById(R.id.btnTroLai);
        buttonULImg = findViewById(R.id.btnULImgUpdate);

        edtUpdateSoId = findViewById(R.id.edittextUpdateSoId);
        edtUpdateTen = findViewById(R.id.edittextUpdateTen);
        edtUpdateNgaySinh = findViewById(R.id.edittextUpdateNgaySinh);
        edtUpdateNoiCungCap = findViewById(R.id.edittextUpdateNoiCungCap);
        edtUpdateGhiChu = findViewById(R.id.edtUpdateGhiChu);

        rdFemale = findViewById(R.id.radiobtnUpdateFemale);
        rdMale = findViewById(R.id.radiobtnUpdateMale);


        Intent intent = getIntent();
        int id = intent.getIntExtra("idpet", 0);

        byte[] avatar = intent.getByteArrayExtra("petavatar");
        String soid = intent.getStringExtra("petsoid");
        String ten = intent.getStringExtra("petten");
        String giong = intent.getStringExtra("giong");
        String ngaysinh = intent.getStringExtra("petngaysinh");
        String noicungcap = intent.getStringExtra("noicungcap");
        String ghichu = intent.getStringExtra("petghichu");
        int id_subject = intent.getIntExtra("id_subject", 0);

        Bitmap bitmap = BitmapFactory.decodeByteArray(avatar, 0, avatar.length);
        imageviewUpdatePet.setImageBitmap(bitmap);
        edtUpdateSoId.setText(soid);
        edtUpdateTen.setText(ten);
        edtUpdateNgaySinh.setText(ngaysinh);
        edtUpdateNoiCungCap.setText(noicungcap);
        edtUpdateGhiChu.setText(ghichu);

        database = new Database(this);

        if (giong.equals("Đực")) {
            rdMale.setChecked(true);
            rdFemale.setChecked(false);
        } else {
            rdFemale.setChecked(true);
            rdMale.setChecked(false);
        }


        btnUpdatePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdate(id, id_subject);
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

    private void DialogUpdate(int id, int id_subject) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogupdatesubject);

        dialog.setCanceledOnTouchOutside(false);

        Button btnCo = dialog.findViewById(R.id.btnCoUp);
        Button btnKhong = dialog.findViewById(R.id.btnKhongUp);

        btnCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imageviewUpdatePet.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                    byte[] avatar = byteArray.toByteArray();

                    String giong = "";
                    if (rdMale.isChecked()) {
                        giong = "Đực";
                    } else if (rdFemale.isChecked()) {
                        giong = "Cái";
                    }

                    if(edtUpdateSoId.getText().toString().trim().isEmpty() || edtUpdateTen.getText().toString().trim().isEmpty() || edtUpdateNgaySinh.getText().toString().trim().isEmpty() || edtUpdateNoiCungCap.getText().toString().trim().isEmpty() || giong.isEmpty()) {
                        Toast.makeText(ActivityUpdatePet.this, "Bạn chưa điền đủ thông tin thú cưng!", Toast.LENGTH_SHORT).show();
                    } else {
                        database.Update_Pet(
                                avatar,
                                edtUpdateSoId.getText().toString().trim(),
                                edtUpdateTen.getText().toString().trim(),
                                edtUpdateNgaySinh.getText().toString().trim(),
                                giong,
                                edtUpdateNoiCungCap.getText().toString().trim(),
                                edtUpdateGhiChu.getText().toString().trim(),
                                id
                        );
                        Intent intent = new Intent(ActivityUpdatePet.this, ActivityPet.class);
                        intent.putExtra("id_subject", id_subject);
                        startActivity(intent);
                        Toast.makeText(ActivityUpdatePet.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(ActivityUpdatePet.this, "Bạn cần thêm ảnh cho thú cưng!", Toast.LENGTH_SHORT).show();
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
                imageviewUpdatePet.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}