package com.example.hiteshb.projectmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Faculty extends AppCompatActivity {

    TextView user;
    EditText pre,pre1,pre2,pre3,pre4,pre5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        user=(TextView)findViewById(R.id.user);
        pre1=(EditText)findViewById(R.id.preference1);
        pre2=(EditText)findViewById(R.id.preference2);
        pre3=(EditText)findViewById(R.id.preference3);
        pre4=(EditText)findViewById(R.id.preference4);
        pre5=(EditText)findViewById(R.id.preference5);

        final String userID=getIntent().getStringExtra("user").toString();
        user.setText(userID);

        Button submit=(Button)findViewById(R.id.submit);
        final DBHelper helper=new DBHelper(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.open();
                helper.insertFacultyPreferences(new setGetValues(user.getText().toString(),pre1.getText().toString(),
                        pre2.getText().toString(),pre3.getText().toString(),pre4.getText().toString(),pre5.getText().toString()));
                helper.close();
                Toast.makeText(getApplicationContext(),"Entered Successfully",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Faculty.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
