import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anna on 05.12.15.
 */
public class UserWindow{
    private JPanel panel;
 static JFrame frame;
    private JButton allUserButton;
    private JButton myFriendsButton;
    private JList listOfUser;
    private JTextField searchField;
    private JButton searchButton;
    private JButton backButton;
    private JButton musicButton;
    private JComboBox comboBox1;
    private Controll controll;
    private Contacts contacts;
    private Vector<String> serverContacts = new Vector<String>();
    private String[] nickAndIP;
    static String serverOrFriends;
    private ServerConnection c;
    private PeopleOptionPane peopleOptionPane;
    private FriendOptionPane friendOptionPane;
    static Color color;


    public UserWindow(final ServerConnection c,final Controll controll){
        this.controll=controll;
        this.c=c;
        frame = new JFrame();
        DefaultListModel defaultListModel = new DefaultListModel();
        listOfUser.setModel(defaultListModel);
        setColor(Color.cyan);
        panel.setBackground(UserWindow.color);
       // panel.setBackground(Color.cyan);
        frame.setContentPane(panel);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        myFriendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contacts = controll.getLocalContacts();
                if (contacts.getSize()!=0){
                    Vector<String> vector = new Vector<String>();
                    for (int i=0;i<contacts.getSize();i++) {
                        String online=" ";
                        if(c.isNickOnline(contacts.getNick(i))){
                            online="*";
                        }
                        vector.add(online+"   "+contacts.getNick(i));
                    }
                    listOfUser.setListData(vector);
                    listOfUser.setVisibleRowCount(contacts.getSize());
                    listOfUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    serverOrFriends = "friend";
                }else{
                    JOptionPane.showMessageDialog(frame, "У вас еще нет ни одного друга");
                }
            }
        });

        allUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] arrayOfContacts = controll.getServerContacts();
                for (int i = 0; i < arrayOfContacts.length-10; i++) {
                    String online=" ";
                    if(c.isNickOnline(arrayOfContacts[i])){
                        online="*";
                    }
                     serverContacts.add(online+"   "+arrayOfContacts[i]);
                }
                listOfUser.setListData(serverContacts);
                listOfUser.setVisibleRowCount(serverContacts.size());
                listOfUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                serverOrFriends = "server";
            }
        });

        listOfUser.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object ob = listOfUser.getSelectedValue();
                String strName = ob.toString();///cтранный эксепшен!!!!!!!!!!!!!!!!!!!!!!
                System.out.println(strName);
                ServerConnection c=getServer();
                String IP =c.getIpForNick(strName);
                System.out.println(getServerOrFriends());
                if(getServerOrFriends().equals("server")){
                    peopleOptionPane =  new  PeopleOptionPane(strName,controll,c);
                    frame.setVisible(false);
                    peopleOptionPane.setFriendLabel(strName);
                }else{
                    friendOptionPane =new FriendOptionPane(strName,controll,c);
                    frame.setVisible(false);
                    friendOptionPane.setFriendLabel(strName);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String color=  comboBox1.getSelectedItem().toString();
                Color col= Color.cyan;
               if(color.equals("Red"))col = Color.RED;
                if(color.equals("Yellow")) col = Color.YELLOW;
                if(color.equals("Green")) col = Color.GREEN;
                if(color.equals("Blue")) col = Color.blue;
                if(color.equals("White")) col = Color.WHITE;
                if(color.equals("Light Gray")) col = Color.LIGHT_GRAY;
                if (color.equals("Dark Gray")) col = Color.DARK_GRAY;
                if(color.equals("Pink"))col = Color.PINK;
                if(color.equals("Cyan")) col = Color.CYAN;
                setColor(col);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> tmpVector = controll.search();
                String[] name = new String[1];
                name[0]= tmpVector.get(0);
                System.out.println(name[0]);
                if(!name[0].equals("")){
                    listOfUser.setListData(name);
                    listOfUser.setVisibleRowCount(1);
                    listOfUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                }else{
                  //  JOptionPane.showMessageDialog(frame, "Пользователя с таким именем не существует");
                }
            }
        });
        musicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Music();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                frame.setVisible(false);
            }
        });
    }


    public void setVisibleFrame(boolean bol){
        frame.setVisible(bol);
    }
    public String getTextFromField(){
        return searchField.getText();
    }

    public void setControll(Controll controll){
        this.controll = controll;
    }

    public String getServerOrFriends(){
        return serverOrFriends;
    }
    public ServerConnection getServer(){
    return c;
}

    public void setVisibleUserFrame(boolean bol){
        if(getServerOrFriends().equals("server")){
            peopleOptionPane.setVisible(false);
        }else{
            friendOptionPane.setVisible(false);
        }
    }

    public void setColor(Color color){
        this.color = color;
        panel.setBackground(color);
    }

public static void main(String[]args){
    ServerConnection c = new ServerConnection();
    Controll c1= new Controll();
new UserWindow(c,c1);
}
}



