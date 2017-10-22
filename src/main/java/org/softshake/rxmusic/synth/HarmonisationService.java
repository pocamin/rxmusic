package org.softshake.rxmusic.synth;

import io.reactivex.Observable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by bleroux on 22/10/17.
 */
public class HarmonisationService {

    public Set<Set<Integer>> getMajorChordHarmonisation(int fundamental, int note) {

        List<Integer> majorScaleNotes = Stream.of(0, 2, 4, 5, 7, 9, 11).map(v -> (fundamental + v) % 12).collect(Collectors.toList());

        Set<Set<Integer>> chordsInScale = new HashSet<>();
        for (int i = 0; i < majorScaleNotes.size(); i++) {
            Integer chordFundamentalNote = majorScaleNotes.get(i);
            Set<Integer> chord = new TreeSet<>();

            chord.add(chordFundamentalNote);
            chord.add(majorScaleNotes.get((i + 2) % 7));
            chord.add(majorScaleNotes.get((i + 4) % 7));
            chordsInScale.add(chord);
        }

        return chordsInScale.stream().filter(chord -> chord.contains(note % 12)).collect(Collectors.toSet());
    }


    public Observable<Integer> getOneChordFromMajorScaleContainingNote(int fundamental, int note) {
        List<Set<Integer>> chords = new ArrayList<>(getMajorChordHarmonisation(fundamental, note));
        if (chords.size() == 0) return Observable.just(note, note, note);
        Collections.shuffle(chords);
        return Observable.fromIterable(chords.get(0)).map(chordNote -> 12 * (note / 12) + chordNote % 12);

    }


}
