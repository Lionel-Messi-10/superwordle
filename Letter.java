public class Letter {
    private String value = "a";
    private int index = 0;
    
    public Letter(String a, int b){
        value = a;
        index = b;
    }
    
    public int getPos(){
        return index;
    }
    
    public String getVal(){
        return value;
    }
    
    public void changeVal(String c){
        value = c;
    }
}