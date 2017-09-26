package jsh.example.com.ms_hw03_2_201302480;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button customButton = (Button) findViewById(R.id.customerMng);
        Button salesButton = (Button) findViewById(R.id.salesMng);
        Button productButton = (Button) findViewById(R.id.productMng);


        addEvent(customButton, "고객 관리");
        addEvent(salesButton, "매출 관리");
        addEvent(productButton, "상품 관리");

        Intent intent = getIntent();
        processIntent(intent);
    }

    private void addEvent(Button button, final String msg) {
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("MENU", msg);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void processIntent(Intent intent) {
        if (intent != null){
            Bundle bundle = intent.getExtras();
            Toast.makeText(getApplicationContext(), "Username : "+ bundle.getString("ID") + " Password : "+bundle.getString("PASSWORD"), Toast.LENGTH_LONG).show();

        }
    }

}
