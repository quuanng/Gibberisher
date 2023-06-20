/*
* Author: Quang Pham
*/
public class Gibberisher{
    private Trie<CharBag> model;
    private int seglen;
    private int samCount = 0;
    private CharBag initialLetters;

    //creates a new Gibberisher that uses seglen for segment length and initializes the charbag for initial letters
    public Gibberisher(int seglen){
        this.seglen = seglen;
        this.model = new Trie();
        this.samCount = 0;
        this.initialLetters = new CharBag();
    }
    //adds each word's next letter after a segment to the trie to see what is the possible next letters for that segment
    public void train(String[] strs){
        
        for(int i = 0; i<strs.length; i++){
            LetterSample[] samples = LetterSample.toSamples(strs[i], seglen);
            samCount = samCount + samples.length;

            for(int j = 0; j<samples.length; j++){
                if(samples[j].getSegment() != ""){
                    if(model.get(samples[j].getSegment()) == null){
                        model.put(samples[j].getSegment(), new CharBag());
                    }
                    CharBag temp = model.get(samples[j].getSegment());
                    temp.add(samples[j].getNextLetter());
                    model.put(samples[j].getSegment(), temp);
                } else {
                    // add next letter as a child to the root
                    // data of child trie is charbag of the next letter after
                    String nextLetter = "" + samples[j].getNextLetter();
                    if(model.get(nextLetter) == null){
                        model.put(nextLetter, new CharBag());
                    }
                    initialLetters.add(samples[j].getNextLetter());
                }
            }
        }
    }

    //returns the number of samples used
    public int getSampleCount(){
        return this.samCount;
    }

// returns a generated gibberish word
    public String generate(){
        String word = "";
        word = word + initialLetters.getRandomChar();;
        String segment = word;

        while(!word.substring(word.length()-1).equals(".")){
            if (segment.length() <= seglen){
                word += model.get(segment).getRandomChar();
                segment = word;
            } else {
                String test = "no";
                segment = word.substring(word.length()-seglen);
                try {
                    CharBag segBag = model.get(segment);
                }
                catch (NullPointerException excpt) {
                    word = "" + initialLetters.getRandomChar();
                    segment = word;
                }
                char temp = model.get(segment).getRandomChar();
                for(char c = 'a'; c<='z'; c++){
                    if(temp == c){
                        test = "yes";
                    }
                }
                if(test == "no"){
                    word += '.';
                } else {
                    word += temp;
                }
                segment = word;
            }
        }
        return word.substring(0, word.length()-1);
    }
}