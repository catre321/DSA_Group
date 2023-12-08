// package DSA_Group;

public class SecretKeyGuesser {
    private SecretKey key;
    private int matched;

    public SecretKeyGuesser() {
        this.key = new SecretKey();
    }

    public void start() {
        String str = "MMMMMMMMMMMM";
        char[] current = str.toCharArray();
        guessRecursive(current, 0);
    }

    private void guessRecursive(char[] current, int index) {
        System.out.println("Number of guesses (tracking): " + key.getCounter());  
        System.out.println("I found the secret key. It is " + new String(current));

        char[] candidates = { 'M', 'O', 'C', 'H', 'A' };
        for (char candidate : candidates) {
            // Break if the correct key is found
            if (matched == 12) {
                break;
            }
            current[index] = candidate;
            // Check if the guess is valid
            matched = key.guess(new String(current));

            if (matched != -1 && matched > index) {
                // `matched > index` (additional pruning condition): Only proceed to the char
                // if the number of `matched` is larger than the `current index`
                // If the number of matched characters so far is less than the current index,
                // skip unnecessary recursive calls.
                guessRecursive(current, index + 1);
            }
        }
    }
}
