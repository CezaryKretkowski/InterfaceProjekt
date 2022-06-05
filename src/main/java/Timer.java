import java.awt.*;

public class Timer extends Thread{
    private RenderComponets r=null;
    private boolean start=false;
    private int counter=10*60;
    public synchronized void startTimer(){
        start=true;
    }
    public synchronized void stopTimer(){
        start=false;
    }
    public synchronized int getCount(){
        return counter;
    }
    public Timer(int counter){
        this.start();
    }
    public Timer(){
        counter=10*60;

        this.start();
    }
    public Timer(RenderComponets r){
        counter=10*60;
        this.r=r;
        this.start();
    }

    public void setCounter(int counter) {
        this.counter = counter*60;
    }

    @Override
    public void run(){
        while(true)
        {
            try {
                Thread.sleep(1000);
                if(counter>0&&start){
                    counter--;
                if(r!=null)
                    r.repaint();
                }else if(counter==0)
                    break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
