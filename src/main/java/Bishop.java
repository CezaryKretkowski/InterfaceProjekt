import java.awt.*;

public class Bishop extends Piece{
    public Bishop(char symbol, int x, int y) {
        super(symbol, x, y);
    }
    public  Bishop(char symbol){
        super(symbol);
    }

    @Override
    public void move(char[][] board) {
        move1(board);
        move2(board);
        move3(board);
        move4(board);
    }
    private void move1(char[][] board) {
        int posX=getKords().x;
        int posY=getKords().y;
        int i = posX+1 ;
        int j = posY+1;


        findmoves:while (i <= 7 && j <= 7) {

            Piece nextPieces0 = ChessBoard.getInstance().getPiece(j, i);
            if(nextPieces0 != null &&nextPieces0.color == this.color) break findmoves;
            if (nextPieces0 == null )
                legalsMoves.add(new Point(j,i));
            if(nextPieces0 != null &&nextPieces0.color != this.color) {
                legalsMoves.add(new Point(j,i));
                break findmoves;
            }

            i++;
            j++;
        }
    }

    private void move2(char[][] board) {

        int posX=getKords().x;
        int posY=getKords().y;
        int i = posX -1;
        int j = posY-1;

        findmoves:while (i >= 0 && j >= 0) {

            Piece nextPieces0 = ChessBoard.getInstance().getPiece(j, i);
            if(nextPieces0 != null &&nextPieces0.color == this.color) break findmoves;
            if (nextPieces0 == null )
                legalsMoves.add(new Point(j,i));
            if(nextPieces0 != null &&nextPieces0.color != this.color) {
                legalsMoves.add(new Point(j,i));
                break findmoves;
            }


            i--;
            j--;
        }
    }
    private void move3(char[][] board) {
        int posX=getKords().x;
        int posY=getKords().y;
        int i = posX +1;
        int j = posY-1;
        System.out.println((posX-1)+""+(posY-1));
        findmoves:while (i <= 7 && j >= 0) {

            Piece nextPieces0 = ChessBoard.getInstance().getPiece(j, i);
            if(nextPieces0 != null &&nextPieces0.color == this.color) break findmoves;
            if (nextPieces0 == null )
                legalsMoves.add(new Point(j,i));
            if(nextPieces0 != null &&nextPieces0.color != this.color) {
                legalsMoves.add(new Point(j,i));
                break findmoves;
            }

            i++;
            j--;
        }
    }
    private void move4(char[][] board) {
        int posX=getKords().x;
        int posY=getKords().y;
        int i = posX -1;
        int j = posY+1;

        findmoves:while (i >= 0 && j <= 7) {

            Piece nextPieces0 = ChessBoard.getInstance().getPiece(j, i);
            if(nextPieces0 != null &&nextPieces0.color == this.color) break findmoves;
            if (nextPieces0 == null )
                legalsMoves.add(new Point(j,i));
            if(nextPieces0 != null &&nextPieces0.color != this.color) {
                legalsMoves.add(new Point(j,i));
                break findmoves;
            }

            i--;
            j++;
        }
    }
}
