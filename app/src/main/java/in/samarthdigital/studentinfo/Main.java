package in.samarthdigital.studentinfo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.DataTruncation;

public class Main extends AppCompatActivity implements View.OnClickListener {

    Button btn_comp,btn_mech,btn_elec,btn_etc,btn_add,btn_all;
    Intent intent;
    DataBaseHelper dataBaseHelper;
    int comp,mech,elec,etc,all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(Main.this,ViewStudent.class);
        dataBaseHelper = new DataBaseHelper(this);

        //Get Count of All Students
        comp = dataBaseHelper.getData("Computer Engineering").getCount();
        mech = dataBaseHelper.getData("Mechanical Engineering").getCount();
        elec = dataBaseHelper.getData("Electrical Engineering").getCount();
        etc= dataBaseHelper.getData("E&TC Engineering").getCount();


        btn_comp = findViewById(R.id.btn_comp);
        btn_comp.setText("Computer Engineering         "+comp);
        btn_comp.setOnClickListener(this);

        btn_mech = findViewById(R.id.btn_mech);
        btn_mech.setText("Mechanical Engineering     "+mech);
        btn_mech.setOnClickListener(this);

        btn_elec = findViewById(R.id.btn_elec);
        btn_elec.setText("Electrical Engineering       "+elec);
        btn_elec.setOnClickListener(this);

        btn_etc = findViewById(R.id.btn_etc);
        btn_etc.setText("E&TC Engineering                    "+etc);
        btn_etc.setOnClickListener(this);

        btn_all = findViewById(R.id.btn_all);
        btn_all.setText("All Students                            "+(comp+mech+elec+etc));
        btn_all.setOnClickListener(this);

        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        //starts the View Student Activity
        switch (v.getId()){
            case R.id.btn_comp :
                intent.putExtra("dept","Computer Engineering");
                startActivity(intent);
                finish();
                break;
            case R.id.btn_mech:
                intent.putExtra("dept","Mechanical Engineering");
                startActivity(intent);
                finish();
                break;
            case R.id.btn_elec:
                intent.putExtra("dept","Electrical Engineering");
                startActivity(intent);
                finish();
                break;
            case R.id.btn_etc:
                intent.putExtra("dept","E&TC Engineering");
                startActivity(intent);
                finish();
                break;
            case R.id.btn_all:
                intent.putExtra("dept","All Students");
                startActivity(intent);
                finish();
                break;
            case R.id.btn_add:
                Intent intent = new Intent(Main.this,Add_Student.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
