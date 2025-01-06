class StandardUser extends User {
    public StandardUser(String name, int age, double weight) {
        super(name, age, weight);
    }

    @Override
    public String getUserType() {
        return "Standard";
    }
}
