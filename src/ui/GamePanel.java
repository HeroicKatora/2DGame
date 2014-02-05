package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import control.FFEvent;
import control.FFRepaintListener;
import control.MainEventDispatcher;
import core.Game;
import core.GameMap;

public class GamePanel extends JPanel implements FFRepaintListener {
	private FloatingMenu pauseMenu;
	private Game game;

	public GamePanel() {
		MainEventDispatcher.register(this);
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					pauseMenu.toggle();
					if (getTopLevelAncestor() instanceof MainFrame) {
						// TODO maybe do sth else
						((MainFrame) getTopLevelAncestor()).pauseButton();
					} else {
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				requestFocus();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		setLayout(null);
		pauseMenu = new FloatingMenu(0, 10, 200, 200, 0, -200) {
			@Override
			public void paint(Graphics g) {
				setBackground(Color.white);
				super.paint(g);
			}
		};
		pauseMenu.setColor(Color.white);
		pauseMenu.setCloseOnMouseOver(true);
		add(pauseMenu);
		game = Game.getGame();
	}

	@Override
	public void eventPerformed(FFEvent ffe) {
		game = (Game) ffe.getSource();
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		GameMap map = game.getCorrespondingMap(game.getOurPlayer());
		if (map == null) {
			return;
		}
		for (DisplayedObject o : map.getDisplayedObjects()) {
			o.paint(g);
		}
	}

}
