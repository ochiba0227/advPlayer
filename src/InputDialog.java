import javax.swing.JFrame;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class InputDialog extends JFrame{
	private String value;
	InputDialog(){
		 value = JOptionPane.showInputDialog(this, "�t�B�j�b�V���܂ŉ���H");
	}
	public String getValue(){
		return value;
	}
}
