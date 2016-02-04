import javax.swing.*;
import javax.swing.text.StringContent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * Created by anna on 02.12.15.
 */
class Controll implements Observer {
    private String friendNickNameTmp;
    private Connection connection = new Connection();
    private CommandListenerThread commandListenerThread;
    private BufferedReader buffReadIn;
    private CallListenerThread callListenerThread;
    private PrintWriter PWOut;
    private Caller caller = new Caller();
    Socket socket;
    static Boolean isHaveSocket = false, isCalling = false;
    Command command = new Command();
    private String nickName, friendNickName;
    private boolean isHaveNickName, isNextCallStage;
    private int tmpSize = 25;
    private String IP;
    private Contacts contacts;
    private WorkWithContactsFile wwcf;
    ServerConnection c;
    static ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> arrayListFriends = new ArrayList<String>();
    private Registration registration;
    static boolean isflagEntry;
    private UserWindow userWindow;
    private boolean isWaitNextStageCalling = false;
    static ArrayList<String>arrayListMessage= new ArrayList<String>();
    private PeopleOptionPane peopleOptionPane ;
    private FriendOptionPane friendOptionPane;
    private MessageWindows messageWindows;

    public Controll(){
        callListenerThread = new CallListenerThread(28412);
        callListenerThread.addObserver(this);
        callListenerThread.CallListenerStart("loloshka");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        c = new ServerConnection();
        c.setServerAddress("jdbc:mysql://files.litvinov.in.ua/chatapp_server?characterEncoding=utf-8&useUnicode=true");
        c.connect();
    }

    public Contacts getLocalContacts() {
        try {
            wwcf = new WorkWithContactsFile();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            wwcf.readContacts();
        } catch (IOException e) {
            e.printStackTrace();
        }
        contacts = wwcf.getContacts();
        return wwcf.getContacts();
    }
    public void setRegistration(Registration registration){
        this.registration = registration;
    }
    public void setUserWindow(UserWindow userWindow){
        this.userWindow = userWindow;
    }
    public  void setMessageWindows(MessageWindows messageWindows){
        this.messageWindows = messageWindows;
    }

    public String[] getServerContacts(){
        return c.getAllNicks();
    }

