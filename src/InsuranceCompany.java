import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class InsuranceCompany {
    private static final int MAX_AGREEMENTS = 20;
    private final List<InsuranceAgreement> insuranceAgreements;
    private final Random random = new Random();
    private double totalMoney;
    private final Scanner scanner = new Scanner(System.in);

    public InsuranceCompany() {
        this.insuranceAgreements = new ArrayList<>();
    }

    public void moveTimeForward() {
        for (InsuranceAgreement agreement : insuranceAgreements) {
            totalMoney += agreement.getYearlyFee();
            totalMoney -= agreement.claimInsurance();
        }
        displayAllAgreements();
    }

    public void breakInsuranceAgreement() {
        System.out.println("Select agreement to break: ");
        displayAllAgreements();
        int agreementNumber = scanner.nextInt();
        InsuranceAgreement agreement = insuranceAgreements.get(agreementNumber - 1);
        totalMoney -= agreement.getYearlyFee() * 10;
        insuranceAgreements.remove(agreementNumber - 1);
        System.out.println("Agreement with " + agreement.client.getName() + " is broken.");
    }

    public void displayAllAgreements() {
        int i = 1;
        for (InsuranceAgreement agreement : insuranceAgreements) {
            System.out.println("Agreement " + i + ":");
            agreement.displayAgreement();
            System.out.println();
            i++;
        }
    }

    public void receiveNewApplications() {
        if(insuranceAgreements.size() >= MAX_AGREEMENTS) {
            System.out.println("You have reached the maximum number of agreements.");
            return;
        }

        List<InsuranceApplication> applications = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            applications.add(generateRandomApplication());
        }

        System.out.println("New Applications: ");
        int i = 1;
        for (InsuranceApplication application : applications) {
            System.out.println("Application " + i + ":");
            application.displayApplication();
            System.out.println();
            i++;
        }

        System.out.println("Choose an application to accept, or enter 0 to go back: ");
        int choice = scanner.nextInt();
        if (choice != 0) {
            InsuranceApplication application = applications.get(choice - 1);
            InsuranceAgreement agreement = application.toAgreement();
            insuranceAgreements.add(agreement);
            System.out.println("You have accepted the agreement with " + agreement.client.getName() + ".");
        }
    }

    private InsuranceApplication generateRandomApplication() {
        Client client = new Client("Client " + (insuranceAgreements.size() + 1));
        double yearlyFee = 1000 + random.nextDouble() * 9000; // Random yearly fee between 1000 and 10000
        Risk risk = Risk.values()[random.nextInt(Risk.values().length)]; // Random risk
        double insuranceAmount = yearlyFee * (5 + random.nextDouble() * 15); // Random insurance amount between 5x and 20x yearly fee
        return new InsuranceApplication(client, yearlyFee, risk, insuranceAmount);
    }

    public void seeFinancialBreakdown() {
        double totalPayouts = 0;
        double totalIncome = 0;
        for (InsuranceAgreement agreement : insuranceAgreements) {
            totalPayouts += agreement.getInsuranceAmount();
            totalIncome += agreement.getYearlyFee();
        }
        double netProfit = totalIncome - totalPayouts;

        System.out.println("Financial Breakdown:");
        System.out.println("Total payouts from claims: " + totalPayouts);
        System.out.println("Total income: " + totalIncome);
        System.out.println("Net profit: " + netProfit);
    }
}