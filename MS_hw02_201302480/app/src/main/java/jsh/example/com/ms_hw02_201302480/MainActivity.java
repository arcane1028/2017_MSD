package jsh.example.com.ms_hw02_201302480;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = (EditText) findViewById(R.id.input);
        Button number[] = new Button[10];
        String packName = this.getPackageName();

        for(int i=0;i<10;i++){
            number[i] = (Button) findViewById(getResources().getIdentifier("n"+i,"id",packName));
            addEvent(number[i], String.valueOf(i));
        }

        Button div = (Button) findViewById(R.id.div);
        Button mul = (Button) findViewById(R.id.mul);
        Button plus = (Button) findViewById(R.id.plus);
        Button minus = (Button) findViewById(R.id.min);

        Button result = (Button) findViewById(R.id.result);
        Button clear = (Button) findViewById(R.id.clr);

        addEvent(div, " / ");
        addEvent(mul, " * ");
        addEvent(plus, " + ");
        addEvent(minus, " - ");

        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputValue.setText("");
                inputValue.invalidate();
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ArrayList<String> arr = new ArrayList<>();
                Scanner scan = new Scanner(inputValue.getText().toString());

                while (scan.hasNext()) {
                    arr.add(scan.next());
                }

                arr = calPriority(arr, '*', '/');
                if(arr.size()>1)
                    arr = calPriority(arr, '+', '-');

                Toast.makeText(getApplicationContext(), String.valueOf(Double.valueOf(arr.get(0))), Toast.LENGTH_LONG).show();
            }
        });

    }

    private ArrayList<String> calPriority(ArrayList<String> arr, char op1, char op2) {
        String op;
        double lValue;
        double rValue;

        for (int i = 0; i < arr.size(); i++) {
            op = arr.get(i);
            if (op.charAt(0) == op1 || op.charAt(0) == op2) {
                lValue = Double.valueOf(arr.remove(i - 1));
                op = arr.remove(i - 1);
                rValue = Double.valueOf(arr.remove(i - 1));
                arr.add(i - 1, String.valueOf(getCalculate(lValue, op, rValue)));
                i -= 2;
            }
        }
        return arr;
    }

    private double getCalculate(double lValue, String op, double rValue) {
        double result = 0;

        switch (op.charAt(0)) {
            case '+':
                result = lValue + rValue;
                break;
            case '-':
                result = lValue - rValue;
                break;
            case '*':
                result = lValue * rValue;
                break;
            case '/':
                if (rValue == 0) {
                    break;
                }
                result = lValue / rValue;
                break;
        }
        return result;
    }

    private void addEvent(Button button, final String addText) {
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String currentInput = inputValue.getText().toString();
                StringBuilder cur = new StringBuilder(currentInput);
                cur.append(addText);
                inputValue.setText(cur);
                inputValue.invalidate();
            }
        });
    }

}
