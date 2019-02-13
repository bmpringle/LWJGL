package Code;

import java.util.ArrayList;

public class VertexCollection 
{
    ArrayList<Float> ArrayFloat = new ArrayList();
    float[] VertexArray;
    
    public void addVertex(float v)
    {
        ArrayFloat.add(v);
    }
    
    public void addVertexArray(float[] v)
    {
        for(int i = 0; i<v.length; ++i)
        {
          ArrayFloat.add(v[i]);
        }
    }
    
    public float[] getVertexArray()
    {   
        VertexArray=new float[ArrayFloat.size()]; 
        for(int i=0; i<ArrayFloat.size(); ++i)
        {
        VertexArray[i]=ArrayFloat.get(i);
        }
        return VertexArray;    
    }
}
