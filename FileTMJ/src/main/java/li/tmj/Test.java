package li.tmj;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import li.tmj.io.FileTMJ;

public class Test {

	public static void main(String[] args) throws IOException {
		FileTMJ file1=new FileTMJ("C:\\Users\\Public\\Documents\\Docs\\dev\\files\\Compare\\_test\\abc"); 
		FileTMJ file2=new FileTMJ("C:\\Users\\Public\\Documents\\Docs\\dev\\files\\Compare\\_test\\abc"); 
		System.out.println("file1 vs file1.equalsBinary(file2): "+file1.equalsBinary(file2) );
		
		
		
	}

	public static void main2(String[] args) throws IOException {
		String source1;
		Path path;
		source1="\\\172.16.1.40\\Tobias Meyer-Jansons öffentlicher Ordner\\1 mit RF.textClipping"; //-> exists = false!
//				path=Paths.get(source1);
//				System.out.println(path.getFileSystem().getClass().getSimpleName());	//WindowsFileSystem "MacOSXFileSystem"// Unter Windows per Samba angebundene Laufwerke werden als WindowsFileSystem angezeigt!
//				System.out.println(Files.getFileStore(path));//iM (z:)
		source1="z:\\1 mit RF.textClipping";
//		String source1="\\\172.16.1.40\\Tobias Meyer-Jansons öffentlicher Ordner";
//		String source1="/Users/Public/Documents/x";
//		String source1="/Volumes/Users/Public/Documents/1 mit RF.textClipping";
		 path=Paths.get(source1);
		System.out.println(path.getFileSystem().getClass().getSimpleName());	//WindowsFileSystem "MacOSXFileSystem"// Unter Windows per Samba angebundene Laufwerke werden als WindowsFileSystem angezeigt!
		System.out.println(Files.getFileStore(path));//iM (z:)
		System.out.println(Files.getFileStore(path).name());//iM
		System.out.println(Files.getFileStore(path).type());//NTFS
		System.out.println("path="+path);
		boolean b=Files.exists(path, LinkOption.NOFOLLOW_LINKS) ;
		System.out.println("b="+b);

		FileTMJ fileS1=new FileTMJ(source1);
		String source2="\\\\172.16.1.40\\Tobias Meyer-Jansons öffentlicher Ordner\\2 kein RF.txt";
//		String source2="/Users/tobias/Documents/Docs/dev/_TestData/Testdata/1 mit RF.textClipping";
		FileTMJ fileS2=new FileTMJ(source2);
		String source3="/Volumes/Users/Public/Documents/2 kein RF.txt";
		FileTMJ fileS3=new FileTMJ(source3);
		String source4="/Users/tobias/Documents/Docs/dev/_TestData/Testdata/2 kein RF.txt";
		FileTMJ fileS4=new FileTMJ(source4);
		String nameNew="";
		String dest="/Users/tobias/Documents/Docs/dev/_TestData/Testdata/6";
		
//		FileTMJ fileS=new FileTMJ(source);

		System.out.println("fileS1="+fileS1);
		System.out.println("fileS1="+fileS1+", exists="+fileS1.exists());
		System.out.println("fileS1="+fileS1+", exists="+fileS1.exists()+", size="+fileS1.sizeBytes());
		System.out.println("fileS3="+fileS3+", exists="+fileS3.exists()+", size="+fileS3.sizeBytes());
		System.out.println("fileS2="+fileS2+", exists="+fileS2.exists()+", size="+fileS2.sizeBytes());
		System.out.println("fileS4="+fileS4+", exists="+fileS4.exists()+", size="+fileS4.sizeBytes());
		FileTMJ fileD=new FileTMJ(dest);
		
//		try {
//			fileS.moveTo(fileD);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("fileD="+fileD+", exists="+fileD.exists()+", size="+fileD.sizeBytes());
//		System.out.println("fileS="+fileS+", exists="+fileS.exists()+", size="+fileS.sizeBytes());
	
	
		// Über kreuz testen:
		// √ Resource Fork wird unter macOS auf macFS erkannt und beim Umbennenen berücksichtigt.
		// √ Unter macOS auf macFS können keine Dateien/Ordner erstellt werden, die dem Resource-Fork-Path entsprechen, also verwechselt werden könnten.
		// √ Wie ist es bei Mac-Dateien mit Resource vom Mac aus auf einem NTFS o. Ä.?
		// Wie ist es vom PC aus auf macFS?
		
		
	}

}
