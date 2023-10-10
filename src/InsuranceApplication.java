public class InsuranceApplication {
    private Client client;
    private double yearlyFee;
    private Risk risk;
    private double insuranceAmount;

    public InsuranceApplication(Client client, double yearlyFee, Risk risk, double insuranceAmount) {
        this.client = client;
        this.yearlyFee = yearlyFee;
        this.risk = risk;
        this.insuranceAmount = insuranceAmount;
    }

    public InsuranceAgreement toAgreement() {
        return new InsuranceAgreement(client, yearlyFee, risk, insuranceAmount);
    }

    public void displayApplication() {
        System.out.println("Client: " + client.getName());
        System.out.println("Yearly Fee: " + yearlyFee);
        System.out.println("Risk: " + risk);
        System.out.println("Insurance Amount: " + insuranceAmount);
    }
}
