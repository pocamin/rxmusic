package org.softshake.rxmusic.synth;

import java.util.IdentityHashMap;
import java.util.Map;

public class ReadableNote {
    private static final Map<Integer, String> NOTES_AS_STRING = new IdentityHashMap<Integer, String>() {{
        put(3, "La#/Sib");
        put(2, "La");
        put(1, "Sol#/Lab");
        put(0, "Sol");
        put(11, "Fa#/Solb");
        put(10, "Fa");
        put(9, "Mi");
        put(8, "Re#/Mib");
        put(7, "Re");
        put(6, "Do#/Reb");
        put(5, "Do");
        put(4, "Si");
    }};

    public static String toReadable(int note) {
        return NOTES_AS_STRING.get(note % 12);

    }
}
