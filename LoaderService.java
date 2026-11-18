public class LoaderService {
    private final String name;
    private double hourlyRate;

    public LoaderService(String name, double hourlyRate) {
        this.name = name;
        setHourlyRate(hourlyRate);
    }

    public String getName() {
        return name;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        if (!Double.isFinite(hourlyRate) || hourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate must be a finite, non-negative number.");
        }
        this.hourlyRate = hourlyRate;
    }
}