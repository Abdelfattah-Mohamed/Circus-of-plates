package View.addition_classes;

import Model.Logger.GameLogger;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public static Sound sound = new Sound();
	private Clip Theme, gameOver, tickTock, Gotcha, Three, Two;
	private static boolean finished = false, almost = false;

	private Sound() {
	}

	public static Sound getInstance() {
		return sound;
	}

	public void startTheme() {
		try {
			URL url = Sound.class.getResource("/theme.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Theme = AudioSystem.getClip();
            Theme.open(audioIn);
            Theme.start();
            URL url1 = Sound.class.getResource("/start.wav");
            AudioInputStream audioIn1 = AudioSystem.getAudioInputStream(url1);
            Theme = AudioSystem.getClip();
            Theme.open(audioIn1);
            Theme.start();
			GameLogger.getInstance().log.debug("Music set to theme.wav");
		} catch (Exception e) {
			GameLogger.getInstance().log.error("can't find audio file");
		}
	}

	public void stopTheme() {
		Theme.stop();
	}

	public void gameOver() {
		if (!finished) {
			try {
				finished = true;
				URL url = Sound.class.getResource("/gameover.wav");
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				gameOver = AudioSystem.getClip();
				gameOver.open(audioIn);
				gameOver.start();
				Three.stop();
				Theme.stop();
				Two.stop();
				tickTock.stop();
				Gotcha.stop();
			} catch (Exception e) {
				GameLogger.getInstance().log.error("can't find audio file");
			}
		}
	}

	public void almostDone() {
		if(!almost) {
			try {
				almost = true;
				URL url = Sound.class.getResource("/ticktock.wav");
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				tickTock = AudioSystem.getClip();
				tickTock.open(audioIn);
//
//			FloatControl gainControl =
//				    (FloatControl) gameOver.getControl(FloatControl.Type.MASTER_GAIN);
//				gainControl.setValue(-2.0f);

				tickTock.start();
			} catch (Exception e) {
				GameLogger.getInstance().log.error("can't find audio file");
			}
		}
	}

	public void caught() {
		try {
			URL url = Sound.class.getResource("/catch.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			Gotcha = AudioSystem.getClip();
			Gotcha.open(audioIn);
//
//			FloatControl gainControl =
//				    (FloatControl) gameOver.getControl(FloatControl.Type.MASTER_GAIN);
//				gainControl.setValue(-2.0f);

			Gotcha.start();
		} catch (Exception e) {
			GameLogger.getInstance().log.error("can't find audio file");
		}
	}

    /*public void three() {
        try {
            URL url = Sound.class.getResource("/three.wav");
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
    }*/

	public void two() {
		try {
			URL url = Sound.class.getResource("/two.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			Two = AudioSystem.getClip();
			Two.open(audioIn);
//
//			FloatControl gainControl =
//				    (FloatControl) gameOver.getControl(FloatControl.Type.MASTER_GAIN);
//				gainControl.setValue(-2.0f);

			Two.start();
		} catch (Exception e) {
			GameLogger.getInstance().log.error("can't find audio file");
		}
	}
}