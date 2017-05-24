class Trie {
    private TrieNode root;                                                     //Root node for the data structure.

    public Trie() {
        root = new TrieNode();
    }                                   //Constructor.

    //Function to insert a word into Trie.
    public void insert(String word) {                                          //
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (p.arr[index] == null) {
                TrieNode temp = new TrieNode();
                temp.setCh(word.charAt(i));
                p.arr[index] = temp;
                p = temp;
            } else p = p.arr[index];
        }
        p.setWordComplete(true);                                               //Set word as completed.
    }

    //Function to returnNode from Trie.
    public TrieNode returnNode(String prefix) {
        TrieNode p = searchNode(prefix);
        if (p == null)
            return null;
        else
            return p;
    }

    //Function to search a node in Trie.
    public TrieNode searchNode(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            if (p.arr[index] != null)
                p = p.arr[index];
            else
                return null;
        }
        if (p == root)
            return null;
        return p;
    }

    //Recursive Function to print words starting with given prefix.
    public void printPrefixWords(TrieNode trieNode, String temp) {
        if (trieNode != null) {
            if (trieNode.isWordComplete())
                System.out.println(temp);
            for (TrieNode n : trieNode.getArr())
                if (n != null)
                    printPrefixWords(n, temp + n.getCh());
        }
    }

    //(Unused)Function to print created Trie Structure.
    public void printTrie(TrieNode node, int space) {
        if (node != null) {
            for (int i = 0; i < space; i++)
                System.out.print(" ");
            System.out.print(node.getCh() + "\n");
            for (TrieNode n : node.getArr())
                printTrie(n, space + 2);
        }
    }
}


