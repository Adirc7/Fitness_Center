package lk.sliit.fitnesscenter.fitnesscentermembershipsystem.model;

public class yearlyPlans extends monthlyPlans {

    private String addons;
    private String subTotal;
    private String discont;
    private String finalPrice;

    public yearlyPlans(String planId, String planName, String price, String addons, String subTotal, String discont, String finalPrice) {
        super(planId, planName, price, "");
        this.addons = addons;
        this.subTotal = subTotal;
        this.discont = discont;
        this.finalPrice = finalPrice;
    }

    public String getAddons() {
        return addons;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public String getDiscont() {
        return discont;
    }

    public String getFinalPrice() {
        return finalPrice;
    }
}
