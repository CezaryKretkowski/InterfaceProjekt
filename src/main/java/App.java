import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class App extends JFrame {
    public JFrame window;
    private JPanel view;
    public  JMenuBar menuBar;
    public JMenu m;
    public JMenuItem settings;
    public Settings s;
    void addSubMenu(){
        settings=new JMenuItem("settings");
        settings.addActionListener(s);
        m.add(settings);
    }

    public App(){
        s=new Settings();
        window=new JFrame();
        menuBar=new JMenuBar();
        window.setBounds(100, 100, 800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view=new RenderComponets();
        m=new JMenu("Menu");
        window.setJMenuBar(menuBar);
        addSubMenu();
        menuBar.add(m);
        window.setContentPane(view);
    }
    public static void main( String[] args )
    {
        SwingUtilities.invokeLater(() -> {
            LafManager.setTheme(new DarculaTheme());
            LafManager.install();

            try {
                App app = new App();
                app.window.setVisible(true);

            } catch (Exception e) {
                e.getStackTrace();
            }
        });
    }
}
