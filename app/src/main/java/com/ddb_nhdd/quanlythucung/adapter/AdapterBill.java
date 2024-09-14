package com.ddb_nhdd.quanlythucung.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ddb_nhdd.quanlythucung.ActivityBill;

import com.ddb_nhdd.quanlythucung.R;
import com.ddb_nhdd.quanlythucung.model.Bill;


import java.util.ArrayList;

public class AdapterBill extends BaseAdapter{
    private ActivityBill context;
    private ArrayList<Bill> ArrayListBill;

    public AdapterBill(ActivityBill context, ArrayList<Bill> arrayListBill) {
        this.context = context;
        ArrayListBill = arrayListBill;
    }

    @Override
    public int getCount() {
        return ArrayListBill.size();
    }

    @Override
    public Object getItem(int position) {
        return ArrayListBill.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.listbill, null);

        TextView tvBillKind = convertView.findViewById(R.id.tvBillKind);
        TextView tvBillID = convertView.findViewById(R.id.tvBillID);

        ImageButton buttonWatchBill = convertView.findViewById(R.id.btnWatchBill);
        ImageButton buttonDeleteBill = convertView.findViewById(R.id.btnDeleteBill);

        Bill bill = ArrayListBill.get(position);
        tvBillKind.setText(bill.getBill_kind());
        tvBillID.setText(bill.getBill_id());

        int id = bill.getId_bill();

        buttonWatchBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.information(id);

            }
        });

        buttonDeleteBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.delete(id);
            }
        });

        return convertView;
    }
}
