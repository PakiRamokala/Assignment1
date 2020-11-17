package com.e.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Survey extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;


  private  EditText Surname,Name,Phone,Date,Age;
   private Button Continue;
   private CheckBox pizza,pasta,pap,chicken,beef,other;
   private RadioButton a1,a2,a3,a4,a5,b1,b2,b3,b4,b5,c1,c2,c3,c4,c5,d1,d2,d3,d4,d5;
   //int lowest,highest,total,rating1,rating2,rating3,rating4;

   String Food;

    private DatePickerDialog.OnDateSetListener dateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);


        Surname = (EditText)findViewById(R.id.et_Surname);
        Name = (EditText)findViewById(R.id.et_Name);
        Phone = (EditText)findViewById(R.id.et_Phone);
        Date = (EditText)findViewById(R.id.et_Date);
        Age = (EditText)findViewById(R.id.et_Age);
        Continue = (Button) findViewById(R.id.btn_Continue);


        pizza = (CheckBox)findViewById(R.id.cb_Pizza);
        pasta = (CheckBox)findViewById(R.id.cb_Pasta);
        pap = (CheckBox)findViewById(R.id.cb_Pap);
        chicken = (CheckBox)findViewById(R.id.cb_Chicken);
        beef = (CheckBox)findViewById(R.id.cb_Beef);
        other = (CheckBox)findViewById(R.id.cb_Other);


        a1 = (RadioButton)findViewById(R.id.A1);
        a2 = (RadioButton)findViewById(R.id.A2);
        a3 = (RadioButton)findViewById(R.id.A3);
        a4 = (RadioButton)findViewById(R.id.A4);
        a5 = (RadioButton)findViewById(R.id.A5);
        b1 = (RadioButton)findViewById(R.id.B1);
        b2 = (RadioButton)findViewById(R.id.B2);
        b3 = (RadioButton)findViewById(R.id.B3);
        b4 = (RadioButton)findViewById(R.id.B4);
        b5 = (RadioButton)findViewById(R.id.B5);
        c1 = (RadioButton)findViewById(R.id.C1);
        c2 = (RadioButton)findViewById(R.id.C2);
        c3 = (RadioButton)findViewById(R.id.C3);
        c4 = (RadioButton)findViewById(R.id.C4);
        c5 = (RadioButton)findViewById(R.id.C5);
        d1 = (RadioButton)findViewById(R.id.D1);
        d2 = (RadioButton)findViewById(R.id.D2);
        d3 = (RadioButton)findViewById(R.id.D3);
        d4 = (RadioButton)findViewById(R.id.D4);
        d5 = (RadioButton)findViewById(R.id.D5);







        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Survey.this,android.R.style.Widget_Holo_Light,dateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                dialog.show();

            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                Date.setText(date);
            }
        };










        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Results");


                String surname = Surname.getText().toString();
                String name = Name.getText().toString();
                String phone = Phone.getText().toString();
                String date = Date.getText().toString();
                String age = Age.getText().toString();






                int mAge = Integer.parseInt(age);

               try
               {
                   food();
                   rating();
                   ratingB();
                   ratingC();
                   ratingD();

                   String EatOut,Movies,WatchTV,likeRadio;

                   EatOut = rating();
                  // rating1 += Integer.parseInt(EatOut);

                   Movies = ratingB();
                  // rating2 += Integer.parseInt(Movies);

                   WatchTV = ratingC();
                  // rating3 += Integer.parseInt(WatchTV);

                   likeRadio = ratingD();
                  // rating4 += Integer.parseInt(likeRadio);




                   if( phone.isEmpty() || surname.isEmpty() || name.isEmpty() ||  date.isEmpty() || mAge < 5 || mAge > 120 || Food.isEmpty()|| EatOut.isEmpty() || Movies.isEmpty() || WatchTV.isEmpty() || likeRadio.isEmpty())
                   {
                       Toast.makeText(Survey.this, "Unsuccessful. . enter all fields", Toast.LENGTH_SHORT).show();
                   }

                   else
                   {
                       ResultHelperClass helperClass = new ResultHelperClass(surname,name,phone,date,age,Food,EatOut,Movies,WatchTV,likeRadio);

                       reference.child(phone).setValue(helperClass);


                       startActivity(new Intent(Survey.this,MainActivity.class));
                       finish();
                       Toast.makeText(Survey.this, "Successful", Toast.LENGTH_SHORT).show();

                   }
               }


               catch (Exception ex)
               {
                   Toast.makeText(Survey.this, "error:   " + ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
               }

            }







        });



    }




    public String food()
    {
        Food = " ";

        if(pizza.isChecked())
        { Food = Food + "  pizza" + "/"; }

        if(pasta.isChecked())
        { Food = Food + "  pasta" + "/"; }

        if(pap.isChecked())
        { Food = Food + "  pap and wors" + "/"; }

        if(chicken.isChecked())
        { Food = Food + "  chicken stir fry" + "/"; }

        if(beef.isSelected())
        { Food = Food + "  beef stir fry" + "/"; }



        return Food;
    }

    public String rating()
    {
        String rating = "";

        if(a1.isChecked())
        { a2.setChecked(false); a3.setChecked(false); a4.setChecked(false); a5.setChecked(false);
          rating = String.valueOf(1);

        }

        if(a2.isChecked())
        { a1.setChecked(false); a3.setChecked(false); a4.setChecked(false); a5.setChecked(false);
            rating = String.valueOf(2);

        }

        if(a3.isChecked())
        { a2.setChecked(false); a1.setChecked(false); a4.setChecked(false); a5.setChecked(false);
            rating = String.valueOf(3);

        }

        if(a4.isChecked())
        { a2.setChecked(false); a3.setChecked(false); a1.setChecked(false); a5.setChecked(false);
            rating = String.valueOf(4);

        }

        if(a5.isChecked())
        { a2.setChecked(false); a3.setChecked(false); a4.setChecked(false); a1.setChecked(false);
            rating = String.valueOf(5);

        }


        return rating;
    }

    public String ratingB()
    {
        String B = "";

        if(b1.isChecked())
        { b2.setChecked(false); b3.setChecked(false); b4.setChecked(false); b5.setChecked(false);
            B = String.valueOf(1);

        }

        if(b2.isChecked())
        { b1.setChecked(false); b3.setChecked(false); b4.setChecked(false); b5.setChecked(false);
            B = String.valueOf(2);

        }

        if(b3.isChecked())
        { b1.setChecked(false); b2.setChecked(false); b4.setChecked(false); b5.setChecked(false);
            B = String.valueOf(3);

        }

        if(b4.isChecked())
        { b1.setChecked(false); b2.setChecked(false); b3.setChecked(false); b5.setChecked(false);
            B = String.valueOf(4);

        }

        if(b5.isChecked())
        { b1.setChecked(false); b2.setChecked(false); b3.setChecked(false); b4.setChecked(false);
            B = String.valueOf(5);

        }


        return B;}

    public String ratingC()
    {
        String C = "";


        if(c1.isChecked())
    { c2.setChecked(false); c3.setChecked(false); c4.setChecked(false); c5.setChecked(false);
        C = String.valueOf(1);

    }

        if(c2.isChecked())
        { c1.setChecked(false); c3.setChecked(false); c4.setChecked(false); c5.setChecked(false);
            C = String.valueOf(2);

        }

        if(c3.isChecked())
        { c1.setChecked(false); c2.setChecked(false); c4.setChecked(false); c5.setChecked(false);
            C = String.valueOf(3);

        }

        if(c4.isChecked())
        { c1.setChecked(false); c2.setChecked(false); c3.setChecked(false); c5.setChecked(false);
            C = String.valueOf(4);

        }

        if(c5.isChecked())
        { c1.setChecked(false); c2.setChecked(false); c3.setChecked(false); c4.setChecked(false);
            C = String.valueOf(5);

        }

        return C;
    }
    public String ratingD()
    {
        String D = "";


        if(d1.isChecked())
        { d2.setChecked(false); d3.setChecked(false); d4.setChecked(false); d5.setChecked(false);
            D = String.valueOf(1);

        }

        if(d2.isChecked())
        { d1.setChecked(false); d3.setChecked(false); d4.setChecked(false); d5.setChecked(false);
            D = String.valueOf(2);

        }

        if(d3.isChecked())
        { d1.setChecked(false); d2.setChecked(false); d4.setChecked(false); d5.setChecked(false);
            D = String.valueOf(3);

        }

        if(d4.isChecked())
        { d1.setChecked(false); d2.setChecked(false); d3.setChecked(false); d5.setChecked(false);
            D = String.valueOf(4);

        }

        if(d5.isChecked())
        { d1.setChecked(false); d2.setChecked(false); d3.setChecked(false); d4.setChecked(false);
            D = String.valueOf(5);

        }

        return D;
    }



}

