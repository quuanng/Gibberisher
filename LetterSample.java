/*
* Author: Quang Pham
*/
public class LetterSample{
    private String segment;
    private char next;

    //creates a letter sample
    public LetterSample(String str, char c){
        this.segment = str;
        this.next = c;
    }

    //gets the segment
    public String getSegment(){
        return segment;
    }

    //gets the next letter for the segment
    public char getNextLetter(){
        return next;
    }

    // for printing testing purpose
    public String toString(){
        return "\"" + segment + "\" -> " + next;
    }
    // creates lettersamples for the whole string with the inputted segment size and stops at '.'
    public static final char STOP = '.';
    public static LetterSample[] toSamples(String input, int segmentSize){
        String str = input;
        String temp = "";
        str = str + '.';

        LetterSample[] letSeg = new LetterSample[str.length()];

        for(int i = 0; i<str.length(); i++){
            letSeg[i] = new LetterSample(temp, str.charAt(i));
            if(segmentSize == 0){
                temp = "";
            } else if (temp.length() == segmentSize){
                temp = temp.substring(1, segmentSize) + str.charAt(i);
            } else {
                temp += str.charAt(i);
            }
        }

        return letSeg;
    }
}