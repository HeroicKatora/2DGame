package core;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

import ui.DisplayedObject;

public abstract class GameCreature implements DisplayedObject, Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = "GameCreature".hashCode();

    protected Point position = new Point(0,0);
    protected int rotation = 0;
    protected double maxHP = 100;
    protected double damageTaken = 0;
    protected double hPPerSecond = 1;
    protected GameMap map = null;

    @Override
    public Point getPosition() {
	// TODO Auto-generated method stub
	return position;
    }

    @Override
    public int getRotation() {
	// TODO Auto-generated method stub
	return rotation;
    }
    public void onAddCreature(GameMap map){
	this.map = map;
    }
    public abstract void onDeath(GameMap map);
    public void onRemoveCreature(GameMap map){
	this.map = null;
    }
    @Override
    public void paint(Graphics g){
	//draw the base for every creature
    }

}
