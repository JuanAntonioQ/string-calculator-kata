public class StringCalculator {

    public String add(String inputs) {
        boolean thereAreNoNumbers = inputs.isEmpty();
        if(thereAreNoNumbers){
            return "0";
        }
        return calculateSumOf(inputs);
    }

    private String calculateSumOf(String inputs) {
        boolean isPrepareToUseCustomSeparators = inputs.indexOf("//") == 0;
        if(isPrepareToUseCustomSeparators){
            return calculateSumWhenDelimiterIsACustomSeparator(inputs);
        }

        if(inputs.contains("\n")){
            return calculateSumWhenDelimiterIsANewLine(inputs);
        }

        if(inputs.contains(",")) {
            return calculateSumWhenDelimiterIsComma(inputs);
        }

        return inputs;
    }

    private String calculateSumWhenDelimiterIsACustomSeparator(String inputs) {
        String delimiter = inputs.split("\n")[0].replace("/", "");
        String numbers = inputs.split("\n")[1].replace("/", "");
        String[] inputNumbers = numbers.split(delimiter);
        checkNegativeNumbers(inputNumbers);
        return calculateSum(inputNumbers);
    }

    private String calculateSumWhenDelimiterIsANewLine(String inputs) {
        String[] inputNumbers = inputs.split("\n");
        String numbers = "";
        for (int i = 0; i < inputNumbers.length; i++) {
            if (inputNumbers[i].contains(",")){
                numbers += calculateSum(inputNumbers[i].split(",")) + " ";
            } else {
                numbers += inputNumbers[i] + " ";
            }
        }
        numbers = numbers.trim();
        checkNegativeNumbers(numbers.split(" "));
        return calculateSum(numbers.split(" "));
    }

    private String calculateSumWhenDelimiterIsComma(String inputs) {
        String lastInput = inputs.substring(inputs.length() - 1);
        checkLastInput(lastInput);
        String[] inputNumbers = inputs.split(",");
        checkNegativeNumbers(inputNumbers);
        return calculateSum(inputNumbers);
    }

    private String calculateSum(String[] inputNumbers) {
        float sum = 0;
        for (String inputNumber : inputNumbers) {
            sum += Float.parseFloat(inputNumber);
        }
        return Float.toString(sum).substring(0, 3);
    }

    public void checkNegativeNumbers(String[] numbers){
        String negativeNumbers = "";
        for (int i = 0; i < numbers.length; i++) {
            if(Float.parseFloat(numbers[i]) < 0){
                negativeNumbers += numbers[i] + ",";
            }
        }

        if(negativeNumbers.length() > 0){
            throw new NegativeNumbersNotAllowedException("Negatives not allowed: " + negativeNumbers);
        }
    }

    public void checkLastInput(String input){
        try{
            Integer.parseInt(input);
        } catch(NumberFormatException exception){
            throw new InvalidNumberException("Number expected but EOF found");
        }
    }
}
