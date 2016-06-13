import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Pi {
	private static double count=0;
	private static double count1=0;
	private static double total=0; 
	private static double pi=0;
	
public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
	
private final static IntWritable one = new IntWritable(1);
private Text word = new Text();
private Text inside = new Text("inside");
private Text outside = new Text("outside");
private int r = 9;


public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

String line = value.toString();
StringTokenizer tokenizer = new StringTokenizer(line);


while (tokenizer.hasMoreTokens()) {
word.set(tokenizer.nextToken());
String initLetter = word.toString();
String text = Character.toString((initLetter.charAt(1)));
int x = Integer.parseInt(text);

//String initLetter1 = word.toString();
String text1 = Character.toString((initLetter.charAt(3)));
int y = Integer.parseInt(text1);
//System.out.println(x);

double hypo;

hypo = Math.sqrt(Math.pow(x-0,2) + Math.pow(y-0,2));
System.out.println("hypo"+hypo);

if(hypo<=r)
{
	context.write(inside,one);
	count++;
	
}
else
{
	context.write(outside,one);
	count1++;
	
}



}

total = count1+count;
System.out.println("total = "+total);

}
}

public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {
	
public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
int sum = 0;



for (IntWritable val : values) {
sum += val.get();

}


context.write(key, new IntWritable(sum));

if (key.toString().equals("outside")) {
	   
	System.out.println("inside = " +count);

	pi=count/total;
	pi=pi*4;
	System.out.println("pi = "+pi);
	context.write(new Text("Pi = "+pi), null);
    
}


}}

public static void main(String[] args) throws Exception {
	
Configuration conf = new Configuration();
Job job = new Job(conf, "Pi Calculation");
job.setJarByClass(Pi.class);
job.setOutputKeyClass(Text.class);
job.setOutputValueClass(IntWritable.class);
job.setMapperClass(Map.class);
job.setReducerClass(Reduce.class);
job.setInputFormatClass(TextInputFormat.class);
job.setOutputFormatClass(TextOutputFormat.class);

job.setNumReduceTasks(1);
FileInputFormat.addInputPath(job, new Path(args[0]));
FileOutputFormat.setOutputPath(job, new Path(args[1]));


job.waitForCompletion(true);
}

}