package example.com.jsh.ms_hw07_201302480;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by JSH on 2017-10-27.
 */

public class SingerItemView extends LinearLayout {
    TextView textView;
    TextView textView2; //생일
    TextView textView3; //전화번호
    ImageView imageView;

    public SingerItemView(Context context) {
        super(context);
        init(context);
    }
    public SingerItemView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        init(context);
    }
    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item, this, true);

        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public void setName(String name){ textView.setText(name);}
    public void setBirth(String birth){textView2.setText(birth);}
    public void setPhone(String phone){ textView3.setText(String.valueOf(phone));}
    public void setImage(int resId){imageView.setImageResource(resId);}
}
