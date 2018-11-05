package business;

import android.media.AudioManager;
import android.media.ToneGenerator;

public class SensoSound {


    private final int TONE_DURATION = 150;
    private final int TONE_VOLUME = 100;

    private ToneGenerator generator = new ToneGenerator(AudioManager.STREAM_MUSIC, TONE_VOLUME);

    public void Yellow() {
        this.generator.startTone(ToneGenerator.TONE_CDMA_PIP, TONE_DURATION);
    }

    public void Red() {
        this.generator.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, TONE_DURATION);
    }

    public void Green() {
        this.generator.startTone(ToneGenerator.TONE_CDMA_ABBR_INTERCEPT, TONE_DURATION);
    }

    public void Blue() {
        this.generator.startTone(ToneGenerator.TONE_CDMA_ABBR_REORDER, TONE_DURATION);
    }
}
