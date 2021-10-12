package Model.GenerateOTP;

import java.util.Random;

public class OTPGeneration {


    public String generateOTP( int length ){

        /* In java String is immutable white StringBuilder is mutable
        * therefore to build we use StringBuilder */

        StringBuilder OTP = new StringBuilder();

        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String simpleLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String possibleCharacters = capitalLetters + simpleLetters + numbers ;

        Random random = new Random();

        for ( int i = 0; i < length ; i++){

            /* Here we select a random number between zero (inclusive) and length of possibleCharacters (exclusive)
            * , then using this random number we select a character at than index in possibleCharacters
            * and we append this character to the OTP */
            OTP.append( possibleCharacters.charAt( random.nextInt( possibleCharacters.length() ) ) );

        }

        return OTP.toString();
    }

}
