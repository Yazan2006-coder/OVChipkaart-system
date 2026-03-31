package OVchipkaart;

import java.util.Random;

public class Kaartlezer
{
    private boolean checkIn;
    private double instapTarief;

    public Kaartlezer()
    {
        // willekeurig instaptarief tussen 1 en 5 euro
        Random random = new Random();
        this.instapTarief = 1.0 + (random.nextInt(400) / 100.0);
        this.checkIn = false;
    }

    public void inchecken(Card ovChipkaart)
    {
	// met ! wordt de boolean's waarde omgekeerd
        if (!checkIn)
        {
            System.out.println("Kaartlezer: kaart gelezen.");
            ovChipkaart.inchecken(instapTarief);
            ovChipkaart.checkIn();
            this.checkIn = true;
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
            // willekeurige reiskosten
            Random random = new Random();
            double reiskosten = 0.50 + (random.nextInt(250) / 100.0);
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
        }
        else
        {
            System.out.println("Kaartlezer: niet ingecheckt!");
        }
    }
 // Toont het huidige instaptarief
    public void showInstapTarief()
    {
        System.out.printf("Instaptarief: EUR %.2f%n", instapTarief);
    }
 // Geeft de incheck-status terug
    public boolean isCheckIn()
    {
        return checkIn;
    }
 // Geeft het instaptarief terug
    public double getInstapTarief()
    {
        return instapTarief;
    }
}
