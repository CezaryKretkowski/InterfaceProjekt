import java.awt.*;

public class King extends Piece  {
    public King(char symbol, int x, int y) {
        super(symbol, x, y);
    }
    public King(char symbol){
        super(symbol);
    }

    @Override
    public void move(char[][] board) {
        int posX=getKords().x;
        int posY=getKords().y;

        if (posX  >= 0 && posX<= 7 && posY >= 0 && posY <= 7) {

            if(posX-1 >= 0 ) {
                Piece nextPieces0 = ChessBoard.getInstance().getPiece( posY,posX - 1);
                if(board[posX - 1][posY] =='0'||(nextPieces0!=null&& nextPieces0.color!=this.color))
                    legalsMoves.add(new Point(posY , posX - 1));
            }

            if(posX+ 1<= 7) {
                Piece nextPieces1 = ChessBoard.getInstance().getPiece(posY,posX + 1 );
                if(board[posX + 1][posY] =='0'||(nextPieces1!=null&& nextPieces1.color!=this.color))
                    legalsMoves.add(new Point(posY , posX +1));
            }

            if(posY -1>= 0 ) {
                Piece nextPieces2 = ChessBoard.getInstance().getPiece(posY-1,posX  );
                if(board[posX ][posY-1] =='0'||(nextPieces2!=null&& nextPieces2.color!=this.color))
                    legalsMoves.add(new Point(posY-1 , posX ));
            }

            if(posY+ 1<= 7 ) {
                Piece nextPieces3 = ChessBoard.getInstance().getPiece(posY+1,posX  );
                if(board[posX ][posY+1] =='0'||(nextPieces3!=null&& nextPieces3.color!=this.color))
                    legalsMoves.add(new Point(posY+1 , posX ));
            }

            if(posX-1 >= 0 &&posY -1>= 0) {
                Piece nextPieces4 = ChessBoard.getInstance().getPiece(posY-1,posX - 1 );
                if(board[posX - 1][posY-1]=='0' ||(nextPieces4!=null&& nextPieces4.color!=this.color))
                    legalsMoves.add(new Point(posY-1 , posX - 1));
            }
            if(posX-1 >= 0 &&posY +1<= 7 ) {
                Piece nextPieces6 = ChessBoard.getInstance().getPiece(posY+1,posX - 1 );
                if(board[posX - 1][posY+1]=='0' ||(nextPieces6!=null&& nextPieces6.color!=this.color))
                    legalsMoves.add(new Point(posY+1 , posX -1));
            }

            if(posX+ 1<= 7 &&posY+ 1<= 7 ) {
                Piece nextPieces5 = ChessBoard.getInstance().getPiece(posY+1,posX + 1 );
                if(board[posX + 1][posY+1] =='0'||(nextPieces5!=null&& nextPieces5.color!=this.color))
                    legalsMoves.add(new Point(posY+1 , posX +1));
            }
            if(posX+ 1<= 7 &&posY -1>= 0 ) {
                Piece nextPieces10 = ChessBoard.getInstance().getPiece(posY-1,posX + 1 );
                if(board[posX + 1][posY-1] =='0'||(nextPieces10!=null&& nextPieces10.color!=this.color))
                    legalsMoves.add(new Point(posY-1 , posX +1));
            }

        }
    }
}
