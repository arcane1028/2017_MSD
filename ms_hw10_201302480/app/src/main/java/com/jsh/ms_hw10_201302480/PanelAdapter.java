package com.jsh.ms_hw10_201302480;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by JSH on 2017-11-17.
 */

public class PanelAdapter extends BaseAdapter {

    ArrayList<PannelItem> items = new ArrayList<PannelItem>();

    private Context context;

    public PanelAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(PannelItem item){ items.add(item);}
    public void removeItem(int index ){
        items.remove(index);
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PannelItemView view = new PannelItemView(context);

        PannelItem item = items.get(position);
        view.setName(item.getName());
        view.setPhone(item.getPhone());
        view.setCity(item.getCity());
        view.setImage(item.getResId());

        return view;
    }

}
