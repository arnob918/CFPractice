import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.json.JSONArray;
import org.json.JSONObject;

public class ProfilePage extends JFrame implements MouseListener{
	
	

	static HttpURLConnection connection, con;
	static BufferedReader reader, br, read;
	static StringBuffer res = new StringBuffer();
	static StringBuffer res2 = new StringBuffer();
	static JFrame processing = new JFrame("processing");
	ImageIcon icon = new ImageIcon(LoginPage.class.getResource("icon.png"));
	ImageIcon icon2 = new ImageIcon(LoginPage.class.getResource("images.png"));
	JPanel headerpanel = new JPanel();
	JButton dashboardbtn = new JButton("Dashboard");
	JButton practicebtn = new JButton("Practice");
	JButton helpbtn = new JButton("Help");
	
	JPanel leftpanel = new JPanel();
	JPanel visuals = new JPanel();
	JLabel visual = new JLabel("Visuals");
	JButton rating = new JButton("Solved by Raing");
	JButton tags = new JButton("Solved by Tags");
	JButton abc = new JButton("Solved by Index");
	JButton time = new JButton("Solved by Time");
	JButton cfpro = new JButton("CF Profile");
	JButton cftr = new JButton("CF Tracker");
	
	JPanel middlepanel = new JPanel();
	ImageIcon imgi;
	Image img;
	
	//ImageIcon profileimg = new ImageIcon(getClass().getResource("home/arnob918/Pictures/Screenshot from 2021-06-07 16-21-22.png"));
	
	JPanel rightpanel = new JPanel();
	
	String api, handle, fname, lname, rankname;
	int cfrating;
	Color ratingcol;
	JLabel name = new JLabel();
	JLabel showhandle = new JLabel();
	JLabel Rating = new JLabel("Rating: ");
	JLabel sRating = new JLabel();
	String cfprofilelink = "https://codeforces.com/profile/";
	JButton logoutbtn = new JButton("LOGOUT");

	static Map <Integer, Integer> ratingwise = new HashMap<Integer, Integer>();
	static Map <String, Integer> tagwise = new HashMap<String, Integer>();
	static Map <String, Integer> abcwise = new HashMap<String, Integer>();
	static Map <Integer, Integer> timewise = new HashMap<Integer, Integer>();
	static Set<String> ids = new HashSet<String>();

	static DefaultCategoryDataset ratingdata = new DefaultCategoryDataset();
	static DefaultPieDataset tagdata = new DefaultPieDataset();
	static DefaultCategoryDataset abcdata = new DefaultCategoryDataset();
	
	JLabel processlbl = new JLabel();
	JLabel totalsolved = new JLabel();
	JLabel totalpoint = new JLabel();
	
