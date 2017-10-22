package org.softshake.rxmusic.synth;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Constants for notes, keys, instruments, and channels defined by the
 * MIDI specification.
 * <p>
 * Based in part on a class originally designed by Viera K. Proulx.
 */
public interface SoundConstants {

    /**
     * Default channel assignments
     */
    public final static int
            PIANO = 0,
            ORGAN = 1,
            BASS = 2,
            VIOLIN = 3,
            CELLO = 4,
            STEELDRUM = 5,
            CHOIR = 6,
            TUBA = 7,
            SAX = 8,
    // NOTE: Channel 10 (#9) is always PERCUSSION!
    PERCUSSION = 9,
            WOOD_BLOCK = 10,
            BAGPIPE = 11,
            BIRD_TWEET = 12,
            SEASHORE = 13,
            APPLAUSE = 14,
            TELEPHONE = 15;

    /**
     * Standard MIDI instrument numbers
     */
    public final static int
            // Pianos
            AcousticGrandPiano = 1,
            BrightAcousticPiano = 2,
            ElectricGrandPiano = 3,
            HonkyTonkPiano = 4,
            ElectricPiano_1 = 5,
            ElectricPiano_2 = 6,
            Harpsichord = 7,
            Clavi = 8,

    // Chromatic Percussion
    Celesta = 9,
            Glockenspiel = 10,
            MusicBox = 11,
            Vibraphone = 12,
            Marimba = 13,
            Xylophone = 14,
            TubularBells = 15,
            Dulcimer = 16,

    // Organs
    DrawbarOrgan = 17,
            PercussiveOrgan = 18,
            RockOrgan = 19,
            ChurchOrgan = 20,
            ReedOrgan = 21,
            Accordion = 22,
            Harmonica = 23,
            TangoAccordion = 24,

    // Guitars
    AcousticGuitar_nylon = 25,
            AcousticGuitar_steel = 26,
            ElectricGuitar_jazz = 27,
            ElectricGuitar_clean = 28,
            ElectricGuitar_muted = 29,
            OverdrivenGuitar = 30,
            DistortionGuitar = 31,
            GuitarHarmonics = 32,

    // Basses
    AcousticBass = 33,
            ElectricBass_finger = 34,
            ElectricBass_pick = 35,
            FretlessBass = 36,
            SlapBass_1 = 37,
            SlapBass_2 = 38,
            SynthBass_1 = 39,
            SynthBass_2 = 40,

    // Strings
    Violin = 41,
            Viola = 42,
            Cello = 43,
            Contrabass = 44,
            TremoloStrings = 45,
            PizzicatoStrings = 46,
            OrchestralHarp = 47,
            Timpani = 48,

    // Ensemble
    StringEnsemble_1 = 49,
            StringEnsemble_2 = 50,
            SynthStrings_1 = 51,
            SynthStrings_2 = 52,
            ChoirAahs = 53,
            VoiceOohs = 54,
            SynthVoice = 55,
            OrchestraHit = 56,

    // Brass
    Trumpet = 57,
            Trombone = 58,
            Tuba = 59,
            MutedTrumpet = 60,
            FrenchHorn = 61,
            BrassSection = 62,
            SynthBrass_1 = 63,
            SynthBrass_2 = 64,

    // Reed
    SopranoSax = 65,
            AltoSax = 66,
            TenorSax = 67,
            BaritoneSax = 68,
            Oboe = 69,
            EnglishHorn = 70,
            Bassoon = 71,
            Clarinet = 72,

    // Pipe
    Piccolo = 73,
            Flute = 74,
            Recorder = 75,
            PanFlute = 76,
            BlownBottle = 77,
            Skakuhachi = 78,
            Whistle = 79,
            Ocarina = 80,

    // Synth Lead
    Lead_1_square = 81,
            Lead_2_sawtooth = 82,
            Lead_3_calliope = 83,
            Lead_4_chiff = 84,
            Lead_5_charang = 85,
            Lead_6_voice = 86,
            Lead_7_fifths = 87,
            Lead_8_basslead = 88,

