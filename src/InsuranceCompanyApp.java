import java.util.Scanner;

public class InsuranceCompanyApp {

    public static void main(String[] args) {
        InsuranceCompany company = new InsuranceCompany();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLaSalle Insurance Menu:");
            System.out.println("1. Move time forward");
            System.out.println("2. Break insurance agreement");
            System.out.println("3. Display all agreements");
            System.out.println("4. Receive new applications");
            System.out.println("5. See financial breakdown");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    company.moveTimeForward();
                    break;
                case 2:
                    company.breakInsuranceAgreement();
                    break;
                case 3:
                    company.displayAllAgreements();
                    break;
                case 4:
                    company.receiveNewApplications();
                    break;
                case 5:
                    company.seeFinancialBreakdown();
                    break;
                case 6:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}