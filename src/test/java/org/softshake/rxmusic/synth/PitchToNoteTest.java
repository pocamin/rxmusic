package org.softshake.rxmusic.synth;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by bleroux on 18/09/17.
 */
public class PitchToNoteTest {
    @Test
    public void get() {
        Assert.assertEquals(0, PitchToNote.getNote(10d));
        Assert.assertEquals(15, PitchToNote.getNote(39d));
        Assert.assertEquals(16, PitchToNote.getNote(41d));


    }
}
