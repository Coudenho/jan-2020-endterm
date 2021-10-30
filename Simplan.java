
import java.util.Objects;
import java.util.Scanner;

public abstract class Simplan {
    private String provider;
    private String formula;
    private double monthlyPrice;
    private Object callMinutes;
    private Object texts;
    private Object gigabytes;
    private boolean studentPlan;
    private int studentDiscount;

    /**
     * creates a Simplan
     * @param provider
     * @param formula
     * @param monthlyPrice
     * @param callMinutes
     * @param texts
     * @param gigabytes
     */
    public Simplan(String provider, String formula, double monthlyPrice, Object callMinutes, Object texts, Object gigabytes) {
        this.provider = provider;
        this.formula = formula;
        this.monthlyPrice = monthlyPrice;
        this.callMinutes = callMinutes;
        this.texts = texts;
        this.gigabytes = gigabytes;
    }

    /**
     * creates a Simplan for students
     * @param provider
     * @param formula
     * @param monthlyPrice
     * @param callMinutes
     * @param texts
     * @param gigabytes
     * @param studentPlan
     * @param studentDiscount
     */
    public Simplan(String provider, String formula, double monthlyPrice, Object callMinutes, Object texts, Object gigabytes, boolean studentPlan, int studentDiscount) {
        this.provider = provider;
        this.formula = formula;
        this.monthlyPrice = monthlyPrice;
        this.callMinutes = callMinutes;
        this.texts = texts;
        this.gigabytes = gigabytes;
        this.studentPlan = studentPlan;
        this.studentDiscount = studentDiscount;
    }

    /**
     *
     * @return if the discount for students
     */
    public int getStudentDiscount() {
        return studentDiscount;
    }

    /**
     *
     * @return if it is for students
     */
    public boolean isStudentPlan() {
        return studentPlan;
    }
    public void setMonthlyPrice(double monthlyPrice){
        this.monthlyPrice = monthlyPrice;
    }


    /**
     *
     * @return the monthly price
     */
    public double getMonthlyPrice() {
        return monthlyPrice;
    }


    /**
     *
     * @return the call minutes you get
     */
    public Object getCallMinutes() {
        return callMinutes;
    }

    /**
     *
     * @return the amount of GB that you get
     */
    public Object getGigabytes() {
        return gigabytes;
    }

    /**
     *
     * @return the amount of texts that you get
     */
    public Object getTexts() {
        return texts;
    }

    /**
     *
     * @return the formula of this plan
     */
    public String getFormula() {
        return formula;
    }

    /**
     *
     * @return the provider of this plan
     */
    public String getProvider() {
        return provider;
    }

    /**
     *
     * @param simplan
     * @return returns a human friendly string representation of the given Simplan
     */
    public String toString(Simplan simplan){
        String result = "";

        if (simplan instanceof Prepaid){
            result += "Prepaid plan ";
            if (simplan.studentPlan){
                result += "for students:";
            }
            else{
                result += ":";
            }
        }
        else {

            result += "Subscription plan ";
            if (simplan.studentPlan){
                result += "for students:";

            }
            else{
                result += ":";
            }
        }
        result += " " + this.provider + " " + this.formula +  ", costs ";

        if (simplan.studentPlan){
            result += (this.monthlyPrice * ((100-studentDiscount))/100);
        }
        else {
            result += this.monthlyPrice;
        }
        result += " Euro" + "\n" +
                "includes " + this.callMinutes + " calling minutes, " + this.texts + " texts and " + this.gigabytes + "GB of data";
        if (simplan instanceof Prepaid){
            Prepaid that = (Prepaid) simplan;
            result += "\n" +  "calling minutes carry over: ";
            if (that.isCallMinutesCarry()) {
                result += "yes \n";
            }
            else{
                result += "no \n";
            }
            result += "texts carry over: ";
            if (that.isTextsCarry()){
                result += "yes \n";
            }
            else{
                result += "no \n";
            }
            result += "internet data carry over: ";
            if (that.isGigabytesCarry()){
                result += "yes \n";}
            else {
                result += "no \n";
            }}
        else{
            result += "\n";
        }
        return result;
    }

