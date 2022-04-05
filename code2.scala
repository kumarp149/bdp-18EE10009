// ham and spam are the rdd already genereated in the question 1


//super word might not exist in ham data so you may used some other word instead of "super"
val qword=Array("call","home","super")

println("Top 5 most frequent words occuring with the given word")
//using spam co-ocurrence rdd
println("For Spam")
for (q <- qword)
{
val top5=spam.filter(el=>(el._1(0)==q||el._1(1)==q)).sortBy(_._2,false).take(5)

printf("With word %s: ", q)
for (el<-top5)
{
val word= if (el._1(0) ==q ) el._1(1) else el._1(0)
val counts=el._2
println(word,counts)
}
println("\n")
}

println("For ham")
//using ham co-ocurrence rdd
for (q <- qword)
{
printf("With word %s: ", q)
val top5=ham.filter(el=>(el._1(0)==q||el._1(1)==q)).sortBy(_._2,false).take(5)
for (el<-top5)
{
val word= if (el._1(0) ==q ) el._1(1) else el._1(0)
val counts=el._2
println(word,counts)
}
println("\n")
}

// you may also save the output to txt file I'm just printing the results