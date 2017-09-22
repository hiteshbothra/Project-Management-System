package com.example.hiteshb.projectmanagement;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class allotProject extends AppCompatActivity {

    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allot_project);
        DBHelper helper = new DBHelper(this);
        user = getIntent().getStringExtra("User");
        final String guide = helper.searchGuide(user);
        Log.d("Guide:",guide);


        Button startBtn = (Button) findViewById(R.id.button);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse("mailto:"));
                String to[] = {"hiteshbothrabansal@nirmauni.ac.in"};
                i.putExtra(Intent.EXTRA_EMAIL, to);
                i.putExtra(Intent.EXTRA_SUBJECT, "Project Allocation");
                i.putExtra(Intent.EXTRA_TEXT, "Your guide of project is: "+guide);
                i.setType("message/rfc822");
                startActivity(Intent.createChooser(i, "Send Email"));
            }
        });
    }
    /*
    public void passProject(String s)
    {
        Log.d("Project:", s);
    }*/

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {user+"@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(allotProject.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
