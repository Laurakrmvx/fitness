class PremiumUser extends User {
    private int bonusWorkouts;

    public PremiumUser(String name, int age, double weight, int bonusWorkouts) {
        super(name, age, weight);
        this.bonusWorkouts = bonusWorkouts;
    }

    public int getBonusWorkouts() {
        return bonusWorkouts;
    }

    public void setBonusWorkouts(int bonusWorkouts) {
        this.bonusWorkouts = bonusWorkouts;
    }

    @Override
    public String getUserType() {
        return "Premium";
    }

    @Override
    public String toString() {
        return super.toString() + ", bonusWorkouts=" + bonusWorkouts;
    }
}
