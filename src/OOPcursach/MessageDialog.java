import apple.laf.JRSUIUtils;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.net.URL;
/**
 * Created by anna on 14.10.15.
 */
public class MessageDialog {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                MessageWindows window = new MessageWindows("f");
                window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.frame.setVisible(true);
                window.frame.setSize(1000, 800);
                window.frame.setResizable(true);
            }
        });
    }

    }


class MessageWindows {
      static  JFrame frame;
    private JPanel panel;
    private JButton send;
    private JButton disconnect;
    private JTextField messageField;
    private JLabel  nameLabel;
    private JButton smileButton;
    JScrollPane scrollPane;
    private JPanel dialogArea;
    private JPanel panelSmile;
    private GridBagConstraints c;
    private Controll controll;
    private Vector<String> vectorMessage = new Vector<String>();
    private String message = "";
    private JLabel smile1;
    private JLabel smile2;
    private JLabel smile3;
    private JLabel smile4;
    private JLabel smile5;
    private JLabel smile6;
    private JLabel smile7;
    private JLabel smile8;
    private JLabel smile9;
    private Box lableBox;
    private JScrollBar scrollBar;
     static JLabel[] label;
    private int sizeLabel;
    private VectorLabel vectorLabel;
     static int i=-1;
    private JLabel smile10;
    private Toolkit kit = Toolkit.getDefaultToolkit();
    private ArrayList arrayList= new ArrayList();


