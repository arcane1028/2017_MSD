package com.jsh.ms_hw10_201302480;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by JSH on 2017-11-17.
 */

public class PannelItemView extends LinearLayout {
    TextView nameView;
    TextView phoneView;
    TextView cityView;
    ImageView imageView;


    public PannelItemView(Context context) {
        super(context);
        init(context);
    }
    public PannelItemView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        init(context);
    }
    public void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.pannel_item, this, true);

        nameView = (TextView)findViewById(R.id.name);
        phoneView = (TextView)findViewById(R.id.phone);
        cityView = (TextView)findViewById(R.id.city);
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public void setName(String name){ nameView.setText(name);}
    public void setPhone(String phone){phoneView.setText(phone);}
    public void setCity(String city){ cityView.setText(city);}
    public void setImage(int resId){imageView.setImageResource(resId);}



}
