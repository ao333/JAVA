package edu.gatech.seclass.sdpcryptogram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.sdpcryptogram.domain.MyCryptogram;
import edu.gatech.seclass.sdpcryptogram.domain.MyPlayer;
import edu.gatech.seclass.sdpcryptogram.services.CryptoManagerImpl;
import edu.gatech.seclass.sdpcryptogram.services.Distance;

public class Admin extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        final EditText SolutionPhraseText = (EditText) findViewById(R.id.SolutionPhraseText);
        final EditText MatchingEncodedPhraseText = (EditText) findViewById(R.id.MatchingEncodedPhraseText);
        final EditText ImpliedSubsitutionCipherText = (EditText) findViewById(R.id.ImpliedSubsitutionCipherText);
        final EditText FirstNameText = (EditText) findViewById(R.id.FirstNameText);
        final EditText LastNameText = (EditText) findViewById(R.id.LastNameText);
        final EditText PlayerNameText = (EditText) findViewById(R.id.PlayerNameText);
        Button TestEncryptionButton = (Button) findViewById(R.id.TestEncryptionButton);
        Button AddNewCryptogramButton = (Button) findViewById(R.id.AddNewCryptogramButton);
        Button AddNewPlayerButton = (Button) findViewById(R.id.AddNewPlayerButton);

        AddNewCryptogramButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String Solution = SolutionPhraseText.getText().toString();
                String EncodedPhrase = MatchingEncodedPhraseText.getText().toString();

                try {
                    MyCryptogram mc = new MyCryptogram(Solution, EncodedPhrase);
                    mc = CryptoManagerImpl.getAdminManager().addCryptogram(mc);
                    if(mc.getUniqueID() != null) {
                        Toast.makeText(Admin.this, "Cryptogram Added! ID=" + mc.getUniqueID(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Admin.this, "Error adding cryptogram!", Toast.LENGTH_SHORT).show();
                    }
                } catch(Exception e) {
                    Toast.makeText(Admin.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AddNewPlayerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String FirstName = FirstNameText.getText().toString();
                String LastName = LastNameText.getText().toString();
                String PlayerName = PlayerNameText.getText().toString();

                try {
                    if(!PlayerName.equals("")&&!FirstName.equals("")&&!LastName.equals("")) {
                        MyPlayer player = new MyPlayer(PlayerName, FirstName, LastName, true);
                        CryptoManagerImpl.getAdminManager().addNewPlayer(player);
                        Toast.makeText(Admin.this, "New Player Added!", Toast.LENGTH_SHORT).show();
                    }else {Toast.makeText(Admin.this, "Add More Info!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    Toast.makeText(Admin.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TestEncryptionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (SolutionPhraseText.length() != MatchingEncodedPhraseText.length()){
                    Toast.makeText(Admin.this, "String Count Mismatch!", Toast.LENGTH_SHORT).show();
                }try{
                StringBuilder result = Distance.Shifter(SolutionPhraseText.getText().toString(), MatchingEncodedPhraseText.getText().toString());
                ImpliedSubsitutionCipherText.setText(result);
            } catch(Exception e) {
                    Toast.makeText(Admin.this, "Input Mismatch!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    };
}