package org.softshake.rxmusic.synth;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.softshake.rxmusic.synth.MySynthesizer.HIGH_VELOCITY;
import static org.softshake.rxmusic.synth.MySynthesizer.LOW_VELOCITY;
import static org.softshake.rxmusic.synth.Utils.DO_NOTHING_ON_ERROR;

public class SoftShakeMixer10 {
    private final int BEAT = 500;

    private MySynthesizer mySynthesizer = new MySynthesizer();
    private static final CountDownLatch finished = new CountDownLatch(1);

    /**
     * Lets add more complex rhythms
     * I don't want to play all day long
     */
    public SoftShakeMixer10() throws InterruptedException, LineUnavailableException, UnsupportedAudioFileException {
        Subject<Long> beat = PublishSubject.create();
        HarmonisationService harmonisationService = new HarmonisationService();


        beat.subscribe(__ -> mySynthesizer.tapOnClave(LOW_VELOCITY));

        PitchListener pitchListener = new PitchListener();
        Observable<Integer> pitchEvents = pitchListener.getObservable()
                .map(PitchToNote::getNote)
                .map(n -> n + 17)
                .buffer(beat)
                .map(recognizedNotes ->
                        recognizedNotes.stream()
                                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                                .entrySet().stream()
                                .max(Comparator.comparing(Map.Entry::getValue))
                                .map(Map.Entry::getKey)
                                .orElse(0)
                );

        pitchEvents.filter(n -> n != 0)
                .flatMap(note -> harmonisationService.getOneChordFromMajorScaleContainingNote(SoundConstants.NoteC, note))
                .zipWith(beat, (note, __) -> note)
                .subscribe(note -> mySynthesizer.playNote(MySynthesizer.Instr.ACCORDION, note, 250, HIGH_VELOCITY));


        Observable.interval(BEAT, TimeUnit.MILLISECONDS)
                .take(60, TimeUnit.SECONDS)
                .subscribe(beat::onNext, DO_NOTHING_ON_ERROR, () -> {
                    beat.onComplete();
                    finished.countDown();
                });
    }


    public static void main(String[] args) throws InterruptedException, LineUnavailableException, UnsupportedAudioFileException {
        SoftShakeMixer10 softShakeMixer = new SoftShakeMixer10();
        finished.await();
        System.out.println("That was time !");
        softShakeMixer.mySynthesizer.stop();
    }

}
