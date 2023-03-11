package com.terence.hexadecimalcalculator.models;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import  org.junit.jupiter.api.Assertions;


/**
 * @author Terence Lee
 *
 * Contains the unit tests for the various public methods of the HexadecimalCalculator
 * clas
 * */
class HexadecimalCalculatorTest {


    @ParameterizedTest
    @CsvSource({"1234,abcd,be01",  "45a,67b,ad5", "-12,-13c,-14e",
                "12,-13c,-12a", "-12,13c,12a"})
    void add_validParams_correctResult(String firstHexadecimalNumber, String secondHexadecimalNumber,
                                     String expectedHexadecimalResult) {

        String actualHexadecimalResult = HexadecimalCalculator.add(firstHexadecimalNumber,
                                                    secondHexadecimalNumber);

        Assertions.assertEquals(expectedHexadecimalResult, actualHexadecimalResult);

    }


    @ParameterizedTest
    @CsvSource({"8000000000000000,1", "-8000000000000001,1",
            "1,8000000000000000", "1,-8000000000000001",
            "123,1.5", "1.5,123", "Hello,1", "1,Hello"})
    void add_invalidParams_overlyLargeOrInvalidNumber_NumberFormatException(String firstHexadecimalNumber, String secondHexadecimalNumber) {

        Assertions.assertThrows(NumberFormatException.class,

                ()->HexadecimalCalculator.add(firstHexadecimalNumber,
                        secondHexadecimalNumber)
        );

    }


    @ParameterizedTest
    @CsvSource({"7FFFFFFFFFFFFFFF,1", "-8000000000000000,-1"})
    void add_invalidParams_overlyLargeResult_ArithmeticException(String firstHexadecimalNumber, String secondHexadecimalNumber) {

        Assertions.assertThrows(ArithmeticException.class,

                ()->HexadecimalCalculator.add(firstHexadecimalNumber,
                        secondHexadecimalNumber)
        );

    }




    @ParameterizedTest
    @CsvSource({"abcd,1234,9999",  "123,45,de", "-12,-13c,12a",
            "12,-13c,14e", "-12,13c,-14e"})
    void subtract_validParams_correctResult(String firstHexadecimalNumber,
                                            String secondHexadecimalNumber,
                                            String expectedHexadecimalResult) {

        String actualHexadecimalResult =
                HexadecimalCalculator.subtract(firstHexadecimalNumber,
                                                secondHexadecimalNumber);

        Assertions.assertEquals(expectedHexadecimalResult, actualHexadecimalResult);
    }



    @ParameterizedTest
    @CsvSource({"8000000000000000,1", "-8000000000000001,1",
            "1,8000000000000000", "1,-8000000000000001",
            "123,1.5", "1.5,123", "Hello,1", "1,Hello"})
    void subtract_invalidParams_overlyLargeOrInvalidNumber_NumberFormatException(
            String firstHexadecimalNumber, String secondHexadecimalNumber) {

        Assertions.assertThrows(NumberFormatException.class,

                ()->HexadecimalCalculator.subtract(firstHexadecimalNumber,
                        secondHexadecimalNumber)
        );

    }


    @ParameterizedTest
    @CsvSource({"-2,7FFFFFFFFFFFFFFF", "-7FFFFFFFFFFFFFFF,2",
            "1,-8000000000000000", "-8000000000000000,1"})
    void subtract_invalidParams_overlyLargeResult_ArithmeticException(
            String firstHexadecimalNumber, String secondHexadecimalNumber) {

        Assertions.assertThrows(ArithmeticException.class,

                ()->HexadecimalCalculator.subtract(firstHexadecimalNumber,
                        secondHexadecimalNumber)
        );

    }




    @ParameterizedTest
    @CsvSource({"abcd,1234,c374fa4",  "12,ef,10ce", "-12,-13c,1638",
            "12,-13c,-1638", "-12,13c,-1638"})
    void multiply_validParams_correctResult(String firstHexadecimalNumber,
                                            String secondHexadecimalNumber,
                                            String expectedHexadecimalResult) {

        String actualHexadecimalResult =
                HexadecimalCalculator.multiply(firstHexadecimalNumber,
                        secondHexadecimalNumber);

        Assertions.assertEquals(expectedHexadecimalResult, actualHexadecimalResult);
    }



