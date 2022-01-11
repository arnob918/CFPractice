import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.*;

import org.json.*;

public class CurrentPracticePage extends JFrame implements MouseListener{
	
	JPanel progresspanel = new JPanel();
	JProgressBar probar = new JProgressBar();
	int targetpoint, gainedpoint;
	String handle;
	JLabel gplabel = new JLabel();
	JLabel tplabel = new JLabel();
	JLabel timerem = new JLabel();
	JButton dashboardbtn = new JButton("Dashboard");
	JButton finish = new JButton("Finish");
	

	JPanel problempanel = new JPanel();
	JLabel cursol = new JLabel("Currently Solving");
	JLabel name = new JLabel();
	JLabel points = new JLabel();
	JButton open = new JButton("Open");
	JButton solved = new JButton("Solved");
	JButton skip = new JButton("Skip");
	JButton re = new JButton("Refresh");
	String jsonarray, problemname, index;
	int point, contestid, currat;
	String link;
	JSONArray jarr;
	int ind;
	int len;
	long endtime;
	
	CurrentPracticePage(){
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("Data/nowhandle.txt"));
			handle = br.readLine();
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			BufferedReader bw = new BufferedReader(new FileReader("Data/"+handle+"/tagertpoint.txt"));
			targetpoint = Integer.parseInt(bw.readLine());
			bw.close();
		} catch (IOException e4) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			BufferedReader bw = new BufferedReader(new FileReader("Data/"+handle+"/gainedpoint.txt"));
			gainedpoint = Integer.parseInt(bw.readLine());
			bw.close();
		} catch (IOException e4) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//gainedpoint = 10;
		try {
			BufferedReader bw = new BufferedReader(new FileReader("Data/"+handle+"/jsonarray.txt"));
			jsonarray = bw.readLine();
			bw.close();
		} catch (IOException e4) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			BufferedReader bw = new BufferedReader(new FileReader("Data/"+handle+"/rating.txt"));
			currat = Integer.parseInt(bw.readLine());
			bw.close();
		} catch (IOException e4) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		currat/=100;
		String indx ;
		try {
			BufferedReader bw = new BufferedReader(new FileReader("Data/"+handle+"/problemname.txt"));
			indx = bw.readLine();
			bw.close();
		} catch (IOException e4) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		jarr = new JSONArray(jsonarray);
		len = jarr.length();
		//System.out.println(len);
		
		if(indx.equals("**")) {
			ind = (int) Math.floor(Math.random()*len);
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/problemname.txt"));
				bw.write(Integer.toString(ind));
				bw.close();
			} catch (IOException e4) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else {
			ind = Integer.parseInt(indx);
		}
		//System.out.println(jsonarray);
		JSONObject  obj = jarr.getJSONObject(ind);
		problemname = obj.getString("name");
		int rat = obj.getInt("rating");
		rat/=100;
		
		contestid = obj.getInt("contestId");
		index = obj.getString("index");
		
		if(rat >= currat) {
			point = 2;
			point += (rat-currat)*5;
		}
		else {
			point = 1;
		}
		link = "https://codeforces.com/contest/"+Integer.toString(contestid)+"/problem/"+index;

		System.out.println("hi");
		this.setBounds(600, 150, 700, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setTitle("Current Practice");
		this.getContentPane().setBackground(new Color(0x1f253d));
		

		dashboardbtn.setBounds(20, 20, 320, 60);
		dashboardbtn.setBackground(new Color(0x394165));
		dashboardbtn.setForeground(Color.white);
		dashboardbtn.setFont(new Font("Arial", Font.PLAIN, 24));
		dashboardbtn.setBorder(null);
		dashboardbtn.setFocusable(false);
		dashboardbtn.addMouseListener(this);
		
		finish.setBounds(360, 20, 320, 60);
		finish.setBackground(Color.red);
		finish.setForeground(Color.white);
		finish.setFont(new Font("Arial", Font.PLAIN, 24));
		finish.setBorder(null);
		finish.setFocusable(false);
		finish.addMouseListener(this);
		
		progresspanel.setBounds(20, 120, 660, 300);
		progresspanel.setBackground(new Color(0x394165));
		progresspanel.setLayout(null);
		
		probar.setMaximum(targetpoint);
		probar.setBounds(20, 20, 620, 80);
		probar.setStringPainted(true);
		probar.setValue(Math.max(gainedpoint,0));
		probar.setFont(new Font("Arial", Font.PLAIN, 28));
		probar.setForeground(Color.green);
		
		gplabel.setText("Gained point: "+Integer.toString(gainedpoint));
		gplabel.setFont(new Font("Arial", Font.PLAIN, 28));
		gplabel.setForeground(Color.white);
		gplabel.setBounds(20, 120, 310, 35);
		
		tplabel.setText("Target point: "+Integer.toString(targetpoint));
		tplabel.setFont(new Font("Arial", Font.PLAIN, 28));
		tplabel.setForeground(Color.white);
		tplabel.setBounds(390, 120, 310, 35);
		
		//timerem.setText("Target point: "+Integer.toString(targetpoint));
		timerem.setFont(new Font("Arial", Font.PLAIN, 35));
		timerem.setForeground(Color.green);
		timerem.setBackground(Color.black);
		timerem.setBounds(20, 200, 460, 80);
		timerem.setOpaque(true);
		
		
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
		long time = endtime-now;
		if(time < 0) {
			if(gainedpoint < targetpoint) {
				JOptionPane.showMessageDialog(null, "Sorry, you failed in this session", "Error", JOptionPane.ERROR_MESSAGE);
				new DashboardPage();
				this.dispose();
			}
		}
		//String date = new java.text.SimpleDateFormat("hh:mm:ss").format(new java.util.Date ((time)));
		long h = time/3600;
		time=time%3600;
		long m = time/60;
		long s = time%60;
		String date = " Remaing Time: "+Long.toString(h)+":"+Long.toString(m)+":"+Long.toString(s);
		timerem.setText(date);
		re.setBounds(490, 210, 100, 60);
		re.setBackground(new Color(0x394165));
		re.setForeground(Color.white);
		re.setFont(new Font("Arial", Font.PLAIN, 20));
		re.setFocusable(false);
		re.setBorder(null);
		re.addMouseListener(this);
		
		problempanel.setBounds(20, 440, 660, 305);
		problempanel.setBackground(new Color(0x394165));
		problempanel.setLayout(null);
		
		cursol.setBounds(0, 0, 660, 40);
		cursol.setHorizontalAlignment(SwingConstants.CENTER);
		cursol.setForeground(Color.white);
		cursol.setFont(new Font("Arial", Font.PLAIN, 24));
		
		name.setBounds(0, 60, 660, 40);
		name.setText(problemname);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setForeground(Color.green);
		name.setFont(new Font("Arial", Font.PLAIN, 35));
		
		points.setBounds(0,120, 660, 40);
		points.setText("Points: "+Integer.toString(point));
		points.setHorizontalAlignment(SwingConstants.CENTER);
		points.setForeground(Color.red);
		points.setFont(new Font("Arial", Font.PLAIN, 35));
		
		open.setBounds(0, 215, 220, 90);
		open.setBackground(new Color(0x394165));
		open.setForeground(Color.white);
		open.setFont(new Font("Arial", Font.PLAIN, 30));
		open.setFocusable(false);
		open.setBorder(null);
		open.addMouseListener(this);
		
		solved.setBounds(220, 215, 220, 90);
		solved.setBackground(new Color(0x394165));
		solved.setForeground(Color.white);
		solved.setFont(new Font("Arial", Font.PLAIN, 30));
		solved.setFocusable(false);
		solved.setBorder(null);
		solved.addMouseListener(this);
		
		skip.setBounds(440, 215, 220, 90);
		skip.setBackground(new Color(0x394165));
		skip.setForeground(Color.white);
		skip.setFont(new Font("Arial", Font.PLAIN, 30));
		skip.setFocusable(false);
		skip.setBorder(null);
		skip.addMouseListener(this);
		
		progresspanel.add(probar);
		progresspanel.add(gplabel);
		progresspanel.add(tplabel);
		progresspanel.add(timerem);
		progresspanel.add(re);
		this.add(progresspanel);
		
		problempanel.add(cursol);
		problempanel.add(name);
		problempanel.add(points);
		problempanel.add(open);
		problempanel.add(solved);
		problempanel.add(skip);
		this.add(problempanel);
		this.add(dashboardbtn);
		this.add(finish);
		
		this.setVisible(true);

		

		System.out.println("hi");
		//gettime();
	}
	
	/*public void gettime() {
		while(true) {
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
			long time = endtime-now;
			String date = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date (time*1000));
			date = " Remaing Time: "+date;
			timerem.setText(date);
		}
	}*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CurrentPracticePage();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == dashboardbtn) {
			this.dispose();
			new DashboardPage();
		}
		if(e.getSource() == finish) {
			int choise = JOptionPane.showConfirmDialog(null, "If you finish all data of this session will be lost\n Do you want to Finish?", "Attention", JOptionPane.YES_NO_OPTION);
			if(choise == JOptionPane.YES_OPTION) {
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/endtime.txt"));
					bw.write("0");
					bw.close();
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/starttime.txt"));
					bw.write("0");
					bw.close();
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				this.dispose();
				new DashboardPage();
			}
		}
		if(e.getSource() == re) {
			if(gainedpoint >= targetpoint) {
				JOptionPane.showMessageDialog(null, "Congratulation, you passed this session", "Error", JOptionPane.PLAIN_MESSAGE);
				new DashboardPage();
				this.dispose();
			}
			long now = System.currentTimeMillis()/1000; 
			long time = endtime-now;
			if(time < 0) {
				if(gainedpoint < targetpoint) {
					JOptionPane.showMessageDialog(null, "Sorry, you failed in this session", "Error", JOptionPane.ERROR_MESSAGE);
					new DashboardPage();
					this.dispose();
				}
			}
			long h = time/3600;
			time=time%3600;
			long m = time/60;
			long s = time%60;
			String date = " Remaing Time: "+Long.toString(h)+":"+Long.toString(m)+":"+Long.toString(s);
			
			timerem.setText(date);
		}
		if(e.getSource() == open) {
			Desktop d = Desktop.getDesktop();
			try {
				d.browse(new URI(link));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Sorry, Please Try Again, Check Internet", "Error", JOptionPane.ERROR_MESSAGE);
				
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Sorry, Please Try Again, Check Internet", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		}
		if(e.getSource() == solved) {
			HttpURLConnection connection = null;
			BufferedReader reader;
			String line;
			StringBuffer res = new StringBuffer();
			String api = "https://codeforces.com/api/contest.status?contestId="+Integer.toString(contestid)+"&handle="+handle;
			//System.out.println(api);
			try {
				URL url = new URL(api);
				
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
				//System.out.println(res.toString());
				JSONObject jobject = new JSONObject(res.toString());
				String stat = jobject.getString("status");
				//System.out.println(stat);
				if(stat.equals("FAILED")) {
					JOptionPane.showMessageDialog(null, "Possible Causes:\n1. Internet not connected\n2. Codeforces server down", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					JSONArray arr = jobject.getJSONArray("result");
					int fl = 0;
					for(int i=0; i<arr.length(); i++) {
						JSONObject obj = arr.getJSONObject(i);
						JSONObject pobj = obj.getJSONObject("problem");
						if(index.equals(pobj.getString("index")) && obj.getString("verdict").equals("OK")) {
							fl = 1;
							break;
						}
					}
					if(fl==0) {
						JOptionPane.showMessageDialog(null, "You haven't solved this problem yet", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						int tp = 0;
						try {
							BufferedReader bw = new BufferedReader(new FileReader("Data/"+handle+"/totalpoint.txt"));
							tp = Integer.parseInt(bw.readLine());
							bw.close();
						} catch (IOException e4) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						tp += point;
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/totalpoint.txt"));
							bw.write(Integer.toString(tp));
							bw.close();
						} catch (IOException e4) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						if(gainedpoint+point >= targetpoint) {
							JOptionPane.showMessageDialog(null, "Congratulation, you passed this session", "Error", JOptionPane.PLAIN_MESSAGE);
							new DashboardPage();
							this.dispose();
						}
						jarr.remove(ind);
						len--;
						if(len == 0) {
							;;;;
						}
						ind = (int) Math.floor(Math.random()*len);
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/problemname.txt"));
							bw.write(Integer.toString(ind));
							bw.close();
						} catch (IOException e4) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						JSONObject  obj = jarr.getJSONObject(ind);
						problemname = obj.getString("name");
						int rat = obj.getInt("rating");
						rat/=100;
						
						contestid = obj.getInt("contestId");
						index = obj.getString("index");
						
						if(rat >= currat) {
							point = 2;
							point += (rat-currat)*5;
						}
						else {
							point = 1;
						}
						link = "https://codeforces.com/contest/"+Integer.toString(contestid)+"/problem/"+index;
						
						name.setText(problemname);
						points.setText("Points: "+Integer.toString(point));
						
						gainedpoint += point;
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/gainedpoint.txt"));
							bw.write(Integer.toString(gainedpoint));
							bw.close();
						} catch (IOException e4) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						gplabel.setText("Gained point: "+Integer.toString(gainedpoint));
						probar.setValue(Math.max(gainedpoint,0));
					}
				}
				
			} catch (MalformedURLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			connection.disconnect();
		}
		if(e.getSource() == skip) {
			int choise = JOptionPane.showConfirmDialog(null, "If you skip this problem you will lose 1 point\n Do you want to skip?", "Attention", JOptionPane.YES_NO_OPTION);
			if(choise == JOptionPane.YES_OPTION) {
				int tp = 0;
				try {
					BufferedReader bw = new BufferedReader(new FileReader("Data/"+handle+"/totalpoint.txt"));
					tp = Integer.parseInt(bw.readLine());
					bw.close();
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				tp -= 1;
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/totalpoint.txt"));
					bw.write(Integer.toString(tp));
					bw.close();
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				jarr.remove(ind);
				len--;
				if(len == 0) {
					;;;;
				}
				ind = (int) Math.floor(Math.random()*len);
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/problemname.txt"));
					bw.write(Integer.toString(ind));
					bw.close();
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JSONObject  obj = jarr.getJSONObject(ind);
				problemname = obj.getString("name");
				int rat = obj.getInt("rating");
				rat/=100;
				
				contestid = obj.getInt("contestId");
				index = obj.getString("index");
				
				if(rat >= currat) {
					point = 2;
					point += (rat-currat)*5;
				}
				else {
					point = 1;
				}
				link = "https://codeforces.com/contest/"+Integer.toString(contestid)+"/problem/"+index;
				
				name.setText(problemname);
				points.setText("Points: "+Integer.toString(point));
				
				gainedpoint -= 1;
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter("Data/"+handle+"/gainedpoint.txt"));
					bw.write(Integer.toString(gainedpoint));
					bw.close();
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Sorry, Internet not connected or Codeforces server is down", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				gplabel.setText("Gained point: "+Integer.toString(gainedpoint));
				probar.setValue(Math.max(gainedpoint,0));
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
		if(e.getSource() == open) {
			open.setBackground(new Color(0x1f253d));
		}
		if(e.getSource() == solved) {
			solved.setBackground(Color.green);
		}
		if(e.getSource() == skip) {
			skip.setBackground(Color.red);
		}
		if(e.getSource() == re) {
			re.setBackground(Color.red);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == open) {
			open.setBackground(new Color(0x394165));
		}
		if(e.getSource() == solved) {
			solved.setBackground(new Color(0x394165));
		}
		if(e.getSource() == skip) {
			skip.setBackground(new Color(0x394165));
		}
		if(e.getSource() == re) {
			re.setBackground(new Color(0x394165));
		}
	}

}
