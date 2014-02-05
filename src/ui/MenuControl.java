package ui;

import java.awt.Point;

class MenuControl extends Thread{
    private int aimX, aimY;
    private int speed;
    private boolean stopped;
    private long lastCheck;
    private FloatingMenu toMove;
    public MenuControl(FloatingMenu toMove){
	this.toMove = toMove;
	aimX = toMove.getLocation().x;
	aimY = toMove.getLocation().y;
	speed = 100;
	stopped = false;
	lastCheck = System.currentTimeMillis();
    }
    public void hold(){
	stopped = true;
    }
    @Override
    public void run() {
	//TODO Performance
	while(!stopped){
	    Point p = toMove.getLocation();
	    if (p.x != aimX||p.y != aimY) {
		double speedL = (double) speed / 1000
			* (System.currentTimeMillis() - lastCheck);
		Point mov;
		if (p.distance(new Point(aimX, aimY)) < speedL) {
		    mov = new Point(aimX - p.x, aimY - p.y);
		} else {
		    double length = Math.sqrt((p.x - aimX) * (p.x - aimX)
			    + (p.y - aimY) * (p.y - aimY));
		    mov = new Point((int) (speedL * (aimX - p.x) / length),
			    (int) (speedL * (aimY - p.y) / length));
		}
		toMove.setLocation(p.x + mov.x, p.y + mov.y);
	    }else{
		stopped = true;
	    }
	    lastCheck = System.currentTimeMillis();
	    if(toMove.getParent() != null)toMove.getParent().repaint();
	    toMove.repaint();
	    try{
		sleep(30);
	    }catch(Exception ex){
		System.out.println("Something went terribly wrong!");
	    }
	}
	toMove.removeControl();
    }
    public void set(int newX, int newY){
	aimX = newX;
	aimY = newY;
    }
    public void setSpeed(int pixelPerSecond){
	speed = pixelPerSecond;
    }
    public void setX(int newX){
	aimX = newX;
    }
    public void setY(int newY){
	aimY = newY;
    }
}