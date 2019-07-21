import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bank {

    private String dataOfCard;

    public boolean cardIsValid(String numberOfCard) {

        Pattern pattern = Pattern.compile(numberOfCard + "\\s");
        try (FileReader reader = new FileReader("Data.txt")) {
            Scanner in = new Scanner(reader);
            while (in.hasNextLine()) {
                String c = in.nextLine();
                Matcher matcher = pattern.matcher(c);
                if (matcher.find()) {
                    dataOfCard = c;
                    return true;
                }
            }


        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        System.out.println("Card is not valid");
        return false;
    }

    public boolean checkPin(String pin) {

        Pattern pattern = Pattern.compile("\\s" + pin + "\\s");


        Matcher matcher = pattern.matcher(dataOfCard);
        if (matcher.find()) {
            return true;
        }
        System.out.println("Wrong pin");
        return false;
    }

    public boolean isLocked() {
        unlockedCard();
        Pattern pattern = Pattern.compile("\\sl\\s");


        Matcher matcher = pattern.matcher(dataOfCard);
        if (matcher.find()) {
            System.out.println("Card is locked");
            return true;
        }

        return false;
    }

    public double getBalance() {
        Pattern pattern = Pattern.compile("\\d+[.].*");


        Matcher matcher = pattern.matcher(dataOfCard);
        matcher.find();
        return Double.valueOf(matcher.group());

    }

    public boolean setBalance(int sum) {
        if (sum <= 1000000 && sum >= -getBalance()) {
            saveData(dataOfCard.replaceAll(String.valueOf(getBalance()), String.valueOf(getBalance() + sum)));
            dataOfCard = dataOfCard.replaceAll(String.valueOf(getBalance()), String.valueOf(getBalance() + sum));
            System.out.println("Operation was successful");
            return true;
        } else if (sum > 1000000) {
            System.out.println("Value exceeds valid value");
            return false;
        } else {
            System.out.println("Balance < sum");
            return false;
        }
    }

    public void lockedCard() {

        saveData(dataOfCard.replace('u', 'l'));
        dataOfCard = dataOfCard.replace('u', 'l');
        System.out.println("CreditCard was locked");

    }

    private void unlockedCard() {

        saveData(dataOfCard.replace('l', 'u'));
        dataOfCard = dataOfCard.replace('l', 'u');

    }

    private void saveData(String newDataOfCard) {
        try {
            Path path = Paths.get("Data.txt");
            Charset charset = StandardCharsets.UTF_8;
            String content = new String(Files.readAllBytes(path), charset);
            content = content.replaceAll(dataOfCard, newDataOfCard);
            Files.write(path, content.getBytes());
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

}
