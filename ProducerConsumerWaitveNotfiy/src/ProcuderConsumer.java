
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProcuderConsumer {
    Random random = new Random();
    Object lock = new Object();
    int limit = 10;
    Queue<Integer> queue = new LinkedList<Integer>();
    
    
    public void  procuder(){
        while (true) {            
                try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProcuderConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        synchronized(lock){
            if(queue.size()==limit){
                try {
                    lock.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProcuderConsumer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                int value = random.nextInt(100);
                queue.offer(value);
                System.out.println("Procuder :"+value);
                //lock.notify();
                }
            }
        }
        
    }
    
    public void consumer(){
        
        while (true) {
         try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProcuderConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        synchronized(lock){
            
            if(queue.size()==0){
                try {
                    lock.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProcuderConsumer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                int deger = queue.poll();
                System.out.println("Consumer :"+deger);
                System.out.println("Queue size:"+queue.size());
                //lock.notify();
                }
            }
            
        }
       
    }
    
}
