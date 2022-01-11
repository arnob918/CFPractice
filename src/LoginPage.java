import java.awt.Color;

import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.*;

//import firstPackage.LoginClass;

public class LoginPage extends JFrame implements MouseListener{
	
	HttpURLConnection connection;
	BufferedReader reader;
	StringBuffer res = new StringBuffer();
	JButton button = new JButton();
	JTextField txt = new JTextField();
	ImageIcon img = new ImageIcon(LoginPage.class.getResource("viex.png"));
	ImageIcon icon = new ImageIcon(LoginPage.class.getResource("icon.png"));
	JLabel iconlabel = new JLabel(img);
	JLabel logintxt = new JLabel("LOGIN");
	JPanel panel = new JPanel();
	JPanel loginpanel = new JPanel();
	JLabel handlelabel = new JLabel("Handle:");
	JTextField handletxt = new JTextField();
	JLabel passlabel = new JLabel("Password:");
	JPasswordField passtxt = new JPasswordField();
	JPanel underhpanel = new JPanel();
	JPanel underppanel = new JPanel();
	JButton signupbtn = new JButton("SIGNUP");
	JButton loginbtn = new JButton("LOGIN");
	JButton exitbtn = new JButton("EXIT");
	JButton resetbtn = new JButton("RESET");
	String handle = "";
	String pass = "";
	String s = "https://codeforces.com/api/user.info?handles=";
	String path = "Data/";
	
	LoginPage(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(250, 150, 1400, 800);
		this.setLayout(null);
		this.setTitle("CF Practice Helper");
		this.getContentPane().setBackground(new Color(0x1f253d));
		this.setVisible(true);
		this.setIconImage(icon.getImage());
		this.setResizable(false);
		
		
		iconlabel.setBounds(94, 144, img.getIconWidth(), img.getIconHeight());
		logintxt.setBounds(200, 0, 200, 100);
		logintxt.setForeground(Color.yellow);
		logintxt.setBackground(new Color(0x8E1F1F));
		logintxt.setFont(new Font("Arial", Font.BOLD, 45));
		logintxt.setBorder(null);
		
		handlelabel.setBounds(50, 60, 200, 30);
		handlelabel.setForeground(Color.yellow);
		handlelabel.setFont(new Font("Arial", Font.ITALIC, 30));
		handletxt.setBounds(50, 90, 300, 40);
		handletxt.setBackground(new Color(0xDF7B49));
		handletxt.setBorder(null);
		handletxt.setForeground(Color.darkGray);
		handletxt.setFont(new Font("Arial", Font.PLAIN, 30));
		handletxt.setToolTipText("Codeforces Handle");
		
		passlabel.setBounds(50, 190, 200, 30);
		passlabel.setForeground(Color.yellow);
		passlabel.setFont(new Font("Arial", Font.ITALIC, 30));
		passtxt.setBounds(50, 220, 300, 40);
		passtxt.setBackground(new Color(0xDF7B49));
		passtxt.setBorder(null);
		passtxt.setForeground(Color.darkGray);
		passtxt.setFont(new Font("Arial", Font.PLAIN, 30));
		passtxt.setEchoChar(';');
		passtxt.setToolTipText("CFPractice Password");
		
		panel.setBounds(770, 70, 560, 600);
		panel.setBackground(new Color(0x8E1F1F));
		panel.setLayout(null);
		loginpanel.setBounds(80, 120, 400, 350);
		loginpanel.setBackground(new Color(0xDF7B49));
		loginpanel.setLayout(null);
		underhpanel.setBounds(50, 130, 300, 2);
		underhpanel.setBackground(Color.yellow);
		underppanel.setBounds(50, 260, 300, 2);
		underppanel.setBackground(Color.yellow);
		
		loginbtn.setBounds(280, 470, 200, 80);
		loginbtn.setBackground(new Color(0xDF7B49));
		loginbtn.setForeground(Color.yellow);
		loginbtn.setFont(new Font("Arial", Font.PLAIN, 30));
		loginbtn.setBorder(null);
		loginbtn.setFocusable(false);
		
		signupbtn.setBounds(80, 470, 200, 80);
		signupbtn.setBackground(new Color(0x8E1F1F));
		signupbtn.setForeground(Color.white);
		signupbtn.setFont(new Font("Arial", Font.PLAIN, 30));
		signupbtn.setBorder(null);
		signupbtn.setFocusable(false);
		
		exitbtn.setBounds(1180, 690, 150, 60);
		exitbtn.setBackground(new Color(0x8E1F1F));
		exitbtn.setForeground(Color.white);
		exitbtn.setFont(new Font("Arial", Font.PLAIN, 30));
		exitbtn.setBorder(null);
		exitbtn.setFocusable(false);
		
		resetbtn.setBounds(1000, 690, 150, 60);
		resetbtn.setBackground(new Color(0x8E1F1F));
		resetbtn.setForeground(Color.white);
		resetbtn.setFont(new Font("Arial", Font.PLAIN, 30));
		resetbtn.setBorder(null);
		resetbtn.setFocusable(false);

		loginbtn.addMouseListener(this);
		signupbtn.addMouseListener(this);
		exitbtn.addMouseListener(this);
		resetbtn.addMouseListener(this);
		handletxt.addMouseListener(this);
		passtxt.addMouseListener(this);
		
		this.add(iconlabel);
		this.add(panel);
		this.add(exitbtn);
		this.add(resetbtn);
		panel.add(loginpanel);
		panel.add(logintxt);
		panel.add(loginbtn);
		panel.add(signupbtn);
		loginpanel.add(handlelabel);
		loginpanel.add(underhpanel);
		loginpanel.add(handletxt);
		loginpanel.add(passlabel);
		loginpanel.add(underppanel);
		loginpanel.add(passtxt);
	}

