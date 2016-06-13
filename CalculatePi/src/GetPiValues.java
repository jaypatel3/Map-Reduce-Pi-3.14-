import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;




public class GetPiValues {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		//System.out.println("Enter an integer:");
		Scanner input =new Scanner(System.in);
		Random r = new Random();
		int index = r.nextInt(1000000);
		System.out.println(index);
		
		int num[] = new int[index];
		for(int i=0;i<index;i++){
		    num[i] = r.nextInt(10);
		    System.out.print("("+num[i]+",");
		    
		    num[i] = r.nextInt(10);
		    System.out.print(num[i]+") ");
		    
}

	}

}
