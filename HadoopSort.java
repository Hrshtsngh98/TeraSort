package com.map.reduce.sort;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class HadoopSort {
	public static void main(String [] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException{
		    Job j=Job.getInstance( new Configuration(), "Hadoop Sort");
		    j.setJarByClass(MapReduceSort.class);			
		    j.setMapperClass(SortMapper.class);				//setting mapper class
		    j.setReducerClass(SortReduce.class);			//setting mreducer class
		    ob.setMapperClass(SortMapper.class);			//setting mapper
		    j.setReducerClass(SortReduce.class);		    //setting reducer
		    j.setOutputKeyClass(Text.class);				//setting output key
		    j.setOutputValueClass(Text.class);
		    FileInputFormat.addInputPath( j, new Path(args[0]));
		    FileOutputFormat.setOutputPath( j, new Path(args[1]));			//giving the path for input and output
		    System.exit( j.waitForCompletion(true)?0:1);					//Submitting the job on completion 
	} 
}
class SortMapper extends Mapper<LongWritable,Text,Text,Text>{
		 protected void map(LongWritable key,Text val,Context context) throws IOException, InterruptedException{
		 	String AsciiKey;
			 String t=val.toString();					
			 AsciiKey=t.substring(0, 11);
			 context.write(new Text(AsciiKey),new Text(t.substring(11,t.length())));
		 }
		
	}
class SortReduce extends Reducer<Text,Text,Text,Text> {
	    protected void reduce(Text key, Iterable<Text> valueList,Context context) throws Exception {
	    	context.write(key,valueList.iterator().next());
	    }
	}