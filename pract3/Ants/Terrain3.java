import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Terrain3 implements Terrain {
    //Gestión de interbloqueos
    
    //con una variable condition por celda del territorio. Incluye un 
    //mecanismo para resolver el problema de los interbloqueos
    
    Viewer v;
    private ReentrantLock lock;
    private Condition[][] cond;
    
    public  Terrain3 (int t, int ants, int movs) {
        v=new Viewer(t,ants,movs,"1.- monitor general");
        for (int i=0; i<ants; i++) new Ant(i,this,movs).start();
        lock = new ReentrantLock();
        cond = new Condition[t][t];
        for (int j = 0; j < t; j++){
            for (int z = 0; z < t; z++){
                cond[j][z] = lock.newCondition();
            }
        }
    }
    
    public void hi (int a) {
        v.hi(a);
    }
    
    public void bye (int a) {
        try{
            lock.lock();
            Pos act = v.getPos(a);
            v.bye(a);
            cond[act.x][act.y].signal();
        } finally {
            lock.unlock();
        }
    }
    
    public void move (int a) throws InterruptedException {
        lock.lock();
        try{
           v.turn(a);
           Pos dest = v.dest(a); 
           Pos act = v.getPos(a);
           boolean libre = false;
           try{
               while (v.occupied(dest)) {
                   libre = cond[dest.x][dest.y].await(300, TimeUnit.MILLISECONDS);
                   if (libre == false){
                       v.chgDir(a);
                       dest = v.dest(a);
                   }
                   v.retry(a);
                } 
               v.go(a);
               cond[act.x][act.y].signal();
           }catch (InterruptedException e) {}
        } finally {
            lock.unlock();
        }
    }
}
