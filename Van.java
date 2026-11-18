public class Van {
    private static final String SMALL = "Small";

    private final String name;
    private final double basePrice;
    private final String size;
    private LoaderService appliedOption;

    public Van(String name, double basePrice, String size, LoaderService appliedOption) {
        if (!Double.isFinite(basePrice) || basePrice < 0) {
            throw new IllegalArgumentException("Base price must be a finite, non-negative number.");
        }

        this.name = name;
        this.basePrice = basePrice;
        this.size = size;
        this.appliedOption = appliedOption;
    }

    public Van(String name, double basePrice) {
        this(name, basePrice, SMALL, null);
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String getSize() {
        return size;
    }

    public LoaderService getAppliedOption() {
        return appliedOption;
    }

    public double calculateFinalPrice() {
        if (size == null) {
            throw new IllegalArgumentException("Van size is required.");
        }

        double multiplier = switch (size) {
            case SMALL -> 1.00; // Base price
            case "Medium" -> 1.35; // Add 35%
            case "Large" -> 1.80; // Add 80%
            default -> throw new IllegalArgumentException("Unknown van size: " + size);
        };

        return basePrice * multiplier;
    }

    public double calculateFinalPrice(LoaderService loader) {
        return calculateFinalPrice(loader, 1);
    }

    public double calculateFinalPrice(LoaderService loader, int hours) {
        if (loader == null) {
            throw new IllegalArgumentException("Loader service is required.");
        }
        if (hours <= 0) {
            throw new IllegalArgumentException("Hours must be greater than zero.");
        }

        double total = calculateFinalPrice() + loader.getHourlyRate() * hours;
        this.appliedOption = loader;
        return total;
    }

    public double addLoader(LoaderService loader) {
        return calculateFinalPrice(loader);
    }

    public double addLoader(LoaderService loader, int hours) {
        return calculateFinalPrice(loader, hours);
    }
}