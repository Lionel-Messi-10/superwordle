import java.util.Scanner;

public class Wordle{
    private Word answer;
    private int guesses = 0;
    private int max = 6;
    private WordBank wordbank;
    private WordBank evil;
    private Scanner input;
    private boolean win = false;
    private Word attempt;
    private Letter h1;
    private Letter h2;
    private Letter h3;
    private Letter h4;
    private Letter h5;
    private String ans;
    
    public Wordle(WordBank a, Scanner b, WordBank c){
        wordbank = a;
        input = b;
        evil = c;
    }
    
    public boolean run(){
        win = false;
        guesses = 0;
        
        if(GameRunner.difficulty == 0){
            max = 6;
            ans = wordbank.getRandomWord();
        }else{
            max = 5;
            ans = evil.getRandomWord();
        }
        
        
        answer = new Word(ans);
        
        while(win == false && guesses < max){
            guesses+=1;
            ask();
            if (hint().equals("win")){
                win = true;
            }else{
                System.out.println(hint());
                System.out.println();
            }
        }
        return win;
    }
    
    private void ask(){
        System.out.println("Enter guess #" + guesses);
        Word bo = new Word(input.nextLine());
        if (wordbank.isValidWord(bo.toString())){
            attempt = bo;
        }else{
            System.out.println("Invalid Guess.");
            ask();
        }
    }
    
    private String hint(){
        if (attempt.getLetter(1).equals(answer.getLetter(1))){
            h1 = new Letter(answer.getLetter(1),1);
            answer.changeLetter(1,"#");
        }else{
            h1 = new Letter("?",1); 
        }
        if (attempt.getLetter(2).equals(answer.getLetter(2))){
            h2 = new Letter(answer.getLetter(2),2);
            answer.changeLetter(2,"#");
        }else{
            h2 = new Letter("?",2);
        }
        if (attempt.getLetter(3).equals(answer.getLetter(3))){
            h3 = new Letter(answer.getLetter(3),3);
            answer.changeLetter(3,"#");
        }else{
            h3 = new Letter("?",3);
        }
        if (attempt.getLetter(4).equals(answer.getLetter(4))){
            h4 = new Letter(answer.getLetter(4),4);
            answer.changeLetter(4,"#");
        }else{
            h4 = new Letter("?",4);
        }
        if (attempt.getLetter(5).equals(answer.getLetter(5))){
            h5 = new Letter(answer.getLetter(5),5);
            answer.changeLetter(5,"#");
        }else{
            h5 = new Letter("?",5);
        }
        Word hint = new Word(h1,h2,h3,h4,h5);
            
        if (h1.getVal().equals("?")){
            h1.changeVal("*");
            for (int i = 1; i < 6; i++){
                if (attempt.getLetter(1).equals(answer.getLetter(i))){
                    h1.changeVal("+");
                    answer.changeLetter(i,"#");
                    break;
                }
            }
        }
        
        if (h2.getVal().equals("?")){
            h2.changeVal("*");
            for (int i = 1; i < 6; i++){
                if (attempt.getLetter(2).equals(answer.getLetter(i))){
                    h2.changeVal("+");
                    answer.changeLetter(i,"#");
                    break;
                }
            }
        }
        
        if (h3.getVal().equals("?")){
            h3.changeVal("*");
            for (int i = 1; i < 6; i++){
                if (attempt.getLetter(3).equals(answer.getLetter(i))){
                    h3.changeVal("+");
                    answer.changeLetter(i,"#");
                    break;
                }
            }
        }
        
        if (h4.getVal().equals("?")){
            h4.changeVal("*");
            for (int i = 1; i < 6; i++){
                if (attempt.getLetter(4).equals(answer.getLetter(i))){
                    h4.changeVal("+");
                    answer.changeLetter(i,"#");
                    break;
                }
            }
        }
        
        if (h5.getVal().equals("?")){
            h5.changeVal("*");
            for (int i = 1; i < 6; i++){
                if (attempt.getLetter(5).equals(answer.getLetter(i))){
                    h5.changeVal("+");
                    answer.changeLetter(i,"#");
                    break;
                }
            }
        }
        
        if (hint.toString().equals(ans)){
            return "win";
        }else{
            answer = new Word(ans);
            return hint.toString();
        }
    }
    
    public int getNumGuesses(){
        return guesses;
    }
    
    public String getAnswer(){
        return ans;
    }
}