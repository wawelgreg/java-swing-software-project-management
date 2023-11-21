public class Risk {
    private String riskName;
    private String riskDescription;
    private int riskProbability;

    public Risk(String riskName, String riskDescription, int riskProbability)
    {
        this.riskName = riskName;
        this.riskDescription = riskDescription;
        this.riskProbability = riskProbability;
    }

    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public void setRiskProbability(int riskProbability) {
        this.riskProbability = riskProbability;
    }

    public int getRiskProbability() {
        return riskProbability;
    }

    public String getRiskDescription() {
        return riskDescription;
    }

    public String getRiskName() {
        return riskName;
    }
}
