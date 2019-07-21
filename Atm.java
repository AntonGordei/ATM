import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Atm {

    private List<Integer> banknotes = new ArrayList<Integer>();

    private double balance;

    Atm() {
        getBanknotes();
        getBalanceFromFile();
    }

    private void getBanknotes() {
        try {
            Scanner scanner = new Scanner(new File("Balance.txt"));
            while (scanner.hasNextInt()) {
                try {
                    banknotes.add(scanner.nextInt());
                } catch (NumberFormatException ex) {
                    continue;
                }
            }

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }


    }

    private void getBalanceFromFile() {
      try {
          balance = banknotes.get(0) * 50 + banknotes.get(1) * 20 + banknotes.get(2) * 10 + banknotes.get(3) * 5;
      }catch ( IndexOutOfBoundsException ex){
          System.out.println(ex.getMessage());
      }
    }

    private void saveData() {
        try {
            File file = new File("Balance.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(banknotes.get(0) + " " + banknotes.get(1) + " " + banknotes.get(2) + " " + banknotes.get(3));
            writer.flush();
            writer.close();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    public void giveMoney(int sum) {
        if (checkSum(sum)) {
            while (sum > 0) {
                if (sum - 50 >= 0 && banknotes.get(0) > 0) {
                    sum -= 50;
                    banknotes.set(0, banknotes.get(0) - 1);
                    continue;
                }
                if (sum - 20 >= 0 && banknotes.get(1) > 0) {
                    sum -= 20;
                    banknotes.set(1, banknotes.get(1) - 1);
                    continue;
                }
                if (sum - 10 >= 0 && banknotes.get(2) > 0) {
                    sum -= 10;
                    banknotes.set(2, banknotes.get(2) - 1);
                    continue;
                }
                if (sum - 5 >= 0 && banknotes.get(3) > 0) {
                    sum -= 5;
                    banknotes.set(3, banknotes.get(3) - 1);
                    continue;
                }
            }
            System.out.println("Take the money");
            saveData();
        }
    }

    public boolean checkSum(int sum) {
        if (sum >= balance && sum % 5 != 0) {
            System.out.println("Sum cannot be issued ");
            return false;
        }


        return true;
    }

}