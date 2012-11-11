import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class outside extends JFrame implements ActionListener,Runnable {

	JButton next;
	JButton dispose;
	JButton open;
	JButton out;
	JButton times;
	JButton outfileb;
	JButton finish;
	JLabel counter;
	JLabel memo;
	private FileChoose fchoose = new FileChoose();

	outside(){
		JFrame frame = new JFrame();
		frame.setTitle("らんだむぷれいや～");
		frame.setVisible(true);
		frame.setBounds(100, 100, 300, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(new BorderLayout());
		JPanel textpanel = new JPanel();
		textpanel.setLayout(new BorderLayout());
		JPanel memopanel = new JPanel();
		memopanel.setLayout(new BorderLayout());
		JPanel buttonpanel = new JPanel();
		buttonpanel.setLayout(new FlowLayout());

		next = new JButton("次へ");
		dispose = new JButton("終わる");
		open = new JButton("ディレクトリを開く");
		out = new JButton("out");
		outfileb = new JButton("outfile設定");
		times = new JButton("回数設定");
		finish = new JButton("finish設定");
		counter = new JLabel("らんだむぷれいや～",JLabel.CENTER);
		memo = new JLabel("フィニッシュ出現まで100回",JLabel.CENTER);

		next.addActionListener(this);
		dispose.addActionListener(this);
		open.addActionListener(this);
		out.addActionListener(this);
		outfileb.addActionListener(this);
		times.addActionListener(this);
		finish.addActionListener(this);

		buttonpanel.add(next);
		buttonpanel.add(open);
		buttonpanel.add(times);
		buttonpanel.add(outfileb);
		buttonpanel.add(out);
		buttonpanel.add(finish);
		buttonpanel.add(dispose);
		textpanel.add(counter);
		memopanel.add(memo);
		mainpanel.add(textpanel,BorderLayout.NORTH);
		mainpanel.add(buttonpanel,BorderLayout.CENTER);
		mainpanel.add(memopanel,BorderLayout.SOUTH);
		frame.add(mainpanel);
	}

	public void start() {
	}
	public void run() {
	}

	public void nextsong(String playfile){
		if(!fchoose.getIsfinish()&&!fchoose.getIsout()){
			if(playfile.equals("")){
				nextsong(fchoose.getplayFile());
			}
			else{
				try {
					myplayer.Getmthread().close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if(fchoose.finishfile(playfile)){
					counter.setText("フィニッシュ");
					fchoose.setIsfinish(true);
				}
				else{
					fchoose.setCounter(fchoose.getCounter()+1);
					counter.setText(fchoose.getCounter() + "回目 ： " +playfile);
				}
				myplayer.Setmthread(new playmethod(fchoose.getPath()+"\\"+playfile));
				myplayer.Getmthread().start();
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		//イベントがbuttonで起こっていたら…
		if (e.getSource() == next) {
			nextsong(fchoose.getplayFile());
		}
		if (e.getSource()==open) { // ボタンクリックイベント
			if(fchoose.getlist()==JFileChooser.APPROVE_OPTION){
				nextsong(fchoose.getplayFile());
			}
		}
		if (e.getSource()==out) { // ボタンクリックイベント
			if(fchoose.getOutfile()!=null&&fchoose.getOutfile().exists()&&!fchoose.getIsout()){
				try {
					myplayer.Getmthread().close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				myplayer.Setmthread(new playmethod(fchoose.getOut()));
				myplayer.Getmthread().start();
				fchoose.setIsout(true);
				counter.setText("アウト");
			}
		}
		if (e.getSource()==outfileb) { // ボタンクリックイベント
			fchoose.OutfileChoose();
		}
		if (e.getSource()==times) { // ボタンクリックイベント
			InputDialog id = new InputDialog();
			fchoose.setkaisu(Integer.parseInt(id.getValue()),false);
			memo.setText("フィニッシュ出現まで"+Integer.parseInt(id.getValue())+"回");
		}
		if (e.getSource()==finish) { // ボタンクリックイベント
			fchoose.finishChoose();
		}
		if (e.getSource()==dispose) { // ボタンクリックイベント
			dispose(); // アプリケーションフレーム破棄
			System.exit(0); // 終了
		}
	}
}