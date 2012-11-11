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
		frame.setTitle("��񂾂ނՂꂢ��`");
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

		next = new JButton("����");
		dispose = new JButton("�I���");
		open = new JButton("�f�B���N�g�����J��");
		out = new JButton("out");
		outfileb = new JButton("outfile�ݒ�");
		times = new JButton("�񐔐ݒ�");
		finish = new JButton("finish�ݒ�");
		counter = new JLabel("��񂾂ނՂꂢ��`",JLabel.CENTER);
		memo = new JLabel("�t�B�j�b�V���o���܂�100��",JLabel.CENTER);

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
					counter.setText("�t�B�j�b�V��");
					fchoose.setIsfinish(true);
				}
				else{
					fchoose.setCounter(fchoose.getCounter()+1);
					counter.setText(fchoose.getCounter() + "��� �F " +playfile);
				}
				myplayer.Setmthread(new playmethod(fchoose.getPath()+"\\"+playfile));
				myplayer.Getmthread().start();
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		//�C�x���g��button�ŋN�����Ă�����c
		if (e.getSource() == next) {
			nextsong(fchoose.getplayFile());
		}
		if (e.getSource()==open) { // �{�^���N���b�N�C�x���g
			if(fchoose.getlist()==JFileChooser.APPROVE_OPTION){
				nextsong(fchoose.getplayFile());
			}
		}
		if (e.getSource()==out) { // �{�^���N���b�N�C�x���g
			if(fchoose.getOutfile()!=null&&fchoose.getOutfile().exists()&&!fchoose.getIsout()){
				try {
					myplayer.Getmthread().close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				myplayer.Setmthread(new playmethod(fchoose.getOut()));
				myplayer.Getmthread().start();
				fchoose.setIsout(true);
				counter.setText("�A�E�g");
			}
		}
		if (e.getSource()==outfileb) { // �{�^���N���b�N�C�x���g
			fchoose.OutfileChoose();
		}
		if (e.getSource()==times) { // �{�^���N���b�N�C�x���g
			InputDialog id = new InputDialog();
			fchoose.setkaisu(Integer.parseInt(id.getValue()),false);
			memo.setText("�t�B�j�b�V���o���܂�"+Integer.parseInt(id.getValue())+"��");
		}
		if (e.getSource()==finish) { // �{�^���N���b�N�C�x���g
			fchoose.finishChoose();
		}
		if (e.getSource()==dispose) { // �{�^���N���b�N�C�x���g
			dispose(); // �A�v���P�[�V�����t���[���j��
			System.exit(0); // �I��
		}
	}
}