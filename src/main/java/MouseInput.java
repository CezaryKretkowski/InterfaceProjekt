import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {
    public enum Turn {
        Black,
        White
    }

    ;
    RenderComponets renderComponets;
    public int mousePosX;
    public int mousePosY;
    public Point currentMousePos;
    public Piece selected = null;
    public static Turn turn = Turn.White;
    public boolean pouse = false;
    private String fen;


    void calculateMouseCoords(int posX, int posY) {
        if (posX > renderComponets.boardPosX.x && posY < renderComponets.boardPosX.y)
            mousePosX = (posX - renderComponets.boardPosX.x) / (int) renderComponets.chessFildWidth;
        else
            mousePosX = 8;
        if (posY > renderComponets.boardPosy.x && posY < renderComponets.boardPosy.y)
            mousePosY = (posY - renderComponets.boardPosy.x) / (int) renderComponets.chessFildWidth;
        else
            mousePosY = 8;
    }

    public MouseInput(RenderComponets r) {
        renderComponets = r;
        currentMousePos = new Point();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        fen = ChessBoard.tabToFen(ChessBoard.getInstance().getBord());
        if (!pouse) {
            if (mousePosX < 8 && 8 > mousePosY) {
                selected = ChessBoard.getInstance().getPiece(mousePosX, mousePosY);
                if (null != selected && selected.getColor() == turn) {
                    selected.move(ChessBoard.getInstance().getBord());
                    renderComponets.setLegalMoves(selected.legalsMoves);
                    for (Point p : selected.legalsMoves) {
                        System.out.println(p.x + " " + p.y);
                    }
                    ChessBoard.getInstance().clearPieces(mousePosX, mousePosY);
                    selected.setKords(mousePosX, mousePosY);

                } else
                    selected = null;

            } else
                selected = null;


            renderComponets.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!pouse) {
            calculateMouseCoords(e.getX(), e.getY());
            if (selected != null) {
                if (mousePosX < 8 && 8 > mousePosY) {
                    selected.setKords(mousePosX, mousePosY);
                    ChessBoard.getInstance().setPiece(selected, mousePosX, mousePosY);
                    if (!fen.equals(ChessBoard.tabToFen(ChessBoard.getInstance().getBord()))) {
                        turn = turn == Turn.White ? Turn.Black : Turn.White;
                        if (turn == Turn.White) {
                            renderComponets.whiteTimer.stopTimer();
                            renderComponets.blackTimer.startTimer();
                        } else {
                            renderComponets.whiteTimer.startTimer();
                            renderComponets.blackTimer.stopTimer();
                        }
                    }
                    selected = null;
                } else {
                    ChessBoard.getInstance().setPiece(selected, selected.getKords().x, selected.getKords().y);
                    selected = null;
                }
            }
            renderComponets.repaint();
        }


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currentMousePos.x = e.getX();
        currentMousePos.y = e.getY();
        renderComponets.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        currentMousePos.x = e.getX();
        currentMousePos.y = e.getY();
        calculateMouseCoords(e.getX(), e.getY());
        renderComponets.repaint();
    }
}
