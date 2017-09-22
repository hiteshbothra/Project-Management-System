package com.example.hiteshb.projectmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText userID,password;
    Button logIN;
    TextView forgot;
    RadioButton rStudent,rFaculty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button signUp=(Button)findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,Signup.class);
                startActivity(i);
            }
        });

        forgot=(TextView)findViewById(R.id.forgotPassword);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,forgot.class);
                startActivity(i);
            }
        });

        final DBHelper helper = new DBHelper(this);

        userID=(EditText)findViewById(R.id.user_id);
        password=(EditText)findViewById(R.id.password);
        logIN=(Button)findViewById(R.id.login);
        rStudent=(RadioButton)findViewById(R.id.student);
        rFaculty=(RadioButton)findViewById(R.id.faculty);

        logIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=userID.getText().toString();
                String pass=password.getText().toString();
                if(user.length()==0 || pass.length()==0)
                    Toast.makeText(Login.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                else
                {
                    try
                    {
                        if(rStudent.isChecked())
                        {
                            String CorrectPass = helper.studentSearchPass(user);
                            if (pass.matches(CorrectPass))
                            {
                                // Check whether the preferences are entered or not
                                if(helper.studentUserSearch(user))
                                {
                                    Toast.makeText(Login.this,"You have already filled the choices",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Intent i=new Intent(Login.this,Student.class);
                                    i.putExtra("user",user);
                                    startActivity(i);
                                }
                            }
                            else
                            {
                                Toast.makeText(Login.this,"UserName or Password incorrect",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if(rFaculty.isChecked())
                        {
                            String CorrectPass = helper.facultySearchpass(user);
                            if (pass.equals(CorrectPass))
                            {
                                // Check whether the preferences are entered or not
                                if(helper.facultyUserSearch(user))
                                {
                                    Toast.makeText(Login.this,"You have already filled the choices",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Intent i=new Intent(Login.this,Faculty.class);
                                    i.putExtra("user",user);
                                    startActivity(i);
                                    finish();
                                }
                            }
                            else
                            {
                                Toast.makeText(Login.this,"UserName or Password incorrect",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Login.this,"Please Cheacked RadioButton",Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(Login.this, e.toString(), Toast.LENGTH_SHORT);
                    }

                }
            }
        });

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, allotProject.class);
                i.putExtra("User", userID.getText().toString());
                startActivity(i);
            }
        });

    }
}
