import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by anna on 05.12.15.
 */
public class FriendOptionPane{//} implements Observer{
    static JFrame frame;
    private JButton deleteFromFriendsButton;
    private JButton callButton;
    private JLabel friendLabel;
    private JPanel panel;
    private JButton backButton;
    private JTextField ipField;
    private Controll controll;


    public FriendOptionPane(final String nick,final Controll controll,final ServerConnection c) {
        this.controll=controll;
        frame = new JFrame();
        panel.setBackground(UserWindow.color);
      //  panel.setBackground(Color.cyan);
        frame.setContentPane(panel);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        deleteFromFriendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controll.delete(nick);
                System.out.println("Друг удален");
            }
        });

        callButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controll.connect(getFriendLabel(),getIPFild());
               if(!Controll.isCalling)
                    JOptionPane.showMessageDialog(frame,controll.getTextFromArray(Controll.arrayList));

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
    public void setColor(Color color){
        panel.setBackground(color);
    }

    public String getIPFild(){
        return ipField.getText();
    }
    public void setFriendLabel(String nameText){
        friendLabel.setText(nameText);
    }
    public String getFriendLabel(){
        return  friendLabel.getText();
    }

    public void setControll(Controll controll) {
        this.controll = controll;
    }
    public  JFrame getFrame(){
        return  frame;
    }
    public void setVisible(boolean bol){
        frame.setVisible(bol);
    }

}
