import java.util.ArrayList;
import java.util.Scanner;

class ZorgApp {
    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<>();

        users.add(new Admin(1, "Admin"));
        users.add(new Huisarts(2, "Huisarts"));
        users.add(new Tandarts(3, "Tandarts"));
        users.add(new Fysio(4, "Fysio"));
        users.add(new Apotheker(5, "Apotheker"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Login ===");
            System.out.println("Beschikbare Gebruikers:");

            for (User user : users) {
                System.out.println(user.getUserID() + " - " + user.getUserName());
            }

            System.out.print("Voer user ID in: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Ongeldige invoer.");
                scanner.nextLine();
                continue;
            }

            int gekozenId = scanner.nextInt();
            scanner.nextLine();

            User ingelogdeUser = null;

            for (User user : users) {
                if (user.getUserID() == gekozenId) {
                    ingelogdeUser = user;
                    break;
                }
            }

            if (ingelogdeUser == null) {
                System.out.println("Gebruiker niet gevonden.\n");
                continue;
            }

            System.out.println("Login als: " + ingelogdeUser.getUserName());

            System.out.print("Voer gebruikersnaam in: ");
            String username = scanner.nextLine();

            System.out.print("Voer wachtwoord in: ");
            String password = scanner.nextLine();

            if (username.equalsIgnoreCase(ingelogdeUser.getUserName()) && password.equals("1234")) {
                System.out.println("Succesvol ingelogd!\n");

                Administration administration = new Administration(ingelogdeUser);
                administration.menu();
                break;
            } else {
                System.out.println("Gebruikersnaam of wachtwoord is fout.\n");
            }
        }
    }
}