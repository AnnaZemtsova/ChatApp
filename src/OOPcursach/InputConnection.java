import com.sun.tools.javah.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anna on 08.12.15.
 */
public class InputConnection {
    private JFrame frame = new JFrame();
    private JButton acceptButton;
    private JButton rejectButton;
    private JLabel inputCalling;
    private JPanel panel;
    static boolean flag;
    static boolean isInputConnection;

    public InputConnection(String nickName){
        isInputConnection = false;
        panel.setBackground(UserWindow.color);
       // panel.setBackground(Color.cyan);
        frame.setContentPane(panel);
        frame.setSize(300,200);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        inputCalling.setText(nickName);

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             flag=true;
                isInputConnection=false;
                frame.setVisible(false);
            }
        });
        rejectButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                flag = false;
                isInputConnection=false;
                frame.setVisible(false);
            }
        });
    }
    public void setColor(Color color){
        panel.setBackground(color);
    }

}
