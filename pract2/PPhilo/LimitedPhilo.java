
public class LimitedPhilo extends Philo {
    public LimitedPhilo(int id, int cycles, int delay, Table table) {super(id, cycles, delay, table);}
    
    /* COMPLETAR */
    public void run() {
        try {
            table.begin(id);
            for (int i=0; i<cycles; i++) {
                table.enter(id);//avisa por si puede entrar
                table.takeR(id); delay(); table.takeL(id);
                table.eat(id); randomDelay();
                table.dropR(id); table.dropL(id);
                table.exit(id);//avisa de que va a salir
                table.ponder(id); randomDelay();//piensa              
            }
            table.end(id);
        } 
        catch (InterruptedException ex) {}
    }
}