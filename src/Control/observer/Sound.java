package Control.observer;

import Model.Logger.GameLogger;
import Control.world_class.Circus;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound extends Observer{
	private Circus game;
	private Clip Three;
	public Sound(Circus game) {
		this.game = game;
		game.attach(this);
	}

	@Override
	public void update(int num) {
		// TODO this must be called from strategy
		// update sound here.
		try {
			URL url = View.addition_classes.Sound.class.getResource("/three.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			Three = AudioSystem.getClip();
			Three.open(audioIn);
//
//			FloatControl gainControl =
//				    (FloatControl) gameOver.getControl(FloatControl.Type.MASTER_GAIN);
//				gainControl.setValue(-2.0f);

			Three.start();
		} catch (Exception e) {
			GameLogger.getInstance().log.error("can't find audio file");
		}
	}
}
