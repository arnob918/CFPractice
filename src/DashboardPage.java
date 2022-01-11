
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.*;

class pair {
	String hand;
	int pt;
	pair(String s, int p){
		hand = s;
		pt = p;
	}
}

public class DashboardPage extends JFrame implements MouseListener{
	
	JPanel headerpanel = new JPanel();

	JPanel panel = new JPanel();
	JButton profilebtn = new JButton("Profile");
	JButton practicebtn = new JButton("Practice");
	JLabel totalpoint = new JLabel();
	String handle;
	JTextArea area = new JTextArea();
	JLabel ranklist = new JLabel("Ranklist:");
	
	DashboardPage(){
		Vector<pair> all = new Vector();
		Vector<String> str = new Vector();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("Data/allhandle.txt"));
			String s;
			while((s = br.readLine()) != null) {
				str.add(s);
			}
			br.close();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for(String h: str) {
			if(h.isEmpty()) {
				break;
			}
			System.out.println(h);
			try {
				BufferedReader br = new BufferedReader(new FileReader("Data/"+h+"/totalpoint.txt"));
				int tp = Integer.parseInt(br.readLine());
				all.add(new pair(h, tp));
				br.close();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		System.out.println("hi");
		for(int i=0; i<all.size(); i++) {
			for(int j=1; j<all.size(); j++) {
				if(all.get(j).pt > all.get(j-1).pt) {
					pair p = all.get(j);
					all.set(j, all.get(j-1));
					all.set(j-1, p);
				}
			}
		}
		String rank="";
		for(int i=0; i<Math.min(5, all.size()); i++){
			String s="";
			s += Integer.toString(i+1);
			s += ". -> "+all.get(i).hand +" -> "+Integer.toString(all.get(i).pt);;
			s += "\n";
			rank += s;
		}
		
		
		
		
		this.setBounds(600, 150, 700, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setTitle("Dashboard");
		this.getContentPane().setBackground(new Color(0x1f253d));
		headerpanel.setBounds(30, 30, 630, 100);
		headerpanel.setBackground(new Color(0x394165));
		headerpanel.setLayout(null);
		profilebtn.setBounds(20, 20, 180, 60);
		profilebtn.setBackground(new Color(0x394165));
		profilebtn.setForeground(Color.white);
		profilebtn.setFont(new Font("Arial", Font.PLAIN, 24));
		profilebtn.setBorder(null);
		profilebtn.setFocusable(false);
		profilebtn.addMouseListener(this);
		practicebtn.setBounds(200, 20, 180, 60);
		practicebtn.setBackground(new Color(0x394165));
		practicebtn.setForeground(Color.white);
		practicebtn.setFont(new Font("Arial", Font.PLAIN, 24));
		practicebtn.setBorder(null);
		practicebtn.setFocusable(false);
		practicebtn.addMouseListener(this);
		panel.setBounds(30, 160, 630, 480);
		panel.setBackground(new Color(0x394165));
		panel.setLayout(null);
		totalpoint.setBounds(0, 0, 630, 40);
		totalpoint.setHorizontalAlignment(SwingConstants.CENTER);
		totalpoint.setForeground(Color.white);
		totalpoint.setFont(new Font("Arial", Font.PLAIN, 24));
		int tp;
		try {
			BufferedReader br = new BufferedReader(new FileReader("Data/nowhandle.txt"));
			handle = br.readLine();
			br.close();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader("Data/"+handle+"/totalpoint.txt"));
			tp = Integer.parseInt(br.readLine());
			br.close();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		totalpoint.setText("Total Gained Point: "+Integer.toString(tp));
		
		area.setBounds(20, 100, 590, 360);
		area.setText(rank);
		area.setBackground(Color.black);
		area.setForeground(Color.green);
		area.setFont(new Font("Arial", Font.PLAIN, 35));
		ranklist.setBounds(0, 60, 630, 40);
		ranklist.setForeground(Color.white);
		ranklist.setFont(new Font("Arial", Font.PLAIN, 30));
		
		this.add(headerpanel);
		headerpanel.add(practicebtn);
		headerpanel.add(profilebtn);
		
		this.add(panel);
		panel.add(totalpoint);
		panel.add(area);
		panel.add(ranklist);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new DashboardPage();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == profilebtn) {
			try {
				this.dispose();
				new ProfilePage();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if(e.getSource() == practicebtn) {
			long endtime;
			try {
				BufferedReader bw = new BufferedReader(new FileReader("Data/"+handle+"/endtime.txt"));
				endtime = Long.parseLong(bw.readLine());
				bw.close();
			} catch (IOException e4) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			long now = System.currentTimeMillis()/1000; 
			this.dispose();
			if(now > endtime) {
				try {
					
					new PracticePage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				new CurrentPracticePage();
			}
			
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
		if(e.getSource() == profilebtn) {
			profilebtn.setBackground(new Color(0x1f253d));
		}
		if(e.getSource() == practicebtn) {
			practicebtn.setBackground(new Color(0x1f253d));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == profilebtn) {
			profilebtn.setBackground(new Color(0x394165));
		}
		if(e.getSource() == practicebtn) {
			practicebtn.setBackground(new Color(0x394165));
		}
	}

}
