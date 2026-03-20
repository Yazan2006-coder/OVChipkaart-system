package OVchipkaart;

import java.util.Random;
import java.util.Scanner;

public class CardTest
{
    // Minimale en maximale beginsaldo als benoemde constanten
    private static final double MIN_BEGIN_SALDO = 5.0;
    private static final double MAX_BEGIN_SALDO = 25.0;

    public static void main(String[] args)
    {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        // Genereer een willekeurig beginsaldo tussen EUR 5.00 en EUR 25.00
        double beginSaldo = MIN_BEGIN_SALDO + (random.nextInt((int)((MAX_BEGIN_SALDO - MIN_BEGIN_SALDO) * 100)) / 100.0);
        Card kaart = new Card(1001, beginSaldo);
        Kaartlezer lezer = new Kaartlezer();
        OplaadPunt oplaadPunt = new OplaadPunt();

        System.out.println("=== OV Chipkaart Systeem ===");
        System.out.printf("Welkom! Uw beginsaldo: EUR %.2f%n", beginSaldo);

        boolean running = true;
        // Blijf het menu tonen totdat de gebruiker kiest om af te sluiten
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
                    // Toon instaptarief en check de kaart in
                    System.out.println("\n-- Inchecken --");
                    lezer.showInstapTarief();
                    lezer.inchecken(kaart);
                    break;

                case "2":
                    // Uitchecken is alleen mogelijk als de kaart eerst is ingecheckt
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
                    // Lees de kaart uit en toon het huidige saldo (enkel readCard — showSaldo zou het dubbel tonen)
                    System.out.println("\n-- Saldo bekijken --");
                    oplaadPunt.readCard(kaart);
                    break;

                case "4":
                    // Laat de gebruiker een bedrag invoeren om op te laden
                    System.out.println("\n-- Saldo opladen --");
                    oplaadPunt.oplaad(kaart, scanner);
                    break;

                case "5":
                    // Stopt de loop en sluit het programma af
                    System.out.println("Tot ziens!");
                    running = false;
                    break;

                default:
                    // Ongeldige invoer, vraag opnieuw
                    System.out.println("Ongeldige keuze. Kies 1 t/m 5.");
            }
        }

        scanner.close();
    }
}