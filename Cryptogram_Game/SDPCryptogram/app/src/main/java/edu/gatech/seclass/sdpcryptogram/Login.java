package edu.gatech.seclass.sdpcryptogram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.sdpcryptogram.services.CryptoManagerImpl;
import edu.gatech.seclass.sdpcryptogram.services.Utils;

public class Login extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button LogInAsPlayerButton = (Button)findViewById(R.id.LogInAsPlayerButton);
        Button LogInAsAdminButton = (Button)findViewById(R.id.LogInAsAdminButton);
        final EditText EnterPlayerNameText = (EditText) findViewById(R.id.EnterPlayerNameText);

        LogInAsPlayerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String EnterPlayerName = EnterPlayerNameText.getText().toString();

                if (EnterPlayerName.equals("") || EnterPlayerName.equals("Enter PlayerName")){
                    Toast.makeText(Login.this,"Please input valid PlayerName",Toast.LENGTH_LONG).show();
                } else {

                    boolean validPlayer = CryptoManagerImpl.getPlayerManager().loginAs(EnterPlayerName);

                    if(!validPlayer) {
                        Toast.makeText(Login.this,"User does not exist",Toast.LENGTH_LONG).show();
                    } else {
                        Utils.loginUsername = EnterPlayerName;
                        startActivity(new Intent(Login.this, Player.class));
                    }
                }
            }
        });
        LogInAsAdminButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Admin.class));
            }
        });
    }
}