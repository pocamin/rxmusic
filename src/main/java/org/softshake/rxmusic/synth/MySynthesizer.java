package org.softshake.rxmusic.synth;

import javax.sound.midi.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by bleroux on 19/10/17.
 */
public class MySynthesizer {

    public static final Integer NO_NOTE = 0;
    private Timer timer = new Timer();


    public static final int LOW_VELOCITY = 20;
    public static final int MED_VELOCITY = 75;
    public static final int HIGH_VELOCITY = 127;


    public enum Instr {
        ACCORDION(SoundConstants.Accordion, 1),
        GUITAR(SoundConstants.AcousticGuitar_nylon, 2),
        CLARINET(SoundConstants.Clarinet, 3),
        CLAVES(SoundConstants.Woodblock, 4);


        private final int midiIndex;
        private final int midiChannel;

        Instr(int midiIndex, int midiChannel) {
            this.midiIndex = midiIndex;
            this.midiChannel = midiChannel;
        }
    }


    public static final int ACCORDION = 23;
    private static final int GUITAR = 24;
    private static final int WOODBLOK = 115;

    private Synthesizer synth;

    public MySynthesizer() {
        try {
            Thread.sleep(1000);

            initSynth();
            inItMidiChannel();
        } catch (MidiUnavailableException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    private void initSynth() throws MidiUnavailableException {
        synth = MidiSystem.getSynthesizer();
        synth.open();
    }


    private Long getCurrentTimeStamp() throws MidiUnavailableException {
        return MidiSystem.getMidiDevice(synth.getDeviceInfo()).getMicrosecondPosition();
    }


    private void inItMidiChannel() {
        Soundbank bank = synth.getDefaultSoundbank();
        synth.loadAllInstruments(bank);
        Instrument instrs[] = synth.getLoadedInstruments();

        MidiChannel channels[] = synth.getChannels();
        for (Instr instr : Instr.values()) {
            channels[instr.midiChannel].programChange(instrs[instr.midiIndex].getPatch().getBank(), instrs[instr.midiIndex].getPatch().getProgram());
        }
    }


    public void playNote(Instr instrument, int midiNote, long duration, int velocity) {
        try {
            if (midiNote != NO_NOTE) {
                synth.getReceiver().send(new ShortMessage(ShortMessage.NOTE_ON, instrument.midiChannel, midiNote,
                        velocity), -1);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            synth.getReceiver().send(new ShortMessage(ShortMessage.NOTE_OFF, instrument.midiChannel, midiNote, velocity),
                                    -1);
                        } catch (MidiUnavailableException | InvalidMidiDataException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, duration);
            }
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }

    public void playDrums(int drumNote, long duration, int velocity) {
        try {
            synth.getReceiver().send(new ShortMessage(ShortMessage.NOTE_ON, 9, drumNote,
                    velocity), getCurrentTimeStamp());
            synth.getReceiver().send(new ShortMessage(ShortMessage.NOTE_OFF, 9, drumNote, velocity),
                    getCurrentTimeStamp() + duration);
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }


    public void tapOnClave(int velocity) {
        playNote(Instr.CLAVES, 95, 500_000, velocity);
    }


    public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
        MySynthesizer mySynthesizer = new MySynthesizer();

        Instrument instrs[] = mySynthesizer.synth.getLoadedInstruments();
        for (int i = 0; i < instrs.length; i++) {
            System.out.println(i + ":" + instrs[i].getName());
        }


        mySynthesizer.playDrums(SoundConstants.ClosedHiHat, 500_000, HIGH_VELOCITY);

        mySynthesizer.tapOnClave(HIGH_VELOCITY);
        System.out.println("stop in 3");
        Thread.sleep(1000);
        System.out.println("stop in 2");
        Thread.sleep(1000);
        System.out.println("stop in 1");
        Thread.sleep(1000);

    }

    public void stop() {
        // sorry i don't now how to close properly
        System.exit(0);
    }

}
