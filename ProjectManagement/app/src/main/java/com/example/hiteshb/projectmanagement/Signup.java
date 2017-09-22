package com.example.hiteshb.projectmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText userId,email,pass,conPass;
    Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        userId = (EditText) findViewById(R.id.user_id);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        conPass = (EditText) findViewById(R.id.cPassword);
        signin = (Button) findViewById(R.id.signIn);
        final DBHelper helper=new DBHelper(this);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempUserId=userId.getText().toString();
                String tempEmail=email.getText().toString();
                String tempPass=pass.getText().toString();
                String tempCPass=conPass.getText().toString();
                Log.d("pass",tempPass.trim());
                Log.d("Cpass",tempCPass.trim());

                RadioGroup r=(RadioGroup)findViewById(R.id.group);
                int selectId=r.getCheckedRadioButtonId();
                RadioButton rb=(RadioButton)findViewById(selectId);
                String tempType=rb.getText().toString();
                //String tempType="Student";

                if(tempUserId.equals("") || tempUserId==null ||tempUserId.length()==0
                        || tempEmail.equals("") || tempEmail==null || tempEmail.length()==0
                        || tempPass.equals("") || tempPass==null ||tempPass.length()==0
                        || tempCPass.equals("") || tempCPass==null ||tempCPass.length()==0
                        || tempType.equals("") || tempType==null ||tempType.length()==0)
                {
                    Toast.makeText(Signup.this,"Please Fill all the Details",Toast.LENGTH_SHORT).show();
                }

                else if(!tempCPass.equals(tempPass))
                {
                    Toast.makeText(Signup.this,"Password is not same in both field",Toast.LENGTH_SHORT).show();
                }

                else if(tempPass.length()<8)
                {
                    Toast.makeText(Signup.this,"Password length should be greater than 7",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    helper.open();
                    helper.insertData(new setGetValues(tempUserId,tempEmail,tempPass,tempType));
                    Toast.makeText(Signup.this,tempType,Toast.LENGTH_SHORT).show();
                    Toast.makeText(Signup.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                    finish();

                    helper.close();
                }
            }
        });
    }
}
