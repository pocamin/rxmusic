package org.softshake.rxmusic.synth;

import io.reactivex.Observable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SoftShakeMixer3Fixed {
    private MySynthesizer mySynthesizer = new MySynthesizer();
    private static final CountDownLatch finished = new CountDownLatch(1);

    /**
     * Feel like i need a metronome
     */
    public SoftShakeMixer3Fixed() {
        Observable.interval(250, TimeUnit.MILLISECONDS)
                .subscribe(__ -> mySynthesizer.tapOnClave(MySynthesizer.HIGH_VELOCITY),
                        Utils.DO_NOTHING_ON_ERROR,
                        finished::countDown);

    }


    public static void main(String[] args) throws InterruptedException {
        SoftShakeMixer3Fixed softShakeMixer = new SoftShakeMixer3Fixed();
        softShakeMixer.mySynthesizer.stop();
    }

}
