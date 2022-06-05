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
                if(board[posX - 1][posY+1] != '0'){
                   // if(ChessBoard.getInstance().getPiece(posX - 1,posY+1).getColor()!=color){
                 //       legalsMoves.add(new Point(posY+1, posX - 1));
                   // }
                }
                if(board[posX - 1][posY-1] != '0'){
                  //  if(ChessBoard.getInstance().getPiece(posX - 1,posY-1).getColor()!=color){
                        legalsMoves.add(new Point(posY-1, posX - 1));
                   // }
                }

                if(posX==6&&board[posX - 2][posY] == '0') {
                    legalsMoves.add(new Point(posY, posX - 2));
                }

            } else if (color ==  MouseInput.turn.Black) {

                if (board[posX + 1][posY] == '0')
                    legalsMoves.add(new Point(posY, posX + 1));
                if (board[posX + 1][posY+1] != '0'){

                }
                if (board[posX + 1][posY-1] != '0'){

                }

                if(posX==1&&board[posX + 2][posY] == '0') {
                    legalsMoves.add(new Point(posY, posX + 2));
                }

            }
        }
    }
}
