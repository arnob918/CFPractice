import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.JSONArray;
import org.json.JSONObject;



public class CreatePractice extends JFrame implements MouseListener {

	static int ratfrom;
	static int ratto;
	static public void get_from(int i) {
		ratfrom = i;
	}
	static public void get_to(int i) {
		ratto = i;
	}
	JPanel tag = new JPanel();
	JPanel rating = new JPanel();
	JPanel time = new JPanel();
	JPanel point = new JPanel();
	JLabel selecttags = new JLabel("Select Tags:");
	JLabel ratingrange = new JLabel("Difficulty Range:");
	JLabel timelabel = new JLabel("Time:");
	JTextField selecttagtxt = new JTextField("Random");
	JButton tagbtn = new JButton("Select");
	//JButton ratingbtn = new JButton("Select");
	String[] stags = {"implementation",
			"math",
			"greedy",
			"dp",
			"data structures",
			"brute force",
			"constructive algorithms",
			"graphs",
			"sortings",
			"binary search",
			"dfs and similar",
			"trees",
			"strings",
			"number theory",
			"combinatorics",
			"geometry",
			"bitmasks",
			"two pointers",
			"dsu",
			"shortest paths",
			"probabilities",
			"divide and conquer",
			"hashing",
			"games",
			"flows",
			"interactive",
			"matrices",
			"string suffix structures",
			"fft",
			"graph matchings",
			"ternary search",
			"expression parsing",
			"meet-in-the-middle",
			"2-sat",
			"chinese remainder theorem",
			"schedules"};
	
	String [] apitags = {"implementation",
			"math",
			"greedy",
			"dp",
			"data+structures",
			"brute+force",
			"constructive+algorithms",
			"graphs",
			"sortings",
			"binary+search",
			"dfs+and+similar",
			"trees",
			"strings",
			"number+theory",
			"combinatorics",
			"geometry",
			"bitmasks",
			"two+pointers",
			"dsu",
			"shortest+paths",
			"probabilities",
			"divide+and+conquer",
			"hashing",
			"games",
			"flows",
			"interactive",
			"matrices",
			"string+suffix+structures",
			"fft",
			"graph+matchings",
			"ternary+search",
			"expression+parsing",
			"meet-in-the-middle",
			"2-sat",
			"chinese+remainder+theorem",
			"schedules"};

	Vector<JCheckBox> vck = new Vector<JCheckBox>();
	String apisuffix = "";
	JLabel fromlbl = new JLabel("From:");
	JLabel tolbl = new JLabel("To:");
	JLabel duration  = new JLabel("Duration:");
	JLabel hh  = new JLabel("HH:");
	JLabel mm  = new JLabel("MM:");
	JLabel ss  = new JLabel("SS:");
	JTextField hht = new JTextField("0");
	JTextField mmt = new JTextField("0");
	JTextField sst = new JTextField("0");
	JLabel tarpoint = new JLabel("Target Point: ");
	JTextField tarpt = new JTextField("0");
	JButton con = new JButton("Confirm");
	JButton dis = new JButton("Discard");
	JTextField fromtxt = new JTextField("1400");
	JTextField totxt = new JTextField("2000");

	String hand, rat, tarpnt;
	//JButton ratrefresh = new JButton("Refresh");
	
	//static int fxfrom, fxto;
	//String hand, rat;
	//static JFrame frem = new JFrame();
	//JLabel fromlabel = new JLabel();
	//JLabel tolabel = new JLabel();
	//JSlider fromslider;
	//JSlider toslider;
	//JPanel frompanel;
	//JPanel topanel;
	//JLabel rfromtxt = new JLabel("From: ");
	//JLabel rtotxt = new JLabel("To: ");
	//static JButton done = new JButton("Done");
	
	
	CreatePractice(){
		Arrays.sort(stags);
		Arrays.sort(apitags);
		for(int i=0; i<stags.length; i++) {
			JCheckBox ckb = new JCheckBox();
			ckb.setText(stags[i]);
			vck.add(ckb);
		}
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("Data/nowhandle.txt"));
			hand = br.readLine();
			br.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			br = new BufferedReader(new FileReader("Data/"+hand+"/rating.txt"));
			rat = br.readLine();
			br.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int handle = Integer.parseInt(rat);
		handle /= 100;
		handle = Math.max(handle, 8);
		handle *= 100;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(600, 100, 700, 900);
		this.setVisible(true);
		this.setLayout(null);
		this.setTitle("Create New Practice");
		this.getContentPane().setBackground(new Color(0x1f253d));
			
