package rules;

public interface RegForm {
    String idNumber();
    User user();
    String organization();
    int money();
    void refill (int money);
    boolean payment (int money);
}
