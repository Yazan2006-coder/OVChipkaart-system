package OVchipkaart;

import java.util.Random;
import java.util.Scanner;

public class CardTest
{
    public static void main(String[] args)
    {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        // steden aanmaken met x en y coordinaten
        // coordinaten zijn gebaseerd op een simpele kaart van nederland
        Locatie amsterdam  = new Locatie("Amsterdam",  0,    0);
        Locatie rotterdam  = new Locatie("Rotterdam",  -45,  -60);
        Locatie denhaag    = new Locatie("Den Haag",   -55,  -70);
        Locatie utrecht    = new Locatie("Utrecht",    10,   -30);
        Locatie arnhem     = new Locatie("Arnhem",     60,   -50);
        Locatie eindhoven  = new Locatie("Eindhoven",  20,   -110);
        Locatie groningen  = new Locatie("Groningen",  80,   120);
        Locatie maastricht = new Locatie("Maastricht", 30,   -170);

        // kaartlezers aanmaken op elke locatie
        Kaartlezer lezerAmsterdam  = new Kaartlezer(amsterdam);
        Kaartlezer lezerRotterdam  = new Kaartlezer(rotterdam);
        Kaartlezer lezerDenhaag    = new Kaartlezer(denhaag);
        Kaartlezer lezerUtrecht    = new Kaartlezer(utrecht);
        Kaartlezer lezerArnhem     = new Kaartlezer(arnhem);
        Kaartlezer lezerEindhoven  = new Kaartlezer(eindhoven);
        Kaartlezer lezerGroningen  = new Kaartlezer(groningen);
        Kaartlezer lezerMaastricht = new Kaartlezer(maastricht);

        // alle lezers in een array zodat we ze makkelijk kunnen opzoeken
        Kaartlezer[] lezers = {
            lezerAmsterdam, lezerRotterdam, lezerDenhaag, lezerUtrecht,
            lezerArnhem, lezerEindhoven, lezerGroningen, lezerMaastricht
        };

        // willekeurig beginsaldo tussen 5 en 25
        double beginSaldo = 5.0 + (random.nextInt(2000) / 100.0);
        // beginlocatie is Amsterdam
        Card kaart = new Card(1001, beginSaldo, amsterdam);
        OplaadPunt oplaadPunt = new OplaadPunt();

        System.out.println("=== OV Chipkaart Systeem ===");
        System.out.printf("Welkom! Uw beginlocatie: %s%n", kaart.getHuidigeLocatie().getNaam());
        System.out.printf("Uw beginsaldo: EUR %.2f%n", beginSaldo);

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
                    System.out.println("Huidige locatie: " + kaart.getHuidigeLocatie().getNaam());
                    // zoek de kaartlezer op de huidige locatie van de kaart
                    Kaartlezer incheckLezer = zoekLezer(lezers, kaart.getHuidigeLocatie());
                    incheckLezer.showInstapTarief();
                    incheckLezer.inchecken(kaart);
                    break;

                case "2":
                    System.out.println("\n-- Uitchecken --");
                    if (kaart.getIncheckStad() == null)
                    {
                        System.out.println("U kunt niet uitchecken zonder eerst ingecheckt te zijn!");
                    }
                    else
                    {
                        Kaartlezer uitcheckLezer = kiesKaartlezer(scanner, lezers);
                        if (uitcheckLezer != null)
                        {
                            uitcheckLezer.uitchecken(kaart);
                        }
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

    // zoek de kaartlezer die op de gegeven stad staat
    static Kaartlezer zoekLezer(Kaartlezer[] lezers, Locatie stad)
    {
        for (int i = 0; i < lezers.length; i++)
        {
            if (lezers[i].getLocatie().getNaam().equals(stad.getNaam()))
            {
                return lezers[i];
            }
        }
        // als er geen lezer gevonden wordt, geef de eerste terug
        return lezers[0];
    }

    // laat de gebruiker een kaartlezer kiezen op basis van locatie
    static Kaartlezer kiesKaartlezer(Scanner scanner, Kaartlezer[] lezers)
    {
        System.out.println("Kies een station:");
        for (int i = 0; i < lezers.length; i++)
        {
            System.out.println((i + 1) + ". " + lezers[i].getLocatie().getNaam());
        }
        System.out.print("Kies een station (1-" + lezers.length + "): ");

        String keuze = scanner.nextLine().trim();

        try
        {
            int index = Integer.parseInt(keuze) - 1;
            if (index >= 0 && index < lezers.length)
            {
                return lezers[index];
            }
            else
            {
                System.out.println("Ongeldige keuze.");
                return null;
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("Ongeldige invoer.");
            return null;
        }
    }

}