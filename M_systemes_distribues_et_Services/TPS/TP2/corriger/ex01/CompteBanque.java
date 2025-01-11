public class CompteBanque
{
	private int sold;

	public int getSold()
	{
		return (this.sold);
	}

	public void setSold(int sold)
	{
		this.sold = sold;
	}

	public void retirer(int sold)
	{
		this.sold = this.sold - sold;
	}
}

