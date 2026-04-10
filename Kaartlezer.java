package OVchipkaart;

public class Kaartlezer
{
    private boolean checkIn;
    private double instapTarief;
    private Locatie locatie;
    private Locatie incheckStad;

    public Kaartlezer(Locatie locatie)
    {
        this.locatie = locatie;
        this.checkIn = false;
        // vast instaptarief
        this.instapTarief = 1.07;
        this.incheckStad = null;
    }

    public void inchecken(Card ovChipkaart)
    {
        if (!checkIn)
        {
            Locatie vanLocatie = ovChipkaart.getHuidigeLocatie();
            System.out.println("Kaartlezer: kaart gelezen bij " + vanLocatie.getNaam() + ".");
            ovChipkaart.inchecken(instapTarief);
            ovChipkaart.checkIn();
            ovChipkaart.setIncheckStad(vanLocatie);
            this.incheckStad = vanLocatie;
            this.checkIn = true;
        }
        else
        {
            System.out.println("Kaartlezer: al ingecheckt! Uitchecken eerst.");
        }
    }

    public void uitchecken(Card ovChipkaart)
    {
        if (checkIn || ovChipkaart.getIncheckStad() != null)
        {
            Locatie vanStad = ovChipkaart.getIncheckStad();

            // bereken afstand met stelling van pythagoras
            double dx = locatie.getX() - vanStad.getX();
            double dy = locatie.getY() - vanStad.getY();
            double afstand = Math.sqrt(dx * dx + dy * dy);

            // prijs is 0.12 per km
            double reiskosten = afstand * 0.12;

            System.out.println("Reis van " + vanStad.getNaam() + " naar " + locatie.getNaam());
            System.out.printf("Afstand (pythagoras): %.1f km%n", afstand);
            System.out.printf("Reiskosten: EUR %.2f%n", reiskosten);

            // instaptarief wordt teruggestort bij uitchecken
            ovChipkaart.setSaldo(ovChipkaart.getSaldo() + instapTarief);
            System.out.printf("Instaptarief van EUR %.2f teruggestort.%n", instapTarief);

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
            ovChipkaart.setIncheckStad(null);
            ovChipkaart.setHuidigeLocatie(locatie);
            System.out.println("Huidige locatie bijgewerkt naar " + locatie.getNaam() + ".");
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

    public Locatie getLocatie()
    {
        return locatie;
    }
}