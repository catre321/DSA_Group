package Group;

public class SecretKeyGuesser {
  private SecretKey key;
  private int currMatches = 0;
  private int currMaxMatches = 0;
  private int currIndex = 0;
  private int charOfIndex = 0;
  private boolean flag = false; // flag for NOT duplicate guess

  private char[] _charOf = new char[] { 'M', 'O', 'C', 'H', 'A' };
  private char[] currKey = new char[12];

  public void start() {
    // brute force key guessing
    key = new SecretKey();

    // 1st method
    // for (int i = 0; i < 5; i++) {
    //   if (FirstMethod(0, i) == 12)
    //     break; // use recursion to try difference key of character
    // }

    // 2nd method

    String str = "MMMMMMMMMMMM";
    while (currMatches != 12) {
      if (!flag) {
        // call to check the key
        currMatches = key.guess(str);
        System.out.println("Guessing... " + str);
      } else {
        flag = false;
      }
      //init the currMaxMatches for the first run 
      if (key.getCounter() == 1) {
        currMaxMatches = currMatches;
      }
      // if the key is correct break loop
      if (currMatches == 12) {
        break;
      }
      // save case if the charOfIndex is over the 5 possible characters 
      if (charOfIndex >= 5) {
        charOfIndex = 0;
      }
      // call for validate the current key
      str = SecondMethod(str);
    }
    System.out.println("Done: " + String.valueOf(currKey));

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
    if (currMaxMatches < currMatches) { // correct character
      System.out.println("correct char");
      currIndex++; // point next key character
      charOfIndex = 0; // reset the possible character pointer 
      currMaxMatches = currMatches; // update the max value
      flag = true; // avoid duplicate guess() call 
    } else if (currMaxMatches > currMatches) { // previous char is correct
      System.out.println("previous char is correct");
      currKey = current.toCharArray();
      charOfIndex -= 1; // point back to previous possible character
      currKey[currIndex] = _charOf[charOfIndex]; // switch back to previous character
      currIndex++; // point next key character
      charOfIndex = 0; // reset the possible character pointer 
      currMatches = currMaxMatches; // roll back the currMatches value
      flag = true; // avoid duplicate guess() call 
    } else {
      System.out.println("try next char");
      currKey = current.toCharArray();
      charOfIndex++; // point to next possible character to check
      currKey[currIndex] = _charOf[charOfIndex]; // switch to next character
    }
    System.out.println("the next string: " + String.valueOf(currKey));
    return String.valueOf(currKey);
  }
}
