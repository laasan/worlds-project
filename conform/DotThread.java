import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DotThread
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                JFrame frame = new BounceFrame();
                frame.setTitle("BounceThread");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * A runnable that animates a bouncing ball.
 */
class BallRunnable implements Runnable
{
    private Dot ball;
    private Component component;
    public static final int STEPS = 100000;
    public static final int DELAY = 5;
    public static boolean colorShake = false;
    /**
     * Constructs the runnable.
     * @param aBall the ball to bounce
     * @param aComponent the component in which the ball bounces
     */
    public BallRunnable(Dot aBall, Component aComponent)
    {
        ball = aBall;
        component = aComponent;
    }

    public void run()
    {
        try
        {
            for (int i = 1; i <= STEPS; i++)
            {
                ball.move(component.getBounds());
                component.repaint();
                Thread.sleep(DELAY);
            }
        }
        catch (InterruptedException e)
        {
        }
    }

}

/**
 * The frame with panel and buttons.
 */
class BounceFrame extends JFrame
{
    private DotComponent comp;

    /**
     * Constructs the frame with the component for showing the bouncing ball and Start and Close
     * buttons
     */
    public BounceFrame()
    {
        comp = new DotComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                addBall();
            }
        });

        addButton(buttonPanel, "Close", new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });
        addButton(buttonPanel, "color shake", new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
               BallRunnable.colorShake = true;
            }
        });
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    /**
     * Adds a button to a container.
     * @param c the container
     * @param title the button title
     * @param listener the action listener for the button
     */
    public void addButton(Container c, String title, ActionListener listener)
    {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    /**
     * Adds a bouncing ball to the canvas and starts a thread to make it bounce
     */
    public void addBall()
    {
        Dot b = new Dot();
        comp.add(b);
        Runnable r = new BallRunnable(b, comp);
        Thread t = new Thread(r);
        t.start();
    }
}