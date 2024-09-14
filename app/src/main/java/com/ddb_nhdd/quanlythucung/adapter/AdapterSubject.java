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

import com.ddb_nhdd.quanlythucung.ActivitySubject;
import com.ddb_nhdd.quanlythucung.R;
import com.ddb_nhdd.quanlythucung.model.Subject;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterSubject extends BaseAdapter {

    private ActivitySubject context;
    private ArrayList<Subject> ArrayListSubject;

    public AdapterSubject(ActivitySubject context, ArrayList<Subject> arrayListSubject) {
        this.context = context;
        ArrayListSubject = arrayListSubject;
    }

    @Override
    public int getCount() {
        return ArrayListSubject.size();
    }

    @Override
    public Object getItem(int position) {
        return ArrayListSubject.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.listsubject, null);

        ImageView ImageViewAvatar = convertView.findViewById(R.id.ivPL);

        TextView TextViewMaPhanLoai = convertView.findViewById(R.id.textviewMaPhanLoai);
        TextView TextViewPhanLoai = convertView.findViewById(R.id.textviewPhanLoai);

        ImageButton imageDelete = convertView.findViewById(R.id.subjectDelete);
        ImageButton imageInformation = convertView.findViewById(R.id.subjectInformation);
        ImageButton imageUpdate = convertView.findViewById(R.id.subjectUpdate);

        Subject subject = ArrayListSubject.get(position);
        byte[] avatar = subject.getSubject_avatar();
        Bitmap bitmap = BitmapFactory.decodeByteArray(avatar, 0, avatar.length);

        ImageViewAvatar.setImageBitmap(bitmap);
        TextViewPhanLoai.setText(subject.getSubject_pl());
        TextViewMaPhanLoai.setText(subject.getSubject_mpl());

        int id = subject.getId();

        imageInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    context.information(id);

            }
        });

        imageUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.update(id);
            }
        });

        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.delete(id);
            }
        });
        return convertView;
    }
}
