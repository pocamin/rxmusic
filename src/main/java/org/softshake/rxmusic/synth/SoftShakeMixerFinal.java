package org.softshake.rxmusic.synth;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.softshake.rxmusic.synth.Utils.DO_NOTHING_ON_ERROR;

public class SoftShakeMixerFinal {
    private final int BEAT = 5000;

    private MySynthesizer mySynthesizer = new MySynthesizer();
    private static final CountDownLatch finished = new CountDownLatch(1);

    /**
     * Lets add more complex rhythms
     * I don't want to play all day long
     */
    public SoftShakeMixerFinal() throws InterruptedException, LineUnavailableException, UnsupportedAudioFileException {
        Subject<Long> beat = PublishSubject.create();

        Observable.fromArray(Music.SUPER_MUSIC)
                .zipWith(beat, (note, __) -> note)
                .subscribe(Music::processor);


        Observable.interval(BEAT, TimeUnit.MILLISECONDS)
                .take(60, TimeUnit.SECONDS)
                .subscribe(beat::onNext, DO_NOTHING_ON_ERROR, () -> {
                    beat.onComplete();
                    finished.countDown();
                });
    }


    public static void main(String[] args) throws InterruptedException, LineUnavailableException, UnsupportedAudioFileException {
        SoftShakeMixerFinal softShakeMixer = new SoftShakeMixerFinal();
        SoftShakeMixer5 softShakeMixer5 = new SoftShakeMixer5();
        finished.await();
        System.out.println("That was time !");
        softShakeMixer.mySynthesizer.stop();
    }

}
