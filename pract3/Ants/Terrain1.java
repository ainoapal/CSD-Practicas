import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;

public class Terrain1 implements Terrain {
    //Sincronización con ReentrantLocks en Java 
    
    //con una única variable condition para todo el territorio
    
    Viewer v;
    private ReentrantLock lock;
    private Condition cond;

    public  Terrain1 (int t, int ants, int movs) {
        v=new Viewer(t,ants,movs,"1.- monitor general");
        for (int i=0; i<ants; i++) new Ant(i,this,movs).start();
        lock = new ReentrantLock();
        cond = lock.newCondition();
    }
    
    public void hi (int a) {
        v.hi(a);
    }
    
    public void bye (int a) {
        try{
            lock.lock();
            v.bye(a); //esto si
            cond.signalAll();
        } finally {
            lock.unlock();
        }
    }
    
    public void move (int a) throws InterruptedException {
        lock.lock();
        try{
           v.turn(a); //esto si
           Pos dest = v.dest(a); 
           try{
               while (v.occupied(dest)) { //esto si
                   cond.await();
                   v.retry(a);
                } 
                v.go(a); //esto si
                cond.signalAll();
           }catch (InterruptedException e) {}
        } finally {
            lock.unlock();
        }
    }
}