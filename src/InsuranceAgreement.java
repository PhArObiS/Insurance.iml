import java.util.Random;

public class InsuranceAgreement {
    Client client;
    private double yearlyFee;
    private Risk risk;
    private double insuranceAmount;
    private final Random random = new Random();

    public InsuranceAgreement(Client client, double yearlyFee, Risk risk, double insuranceAmount) {
        this.client = client;
        this.yearlyFee = yearlyFee;
        this.risk = risk;
        this.insuranceAmount = insuranceAmount;
    }

    public double getYearlyFee() {
        return yearlyFee;
    }

    public double getInsuranceAmount() {
        return insuranceAmount;
    }

    public double claimInsurance() {
        if (random.nextDouble() <= risk.getRiskValue()) {
            return insuranceAmount;
        }
        return 0;
    }

    public void displayAgreement() {
        System.out.println("Client: " + client.getName());
        System.out.println("Yearly Fee: " + yearlyFee);
        System.out.println("Risk: " + risk);
        System.out.println("Insurance Amount: " + insuranceAmount);
    }

    public Client getClient() {
        return client;
    }

    public <E extends Enum<E>> Enum<E> getRisk() {
        return (Enum<E>) risk;
    }
}
