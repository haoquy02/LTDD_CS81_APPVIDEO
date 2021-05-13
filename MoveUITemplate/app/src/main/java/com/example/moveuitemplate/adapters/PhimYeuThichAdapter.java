package com.example.moveuitemplate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.moveuitemplate.R;
import com.example.moveuitemplate.models.Phim;

import java.util.ArrayList;

public class PhimYeuThichAdapter extends BaseAdapter {

    Context context;
    ArrayList<Phim> arrayPhim;

    public PhimYeuThichAdapter(Context context, ArrayList<Phim> arrayPhim) {
        this.context = context;
        this.arrayPhim = arrayPhim;
    }

    @Override
    public int getCount() {
        return arrayPhim.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayPhim.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public TextView tvTenPhim, tvIDPhim;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_phimyeuthich, null);
            viewHolder.tvIDPhim = view.findViewById(R.id.tv_idphim);
            viewHolder.tvTenPhim = view.findViewById(R.id.tv_tenphim);

            view.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Phim phim = (Phim) getItem(i);
        viewHolder.tvIDPhim.setText(String.valueOf(phim.getID()));
        viewHolder.tvTenPhim.setText(phim.getTenPhim());



        return view;
    }



}