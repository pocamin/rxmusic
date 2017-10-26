package org.softshake.rxmusic.synth;

import io.reactivex.schedulers.Schedulers;

public class SoftShakeMixer2ABitBetter {

    /**
     * hey I play (try) note not pitch
     */
    public SoftShakeMixer2ABitBetter() {
        try {

            new PitchListener()
                    .getObservable()
                    .map(PitchToNote::getNote)
                    .map(ReadableNote::toReadable)

                    .observeOn(Schedulers.io())
                    .subscribe(System.out::println);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SoftShakeMixer2ABitBetter softShakeMixer = new SoftShakeMixer2ABitBetter();
    }

}
