public enum Risk {

    LOW(0.05),
    MEDIUM(0.5),
    HIGH(1.0);

    private final double risk;

    Risk(double risk) {
        this.risk = risk;
    }

    public double getRisk() {
        return this.risk;
    }

    public double getRiskValue() {
        return this.risk;
    }
}
