package org.softshake.rxmusic.synth;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.softshake.rxmusic.synth.MySynthesizer.MED_VELOCITY;
import static org.softshake.rxmusic.synth.Utils.DO_NOTHING_ON_ERROR;

public class SoftShakeMixer8 {


    private MySynthesizer mySynthesizer = new MySynthesizer();
    private static final CountDownLatch finished = new CountDownLatch(1);


    private static Observable<Integer> MELODY = Observable.fromArray(
            SoundConstants.NoteC,
            SoundConstants.NoteG,
            SoundConstants.NoteA
    );

    /**
     * Lets add more complex rhythms
     * I don't want to play all day long
     */
    public SoftShakeMixer8() throws InterruptedException {
        Subject<Long> beat = PublishSubject.create();
        HarmonisationService harmonisationService = new HarmonisationService();


        Observable<Integer> playedNotes = MELODY
                .repeat(100)
                .flatMap(note ->
                        harmonisationService.getOneChordFromMajorScaleContainingNote(SoundConstants.NoteC, note)
                                .concatWith(Observable.just(MySynthesizer.NO_NOTE).repeat(5))

                )
                .zipWith(beat, (note, __) -> note);

        playedNotes.subscribe(note -> mySynthesizer.playNote(MySynthesizer.Instr.ACCORDION, note, 250,
                MED_VELOCITY));


        Observable.interval(250, TimeUnit.MILLISECONDS)
                .take(15, TimeUnit.SECONDS)
                .subscribe(beat::onNext, DO_NOTHING_ON_ERROR, () -> {
                    beat.onComplete();
                    finished.countDown();
                });
    }


    public static void main(String[] args) throws InterruptedException {
        SoftShakeMixer8 softShakeMixer = new SoftShakeMixer8();
        finished.await();
        System.out.println("That was time !");
        softShakeMixer.mySynthesizer.stop();
    }

}
