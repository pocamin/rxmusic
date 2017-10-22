package org.softshake.rxmusic.synth;

public class SoftShakeMixer1 {

    /**
     * Create an observable and subscribe to it
     * Oh Yeah
     */
    public SoftShakeMixer1() {
        try {
            new PitchListener()
                    .getObservable()
                    .subscribe(System.out::println);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new SoftShakeMixer1();
    }

}
