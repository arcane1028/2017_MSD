package example.com.jsh.ms_lab10_2_201302480;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class PenPaletteDialog extends Activity {

    GridView gridView;
    Button closeBtn;
    PenDataAdapter adapter;

    public static OnPenSelectedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        this.setTitle("선 굵기 선택");
        gridView = (GridView)findViewById(R.id.colorGrid);
        closeBtn = (Button)findViewById(R.id.closeBtn);

        gridView.setColumnWidth(14);
        gridView.setBackgroundColor(Color.GRAY);
        gridView.setVerticalSpacing(4);
        gridView.setHorizontalSpacing(4);

        adapter = new PenDataAdapter(this);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(adapter.getNumColumns());

        closeBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    class PenDataAdapter extends BaseAdapter {

        Context mContext;

        public final int [] pens = new int[]
                {
                        1,2,3,4,5,
                        6,7,8,9,10,
                        11,13,15,17,20
                };

        int rowCount;
        int columnCount;

        public PenDataAdapter(Context mContext) {
            super();

            this.mContext = mContext;
            rowCount = 3;
            columnCount = 5;
        }

        public int getNumColumns()
        {
            return columnCount;
        }

        @Override
        public int getCount() {
            return rowCount*columnCount;
        }

        @Override
        public Object getItem(int i) {
            return pens[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Log.d("PenDataAdapter", "getView("+i+") called.");

            int rowIndex = i/rowCount;
            int columnIndex = i%rowCount;
            Log.d("PenDataAdapter", "Index : "+ rowIndex+", "+columnIndex);

            GridView.LayoutParams params = new GridView.LayoutParams(
                    GridView.LayoutParams.MATCH_PARENT,
                    GridView.LayoutParams.MATCH_PARENT);

            int areaWidth = 10;
            int areaHeight = 20;

            Bitmap penBitmap = Bitmap.createBitmap(areaWidth, areaHeight, Bitmap.Config.ARGB_8888);
            Canvas penCanvas = new Canvas();
            penCanvas.setBitmap(penBitmap);

            Paint mPaint = new Paint();
            mPaint.setColor(Color.WHITE);
            penCanvas.drawRect(0,0,areaWidth, areaHeight, mPaint);

            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeWidth((float)pens[i]);
            penCanvas.drawLine(0, areaHeight/2, areaWidth-1, areaHeight/2, mPaint);
            BitmapDrawable penDrawable = new BitmapDrawable(mContext.getResources(), penBitmap);


            Button aItem = new Button(mContext);
            aItem.setText(" ");
            aItem.setLayoutParams(params);
            aItem.setPadding(4,4,4,4);
            aItem.setBackgroundDrawable(penDrawable);
            aItem.setHeight(120);
            aItem.setTag(pens[i]);

            aItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (PenPaletteDialog.listener != null)
                    {
                        PenPaletteDialog.listener.onPenSelected(
                                ((Integer)view.getTag()).intValue()
                        );

                    }
                    ((PenPaletteDialog)mContext).finish();
                }
            });
            return aItem;
        }
    }
}
