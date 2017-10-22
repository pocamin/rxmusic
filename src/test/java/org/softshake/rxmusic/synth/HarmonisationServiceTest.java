package org.softshake.rxmusic.synth;

import org.junit.Test;

import java.util.stream.Collectors;

/**
 * Created by bleroux on 22/10/17.
 */
public class HarmonisationServiceTest {
    HarmonisationService harmonisationService = new HarmonisationService();

    @Test
    public void getMajorChordHarmonisation() {
        System.out.println(ReadableNote.toReadable(10));


        String chords = harmonisationService.getMajorChordHarmonisation(10, 10)
                .stream()
                .map(chord -> chord.stream()
                        .map(ReadableNote::toReadable)
                        .collect(Collectors.joining(" ")))
                .collect(Collectors.joining(", "));

        System.out.println(chords);

    }


}