    // Synth Pad
    Pad_1_newage = 89,
            Pad_2_warm = 90,
            Pad_3_polysynth = 91,
            Pad_4_choir = 92,
            Pad_5_bowed = 93,
            Pad_6_metallic = 94,
            Pad_7_halo = 95,
            Pad_8_sweep = 96,

    // Synth Effects
    FX_1_rain = 97,
            FX_2_soundtrack = 98,
            FX_3_crystal = 99,
            FX_4_atmosphere = 100,
            FX_5_brightness = 101,
            FX_6_goblins = 102,
            FX_7_echoes = 103,
            FX_8_scifi = 104,

    // Ethnic
    Sitar = 105,
            Banjo = 106,
            Shamisen = 107,
            Koto = 108,
            Kalimba = 109,
            Bagpipe = 110,
            Fiddle = 111,
            Shanai = 112,

    // Percussive
    TinkleBell = 113,
            Agogo = 114,
            SteelDrums = 115,
            Woodblock = 116,
            TaikoDrum = 117,
            MelodicTom = 118,
            SynthDrum = 119,
            ReverseCymbal = 120,

    // Sound Effects
    GuitarFretNoise = 121,
            BreathNoise = 122,
            Seashore = 123,
            BirdTweet = 124,
            TelephoneRing = 125,
            Helicopter = 126,
            Applause = 127,
            Gunshot = 128;

    /**
     * Default instrument selection
     */
    int[] INSTRUMENTS = new int[]{
            AcousticGrandPiano, ChurchOrgan, Contrabass, Violin,
            Cello, SteelDrums, ChoirAahs, Tuba,
            AltoSax, Woodblock, PERCUSSION, Bagpipe,
            BirdTweet, Seashore, Applause, TelephoneRing};

    /**
     * Names for the Notes/Keys
     */
    int NoteDownC = 48,
            NoteDownCp = 49,
            NoteDownD = 50,
            NoteDownDp = 51,
            NoteDownE = 52,
            NoteDownF = 53,
            NoteDownFp = 54,
            NoteDownG = 55,
            NoteDownGp = 56,
            NoteDownA = 57,
            NoteDownAp = 58,
            NoteDownB = 59,
            NoteC = 60,
            NoteCp = 61,
            NoteD = 62,
            NoteDp = 63,
            NoteE = 64,
            NoteF = 65,
            NoteFp = 66,
            NoteG = 67,
            NoteGp = 68,
            NoteA = 69,
            NoteAp = 70,
            NoteB = 71,
            NoteUpC = 72,
            NoteUpCp = 73,
            NoteUpD = 74,
            NoteUpDp = 75,
            NoteUpE = 76,
            NoteUpF = 77,
            NoteUpFp = 78,
            NoteUpG = 79,
            NoteUpGp = 80,
            NoteUpA = 81,
            NoteUpAp = 82,
            NoteUpB = 83;

    /**  */
    public static final int
            AcousticBassDrum = 35,
            BassDrum_1 = 36,
            SideStick = 37,
            AcousticSnare = 38,
            HandClap = 39,
            ElectricSnare = 40,
            LowFloorTom = 41,
            ClosedHiHat = 42,
            HighFloorTom = 43,
            PedalHiHat = 44,
            LowTom = 45,
            OpenHiHat = 46,
            LowMidTom = 47,
            HiMidTom = 48,
            CrashCymbal_1 = 49,
            HighTom = 50,
            RideCymbal1 = 51,
            ChineseCymbal = 52,
            RideBell = 53,
            Tambourine = 54,
            SplashCymbal = 55,
            Cowbell = 56,
            CrashCymbal_2 = 57,
            Vibraslap = 58,
            RideCymbal_2 = 59,
            HiBongo = 60,
            LowBongo = 61,
            MuteHiConga = 62,
            OpenHiConga = 63,
            LowConga = 64,
            HighTimbale = 65,
            LowTimbale = 66,
            HighAgogo = 67,
            LowAgogo = 68,
            Cabasa = 69,
            Maracas = 70,
            ShortWhistle = 71,
            LongWhistle = 72,
            ShortGuiro = 73,
            LongGuiro = 74,
            Claves = 75,
            HiWoodBlock = 76,
            LowWoodBlock = 77,
            MuteCuica = 78,
            OpenCuica = 79,
            MuteTriangle = 80,
            OpenTriangle = 81;

