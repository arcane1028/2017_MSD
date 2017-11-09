package example.com.jsh.ms_hw08_201302480;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by JSH on 2017-11-09.
 */

public class ProductItemAdapter extends BaseAdapter {

    private ArrayList<ProductData> items = new ArrayList<ProductData>();
    private Context context;

    public ProductItemAdapter(Context context, GridView gridView) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(ProductData item){ items.add(item);}


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

        ProductItemView view = new ProductItemView(context);

        ProductData item = items.get(position);
        view.setProductName(item.getProductName());
        view.setProductPrice(String.format("%,d", item.getProductPrice() ) );
        view.setProductText(item.getProductText());
        view.setImageView(item.getImageId());
        view.setProductMaker("["+item.getProductMaker()+"]");

        return view;
    }
}
