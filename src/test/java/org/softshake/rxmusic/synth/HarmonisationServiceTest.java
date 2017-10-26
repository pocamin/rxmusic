package org.softshake.rxmusic.synth;

import org.junit.Test;

import java.util.stream.Collectors;

/**
 * Created by bleroux on 22/10/17.
 */
public class HarmonisationServiceTest {
    HarmonisationService harmonisationService = new HarmonisationService();

    @Test
    public void getMajorChordHarmonisation() throws TryToPlayTheGoodNote {
        System.out.println(ReadableNote.toReadable(17));


        System.out.println(harmonisationService.getMajorChordHarmonisation(5, 17)
                .stream()
                .map(ReadableNote::toReadable)
                .collect(Collectors.joining(", ")));

    }


}