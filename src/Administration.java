import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

class Administration {

    static final int STOP = 0;
    static final int VIEW = 1;
    static final int PatientList = 2;
    static final int SelectPatient = 3;
    static final int EditPatient = 4;
    static final int AddMeds = 5;
    static final int EditMeds = 6;
    static final int DeleteMeds = 7;



    Patient currentPatient;
    User currentUser;



    ArrayList<Patient> patienten = new ArrayList<>();



    public static void main(String[] args) {
        ZorgApp.main(new String[0]);
    }



    Administration(User user) {
        patienten.add(new Patient(1, "Van Puffelen", "Pierre", LocalDate.of(2000, 2, 29), 80, 1.80));
        patienten.add(new Patient(2, "Presley", "Elvis", LocalDate.of(2001, 3, 28), 60, 1.70));
        patienten.add(new Patient(3, "Smith", "Jan", LocalDate.of(2004, 6, 15), 75, 1.90));
        patienten.add(new Patient(4, "Gemayel", "Bashir", LocalDate.of(2007, 4, 10), 77, 1.90));
        patienten.add(new Patient(5, "GeaGea", "Samir", LocalDate.of(2006, 7, 23), 87, 1.99));
        patienten.add(new Patient(6, "Smith", "John", LocalDate.of(2008, 4, 11), 67, 1.64));



        currentUser = user;
        currentPatient = patienten.get(0);


        System.out.format("Current user: [%d] %s\n", user.getUserID(), user.getUserName());
    }



    void menu() {
        var scanner = new Scanner(System.in);

        boolean nextCycle = true;
        while (nextCycle) {
            System.out.format("%s\n", "=".repeat(80));
            System.out.format("Current patient: %s\n", currentPatient.fullName());

            System.out.format("%d:  STOP\n", STOP);
            System.out.format("%d:  View patient data\n", VIEW);
            System.out.format("%d:  View patient list\n", PatientList);
            System.out.format("%d:  Select patient\n", SelectPatient);
            System.out.format("%d:  Edit patient\n", EditPatient);
            System.out.format("%d:  Medicatie toevoegen\n", AddMeds);
            System.out.format("%d:  Medicatie Editen\n", EditMeds);
            System.out.format("%d:  Medicatie Delete\n", DeleteMeds);

            System.out.print("enter #choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case STOP:
                    nextCycle = false;
                    break;

                case VIEW:
                    currentPatient.viewData();
                    break;

                case PatientList:
                    for (int i = 0; i < patienten.size(); i++) {
                        System.out.print("ID: ");
                        System.out.print(patienten.get(i).id);
                        System.out.print(" = ");

                        System.out.print("Naam: ");
                        System.out.print(patienten.get(i).firstName);
                        System.out.print(" ");
                        System.out.print(patienten.get(i).surname);

                        System.out.print(", geboortedatum: ");
                        System.out.println(patienten.get(i).dateOfBirth.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    }
                    break;



                case SelectPatient:
                    System.out.print("\n Selecteer Patiënt ID: ");
                    int id = scanner.nextInt();

                    boolean gevonden = false;

                    for (int i = 0; i < patienten.size(); i++) {
                        if (patienten.get(i).id == id) {
                            currentPatient = patienten.get(i);
                            gevonden = true;

                            System.out.println("\nGeselecteerde patiënt: " + "\nID: " + currentPatient.id);
                            System.out.println("Voornaam: " + currentPatient.firstName);
                            System.out.println("Achternaam: " + currentPatient.surname);
                            System.out.println("geboortedatum: " + currentPatient.dateOfBirth.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                            System.out.println("leeftijd: " + currentPatient.age);
                            System.out.println("gewicht: " + currentPatient.weight + " kg");
                            System.out.println("lengte: " + currentPatient.height + " M");
                            System.out.println("BMI: " + currentPatient.bmi);
                            System.out.println("Medicatie: " + currentPatient.medicatie);

                            if (currentPatient.medicatie.isEmpty()) {
                                System.out.println("Medication: Geen medicatie");
                            } else {
                                System.out.println("Medication:");
                                for (MedicatieOpslag m : currentPatient.medicatie) {
                                    System.out.println("- " + m);
                                }
                            }
                        }
                    }

                    if (!gevonden) {
                        System.out.println("Geen patiënt gevonden met ID: " + id);
                    }
                    break;



                    case EditPatient:
                    System.out.println("Patiënt bewerken: " + currentPatient.firstName + " " + currentPatient.surname);
                    scanner.nextLine();

                    System.out.print("Nieuwe achternaam: ");
                    String newSurname = scanner.nextLine();

                    if (!newSurname.isEmpty()) {
                        currentPatient.surname = newSurname;
                    }

                    System.out.print("Nieuw gewicht: ");
                    String newWeight = scanner.nextLine();

                    if (!newWeight.isEmpty()) {
                        currentPatient.weight = Double.parseDouble(newWeight);
                    }

                    System.out.print("Nieuwe lengte: ");
                    String newHeight = scanner.nextLine();

                    if (!newHeight.isEmpty()) {
                        currentPatient.height = Double.parseDouble(newHeight);
                    }

                    currentPatient.bmi = currentPatient.weight / (currentPatient.height * currentPatient.height);

                    System.out.println("Patiënt aangepast.");
                    break;



                    case AddMeds:
                    scanner.nextLine();

                    System.out.println("Medication name: ");
                    String name = scanner.nextLine();

                    System.out.println("Dosage: ");
                    String dosage = scanner.nextLine();

                    currentPatient.addMedication(name, dosage);

                    System.out.println("Medication Added :");
                    break;



                case EditMeds:
                    scanner.nextLine();

                    System.out.println("Medication name: ");
                    String medName = scanner.nextLine();

                    System.out.println("Dosage: ");
                    String newDosage = scanner.nextLine();

                    currentPatient.updateMedicationDosage(medName, newDosage);
                    break;

                case DeleteMeds:
                    scanner.nextLine();

                    System.out.println("Medication name to delete: ");
                    String medToDelete = scanner.nextLine();

                    currentPatient.deleteMedication(medToDelete);
                    break;
            }
        }
    }
}