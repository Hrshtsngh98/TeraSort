import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class terasort {
	static File f,w;			//declaring 1TB file object and smaller write file objects
	static BufferedReader br;	//Stream for 1TB file
	static FileWriter fw;		//Reader for 1TB file
	static String line;			//variable for reading string from file
	static Scanner s=new Scanner(System.in);
	public static void main(String ar[]) throws Exception
	{
		System.out.println("Enter Unsorted File Address");			//taking input address of file
		String add=s.nextLine();
		f=new File(add);
		System.out.println("Your file size "+f.length()/100);
		br=new BufferedReader(new FileReader(f));				//defining reader stream for file
		System.out.println("Enter partition size of files you want to create(1GB=10000000)");
		long size=s.nextLong();
		int i=1,j=1;
		List<String> list = new ArrayList<>();		//declaring ArrayList for sorting file
		long t1=System.currentTimeMillis();
	while((line=br.readLine())!=null)			//reading file until file ends
	{	
		list.add(line);							//adding read line to a list
		if(i%size==0)							//Creating a defined file size
		{
		Collections.sort(list);					//sending list to sort
		String fname="data"+j+".txt";			//creating new file
		w=new File(fname);						//new file object
		fw=new FileWriter(w);					//file write object stream
		for(String l:list)						//loop for writing sorted list
		{						
			fw.write(l+"\n");					//writing each line to file	
		}
		fw.flush();
		fw.close();								//saving a file
		list.clear();							//clearing list for new start
		j++;
		}
		i++;
	}
	br.close();
	MergeTera.merge(f.length()/100,size,t1);	//Calling merging function
	}
}
