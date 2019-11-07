package com.example.donasi2.phonebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.donasi2.R;

import java.util.ArrayList;

public class CustomAdapterPhonebook extends BaseAdapter {
    private Context context;
    private ArrayList<PhonebookModel> phonebookModelArrayList;

    public CustomAdapterPhonebook(Context context, ArrayList<PhonebookModel> phonebookModelArrayList) {

        this.context = context;
        this.phonebookModelArrayList = phonebookModelArrayList;
    }

    @Override
    public int getCount() {
        return phonebookModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return phonebookModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.model_phonebook, null, true);

            holder.tvnama = (TextView) convertView.findViewById(R.id.tv_nama_lv);
            holder.tvnohp = (TextView) convertView.findViewById(R.id.tv_nohp_lv);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvnama.setText (phonebookModelArrayList.get(position).getNama());
        holder.tvnohp.setText  (phonebookModelArrayList.get(position).getNohp());
        return convertView;
    }

    private class ViewHolder {
        protected TextView tvnama, tvnohp;
    }
}
