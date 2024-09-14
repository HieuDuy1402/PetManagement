package com.ddb_nhdd.quanlythucung.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ddb_nhdd.quanlythucung.ActivityPet;
import com.ddb_nhdd.quanlythucung.R;
import com.ddb_nhdd.quanlythucung.model.Pet;
import com.ddb_nhdd.quanlythucung.model.Subject;

import java.util.ArrayList;

public class AdapterPet extends BaseAdapter {

    private ActivityPet context;
    private ArrayList<Pet> ArrayListPet;

    public AdapterPet(ActivityPet context, ArrayList<Pet> arrayListPet) {
        this.context = context;
        ArrayListPet = arrayListPet;
    }

    @Override
    public int getCount() {
        return ArrayListPet.size();
    }

    @Override
    public Object getItem(int position) {
        return ArrayListPet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.listpet, null);

            ImageView ImageViewAvatar = convertView.findViewById(R.id.ivPet);

            TextView tvSoId = convertView.findViewById(R.id.textviewSoIdPet);
            TextView tvTen = convertView.findViewById(R.id.textviewPetTen);

            ImageButton imgbtnDelete = convertView.findViewById(R.id.petDelete);
            ImageButton imgbtnUpdate = convertView.findViewById(R.id.petUpdate);
            ImageButton imgbtnInformation = convertView.findViewById(R.id.petInformation);

            Pet pet = ArrayListPet.get(position);

            byte[] avatar = pet.getPet_avatar();
            Bitmap bitmap = BitmapFactory.decodeByteArray(avatar, 0, avatar.length);

            ImageViewAvatar.setImageBitmap(bitmap);
            tvSoId.setText(pet.getPet_soid());
            tvTen.setText(pet.getPet_ten());

            int id = pet.getId_pet();

            imgbtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.delete(id);
                }
            });

            imgbtnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        context.update(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Lỗi khi cập nhật!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            imgbtnInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.information(id);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Lỗi khi hiển thị dữ liệu!", Toast.LENGTH_SHORT).show();
        }

        return convertView;
    }


}
