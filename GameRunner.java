import java.io.IOException;
import java.util.Scanner;

public class GameRunner {
    
    public static boolean playing = true;
    public static int difficulty = 0;
    
    public static String intro(Scanner input) {
        System.out.println("Available commands:");
        System.out.println("   'new' to start a new Wordle game.");
        System.out.println("   'stats' to display cumulative game statistics.");
        System.out.println("   'settings' to open settings.");
        System.out.println("   'quit' to exit the program.");
        return input.nextLine();
    }
    
    public static String settings(Scanner input) {
        System.out.println("Available commands:");
        System.out.println("   'normal' to enable normal mode.");
        System.out.println("   'evil' to enable evil mode (full wordbank, 5 guesses).");
        System.out.println("   'reset' to reset cumulative game statistics.");
        System.out.println("   'back' to go back.");
        return input.nextLine();
    }
    
    public static int ranking(int n, int a, int b, int c){
        double wr = b * 1.0 / a;
        double ag = c * 1.0 / a;
        
        //these are just made up formulas that look like they somewhat work lol
        // just like in old indie games!
        
        if (n == 1){
            return (int)(Math.pow((1 + Math.abs(1 - wr)),30) * (Math.pow((ag * 2.3 - 1.3),4)));
        }else{
            return (int)(Math.pow((1 + Math.abs(1 - wr)),15) * (Math.pow((ag * 2.3 - 1.3),3)));
        }
    }
    

    public static void main(String[] args) throws IOException {
        
        System.out.println("Welcome to SuperWordle by Arkar.");
        System.out.println("This Wordle game allows for duplicate letters, advanced statistics, and multiple difficulties.");
        System.out.println("Enjoy!\n");
        
        int totalNormalGames = 0;
        int totalNormalWins = 0;
        int totalNormalGuesses = 0;
        
        int totalEvilGames = 0;
        int totalEvilWins = 0;
        int totalEvilGuesses = 0;
        
        // Loads in the wordBank. Useful methods are getRandomWord() and  isValidWord()
        // See WordBank.java for method descriptions
        WordBank wordBank = new WordBank(true,0); 
        WordBank evil = new WordBank(true,1); 
        // Create a Scanner
        Scanner input = new Scanner(System.in);
        
        // starts a Wordle game here
        Wordle wordle = new Wordle(wordBank, input, evil);
        boolean success = false;
        
        while (playing==true) {
            String a = intro(input);
            
            System.out.println("----------------------------------------------------------");
            
            if (a.equals("new")){
                success = wordle.run();
                
                if (success) {
                    System.out.println("Congrats! You solved this Wordle in "
                        +wordle.getNumGuesses()+ " attempts!");
                    if(GameRunner.difficulty == 0){
                        totalNormalGames ++;
                        totalNormalWins ++;
                        totalNormalGuesses += wordle.getNumGuesses();
                    }else{
                        totalEvilGames ++;
                        totalEvilWins ++;
                        totalEvilGuesses += wordle.getNumGuesses();
                    }
                } else {
                    System.out.println("The word was " + wordle.getAnswer());
                    System.out.println("Better luck next game!");
                    if(GameRunner.difficulty == 0){
                        totalNormalGames ++;
                        totalNormalGuesses += wordle.getNumGuesses();
                    }else{
                        totalEvilGames ++;
                        totalEvilGuesses += wordle.getNumGuesses();
                    }
                }
                
            }else if(a.equals("stats")){
                
                System.out.println("Normal Mode:");
                System.out.println(totalNormalGames + " games");
                System.out.println(totalNormalWins + " wins");
                System.out.println((totalNormalWins * 100.0 / totalNormalGames) + "% winrate");
                System.out.println((totalNormalGuesses * 1.0 / totalNormalGames) + " guesses per game average");
                System.out.println(((totalNormalGuesses * 1.0 - 6 * (totalNormalGames - totalNormalWins))/ totalNormalWins) + " guesses per win average");
                
                System.out.println();
                if (totalNormalGames < 5){
                    System.out.println("Play " + (5 - totalNormalGames) + " more games on Normal difficulty to earn a ranking.");
                }else{
                    System.out.println("Estimated NM Global Ranking: #" + ranking(1, totalNormalGames,totalNormalWins,totalNormalGuesses));
                }
                
                System.out.println();
                
                System.out.println("Evil Mode:");
                System.out.println(totalEvilGames + " games");
                System.out.println(totalEvilWins + " wins");
                System.out.println((totalEvilWins * 100.0 / totalEvilGames) + "% winrate");
                System.out.println((totalEvilGuesses * 1.0 / totalEvilGames) + " guesses per game average");
                System.out.println(((totalEvilGuesses * 1.0  - 5 * (totalEvilGames - totalEvilWins))/ totalEvilWins) + " guesses per win average");
                
                System.out.println();
                if (totalEvilGames < 5){
                    System.out.println("Play " + (5 - totalEvilGames) + " more games on Evil difficulty to earn a ranking.");
                }else{
                    System.out.println("Estimated EM Global Ranking: #" + ranking(2, totalEvilGames,totalEvilWins,totalEvilGuesses));
                }
                
                
            }else if(a.equals("settings")){
                String b = settings(input);
                
                if(b.equals("normal")){
                    difficulty = 0;
                    System.out.println("Normal mode activated.");
                    
                }else if(b.equals("evil")){
                    difficulty = 1;
                    System.out.println("Evil mode activated.");
                    
                }else if(b.equals("reset")){
                    totalNormalGames = 0;
                    totalNormalWins = 0;
                    totalNormalGuesses = 0;
                    
                    totalEvilGames = 0;
                    totalEvilWins = 0;
                    totalEvilGuesses = 0;
                    
                    System.out.println("Stats have been reset.");
                }else if(b.equals("back")){
                    //this doesnt need to do anything
                }else{
                    System.out.println("That is not an option");
                    settings(input);
                }
                
            }else if(a.equals("quit")){
                System.out.println("Thank you for playing!");
                playing = false;
            }else{
                System.out.println("That is not an option");
            }
            
            System.out.println("----------------------------------------------------------");
        }
    }
}