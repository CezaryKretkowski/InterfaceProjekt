import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeChessBoardColor extends JPanel {
    JSpinner firstRGB[]={
            new JSpinner(new SpinnerNumberModel(0.0, 0.0, 255.0, 1.0)),
            new JSpinner(new SpinnerNumberModel(0.0, 0.0, 255.0, 1.0)),
            new JSpinner(new SpinnerNumberModel(0.0, 0.0, 255.0, 1.0))
    };
    JSpinner secondRGB[]={
            new JSpinner(new SpinnerNumberModel(255.0, 0.0, 255.0, 1.0)),
            new JSpinner(new SpinnerNumberModel(255.0, 0.0, 255.0, 1.0)),
            new JSpinner(new SpinnerNumberModel(255.0, 0.0, 255.0, 1.0))
    };
    Color first= new Color(0.0f, 0.4f, 0.0f);
    Color second=Color.WHITE;

    JButton save;
    public ChangeChessBoardColor(){
        ChangeListener listenerChange = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                repaint();
            }
        };


        setLayout(null);
        save=new JButton("Save");
        save.setBounds(390,40,100,30);
        Label color0=new Label("First  Color");
        color0.setBounds(60,10,120,30);
        Label color1=new Label("Second Color");
        color1.setBounds(260,10,120,30);
        firstRGB[0].setBounds(10,40,60,30);
        firstRGB[1].setBounds(70,40,60,30);
        firstRGB[2].setBounds(130,40,60,30);
        secondRGB[0].setBounds(200,40,60,30);
        secondRGB[1].setBounds(260,40,60,30);
        secondRGB[2].setBounds(320,40,60,30);

        firstRGB[0].addChangeListener(listenerChange);
        firstRGB[1].addChangeListener(listenerChange);
        firstRGB[2].addChangeListener(listenerChange);

        secondRGB[0].addChangeListener(listenerChange);
        secondRGB[1].addChangeListener(listenerChange);
        secondRGB[2].addChangeListener(listenerChange);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ChessBoard.first=first;
               ChessBoard.second=second;
            }
        });
        add(firstRGB[0]);
        add(firstRGB[1]);
        add(firstRGB[2]);
        add(color0);
       add(color1);
        add(save);
        add(secondRGB[0]);
        add(secondRGB[1]);
        add(secondRGB[2]);
        //add(this);

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        double fr=(Double) firstRGB[0].getValue();
        double fg=(Double) firstRGB[1].getValue();
        double fb=(Double) firstRGB[2].getValue();
        double sr=(Double) secondRGB[0].getValue();
        double sg=(Double) secondRGB[1].getValue();
        double sb=(Double) secondRGB[2].getValue();
         first =new Color((int)fr,(int)fg,(int)fb);
         second =new Color((int)sr,(int)sg,(int)sb);

        ChessBoard.drawBoard((Graphics2D) g,10,100,40,first,second);
    }
}
