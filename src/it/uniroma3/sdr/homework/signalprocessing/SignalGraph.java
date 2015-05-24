package it.uniroma3.sdr.homework.signalprocessing;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import it.uniroma3.sdr.homework.model.*;


public class SignalGraph extends JPanel{
	private static final long serialVersionUID = 1L;
	private Signal segnale;
    final int PAD = 20;
	public SignalGraph(Signal segnale){
		this.segnale=segnale;
		this.setSize(800,800);
	}

	public void paintGraph(Graphics g){
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
    	// Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, 800-PAD));
        // Draw abcissa.
        g2.draw(new Line2D.Double(PAD, 800-PAD, 800-PAD, 800-PAD));     
        for (int i = 0; i < 500; i++){
            int x = (int)segnale.getReale()[i];
            int y = (int)segnale.getImmaginaria()[i];		
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
	}
}
