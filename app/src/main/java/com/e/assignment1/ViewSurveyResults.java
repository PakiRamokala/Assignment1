package com.e.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewSurveyResults extends AppCompatActivity {

    private DatabaseReference reference,averageAge,oldest,youngest,pizza,pasta,pap,eatOut,movies,tv,likeRadio;
    private FirebaseAuth mAuth;

    private int count = 0;

    TextView Total,Average,Oldest,Youngest,Pizza,Pasta,Pap,EatOut,Movies,WatchTV,LikeRadio;

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_survey_results);



        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Results");
        averageAge =  FirebaseDatabase.getInstance().getReference().child("Results");
        pizza =  FirebaseDatabase.getInstance().getReference().child("food");


        btnBack = (Button)findViewById(R.id.back);
        Total = (TextView)findViewById(R.id.tv_noOfSurveys);
        Average = (TextView)findViewById(R.id.tv_Average);
        Oldest = (TextView)findViewById(R.id.tv_Oldest);
        Youngest = (TextView)findViewById(R.id.tv_Youngest);
        Pizza = (TextView)findViewById(R.id.tv_noOfSurveys);
        Pasta = (TextView)findViewById(R.id.tv_noOfSurveys);
        Pap = (TextView)findViewById(R.id.tv_noOfSurveys);
        EatOut = (TextView)findViewById(R.id.tv_noOfSurveys);
        Movies = (TextView)findViewById(R.id.tv_noOfSurveys);
        WatchTV = (TextView)findViewById(R.id.tv_noOfSurveys);
        LikeRadio = (TextView)findViewById(R.id.tv_noOfSurveys);







        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewSurveyResults.this,MainActivity.class));
                finish();
            }
        });






        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    count = (int) dataSnapshot.getChildrenCount();


                    Total.setText("Total No of Surveys Completed:        "+ count + "   Surveys ");
                }

                else
                    {

                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final double[] mAge = {0};

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usersRef = rootRef.child("age");

        averageAge.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    double ageCount = 0;
                    int i = 0;
                    int young = 0;
                    int old = 0;
                    while(i<=count)
                    {

                        if(young > dataSnapshot.getChildrenCount() )
                        {
                            young = (int) dataSnapshot.getChildrenCount();
                        }

                        if(old < dataSnapshot.getChildrenCount() )
                        {
                            old = (int) dataSnapshot.getChildrenCount();
                        }

                        ageCount += dataSnapshot.getChildrenCount();

                        i++;
                    }


                    mAge[0] = 0;
                    mAge[0] = ageCount / count;

                    Average.setText("Average age:        "+ mAge + "   Years ");
                    Youngest.setText("Youngest person who participated in survey: "+ young + " Years ");
                    Oldest.setText("Oldest person who participated in survey:    "+ old + "   Years ");

                }

                else
                    {

                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }







}