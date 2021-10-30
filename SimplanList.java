import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SimplanList {
    List<Simplan> simList;

    public SimplanList(List<Simplan> simList){
        this.simList = simList;
    }

    public static List<Simplan> simListReader(Scanner subscanner, Scanner prepaidscanner){
        List<Simplan> simList = new ArrayList<>();
        while (subscanner.hasNextLine()){
           simList.add(Simplan.readsubscription(new Scanner(subscanner.nextLine())));
        }
        while (prepaidscanner.hasNextLine()){
            simList.add(Simplan.readPrepaid(new Scanner(prepaidscanner.nextLine())));
        }
    return simList;

    }
    public List<Simplan> prepaidPlans(){
        return simList.stream()
                .filter(v -> v instanceof Prepaid)
                .collect(Collectors.toList());

    }
    public List<Simplan> subscriptionPlans(){
        return simList.stream()
                .filter(v -> v instanceof subscription)
                .collect(Collectors.toList());

    }
    public void isForStudents(){
        simList.stream()
                .forEach(v -> v.setMonthlyPrice(v.getMonthlyPrice() * (v.getStudentDiscount() - 100)/100 ));



    }
    public List<Simplan> maxPriceFilter(double monthlyPrice){
        return simList.stream()
                .filter(v -> (v.getMonthlyPrice() <= monthlyPrice))
                .collect(Collectors.toList());
    }
    public List<Simplan> maxSpecification(int calling, int texts, int giga){
        return  simList.stream()
                .filter(v -> ((int) v.getCallMinutes() <= calling))
                .filter(v -> ((int) v.getTexts() <= texts))
                .filter((v -> ((int) v.getGigabytes() <= giga)))
                .collect(Collectors.toList());
    }
    public List<Simplan> isNotForStudents(){
        return simList.stream()
                .filter(v -> !(v.isStudentPlan()))
                .collect(Collectors.toList());
    }
    public String toString(){
        String result = "";

        for (Simplan simplan: simList){
            result += simplan.toString(simplan) + "\n";

        }
        return result;
    }


}
