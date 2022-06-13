import com.github.weisj.darklaf.iconset.AllIcons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenEditor extends JPanel implements MouseMotionListener, MouseListener {
    private boolean ANTIALIAS;
    private RenderingHints hints;
    int mousePosX;
    int mousePosY;
    char board[][];
    Point mousePos;
    Piece selected;
    String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    String oldFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";

    public FenEditor() {
        board = ChessBoard.fenToTab(fen);
        ANTIALIAS = true;
        setAntiAliasing();
        addMouseListener(this);
        addMouseMotionListener(this);
        JButton save =new JButton("Save");
        JButton delete =new JButton("Delete");
        JButton restart =new JButton("Restart");
        setLayout(null);
        delete.setBounds(40,510,70,70);
        save.setBounds(340, 550, 150, 30);
        restart.setBounds(190, 550, 150, 30);
        add(save);
        add(restart);
        add(delete);

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fen="rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
                repaint();
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Do you wont save settings?", "Save Settings",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
                if (confirm == 0) {
                    ChessBoard.getInstance().setBord(board);
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
        g2d.drawString("Chose start position", 100, 40);
        if (ANTIALIAS)
            g2d.setRenderingHints(hints);
        ChessBoard.drawBoard(g2d, 40, 90, 50);
        paintPosytion(g2d, fen);
        if (selected != null) {
            Piece.drawPieces(selected, g2d, 50, mousePos);
        }
    }

    public void paintPosytion(Graphics2D g2d, String fen) {
        char tab[][] = ChessBoard.fenToTab(fen);
        for (int i = 0; i < tab.length; i++) {

            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j] != '0') {
                    System.out.println(tab[i][j]);
                    Piece.drawPieces(new Piece(tab[i][j], 40 + (j * 50) + 7, 90 + (i * 50) + 7), g2d, 50);
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

    void calculateMouseCoords(int posX, int posY) {
        if (posX > 40 && posX < 440)
            mousePosX = (posX - 40) / 50;
        else
            mousePosX = 8;
        if (posY > 90 && posY < 490)
            mousePosY = (posY - 90) / 50;
        else
            mousePosY = 8;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePos = new Point(e.getX(), e.getY());
        if (mousePosX < 8 && mousePosY < 8) {
            oldFen=fen;
            if (board[mousePosY][mousePosX] != '0'){
                selected = new Piece(board[mousePosY][mousePosX]);
                board[mousePosY][mousePosX]='0';
                fen=ChessBoard.tabToFen(board);
            }
            else
                selected=null;

        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePos = new Point(e.getX(), e.getY());
        calculateMouseCoords(e.getX(), e.getY());
        if (mousePosX < 8 && mousePosY < 8) {
            if(selected!=null){
                board[mousePosY][mousePosX]=selected.getSymbol();
                fen=ChessBoard.tabToFen(board);
                selected=null;
            }
        }else if(e.getX()>40&&e.getX()<110&&e.getY()>510&&e.getY()<580){
            selected=null;
        }else{
            selected=null;
            fen=oldFen;
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePos = new Point(e.getX(), e.getY());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        calculateMouseCoords(e.getX(), e.getY());
        mousePos = new Point(e.getX(), e.getY());
        //System.out.println(mousePosX+" / "+mousePosY);
        repaint();
    }
}
