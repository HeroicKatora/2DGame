package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ui.MainFrame;
import control.FFControlListener;
import control.FFEvent;
import control.FFRepaintEvent;
import control.MainEventDispatcher;

public class Game implements Runnable, FFControlListener {

    private static Game game;

    public static Game getGame() {
	if (game == null) {
	    game = new Game();
	}
	return game;
    }

    public static boolean hasGame() {
	return !(game == null);
    }

    public static void main(String[] args) {
	Game.runGame();
	new MainFrame();
    }

    public static void runGame() {
	Game g = getGame();
	if (g.stopped == true) {
	    Thread t = new Thread(g);
	    t.start();
	}
    }

    private List<GameMap> activeMaps;
    private List<GameTickable> tickables;
    private HashMap<GamePlayer, GameMap> playerMaps;
    private GamePlayer ourPlayer;
    private volatile boolean stopped;

    private long lastTick;

    private Game() {
	activeMaps = new ArrayList<GameMap>();
	tickables = new ArrayList<GameTickable>();
	playerMaps = new HashMap<GamePlayer, GameMap>();
	ourPlayer = new GamePlayer("This is a stub");
	stopped = true;
	lastTick = 0;
	GameMap mainMap = new GameMap();
	mainMap.addCreatue(ourPlayer);
	putPlayerMap(ourPlayer, mainMap);
	registerMapAsActive(mainMap);
	MainEventDispatcher.register(this);
    }

    public boolean deregisterMapAsActive(GameMap map) {
	return activeMaps.remove(map);
    }

    public boolean deregisterTickable(GameTickable t) {
	return tickables.remove(t);
    }

    @Override
    public void eventPerformed(FFEvent ffe) {
	// TODO Auto-generated method stub

    }

    public GameMap getCorrespondingMap(GamePlayer p) {
	return playerMaps.get(p);
    }

    public GamePlayer getOurPlayer() {
	return ourPlayer;
    }

    public boolean isStopped() {
	return stopped;
    }

    public void putPlayerMap(GamePlayer p, GameMap m) {
	playerMaps.put(p, m);
    }

    public void registerMapAsActive(GameMap map) {
	if (!activeMaps.contains(map))
	    activeMaps.add(map);
    }

    public void registerTickable(GameTickable t) {
	if (!tickables.contains(t))
	    tickables.add(t);
    }

    @Override
    public void run() {
	stopped = false;
	lastTick = System.currentTimeMillis();
	while (!stopped) {
	    for (GameMap map : activeMaps) {
		map.tick();
	    }
	    MainEventDispatcher.dispatch(new FFRepaintEvent());
	    try {
		long timeToSleep = Math.max(
			33 - (System.currentTimeMillis() - lastTick), 0);
		lastTick = System.currentTimeMillis();
		Thread.sleep(timeToSleep);
	    } catch (InterruptedException e) {
		System.out.println("The main loop was interrupted!");
	    }
	}
    }

    public void stop() {
	stopped = true;
    }
}
