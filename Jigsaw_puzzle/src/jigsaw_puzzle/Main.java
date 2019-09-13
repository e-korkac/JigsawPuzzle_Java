package jigsaw_puzzle;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;

public class Main {

    static JButton invisible;

    static void switchButtons(JButton clickedButton) {
        if (invisible.getLocation().x < (clickedButton.getLocation().x - clickedButton.getSize().width)
                || invisible.getLocation().x > (clickedButton.getLocation().x + clickedButton.getSize().width)
                || invisible.getLocation().y < (clickedButton.getLocation().y - clickedButton.getSize().height)
                || invisible.getLocation().y > (clickedButton.getLocation().y + clickedButton.getSize().height)
                || (invisible.getLocation().y != clickedButton.getLocation().y
                && invisible.getLocation().x != clickedButton.getLocation().x)) {
            return;
        }

        Point tmpLoc = clickedButton.getLocation();
        clickedButton.setLocation(invisible.getLocation());
        invisible.setLocation(tmpLoc);
    }

    public static void main(String[] args) {
        LayoutManager layOut = new GridLayout(3, 3);

        Frame f = new Frame();
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setLayout(layOut);

        List<Integer> numbers = new ArrayList<>();
        JButton btn = new JButton();
        for (int i = 1; i < 13; i++) {
            numbers.add(i);
        }
        
        Collections.shuffle(numbers);
        
        for(int number : numbers){
            btn = new JButton(String.valueOf(number));
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    switchButtons((JButton) e.getSource());
                }
            });
            f.add(btn);
        }
       
        btn.setVisible(false);
        invisible = btn;

        f.setSize(300, 300);
        f.setVisible(true);
        
        
    }
}
