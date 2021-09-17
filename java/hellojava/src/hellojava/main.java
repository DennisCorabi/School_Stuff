package hellojava;
import java.util.Scanner;
import java.util.Random;
import javax.swing.JOptionPane;

public class main {

	public static void main(String[] args) 
	{	
		Scanner scanner= new Scanner(System.in);
		System.out.println("inserisci la lughezza del vettore: ");
		int index= scanner.nextInt();
		System.out.println(index);
		int[] numbers = new int[index];
		
		for (int i=0;i<numbers.length;i++)
		{
			System.out.println("inserisci il numero "+i+": ");
			numbers[i]=scanner.nextInt();
		}
		for (int i=0; i<numbers.length; i++)
		{
			
			for (int y=0; y<numbers.length;y++)
			{
				if (numbers[i]<=numbers[y])
				{
					int temp;
					temp=numbers[i];
					numbers[i]=numbers[y];
					numbers[y]=temp;
					
				}
			}
		}
		System.out.println("i numeri ordinati sono: ");
		for (int i=0;i<numbers.length;i++)
			System.out.print(numbers[i]+"\t");
		scanner.close();
			
		
		
	}

}
