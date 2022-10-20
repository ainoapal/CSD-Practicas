
public class LefthandedPhilo extends Philo { //ASSIMETRY
    public LefthandedPhilo(int id, int cycles, int delay, Table table) {super(id,cycles,delay,table);}
    
    /* COMPLETAR */     //  <-- Puesto por mi
    /* clase LefthandedPhilo que corresponde a un filósofo que 
     * debe coger los tenedores en orden contrario al de Philo, 
     * pero mantiene el orden en que se liberan.
     * Código de Philo:
     * public void run() {
        try {
            table.begin(id);
            for (int i=0; i<cycles; i++) {
                table.takeR(id); delay(); table.takeL(id);
                table.eat(id); randomDelay();
                table.dropR(id); table.dropL(id);
                table.ponder(id); randomDelay();
            }
            table.end(id);
        } 
        catch (InterruptedException ex) {}
    }
     */
    public void run() {
        try {
            table.begin(id);
            for (int i=0; i<cycles; i++) {
                table.takeL(id); delay(); table.takeR(id);
                table.eat(id); randomDelay();
                table.dropR(id); table.dropL(id);
                table.ponder(id); randomDelay();
            }
            table.end(id);
        } 
        catch (InterruptedException ex) {}
    }
     
}