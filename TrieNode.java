public class TrieNode {
    private char ch;                                            //To store character in every trie node.
    TrieNode[] arr;                                             //To store children of the trie node.
    private boolean isWordComplete;                             //To check if word is complete or not.

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public TrieNode[] getArr() {
        return arr;
    }

    public boolean isWordComplete() {
        return isWordComplete;
    }

    public void setWordComplete(boolean wordComplete) {
        isWordComplete = wordComplete;
    }

    public TrieNode() {
        this.arr = new TrieNode[26];            //Optimized to use for 26 alphabets.
    }

}

