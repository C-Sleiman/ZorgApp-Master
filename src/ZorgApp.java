import java.util.ArrayList;
import java.util.Scanner;

class ZorgApp {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();

        users.add(new User(1, "Admin"));
        users.add(new User(2, "Huisarts"));
        users.add(new User(3, "Tandarts"));
        users.add(new User(4, "Fysio"));
        users.add(new User(5, "Apotheker"));

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Login ===");
        System.out.println("Beschikbare Gebruikers:");

        for (User user : users) {
            System.out.println(user.getUserID() + " - " + user.getUserName());
        }

        System.out.print("Voer user ID in: ");
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
            System.out.println("Ongeldige login. Gebruiker niet gevonden.");
            return;
        }

        System.out.print("Voer gebruikersnaam in: ");
        String ingevoerdeGebruikersnaam = scanner.nextLine();

        System.out.print("Voer wachtwoord in: ");
        String ingevoerdWachtwoord = scanner.nextLine();

        if (ingevoerdeGebruikersnaam.equalsIgnoreCase(ingelogdeUser.getUserName())
                && ingevoerdWachtwoord.equals("1234")) {

            System.out.println("Ingelogd als: " + ingelogdeUser.getUserName());

            Administration administration = new Administration(ingelogdeUser);
            administration.menu();
        } else {
            System.out.println("Gebruikersnaam of wachtwoord is fout.");
        }
    }
}