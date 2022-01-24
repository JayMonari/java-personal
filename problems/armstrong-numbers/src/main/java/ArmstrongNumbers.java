class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {
        int sum = 0;
        int copy = numberToCheck;
        int exponent = Integer.toString(numberToCheck).length();
        int remainder;
        while (copy > 0) {
            remainder = copy % 10;
            copy /= 10;
            sum += Math.pow(remainder, exponent);
        }
        return sum == numberToCheck;
    }
}
