package example.com.jsh.ms_lab10_2_201302480;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class ColorPaletteDialog extends Activity {

    GridView gridView;
    Button closeBtn;
    ColorDataAdapter adapter;

    public static OnColorSelectedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        this.setTitle("색상 선택");

        gridView = (GridView) findViewById(R.id.colorGrid);
        closeBtn = (Button) findViewById(R.id.closeBtn);

        gridView.setColumnWidth(14);
        gridView.setBackgroundColor(Color.GRAY);
        gridView.setVerticalSpacing(4);
        gridView.setHorizontalSpacing(4);

        adapter = new ColorDataAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(adapter.getNumColums());

        closeBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    class ColorDataAdapter extends BaseAdapter{
        Context mContext;

        public final int [] colors = new int[] {
                0xff000000,0xff00007f,0xff0000ff,0xff007f00,0xff007f7f,0xff00ff00,0xff00ff7f,
                0xff00ffff,0xff7f007f,0xff7f00ff,0xff7f7f00,0xff7f7f7f,0xffff0000,0xffff007f,
                0xffff00ff,0xffff7f00,0xffff7f7f,0xffff7fff,0xffffff00,0xffffff7f,0xffffffff
        };

        int rowCount;
        int columnCount;

        public ColorDataAdapter(Context context) {
            super();
            this.mContext = context;
            rowCount = 3;
            columnCount = 7;
        }
        public int getNumColums()
        {
            return columnCount;
        }

        @Override
        public int getCount() {
            return rowCount*columnCount;
        }

        @Override
        public Object getItem(int i) {
            return colors[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Log.d("ColorDataAdapter", "getView("+i+") called.");

            int rowIndex = i/rowCount;
            int columnIndex = i%rowCount;
            Log.d("ColorDataAdapter", "Index : "+ rowIndex+", "+columnIndex);

            GridView.LayoutParams params = new GridView.LayoutParams(
                    GridView.LayoutParams.MATCH_PARENT,
                    GridView.LayoutParams.MATCH_PARENT);

            Button aItem = new Button(mContext);
            aItem.setText(" ");
            aItem.setLayoutParams(params);
            aItem.setPadding(4,4,4,4);
            aItem.setBackgroundColor(colors[i]);
            aItem.setHeight(120);
            aItem.setTag(colors[i]);

            aItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ColorPaletteDialog.listener != null)
                    {
                        ColorPaletteDialog.listener.onColorSelected(
                                ((Integer)view.getTag()).intValue()
                        );

                    }
                    ((ColorPaletteDialog)mContext).finish();
                }
            });
            return aItem;
        }
    }
}
