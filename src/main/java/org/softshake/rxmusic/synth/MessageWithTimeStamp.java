package org.softshake.rxmusic.synth;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

/**
 * Created by bleroux on 14/09/17.
 */
@Data
@RequiredArgsConstructor
public class MessageWithTimeStamp {
    private final ShortMessage midiMessage;
    private final Long timeStamp;


    public MessageWithTimeStamp addPitch(Integer deltaPitch) {
        try {
            ShortMessage shortMessage = new ShortMessage(midiMessage.getCommand(), midiMessage.getChannel(), midiMessage
                    .getData1() + deltaPitch, midiMessage.getData2());
            return new MessageWithTimeStamp(shortMessage, timeStamp);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }


    public MessageWithTimeStamp changeChannel(Integer newChannel) {
        try {
            ShortMessage shortMessage = new ShortMessage(midiMessage.getCommand(), newChannel,
                    midiMessage.getData1(), midiMessage.getData2());
            return new MessageWithTimeStamp(shortMessage, timeStamp);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }


    public MessageWithTimeStamp delay(int delay) {
        return new MessageWithTimeStamp(midiMessage, timeStamp + delay);
    }

}
