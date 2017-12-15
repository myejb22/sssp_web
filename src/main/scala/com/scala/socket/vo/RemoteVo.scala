package com.scala.socket.vo

/**
  * <p></p>
  *
  * @author Andy 2017/12/15
  */
trait RemoteVo extends Serializable{

}

/**
  * 手机端的信息
  * @param num
  * @param content
  */
case class PhoneMsg(val num:String,val content:String) extends RemoteVo

/**
  * 邮件端的信息
  * @param num
  * @param content
  */
case class EmailMsg(val num:String,val content:String) extends RemoteVo

/**心跳端
  *
  * @param id
  * @param status
  */
case class HeaBeat(val id:String,val status:String) extends RemoteVo

case class Result(val id:Int,val msg:String) extends RemoteVo
