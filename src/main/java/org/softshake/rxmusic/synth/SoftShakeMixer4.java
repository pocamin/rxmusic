package org.softshake.rxmusic.synth;

import io.reactivex.Observable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SoftShakeMixer4 {
    private MySynthesizer mySynthesizer = new MySynthesizer();
    private static final CountDownLatch finished = new CountDownLatch(1);

    /**
     * I don't want to play all day long
     */
    public SoftShakeMixer4() throws InterruptedException {
        Observable.interval(1000, 250, TimeUnit.MILLISECONDS)
                .take(5, TimeUnit.SECONDS)
                .subscribe(__ -> mySynthesizer.tapOnClave(MySynthesizer.HIGH_VELOCITY),
                        Utils.DO_NOTHING_ON_ERROR,
                        finished::countDown);
    }


    public static void main(String[] args) throws InterruptedException {
        SoftShakeMixer4 softShakeMixer = new SoftShakeMixer4();
        finished.await();
        System.out.println("That was time !");
        softShakeMixer.mySynthesizer.stop();
    }

}
