package org.softshake.rxmusic.synth;

import io.reactivex.Observable;

import javax.sound.midi.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class SoftShakeMixer {

    public static final Observable<Integer> GUITAR_STROKE = Observable.just(1000000, 2000000, 3000000, 4000000);
    public static final int ACCORDION = 23;
    private static final int GUITAR = 24;


    Synthesizer synth;


    public SoftShakeMixer() {
        try {
            initSynth();
            inItMidiChannel();

//
//            ObservableMidiReceiver nanoPad2Observable = getNanopad2Observable();
//
//            nanoPad2Observable
//                    .map(messageWithTimeStamp -> messageWithTimeStamp.changeChannel(1))
//                    .flatMap(messageWithTimeStamp -> {
//                        return Observable
//                                .just(0, 4, 7, 12)
//                                .map(messageWithTimeStamp::addPitch)
//                                .zipWith(GUITAR_STROKE, MessageWithTimeStamp::delay);
//                    }).subscribe(messageWithTimeStamp -> {
//                synth.getReceiver().send(messageWithTimeStamp
//                        .getMidiMessage(), messageWithTimeStamp.getTimeStamp());
//            });
//


            Observable<Long> ti = Observable.interval(300, TimeUnit.MILLISECONDS);

            PitchListener pitchListener = new PitchListener();
            pitchListener.getObservable().subscribe(System.out::println);


//            Observable<MessageWithTimeStamp> notes = pitchListener.getObservable()
//                    .take(3, TimeUnit.SECONDS)
//                    .cache()
//                    .repeat(100)
//                    .skip(3, TimeUnit.SECONDS)
//                    .delay(3, TimeUnit.SECONDS)
//                    .zipWith(ti, (shortMessage, aLong) -> shortMessage)
//                    .map(shortMessage -> new MessageWithTimeStamp(shortMessage, getCurrentTimeStamp()))
//
//                    .flatMap(messageWithTimeStamp -> {
//                        return Observable
//                                .just(0, 4, 7, 12)
//                                .map(messageWithTimeStamp::addPitch)
//                                .map(m -> m.changeChannel(1))
//                                .zipWith(GUITAR_STROKE, MessageWithTimeStamp::delay);
//                    });
//
//            CountDownLatch gate = new CountDownLatch(1);
//            Observable.interval(1, TimeUnit.SECONDS)
//                    .take(3)
//                    .observeOn(Schedulers.io()).subscribe(x -> System
//                    .out.println(x + 1), __ -> {
//            }, gate::countDown);
//            gate.await();
//            System.out.println("ready");
//
//            notes.subscribe(this::sendToSynth);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private Long getCurrentTimeStamp() throws MidiUnavailableException {
        return MidiSystem.getMidiDevice(synth.getDeviceInfo()).getMicrosecondPosition();
    }

    private void sendToSynth(MessageWithTimeStamp messageWithTimeStamp) throws MidiUnavailableException {
        synth.getReceiver().send(messageWithTimeStamp
                .getMidiMessage(), messageWithTimeStamp.getTimeStamp());
    }

    private ObservableMidiReceiver getNanopad2Observable() throws MidiUnavailableException {
        MidiDevice midiDevice = MidiSystem.getMidiDevice(Stream.of(MidiSystem.getMidiDeviceInfo()).filter(i -> i
                .getName().contains("nanoPAD2")).findFirst().orElseThrow(() -> new RuntimeException("could not " +
                "find nano pad 2")));
        ObservableMidiReceiver nanoPad2Observable = new ObservableMidiReceiver();
        midiDevice.getTransmitter().setReceiver(nanoPad2Observable);
        midiDevice.open();
        return nanoPad2Observable;
    }

    private void inItMidiChannel() {
        Soundbank bank = synth.getDefaultSoundbank();
        synth.loadAllInstruments(bank);
        Instrument instrs[] = synth.getLoadedInstruments();
        for (int i = 0; i < instrs.length; i++) {
            System.out.println(i + ":" + instrs[i].getName());
        }

        MidiChannel channels[] = synth.getChannels();
        channels[0].programChange(instrs[ACCORDION].getPatch().getBank(), instrs[ACCORDION].getPatch().getProgram());
        channels[1].programChange(instrs[GUITAR].getPatch().getBank(), instrs[GUITAR].getPatch().getProgram());
    }

    private void initSynth() throws MidiUnavailableException {
        synth = MidiSystem.getSynthesizer();
        synth.open();
    }

    public static void main(String[] args) {
        SoftShakeMixer softShakeMixer = new SoftShakeMixer();
    }

}
