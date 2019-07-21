public class CreditCard {
    private String numberOfCard;
    private String pin;
    private double balance;
    private boolean isValid;
    private boolean isLocked;

    public String getNumberOfCard() {
        return numberOfCard;
    }

    public void setNumberOfCard(String numberOfCard) {
        this.numberOfCard = numberOfCard;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setIsLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public String getPin() {
        return pin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }


}
