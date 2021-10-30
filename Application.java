import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please give me a subscription file ");
            Scanner subscriptionScanner = new Scanner(new File(scanner.nextLine()));
          //  Scanner subscriptionScanner = new Scanner(new File("subscriptions.txt"));
            System.out.println("Please give me a prepaid file ");
            Scanner prepaidScanner = new Scanner(new File(scanner.nextLine()));
          //  Scanner prepaidScanner = new Scanner(new File("prepaid.txt"));
            SimplanList SimplanLit = new SimplanList(SimplanList.simListReader(subscriptionScanner, prepaidScanner));

            menuPrinter(SimplanLit);



        } catch (FileNotFoundException e) {
            System.out.println("Sorry but that file was not found");
        }

    }

    public static void menuPrinter(SimplanList simplanList){
        System.out.println("Please make your choice: \n" +
                "1 – Show all subscriptions and prepaid formulas\n" +
                "2 – Filter subscriptions and prepaid formulas\n" +
                "3 – Write to file\n" +
                "4 – Stop the program\n");
        Scanner scanner = new Scanner(System.in);
        int Choice = scanner.nextInt();
        switch (Choice){
            case 1:
                System.out.println(simplanList.toString());

                break;
            case 2: filterOptionMenu(simplanList);

                    break;
            case 4: System.exit(0);


        }
        menuPrinter(simplanList);
    }
    public static void filterOptionMenu(SimplanList simplanList){
        System.out.println("What do you want to see?:\n" +
                "1 - Show all phone plans\n" +
                "2 - Show only subscription plans \n" +
                "3 - Show only prepaid plans\n");
        String isStudent = "Are you a student?: \n" +
                "1 - yes \n" +
                "2 - no \n";
        String specify = "Please choose one of the following options: \n" +
                "1 - show based on maximum price \n" +
                "2 - show based on minimum amount of calling minutes, texts and gigabytes";
        Scanner scanner = new Scanner(System.in);
        boolean isStudentasBool = false;
        int Choice = scanner.nextInt();
        System.out.println(isStudent);
        int Choice2 = scanner.nextInt();
        System.out.println(specify);
        int Choice3 = scanner.nextInt();
        SimplanList temp = simplanList;
        switch (Choice){
            case 1: break;
            case 2: temp = new SimplanList(temp.subscriptionPlans());
                    break;
            case 3: temp = new SimplanList(temp.prepaidPlans());
                    break;
        }
        switch (Choice2){
            case 1:
                break;

            case 2: temp = new SimplanList(temp.isNotForStudents());
                break;
        }
        switch (Choice3){
            case 1: System.out.println("What is your max price range?:");
                    Scanner scanner1 = new Scanner(System.in);
                    Double input = Double.parseDouble(scanner1.nextLine());
                    temp = new SimplanList(temp.maxPriceFilter(input));
                    System.out.println(temp.toString());
                    break;
            case 2: System.out.println("What is your max calling minutes?:");
                    int calling = scanner.nextInt();
                    System.out.println("What is your max texts?:");
                    int texts = scanner.nextInt();
                    System.out.println("What is your max gigabytes?:");
                    int giga = scanner.nextInt();
                    temp = new SimplanList((temp.maxSpecification(calling, texts, giga)));
                    System.out.println((temp.toString()));
        }
    }
}
