package dev.celestiacraft.libs.utils.annotation.log;

import java.lang.reflect.Method;

public class LogProcessor {
	public static void process(Object obj) {
		for (Method method : obj.getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(Log.class)) {
				String msg = "Executing " + method.getName();

				try {
					method.setAccessible(true);
					method.invoke(obj);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}
	}
}