package humphries.hwthree;

import java.awt.*;
import javax.swing.*;

/**
 * A polynomial calculator
 * @author TCSS 342
 * @version 1.0
 */

public class PolyCalc {

    private PolyCalcEngine engine;
    private PolyCalcUI gui;

    public PolyCalc() {
        //Construct the application
        engine = new PolyCalcEngine();
        gui = new PolyCalcUI(engine);
        JFrame frame = gui.getFrame();

        //Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
        gui.redisplay();
    }

    public static void main(String[] args) {
        /*
           To test your code without using the GUI,
           you might want to write code like this.
           The output will appear on the terminal window.
        */

        Polynomial a = new Polynomial();
        a.zeroPolynomial();
        System.out.println ( "a = " + a.print() );
        a.insertTerm(-3, 5);
        System.out.println ( "a = " + a.print() );
        a.insertTerm(2, 1);
        System.out.println ( "a = " + a.print() );

        a.insertTerm(2,2);
        a.insertTerm(5,3);
        a.insertTerm(87,10);
        a.insertTerm(3, 5);
        a.insertTerm(5, 5);
        a.insertTerm(-5, 6);
        a.insertTerm(-5, 7);

        System.out.println("a = " + a.print());
        a.negate();
        System.out.println("new a = "+a.print());
        Polynomial b = new Polynomial();
        b.insertTerm(2, 6);
        b.insertTerm(5,9);
        a.plus(b);
        System.out.println(b.print());
        System.out.println("addition a = " + a.print());
        Polynomial c = new Polynomial();
        c.insertTerm(3,5);
        c.insertTerm(7,6);
        c.insertTerm(1,1);
        a.minus(c);
        System.out.println(c.print());
        System.out.println(a.print());
        System.out.println("derivative a= " +a.derivative().print());

        new PolyCalc();
    }

}