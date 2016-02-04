import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by anna on 26.01.16.
 */
public class Music {

    private JFrame frame;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JButton backButton;
    private JButton addButton;
    private JButton searchButton;
    private JTextField searchField;
    private JList list;

    private Sound sound;
    private Vector<String> musicWithGaps = new Vector<String>();
    private ArrayList<String> musicArray = new ArrayList<String>();
    private ArrayList<String> allMusic;
    private String lastSong="";
    static WorkWithMusicFile workWithMusicFile;
    private static int SIZE = 40;
    private String highlight;
   private int amountClick = 0;
   private String lastCurrent = "";

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Music() throws FileNotFoundException {
        sound = new Sound();
        frame = new JFrame();
        panel = new JPanel();
        list = new JList<JLabel>();
        scrollPane = new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        backButton = new JButton();
        addButton = new JButton();
        //searchButton = new JButton();
       // searchField = new JTextField();
        workWithMusicFile = new WorkWithMusicFile("music.txt");

        panel.setPreferredSize(new Dimension(1000, 800));
        scrollPane.setPreferredSize(new Dimension(900, 700));
       // searchField.setPreferredSize(new Dimension(820, 30));
        panel.setBackground(UserWindow.color);
       // searchButton.setText("Search");
        backButton.setText("Back");
        addButton.setText("Add");

        panel.add(backButton, new GridBagConstraints(4, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(addButton, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(scrollPane, new GridBagConstraints(0, 1, 4, 2, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
       // panel.add(searchField, new GridBagConstraints(0, 3, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        //panel.add(searchButton, new GridBagConstraints(4, 3, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        setMusic();

        frame.pack();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1000, 1000);
        frameDisplayCenter(1000, 1000, frame);
        frame.setResizable(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(sound.isPlaying()) sound.stop();
                frame.setVisible(false);
                UserWindow.frame.setVisible(true);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UploadMusic uploadMusic = new UploadMusic();
                uploadMusic.setMusic(getMusic());
            }
        });

        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                highlight = list.getSelectedValue().toString();
                String currentSong = getMusicString(highlight);
                if (!currentSong.equals(lastCurrent)) {
                    if (lastCurrent.equals("")) {
                        play(currentSong);
                        changePlay(highlight);
                    } else {
                        if(amountClick==0) changePlay(lastSong);
                            sound.stop();
                        play(currentSong);
                        changePlay(highlight);
                        amountClick=0;
                    }
                } else {
                    if (amountClick == 1) {
                        play(currentSong);
                        changePlay(highlight);
                        amountClick=0;
                    } else {
                        sound.stop();
                        changePlay(highlight);
                        amountClick=1;
                    }
                }
                list.repaint();
                lastCurrent = currentSong;
                lastSong =list.getSelectedValue().toString();
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

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void play(String currentSong){
        String path = "/Users/anna/Desktop/звук/";
        path += currentSong;
        path += ".wav";
        File f = new File(path);
        sound = new Sound(f);
        sound.play();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void downloadFromFile() {
        try {
            workWithMusicFile.downloadFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        allMusic = workWithMusicFile.getArrayMusicName();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void frameDisplayCenter(int sizeWidth, int sizeHeight, JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        frame.setBounds(locationX, locationY, sizeWidth, sizeHeight);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Music getMusic() {
        return this;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private String getMusicString(String musicName) {
        String songsName ="";
        int index = 0;
        char[] music = musicName.toCharArray();
        for (int k = musicName.length()-1; k > 0; k--) {
                if (music[k] != ' ') {
                    index = k;
                    break;
                }
        }
        if (index != 0) {
            for (int k = 3; k < index+1; k++) {
                songsName += music[k];
            }
        } else {
            System.out.println("Error");
        }
        return songsName;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void saveToMusicFormat(String strName) {
        int size = strName.length();
       if (size < SIZE) {
            char c =5125;
            strName += c;
            musicWithGaps.add(strName);
       } else {
            System.out.println("Длина песни превышает" + SIZE + "символов");
        }

    }

    public void setColor(Color color){
        panel.setBackground(color);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Vector<String> changePlay(String string) {
        String changedString = "";
        char[] tmp = string.toCharArray();
        if (tmp[0] == 5125) {
            char c = 8545;
            changedString += c;
        } else {
            char c = 5125;
            changedString += c;
        }
        for (int k=1;k<tmp.length;k++) {
            changedString +=tmp[k];
        }
        if (searchIndex(string) != musicWithGaps.size()) {
            musicWithGaps.set(searchIndex(string), changedString);
        } else {
        }
        return musicWithGaps;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int searchIndex(String string) {
        for (int k = 0; k < musicWithGaps.size(); k++) {
            if (musicWithGaps.get(k) == string) {
                return k;
            }
        }
        return musicWithGaps.size();
    }
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public void setMusic(){
      musicWithGaps.clear();
    downloadFromFile();
    musicArray = workWithMusicFile.getArrayMusicName();
    for (int k = 0; k < musicArray.size(); k++) {
        char c = 5125;
        String songsName=c+"  ";
        songsName+=musicArray.get(k);
        musicWithGaps.add(songsName);
    }
      list.setListData(musicWithGaps);
      list.setVisibleRowCount(musicWithGaps.size());
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean compareAllMusic(String songs) {
        for (int k = 0; k < allMusic.size(); k++) {
            if (allMusic.get(k).equals(songs)) {
                return true;
            }
        }
        return false;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws FileNotFoundException {//для теста
        new Music();
    }
}
