package net.sorax.engine.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.lwjgl.util.vector.Vector3f;

public class Shader {
	
	private int program;
	
	public Shader(String vertexShader, String fragmentShader) {
		program = glCreateProgram();
		
		if(program == GL_FALSE) {
			System.out.println("Shader program error");
			System.exit(1);
		}
		
		createShader(loadShader(vertexShader), GL_VERTEX_SHADER);
		createShader(loadShader(fragmentShader), GL_FRAGMENT_SHADER);
		
		glLinkProgram(program);
		glValidateProgram(program);
	}
	
	public void createShader(String source, int type) {
		int shader = glCreateShader(type);
		if(shader == GL_FALSE) {
			System.err.println("Shader error : " + shader);
			System.exit(1);
		}
		glShaderSource(shader, source);
		glCompileShader(shader);
		if(glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println(glGetShaderInfoLog(shader, 2048) + "- " + source);
			System.exit(1);
		}
		glAttachShader(program, shader);
	}
	
	public String loadShader(String shader) {
		String r = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(Shader.class.getResourceAsStream(shader)));
			String buffer = "";
			while((buffer = br.readLine()) != null) {
				r += buffer + "\n";
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public void setUniform(String name, float v) {
		glUniform1f(glGetUniformLocation(program, name), v);
	}
	
	public void setUniform(String name, Vector3f v) {
		glUniform3f(glGetUniformLocation(program, name), v.getX(), v.getY(), v.getZ());
	}
	
	public void bind() {
		glUseProgram(program);
	}
	
	public void unBind() {
		glUseProgram(0);
	}
}
