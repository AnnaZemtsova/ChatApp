import javax.swing.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import java.io.*;
import javax.swing.*;
import javax.swing.text.StringContent;

/**
 * Created by anna on 23.12.15.
 */
/*public class MusicLast {
    static JFrame frame;
    private JPanel panel;
    private JButton back;
    private JTextField searchMusicField;
    private JButton search;
    JScrollPane scrollPane;
    //private JTextPane musicPane;
    private JPanel musicPanel;
    private JButton addMusic;
    private GridBagConstraints c;
    private Controll controll;
    private Box lableBox;
    private JScrollBar scrollBar;
    private JLabel[] label;
    static VectorLabel vectorMusicLabel;
    private int sizeLabel;
    private VectorLabel vectorLabel;
    private JButton[] play;
    private JButton[] pause;
    private Toolkit kit = Toolkit.getDefaultToolkit();
    private ArrayList arrayList = new ArrayList();
    static int size, Msize;
    private JPanel [] tmpPanel;
    private JList<Component> list ;
   // private JList<Component> tmp ;
    private  DefaultListModel<Component> defaultListModel ;
    static int i=0;
    private int Asize,asize =1;
    private Vector index;
    private Sound sound ;
    private MusicLast music;
    private RandomAccessFile fileServerMusic;
    private DataInput dataInput;
    private DataOutput dataOutput;
    String musicNames[]=new String[100];
    static int LENGTH = 323;
    private int pos=0;
    private WorkWithContactsFile workWithContactsFile;
    private UploadMusic uploadMusic;
    private ArrayList<String> allMusic;
    static  WorkWithMusicFile workWithMusicFile ;

    public MusicLast(final JButton play,final JLabel label1,final JButton pause){

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        String path = "/Users/anna/Desktop/звук/";
                        path += label1.getText();
                        path += ".wav";
                        System.out.println(path);
                        File f = new File(path);
                        sound = new Sound(f);
                        sound.play();
                    }
        });

        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = "/Users/anna/Desktop/звук/";
                path += label1.getText();
                path += ".wav";
                System.out.println(path);
                sound.stop();

            }
        });


    }

    public Music() throws FileNotFoundException {
        frame = new JFrame();
        play = new JButton[100];
        pause = new JButton[100];
       // tmpPanel = new JTextPane[10];
        tmpPanel  =new JPanel[100];
        //tmpPanel = new JPanel[30];
        label = new JLabel[100];

        //musicPane = new JTextPane();
        musicPanel = new JPanel();
        panel = new JPanel();
        back = new JButton();
        addMusic = new JButton();
        searchMusicField = new JTextField();
        search = new JButton();
        workWithContactsFile = new WorkWithContactsFile();
        workWithMusicFile = new WorkWithMusicFile("music.txt");
        frame.setLayout(new GridBagLayout());
        musicPanel.setLayout(new BoxLayout(musicPanel,BoxLayout.Y_AXIS));
        panel.setLayout(new GridBagLayout());

         scrollPane = new JScrollPane(musicPanel);
     // defaultListModel = new DefaultListModel();
       // list = new JList<Component>(defaultListModel);
       // scrollPane = new JScrollPane(list);
       // scrollPane.setViewportView(musicPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.setPreferredSize(new Dimension(1000, 800));
        scrollPane.setPreferredSize(new Dimension(900, 500));
        musicPanel.setPreferredSize(new Dimension(900, 500));
        searchMusicField.setPreferredSize(new Dimension(300, 30));
        //setLabels();

        panel.setBackground(Color.cyan);
        search.setText("Search");
        back.setText("Back");
        addMusic.setText("Add");


        panel.add(addMusic, new GridBagConstraints(4, 0, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(back, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(searchMusicField, new GridBagConstraints(0, 3, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(search, new GridBagConstraints(4, 3, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(scrollPane, new GridBagConstraints(0, 1, 4, 2, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        try {
            workWithMusicFile.downloadFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        allMusic = workWithMusicFile.getArrayMusicName();

        for(int j=0;j<allMusic.size() ;j++){
            createPanel(allMusic.get(j));
        }

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1000, 1000);
        frameDisplayCenter(1000,1000,frame);
        frame.setResizable(false);


        back.addActionListener(new ActionListener() {
          @Override
            public void actionPerformed(ActionEvent e) {
             frame.setVisible(false);
              UserWindow.frame.setVisible(true);
         }
        });

        addMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               UploadMusic uploadMusic= new UploadMusic();
               uploadMusic.setMusic(getMusic());
            }
        });

        }


    private void vectorArrIncrease(String[] arr){
        Msize=musicNames.length;
        size=Msize;
        String tvec []=new String [Msize+10];
        for (int i=0; i<size ;i++)
            tvec[i]=musicNames[i];
        musicNames=tvec;
        Msize=Msize+10;
    }

    private  void frameDisplayCenter (int sizeWidth, int sizeHeight, JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        frame.setBounds(locationX, locationY, sizeWidth, sizeHeight);
    }


/*public void setLabels(){
    for (int j=0;j<30;j++) {
        tmpPanel[j] = new JPanel();
        tmpPanel[j].setPreferredSize(new Dimension(400, 15));
        label[j] = new JLabel();
        label[j].setPreferredSize(new Dimension(300, 15));
        tmpPanel[j].add(label[j]);
     //   musicPanel.revalidate();
        musicPanel.add(tmpPanel[j]);
        scrollPane.revalidate();

    }
}*/


 /*  public void createPanel(String songsName){
       tmpPanel[i]= new JPanel();
       tmpPanel[i].setPreferredSize(new Dimension(400, 15));
       label[i] = new JLabel();
       label[i].setPreferredSize(new Dimension(400, 15));
        label[i].setText(songsName);
        play[i] = new JButton();
        play[i].setText("Play");
        pause[i]=new JButton();
        pause[i].setText("Pause");
        tmpPanel[i].add(label[i]);
        tmpPanel[i].add(play[i]);
        tmpPanel[i].add(pause[i]);
        musicPanel.add(tmpPanel[i]);
         scrollPane.revalidate();
        Music music = new Music(play[i],label[i],pause[i]);
        this.i++;
    }



    public void setMusic(Music music){
        this.music = music;
    }

    public  Music getMusic(){
        return this;
    }



    public static void main (String []args){//для теста
        try {
            new Music();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}*/