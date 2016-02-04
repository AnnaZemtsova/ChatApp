import java.io.DataInput;
import java.io.DataOutput;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class WorkWithContactsFile {
    private Contacts contacts;
    private boolean isHaveComponent;
    private RandomAccessFile file;
    private DataInput dataInput;
    private DataOutput dataOutput;

    public static final int LENGTH_NICK = 200, LENGTH_IP = 15;
    private static final int SIZE_CONT = 32+LENGTH_NICK*16 + 32 + LENGTH_IP*16 + 8;

    public WorkWithContactsFile() throws FileNotFoundException {
        file = new RandomAccessFile("ContactsFile.txt", "rw");
        dataInput = file;
        dataOutput = file;
        contacts = new Contacts();
    }

    private String getStringFromFile(int size, int LENGTH) throws IOException {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (; i < size; i++){
            sb.append(dataInput.readChar());
        }
        for (; i < LENGTH; i++)
            dataInput.readChar();

        return sb.toString();
    }

    public void readContacts() throws IOException {
        file.seek(0);
        int count;
        try {
            count = dataInput.readInt();
            //System.out.println(count+" count");
        } catch (IOException e) {
            //System.out.println("ñóêà, êàêîãî õðåíà");
            count = 0;
            file.seek(0);
            dataOutput.writeInt(count);
        }
        if (count != 0) {
            //	file.seek(32);
            for (int i = 0; i < count; i++) {
                file.seek(32+SIZE_CONT*i);
                int count1;
                String IP, nick;
                count1 = dataInput.readInt();
                nick = getStringFromFile(count1, LENGTH_NICK);
                count1 = dataInput.readInt();
                IP = getStringFromFile(count1, LENGTH_IP);
                boolean isDeleted = dataInput.readBoolean();
                if (!isDeleted) contacts.addContact(nick, IP);
                //	System.out.println("i: "+i+" "+isDeleted);
            }
        }
    }

    private void writeInFile(String str, int LENGTH) throws IOException {
        int i = 0;
        dataOutput.writeInt(str.length());
        for(;i<str.length();i++) {
            dataOutput.writeChar(str.charAt(i));
        }
        for(;i<LENGTH;i++)
            dataOutput.writeChar(' ');
    }

    public void addContactToFile(String nick, String IP) throws IOException {
        file.seek(0);
        int count;
        try {
            count = dataInput.readInt();
        } catch (IOException e) {
            count = 0;
        }
        file.seek(0);
        //  System.out.println(count+ " count");
        dataOutput.writeInt(count+1);
        file.seek(32+count*SIZE_CONT);
        writeInFile(nick,LENGTH_NICK);
        writeInFile(IP,LENGTH_IP);
        dataOutput.writeBoolean(false);
    }

    public boolean deleteContacts(String nickName) throws IOException{
        boolean isMakeDelete = false;
        file.seek(0);
        int count = dataInput.readInt();
        if (count != 0) {
            //	file.seek(32);
            for (int i = 0; i < count; i++) {
                file.seek(32+SIZE_CONT*i);
                int count1;
                String IP, nick;
                count1 = dataInput.readInt();
                nick = getStringFromFile(count1, LENGTH_NICK);
                count1 = dataInput.readInt();
                IP = getStringFromFile(count1, LENGTH_IP);
                if (nick.equals(nickName)){
                    isMakeDelete = true;
                    dataOutput.writeBoolean(true);
                }else{
                    boolean isDeleted = file.readBoolean();
                }
            }
        }
        return isMakeDelete;
    }

    public Contacts getContacts() {
        return contacts;
    }

}