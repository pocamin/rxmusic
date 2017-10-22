package org.softshake.rxmusic.synth;

public class SoftShakeMixer2 {

    /**
     * hey I play (try) note not pitch
     */
    public SoftShakeMixer2() {
        try {

            new PitchListener()
                    .getObservable()
                    .map(PitchToNote::getNote)
                    .map(ReadableNote::toReadable)
                    .subscribe(System.out::println);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SoftShakeMixer2 softShakeMixer = new SoftShakeMixer2();
    }

}
