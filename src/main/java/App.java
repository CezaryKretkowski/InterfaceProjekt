import javax.swing.*;

public class App extends JFrame {
    private JPanel view;
    public App(){
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view=new RenderComponets();
        this.setContentPane(view);
    }
    public static void main( String[] args )
    {
        try {
            App window = new App();
            window.setVisible(true);

        }
        catch(Exception e) {
            e.getStackTrace();
        }
    }
}
