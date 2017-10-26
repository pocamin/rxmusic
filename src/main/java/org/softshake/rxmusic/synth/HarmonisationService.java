package org.softshake.rxmusic.synth;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by bleroux on 22/10/17.
 */
public class HarmonisationService {


    public Observable<Integer> getOneChordFromMajorScaleContainingNote(int fundamental, int note) {
        return Observable.create(subscriber -> {
            System.out.println("coucou");
            getMajorChordHarmonisation(fundamental, note)
                    .stream()
                    .map(chordNote -> 12 * (note / 12) + chordNote % 12)
                    .forEach(subscriber::onNext);

            subscriber.onComplete();
        });
    }


    // BLACK BOX
    public Set<Integer> getMajorChordHarmonisation(int fundamental, int note) throws TryToPlayTheGoodNote {

        List<Integer> majorScaleNotes = Stream.of(0, 2, 4, 5, 7, 9, 11)
                .map(v -> (fundamental + v) % 12)
                .collect(Collectors.toList());

        List<Set<Integer>> goodChords = new ArrayList<>();
        // first degree
        goodChords.add(Stream.of(0, 2, 4).map(majorScaleNotes::get).collect(Collectors.toSet()));
        // Forth degree
        goodChords.add(Stream.of(3, 5, 0).map(majorScaleNotes::get).collect(Collectors.toSet()));
        // Fifth degree
        goodChords.add(Stream.of(4, 6, 1).map(majorScaleNotes::get).collect(Collectors.toSet()));

        Random random = new Random();
        return goodChords
                .stream()
                .filter(notes -> notes.contains(note % 12))
                .sorted((a, b) -> random.nextBoolean() ? -1 : 1)
                .findFirst()
                .orElseThrow(() -> new TryToPlayTheGoodNote());
    }

}
