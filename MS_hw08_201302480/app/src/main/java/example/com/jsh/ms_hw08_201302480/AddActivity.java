package example.com.jsh.ms_hw08_201302480;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 101;

    private ImageView imageView;
    private EditText inputMaker;
    private EditText inputName;
    private EditText inputPrice;
    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button button = (Button)findViewById(R.id.addButton);

        imageView = (ImageView)findViewById(R.id.addImageView);
        inputMaker = (EditText)findViewById(R.id.addMaker);
        inputName = (EditText)findViewById(R.id.addName);
        inputPrice = (EditText)findViewById(R.id.addPrice);
        inputText = (EditText)findViewById(R.id.addText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductData data = new ProductData(
                        R.drawable.clothes5,
                        inputMaker.getText().toString(),
                        inputName.getText().toString(),
                        Integer.parseInt(inputPrice.getText().toString()),
                        inputText.getText().toString()
                );
                Intent intent = new Intent();
                intent.putExtra("data", data);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
