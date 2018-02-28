import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Dot
{
    private static final int XSIZE = 15;
    private static final int YSIZE = 15;
    public double x = Math.random()*450;
    public double y = Math.random()*450;
    public double color = Math.random()*1000;
    public boolean stable = false;
    private double dx = 1;
    private double dy = 1;


    /**
     Moves the ball to the next position, reversing direction
     if it hits one of the edges
     */
    public void move(Rectangle2D bounds)
    {
        if(Math.random()>0.5)x += dx; else x -= dx;
        if(Math.random()>0.5)y += dy; else y -= dy;
        if (x < bounds.getMinX())
        {
            x = bounds.getMinX();
            dx = -dx;
        }
        if (x + XSIZE >= bounds.getMaxX())
        {
            x = bounds.getMaxX() - XSIZE;
            dx = -dx;
        }
        if (y < bounds.getMinY())
        {
            y = bounds.getMinY();
            dy = -dy;
        }
        if (y + YSIZE >= bounds.getMaxY())
        {
            y = bounds.getMaxY() - YSIZE;
            dy = -dy;
        }


    }

    /**
     Gets the shape of the ball at its current position.
     */
    public Ellipse2D getShape()
    {
        if(this.stable){
            return new Ellipse2D.Double(x, y, XSIZE*2, YSIZE*2);
        }
        else
        return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
    }
}
