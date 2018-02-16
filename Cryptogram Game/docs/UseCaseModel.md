# Use Case Model

**Author**: ashen38

## 1 Use Case Diagram
![ashen38](Other/UseCaseModel.png?raw=true)

## 2 Use Case Descriptions

Admin
- Requirements: Logs the user in as Admin
- Pre-conditions: Application is booted
- Post-conditions: Use Admin functions
- Scenarios: Add Player, Add Cryptograms
  Add Player
  - Requirements: Add new player logins
  - Pre-conditions: Logged in as Admin
  Add Cryptograms
  - Requirements: Add new cryptogram information
  - Pre-conditions: Logged in as Admin

Player
- Requirements: Logs the user in as Player
- Pre-conditions: Application is booted
- Post-conditions: Use Player functions
- Scenarios: Cryptogram List, View Player Ratings
  View Player Ratings
  - Requirements: View Player Ratings
  - Pre-conditions: Logged in as Player
  - Post-conditions: Use Player Ratings functions
  Cryptogram List
  - Requirements: Chooses from Cryptogram List
  - Pre-conditions: Logged in as Player
  - Post-conditions: Use Cryptogram List functions
  - Scenarios: Solved Cryptogram, Solve Cryptogram
    Solved Cryptogram
    - Requirements: See previously solved Cryptograms
    - Pre-conditions: Chosen from Cryptogram List
    Solve Cryptogram
    - Requirements: Solves the Cryptogram
    - Pre-conditions: Chosen from Cryptogram List
    - Post-conditions: Use Solve Cryptogram functions
    - Scenarios: Encode and Match Solutions
      Encode and Match Solutions
      - Requirements: Encodes and matches input to solutions
      - Pre-conditions: Solved the Cryptogram
      - Post-conditions: Updates Results, Changes, and Ratings
