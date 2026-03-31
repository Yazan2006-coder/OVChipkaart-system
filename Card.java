package OVchipkaart;

public class Card
{
    private double saldo;
    private int cardId;

    // maakt een nieuwe kaart aan
    public Card(int cardId, double beginSaldo)
    {
        this.cardId = cardId;
        this.saldo = beginSaldo;
    }

    // inchecken, saldo wordt minder
    public void inchecken(double instapTarief)
    {
        if (saldo >= instapTarief)
        {
            saldo -= instapTarief;
            System.out.printf("Ingecheckt. Saldo na instaptarief: EUR %.2f%n", saldo);
        }
        else
        {
            System.out.println("Onvoldoende saldo om in te checken!");
        }
    }

    public void checkIn()
    {
        System.out.println("Kaart " + cardId + " heeft ingecheckt.");
    }

    public void checkOut()
    {
        System.out.println("Kaart " + cardId + " heeft uitgecheckt.");
    }

    public double getSaldo()
    {
        return saldo;
    }

    // saldo mag niet negatief zijn
    public void setSaldo(double saldo)
    {
        if (saldo < 0)
        {
            this.saldo = 0;
        }
        else
        {
            this.saldo = saldo;
        }
    }

    public int getCardId()
    {
        return cardId;
    }
}
