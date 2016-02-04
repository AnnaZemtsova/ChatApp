public class Command {
    public static final String accepted = "Accepted";
    public static final String disconnect = "Disconnect";
    public static final String rejected = "Rejected";

    protected String nowCommand;
    protected boolean isHaveFalseCommand;

    public Command() {

    }

    public void setCommand(String command) {
        isHaveFalseCommand = true;
        if (command!=null){
            if (command.equals(accepted)) {
                nowCommand = accepted;
                isHaveFalseCommand = false;

            } else if (command.equals(disconnect)) {
                nowCommand = disconnect;
                isHaveFalseCommand = false;

            } else if (command.equals(rejected)) {
                nowCommand = rejected;
                isHaveFalseCommand = false;

            }
        }
    }

    public String getCommand(){
        return nowCommand;
    }

    public boolean isHaveFalseCommand(){
        return isHaveFalseCommand;
    }
}