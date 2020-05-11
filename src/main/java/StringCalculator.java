

public class StringCalculator {

    public String add(String numbers) {
        boolean thereAreNoNumbers = numbers.isEmpty();
        if(thereAreNoNumbers){
            return "0";
        }
        if(numbers.contains(",")) {
            String[] inputNumbers = numbers.split(",");
            float sum = 0;
            for (int i = 0; i < inputNumbers.length; i++) {
                sum += Float.parseFloat(inputNumbers[i]);
            }
            return Float.toString(sum).substring(0, 3);
        }
        String result = numbers;
        return result;
    }
}
