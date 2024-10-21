package lab1;

/**
 * lab1.Result will be calculated until the invalid point.
 */
class DivisionByZeroException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Invalid input - two points with the same x coordinates";
    }
}
