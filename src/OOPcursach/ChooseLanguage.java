import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by anna on 24.12.15.
 */
public class ChooseLanguage {
    private JFrame frame;
    private JPanel panel;
    private JButton ru;
    private JButton ua;
    private JButton us;
    private JLabel choose;
    private JButton cancel;
    static ArrayList<String> language ;//= new Vector<String>();
    static ArrayList arrayListOfLanguage;
    static int languageChoose;

    public ChooseLanguage(){
        languageChoose=0;
        language = new ArrayList<String>();
        arrayListOfLanguage = new ArrayList();
        frame = new JFrame();
        panel = new JPanel();
        ru = new JButton();
        ua = new JButton();
        us = new JButton();
        cancel = new JButton();
        choose = new JLabel();
        ru.setText("    Русский");
        ua.setText("Українська");
        us.setText("    English");
        choose.setText("Выберите язык");
        cancel.setText("Отменить");
        panel.setBackground(Color.cyan);
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(300, 200));
        panel.add(choose, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(ru,new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(ua,new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(us,new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(cancel,new GridBagConstraints(0, 4, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        frame.add(panel);
        FullAllArrays();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(300, 200);
        frame.setResizable(false);

        ru.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                languageChoose=1;
            }
        });

        ua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languageChoose=2;
            }
        });

        us.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languageChoose=3;
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                languageChoose=3;
                frame.setVisible(false);
            }
        });
    }

    public void FullAllArrays(){
        if (languageChoose==1) {
            language.add(1, "Регистрация");
            language.add(2, "Все пользователи");
            language.add(3, "Мои друзья");
            language.add(4, "Музыка");
            language.add(5, "Поиск");
            language.add(6, "Назад");
            language.add(7, "Позвонить");
            language.add(8, "Удалить из друзей");
            language.add(9, "Добавить в друзья");
            language.add(10, "Отменить");
            language.add(11, "Добавить");
            language.add(12, "Играть");
            language.add(13, "Пауза");
            language.add(14, "Удалить");
            language.add(15, "Принять");
            language.add(16, "Отклонить");
            language.add(17, "Отключить");
            language.add(18, "Отправить");
        }
        if (languageChoose==2){
            language.add(0,"Вхід");
            language.add(1,"Регістрація");
            language.add(2,"Усі користувачі");
            language.add(3," Мої друзі");
            language.add(4,"Музика");
            language.add(5,"Пошук");
            language.add(6,"Назад");
            language.add(7,"Зателефонувати");
            language.add(8,"Видалити з друзів");
            language.add(9,"Додати в друзі");
            language.add(10,"Відмінити");
            language.add(11,"Додати");
            language.add(12,"Грати");
            language.add(13,"Пауза");
            language.add(14,"Видалити");
            language.add(15,"Прийняти");
            language.add(16,"Відхилити");
            language.add(17,"Від'єднати");
            language.add(18,"Відправити");
        }

        if (languageChoose==3){
            language.add(0,"Entry");
            language.add(1,"Registrarion");
            language.add(2,"All User");
            language.add(3,"My friends");
            language.add(4,"Music");
            language.add(5,"Search");
            language.add(6,"Back");
            language.add(7,"Call");
            language.add(8,"Delete From Friends");
            language.add(9,"Add to Friends");
            language.add(10,"Cancel");
            language.add(11,"Add");
            language.add(12,"Play");
            language.add(13,"Pause");
            language.add(14,"Delete");
            language.add(15,"Accept");
            language.add(16,"Reject");
            language.add(17,"Disconnect");
            language.add(18,"Send");
        }
    }

    public static void main(String[]args){
        new ChooseLanguage();
        for (int i=0;i<language.size();i++){
            System.out.println(language.get(i));
        }
    }
}
