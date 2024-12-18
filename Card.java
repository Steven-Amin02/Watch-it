package Model;

public class Card{
    private String number;
    private String name;
    //private String expDate;
    private String cvv;

    public Card(String number, String name, String cvv) {
        this.number = number;
        this.name = name;
        this.cvv = cvv;
    }

    public boolean visaValidation(String number, String name, String cvv) {
        this.number = number;
        this.name = name;
        this.cvv = cvv;
        {
            if(number.startsWith("4") && number.length()==16)
                return true;
            else
                return false;
        }
    }

    public boolean processVisaPayment(double Pamount){
        if(this.visaValidation(number, name, cvv))
        {
            System.out.println("Payment successful");
            return true;
        }
        else{
            System.out.println("payment failed");
            return false;
        }
    }

    public boolean MastercardValidation(String number, String name, String cvv)
    {
        this.number = number;
        this.name = name;
        this.cvv = cvv;

        if(number.startsWith("5") && number.length()==16)
            return true;
        else
            return false;
    }

    public boolean processMastercardPayment(double Pamount){
        if(MastercardValidation(number , name , cvv))
        {
            System.out.println("Payment successful");
            return true;
        }
        else{
            System.out.println("payment failed");
            return false;
        }

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }


}
