import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
    private RenderingHints hints;
    public Timer whiteTimer;
    public Timer blackTimer;

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

    public RenderComponets() {

        this.ANTIALIAS = true;
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
        whiteTimer=new Timer(this);
        //whiteTimer.startTimer();
        blackTimer=new Timer(this);
      //  blackTimer.startTimer();
        this.setAntiAliasing();
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
        ChessBoard.drawBoard(g2d, (int) leftPanel, 30, (int) chessFildWidth, new Color(0.0f, 0.4f, 0.0f), Color.WHITE);
        if (ANTIALIAS)
            g2d.setRenderingHints(hints);
        paintPosytion(g2d);
        //  if(null!=mouseListener.getSeletedPiece()){
        //     Piece.drawPieces(mouseListener.getSeletedPiece(),g2d,(int)chessFildWidth);
        // }
        // g2d.drawString(String.valueOf(mouseListener.getMousePosX())+"/"+String.valueOf(mouseListener.getMousePosY()),20,20);
        g2d.setColor(Color.WHITE);
        g2d.drawString(String.valueOf(mouseInput.mousePosX) + "/" + String.valueOf(mouseInput.mousePosY), 20, 40);
        char sys = '0';
        if (null != mouseInput.selected)
            sys = mouseInput.selected.getSymbol();
        else
            sys = '0';


        g2d.drawString(String.valueOf(sys), 20, 60);
        if (mouseInput.mousePosY < 8 && mouseInput.mousePosX < 8)
            g2d.drawString(String.valueOf(ChessBoard.getInstance().getBord()[mouseInput.mousePosY][mouseInput.mousePosX]), 20, 80);
        else
            g2d.drawString(String.valueOf('0'), 20, 80);

        if(MouseInput.turn== MouseInput.Turn.Black)
            g2d.drawString(String.valueOf("Black"), 20, 100);
        else
            g2d.drawString(String.valueOf("White"), 20, 100);

        if(mouseInput.selected!=null) {
            Piece.drawPieces(mouseInput.selected, g2d,(int)chessFildWidth ,mouseInput.currentMousePos);
            System.out.println("dragged "+ mouseInput.currentMousePos.getX());
        }

        interfaceElements();
    }//

    public void interfaceElements(){
        int rightPanel=(int)(leftPanel+chessBoardWidth)+20;
        int heigt=(int) (chessBoardWidth/2);
        int width=widthOfWindow-(int)rightPanel-40;

        g2d.setColor(Color.black);
        g2d.fillRect(rightPanel,heigt-70,width,60);
        g2d.fillRect(rightPanel,heigt+70,width,60);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        int time=whiteTimer.getCount();

        g2d.drawString(String.valueOf(timeToString(whiteTimer.getCount())), rightPanel+(width/2)-50, heigt-25);
        g2d.drawString(String.valueOf(timeToString(blackTimer.getCount())), rightPanel+(width/2)-50, heigt+115);
    }
    public String timeToString(int time){
        int minutes=time/60;
        int seconds=time-minutes*60;
        String minutesString=minutes+":";
        String secondsString=String.valueOf(seconds);
        if(minutes<10)
            minutesString="0"+minutesString;
        if(seconds<10)
            secondsString="0"+secondsString;
        String out=minutesString+secondsString;
        return out;
    }

}
