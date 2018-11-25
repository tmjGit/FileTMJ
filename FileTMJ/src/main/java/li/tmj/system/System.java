package li.tmj.system;

import java.io.IOException;
//import li.tmj.app.Application;//Application.ORGANIZATION
import li.tmj.system.Windows;
import org.pmw.tinylog.Logger;

public class System {
	public static String standardLogPath(String ApplicationOrganization) throws IOException {
		String fileSeparator=java.lang.System.getProperty("file.separator");
		if(java.lang.System.getProperty("os.name").equals("Mac OS X")) {
			return java.lang.System.getProperty("user.home")+fileSeparator+"Library"+fileSeparator+"Logs"+fileSeparator;
			
		}else if ( java.lang.System.getProperty("os.name").substring(0, 6).equals("Windows") ) {
//			if (com.sun.jna.Platform.isWindows()) {
//			C:\ProgramData\MyCompany // XP
//			AppData\*\Microsoft\(app name),
//	  	 	organization\app convention 
			try {
				return Windows.getLocalAppData()+fileSeparator+ApplicationOrganization+fileSeparator;
			} catch (IOException e) {
				Logger.error("Could not get Windows' path %LOCALAPPDATA% for the log file. "+e.fillInStackTrace());
				throw e;
			}
//			return "";
			
		}else {
			throw new IOException("Unknown operating system. Cannot get log path.");
		}
	}
}
