import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class DotComponent extends JComponent
{
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HEIGHT = 450;

    private java.util.List<Dot> balls = new ArrayList<>();
    /**
     * Add a ball to the panel.
     * @param b the ball to add
     */
    public void add(Dot b)
    {
        balls.add(b);
        if(Math.random() > 0.99) b.stable = true;
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        for (Dot b : balls)
        {
            float a1 = (float) (b.color*10);
            float a2 = (float) (1);
            float a3 = (float) (1);

            int cnt = 1;
            double clr = 0;
            if(BallRunnable.colorShake){
                if(!b.stable){
                    for(Dot b2 : balls){
                        //b.color = b.color + b2.color/(100000*(Math.sqrt((b.x - b2.x)*(b.x - b2.x) + (b.y - b2.y)*(b.y - b2.y))/450)*balls.size());
                        //b.color = b.color + b2.color/100000*balls.size();
                        if(Math.sqrt((b.x - b2.x)*(b.x - b2.x) + (b.y - b2.y)*(b.y - b2.y)) < 50){
                           cnt++;
                           clr = clr + b2.color;
                        }

                        if(cnt>0){
                           b.color = (b.color + clr)/cnt;
                        }
                    }
                }
            }


            g2.setColor(Color.getHSBColor(a1,a2,a3));
            g2.fill(b.getShape());
        }


    }

    public Dimension getPreferredSize() { return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
}
