package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FloatingMenu extends GameMenu {

    private static final long serialVersionUID = "FloatingMenu".hashCode();
    MenuControl control = new MenuControl(this);
    protected int shownX, shownY;
    protected int hiddenX, hiddenY;
    protected boolean mouseOver;
    private Color color;
    private boolean closeLeave = true;
    private boolean closeMouseOver = true;

    public FloatingMenu() {
	this(0, 0, 200, 100);
    }

    public FloatingMenu(int x, int y, int width, int height) {
	this(x, y, width, height, -width, y);
    }

    public FloatingMenu(int x, int y, int width, int height, int hX, int hY) {
	setSize(width, height);
	setLocation(hX, hY);
	shownX = x;
	shownY = y;
	hiddenX = hX;
	hiddenY = hY;
	deactivatePR();
	color = new Color(255, 255, 255);
	control.setSpeed(width * 2);
	control.start();
	mouseOver = false;
	addMouseListener(new MouseListener() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {
		takeAction(e);
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
		takeAction(e);
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {
	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {
	    }
	});
    }

    /**
     * Can be called by external sources to activate the Menu. May or may not
     * check some conditions in the future.
     */
    @Override
    public void activate() {
	activatePR();
    }

    protected void activatePR() {
	if (control == null) {
	    control = constructControl(shownX, shownY);
	    control.start();
	}
	control.setX(shownX);
	control.setY(shownY);
	active = true;
    }

    private MenuControl constructControl(int aimX, int aimY) {
	MenuControl ret = new MenuControl(this);
	ret.set(aimX, aimY);
	ret.setSpeed(getWidth() * 2);
	return ret;
    }

    /**
     * The front-end of deactivate. Override this if you want to check
     * conditions before just deactivating the menu.
     */

    @Override
    public void deactivate() {
	if (!(hasFocus() || (mouseOver && !closeMouseOver)))
	    deactivatePR();
    }

    protected void deactivatePR() {
	if (control == null) {
	    control = constructControl(hiddenX, hiddenY);
	    control.start();
	}
	control.setX(hiddenX);
	control.setY(hiddenY);
	active = false;
    }

    @Override
    public int getNext(int selected, Direction direction) {
	// TODO Auto-generated method stub
	return 0;
    }

    public boolean getState() {
	return active;
    }

    @Override
    public void paint(Graphics g) {
	setBackground(color);
	super.paint(g);
    }

    public void removeControl() {
	control.hold();
	control = null;
    }

    public void setCloseOnLeave(boolean close) {
	this.closeLeave = close;
    }

    public void setCloseOnMouseOver(boolean close) {
	this.closeMouseOver = close;
    }

    public void setColor(Color c) {
	this.color = c;
    }

    private void takeAction(MouseEvent e) {
	System.out.println(e.getX()+" "+e.getY());
	if (e.getX() > 0 && e.getX() < getWidth() && e.getY() > 0
		&& e.getY() < getHeight()) {
	    mouseOver = true;
	    activatePR();
	} else {
	    mouseOver = false;
	    if (closeLeave)
		deactivatePR();
	}
    }

    @Override
    public void toggle() {
	if (active)
	    deactivate();
	else
	    activate();
    }
}
