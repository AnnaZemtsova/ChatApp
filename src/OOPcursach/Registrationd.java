import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by anna on 05.12.15.
 */
public class Registrationd {
   private JFrame frame = new JFrame();
    private JPanel panel;//=new JPanel();
    private JTextField loginField;
    private JButton registrationButton;
    private JButton entryButton;
    private JPanel panelImage;//= new JPanel();
    private Controll controll;
    private ImagePanel img;//=new ImagePanel();//"/Users/anna/Desktop/ChatCursach/src/out/Image/JAVA.jpg");
    private JLabel  imageLabel;//=new JLabel();
    private ServerConnection c;




    public Registrationd(){
        //img=new ImagePanel();
     //  /// img.setPreferredSize(new Dimension(200,200));
       // imageLabel = new JLabel(new ImageIcon());
        //panelImage.add(imageLabel);
        //imageLabel = new JLabel(new ImageIcon());
        //imageLabel.add(img);
        panelImage.setBackground(Color.blue);
    //    panel.setBackground(Color.getHSBColor(12, 37, 54));
  //      JButton button = new JButton();
    //   panelImage.imageUpdate(img,1,1,1,1,1);
        frame.setContentPane(panel);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
//        img.setImage("/Users/anna/Desktop/ChatCursach/src/out/Image/JAVA.jpg");

        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controll.registration()){
                    UserWindow userWindow = new  UserWindow(c,controll);
                    userWindow.setControll(controll);
                    controll.setUserWindow(userWindow);
                    frame.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(frame, controll.getTextFromArray(Controll.arrayList));
                }
            }
        });

        entryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controll.entry()){
                    UserWindow userWindow = new  UserWindow(c,controll);
                    userWindow.setControll(controll);
                    controll.setUserWindow(userWindow);
                    //userWindow.setServer(c);
                    frame.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(frame, controll.getTextFromArray(Controll.arrayList));
                }
            }
        });
    }

    public void showWindow(){
        new UserWindow(c,controll);
    }

    public void setControl(Controll control){
        this.controll=control;
    }

    public  String getTextFromField(){
        return loginField.getText();
    }


    public void setServerConnection(ServerConnection c){
        this.c=c;
    }

    public void setVisibleFrame(boolean bol){
        frame.setVisible(bol);
    }
}
