import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

class playmethod extends Thread{
	static Player player;
	static BufferedInputStream stream;
	private String nowplay;

	public static void play(String file)throws JavaLayerException, FileNotFoundException {
		stream = new BufferedInputStream((new FileInputStream(file)));
		player = new Player(stream);
		player.play();
	}

	public void close() throws IOException {
		if (player != null) {
			player.close();
		}
		if (stream != null) {
			stream.close();
		}
	}

	public void start(){
		new Thread(this).start();
	}
	
	playmethod(){
	}
	playmethod(String playfile){
		nowplay=playfile;
	}

	public void run() {
		try {
			play(nowplay);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		} finally {
			if (player.isComplete()) {
				myplayer.Getopanel().nextsong("");
			}
		}
	}
}