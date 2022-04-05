val rdd = sc.textFile("./spam.csv")

val stopwords = sc.textFile("./stopwords.txt").collect().toSet


val cleaned_data= rdd.map(line=>{
      val tokens = line.split(",")
      val label=tokens(0)
      val text = tokens.drop(1).mkString(" ").toLowerCase().replaceAll("[^A-Za-z0-9 ]", "") //.replaceAll(" +", " ")
      val cleaned_text= text.split(" ").filter(!stopwords.contains(_)).filter(_.nonEmpty)

      (label,cleaned_text.toList)
      })


val ham_data=cleaned_data.filter(_._1=="ham").map(_._2)
val spam_data=cleaned_data.filter(_._1=="spam").map(_._2)

val ham=ham_data.map(_.sorted).flatMap(_.combinations(2)).map((_,1)).reduceByKey(_+_)
val spam=spam_data.map(_.sorted).flatMap(_.combinations(2)).map((_,1)).reduceByKey(_+_)

ham.map(el=>(el._1(0),el._1(1),el._2)).coalesce(1).saveAsTextFile("./ham.txt")
spam.map(el=>(el._1(0),el._1(1),el._2)).coalesce(1).saveAsTextFile("./spam.txt")

// you  can also use collect function on the rdd and save it to text file
// as saving rdd directly to txt file generates extra configuration file