    public  MessageWindows(String friendNick) {

        frame = new JFrame();
        panel = new JPanel();
        panelSmile = new JPanel();
        dialogArea = new JPanel();
        nameLabel = new JLabel();
        messageField = new JTextField();
        disconnect = new JButton();
        send = new JButton();
        vectorLabel = new VectorLabel();
        panel.setBackground(UserWindow.color);
       // panel.setBackground(Color.cyan);
        panel.setLayout(new GridBagLayout());
        panelSmile.setLayout(new GridBagLayout());
        panelSmile.setPreferredSize(new Dimension(50, 600));
        panelSmile.setBackground(Color.WHITE);
        dialogArea.setBackground(Color.WHITE);
        dialogArea.setLayout(new BoxLayout(dialogArea, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(dialogArea);
        nameLabel.setPreferredSize(new Dimension(100, 30));
        nameLabel.setText(friendNick);
        send.setText("Send");
        disconnect.setText("Disconnect");
        messageField.setPreferredSize(new Dimension(800, 30));
        createSmileButton();
        label = vectorLabel.VectorLabel(label);
        setLabel();
        panel.add(nameLabel, new GridBagConstraints(1, 0, 1, 1, 5.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1, 1, 1, 1), 1, 1));
        panel.add(disconnect, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.EAST, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(scrollPane, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.ABOVE_BASELINE_LEADING, new Insets(1, 1, 1, 1), 1, 1));
        panel.add(panelSmile,new GridBagConstraints(2,1,1,1,1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.ABOVE_BASELINE_LEADING, new Insets(10, 10, 10, 10), 1, 1));
        panel.add(messageField,new GridBagConstraints(0, 4, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.ABOVE_BASELINE_LEADING, new Insets(1, 1, 1 ,1), 1, 1));
        panel.add(send, new GridBagConstraints(2, 4, 0, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.EAST, new Insets(0, 0, 0,0), 0, 0));

        scrollPane.setAutoscrolls(true);
        scrollPane.setWheelScrollingEnabled(true);


        frame.pack();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
       frame.setSize(1000, 800);



        smile1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
               controll.sendSmile("smile1");
                    }
        });

        smile2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controll.sendSmile("smile2");
            }
        });

        smile3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controll.sendSmile("smile3");

            }
        });

        smile4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
               controll.sendSmile("smile4");
            }
        });

        smile5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
               // i++;
                  controll.sendSmile("smile5");
            }
        });

        smile6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
               // i++;
                 controll.sendSmile("smile6");
            }

        });

        smile7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
               controll.sendSmile("smile7");

            }
        });

        smile8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controll.sendSmile("smile8");
            }

        });

        smile9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                 controll.sendSmile("smile9");
            }
        });

        smile10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controll.sendSmile("smile10");
            }
        });

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (controll==null){
                    System.out.println("CONTROLLNULL");
               }
               controll.send();
               createMessage(messageField.getText());
                messageField.setText("");
            }
        });

        disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controll.sendDisconnect();
                    controll.disconnect();
                frame.setVisible(false);
                UserWindow.frame.setVisible(true);

            }
        });

        messageField.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                arrayList.add(messageField.getText());
            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });

    }


    public void setNameFriendOnLabel(String name){
        nameLabel.setText(name);
    }



    private void createSmileButton(){
        smile1 = new JLabel();
        smile2 = new JLabel();
        smile3 = new JLabel();
        smile4 = new JLabel();
        smile5 = new JLabel();
        smile6 = new JLabel();
        smile7 = new JLabel();
        smile8 = new JLabel();
        smile9 = new JLabel();
        smile10 = new JLabel();


        smile1.setIcon(new ImageIcon("/Users/anna/Desktop/ChatCursach/src/Image/1.jpg"));
        smile2.setIcon(new ImageIcon("/Users/anna/Desktop/ChatCursach/src/Image/2.jpg"));
        smile3.setIcon(new ImageIcon("/Users/anna/Desktop/ChatCursach/src/Image/3.jpg"));
        smile4.setIcon(new ImageIcon("/Users/anna/Desktop/ChatCursach/src/Image/4.jpg"));
        smile5.setIcon(new ImageIcon("/Users/anna/Desktop/ChatCursach/src/Image/5.jpg"));
        smile6.setIcon(new ImageIcon("/Users/anna/Desktop/ChatCursach/src/Image/6.jpg"));
        smile7.setIcon(new ImageIcon("/Users/anna/Desktop/ChatCursach/src/Image/7.jpg"));
        smile8.setIcon(new ImageIcon("/Users/anna/Desktop/ChatCursach/src/Image/8.jpg"));
        smile9.setIcon(new ImageIcon("/Users/anna/Desktop/ChatCursach/src/Image/9.jpg"));
        smile10.setIcon(new ImageIcon("/Users/anna/Desktop/ChatCursach/src/Image/10.jpg"));


        panelSmile.add(smile1,new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 1, 1));
        panelSmile.add(smile2,new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 1, 1));
        panelSmile.add(smile3,new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 1, 1));
        panelSmile.add(smile4,new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 1, 1));
        panelSmile.add(smile5,new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 1, 1));
        panelSmile.add(smile6,new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 1, 1));
        panelSmile.add(smile7,new GridBagConstraints(0, 6, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 1, 1));
        panelSmile.add(smile8,new GridBagConstraints(0, 7, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 1, 1));
        panelSmile.add(smile9,new GridBagConstraints(0, 8,1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 1, 1));
        panelSmile.add(smile10,new GridBagConstraints(0, 9, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 1, 1));

    }

    public void setControll(Controll controll){
        this.controll = controll;
    }

    public String getTextFromField(){
        return messageField.getText();
    }

    public void createMessage(String message){
        i++;
        if(i>=sizeLabel-1) {
            vectorLabel.VectorLabelIncrease(label);
            sizeLabel=sizeLabel+10;
        }

        if(i>40)
        dialogArea.add(label[i]);
        label[i].setText(message);
    }

    public void setLabel(){
        for (int i=0;i<37;i++){
            if (i >= sizeLabel - 1) {
                vectorLabel.VectorLabelIncrease(label);
                sizeLabel = sizeLabel * 2;
            }
            dialogArea.add(label[i]);
            label[i].setText("                                                                                                                                                                                                    ");
              }
    }

    public void createSmile(String smileName) {
        i++;
        if (i >= sizeLabel - 1) {
            vectorLabel.VectorLabelIncrease(label);
            sizeLabel = sizeLabel * 2;
        }
        if(i>40)
        dialogArea.add(label[i]);
        if (smileName.equals("smile1")) {
            label[i].setIcon(smile1.getIcon());
        }
        if (smileName.equals("smile2")) {
            label[i].setIcon(smile2.getIcon());
        }
        if (smileName.equals("smile3")) {
            label[i].setIcon(smile3.getIcon());
        }
        if (smileName.equals("smile4")) {
            label[i].setIcon(smile4.getIcon());
        }
        if (smileName.equals("smile5")) {
            label[i].setIcon(smile5.getIcon());
        }
        if (smileName.equals("smile6")) {
            label[i].setIcon(smile6.getIcon());
        }
        if (smileName.equals("smile7")) {
            label[i].setIcon(smile7.getIcon());
        }
        if (smileName.equals("smile8")) {
            label[i].setIcon(smile8.getIcon());
        }
        if (smileName.equals("smile9")) {
            label[i].setIcon(smile9.getIcon());
        }
        if (smileName.equals("smile10")) {
            label[i].setIcon(smile10.getIcon());
        }
    }
    public void setColor(Color color){
        panel.setBackground(color);
    }
    public JFrame getFrame(){
        return frame;
    }
}






