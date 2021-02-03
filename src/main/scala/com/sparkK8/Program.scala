package com
package sparkK8

import com.sparkK8.PersonImplicits.Person
import org.apache.spark.sql.SparkSession

object Program extends App {

  val spark = SparkSession.builder
    .master("local[*]")
    .appName("StructuredFileSink")
    .getOrCreate()

  import spark.implicits._

  val person1 = Person(name = "John Smith")
  val person2 = Person(name = "Maria Garcia")

  val people = Seq(person1, person2).toDS()
  people.printSchema()
  people.show()

  people.filterCustomers("John").show()

}
