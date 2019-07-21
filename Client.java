import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {

    public CreditCard creditCard = new CreditCard();

    public void inputNumberOfCard() {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{4}-\\d{4}-\\d{4}");
        Scanner in = new Scanner(System.in);
        System.out.print("Input number of card ХХХХ-ХХХХ-ХХХХ-ХХХХ ");
        String number = in.nextLine();
        Matcher matcher = pattern.matcher(number);
        if (matcher.find()) {
            creditCard.setNumberOfCard(number);
        } else {
            System.out.println("Incorrect ");
            inputNumberOfCard();
        }
    }

    public void inputPin() {
        Pattern pattern = Pattern.compile("\\d{4}");
        Scanner in = new Scanner(System.in);
        System.out.print("Input pin of card");
        String pin = in.nextLine();
        Matcher matcher = pattern.matcher(pin);
        if (matcher.find()) {
            creditCard.setPin(pin);
        } else {
            System.out.println("Incorrect ");
            inputPin();
        }
    }


}
