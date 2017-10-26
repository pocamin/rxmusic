package org.softshake.rxmusic.synth;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static io.reactivex.Observable.just;
import static org.softshake.rxmusic.synth.MySynthesizer.MED_VELOCITY;
import static org.softshake.rxmusic.synth.MySynthesizer.NO_NOTE;
import static org.softshake.rxmusic.synth.SoundConstants.*;
import static org.softshake.rxmusic.synth.Utils.DO_NOTHING_ON_ERROR;

public class SoftShakeMixer7 {


    private MySynthesizer mySynthesizer = new MySynthesizer();
    private static final CountDownLatch finished = new CountDownLatch(1);


    private static Observable<Integer> MELODY = Observable.fromArray(
            NoteC,
            NoteF,
            NoteG,
            NoteDownDp
    );

    /**
     * I don't like fugue and
     * Oups I hit a black key
     */
    public SoftShakeMixer7() throws InterruptedException {
        Subject<Long> beat = PublishSubject.create();
        HarmonisationService harmonisationService = new HarmonisationService();

        Observable<Integer> alternative = just(NoteC, NO_NOTE, NO_NOTE);

        Observable<Integer> playedNotes = MELODY
                .repeat(100)
                .flatMap(note ->
                        harmonisationService
                                .getOneChordFromMajorScaleContainingNote(NoteC, note)
                                .onErrorResumeNext(alternative)
                                .concatWith(just(NO_NOTE))

                )
                .zipWith(beat, (note, __) -> note);

        playedNotes.subscribe(note -> mySynthesizer.playNote(MySynthesizer.Instr.ACCORDION, note, 250,
                MED_VELOCITY));


        Observable.interval(125, TimeUnit.MILLISECONDS)
                .take(15, TimeUnit.SECONDS)
                .subscribe(beat::onNext, DO_NOTHING_ON_ERROR, () -> {
                    beat.onComplete();
                    finished.countDown();
                });
    }


    public static void main(String[] args) throws InterruptedException {
        SoftShakeMixer7 softShakeMixer = new SoftShakeMixer7();
        finished.await();
        System.out.println("That was time !");
        softShakeMixer.mySynthesizer.stop();
    }

}
