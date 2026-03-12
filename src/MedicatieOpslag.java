public class MedicatieOpslag {

    private final String name;
    private String dosage;

    public MedicatieOpslag(String name, String dosage) {
        this.name = name;
        this.dosage = dosage;
    }

    public String getName() {
        return name;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return name + " | Dosage: " + dosage;
    }
}