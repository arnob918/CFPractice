import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class PracticePage extends JFrame implements MouseListener{

	ImageIcon icon = new ImageIcon(LoginPage.class.getResource("icon.png"));
	JLabel noprac = new JLabel("NO current Practice Session!");
	JButton start = new JButton("New");
	JPanel headerpanel = new JPanel();
	JButton dashboardbtn = new JButton("Dashboard");
	JButton profilebtn = new JButton("Profile");

	JButton dlt = new JButton("Delete");
	static long sttime, entime;
	String path = "", handle;
	
	PracticePage() throws IOException{
		
		headerpanel.setBounds(30, 30, 540, 100);
		headerpanel.setBackground(new Color(0x394165));
		headerpanel.setLayout(null);
		dashboardbtn.setBounds(15, 20, 170, 60);
		dashboardbtn.setBackground(new Color(0x394165));
		dashboardbtn.setForeground(Color.white);
		dashboardbtn.setFont(new Font("Arial", Font.PLAIN, 24));
		dashboardbtn.setBorder(null);
		dashboardbtn.setFocusable(false);
		dashboardbtn.addMouseListener(this);
		profilebtn.setBounds(185, 20, 170, 60);
		profilebtn.setBackground(new Color(0x394165));
		profilebtn.setForeground(Color.white);
		profilebtn.setFont(new Font("Arial", Font.PLAIN, 24));
		profilebtn.setBorder(null);
		profilebtn.setFocusable(false);
		profilebtn.addMouseListener(this);
		start.setBounds(355, 20, 170, 60);
		start.setBackground(new Color(0x394165));
		start.setForeground(Color.white);
		start.setFont(new Font("Arial", Font.PLAIN, 24));
		start.setBorder(null);
		start.setFocusable(false);
		start.addMouseListener(this);
		
		noprac.setBounds(20, 300, 560, 150);
		noprac.setFont(new Font("Verdana", Font.PLAIN, 38));
		noprac.setForeground(Color.green);
		noprac.setBackground(Color.black);
		noprac.setOpaque(true);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(650, 300, 600, 500);
		this.setLayout(null);
		this.setTitle("Start Practice");
		this.setIconImage(icon.getImage());
		this.getContentPane().setBackground(new Color(0x1f253d));
		
		
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("Data/nowhandle.txt"));
			handle = br.readLine();
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		path = "Data/"+handle;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path+"/starttime.txt"));
			String s = br.readLine();
			br.close();
			sttime = Long.parseLong(s);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path+"/endtime.txt"));
			String s = br.readLine();
			br.close();
			entime = Long.parseLong(s);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		long epoch = System.currentTimeMillis()/1000;
		System.out.println(epoch);
		if(epoch < sttime) {
			JLabel future = new JLabel();
			String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (sttime*1000));
			future.setText("Upcomming Practice: "+date);
			future.setBounds(20, 150, 560, 50);
			future.setFont(new Font("Verdana", Font.PLAIN, 25));
			future.setForeground(Color.red);
			future.setBackground(Color.black);
			future.setOpaque(true);
			start.removeMouseListener(this);
			dlt.setBounds(225, 220, 150, 40);
			dlt.setBackground(Color.red);
			dlt.setForeground(Color.white);
			dlt.setFont(new Font("Arial", Font.PLAIN, 20));
			dlt.setBorder(null);
			dlt.setFocusable(false);
			dlt.addMouseListener(this);

			this.add(future);
			this.add(dlt);
		}
		
		headerpanel.add(dashboardbtn);
		headerpanel.add(profilebtn);
		headerpanel.add(start);
		
		this.add(noprac);
		this.add(headerpanel);
		
		
		
		this.setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new PracticePage();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == start) {
			this.dispose();
			new CreatePractice();
		}
		if(e.getSource() == dashboardbtn) {
			this.dispose();
			new DashboardPage();
		}
		if(e.getSource() == profilebtn) {
			try {
				this.dispose();
				new ProfilePage();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Sorry, Please Try Again, Check Internet", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		}
		if(e.getSource() == dlt) {
			String newpath = path+"/starttime.txt";
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(newpath));
				bw.write("0");
				bw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("excp");
				e1.printStackTrace();
			}
			newpath = path+"/endtime.txt";
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(newpath));
				bw.write("0");
				bw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("excp");
				e1.printStackTrace();
			}
			try {
				new PracticePage();
				this.dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
		if(e.getSource() == dashboardbtn) {
			dashboardbtn.setBackground(new Color(0x1f253d));
		}
		if(e.getSource() == profilebtn) {
			profilebtn.setBackground(new Color(0x1f253d));
		}
		if(e.getSource() == start) {
			start.setBackground(new Color(0x1f253d));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == dashboardbtn) {
			dashboardbtn.setBackground(new Color(0x394165));
		}
		if(e.getSource() == profilebtn) {
			profilebtn.setBackground(new Color(0x394165));
		}
		if(e.getSource() == start) {
			start.setBackground(new Color(0x394165));
		}
	}

}
