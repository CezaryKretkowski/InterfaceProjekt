import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Settings extends JDialog implements ActionListener{
    JTabbedPane tabbedPane;
    JPanel cards[] = {
            new ChangeChessBoardColor(),
            new JPanel(),
            new JPanel()
    };

    public Settings() {
        this.setSize(512, 650);
        tabbedPane=new JTabbedPane();
        cards[1].add(new Label("game Setting"));

        tabbedPane.addTab("Apperence Setting",cards[0]);
        tabbedPane.addTab("Game Setting",cards[1]);
        this.add(tabbedPane, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("settings")) {
            this.setVisible(true);
        }
    }


}
