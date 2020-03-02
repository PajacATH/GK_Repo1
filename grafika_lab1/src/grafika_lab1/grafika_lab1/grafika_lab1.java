package grafika_lab1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Math.*;


public class grafika_lab1 extends JPanel {

	private class Display extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.translate(300,300);  // Moves (0,0) to the center of the display.
			int whichTransform = transformSelect.getSelectedIndex();
			
			switch(whichTransform)
			{
			case 0:
				break;
			case 1:
				g2.scale(3,3);
				break;
			case 2:
				g2.rotate(Math.toRadians(45));
				break;
			case 3:
				g2.rotate(Math.toRadians(180));
				g2.scale(0.5, 2);
				break;
			case 4:
				g2.shear(0.4,0.0);
				break;
			case 5:
				g2.scale(2, 1);
				g2.translate(0, -200);
				break;
			case 6:
				g2.shear(0.4, 0.0);
				g2.rotate(Math.toRadians(110));
			case 7:
				g2.rotate(Math.toRadians(180));
				g2.scale(1, 2);
				break;
			case 8:
				g2.shear(0.4, 0.0);
				g2.rotate(Math.toRadians(110));
			case 9:
				g2.shear(0.4,0.0);
				break;
			}
			

			// TODO Apply transforms here, depending on the value of whichTransform! 10.2
			g2.drawImage(pic, 0, 0, null); // Draw image with center at (0,0).
			
		    Polygon p = new Polygon();
		    for (int i = 0; i < 9; i++)
		    p.addPoint((int) (90 * Math.cos(i * 2 * Math.PI / 9)),
		    		   (int) (90 * Math.sin(i * 2 * Math.PI / 9)));
		    g2.setColor(Color.RED);
		    g.drawPolygon(p);
		    g2.fillPolygon(p);

		}
	}

	private Display display;
	private BufferedImage pic;
	private JComboBox<String> transformSelect;

	public grafika_lab1() throws IOException 
	{
		display = new Display();
		display.setBackground(Color.cyan);
		display.setPreferredSize(new Dimension(600,600));
		transformSelect = new JComboBox<String>();
		transformSelect.addItem("None");
		for (int i = 1; i < 10; i++) {
			transformSelect.addItem("No. " + i);
		}
		transformSelect.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.repaint();
			}
		});
		setLayout(new BorderLayout(3,3));
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		top.add(new JLabel("Transform: "));
		top.add(transformSelect);
		add(display,BorderLayout.CENTER);
		add(top,BorderLayout.NORTH);
	}

	

	public static void main(String[] args) throws IOException {
		JFrame window = new JFrame("2D Transforms");
		window.setContentPane(new grafika_lab1());
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation( (screen.width - window.getWidth())/2, (screen.height - window.getHeight())/2 );
		window.setVisible(true);
	}

}