package li.tmj.io;

//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
import li.tmj.io.filetmj.AppTest;
import java.io.BufferedInputStream;
import java.io.File;
import li.tmj.io.FileTMJ;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.spi.FileSystemProvider;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.pmw.tinylog.Logger;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class FileTMJTest {//extends TestCase{
//	private Path dataForkPath;
//	private Path resourceForkPath;
	private FileTMJ fileTMJ;

//	@BeforeEach
//	void setUp() throws Exception {
//		fileTMJ = new FileTMJ("path");
//	}

//	@AfterEach
//	void tearDown() throws Exception {
//			
//	}
	
//	assertTrue(klasse.m1(3, 3) == 10, () -> "Im Fehlerfall...");
//	@DisplayName("erster Test") void ...
	
//	@ParameterizedTest
//	@ValueSource(strings = { "Hello", "JUnit" })
//	void withValueSource(String word) {
//		assertNotNull(word);
//	}

//	@ParameterizedTest
//	@CsvFileSource(resources = "/resource/two-column.csv")
//	void testWithCsvFileSource(String first, int second) {
//		assertNotNull(first);
//		assertNotEquals(0, second);
//	}

//	assertTimeout(Duration.ofMillis(100), () -> {
//	klasse.m2();
//});	



	@Test
	void constructorTest() {
		Path path=Paths.get("a", "b");
		FileTMJ fileTMJ=new FileTMJ(path);//FileTMJTest(Path path)
		assertNotNull(fileTMJ);

		File file=new File("a");
		fileTMJ=new FileTMJ(file);//FileTMJTest(File file)
		assertNotNull(fileTMJ);

//		FileTMJ.SpecificPath specificPath=new FileTMJ.SpecificPath("path") {//SpecificPath(String path)
//			@Override
//			protected String convert(String path) {
//				return "path";
//			}
//		};
//		fileTMJ=new FileTMJ(specificPath);
//		assertNotNull(fileTMJ);
//
//		specificPath=new FileTMJ.SpecificPath(FileSystems.getDefault(),"path") {//SpecificPath(String path)
//			@Override
//			protected String convert(String path) {
//				return "path";
//			}
//		};
//		fileTMJ=new FileTMJ(specificPath);
//		assertNotNull(fileTMJ);
//
////		specificPath=new FileTMJ.UncPath("/Users/");
////		fileTMJ=new FileTMJ(specificPath);//uncFilePath
////		assertNotNull(fileTMJ);
//		
//		specificPath=new FileTMJ.WindowsPath("c:\\path\\xyz");
//		fileTMJ=new FileTMJ(specificPath);//FileTMJ(SpecificPath path)
//		assertNotNull(fileTMJ);
//		
//		specificPath=new FileTMJ.MacOSclassicFilePath("c:\\path\\xyz");
//		fileTMJ=new FileTMJ(specificPath);//FileTMJ(SpecificPath path)
//		assertNotNull(fileTMJ);
//		
//		
//		fileTMJ=new FileTMJ("path");//FileTMJ(String filePath)
//		assertNotNull(fileTMJ);
//		
//		fileTMJ=new FileTMJ(FileSystems.getDefault(),"path");//FileTMJ(FileSystem fileSystem,String filePath)
//		assertNotNull(fileTMJ);
//		
//		fileTMJ=new FileTMJ(FileSystemProvider.installedProviders().get(0),Paths.get("path").toUri());//FileTMJ(FileSystemProvider fileSystemProvider, URI uri)
//		assertNotNull(fileTMJ);
	}

//	@Test
//	void specificPathConstructorTest() {
//		FileTMJ.SpecificPath specificPath=new FileTMJ.SpecificPath("path") {//SpecificPath(String path)
//			@Override
//			protected String convert(String path) {
//				return "path";
//			}
//		};
//		assertNotNull(specificPath);
//		Path path=specificPath.getPath();
//		assertNotNull(path);
//		String s=specificPath.convert("");
//		assertNotNull(s);
//		
//		specificPath=new FileTMJ.SpecificPath(FileSystems.getDefault(),"path") {//SpecificPath(FileSystem fileSystem,String path)
//			@Override
//			protected String convert(String path) {
//				return "path";
//			}
//		};
//		assertNotNull(specificPath);
//		path=specificPath.getPath();
//		assertNotNull(path);
//		s=specificPath.convert("");
//		assertNotNull(s);
//	}

//	@Test
//	void windowsPath() {
//		FileTMJ.WindowsPath windowsPath=new FileTMJ.WindowsPath("c:\\path\\xyz"); //WindowsPath(String path)
//		assertNotNull(windowsPath);
//		Path path=windowsPath.getPath();
//		assertNotNull(path);
//		String s=windowsPath.convert("");
//		assertNotNull(s);
//		
//		windowsPath=new FileTMJ.WindowsPath(FileSystems.getDefault(),"c:\\path\\xyz") {//WindowsPath(FileSystem fileSystem,String path)
////			@Override
////			protected String convert(String path) {
////				return "/path/xyz";
////			}
//		};
//		assertNotNull(windowsPath);
//		path=windowsPath.getPath();
//		assertNotNull(path);
//		s=windowsPath.convert("");
//		assertNotNull(s);
//	}

//	@Test
//	void macOSclassicFilePath() {
//		FileTMJ.MacOSclassicFilePath macOSclassicFilePath=new FileTMJ.MacOSclassicFilePath("Macintosh HD:SystemOrdner") {//MacOSclassicFilePath(String path)
//			@Override
//			protected String convert(String path) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		};
//		assertNotNull(macOSclassicFilePath);
//		Path path=macOSclassicFilePath.getPath();
//		assertNotNull(path);
//		String s=macOSclassicFilePath.convert("");
//		assertNotNull(s);
//		
//		macOSclassicFilePath=new FileTMJ.MacOSclassicFilePath(FileSystems.getDefault(),"Macintosh HD:SystemOrdner") {//MacOSclassicFilePath(FileSystem fileSystem,String path)
//			@Override
//			protected String convert(String path) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		};
//		assertNotNull(macOSclassicFilePath);
//		path=macOSclassicFilePath.getPath();
//		assertNotNull(path);
//		s=macOSclassicFilePath.convert("");
//		assertNotNull(s);
//	}

	@Test void hasDataForkTest() {//TODO Mock fileTMJ for this!
		fileTMJ = new FileTMJ("path");
		try {
			boolean b=fileTMJ.hasResourceFork();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test void hasResourceForkTest() {//TODO Mock fileTMJ for this!
		fileTMJ = new FileTMJ("path");
		try {
			boolean b=fileTMJ.hasResourceFork();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test void childTest() {//TODO Mock fileTMJ for this!
			fileTMJ=new FileTMJ("/").child("xyz");
			assertNotNull(fileTMJ);
	}

	@Test void containingFilesTest() {//TODO Mock fileTMJ for this!
		// Exception testen
		Assertions.assertThrows(NoSuchFileException.class, () -> {
			new FileTMJ("/wegfqbxq23tr6xbuwake").containingFiles();
		});

		Assertions.assertThrows(NotDirectoryException.class, () -> {
			new FileTMJ("/.DS_Store").containingFiles();
		});

		try {
			FileTMJ[] fileTMJs=new FileTMJ("/Users").containingFiles();
			assertNotNull(fileTMJs);
			assertTrue(0<fileTMJs.length);
		} catch (NotDirectoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test void existsTest() {//TODO Mock fileTMJ for this!
		assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").exists() );
		assertTrue( new FileTMJ("/").exists() );
	}

	@Test void extensionTest() {//TODO Mock fileTMJ for this!
		assertEquals( new FileTMJ("/test.abc").extension(), "abc" );
		assertEquals( FileTMJ.extension( Paths.get("/test.abc","") ), "abc" );
	}

	@Test void isDirectoryTest() {//TODO Mock fileTMJ for this!
		assertTrue( new FileTMJ("/Users").isDirectory() );
		assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").isDirectory() );
	}

	@Test void isRegularFile() {//TODO Mock fileTMJ for this!
		assertTrue( new FileTMJ("/.DS_Store").isRegularFile() );
		assertFalse( new FileTMJ("/Users").isRegularFile() );
	}

	@Test void isPackage() {//TODO Mock fileTMJ for this!
		assertTrue( new FileTMJ("/Applications/Mail.app").isPackage() );
		assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").isPackage() );
		assertFalse( new FileTMJ("/Users").isPackage() );
	}

//	@Test void isEmpty() {//TODO Mock fileTMJ for this!
//		assertTrue( new FileTMJ("/Applications/Mail.app").isEmpty() );
//		assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").isEmpty() );
//		assertFalse( new FileTMJ("/Users").isEmpty() );
//	}

//	@Test void moveToTest(FileTMJ destination) throws IOException {
//		Path path=Files.move(dataForkPath, destination.dataForkPath, StandardCopyOption.ATOMIC_MOVE);
//		return new FileTMJ(path);
//	}

//	@Test void setNameTest(String nameNew) throws IOException {
//		return false;
//		}
	
//	@Test void sizeBytes() throws SecurityException, IOException{
//		return dataSize+resourceSize;
//	}

//	@Test public void toUriTest() {
//		assertNotNull( new FileTMJ("/path").toUri() );
//    }
	

//	public File toFile() {
//	return dataForkPath.toFile();
//}

//public Path toPath() {
//	return dataForkPath;
//}

//public String getAbsolutePath() throws IOException {
//	return file.getCanonicalPath();
//}

//	deviceName() { # $1 = path
//		# returns device name on which path is located
//		local d=$(df "$1" | grep /dev | awk '{print $1}')
//		echo "${d:5}" # device name w/o /dev/
//	}

//	disableOpenWarning(){ # $1 = Application path
//		# removes OS' warning for new downloaded Applications which only an admin can stop
//		if [ 6 -le $displayLevel ]; then
//			echo "disableOpenWarning..."
//		fi
//		if [ "$1" == "" ]; then	return 1; fi
//		xattr -dvx com.apple.quarantine "$1"
//	}

//	isMacOSAlias(){ # $1 = file path
//		# if [ -f "$1" ]; then return 1; fi # no regular file, so no macOS Alias
//		# local f=$(file -bhs "$1") # b=brief, h=symlink as file itself, I=mime types instead of human words, 
//								  # k=keep going after first match, s=avoid special files problems
//	#	local f=$(file -bhIks "$1")
//		# application/x-directory; charset=binary
//		# application/x-symlink; charset=binary		Ziel Ordner oder Datei unerheblich
//		# application/octet-stream; charset=binary	macOS Alias
//	#	[ "MacOS Alias file" == $f ] # ${f:0:20} ] # macOS < 10.12?
//		
//		local x=$(mdls -name kMDItemKind "$1") # works with which macOS version?
//		[ 'kMDItemKind = "Alias"' == "$x" ]
//
//		return $?
//	}

//	isBrokenSymlink(){ # $1 = file path
//		if [ -L "$1" ]; then return 1; fi # no symlink
//		local f=$(file -bh "$1")
//		[ "broken symbolic link" == ${f:0:20} ]
//		return $?
//	}

//	isSameDevice(){ # $1, $2 = paths to check
//	# returns 0 aka true if the device names of $1 and $2 are the same, 1 aka false if not, 2 in case of an error
//	local d1=$(deviceName "$1")
//	if [ ! 0 -eq $? ]; then return 2; fi
//	local d2=$(deviceName "$2")
//	if [ ! 0 -eq $? ]; then return 2; fi
//	[ "$d1" == "$d2" ]
//	return $?
//}

	
//	public String macOSTypeCreator(){
//		local -a x=($(xattr -sp com.apple.FinderInfo "$1")) # macOS Data Hex Values
//		local result=$?
////		if( 0 -lt $result ]; then return null; } // cancel on error
//		return %b "\x${x[0]}" "\x${x[1]}" "\x${x[2]}" "\x${x[3]}" "\x${x[4]}" "\x${x[5]}" "\x${x[6]}" "\x${x[7]}" // macOS Type/Creator
//	}


//	readFreeDiscSpace() { # current disc space
//	# Parameter: $1 = path, which's disk interests
//		local p="$1"
//		local KiB=`df "$p" | grep / | awk -F' ' '{print $4}'` # available in 512B blocks
//		expr $KiB / 2 # ...in KiB
//		#	KiB=`expr $KiB / 2` # ...in KiB
//		#	echo $KiB
//	}

	@Test void hashCodeTest() {
		assertTrue( 1<new FileTMJ("/path").hashCode() );
	}

	@Test void equalsTest() {
		FileTMJ fileTMJ=new FileTMJ("/");
		FileTMJ fileTMJ2=new FileTMJ("/Users");
		assertNotEquals(fileTMJ, fileTMJ2);
		fileTMJ=fileTMJ.child("Users");
		assertEquals(fileTMJ, fileTMJ2);
	}

//	public boolean equalsBinary(FileTMJ file) throws IOException {
//		return equalsBinary(this,file);
//	}

//	public static boolean equalsBinary(FileTMJ file1,FileTMJ file2) throws IOException {
//		return true;
//	}

//	private static boolean compareConcreteIntern(Path file1,Path file2) throws IOException {
//		return true;
//	}

//	public int compareTo(FileTMJ file) {
//		return dataForkPath.compareTo(file.dataForkPath);
//    }

	@Test void toStringTest() {
		FileTMJ fileTMJ=new FileTMJ("/Users");
		String s=fileTMJ.toString();
		assertNotNull( s );
		assertNotEquals("", s);
	}

}
