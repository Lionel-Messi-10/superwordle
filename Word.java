public class Word {
    private Letter l1;
    private Letter l2;
    private Letter l3;
    private Letter l4;
    private Letter l5;
    
    public Word(String a){
        this.l1 = new Letter(a.substring(0,1),1);
        this.l2 = new Letter(a.substring(1,2),2);
        this.l3 = new Letter(a.substring(2,3),3);
        this.l4 = new Letter(a.substring(3,4),4);
        this.l5 = new Letter(a.substring(4,5),5);
    }
    
    public void changeWord(String a){
        this.l1 = new Letter(a.substring(0,1),1);
        this.l2 = new Letter(a.substring(1,2),2);
        this.l3 = new Letter(a.substring(2,3),3);
        this.l4 = new Letter(a.substring(3,4),4);
        this.l5 = new Letter(a.substring(4,5),5);
    }
    
    public Word(Letter a, Letter b, Letter c, Letter d, Letter e){
        l1 = a;
        l2 = b;
        l3 = c;
        l4 = d;
        l5 = e;
    }
    
    public int getIndex(int c){
        if (c == 1){
            return l1.getPos();
        }else if(c == 2){
            return l2.getPos();
        }else if(c == 3){
            return l3.getPos();
        }else if(c == 4){
            return l4.getPos();
        }else{
            return l5.getPos();
        }
    }
    
    public String getLetter(int c){
        if (c == 1){
            return l1.getVal();
        }else if(c == 2){
            return l2.getVal();
        }else if(c == 3){
            return l3.getVal();
        }else if(c == 4){
            return l4.getVal();
        }else{
            return l5.getVal();
        }
    }
    
    public void changeLetter(int d, String e){
        if (d == 1){
            l1.changeVal(e);
        }else if(d == 2){
            l2.changeVal(e);
        }else if(d == 3){
            l3.changeVal(e);
        }else if(d == 4){
            l4.changeVal(e);
        }else{
            l5.changeVal(e);
        }
    }
    
    public String toString(){
        return (l1.getVal()+l2.getVal()+l3.getVal()+l4.getVal()+l5.getVal());
    }
}