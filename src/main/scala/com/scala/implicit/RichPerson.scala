package com.scala.`implicit`

import java.util.{Calendar, Date}

import com.myejb22.sssp.entity.Employee
import com.scala.SpescialPerson
import org.apache.commons.lang3.time.DateUtils

/**
  * <p></p>
  *
  * @author Andy 2017/12/15
  */
object RichPerson {

  implicit def employee2SpescialPerson(obj:Object):SpescialPerson= {
    if (obj.getClass == classOf[Employee]) {
      val employee = obj.asInstanceOf[Employee]
      val birDate = DateUtils.toCalendar(employee.getBirthDay)
      val birMonth = birDate.get(Calendar.MONTH)
      val birDay = birDate.get(Calendar.DAY_OF_MONTH)

      val systemDate = DateUtils.toCalendar(new Date)
      val sysMonth = systemDate.get(Calendar.MONTH)
      val sysDay = systemDate.get(Calendar.DAY_OF_MONTH)

      var age = systemDate.get(Calendar.YEAR) - birDate.get(Calendar.YEAR)
      if( birMonth >= sysMonth){
        if(birMonth == sysMonth){
          if(birDay > sysDay) age -=1
        }else{
          age -=1
        }
      }
      new SpescialPerson(employee.getLastName, employee.getEmail, age)
    }else{
      None
    }
  }
}
