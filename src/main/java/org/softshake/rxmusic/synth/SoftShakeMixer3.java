package org.softshake.rxmusic.synth;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class SoftShakeMixer3 {
    private MySynthesizer mySynthesizer = new MySynthesizer();

    /**
     * Feel like i need a metronome
     */
    public SoftShakeMixer3() {

        Observable.interval(250, TimeUnit.MILLISECONDS)
                .subscribe(__ -> mySynthesizer.tapOnClave(MySynthesizer.HIGH_VELOCITY));


    }




    public static void main(String[] args) throws InterruptedException {
        SoftShakeMixer3 softShakeMixer = new SoftShakeMixer3();
        softShakeMixer.mySynthesizer.stop();
    }

}
