package org.softshake.rxmusic.synth;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.softshake.rxmusic.synth.MySynthesizer.HIGH_VELOCITY;
import static org.softshake.rxmusic.synth.MySynthesizer.MED_VELOCITY;
import static org.softshake.rxmusic.synth.SoundConstants.*;
import static org.softshake.rxmusic.synth.Utils.*;

public class SoftShakeMixer5 {


    public static final int LOT = 40;
    private MySynthesizer mySynthesizer = new MySynthesizer();
    private static final CountDownLatch finished = new CountDownLatch(1);


    private static Observable<Boolean> HH_RHYTHM = Observable.fromArray(O, X, O, X);
    private static Observable<Boolean> SN_RHYTHM = Observable.fromArray(X, O, O, X, X, O, O, O);
    private static Observable<Boolean> TM_RHYTHM = Observable.fromArray(X, O, O, O, X, X, O, O);
    private static Observable<Boolean> CB_RHYTHM = Observable.fromArray(O, O, O, O, O, O, O, X);


    /**
     * Lets add more complex rhythms
     * I don't want to play all day long
     */
    public SoftShakeMixer5() throws InterruptedException {
        Subject<Long> beat = PublishSubject.create();


        HH_RHYTHM.repeat(LOT).zipWith(beat, (mustPlay, __) -> mustPlay)
                .filter(mustPlay -> mustPlay)
                .subscribe(__ -> mySynthesizer.playDrums(ClosedHiHat, 200_000, MED_VELOCITY),
                        DO_NOTHING_ON_ERROR);

        SN_RHYTHM.repeat(LOT).zipWith(beat, (mustPlay, __) -> mustPlay)
                .skip(5, TimeUnit.SECONDS)
                .filter(mustPlay -> mustPlay)
                .subscribe(__ -> mySynthesizer.playDrums(AcousticSnare, 200_000, HIGH_VELOCITY));


        TM_RHYTHM.repeat(LOT).zipWith(beat, (mustPlay, __) -> mustPlay)
                .filter(mustPlay -> mustPlay)
                .skip(3, TimeUnit.SECONDS)
                .subscribe(__ -> mySynthesizer.playDrums(HighTom, 200_000, HIGH_VELOCITY));


        CB_RHYTHM.repeat(LOT).zipWith(beat, (mustPlay, __) -> mustPlay)
                .filter(mustPlay -> mustPlay)
                .subscribe(__ -> mySynthesizer.playDrums(CrashCymbal_1, 1_000_000, HIGH_VELOCITY));


        Observable.interval(250, TimeUnit.MILLISECONDS)
                .take(20, TimeUnit.SECONDS)
                .subscribe(beat::onNext, DO_NOTHING_ON_ERROR, () -> {
                    beat.onComplete();
                    finished.countDown();
                });
    }


    public static void main(String[] args) throws InterruptedException {
        SoftShakeMixer5 softShakeMixer = new SoftShakeMixer5();
        finished.await();
        System.out.println("That was time !");
        softShakeMixer.mySynthesizer.stop();
    }

}
