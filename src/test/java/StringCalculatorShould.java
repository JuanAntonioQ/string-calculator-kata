import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class StringCalculatorShould {

    /*
     * TO DO LIST
     *
     * "" -> 0
     * "1" -> 1
     * "1,2" -> 3
     * "1.1,2.2" -> 3.3
     * "1\n2,3" -> 6
     * "1,3," -> throw exception
     * "175.2,\n35" -> throw exception
     * "//;\n1;2" -> 3
     * "//|\n1|2|3" -> 6
     * "//sep\n2sep3 -> 5
     * "//|\n1|2,3" -> throw exception
     *
     */

    @Test
    public void return_zero_when_there_are_not_numbers(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("")).isEqualTo("0");
    }

    @Test
    public void return_number_when_receive_a_number(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("1")).isEqualTo("1");
    }

    @Test
    public void return_sum_of_numbers_that_are_separated_by_commas(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("1,2")).isEqualTo("3.0");
        assertThat(stringCalculator.add("1.1,2.2")).isEqualTo("3.3");
    }

    @Test(expected = InvalidNumberException.class)
    public void throw_exception_when_there_is_no_number_in_the_last_position(){
        StringCalculator stringCalculator = new StringCalculator();
        stringCalculator.add("1,3,");
    }

    @Test
    public void return_sum_of_numbers_that_are_separated_by_custom_separators(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("//;\n1;2")).isEqualTo("3.0");
        assertThat(stringCalculator.add("//\\|\n1|2")).isEqualTo("3.0");
        assertThat(stringCalculator.add("//sep\n2sep3")).isEqualTo("5.0");
    }

    @Test
    public void return_sum_of_numbers_that_are_separated_by_newline(){
        StringCalculator stringCalculator = new StringCalculator();
        assertThat(stringCalculator.add("1\n1")).isEqualTo("2.0");
        assertThat(stringCalculator.add("2\n3")).isEqualTo("5.0");
        assertThat(stringCalculator.add("2\n3,2\n2")).isEqualTo("9.0");
    }

    @Test(expected = NegativeNumbersNotAllowedException.class)
    public void throw_exception_when_there_are_negative_numbers(){
        StringCalculator stringCalculator = new StringCalculator();
        stringCalculator.add("1,-2,-5");
        stringCalculator.add("//,\n2,-4,-5");
    }


}
