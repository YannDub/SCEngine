package com.yanndub.scengine.graphics;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private final int width, height;
    private final String title;
    private long window;
    private boolean isCloseRequested;

    public Window(final String title, final int width, final int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.isCloseRequested = false;
    }

    public void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        this.window = glfwCreateWindow(this.width, this.height, title, NULL, NULL);
        if (this.window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        glfwSetKeyCallback(this.window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                this.close();
            }
        });

        try (final MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(this.window, pWidth, pHeight);

            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            if (vidMode == null) {
                throw new RuntimeException("No vidMode for the GLFW application");
            }

            glfwSetWindowPos(this.window, (vidMode.width() - pWidth.get(0)) / 2, vidMode.height() - pHeight.get(0) / 2);
        }

        glfwMakeContextCurrent(this.window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    public void loop() {
        GL.createCapabilities();

        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        while (!glfwWindowShouldClose(this.window)) {
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(this.window);

            glfwPollEvents();
        }
    }

    public void close() {
        glfwSetWindowShouldClose(window, true);
        this.isCloseRequested = true;
    }

    public boolean isCloseRequested() {
        return this.isCloseRequested;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
