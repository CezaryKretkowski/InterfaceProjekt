import java.awt.*;

public class ChessBoard {
    private static ChessBoard chessBoard_Instance = null;
    public static Color first= new Color(0.0f, 0.4f, 0.0f);
    public static Color second=Color.WHITE;
    public static Color helpPointer=Color.BLACK;
    public static ChessBoard getInstance() {
        if (chessBoard_Instance == null)
            chessBoard_Instance = new ChessBoard();

        return chessBoard_Instance;
    }

    private char[][] bord = {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {'0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
    };

    public char[][] getBord() {
        return bord;
    }

    public void setBord(char[][] bord) {
        this.bord = bord;
    }

    public static void drawBoard(Graphics2D g2d, int posytionX, int posytionY, int with,
                                 Color first, Color second) {

        int counter = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((counter & 1) == 0)
                    g2d.setColor(first);
                else
                    g2d.setColor(second);

                g2d.fillRect(posytionX + (with * j), posytionY + (with * i), with, with);
                counter++;

            }
            counter++;
        }

    }

    public static void drawBoard(Graphics2D g2d, int posytionX, int posytionY, int with) {

        int counter = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((counter & 1) == 0)
                    g2d.setColor(first);
                else
                    g2d.setColor(second);

                g2d.fillRect(posytionX + (with * j), posytionY + (with * i), with, with);
                counter++;

            }
            counter++;
        }

    }
    public Piece getPiece(int x, int y) {
        char symbol = bord[y][x];
        if (symbol != '0')
            return selectPieces(symbol,new Point(x, y));
        else
            return null;
    }
    public Piece selectPieces(char symbol,Point point){
        Piece p=new Piece(symbol);
        switch (symbol){
            case 'p':p=new Pawn(symbol, point.y, point.x); break;
            case 'P':p=new Pawn(symbol, point.y, point.x); break;
        }
        return p;
    }


    public void clearPieces(int x, int y) {

        bord[y][x] = '0';
    }

    public void setPiece(Piece p, int x, int y) {

        bord[y][x] = p.getSymbol();
    }
    public static char[][] fenToTab(String fen) {
        char piece;
        char board[][]=new char[8][8];
        int i = 0;
        int x = 0, y = 0;
        while (i < fen.length()) {
            piece = fen.charAt(i);

            if (piece == 'n' || piece == 'b' || piece == 'k' || piece == 'q' || piece == 'r' || piece == 'p'
                    || piece == 'N' || piece == 'B' || piece == 'K' || piece == 'Q' || piece == 'R' || piece == 'P') {
                board[y][x] = piece;

                x += 1;
            } else if (piece == '/') {
                y += 1;
                x = 0;
            } else if (piece >= '1' || piece <= '8') {
                int ofset = (int) piece - 48;
                for (int j = 0; j < ofset; j++) {
                    board[y][x] = '0';
                    x += 1;
                }
            }

            i++;
        }
        return board;

    }
    public static String tabToFen(char[][] board) {
        String fen1 = "";
        String fen;
        int licznik = 0;
        for (int w = 0; w < 8; w++) {
            for (int i = 0; i < 8; i++) {
                if (board[w][i] == '0') {
                    licznik = 0;
                    int j = i;
                    while (j < 8 && board[w][j] == '0') {
                        j++;
                        licznik++;
                    }
                    fen1 += (char) (licznik + '0');
                    i = i + licznik - 1;
                } else {
                    fen1 += board[w][i];
                }
            }
            fen1 += "/";
        }
        fen = fen1;
        return fen;

    }

}
