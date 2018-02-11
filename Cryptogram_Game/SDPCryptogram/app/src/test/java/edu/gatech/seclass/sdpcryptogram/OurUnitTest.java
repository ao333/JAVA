package edu.gatech.seclass.sdpcryptogram;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import edu.gatech.seclass.sdpcryptogram.domain.MyCryptogram;
import edu.gatech.seclass.sdpcryptogram.domain.MyPlayer;
import edu.gatech.seclass.sdpcryptogram.services.CryptoManagerImpl;
import edu.gatech.seclass.sdpcryptogram.services.DAO;
import edu.gatech.seclass.sdpcryptogram.services.Distance;
import edu.gatech.seclass.sdpcryptogram.services.IAdminManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

//public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//    }
//}
public class OurUnitTest {


    private static DAO dao;
    private static IAdminManager adminManager;


    @BeforeClass
    public static void setup() {
        File file = new File("junittest");
        file.delete();
        dao = new DAO("junittest", null);

        adminManager = CryptoManagerImpl.getAdminManager();

        ((CryptoManagerImpl) adminManager).setDao(dao);

    }

    /*Test adding new player function */

    @Test (expected = IllegalArgumentException.class)
    public void testAdmin_AddNewPlayer1() {
        MyPlayer player = new MyPlayer("", "John", "Doe", false);
        adminManager.addNewPlayer(player);

    }
    @Test
    public void testAdmin_AddNewPlayer2(){
        MyPlayer player = new MyPlayer("TestPlayer1", "JohnDoe", "", false);
        adminManager.addNewPlayer(player);
        assertEquals("TestPlayer1",player.getUsername());
    }

    @Test
    public void testAdmin_AddNewPlayer4() {
        MyPlayer player = new MyPlayer("TestPlayer1", "John", "Doe", false);
        adminManager.addNewPlayer(player);
        assertEquals("TestPlayer1",player.getUsername());
    }
    @Test
    public void testAdmin_AddNewPlayer5() throws Exception {
        MyPlayer player = new MyPlayer("TestPlayer1", "Will", "Smith", false);
        adminManager.addNewPlayer(player);
    }

    /*Test adding new cryptogram function */

    @Test (expected = IllegalStateException.class)
    public void testAdmin_AddCryptogram1() {
        MyCryptogram cryptogram = new MyCryptogram("a", "");
        cryptogram = adminManager.addCryptogram(cryptogram);
    }

    @Test (expected = IllegalStateException.class)
    public void testAdmin_AddCryptogram2() {
        MyCryptogram cryptogram = new MyCryptogram("a", "");
        cryptogram = adminManager.addCryptogram(cryptogram);
    }

    @Test (expected = IllegalStateException.class)
    public void testAdmin_AddCryptogram3() {
        MyCryptogram cryptogram = new MyCryptogram("", "a");
        cryptogram = adminManager.addCryptogram(cryptogram);
        assertNotNull(cryptogram.getUniqueID());
        MyCryptogram cryptogram2 = adminManager.addCryptogram(cryptogram);
    }

    @Test
    public void testAdmin_AddCryptogram4() {
        MyCryptogram cryptogram = new MyCryptogram("zoo", "ann");
        cryptogram = adminManager.addCryptogram(cryptogram);
        assertNotNull(cryptogram.getUniqueID());
        MyCryptogram cryptogram2 = adminManager.addCryptogram(cryptogram);
    }
    

  
    @Test
    public void testShifter2() {
        String SolutionPhraseText = "B";
        String MatchingEncodedPhraseText = "A";
        StringBuilder result = Distance.Shifter(SolutionPhraseText, MatchingEncodedPhraseText);
        assertEquals("1,", result.toString());
    }
    @Test
    public void testShifter3() {
        String SolutionPhraseText = "A";
        String MatchingEncodedPhraseText = "B";
        StringBuilder result = Distance.Shifter(SolutionPhraseText, MatchingEncodedPhraseText);
        assertEquals("25,", result.toString());
    }

    @Test
    public void testShifter4() {
        String SolutionPhraseText = "CBD";
        String MatchingEncodedPhraseText = "ABC";
        StringBuilder result = Distance.Shifter(SolutionPhraseText, MatchingEncodedPhraseText);
        assertEquals("2,0,1,", result.toString());
    }

    /*Test failed cases with nonalphabetic characters in string*/
    @Test (expected = IllegalStateException.class)
    public void testShifter5() {
        String SolutionPhraseText = "APpl ";
        String MatchingEncodedPhraseText = "Bpp7%";
        StringBuilder result = Distance.Shifter(SolutionPhraseText, MatchingEncodedPhraseText);
    }


    @Test
    public void testSaveCryptogram1() {
        String uniqueID = "Cptg0002";
        MyCryptogram cryptogram = new MyCryptogram("ABC", "BCD");
        cryptogram.setUniqueID(uniqueID);
        dao.saveCryptogram(cryptogram);

    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testSaveCryptogram2() {
        String uniqueID = "testID";
        MyCryptogram cryptogram = new MyCryptogram("ABC", "BCD");
        cryptogram.setUniqueID(uniqueID);
        dao.saveCryptogram(cryptogram);

    }


    @Test
    public void testequals1() {
        String uniqueID = "testID";
        MyCryptogram cryptogram = new MyCryptogram("ABC", "BCD");
        cryptogram.setUniqueID(uniqueID);
        dao.saveCryptogram(cryptogram);

    }




}
