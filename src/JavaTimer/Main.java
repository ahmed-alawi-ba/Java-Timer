
package JavaTimer;


public class Main {
    
    public static void main(String[] args)        {
//        new Thread(new Test()).start();
        
        new Main().run();
    }
    
    
    
    
    
    public void run(){
        Thread t1 = new Thread(new TimerGUI());
        
        t1.start();
        
    }
}
