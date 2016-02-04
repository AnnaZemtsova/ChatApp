import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
 * Created by anna on 23.12.15.
 */
public class UploadMusic {
   private JFrame frame;
   private JPanel panel;
   private JLabel label;
   private JTextField textField;
   private JButton cancel;
   private JButton upload;
   private JPanel panelNameMusic;
    private Vector nameMusic = new Vector();
   private JList<String> listOfMusic = new JList<String>();
    private Music music;
    static ArrayList<String> addedMusic = new ArrayList<String>();
    public JButton back;
    int i =0;
   static final int LENGTH = 323;


    public UploadMusic() {
        frame = new JFrame();
        panel = new JPanel();
        back = new JButton();
        back.setText("Сancel");
        panelNameMusic = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(500, 700));
        panelNameMusic.setPreferredSize(new Dimension(500, 550));

        FullVector();
        DefaultListModel defaultListModel = new DefaultListModel();

        listOfMusic.setModel(defaultListModel);
        listOfMusic.setPreferredSize(new Dimension(500, 550));
        listOfMusic.setListData(nameMusic);
        listOfMusic.setVisibleRowCount(nameMusic.size());
        listOfMusic.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listOfMusic.setBackground(Color.cyan);

        panel.setBackground(UserWindow.color);
       // panel.setBackground(Color.cyan);
        panelNameMusic.add(listOfMusic);
       // cancel = new JButton();
        //cancel.setText("Cancel");

        panel.add(back, new GridBagConstraints(0, 0, 0, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.WEST, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(panelNameMusic, new GridBagConstraints(1, 1, 2, 0, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
       // panel.add(cancel, new GridBagConstraints(1, 2, 1, 0, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.EAST, new Insets(0, 0, 0, 0), 0, 0));

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 700);
        frameDisplayCenter(500,700,frame);
        frame.setResizable(false);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

  listOfMusic.addMouseListener(new MouseListener() {
    @Override
    public void mouseClicked(MouseEvent e) {
        Object ob = listOfMusic.getSelectedValue();
        String strName = ob.toString();
        if(!music.compareAllMusic(strName)) {
            try {
                Music.workWithMusicFile.saveToFile(strName);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
           // music.saveToMusicFormat(strName);
            music.setMusic();
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

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }


    private  void frameDisplayCenter (int sizeWidth, int sizeHeight, JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        frame.setBounds(locationX, locationY, sizeWidth, sizeHeight);
    }

    public void FullVector(){
        nameMusic.add("Arch Enemy – Taking Back My Soul");
        nameMusic.add("Arch Enemy – Dead Eyes See No Future");
        nameMusic.add("Arch Enemy – Savage Messiah");
        nameMusic.add("Arch Enemy – Burning Angel");
        nameMusic.add("Arch Enemy – Enemy Within");
        nameMusic.add("Arch Enemy – Heart Of Darkness");
        nameMusic.add("Arch Enemy – We Will Rise");
        nameMusic.add("Arch Enemy – As the Pages Burn");
        nameMusic.add("Arch Enemy – My Apocalypse");
        nameMusic.add("Arch Enemy – Ravenous");
        nameMusic.add("Arch Enemy – Shadows and Dust");
        nameMusic.add("Arch Enemy – War Eternal");
        nameMusic.add("Arch Enemy – Under Black Flags We March");
        nameMusic.add("Otep – Buried Alive");
        nameMusic.add("Save – Улыбка Саманты");
        nameMusic.add("Save – Ложь прошла сквозь стену");
        nameMusic.add("Save – Стояли звери");
        nameMusic.add("Save – Смерть или Бог");
        nameMusic.add("Rammstein – Mein Herz Brennt");
        nameMusic.add("Rammstein – Ich Will");
        nameMusic.add("Arch Enemy – No More Regrets");
    }

    public ArrayList<String> readMusicFromFile(){
        ArrayList<String> musicNames = new ArrayList<String>();
        try {
            Music.workWithMusicFile.downloadFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        musicNames =  Music.workWithMusicFile.getArrayMusicName();
        return musicNames;
    }
    public void setColor(Color color){
        panel.setBackground(color);
    }


    public void setMusic(Music music){
        this.music = music;
    }


    public static void main(String []args){
        new UploadMusic();
    }
}
