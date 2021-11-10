class CustomCheckedException extends Exception {
    private static final long serialVersionUID = 123095890L;

    CustomCheckedException() {
        super();
    }

    CustomCheckedException(String message) {
        super(message);
    }
}
