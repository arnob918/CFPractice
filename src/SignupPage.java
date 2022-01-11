import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.json.*;

//import firstPackage.LoginClass;

public class SignupPage extends JFrame implements MouseListener{
	
	HttpURLConnection connection;
	BufferedReader reader;
	StringBuffer res = new StringBuffer();
	JButton button = new JButton();
	JTextField txt = new JTextField();
	ImageIcon img = new ImageIcon(LoginPage.class.getResource("viex.png"));
	ImageIcon icon = new ImageIcon(LoginPage.class.getResource("icon.png"));
	JLabel iconlabel = new JLabel(img);
	JLabel logintxt = new JLabel("SIGNUP");
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
	String api = "https://codeforces.com/api/user.info?handles=";
	String pass, fname, lname, imgurl;
	String path = "Data/";
	
	SignupPage(){
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
		passtxt.setToolTipText("Create a new Password");
		
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
		loginbtn.setBackground(new Color(0x8E1F1F));
		loginbtn.setForeground(Color.white);
		loginbtn.setFont(new Font("Arial", Font.PLAIN, 30));
		loginbtn.setBorder(null);
		loginbtn.setFocusable(false);
		
		signupbtn.setBounds(80, 470, 200, 80);
		signupbtn.setBackground(new Color(0xDF7B49));
		signupbtn.setForeground(Color.yellow);
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
			new SignupPage();
			this.dispose();
		}
		if(e.getSource() == loginbtn) {
			LoginPage page = new LoginPage();
			this.dispose();
		}
		if(e.getSource() == exitbtn) {
			System.exit(0);
		}
		if(e.getSource() == handletxt) {
			handlelabel.setForeground(Color.yellow);
			underhpanel.setBackground(Color.yellow);
		}
		if(e.getSource() == passtxt) {
			passlabel.setForeground(Color.yellow);
			underppanel.setBackground(Color.yellow);
		}
		if(e.getSource() == signupbtn) {
			
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
				
				JOptionPane.showMessageDialog(null, "You already have an account, Please login", "Error", JOptionPane.ERROR_MESSAGE);
				new LoginPage();
				this.dispose();
			}
			else {
				//JOptionPane.showMessageDialog(null, "Successfully Created an Account", "Error", JOptionPane.ERROR_MESSAGE);
				
				
				api += handle;
				try {
					URL url = new URL(api);
					connection = (HttpURLConnection) url.openConnection();
					
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(10000);
					connection.setReadTimeout(10000);
					
					int st = connection.getResponseCode();
					String line;
					if(st > 299) {
						reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
						while((line = reader.readLine()) != null) {
							res.append(line);
						}
						reader.close();
					}
					else {
						reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						while((line = reader.readLine()) != null) {
							res.append(line);
						}
						reader.close();
					}
					System.out.println(res.toString());
					JSONObject jobject = new JSONObject(res.toString());
					String stat = jobject.getString("status");
					System.out.println(stat);
					if(stat.equals("FAILED")) {
						JOptionPane.showMessageDialog(null, "Possible Causes:\n1. Entered incorrect handle\n2. Internet not connected\n3. Codeforces server down", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						Vector<String> all = new Vector();
						try {
							BufferedReader br = new BufferedReader(new FileReader("Data/allhandle.txt"));
							String s;
							while((s = br.readLine()) != null) {
								all.add(s);
							}
							br.close();
						} catch (IOException e3) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						all.add(handle);
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter("Data/allhandle.txt"));
							for(String ss: all) {
								bw.write(ss+"\n");
							}
							bw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							System.out.println("excp");
							e1.printStackTrace();
						}
						file.mkdir();
						String newpath = path+"/passwort.txt";
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter(newpath));
							bw.write(pass);
							bw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							System.out.println("excp");
							e1.printStackTrace();
						}
						newpath = path+"/handle.txt";
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter(newpath));
							bw.write(handle);
							bw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							System.out.println("excp");
							e1.printStackTrace();
						}
						newpath = path+"/starttime.txt";
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
							BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/totalpoint.txt"));
							bw.write("0");
							bw.close();
						} catch (IOException e4) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/gainedpoint.txt"));
							bw.write("0");
							bw.close();
						} catch (IOException e4) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/jsonarray.txt"));
							bw.write("");
							bw.close();
						} catch (IOException e4) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/problemname.txt"));
							bw.write("");
							bw.close();
						} catch (IOException e4) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/solvednames.txt"));
							bw.write("");
							bw.close();
						} catch (IOException e4) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/targetpoint.txt"));
							bw.write("0");
							bw.close();
						} catch (IOException e4) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/totalpoint.txt"));
							bw.write("0");
							bw.close();
						} catch (IOException e4) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						JSONArray arr = jobject.getJSONArray("result");
						JSONObject obj = arr.getJSONObject(0);
						if(obj.has("firstName")) {
							fname = obj.getString("firstName");
						}
						if(obj.has("lastName")) {
							lname = obj.getString("lastName");
						}
						JOptionPane.showMessageDialog(null, "Welcome "+fname + " " + lname, "Successfull", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Possible Causes:\n1. Internet not connected\n2. Codeforces server down", "Error", JOptionPane.ERROR_MESSAGE);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Possible Causes:\n1. Internet not connected\n2. Codeforces server down", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
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
		if(e.getSource() == signupbtn) {
			signupbtn.setForeground(Color.green);
			signupbtn.setBackground(new Color(0xDE6123));
		}
		if(e.getSource() == loginbtn) {
			loginbtn.setBackground(new Color(0xB24F1E));
			loginbtn.setForeground(Color.yellow);
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
		if(e.getSource() == signupbtn) {
			signupbtn.setForeground(Color.yellow);
			signupbtn.setBackground(new Color(0xDF7B49));
		}
		if(e.getSource() == loginbtn) {
			loginbtn.setBackground(new Color(0x8E1F1F));
			loginbtn.setForeground(Color.white);
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

