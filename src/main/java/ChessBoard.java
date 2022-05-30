import java.awt.*;

public class ChessBoard {
    private static ChessBoard chessBoard_Instance = null;
    public static Color first= new Color(0.0f, 0.4f, 0.0f);
    public static Color second=Color.WHITE;
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
            return new Piece(symbol);
        else
            return null;
    }

    public void clearPieces(int x, int y) {

        bord[y][x] = '0';
    }

    public void setPiece(Piece p, int x, int y) {

        bord[y][x] = p.getSymbol();
    }

}
