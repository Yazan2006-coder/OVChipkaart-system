package OVchipkaart;

import java.util.Random;

public class Kaartlezer
{
    private boolean checkIn;
    private double instapTarief;
    private boolean checkOut;

    public Kaartlezer()
    {
	Random random = new Random();
	// Random instaptarief tussen €1.00 en €5.00
	this.instapTarief = 1.0 + (random.nextInt(400) / 100.0);
	this.checkIn = false;
	this.checkOut = false;
    }

    public void inchecken(Card ovChipkaart)
    {
	if (checkIn)
	{
	    System.out.println("Kaartlezer: kaart gelezen.");
	    ovChipkaart.inchecken(instapTarief);
	    ovChipkaart.checkIn();
	    this.checkIn = true;
	    this.checkOut = false;
	}
	else
	{
	    System.out.println("Kaartlezer: al ingecheckt! Uitchecken eerst.");
	}
    }

    public void uitchecken(Card ovChipkaart)
    {
	if (checkIn)
	{
	    // Random reiskosten tussen €0.50 en €3.00 die nog worden afgetrokken bij
	    // uitchecken
	    Random random = new Random();
	    double reiskosten = 0.50 + (random.nextInt(250) / 100.0);
	    System.out.printf("Reiskosten: €%.2f%n", reiskosten);
	    if (ovChipkaart.getSaldo() >= reiskosten)
	    {
		ovChipkaart.setSaldo(ovChipkaart.getSaldo() - reiskosten);
		System.out.printf("Uitgecheckt. Resterend saldo: €%.2f%n", ovChipkaart.getSaldo());
	    }
	    else
	    {
		System.out.println("Onvoldoende saldo voor reiskosten. Rit toch verwerkt.");
	    }
	    ovChipkaart.checkOut();
	    this.checkOut = true;
	    this.checkIn = false;
	}
	else
	{
	    System.out.println("Kaartlezer: niet ingecheckt!");
	}
    }

    public void readCard()
    {
	System.out.println("Kaart wordt gelezen...");
    }
    // Toont het huidige instaptarief
    public void showInstapTarief()
    {
	System.out.printf("Instaptarief: €%.2f%n", instapTarief);
    }
    // Geeft de incheck-status terug
    public boolean isCheckIn()
    {
	return checkIn;
    }
    // Geeft de uitcheck-status terug
    public boolean isCheckOut()
    {
	return checkOut;
    }
    // Geeft het instaptarief terug
    public double getInstapTarief()
    {
	return instapTarief;
    }
}