		tag.setBounds(50, 50, 600, 200);
		tag.setBackground(new Color(0x394165));
		tag.setLayout(null);
		selecttags.setBounds(0, 0, 600, 40);
		selecttags.setHorizontalAlignment(SwingConstants.CENTER);
		selecttags.setForeground(Color.white);
		selecttags.setFont(new Font("Arial", Font.PLAIN, 24));
		//selecttagtxt.setText("Random");
		selecttagtxt.setBounds(20, 60, 560, 40);
		
		tagbtn.setBounds(225, 130, 150, 40);
		tagbtn.setBackground(new Color(0x394165));
		tagbtn.setForeground(Color.white);
		tagbtn.setFont(new Font("Arial", Font.PLAIN, 24));
		tagbtn.setBorder(null);
		tagbtn.setFocusable(false);
		tagbtn.addMouseListener(this);
		
		selecttagtxt.setEditable(false);
		
		rating.setBounds(50, 280, 600, 150);
		rating.setBackground(new Color(0x394165));
		rating.setLayout(null);
		ratingrange.setBounds(0, 0, 600, 40);
		ratingrange.setHorizontalAlignment(SwingConstants.CENTER);
		ratingrange.setForeground(Color.white);
		ratingrange.setFont(new Font("Arial", Font.PLAIN, 24));
		fromlbl.setBounds(50, 60, 100, 50);
		tolbl.setBounds(350, 60, 100, 50);
		
		fromtxt.setBounds(150, 60, 100, 50);
		totxt.setBounds(450, 60, 100, 50);
		fromlbl.setBackground(new Color(0x394165));
		fromlbl.setForeground(Color.white);
		fromlbl.setFont(new Font("Arial", Font.PLAIN, 25));
		tolbl.setBackground(new Color(0x394165));
		tolbl.setForeground(Color.white);
		tolbl.setFont(new Font("Arial", Font.PLAIN, 25));
		fromtxt.setBackground(Color.black);
		totxt.setBackground(Color.black);
		fromtxt.setForeground(Color.green);
		totxt.setForeground(Color.green);
		fromtxt.setFont(new Font("Arial", Font.PLAIN, 25));
		totxt.setFont(new Font("Arial", Font.PLAIN, 25));
		//fromtxt.setText();
		//totxt.setText();
		//ratingbtn.setBounds(225, 130, 150, 40);
		//ratingbtn.setBackground(new Color(0x394165));
		//ratingbtn.setForeground(Color.white);
		//ratingbtn.setFont(new Font("Arial", Font.PLAIN, 24));
		//ratingbtn.setBorder(null);
		//ratingbtn.setFocusable(false);
		//ratingbtn.addMouseListener(this);
		//ratrefresh.setBounds(375, 130, 150, 40);
		//ratrefresh.setBackground(new Color(0x394165));
		//ratrefresh.setForeground(Color.white);
		//ratrefresh.setFont(new Font("Arial", Font.PLAIN, 24));
		//ratrefresh.setBorder(null);
		//ratrefresh.setFocusable(false);
		//ratrefresh.addMouseListener(this);
		
		
		
		
		time.setBounds(50, 460, 600, 150);
		time.setBackground(new Color(0x394165));
		time.setLayout(null);
		
