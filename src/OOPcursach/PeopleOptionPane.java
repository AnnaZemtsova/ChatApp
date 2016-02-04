import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by anna on 05.12.15.
 */
public class PeopleOptionPane{
    static JFrame frame=new JFrame();
    private JButton callButton;
    private JLabel peopleLabel; private Controll controll;
    private JButton addToFriendsButton;
    private JPanel panel;
    private JButton backButton;
    private JTextField IPField;
    private ServerConnection c;

    public PeopleOptionPane(final String nick,final Controll controll,final ServerConnection c) {
        this.c=c;
        this.controll = controll;
        panel.setBackground(Color.cyan);
        frame.setContentPane(panel);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);


    addToFriendsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String IP= c.getIpForNick(nick);
            controll.addToContacts(nick,IP);
        }
    });

    callButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            controll.connect(getFriendLabel(),getIPFild());

        }
    });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                controll.setVisibleFrame("UserWindow",true);
            }
        });
}

    public String getIPFild(){
        return IPField.getText();
    }

    public void setFriendLabel(String nameText){
        peopleLabel.setText(nameText);

    }
    public void setColor(Color color){
        panel.setBackground(color);
    }
    public String getFriendLabel(){
        return  peopleLabel.getText();
    }
     public  JFrame getFrame(){
    return  frame;
}
    public void setVisible(boolean bol){
        frame.setVisible(bol);
    }

}