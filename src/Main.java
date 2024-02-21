interface DiscountRate {
    double getServiceDiscountRate();
    double getProductDiscountRate();
}

class Sale {
    private final Customer customer;
    private double productExpense;
    private double serviceExpense;

    public Sale(Customer customer) {
        this.customer = customer;
    }

    public void setProductExpense(double productExpense) {
        this.productExpense = productExpense;
    }

    public void setServiceExpense(double serviceExpense) {
        this.serviceExpense = serviceExpense;
    }

    public double getTotal() {
        double productDiscount = productExpense * customer.getProductDiscountRate();
        double serviceDiscount = serviceExpense * customer.getServiceDiscountRate();

        return productExpense - productDiscount + serviceExpense - serviceDiscount;
    }

    public void displayInfo() {
        System.out.println("Sale Information:");
        System.out.println("Customer Name: " + customer.getCustomerName());
        System.out.println("Customer Type: " + customer.getCustomerType());
        System.out.println("Product Expense: $" + productExpense);
        System.out.println("Service Expense: $" + serviceExpense);
        System.out.println("Total: $" + getTotal());
        System.out.println();
        System.out.println("=================================================\n");
    }
}

class Customer implements DiscountRate {
    private final String customerName;
    private final String customerType;

    public Customer(String customerName, String customerType) {
        this.customerName = customerName;
        this.customerType = customerType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    @Override
    public double getServiceDiscountRate() {
        return switch (customerType) {
            case "Premium" -> 0.20;
            case "Gold" -> 0.15;
            case "Silver" -> 0.10;
            default -> 0.0;
        };
    }

    @Override
    public double getProductDiscountRate() {
        return switch (customerType) {
            case "Premium", "Gold", "Silver" -> 0.10;
            default -> 0.0;
        };
    }
}

public class Main {
    public static void main(String[] args) {
        Customer c1 = new Customer("Sovisal", "Normal");
        Customer c2 = new Customer("Sokchea", "Premium");
        Customer c3 = new Customer("Sokpov", "Gold");
        Customer c4 = new Customer("Vitou", "Silver");

        Sale sale_c1 = new Sale(c1);
        Sale sale_c2 = new Sale(c2);
        Sale sale_c3 = new Sale(c3);
        Sale sale_c4 = new Sale(c4);

        sale_c1.setProductExpense(100);
        sale_c1.setServiceExpense(100);
        sale_c1.displayInfo();

        sale_c2.setProductExpense(100);
        sale_c2.setServiceExpense(100);
        sale_c2.displayInfo();

        sale_c3.setProductExpense(100);
        sale_c3.setServiceExpense(100);
        sale_c3.displayInfo();

        sale_c4.setProductExpense(100);
        sale_c4.setServiceExpense(100);
        sale_c4.displayInfo();
    }
}
