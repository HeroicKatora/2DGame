package control;

import java.util.EventObject;

import core.Game;

public class FFEvent extends EventObject {

    /**
     * 
     */
    private static final long serialVersionUID = "FFEvent".hashCode();

    public FFEvent(){
	this(Game.getGame());
    }

    public FFEvent(Object arg0) {
	super(arg0);
    }

    public Class<? extends FFListener> getListenerClass(){
	return FFListener.class;
    }

}
