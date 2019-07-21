
import java.util.InputMismatchException;
import java.util.Scanner;

public class Service {

    private Client client = new Client();
    private Bank bank = new Bank();
    private Atm atm = new Atm();

    private void checkBalance() {
        System.out.println(bank.getBalance());
    }

    private void takeMoney() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input '1' to take 100");
        System.out.println("Input '2' to take 50");
        System.out.println("Input '3' to take 20");
        System.out.println("Input '4' to take another sum");
        System.out.println("Input 'exit' to exit");
        String string = in.nextLine();
        switch (string) {
            case "1":
                if (atm.checkSum(100) && bank.setBalance(-100))
                    atm.giveMoney(100);
                break;
            case "2":
                if (atm.checkSum(50) && bank.setBalance(-50))
                    atm.giveMoney(50);
                break;
            case "3":
                if (atm.checkSum(20) && bank.setBalance(-20))
                    atm.giveMoney(20);
                break;
            case "4":
                System.out.println("Input int sum");
                int sum = 0;
                try {
                    sum = in.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println(ex.getMessage());
                }
                if (atm.checkSum(sum) && bank.setBalance(-sum))
                    atm.giveMoney(sum);
                break;
        }


    }

    private void topUpBalance() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input sum");
        int sum = 0;
        try {
            sum = in.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println(ex.getMessage());
        }
        bank.setBalance(sum);
    }

    public void functionMenu() {
        System.out.println("Input 'check' to check balance");
        System.out.println("Input 'take' to take money");
        System.out.println("Input 'top up' to top up balance");
        System.out.println("Input 'exit' to exit");
        Scanner in = new Scanner(System.in);
        String string = in.nextLine();

        switch (string) {
            case "check":
                checkBalance();
                functionMenu();
                break;
            case "take":
                takeMoney();
                functionMenu();
                break;
            case "top up":
                topUpBalance();
                functionMenu();
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect input");
                functionMenu();
                break;
        }

    }

    public static void main(String[] args) {
        Service service = new Service();
        service.client.inputNumberOfCard();
        if (service.bank.cardIsValid(service.client.creditCard.getNumberOfCard()) && !service.bank.isLocked()) {
            int count = 0;
            while (count < 3) {
                count++;
                service.client.inputPin();
                if (service.bank.checkPin(service.client.creditCard.getPin())) {
                    service.functionMenu();
                    break;
                }
            }
            service.bank.lockedCard();
        }
    }
}
