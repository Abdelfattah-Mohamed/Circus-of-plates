package Control.dynamicLinkage;

import Model.Logger.GameLogger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class jarread {
	@SuppressWarnings("resource")
	public List<Class<?>> getCrunchifyClassNamesFromJar(String crunchifyJarName) {
		crunchifyJarName = "lib\\" + crunchifyJarName;
		List<Class<?>> listofClasses = new ArrayList<Class<?>>();
		try {
			File pathToJar = new File(crunchifyJarName);
			JarFile jarFile;
			jarFile = new JarFile(pathToJar);
			GameLogger.getInstance().log.debug("Jar turned into Object");
			Enumeration<JarEntry> e = jarFile.entries();

			URL[] urls = { new URL("jar:file:" + pathToJar + "!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);
			GameLogger.getInstance().log.debug("Getting Classes from Jar");
			while (e.hasMoreElements()) {
				JarEntry je = e.nextElement();
				if (je.isDirectory() || !je.getName().endsWith(".class")) {
					continue;
				}
				String className = je.getName().substring(0, je.getName().length() - 6);
				className = className.replace('/', '.');
				Class<?> c = cl.loadClass(className);
				GameLogger.getInstance().log.debug( "Class: "+className + " added to the list");
				listofClasses.add(c);
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			GameLogger.getInstance().log.error("JAR/FILE NOT FOUND");
			e.printStackTrace();
		}

		return listofClasses;
	}
}
