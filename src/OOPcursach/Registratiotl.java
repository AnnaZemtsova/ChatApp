import sun.plugin2.os.windows.Windows;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import static java.awt.BorderLayout.*;

/**
 * Created by anna on 14.10.15.
 */

class Registration {
    ImagePanel img;
    float[] rgb = new float[3];
    JFrame frame;
    JPanel panel;
    JButton registration;
    JButton entry;
    JTextField loginField;
    private JLabel label;
    GridBagConstraints grid;
    private Controll controll;
    private ServerConnection c;

    public Registration() {
        frame = new JFrame();
        frame.setSize(400,500);
        frameDisplayCenter(400,500,frame);
        frame.setResizable(false);
        frame.setVisible(true);

        panel = new JPanel();
        img = new ImagePanel();
        loginField = new JTextField();
        label = new JLabel();

        img.setPreferredSize(new Dimension(300, 300));
        panel.setPreferredSize(new Dimension(400, 500));
        panel.setBackground(Color.cyan);
        grid = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        loginField.setPreferredSize(new Dimension(120, 25));

        panel.add(img, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(loginField, new GridBagConstraints(0, 2, 1, 1, 5.0, 5.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        this.makeButton();
        frame.add(panel);
        img.setImage("/Users/anna/Desktop/ChatCursach/src/Image/jjj.jpg");

        registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (controll.registration()) {
                    UserWindow userWindow = new UserWindow(c, controll);
                    userWindow.setControll(controll);
                    controll.setUserWindow(userWindow);
                    frame.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(frame, controll.getTextFromArray(Controll.arrayList));
                }
            }
        });
        entry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controll.entry()) {
                    UserWindow userWindow = new UserWindow(c, controll);
                    userWindow.setControll(controll);
                    controll.setUserWindow(userWindow);
                    frame.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(frame, controll.getTextFromArray(Controll.arrayList));
                }

            }
        });
    }


    void makeButton() {
        registration = new JButton();
        registration.setText("Registration");
        entry = new JButton();
        entry.setText("    Entry       ");
        panel.add(registration, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.ABOVE_BASELINE_LEADING, new Insets(1, 1, 1, 1), 0, 0));
        panel.add(entry, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.ABOVE_BASELINE_LEADING, new Insets(1, 1, 1, 1), 0, 0));
    }


    public void showWindow(){
        new UserWindow(c,controll);
    }


    private  void frameDisplayCenter (int sizeWidth, int sizeHeight, JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        frame.setBounds(locationX, locationY, sizeWidth, sizeHeight);
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

    public static void main(String[]args){
        new Registration();
    }
}


class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel(String path) {
        setImage(path);
    }

    public ImagePanel() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters
    }
    public void setImage(String path){
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException ex) {
            // handle exception...
        }
    }
}

