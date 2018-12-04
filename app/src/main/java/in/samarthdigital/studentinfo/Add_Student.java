package in.samarthdigital.studentinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Add_Student extends AppCompatActivity{
    private Spinner spinner;
    private String department;
    private Button btn_add;
    private DataBaseHelper helper;
    private EditText enroll,fname,lname;
    private String[] departments = {"Select Department","Computer Engineering","Mechanical Engineering","Electrical Engineering","E&TC Engineering"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__student);

        spinner = findViewById(R.id.add_spinner);
        btn_add = findViewById(R.id.add_btn_add);
        enroll = findViewById(R.id.add_txt_enroll);
        fname = findViewById(R.id.add_txt_fname);
        lname = findViewById(R.id.add_txt_lname);

        //Spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.style_spinner,departments);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                department = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Add_Student.this, "Please select department", Toast.LENGTH_SHORT).show();
            }
        });

        //Add New Student to Database
        helper = new DataBaseHelper(this);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //IF to check any field is empty
                if(enroll.getText().toString().isEmpty()||fname.getText().toString().isEmpty()||lname.getText().toString().isEmpty()||department.equals("Select Department")){

                    //Show Toast if any field is empty
                    Toast.makeText(Add_Student.this, "All Fields Required", Toast.LENGTH_SHORT).show();

                }else {

                    //check database if there enrollment already exist in database or not
                    if(helper.insertData(Double.valueOf(enroll.getText().toString()),fname.getText().toString().trim(),lname.getText().toString().trim(),department))
                        Toast.makeText(Add_Student.this, "Student Added", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Add_Student.this, "Enrollment No Already Exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Add_Student.this,Main.class);
        startActivity(intent);
        finish();
    }
}
