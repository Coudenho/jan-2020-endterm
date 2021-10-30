public class subscription extends Simplan{

    public subscription(String provider, String formula, double monthlyPrice, Object callMinutes, Object texts, Object gigabytes, boolean studentPlan, int studentDiscount) {
        super(provider, formula, monthlyPrice, callMinutes, texts, gigabytes, studentPlan, studentDiscount);


    }
    public subscription(String provider, String formula, double monthlyPrice, Object callMinutes, Object texts, Object gigabytes) {
        super(provider, formula, monthlyPrice, callMinutes, texts, gigabytes);


    }

}
