package SecretkeyGuessing.Modified2;
// package Group;

public class SecretKeyGuesser {
  private SecretKey key;
  private int matched;
  private int max;

  public SecretKeyGuesser() {
    this.key = new SecretKey();
  }

  public void start() {
    String str = "MMMMMMMMMMMM";
    char[] current = str.toCharArray();
    guessRecursive(current, 0);
  }

  private void guessRecursive(char[] current, int index) {
    if (index == 12) {
      // Base case: found the correct key
      System.out.println("I found the secret key. It is " + new String(current));
      System.out.println("Number of guesses: " + key.getCounter());
      return;
    }

    char[] candidates = { 'M', 'O', 'C', 'H', 'A' };
    for (char candidate : candidates) {
      current[index] = candidate;
      if (matched == 12) {
        break;
      }
      if(max < matched){
        max = matched;
        break;
      }

      // Check if the guess is valid
      matched = key.guess(new String(current));

      if (matched != -1 && matched >= index) {
        // Additional constraint: Only proceed if the number of matched is at least the
        // current index
        guessRecursive(current, index + 1);
      }
    }
  }
}
