package addition_classes;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public static Sound sound = new Sound();
	private Clip clipCircus, clipCheering;

	private Sound() {
	}

	public static Sound getInstance() {
		return sound;
	}

	public void startCircusSound() {
		try {
			URL url = Sound.class.getResource("/theme.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			clipCircus = AudioSystem.getClip();
			clipCircus.open(audioIn);
			clipCircus.start();
		} catch (Exception e) {
		}
	}

	public void stopCircusSound() {
		clipCircus.stop();
	}

	public void startCheering() {
		try {
			URL url = Sound.class.getResource("/pics/cheering.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			clipCheering = AudioSystem.getClip();
			clipCheering.open(audioIn);
//			
//			FloatControl gainControl = 
//				    (FloatControl) clipCheering.getControl(FloatControl.Type.MASTER_GAIN);
//				gainControl.setValue(-2.0f);
			
			clipCheering.start();
		} catch (Exception e) {
		}
	}

	public void StopCheering() {
		clipCheering.stop();
	}
}