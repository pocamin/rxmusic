package org.softshake.rxmusic.synth;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Created by bleroux on 14/09/17.
 */
public class ObservableMidiReceiver extends Observable<MessageWithTimeStamp> implements Receiver {

    private final PublishSubject<MessageWithTimeStamp> observable;


    public ObservableMidiReceiver() {
        this.observable = PublishSubject.create();
    }


    @Override
    public void send(MidiMessage message, long timeStamp) {
        try {
            this.observable.onNext(new MessageWithTimeStamp(
                    new ShortMessage(intAt(message, 0),
                            0,
                            intAt(message, 1),
                            intAt(message, 2)),
                    timeStamp));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    private int intAt(MidiMessage message, int pos) {
        return (int) message.getMessage()[pos] & 0xFF;
    }

    @Override
    public void close() {

    }

    @Override
    protected void subscribeActual(Observer<? super MessageWithTimeStamp> observer) {
        observable.subscribe(observer);
    }
}
