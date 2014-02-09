package ui;

import javax.swing.JPanel;

public abstract class GameMenu extends JPanel{

    public enum Direction{UP, RIGHT, DOWN, LEFT}
    private static final long serialVersionUID = "GameMenu".hashCode();;
    protected int selected;
    protected boolean active;

    public abstract void activate();
    public abstract void deactivate();
    public int getNext(Direction direction){
	return getNext(selected, direction);
    }
    public int getNext(int selected){
	return getNext(selected, Direction.RIGHT);
    }
    public abstract int getNext(int selected, Direction direction);
    public void toggle(){
	if(active)
	    deactivate();
	else
	    activate();
    }


}