		timelabel.setBounds(0, 0, 600, 40);
		timelabel.setHorizontalAlignment(SwingConstants.CENTER);
		timelabel.setForeground(Color.white);
		timelabel.setFont(new Font("Arial", Font.PLAIN, 24));
		
		
		duration.setBounds(20, 50, 120, 80);
		duration.setForeground(Color.white);
		duration.setFont(new Font("Arial", Font.PLAIN, 24));
		hh.setBounds(140, 50, 50, 80);
		hh.setForeground(Color.white);
		hh.setFont(new Font("Arial", Font.PLAIN, 24));
		mm.setBounds(290, 50, 50, 80);
		mm.setForeground(Color.white);
		mm.setFont(new Font("Arial", Font.PLAIN, 24));
		ss.setBounds(440, 50, 50, 80);
		ss.setForeground(Color.white);
		ss.setFont(new Font("Arial", Font.PLAIN, 24));
		

		hht.setBounds(190, 60, 80, 50);
		hht.setBackground(Color.black);
		hht.setForeground(Color.green);
		hht.setFont(new Font("Arial", Font.PLAIN, 25));
		mmt.setBounds(340, 60, 80, 50);
		mmt.setBackground(Color.black);
		mmt.setForeground(Color.green);
		mmt.setFont(new Font("Arial", Font.PLAIN, 25));
		sst.setBounds(490, 60, 80, 50);
		sst.setBackground(Color.black);
		sst.setForeground(Color.green);
		sst.setFont(new Font("Arial", Font.PLAIN, 25));

		
		point.setBounds(50, 640, 600, 120);
		point.setBackground(new Color(0x394165));
		point.setLayout(null);
		
		tarpoint.setBounds(20, 20, 250, 80);
		tarpoint.setForeground(Color.white);
		tarpoint.setFont(new Font("Arial", Font.PLAIN, 30));
		tarpt.setBounds(270, 40, 200, 50);
		tarpt.setBackground(Color.black);
		tarpt.setForeground(Color.green);
		tarpt.setFont(new Font("Arial", Font.PLAIN, 28));
		
		con.setBounds(100, 770, 200, 70);
		con.setBackground(Color.green);
		con.setFont(new Font("Arial", Font.PLAIN, 28));
		con.setFocusable(false);
		con.addMouseListener(this);
		
		dis.setBounds(400, 770, 200, 70);
		dis.setBackground(Color.red);
		dis.setFont(new Font("Arial", Font.PLAIN, 28));
		dis.setFocusable(false);
		dis.addMouseListener(this);
		
