/**
 * Created by anna on 20.12.15.
 */
public class SmileCommand extends Command {
    public static final String smile = "Smile";
    private String sendedSmile;
    private boolean isHaveSmile;
    public SmileCommand (){
        super.nowCommand = smile;
    }

    public void setCommand(String command){
        if (smile.equals(command))
            super.isHaveFalseCommand = false;
        else super.isHaveFalseCommand = true;
   }

    public void setSmile(String sendedSmile){
        this.sendedSmile = sendedSmile;
        this.isHaveSmile = true;
    }

    public boolean isHaveSmile(){
        return isHaveSmile;
    }

    public String getSmile(){
        isHaveSmile = false;
        return sendedSmile;
    }
}
