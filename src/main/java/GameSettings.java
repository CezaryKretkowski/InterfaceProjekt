import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettings extends JPanel  {
    JLabel title;
    String fen= "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    JList<String> fenList;
    private boolean ANTIALIAS;
    private RenderingHints hints;
    JTextArea getFen;
    JButton saveFen;
    JButton Save;
    String data[]={
            "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR",
            "6R1/8/1p6/4k3/P3r2p/7P/1r4P1/R6K",
            "1r4n1/3k1p2/pq2pP1p/1p2P1pP/3P2P1/2Q5/8/R1R3K1",
            "3r2k1/R2N1pp1/2pP3p/2q1p3/4Pn2/1r3N1P/3Q1PB1/3R2K1",
            "8/4R3/8/7p/1P2Pk1P/2Kp4/8/3r4",
            "8/5bNk/1p4pp/8/1r2B3/2q3P1/2Q4P/4R2K",
            "r3r1k1/3p2p1/b1pq3p/R1nP1p2/2B1p3/1P5P/5PPN/1N1QR1K1",
            "r1bnk2r/pppp1ppp/1b4q1/4P3/2B1N3/Q1Pp1N2/P4PPP/R3R1K1",
            "r2r4/p1p2p1p/n5k1/1p5N/2p2R2/5N2/P1K3PP/5R2",
            "r1qbr2k/1p2n1pp/3B1n2/2P1Np2/p4N2/PQ4P1/1P3P1P/3RR1K1",
            "2q2r2/5rk1/4pNpp/p2pPn2/P1pP2QP/2P2R2/2B3P1/6K1"
    };
    JSlider timerChose;
    int gameTime=10;
    String gamePace="10.00";
    GameSettings(){

        ANTIALIAS=true;
        setAntiAliasing();
        setLayout(null);
       // title=new JLabel("Chose the pace of the game");
       // title.setBounds(160,40,200,40);
       // add(title);
        timerChose=new JSlider(1,60,10);
        timerChose.setBounds(50,80,200,50);

        timerChose.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gameTime=timerChose.getValue();
                gamePace=gameTime+".00";
                repaint();
            }
        });
        add(timerChose);
        fenList=new JList<>(data);
        fenList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               fen=fenList.getSelectedValue();
               repaint();
            }
        });
        fenList.setBounds(300,170,100,240);
        add(fenList);
        getFen=new JTextArea();
        JLabel fenEditor=new JLabel("Provide your own position");
        fenEditor.setBounds(40,420,240,30);
        getFen.setBounds(40,450,240,30);

        saveFen=new JButton("Save Fen");
        saveFen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fen=getFen.getText();
                if(fen.isEmpty()){
                    fen= "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
                }
            }
        });
        saveFen.setBounds(300,450,100,30);
        add(fenEditor);
        add(saveFen);
        add(getFen);
        Save =new JButton("Save");
        Save.setBounds(250,520,170,50);
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessBoard.getInstance().setBord(ChessBoard.fenToTab(fen));
                RenderComponets.paceOfGame=gameTime;
                RenderComponets.setTime();
            }
        });
        add(Save);

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g2d.drawString(gamePace,260,110);
        g2d.drawString("Chose the pace of the game",50,40);
        if (ANTIALIAS)
            g2d.setRenderingHints(hints);
        ChessBoard.drawBoard(g2d,40,170,30);
        paintPosytion(g2d,fen);
    }
    public void paintPosytion(Graphics2D g2d,String fen) {
        char tab[][] = ChessBoard.fenToTab(fen);
        for (int i = 0; i < tab.length; i++) {

            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j] != '0'){
                    System.out.println(tab[i][j]);
                    Piece.drawPieces(new Piece(tab[i][j],  40 + (j * 30) + 2, 170 + (i * 30) + 2), g2d, 40);
                }
            }
        }

    }
    public void setAntiAliasing() {
        hints = new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        if (ANTIALIAS)
            hints.put(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        else
            hints.put(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_OFF);


    }

}
