package org.softshake.rxmusic.synth;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import io.reactivex.subjects.PublishSubject;

import javax.sound.sampled.*;
import java.util.stream.Stream;

/**
 * Created by bleroux on 18/09/17.
 */
public class PitchListener implements PitchDetectionHandler {
    private final PitchProcessor.PitchEstimationAlgorithm algorithm = PitchProcessor.PitchEstimationAlgorithm.YIN;
    private AudioDispatcher dispatcher;
    private PublishSubject<Float> noteSubject = PublishSubject.create();


    public PitchListener() throws LineUnavailableException,
            UnsupportedAudioFileException {

        Mixer.Info mixerInfo = Stream.of(AudioSystem.getMixerInfo())
                .filter(info -> info.getDescription().contains("USB"))
                .findFirst().orElseThrow(() -> new RuntimeException("cannot find usb mixer"));
        Mixer mixer = AudioSystem.getMixer(mixerInfo);
        float sampleRate = 44100;
        int bufferSize = 1024;
        int overlap = 0;
        final AudioFormat format = new AudioFormat(sampleRate, 16, 1, true, true);
        final DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, format);
        TargetDataLine line = (TargetDataLine) mixer.getLine(dataLineInfo);
        final int numberOfSamples = bufferSize;
        line.open(format, numberOfSamples);
        line.start();
        JVMAudioInputStream audioStream = new JVMAudioInputStream(new AudioInputStream(line));
        dispatcher = new AudioDispatcher(audioStream, bufferSize, overlap);
        dispatcher.addAudioProcessor(new PitchProcessor(algorithm, sampleRate, bufferSize, this));
        new Thread(dispatcher, "Audio dispatching").start();
    }

    @Override
    public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
        if (pitchDetectionResult.getPitch() < 0) {
            return;
        }

        noteSubject.onNext(pitchDetectionResult.getPitch());
    }

    public PublishSubject<Float> getObservable() {
        return noteSubject;
    }
}