		this.add(tag);
		tag.add(selecttags);
		tag.add(selecttagtxt);
		tag.add(tagbtn);
		rating.add(ratingrange);
		rating.add(fromlbl);
		rating.add(fromtxt);
		rating.add(tolbl);
		rating.add(totxt);
		//rating.add(ratingbtn);
		//rating.add(ratrefresh);
		this.add(rating);
		this.add(time);
		time.add(timelabel);
		time.add(duration);
		time.add(hh);
		time.add(mm);
		time.add(ss);
		time.add(hht);
		time.add(sst);
		time.add(mmt);
		this.add(point);
		point.add(tarpoint);
		point.add(tarpt);
		this.add(dis);
		this.add(con);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CreatePractice();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == dis) {
			try {
				new PracticePage();
				this.dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == con) {
			
			Set<String> ids = new HashSet<String>();
			
			try {
				BufferedReader br = new BufferedReader(new FileReader("Data/"+hand+"/solvednames.txt"));
				String s;
				while((s = br.readLine()) != null) {
					ids.add(s);
				}
				br.close();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			for(String s: ids) {
				System.out.println(s);
			}
			int up, lo;
			up = Integer.parseInt(totxt.getText());
			lo = Integer.parseInt(fromtxt.getText());
			HttpURLConnection connection;
			BufferedReader reader;
			StringBuffer res = new StringBuffer();
			String api = "https://codeforces.com/api/problemset.problems"+apisuffix;
			System.out.println(api);
			try {
				URL url = new URL(api);
				connection = (HttpURLConnection) url.openConnection();
				
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(10000);
				connection.setReadTimeout(10000);
				
				int st = connection.getResponseCode();
				String line;
				if(st > 299) {
					JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
					return;
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
					JOptionPane.showMessageDialog(null, "Possible Causes:\n1. Internet not connected\n2. Codeforces server down", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					
					JSONObject object = jobject.getJSONObject("result");
					JSONArray arr = object.getJSONArray("problems");
					JSONArray narr = new JSONArray();
					for(int i=0; i<arr.length(); i++) {
						JSONObject obj = arr.getJSONObject(i);
						if(obj.has("rating") ) {
							int rat = obj.getInt("rating");
							if(rat >= lo  && rat <= up) {
								String name = obj.getString("name");
								if(!ids.contains(name))
									narr.put(obj);
							}
						}
					}
					System.out.println(narr.toString());
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+hand+"/jsonarray.txt"));
						bw.write(narr.toString());
						bw.close();
					} catch (IOException e4) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+hand+"/jsonarray.txt"));
						bw.write(narr.toString());
						bw.close();
					} catch (IOException e4) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					tarpnt = tarpt.getText();
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+hand+"/tagertpoint.txt"));
						bw.write(tarpnt);
						bw.close();
					} catch (IOException e4) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+hand+"/gainedpoint.txt"));
						bw.write("0");
						bw.close();
					} catch (IOException e4) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					long epoch = System.currentTimeMillis()/1000;
					int h = Integer.parseInt(hht.getText());
					int m = Integer.parseInt(mmt.getText());
					int s = Integer.parseInt(sst.getText());
					System.out.println(epoch+(h*3600)+(m*60)+s);
					System.out.println(epoch);
					String end = Long.toString(epoch+(h*3600)+(m*60)+s);
					String stt = Long.toString(epoch);
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+hand+"/endtime.txt"));
						bw.write(end);
						bw.close();
					} catch (IOException e4) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+hand+"/starttime.txt"));
						bw.write(stt);
						bw.close();
					} catch (IOException e4) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+hand+"/problemname.txt"));
						bw.write("**");
						bw.close();
					} catch (IOException e4) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			} catch (IOException  e2) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			
			CurrentPracticePage nn = new CurrentPracticePage();
			
			this.dispose();
		}
		if(e.getSource() == tagbtn) {
			JFrame frm = new JFrame();
			frm.setLocation(600, 350);
			frm.setSize(600, 350);
			frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frm.setVisible(true);
			frm.setLayout(new FlowLayout());
			for(int i=0; i<vck.size(); i++) {
				frm.add(vck.get(i));
			}
			//frm.pack();
			JButton done = new JButton("DONE");
			done.addActionListener(ex -> {
				String s = "";
				apisuffix = "";
				for(int i=0; i<vck.size(); i++) {
					if(vck.get(i).isSelected()) {
						if(s.isEmpty()) {
							s += stags[i];
						}
						else {
							s += ", " + stags[i];
						}
						if(apisuffix.isEmpty()) {
							apisuffix = "?tags=";
							apisuffix += apitags[i];
						}
						else {
							apisuffix += ";" + apitags[i]; 
						}
					}
				}
				if(s.isEmpty()) s = "Random";
				selecttagtxt.setText(s);
				frm.dispose();
			});
			//done.setSize(400, 50);;
			frm.add(done);
			
		}
		/*if(e.getSource() == ratingbtn) {
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader("Data/nowhandle.txt"));
				hand = br.readLine();
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				br = new BufferedReader(new FileReader("Data/"+hand+"/rating.txt"));
				rat = br.readLine();
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int handle = Integer.parseInt(rat);
			handle /= 100;
			int low = handle-6;
			low = Math.max(low, 8);
			int hi = handle+6;
			hi = Math.max(hi, low+12);
			handle = Math.max(handle, 8);
			fxfrom = fxto = handle*100;
			frem.setBounds(500, 300, 800, 700);
			frem.setTitle("Range of Rating");
			frem.setLayout(null);
			frem.getContentPane().setBackground(new Color(0x1f253d));
			frem.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			
			frompanel = new JPanel();
			frompanel.setBounds(20, 20, 760, 260);
			frompanel.setBackground(new Color(0x394165));
			frompanel.setLayout(null);
			
			rfromtxt.setBounds(0, 0, 100, 50);
			rfromtxt.setForeground(Color.white);
			rfromtxt.setFont(new Font("Arial", Font.PLAIN, 30));
			rfromtxt.setBackground(new Color(0x394165));
			fromslider = new JSlider(low, hi, handle);
			fromslider.setBounds(20, 60, 720, 150);
			
			fromslider.setPaintTicks(true);
			fromslider.setMinorTickSpacing(1);
			
			fromslider.setPaintTrack(true);
			fromslider.setMajorTickSpacing(2);
			
			fromslider.setPaintLabels(true);
			fromslider.setFont(new Font("Arial", Font.PLAIN, 20));
			fromslider.setBackground(new Color(0x394165));
			fromslider.setForeground(Color.white);
			fromslider.addChangeListener(fx2 -> {
				fromlabel.setText(Integer.toString(fromslider.getValue()*100));
				fxfrom = fromslider.getValue()*100;
			});
			
			fromlabel.setText(Integer.toString(handle*100));
			fromlabel.setBounds(320, 210, 200, 50);
			fromlabel.setForeground(Color.white);
			fromlabel.setFont(new Font("Arial", Font.PLAIN, 50));
			fromlabel.setBackground(new Color(0x1f253d));

			frompanel.add(fromtxt);
			frompanel.add(fromlabel);
			frompanel.add(fromslider);
			frompanel.add(fromtxt);
			
			topanel = new JPanel();
			topanel.setBounds(20, 300, 760, 260);
			topanel.setBackground(new Color(0x394165));
			topanel.setLayout(null);
			
			rtotxt.setBounds(0, 0, 100, 50);
			rtotxt.setForeground(Color.white);
			rtotxt.setFont(new Font("Arial", Font.PLAIN, 30));
			rtotxt.setBackground(new Color(0x394165));
			toslider = new JSlider(low, hi, handle);
			toslider.setBounds(20, 60, 720, 150);
			
			toslider.setPaintTicks(true);
			toslider.setMinorTickSpacing(1);
			
			toslider.setPaintTrack(true);
			toslider.setMajorTickSpacing(2);
			
			toslider.setPaintLabels(true);
			toslider.setFont(new Font("Arial", Font.PLAIN, 20));
			toslider.setBackground(new Color(0x394165));
			toslider.setForeground(Color.white);
			toslider.addChangeListener(fx -> {
				tolabel.setText(Integer.toString(toslider.getValue()*100));
				fxto = toslider.getValue()*100;
			});
			
			tolabel.setText(Integer.toString(handle*100));
			tolabel.setBounds(320, 210, 200, 50);
			tolabel.setForeground(Color.white);
			tolabel.setFont(new Font("Arial", Font.PLAIN, 50));
			tolabel.setBackground(new Color(0x1f253d));
			
			done.setBounds(350, 580, 100, 50);
			//done.setForeground(Color.yellow);
			done.setFont(new Font("Arial", Font.PLAIN, 20));
			done.setBackground(Color.green);
			done.setFocusable(false);
			
			done.addActionListener(ex -> {
				  
			    fromtxt.setText(Integer.toString(fxfrom));
				totxt.setText(Integer.toString(fxto));
				System.out.println(fxfrom);
				System.out.println(fxto);
				this.revalidate();
				frem.dispose();
			});

			topanel.add(totxt);
			topanel.add(tolabel);
			topanel.add(toslider);
			topanel.add(totxt);
			
			frem.add(frompanel);
			frem.add(topanel);
			frem.add(done);
			//System.out.println("body");
			frem.setVisible(true);
			
		}*/
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
		if(e.getSource() == tagbtn) {
			tagbtn.setBackground(new Color(0x1f253d));
		}
		/*if(e.getSource() == ratingbtn) {
			ratingbtn.setBackground(new Color(0x1f253d));
		}
		if(e.getSource() == ratrefresh) {
			ratrefresh.setBackground(Color.red);
		}*/
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == tagbtn) {
			tagbtn.setBackground(new Color(0x394165));
		}
		/*if(e.getSource() == ratingbtn) {
			ratingbtn.setBackground(new Color(0x394165));
		}
		if(e.getSource() == ratrefresh) {
			ratrefresh.setBackground(new Color(0x394165));
		}*/
	}

}

