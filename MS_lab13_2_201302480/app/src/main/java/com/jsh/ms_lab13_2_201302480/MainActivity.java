package com.jsh.ms_lab13_2_201302480;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView status;
    public static final String TAG = "MainActivity";

    private static String DATABASE_NAME = null;
    private static String TABLE_NAME = "employee";
    private static int DATABASE_VERSION = 1;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = (TextView) findViewById(R.id.status);
        final EditText input01 = (EditText) findViewById(R.id.input01);

        Button queryBtn = (Button) findViewById(R.id.queryBtn);
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DATABASE_NAME = input01.getText().toString();
                boolean isOpen = openDatabase();
                if (isOpen) {
                    executeRawQuery();
                    executeRawQueryParam();
                }
            }
        });

    }

    private boolean openDatabase() {

        println("opening database [" + DATABASE_NAME + "].");

        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();

        return true;
    }


    private void executeRawQuery() {

        println("\nexecuteRawQuery called.\n");
        println(TABLE_NAME);

        Cursor cursor = database.rawQuery("select count (*) as Total from " + TABLE_NAME, null);
        println("cursor count : " + cursor.getCount());

        cursor.moveToNext();

        println("record count : " + cursor.getInt(0));

        cursor.close();

    }

    private void executeRawQueryParam() {

        println("\nexecuteRawQueryParam called.\n");

        String SQL = "select name, age, phone "
                + "from " + TABLE_NAME
                + "where age > ?";
        String[] args = {"30"};

        Cursor cursor = database.rawQuery(SQL, args);
        int recordCount = cursor.getCount();
        println("cursor count : " + recordCount + "\n");

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();
            String name = cursor.getString(0);
            int age = cursor.getInt(1);
            String phone = cursor.getString(2);

            println("Record #" + i + " : " + name + ", " + age + ", " + phone);
        }

        cursor.close();

    }

    private void println(String msg) {
        Log.d(TAG, msg);
        status.append("\n" + msg);
    }


    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            println("creating table [" + TABLE_NAME + "].");

            try {
                String DROP_SQL = "drop table if exists " + TABLE_NAME;
                database.execSQL(DROP_SQL);
            } catch (Exception e) {
                Log.e(TAG, "Exception in DROP_SQL", e);
            }

            String CREATE_SQL = "create table " + TABLE_NAME + "("
                    + " _id integer PRIMARY KEY autoincrement, "
                    + " name text, "
                    + " age integer, "
                    + " phone text )";

            try {
                database.execSQL(CREATE_SQL);
            } catch (Exception e) {
                Log.e(TAG, "Exception in CREATE_SQL", e);
            }

            println("inserting records.");

            try
            {
                database.execSQL("insert into "+TABLE_NAME+"(name, age, phone) values ('John', 20, '010-7788-1234');");
                database.execSQL("insert into "+TABLE_NAME+"(name, age, phone) values ('Mike', 35, '010-8888-1111');");
                database.execSQL("insert into "+TABLE_NAME+"(name, age, phone) values ('Sean', 26, '010-6677-4321');");

            }catch (Exception e)
            {
                Log.e(TAG, "Exception in insert SQL", e);
            }
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            println("opened database [" + DATABASE_NAME + "].");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            Log.w(TAG, "Upgrading database from version " + i + " to "+i1+".");
        }
    }

}
