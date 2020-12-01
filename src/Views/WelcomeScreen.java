package Views;

import javax.swing.*;
import java.awt.*;

/**
 * Sets global instantiation parameters for the welcome screens.
 *
 */
public abstract class WelcomeScreen extends JFrame {
    public WelcomeScreen(){
        super("University Administration System");
        super.setMinimumSize(new Dimension(600,400));
        super.setLocationRelativeTo(null);
    }
}
