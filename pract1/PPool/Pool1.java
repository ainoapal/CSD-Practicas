
public class Pool1 extends Pool {   //no kids alone
    int instructors = 0, kids = 0;
    public void init(int ki, int cap)           {}
    public synchronized void kidSwims() throws InterruptedException{
        while(instructors == 0) {
            log.waitingToSwim();
            wait();
        }
        kids++;
        log.swimming();
    }
    public synchronized void kidRests(){
        kids--;
        notifyAll();
        log.resting(); 
    }
    public synchronized void instructorSwims(){
        instructors++;
        notifyAll();
        log.swimming();
    }
    public synchronized void instructorRests() throws 
    InterruptedException{
        while(instructors <= 1 && kids > 0) {
            log.waitingToRest();
            wait();
        }
        instructors--;
        log.resting(); 
    }
}
