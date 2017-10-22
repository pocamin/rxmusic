package org.softshake.rxmusic.synth;

import io.reactivex.Observable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SoftShakeMixer4TakeCare {
    private MySynthesizer mySynthesizer = new MySynthesizer();
    private static final CountDownLatch finished = new CountDownLatch(1);

    /**
     * I don't want to play all day long
     * <p>
     * You sometime need to know observable implementations
     */
    public SoftShakeMixer4TakeCare() throws InterruptedException {
        Observable<Long> metronome = Observable.interval(3000, 250, TimeUnit.MILLISECONDS)
                .take(5, TimeUnit.SECONDS);


        System.out.println("first metronome start");
        metronome.subscribe(System.out::println);

        Thread.sleep(7000);

        System.out.println("second metronome start");
        metronome.subscribe(System.out::println,
                Utils.DO_NOTHING_ON_ERROR,
                finished::countDown);
    }


    public static void main(String[] args) throws InterruptedException {
        SoftShakeMixer4TakeCare softShakeMixer = new SoftShakeMixer4TakeCare();
        finished.await();
        System.out.println("That was time !");
        softShakeMixer.mySynthesizer.stop();
    }

}
