package com.example.hiteshb.projectmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Student extends AppCompatActivity {

    TextView user;
    EditText pre,pre1,pre2,pre3,pre4,pre5,pre6,pre7,pre8,pre9,pre10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        user=(TextView)findViewById(R.id.user);
        pre=(EditText)findViewById(R.id.preference);
        pre1=(EditText)findViewById(R.id.preference1);
        pre2=(EditText)findViewById(R.id.preference2);
        pre3=(EditText)findViewById(R.id.preference3);
        pre4=(EditText)findViewById(R.id.preference4);
        pre5=(EditText)findViewById(R.id.preference5);
        pre6=(EditText)findViewById(R.id.preference6);
        pre7=(EditText)findViewById(R.id.preference7);
        pre8=(EditText)findViewById(R.id.preference8);
        pre9=(EditText)findViewById(R.id.preference9);
        pre10=(EditText)findViewById(R.id.preference10);
        Button submit=(Button)findViewById(R.id.submit);

        final String userID=getIntent().getStringExtra("user").toString();

        Log.d("user:",userID);
        user.setText(userID);
        final DBHelper helper=new DBHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.open();
                helper.insertStudentPreferences(new setGetValues(userID,pre.getText().toString(),pre1.getText().toString(),
                        pre2.getText().toString(),pre3.getText().toString(),pre4.getText().toString(),pre5.getText().toString(),
                        pre6.getText().toString(),pre7.getText().toString(),pre8.getText().toString(),
                        pre9.getText().toString(),pre10.getText().toString()));
                helper.close();
                Toast.makeText(getApplicationContext(),"Entered Successfully",Toast.LENGTH_SHORT).show();



                Intent i = new Intent(Student.this, Login.class);
                startActivity(i);
                finish();
            }
        });

        Button v = (Button) findViewById(R.id.view);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Student.this,GetProjectList.class);
                startActivity(i);
            }
        });
    }
}
