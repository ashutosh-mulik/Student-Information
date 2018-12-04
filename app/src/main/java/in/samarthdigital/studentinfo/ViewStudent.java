package in.samarthdigital.studentinfo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewStudent extends AppCompatActivity {
    Intent intent;
    RecyclerView recyclerView;
    String dept;
    DataBaseHelper dataBaseHelper;
    Cursor cursor;
    List<String> fname,lname,enroll,department,id;
    TextView txt_top;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        //Department Name from Intent
        intent = getIntent();
        dept = intent.getStringExtra("dept");

        txt_top = findViewById(R.id.view_top_name);
        txt_top.setText(dept);

        fname = new ArrayList<String>();
        lname = new ArrayList<String>();
        enroll = new ArrayList<String>();
        department = new ArrayList<String>();
        id = new ArrayList<String>();

        dataBaseHelper = new DataBaseHelper(this);
        if (dept.equals("All Students")){
            cursor = dataBaseHelper.getAll();
            setData();

        }else {
            cursor = dataBaseHelper.getData(dept);
            setData();
        }

        recyclerView = findViewById(R.id.view_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerAdapter(id,enroll,fname,lname,department,this));
    }

    @Override
    public void onBackPressed() {
        intent = new Intent(ViewStudent.this,Main.class);
        startActivity(intent);
        finish();
    }

    public void setData(){
        if (cursor.getCount()!=0){
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                enroll.add(cursor.getString(1));
                fname.add(cursor.getString(2));
                lname.add(cursor.getString(3));
                department.add(cursor.getString(4));
            }
        }else
            Toast.makeText(this, "Students Not Found", Toast.LENGTH_SHORT).show();
    }
}
