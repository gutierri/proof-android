package me.gutierri.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signInButton = (Button) findViewById(R.id.button_sign_in);
        signInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d("SignIn", "SignIn button clicked");
        startActivity(
            new Intent(this, MainActivity.class)
        );
    }
}