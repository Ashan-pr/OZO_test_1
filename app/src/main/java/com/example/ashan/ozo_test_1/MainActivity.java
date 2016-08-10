package com.example.ashan.ozo_test_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  // removes the title bar from activity
        setContentView(R.layout.activity_main);

        Button btnMoodle,btnWiki,btnTuto,btnStack,btnSchool;

        TextView txtRate=(TextView)findViewById(R.id.txtRate);

        btnMoodle=(Button)findViewById(R.id.btnMoodle);
        btnStack=(Button)findViewById(R.id.btnStackOverflow);
        btnWiki=(Button)findViewById(R.id.btnWiki);
        btnTuto=(Button)findViewById(R.id.btnTuto);
        btnSchool=(Button)findViewById(R.id.btnW3Cschools);


        final String Tuto,Wiki,Moodle,Stack,school;
        Tuto="https://www.tutorialspoint.com";
        Wiki="https://www.wikipedia.org/";
        Moodle="https://moodle.itfac.mrt.ac.lk/login/index.php";
        Stack="http://stackoverflow.com/??tab=month";
        school="http://www.w3schools.com/";


        final TextView txtAppName=(TextView)findViewById(R.id.txtAppName);
        txtAppName.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.slide_in_left));

        txtRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Ratings = new Intent(MainActivity.this, RatingAndComments.class);
                startActivity(Ratings);
            }
        });

        btnTuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Tutor=new Intent(MainActivity.this,webview_java.class);
                Tutor.putExtra("TutorialsP", Tuto); //passing address, here TutorialsP means a key to identify the value we are passing
                startActivity(Tutor);
            }
        });

        btnStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStack=new Intent(MainActivity.this,webview_java.class);
                intentStack.putExtra("Stack", Stack);

                startActivity(intentStack);
            }
        });

        btnWiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWiki=new Intent(MainActivity.this,webview_java.class);

                intentWiki.putExtra("Wiki",Wiki);
                startActivity(intentWiki);
            }
        });

        btnMoodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMoodle=new Intent(MainActivity.this,webview_java.class);
                intentMoodle.putExtra("Moodle",Moodle);
                startActivity(intentMoodle);
            }
        });

        btnSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSchools=new Intent(MainActivity.this,webview_java.class);
                intentSchools.putExtra("School",school);
                startActivity(intentSchools);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
