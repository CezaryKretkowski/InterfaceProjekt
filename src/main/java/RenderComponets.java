import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.LinkedList;

public class RenderComponets extends JPanel {

    private int heightOfWindow;
    private int widthOfWindow;
    private MouseInput mouseInput;
    public double chessBoardWidth;
    public double chessFildWidth;
    private double leftPanel;
    public Graphics2D g2d;
    public Point boardPosX;
    public Point boardPosy;
    private boolean ANTIALIAS;
    private LinkedList<Point> legalMoves;
    private RenderingHints hints;
    public static Timer whiteTimer;
    public static Timer blackTimer;
    public static int paceOfGame = 10;
    JButton restart;
    JButton pouse;
    JButton resum;
    JButton whiteSurender;
    JButton blackSurender;

    public void setLegalMoves(LinkedList<Point> legalMoves) {
        this.legalMoves = legalMoves;
    }

    public static void setTime() {
        blackTimer.setCounter(paceOfGame);
        whiteTimer.setCounter(paceOfGame);
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

    protected void calculateSize() {

        Rectangle r = super.getBounds();
        heightOfWindow = r.height;
        widthOfWindow = r.width;
        double w = (double) widthOfWindow / (double) 100;
        this.chessBoardWidth = w * 50.75;
        this.chessFildWidth = chessBoardWidth / 8.0;
        if (chessFildWidth < 50.0)
            chessFildWidth = 50.0;
        if (chessFildWidth > 120.0)
            chessFildWidth = 120.0;
        this.leftPanel = w * 12.65;
        this.boardPosX.x = (int) leftPanel;
        this.boardPosX.y = (int) leftPanel + ((int) chessFildWidth * 8);
        this.boardPosy.x = 30;
        this.boardPosy.y = 30 + ((int) chessFildWidth * 8);

    }

    protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = ClassLoader.getSystemResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public RenderComponets() {


        whiteSurender = new JButton("Resign");
        blackSurender = new JButton("Resign");
        whiteSurender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MouseInput.turn == MouseInput.Turn.White)
                    resigne(MouseInput.Turn.White);
            }
        });
        blackSurender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MouseInput.turn == MouseInput.Turn.Black)
                    resigne(MouseInput.Turn.Black);
            }
        });
        add(whiteSurender);
        add(blackSurender);
        this.legalMoves = new LinkedList<>();
        this.ANTIALIAS = true;
        setLayout(null);
        this.boardPosX = new Point();
        this.boardPosy = new Point();
        this.setBackground(Color.DARK_GRAY);
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        mouseInput = new MouseInput(this);
        this.addMouseListener(mouseInput);

        this.addMouseMotionListener(mouseInput);
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                repaint();
            }

        });
        whiteTimer = new Timer(this);
        //whiteTimer.startTimer();
        blackTimer = new Timer(this);
        //  blackTimer.startTimer();
        this.setAntiAliasing();
        restart = new JButton("Restart");
        pouse = new JButton("Pause");
        resum = new JButton("Resume");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
                repaint();
                legalMoves.clear();
                MouseInput.turn = MouseInput.Turn.White;
            }
        });
        pouse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mouseInput.pouse = true;
                //  if(MouseInput.turn== MouseInput.Turn.White)
                whiteTimer.stopTimer();
                // if(MouseInput.turn== MouseInput.Turn.Black)
                blackTimer.stopTimer();
            }
        });
        resum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mouseInput.pouse = false;
                if (!ChessBoard.tabToFen(ChessBoard.getInstance().getBord()).equals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/")) {
                    if (MouseInput.turn != MouseInput.Turn.White)
                        whiteTimer.startTimer();
                    if (MouseInput.turn != MouseInput.Turn.Black)
                        blackTimer.startTimer();
                }
            }
        });
        add(restart);
        add(pouse);
        add(resum);
    }

    public void paintPosytion(Graphics2D g2d) {
        char tab[][] = ChessBoard.getInstance().getBord();
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j] != '0')
                    Piece.drawPieces(new Piece(tab[i][j], (int) leftPanel + (j * (int) chessFildWidth) + 7, 30 + (i * (int) chessFildWidth) + 7), g2d, (int) chessFildWidth);
            }
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        calculateSize();
        ChessBoard.drawBoard(g2d, (int) leftPanel, 30, (int) chessFildWidth);
        if (ANTIALIAS)
            g2d.setRenderingHints(hints);
        paintPosytion(g2d);
        //  if(null!=mouseListener.getSeletedPiece()){
        //     Piece.drawPieces(mouseListener.getSeletedPiece(),g2d,(int)chessFildWidth);
        // }
        // g2d.drawString(String.valueOf(mouseListener.getMousePosX())+"/"+String.valueOf(mouseListener.getMousePosY()),20,20);
        g2d.setColor(Color.WHITE);
       // g2d.drawString(String.valueOf(mouseInput.mousePosX) + "/" + String.valueOf(mouseInput.mousePosY), 20, 40);
        char sys = '0';
        if (null != mouseInput.selected)
            sys = mouseInput.selected.getSymbol();
        else
            sys = '0';


       // g2d.drawString(String.valueOf(sys), 20, 60);
