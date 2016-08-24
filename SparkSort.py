from __future__ import print_function
import sys
from pyspark import SparkContext
from pyspark import SparkConf

if __name__ == "__main__":
    conf=(SparkConf().setAppName("SortPython").set("spark.executor.memory","2.5g").set("spark.memory", "2.5g").set("spark.default.parallelism","8"))
    sc=SparkContext(conf=conf)
    lines=sc.textFile(sys.argv[1], 1,use_unicode=False).map(lambda x: (x[0:10],x[10:]))
    sortedCount=lines.sortByKey(True,1).map(lambda x: (x[0] + x[1].strip('\n'))+'\r') 
	sortedCount.saveAsTextFile("100gSparkOutput")
	sc.stop()
