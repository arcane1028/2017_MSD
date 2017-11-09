package example.com.jsh.ms_hw08_201302480;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 101;
    private ActionBar actionBar;
    private ProductItemAdapter adapter;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);

        actionBar = this.getSupportActionBar();
        actionBar.show();

        adapter = new ProductItemAdapter(getApplicationContext(), gridView);

        adapter.addItem(new ProductData(R.drawable.clothes1, "빈폴", "롱 코트", 300000, "기획상품" ));
        adapter.addItem(new ProductData(R.drawable.clothes2, "나이키","운동화",  70000, "특가상품"));
        adapter.addItem(new ProductData(R.drawable.clothes3, "폴로","남방",  150000, "계절상품"));
        adapter.addItem(new ProductData(R.drawable.clothes4, "리바이스","모자", 40000, "반짝상품"));

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                TextView name = (TextView) view.findViewById(R.id.productName);
                TextView price = (TextView) view.findViewById(R.id.productPrice);

                Toast.makeText(MainActivity.this,
                        "선택된 상품 : "+name.getText()+"\n 가격 : "+price.getText(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK)
        {
            if(requestCode == REQUEST_CODE)
            {
                if (data != null)
                {
                    Bundle bundle = data.getExtras();
                    ProductData addData = (ProductData) bundle.get("data");
                    adapter.addItem(addData);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "상품이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
