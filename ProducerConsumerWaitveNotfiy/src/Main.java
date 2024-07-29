
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    public static void main(String[] args) {
        ProcuderConsumer pc = new ProcuderConsumer();
        
        
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                pc.procuder();
            }
        });
        
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                pc.consumer();
            }
        });
        
        th1.start();
        th2.start();
        
        try {
            th1.join();
            th2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
