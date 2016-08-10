package com.example.ashan.ozo_test_1;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ashan on 6/17/2016.
 */



public class RatingAndComments extends  Activity {


    private static float ratingAmount;
    private static RatingBar rtnBar;
    private static EditText comment,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  // removes the title bar from activity
        setContentView(R.layout.rating_and_comments);

        Button btnSbmt,btnVisit;
        btnSbmt=(Button)findViewById(R.id.btnSubmit);
        btnVisit = (Button)findViewById(R.id.btnVisitOurSite);

        listenerForRatingBar();

        comment=(EditText)findViewById(R.id.edtCmnt);
        name=(EditText)findViewById(R.id.edtName);


        btnSbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commnt =comment.getText().toString();
                String nm=name.getText().toString();
                String type="comment";
                String rating= String.valueOf(ratingAmount);
                BackgroundWorker backgroundWorker=new BackgroundWorker(RatingAndComments.this);
                backgroundWorker.execute(type, commnt, rating, nm);
                Toast.makeText(RatingAndComments.this,"You", Toast.LENGTH_SHORT).show();

                //clearing text field and name field after submiting
                comment.setText("");
                name.setText("");


            }
        });

        final String ourSite="http://192.168.43.177/fitkit_1.0/index.php/frameworks";
        btnVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainS=new Intent(RatingAndComments.this,webview_java.class);
                MainS.putExtra("ourSite", ourSite); //passing address, here TutorialsP means a key to identify the value we are passing
                startActivity(MainS);
            }
        });


    }

    public void listenerForRatingBar(){
        rtnBar=(RatingBar)findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) rtnBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
       // stars.getDrawable(1).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.green(50), PorterDuff.Mode.SRC_ATOP);

        rtnBar.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean rated) {
                        ratingAmount = rating;
                        Toast.makeText(RatingAndComments.this,
                                "You rated the app to: " + ratingAmount, Toast.LENGTH_SHORT).show();
                    }
                }
        );


    }



}
