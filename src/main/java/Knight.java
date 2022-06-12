import java.awt.*;

public class Knight extends Piece{
    public Knight(char symbol) {
        super(symbol);
    }
    public Knight(char symbol, int x, int y){
        super(symbol,x,y);
    }

    @Override
    public void move(char[][] board) {
        int posX=getKords().x;
        int posY=getKords().y;

        if (posY + 1 >= 0 && posX - 2 >= 0 && posX - 2 <= 7 && posY + 1 <= 7) {
            Piece nextPieces0 = ChessBoard.getInstance().getPiece(posX - 2, posY + 1);
            if (nextPieces0 == null || nextPieces0.color != this.color)
                legalsMoves.add(new Point(posY + 1, posX - 2));
        }
        if (posY - 1 >= 0 && posX - 2 >= 0 && posX - 2 <= 7 && posY - 1 <= 7) {
            Piece nextPieces0 = ChessBoard.getInstance().getPiece(posX - 2, posY - 1);
            if (nextPieces0 == null || nextPieces0.color != this.color)
                legalsMoves.add(new Point(posY - 1, posX - 2));
        }
        if (posY + 1 >= 0 && posX + 2 >= 0 && posX + 2 <= 7 && posY + 1 <= 7) {
            Piece nextPieces0 = ChessBoard.getInstance().getPiece(posX + 2, posY + 1);
            if (nextPieces0 == null || nextPieces0.color != this.color)
                legalsMoves.add(new Point(posY + 1, posX + 2));
        }
        if (posY - 1 >= 0 && posX + 2 >= 0 && posX + 2 <= 7 && posY - 1 <= 7) {
            Piece nextPieces0 = ChessBoard.getInstance().getPiece(posX + 2, posY - 1);
            if (nextPieces0 == null || nextPieces0.color != this.color)
                legalsMoves.add(new Point(posY - 1, posX + 2));
        }
        if (posY - 2 >= 0 && posX + 1 >= 0 && posX + 1 <= 7 && posY - 2 <= 7) {
            Piece nextPieces0 = ChessBoard.getInstance().getPiece(posX + 1, posY - 2);
            if (nextPieces0 == null || nextPieces0.color != this.color)
                legalsMoves.add(new Point(posY - 2, posX + 1));
        }
        if (posY + 2 >= 0 && posX + 1 >= 0 && posX + 1 <= 7 && posY + 2 <= 7) {
            Piece nextPieces0 = ChessBoard.getInstance().getPiece(posX + 1, posY + 2);
            if (nextPieces0 == null || nextPieces0.color != this.color)
                legalsMoves.add(new Point(posY + 2, posX + 1));
        }

        if (posY + 2 >= 0 && posX - 1 >= 0 && posX - 1 <= 7 && posY + 2 <= 7) {
            Piece nextPieces0 = ChessBoard.getInstance().getPiece(posX - 1, posY + 2);
            if (nextPieces0 == null || nextPieces0.color != this.color)
                legalsMoves.add(new Point(posY + 2, posX - 1));
        }
        if (posY - 2 >= 0 && posX - 1 >= 0 && posX - 1 <=7 && posY - 2 <= 7) {
            Piece nextPieces0 = ChessBoard.getInstance().getPiece(posX - 1, posY -2);
            if (nextPieces0 == null || nextPieces0.color != this.color)
                legalsMoves.add(new Point(posY - 2, posX - 1));
        }
    }
}
