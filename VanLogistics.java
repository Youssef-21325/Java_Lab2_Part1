import java.util.Locale;

public class VanLogistics {
    private static int totalOrdersCount;
    private static double totalRevenue;

    public static int getTotalOrdersCount() {
        return totalOrdersCount;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    private static void recordOrder(Van van, double price) {
        totalOrdersCount++;
        totalRevenue += price;
        System.out.printf(Locale.ROOT, "%s: %.2f RUB%n", van.getName(), price);
    }

    public static void printGlobalStats() {
        System.out.println();
        System.out.println("Total orders: " + getTotalOrdersCount());
        System.out.printf(Locale.ROOT, "Total revenue: %.2f RUB%n", getTotalRevenue());
    }

    public static void main(String[] args) {
        LoaderService loader = new LoaderService("Standard loading", 200.0);

        Van smallVan = new Van("Ford Transit", 1000.0);
        Van mediumVan = new Van("Mercedes Sprinter", 1000.0, "Medium", null);
        Van largeTruck = new Van("MAN Truck", 1000.0, "Large", null);

        recordOrder(smallVan, smallVan.calculateFinalPrice());
        recordOrder(mediumVan, mediumVan.calculateFinalPrice(loader));
        recordOrder(largeTruck, largeTruck.calculateFinalPrice(loader, 3));

        printGlobalStats();
    }
}