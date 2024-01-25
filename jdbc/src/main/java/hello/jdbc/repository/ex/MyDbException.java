package hello.jdbc.repository.ex;

public class MyDbException extends RuntimeException {

    public MyDbException() {
    }

    public MyDbException(String message) {
        super(message);
    }

    // 감쌌기 때문에, 원인을 꼭 들고 와야 한다.
    public MyDbException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDbException(Throwable cause) {
        super(cause);
    }


}
