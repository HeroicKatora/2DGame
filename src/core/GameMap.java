package core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ui.DisplayedObject;
import util.DescriptionImage;
import util.FileBuffer;

public class GameMap implements DisplayedObject, GameTickable{

    private HashMap<String, InterruptableCommand> spawns;
    private List<DisplayedObject> paintedObjects;
    private List<GameTickable> tickables;

    public GameMap(){
	spawns = new HashMap<String, InterruptableCommand>();
	paintedObjects = new ArrayList<DisplayedObject>();
	tickables = new ArrayList<GameTickable>();
	paintedObjects.add(this);
    }

    public boolean addCreatue(GameCreature c){
	paintedObjects.add(c);
	if(c instanceof GameTickable) tickables.add((GameTickable) c);
	c.onAddCreature(this);
	return true;
    }
    public boolean deregisterTickable(GameTickable t){
	return tickables.remove(t);
    }

    public List<DisplayedObject> getDisplayedObjects(){
	return paintedObjects;
    }

    @Override
    public Image getImage() {
	return (Image) FileBuffer.getBuffer("resources/maps/MapBase.jpg").getData(new DescriptionImage());
    }
    @Override
    public Point getPosition() {
	return new Point(0,0);
    }
    @Override
    public int getRotation() {
	return 0;
    }
    public boolean playerEnter(GamePlayer p){
	Game.getGame().putPlayerMap(p, this);
	addCreatue(p);
	return true;
    }
    public boolean playerLeave(GamePlayer p){
	return removeCreature(p);
    }

    public void registerTickable(GameTickable t){
	if(!tickables.contains(t)) tickables.add(t);
    }

    public boolean removeCreature(GameCreature c){
	c.onRemoveCreature(this);
	return paintedObjects.remove(c);
    }

    @Override
    public void tick() {
	for(GameTickable tick:tickables){
	    tick.tick();
	}
    }

	@Override
	public void paint(Graphics g) {
		g.drawImage(getImage(), 0, 0, null);
	}
}
