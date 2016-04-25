package com.zanclus.interview.morse.mismatch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dphillips on 4/21/16.
 */
public class Main {

    private static final int MORSE_TABLE = 0;
    private static final int DICTIONARY = 1;
    private static final int CYPHERTEXT = 2;
    public static final int MAX_LINE_LENGTH = 80;

    private Map<String, String> morseMap = new HashMap<>();
    private Map<String, ArrayList<String>> dictionary = new HashMap<>();
    private List<String> wordList = new ArrayList<>();

    /**
     * Entry-point for the application
     * @param argv The command-line arguments
     * @throws IOException If there is a problem reading the input file.
     */
    static void main(String... argv) throws IOException {
        String[] lines = null;
        Files.lines(Paths.get(argv[0])).collect(Collectors.toList()).toArray(lines);
        Main main = new Main(lines);
    }

    /**
     *
     * @param lines
     * @throws IOException
     */
    public Main(String[] lines) {
        System.out.print(loadInput(lines));
    }

    /**
     * Main body of the program.
     */
    String init() {
        StringBuilder output = new StringBuilder();
        output.setLength(0);
        for (String word: wordList) {
            boolean hasMultipleMatches = false;
            boolean hasAmbiguousMatch = false;
            String bestMatch = null;
            List<String> matches = findExactMatches(word);
            if (matches.size()==1) {
                bestMatch = matches.get(0);
            } else if (matches.size()>1) {  // One or more exact matches were found.
                // Multiple matches found. Check to see if there is one which is shorted than the others
                List<String> multipleSameLengthMatches = findLongestMatch(matches);
                bestMatch = multipleSameLengthMatches.get(0);
                if (multipleSameLengthMatches.size()>1) {
                    hasMultipleMatches = true;
                }
            } else {   // No exact match was found, find longest matching substring
                String longestMatch = findBestMatch(word);
                bestMatch = dictionary.get(longestMatch).get(0);
                hasAmbiguousMatch = true;
            }
            output.append(bestMatch);
            if (hasMultipleMatches) {
                output.append("!");
            }
            if (hasAmbiguousMatch) {
                output.append("?");
            }
            output.append("\n");
        }
        return output.toString();
    }

    List<String> findLongestMatch(List<String> matches) {
        int shortest = MAX_LINE_LENGTH;
        List<String> multipleSameLengthMatches = new ArrayList<>();
        for (String item: matches) {   // Iterate through matches
            if (item.length()==shortest) {   // If a match is equal to shortest, append it to the list
                multipleSameLengthMatches.add(item);
            } else if (item.length() < shortest) {  // If match is shorter, clear the list and add the new item
                multipleSameLengthMatches.clear();
                multipleSameLengthMatches.add(item);
                shortest = item.length();
            }
        }
        return multipleSameLengthMatches;
    }

    String findBestMatch(String word) {
        String longestMatch = null;
        int longestMatchLen = 0;
        for (Map.Entry<String, ArrayList<String>> entry: dictionary.entrySet()) {
            String morse = entry.getKey();
            int matchLength = findLongestMatchingSubstring(word, morse);
            if (longestMatch==null) {
                longestMatch = entry.getKey();
                longestMatchLen = matchLength;
            } else {
                if (matchLength>longestMatchLen) {
                    longestMatch = entry.getKey();
                    longestMatchLen = matchLength;
                }
            }
        }
        return longestMatch;
    }

    List<String> findExactMatches(String word) {
        List<String> matches = new ArrayList<>();
        for (Map.Entry<String, ArrayList<String>> entry: dictionary.entrySet()) {
            String morse = entry.getKey();
            if (morse.contentEquals(word)) {
                matches.addAll(entry.getValue().stream().collect(Collectors.toList()));
            }
        }
        return matches;
    }

    /**
     * Load and parse the input
     * @param lines A {@link Stream} of Strings which represents the lines of the input data.
     * @throws IOException
     */
    String loadInput(String[] lines) {
        int state = MORSE_TABLE;

        for (String line: lines) {
            switch(state) {
                case MORSE_TABLE:
                    if (line.trim().contentEquals("*")) {
                        state = DICTIONARY;
                    } else {
                        String letter = line.substring(0,1);
                        String morse = line.substring(1).trim();
                        morseMap.put(letter, morse);
                    }
                    break;
                case DICTIONARY:
                    if (line.trim().contentEquals("*")) {
                        state = CYPHERTEXT;
                    } else if (line.trim().length()==0) {
                        break;
                    } else {
                        String word = line.trim();
                        convertWordToMorse(word);
                    }
                    break;
                case CYPHERTEXT:
                    if (line.trim().contentEquals("*")) {
                        return init();
                    } else {
                        if (line.trim().length()>0) {
                            for (String item: line.trim().split(" ")) {
                                if (item.trim().length()>0) {
                                    wordList.add(item.trim());
                                }
                            }
                        }
                    }
                    break;
                default:
                    System.err.println("Unknown state!");
            }
        }
        return null;
    }

    /**
     * Given a word, convert the word to Morse and store the plaintext and cyphertext in a map
     * @param word The input word from the dictionary
     */
    void convertWordToMorse(String word) {
        StringBuilder sb = new StringBuilder();
        for (char letter: word.toCharArray()) {
            String strLetter = Character.toString(letter);
            sb.append(morseMap.get(strLetter));
        }
        String morse = sb.toString();
        if (dictionary.get(morse)==null) {
            dictionary.put(morse, new ArrayList<>());
        }
        dictionary.get(morse).add(word);
    }

    int findLongestMatchingSubstring(String inputMorse, String dictionaryMorse) {
        int retVal = 0;
        for (int x=0; x<inputMorse.length(); x++) {
            if (inputMorse.length()>=(x+1) && dictionaryMorse.length()>=(x+1)) {
                return retVal;
            } else if (inputMorse.charAt(x)==dictionaryMorse.charAt(x)) {
                retVal++;
            } else {
                return retVal;
            }
        }
        return retVal;
    }
}
