package Model;
import java.util.Scanner;

public abstract class Payment {
    private int paymentId;
    private double paymentAmount;
    private String paymentStatus;

    public Payment(int paymentId, double paymentAmount, String paymentStatus) {
        this.paymentId = paymentId;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
    }

    public final void paymentMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Select Payment Method:");
        System.out.println("(1) Visa");
        System.out.println("(2) Mastercard");

        int choice = input.nextInt();
        input.nextLine(); // Consume newline

        System.out.println("Enter Card Number:");
        String number = input.nextLine();

        System.out.println("Enter Cardholder Name:");
        String name = input.nextLine();

        System.out.println("Enter CVV:");
        String cvv = input.nextLine();

        Card card = new Card(number, name, cvv);

        // paymentAmount will be the price of the plan that user chooose

        switch (choice) {
            case 1:
                card.processVisaPayment(paymentAmount);
                break;
            case 2:
                card.processMastercardPayment(paymentAmount);
                break;
            default:
                System.out.println("Invalid choice. Payment failed.");
        }
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