	public static void main(String[] args) throws IOException{
		
		//new LoginPage();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == resetbtn) {
			new LoginPage();
			this.dispose();
		}
		if(e.getSource() == signupbtn) {
			SignupPage page = new SignupPage();
			this.dispose();
		}
		if(e.getSource() == handletxt) {
			handlelabel.setForeground(Color.yellow);
			underhpanel.setBackground(Color.yellow);
		}
		if(e.getSource() == passtxt) {
			passlabel.setForeground(Color.yellow);
			underppanel.setBackground(Color.yellow);
		}
		if(e.getSource() == loginbtn) {
			handle = handletxt.getText();
			pass = passtxt.getText();
			handletxt.setEditable(false);
			passtxt.setEditable(false);
			path += handle;
			File file = new File(path);
			if(handle.isEmpty()) {
				handlelabel.setForeground(Color.red);
				underhpanel.setBackground(Color.red);
				JOptionPane.showMessageDialog(null, "You haven't entered your handle", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(pass.isEmpty()) {
				passlabel.setForeground(Color.red);
				underppanel.setBackground(Color.red);
				JOptionPane.showMessageDialog(null, "You haven't entered your password", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			else if(file.exists()) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(path+"/passwort.txt"));
					String realpass = br.readLine();
					br.close();
					if(!pass.equals(realpass)) {
						JOptionPane.showMessageDialog(null, "Incorrect Password", "Error", JOptionPane.ERROR_MESSAGE);
						this.dispose();
						new LoginPage();
						
						return;
					}
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("Data/nowhandle.txt"));
					bw.write(handle);
					bw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Sorry, Please Try Again", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				try {
					this.dispose();
					new ProfilePage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Welcome", "Welcome", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "You haven't signed up yet!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == exitbtn) {
			System.exit(0);
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
		if(e.getSource() == loginbtn) {
			loginbtn.setForeground(Color.green);
			loginbtn.setBackground(new Color(0xDE6123));
		}
		if(e.getSource() == signupbtn) {
			signupbtn.setBackground(new Color(0xB24F1E));
			signupbtn.setForeground(Color.yellow);
		}
		if(e.getSource() == exitbtn) {
			exitbtn.setBackground(Color.red);
			exitbtn.setForeground(Color.yellow);
		}
		if(e.getSource() == resetbtn) {
			resetbtn.setBackground(Color.red);
			resetbtn.setForeground(Color.yellow);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == loginbtn) {
			loginbtn.setForeground(Color.yellow);
			loginbtn.setBackground(new Color(0xDF7B49));
		}
		if(e.getSource() == signupbtn) {
			signupbtn.setBackground(new Color(0x8E1F1F));
			signupbtn.setForeground(Color.white);
		}
		if(e.getSource() == exitbtn) {
			exitbtn.setBackground(new Color(0x8E1F1F));
			exitbtn.setForeground(Color.white);
		}
		if(e.getSource() == resetbtn) {
			resetbtn.setBackground(new Color(0x8E1F1F));
			resetbtn.setForeground(Color.white);
		}
	}

	

}
