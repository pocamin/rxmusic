package org.softshake.rxmusic.synth;

import java.util.TreeMap;

/**
 * Created by bleroux on 18/09/17.
 */
public class PitchToNote extends TreeMap<Double, Integer> {
    private static PitchToNote instance = new PitchToNote();


    public static int getNote(double frequency) {
        Double ceiling = instance.ceilingKey(frequency);
        Double floor = instance.floorKey(frequency);

        if (ceiling == null) {
            return instance.get(floor);
        }
        if (floor == null) {
            return instance.get(ceiling);
        }
        return (ceiling - frequency < frequency - floor) ? instance.get(ceiling) : instance.get(floor);
    }

    private PitchToNote() {
        addPitch(21.83);
        addPitch(23.12);
        addPitch(24.50);
        addPitch(25.96);
        addPitch(27.50);
        addPitch(29.14);
        addPitch(30.87);
        addPitch(32.70);
        addPitch(34.65);
        addPitch(36.71);
        addPitch(38.89);
        addPitch(41.20);
        addPitch(43.65);
        addPitch(46.25);
        addPitch(49.00);
        addPitch(51.91);
        addPitch(55.00);
        addPitch(58.27);
        addPitch(61.74);
        addPitch(65.41);
        addPitch(69.30);
        addPitch(73.42);
        addPitch(77.78);
        addPitch(82.41);
        addPitch(87.31);
        addPitch(92.50);
        addPitch(98.00);
        addPitch(103.83);
        addPitch(110.00);
        addPitch(116.54);
        addPitch(123.47);
        addPitch(130.81);
        addPitch(138.59);
        addPitch(146.83);
        addPitch(155.56);
        addPitch(164.81);
        addPitch(174.61);
        addPitch(185.00);
        addPitch(196.00);
        addPitch(207.65);
        addPitch(220.00);
        addPitch(233.08);
        addPitch(246.94);
        addPitch(261.63);
        addPitch(277.18);
        addPitch(293.66);
        addPitch(311.13);
        addPitch(329.63);
        addPitch(349.23);
        addPitch(369.99);
        addPitch(392.00);
        addPitch(415.30);
        addPitch(440.00);
        addPitch(466.16);
        addPitch(493.88);
        addPitch(523.25);
        addPitch(554.37);
        addPitch(587.33);
        addPitch(622.25);
        addPitch(659.25);
        addPitch(698.46);
        addPitch(739.99);
        addPitch(783.99);
        addPitch(830.61);
        addPitch(880.00);
        addPitch(932.33);
        addPitch(987.77);
        addPitch(1046.50);
        addPitch(1108.73);
        addPitch(1174.66);
        addPitch(1244.51);
        addPitch(1318.51);
        addPitch(1396.91);
        addPitch(1479.98);
        addPitch(1567.98);
        addPitch(1661.22);
        addPitch(1760.00);
        addPitch(1864.66);
        addPitch(1975.53);
        addPitch(2093.00);
        addPitch(2217.46);
        addPitch(2349.32);
        addPitch(2489.02);
        addPitch(2637.02);
        addPitch(2793.83);
        addPitch(2959.96);
        addPitch(3135.96);
        addPitch(3322.44);
        addPitch(3520.00);
        addPitch(3729.31);
        addPitch(3951.07);
        addPitch(4186.01);
        addPitch(4434.92);
        addPitch(4698.63);
        addPitch(4978.03);
        addPitch(5274.04);
        addPitch(5587.65);
        addPitch(5919.91);
        addPitch(6271.93);
        addPitch(6644.88);
        addPitch(7040.00);
        addPitch(7458.62);
        addPitch(7902.13);
    }

    private void addPitch(double v) {
        put(v, size());
    }

}
