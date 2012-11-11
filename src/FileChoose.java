import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class FileChoose extends JFrame{
	private int number=0;
	private int counter=0;
	private int kaisu=100;

	final static String withmp3 = "[.mp3]$|[.wav]$";
	private String finish = "フィニッシュ.mp3";
	private String out = "out.mp3";
	private Pattern M1 = Pattern.compile(withmp3, Pattern.CASE_INSENSITIVE);
	private Pattern M2= Pattern.compile(finish, Pattern.CASE_INSENSITIVE);
	private Pattern M3= Pattern.compile(out, Pattern.CASE_INSENSITIVE);
	private Matcher finishso;
	private Matcher matchmp3;
	private Matcher outmp3;

	private static String[] fnames;
	private static JFileChooser filechooser;
	private String path="E:\\";
	private File outfile;
	
	private boolean isout=false;
	private boolean isfinish=false;

	public void setkaisu(int kaisu,boolean cflag){
		this.kaisu=kaisu;
		if(cflag==true){
			counter=0;
		}
	}
	public String getPath(){
		return path;
	}
	
	public String getplayFile(){
		if(counter<kaisu){
			do{
				number=(int) Math.floor(Math.random()*(fnames.length-1));
				finishso=M2.matcher(fnames[number]);
				outmp3=M3.matcher(fnames[number]);
			}while(finishso.find()||outmp3.find());
		}
		else{
			do{
				number=(int) Math.floor(Math.random()*(fnames.length-1));
				outmp3=M3.matcher(fnames[number]);
			}while(outmp3.find());
		}
		System.out.println("fnames["+number+"]="+fnames[number]);
		return fnames[number];
	}
	public int getNumber() {
		return number;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter=counter;
	}
	public int getlist(){
		int ischoose;
		filechooser = new JFileChooser(path);
		filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		ischoose=filechooser.showOpenDialog(this);
		if(ischoose== JFileChooser.APPROVE_OPTION){
			counter = 0;
			fnames = filechooser.getSelectedFile().list();
			path=filechooser.getSelectedFile().getAbsolutePath();
			List<String> templist = new ArrayList<String>(Arrays.asList(fnames));
			for (int i = 0; i < templist.size(); i++) {
				matchmp3=M1.matcher(templist.get(i));
				if(matchmp3.find()){
					System.out.println((i + 1) + ":    "+path+"\\"+templist.get(i));
				}
				else{
					templist.remove(i);
				}
			}
			fnames = (String[]) templist.toArray(new String[templist.size()]);
			System.out.println("path:"+path);
			System.out.println("------------------------------");
		}
		return ischoose;
	}
	
	public void OutfileChoose() {
		JFileChooser outfilec = new JFileChooser(path);
		outfilec.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (outfilec.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
			try {
				out=outfilec.getSelectedFile().getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(out);
			outfile = new File(out);
			M3= Pattern.compile(outfilec.getSelectedFile().getName(), Pattern.CASE_INSENSITIVE);
			System.out.println(outfilec.getSelectedFile().getName());
		}
	}
	public String getOut() {
		return out;
	}
	public File getOutfile() {
		return outfile;
	}
	public void finishChoose() {
		JFileChooser finishfilec = new JFileChooser(path);
		finishfilec.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (finishfilec.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
			try {
				finish=finishfilec.getSelectedFile().getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
			M2= Pattern.compile(finishfilec.getSelectedFile().getName(), Pattern.CASE_INSENSITIVE);
		}
	}
	public boolean finishfile(String test){
		Matcher filefinish=M2.matcher(test);
		if(filefinish.find()){
			return true;
		}
		return false;
	}
	public boolean getIsout(){
		return isout;
	}
	public boolean getIsfinish(){
		return isfinish;
	}
	public void setIsout(boolean b) {
		isout=b;
	}
	public String getFinish(){
		return finish;
	}
	public void setIsfinish(boolean b) {
		isfinish=b;		
	}
}
