package ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = "MainFrame".hashCode();
    private FloatingMenu mainMenu;

    public MainFrame() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(null);
	setBackground(Color.green);

	setSize(800, 600);
	mainMenu = new FloatingMenu(0, 30, 100, 300, -90, 30) {
	    @Override
	    public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.drawRect(-1, 0, getWidth() - 1, getHeight() - 1);
	    }
	};
	mainMenu.setLayout(null);
	mainMenu.setOpaque(false);
	add(mainMenu);

	Button b = new Button("Toggle");
	b.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		mainMenu.toggle();
		Thread t = new Thread(new Runnable() {
		    @Override
		    public void run() {
			try {
			    Thread.sleep(3000);
			    mainMenu.deactivate();
			} catch (InterruptedException x) {
			}
		    }
		});
		t.start();
	    }
	});
	b.setSize(100, 50);
	b.setLocation(400, 200);
	add(b);

	GamePanel gp = new GamePanel();
	gp.setLocation(100, 30);
	gp.setSize(300, 300);
	add(gp);
	System.out.println(gp.isFocusable());
	gp.setFocusable(true);

	setVisible(true);
    }

    public void pauseButton() {
	mainMenu.toggle();
    }
}
