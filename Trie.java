/*
* Author: Quang Pham
*/
public class Trie<T>{
    private TrieNode<T> root;
    // creates a new trie
    public Trie(){
        this.root = new TrieNode();
    }

    // returns the node from the string inputted
    private TrieNode<T> getNode(String str){
        TrieNode<T> temp = root.getChild(str.charAt(0));
        for(int i = 1; i<str.length(); i++){
            temp = temp.getChild(str.charAt(i));
        }
        return temp;
    }

    // returns the data of the TrieNode in Trie from the inputted string
    public T get(String str){
        return getNode(str).getData();
    }

    // changes the child of the inputted string's trienode to d
    public void put(String str, T d){
        getNode(str).setData(d);
    }

    // returns the root of the trie
    public TrieNode<T> getRoot(){
        return this.root;
    }

}