import java.io.*;
import java.util.ArrayList;

/**
 * Created by anna on 09.01.16.
 */


public class WorkWithMusicFile {
    private DataOutput dataOutput;
    private DataInput dataInput;
    private RandomAccessFile randomAccessFile;
    private final static int LENGTH = 200;
    private ArrayList <String> musicName;

    public WorkWithMusicFile(String path) throws FileNotFoundException {
        randomAccessFile = new RandomAccessFile(path,"rw");
        dataInput = randomAccessFile;
        dataOutput=randomAccessFile;
        musicName = new ArrayList<String>();
    }

    public ArrayList<String> getArrayMusicName(){
        return musicName;
    }

    public void downloadFromFile() throws IOException {
        musicName.clear();
        int count;
        randomAccessFile.seek(0);
        try{
            count = dataInput.readInt();
        }catch (IOException e){
            randomAccessFile.seek(0);
            count=0;
            dataOutput.writeInt(0);
        }
        for (int i =0;i<count;i++){
            randomAccessFile.seek(4+(4+LENGTH*2)*i);
            musicName.add(readName());
        }
    }

    private String readName() throws IOException{
        StringBuffer stringBuffer = new StringBuffer();
            int lengthName = dataInput.readInt();
            for (int i=0;i<lengthName;i++){
                stringBuffer.append(dataInput.readChar());
            }

        return stringBuffer.toString();
    }

    private void writeStringToFile(String musicName) throws IOException {
        dataOutput.writeInt(musicName.length());
        dataOutput.writeChars(musicName);
        for (int i=musicName.length();i<LENGTH;i++){
            dataOutput.writeChar(' ');
        }
    }


    public void saveToFile(String musicName) throws IOException {
         int count;
        randomAccessFile.seek(0);
        try{
            count = dataInput.readInt();
            randomAccessFile.seek(0);
            dataOutput.writeInt(count+1);
        }catch (IOException e){
            randomAccessFile.seek(0);
            count=0;
            dataOutput.writeInt(0);
        }
        randomAccessFile.seek(4+(4+LENGTH*2)*count);
        writeStringToFile(musicName);
    }


    public boolean chackSameNames(String musicNames) throws IOException {
        downloadFromFile();
        for (int i=0;i<musicName.size();i++){
            if(musicName.get(i).equals(musicNames)){
                return true;
            }
        }
       return false;
    }
}