    @ParameterizedTest
    @CsvSource({"8000000000000000,1", "-8000000000000001,1",
            "1,8000000000000000", "1,-8000000000000001",
            "123,1.5", "1.5,123","Hello,1", "1,Hello"})
    void multiply_invalidParams_overlyLargeOrInvalidNumber_NumberFormatException(String firstHexadecimalNumber, String secondHexadecimalNumber) {

        Assertions.assertThrows(NumberFormatException.class,

                ()->HexadecimalCalculator.multiply(firstHexadecimalNumber,
                        secondHexadecimalNumber)
        );

    }



    @ParameterizedTest
    @CsvSource({"-2,7FFFFFFFFFFFFFFF", "-7FFFFFFFFFFFFFFF,2",
            "3,-8000000000000000", "-8000000000000000,3"})
    void multiply_invalidParams_overlyLargeResult_ArithmeticException(String firstHexadecimalNumber, String secondHexadecimalNumber) {

        Assertions.assertThrows(ArithmeticException.class,

                ()->HexadecimalCalculator.multiply(firstHexadecimalNumber,
                        secondHexadecimalNumber)
        );

    }



    @ParameterizedTest
    @CsvSource({"abcd,1234,9",  "4567,ef,4a", "-1234,-13,f5",
            "1234,-13,-f5", "-1234,13,-f5"})
    void divide_validParams_correctResult(String firstHexadecimalNumber,
                                          String secondHexadecimalNumber,
                                        String expectedHexadecimalResult) {

        String actualHexadecimalResult =
                HexadecimalCalculator.divide(firstHexadecimalNumber,
                        secondHexadecimalNumber);

        Assertions.assertEquals(expectedHexadecimalResult, actualHexadecimalResult);
    }




    @ParameterizedTest
    @CsvSource({"8000000000000000,1", "-8000000000000001,1",
            "-8000000000000001,0",
            "1,8000000000000000", "1,-8000000000000001",
            "123,1.5", "1.5,123", "Hello,1", "1,Hello"})
    void divide_invalidParams_overlyLargeOrInvalidNumber_NumberFormatException(String firstHexadecimalNumber, String secondHexadecimalNumber) {

        Assertions.assertThrows(NumberFormatException.class,

                ()->HexadecimalCalculator.divide(firstHexadecimalNumber,
                        secondHexadecimalNumber)
        );

    }


    @ParameterizedTest
    @CsvSource({"abcd,1234,7f9",  "4567,ef,51", "-1234,-13,-5",
            "1234,-13,5", "-1234,13,-5"})
    void modulus_validParams_correctResult(String firstHexadecimalNumber,
                                           String secondHexadecimalNumber,
                                            String expectedHexadecimalResult) {

        String actualHexadecimalResult =
                HexadecimalCalculator.modulus(firstHexadecimalNumber,
                        secondHexadecimalNumber);

        Assertions.assertEquals(expectedHexadecimalResult, actualHexadecimalResult);
    }


    @ParameterizedTest
    @CsvSource({"8000000000000000,1", "-8000000000000001,1",
            "1,8000000000000000", "1,-8000000000000001",
            "123,1.5", "1.5,123", "Hello,1", "1,Hello"})
    void modulus_invalidParams_overlyLargeOrInvalidNumber_NumberFormatException(String firstHexadecimalNumber, String secondHexadecimalNumber) {

        Assertions.assertThrows(NumberFormatException.class,

                ()->HexadecimalCalculator.modulus(firstHexadecimalNumber,
                        secondHexadecimalNumber)
        );

    }


    @ParameterizedTest
    @CsvSource({"abcd,43981",  "1234,4660", "-345,-837",
            "-af2,-2802"})
    void convertHexadecimalToDecimal_validParam_correctResult(
                                    String hexadecimalNumber,
                                     long expectedDecimalResult) {

        long actualDecimalResult =
                HexadecimalCalculator.convertHexadecimalToDecimal(hexadecimalNumber);

        Assertions.assertEquals(expectedDecimalResult, actualDecimalResult);
    }


    @ParameterizedTest
    @CsvSource({"8000000000000000", "-8000000000000001",
            "1.5", "Hello"})
    void convertHexadecimalToDecimal_invalidParams_overlyLargeOrInvalidNumber_NumberFormatException(
            String hexadecimalNumber) {

        Assertions.assertThrows(NumberFormatException.class,

                ()->HexadecimalCalculator.convertHexadecimalToDecimal(hexadecimalNumber)
        );

    }
}