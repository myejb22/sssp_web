package com.scala.socket

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.Socket

import com.scala.socket.vo.{EmailMsg, HeaBeat, PhoneMsg, Result}

/**
  * <p></p>
  *
  * @author Andy 2017/12/15
  */
object MySocketClient {
  def main(args: Array[String]): Unit = {
    val socket = new Socket("127.0.0.1", 8888)
    val oos = new ObjectOutputStream(socket.getOutputStream)
    val ois = new ObjectInputStream(socket.getInputStream)

    val phone = new PhoneMsg("15645697856", "你好！请问今晚有空吗？")
    val emailMsg = new EmailMsg("zs@163.com", "亲爱的XXX，有空的话麻烦帮忙充点钱，账号里没有钱买装备呢！")
    val beat = new HeaBeat("hadoop", "active")

    oos.writeObject(phone)
    oos.flush()

    var result = ois.readObject()
    if(result.getClass == classOf[Result]){
      println(result.asInstanceOf[Result].id+"-> 返回内容："+result.asInstanceOf[Result].msg)
    }

    oos.writeObject(emailMsg)
    oos.flush()
    result = ois.readObject()
    if(result.getClass == classOf[Result]){
      println(result.asInstanceOf[Result].id+"-> 返回内容："+result.asInstanceOf[Result].msg)
    }

    oos.writeObject(beat)
    oos.flush()
    result = ois.readObject()
    if(result.getClass == classOf[Result]){
      println(result.asInstanceOf[Result].id+"-> 返回内容："+result.asInstanceOf[Result].msg)
    }

    ois.close()
    oos.close()
    socket.close()
  }
}
