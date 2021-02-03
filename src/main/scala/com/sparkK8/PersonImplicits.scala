package com
package sparkK8

import java.util.UUID

import org.apache.spark.sql.{Dataset, Encoder, Encoders, SparkSession}

package object PersonImplicits {

  implicit val encoder: Encoder[Person] = Encoders.product[Person]

  abstract class PersonAbstract(id: String = UUID.randomUUID().toString,
                                name: String) {
    def firstName: String = name.split(" ")(0)
  }

  case class Person(id: String = UUID.randomUUID().toString, name: String = "")
      extends PersonAbstract(id: String, name: String)

  implicit class PersonImplicits[T <: PersonAbstract](dataset: Dataset[T]) {
    val context = SparkSession.getActiveSession.get.sqlContext
    import context.implicits._

    def getAllFirstNames[T <: PersonAbstract]: Dataset[String] = {
      dataset
        .map(_.firstName)
        .withColumnRenamed("value", "first_name")
        .as[String]
    }

    def filterCustomers[T <: PersonAbstract](
      firstName: String
    ): Dataset[Person] = {
      dataset
        .as[Person]
        .filter(data => data.firstName == firstName)
        .as[Person]
    }
  }

}
