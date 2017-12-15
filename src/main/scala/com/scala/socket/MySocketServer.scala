package com.scala.socket

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.ServerSocket

import com.scala.socket.vo.{EmailMsg, HeaBeat, PhoneMsg, Result}

/**
  * <p></p>
  *
  * @author Andy 2017/12/15
  */
object MySocketServer {
  def main(args: Array[String]): Unit = {
    val serverSocket = new ServerSocket(8888)
    println("服务端已启动...")

    val socket = serverSocket.accept()
    val oos = new ObjectOutputStream(socket.getOutputStream)
    val ois = new ObjectInputStream(socket.getInputStream)
    while (true) {
      val obj = ois.readObject()
      val result = obj match {
        case PhoneMsg(num, content) => phoneMsg(num, content)
        case EmailMsg(num, content) => emailMsg(num, content)
        case HeaBeat(id, status) => heaBeat(id, status)
      }

      oos.writeObject(result)
      oos.flush()
    }
  }

  /**
    * 手机端发送信息
    *
    * @param num
    * @param content
    */
  def phoneMsg(num: String, content: String): Result = {
    println(s"手机号码【$num】调用了手机端发送消息，内容：$content")
    new Result(1,"手机消息发送成功")
  }

  /**
    * 邮件端发送信息
    *
    * @param num
    * @param content
    */
  def emailMsg(num: String, content: String): Result = {
    println(s"邮箱号【$num】调用了邮件端发送消息，内容：$content")
    new Result(2,"邮件消息发送成功")
  }

  /**
    * 心跳端，查看当前心跳是否还活着
    *
    * @param id
    * @param status
    */
  def heaBeat(id: String, status: String): Result = {
    println(s"心跳id【$id】当前服务器的状态：$status")
    val sid = 3
    var msg = "当前服务器的状态已经僵死"
    if(status == "active") {
      msg = "当前服务器的状态Active"
    }
    new Result(sid,msg)
  }
}
