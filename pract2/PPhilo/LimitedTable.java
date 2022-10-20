
public class LimitedTable extends RegularTable { //max 4 in dinning-room
    public LimitedTable(StateManager state) {super(state);}
    
    
    /* COMPLETAR */
    
    int cont = 0;
    
    public synchronized void exit(int id){
        state.exit(id);
        cont--;
        notifyAll();
    }
    
    public synchronized void enter(int id)throws InterruptedException{
        while(cont==4){
            state.wenter(id);
            wait();
        }
        state.enter(id);
        cont++;
    }
}
