public class Prepaid extends Simplan{
    private boolean callMinutesCarry;
    private boolean textsCarry;
    private boolean gigabytesCarry;

    /**
     * Creates a prepaid Simplan that isn't exclusively for students
     *
     * @param provider
     * @param formula
     * @param monthlyPrice
     * @param callMinutes
     * @param texts
     * @param gigabytes
     * @param callMinutesCarry
     * @param textsCarry
     * @param gigabytesCarry
     * @param studentPlan
     * @param studentDiscount
     */
    public Prepaid(String provider, String formula, double monthlyPrice, Object callMinutes, Object texts, Object gigabytes, boolean callMinutesCarry, boolean textsCarry, boolean gigabytesCarry, boolean studentPlan, int studentDiscount) {
        super(provider, formula, monthlyPrice, callMinutes, texts, gigabytes, studentPlan, studentDiscount);
        this.callMinutesCarry = callMinutesCarry;
        this.textsCarry = textsCarry;
        this.gigabytesCarry = gigabytesCarry;
    }

    /**
     * creates a prepaid simplan that is for students
     *
     * @param provider
     * @param formula
     * @param monthlyPrice
     * @param callMinutes
     * @param texts
     * @param gigabytes
     * @param callMinutesCarry
     * @param textsCarry
     * @param gigabytesCarry
     */
    public Prepaid(String provider, String formula, double monthlyPrice, Object callMinutes, Object texts, Object gigabytes, boolean callMinutesCarry, boolean textsCarry, boolean gigabytesCarry) {
        super(provider, formula, monthlyPrice, callMinutes, texts, gigabytes );
        this.callMinutesCarry = callMinutesCarry;
        this.textsCarry = textsCarry;
        this.gigabytesCarry = gigabytesCarry;
    }


    /**
     *
     * @return if this prepaid simplan carries over your callminutes
     */
    public boolean isCallMinutesCarry() {
        return callMinutesCarry;
    }

    /**
     *
     * @return if this prepaid simplan carries over your texts
     */
    public boolean isTextsCarry() {
        return textsCarry;
    }

    /**
     *
     * @return if this prepaid simplan carries over you Gigabytes
     */
    public boolean isGigabytesCarry() {
        return gigabytesCarry;
    }
}
