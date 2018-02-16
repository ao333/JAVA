package edu.gatech.seclass.sdpcryptogram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.gatech.seclass.sdpcryptogram.domain.MyCryptogram;
import edu.gatech.seclass.sdpcryptogram.domain.MyPlayerCryptogramSolution;
import edu.gatech.seclass.sdpcryptogram.domain.MyPlayerRating;
import edu.gatech.seclass.sdpcryptogram.services.CryptoManagerImpl;
import edu.gatech.seclass.sdpcryptogram.services.Distance;
import edu.gatech.seclass.sdpcryptogram.services.IPlayerManager;
import edu.gatech.seclass.sdpcryptogram.services.Utils;

public class Player extends AppCompatActivity {

    private List<MyPlayerCryptogramSolution> cryptogramSolutions;
    private static IPlayerManager playerManager = CryptoManagerImpl.getPlayerManager();

    private static String selectedCryptogramID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        List<MyPlayerCryptogramSolution> cryptogramSolutions = playerManager.listOfCryptogramDataToShow(Utils.loginUsername);
        this.cryptogramSolutions = cryptogramSolutions;

        List<String> dataList = new ArrayList<>();
        for (MyPlayerCryptogramSolution solution : cryptogramSolutions) {
            String cryptoStr = "UID:" + solution.getCryptogramID();
            cryptoStr += ", " + (solution.getSolved() ? "Solved" : "Unsolved");
            cryptoStr += ", Incorrect:" + (solution.playerIncorrectSubmissions());

            dataList.add(cryptoStr);
        }

        String[] dataArray = Utils.convertListToArray(dataList);

        Button TestButton = (Button) findViewById(R.id.TestButton);
        Button SubmitButton = (Button) findViewById(R.id.SubmitButton);

        ListView CryptogramListList = (ListView) findViewById(R.id.CryptogramListList);
//        String[] DummyCrypto = {"UID" + ", Solved?" + ", #Incorrects", "UID2" + ", Solved?2" + ", #Incorrects2", "UID3" + ", Solved?3" + ", #Incorrects3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataArray);
        CryptogramListList.setAdapter(adapter);

        ListView PlayerRatingsList = (ListView) findViewById(R.id.PlayerRatingsList);

        List<MyPlayerRating> playerRatings = fetchPlayerRatings();// CryptoManagerImpl.getPlayerManager().listOfPlayerRatings();
        List<String> playerRatingStrs = new ArrayList<String>();
        for (MyPlayerRating playerRating : playerRatings) {
            String ratingStr = "Name:" + playerRating.getDisplayName();
            ratingStr += ", Solved:" + playerRating.getCryptogramsSolved();
            ratingStr += ", Started:" + playerRating.getCryptogramsStarted();
            ratingStr += ", Incorrect:" + playerRating.getIncorrectSolutionSubmitted();

            playerRatingStrs.add(ratingStr);
        }

        String[] ratingData = Utils.convertListToArray(playerRatingStrs);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ratingData);
        PlayerRatingsList.setAdapter(adapter2);

        final EditText CryptogramEditText = (EditText) findViewById(R.id.CryptogramEditText);
        final EditText YourSolutionText = (EditText) findViewById(R.id.YourSolutionText);
        final EditText ImpliedSubsitutionCipherText2 = (EditText) findViewById(R.id.ImpliedSubsitutionCipherText2);

        CryptogramListList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                try {
                    MyPlayerCryptogramSolution cryptogramSolution = Player.this.cryptogramSolutions.get(position);
                    MyCryptogram cryptogram = playerManager.findCryptogramForID(cryptogramSolution.getCryptogramID());

                    selectedCryptogramID = cryptogram.getUniqueID();
                    CryptogramEditText.setText(cryptogram.getMatchingEncodedPhrase());
                    YourSolutionText.setText(cryptogramSolution.latestPlayerSolution());
                } catch(Exception e){
                    Toast.makeText(Player.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    StringBuilder result = Distance.Shifter(YourSolutionText.getText().toString(), CryptogramEditText.getText().toString());
                    ImpliedSubsitutionCipherText2.setText(result);

                    MyCryptogram cryptogram = CryptoManagerImpl.getPlayerManager().findCryptogramForID(selectedCryptogramID);
                    if ((YourSolutionText.getText().toString().equalsIgnoreCase(cryptogram.getSolutionPhrase()))) {
                        Toast.makeText(Player.this, "Correct!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Player.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                    }
            } catch(Exception e){
                    Toast.makeText(Player.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String solutionText = YourSolutionText.getText().toString();
                String username = Utils.loginUsername;
                try {
                    MyCryptogram cryptogram = CryptoManagerImpl.getPlayerManager().findCryptogramForID(selectedCryptogramID);
                    CryptoManagerImpl.getPlayerManager().solveCryptogramForPlayer(username, selectedCryptogramID, solutionText);
                    if ((YourSolutionText.getText().toString().equalsIgnoreCase(cryptogram.getSolutionPhrase()))) {
                        Toast.makeText(Player.this, "Correct! Submitted!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Player.this, "Incorrect! Submitted!", Toast.LENGTH_SHORT).show();
                    }
                } catch(Exception e){
                    Toast.makeText(Player.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private static List<MyPlayerRating> fetchPlayerRatings() {

        List<MyPlayerRating> playerRatings = CryptoManagerImpl.getPlayerManager().listOfPlayerRatings();

        Collections.sort(playerRatings, new Comparator<MyPlayerRating>() {
            @Override
            public int compare(MyPlayerRating playerRating, MyPlayerRating t1) {
                if(playerRating.getCryptogramsSolved() >= t1.getCryptogramsSolved()) {
                    return -1;
                }
                return 1;
            }
        });

        return playerRatings;
    }

}