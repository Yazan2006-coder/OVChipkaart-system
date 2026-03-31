package OVchipkaart;

import java.util.Random;
import java.util.Scanner;

public class CardTest
{
    public static void main(String[] args)
    {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        // willekeurig beginsaldo tussen 5 en 25
        double beginSaldo = 5.0 + (random.nextInt(2000) / 100.0);
        Card kaart = new Card(1001, beginSaldo);
        Kaartlezer lezer = new Kaartlezer();
        OplaadPunt oplaadPunt = new OplaadPunt();

        System.out.println("=== OV Chipkaart Systeem ===");
        System.out.printf("Welkom! Uw beginsaldo: EUR %.2f%n", beginSaldo);

        boolean running = true;
        while (running)
        {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Inchecken");
            System.out.println("2. Uitchecken");
            System.out.println("3. Saldo bekijken");
            System.out.println("4. Saldo opladen");
            System.out.println("5. Afsluiten");
            System.out.print("Kies een optie: ");

            String keuze = scanner.nextLine().trim();

            switch (keuze)
            {
                case "1":
                    System.out.println("\n-- Inchecken --");
                    lezer.showInstapTarief();
                    lezer.inchecken(kaart);
                    break;

                case "2":
                    System.out.println("\n-- Uitchecken --");
                    if (!lezer.isCheckIn())
                    {
                        System.out.println("U kunt niet uitchecken zonder eerst ingecheckt te zijn!");
                    }
                    else
                    {
                        lezer.uitchecken(kaart);
                    }
                    break;

                case "3":
                    System.out.println("\n-- Saldo bekijken --");
                    oplaadPunt.readCard(kaart);
                    break;

                case "4":
                    System.out.println("\n-- Saldo opladen --");
                    oplaadPunt.oplaad(kaart, scanner);
                    break;

                case "5":
                    System.out.println("Tot ziens!");
                    running = false;
                    break;

                default:
                    System.out.println("Ongeldige keuze. Kies 1 t/m 5.");
            }
        }

        scanner.close();
    }
}
