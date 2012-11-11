import javax.swing.JFrame;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class InputDialog extends JFrame{
	private String value;
	InputDialog(){
		 value = JOptionPane.showInputDialog(this, "フィニッシュまで何回？");
	}
	public String getValue(){
		return value;
	}
}
