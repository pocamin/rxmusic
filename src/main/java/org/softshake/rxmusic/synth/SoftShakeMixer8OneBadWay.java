package org.softshake.rxmusic.synth;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.softshake.rxmusic.synth.MySynthesizer.MED_VELOCITY;
import static org.softshake.rxmusic.synth.MySynthesizer.NO_NOTE;
import static org.softshake.rxmusic.synth.Utils.DO_NOTHING_ON_ERROR;

public class SoftShakeMixer8OneBadWay {


    private MySynthesizer mySynthesizer = new MySynthesizer();
    private static final CountDownLatch finished = new CountDownLatch(1);


    private static Observable<Integer> MELODY = Observable.fromArray(
            SoundConstants.NoteC,
            SoundConstants.NoteA,
            SoundConstants.NoteD
    );

    /**
     * Sometime great power means great responsibility
     */
    public SoftShakeMixer8OneBadWay() throws InterruptedException {
        Subject<Long> beat = PublishSubject.create();
        HarmonisationService harmonisationService = new HarmonisationService();


        Observable<Integer> playedNotes = MELODY
                .flatMap(note ->
                        harmonisationService.getOneChordFromMajorScaleContainingNote(SoundConstants.NoteC, note)
                                .concatWith(Observable.just(MySynthesizer.NO_NOTE).repeat(5))

                )
                .zipWith(beat, (note, __) -> note)
                .cache();


        // Very good idea !!! let's also display notes
        playedNotes
                .filter(note -> note != NO_NOTE)
                .map(ReadableNote::toReadable)
                .subscribe(System.out::println);


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
        SoftShakeMixer8OneBadWay softShakeMixer = new SoftShakeMixer8OneBadWay();
        finished.await();
        System.out.println("That was time !");
        softShakeMixer.mySynthesizer.stop();
    }

}
