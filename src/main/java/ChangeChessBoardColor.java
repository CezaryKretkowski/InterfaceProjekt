import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorChooserUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeChessBoardColor extends JPanel {

    Color first = new Color(0.0f, 0.4f, 0.0f);
    Color second = Color.WHITE;
    Color third = Color.black;

    JButton save;
    JButton helpColorPcker;
    JButton saveFirstColor;
    JButton saveSecondColor;

    public ChangeChessBoardColor() {


        first = ChessBoard.first;
        second = ChessBoard.second;

        setLayout(null);
        save = new JButton("Save");
        save.setBounds(340, 550, 150, 30);

        saveFirstColor = new JButton("Chose first color");
        saveFirstColor.setBounds(340, 90 + 20, 150, 30);

        saveSecondColor = new JButton("Chose second color");
        saveSecondColor.setBounds(340, 140 + 20, 150, 30);

        helpColorPcker = new JButton("Chose help  picker color");
        helpColorPcker.setBounds(340, 40 + 20, 150, 30);

        Label color0 = new Label("Choose your appearance settings");
        color0.setBounds(100, 10, 250, 30);


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Do you wont save settings?", "Save Settings",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (confirm == 0) {
                    ChessBoard.first = first;
                    ChessBoard.second = second;
                    ChessBoard.helpPointer = third;
                }
            }
        });

        // add(color0);
        add(save);
        add(saveFirstColor);
        add(saveSecondColor);
        add(helpColorPcker);

        //add(this);

        saveFirstColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(new JColorChooser(),
                        "Choose a color...", getBackground());
                if (c != null) {
                    first = c;
                    repaint();
                }
            }
        });

        saveSecondColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(new JColorChooser(),
                        "Choose a color...", getBackground());
                if (c != null) {
                    second = c;
                    repaint();
                }
            }
        });

        helpColorPcker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(new JColorChooser(),
                        "Choose a color...", getBackground());
                if (c != null) {
                    third = c;
                    repaint();
                }
            }
        });

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g2d.drawString("Choose your appearance settings", 15, 40);

        ChessBoard.drawBoard((Graphics2D) g, 10, 60, 40, first, second);


        g2d.setColor(first);
        g2d.fillRect(10, 390 + 20, 160, 160);
        g2d.setColor(second);
        g2d.fillRect(170, 390 + 20, 160, 160);
        g2d.setColor(third);
        g2d.fillOval(45 + 15, 420 + 35, 60, 60);
        g2d.setColor(third);
        g2d.fillOval(205 + 15, 420 + 35, 60, 60);
    }
}
