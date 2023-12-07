package Group;

public class SecretKeyGuesser {
  private SecretKey key;
  private int currMatches = 0;
  private int currMaxMatches = 0;
  private int currIndex = 0;
  private int charOfIndex = 1;
  private boolean flag = false; // flag for NOT duplicate guess
    
  private char[] _charOf = new char[] { 'M', 'O', 'C', 'H', 'A' };
  private char[] curr = new char[12];


  public void start() {
    // brute force key guessing
    key = new SecretKey();

    // 1st method
    // for (int i = 0; i < 5; i++) {
    // if (FirstMethod(0, i) == 12)
    // break; // use recursion to try difference key of character
    // }

    // 2nd method

    String str = "MMMMMMMMMMMM";
    while (currMatches != 12) {
      if (!flag) {
        currMatches = key.guess(str);
        System.out.println("Guessing... " + str);
      } else {
        flag = false;
      }
      if (key.getCounter() == 1) {
        currMaxMatches = currMatches;
      }
      if (currMatches == 12) {
        break;
      }
      if (charOfIndex >= 5) {
        charOfIndex = 1;
      }
      str = SecondMethod(str);
    }

    // // System.out.println("I found the secret key. It is " + str);
    System.out.println("Done: " + String.valueOf(curr));

  }

  // CHAMOMOCHAHA

  // 1st method
  // public int FirstMethod(int pos, int c) {
  //   curr[pos] = _charOf[c];

  //   if (pos == 11) {
  //     // System.out.println(curr);
  //     count++;
  //     if (count % 1000000 == 0) {
  //       System.out.println(count + " " + String.valueOf(curr)); // print per 1 million count to increase running speed
  //     }
  //     int guess = key.guess(String.valueOf(curr));
  //     // System.out.println("Guess: " + guess);
  //     return guess;
  //   }
  //   int max = 0;
  //   for (int i = 0; i < 5; i++) {
  //     int guess = FirstMethod(pos + 1, i);
  //     // if (guess + pos + 2 <= 12) return 12-pos-1;
  //     if (guess + pos + 1 <= 11)
  //       return 11 - pos; // terminate if all the character on top is no-hope

  //     if (guess > max)
  //       max = guess;
  //     if (max == 12)
  //       return max;
  //   }
  //   return max;
  // }

  // 2nd method
  private String SecondMethod(String current) {
    System.out.println("currIndex: " + currIndex);
    System.out.println("charOfIndex: " + charOfIndex);
    if (currMaxMatches < currMatches) { // correct char
      System.out.println("correct char");
      currIndex++;
      charOfIndex = 1;
      currMaxMatches = currMatches;
      flag = true;
    } else if (currMaxMatches > currMatches) { // previous char is correct
      System.out.println("previous char is correct");
      curr = current.toCharArray();
      charOfIndex -= 2;
      curr[currIndex] = _charOf[charOfIndex];
      currIndex++;
      charOfIndex = 1;
      currMatches = currMaxMatches;
      flag = true;
    } else if (currMaxMatches == currMatches) {
      System.out.println("try next char");
      curr = current.toCharArray();
      curr[currIndex] = _charOf[charOfIndex];
      charOfIndex++;
    }
    System.out.println("the next string: " + String.valueOf(curr));
    return String.valueOf(curr);
  }
}
