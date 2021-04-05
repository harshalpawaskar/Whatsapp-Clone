package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    Button loginOrSignUpButton;
    TextView toggleTextView;
    EditText usernameEditText,passwordEditText;

    Boolean loginMode = false;

    public void RedirectActivity(){
        if(ParseUser.getCurrentUser()!=null){
            Intent intent = new Intent(this,UsersListActivity.class);
            finish();
            startActivity(intent);
        }
    }

    public void ToggleLoginSignUp(View view){
        if(loginMode){
            loginMode = false;
            loginOrSignUpButton.setText("SignUp");
            toggleTextView.setText("or, Login");
        }
        else {
            loginMode = true;
            loginOrSignUpButton.setText("Login");
            toggleTextView.setText("or, SignUp");
        }
    }

    public void LoginOrSignUp(View view){

        if(loginMode){
            ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e==null){
                        RedirectActivity();
                    }
                    else {
                        if(e.getMessage().toLowerCase().contains("java")) {
                            Toast.makeText(getApplicationContext(),e.getMessage().substring(e.getMessage().indexOf(" ")),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
        else {
            ParseUser user = new ParseUser();
            user.setUsername(usernameEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        RedirectActivity();
                    } else {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = (EditText) findViewById(R.id.userNameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginOrSignUpButton = (Button) findViewById(R.id.LoginOrSignUpButton);
        toggleTextView = (TextView) findViewById(R.id.toggleTextView);

        if(ParseUser.getCurrentUser()!=null){
            RedirectActivity();
        }
    }
}