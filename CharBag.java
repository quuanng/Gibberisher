/*
* Author: Quang Pham
*/
import java.util.Random;
public class CharBag{
    private String[] letters;
    private char letter;
    private int adds = 0;
    private int removes = 0;

    //creates a new CharBag
    public CharBag(){
        this.letters = new String[27]; // used String to combine char value, can use substring, Integer.parseInt, and Integer.toString to manipulate
        int temp = 0;
        String str = "";
        for(char c = 'a'; c <= 'z'; c++){
            letters[temp] = str + c + " 0";
            temp++;
        }
        letters[26] = '.' + " 0";
    }

    // adds a char to the charbag
    public void add(char c){
        //check if it is a valid character
        String isValid = "no";
        char cha = Character.toLowerCase(c);
        if(cha == '.'){
            isValid = "yes";
        }
        for(char ch = 'a'; ch<= 'z'; ch++){
            if(cha == ch){
                isValid = "yes";
            }
        }
        // parse int the number from the string, increment it, then string it back on to the letters array
        if(isValid == "yes"){
            for(int i = 0; i<27; i++){
                if(letters[i].charAt(0) == cha){
                    int tempCount = Integer.parseInt(letters[i].substring(2)) + 1;
                    letters[i] = letters[i].substring(0, 2) + Integer.toString(tempCount);
                    adds++;
                }
            }
        } else { // increments the period number in the array if not a valid letter
            int tempCount = Integer.parseInt(letters[26].substring(2)) + 1;
            letters[26] = letters[26].substring(0, 2) + Integer.toString(tempCount);
            adds++;

        }
    }
    // removes a char from the CharBag
    public void remove(char c){
        char cha = Character.toLowerCase(c);
        int temp = 0;
        // checks to see if there exists any of the char c inside the charbag to be removed
        for(int i = 0; i<27; i++){
            if(letters[i].charAt(0) == cha){
                if(Integer.parseInt(letters[i].substring(2)) > 0){
                    int tempCount = Integer.parseInt(letters[i].substring(2)) - 1;
                    letters[i] = letters[i].substring(0, 2) + Integer.toString(tempCount);
                    removes++;
                }
            } else{
                temp++;
            }
        } //will try to remove a period if inputted char isn't a valid char or period
        if(temp == 27){
            if(Integer.parseInt(letters[26].substring(2)) > 0){
                    int tempCount = Integer.parseInt(letters[26].substring(2)) - 1;
                    letters[26] = letters[26].substring(0, 2) + Integer.toString(tempCount);
                    removes++;
                }
        }
    }
    // gets the number of char c's in the charbag
    public int getCount(char c){
        int temp = 0;
        for(int i = 0; i<27; i++){
            if(letters[i].charAt(0) == c){
                return Integer.parseInt(letters[i].substring(2));
            } else {
                temp++;
            }
        }
        if(temp == 27){
            return Integer.parseInt(letters[26].substring(2));
        }
        return 0;
    }
    //number of chars in the CharBag
    public int getSize(){
        return adds - removes;
    }
    // prints out the char bag
    public String toString(){
        int temp = 0;
        String str = "CharBag{";
        for(char b = 'a'; b<='z'; b++){
            str += letters[temp].substring(0, 1) + ":" + letters[temp].substring(2) + ", ";
            temp++;
        }
        str += letters[26].substring(0, 1) + ":" + letters[26].substring(2) + "}";
        return str;
    }
    //gets a random number between 1 and the size of the array then subtracts from each of the letters counts until it isn't greater than the count of a letter returning that letter
    public char getRandomChar(){
        if(getSize() == 0){
            return '.';
        }
        Random rand = new Random();
        int randNum = rand.nextInt(getSize()) + 1;
        char temp = 'a';
        while(randNum > getCount(temp)){
            randNum = randNum - getCount(temp);
            temp++;
        }
        return temp;
    }
}