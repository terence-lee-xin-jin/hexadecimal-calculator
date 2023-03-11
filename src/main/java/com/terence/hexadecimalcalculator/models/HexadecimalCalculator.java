package com.terence.hexadecimalcalculator.models;

/**
 * @author Terence Lee
 *
 * A simple class that perform arithmetic operations for hexadecimal numbers
 *
 * E.g.
 * <pre>
      String hexadecimalAddResult = HexadecimalCalculator.add("9", "A");

      System.out.println(hexadecimalAddResult); // 13

 * </pre>
 *
 * */
public class HexadecimalCalculator {

    //max allowable value for the add, subtract, multiply, divide arguments
    //without throwing NumberFormatException
    public static final String MAX_OPERAND_VALUE_INCLUSIVE = "7FFFFFFFFFFFFFFF";


    //min allowable value for the add, subtract, multiply, divide arguments
    //without throwing NumberFormatException
    public static final String MIN_OPERAND_VALUE_INCLUSIVE = "-8000000000000000";


    /**
     * The constructor is made private as all methods of this class are static, and
     * therefore the constructor is not required
     * */
    private HexadecimalCalculator() {

    }


    /**
     * Returns the sum of the two hexadecimal numbers in hexadecimal
     *
     * @param firstHexadecimalNumber the first value in hexadecimal
     * @param secondHexadecimalNumber the second value in hexadecimal
     *
     * @throws NumberFormatException if either argument do not represent a valid long number
     *
     * @throws ArithmeticException if result overflows a long
     *
     * @return a result of the subtraction in hexadecimal
     * */
    public static String add(String firstHexadecimalNumber, String secondHexadecimalNumber){

        long firstDecimalNumber = convertHexadecimalToDecimal(firstHexadecimalNumber);
        long secondDecimalNumber = convertHexadecimalToDecimal(secondHexadecimalNumber);

        long decimalResult = Math.addExact(firstDecimalNumber, secondDecimalNumber);
        String hexadecimalResult = convertDecimalToHexadecimal(decimalResult);



        return hexadecimalResult;
    }


    /**
     * Returns the difference of the two hexadecimal numbers in hexadecimal
     *
     * @param firstHexadecimalNumber the first value in hexadecimal
     * @param secondHexadecimalNumber the second value in hexadecimal
     *
     * @throws NumberFormatException if either argument do not represent a valid long number
     *
     * @throws ArithmeticException if result overflows a long
     *
     * @return a result of the subtraction in hexadecimal
     * */
    public static String subtract(String firstHexadecimalNumber, String secondHexadecimalNumber){

        long firstDecimalNumber = convertHexadecimalToDecimal(firstHexadecimalNumber);
        long secondDecimalNumber = convertHexadecimalToDecimal(secondHexadecimalNumber);

        long decimalResult = Math.subtractExact(firstDecimalNumber, secondDecimalNumber);
        String hexadecimalResult = convertDecimalToHexadecimal(decimalResult);



        return hexadecimalResult;
    }



    /**
     * Multiply two hexadecimal numbers, and return the result in hexadecimal
     *
     * @param firstHexadecimalNumber the first value in hexadecimal
     * @param secondHexadecimalNumber the second value in hexadecimal
     *
     * @throws NumberFormatException if either argument do not represent a valid long number
     *
     * @throws ArithmeticException if result overflows a long
     *
     * @return a result of the multiplication in hexadecimal
     * */
    public static String multiply(String firstHexadecimalNumber, String secondHexadecimalNumber){

        long firstDecimalNumber = convertHexadecimalToDecimal(firstHexadecimalNumber);
        long secondDecimalNumber = convertHexadecimalToDecimal(secondHexadecimalNumber);

        long decimalResult = Math.multiplyExact(firstDecimalNumber, secondDecimalNumber);
        String hexadecimalResult = convertDecimalToHexadecimal(decimalResult);

        return hexadecimalResult;
    }


    /**
     * Returns the quotient of the two hexadecimal number in hexadecimal
     *
     * @param firstHexadecimalNumber the dividend in hexadecimal
     * @param secondHexadecimalNumber the divisor in hexadecimal
     *
     * @throws NumberFormatException if either argument do not represent a valid long number
     *
     * @throws ArithmeticException if the second argument is zero("0"),
     *          or if result overflows a long,
     *
     * @return a result of the subtraction in hexadecimal
     * */
    public static String divide(String firstHexadecimalNumber, String secondHexadecimalNumber){

        long firstDecimalNumber = convertHexadecimalToDecimal(firstHexadecimalNumber);
        long secondDecimalNumber = convertHexadecimalToDecimal(secondHexadecimalNumber);

        long decimalQuotientResult = Math.divideExact(firstDecimalNumber, secondDecimalNumber);

        String hexadecimalQuotientResult = convertDecimalToHexadecimal(decimalQuotientResult);

        return hexadecimalQuotientResult;
    }



    /**
     * Returns the remainder(modulus) of the two hexadecimal number in hexadecimal
     *
     * @param firstHexadecimalNumber the dividend in hexadecimal
     * @param secondHexadecimalNumber the divisor in hexadecimal
     *
     * @throws NumberFormatException if either argument do not represent a valid long number
     *
     * @throws ArithmeticException if the second argument is zero("0"),
     *          or if result overflows a long,
     *
     * @return a result of the subtraction in hexadecimal
     * */
    public static String modulus(String firstHexadecimalNumber, String secondHexadecimalNumber){

        long firstDecimalNumber = convertHexadecimalToDecimal(firstHexadecimalNumber);
        long secondDecimalNumber = convertHexadecimalToDecimal(secondHexadecimalNumber);


        long decimalRemainderResult = firstDecimalNumber % secondDecimalNumber;

        String hexadecimalRemainderResult = convertDecimalToHexadecimal(decimalRemainderResult);

        return hexadecimalRemainderResult;
    }



    /**
     * Converts a hexadecimal number to a decimal number
     *
     * @param hexadecimalNumber the hexadecimal number to be converted to be decimal number
     *
     * @throws NumberFormatException if the argument does not represent a valid hexadecimal number
     * */
    public static long convertHexadecimalToDecimal(String hexadecimalNumber)
    {
        final int HEXADECIMAL_RADIX = 16;

        return Long.parseLong(hexadecimalNumber, HEXADECIMAL_RADIX);
    }


    /**
     * Converts a decimal number to a hexadecimal number
     *
     * @param decimalNumber the decimal number to be converted to be hexadecimal number
     *
     * */
    private static String convertDecimalToHexadecimal(long decimalNumber){

        long unsignedDecimalNumber = decimalNumber;

        if (decimalNumber < 0){
            unsignedDecimalNumber = Math.abs(decimalNumber);
        }

        String hexadecimalNumber = Long.toHexString(unsignedDecimalNumber);


        if (decimalNumber < 0){
            hexadecimalNumber = "-" + hexadecimalNumber;
        }

        return hexadecimalNumber;
    }
}
