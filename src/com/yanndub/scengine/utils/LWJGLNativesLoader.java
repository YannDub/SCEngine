package com.yanndub.scengine.utils;

import java.io.File;
import java.lang.reflect.Field;


public class LWJGLNativesLoader {
	
	public static boolean isWindows() {
		String osName = System.getProperty("os.name");
		return (osName.indexOf("win") >= 0 || osName.indexOf("Win") >= 0);
	}

	public static boolean isMac() {
		String osName = System.getProperty("os.name");
		return (osName.indexOf("mac") >= 0 || osName.indexOf("Mac") >= 0);
	}
	public static boolean isUnix() {
		String osName = System.getProperty("os.name");
		return (osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0 );
	}
	public static boolean isSolaris() {
		String osName = System.getProperty("os.name");
		return (osName.indexOf("sunos") >= 0 || osName.indexOf("Sunos") >= 0);
	}

	public static void addLibraryPath(String path) {
		File dir = new File(path);
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException(dir + " n'est pas un dossier!");
		}
		String javaLibraryPath = System.getProperty("java.library.path");
		System.setProperty("java.library.path", javaLibraryPath + File.pathSeparatorChar + dir.getAbsolutePath());
	}

	public static void addLWJGLNative(String path) {
		try {
			Field field = ClassLoader.class.getDeclaredField("usr_paths");
			field.setAccessible(true);
			field.set(null, null);

			field = ClassLoader.class.getDeclaredField("sys_paths");
			field.setAccessible(true);
			field.set(null, null);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		String osDir;
		if (isWindows()) {
			osDir = "windows";
		} else if (isUnix()) {
			osDir = "linux";
		} else if (isMac()) {
			osDir = "macosx";
		} else if (isSolaris()) {
			osDir = "solaris";
		} else {
			throw new RuntimeException("OS non supporter: " + System.getProperty("os.name"));
		}
		addLibraryPath(path+osDir+"/");
	}

	public static void systemInfo() {
		System.out.println("Available processors (cores): " + 
				Runtime.getRuntime().availableProcessors());

		System.out.println("Free memory (bytes): " + 
				Runtime.getRuntime().freeMemory());

		long maxMemory = Runtime.getRuntime().maxMemory();
		System.out.println("Maximum memory (bytes): " + 
				(maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

		System.out.println("Total memory (bytes): " + 
				Runtime.getRuntime().totalMemory());

		File[] roots = File.listRoots();
		for (File root : roots) {
			System.out.println("File system root: " + root.getAbsolutePath());
			System.out.println("Total space (bytes): " + root.getTotalSpace());
			System.out.println("Free space (bytes): " + root.getFreeSpace());
			System.out.println("Usable space (bytes): " + root.getUsableSpace());
		}
	}
}