//        if (mouseInput.mousePosY < 8 && mouseInput.mousePosX < 8)
//            g2d.drawString(String.valueOf(ChessBoard.getInstance().getBord()[mouseInput.mousePosY][mouseInput.mousePosX]), 20, 80);
//        else
//            g2d.drawString(String.valueOf('0'), 20, 80);
//
//        if (MouseInput.turn == MouseInput.Turn.Black)
//            g2d.drawString(String.valueOf("Black"), 20, 100);
//        else
//            g2d.drawString(String.valueOf("White"), 20, 100);

        if (mouseInput.selected != null) {
            Piece.drawPieces(mouseInput.selected, g2d, (int) chessFildWidth, mouseInput.currentMousePos);
            System.out.println("dragged " + mouseInput.currentMousePos.getX());
        }

        interfaceElements();
        if (!legalMoves.isEmpty()) {
            for (Point p : legalMoves) {
                drawLegalMoves(g2d, p);
            }
        }
    }//

    void restartGame() {


        blackTimer.stopTimer();
        whiteTimer.stopTimer();
        setTime();
        ChessBoard.getInstance().setBord(ChessBoard.fenToTab("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"));
    }
    void resigne(MouseInput.Turn t){
        if(t== MouseInput.Turn.White)
            JOptionPane.showMessageDialog(this, "White surrendered the party to Black's victory!");
        else
            JOptionPane.showMessageDialog(this, "Black surrendered the party to White's victory!");
        restartGame();
    }
    public void interfaceElements() {
        int rightPanel = (int) (leftPanel + chessBoardWidth) + 20;
        int heigt = (int) (chessBoardWidth / 2);
        int width = widthOfWindow - (int) rightPanel - 40;
        int widthButton = width / 3;

        g2d.setColor(Color.black);
        g2d.fillRect(rightPanel, heigt - 70, width, 60);
        g2d.fillRect(rightPanel, heigt + 70, width, 60);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        int time = whiteTimer.getCount();

        restart.setBounds(rightPanel, heigt + 10, widthButton, 40);
        pouse.setBounds(rightPanel + widthButton, heigt + 10, widthButton, 40);
        resum.setBounds(rightPanel + 2 * widthButton, heigt + 10, widthButton, 40);
        whiteSurender.setBounds(rightPanel, heigt + 70 + 70, width, 40);
        blackSurender.setBounds(rightPanel, heigt - 70 - 55, width, 40);
        g2d.drawString(String.valueOf(timeToString(whiteTimer.getCount())), rightPanel + (width / 2) - 50, heigt - 25);
        g2d.drawString(String.valueOf(timeToString(blackTimer.getCount())), rightPanel + (width / 2) - 50, heigt + 115);
    }

    public String timeToString(int time) {
        int minutes = time / 60;
        int seconds = time - minutes * 60;
        String minutesString = minutes + ":";
        String secondsString = String.valueOf(seconds);
        if (minutes < 10)
            minutesString = "0" + minutesString;
        if (seconds < 10)
            secondsString = "0" + secondsString;
        String out = minutesString + secondsString;
        return out;
    }

    public void drawLegalMoves(Graphics2D g2d, Point p) {
        g2d.setColor(ChessBoard.helpPointer);
        int ovalSize = (int) chessFildWidth / 3;
        int x = (int) leftPanel + (p.x * (int) chessFildWidth) + 7;
        int y = 30 + ((p.y * (int) chessFildWidth) + 7);
        g2d.fillOval(x, y, ovalSize, ovalSize);
    }

}
