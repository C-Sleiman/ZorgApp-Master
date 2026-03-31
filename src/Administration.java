import java.time.LocalDate;
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
    static final int AddAllergies = 8;
    static final int EditAllergies = 9;
    static final int DeleteAllergies = 10;
    static final int AddConsult = 11;
    static final int EditConsult = 12;
    static final int DeleteConsult = 13;
    static final int EditPatientLungcap = 14;
    static final int LOGOUT = 15;




    Patient currentPatient;
    User currentUser;



    ArrayList<Patient> patienten = new ArrayList<>();



    public static void main(String[] args) {
        ZorgApp.main(new String[0]);
    }



    Administration(User user) {
        patienten.add(new Patient(1, "Van Puffelen", "Pierre", LocalDate.of(2000, 2, 29), 80, 1.80, 5));
        patienten.add(new Patient(2, "Presley", "Elvis", LocalDate.of(2001, 3, 28), 60, 1.70, 4));
        patienten.add(new Patient(3, "Smith", "Jan", LocalDate.of(2004, 6, 15), 75, 1.90, 7));
        patienten.add(new Patient(4, "Gemayel", "Bashir", LocalDate.of(2007, 4, 10), 77, 1.90, 5));
        patienten.add(new Patient(5, "GeaGea", "Samir", LocalDate.of(2006, 7, 23), 87, 1.99, 5));
        patienten.add(new Patient(6, "Smith", "John", LocalDate.of(2008, 4, 11), 67, 1.64, 6));



        currentUser = user;
        currentPatient = patienten.get(0);


        System.out.format("Current user: [%d] %s\n", user.getUserID(), user.getUserName());
    }



    boolean menu() {
        var scanner = new Scanner(System.in);

        while (true) {
            System.out.format("%s\n", "=".repeat(80));
            System.out.format("Current patient: %s\n", currentPatient.fullName());

            if (currentUser.hasAccess(STOP))
                System.out.format("%d:  STOP\n", STOP);

            if (currentUser.hasAccess(VIEW))
                System.out.format("%d:  View patient data\n", VIEW);

            if (currentUser.hasAccess(PatientList))
                System.out.format("%d:  View patient list\n", PatientList);

            if (currentUser.hasAccess(SelectPatient))
                System.out.format("%d:  Select patient\n", SelectPatient);

            if (currentUser.hasAccess(EditPatient))
                System.out.format("%d:  Edit patient\n", EditPatient);

            if (currentUser.hasAccess(EditPatientLungcap))
                System.out.format("%d:  Edit longcapaciteit\n", EditPatientLungcap);

            if (currentUser.hasAccess(AddMeds))
                System.out.format("%d:  Medicatie toevoegen\n", AddMeds);

            if (currentUser.hasAccess(EditMeds))
                System.out.format("%d:  Medicatie Editen\n", EditMeds);

            if (currentUser.hasAccess(DeleteMeds))
                System.out.format("%d:  Medicatie Delete\n", DeleteMeds);

            if(currentUser.hasAccess(AddAllergies))
                System.out.format("%d:  add Allergies\n", AddAllergies);

            if(currentUser.hasAccess(EditAllergies))
                System.out.format("%d:  Edit Allergies\n", EditAllergies);

            if (currentUser.hasAccess(DeleteAllergies))
                System.out.format("%d:  Delete Allergies\n", DeleteAllergies);

            if (currentUser.hasAccess(AddConsult))
                System.out.format("%d:  Add consult\n", AddConsult);

            if (currentUser.hasAccess(EditConsult))
                System.out.format("%d:  Edit Consult\n", EditConsult);

            if (currentUser.hasAccess(DeleteConsult))
                System.out.format("%d:  delete Consult\n", DeleteConsult);

            if (currentUser.hasAccess(LOGOUT))
                System.out.format("%d:  Logout\n", LOGOUT);



            System.out.print("enter #choice: ");
            int choice = scanner.nextInt();

            if (!currentUser.hasAccess(choice)) {
                System.out.println("Geen toegang tot deze optie!");
                continue;
            }

            switch (choice) {
                case STOP:
                    return false;

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
                            System.out.println("BMI: " + String.format("%.1f", currentPatient.bmi));





                            if (currentPatient.medicatie.isEmpty()) {
                                System.out.println("Medication: Geen medicatie");
                            } else {
                                System.out.println("Medication:");
                                for (MedicatieOpslag m : currentPatient.medicatie) {
                                    System.out.println("- " + m);
                                }
                            }
                            if (currentPatient.allergies.isEmpty()) {
                                System.out.println("Allergies: Geen allergieën");
                            } else {
                                System.out.println("Allergies:");
                                for (Allergies a : currentPatient.allergies) {
                                    System.out.println("- " + a);
                                }
                            }
                            if (currentPatient.consults.isEmpty()) {
                                System.out.println("Consultation: Geen consultaties");
                            } else {
                                System.out.println("Consultation:");
                                for (Consult c : currentPatient.consults) {
                                    System.out.println("- " + c);
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

                    System.out.print("Nieuwe longcapaciteit: ");
                    String newLungcap = scanner.nextLine();

                    if (!newLungcap.isEmpty()) {
                        currentPatient.lungcap = Double.parseDouble(newLungcap);
                    }

                    currentPatient.bmi = currentPatient.weight / (currentPatient.height * currentPatient.height);


                    System.out.println("Patient aangepast.");

                    break;

                case EditPatientLungcap:
                    System.out.println("Longcapaciteit bewerken voor: " + currentPatient.firstName + " " + currentPatient.surname);
                    scanner.nextLine();

                    System.out.print("Huidige longcapaciteit: " + currentPatient.lungcap + " L\n");
                    System.out.print("Nieuwe longcapaciteit: ");
                    String newLungcapValue = scanner.nextLine();

                    if (!newLungcapValue.isEmpty()) {
                        currentPatient.lungcap = Double.parseDouble(newLungcapValue);
                        System.out.println("Longcapaciteit aangepast naar: " + String.format("%.1f", currentPatient.lungcap) + " L");
                    } else {
                        System.out.println("Longcapaciteit niet gewijzigd.");
                    }
                    break;


                    case AddMeds:
                    scanner.nextLine();

                    System.out.println("Medicatie Opslag:");
                    System.out.println("- Paracetamol   - 50000 mg");
                    System.out.println("- Ibuprofen     - 20000 mg");
                    System.out.println("- Antibiotica   - 60000 mg");
                    System.out.println("- Antidepresiva - 30000 mg");

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


                case AddAllergies:
                    scanner.nextLine();

                    System.out.println("Allergie name: ");
                    String allergies = scanner.nextLine();

                    currentPatient.AddAllergies(allergies);

                    System.out.println("Allergie Added ");
                    break;

                case EditAllergies:
                    scanner.nextLine();

                    System.out.println("Allergie name to update: ");
                    String allergyToUpdate = scanner.nextLine();

                    System.out.println("New Allergie name: ");
                    String newAllergyName = scanner.nextLine();

                    currentPatient.updateAllergies(allergyToUpdate, newAllergyName);
                    break;

                case DeleteAllergies:
                    scanner.nextLine();

                    System.out.println("Allergie name to delete: ");
                    String allergyToDelete = scanner.nextLine();

                    currentPatient.deleteAllergies(allergyToDelete);
                    break;


                case AddConsult:
                    scanner.nextLine();

                    System.out.println("Consultation: ");
                    String consultation = scanner.nextLine();

                    currentPatient.AddConsult(consultation);

                    System.out.println("Consultation Added ");
                    break;

                case EditConsult:
                    scanner.nextLine();

                    System.out.println("Consultation: ");
                    String newConsultation = scanner.nextLine();

                    currentPatient.Editconsult(newConsultation);
                    break;

                case DeleteConsult:
                    if (currentPatient.consults.isEmpty()) {
                        System.out.println("Geen consultaties om te verwijderen.");
                    } else {
                        System.out.println("Huidige consultatie(s):");
                        for (int i = 0; i < currentPatient.consults.size(); i++) {
                            System.out.println((i + 1) + ": " + currentPatient.consults.get(i));
                        }

                        scanner.nextLine();
                        System.out.print("Wil je deze consult(s) verwijderen? (1: Ja, 2: Nee): ");
                        int deleteChoice = scanner.nextInt();

                        if (deleteChoice == 1) {
                            if (!currentPatient.consults.isEmpty()) {
                                currentPatient.consults.clear();
                                System.out.println("Alle consultaties verwijderd.");
                            }
                        } else if (deleteChoice == 2) {
                            System.out.println("Verwijdering geannuleerd.");
                        } else {
                            System.out.println("Ongeldige keuze.");
                        }
                    }
                    break;

                case LOGOUT:
                    Logout logout = new Logout();
                    logout.performLogout();
                    return true;

            }
        }
    }
}