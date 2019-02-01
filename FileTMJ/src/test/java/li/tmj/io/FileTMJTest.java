//package li.tmj.io;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.Closeable;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.Serializable;
//import java.io.UncheckedIOException;
//import java.net.MalformedURLException;
//import java.net.URI;
//import java.net.URL;
//import java.nio.channels.SeekableByteChannel;
//import java.nio.file.AtomicMoveNotSupportedException;
//import java.nio.file.CopyOption;
//import java.nio.file.DirectoryIteratorException;
//import java.nio.file.DirectoryNotEmptyException;
//import java.nio.file.DirectoryStream;
//import java.nio.file.FileAlreadyExistsException;
//import java.nio.file.FileStore;
//import java.nio.file.FileSystem;
//import java.nio.file.FileSystemException;
//import java.nio.file.FileSystemLoopException;
//import java.nio.file.FileSystems;
//import java.nio.file.FileVisitOption;
//import java.nio.file.InvalidPathException;
//import java.nio.file.LinkOption;
//import java.nio.file.LinkPermission;
//import java.nio.file.NoSuchFileException;
//import java.nio.file.NotDirectoryException;
//import java.nio.file.NotLinkException;
//import java.nio.file.OpenOption;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.SecureDirectoryStream;
//import java.nio.file.StandardCopyOption;
//import java.nio.file.StandardOpenOption;
//import java.nio.file.attribute.BasicFileAttributeView;
//import java.nio.file.attribute.BasicFileAttributes;
//import java.nio.file.attribute.FileAttribute;
//import java.nio.file.attribute.FileAttributeView;
//import java.nio.file.attribute.PosixFilePermission;
//import java.nio.file.attribute.PosixFilePermissions;
//import java.nio.file.spi.FileSystemProvider;
//import java.security.AccessController;
//import java.security.SecureRandom;
//import java.util.EnumSet;
//import java.util.Iterator;
//import java.util.Objects;
//import java.util.Set;
//import java.util.Spliterator;
//import java.util.Spliterators;
//import java.util.stream.Stream;
//import java.util.stream.StreamSupport;
//import li.tmj.io.FileTMJ.CompareResult.CompareResultDetail;
//import sun.security.action.GetPropertyAction;
//
//
//
////import junit.framework.Test;
////import junit.framework.TestCase;
////import junit.framework.TestSuite;
//import li.tmj.io.filetmj.AppTest;
//import java.io.BufferedInputStream;
//import java.io.File;
//import li.tmj.io.FileTMJ;
//import java.io.FileNotFoundException;
//import java.io.FilenameFilter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URI;
//import java.nio.file.FileAlreadyExistsException;
//import java.nio.file.FileSystem;
//import java.nio.file.FileSystemNotFoundException;
//import java.nio.file.FileSystems;
//import java.nio.file.Files;
//import java.nio.file.InvalidPathException;
//import java.nio.file.LinkOption;
//import java.nio.file.NoSuchFileException;
//import java.nio.file.NotDirectoryException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.nio.file.spi.FileSystemProvider;
//import java.util.Arrays;
//import java.util.regex.Pattern;
//import java.util.stream.Stream;
//import org.pmw.tinylog.Logger;
//import static org.junit.jupiter.api.Assertions.*;
//import java.time.Duration;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvFileSource;
//import org.junit.jupiter.params.provider.ValueSource;
//
//public class FileTMJTest {//extends TestCase{
////	private Path dataForkPath;
////	private Path resourceForkPath;
//	private FileTMJ fileTMJ;
//
////	@BeforeEach
////	void setUp() throws Exception {
////		fileTMJ = new FileTMJ("path");
////	}
//
////	@AfterEach
////	void tearDown() throws Exception {
////			
////	}
//	
////	assertTrue(klasse.m1(3, 3) == 10, () -> "Im Fehlerfall...");
////	@DisplayName("erster Test") void ...
//	
////	@ParameterizedTest
////	@ValueSource(strings = { "Hello", "JUnit" })
////	void withValueSource(String word) {
////		assertNotNull(word);
////	}
//
////	@ParameterizedTest
////	@CsvFileSource(resources = "/resource/two-column.csv")
////	void testWithCsvFileSource(String first, int second) {
////		assertNotNull(first);
////		assertNotEquals(0, second);
////	}
//
////	assertTimeout(Duration.ofMillis(100), () -> {
////	klasse.m2();
////});	
//
//
//
//	@Test
//	void constructor() {
//		Path path=Paths.get("a", "b");
//		FileTMJ fileTMJ=new FileTMJ(path);//FileTMJTest(Path path)
//		assertNotNull(fileTMJ);
//
//		File file=new File("a");
//		fileTMJ=new FileTMJ(file);//FileTMJTest(File file)
//		assertNotNull(fileTMJ);
//
////		FileTMJ.SpecificPath specificPath=new FileTMJ.SpecificPath("path") {//SpecificPath(String path)
////			@Override
////			protected String convert(String path) {
////				return "path";
////			}
////		};
////		fileTMJ=new FileTMJ(specificPath);
////		assertNotNull(fileTMJ);
////
////		specificPath=new FileTMJ.SpecificPath(FileSystems.getDefault(),"path") {//SpecificPath(String path)
////			@Override
////			protected String convert(String path) {
////				return "path";
////			}
////		};
////		fileTMJ=new FileTMJ(specificPath);
////		assertNotNull(fileTMJ);
////
//////		specificPath=new FileTMJ.UncPath("/Users/");
//////		fileTMJ=new FileTMJ(specificPath);//uncFilePath
//////		assertNotNull(fileTMJ);
////		
////		specificPath=new FileTMJ.WindowsPath("c:\\path\\xyz");
////		fileTMJ=new FileTMJ(specificPath);//FileTMJ(SpecificPath path)
////		assertNotNull(fileTMJ);
////		
////		specificPath=new FileTMJ.MacOSclassicFilePath("c:\\path\\xyz");
////		fileTMJ=new FileTMJ(specificPath);//FileTMJ(SpecificPath path)
////		assertNotNull(fileTMJ);
////		
////		
////		fileTMJ=new FileTMJ("path");//FileTMJ(String filePath)
////		assertNotNull(fileTMJ);
////		
////		fileTMJ=new FileTMJ(FileSystems.getDefault(),"path");//FileTMJ(FileSystem fileSystem,String filePath)
////		assertNotNull(fileTMJ);
////		
////		fileTMJ=new FileTMJ(FileSystemProvider.installedProviders().get(0),Paths.get("path").toUri());//FileTMJ(FileSystemProvider fileSystemProvider, URI uri)
////		assertNotNull(fileTMJ);
//	}
//
////	@Test
////	void specificPathConstructor() {
////		FileTMJ.SpecificPath specificPath=new FileTMJ.SpecificPath("path") {//SpecificPath(String path)
////			@Override
////			protected String convert(String path) {
////				return "path";
////			}
////		};
////		assertNotNull(specificPath);
////		Path path=specificPath.getPath();
////		assertNotNull(path);
////		String s=specificPath.convert("");
////		assertNotNull(s);
////		
////		specificPath=new FileTMJ.SpecificPath(FileSystems.getDefault(),"path") {//SpecificPath(FileSystem fileSystem,String path)
////			@Override
////			protected String convert(String path) {
////				return "path";
////			}
////		};
////		assertNotNull(specificPath);
////		path=specificPath.getPath();
////		assertNotNull(path);
////		s=specificPath.convert("");
////		assertNotNull(s);
////	}
//
////	@Test
////	void windowsPath() {
////		FileTMJ.WindowsPath windowsPath=new FileTMJ.WindowsPath("c:\\path\\xyz"); //WindowsPath(String path)
////		assertNotNull(windowsPath);
////		Path path=windowsPath.getPath();
////		assertNotNull(path);
////		String s=windowsPath.convert("");
////		assertNotNull(s);
////		
////		windowsPath=new FileTMJ.WindowsPath(FileSystems.getDefault(),"c:\\path\\xyz") {//WindowsPath(FileSystem fileSystem,String path)
//////			@Override
//////			protected String convert(String path) {
//////				return "/path/xyz";
//////			}
////		};
////		assertNotNull(windowsPath);
////		path=windowsPath.getPath();
////		assertNotNull(path);
////		s=windowsPath.convert("");
////		assertNotNull(s);
////	}
//
////	@Test
////	void macOSclassicFilePath() {
////		FileTMJ.MacOSclassicFilePath macOSclassicFilePath=new FileTMJ.MacOSclassicFilePath("Macintosh HD:SystemOrdner") {//MacOSclassicFilePath(String path)
////			@Override
////			protected String convert(String path) {
////				// TODO Auto-generated method stub
////				return null;
////			}
////		};
////		assertNotNull(macOSclassicFilePath);
////		Path path=macOSclassicFilePath.getPath();
////		assertNotNull(path);
////		String s=macOSclassicFilePath.convert("");
////		assertNotNull(s);
////		
////		macOSclassicFilePath=new FileTMJ.MacOSclassicFilePath(FileSystems.getDefault(),"Macintosh HD:SystemOrdner") {//MacOSclassicFilePath(FileSystem fileSystem,String path)
////			@Override
////			protected String convert(String path) {
////				// TODO Auto-generated method stub
////				return null;
////			}
////		};
////		assertNotNull(macOSclassicFilePath);
////		path=macOSclassicFilePath.getPath();
////		assertNotNull(path);
////		s=macOSclassicFilePath.convert("");
////		assertNotNull(s);
////	}
//
//
//
//
//	@Test void containingFiles() {//TODO Mock fileTMJ for this!
//		// Exception testen
//		Assertions.assertThrows(NoSuchFileException.class, () -> {
//			new FileTMJ("/wegfqbxq23tr6xbuwake").containingFiles();
//		});
//
//		Assertions.assertThrows(NotDirectoryException.class, () -> {
//			new FileTMJ("/.DS_Store").containingFiles();
//		});
//
//		try {
//			FileTMJ[] fileTMJs=new FileTMJ("/Users").containingFiles();
//			assertNotNull(fileTMJs);
//			assertTrue(0<fileTMJs.length);
//		} catch (NotDirectoryException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//
//
//
//
//
//
////	@Test void moveToTest(FileTMJ destination) throws IOException {
////		Path path=Files.move(dataForkPath, destination.dataForkPath, StandardCopyOption.ATOMIC_MOVE);
////		return new FileTMJ(path);
////	}
//
////	@Test void setNameTest(String nameNew) throws IOException {
////		return false;
////		}
//	
////	@Test void sizeBytes() throws SecurityException, IOException{
////		return dataSize+resourceSize;
////	}
//
////	@Test public void toUri() {
////		assertNotNull( new FileTMJ("/path").toUri() );
////  }
//	
//
////	public File toFile() {
////	return dataForkPath.toFile();
////}
//
////public Path toPath() {
////	return dataForkPath;
////}
//
////public String getAbsolutePath() throws IOException {
////	return file.getCanonicalPath();
////}
//
////	deviceName() { # $1 = path
////		# returns device name on which path is located
////		local d=$(df "$1" | grep /dev | awk '{print $1}')
////		echo "${d:5}" # device name w/o /dev/
////	}
//
////	disableOpenWarning(){ # $1 = Application path
////		# removes OS' warning for new downloaded Applications which only an admin can stop
////		if [ 6 -le $displayLevel ]; then
////			echo "disableOpenWarning..."
////		fi
////		if [ "$1" == "" ]; then	return 1; fi
////		xattr -dvx com.apple.quarantine "$1"
////	}
//
////	isMacOSAlias(){ # $1 = file path
////		# if [ -f "$1" ]; then return 1; fi # no regular file, so no macOS Alias
////		# local f=$(file -bhs "$1") # b=brief, h=symlink as file itself, I=mime types instead of human words, 
////								  # k=keep going after first match, s=avoid special files problems
////	#	local f=$(file -bhIks "$1")
////		# application/x-directory; charset=binary
////		# application/x-symlink; charset=binary		Ziel Ordner oder Datei unerheblich
////		# application/octet-stream; charset=binary	macOS Alias
////	#	[ "MacOS Alias file" == $f ] # ${f:0:20} ] # macOS < 10.12?
////		
////		local x=$(mdls -name kMDItemKind "$1") # works with which macOS version?
////		[ 'kMDItemKind = "Alias"' == "$x" ]
////
////		return $?
////	}
//
////	isBrokenSymlink(){ # $1 = file path
////		if [ -L "$1" ]; then return 1; fi # no symlink
////		local f=$(file -bh "$1")
////		[ "broken symbolic link" == ${f:0:20} ]
////		return $?
////	}
//
////	isSameDevice(){ # $1, $2 = paths to check
////	# returns 0 aka true if the device names of $1 and $2 are the same, 1 aka false if not, 2 in case of an error
////	local d1=$(deviceName "$1")
////	if [ ! 0 -eq $? ]; then return 2; fi
////	local d2=$(deviceName "$2")
////	if [ ! 0 -eq $? ]; then return 2; fi
////	[ "$d1" == "$d2" ]
////	return $?
////}
//
//	
////	public String macOSTypeCreator(){
////		local -a x=($(xattr -sp com.apple.FinderInfo "$1")) # macOS Data Hex Values
////		local result=$?
//////		if( 0 -lt $result ]; then return null; } // cancel on error
////		return %b "\x${x[0]}" "\x${x[1]}" "\x${x[2]}" "\x${x[3]}" "\x${x[4]}" "\x${x[5]}" "\x${x[6]}" "\x${x[7]}" // macOS Type/Creator
////	}
//
//
////	readFreeDiscSpace() { # current disc space
////	# Parameter: $1 = path, which's disk interests
////		local p="$1"
////		local KiB=`df "$p" | grep / | awk -F' ' '{print $4}'` # available in 512B blocks
////		expr $KiB / 2 # ...in KiB
////		#	KiB=`expr $KiB / 2` # ...in KiB
////		#	echo $KiB
////	}
//
//	@Test void hashCode() {
//		assertTrue( 1<new FileTMJ("/path").hashCode() );
//	}
//
//	@Test void equals() {
//		FileTMJ fileTMJ=new FileTMJ("/");
//		FileTMJ fileTMJ2=new FileTMJ("/Users");
//		assertNotEquals(fileTMJ, fileTMJ2);
//		fileTMJ=fileTMJ.child("Users");
//		assertEquals(fileTMJ, fileTMJ2);
//	}
//
////	public boolean equalsBinary(FileTMJ file) throws IOException {
////		return equalsBinary(this,file);
////	}
//
////	public static boolean equalsBinary(FileTMJ file1,FileTMJ file2) throws IOException {
////		return true;
////	}
//
////	private static boolean compareConcreteIntern(Path file1,Path file2) throws IOException {
////		return true;
////	}
//
////	public int compareTo(FileTMJ file) {
////		return dataForkPath.compareTo(file.dataForkPath);
////  }
//
//	@Test void toString() {
//		FileTMJ fileTMJ=new FileTMJ("/Users");
//		String s=fileTMJ.toString();
//		assertNotNull( s );
//		assertNotEquals("", s);
//	}
//
//
//	
//	@Test public void separatorChar() {
//		//    public static final char separatorChar = '?';
//	}
//	
//	@Test public void separator() {
//		//public static final String separator = "" + separatorChar;
//	}
//	
//	@Test public void pathSeparatorChar() {
//		//public static final char pathSeparatorChar = '?';
//	}
//	
//	@Test public void pathSeparator() {
//		//public static final String pathSeparator = "" + pathSeparatorChar;
//	}
//
//	@Test public void constructor() {
//		//public FileTMJ(Path path) {
//		//public FileTMJ(File file) throws InvalidPathException {
//		//public FileTMJ(String filePath) {
//		//public FileTMJ(String filePath, FileSystem fileSystem) {
//	}
//	
//	@Test void hasDataFork() {//TODO Mock fileTMJ for this!
//		fileTMJ = new FileTMJ("path");
//		try {
//			boolean b=fileTMJ.hasDataFork();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test void hasResourceFork() {//TODO Mock fileTMJ for this!
//		fileTMJ = new FileTMJ("path");
//		try {
//			boolean b=fileTMJ.hasResourceFork();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Test void exists() {//TODO Mock fileTMJ for this!
//		assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").exists() );
//		assertTrue( new FileTMJ("/").exists() );
//    }
//
//	@Test void isDirectory() {//TODO Mock fileTMJ for this!
//		assertTrue( new FileTMJ("/Users").isDirectory() );//TODO this is environment specific!
//		assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").isDirectory() );
//		//return isDirectory(LinkOption.NOFOLLOW_LINKS);
//		//public boolean isDirectory(LinkOption... linkOptions) {
//    }
//
//	@Test void isRegularFile() {//TODO Mock fileTMJ for this!
//		assertTrue( new FileTMJ("/.DS_Store").isRegularFile() );
//		assertFalse( new FileTMJ("/Users").isRegularFile() );
//		//public boolean isRegularFile(LinkOption... linkOptions) {
//    }
//    
//	@Test void extension() {//TODO Mock fileTMJ for this!
//		assertEquals( new FileTMJ("/test.abc").extension(), "abc" );
//		assertEquals( FileTMJ.extension( Paths.get("/test.abc","") ), "abc" );
//		//public static String extension(Path path) { // returns extension, if any
//	}
//
//	@Test void isPackage() {//TODO Mock fileTMJ for this!
//		assertTrue( new FileTMJ("/Applications/Mail.app").isPackage() );
//		assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").isPackage() );
//		assertFalse( new FileTMJ("/Users").isPackage() );
//	}
//
//	@Test void isEmpty() {//TODO Mock fileTMJ for this!
////		assertTrue( new FileTMJ("/Applications/Mail.app").isEmpty() );
////		assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").isEmpty() );
////		assertFalse( new FileTMJ("/Users").isEmpty() );
//	}
//    
//	@Test void getRoot() {
//		//public FileTMJ getRoot(){
//	}
//
//	@Test void getFileName() {
//		//public String getFileName(){
//	}
//
//	@Test void getPath() {
//		//public Path getPath() {
//	}
//	@Test void getPathString() {
//		//public String getPathString() {
//	}
//  
//	@Test void toNormalized() {
//		//public Path toNormalized() {
//	}
//
//	@Test void normalize() {
//		//public Path normalize() {
//	}
//
//	@Test void isAbsolute() {
//		//public boolean isAbsolute() {
//	}
//
//	@Test void toAbsolutePath() {
//		//public Path toAbsolutePath() {
//	}
//
//	@Test void absolutize() {
//		//public Path absolutize() {
//	}
//
//	@Test void toRealPath() {
//		//public Path toRealPath(LinkOption... options) throws IOException{
//	}
//
//	@Test void realize() {
//		//public Path realize(LinkOption... options) throws IOException {
//	}
//
//	@Test void toCanonicalPath() {
//		//public Path toCanonicalPath() throws IOException {
//	}
//	
//	@Test void toCanonicalPathString() {
//		//public String toCanonicalPathString() throws IOException {
//	}
// 
//	@Test void toFile() {
//		//public File toFile(){
//	}
// 
//	@Test void toPath() {
//		//public Path toPath() {
//	}
//
//	@Test void toURI() {
//		//public URI toURI() {
//	}
//
//	@Test void toURL() {
//		//public URL toURL() throws MalformedURLException {
//    }
//    
//    @Test void getHome() {
//    	//public static FileTMJ getHome() {
//    }
//  
//    @Test void getFileSystem() {
//    	//public FileSystem getFileSystem(){
//	}
//
//    @Test void getParent() {
//    	//public FileTMJ getParent(){
//    }
//
//	@Test void child() {//TODO Mock fileTMJ for this!
//		fileTMJ=new FileTMJ("/").child("xyz");
//		assertNotNull(fileTMJ);
//    	//public FileTMJ child(String child) throws InvalidPathException{
//    	//public FileTMJ child(Path child) throws InvalidPathException{
//	}
//    
//	@Test void getPathElementCount(){
//		//public int getPathElementCount(){
//	}
//
//	@Test void getPathElementName() {
//		//public Path getPathElementName(int index) throws IllegalArgumentException{
//	}
//
//    /**
//     * Constructs a relative path between this path and a given path.
//     *
//     * <p> Relativization is the inverse of {@link #resolve(Path) resolution}.
//     * This method attempts to construct a {@link #isAbsolute relative} path
//     * that when {@link #resolve(Path) resolved} against this path, yields a
//     * path that locates the same file as the given path. For example, on UNIX,
//     * if this path is {@code "/a/b"} and the given path is {@code "/a/b/c/d"}
//     * then the resulting relative path would be {@code "c/d"}. Where this
//     * path and the given path do not have a {@link #getRoot root} component,
//     * then a relative path can be constructed. A relative path cannot be
//     * constructed if only one of the paths have a root component. Where both
//     * paths have a root component then it is implementation dependent if a
//     * relative path can be constructed. If this path and the given path are
//     * {@link #equals equal} then an <i>empty path</i> is returned.
//     *
//     * <p> For any two {@link #normalize normalized} paths <i>p</i> and
//     * <i>q</i>, where <i>q</i> does not have a root component,
//     * <blockquote>
//     *   <i>p</i><tt>.relativize(</tt><i>p</i><tt>.resolve(</tt><i>q</i><tt>)).equals(</tt><i>q</i><tt>)</tt>
//     * </blockquote>
//     *
//     * <p> When symbolic links are supported, then whether the resulting path,
//     * when resolved against this path, yields a path that can be used to locate
//     * the {@link Files#isSameFile same} file as {@code other} is implementation
//     * dependent. For example, if this path is  {@code "/a/b"} and the given
//     * path is {@code "/a/x"} then the resulting relative path may be {@code
//     * "../x"}. If {@code "b"} is a symbolic link then is implementation
//     * dependent if {@code "a/b/../x"} would locate the same file as {@code "/a/x"}.
//     *
//     * @param   other
//     *          the path to relativize against this path
//     *
//     * @return  the resulting relative path, or an empty path if both paths are
//     *          equal
//     *
//     * @throws  IllegalArgumentException
//     *          if {@code other} is not a {@code Path} that can be relativized
//     *          against this path
//     */
//	/**
//     * Relativizes the given URI against this URI.
//     *
//     * <p> The relativization of the given URI against this URI is computed as
//     * follows: </p>
//     *
//     * <ol>
//     *
//     *   <li><p> If either this URI or the given URI are opaque, or if the
//     *   scheme and authority components of the two URIs are not identical, or
//     *   if the path of this URI is not a prefix of the path of the given URI,
//     *   then the given URI is returned. </p></li>
//     *
//     *   <li><p> Otherwise a new relative hierarchical URI is constructed with
//     *   query and fragment components taken from the given URI and with a path
//     *   component computed by removing this URI's path from the beginning of
//     *   the given URI's path. </p></li>
//     *
//     * </ol>
//     *
//     * @param  uri  The URI to be relativized against this URI
//     * @return The resulting URI
//     *
//     * @throws  NullPointerException
//     *          If {@code uri} is {@code null}
//     */
//    public Path relativize(FileTMJ other){
//    	return dataForkPath.relativize(other.dataForkPath);
//	}
//    public String relativizeString(FileTMJ other){
//    	return relativize(other).toString();
//	}
//	
//    /**
//     * Returns the hierarchy distance of the child {@link FileTMJ} whithin this FileTMJ.
//     * Returns {@code 0} if the files are equal and therefor at the same level.
//     * Returns {@code -1} if this file's path does not contain the child.
//     * If the given child is a true child of this file then a positive Integer is returned representing the hierarchical distance of the paths.
//     * 
//     * <p> Whether or not the root component of this file's path starts with the root
//     * component of the given child's path is file system specific. If only one of them
//     * has a root component then {@code -1} is returned.
//     *
//     * <p> If the files are associated with different {@code FileSystem}s then {@code -1} is returned.
//     *
//     * @param   child
//     *          the given file
//     *
//     * @return  Integer representing the hierarchical distance of the paths, -1 otherwise.
//     */
//    public int childDepth(FileTMJ child) {
//		throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
//	}
//
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    /**
//     * Returns an array of strings naming the files and directories in the
//     * directory denoted by this abstract pathname.
//     *
//     * <p> If this abstract pathname does not denote a directory, then this
//     * method returns {@code null}.  Otherwise an array of strings is
//     * returned, one for each file or directory in the directory.  Names
//     * denoting the directory itself and the directory's parent directory are
//     * not included in the result.  Each string is a file name rather than a
//     * complete path.
//     *
//     * <p> There is no guarantee that the name strings in the resulting array
//     * will appear in any specific order; they are not, in particular,
//     * guaranteed to appear in alphabetical order.
//     *
//     * <p> Note that the {@link java.nio.file.Files} class defines the {@link
//     * java.nio.file.Files#newDirectoryStream(Path) newDirectoryStream} method to
//     * open a directory and iterate over the names of the files in the directory.
//     * This may use less resources when working with very large directories, and
//     * may be more responsive when working with remote directories.
//     *
//     * @return  An array of strings naming the files and directories in the
//     *          directory denoted by this abstract pathname.  The array will be
//     *          empty if the directory is empty.  Returns {@code null} if
//     *          this abstract pathname does not denote a directory, or if an
//     *          I/O error occurs.
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its {@link
//     *          SecurityManager#checkRead(String)} method denies read access to
//     *          the directory
//     */
//    /**
//     * Returns an array of abstract pathnames denoting the files in the
//     * directory denoted by this abstract pathname.
//     *
//     * <p> If this abstract pathname does not denote a directory, then this
//     * method returns {@code null}.  Otherwise an array of {@code File} objects
//     * is returned, one for each file or directory in the directory.  Pathnames
//     * denoting the directory itself and the directory's parent directory are
//     * not included in the result.  Each resulting abstract pathname is
//     * constructed from this abstract pathname using the {@link #File(File,
//     * String) File(File,&nbsp;String)} constructor.  Therefore if this
//     * pathname is absolute then each resulting pathname is absolute; if this
//     * pathname is relative then each resulting pathname will be relative to
//     * the same directory.
//     *
//     * <p> There is no guarantee that the name strings in the resulting array
//     * will appear in any specific order; they are not, in particular,
//     * guaranteed to appear in alphabetical order.
//     *
//     * <p> Note that the {@link java.nio.file.Files} class defines the {@link
//     * java.nio.file.Files#newDirectoryStream(Path) newDirectoryStream} method
//     * to open a directory and iterate over the names of the files in the
//     * directory. This may use less resources when working with very large
//     * directories.
//     *
//     * @return  An array of abstract pathnames denoting the files and
//     *          directories in the directory denoted by this abstract pathname.
//     *          The array will be empty if the directory is empty.  Returns
//     *          {@code null} if this abstract pathname does not denote a
//     *          directory, or if an I/O error occurs.
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its {@link
//     *          SecurityManager#checkRead(String)} method denies read access to
//     *          the directory
//     *
//     * @since  1.2
//     */
//	public FileTMJ[] containingFiles() throws NotDirectoryException, NoSuchFileException, SecurityException, IOException {
//																														
//		return containingFilesStream().toArray(FileTMJ[]::new);
//																													 
////        SecurityManager security = System.getSecurityManager();
////        if (security != null) {
////            security.checkRead(path);
////        }
////        if (isInvalid()) {
////            return null;
////        }
////        return fs.list(this);
////        String[] ss = list();
////        if (ss == null) return null;
////        int n = ss.length;
////        File[] fs = new File[n];
////        for (int i = 0; i < n; i++) {
////            fs[i] = new File(ss[i], this);
////        }
////        return fs;
//    }
//
//   
//	private static class AcceptAllFilter implements DirectoryStream.Filter<Path> {
//		private AcceptAllFilter() { }
//		@Override public boolean accept(Path entry) { return true; }
//		static final AcceptAllFilter FILTER = new AcceptAllFilter();
//	}
//	/**
//     * Opens a directory, returning a {@link DirectoryStream} to iterate over
//     * all entries in the directory. The elements returned by the directory
//     * stream's {@link DirectoryStream#iterator iterator} are of type {@code
//     * Path}, each one representing an entry in the directory. The {@code Path}
//     * objects are obtained as if by {@link Path#resolve(Path) resolving} the
//     * name of the directory entry against {@code dir}.
//     *
//     * <p> When not using the try-with-resources construct, then directory
//     * stream's {@code close} method should be invoked after iteration is
//     * completed so as to free any resources held for the open directory.
//     *
//     * <p> When an implementation supports operations on entries in the
//     * directory that execute in a race-free manner then the returned directory
//     * stream is a {@link SecureDirectoryStream}.
//     *
//     * @param   dir
//     *          the path to the directory
//     *
//     * @return  a new and open {@code DirectoryStream} object
//     *
//     * @throws  NotDirectoryException
//     *          if the file could not otherwise be opened because it is not
//     *          a directory <i>(optional specific exception)</i>
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the directory.
//     */
//    public static DirectoryStream<Path> newDirectoryStream(Path dir) throws IOException {
//    	return dir.getFileSystem().provider().newDirectoryStream(dir, AcceptAllFilter.FILTER);
//    }
//    /**
//     * Convert a Closeable to a Runnable by converting checked IOException
//     * to UncheckedIOException
//     */
//    private static Runnable asUncheckedRunnable(Closeable c) {
//        return () -> {
//            try {
//                c.close();
//            } catch (IOException e) {
//                throw new UncheckedIOException(e);
//            }
//        };
//    }
//    /**
//	 * From java.nio.file.Files.list
//     * Return a lazily populated {@code Stream}, the elements of
//     * which are the entries in the directory.  The listing is not recursive.
//     *
//     * <p> The elements of the stream are {@link Path} objects that are
//     * obtained as if by {@link Path#resolve(Path) resolving} the name of the
//     * directory entry against {@code dir}. Some file systems maintain special
//     * links to the directory itself and the directory's parent directory.
//     * Entries representing these links are not included.
//     *
//     * <p> The stream is <i>weakly consistent</i>. It is thread safe but does
//     * not freeze the directory while iterating, so it may (or may not)
//     * reflect updates to the directory that occur after returning from this
//     * method.
//     *
//     * <p> The returned stream encapsulates a {@link DirectoryStream}.
//     * If timely disposal of file system resources is required, the
//     * {@code try}-with-resources construct should be used to ensure that the
//     * stream's {@link Stream#close close} method is invoked after the stream
//     * operations are completed.
//     *
//     * <p> Operating on a closed stream behaves as if the end of stream
//     * has been reached. Due to read-ahead, one or more elements may be
//     * returned after the stream has been closed.
//     *
//     * <p> If an {@link IOException} is thrown when accessing the directory
//     * after this method has returned, it is wrapped in an {@link
//     * UncheckedIOException} which will be thrown from the method that caused
//     * the access to take place.
//     *
//     * @param   dir  The path to the directory
//     *
//     * @return  The {@code Stream} describing the content of the
//     *          directory
//     *
//     * @throws  NotDirectoryException
//																																					 
//     *          if the file is no directory <i>(optional specific exception)</i>
//     * @throws  IOException
//     *          if an I/O error occurs when opening the directory
//     * @throws  SecurityException
//																																							
//																																									
//     *          If a security manager denies read access to the directory.
//     *
//     * @see     #newDirectoryStream(Path)
//     * @since   1.8
//     */
// 
////    public static Stream<Path> list(Path dir) throws IOException {
////        DirectoryStream<Path> ds = java.nio.file.Files.newDirectoryStream(dir);
////        try {
////            final Iterator<Path> delegate = ds.iterator();
////
////            // Re-wrap DirectoryIteratorException to UncheckedIOException
////            Iterator<Path> it = new Iterator<Path>() {
////                @Override
////                public boolean hasNext() {
////                    try {
////                        return delegate.hasNext();
////                    } catch (DirectoryIteratorException e) {
////                        throw new UncheckedIOException(e.getCause());
////                    }
////                }
////                @Override
////                public Path next() {
////                    try {
////                        return delegate.next();
////                    } catch (DirectoryIteratorException e) {
////                        throw new UncheckedIOException(e.getCause());
////                    }
////                }
////            };
////
////            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(it, Spliterator.DISTINCT), false)
////                                .onClose(asUncheckedRunnable(ds));
////        } catch (Error|RuntimeException e) {
////            try {
////                ds.close();
////            } catch (IOException ex) {
////                try {
////                    e.addSuppressed(ex);
////                } catch (Throwable ignore) {}
////            }
////            throw e;
////        }
////    }
//	private Stream<Path> containingFilesStream() throws NotDirectoryException,IOException,UncheckedIOException,SecurityException {
//		 /**
//	     * Opens a directory, returning a {@link DirectoryStream} to iterate over
//	     * all entries in the directory. The elements returned by the directory
//	     * stream's {@link DirectoryStream#iterator iterator} are of type {@code
//	     * Path}, each one representing an entry in the directory. The {@code Path}
//	     * objects are obtained as if by {@link Path#resolve(Path) resolving} the
//	     * name of the directory entry against {@code dir}.
//	     *
//	     * <p> When not using the try-with-resources construct, then directory
//	     * stream's {@code close} method should be invoked after iteration is
//	     * completed so as to free any resources held for the open directory.
//	     *
//	     * <p> When an implementation supports operations on entries in the
//	     * directory that execute in a race-free manner then the returned directory
//	     * stream is a {@link SecureDirectoryStream}.
//	     *
//	     * @param   dir
//	     *          the path to the directory
//	     *
//	     * @return  a new and open {@code DirectoryStream} object
//	     *
//	     * @throws  NotDirectoryException
//	     *          if the file could not otherwise be opened because it is not
//	     *          a directory <i>(optional specific exception)</i>
//	     * @throws  IOException
//	     *          if an I/O error occurs
//	     * @throws  SecurityException
//	     *          In the case of the default provider, and a security manager is
//	     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//	     *          method is invoked to check read access to the directory.
//	     */
////	    public static DirectoryStream<Path> newDirectoryStream(Path dir)
////	        throws IOException
////	    {
//		DirectoryStream	directoryStream= dataForkPath.getFileSystem().provider().newDirectoryStream(dataForkPath, AcceptAllFilter.FILTER);
////	    }
////		DirectoryStream<Path> directoryStream = java.nio.file.Files.newDirectoryStream(dataForkPath);
////		try(DirectoryStream<Path> directoryStream = java.nio.file.Files.newDirectoryStream(dataForkPath);) {
//			final Iterator<Path> iterator = directoryStream.iterator();
//
//																																
//
//			// Re-wrap DirectoryIteratorException to UncheckedIOException
//			Iterator<Path> it = new Iterator<Path>() {
//				@Override
//				public boolean hasNext() {
//					try {
//						return iterator.hasNext();
//					} catch (DirectoryIteratorException e) {
//						throw new UncheckedIOException(e.getCause());
//					}
//				}
//				@Override
//				public Path next() {
//					try {
//						return iterator.next();
//					} catch (DirectoryIteratorException e) {
//						throw new UncheckedIOException(e.getCause());
//					}
//				}
//			};
//
//			return StreamSupport.stream(Spliterators.spliteratorUnknownSize(it, Spliterator.DISTINCT), false)
//					.onClose( () -> {
//						//From java.nio.file.Files: Convert a Closeable to a Runnable by converting checked IOException to UncheckedIOException
//						try {
//							directoryStream.close();
//						} catch (IOException e) {
//							throw new UncheckedIOException(e);
//						}
//					} );
//	}
//    /**
//			 * Stream<Path> java.nio.file.Files.list(Path dir) throws IOException
//		Return a lazily populated Stream, the elements of which are the entries in the directory. The listing is not recursive.
//		The elements of the stream are Path objects that are obtained as if by resolving the name of the directory entry against dir. Some file systems maintain special links to the directory itself and the directory's parent directory. Entries representing these links are not included.
//		The stream is weakly consistent. It is thread safe but does not freeze the directory while iterating, so it may (or may not) reflect updates to the directory that occur after returning from this method.
//		The returned stream encapsulates a DirectoryStream. If timely disposal of file system resources is required, the try-with-resources construct should be used to ensure that the stream's close method is invoked after the stream operations are completed.
//		Operating on a closed stream behaves as if the end of stream has been reached. Due to read-ahead, one or more elements may be returned after the stream has been closed.
//		If an IOException is thrown when accessing the directory after this method has returned, it is wrapped in an UncheckedIOException which will be thrown from the method that caused the access to take place.
//		Parameters:
//		dir The path to the directory
//		Returns:
//		The Stream describing the content of the directory
//		Throws:
//		NotDirectoryException - if the file could not otherwise be opened because it is not a directory (optional specific exception)
//		IOException - if an I/O error occurs when opening the directory
//		SecurityException - In the case of the default provider, and a security manager is installed, the checkRead method is invoked to check read access to the directory.
//		Since:
//		1.8
//		See Also:
//		newDirectoryStream(Path)
//			 * @throws IOException,SecurityException,NotDirectoryException
//			 * 		NoSuchFileException - if this FileTMJ does not exist and so cannot contain anything
//			 */
////			public Stream<FileTMJ> containingFilesStream() throws IOException,SecurityException,NotDirectoryException,NoSuchFileException{
////				return java.nio.file.Files.list(dataForkPath).map(path->new FileTMJ(path));
////			}
//	/**
//     * Returns an array of abstract pathnames denoting the files and
//     * directories in the directory denoted by this abstract pathname that
//     * satisfy the specified filter.  The behavior of this method is the same
//     * as that of the {@link #listFiles()} method, except that the pathnames in
//     * the returned array must satisfy the filter.  If the given {@code filter}
//     * is {@code null} then all pathnames are accepted.  Otherwise, a pathname
//     * satisfies the filter if and only if the value {@code true} results when
//     * the {@link FilenameFilter#accept
//     * FilenameFilter.accept(File,&nbsp;String)} method of the filter is
//     * invoked on this abstract pathname and the name of a file or directory in
//     * the directory that it denotes.
//     *
//     * @param  filter
//     *         A filename filter
//     *
//     * @return  An array of abstract pathnames denoting the files and
//     *          directories in the directory denoted by this abstract pathname.
//     *          The array will be empty if the directory is empty.  Returns
//     *          {@code null} if this abstract pathname does not denote a
//     *          directory, or if an I/O error occurs.
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its {@link
//     *          SecurityManager#checkRead(String)} method denies read access to
//     *          the directory
//     *
//     * @since  1.2
//     * @see java.nio.file.Files#newDirectoryStream(Path,String)
//     */
////	public FileTMJ[] containingFiles(FilenameFilter filter) throws NotDirectoryException, NoSuchFileException, SecurityException, IOException {
////		return java.nio.file.Files.list(dataForkPath)
////			.filter(path->filter.accept(path.toFile(), path.getFileName().toString()))
////			.toArray(FileTMJ[]::new);
////	}
////	public FileTMJ[] containingFiles(FileFilter filter) throws NotDirectoryException, NoSuchFileException, SecurityException, IOException {
////		return containingFilesStream()
////			.filter( path->filter.accept( path.toFile() ))
////			.toArray(FileTMJ[]::new);
////	}
////	public Stream<FileTMJ> containingFilesStream(FilenameFilter filter) throws IOException,SecurityException,NotDirectoryException,NoSuchFileException{
////		return java.nio.file.Files.list(dataForkPath)
////			.filter(path->filter.accept(path.toFile(), path.getFileName().toString()))
////			.map(path->new FileTMJ(path));
////	}  
////	public Stream<FileTMJ> containingFilesStream(FileFilter filter) throws IOException,SecurityException,NotDirectoryException,NoSuchFileException{
////		return java.nio.file.Files.list(dataForkPath)
////			.filter( path->filter.accept( path.toFile() ))
////			.map(path->new FileTMJ(path));
////	}
//	
//
//
//   
//  
//    /**
//     * Return a {@code Stream} that is lazily populated with {@code
//     * Path} by walking the file tree rooted at a given starting file.  The
//     * file tree is traversed <em>depth-first</em>, the elements in the stream
//     * are {@link Path} objects that are obtained as if by {@link
//     * Path#resolve(Path) resolving} the relative path against {@code start}.
//     *
//     * <p> The {@code stream} walks the file tree as elements are consumed.
//     * The {@code Stream} returned is guaranteed to have at least one
//     * element, the starting file itself. For each file visited, the stream
//     * attempts to read its {@link BasicFileAttributes}. If the file is a
//     * directory and can be opened successfully, entries in the directory, and
//     * their <em>descendants</em> will follow the directory in the stream as
//     * they are encountered. When all entries have been visited, then the
//     * directory is closed. The file tree walk then continues at the next
//     * <em>sibling</em> of the directory.
//     *
//     * <p> The stream is <i>weakly consistent</i>. It does not freeze the
//     * file tree while iterating, so it may (or may not) reflect updates to
//     * the file tree that occur after returned from this method.
//     *
//     * <p> By default, symbolic links are not automatically followed by this
//     * method. If the {@code options} parameter contains the {@link
//     * FileVisitOption#FOLLOW_LINKS FOLLOW_LINKS} option then symbolic links are
//     * followed. When following links, and the attributes of the target cannot
//     * be read, then this method attempts to get the {@code BasicFileAttributes}
//     * of the link.
//     *
//     * <p> If the {@code options} parameter contains the {@link
//     * FileVisitOption#FOLLOW_LINKS FOLLOW_LINKS} option then the stream keeps
//     * track of directories visited so that cycles can be detected. A cycle
//     * arises when there is an entry in a directory that is an ancestor of the
//     * directory. Cycle detection is done by recording the {@link
//     * java.nio.file.attribute.BasicFileAttributes#fileKey file-key} of directories,
//     * or if file keys are not available, by invoking the {@link #isSameFile
//     * isSameFile} method to test if a directory is the same file as an
//     * ancestor. When a cycle is detected it is treated as an I/O error with
//     * an instance of {@link FileSystemLoopException}.
//     *
//     * <p> The {@code maxDepth} parameter is the maximum number of levels of
//     * directories to visit. A value of {@code 0} means that only the starting
//     * file is visited, unless denied by the security manager. A value of
//     * {@link Integer#MAX_VALUE MAX_VALUE} may be used to indicate that all
//     * levels should be visited.
//     *
//     * <p> When a security manager is installed and it denies access to a file
//     * (or directory), then it is ignored and not included in the stream.
//     *
//     * <p> The returned stream encapsulates one or more {@link DirectoryStream}s.
//     * If timely disposal of file system resources is required, the
//     * {@code try}-with-resources construct should be used to ensure that the
//     * stream's {@link Stream#close close} method is invoked after the stream
//     * operations are completed.  Operating on a closed stream will result in an
//     * {@link java.lang.IllegalStateException}.
//     *
//     * <p> If an {@link IOException} is thrown when accessing the directory
//     * after this method has returned, it is wrapped in an {@link
//     * UncheckedIOException} which will be thrown from the method that caused
//     * the access to take place.
//     *
//     * @param   start
//     *          the starting file
//     * @param   maxDepth
//     *          the maximum number of directory levels to visit
//     * @param   options
//     *          options to configure the traversal
//     *
//     * @return  the {@link Stream} of {@link Path}
//     *
//     * @throws  IllegalArgumentException
//     *          if the {@code maxDepth} parameter is negative
//     * @throws  SecurityException
//     *          If the security manager denies access to the starting file.
//     *          In the case of the default provider, the {@link
//     *          SecurityManager#checkRead(String) checkRead} method is invoked
//     *          to check read access to the directory.
//     * @throws  IOException
//     *          if an I/O error is thrown when accessing the starting file.
//     * @since   1.8
//     */
//    public static Stream<FileTMJ> walk(int maxDepth, FileVisitOption... options) throws IOException{
//		throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
////        FileTreeIterator iterator = new FileTreeIterator(start, maxDepth, options);
////        try {
////            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.DISTINCT), false)
////                                .onClose(iterator::close)
////                                .map(entry -> entry.file());
////        } catch (Error|RuntimeException e) {
////            iterator.close();
////            throw e;
////        }
//    }
//
//    /**
//     * Return a {@code Stream} that is lazily populated with {@code
//     * Path} by walking the file tree rooted at a given starting file.  The
//     * file tree is traversed <em>depth-first</em>, the elements in the stream
//     * are {@link Path} objects that are obtained as if by {@link
//     * Path#resolve(Path) resolving} the relative path against {@code start}.
//     *
//     * <p> This method works as if invoking it were equivalent to evaluating the
//     * expression:
//     * <blockquote><pre>
//     * walk(start, Integer.MAX_VALUE, options)
//     * </pre></blockquote>
//     * In other words, it visits all levels of the file tree.
//     *
//     * <p> The returned stream encapsulates one or more {@link DirectoryStream}s.
//     * If timely disposal of file system resources is required, the
//     * {@code try}-with-resources construct should be used to ensure that the
//     * stream's {@link Stream#close close} method is invoked after the stream
//     * operations are completed.  Operating on a closed stream will result in an
//     * {@link java.lang.IllegalStateException}.
//     *
//     * @param   start
//     *          the starting file
//     * @param   options
//     *          options to configure the traversal
//     *
//     * @return  the {@link Stream} of {@link Path}
//     *
//     * @throws  SecurityException
//     *          If the security manager denies access to the starting file.
//     *          In the case of the default provider, the {@link
//     *          SecurityManager#checkRead(String) checkRead} method is invoked
//     *          to check read access to the directory.
//     * @throws  IOException
//     *          if an I/O error is thrown when accessing the starting file.
//     *
//     * @see     #walk(Path, int, FileVisitOption...)
//     * @since   1.8
//     */
//    public static Stream<FileTMJ> walk(FileVisitOption... options) throws IOException {
//		throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
////        return walk(start, Integer.MAX_VALUE, options);
//    }
//
//    /**
//     * Return a {@code Stream} that is lazily populated with {@code
//     * Path} by searching for files in a file tree rooted at a given starting
//     * file.
//     *
//     * <p> This method walks the file tree in exactly the manner specified by
//     * the {@link #walk walk} method. For each file encountered, the given
//     * {@link BiPredicate} is invoked with its {@link Path} and {@link
//     * BasicFileAttributes}. The {@code Path} object is obtained as if by
//     * {@link Path#resolve(Path) resolving} the relative path against {@code
//     * start} and is only included in the returned {@link Stream} if
//     * the {@code BiPredicate} returns true. Compare to calling {@link
//     * java.util.stream.Stream#filter filter} on the {@code Stream}
//     * returned by {@code walk} method, this method may be more efficient by
//     * avoiding redundant retrieval of the {@code BasicFileAttributes}.
//     *
//     * <p> The returned stream encapsulates one or more {@link DirectoryStream}s.
//     * If timely disposal of file system resources is required, the
//     * {@code try}-with-resources construct should be used to ensure that the
//     * stream's {@link Stream#close close} method is invoked after the stream
//     * operations are completed.  Operating on a closed stream will result in an
//     * {@link java.lang.IllegalStateException}.
//     *
//     * <p> If an {@link IOException} is thrown when accessing the directory
//     * after returned from this method, it is wrapped in an {@link
//     * UncheckedIOException} which will be thrown from the method that caused
//     * the access to take place.
//     *
//     * @param   start
//     *          the starting file
//     * @param   maxDepth
//     *          the maximum number of directory levels to search
//     * @param   matcher
//     *          the function used to decide whether a file should be included
//     *          in the returned stream
//     * @param   options
//     *          options to configure the traversal
//     *
//     * @return  the {@link Stream} of {@link Path}
//     *
//     * @throws  IllegalArgumentException
//     *          if the {@code maxDepth} parameter is negative
//     * @throws  SecurityException
//     *          If the security manager denies access to the starting file.
//     *          In the case of the default provider, the {@link
//     *          SecurityManager#checkRead(String) checkRead} method is invoked
//     *          to check read access to the directory.
//     * @throws  IOException
//     *          if an I/O error is thrown when accessing the starting file.
//     *
//     * @see     #walk(Path, int, FileVisitOption...)
//     * @since   1.8
//     */
////    public static Stream<FileTMJ> find(int maxDepth, BiPredicate<FileTMJ, BasicFileAttributes> matcher, FileVisitOption... options) throws IOException {
////		throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
//////        FileTreeIterator iterator = new FileTreeIterator(start, maxDepth, options);
//////        try {
//////            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.DISTINCT), false)
//////                                .onClose(iterator::close)
//////                                .filter(entry -> matcher.test(entry.file(), entry.attributes()))
//////                                .map(entry -> entry.file());
//////        } catch (Error|RuntimeException e) {
//////            iterator.close();
//////            throw e;
//////        }
////    }
//    
//    
//    
//    
//    
//    /**
//     * Reads the target of a symbolic link <i>(optional operation)</i>.
//     *
//     * <p> If the file system supports <a href="package-summary.html#links">symbolic
//     * links</a> then this method is used to read the target of the link, failing
//     * if the file is not a symbolic link. The target of the link need not exist.
//     * The returned {@code Path} object will be associated with the same file
//     * system as {@code link}.
//     *
//     * @param   link
//     *          the path to the symbolic link
//     *
//     * @return  a {@code Path} object representing the target of the link
//     *
//     * @throws  UnsupportedOperationException
//     *          if the implementation does not support symbolic links
//     * @throws  NotLinkException
//     *          if the target could otherwise not be read because the file
//     *          is not a symbolic link <i>(optional specific exception)</i>
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          If a security manager denies to read the link.
//																																						
//																																						
//     */
//    public FileTMJ readSymbolicLinkTarget() throws IOException, UnsupportedOperationException,NotLinkException,SecurityException {
//																																
//    	return new FileTMJ(dataForkPath.getFileSystem().provider().readSymbolicLink(dataForkPath));
//    }
//    
//    /**
//     * From java.nio.file.Files
//     * Tests whether a file is a symbolic link.
//     *
//     * <p> Where it is required to distinguish an I/O exception from the case
//     * that the file is not a symbolic link then the file attributes can be
//     * read with the {@link #readAttributes(Path,Class,LinkOption[])
//     * readAttributes} method and the file type tested with the {@link
//     * BasicFileAttributes#isSymbolicLink} method.
//     *
//     * @param   path  The path to the file
//     *
//     * @return  {@code true} if the file is a symbolic link; {@code false} if
//     *          the file does not exist, is not a symbolic link, or it cannot
//     *          be determined if the file is a symbolic link or not.
//     *
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, its {@link SecurityManager#checkRead(String) checkRead}
//     *          method denies read access to the file.
//     */
//    public boolean isSymbolicLink() {
//        try {
//        	return readBasicFileAttributes(dataForkPath,LinkOption.NOFOLLOW_LINKS).isSymbolicLink();
//        } catch (IOException ioe) {
//            return false;
//        }
//    }
//    
//   
//		
//		
//    
//	/**
//	 * From java.nio.file.Files.exists
//	 * Returns the size of a file (in bytes). 
//	 * The size may differ from the actual size on the file system due to compression, support for sparse files, or other reasons. 
//	 * The size of files that are not regular files is implementation specific and therefore unspecified.
//     * @param   options
//     *          options indicating how symbolic links are handled
//     * 
//	 * @return size in bytes, unspecified if directory
//	 * @throws SecurityException - If a security manager exists and its java.lang.SecurityManager.checkRead(java.lang.String) method denies read access to the file
//	 * @throws IOException - if an I/O error occurs
//	 * 
//	 * long java.nio.file.Files.size(Path path) throws IOException
//	 */
//	public long sizeBytes() throws SecurityException, IOException{
//		return sizeBytes(LinkOption.NOFOLLOW_LINKS);
//	}
//	public long sizeBytes(LinkOption...linkOptions) throws SecurityException, IOException{
//		long dataSize=0;
//		long resourceSize=0;
////		if(!exists(linkOptions)){
////		}else {
//			// java.nio.file.Files.exists tries to read file attributes and interpretes an error as not-exists!
//			// So we don't need that separated.
//			try {
//				dataSize=readBasicFileAttributes(dataForkPath, linkOptions).size(); // file exists
//			} catch (IOException e2) { // does not exist or unable to determine if file exists
//				dataSize=-1;
//			}
//			try {
//				resourceSize=readBasicFileAttributes(resourceForkPath, linkOptions).size(); // file exists
//			} catch (IOException e2) { // does not exist or unable to determine if file exists
//				resourceSize=-1;
//			}
//			if(0>dataSize) {
//				if(0>resourceSize) {
//					throw new FileNotFoundException();
//				}else {
//					return resourceSize;
//				}
//			}else if(0>resourceSize){
//				return dataSize;
//			}else {
//				return dataSize+resourceSize;
//			}
////		}
////		if(!java.nio.file.Files.exists(dataForkPath, linkOptions) && !java.nio.file.Files.exists(resourceForkPath,linkOptions)) {
////			throw new FileNotFoundException();
////		}else {
////			if(java.nio.file.Files.exists(dataForkPath, linkOptions)) {
////				dataSize=java.nio.file.Files.size(dataForkPath);
////			}
////			if(java.nio.file.Files.exists(resourceForkPath,linkOptions)) {
////				resourceSize=java.nio.file.Files.size(resourceForkPath);
////			}
////		}
////		return dataSize+resourceSize;
//	}
//    
//    
//	/** returns true, if the object's name was sucessfully changed or need not to be changed, false otherwise. 
//	 * nameNew must not be null
//	 * @throws IOException 
//	 * */
//	public boolean setName(String nameNew) throws IOException {
//		return setName(Paths.get(nameNew));
//	}
//	public boolean setName(Path nameNew) throws IOException {
////			if(dataForkPath.getFileName().toString().equals(nameNew)){
////				return true;
////			}
//			Path parent=dataForkPath.getParent();
//			// try{
//			Path dest=parent.resolve(nameNew);
//			// }
//			
//			try {
//				readBasicFileAttributes(dest, LinkOption.NOFOLLOW_LINKS); // test for any attribute
//				throw new FileAlreadyExistsException(dest.toString());
//			} catch (IOException e) { // does not exist or unable to determine if file exists
//			}
//
//			try {
//				dataForkPath=moveTo(dest);//Dies wirkt sich auch korrekt auf den Ressource Fork aus, jedenfalls unter macOS 10.13.6 und HFS+j
//			} catch (IOException e) {
//				e.printStackTrace();
//				throw e;
//			}
////			 }catch(Error e){ the error_message number the error_number
////				if( the error_number is -59 ){
////					the error_message="This name contains improper characters, such as a colon (:)."
////				}else{ // the suggested name is too long
////					the error_message=error_message // "The name is more than 31 characters long."
////				}
////				tell me=display dialog the error_message default answer nameNew buttons {"Cancel", "Skip", "OK"} default button 3
////				copy the result as list={nameNew, button_pressed}
////				if( the button_pressed is "Skip" ){ return 0
////				my setFilesystemObjectName(filesystemObject, nameNew)
////			}
//		return false;
//		}
//	
//	
//    /**
//     * Renames the file denoted by this abstract pathname.
//     *
//     * <p> Many aspects of the behavior of this method are inherently
//     * platform-dependent: The rename operation might not be able to move a
//     * file from one filesystem to another, it might not be atomic, and it
//     * might not succeed if a file with the destination abstract pathname
//     * already exists.  The return value should always be checked to make sure
//     * that the rename operation was successful.
//     *
//     * <p> Note that the {@link java.nio.file.Files} class defines the {@link
//     * java.nio.file.Files#move move} method to move or rename a file in a
//     * platform independent manner.
//     *
//     * @param  dest  The new abstract pathname for the named file
//     *
//     * @return  <code>true</code> if and only if the renaming succeeded;
//     *          <code>false</code> otherwise
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its <code>{@link
//     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
//     *          method denies write access to either the old or new pathnames
//     *
//     * @throws  NullPointerException
//     *          If parameter <code>dest</code> is <code>null</code>
//     */
//    /**
//     * Move or rename a file to a target file.
//     *
//     * <p> By default, this method attempts to move the file to the target
//     * file, failing if the target file exists except if the source and
//     * target are the {@link #isSameFile same} file, in which case this method
//     * has no effect. If the file is a symbolic link then the symbolic link
//     * itself, not the target of the link, is moved. This method may be
//     * invoked to move an empty directory. In some implementations a directory
//     * has entries for special files or links that are created when the
//     * directory is created. In such implementations a directory is considered
//     * empty when only the special entries exist. When invoked to move a
//     * directory that is not empty then the directory is moved if it does not
//     * require moving the entries in the directory.  For example, renaming a
//     * directory on the same {@link FileStore} will usually not require moving
//     * the entries in the directory. When moving a directory requires that its
//     * entries be moved then this method fails (by throwing an {@code
//     * IOException}). To move a <i>file tree</i> may involve copying rather
//     * than moving directories and this can be done using the {@link
//     * #copy copy} method in conjunction with the {@link
//     * #walkFileTree java.nio.file.Files.walkFileTree} utility method.
//     *
//     * <p> The {@code options} parameter may include any of the following:
//     *
//     * <table border=1 cellpadding=5 summary="">
//     * <tr> <th>Option</th> <th>Description</th> </tr>
//     * <tr>
//     *   <td> {@link StandardCopyOption#REPLACE_EXISTING REPLACE_EXISTING} </td>
//     *   <td> If the target file exists, then the target file is replaced if it
//     *     is not a non-empty directory. If the target file exists and is a
//     *     symbolic link, then the symbolic link itself, not the target of
//     *     the link, is replaced. </td>
//     * </tr>
//     * <tr>
//     *   <td> {@link StandardCopyOption#ATOMIC_MOVE ATOMIC_MOVE} </td>
//     *   <td> The move is performed as an atomic file system operation and all
//     *     other options are ignored. If the target file exists then it is
//     *     implementation specific if the existing file is replaced or this method
//     *     fails by throwing an {@link IOException}. If the move cannot be
//     *     performed as an atomic file system operation then {@link
//     *     AtomicMoveNotSupportedException} is thrown. This can arise, for
//     *     example, when the target location is on a different {@code FileStore}
//     *     and would require that the file be copied, or target location is
//     *     associated with a different provider to this object. </td>
//     * </table>
//     *
//     * <p> An implementation of this interface may support additional
//     * implementation specific options.
//     *
//     * <p> Moving a file will copy the {@link
//     * BasicFileAttributes#lastModifiedTime last-modified-time} to the target
//     * file if supported by both source and target file stores. Copying of file
//     * timestamps may result in precision loss. An implementation may also
//     * attempt to copy other file attributes but is not required to fail if the
//     * file attributes cannot be copied. When the move is performed as
//     * a non-atomic operation, and an {@code IOException} is thrown, then the
//     * state of the files is not defined. The original file and the target file
//     * may both exist, the target file may be incomplete or some of its file
//     * attributes may not been copied from the original file.
//     *
//     * <p> <b>Usage Examples:</b>
//     * Suppose we want to rename a file to "newname", keeping the file in the
//     * same directory:
//     * <pre>
//     *     Path source = ...
//     *     java.nio.file.Files.move(source, source.resolveSibling("newname"));
//     * </pre>
//     * Alternatively, suppose we want to move a file to new directory, keeping
//     * the same file name, and replacing any existing file of that name in the
//     * directory:
//     * <pre>
//     *     Path source = ...
//     *     Path newdir = ...
//     *     java.nio.file.Files.move(source, newdir.resolve(source.getFileName()), REPLACE_EXISTING);
//     * </pre>
//     *
//     * @param   target
//     *          the path to the target file (may be associated with a different
//     *          provider to the source path)
//     * @param   options
//     *          options specifying how the move should be done
//     *
//     * @return  the path to the target file
//     *
//     * @throws  UnsupportedOperationException
//     *          if the array contains a copy option that is not supported
//     * @throws  FileAlreadyExistsException
//     *          if the target file exists but cannot be replaced because the
//     *          {@code REPLACE_EXISTING} option is not specified <i>(optional
//     *          specific exception)</i>
//     * @throws  DirectoryNotEmptyException
//     *          the {@code REPLACE_EXISTING} option is specified but the file
//     *          cannot be replaced because it is a non-empty directory
//     *          <i>(optional specific exception)</i>
//     * @throws  AtomicMoveNotSupportedException
//     *          if the options array contains the {@code ATOMIC_MOVE} option but
//     *          the file cannot be moved as an atomic file system operation.
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkWrite(String) checkWrite}
//     *          method is invoked to check write access to both the source and
//     *          target file.
//     */
////        public boolean moveTo(File dest, CopyOption... options)throws IOException {
////        FileSystemProvider provider = provider(source);
////        if (provider(target) == provider) {
////            // same provider
////            provider.move(source, target, options);
////        } else {
////            // different providers
////            copyMoveHelper_moveToForeignTarget(source, target, options);
////        }
////        return target;
////        SecurityManager security = System.getSecurityManager();
////        if (security != null) {
////            security.checkWrite(path);
////            security.checkWrite(dest.path);
////        }
////        if (dest == null) {
////            throw new NullPointerException();
////        }
////        if (this.isInvalid() || dest.isInvalid()) {
////            return false;
////        }
////        return fs.rename(this, dest);
//		public FileTMJ moveTo(FileTMJ destination) throws IOException {
//			Path path=moveTo(destination.dataForkPath);
//			return new FileTMJ(path);
//////			public static Path move(Path source, Path target, CopyOption... options) throws IOException  {
////			        FileSystemProvider provider = dataForkPath.getFileSystem().provider();
////			        if (destination.getFileSystem().provider() == provider) {
////			            // same provider
////			            provider.move(dataForkPath, destination.dataForkPath, StandardCopyOption.ATOMIC_MOVE);
////			        } else {
////			            // different providers
//////			        	copyMoveHelper_moveToForeignTarget(dataForkPath, destination, StandardCopyOption.ATOMIC_MOVE);
////			        	copyMoveHelper_moveToForeignTarget(dataForkPath, destination, StandardCopyOption.ATOMIC_MOVE);
////			 
////     copyToForeignTarget(dataForkPath, destination.dataForkPath, convertMoveToCopyOptions(StandardCopyOption.ATOMIC_MOVE));
////     java.nio.file.Files.delete(dataForkPath);
//// }
////			        }
////			        return new FileTMJ(destination);
//////			    }
//		}
//		
//		
//		
//		
//		
//		
//		 /**
//	     * Move or rename a file to a target file.
//	     *
//	     * <p> By default, this method attempts to move the file to the target
//	     * file, failing if the target file exists except if the source and
//	     * target are the {@link #isSameFile same} file, in which case this method
//	     * has no effect. If the file is a symbolic link then the symbolic link
//	     * itself, not the target of the link, is moved. This method may be
//	     * invoked to move an empty directory. In some implementations a directory
//	     * has entries for special files or links that are created when the
//	     * directory is created. In such implementations a directory is considered
//	     * empty when only the special entries exist. When invoked to move a
//	     * directory that is not empty then the directory is moved if it does not
//	     * require moving the entries in the directory.  For example, renaming a
//	     * directory on the same {@link FileStore} will usually not require moving
//	     * the entries in the directory. When moving a directory requires that its
//	     * entries be moved then this method fails (by throwing an {@code
//	     * IOException}). To move a <i>file tree</i> may involve copying rather
//	     * than moving directories and this can be done using the {@link
//	     * #copy copy} method in conjunction with the {@link
//	     * #walkFileTree Files.walkFileTree} utility method.
//	     *
//	     * <p> The {@code options} parameter may include any of the following:
//	     *
//	     * <table border=1 cellpadding=5 summary="">
//	     * <tr> <th>Option</th> <th>Description</th> </tr>
//	     * <tr>
//	     *   <td> {@link StandardCopyOption#REPLACE_EXISTING REPLACE_EXISTING} </td>
//	     *   <td> If the target file exists, then the target file is replaced if it
//	     *     is not a non-empty directory. If the target file exists and is a
//	     *     symbolic link, then the symbolic link itself, not the target of
//	     *     the link, is replaced. </td>
//	     * </tr>
//	     * <tr>
//	     *   <td> {@link StandardCopyOption#ATOMIC_MOVE ATOMIC_MOVE} </td>
//	     *   <td> The move is performed as an atomic file system operation and all
//	     *     other options are ignored. If the target file exists then it is
//	     *     implementation specific if the existing file is replaced or this method
//	     *     fails by throwing an {@link IOException}. If the move cannot be
//	     *     performed as an atomic file system operation then {@link
//	     *     AtomicMoveNotSupportedException} is thrown. This can arise, for
//	     *     example, when the target location is on a different {@code FileStore}
//	     *     and would require that the file be copied, or target location is
//	     *     associated with a different provider to this object. </td>
//	     * </table>
//	     *
//	     * <p> An implementation of this interface may support additional
//	     * implementation specific options.
//	     *
//	     * <p> Moving a file will copy the {@link
//	     * BasicFileAttributes#lastModifiedTime last-modified-time} to the target
//	     * file if supported by both source and target file stores. Copying of file
//	     * timestamps may result in precision loss. An implementation may also
//	     * attempt to copy other file attributes but is not required to fail if the
//	     * file attributes cannot be copied. When the move is performed as
//	     * a non-atomic operation, and an {@code IOException} is thrown, then the
//	     * state of the files is not defined. The original file and the target file
//	     * may both exist, the target file may be incomplete or some of its file
//	     * attributes may not been copied from the original file.
//	     *
//	     * <p> <b>Usage Examples:</b>
//	     * Suppose we want to rename a file to "newname", keeping the file in the
//	     * same directory:
//	     * <pre>
//	     *     Path source = ...
//	     *     Files.move(source, source.resolveSibling("newname"));
//	     * </pre>
//	     * Alternatively, suppose we want to move a file to new directory, keeping
//	     * the same file name, and replacing any existing file of that name in the
//	     * directory:
//	     * <pre>
//	     *     Path source = ...
//	     *     Path newdir = ...
//	     *     Files.move(source, newdir.resolve(source.getFileName()), REPLACE_EXISTING);
//	     * </pre>
//	     *
//‚	     * @param   target
//	     *          the path to the target file (may be associated with a different
//	     *          provider to the source path)
//	     * @param   options
//	     *          options specifying how the move should be done
//	     *
//	     * @return  the path to the target file
//	     *
//	     * @throws  UnsupportedOperationException
//	     *          if the array contains a copy option that is not supported
//	     * @throws  FileAlreadyExistsException
//	     *          if the target file exists but cannot be replaced because the
//	     *          {@code REPLACE_EXISTING} option is not specified <i>(optional
//	     *          specific exception)</i>
//	     * @throws  DirectoryNotEmptyException
//	     *          the {@code REPLACE_EXISTING} option is specified but the file
//	     *          cannot be replaced because it is a non-empty directory
//	     *          <i>(optional specific exception)</i>
//	     * @throws  AtomicMoveNotSupportedException
//	     *          if the options array contains the {@code ATOMIC_MOVE} option but
//	     *          the file cannot be moved as an atomic file system operation.
//	     * @throws  IOException
//	     *          if an I/O error occurs
//	     * @throws  SecurityException
//	     *          In the case of the default provider, and a security manager is
//	     *          installed, the {@link SecurityManager#checkWrite(String) checkWrite}
//	     *          method is invoked to check write access to both the source and
//	     *          target file.
//	     */
//		/**
//		 * Converts the given array of options for moving a file to options suitable
//		 * for copying the file when a move is implemented as copy + delete.
//		 */  
//		private Path moveTo(Path target) throws UnsupportedOperationException,FileAlreadyExistsException,DirectoryNotEmptyException,AtomicMoveNotSupportedException,IOException,SecurityException   {
//	        FileSystemProvider provider = dataForkPath.getFileSystem().provider();
//	        if (target.getFileSystem().provider() == provider) { // same provider
//	            provider.move(dataForkPath, target, StandardCopyOption.ATOMIC_MOVE);
//	        } else {
//	        	// different providers -> copy+delete           
//	        	CopyOption[] newOptions = new CopyOption[2];
////	           	throw new AtomicMoveNotSupportedException(null, null, "Atomic move between providers is not supported");
//	        	copyMoveHelper_copyToForeignTarget(dataForkPath, target, new CopyOption[] {LinkOption.NOFOLLOW_LINKS,StandardCopyOption.COPY_ATTRIBUTES});
//	        	dataForkPath.getFileSystem().provider().delete(dataForkPath);
//	        }
//	        return target;
//	    }
//
//    
//    
//    /**
//     * Remove this file/directory from the file system.
//     * 
//     * In this is a directory, its contents will be removed, first. Consequently this 
//     * method may not be atomic with respect to other file system operations.
//	 * If this is a symbolic link then the link itself is deleted, not its target.
//     *
//     * @return  {@code true} – if the file/directory was successfully deleted,
//     *          {@code false} – if it could not be deleted because it did not exist
//     * @throws  IOException – if an I/O error occurs
//     * @throws  SecurityException – if a security manager denies the operation
//     */
//	    public boolean removeIfExists() throws SecurityException,IOException{
//	    		return removeIfExists(false);
//	    }
//    public boolean removeIfExists(boolean ignoreErrors) throws SecurityException,IOException{
//    	if(isDirectory()) {
//    		removeDirContent(ignoreErrors);
//    	}
//    	return dataForkPath.getFileSystem().provider().deleteIfExists(dataForkPath);
////    	return java.nio.file.Files.deleteIfExists(dataForkPath);
////		 * java.nio.file.Files.delete(path);
////       * = path.getFileSystem().provider().delete(path);
////		 * boolean java.nio.file.Files.deleteIfExists(path);
////		 * = path.getFileSystem().provider().deleteIfExists(path)
////	     * May require to examine if file is a directory. A directory must be empty.
////	     * This method can be used with the {@link #walkFileTree walkFileTree}
////	     * method to delete a directory and all entries in the directory, or an
////	     * entire <i>file-tree</i> where required.
////    	 * Consequently this method may not be atomic with respect to other file system operations.  
////	     * If the file is a symbolic link then the link itself is deleted, not its target.
////	     * @throws  DirectoryNotEmptyException
////	     * @throws  IOException
////	     * @throws  SecurityException
////	     *          If a security manager denies the operation.
////	     *          {@link SecurityManager#checkDelete(String)} method
////	     *          is invoked to check delete access to the file
//	    
////		 * boolean java.io.File.delete();
////		 * = SecurityManager security = System.getSecurityManager();
////        if (security != null) { security.checkDelete(path); } // throws SecurityException
////        if (isInvalid()) { return false; } // if Pfad == char(0)
////        return fs.delete(this);
////	     * If a directory, then it must be empty.
////	     * @return  <code>true</code> if successfully deleted; <code>false</code> otherwise
////	     * @throws  SecurityException
//    }
//    private void removeDirContent(boolean ignoreErrors) throws SecurityException, IOException {
//    	FileTMJ[] files=containingFiles();
//    	for(FileTMJ file:files) {
//    		if(!file.removeIfExists() && !ignoreErrors) {
//    			throw new IOException("File tree has changed during operation.");
//    		}
//    	}
//    }
//    
//    
//    /**
//     * Atomically creates a new, empty file addressed by this object if a file with 
//     * this name does not yet exist. The check for the
//     * existence of the file and the creation of the file if it does not exist
//     * are a single operation that is atomic with respect to all other
//     * filesystem activities that might affect the file.
//     * <p>Missing directories in the file's path will be create, first, if nesseccary.
//     * <p> The {@code attrs} parameter is optional {@link FileAttribute
//     * file-attributes} to set atomically when creating the file. Each attribute
//     * is identified by its {@link FileAttribute#name name}. If more than one
//     * attribute of the same name is included in the array then all but the last
//     * occurrence is ignored.
//     * <P>
//     * Note: this method should <i>not</i> be used for file-locking, as
//     * the resulting protocol cannot be made to work reliably. The
//     * {@link java.nio.channels.FileLock FileLock}
//     * facility should be used instead.
//     *
//     * @param   attrs – optional list of file attributes
//     * @return  {@code true} if the file was successfully created, {@code false} if it already existed
//     * @throws  UnsupportedOperationException – if the array contains an attribute that 
//     *          cannot be set atomically when creating the file
//     * @throws  IOException – if an I/O error occurs
//     * @throws  SecurityException – if a security manager denies the write access
//     */
//    public boolean createFileIfNotExists(FileAttribute<?>... attrs) throws IOException {
//    	try {
//    		FileTMJ file=getParent();
//    		if(null!=file) {
//    			file.createDirectory(attrs);
//    		}
//    		// from java.nio.file.Files:
//    		getFileSystem().provider().newByteChannel(
//    			dataForkPath
//    			, EnumSet.<StandardOpenOption>of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)
//    			, attrs
//    		).close();
//    		return true;
//    	}catch(FileAlreadyExistsException e) {
//    		return false;
//    	}
//    }
//
//    /**
//     * Creates a directory by creating all nonexistent parent directories first.
//     * Unlike the {@link #createDirectory createDirectory} method, an exception
//     * is not thrown if the directory could not be created because it already
//     * exists.
//     *
//     * <p> The {@code attrs} parameter is optional {@link FileAttribute
//     * file-attributes} to set atomically when creating the nonexistent
//     * directories. Each file attribute is identified by its {@link
//     * FileAttribute#name name}. If more than one attribute of the same name is
//     * included in the array then all but the last occurrence is ignored.
//     *
//     * <p> If this method fails, then it may do so after creating some, but not
//     * all, of the parent directories.
//     *
//     * @param   attrs
//     *          an optional list of file attributes to set atomically when
//     *          creating the directory
//     * @return  <code>true</code> if and only if the directory was created,
//     *          along with all necessary parent directories; <code>false</code>
//     *          otherwise
//     * @throws  UnsupportedOperationException
//     *          if the array contains an attribute that cannot be set atomically
//     *          when creating the directory
//     * @throws  FileAlreadyExistsException
//     *          if {@code dir} exists but is not a directory <i>(optional specific
//     *          exception)</i>
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          in the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkWrite(String) checkWrite}
//     *          method is invoked prior to attempting to create a directory and
//     *          its {@link SecurityManager#checkRead(String) checkRead} is
//     *          invoked for each parent directory that is checked. If {@code
//     *          dir} is not an absolute path then its {@link Path#toAbsolutePath
//     *          toAbsolutePath} may need to be invoked to get its absolute path.
//     *          This may invoke the security manager's {@link
//     *          SecurityManager#chyyyyyyyyeckPropertyAccess(String) checkPropertyAccess}
//     *          method to check access to the system property {@code user.dir}
//     */
//	 /**
//     * Creates a directory by creating all nonexistent parent directories first.
//     * Unlike the {@link #createDirectory createDirectory} method, an exception
//     * is not thrown if the directory could not be created because it already
//     * exists.
//     *
//     * <p> The {@code attrs} parameter is optional {@link FileAttribute
//     * file-attributes} to set atomically when creating the nonexistent
//     * directories. Each file attribute is identified by its {@link
//     * FileAttribute#name name}. If more than one attribute of the same name is
//     * included in the array then all but the last occurrence is ignored.
//     *
//     * <p> If this method fails, then it may do so after creating some, but not
//     * all, of the parent directories.
//     *
//     * @param   dir
//     *          the directory to create
//     *
//     * @param   attrs
//     *          an optional list of file attributes to set atomically when
//     *          creating the directory
//     *
//     * @return  the directory
//     *
//     * @throws  UnsupportedOperationException
//     *          if the array contains an attribute that cannot be set atomically
//     *          when creating the directory
//     * @throws  FileAlreadyExistsException
//     *          if {@code dir} exists but is not a directory <i>(optional specific
//     *          exception)</i>
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          in the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkWrite(String) checkWrite}
//     *          method is invoked prior to attempting to create a directory and
//     *          its {@link SecurityManager#checkRead(String) checkRead} is
//     *          invoked for each parent directory that is checked. If {@code
//     *          dir} is not an absolute path then its {@link Path#toAbsolutePath
//     *          toAbsolutePath} may need to be invoked to get its absolute path.
//     *          This may invoke the security manager's {@link
//     *          SecurityManager#checkPropertyAccess(String) checkPropertyAccess}
//     *          method to check access to the system property {@code user.dir}
//     */
//    public boolean createDirectory(FileAttribute<?>... attributes) throws UnsupportedOperationException,FileAlreadyExistsException,IOException,SecurityException {
//    	Path path;
//    	// attempt to create the directory
//    	try {
//    		createAndCheckIsDirectory(dataForkPath, attributes);
//    		path= dataForkPath;
//    	} catch (FileAlreadyExistsException x) {// file exists and is not a directory
//    		throw x;
//    	} catch (IOException x) {
//    		// parent may not exist or other reason
//    	}
//    	SecurityException se = null;
//    	try {
//    		dataForkPath = dataForkPath.toAbsolutePath();
//    	} catch (SecurityException x) {
//    		// don't have permission to get absolute path
//    		se = x;
//    	}
//    	// find a decendent that exists
//    	Path parent = dataForkPath.getParent();
//    	while (parent != null) {
//    		try {
//    			parent.getFileSystem().provider().checkAccess(parent);
//    			break;
//    		} catch (NoSuchFileException x) {
//    			// does not exist
//    		}
//    		parent = parent.getParent();
//    	}
//    	if (parent == null) {
//    		// unable to find existing parent
//    		if (se == null) {
//    			throw new FileSystemException(dataForkPath.toString(), null, "Unable to determine if root directory exists");
//    		} else {
//    			throw se;
//    		}
//    	}
//
//    	// create directories
//    	Path child = parent;
//    	for (Path name: parent.relativize(dataForkPath)) {
//    		child = child.resolve(name);
//    		createAndCheckIsDirectory(child, attributes);
//    	}
//    	path= dataForkPath;
//
//    	if(null!=path) {
//    		init(path);
//    		return true;
//    	}
//    	return false;
//    }
//    /**
//     * Used by createDirectories to attempt to create a directory. A no-op
//     * if the directory already exists.
//	 *
//     * @throws  SecurityException
//     *          If a security manager denies read access to the file.
//     */
//    private static void createAndCheckIsDirectory(Path path, FileAttribute<?>... attributes) throws IOException,SecurityException{
//        try {
//            path.getFileSystem().provider().createDirectory(path, attributes);
//        } catch (FileAlreadyExistsException x) {
//        	boolean isDirectory;
//        	try {
//        		isDirectory= readBasicFileAttributes(path, LinkOption.NOFOLLOW_LINKS).isDirectory();
//        	} catch (IOException e) {
//        		isDirectory= false;
//        	}
//        	if(!isDirectory) {
//        		throw x;
//        	}
//        }
//    }    
//
//    public static FileTMJ systemTempDirectory() {
//    	return new FileTMJ(Paths.get(AccessController.doPrivileged(new GetPropertyAction("java.io.tmpdir"))));
//    }
//
//    /**
//     * Creates a new empty file in the system's temporary directory.
//     * Uses prefix and suffix strings to generate its name.
//     *
//     * <p> The details as to how the name of the file is constructed is
//     * implementation dependent and therefore not specified. Where possible
//     * the {@code prefix} and {@code suffix} are used to construct candidate
//     * names in the same manner as the {@link
//     * java.io.File#createTempFile(String,String,File)} method.
//     *
//     * <p> Where used as a <em>work files</em>,
//     * the resulting file may be opened using the {@link
//     * StandardOpenOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} option so that the
//     * file is deleted when the appropriate {@code close} method is invoked.
//     * Alternatively, a {@link Runtime#addShutdownHook shutdown-hook}, or the
//     * {@link java.io.File#deleteOnExit} mechanism may be used to delete the
//     * file automatically.
//     *
//     * <p> The {@code attributes} parameter is optional {@link FileAttribute
//     * file-attributes} to set atomically when creating the file. Each attribute
//     * is identified by its {@link FileAttribute#name name}. If more than one
//     * attribute of the same name is included in the array then all but the last
//     * occurrence is ignored. When no file attributes are specified, then the
//     * resulting file may have more restrictive access permissions to files
//     * created by the {@link java.io.File#createTempFile(String,String,File)}
//     * method.
//     *
//     * @param   prefix – prefix used in generating the file's name;
//     *          may be {@code null}
//     * @param   suffix – suffix used in generating the file's name;
//     *          may be {@code null}, in which case "{@code .tmp}" is used
//     * @param   attributes – optional list of file attributes to set atomically when
//     *          creating the file
//     * @return  newly created file that did not exist before
//     * @throws  IllegalArgumentException – if the prefix or suffix parameters cannot be used to generate
//     *          a candidate file name
//     * @throws  UnsupportedOperationException – if the array contains an attribute that cannot be set atomically
//     *          when creating the directory
//     * @throws  IOException – if an I/O error occurs
//     * @throws  SecurityException – if a security manager exists and denies the write access
//     */
//      	 /**
//         * Creates a temporary file in the given directory, or in in the
//         * temporary directory if dir is {@code null}.
//         */
//    public static FileTMJ createTempFileChild(String prefix, String suffix, FileAttribute<?>... attributes) throws IOException {
//    	return new FileTMJ(tempFileHelper_create(null, prefix, suffix,false, attributes));
//    }
//    public static FileTMJ createTempFileChild(FileAttribute<?>... attributes) throws IOException {
//    	return createTempFileChild(null,null,attributes);
//    }
//    /**
//     * Creates a new empty file. If this is a file creates the file next to it.
//     * If this is a directory creates it inside of it.
//     * Uses prefix and suffix strings to generate its name.
//     *
//     * <p> The details as to how the name of the file is constructed is
//     * implementation dependent and therefore not specified. Where possible
//     * the {@code prefix} and {@code suffix} are used to construct candidate
//     * names in the same manner as the {@link
//     * java.io.File#createTempFile(String,String,File)} method.
//     *
//     * <p> Where used as a <em>work files</em>,
//     * the resulting file may be opened using the {@link
//     * StandardOpenOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} option so that the
//     * file is deleted when the appropriate {@code close} method is invoked.
//     * Alternatively, a {@link Runtime#addShutdownHook shutdown-hook}, or the
//     * {@link java.io.File#deleteOnExit} mechanism may be used to delete the
//     * file automatically.
//     *
//     * <p> The {@code attributes} parameter is optional {@link FileAttribute
//     * file-attributes} to set atomically when creating the file. Each attribute
//     * is identified by its {@link FileAttribute#name name}. If more than one
//     * attribute of the same name is included in the array then all but the last
//     * occurrence is ignored. When no file attributes are specified, then the
//     * resulting file may have more restrictive access permissions to files
//     * created by the {@link java.io.File#createTempFile(String,String,File)}
//     * method.
//     *
//     * @param   prefix – prefix used in generating the file's name;
//     *          may be {@code null}
//     * @param   suffix – suffix used in generating the file's name;
//     *          may be {@code null}, in which case "{@code .tmp}" is used
//     * @param   attributes – optional list of file attributes to set atomically when
//     *          creating the file
//     * @return  newly created file that did not exist before
//     * @throws  IllegalArgumentException – if the prefix or suffix parameters cannot be used to generate
//     *          a candidate file name
//     * @throws  UnsupportedOperationException – if the array contains an attribute that cannot be set atomically
//     *          when creating the directory
//     * @throws  IOException – if an I/O error occurs
//     * @throws  SecurityException – if a security manager exists and denies the write access
//     */
//    /**
//     * Creates a temporary file in the given directory, or in in the
//     * temporary directory if dir is {@code null}.
//     */
//    public FileTMJ createTempFile(String prefix, String suffix, FileAttribute<?>... attributes) throws IOException {
//    	if(isDirectory()) {
//    		return new FileTMJ(tempFileHelper_create(Objects.requireNonNull(dataForkPath),prefix, suffix, false,attributes));
//    	}else {
//    		return new FileTMJ(tempFileHelper_create(Objects.requireNonNull(dataForkPath.getParent()),prefix, suffix, false,attributes));
//    	}
//    }
//    public FileTMJ createTempFile(FileAttribute<?>... attributes) throws IOException {
//    	return createTempFile(null,null,attributes);
//    }
//
//
//
//
//    /**
//     * Creates a new directory in the system's temporary directory.
//     * Uses the prefix string to generate its name.
//     *
//     * <p> The details as to how the name of the directory is constructed is
//     * implementation dependent and therefore not specified. Where possible
//     * the {@code prefix} is used to construct candidate
//     * names.
//     *
//     * <p> The {@code attributes} parameter is optional {@link FileAttribute
//     * file-attributes} to set atomically when creating the file. Each attribute
//     * is identified by its {@link FileAttribute#name name}. If more than one
//     * attribute of the same name is included in the array then all but the last
//     * occurrence is ignored.
//     *
//     * @param   prefix – prefix used in generating the file's name;
//     *          may be {@code null}
//     * @param   attributes – optional list of file attributes to set atomically when
//     *          creating the file
//     * @return  newly created directory that did not exist before
//     * @throws  IllegalArgumentException – if the prefix or suffix parameters cannot be used to generate
//     *          a candidate file name
//     * @throws  UnsupportedOperationException – if the array contains an attribute that cannot be set atomically
//     *          when creating the directory
//     * @throws  IOException – if an I/O error occurs
//     * @throws  SecurityException – if a security manager exists and denies the write access
//     */
//    /**
//     * Creates a temporary directory in the given directory, or in in the
//     * temporary directory if dir is {@code null}.
//     */
//    public static FileTMJ createTempDirectory(String prefix, FileAttribute<?>... attributes) throws IOException {
//    	return new FileTMJ(tempFileHelper_create(null, prefix, null, true, attributes));
//    }
//    public static FileTMJ createTempDirectory(FileAttribute<?>... attributes) throws IOException {
//    	return createTempFileChild(null,null,attributes);
//    }
//    /**
//     * Creates a new directory. If this is a file creates the directory next to it.
//     * If this is a directory creates the new one inside of it.
//     * Uses prefix and suffix strings to generate its name.
//     *
//     * <p> The details as to how the name of the file is constructed is
//     * implementation dependent and therefore not specified. Where possible
//     * the {@code prefix} and {@code suffix} are used to construct candidate
//     * names in the same manner as the {@link
//     * java.io.File#createTempFile(String,String,File)} method.
//     *
//     * <p> Where used as a <em>work files</em>,
//     * the resulting file may be opened using the {@link
//     * StandardOpenOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} option so that the
//     * file is deleted when the appropriate {@code close} method is invoked.
//     * Alternatively, a {@link Runtime#addShutdownHook shutdown-hook}, or the
//     * {@link java.io.File#deleteOnExit} mechanism may be used to delete the
//     * file automatically.
//     *
//     * <p> The {@code attrs} parameter is optional {@link FileAttribute
//     * file-attributes} to set atomically when creating the file. Each attribute
//     * is identified by its {@link FileAttribute#name name}. If more than one
//     * attribute of the same name is included in the array then all but the last
//     * occurrence is ignored. When no file attributes are specified, then the
//     * resulting file may have more restrictive access permissions to files
//     * created by the {@link java.io.File#createTempFile(String,String,File)}
//     * method.
//     *
//     * @param   prefix – prefix used in generating the file's name;
//     *          may be {@code null}
//     * @param   attributes – optional list of file attributes to set atomically when
//     *          creating the file
//     * @return  newly created file that did not exist before
//     * @throws  IllegalArgumentException – if the prefix or suffix parameters cannot be used to generate
//     *          a candidate file name
//     * @throws  UnsupportedOperationException – if the array contains an attribute that cannot be set atomically
//     *          when creating the directory
//     * @throws  IOException – if an I/O error occurs
//     * @throws  SecurityException – if a security manager exists and denies the write access
//     */
//    /**
//     * Creates a temporary directory in the given directory, or in in the
//     * temporary directory if dir is {@code null}.
//     */ 
//    public FileTMJ createTempDirectoryChild(String prefix, FileAttribute<?>... attributes) throws IOException {
//    	if(isDirectory()) {
//        	return new FileTMJ(tempFileHelper_create(Objects.requireNonNull(dataForkPath),prefix, null,true,attributes));
//    	}else {
//        	return new FileTMJ(tempFileHelper_create(Objects.requireNonNull(dataForkPath.getParent()),prefix, null,true,attributes));
//    	}
//    }
//    public FileTMJ createTempDirectoryChild(FileAttribute<?>... attributes) throws IOException {
//    	return createTempDirectoryChild(null,attributes);
//    }
//
//    /**
//     * Creates a symbolic link to a target.
//     *
//     * <p> The {@code target} parameter is the target of the link.
//     *
//     * <p> The {@code attributes} parameter is optional {@link FileAttribute
//     * attributes} to set atomically when creating the link. Each attribute is
//     * identified by its {@link FileAttribute#name name}. If more than one attribute
//     * of the same name is included in the array then all but the last occurrence
//     * is ignored.
//     *
//     * <p> Where symbolic links are supported, but the underlying {@link FileStore}
//     * does not support symbolic links, then this may fail with an {@link
//     * IOException}. Additionally, some operating systems may require that the
//     * Java virtual machine be started with implementation specific privileges to
//     * create symbolic links, in which case this method may throw {@code IOException}.
//     *
//     * @param   link – the symbolic link file to create
//     * @param   target – target of the symbolic link
//     * @param   attributes – optional array of attributes to set atomically when creating the
//     *          symbolic link
//     * @return  {@code true} – if the link was successfully created, {@code false} otherwise
//     * @throws  UnsupportedOperationException – if the implementation does not support symbolic links or the
//     *          array contains an attribute that cannot be set atomically when
//     *          creating the symbolic link
//     * @throws  IOException – if an I/O error occurs
//     * @throws  SecurityException – if a security manager denies the write access.
//     */
//    public boolean createSymbolicLink(FileTMJ link,boolean override, FileAttribute<?>... attributes) throws UnsupportedOperationException,SecurityException,IOException  {
//    	if(override) {
//    		link.removeIfExists();
//    	}else if(link.exists()) {
//    		return false;
//    	}
//    	try {
//    		getFileSystem().provider().createSymbolicLink(link.dataForkPath, dataForkPath, attributes);
//    		return true;
//    	}catch(FileAlreadyExistsException e) {
//    		throw new IOException(e); // if this happens, there was a unregular problem with the existence.
//    	}
//    }
//    public boolean createSymbolicLinkIfNotExist(FileTMJ link, FileAttribute<?>... attributes) throws UnsupportedOperationException,SecurityException,IOException  {
//    	return createSymbolicLink(link,false,attributes);
//    }
//
//    /**
//     * Creates a new link (directory entry) for an existing file <i>(optional
//     * operation)</i>.
//     *
//     * <p> The {@code link} parameter locates the directory entry to create.
//     * The {@code existing} parameter is the path to an existing file. This
//     * method creates a new directory entry for the file so that it can be
//     * accessed using {@code link} as the path. On some file systems this is
//     * known as creating a "hard link". Whether the file attributes are
//     * maintained for the file or for each directory entry is file system
//     * specific and therefore not specified. Typically, a file system requires
//     * that all links (directory entries) for a file be on the same file system.
//     * Furthermore, on some platforms, the Java virtual machine may require to
//     * be started with implementation specific privileges to create hard links
//     * or to create links to directories.
//     *
//     * @param   link – the link (directory entry) to create
//     * @param   override – if true and there is a file/directory with the same path as link,
//     *          then that one will be removed first. Otherwise nothing special happens.
//     * @return  {@code true} – if the link was successfully created, {@code false} otherwise
//     * @throws  UnsupportedOperationException – if the implementation does not support symbolic links or the
//     *          array contains an attribute that cannot be set atomically when
//     *          creating the symbolic link
//     * @throws  IOException – if an I/O error occurs
//     * @throws  SecurityException – if a security manager denies the write access.
//     */
//    public boolean createLink(FileTMJ link,boolean override) throws UnsupportedOperationException,FileAlreadyExistsException,SecurityException,IOException  {
//    	if(override) {
//    		link.removeIfExists();
//    	}else if(link.exists()) {
//    		return false;
//    	}
//    	try {
//    		getFileSystem().provider().createLink(link.dataForkPath, dataForkPath);
//    		return true;
//    	}catch(FileAlreadyExistsException e) {
//    		throw new IOException(e); // if this happens, there was a unregular problem with the existence.
//    	}
//    }
//    public boolean createLinkIfNotExist(FileTMJ link) throws UnsupportedOperationException,FileAlreadyExistsException,SecurityException,IOException  {
//    	return createLink(link,false);
//    }
//
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//
//
//      
//    public byte[] readDataFork(LinkOption... options) throws FileNotFoundException,IllegalArgumentException,SecurityException,UnsupportedOperationException,IOException{
////    	if(!exists()) {
////    		throw new FileNotFoundException(dataForkPath+" does not exist!");
////    	}
//    	if(!hasDataFork()) {
//    		return null;
//    	}
//    	return readFork(dataForkPath, options);
//    }
//    public byte[] readResourceFork(LinkOption... options) throws FileNotFoundException,IllegalArgumentException,SecurityException,UnsupportedOperationException,IOException{
//    	if(!hasResourceFork()) {
//    		return null;
//    	}
//    	return readFork(resourceForkPath, options);
//    }
//    /** Read the given binary file, and return its contents as a byte array.
//     * @throws IOException */ 
//    private byte[] readFork(Path path, LinkOption... options) throws FileNotFoundException,IllegalArgumentException,SecurityException,UnsupportedOperationException,IOException{
//    	int len=(int)readBasicFileAttributes(path).size();
//        byte[] result = new byte[len];//TODO unchecked casting?!
//    	int totalBytesRead = 0;
//    	try( InputStream inputStream=new BufferedInputStream(path.getFileSystem().provider().newInputStream(path, options)) ){
//    		while(totalBytesRead < result.length){
//    			int bytesRemaining = result.length - totalBytesRead;
//    			//bufferedInputStream.read() returns -1, 0, or more :
//    			  int bytesRead = inputStream.read(result, totalBytesRead, bytesRemaining); 
//    			  if (bytesRead > 0){
//    				  totalBytesRead = totalBytesRead + bytesRead;
//    			  }
//    		  }
//    			  /* the above style is a bit tricky: it places bytes into the 'result' array; 
//                   'result' is an output parameter; the while loop usually has a single iteration only.   */
////    			  log("Num bytes read: " + totalBytesRead);
//    	  }catch(RuntimeException e) {
////    	  catch (FileNotFoundException ex) {
////    			 Throws:IllegalArgumentException - if an invalid combination of options is specified
////    		  UnsupportedOperationException - if an unsupported option is specified
////    		  IOException - if an I/O error occurs
////    		  SecurityException - If a security manager denies access to the file.
//    	  }
//    		  return result;
//      }
//    
//    private byte[] readForkAsBytesIntern(Path path,int maxBytes, LinkOption... options) throws FileNotFoundException,IllegalArgumentException,SecurityException,UnsupportedOperationException,IOException{
//    	try( InputStream inputStream=new BufferedInputStream(path.getFileSystem().provider().newInputStream(path, options)) ){
//			if(0==maxBytes) {
//				maxBytes=(int) readBasicFileAttributes(path).size();//// TODO works with small files, only?
//			}
//			byte[] fileBytes = new byte[maxBytes]; 
//			
////			(java.nio.file.Files.readAllBytes())
//			
//			inputStream.read(fileBytes);
//			return fileBytes;
//			
////			//Allocate buffer with 4byte = 32bit = Integer.SIZE
////			int bytesRead;
////			while ((bytesRead = inputStream.read(fileBytes)) != -1){
////			   //if bytesRead == 4 you read 1 int
////			   //do your stuff
////			}
//			
//		} catch (FileNotFoundException e) {
////			Logger.error(ErrorSet.fileNotFoundX(),file);
//		} catch (IOException e) {
////			Logger.error(ErrorSet.couldNotAccessFile(),file);
//		}
//		return null;
//	}
//      
//      /**
//       Write a byte array to the given file. 
//       Writing binary data is significantly simpler than reading it. 
//      */
//      void write(byte[] input, String outputFileName){
//        log("Writing binary file...");
//        try {
//          OutputStream output = null;
//          try {
//            output = new BufferedOutputStream(new FileOutputStream(outputFileName));
//            output.write(input);
//          }
//          finally {
//            output.close();
//          }
//        }
//        catch(FileNotFoundException ex){
//          log("File not found.");
//        }
//        catch(IOException ex){
//          log(ex);
//        }
//      }
//      
//      /** Read the given binary file, and return its contents as a byte array.*/ 
//      byte[] readAlternateImpl(String inputFileName){
//        log("Reading in binary file named : " + inputFileName);
//        File file = new File(inputFileName);
//        log("File size: " + file.length());
//        byte[] result = null;
//        try {
//          InputStream input =  new BufferedInputStream(new FileInputStream(file));
//          result = readAndClose(input);
//        }
//        catch (FileNotFoundException ex){
//          log(ex);
//        }
//        return result;
//      }
//      
//      /**
//       Read an input stream, and return it as a byte array.  
//       Sometimes the source of bytes is an input stream instead of a file. 
//       This implementation closes aInput after it's read.
//      */
//      byte[] readAndClose(InputStream input){
//        //carries the data from input to output :    
//        byte[] bucket = new byte[32*1024]; 
//        ByteArrayOutputStream result = null; 
//        try  {
//          try {
//            //Use buffering? No. Buffering avoids costly access to disk or network;
//            //buffering to an in-memory stream makes no sense.
//            result = new ByteArrayOutputStream(bucket.length);
//            int bytesRead = 0;
//            while(bytesRead != -1){
//              //aInput.read() returns -1, 0, or more :
//              bytesRead = input.read(bucket);
//              if(bytesRead > 0){
//                result.write(bucket, 0, bytesRead);
//              }
//            }
//          }
//          finally {
//            input.close();
//            //result.close(); this is a no-operation for ByteArrayOutputStream
//          }
//        }
//        catch (IOException ex){
//          log(ex);
//        }
//        return result.toByteArray();
//      }
//      
//      private static void log(Object thing){
//        System.out.println(String.valueOf(thing));
//      }
////    } 
//    
//    
//    
//    
//    
//    
//      
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//	// Getter, Setter, Overrides, toString, hash, etc. /////////////////////////////////
//	
//    /**
//     * Compares two file objects lexicographically.
//     * Returns the result of the compareTo method of the underlying {@link Path} objects.
//     * The ordering defined by this method is provider specific, and in the case of the default
//     * provider, platform specific. For example on UNIX systems, alphabetic case commonly is significant in comparing
//     * pathnames, on Microsoft Windows systems it is not.
//     * This method does not access the file system and neither file is required to exist.
//     *
//     * <p> This method may not be used to compare paths that are associated
//     * with different file system providers.
//     *
//     * @param   file  the path compared to this path.
//     *
//     * @return  zero if the argument corresponds to a path that is {@link #equals equal} to this object's path,
//     *          a value less than zero if this object's path is lexicographically less than the argument's path,
//     *          a value greater than zero if this object's path is lexicographically greater than the argument's path.
//     *
//     * @throws  ClassCastException
//     *          if the paths are associated with different providers.
//     */
//	public int compareTo(FileTMJ file) {
//		return dataForkPath.compareTo(file.dataForkPath);
//	}
//	
//	/**
//     * Returns an iterator over the name elements of this path.
//    *
//    * <p> The first element returned by the iterator represents the name
//    * element that is closest to the root in the directory hierarchy, the
//    * second element is the next closest, and so on. The last element returned
//    * is the name of the file or directory denoted by this path. The {@link
//    * #getRoot root} component, if present, is not returned by the iterator.
//    *
//    * @return  an iterator over the name elements of this path.
//    */
//	   @Override public Iterator<Path> iterator() {
//			throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
//		} 
//	
//	   /**
//	     * Tests this file object for substantial equality with the given object.
//	     * Returns the result of the equals method of the underlying {@link Path} objects.
//	     * @param   obj   The object to be compared with
//	     * @return  {@code true} if the objects are the same;
//	     *          {@code false} otherwise.
//	     */
//		@Override
//		public boolean equals(Object obj) {
//			if (this == obj)
//				return true;
//			if (obj == null)
//				return false;
//			if (getClass() != obj.getClass())
//				return false;
//			FileTMJ other = (FileTMJ) obj;
//			return dataForkPath.equals(other.dataForkPath);
//		}
//	   
//	   /**
//	     * Compares two URLs, excluding the fragment component.<p>
//	     *
//	     * Returns {@code true} if this {@code URL} and the
//	     * {@code other} argument are equal without taking the
//	     * fragment component into consideration.
//	     *
//	     * @param   other   the {@code URL} to compare against.
//	     * @return  {@code true} if they reference the same remote object;
//	     *          {@code false} otherwise.
//	     */
//	   /**
//	     * Tests if two paths locate the same file.
//	     *
//	     * <p> If both {@code Path} objects are {@link Path#equals(Object) equal}
//	     * then this method returns {@code true} without checking if the file exists.
//	     * If the two {@code Path} objects are associated with different providers
//	     * then this method returns {@code false}. Otherwise, this method checks if
//	     * both {@code Path} objects locate the same file, and depending on the
//	     * implementation, may require to open or access both files.
//	     *
//	     * <p> If the file system and files remain static, then this method implements
//	     * an equivalence relation for non-null {@code Paths}.
//	     * <ul>
//	     * <li>It is <i>reflexive</i>: for {@code Path} {@code f},
//	     *     {@code isSameFile(f,f)} should return {@code true}.
//	     * <li>It is <i>symmetric</i>: for two {@code Paths} {@code f} and {@code g},
//	     *     {@code isSameFile(f,g)} will equal {@code isSameFile(g,f)}.
//	     * <li>It is <i>transitive</i>: for three {@code Paths}
//	     *     {@code f}, {@code g}, and {@code h}, if {@code isSameFile(f,g)} returns
//	     *     {@code true} and {@code isSameFile(g,h)} returns {@code true}, then
//	     *     {@code isSameFile(f,h)} will return return {@code true}.
//	     * </ul>
//	     *
//	     * @param   path
//	     *          one path to the file
//	     * @param   path2
//	     *          the other path
//	     *
//	     * @return  {@code true} if, and only if, the two paths locate the same file
//	     *
//	     * @throws  IOException
//	     *          if an I/O error occurs
//	     * @throws  SecurityException
//	     *          In the case of the default provider, and a security manager is
//	     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//	     *          method is invoked to check read access to both files.
//	     *
//	     * @see java.nio.file.attribute.BasicFileAttributes#fileKey
//	     */
////	    public static boolean isSameFile(Path path, Path path2) throws IOException {
////	        return provider(path).isSameFile(path, path2);
//		public boolean sameFile(FileTMJ otherFile) throws IOException {
//			if (this == otherFile) { return true; }
//			if (null==otherFile) { return false; }
//			IOException ioException=null;
//			Path path=null;
//			try {
//				path=toCanonicalPath();
//			}catch(IOException e){
//				ioException=e;
//			}
//			Path otherPath=null;
//			try {
//				otherPath=otherFile.toCanonicalPath();
//			}catch(IOException e){
//				throw ioException;
//			}
//			if(null==path) { return false; }
//			return path.equals(otherPath); // return toCanonicalPath().equals(otherFile.toCanonicalPath());
//		}
//		
//		
//		static class CompareResult {
//			enum CompareResultDetail { EXIST_vs_DONT, DONT_vs_EXIST, SIZE_DIFFERS, CONTENT_DIFFERS, TYPE_DIFFERS, IDENTICAL, SAME_CONTENT, SAME_FILE, BOTH_EXIST, BOTH_DONT_EXIST; 
//			
//				public boolean isSame() {
//					return this==SAME_CONTENT || this==SAME_FILE ||this==BOTH_DONT_EXIST;
//				}
//			}
//			
//			private CompareResultDetail dataFork;
//			private CompareResultDetail resourceFork;
//			private CompareResultDetail dataSizeBytes;
//			private CompareResultDetail resourceSizeBytes;
////			private CompareResultDetail sizeBytes;
////			private CompareResultDetail sizeBytes;
//			private CompareResultDetail resultDetails;
//			private boolean result;
//
//			public CompareResultDetail getDataFork() {
//				return dataFork;
//			}
//			protected void setDataFork(CompareResultDetail resultDetail) {
//				this.dataFork = resultDetail;
//			}
//			public CompareResultDetail getResourceFork() {
//				return resourceFork;
//			}
//			protected void setResourceFork(CompareResultDetail resultDetail) {
//				this.resourceFork = resultDetail;
//			}
//			public CompareResultDetail getDataSizeBytes() {
//				return dataSizeBytes;
//			}
//			protected void setDataSizeBytes(CompareResultDetail resultDetail) {
//				this.dataSizeBytes = resultDetail;
//			}
//			public CompareResultDetail getResourceSizeBytes() {
//				return resourceSizeBytes;
//			}
//			protected void setResourceSizeBytes(CompareResultDetail resultDetail) {
//				this.resourceSizeBytes = resultDetail;
//			}
//			public CompareResultDetail getResultDetails() {
//				return resultDetails;
//			}
//			protected void setResultDetails(CompareResultDetail resultDetail) {
//				this.resultDetails = resultDetail;
//			}
//			public boolean isResult() {
//				return result;
//			}
//			protected void setResult(boolean result) {
//				this.result = result;
//			}
//
//			@Override
//			public int hashCode() {
//				final int prime = 31;
//				int result = 1;
//				result = prime * result + ((dataFork == null) ? 0 : dataFork.hashCode());
//				result = prime * result + ((dataSizeBytes == null) ? 0 : dataSizeBytes.hashCode());
//				result = prime * result + ((resourceFork == null) ? 0 : resourceFork.hashCode());
//				result = prime * result + ((resourceSizeBytes == null) ? 0 : resourceSizeBytes.hashCode());
//				result = prime * result + (this.result ? 1231 : 1237);
//				return result;
//			}
//			
//			@Override
//			public boolean equals(Object obj) {
//				if (this == obj)
//					return true;
//				if (obj == null)
//					return false;
//				if (getClass() != obj.getClass())
//					return false;
//				CompareResult other = (CompareResult) obj;
//				if (dataFork != other.dataFork)
//					return false;
//				if (dataSizeBytes != other.dataSizeBytes)
//					return false;
//				if (resourceFork != other.resourceFork)
//					return false;
//				if (resourceSizeBytes != other.resourceSizeBytes)
//					return false;
//				if (resultDetails != other.resultDetails)
//					return false;
//				if (result != other.result)
//					return false;
//				return true;
//			}
//
//			@Override
//			public String toString() {
//				return "CompareResult [dataFork=" + dataFork + ", resourceFork=" + resourceFork + ", dataSizeBytes="
//						+ dataSizeBytes + ", resourceSizeBytes=" + resourceSizeBytes + ", resultDetails=" + resultDetails + ", result=" + result + "]";
//			}
//			
//			
//		}
//		 //----from org.apache.commons.io.FileUtils.contentEquals(final File file1, final File file2)
//		/*
//		 * Licensed to the Apache Software Foundation (ASF) under one or more
//		 * contributor license agreements.  See the NOTICE file distributed with
//		 * this work for additional information regarding copyright ownership.
//		 * The ASF licenses this file to You under the Apache License, Version 2.0
//		 * (the "License"); you may not use this file except in compliance with
//		 * the License.  You may obtain a copy of the License at
//		 *
//		 *      http://www.apache.org/licenses/LICENSE-2.0
//		 *
//		 * Unless required by applicable law or agreed to in writing, software
//		 * distributed under the License is distributed on an "AS IS" BASIS,
//		 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//		 * See the License for the specific language governing permissions and
//		 * limitations under the License.
//		 */
//		// Origin of code: Excalibur, Alexandria, Commons-Utils
//	    /**
//	     * Compares the contents of two files to determine if they are equal or not.
//	     * <p>
//	     * This method checks to see if the two files are different lengths
//	     * or if they point to the same file, before resorting to byte-by-byte
//	     * comparison of the contents.
//	     * <p>
//	     * Code origin: Avalon
//	     *
//	     * @param file1 the first file
//	     * @param file2 the second file
//	     * @return true if the content of the files are equal or they both don't
//	     * exist, false otherwise
//	     * @throws IOException in case of an I/O error
//	     */
//		public boolean sameContent(FileTMJ otherFile) throws IOException{
//			return sameContent(otherFile,false).result;
//		}
//		public CompareResult sameContent(FileTMJ otherFile,boolean complete) throws IOException{
//			CompareResult result=new CompareResult();
//			if(hasDataFork()) {
//				if(otherFile.hasDataFork()){
//					result.setDataFork(CompareResultDetail.BOTH_EXIST);
//				}else {
//					result.setDataFork(CompareResultDetail.EXIST_vs_DONT);
//					result.setResult(false);
//					if(!complete) {
//						return result;
//					}
//				}
//			}else if(otherFile.hasDataFork()) {
//				result.setDataFork(CompareResultDetail.DONT_vs_EXIST);
//				result.setResult(false);
//				if(!complete) {
//					return result;
//				}
//			}else {
//				result.setDataFork(CompareResultDetail.BOTH_DONT_EXIST);
//			}
////			final boolean dataForkYes = hasDataFork();
////			if (dataForkYes != otherFile.hasDataFork()) {
////				return false;
////	        }
//			if(hasResourceFork()) {
//				if(otherFile.hasResourceFork()){
//					result.setResourceFork(CompareResultDetail.BOTH_EXIST);
//				}else {
//					result.setResourceFork(CompareResultDetail.EXIST_vs_DONT);
//					result.setResult(false);
//					if(!complete) {
//						return result;
//					}
//				}
//			}else if(otherFile.hasResourceFork()) {
//				result.setResourceFork(CompareResultDetail.DONT_vs_EXIST);
//				result.setResult(false);
//				if(!complete) {
//					return result;
//				}
//			}else {
//				result.setResourceFork(CompareResultDetail.BOTH_DONT_EXIST);
//			}
////			final boolean resourceForkYes = hasResourceFork();
////	        if (resourceForkYes != otherFile.hasResourceFork()) {
////	            return false;
////	        }
//			if(CompareResultDetail.BOTH_DONT_EXIST==result.getDataFork() && CompareResultDetail.BOTH_DONT_EXIST==result.getResourceFork()) {
////	        if (!dataForkYes && !resourceForkYes) {  // two not existing files are equal
//				result.setResult(true);
//	        	return result;
//	        }
//	if (isDirectory() || otherFile.isDirectory()) {
//		// don't want to compare directory contents
//		throw new IOException("Can't compare directories, only files");
//	}
////		    if (sizeBytes() != otherFile.sizeBytes()) {
//			if(CompareResultDetail.BOTH_EXIST==result.getDataFork()) {
//				if(readBasicFileAttributes(dataForkPath).size() == readBasicFileAttributes(otherFile.dataForkPath).size()) {
//					result.setDataSizeBytes(CompareResultDetail.IDENTICAL);
//				}else {
//					result.setDataSizeBytes(CompareResultDetail.SIZE_DIFFERS);
//					result.setResult(false);
//					if(!complete) {
//						return result;
//					}
//				}
//			}
//			if(CompareResultDetail.BOTH_EXIST==result.getResourceFork()) {
//				if(readBasicFileAttributes(resourceForkPath).size() == readBasicFileAttributes(otherFile.resourceForkPath).size()) {
//					result.setResourceSizeBytes(CompareResultDetail.IDENTICAL);
//				}else {
//					result.setResourceSizeBytes(CompareResultDetail.SIZE_DIFFERS);
//					result.setResult(false);
//					if(!complete) {
//						return result;
//					}
//				}
//			}
//		    if (toCanonicalPath().equals(otherFile.toCanonicalPath())) {
//		    	result.setDataFork(CompareResultDetail.SAME_FILE);
//		    	result.setResourceFork(CompareResultDetail.SAME_FILE);
//		    	return result;
//		    }
//			if(CompareResultDetail.BOTH_EXIST==result.getDataFork()) {
////		    if(dataForkYes) {
//		    	try ( InputStream inputStream = dataForkPath.getFileSystem().provider().newInputStream(dataForkPath, LinkOption.NOFOLLOW_LINKS);
//		    		  InputStream otherInputStream = otherFile.dataForkPath.getFileSystem().provider().newInputStream(otherFile.dataForkPath, LinkOption.NOFOLLOW_LINKS);
//		    		) {
//		    		if(inputStreamContentEquals(inputStream, otherInputStream)) {
//		    			result.setDataFork(CompareResultDetail.SAME_CONTENT);
//						result.setResult(true);
//		    		}else {
//		    			result.setDataFork(CompareResultDetail.CONTENT_DIFFERS);
//						result.setResult(false);
//						if(!complete) {
//							return result;
//						}
//		    		}
//		    	}
//		    }
//			if(CompareResultDetail.BOTH_EXIST==result.getResourceFork()) {
////		    if(resourceForkYes) {
//		    	try ( InputStream inputStream = resourceForkPath.getFileSystem().provider().newInputStream(resourceForkPath, LinkOption.NOFOLLOW_LINKS);
//		    		  InputStream otherInputStream = otherFile.resourceForkPath.getFileSystem().provider().newInputStream(otherFile.resourceForkPath, LinkOption.NOFOLLOW_LINKS);
//		    			) {
//		    		if(!inputStreamContentEquals(inputStream, otherInputStream)) {
//		    			result.setDataFork(CompareResultDetail.SAME_CONTENT);
//						result.setResult(true);
//		    		}else {
//		    			result.setDataFork(CompareResultDetail.CONTENT_DIFFERS);
//						result.setResult(false);
//						if(!complete) {
//							return result;
//						}
//		    		}
//		    	}
//		    }
//		    return result;   
//
////			byte[] thisBytes,otherBytes;
////			thisBytes=readDataFork();
////			otherBytes=otherFile.readDataFork();
////			boolean same=thisBytes.equals(otherBytes);//TODO dies wird man wohl selbst implementiert werden müssen.
////			if(!same) {return false;}
////			thisBytes=readResourceFork();
////			otherBytes=otherFile.readResourceFork();
////			same=thisBytes.equals(otherBytes);//TODO s.v.
////			if(!same) {return false;}
////			return true;		
//		}
//		//from org.apache.commons.io.IOUtils.contentEquals(InputStream input1, InputStream input2)
//		/*
//		 * Licensed to the Apache Software Foundation (ASF) under one or more
//		 * contributor license agreements.  See the NOTICE file distributed with
//		 * this work for additional information regarding copyright ownership.
//		 * The ASF licenses this file to You under the Apache License, Version 2.0
//		 * (the "License"); you may not use this file except in compliance with
//		 * the License.  You may obtain a copy of the License at
//		 *
//		 *      http://www.apache.org/licenses/LICENSE-2.0
//		 *
//		 * Unless required by applicable law or agreed to in writing, software
//		 * distributed under the License is distributed on an "AS IS" BASIS,
//		 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//		 * See the License for the specific language governing permissions and
//		 * limitations under the License.
//		 */
//		/*
//		 * All the methods in this class that read a stream are buffered internally.
//		 * This means that there is no cause to use a <code>BufferedInputStream</code>
//		 * or <code>BufferedReader</code>. The default buffer size of 4K has been shown
//		 * to be efficient in tests.
//		 * <p>
//		 * Origin of code: Excalibur.
//		 */
//	    /**
//	     * Compares the contents of two Streams to determine if they are equal or
//	     * not.
//	     * <p>
//	     * This method buffers the input internally using
//	     * <code>BufferedInputStream</code> if they are not already buffered.
//	     *
//	     * @param input1 the first stream
//	     * @param input2 the second stream
//	     * @return true if the content of the streams are equal or they both don't
//	     * exist, false otherwise
//	     * @throws NullPointerException if either input is null
//	     * @throws IOException          if an I/O error occurs
//	     */
//	    private static boolean inputStreamContentEquals(InputStream input1, InputStream input2) throws IOException {
//	        if (input1 == input2) {
//	            return true;
//	        }
//	        if (!(input1 instanceof BufferedInputStream)) {
//	            input1 = new BufferedInputStream(input1);
//	        }
//	        if (!(input2 instanceof BufferedInputStream)) {
//	            input2 = new BufferedInputStream(input2);
//	        }
//
//	        int ch = input1.read();
//	        while (-1 != ch) {// read returns -1 on EOF!
//	            final int ch2 = input2.read();
//	            if (ch != ch2) {
//	                return false;
//	            }
//	            ch = input1.read();
//	        }
//	        final int ch2 = input2.read();
//	        return -1==ch2;
//	    }
//		
//	   
//
//	    /**
//	     * Computes a hash code for this file object.
//	     * <p> The Computation uses the hashCode method of the underlying {@link Path} object.
//	     * and satisfies the general contract of the {@link Object#hashCode
//	     * Object.hashCode} method.
//	     *
//	     * @return  The hash-code value for this file object.
//	     */
//		@Override
//		public int hashCode() {
//			final int prime = 31;
//			return java.util.Objects.hash( 
//					prime,
//					dataForkPath
//				);
//		}
//
//  /**
//   * Returns the {@code String} representation of this {@code FileTMJ}.
//   *
//   * <p> The returned string uses the default name {@link
//   * FileSystem#getSeparator separator} to separate names in the path.
//   *
//     * @return  a string representation of this object.
//     */
//    @Override
//	public String toString() {
//		String s= "FileTMJ [path=" + dataForkPath;
//		try {
//			s=s+ ", hasDataFork=" + hasDataFork();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		try {
//			s=s+ ", hasResourceFork=" + hasResourceFork();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		s=s+ "]";
//		return s;
//	}
//    
//    
//   
//    
//    
//    
//    
//////////////////////java.nio.file.CopyMoveHelper ////////////////////
//
///*
// * Copyright (c) 2011, Oracle and/or its affiliates. All rights reserved.
// * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
///**
// * Helper class to support copying or moving files when the source and target
// * are associated with different providers.
// */
////class CopyMoveHelper {
////	private CopyMoveHelper() { }
//
//	/**
//	 * Parses the arguments for a file copy operation.
//	 */
//    private static class CopyOptions {
//		boolean replaceExisting = false;
//		boolean copyAttributes = false;
//		boolean followLinks = true;
//
//		private CopyOptions() { }
//
//		static CopyOptions parse(CopyOption... options) {
//			CopyOptions result = new CopyOptions();
//			for (CopyOption option: options) {
//				if (option == StandardCopyOption.REPLACE_EXISTING) {
//					result.replaceExisting = true;
//					continue;
//				}
//				if (option == LinkOption.NOFOLLOW_LINKS) {
//					result.followLinks = false;
//					continue;
//				}
//				if (option == StandardCopyOption.COPY_ATTRIBUTES) {
//					result.copyAttributes = true;
//					continue;
//				}
//				if (option == null) { throw new NullPointerException(); }
//				throw new UnsupportedOperationException("'" + option + "' is not a recognized copy option");
//			}
//			return result;
//		}
//	}
//
////	/**
////	 * Converts the given array of options for moving a file to options suitable
////	 * for copying the file when a move is implemented as copy + delete.
////	 */
////	private static CopyOption[] copyMoveHelper_convertMoveToCopyOptions(CopyOption... options) throws AtomicMoveNotSupportedException {
////		int len = options.length;
////		CopyOption[] newOptions = new CopyOption[len+2];
////		for (int i=0; i<len; i++) {
////			CopyOption option = options[i];
////			if (option == StandardCopyOption.ATOMIC_MOVE) {
////				throw new AtomicMoveNotSupportedException(null, null, "Atomic move between providers is not supported");
////			}
////			newOptions[i] = option;
////		}
////		newOptions[len] = LinkOption.NOFOLLOW_LINKS;
////		newOptions[len+1] = StandardCopyOption.COPY_ATTRIBUTES;
////		return newOptions;
////	}
//
//    /** Simple copy for use when source and target are associated with different providers */
//	private static void copyMoveHelper_copyToForeignTarget(Path source, Path target, CopyOption... options) throws IOException{
//		CopyOptions opts = CopyOptions.parse(options);
//		LinkOption[] linkOptions = (opts.followLinks) ? new LinkOption[0] : new LinkOption[] { LinkOption.NOFOLLOW_LINKS };
//
//		// attributes of source file
//		BasicFileAttributes attrs = readBasicFileAttributes(source,linkOptions);
//		if (attrs.isSymbolicLink()) { throw new IOException("Copying of symbolic links not supported");}
//
//		// delete target if it exists and REPLACE_EXISTING is specified
//		if (opts.replaceExisting) {
//			target.getFileSystem().provider().deleteIfExists(target);
//		} else {
//			try {
//				readBasicFileAttributes(target, LinkOption.NOFOLLOW_LINKS); // attempt to read any attribute without following links
//				throw new FileAlreadyExistsException(target.toString());
//			} catch (IOException x) {
//			}
//		}
//		// create directory or copy file
//		if (attrs.isDirectory()) {
//			target.getFileSystem().provider().createDirectory(target);
//		} else {
//			try (InputStream in = source.getFileSystem().provider().newInputStream(source)) {
//				final int BUFFER_SIZE = 8192;
//				// ensure not null before opening file
//				Objects.requireNonNull(in);
//
//				// attempt to create target file. If it fails with FileAlreadyExistsException then it may be because the security
//				// manager prevented us from deleting the file, in which case we just throw the SecurityException.
//				OutputStream outputStream;
//				try {
//					outputStream= target.getFileSystem().provider().newOutputStream(target, new OpenOption[] {StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE});
//				} catch (FileAlreadyExistsException x) {
//					// someone else won the race and created the file
//					throw x;
//				}
//
//				// do the copy
//				try (OutputStream out = outputStream) {
//					byte[] buf = new byte[BUFFER_SIZE];
//					int n;
//					while ((n = in.read(buf)) > 0) {
//						out.write(buf, 0, n);
//					}
//				}
//
//			}
//		}
//
//    	// copy basic attributes to target
//    	if (opts.copyAttributes) {
//    		BasicFileAttributeView view = //files_getFileAttributeView(target, BasicFileAttributeView.class);
//    				target.getFileSystem().provider().getFileAttributeView(target, BasicFileAttributeView.class);//, options);
//            try {
//    			view.setTimes(attrs.lastModifiedTime(), attrs.lastAccessTime(), attrs.creationTime());
//    		} catch (Throwable x) { // rollback
//    			try {
//    				target.getFileSystem().provider().delete(target);
//    			} catch (Throwable suppressed) {
//    				x.addSuppressed(suppressed);
//    			}
//    			throw x;
//    		}
//    	}
//    }
//
//////////////////////java.nio.file.CopyMoveHelper ////////////////////
//
//
//
//
//
//
//
//
//////////////////////java.nio.file.TempFileHelper ////////////////////
//	
///*
// * Copyright (c) 2009, 2013, Oracle and/or its affiliates. All rights reserved.
// * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
///**
// * Helper class to support creation of temporary files and directories with
// * initial attributes.
// */
//    private static final Path tempFileHelper_tmpdir = Paths.get(AccessController.doPrivileged(new GetPropertyAction("java.io.tmpdir")));
//
//    private static Path tempFileHelper_generatePath(String prefix, String suffix, Path dir) {
//    	final SecureRandom tempFileHelper_random = new SecureRandom();
//        long n = tempFileHelper_random.nextLong();
//        n = (n == Long.MIN_VALUE) ? 0 : Math.abs(n);
//        Path name = dir.getFileSystem().getPath(prefix + Long.toString(n) + suffix);
//        // the generated name should be a simple file name
//        if (name.getParent() != null)
//            throw new IllegalArgumentException("Invalid prefix or suffix");
//        return dir.resolve(name);
//    }
//
//
//    /**
//     * Creates a file or directory in in the given given directory (or in the
//     * temporary directory if dir is {@code null}).
//     */
//    private static Path tempFileHelper_create(Path dir, String prefix, String suffix, boolean createDirectory, FileAttribute<?>[] attrs) throws IOException{
//        final FileAttribute<Set<PosixFilePermission>> tempFileHelper_filePermissions = PosixFilePermissions.asFileAttribute(EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE));
//        final FileAttribute<Set<PosixFilePermission>> tempFileHelper_dirPermissions = PosixFilePermissions.asFileAttribute(EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE));
//        if (prefix == null)
//            prefix = "";
//        if (suffix == null)
//            suffix = (createDirectory) ? "" : ".tmp";
//        if (dir == null)
//            dir = tempFileHelper_tmpdir;
//
//        // in POSIX environments use default file and directory permissions
//        // if initial permissions not given by caller.
//        final boolean tempFileHelper_isPosix = FileSystems.getDefault().supportedFileAttributeViews().contains("posix");
//        if (tempFileHelper_isPosix && (dir.getFileSystem() == FileSystems.getDefault())) {
//            if (attrs.length == 0) {
//                // no attributes so use default permissions
//                attrs = new FileAttribute<?>[1];
//                attrs[0] = (createDirectory) ? tempFileHelper_dirPermissions :
//                	tempFileHelper_filePermissions;
//            } else {
//                // check if posix permissions given; if not use default
//                boolean hasPermissions = false;
//                for (int i=0; i<attrs.length; i++) {
//                    if (attrs[i].name().equals("posix:permissions")) {
//                        hasPermissions = true;
//                        break;
//                    }
//                }
//                if (!hasPermissions) {
//                    FileAttribute<?>[] copy = new FileAttribute<?>[attrs.length+1];
//                    System.arraycopy(attrs, 0, copy, 0, attrs.length);
//                    attrs = copy;
//                    attrs[attrs.length-1] = (createDirectory) ?
//                    		tempFileHelper_dirPermissions :
//                    			tempFileHelper_filePermissions;
//                }
//            }
//        }
//
//        // loop generating random names until file or directory can be created
//        SecurityManager sm = System.getSecurityManager();
//        for (;;) {
//            Path f;
//            try {
//                f = tempFileHelper_generatePath(prefix, suffix, dir);
//            } catch (InvalidPathException e) {
//                // don't reveal temporary directory location
//                if (sm != null)
//                    throw new IllegalArgumentException("Invalid prefix or suffix");
//                throw e;
//            }
//            try {
//                if (createDirectory) {
//                	f.getFileSystem().provider().createDirectory(f, attrs);
//                	return f;
//                } else {
//                	f.getFileSystem().provider().newByteChannel(f, EnumSet.<StandardOpenOption>of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE), attrs).close();
//                	return f;
//                }
//            } catch (SecurityException e) {
//                // don't reveal temporary directory location
//                if (dir == tempFileHelper_tmpdir && sm != null)
//                    throw new SecurityException("Unable to create temporary file or directory");
//                throw e;
//            } catch (FileAlreadyExistsException e) {
//                // ignore
//            }
//        }
//    }
//
//
//////////////////////java.nio.file.TempFileHelper ////////////////////
//
//
//
//
//
//
//} 
//
//
