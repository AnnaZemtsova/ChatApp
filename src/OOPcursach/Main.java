import javax.swing.*;

/**
 * Created by anna on 05.12.15.
 */
public class Main extends JFrame{
    public static void main(String[]args){
      ServerConnection  c=new ServerConnection();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        c = new ServerConnection();
        c.setServerAddress("jdbc:mysql://files.litvinov.in.ua/chatapp_server?characterEncoding=utf-8&useUnicode=true");
        c.connect();
            Registration registration = new Registration();
            registration.setVisibleFrame(true);
            registration.setServerConnection(c);
            Controll controll = new Controll();
            registration.setControl(controll);
            controll.setRegistration(registration);
        }
   // }
}
