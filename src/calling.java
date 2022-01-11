import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class calling extends JFrame implements MouseListener{
	
	JButton button = new JButton("New Problem");

	calling(){
		this.setLocation(800, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setTitle("New Problem");
		this.getContentPane().setBackground(new Color(0x1f253d));
		
		button.setBackground(new Color(0x394165));
		button.setForeground(Color.white);
		button.setFont(new Font("Arial", Font.PLAIN, 30));
		button.setFocusable(false);
		button.setBorder(null);
		button.addMouseListener(this);
		
		this.add(button);
		this.setVisible(true);
	}
	static void main(String[] args) {
		new calling();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button) {
			new CurrentPracticePage();
			this.dispose();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button) {
			button.setBackground(Color.blue);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button) {
			button.setBackground(new Color(0x1f253d));
		}
	}
}
