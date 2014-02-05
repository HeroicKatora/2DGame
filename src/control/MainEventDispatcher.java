package control;

import java.util.ArrayList;

public class MainEventDispatcher {
    private static ArrayList<FFListener> listeners = new ArrayList<FFListener>();


    public static void deregister(FFListener l){
	listeners.remove(l);
    }

    /**
     * Informs all Listeners to the event type about the performed event.
     * What Listeners are notified is handled by the FFEvent ffe by its method getListenerClass.
     * @param ffe
     */
    public static void dispatch(FFEvent ffe){
	for(FFListener l :listeners){
	    if(ffe.getListenerClass().isAssignableFrom(l.getClass())){
		l.eventPerformed(ffe);
	    }
	}
    }

    public static void register(FFListener l){
	listeners.add(l);
    }
}
