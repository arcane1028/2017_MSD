package com.jsh.ms_lab13_1_201302480;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String databaseName;
    String tableName;
    TextView status;
    boolean databaseCreated = false;
    boolean tableCreated = false;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText databaseNameInput = (EditText) findViewById(R.id.databaseNameInput);
        final EditText tableNameInput = (EditText) findViewById(R.id.tableNameInput);

        Button createDatabaseBtn = (Button) findViewById(R.id.createDatabaseBtn);
        createDatabaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseName = databaseNameInput.getText().toString();
                createDatabase(databaseName);
            }
        });

        final Button createTableBtn = (Button) findViewById(R.id.createTableBtn);
        createTableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableName = tableNameInput.getText().toString();
                createTable(tableName);
                int count = insertRecord(tableName);
                println(count + "record inserted.");
            }
        });

        status = (TextView) findViewById(R.id.status);

    }

    private void createDatabase(String databaseName) {
        println("creating database [" + databaseName + "].");

        try {
            database = openOrCreateDatabase(
                    databaseName,
                    Activity.MODE_PRIVATE,
                    null
            );

            databaseCreated = true;
            println("database is created.");
        } catch (Exception e) {
            e.printStackTrace();
            println("database id not created");
        }
    }


    private void createTable(String tableName) {
        println("creating database [" + tableName + "].");

        database.execSQL("create table if not exists" + tableName + "("
                + "_id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + "age integer, "
                + " phone text);"
        );

        tableCreated = true;
    }
    private int insertRecord(String tableName) {
        println("inserting records into table "+ tableName+".");

        int count = 3;
        database.execSQL("insert into "+tableName+"(name, age, phone) values ('John', 20, '010-7788-1234');");
        database.execSQL("insert into "+tableName+"(name, age, phone) values ('Mike', 35, '010-8888-1111');");
        database.execSQL("insert into "+tableName+"(name, age, phone) values ('Sean', 26, '010-6677-4321');");

        return count;
    }

    private void println(String msg)
    {
        Log.d("MainActivity", msg);
        status.append("\n"+msg);
    }

}
