package com.zanclus.interview.morse.mismatch;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by dphillips on 4/25/16.
 */
public class MainTest {

    @Test
    public void endToEndTestA() {
        String input =
                "A       .-\n" +
                "B       -...\n" +
                "C       -.-.\n" +
                "D       -..\n" +
                "E       .\n" +
                "F       ..-.\n" +
                "G       --.\n" +
                "H       ....\n" +
                "I       ..\n" +
                "J       .---\n" +
                "K       -.-\n" +
                "L       .-..\n" +
                "M       --\n" +
                "N       -.\n" +
                "O       ---\n" +
                "P       .--.\n" +
                "Q       --.-\n" +
                "R       .-.\n" +
                "S       ...\n" +
                "T       -\n" +
                "U       ..-\n" +
                "V       ...-\n" +
                "W       .--\n" +
                "X       -..-\n" +
                "Y       -.--\n" +
                "Z       --..\n" +
                "0       ------\n" +
                "1       .-----\n" +
                "2       ..---\n" +
                "3       ...--\n" +
                "4       ....-\n" +
                "5       .....\n" +
                "6       -....\n" +
                "7       --...\n" +
                "8       ---..\n" +
                "9       ----.\n" +
                "*\n" +
                "AN\n" +
                "EARTHQUAKE\n" +
                "EAT\n" +
                "GOD\n" +
                "HATH\n" +
                "IM\n" +
                "EW\n" +
                "READY\n" +
                "TO\n" +
                "WHAT\n" +
                "WROTH\n" +
                "*\n" +
                ".--.....--   .....--....\n" +
                "--.----..   .--.-.----..\n" +
                ".--.....--   .--.\n" +
                "..-.-.-....--.-..-.--.-.\n" +
                "..--   .-...--..-.--\n" +
                "----        ..--\n" +
                "*\n";
        String[] lines = input.split("\n");
        Main main = new Main(lines);
        System.out.println();
        System.out.println();
        String result = main.init();
        assertEquals("Result MUST match expected output", "WHAT\n" +
                "HATH\n" +
                "GOD\n" +
                "WROTH?\n" +
                "WHAT\n" +
                "AN\n" +
                "EARTHQUAKE\n" +
                "IM!\n" +
                "READY\n" +
                "TO\n" +
                "IM!".trim(), result.trim());
    }

    @Test
    public void endToEndTestB() {
        String input =
                "A       .-\n" +
                "B       -...\n" +
                "C       -.-.\n" +
                "D       -..\n" +
                "E       .\n" +
                "F       ..-.\n" +
                "G       --.\n" +
                "H       ....\n" +
                "I       ..\n" +
                "J       .---\n" +
                "K       -.-\n" +
                "L       .-..\n" +
                "M       --\n" +
                "N       -.\n" +
                "O       ---\n" +
                "P       .--.\n" +
                "Q       --.-\n" +
                "R       .-.\n" +
                "S       ...\n" +
                "T       -\n" +
                "U       ..-\n" +
                "V       ...-\n" +
                "W       .--\n" +
                "X       -..-\n" +
                "Y       -.--\n" +
                "Z       --..\n" +
                "0       ------\n" +
                "1       .-----\n" +
                "2       ..---\n" +
                "3       ...--\n" +
                "4       ....-\n" +
                "5       .....\n" +
                "6       -....\n" +
                "7       --...\n" +
                "8       ---..\n" +
                "9       ----.\n" +
                "*\n" +
                "AN\n" +
                "EARTHQUAKE\n" +
                "EAT\n" +
                "GOD\n" +
                "HATH\n" +
                "IM\n" +
                "READY\n" +
                "TO\n" +
                "WHAT\n" +
                "WROTH\n" +
                "*\n" +
                ".--.....--   .....--....\n" +
                "--.----..   .--.-.----..\n" +
                ".--.....--   .--.\n" +
                "..-.-.-....--.-..-.--.-.\n" +
                "..--   .-...--..-.--\n" +
                "----        ..--\n" +
                "*\n";
        String[] lines = input.split("\n");
        Main main = new Main(lines);
        System.out.println();
        System.out.println();
        String result = main.init();
        assertEquals("Result MUST match expected output", "WHAT\n" +
                "HATH\n" +
                "GOD\n" +
                "WROTH?\n" +
                "WHAT\n" +
                "AN\n" +
                "EARTHQUAKE\n" +
                "IM\n" +
                "READY\n" +
                "TO\n" +
                "IM".trim(), result.trim());
    }
}