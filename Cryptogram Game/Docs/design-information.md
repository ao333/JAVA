**1)**
Start application **StartApplication()**
Logging in as either Admin or Player depends on if the inputted ID is matched **Match()** to **AdminID** or **PlayerID**.

**2)**
(1) Choosing cryptograms is generally color-coded as **Black**.
(2) Solving cryptograms is generally **Blue**.
(3) View Previous cryptograms is generally **Black**.
(4) View Player Ratings is generally **Green**.

**3)**
(1) Add cryptograms is **Red**.
(2) Add local players is **Pink**.

**4)** The database will be omitted, as the External Web Server will be assumed to save persistent information and execute functions.

**5)** The External Web Service Utility will function as our database and computer.
a) Send updated player ratings **SendPlayerRatings()**
b) Send new cryptograms **SendCryptogram()** and receive new UID **AssignUID()**
c) Request cryptograms list **RequestCryptoList()**
d) Request Player Ratings **RequestPlayerRatings()**

**6)** Cryptogram has **EncodedPhrase:String**, **SubsitutionCipher:Char**, and **Solution:String**.

**7)** Cryptogram shall only encode alphabetic characters but it may include other characters. This is achieved with **Restrictions(Regex)**.

**8)**
a) b) c) **FirstName:String, LastName:String, PlayerID:String** under Player and PlayerRating classes, since PlayerRating will save the progress of newly added players also.

**9)**
a) b) **AddCrypto(Solution, EncodedPhrase)**
c) **EditCrypto(Solution, EncodedPhrase)**
d) **SaveCryptoWithUID(Cyptogram,UID)**
Confirmation message and unique identifier **AssignUID(Cyptogram,UID), SendConfirmation(UID)**

**10)**
a) Choose a cryptogram **RequestCryptoList(), ChooseCrypto(UID)**
b) View the chosen cryptogram **ViewCryptoProgress(UID, SavedCryptoProgress)**
c) **AssignReplaceLetter()**
**Encode(Solution, SubsitutionCipher, Restrictions)**
**MatchCorrect(ReplaceLetter, Solution)**
**MatchInCorrect(ReplaceLetter, Solution)**
**ViewChanges(MatchCorrect)**

***Please note that there are no "decoding" because that process is done by the human. The human computes the decoded message and inputs it into the game. The game then matches it to the Solution.***

d) **SubmitSolution(ReplaceLetter)**
Indicate whether correct **Result(MatchCorrect)**
Return to the List**ReturnTo(CryptoList)**

**11)**
Identifier **RequestCryptoList(UID, CountCorrectCrypto, CountIncorrectCrypto)**
whether the player has solved it**CountCorrectCrypto:boolean**
number of incorrect solutions **CountIncorrectCrypto:Int**

**12)** list of player ratings shall display
 his or her name **FirstName:String, LastName:String, PlayerID:String**
 number of cryptograms solved **CountCorrectPlayer(MatchCorrect)**
 the number of cryptograms started **CountStart()**
 total number of incorrect solutions **CountIncorrectPlayer:(MatchInCorrect)**
 sorted in descending order by the number of cryptograms solved **Sort(CorrectCount, Descend)**

**13)** I did not consider this requirement because it does not affect the design directly.