    /**
     * Names for the standard MIDI instrument
     */
    ArrayList<String> INSTRUMENT_NAMES =
            new ArrayList<String>(Arrays.asList(
                    "Acoustic Grand Piano", "Bright Acoustic Piano", "Electric Grand Piano", "Honky-Tonk Piano",
                    "Electric Piano 1", "Electric Piano 2", "Harpsichord", "Clavi",

                    "Celesta", "Glockenspiel", "Music Box", "Vibraphone",
                    "Marimba", "Xylophone", "Tubular Bells", "Dulcimer",

                    "Drawbar Organ", "Percussive Organ", "Rock Organ", "Church Organ",
                    "Reed Organ", "Accordion", "Harmonica", "Tango Accordion",

                    "Acoustic Guitar (nylon)", "Acoustic Guitar (steel)", "Electric Guitar (jazz)", "Electric Guitar (clean)",
                    "Electric Guitar (muted)", "Overdriven Guitar", "Distortion Guitar", "Guitar Harmonics",

                    "Acoustic Bass", "Electric Bass (finger)", "Electric Bass (pick)", "Fretless Bass",
                    "Slap Bass 1", "Slap Bass 2", "Synth Bass 1", "Synth Bass 2",

                    "Violin", "Viola", "Cello", "Contrabass",
                    "Tremolo Strings", "Pizzicato Strings", "Orchestral Harp", "Timpani",

                    "String Ensemble 1", "String Ensemble 2", "SynthStrings 1", "SynthStrings 2",
                    "Choir Aahs", "Voice Oohs", "Synth Voice", "Orchestra Hit",

                    "Trumpet", "Trombone", "Tuba", "Muted Trumpet",
                    "French Horn", "Brass Section", "SynthBrass 1", "SynthBrass 2",

                    "Soprano Sax", "Alto Sax", "Tenor Sax", "Baritone Sax",
                    "Oboe", "English Horn", "Bassoon", "Clarinet",

                    "Piccolo", "Flute", "Recorder", "Pan Flute",
                    "Blown Bottle", "Skakuhachi", "Whistle", "Ocarina",

                    "Lead 1 (square)", "Lead 2 (sawtooth)", "Lead 3 (calliope)", "Lead 4 (chiff)",
                    "Lead 5 (charang)", "Lead 6 (voice)", "Lead 7 (fifths)", "Lead 8 (bass+lead)",

                    "Pad 1 (new age)", "Pad 2 (warm)", "Pad 3 (polysynth)", "Pad 4 (choir)",
                    "Pad 5 (bowed)", "Pad 6 (metallic)", "Pad 7 (halo)", "Pad 8 (sweep)",

                    "FX 1 (rain)", "FX 2 (soundtrack)", "FX 3 (crystal)", "FX 4 (atmosphere)",
                    "FX 5 (brightness)", "FX 6 (goblins)", "FX 7 (echoes)", "FX 8 (sci-fi)",

                    "Sitar", "Banjo", "Shamisen", "Koto",
                    "Kalimba", "Bagpipe", "Fiddle", "Shanai",

                    "Tinkle Bell", "Agogo", "Steel Drums", "Woodblock",
                    "Taiko Drum", "Melodic Tom", "Synth Drum", "Reverse Cymbal",

                    "Guitar Fret Noise", "Breath Noise", "Seashore", "Bird Tweet",
                    "Telephone Ring", "Helicopter", "Applause", "Gunshot"));
}