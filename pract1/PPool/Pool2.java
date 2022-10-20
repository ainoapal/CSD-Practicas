// CSD feb 2015 Juansa Sendra

public class Pool2 extends Pool{ //max kids/instructor
    private int ki = 0;
    private int cap = 0;
    private int kids = 0;
    private int instructors = 0;
    public void init(int ki, int cap)  {//variables para condiciones         
        this.ki = ki;
        this.cap = cap;
    }
    //ki es el numero de niños por instructor y cap es la capacidad maxima deotro de la piscina (5)
    public synchronized void kidSwims() throws InterruptedException{
        
        while( instructors < 1 || kids >= ki * instructors ) {
            log.waitingToSwim();
            wait();
        }
        // Actualiza estado
        kids++;
        log.swimming();
    }
    public synchronized void kidRests()  throws InterruptedException    {
        kids--;
        notifyAll();//para que los instructores sepan que no se vayan hasta que kids sea 0
        log.resting(); 
    }
    public synchronized void instructorSwims() throws InterruptedException  {
        instructors++;
        notifyAll();//para que los niños sepan que ya no pueden entrar
        log.swimming();
    }
    public synchronized void instructorRests()  throws InterruptedException {
        while((instructors <= 1 && kids > 0 ) || (kids > ki * (instructors-1)) ) { // Numero de niños tiene que ser igual o mayor que los papis*capacidad
            log.waitingToRest();   
            wait();
        }
        instructors--;
        log.resting(); 
    }
}
