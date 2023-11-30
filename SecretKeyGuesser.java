package Group;



public class SecretKeyGuesser {
  SecretKey key;
  public void start() {
    // brute force key guessing
    key = new SecretKey();
    // String str = "MMMMMMMMMMMM";
    // while (key.guess(str) != 12) {
    //   str = next(str);
    //   System.out.println("Guessing... " + str);
    // }
    // System.out.println("I found the secret key. It is " + str);
    for (int i=0; i < 5; i++) {
      if (test(0, i) == 12) break; // use recursion to try difference key of character
    }
    // System.out.println("I found the secret key. It is " + str);
    System.out.println("Done: " + String.valueOf(curr));
  
  }

  // static int order(char c) {
  //   if (c == 'M') {
  //     return 0;
  //   } else if (c == 'O') {
  //     return 1;
  //   } else if (c == 'C') {
  //     return 2;
  //   } else if (c == 'H') {
  //     return 3;
  //   } 
  //   return 4;
  // }

  // static char charOf(int order) {
  //   if (order == 0) {
  //     return 'M';
  //   } else if (order == 1) {
  //     return 'O';
  //   } else if (order == 2) {
  //     return 'C';
  //   } else if (order == 3) {
  //     return 'H';
  //   } 
  //   return 'A';
  // }

  char[] _charOf = new char[] { 'M', 'O', 'C', 'H', 'A' };

  // return the next value in 'MOCHA' order, that is
  // M < O < C < H < A
  // public String next(String current) {
  //   char[] curr = current.toCharArray();
  //   for (int i = curr.length - 1; i >=0; i--) {
  //     if (order(curr[i]) < 4) {
  //       // increase this one and stop
  //       curr[i] = charOf(order(curr[i]) + 1);
  //       break;
  //     }
  //     curr[i] = 'M';
  //   }
  //   return String.valueOf(curr);
  // }
  // CHAMOMOCHAHA

  private char[] curr = new char[12];
  private int count = 0;

  public int test(int pos, int c) {
    curr[pos] = _charOf[c];
    
    if (pos == 11) {
      // System.out.println(curr);
      count++;
      if (count % 1000000 == 0) {
        System.out.println(count + " " + String.valueOf(curr)); // print per 1 million count to increase running speed
      }
      int guess = key.guess(String.valueOf(curr));
      // System.out.println("Guess: " + guess);
      return guess;
    }
    int max = 0;
    for (int i=0; i<5; i++) {
      int guess = test(pos + 1, i);
      // if (guess + pos + 2 <= 12) return 12-pos-1;
      if (guess + pos + 1 <= 11) return 11-pos; // terminate if all the character on top is no-hope
      
      if (guess > max) max = guess;
      if (max == 12) return max;
    }
    return max;
  }
}