    /**
     *
     * @param o
     * @return a boolean that tells you if the methods are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Simplan simplan = (Simplan) o;
        return monthlyPrice == simplan.monthlyPrice && Objects.equals(provider, simplan.provider) && Objects.equals(formula, simplan.formula) && Objects.equals(callMinutes, simplan.callMinutes) && Objects.equals(texts, simplan.texts) && Objects.equals(gigabytes, simplan.gigabytes);
    }


    /**
     *
     * @param scanner
     * @return a scanner converted to a Simplan
     */
    public static Simplan readsubscription(Scanner scanner){
        Scanner scn = scanner;
        scn.useDelimiter(", ");
        String provider = scn.next();
        String formula = scn.next();
        Object callMinutes;
        Object texts;
        Object gigabytes;
        double monthlyPrice = Double.parseDouble(scn.next().split(" ")[0]);
        try {
             callMinutes =  Integer.valueOf(scn.next());
        }
        catch (NumberFormatException e){
             callMinutes =  "unlimited";
           //  scn.next();
        }
        try {
            texts =  Integer.valueOf(scn.next());
        }
        catch (NumberFormatException e){
            texts = "unlimited";
            //scn.next();
        }
        try {
            gigabytes =  Integer.valueOf(scn.next());
        }
        catch (NumberFormatException e){
            gigabytes = "unlimited";
            //scn.next();
        }

        subscription sub;
        if (scn.hasNext()) {
            boolean studentPlan = false;
             if (scn.next().startsWith("Student")){
                 studentPlan = true;
             }

            int studentDiscount = Integer.parseInt(scn.next().split("%")[0]);
            sub = new subscription(provider, formula, monthlyPrice, callMinutes, texts, gigabytes, studentPlan, studentDiscount);
        }
        else{
            sub = new subscription(provider, formula, monthlyPrice, callMinutes, texts, gigabytes);
        }
        return sub;

    }
    public static Simplan readPrepaid(Scanner scanner){
        Scanner scn = scanner;
        scn.useDelimiter(", ");
        String provider = scn.next();
        String formula = scn.next();
        Object callMinutes;
        Object texts;
        Object gigabytes;
        boolean callMinutesCarry;
        boolean textsCarry;
        boolean gigabytesCarry;
        double monthlyPrice = Double.parseDouble(scn.next().split(" ")[0]);
        try {
            callMinutes =  Integer.valueOf(scn.next());
        }
        catch (NumberFormatException e){
            callMinutes =  "unlimited";
            //  scn.next();
        }
        try {
            texts =  Integer.valueOf(scn.next());
        }
        catch (NumberFormatException e){
            texts = "unlimited";
            //scn.next();
        }
        try {
            gigabytes =  Integer.valueOf(scn.next());
        }
        catch (NumberFormatException e){
            gigabytes = "unlimited";
            //scn.next();
        }
        callMinutesCarry = scn.nextBoolean();
        textsCarry = scn.nextBoolean();
        gigabytesCarry = scn.nextBoolean();
        // System.out.println(scn.next());
        // System.out.println(scn.next());
        // System.out.println(scn.next());
        // System.out.println(scn.next());
        Prepaid pre1;
        if (scn.hasNext()) {
            boolean studentPlan = false;
            if (scn.next().startsWith("Student")){
                studentPlan = true;
            }

            int studentDiscount = Integer.parseInt(scn.next().split("%")[0]);
            pre1 = new Prepaid(provider, formula, monthlyPrice, callMinutes, texts, gigabytes, callMinutesCarry, textsCarry, gigabytesCarry, studentPlan, studentDiscount);
        }
        else{
            pre1 = new Prepaid(provider, formula, monthlyPrice, callMinutes, texts, gigabytes, callMinutesCarry, textsCarry, gigabytesCarry);
        }
        return pre1;

    }
}
