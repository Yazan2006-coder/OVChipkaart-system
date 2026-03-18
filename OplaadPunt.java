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
    // Vraagt de gebruiker hoeveel hij wil opladen en voegt dat toe aan het saldo
    // bevestiging van de invoer: bedrag moet positief zijn en een geldig getal
    public void oplaad(Card kaart, Scanner scanner)
    {
	System.out.print("Hoeveel wilt u opladen? EUR ");
	try
	{
	    double bedrag = Double.parseDouble(scanner.nextLine().trim());
	    if (bedrag <= 0)
	    {
		System.out.println("Ongeldig bedrag. Voer een positief getal in.");
		return;
	    }
	    this.saldoCharge = bedrag;
	    kaart.setSaldo(kaart.getSaldo() + saldoCharge);
	    System.out.printf("EUR %.2f opgeladen. Nieuw saldo: EUR %.2f%n", saldoCharge, kaart.getSaldo());
	}
	catch (NumberFormatException e){
	 // Wordt gegooid als de gebruiker iets anders dan een getal invoert
	    System.out.println("Ongeldig bedrag. Voer een geldig getal in.");
	}
    }

    public double getSaldoCharge()
    {
	return saldoCharge;
    }

    public void setSaldoCharge(double saldoCharge)
    {
	this.saldoCharge = saldoCharge;
    }
}