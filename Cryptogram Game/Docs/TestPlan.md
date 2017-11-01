# Test Plan

**Author**: ashen38

## 1 Testing Strategy

### 1.1 Overall strategy

Our team will perform unit and system testing. Specifically, we seek to test each individual requirement, classes, and methods. xsun311 will also be testing the overall system. JUnit test cases can be found under package/OurUnitTests.

### 1.2 Test Selection

We will use black-box testing, as testers and designers can work independently. This can also bring a new perspective as designers tend to have biased view of potential errors. Boundary analysis and Class Partitioning analysis are efficient in identifying some common bugs.

Each class will be tested for expected outcomes, including exceptions for some common operator errors. 

### 1.3 Adequacy Criterion

We seek to achieve high structural coverage by unit-testing key functions pertaining to the project requirements. **All failed tests and known errors have been resolved.**

For unforseen input errors, pop-up toasts should catch those exceptions.

### 1.4 Bug Tracking

Bugs will be recorded and tested by testers on TestPlan.md. These are continously updated on the team git repository.

Major issues will be discussed on slack and hangouts with enhanced communication.

### 1.5 Technology

JUnit and Android Studio's Emulator will be used for development and testing.

Git is used for version control

## 2 Test Cases

### 2.1 Admin Unit Test

July 11, 2017

ID | Purpose | Method | Expected | Actual | Pass/Fail | Detail
--- | --- | --- | --- | --- | --- | ---
001 | Test adding new player function  | add_new_player(null) | return error msg | Result | Fail | Fail to throw exception when playername is null
002 | Test adding new player function  | add_new_player(new username) | add new player to player list  | Result | Pass| 
003 | Test adding new player function  | add_new_player(duplicate username) | return error msg | Result | Pass | 
004 | Test adding new cryptogram function  | add_new_cryptogram(null) | return error msg | Result | Fail | Other
005 | Test adding new cryptogram function  | add_new_cryptogram(duplicate cryptogram) | return error msg | Result | Fail | Same encoded phrase, different solutions
006 | Test adding new cryptogram function  | add_new_cryptogram(new cryptogram) | add new cryptogram to cryptogram list  | Result | Fail | Need constrains on encoded phrase and solution
007 | Test editing existing cryptogram function  | edit_existing_cryptogram(new cryptogram) | edit existing cryptogram string/solution from cryptogram list  | Result | Pass/Fail | Not available right now

### 2.2 Player Functional Testing

ID | Purpose | Method | Expected | Actual | Pass/Fail | Detail
--- | --- | --- | --- | --- | --- | ---
008 | Test viewing player rank function  | view_player_rank(username) | return top players list and player's own rank | Result | Pass
009 | Test viewing player history function  | view_history(username) | return solved/submitted/started cryptograms list | Result | Pass
010 | Test viewing player grade function  | view_history(username) | return # of submitted, # of solved and the successful rate of player  | Result | Pass
011 | Test solving cryptogram function  | solve_cryptogram(stuatus = 'started') | return corresponding status change to player history | Result | Pass
012 | Test solving cryptogram function  | solve_cryptogram(stuatus = 'submitted') | return corresponding status change to player history | Result | Pass
013 | Test checking solution of cryptogram function  | check_solution(stuatus = 'submitted') | return corresponding status change to player history/player success rate/ player rank | Result | Pass 

July 12, 2017

ID | Purpose | Method | Expected | Result | Pass/Fail | Detail
--- | --- | --- | --- | --- | --- | ---
001 | Test adding new player function  | add_new_player(null) | return error msg | Result | Fail | Fail to throw exception when playername is null
002 | Test adding new player function  | add_new_player(new username) | add new player to player list  | Result | Pass| 
003 | Test adding new player function  | add_new_player(duplicate username) | return error msg | Result | Pass | 
004 | Test adding new cryptogram function  | add_new_cryptogram(null) | return error msg | Result | Fail | Other
005 | Test adding new cryptogram function  | add_new_cryptogram(duplicate cryptogram) | return error msg | Result | Fail | Same encoded phrase, different solutions
006 | Test adding new cryptogram function  | add_new_cryptogram(new cryptogram) | add new cryptogram to cryptogram list  | Result | Fail | Need constrains on encoded phrase and solution

ID | Purpose | Method | Expected | Result | Pass/Fail | Detail
--- | --- | --- | --- | --- | --- | ---
007 | Test viewing player rank function  | view_player_rank(username) | return top players list and player's own rank | Result | Fail | Player list shall display name (without comma), Not Username, Sorted by the number of Cryptogram Solved, not sorted currently
008 | Test viewing player grade function  | view_history(username) | return # of submitted, # of solved and the successful rate of player  | Result | Pass| Other
009 | Test solving cryptogram function  | solve_cryptogram(stuatus = 'started') | return corresponding status change to player history | Result | Pass/Fail | To be done
010 | Test solving cryptogram function  | solve_cryptogram(stuatus = 'submitted') | return corresponding status change to player history | Result | Pass/Fail | To be done
011 | Test checking solution of cryptogram function  | check_solution(stuatus = 'submitted') | return corresponding status change to player history/player success rate/ player rank | Result | To be done | Other

July 13, 2017

ID | Purpose | Method | Expected | Result | Pass/Fail | Detail
--- | --- | --- | --- | --- | --- | ---
001 | Sort | AVD | Sorted | Not Sorted | Fail | Fixed
002 | Player Rating | AVD | Name Listed | Username Listed | Fail | Fixed
003 | Confirmation with UID | AVD | Confirmation with UID | No UID/Wrong UID | Fail | Fixed

July 14, 2017

Test ID | Purpose | Method | Expected | Result | Pass/Fail | Detail
--- | --- | --- | --- | --- | --- | ---
001 | Test adding new player function  | add_new_player(null) | return error msg | Fixed | Pass | Exceptions for null input
002 | Test adding new player function  | add_new_player(new username) | add new player to player list  | fixed | Pass| 
004 | Test adding new cryptogram function  | add_new_cryptogram(null) | return error msg | Fixed | Pass | Other
005 | Test adding new cryptogram function  | add_new_cryptogram(duplicate cryptogram) | return error msg | Fixed | Pass | Validate function works
006 | Test adding new cryptogram function  | add_new_cryptogram(new cryptogram) | add new cryptogram to cryptogram list  | Fixed | Pass | Shift function works

Test ID | Purpose | Method | Expected | Result | Pass/Fail | Detail
--- | --- | --- | --- | --- | --- | ---
007 | Test viewing player rank function  | view_player_rank(username) | return top players list and player's own rank | Fixed | Pass| Player list shall display name (without comma), Not Username, Sorted by the number of Cryptogram Solved, not sorted currently
008 | Test viewing player grade function  | view_history(username) | return # of submitted, # of solved and the successful rate of player  | | Pass| Other
009 | Test solving cryptogram function  | solve_cryptogram(stuatus = 'started') | return corresponding status change to player history |  | Pass | 
010 | Test solving cryptogram function  | solve_cryptogram(stuatus = 'submitted') | return corresponding status change to player history |  | Pass| 
011 | Test checking solution of cryptogram function  | check_solution(stuatus = 'submitted') | return corresponding status change to player history/player success rate/ player rank |  | Pass |

