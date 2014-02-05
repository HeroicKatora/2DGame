package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import util.DescriptionImage;
import util.FileBuffer;

public class GamePlayer extends GameCreature {

    private final String name;
    /*
     *  *
     */
    private static final long serialVersionUID = "GamePlayer".hashCode();
    public GamePlayer(String name){
	this.name = name;
    }

    @Override
    public Image getImage() {
	Image img = (Image) FileBuffer.getBuffer("resources/creatures/PlayerBase.png").getData(new DescriptionImage());
	/*if(img != null){
		    Graphics2D g = (Graphics2D) img.getGraphics();
		    g.setColor(Color.BLACK);
		    g.drawString(name, 10, 10);
		    //TODO realign character name
		}*/
	return img;

    }

    @Override
    public void onAddCreature(GameMap map) {
	super.onAddCreature(map);
	if(Game.hasGame()){
	    Game.getGame().putPlayerMap(this, map);
	    Game.getGame().registerMapAsActive(map);
	}
	System.out.println("A new player has been added");
    }

    @Override
    public void onDeath(GameMap map) {
	System.out.println("A player has died");
    }

    @Override
    public void onRemoveCreature(GameMap map) {
	super.onRemoveCreature(map);
	System.out.println("A player has been removed");
    }

    @Override
    public void paint(Graphics g) {
	super.paint(g);
	g.drawImage(getImage(), position.x, position.y, null);
	g.setColor(Color.red);
	g.drawString(name, 20, 20);
    }

}
