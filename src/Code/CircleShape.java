package Code;

import static org.lwjgl.glfw.GLFW.GLFW_STICKY_KEYS;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

public class CircleShape 
{
    private int x;
    
    private int a;
    private int rn;

    float[] array;
    double ConversionFactor = Math.PI/180;
    
    
    public float[] CreateCircleP(float xCenter, float yCenter, float r, float percision, float zdepth)
    {
        double degreesChange = 360/percision;  
        double radiansChange = degreesChange*ConversionFactor;
        
        float[] tarray = new float[(int) (percision*9)];
        array = new float[(int) (percision)*18];
        
        for(x=0; x<percision ;++x)
        {
            float x1= xCenter+(float) Math.cos(radiansChange*(x+1))*r;
            float y1 = yCenter+(float) Math.sin(radiansChange*(x+1))*r;
            tarray[3*(x+1)] = x1;
            tarray[3*(x+1)+1] = y1;
            tarray[3*(x+1)+2] = zdepth;
        }
        
        for(int y=0; y<percision; ++y)
        {
            array[18*y]= xCenter;
            array[18*y+1]= yCenter;
            array[18*y+2]= zdepth;
            array[18*y+3]= tarray[6*y];
            array[18*y+4]= tarray[6*y+1];
            array[18*y+5]= tarray[6*y+2];
            array[18*y+6]= tarray[6*y+3];
            array[18*y+7]= tarray[6*y+4];
            array[18*y+8]= tarray[6*y+5];
            
            PrintFloatArray(array);
  
        }   
        
        for(int y=0; y<percision; ++y){
            array[18*y+9]= xCenter;
            array[18*y+10]= yCenter;
            array[18*y+11]= zdepth;
            array[18*y+12]= tarray[6*y+3];
            array[18*y+13]= tarray[6*y+4];
            array[18*y+14]= tarray[6*y+5];
            array[18*y+15]= tarray[6*y+6];
            array[18*y+16]= tarray[6*y+7];
            array[18*y+17]= tarray[6*y+8];
            
            PrintFloatArray(array);
            
        }
        
        return array;
        
    }
    
    public float[] CreateCircleNP(float xCenter, float yCenter, float r, float zdepth)
    {
        float percision = 500*r;
        return CreateCircleP(xCenter, yCenter, r, percision, zdepth);
    }
    
    public float[] getCircleArray()
    {
        return array;
    }



    
    public void PrintFloatArray(float[] array)
    {
        for(rn=0; rn<6;++rn)
        {
            System.out.println(array[3*rn] + ", " + array[3*rn+1] + ", " + array[3*rn+2]);
            if(array[3*rn]==0)
            {
                if(array[3*rn+1]==0)
                {
                    if(array[3*rn+2]==0)
                    {
                        if(array[3*rn+3]==0)
                        {
                            if(array[3*rn+4]==0)
                            {
                                if(array[3*rn+5]==0)
                                {
                                    if(array[3*rn+6]==0)
                                    {
                                        if(array[3*rn+7]==0)
                                        {
                                            if(array[3*rn+8]==0)
                                            {
                                                System.out.println(3*rn + ", " + 3*rn+1  + ", " + 3*rn+2  + ", " + 3*rn+3  + ", " + 3*rn+4  + ", " + 3*rn+5  + ", " + 3*rn+6  + ", " + 3*rn+7  + ", " + 3*rn+8);
                                               // glfwSetWindowShouldClose(main.window, true); // We will detect this in the rendering loop
                                            } 
                                        } 
                                    } 
                                } 
                            } 
                       } 
                    } 
                }
            }
        }
    }
}
