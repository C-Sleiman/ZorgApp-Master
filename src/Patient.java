import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class Patient {
    static final int RETURN = 0;
    static final int SURNAME = 1;
    static final int FIRSTNAME = 2;
    static final int DATEOFBIRTH = 3;
    static final int AGE = 4;
    static final int WEIGHT = 5;
    static final int HEIGHT = 6;
    static final int BMI = 7;
    static final int MEDICATIE = 8;

    int id;
    String surname;
    String firstName;
    LocalDate dateOfBirth;
    int age;
    double weight;
    double height;
    double bmi;

    public final List<MedicatieOpslag> medicatie = new ArrayList<>();

    Patient(int id, String surname, String firstName, LocalDate dateOfBirth, double weight, double height) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.height = height;

        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();

        if (height > 0) {
            this.bmi = weight / (height * height);
        } else {
            this.bmi = 0;
        }
    }

    String getSurname() {
        return surname;
    }

    String getFirstName() {
        return firstName;
    }

    int getAge() {
        return age;
    }

    double getWeight() {
        return weight;
    }

    double getHeight() {
        return height;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    double getBmi() {
        return bmi;
    }

    void viewData() {
        System.out.format("===== Patient id=%d ==============================\n", id);
        System.out.format("%-17s %s\n", "Surname:", surname);
        System.out.format("%-17s %s\n", "firstName:", firstName);
        System.out.format("%-17s %s\n", "Date of birth:", formatter.format(dateOfBirth));
        System.out.format("%-17s %s\n", "Age:", age);
        System.out.format("%-17s %s\n", "Weight:", weight + " kg");
        System.out.format("%-17s %s\n", "Height:", height + " m");
        System.out.format("%-17s %s\n", "BMI:", bmi);

        if (medicatie.isEmpty()) {
            System.out.format("%-17s %s\n", "Medication:", "Geen medicatie");
        } else {
            System.out.println("Medication:");
            for (MedicatieOpslag m : medicatie) {
                System.out.println("- " + m);
            }
        }
    }

    public void addMedication(String medication, String dosage) {
        medicatie.add(new MedicatieOpslag(medication, dosage));
    }

    public void updateMedicationDosage(String medication, String dosage) {
        for (MedicatieOpslag m : medicatie) {
            if (m.getName().equalsIgnoreCase(medication)) {
                m.setDosage(dosage);
                System.out.println("Dosage updated for " + medication);
                return;
            }
        }
        System.out.println("Medication not found");
    }

    public void deleteMedication(String medication) {
        for (MedicatieOpslag m : medicatie) {
            if (m.getName().equalsIgnoreCase(medication)) {
                medicatie.remove(m);
                System.out.println("Dosage deleted from " + medication);
                return;
            }
        }
    }

    String fullName() {
        return String.format("%s %s [%d]", firstName, surname, age);
    }
}