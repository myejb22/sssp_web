package com.scala

import com.myejb22.sssp.entity.Employee
import com.scala.`implicit`.RichPerson._
import org.joda.time.DateTime

class SpescialPerson(val name: String, val email: String, val age: Int) {

}

class Company {
  def inCompanyWork(p: SpescialPerson): Unit = {
    println("【" + p.name + "】在华为的高大上办公室上班呢，如有事情，请发邮件给他，他的邮箱地址【" + p.email + "】 他今年有【" + p.age + "】")
  }
}

class Person() {
  def sayHello(p: Employee): Unit = {
    println(p.getLastName + "\t" + p.getEmail)
  }
}

object Test {
  def main(args: Array[String]): Unit = {
    val emp = new Employee;
    emp.setLastName("张三")
    emp.setEmail("myejb22@163.com")
    emp.setBirthDay(DateTime.parse("2001-01-25").toDate)
    /*new Person().sayHello(emp)*/
    val com = new Company
    com.inCompanyWork(emp)
  }
}