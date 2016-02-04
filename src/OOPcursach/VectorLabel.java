import javax.swing.*;

public class VectorLabel {
    static int size,Msize;


    public JLabel[] VectorLabel(JLabel[] labels){
        Msize=10;
        size=0;
        labels=new JLabel[Msize];
        for (int i=0;i<10;i++){
            labels[i]= new JLabel();
        }
        return labels;
    }


    public void VectorLabelIncrease(JLabel[] arr){
        Msize=arr.length;
        size=Msize;
        JLabel tvec []=new JLabel [Msize+10];
        for (int i=0; i<size ;i++)
            tvec[i]=arr[i];
        MessageWindows.label=tvec;
        Msize=Msize+10;
        for (int i= size-1;i<Msize;i++){
            MessageWindows.label[i]= new JLabel();
        }
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return Msize;
    }

    public JLabel get(int pos){
        return MessageWindows.label[pos];
    }


    public void set(JLabel val, int pos){
        MessageWindows.label[pos]=val;
    }



    public void erase(int pos){
        for (int i=pos;i<(size-1);i++)
            MessageWindows.label[i]=MessageWindows.label[i+1];
        size--;
    }

    public void clear(){
        size=0;
    }
}