import javax.swing.*;

/**
 * @program: ks-parent
 * @description:
 * @author: pzy
 * @create: 2019-08-05 14:05
 **/
public class Login {
    private JPanel LoginLable;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        //frame.setContentPane(new Login().LoginLable);
        frame.setSize(1000,1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
