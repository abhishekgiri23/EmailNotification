import javax.mail._
import javax.mail.internet.{InternetAddress, MimeMessage}
import java.util.Properties

/**
  * Created by knoldus on 18/9/16.
  */
class EmailNotification {


  def sendEmail(from: String, to: String,
                subject: String, body: String): Unit = {
    //val to: String = "abc@gmail.com" //change accordingly
    // Sender's email ID needs to be mentioned
    //val from: String = "xyz@gmail.com" //change accordingly
    val username: String = "username" //change accordingly
    val password: String = "password" //change accordingly
    val host: String = "smtp.gmail.com"
    val props: Properties = new Properties
    props.put("mail.smtp.auth", "true")
    props.put("mail.smtp.starttls.enable", "true")
    props.put("mail.smtp.host", host)
    props.put("mail.smtp.port", "587")
    // Get the Session object.
    val session: Session = Session.getInstance(props, new Authenticator() {
      override protected def getPasswordAuthentication: PasswordAuthentication = {
        return new PasswordAuthentication(username, password)
      }
    })
    try {
      // Create a default MimeMessage object.
      val message = new MimeMessage(session);
      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));
      // Set To: header field of the header.
      message.addRecipients(Message.RecipientType.TO,to)
      // Set Subject: header field
      message.setSubject("Email Notification Demo");
      // Now set the actual message
      message.setText("Hello, This is from scala....");
      // Send message
      Transport.send(message)
      System.out.println("Sent message successfully....");
    } catch {
      case ex : Exception => println("email notification...Error" + ex.printStackTrace())
    }
  }
}


object Email extends EmailNotification with App{
  val em = new EmailNotification
  em.sendEmail("abc@gmail.com","xyz@hotmail.com",
    "email notification demo","hi this a is demo for email notification")
}
