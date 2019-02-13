package Code;

public class RegularPolygon 
{
    float[] Polygon;
    CircleShape c;
   
    public RegularPolygon(float x, float y, int NumSides, float Radius)
    {
        c=new CircleShape(x, y, Radius, NumSides, 0, false); 
        Polygon = c.getArray();
    }
    
    public float[] getArray()
    {   
        return Polygon;
    }
    
    public void TransformArrayX(float x)
    {      
        c.TransformArrayX(x);
    }

    public void TransformArrayY(float y)
    {
        c.TransformArrayY(y);
    }

    public void TransformArrayZ(float z)
    {
        c.TransformArrayZ(z);
    }
    
    
}
