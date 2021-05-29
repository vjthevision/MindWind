package com.example.mindwind.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mindwind.Common.Startpage;
import com.example.mindwind.R;
import com.google.android.material.textfield.TextInputLayout;


public class SignUp extends AppCompatActivity {

    ImageView backBtn;
    Button next, login;
    TextView titleText,slideText;

    //Get Data Variables
    TextInputLayout fullName, username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks
        backBtn = findViewById(R.id.signup_back_btn);
        next = findViewById(R.id.signup_next_btn);
        login = findViewById(R.id.signup_login_btn);
        titleText = findViewById(R.id.signup_title_text);
        slideText =findViewById(R.id.signup_slide_text);

        //Hooks for Data Variable
        fullName = findViewById(R.id.signup_fullname);
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);


    }

    public void callNextSignupScreen(View view) {

        if(!validateFullName() | !validateUserName() | !validateEmail() | !validatePassword()){
            return;
        }

        Intent intent = new Intent(getApplicationContext(), SignUp2ndClass.class);

        // Add Transitions


//        String name = getIntent.getStringExtra("fullName");
//        String email = getIntent().getStringExtra("email");
//        String username = getIntent().getStringExtra("username");
//        String password = getIntent().getStringExtra("password");

        Pair[] pairs = new Pair[5];

        pairs[0] = new Pair<View, String>(backBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(login, "transition_signup_btn");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_btn");
        pairs[4] = new Pair<View, String>(slideText, "transition_slide_text");

        //Pass all fields to the next activity.
        intent.putExtra("fullName", fullName.getEditText().getText().toString().trim());
        intent.putExtra("email", email.getEditText().getText().toString().trim());
        intent.putExtra("username", username.getEditText().getText().toString().trim());
        intent.putExtra("password", password.getEditText().getText().toString().trim());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }

    private boolean validateFullName () {
        String val = fullName.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            fullName.setError("Name cannot be empty.");
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUserName () {
        String val = username.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";
        if (val.isEmpty()) {
            username.setError("Username cannot be empty.");
            return false;
        } else if (val.length() > 20) {
            username.setError("Username is too big.");
            return false;
        } else if (!val.matches(checkspaces)) {
            username.setError("No Whitespaces are allowed");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail () {
        String val = email.getEditText().getText().toString().trim();
        String checkemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            email.setError("Email cannot be empty.");
            return false;
        }
        else if (!val.matches(checkemail)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword () {
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                "(?=.*[0-9])" +         //atleast 1 digit
                //"(?=.*[a-z])" +         //atleast 1 lower case letter
                //"(?=.*[A-Z])" +         //atleast 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //atleast 1 special character
                "(?=\\S+$)" +           //no whitespaces
                ".{6,}" +               //atleast 6 characters
                "$";
        if (val.isEmpty()) {
            email.setError("Password cannot be empty.");
            return false;
        }
        else if (!val.matches(checkPassword)) {
            email.setError("Password should contain atleast 1 digit,6 characters, atleast 1 special character.");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    public void callUserStartupScreen(View view){
        startActivity(new Intent(SignUp.this, UserStartUpScreen.class));
    }

}