/**
 * This class models the 'Bombe' device.
 * The Bombe would be used to decode messages which have been encoded by an enigma machine when not all the settings
 * of the machine are known. This class includes methods to decode for the three specific examples outlined in the
 * coursework specification.
 */
public class Bombe {
    /**
     * Method to solve the first Bombe challenge
     * Iterates through every possible combination of two plugs where one plug has an end 'D' and the other has
     * an end 'S'. The other settings of the EnigmaMachine are predefined. Any result containing the word 'ANSWER'
     * will be printed to the console along with the plugs that were used to produce this result.
     * @throws Exception - Thrown if any part of the enigma machine encounters a problem
     */
    public void test1() throws Exception{
        EnigmaMachine enigmaMachine = new EnigmaMachine();
        enigmaMachine.addRotor(new BasicRotor("IV"), 0);
        enigmaMachine.addRotor(new BasicRotor("III"), 1);
        enigmaMachine.addRotor(new BasicRotor("II"), 2);
        enigmaMachine.addReflector(new Reflector());
        enigmaMachine.getReflector().initialise("ReflectorI");
        String input = "JBZAQVEBRPEVPUOBXFLCPJQSYFJI";
        StringBuilder result = new StringBuilder();
        char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        for(int i=0;i<26;i++){
            for(int j=0;j<26;j++){
                result.delete(0, result.length());
                enigmaMachine.getRotor(0).setPosition(8);
                enigmaMachine.getRotor(1).setPosition(4);
                enigmaMachine.getRotor(2).setPosition(21);
                enigmaMachine.clearPlugboard();
                enigmaMachine.addPlug('D', alphabet[j]);
                enigmaMachine.addPlug('S', alphabet[i]);
                for(char character:input.toCharArray()){
                    result.append(enigmaMachine.encodeLetter(character));
                }
                if(result.toString().contains("ANSWER")){
                    System.out.println(result.toString() + " - Plugs D"+alphabet[j]+", S"+alphabet[i]);
                }
            }
        }
    }

    /**
     * Method to solve the second Bombe challenge.
     * Iterates through all possible starting positions for the three rotors. The other settings for the Enigma Machine
     * are predefined. Prints any results which contain the word 'ELECTRIC' to the console along with the starting
     * positions used to produce this result.
     * @throws Exception Thrown if any part of the Enigma Machine encounters a problem.
     */
    public void test2() throws Exception{
        EnigmaMachine enigmaMachine = new EnigmaMachine();
        enigmaMachine.addPlug('H', 'L');
        enigmaMachine.addPlug('G', 'P');
        enigmaMachine.addRotor(new BasicRotor("V"), 0);
        enigmaMachine.addRotor(new BasicRotor("III"), 1);
        enigmaMachine.addRotor(new BasicRotor("II"), 2);
        enigmaMachine.addReflector(new Reflector());
        enigmaMachine.getReflector().initialise("ReflectorI");
        String input = "AVPBLOGHFRLTFELQEZQINUAXHTJMXDWERTTCHLZTGBFUPORNHZSLGZMJNEINTBSTBPPQFPMLSVKPETWFD";
        StringBuilder result = new StringBuilder();
        for(int i=0;i<26;i++){
            for(int j=0;j<26;j++){
                for(int k=0;k<26;k++){
                    result.delete(0, result.length());
                    enigmaMachine.setPosition(0, i);
                    enigmaMachine.setPosition(1, j);
                    enigmaMachine.setPosition(2, k);
                    for(char character : input.toCharArray()){
                        result.append(enigmaMachine.encodeLetter(character));
                    }
                    if(result.toString().contains("ELECTRIC")){
                        System.out.println(result.toString() + " - Positions " +i+", "+j+", "+k);
                    }
                }
            }
        }
    }

    /**
     * Method to solve the third bombe challenge.
     * Iterates through the possible rotor types for the 3 rotors. All the other enigma machine settings are predefined.
     * Prints any results which contain the word 'JAVA' to the console along with the rotor types used.
     * @throws Exception Thrown if any part of the enigma machine encounters a problem
     */
    public void test3() throws Exception{
        EnigmaMachine enigmaMachine = new EnigmaMachine();
        enigmaMachine.addPlug('M', 'F');
        enigmaMachine.addPlug('O', 'I');
        enigmaMachine.addReflector(new Reflector());
        enigmaMachine.getReflector().initialise("ReflectorI");
        String[] types = new String[]{"I", "II", "III", "IV", "V"};
        String input = "WMTIOMNXDKUCQCGLNOIBUYLHSFQSVIWYQCLRAAKZNJBOYWW";
        StringBuilder result = new StringBuilder();
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                for(int k=0;k<5;k++){
                    result.delete(0, result.length());
                    enigmaMachine.addRotor(new BasicRotor(types[i]), 0);
                    enigmaMachine.addRotor(new BasicRotor(types[j]), 1);
                    enigmaMachine.addRotor(new BasicRotor(types[k]), 2);
                    enigmaMachine.setPosition(0, 22);
                    enigmaMachine.setPosition(1, 24);
                    enigmaMachine.setPosition(2, 23);
                    for(char character : input.toCharArray()){
                        result.append(enigmaMachine.encodeLetter(character));
                    }
                    if(result.toString().contains("JAVA")){
                        System.out.println(result.toString() + " - Types " +types[i]+", "+types[j]+", "+types[k]);
                    }
                }
            }
        }
    }
}
