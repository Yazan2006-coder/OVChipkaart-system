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

    // Leest het huidige saldo van de kaart uit en slaat het op in saldoCheck
    public void readCard(Card kaart)
    {
        this.saldoCheck = kaart.getSaldo();
        System.out.printf("Kaart gelezen. Huidig saldo: EUR %.2f%n", saldoCheck);
    }

    // Toont het laatst uitgelezen saldo
    public void showSaldo()
    {
        System.out.printf("Saldo op kaart: EUR %.2f%n", saldoCheck);
    }

 // Vraagt de gebruiker welke oplaadmethode hij wil gebruiken:
    // Methode 1: voeg een vast bedrag toe aan het huidige saldo
    // Methode 2: laad op tot een gewenst eindbedrag
    public void oplaad(Card kaart, Scanner scanner) {
        double huidigSaldo = kaart.getSaldo();
        System.out.printf("Huidig saldo: EUR %.2f%n", huidigSaldo);
 
        // Laat de gebruiker kiezen welke methode hij wil gebruiken
        System.out.println("Hoe wilt u opladen?");
        System.out.println("  1. Vast bedrag toevoegen (bijv. EUR 10 erbij)");
        System.out.println("  2. Opladen tot een bepaald bedrag (bijv. tot EUR 20)");
        System.out.print("Kies methode (1 of 2): ");
 
        String methodekeuze = scanner.nextLine().trim();
 
        switch (methodekeuze) {
            case "1":
                // Methode 1: voeg een ingevoerd bedrag toe aan het huidige saldo
                oplaadVastBedrag(kaart, scanner, huidigSaldo);
                break;
            case "2":
                // Methode 2: laad op tot een gewenst eindbedrag
                oplaadTotDoelbedrag(kaart, scanner, huidigSaldo);
                break;
            default:
                System.out.println("Ongeldige keuze. Kies 1 of 2.");
        }
    }
 
    // Methode 1: vraagt een bedrag en telt dat op bij het huidige saldo
    private void oplaadVastBedrag(Card kaart, Scanner scanner, double huidigSaldo) {
        System.out.print("Hoeveel wilt u toevoegen? EUR ");
        try {
            double bedrag = Double.parseDouble(scanner.nextLine().trim());
 
            // Controleer of het ingevoerde bedrag positief is
            if (bedrag <= 0) {
                System.out.println("Ongeldig bedrag. Voer een positief getal in.");
                return;
            }
 
            this.saldoCharge = bedrag;
            kaart.setSaldo(huidigSaldo + saldoCharge);
            System.out.printf("EUR %.2f toegevoegd. Nieuw saldo: EUR %.2f%n", saldoCharge, kaart.getSaldo());
 
        } catch (NumberFormatException e) {
            // Wordt gegooid als de gebruiker iets anders dan een getal invoert
            System.out.println("Ongeldig bedrag. Voer een geldig getal in.");
        }
    }
 
    // Methode 2: vraagt een doelbedrag en laadt het verschil op zodat het saldo
    // precies op het doelbedrag uitkomt
    private void oplaadTotDoelbedrag(Card kaart, Scanner scanner, double huidigSaldo) {
        System.out.print("Tot welk bedrag wilt u opladen? EUR ");
        try {
            double doelbedrag = Double.parseDouble(scanner.nextLine().trim());
 
            // Controleer of het doelbedrag positief is
            if (doelbedrag <= 0) {
                System.out.println("Ongeldig bedrag. Voer een positief getal in.");
                return;
            }
 
            // Controleer of het doelbedrag hoger is dan het huidige saldo
            if (doelbedrag <= huidigSaldo) {
                System.out.printf("Uw saldo is al EUR %.2f. Kies een bedrag hoger dan uw huidig saldo.%n", huidigSaldo);
                return;
            }
 
            // Bereken het verschil dat opgeladen moet worden
            this.saldoCharge = doelbedrag - huidigSaldo;
            kaart.setSaldo(doelbedrag);
            System.out.printf("EUR %.2f opgeladen. Saldo is nu precies EUR %.2f%n", saldoCharge, kaart.getSaldo());
 
        } catch (NumberFormatException e) {
            // Wordt gegooid als de gebruiker iets anders dan een getal invoert
            System.out.println("Ongeldig bedrag. Voer een geldig getal in.");
        }
    }
 
    // Geeft het opgeladen bedrag terug
    public double getSaldoCharge() { return saldoCharge; }
 
    // Stelt het op te laden bedrag in
    public void setSaldoCharge(double saldoCharge) { this.saldoCharge = saldoCharge; }
}