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
        System.out.printf("Kaart gelezen. Huidig saldo: EUR %.2f%n", huidigSaldo);

        System.out.println("Hoe wilt u opladen?");
        System.out.println("  1. Vast bedrag toevoegen (bijv. EUR 10 erbij)");
        System.out.println("  2. Opladen tot een bepaald bedrag (bijv. tot EUR 20)");
        System.out.print("Kies methode (1 of 2): ");

        String methodekeuze = scanner.nextLine().trim();

        if (methodekeuze.equals("1"))
        {
            // methode 1: voeg een bedrag toe
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
        else if (methodekeuze.equals("2"))
        {
            // methode 2: laad op tot een doelbedrag
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
        else
        {
            System.out.println("Ongeldige keuze. Kies 1 of 2.");
        }
    }

    public double getSaldoCharge() { return saldoCharge; }

    public void setSaldoCharge(double saldoCharge) { this.saldoCharge = saldoCharge; }
}
