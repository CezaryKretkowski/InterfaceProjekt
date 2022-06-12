import java.awt.*;

public class Pawn extends Piece {

    public Pawn(char symbol, int x, int y) {
        super(symbol, x, y);
    }
    public Pawn(char symbol) {
        super(symbol);
    }

    @Override
    public void move(char[][] board) {

        int kolor;
        int posX=getKords().x;
        int posY=getKords().y;


        if (posX - 1 >= 0 && posX <= 7 && posY >= 0 && posY <= 7) {
            if (color == MouseInput.turn.White) {

                if (board[posX - 1][posY] == '0')
                    legalsMoves.add(new Point(posY, posX - 1));

                if (posY + 1 < 8 && posX + 1 < 8 && posX - 1 >= 0&&posY-1>=0) {
                    Piece nextPieces0 = ChessBoard.getInstance().getPiece(posY - 1, posX + 1);
                    if (nextPieces0 != null && nextPieces0.color != this.color){
                        legalsMoves.add(new Point(posY + 1, posX - 1));

                    }
                }
                if (posY - 1 >= 0 && posX + 1 < 8 && posX - 1 >= 0) {
                    Piece nextPieces1 = ChessBoard.getInstance().getPiece(posY - 1, posX - 1);
                    if (nextPieces1 != null && nextPieces1.color != this.color) {
                        legalsMoves.add(new Point(posY - 1, posX - 1));
                        System .out.println("Dodano"+ posX+" "+posY);
                    }

                }
                if (posX == 6 && board[posX - 2][posY] == '0') {
                    legalsMoves.add(new Point(posY, posX - 2));
                }


            } else {

                if (board[posX + 1][posY] == '0')
                    legalsMoves.add(new Point(posY, posX + 1));

                if (posY + 1 < 8 && posX + 1 < 8 && posX - 1 >= 0&&posY-1>=0) {
                    Piece nextPieces0 = ChessBoard.getInstance().getPiece(posY - 1, posX + 1);
                    if (nextPieces0 != null && nextPieces0.color != this.color){
                        legalsMoves.add(new Point(posY + 1, posX + 1));

                    }
                }
                if (posY - 1 >= 0 && posX + 1 < 8 && posX - 1 >= 0) {
                    Piece nextPieces1 = ChessBoard.getInstance().getPiece(posY - 1, posX - 1);
                    if (nextPieces1 != null && nextPieces1.color != this.color) {
                        legalsMoves.add(new Point(posY - 1, posX + 1));
                        System .out.println("Dodano"+ posX+" "+posY);
                    }

                }
                if (posX == 1 && board[posX + 2][posY] == '0') {
                    legalsMoves.add(new Point(posY, posX + 2));
                }

            }
        }
    }
}
