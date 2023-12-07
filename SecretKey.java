package Group;

import java.text.DecimalFormat;

public class SecretKey {
  private String correctKey;
  private int counter;

  public SecretKey() {
    // for the real test, your program will not know this
    correctKey = "CHAMOMOCHAHA"; 
    // correctKey = "MOCHAMOCHAMO"; 
    // correctKey = "MMMMMMMMMMMM"; 
    // correctKey = "AAAAAAAAAAAA";
    // correctKey = "OMAOHCCAAOHO";  
    // correctKey = "HAMMOCMOACAM";  
    counter = 0;
  }
  public int getCounter(){
    return counter;
  }
  public int guess(String guessedKey) {
    // System.out.println("guessedKey: " + guessedKey);
    counter++;
    // validation
    if (guessedKey.length() != correctKey.length()) {
      return -1;
    }
    int matched = 0;
    for (int i = 0; i < guessedKey.length(); i++) {
      char c = guessedKey.charAt(i);
      if (c != 'M' && c != 'O' && c != 'C' && c != 'H' && c != 'A') {  
       return -1;
      }
      if (c == correctKey.charAt(i)) {
        matched++;
      }
    }
    if (matched == correctKey.length()) {
      System.out.println("Number of guesses: " + counter);
    }
    return matched;
  }

  public static void main(String[] args) {
    new SecretKeyGuesser().start();
  }
}
