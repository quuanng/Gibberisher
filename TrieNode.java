/*
* Author: Quang Pham
*/
public class TrieNode<T>{
    private T data;
    private TrieNode[] children;

    //creates a new TrieNode object
    public TrieNode(){
        this.data = null;
        this.children = new TrieNode[26];
    }

    //returns the data of the treenode
    public T getData(){
        return this.data;
    }
    //changes the data of the treenode
    public void setData(T data){
        this.data = data;
    }

    //returns the child of the trienode at the indexed letter
    public TrieNode<T> getChild(char letter){
        int tempCounter = 0;
        for(char c = 'a'; c<='z'; c++){
            if(letter == c){
                tempCounter++;
            }
        }

        if(tempCounter == 0){
            return null;
        }
        if(children[(int)(letter) - 97] == null){
            children[(int)(letter) - 97] = new TrieNode();
            return children[(int)(letter) - 97];
        }
        return children[(int)(letter) - 97];
    }

    //returns the size of the tree
    public int getTreeSize(){
        int count = 0;
        for(int i = 0; i<children.length; i++){
            if(children[i] != null){
                count += children[i].getTreeSize();
            }
        }

        return count + 1;
    }
}