    public void addToContacts(String nick, String IP){
        try {
            wwcf = new WorkWithContactsFile();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            wwcf.readContacts();
        } catch (IOException e) {
            e.printStackTrace();
        }
        contacts = wwcf.getContacts();
        boolean bool = false;
        for (int i = 0; i<contacts.getSize(); i++)
            if (nick.equals(contacts.getNick(i))) bool = true;
        if (!bool){
            try {
                wwcf.addContactToFile(nick,IP);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void delete(String deleteFriend) {
        if (!deleteFriend.equals("")) {
            try {
                wwcf.deleteContacts(deleteFriend);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
public void sendDisconnect(){
    connection.disconnect();
}
    public void connect(String friendNickTmp,String IP){
        ArrayList arrayList1 =new ArrayList();
        arrayList1.add(0,"");
        if (!isHaveSocket) {
            arrayList.clear();
           Vector<String> nickVector = new Vector<String>();
            nickVector.add(0, friendNickTmp);
            nickVector.add(1,IP);
            if (!nickVector.get(0).equals("")) {
                if (!nickVector.get(1).equals("")) {
                    ///if (c.isNickOnline(friendNickTmp)) {
                    if (UserWindow.serverOrFriends.equals("server")) {
                             } else {
                           }
                        caller.setRemoteAdress(nickVector.get(1));
                        try {
                            caller.Call();
                            Socket s = caller.getSocket();
                            connection.setSocket(s);
                            IP = nickVector.get(1);
                            commandListenerThread = new CommandListenerThread("lalathread", connection);
                            commandListenerThread.addObserver(this);
                            commandListenerThread.CommandListenerThreadStart();
                            arrayList.add(String.valueOf(s.getLocalPort()));
                            arrayList.add(String.valueOf(s.getInetAddress()));
                            isCalling = true;
                            isHaveSocket = true;
                        } catch (IOException e1) {
                            arrayList1.add("Вызов по данному адресу не удался");
                        }

                } else {
                    arrayList1.add("Ошибка IP");
                }
            } else {
              arrayList1.add("Такого друга нет на сервере");
           }

            } else {
                arrayList1.add("У вас есть активное подключение!!!");
            }


        if(!arrayList1.get(0).equals("")) {
            if (UserWindow.serverOrFriends.equals("server")) {
                JOptionPane.showMessageDialog(PeopleOptionPane.frame, getTextFromArray(arrayList1));
            } else {
                JOptionPane.showMessageDialog(FriendOptionPane.frame, getTextFromArray(arrayList1));
            }
        }
    }


    public ArrayList<String> getArrayListFriends(){
        arrayListFriends = new ArrayList<String>();
        for(int i=0;i<contacts.getSize();i++){
            arrayListFriends.add(contacts.getNick(i)+" "+contacts.getIP(i)+"\n");
        }
        return arrayListFriends;
    }

    public boolean registration(){
        isflagEntry=false;
        arrayList.clear();
        String nick = registration.getTextFromField();
        if (!isHaveNickName) {
            if (!nick.equals("")){
                if (!ChackGaps(nick)){
                    if(!compareToServerListContacts(nick)){
                        isHaveNickName = true;
                        nickName = nick;
                        c.setLocalNick(nickName);
                        c.goOnline();
                        isflagEntry=true;
                    }else{
                        arrayList.add("Такой ник уже занят! Пожалуйста, выберите другой!");
                    }
                }else{
                    arrayList.add("Вы ввели неправильный ник! ");
                    arrayList.add("Введите ник не используя пробелы!");
                }
            }else{
                arrayList.add("Введите ник!");
            }
        }else{
            arrayList.add("Error"); //????
        }
        return isflagEntry;
    }

    public boolean entry(){
        isflagEntry=false;
        arrayList.clear();
        String nick = registration.getTextFromField();
        if (!isHaveNickName) {
            if (!nick.equals("")){
                if (!ChackGaps(nick)){
                    if(compareToServerListContacts(nick)){
                        isHaveNickName = true;
                        nickName = nick;
                        c.setLocalNick(nickName);
                        c.goOnline();
                        arrayList.add("Вход успешно выполнен!");
                        isflagEntry=true;
                    }else{
                        arrayList.add("Вы не зарегистрированы!!");
                    }
                }else{
                    arrayList.add("Вы ввели неправильный ник! ");
                    arrayList.add("Введите ник не используя пробелы!");
                }
            }else{
                arrayList.add("Введите ник!");
            }
        }else{
            arrayList.add("Error"); //????
        }
        return isflagEntry;
    }

    public void disconnectClose() throws IOException {
        socket=commandListenerThread.getSocket();
        //commandListenerThread.deleteObservers();
        //  commandListenerThread.deleteObserver(guiForm);//???подумать
      //  socket.close();
        commandListenerThread.stop();
        isHaveSocket = false;
    }

    public void disconnect(){
        if (isHaveSocket) {
            System.out.println("i have socket");
            isHaveSocket = false;
          //  arrayList.add("Отключено");
            try {
                disconnectClose();
            } catch (IOException e) {
                e.printStackTrace();
            }
          //  c.goOffline();
        }
    }



    public void setVisibleFrame(String str,boolean bol){
        if(str.equals("Registration")) {
            registration.setVisibleFrame(true);
        }
        if(str.equals("UserWindow")) {
            userWindow.setVisibleFrame(true);
        }
    }

    public void sendSmile(String smileName){
        if (isHaveSocket) {
              messageWindows.createSmile(smileName);
               connection.sendMessage(SmileCommand.smile);
              connection.sendMessage(smileName);
        }
    }

    public void send(){
        arrayList.clear();
        if (isHaveSocket) {
            String str =messageWindows.getTextFromField();
            System.out.println("my message% "+str);
            if (!str.equals("")) {
                connection.sendMessage(MessageCommand.message);
                connection.sendMessage(str);
                arrayListMessage.add(str);
            } else {
                JOptionPane.showMessageDialog(MessageWindows.frame,"Вы не ввели сообщение!");
            }
        } else {
            JOptionPane.showMessageDialog(MessageWindows.frame,"нет доступного подклчения");
        }
    }

    public Vector<String> search(){
        arrayList.clear();
        Vector<String> arrayListTmp = new Vector<String>();
        arrayListTmp.add(0,"");
        arrayListTmp.add(1,"");
        arrayList.add(0,null);
        arrayList.add(1,null);
        String str = userWindow.getTextFromField();
        if (!str.equals("")){
            if(compareToServerListContacts(str)){
                arrayListTmp.add(0,str);
                arrayListTmp.add(1,c.getIpForNick(str));
            }else{
                JOptionPane.showMessageDialog(UserWindow.frame, "Такого пользователя нет на сервере");
            }
        }else{JOptionPane.showMessageDialog(UserWindow.frame, "Введите имя друга");

        }
 /*if(arrayList.get(0)!=null||arrayList.get(1)!=null)
            JOptionPane.showMessageDialog(UserWindow.frame,getTextFromArray(arrayList));*/

        return arrayListTmp;
    }

    public boolean compareToServerListContacts(String nick){
        String [] str = c.getAllNicks();
        for (int i=0;i<str.length;i++){
            if(nick.equals(str[i])){
                return true;
            }
        }
        return false;
    }



    public boolean ChackGaps(String test) {
        for (int j = 0; j < test.length(); j++) {
            if ((test.charAt(j)) == ' ') {
                return true;
            }
        }
        return false;
    }

    public  String getTextFromArray(ArrayList<String> arrayList) {
        String str = "";
        for (int i = 0; i < arrayList.size(); i++) {
            str += arrayList.get(i) + "\n";
        }
        return str;
    }

    public void update(Observable o, Object args) {
        boolean isNotMaked = true;
        String str = (String) args;
        if (str.equals(CallListenerThread.itIsCallLisnenerThread)) {
            Connection connection1 = new Connection();
            isNotMaked = false;
            if (isHaveSocket) {
                try {
                    connection1.setSocket(callListenerThread.getSocket());
                    connection1.sendNickBusy(nickName);
                } catch (IOException e) {
                }
            } else
                try {
                    try {
                        disconnect();
                    } catch (Exception e) {
                    }
                    connection1.setSocket(callListenerThread.getSocket());
                    connection1.sendNickHello(nickName);
                    commandListenerThread = new CommandListenerThread("lala1", connection1);
                    commandListenerThread.addObserver(this);
                    commandListenerThread.CommandListenerThreadStart();
                    isWaitNextStageCalling = true;
                } catch (IOException e) {
                }
        }

        if (isNotMaked && isWaitNextStageCalling) {
            if (str.equals(CommandListenerThread.itIsCommandLisnenerThread)) {
                isWaitNextStageCalling = false;
                try {
                    Command command1 = commandListenerThread.getLastCommand();
                    if (command1.getCommand() == null) System.out.println("null");
                    if (command1 != null && command1.getCommand().equals(NickCommand.helloMessage)) {
                        isNotMaked = false;
                        NickCommand nickCommand = (NickCommand) command1;
                        InputConnection inputConnection = new InputConnection(nickCommand.getNick());
                        InputConnection.isInputConnection = true;
                        while (InputConnection.isInputConnection) {
                            System.out.println(InputConnection.isInputConnection+ "  " + InputConnection.flag);
                        }
                        if (InputConnection.flag) {
                            connection = new Connection();
                            connection.setSocket(commandListenerThread.getSocket());
                            connection.accept();
                            userWindow.setVisibleFrame(false);
                            messageWindows = new MessageWindows(nickCommand.getNick());
                            //nickCommand.getNick());
                            messageWindows.setControll(this);
                            this.setMessageWindows(messageWindows);
                            isHaveSocket = true;
                        } else {
                            connection.setSocket(commandListenerThread.getSocket());
                            connection.reject();
                            try {
                                disconnectClose();
                            } catch (IOException ee) {
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (isNotMaked && isCalling) {
            if (str.equals(CommandListenerThread.itIsCommandLisnenerThread)) {
                isCalling = false;
                Command command1 = commandListenerThread.getLastCommand();

                if (command1 != null && command1.getCommand().equals(NickCommand.helloMessage)) {
                    isNotMaked = false;
                    NickCommand nickCommand = (NickCommand) command1;
                    if (nickCommand.isBusy()) {
                         friendNickNameTmp = nickCommand.getNick();
                        if(UserWindow.serverOrFriends.equals("server")) {
                            JOptionPane.showMessageDialog(PeopleOptionPane.frame,friendNickName+" занят");
                        } else {
                            JOptionPane.showMessageDialog(FriendOptionPane.frame,friendNickName+" занят");
                        }
                        isHaveSocket = false;
                        try {
                            disconnectClose();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("nnnn 1111");
                        isHaveSocket = true;
                        isNextCallStage = true;
                        connection.sendNickHello(nickName);
                    }
            }} else {
                isNotMaked = false;
                isHaveSocket = false;
                if(UserWindow.serverOrFriends.equals("server")) {
                    JOptionPane.showMessageDialog(PeopleOptionPane.frame,"Не удалось позвонить");
                } else {
                    JOptionPane.showMessageDialog(FriendOptionPane.frame,"Не удалось позвонить");
                }
                try {
                    disconnectClose();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            isCalling = false;
        }

        // it is call user 2 stage
        if (isNotMaked && isNextCallStage) {
            if (str.equals(CommandListenerThread.itIsCommandLisnenerThread)) {
                Command command1 = commandListenerThread.getLastCommand();
                if (command1.getCommand().equals(Command.accepted)) {
                    isNotMaked = false;
                    System.out.println("gribi");
                    isCalling=true;
                    userWindow.setVisibleUserFrame(false);
                    messageWindows = new MessageWindows(friendNickNameTmp);// NULL NAME
                    messageWindows.setControll(this);
                    this.setMessageWindows(messageWindows);
                }
                if (command1.getCommand().equals(Command.rejected)) {
                    isNotMaked = false;
                    arrayList.clear();
                    if(UserWindow.serverOrFriends.equals("server")) {
                        JOptionPane.showMessageDialog(PeopleOptionPane.frame,"Aбонент отклонил вызов");
                    } else {
                        JOptionPane.showMessageDialog(FriendOptionPane.frame,"Aбонент отклонил вызов");
                    }
                    try {
                        disconnectClose();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                isNextCallStage = false;
            }
        }

        System.out.println(isNotMaked);
        if (isNotMaked && str.equals(CommandListenerThread.itIsCommandLisnenerThread) && isHaveSocket) {
            System.out.println(isNotMaked);
            Command command1 = commandListenerThread.getLastCommand();
            if (command1 != null) {
                if (command.getCommand() == null)
                    System.out.println("fuck");
                if (command1.getCommand().equals(Command.disconnect)) {
                    JOptionPane.showMessageDialog(MessageWindows.frame,"Друг отключился");
                    //arrayList.add("Друг отключился");
                       MessageWindows.frame.setVisible(false);

                    UserWindow.frame.setVisible(true);
                    disconnect();

                }
                if (command1.getCommand().equals(MessageCommand.message)) {
                    MessageCommand messageCommand = (MessageCommand) command1;
                    messageWindows.createMessage(messageCommand.getMessage());
                    System.out.println(messageCommand.getMessage());
                }
                if (command1.getCommand().equals(SmileCommand.smile)) {
                    SmileCommand smileCommand = (SmileCommand) command1;
                    messageWindows.createSmile(smileCommand.getSmile());
                }
            }

        }
    }
}



