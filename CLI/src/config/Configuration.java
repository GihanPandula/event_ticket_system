package config;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int vendorCount;
    private int customerCount;

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, int vendorCount, int customerCount) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.vendorCount = vendorCount;
        this.customerCount = customerCount;
    }

//    public int getTotalTickets() {
//        return totalTickets;
//    }
//
//    public int getTicketReleaseRate() {
//        return ticketReleaseRate;
//    }
//
//    public int getCustomerRetrievalRate() {
//        return customerRetrievalRate;
//    }
//
//    public int getMaxTicketCapacity() {
//        return maxTicketCapacity;
//    }
//
//    public int getVendorCount() {
//        return vendorCount;
//    }
//
//    public int getCustomerCount() {
//        return customerCount;
//    }

    @Override
    public String toString() {
        return "Configuration:" + "\n" +
                "Total Tickets: " + totalTickets + "\n" +
                "Ticket Release Rate: " + ticketReleaseRate + "\n" +
                "Customer Retrieval Rate: " + customerRetrievalRate + "\n" +
                "Maximum Ticket Capacity: " + maxTicketCapacity + "\n" +
                "Number of Vendors: " + vendorCount + "\n" +
                "Number of Customers: " + customerCount;
    }
}