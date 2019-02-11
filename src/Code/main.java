package Code;

import java.awt.Dimension;
import java.awt.Toolkit;
import org.lwjgl.glfw.*;
import org.lwjgl.system.*;
import org.lwjgl.opengl.*;

import java.nio.*;
import org.lwjgl.BufferUtils;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class main 
{


    public static long window; //window handle
    public static long windowPopup;
    private int progHandle; //the handle to the shader program
    private int vertexBuffer; // VBO
    private int[] bufferHandleArray; // BHA
    
    private float PaddleOneHeight = 0f;
    private float PaddleTwoHeight = 0f;
    
    private int a = 0;
    private int d1 = 0;
    private int d2 = 0;
    private int d3 = 0;
    private int d4 = 0;
    private float[] v1={-1, 0.2f, 0};
    private float[] v2={-1, -0.2f, 0};
    private float[] v3={-0.95f, -0.2f, 0};
    private float[] v4={-0.95f, 0.2f, 0};
    private float[] v5={1, 0.2f, 0};
    private float[] v6={1, -0.2f, 0};
    private float[] v7={0.95f, -0.2f, 0};
    private float[] v8={0.95f, 0.2f, 0};

    private float[] circle = addCirclePS(0.5f, 0.5f, 0.05f, 30, 0);
    private float[] circleC = addCirclePS(0.5f, 0.5f, 0.05f, 3, 0);
    private float[] circle1 = addCircleP(0.25f, 0.25f, 0.1f, 0);
    
   

    
    
    
    
  
    
    float verts2[] = 
    {
        -0.5f, 0.5f, 0f,
        0.5f, 0.5f, 0f,
        0.5f, -0.5f, 0f,
    };
    



    


    private void teardown(long[] windowArray){
        for(int k = 0; k<windowArray.length; ++k){
            // Free the window callbacks and destroy the window
        glfwFreeCallbacks(windowArray[k]);
        glfwDestroyWindow(windowArray[k]);
        }
        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
        
    }

    public void run() {
        createWindow(800, 800);
        long[] windowArray = {
            window
        };
        
        init(windowArray);
        loop();
        teardown(windowArray);

    }

    private void createWindow(int x, int y){
        GLFWErrorCallback.createPrint(System.err).set();

       
        if ( !glfwInit() )
            throw new IllegalStateException("GLFW didn't init");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_SAMPLES, 4);
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

      
        window = glfwCreateWindow(x, y, "LWJGL PONG", NULL, NULL);
        
        
        if ( window == NULL )
            throw new RuntimeException("GLFW didn't make the window");

        
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
            {
                glfwSetWindowShouldClose(window, true); 
            }
               
            
            if (key == GLFW_KEY_K)
            {
                if(d1==0)
                {
                    if(v6[1]+PaddleTwoHeight > -1)
                    {
                        PaddleTwoHeight=PaddleTwoHeight-0.1f; 
                    }
                    
                    d1=1;
                    
                }else
                {
                    d1=0;
                }
             
                System.out.println("EPIC!");
                
            }
            
            if (key == GLFW_KEY_I)
            {
                if(d2==0)
                {
                    if(v5[1]+PaddleTwoHeight < 1)
                    {
                        PaddleTwoHeight=PaddleTwoHeight+0.1f; 
                    }
                    
                    d2=1;
                    
                }else                   
                {
                    d2=0;
                }
                
                System.out.println("EPICER!");
                
            }
            
            if (key == GLFW_KEY_W)
            {
                if(d3==0)
                {
                    if(v1[1]+PaddleOneHeight < 1)
                    {
                        PaddleOneHeight=PaddleOneHeight+0.1f; 
                    }
                    
                    d3=1;
                    
                }else
                {
                    d3=0;
                }
                
                System.out.println("EPICERER!");
                
            }
            
            if (key == GLFW_KEY_S)
            {
                if(d4==0)
                {
                    if(v2[1]+PaddleOneHeight > -1)
                    {
                    PaddleOneHeight=PaddleOneHeight-0.1f; 
                    }
                    
                    d4=1;
                    
                }else
                    
                {
                    d4=0;
                }
                
                System.out.println("EPICEREREST!");
                
            }
            
            if (key == GLFW_KEY_O && action == GLFW_RELEASE)
            {
     
                if(a==0)
                {
                    a=1;
                }else if(a==1)
                {
                    a=2;
                    
                }else if(a==2)
                {
                    a=3;
                }else if(a==3)
                {
                    a=4;
                }else
                {
                    a=0;
                }
            }
            
    
             
        });

        
        try ( MemoryStack stack = stackPush() ) 
        {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

     
            glfwGetWindowSize(window, pWidth, pHeight);

      
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

     
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    private void init(long[] windowC) 
    {         
        for(int k=0; k<windowC.length; ++k)
        {
            
        }
    }
    

    private void loop() {
        
        while ( !glfwWindowShouldClose(window) ) 
        {
            
            float[] c = {0, 0, 1, 0};

            float verts[] = 
            {
                v1[0], v1[1]+PaddleOneHeight, v1[2],
                v2[0], v2[1]+PaddleOneHeight, v2[2],
                v3[0], v3[1]+PaddleOneHeight, v3[2],
                v1[0], v1[1]+PaddleOneHeight, v1[2],
                v3[0], v3[1]+PaddleOneHeight, v3[2],
                v4[0], v4[1]+PaddleOneHeight, v4[2],
                v5[0], v5[1]+PaddleTwoHeight, v5[2],
                v6[0], v6[1]+PaddleTwoHeight, v6[2],
                v7[0], v7[1]+PaddleTwoHeight, v7[2],
                v5[0], v5[1]+PaddleTwoHeight, v5[2],
                v7[0], v7[1]+PaddleTwoHeight, v7[2],
                v8[0], v8[1]+PaddleTwoHeight, v8[2]
            };


          if(a==0)
          {
            bufferHandleArray = GenericBuffer(c, verts, "shader.frag", "shader.vert", vertexBuffer, progHandle, true); // DA Tris
            GenericBufferL(0, verts.length, GL11.GL_TRIANGLES, bufferHandleArray[0], bufferHandleArray[1], true, 0); // DA Tris

          }else if(a==1)
          {
            bufferHandleArray = GenericBuffer(c, verts2, "shader.frag", "shader.vert", vertexBuffer, progHandle, false); // Non DA Tris
            GenericBufferL(0, verts2.length, GL11.GL_TRIANGLES, bufferHandleArray[0], bufferHandleArray[1], false, 0); // Non DA Tris
          }else if(a==2)
          {

            bufferHandleArray = GenericBuffer(c, circle, "shader.frag", "shader.vert", vertexBuffer, progHandle, true); // DA Tris
            GenericBufferL(0, circle.length, GL11.GL_TRIANGLES, bufferHandleArray[0], bufferHandleArray[1], true, 0); // DA Tris
          }else if(a==3)
          {
               bufferHandleArray = GenericBuffer(c, circle1, "shader.frag", "shader.vert", vertexBuffer, progHandle, true); // DA Tris
            GenericBufferL(0, circle1.length, GL11.GL_TRIANGLES, bufferHandleArray[0], bufferHandleArray[1], true, 0); // DA Tris
          }else if(a==4)
          {
            bufferHandleArray = GenericBuffer(c, circleC, "shader.frag", "shader.vert", vertexBuffer, progHandle, true); // DA Tris
            GenericBufferL(0, circleC.length, GL11.GL_TRIANGLES, bufferHandleArray[0], bufferHandleArray[1], true, 0); // DA Tris
          }

          glfwSwapBuffers(window);

            
        }
    }

    public static void main(String[] args) 
    {
        new main().run();
    }
    
    public int[] GenericBuffer(float[] RGBNormalized, float[] verts, String frag, String vert, int buffer, int handle, boolean DA)
    {
        
        //call first
        GL.createCapabilities();
        
       
       
        GL11.glClearColor(RGBNormalized[0], RGBNormalized[1], RGBNormalized[2], RGBNormalized[3]);
       
        CheckCollisionPaddle1();
        CheckCollisionPaddle2();
        
       
        int vertextArrayID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vertextArrayID);

        if(DA==true)
        {
       
            buffer = GL15.glGenBuffers();
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, buffer);
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verts, GL15.GL_STATIC_DRAW);
        }else
        {      
            buffer = GL15.glGenBuffers();
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer);
            GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, verts, GL15.GL_STATIC_DRAW);

        }
        


        //<<<<<<<<<<<<SHADERS>>>>>>>>>>>>>>
        String[] shaderBufferArr = GlUtil.readinShaders(frag, vert); //read shaders into string array
        int vertShaderID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        int fragShaderID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragShaderID, shaderBufferArr[0]);
        GL20.glShaderSource(vertShaderID, shaderBufferArr[1]);

        GL20.glCompileShader(fragShaderID);
        GL20.glCompileShader(vertShaderID);


        handle = GL20.glCreateProgram();
        GL20.glAttachShader(handle, fragShaderID);
        GL20.glAttachShader(handle, vertShaderID);
        GL20.glLinkProgram(handle);

        GL20.glDetachShader(handle, vertShaderID);
        GL20.glDetachShader(handle, fragShaderID);


        GL20.glDeleteShader(vertShaderID);
        GL20.glDeleteShader(fragShaderID);
        int[] a = {buffer, handle};
        
        return a;
        
}

    public void GenericBufferL(int arrayStart, int arrayLength, int DrawSetting, int buffer, int handle, boolean DA, int indicesCount)
    {
        glfwPollEvents();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL20.glUseProgram(handle);
        GL20.glEnableVertexAttribArray(0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, buffer);
        
        if(DA==true)
        {     
            GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
            GL11.glDrawArrays(DrawSetting, arrayStart, arrayLength); 
            
        }else
        {
            GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
            GL11.glDrawElements(GL11.GL_TRIANGLES, 6 , GL11.GL_UNSIGNED_BYTE , indicesCount);
        }
        
        GL20.glDisableVertexAttribArray(0);
         
    }
    
    public void CheckCollisionPaddle1()
    {
        
    }
    
    public void CheckCollisionPaddle2()
    {
        
    }
    
    
    public float[] addCirclePS(float xCenter, float yCenter, float r, float percision, float zdepth)
    {
        CircleShape c = new CircleShape();
        float[] testCircle = c.CreateCircleP(xCenter, yCenter, r, percision, zdepth);
        
        return testCircle;
    }
    
    public float[] addCircleP(float xCenter, float yCenter, float r, float zdepth)
    {
     CircleShape c = new CircleShape();
        float[] testCircle = c.CreateCircleNP(xCenter, yCenter, r, zdepth);

        return testCircle;
    }
    
       
}