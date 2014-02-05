package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

public interface DisplayedObject {
    public Image getImage();
    public void paint(Graphics g);
    public Point getPosition();
    public int getRotation();
}
