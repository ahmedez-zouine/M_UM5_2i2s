public class Transfert implimantes Runnbale
{
    private double sold;
    private final Comptes cmp;
    private double index;
    public double my_sold;

    public Transfert(comptes cmp, int index, double sold)
    {
        this.cmp = cmp;
        this.index = index;
        this.sold = sold;
    }
    
    double do_sold(int index)
    {
        if (index >= this.index)
        {
            if (index != 100)
                return (this.cmp[index] + this.cmp[index-1]);
            return (this.cmp[index]);
        }
        return (-1);
    }
    void run()
    {
        my_sold += do_sold(this.index);
        System.out.println(my_sold);
    }
}