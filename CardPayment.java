package Model;

import java.util.Scanner;

public class CardPayment {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private String paymentStatus;


    public CardPayment() {
    }

    public boolean processPayment() {
        Scanner input = new Scanner(System.in);

        // Collect card details
        System.out.println("Enter Card Number:");
        cardNumber = input.nextLine();

        System.out.println("Enter Cardholder Name:");
        cardHolderName = input.nextLine();

        System.out.println("Enter CVV:");
        cvv = input.nextLine();

        // Determine card type
        if (cardNumber.startsWith("4")) {
            CvVCheck();
            return processVisaPayment();
        } else if (cardNumber.startsWith("5")) {
            CvVCheck();
            return processMastercardPayment();
        }
        else {
            System.out.println("Invalid card type. Payment failed.");
            return false;
        }
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    private boolean processVisaPayment() {
        if (cardNumber.length() == 16) {
            System.out.println("Visa Payment Successful!");
            setPaymentStatus("Completed");
            return true;
        } else {
            System.out.println("Visa Payment Failed. Invalid card details.");
            setPaymentStatus("Failed");
            return false;
        }
    }
    private boolean CvVCheck() {
        if (cvv.length() == 3) {
            return true;
        } else {
            System.out.println("Visa Payment Failed. Invalid card details.");
            setPaymentStatus("Failed");
            return false;
        }
    }

    private boolean processMastercardPayment() {
        if (cardNumber.length() == 16) {
            System.out.println("Mastercard Payment Successful!");
            setPaymentStatus("Completed");
            return true;
        } else {
            System.out.println("Mastercard Payment Failed. Invalid card details.");
            setPaymentStatus("Failed");
            return false;
        }
    }
}
