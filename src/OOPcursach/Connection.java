import javax.swing.*;
import java.awt.event.*;
import java.text.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;


public class Connection{
    private Socket socket;
    private BufferedReader buffReadIn;
    private PrintWriter PWOut;
    private BufferedReader buffReaderIn;
    static String tmpStrTimep;
    static int flag = 1;

    public Connection() {
    }

    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;
        PWOut = new PrintWriter(socket.getOutputStream(), true);
        buffReaderIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    public void sendNickHello(String nick) {
        sendMessage(NickCommand.helloMessage + nick);
    }

    public void sendNickBusy(String nick) {
        sendMessage(NickCommand.helloMessage + nick + NickCommand.busy);
        PWOut.close();
    }

    public void accept() {
        sendMessage(Command.accepted);
    }

    public void reject() {
        sendMessage(Command.rejected);
        PWOut.close();
    }

    public void disconnect() {
        sendMessage(Command.disconnect);
        PWOut.close();
    }

    public void sendMessage(String message) {
        PWOut.println(message);
    }

    public Socket getSocket(){
        return socket;
    }

    public Command receive() throws IOException {
        String com = buffReaderIn.readLine();
        System.out.println("ggha% "+com);
        Command command = new Command();
        command.setCommand(com);
        if (!command.isHaveFalseCommand()) {
            return command;
        }
        MessageCommand messageCOmmand = new MessageCommand();
        messageCOmmand.setCommand(com);
        if (!messageCOmmand.isHaveFalseCommand()) {
            String lkjjkl = buffReaderIn.readLine();
            messageCOmmand.setMessage(lkjjkl);
            System.out.println("mes "+lkjjkl);
            command = messageCOmmand;
            return command;
        }
        NickCommand nickCommand = new NickCommand();
        nickCommand.setCommand(com);
        if (!nickCommand.isHaveFalseCommand()) {
            command = nickCommand;
            return command;
        }

         SmileCommand sMileCommand = new SmileCommand();
         sMileCommand.setCommand(com);
          if(!sMileCommand.isHaveFalseCommand()){
              sMileCommand.setSmile(buffReaderIn.readLine());
              command = sMileCommand;
              return  command;
          }
        return null;
    }


    public String readMessage(){
        String message= null;
        try {
            message = buffReaderIn.readLine();
        } catch (IOException e) {
            message = null;
        }
        return message;
    }

}