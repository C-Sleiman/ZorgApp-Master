public class Allergies {

    private final String allergies;


    public Allergies(String allergies) {
        this.allergies = allergies;
    }

    public String getAllergies() {
        return allergies;
    }

    @Override
    public String toString() {
        return allergies;
    }
}