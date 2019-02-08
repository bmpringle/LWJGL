package Code;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class GlUtil 
{

    static String readFile(String path, Charset encoding)
    {
        Path currentDir = Paths.get("src/Code");
        currentDir = currentDir.toAbsolutePath();
        Path p = Paths.get(currentDir.toString(), path);
        try 
        {
            byte[] encoded = Files.readAllBytes(p);
            return new String(encoded, encoding);
        } catch (IOException e)
        {
            System.out.println("Something went wrong with the reading of the shader: " + e.getMessage());
        }
        return null;
    }
    static String[] readinShaders(String fragShader, String vertShader) 
    {
        String fragmentShaderBuffer = readFile(fragShader, Charset.defaultCharset());
        String vertexShaderBuffer = readFile(vertShader, Charset.defaultCharset());

        if (fragmentShaderBuffer != null && vertexShaderBuffer != null) 
        {
            String[] shaderArr = {fragmentShaderBuffer, vertexShaderBuffer};
            return shaderArr;
        }
        throw new UnsupportedOperationException("There was a problem reading in the shaders");
    }
}