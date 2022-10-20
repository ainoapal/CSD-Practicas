
public class BothOrNoneTable extends RegularTable { //both or none
    public BothOrNoneTable(StateManager state) {super(state);}
    
    /* COMPLETAR */   
    
    public synchronized void takeLR(int id) throws InterruptedException{
        while (!state.leftFree(id) || !state.rightFree(id)) {
            state.wtakeLR(id); wait();
        }
        state.takeLR(id);
    }
}
