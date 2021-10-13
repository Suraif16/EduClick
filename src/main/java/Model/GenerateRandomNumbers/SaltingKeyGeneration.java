package Model.GenerateRandomNumbers;

import java.util.Random;

public class SaltingKeyGeneration {

    public String generateSaltingKey( int length ){

        StringBuilder SaltingKey = new StringBuilder();

        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String simpleLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*_-=+/.?<>";

        String possibleCharacters = capitalLetters + simpleLetters + numbers + symbols;

        Random random = new Random();

        for ( int i = 0; i < length ; i++){

            /* Here we select a random number between zero (inclusive) and length of possibleCharacters (exclusive)
             * , then using this random number we select a character at than index in possibleCharacters
             * and we append this character to the OTP */
            SaltingKey.append( possibleCharacters.charAt( random.nextInt( possibleCharacters.length() ) ) );

        }

        return SaltingKey.toString();
    }

}
