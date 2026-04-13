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

        Kaartlezer[] lezers = {
            lezerAmsterdam, lezerRotterdam, lezerDenhaag, lezerUtrecht,
            lezerArnhem, lezerEindhoven, lezerGroningen, lezerMaastricht
        };

        // meerdere kaarten aanmaken met uniek id en willekeurig beginsaldo
        Card[] kaarten = {
            new Card(1001, 5.0 + (random.nextInt(2000) / 100.0), amsterdam),
            new Card(1002, 5.0 + (random.nextInt(2000) / 100.0), rotterdam),
            new Card(1003, 5.0 + (random.nextInt(2000) / 100.0), utrecht),
            new Card(1004, 5.0 + (random.nextInt(2000) / 100.0), arnhem)
        };

        OplaadPunt oplaadPunt = new OplaadPunt();

        System.out.println("=== OV Chipkaart Systeem ===");

        // buitenste loop: blijf op het kaart-keuze scherm tot de gebruiker afsluit
        boolean programmaActief = true;
        while (programmaActief)
        {
            // kaart kiezer scherm
            Card geselecteerdeKaart = kiesKaart(scanner, kaarten);

            // null betekent dat de gebruiker koos om af te sluiten
            if (geselecteerdeKaart == null)
            {
                System.out.println("Programma afgesloten. Tot ziens!");
                programmaActief = false;
                continue;
            }

            System.out.println("\nKaart " + geselecteerdeKaart.getCardId() + " geselecteerd.");
            System.out.printf("Locatie: %s | Saldo: EUR %.2f%n",
                geselecteerdeKaart.getHuidigeLocatie().getNaam(),
                geselecteerdeKaart.getSaldo());

            // binnenste loop: hoofdmenu voor de geselecteerde kaart
            boolean menuActief = true;
            while (menuActief)
            {
                System.out.println("\n--- Menu (kaart " + geselecteerdeKaart.getCardId() + ") ---");
                System.out.println("1. Inchecken");
                System.out.println("2. Uitchecken");
                System.out.println("3. Saldo bekijken");
                System.out.println("4. Saldo opladen");
                System.out.println("5. Kaart wisselen");
                System.out.print("Kies een optie: ");

                String keuze = scanner.nextLine().trim();

                switch (keuze)
                {
                    case "1":
                        System.out.println("\n-- Inchecken --");
                        System.out.println("Huidige locatie: " + geselecteerdeKaart.getHuidigeLocatie().getNaam());
                        Kaartlezer incheckLezer = zoekLezer(lezers, geselecteerdeKaart.getHuidigeLocatie());
                        incheckLezer.showInstapTarief();
                        incheckLezer.inchecken(geselecteerdeKaart);
                        break;

                    case "2":
                        System.out.println("\n-- Uitchecken --");
                        if (geselecteerdeKaart.getIncheckStad() == null)
                        {
                            System.out.println("U kunt niet uitchecken zonder eerst ingecheckt te zijn!");
                        }
                        else
                        {
                            Kaartlezer uitcheckLezer = kiesKaartlezer(scanner, lezers);
                            if (uitcheckLezer != null)
                            {
                                uitcheckLezer.uitchecken(geselecteerdeKaart);
                            }
                        }
                        break;

                    case "3":
                        System.out.println("\n-- Saldo bekijken --");
                        oplaadPunt.readCard(geselecteerdeKaart);
                        break;

                    case "4":
                        System.out.println("\n-- Saldo opladen --");
                        oplaadPunt.oplaad(geselecteerdeKaart, scanner);
                        break;

                    case "5":
                        // terug naar kaart kiezer
                        System.out.println("Terug naar kaartkeuze...");
                        menuActief = false;
                        break;

                    default:
                        System.out.println("Ongeldige keuze. Kies 1 t/m 5.");
                }
            }
        }

        scanner.close();
    }

    // toont alle kaarten en laat de gebruiker er een kiezen
    // geeft null terug als de gebruiker wil afsluiten
    static Card kiesKaart(Scanner scanner, Card[] kaarten)
    {
        System.out.println("\n=== Kaartkeuze ===");
        System.out.println("Welke kaart wilt u gebruiken?");

        for (int i = 0; i < kaarten.length; i++)
        {
            System.out.printf("%d. Kaart %d | Locatie: %s | Saldo: EUR %.2f%n",
                (i + 1),
                kaarten[i].getCardId(),
                kaarten[i].getHuidigeLocatie().getNaam(),
                kaarten[i].getSaldo());
        }

        System.out.println("0. Afsluiten");
        System.out.print("Kies een kaart: ");

        String keuze = scanner.nextLine().trim();

        // 0 = afsluiten
        if (keuze.equals("0"))
        {
            return null;
        }

        try
        {
            int index = Integer.parseInt(keuze) - 1;
            if (index >= 0 && index < kaarten.length)
            {
                return kaarten[index];
            }
            else
            {
                System.out.println("Ongeldige keuze.");
                return kiesKaart(scanner, kaarten);
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("Ongeldige invoer.");
            return kiesKaart(scanner, kaarten);
        }
    }

    // zoek de kaartlezer die op de gegeven stad staat
    static Kaartlezer zoekLezer(Kaartlezer[] lezers, Locatie Locatie)
    {
        for (int i = 0; i < lezers.length; i++)
        {
            if (lezers[i].getLocatie().getNaam().equals(Locatie.getNaam()))
            {
                return lezers[i];
            }
        }
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