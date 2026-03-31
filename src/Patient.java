import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class Patient {
    
    int id;
    String surname;
    String firstName;
    LocalDate dateOfBirth;
    int age;
    double weight;
    double height;
    double lungcap;
    double bmi;
    List<Allergies> allergies = new ArrayList<>();
    List<Consult> consults = new ArrayList<>();
    

    public final List<MedicatieOpslag> medicatie = new ArrayList<>();

    Patient(int id, String surname, String firstName, LocalDate dateOfBirth, double weight, double height, double lungcap) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.height = height;
        this.lungcap = lungcap;

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
    
    double getLungcap() {return lungcap;}

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
        System.out.format("%-17s %.1f\n", "BMI:", bmi);
        System.out.format("%-17s %s\n", "Lungcap:", lungcap + "L");


        if (medicatie.isEmpty()) {
            System.out.format("%-17s %s\n", "Medication:", "Geen medicatie");
        } else {
            System.out.println("Medication:");
            for (MedicatieOpslag m : medicatie) {
                System.out.println("- " + m);
            }
        }

        if (allergies.isEmpty()) {
            System.out.format("%-17s %s\n", "Allergies:", "Geen allergieën");
        } else {
            System.out.println("Allergies:");
            for (Allergies a : allergies) {
                System.out.println("- " + a);
            }
        }

        if (consults.isEmpty()) {
            System.out.format("%-17s %s\n", "Consultation:", "Geen consultaties");
        } else {
            System.out.println("Consultation:");
            for (Consult c : consults) {
                System.out.println("- " + c);
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

    public void AddAllergies(String allergies) {
        this.allergies.add(new Allergies(allergies));
    }
    
    public void updateAllergies(String oldAllergyName, String newAllergyName) {
        for (int i = 0; i < this.allergies.size(); i++) {
            if (this.allergies.get(i).getAllergies().equalsIgnoreCase(oldAllergyName)) {
                this.allergies.set(i, new Allergies(newAllergyName));
                System.out.println("Allergies updated from " + oldAllergyName + " to " + newAllergyName);
                return;
            }
        }
        System.out.println("Allergy not found");
    }

    public void deleteAllergies(String allergies) {
        for (int i = 0; i < this.allergies.size(); i++) {
            if (this.allergies.get(i).getAllergies().equalsIgnoreCase(allergies)) {
                this.allergies.remove(i);
                System.out.println("Allergy deleted from " + allergies);
                return;
            }
        }
    }

    public void AddConsult(String consult) {
        this.consults.add(new Consult(consult));
    }

    public void Editconsult(String consultation) {
        if (!this.consults.isEmpty()) {
            this.consults.set(0, new Consult(consultation));
            System.out.println("Consultation updated");
        } else {
            System.out.println("No consultation to update");
        }
    }

    public void deleteConsult(String consultation) {
        for (int i = 0; i < this.consults.size(); i++) {
            if (this.consults.get(i).getConsult().equalsIgnoreCase(consultation)) {
                this.consults.remove(i);
                System.out.println("Consultation deleted");
                return;
            }
        }
        System.out.println("Consultation not found");
    }
    
   

    String fullName() {
        return String.format("%s %s [%d]", firstName, surname, age);
    }
}