class CustomUncheckedException extends RuntimeException {
    private static final long serialVersionUID = 912834980L;

    CustomUncheckedException() {
        super();
    }

    CustomUncheckedException(String message) {
        super(message);
    }
}