	static void makingdata(String handle) throws IOException{
		String res2 = null;
		
		BufferedReader reader;
		String line;
		StringBuffer res = new StringBuffer();
		
		try {
			URL url = new URL("https://codeforces.com/api/user.status?handle="+handle);
			
			connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			//connection.setRequestProperty("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImRlZTQ0MmQ3LWMxMTctNGM3Ni1iZDdkLWEwNTFkZjVlNmM5ZSIsImlhdCI6MTYyMTUyNjQ3Nywic3ViIjoiZGV2ZWxvcGVyL2NhOTJlMGQ2LTIwN2MtNDUyYy00MTYwLTZjZTJmMjliMzFkMSIsInNjb3BlcyI6WyJjbGFzaCJdLCJsaW1pdHMiOlt7InRpZXIiOiJkZXZlbG9wZXIvc2lsdmVyIiwidHlwZSI6InRocm90dGxpbmcifSx7ImNpZHJzIjpbIjEwMy4xNDguNzQuODIiXSwidHlwZSI6ImNsaWVudCJ9XX0.o7TiXBdD6RPTOlwOJ8Z44cSV4up_v0vzXSczEsL0jHQoxRHCcubmCJbBg3HeJF86Ny32nU9og1Gzv3qESDlhYw");
			int st = connection.getResponseCode();
			//System.out.print(st);
			
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
			System.out.println(res.toString().substring(1, 300));
			//JSONObject job = new JSONObject(res.toString());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.disconnect();
		}
		System.out.println("length = "+ res.length());
		
	
		JSONObject object = new JSONObject(res.toString());
		//String sss = object.getString("status");
		//if(sss.equals("FAILED")) {
		//	JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
		//	return;
		//}
			
		JSONArray arr = object.getJSONArray("result");
		//System.out.print("arr.length() - ");
		//System.out.println(arr.length());
		for(int i=0; i<arr.length(); i++) {
			JSONObject subobj = arr.getJSONObject(i);
			if(subobj.has("verdict") && subobj.getString("verdict").equals("OK")) {
				//System.out.println("in");
				
				JSONObject proobj = subobj.getJSONObject("problem");
				if(proobj.has("name")) {
					ids.add(proobj.getString("name"));
				}
				if(proobj.has("rating")) {
					Integer rat = proobj.getInt("rating");
					if(ratingwise.containsKey(rat)) {
						Integer val = ratingwise.get(rat); 
						val += 1;
						ratingwise.remove(rat);
						ratingwise.put(rat, val);
					}
					else {
						//System.out.println("hi");
						ratingwise.put(rat, 1);
					}
				}
				if(proobj.has("index")) {
					String ind = proobj.getString("index");
					ind = ind.substring(0, 1);
					if(abcwise.containsKey(ind)) {
						Integer val = abcwise.get(ind);
						val += 1;
						abcwise.remove(ind);
						abcwise.put(ind, val);
					}
					else {
						abcwise.put(ind, 1);
					}
				}
				if(proobj.has("tags")) {
					JSONArray str = proobj.getJSONArray("tags");
					for(int ii=0; ii<str.length(); ii++) {
						//System.out.print(str.getString(ii));
						String key = str.getString(ii);
						if(tagwise.containsKey(key)) {
							Integer val = tagwise.get(key);
							val += 1;
							tagwise.remove(key);
							tagwise.put(key, val);
						}
						else {
							tagwise.put(key, 1);
						}
					}
				}
			}
		}
		TreeMap<Integer,Integer> tratingwise=new  TreeMap<Integer,Integer> (ratingwise);  
		for (Map.Entry<Integer, Integer> me : tratingwise.entrySet()) {
           //System.out.print(me.getKey() + ":");
           // System.out.println(me.getValue());
			ratingdata.setValue(me.getValue(), "Rating", me.getKey());
        }
		TreeMap<String,Integer> ttagwise=new  TreeMap<String,Integer> (tagwise);
		for (Map.Entry<String, Integer> me : ttagwise.entrySet()) {
            //System.out.print(me.getKey() + ":");
            //System.out.println(me.getValue());
			tagdata.setValue(me.getKey(), me.getValue());
        }
		TreeMap<String,Integer> tabcwise=new  TreeMap<String,Integer> (abcwise);  
		for (Map.Entry<String, Integer> me : tabcwise.entrySet()) {
            //System.out.print(me.getKey() + ":");
            //System.out.println(me.getValue());
			abcdata.setValue(me.getValue(), "Index", me.getKey());
        }
		processing.dispose();
		//System.out.println(ids.size());
	}
	
	
	ProfilePage() throws IOException{
		
		processing.setBounds(830, 450, 232, 257);
		processing.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//processing.setResizable(false);
		processing.setAlwaysOnTop(true);
		processing.setLayout(null);
		processlbl.setBounds(0, 0, 222, 247);
		processlbl.setIcon(icon2);
		processlbl.setBackground(Color.yellow);
		processlbl.setOpaque(true);
		processing.add(processlbl);
		processing.setVisible(true);
		
		api  = "https://codeforces.com/api/user.info?handles=";
		try {
			br = new BufferedReader(new FileReader("Data/nowhandle.txt"));
			handle = br.readLine();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			processing.dispose();
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		cfprofilelink += handle;
		try {
			URL url = new URL(api+handle);
			connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			
			int st = connection.getResponseCode();
			String line;
			if(st > 299) {
				processing.dispose();
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
				processing.dispose();
				JOptionPane.showMessageDialog(null, "Possible Causes:\n1. Internet not connected\n2. Codeforces server down", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				
				JSONArray arr = jobject.getJSONArray("result");
				JSONObject obj = arr.getJSONObject(0);
				url = new URL(obj.getString("titlePhoto"));
				BufferedImage image = ImageIO.read(url);
				BufferedImage output = new BufferedImage(150, 150, image.getType());
				
				Graphics2D g2d = output.createGraphics();
				g2d.drawImage(image, 0, 0, 150, 150, null);
				g2d.dispose();
				imgi = new ImageIcon(output);
				
				if(obj.has("rating")) {
					cfrating = obj.getInt("rating");
				}
				else {
					cfrating = 0;
				}
				if(obj.has("firstName")) {
					fname = obj.getString("firstName");
				}
				else {
					fname = "";
				}
				if(obj.has("lastName")) {
					lname = obj.getString("lastName");
				}
				else {
					lname = "";
				}
			}
		} catch (IOException  e) {
			// TODO Auto-generated catch block
			processing.dispose();
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/rating.txt"));
			bw.write(Integer.toString(cfrating));
			bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("excp");
			processing.dispose();
			e1.printStackTrace();
		}
		
		JLabel imglabel = new JLabel(imgi);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(250, 150, 1500, 800);
		this.setLayout(null);
		this.setTitle("Profile");
		this.setVisible(true);
		this.setIconImage(icon.getImage());
		this.getContentPane().setBackground(new Color(0x1f253d));
		
		if(cfrating < 1200) {
			ratingcol = Color.gray;
			rankname = "Newbie";
		}
		else if(cfrating>=1200 && cfrating < 1400) {
			ratingcol = Color.green;
			rankname = "Pupil";
		}
		else if(cfrating>=1400 && cfrating < 1600) {
			ratingcol = Color.cyan;
			rankname = "Specialist";
		}
		else if(cfrating >= 1600 && cfrating<1900) {
			ratingcol = Color.blue;
			rankname = "Expert";
		}
		else if(cfrating>=1900 && cfrating<2100) {
			ratingcol = new Color(0xbc00ad);
			rankname = "Candidate Master";
		}
		else if(cfrating >= 2100 && cfrating < 2400) {
			ratingcol = Color.orange;
			rankname = "Master";
			if(cfrating >= 2300)
				rankname = "International Master";
		}
		else if(cfrating >= 2400 && cfrating < 3000) {
			ratingcol = Color.red;
			rankname = "Grandmaster";
			if(cfrating >= 2600)
				rankname = "International Grandmaster";
		}
		else if (cfrating >= 3000) {
			ratingcol = Color.black;
			rankname = "Legendary Grandmaster";
		}
		
		headerpanel.setBounds(30, 30, 1440, 100);
		headerpanel.setBackground(new Color(0x394165));
		headerpanel.setLayout(null);
		dashboardbtn.setBounds(20, 20, 180, 60);
		dashboardbtn.setBackground(new Color(0x394165));
		dashboardbtn.setForeground(Color.white);
		dashboardbtn.setFont(new Font("Arial", Font.PLAIN, 24));
		dashboardbtn.setBorder(null);
		dashboardbtn.setFocusable(false);
		dashboardbtn.addMouseListener(this);
		practicebtn.setBounds(200, 20, 180, 60);
		practicebtn.setBackground(new Color(0x394165));
		practicebtn.setForeground(Color.white);
		practicebtn.setFont(new Font("Arial", Font.PLAIN, 24));
		practicebtn.setBorder(null);
		practicebtn.setFocusable(false);
		practicebtn.addMouseListener(this);
		helpbtn.setBounds(380, 20, 180, 60);
		helpbtn.setBackground(new Color(0x394165));
		helpbtn.setForeground(Color.white);
		helpbtn.setFont(new Font("Arial", Font.PLAIN, 24));
		helpbtn.setBorder(null);
		helpbtn.setFocusable(false);
		helpbtn.addMouseListener(this);
		logoutbtn.setBounds(1230, 20, 180, 60);
		logoutbtn.setBackground(new Color(0x394165));
		logoutbtn.setForeground(Color.white);
		logoutbtn.setFont(new Font("Arial", Font.PLAIN, 24));
		logoutbtn.setBorder(null);
		logoutbtn.setFocusable(false);
		logoutbtn.addMouseListener(this);
		
		
		leftpanel.setBounds(30, 165, 450, 570);
		leftpanel.setBackground(new Color(0x1f253d));
		leftpanel.setLayout(null);
		visuals.setBounds(0, 0, 450, 80);
		visuals.setBackground(new Color(0x10a8ab));
		visuals.setLayout(null);
		visual.setForeground(Color.white);
		visual.setFont(new Font("Arial", Font.PLAIN, 30));
		visual.setBounds(170, 0, 300, 80);
		rating.setBounds(0, 80, 450, 94);
		rating.setBackground(new Color(0x394165));
		rating.setForeground(Color.white);
		rating.setFont(new Font("Arial", Font.PLAIN, 30));
		rating.setFocusable(false);
		rating.setBorder(null);
		rating.addMouseListener(this);
		tags.setBounds(0, 178, 450, 94);
		tags.setBackground(new Color(0x394165));
		tags.setForeground(Color.white);
		tags.setFont(new Font("Arial", Font.PLAIN, 30));
		tags.setFocusable(false);
		tags.setBorder(null);
		tags.addMouseListener(this);
		abc.setBounds(0, 276, 450, 94);
		abc.setBackground(new Color(0x394165));
		abc.setForeground(Color.white);
		abc.setFont(new Font("Arial", Font.PLAIN, 30));
		abc.setFocusable(false);
		abc.setBorder(null);
		abc.addMouseListener(this);
		time.setBounds(0, 374, 450, 94);
		time.setBackground(new Color(0x394165));
		time.setForeground(Color.white);
		time.setFont(new Font("Arial", Font.PLAIN, 30));
		time.setFocusable(false);
		time.setBorder(null);
		time.addMouseListener(this);
		
		middlepanel.setBounds(525, 165, 450, 570);
		//middlepanel.setBackground(new Color(0x10a8ab));
		middlepanel.setLayout(null);
		imglabel.setBounds(125, 70, 200, 200);
		
		imglabel.setBorder(BorderFactory.createLineBorder(ratingcol, 10));
		name.setBounds(0, 280, 450, 50);
		name.setText(fname+" "+lname);
		name.setFont(new Font("Arial", Font.PLAIN, 30));
		name.setForeground(ratingcol);
		name.setHorizontalAlignment(JLabel.CENTER);
		//name.setBorder(BorderFactory.createLineBorder(Color.white, 3));
		
		showhandle.setBounds(0, 330, 450, 50);
		showhandle.setText(rankname);
		showhandle.setFont(new Font("Arial", Font.PLAIN, 30));
		showhandle.setForeground(ratingcol);
		showhandle.setHorizontalAlignment(JLabel.CENTER);
		Rating.setBounds(140, 380, 150, 50);
		Rating.setFont(new Font("Arial", Font.PLAIN, 30));
		sRating.setBounds(250, 380, 150, 50);
		sRating.setText(Integer.toString(cfrating));
		sRating.setFont(new Font("Arial", Font.PLAIN, 30));
		sRating.setForeground(ratingcol);
		
		cfpro.setBounds(0, 480, 225, 90);
		cfpro.setBackground(new Color(0x394165));
		cfpro.setForeground(Color.white);
		cfpro.setFont(new Font("Arial", Font.PLAIN, 30));
		cfpro.setFocusable(false);
		cfpro.setBorder(null);
		cfpro.addMouseListener(this);
		
		cftr.setBounds(225, 480, 225, 90);
		cftr.setBackground(new Color(0x394165));
		cftr.setForeground(Color.white);
		cftr.setFont(new Font("Arial", Font.PLAIN, 30));
		cftr.setFocusable(false);
		cftr.setBorder(null);
		cftr.addMouseListener(this);
		
		rightpanel.setBounds(1020, 165, 450, 570);
		rightpanel.setBackground(new Color(0x394165));
		

		this.add(headerpanel);
		headerpanel.add(dashboardbtn);
		headerpanel.add(practicebtn);
		//headerpanel.add(helpbtn);
		headerpanel.add(logoutbtn);
		
		this.add(leftpanel);
		leftpanel.add(visuals);
		leftpanel.add(rating);
		leftpanel.add(tags);
		leftpanel.add(abc);
		//leftpanel.add(time);
		visuals.add(visual);
		
		this.add(middlepanel);
		middlepanel.add(name);
		middlepanel.add(imglabel);
		middlepanel.add(showhandle);
		middlepanel.add(Rating);
		middlepanel.add(sRating);
		middlepanel.add(cfpro);
		middlepanel.add(cftr);
		
		this.add(rightpanel);
		rightpanel.add(totalsolved);
		rightpanel.setLayout(null);
		
		makingdata(handle);
		Integer tslvd = ids.size();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/solvednames.txt"));
			for(String x: ids) {
				//System.out.println(x);
				bw.write(x+"\n");
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			processing.dispose();
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int tp;
		try {
			BufferedReader br = new BufferedReader(new FileReader("Data/"+handle+"/totalpoint.txt"));
			tp = Integer.parseInt(br.readLine());
			br.close();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			processing.dispose();
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String tslv = "Total Solved: " + Integer.toString(tslvd);
		totalsolved.setText(tslv);
		totalsolved.setBounds(10, 40, 450, 50);
		totalsolved.setForeground(Color.white);
		totalsolved.setFont(new Font("Arial", Font.PLAIN, 30));
		totalsolved.setHorizontalAlignment(JLabel.CENTER);

		totalpoint.setText(tslv);
		totalpoint.setBounds(10, 140, 450, 50);
		totalpoint.setForeground(Color.white);
		totalpoint.setFont(new Font("Arial", Font.PLAIN, 30));
		totalpoint.setHorizontalAlignment(JLabel.CENTER);
		totalpoint.setText("Total Gained Point: "+Integer.toString(tp));
		rightpanel.add(totalsolved);
		rightpanel.add(totalpoint);
	}

	public static void main(String[] args) throws IOException {
		//new ProfilePage();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == dashboardbtn) {
			this.dispose();
			new DashboardPage();
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
		if(e.getSource() == rating) {
			//ratingdata.addValue(40, "Rating", "100");
			//ratingdata.addValue(60, "Rating", "200");
			//ratingdata.addValue(90, "Rating", "300");
			JFreeChart chart = ChartFactory.createBarChart("Solved by Rating", "Rating", "Number of Solves", ratingdata, PlotOrientation.VERTICAL, true, true, false);
			CategoryPlot plot = new CategoryPlot();
			plot.setRangeGridlinePaint(Color.black);
			
			ChartFrame cfrm = new ChartFrame("Visual", chart, true);
			cfrm.setVisible(true);
			cfrm.setBounds(200, 350, 1800, 500);
		}
		if(e.getSource() == abc) {
			//ratingdata.addValue(40, "Rating", "100");
			//ratingdata.addValue(60, "Rating", "200");
			//ratingdata.addValue(90, "Rating", "300");
			JFreeChart chart = ChartFactory.createBarChart("Solved by Index", "Index", "Number of Solves", abcdata, PlotOrientation.VERTICAL, true, true, false);
			CategoryPlot plot = new CategoryPlot();
			plot.setRangeGridlinePaint(Color.black);
			
			ChartFrame cfrm = new ChartFrame("Visual", chart, true);
			cfrm.setVisible(true);
			cfrm.setBounds(400, 350, 1000, 500);
		}
		if(e.getSource() == tags) {
			//ratingdata.addValue(40, "Rating", "100");
			//ratingdata.addValue(60, "Rating", "200");
			//ratingdata.addValue(90, "Rating", "300");
			//JFreeChart chart = ChartFactory.createBarChart("Solved by Index", "Index", "Number of Solves", abcdata, PlotOrientation.VERTICAL, true, true, false);
			JFreeChart chart = ChartFactory.createPieChart("Solved by Tags", tagdata, true, true, false);
			PiePlot plot = (PiePlot)chart.getPlot();
			//plot.setForegroundAlpha(TOP_ALIGNMENT); 
			
			ChartFrame cfrm = new ChartFrame("Visual", chart );
			cfrm.setVisible(true);
			cfrm.setBounds(380, 100, 1250, 1000);
		}
		if(e.getSource() == cfpro) {
			Desktop d = Desktop.getDesktop();
			try {
				d.browse(new URI(cfprofilelink));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Sorry, Please Try Again, Check Internet", "Error", JOptionPane.ERROR_MESSAGE);
				
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Sorry, Please Try Again, Check Internet", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		}
		if(e.getSource() == cftr) {
			Desktop d = Desktop.getDesktop();
			try {
				d.browse(new URI("https://cf-tracker.tech/"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Sorry, Please Try Again, Check Internet", "Error", JOptionPane.ERROR_MESSAGE);
				
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Sorry, Please Try Again, Check Internet", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		}
		if(e.getSource() == logoutbtn) {
			new LoginPage();
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
		if(e.getSource() == dashboardbtn) {
			dashboardbtn.setBackground(new Color(0x1f253d));
		}
		if(e.getSource() == practicebtn) {
			practicebtn.setBackground(new Color(0x1f253d));
		}
		if(e.getSource() == helpbtn) {
			helpbtn.setBackground(new Color(0x1f253d));
		}
		if(e.getSource() == logoutbtn) {
			logoutbtn.setBackground(Color.red);
		}
		if(e.getSource() == rating) {
			rating.setBackground(new Color(0x1f253d));
		}
		if(e.getSource() == tags) {
			tags.setBackground(new Color(0x1f253d));
		}
		if(e.getSource() == abc) {
			abc.setBackground(new Color(0x1f253d));
		}
		if(e.getSource() == time) {
			time.setBackground(new Color(0x1f253d));
		}
		if(e.getSource() == cfpro) {
			cfpro.setBackground(new Color(0x1f253d));
		}
		if(e.getSource() == cftr) {
			cftr.setBackground(new Color(0x1f253d));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == dashboardbtn) {
			dashboardbtn.setBackground(new Color(0x394165));
		}
		if(e.getSource() == practicebtn) {
			practicebtn.setBackground(new Color(0x394165));
		}
		if(e.getSource() == helpbtn) {
			helpbtn.setBackground(new Color(0x394165));
		}
		if(e.getSource() == logoutbtn) {
			logoutbtn.setBackground(new Color(0x394165));
		}
		if(e.getSource() == rating) {
			rating.setBackground(new Color(0x394165));
		}
		if(e.getSource() == tags) {
			tags.setBackground(new Color(0x394165));
		}
		if(e.getSource() == abc) {
			abc.setBackground(new Color(0x394165));
		}
		if(e.getSource() == time) {
			time.setBackground(new Color(0x394165));
		}
		if(e.getSource() == cfpro) {
			cfpro.setBackground(new Color(0x394165));
		}
		if(e.getSource() == cftr) {
			cftr.setBackground(new Color(0x394165));
		}
	}

}
