package core;

public abstract class InterruptableCommand implements Runnable {
    protected transient boolean stopped = false;

    public abstract void afterRun();
    public abstract void inRun();
    public abstract void onStop();
    public abstract void preRun();
    @Override
    public final void run() {
	preRun();
	while(!stopped){
	    inRun();
	}
	afterRun();
    }
    public final void stop(){
	onStop();
	stopped = true;
	Thread.currentThread().interrupt();
    }

}
