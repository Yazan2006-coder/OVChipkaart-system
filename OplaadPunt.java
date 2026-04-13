package OVchipkaart;

import java.util.Scanner;

public class OplaadPunt
{
    private double saldoCharge;
    private double saldoCheck;

    public OplaadPunt()
    {
        this.saldoCharge = 0;
        this.saldoCheck = 0;
    }

    public void readCard(Card kaart)
    {
        this.saldoCheck = kaart.getSaldo();
        System.out.printf("Kaart gelezen. Huidig saldo: EUR %.2f%n", saldoCheck);
    }

    public void showSaldo()
    {
        System.out.printf("Saldo op kaart: EUR %.2f%n", saldoCheck);
    }

    // opladen, gebruiker kiest methode
    public void oplaad(Card kaart, Scanner scanner)
    {
        double huidigSaldo = kaart.getSaldo();
        System.out.printf("Huidig saldo: EUR %.2f%n", huidigSaldo);

        System.out.println("Hoe wilt u opladen?");
        System.out.println("  1. Vast bedrag toevoegen (bijv. EUR 10 erbij)");
        System.out.println("  2. Opladen tot een bepaald bedrag (bijv. tot EUR 20)");
        System.out.println("  0. Annuleren");
        System.out.print("Kies methode (1, 2 of 0): ");

        String methodekeuze = scanner.nextLine().trim();

        if (methodekeuze.equals("0"))
        {
            System.out.println("Opladen geannuleerd.");
        }
        else if (methodekeuze.equals("1"))
        {
            oplaadMetBedrag(kaart, scanner, huidigSaldo);
        }
        else if (methodekeuze.equals("2"))
        {
            oplaadTotBedrag(kaart, scanner, huidigSaldo);
        }
        else
        {
            System.out.println("Ongeldige keuze. Kies 1, 2 of 0.");
        }
    }

    // methode 1: voeg een vast bedrag toe aan het huidige saldo
    private void oplaadMetBedrag(Card kaart, Scanner scanner, double huidigSaldo)
    {
        System.out.print("Hoeveel wilt u toevoegen? EUR ");
        try
        {
            double bedrag = Double.parseDouble(scanner.nextLine().trim());

            if (bedrag <= 0)
            {
                System.out.println("Ongeldig bedrag. Voer een positief getal in.");
                return;
            }

            this.saldoCharge = bedrag;
            kaart.setSaldo(huidigSaldo + saldoCharge);
            System.out.printf("EUR %.2f toegevoegd. Nieuw saldo: EUR %.2f%n", saldoCharge, kaart.getSaldo());
        }
        catch (NumberFormatException e)
        {
            System.out.println("Ongeldig bedrag. Voer een geldig getal in.");
        }
    }

    // methode 2: laad op tot een doelbedrag
    private void oplaadTotBedrag(Card kaart, Scanner scanner, double huidigSaldo)
    {
        System.out.print("Tot welk bedrag wilt u opladen? EUR ");
        try
        {
            double doelbedrag = Double.parseDouble(scanner.nextLine().trim());

            if (doelbedrag <= 0)
            {
                System.out.println("Ongeldig bedrag. Voer een positief getal in.");
                return;
            }

            if (doelbedrag <= huidigSaldo)
            {
                System.out.printf("Uw saldo is al EUR %.2f. Kies een bedrag hoger dan uw huidig saldo.%n", huidigSaldo);
                return;
            }

            this.saldoCharge = doelbedrag - huidigSaldo;
            kaart.setSaldo(doelbedrag);
            System.out.printf("EUR %.2f opgeladen. Saldo is nu precies EUR %.2f%n", saldoCharge, kaart.getSaldo());
        }
        catch (NumberFormatException e)
        {
            System.out.println("Ongeldig bedrag. Voer een geldig getal in.");
        }
    }

    public double getSaldoCharge() { return saldoCharge; }

    public void setSaldoCharge(double saldoCharge) { this.saldoCharge = saldoCharge; }
}