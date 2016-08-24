import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class MergeTera 
{
	static Scanner s[];							//scanner array for storing scanner objects for all files scan objects
	static BufferedWriter fw;					//writer stream for final sorted file
	public static void merge(long len,long size,long t1) throws Exception
	{
		String min;
		int d=(int)(len/size);
		System.out.println("Number of files created "+d);
		String data[]=new String[d];			//array for storing first line of each file
		s=new Scanner[d];						//Defining array for storing the objects
		int j=0;
		for(int i=0;i<d;i++)					//loop for reading first line of each file
		{
			s[i]=new Scanner(new File("data"+(i+1)+".txt"));		//creating read object of each file
			data[i]=s[i].nextLine();										//reading first line of each file
		}
		fw=new BufferedWriter(new FileWriter("SORTED.txt"));		//file object for the final sorted file
		long record=0;
		
		while(record<(size*d))
		{
			min="~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
					+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";		//initially taking min string as maximum
			for(int i=0;i<d;i++)
			{
				if(min.compareTo(data[i])>0){						//condition for min string
					min=data[i];												//fining minimum string
					j=i;				
				}
			}
			fw.write(data[j]+"\n");												//writing min string to file in sorted order
			fw.flush();
			record++;
			if(s[j].hasNext())											//checking if that file has more content
				data[j]=s[j].nextLine();								//reading from the corresponding file
			else{
				data[j]="~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
						+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";//storing maximum to the array for finished file
				s[j].close();								//closing the completely read file
				new File("data"+(j+1)+".txt").delete();					//deleting the intermediate files
			}
		}
		fw.write(data[j]+"\n");							//writing last line to file
		long t2=System.currentTimeMillis();
		long time=t2-t1;
		System.out.println("Time taken to sort "+time+" milliseconds");	//calculating and printing total time
		fw.flush();
		fw.close();								//saving the final file
		time=time/1000;
		float thr=(float)len/(1024*10)/time;
		System.out.println("Throughput is " + thr +" MB/s");
	}
	public static void main(String ar[]) throws Exception{
	}
}
