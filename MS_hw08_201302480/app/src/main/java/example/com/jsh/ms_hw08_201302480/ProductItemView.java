package example.com.jsh.ms_hw08_201302480;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by JSH on 2017-11-09.
 */

public class ProductItemView extends LinearLayout{

    private ImageView imageView;
    private TextView productName;
    private TextView productPrice;
    private TextView productText;
    private TextView productMaker;

    public ProductItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProductItemView(Context context) {
        super(context);
        init(context);
    }
    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.product_item, this, true);

        productName = (TextView)findViewById(R.id.productName);
        productPrice = (TextView)findViewById(R.id.productPrice);
        productText = (TextView)findViewById(R.id.productText);
        productMaker = (TextView)findViewById(R.id.productMaker);
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public void setProductName(String name){ productName.setText(name);}
    public void setProductPrice(String price){ productPrice.setText(price);}
    public void setProductText(String text){ productText.setText(text);}
    public void setProductMaker(String maker){ productMaker.setText(maker);}
    public void setImageView(int resId){ imageView.setImageResource(resId);}
}
