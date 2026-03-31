package OVchipkaart;

import java.util.Scanner;

public class Kaartlezer
{
    private boolean checkIn;
    private double instapTarief;
    private Locatie incheckStad;

    // alle beschikbare steden met x en y coordinaten
    // coordinaten zijn gebaseerd op een simpele kaart van nederland
    Locatie amsterdam  = new Locatie("Amsterdam",  0,    0);
    Locatie rotterdam  = new Locatie("Rotterdam",  -45,  -60);
    Locatie denhaag    = new Locatie("Den Haag",   -55,  -70);
    Locatie utrecht    = new Locatie("Utrecht",    10,   -30);
    Locatie arnhem     = new Locatie("Arnhem",     60,   -50);
    Locatie eindhoven  = new Locatie("Eindhoven",  20,   -110);
    Locatie groningen  = new Locatie("Groningen",  80,   120);
    Locatie maastricht = new Locatie("Maastricht", 30,   -170);

    public Kaartlezer()
    {
        this.checkIn = false;
        // vast instaptarief
        this.instapTarief = 1.07;
        this.incheckStad = null;
    }

    // laat de gebruiker een stad kiezen uit de lijst
    public Locatie kiesStad(Scanner scanner)
    {
        System.out.println("Kies een stad:");
        System.out.println("1. Amsterdam");
        System.out.println("2. Rotterdam");
        System.out.println("3. Den Haag");
        System.out.println("4. Utrecht");
        System.out.println("5. Arnhem");
        System.out.println("6. Eindhoven");
        System.out.println("7. Groningen");
        System.out.println("8. Maastricht");
        System.out.print("Kies een stad (1-8): ");

        String keuze = scanner.nextLine().trim();

        if (keuze.equals("1"))
        {
            return amsterdam;
        }
        else if (keuze.equals("2"))
        {
            return rotterdam;
        }
        else if (keuze.equals("3"))
        {
            return denhaag;
        }
        else if (keuze.equals("4"))
        {
            return utrecht;
        }
        else if (keuze.equals("5"))
        {
            return arnhem;
        }
        else if (keuze.equals("6"))
        {
            return eindhoven;
        }
        else if (keuze.equals("7"))
        {
            return groningen;
        }
        else if (keuze.equals("8"))
        {
            return maastricht;
        }
        else
        {
            System.out.println("Ongeldige keuze, Amsterdam wordt gekozen.");
            return amsterdam;
        }
    }

    public void inchecken(Card ovChipkaart, Scanner scanner)
    {
        if (!checkIn)
        {
            // laat gebruiker incheckstad kiezen
            incheckStad = kiesStad(scanner);
            System.out.println("Kaartlezer: kaart gelezen bij " + incheckStad.getNaam() + ".");
            ovChipkaart.inchecken(instapTarief);
            ovChipkaart.checkIn();
            this.checkIn = true;
        }
        else
        {
            System.out.println("Kaartlezer: al ingecheckt! Uitchecken eerst.");
        }
    }

    public void uitchecken(Card ovChipkaart, Scanner scanner)
    {
        if (checkIn)
        {
            // laat gebruiker uitcheckstad kiezen
            System.out.println("Waar checkt u uit?");
            Locatie uitcheckStad = kiesStad(scanner);

            // bereken afstand met stelling van pythagoras
            double dx = uitcheckStad.getX() - incheckStad.getX();
            double dy = uitcheckStad.getY() - incheckStad.getY();
            double afstand = Math.sqrt(dx * dx + dy * dy);

            // prijs is 0.12 per km
            double reiskosten = afstand * 0.12;

            System.out.println("Reis van " + incheckStad.getNaam() + " naar " + uitcheckStad.getNaam());
            System.out.printf("Afstand (pythagoras): %.1f km%n", afstand);
            System.out.printf("Reiskosten: EUR %.2f%n", reiskosten);

            if (ovChipkaart.getSaldo() >= reiskosten)
            {
                ovChipkaart.setSaldo(ovChipkaart.getSaldo() - reiskosten);
                System.out.printf("Uitgecheckt. Resterend saldo: EUR %.2f%n", ovChipkaart.getSaldo());
            }
            else
            {
                System.out.println("Onvoldoende saldo voor reiskosten. Rit toch verwerkt.");
            }

            ovChipkaart.checkOut();
            this.checkIn = false;
            this.incheckStad = null;
        }
        else
        {
            System.out.println("Kaartlezer: niet ingecheckt!");
        }
    }

    public void showInstapTarief()
    {
        System.out.printf("Instaptarief: EUR %.2f%n", instapTarief);
    }

    public boolean isCheckIn()
    {
        return checkIn;
    }

    public double getInstapTarief()
    {
        return instapTarief;
    }
}
