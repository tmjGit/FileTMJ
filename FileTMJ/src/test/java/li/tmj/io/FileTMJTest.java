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
//
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
//	
//
//	@Test void hasDataFork() {//TODO Mock fileTMJ for this!
//		//		public boolean hasDataFork() throws IOException {
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
//		//	public boolean hasResourceFork() throws IOException {
//		fileTMJ = new FileTMJ("path");
//		try {
//			boolean b=fileTMJ.hasResourceFork();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//			
//	@Test void child() {//TODO Mock fileTMJ for this!
//		fileTMJ=new FileTMJ("/").child("xyz");
//		assertNotNull(fileTMJ);
//    	//public FileTMJ child(String child) throws InvalidPathException{
//    	//public FileTMJ child(Path child) throws InvalidPathException{
//	}
//	
//	@Test void childDepth() {
//		//public int childDepth(FileTMJ child) {
//	}
//
//
//	@Test void containingFiles() {//TODO Mock fileTMJ for this!
//		// Exception testen
//		Assertions.assertThrows(NoSuchFileException.class, () -> {
//			new FileTMJ("/wegfqbxq23tr6xbuwake").containingFiles();
//		});
//		//public FileTMJ[] containingFiles() throws NotDirectoryException, NoSuchFileException, SecurityException, IOException {																														
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
//	@Test void newDirectoryStream() {
//		//public static DirectoryStream<Path> newDirectoryStream(Path dir) throws IOException {
//	}
//	
//	@Test void createDirectory() {
//		//public boolean createDirectory(FileAttribute<?>... attributes) throws UnsupportedOperationException,FileAlreadyExistsException,IOException,SecurityException {
//	}
//	    
//	@Test void createFileIfNotExists() {
//		//public boolean createFileIfNotExists(FileAttribute<?>... attrs) throws IOException {
//	}
//
//	@Test void exists() {//TODO Mock fileTMJ for this!
//		assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").exists() );
//		assertTrue( new FileTMJ("/").exists() );
//		//public boolean exists() throws SecurityException{
//		//public boolean exists(LinkOption... linkOptions) throws SecurityException{
//	}
//
//	@Test void extension() {//TODO Mock fileTMJ for this!
//		assertEquals( new FileTMJ("/test.abc").extension(), "abc" );
//		assertEquals( FileTMJ.extension( Paths.get("/test.abc","") ), "abc" );
//		//public String extension() {
//		//public static String extension(Path path) { // returns extension, if any
//	}
//
//	@Test void getFileName() {
//		//public String getFileName(){
//	}
//
//	 @Test void getFileSystem() {
//	    	//public FileSystem getFileSystem(){
//	 }
//		  
//	 @Test void getHome() {
//	    	//public static FileTMJ getHome() {
//	 }
//	 
//	 @Test void getParent() {
//		 //public FileTMJ getParent(){
//	 }
//	 	
//	 @Test void getPath() {
//		 //public Path getPath() {
//	 }
//	 @Test void getPathString() {
//		 //public String getPathString() {
//	 }
//
//	 @Test void getPathElementCount(){
//		 //public int getPathElementCount(){
//	 }
//
//	 @Test void getPathElementName() {
//		 //public Path getPathElementName(int index) throws IllegalArgumentException{
//	 }
//	  
//	 @Test void getRoot() {
//		 //public FileTMJ getRoot(){
//	 }
//		
//	 @Test void isAbsolute() {
//		 //public boolean isAbsolute() {
//	 }
//
//	 @Test void isDirectory() {//TODO Mock fileTMJ for this!
//		 assertTrue( new FileTMJ("/Users").isDirectory() );//TODO this is environment specific!
//		 assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").isDirectory() );
//		 //return isDirectory(LinkOption.NOFOLLOW_LINKS);
//		 //public boolean isDirectory(LinkOption... linkOptions) {
//	 }
//
//	 @Test void isEmpty() {//TODO Mock fileTMJ for this!
////			assertTrue( new FileTMJ("/Applications/Mail.app").isEmpty() );
////			assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").isEmpty() );
////			assertFalse( new FileTMJ("/Users").isEmpty() );
//	 }//		public boolean isEmpty() throws SecurityException, IOException {
//			
//	    
//	
//	 @Test void isPackage() {//TODO Mock fileTMJ for this!
//			assertTrue( new FileTMJ("/Applications/Mail.app").isPackage() );
//			assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").isPackage() );
//			assertFalse( new FileTMJ("/Users").isPackage() );
//			//public boolean isPackage() {
//			//private static boolean isPackage(String extension) {
//	 }
//		
//
//
//		@Test void isRegularFile() {//TODO Mock fileTMJ for this!
//			assertTrue( new FileTMJ("/.DS_Store").isRegularFile() );
//			assertFalse( new FileTMJ("/Users").isRegularFile() );
//			//public boolean isRegularFile(LinkOption... linkOptions) {
//			//public boolean isRegularFile() {
//		}
//	   
//		@Test void isSymbolicLink() {
//			//public boolean isSymbolicLink() {
//		}
//		 
//	    
//		@Test void moveTo() {
//			//public FileTMJ moveTo(FileTMJ destination) throws IOException {
//			//private Path moveTo(Path target) throws UnsupportedOperationException,FileAlreadyExistsException,DirectoryNotEmptyException,AtomicMoveNotSupportedException,IOException,SecurityException   {
//	    }
//
//
//		@Test void readSymbolicLinkTarget() {
//			//public FileTMJ readSymbolicLinkTarget() throws IOException, UnsupportedOperationException,NotLinkException,SecurityException {
//	    }
//	   
//
//		@Test void relativize() {
//			//public Path relativize(FileTMJ other){
//	    }
//		@Test void relativizeString() {
//			//public String relativizeString(FileTMJ other){
//		}
//		
//		   
//	    @Test void removeIfExists() {
//	    	//public boolean removeIfExists() throws SecurityException,IOException{
//	    	//public boolean removeIfExists(boolean ignoreErrors) throws SecurityException,IOException{
//		}
//
//	    @Test void setName() {
//	    	//public boolean setName(String nameNew) throws IOException {
//	    	//public boolean setName(Path nameNew) throws IOException {
//	    }
//
//
//		@Test void sizeBytes() {
//			//public long sizeBytes() throws SecurityException, IOException{
//			//public long sizeBytes(LinkOption...linkOptions) throws SecurityException, IOException{
//		}
//	
//		 
//		  public Path toAbsolutePath() {
//			  return dataForkPath.toAbsolutePath();
//		  }
//		  /**
//		   * Returns {@code toAbsolutePath} and changes the internal path representation of this FileTMJ accordingly.
//		   * @return
//		   */
//		  public Path absolutize() {
//			  init(toAbsolutePath());
//			  return dataForkPath;
//		  }
//
//	
//	  public Path toCanonicalPath() throws IOException {
////		  return Paths.get(toCanonicalPathString());
//		  return fileSystem.getPath(toCanonicalPathString());
//
//	  }
//	  public String toCanonicalPathString() throws IOException {
//		  return dataForkPath.toFile().getCanonicalPath();
//	  }
//	 
//	  /**
//	   * ignores the duality of the macOS forks.
//	   * @return
//	   */
//	    public File toFile(){
//	  		return dataForkPath.toFile();
//	    }
//	    
//	  
//	  public Path toNormalized() {
//		  return dataForkPath.normalize();
//	  }
//	  /**
//	   * Returns {@code toNormalized} and changes the internal path representation of this FileTMJ accordingly.
//	   * @return
//	   */
//	  public Path normalize() {
//		  init(toNormalized());
//		  return dataForkPath;
//	  }
//
//	  /**
//	   * ignores the duality of the macOS forks.
//	   * @return
//	   */
//		public Path toPath() {
//			return dataForkPath;
//		}
//
//	  
//	  public Path toRealPath(LinkOption... options) throws IOException{
//		  return dataForkPath.toRealPath(options);
//	}
//	/**
//	 * Returns {@code toRealPath} and changes the internal path representation of this FileTMJ accordingly.
//	 * @return
//	 * @throws IOException 
//	 */
//	  public Path realize(LinkOption... options) throws IOException {
//		  init(toRealPath(options));
//		  return dataForkPath;
//	  }
//
//		
//		public URI toURI() {
//			return dataForkPath.toUri();
//		}
//
//	    
//	    public URL toURL() throws MalformedURLException {
//		  	return toURI().toURL();
//	    }
//	    
//	       
//	  
//	    
//	    public static Stream<FileTMJ> walk(int maxDepth, FileVisitOption... options) throws IOException{
//			throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
//
//	    }
//
//	  
//	    public static Stream<FileTMJ> walk(FileVisitOption... options) throws IOException {
//			throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
////	        return walk(start, Integer.MAX_VALUE, options);
//	    }
//
//
//
//	    public boolean createLink(FileTMJ link,boolean override) throws UnsupportedOperationException,FileAlreadyExistsException,SecurityException,IOException  {
//	    	if(override) {
//	    		link.removeIfExists();
//	    	}else if(link.exists()) {
//	    		return false;
//	    	}
//	    	try {
//	    		getFileSystem().provider().createLink(link.dataForkPath, dataForkPath);
//	    		return true;
//	    	}catch(FileAlreadyExistsException e) {
//	    		throw new IOException(e); // if this happens, there was a unregular problem with the existence.
//	    	}
//	    }
//	    public boolean createLinkIfNotExist(FileTMJ link) throws UnsupportedOperationException,FileAlreadyExistsException,SecurityException,IOException  {
//	    	return createLink(link,false);
//	    }
//
//
//	    /**
//	     * Creates a symbolic link to a target.
//	     *
//	     * <p> The {@code target} parameter is the target of the link.
//	     *
//	     * <p> The {@code attributes} parameter is optional {@link FileAttribute
//	     * attributes} to set atomically when creating the link. Each attribute is
//	     * identified by its {@link FileAttribute#name name}. If more than one attribute
//	     * of the same name is included in the array then all but the last occurrence
//	     * is ignored.
//	     *
//	     * <p> Where symbolic links are supported, but the underlying {@link FileStore}
//	     * does not support symbolic links, then this may fail with an {@link
//	     * IOException}. Additionally, some operating systems may require that the
//	     * Java virtual machine be started with implementation specific privileges to
//	     * create symbolic links, in which case this method may throw {@code IOException}.
//	     *
//	     * @param   link – the symbolic link file to create
//	     * @param   target – target of the symbolic link
//	     * @param   attributes – optional array of attributes to set atomically when creating the
//	     *          symbolic link
//	     * @return  {@code true} – if the link was successfully created, {@code false} otherwise
//	     * @throws  UnsupportedOperationException – if the implementation does not support symbolic links or the
//	     *          array contains an attribute that cannot be set atomically when
//	     *          creating the symbolic link
//	     * @throws  IOException – if an I/O error occurs
//	     * @throws  SecurityException – if a security manager denies the write access.
//	     */
//	    public boolean createSymbolicLink(FileTMJ link,boolean override, FileAttribute<?>... attributes) throws UnsupportedOperationException,SecurityException,IOException  {
//	    	if(override) {
//	    		link.removeIfExists();
//	    	}else if(link.exists()) {
//	    		return false;
//	    	}
//	    	try {
//	    		getFileSystem().provider().createSymbolicLink(link.dataForkPath, dataForkPath, attributes);
//	    		return true;
//	    	}catch(FileAlreadyExistsException e) {
//	    		throw new IOException(e); // if this happens, there was a unregular problem with the existence.
//	    	}
//	    }
//	    public boolean createSymbolicLinkIfNotExist(FileTMJ link, FileAttribute<?>... attributes) throws UnsupportedOperationException,SecurityException,IOException  {
//	    	return createSymbolicLink(link,false,attributes);
//	    }
//	    
//	       
//	            /**
//	         * Creates a new empty file. If this is a file creates the file next to it.
//	         * If this is a directory creates it inside of it.
//	         * Uses prefix and suffix strings to generate its name.
//	         *
//	         * <p> The details as to how the name of the file is constructed is
//	         * implementation dependent and therefore not specified. Where possible
//	         * the {@code prefix} and {@code suffix} are used to construct candidate
//	         * names in the same manner as the {@link
//	         * java.io.File#createTempFile(String,String,File)} method.
//	         *
//	         * <p> Where used as a <em>work files</em>,
//	         * the resulting file may be opened using the {@link
//	         * StandardOpenOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} option so that the
//	         * file is deleted when the appropriate {@code close} method is invoked.
//	         * Alternatively, a {@link Runtime#addShutdownHook shutdown-hook}, or the
//	         * {@link java.io.File#deleteOnExit} mechanism may be used to delete the
//	         * file automatically.
//	         *
//	         * <p> The {@code attributes} parameter is optional {@link FileAttribute
//	         * file-attributes} to set atomically when creating the file. Each attribute
//	         * is identified by its {@link FileAttribute#name name}. If more than one
//	         * attribute of the same name is included in the array then all but the last
//	         * occurrence is ignored. When no file attributes are specified, then the
//	         * resulting file may have more restrictive access permissions to files
//	         * created by the {@link java.io.File#createTempFile(String,String,File)}
//	         * method.
//	         *
//	         * @param   prefix – prefix used in generating the file's name;
//	         *          may be {@code null}
//	         * @param   suffix – suffix used in generating the file's name;
//	         *          may be {@code null}, in which case "{@code .tmp}" is used
//	         * @param   attributes – optional list of file attributes to set atomically when
//	         *          creating the file
//	         * @return  newly created file that did not exist before
//	         * @throws  IllegalArgumentException – if the prefix or suffix parameters cannot be used to generate
//	         *          a candidate file name
//	         * @throws  UnsupportedOperationException – if the array contains an attribute that cannot be set atomically
//	         *          when creating the directory
//	         * @throws  IOException – if an I/O error occurs
//	         * @throws  SecurityException – if a security manager exists and denies the write access
//	         */
//	        /**
//	         * Creates a temporary file in the given directory, or in in the
//	         * temporary directory if dir is {@code null}.
//	         */
//	        public FileTMJ createTempFile(String prefix, String suffix, FileAttribute<?>... attributes) throws IOException {
//	        	if(isDirectory()) {
//	        		return new FileTMJ(tempFileHelper_create(Objects.requireNonNull(dataForkPath),prefix, suffix, false,attributes));
//	        	}else {
//	        		return new FileTMJ(tempFileHelper_create(Objects.requireNonNull(dataForkPath.getParent()),prefix, suffix, false,attributes));
//	        	}
//	        }
//	        public FileTMJ createTempFile(FileAttribute<?>... attributes) throws IOException {
//	        	return createTempFile(null,null,attributes);
//	        }
//	    /**
//	     * Creates a new empty file in the system's temporary directory.
//	     * Uses prefix and suffix strings to generate its name.
//	     *
//	     * <p> The details as to how the name of the file is constructed is
//	     * implementation dependent and therefore not specified. Where possible
//	     * the {@code prefix} and {@code suffix} are used to construct candidate
//	     * names in the same manner as the {@link
//	     * java.io.File#createTempFile(String,String,File)} method.
//	     *
//	     * <p> Where used as a <em>work files</em>,
//	     * the resulting file may be opened using the {@link
//	     * StandardOpenOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} option so that the
//	     * file is deleted when the appropriate {@code close} method is invoked.
//	     * Alternatively, a {@link Runtime#addShutdownHook shutdown-hook}, or the
//	     * {@link java.io.File#deleteOnExit} mechanism may be used to delete the
//	     * file automatically.
//	     *
//	     * <p> The {@code attributes} parameter is optional {@link FileAttribute
//	     * file-attributes} to set atomically when creating the file. Each attribute
//	     * is identified by its {@link FileAttribute#name name}. If more than one
//	     * attribute of the same name is included in the array then all but the last
//	     * occurrence is ignored. When no file attributes are specified, then the
//	     * resulting file may have more restrictive access permissions to files
//	     * created by the {@link java.io.File#createTempFile(String,String,File)}
//	     * method.
//	     *
//	     * @param   prefix – prefix used in generating the file's name;
//	     *          may be {@code null}
//	     * @param   suffix – suffix used in generating the file's name;
//	     *          may be {@code null}, in which case "{@code .tmp}" is used
//	     * @param   attributes – optional list of file attributes to set atomically when
//	     *          creating the file
//	     * @return  newly created file that did not exist before
//	     * @throws  IllegalArgumentException – if the prefix or suffix parameters cannot be used to generate
//	     *          a candidate file name
//	     * @throws  UnsupportedOperationException – if the array contains an attribute that cannot be set atomically
//	     *          when creating the directory
//	     * @throws  IOException – if an I/O error occurs
//	     * @throws  SecurityException – if a security manager exists and denies the write access
//	     */
//	      	 /**
//	         * Creates a temporary file in the given directory, or in in the
//	         * temporary directory if dir is {@code null}.
//	         */
//	    public static FileTMJ createTempFileChild(String prefix, String suffix, FileAttribute<?>... attributes) throws IOException {
//	    	return new FileTMJ(tempFileHelper_create(null, prefix, suffix,false, attributes));
//	    }
//	    public static FileTMJ createTempFileChild(FileAttribute<?>... attributes) throws IOException {
//	    	return createTempFileChild(null,null,attributes);
//	    }
//	   
//
//	 
//	    
//
//	    /**
//	     * Creates a new directory in the system's temporary directory.
//	     * Uses the prefix string to generate its name.
//	     *
//	     * <p> The details as to how the name of the directory is constructed is
//	     * implementation dependent and therefore not specified. Where possible
//	     * the {@code prefix} is used to construct candidate
//	     * names.
//	     *
//	     * <p> The {@code attributes} parameter is optional {@link FileAttribute
//	     * file-attributes} to set atomically when creating the file. Each attribute
//	     * is identified by its {@link FileAttribute#name name}. If more than one
//	     * attribute of the same name is included in the array then all but the last
//	     * occurrence is ignored.
//	     *
//	     * @param   prefix – prefix used in generating the file's name;
//	     *          may be {@code null}
//	     * @param   attributes – optional list of file attributes to set atomically when
//	     *          creating the file
//	     * @return  newly created directory that did not exist before
//	     * @throws  IllegalArgumentException – if the prefix or suffix parameters cannot be used to generate
//	     *          a candidate file name
//	     * @throws  UnsupportedOperationException – if the array contains an attribute that cannot be set atomically
//	     *          when creating the directory
//	     * @throws  IOException – if an I/O error occurs
//	     * @throws  SecurityException – if a security manager exists and denies the write access
//	     */
//	    /**
//	     * Creates a temporary directory in the given directory, or in in the
//	     * temporary directory if dir is {@code null}.
//	     */
//	    public static FileTMJ createTempDirectory(String prefix, FileAttribute<?>... attributes) throws IOException {
//	    	return new FileTMJ(tempFileHelper_create(null, prefix, null, true, attributes));
//	    }
//	    public static FileTMJ createTempDirectory(FileAttribute<?>... attributes) throws IOException {
//	    	return createTempFileChild(null,null,attributes);
//	    }
//	    /**
//	     * Creates a new directory. If this is a file creates the directory next to it.
//	     * If this is a directory creates the new one inside of it.
//	     * Uses prefix and suffix strings to generate its name.
//	     *
//	     * <p> The details as to how the name of the file is constructed is
//	     * implementation dependent and therefore not specified. Where possible
//	     * the {@code prefix} and {@code suffix} are used to construct candidate
//	     * names in the same manner as the {@link
//	     * java.io.File#createTempFile(String,String,File)} method.
//	     *
//	     * <p> Where used as a <em>work files</em>,
//	     * the resulting file may be opened using the {@link
//	     * StandardOpenOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} option so that the
//	     * file is deleted when the appropriate {@code close} method is invoked.
//	     * Alternatively, a {@link Runtime#addShutdownHook shutdown-hook}, or the
//	     * {@link java.io.File#deleteOnExit} mechanism may be used to delete the
//	     * file automatically.
//	     *
//	     * <p> The {@code attrs} parameter is optional {@link FileAttribute
//	     * file-attributes} to set atomically when creating the file. Each attribute
//	     * is identified by its {@link FileAttribute#name name}. If more than one
//	     * attribute of the same name is included in the array then all but the last
//	     * occurrence is ignored. When no file attributes are specified, then the
//	     * resulting file may have more restrictive access permissions to files
//	     * created by the {@link java.io.File#createTempFile(String,String,File)}
//	     * method.
//	     *
//	     * @param   prefix – prefix used in generating the file's name;
//	     *          may be {@code null}
//	     * @param   attributes – optional list of file attributes to set atomically when
//	     *          creating the file
//	     * @return  newly created file that did not exist before
//	     * @throws  IllegalArgumentException – if the prefix or suffix parameters cannot be used to generate
//	     *          a candidate file name
//	     * @throws  UnsupportedOperationException – if the array contains an attribute that cannot be set atomically
//	     *          when creating the directory
//	     * @throws  IOException – if an I/O error occurs
//	     * @throws  SecurityException – if a security manager exists and denies the write access
//	     */
//	    /**
//	     * Creates a temporary directory in the given directory, or in in the
//	     * temporary directory if dir is {@code null}.
//	     */ 
//	    public FileTMJ createTempDirectoryChild(String prefix, FileAttribute<?>... attributes) throws IOException {
//	    	if(isDirectory()) {
//	        	return new FileTMJ(tempFileHelper_create(Objects.requireNonNull(dataForkPath),prefix, null,true,attributes));
//	    	}else {
//	        	return new FileTMJ(tempFileHelper_create(Objects.requireNonNull(dataForkPath.getParent()),prefix, null,true,attributes));
//	    	}
//	    }
//	    public FileTMJ createTempDirectoryChild(FileAttribute<?>... attributes) throws IOException {
//	    	return createTempDirectoryChild(null,attributes);
//	    }
//
//	    
//	    
//
//	    
//	    
//	    /** Read the given binary file, and return its contents as a byte array.*/ 
//	    byte[] readAlternateImpl(String inputFileName){
//	      log("Reading in binary file named : " + inputFileName);
//	      File file = new File(inputFileName);
//	      log("File size: " + file.length());
//	      byte[] result = null;
//	      try {
//	        InputStream input =  new BufferedInputStream(new FileInputStream(file));
//	        result = readAndClose(input);
//	      }
//	      catch (FileNotFoundException ex){
//	        log(ex);
//	      }
//	      return result;
//	    }
//	    
//	    /**
//	     Read an input stream, and return it as a byte array.  
//	     Sometimes the source of bytes is an input stream instead of a file. 
//	     This implementation closes aInput after it's read.
//	    */
//	    byte[] readAndClose(InputStream input){
//	      //carries the data from input to output :    
//	      byte[] bucket = new byte[32*1024]; 
//	      ByteArrayOutputStream result = null; 
//	      try  {
//	        try {
//	          //Use buffering? No. Buffering avoids costly access to disk or network;
//	          //buffering to an in-memory stream makes no sense.
//	          result = new ByteArrayOutputStream(bucket.length);
//	          int bytesRead = 0;
//	          while(bytesRead != -1){
//	            //aInput.read() returns -1, 0, or more :
//	            bytesRead = input.read(bucket);
//	            if(bytesRead > 0){
//	              result.write(bucket, 0, bytesRead);
//	            }
//	          }
//	        }
//	        finally {
//	          input.close();
//	          //result.close(); this is a no-operation for ByteArrayOutputStream
//	        }
//	      }
//	      catch (IOException ex){
//	        log(ex);
//	      }
//	      return result.toByteArray();
//	    }
//	  
//
//
//	    
//	    public byte[] readDataFork(LinkOption... options) throws FileNotFoundException,IllegalArgumentException,SecurityException,UnsupportedOperationException,IOException{
////	    	if(!exists()) {
////	    		throw new FileNotFoundException(dataForkPath+" does not exist!");
////	    	}
//	    	if(!hasDataFork()) {
//	    		return null;
//	    	}
//	    	return readFork(dataForkPath, options);
//	    }
//	    public byte[] readResourceFork(LinkOption... options) throws FileNotFoundException,IllegalArgumentException,SecurityException,UnsupportedOperationException,IOException{
//	    	if(!hasResourceFork()) {
//	    		return null;
//	    	}
//	    	return readFork(resourceForkPath, options);
//	    }
//	    /** Read the given binary file, and return its contents as a byte array.
//	     * @throws IOException */ 
//	    private byte[] readFork(Path path, LinkOption... options) throws FileNotFoundException,IllegalArgumentException,SecurityException,UnsupportedOperationException,IOException{
//	    	int len=(int)readBasicFileAttributes(path).size();
//	        byte[] result = new byte[len];//TODO unchecked casting?!
//	    	int totalBytesRead = 0;
//	    	try( InputStream inputStream=new BufferedInputStream(path.getFileSystem().provider().newInputStream(path, options)) ){
//	    		while(totalBytesRead < result.length){
//	    			int bytesRemaining = result.length - totalBytesRead;
//	    			//bufferedInputStream.read() returns -1, 0, or more :
//	    			  int bytesRead = inputStream.read(result, totalBytesRead, bytesRemaining); 
//	    			  if (bytesRead > 0){
//	    				  totalBytesRead = totalBytesRead + bytesRead;
//	    			  }
//	    		  }
//	    			  /* the above style is a bit tricky: it places bytes into the 'result' array; 
//	                   'result' is an output parameter; the while loop usually has a single iteration only.   */
////	    			  log("Num bytes read: " + totalBytesRead);
//	    	  }catch(RuntimeException e) {
////	    	  catch (FileNotFoundException ex) {
////	    			 Throws:IllegalArgumentException - if an invalid combination of options is specified
////	    		  UnsupportedOperationException - if an unsupported option is specified
////	    		  IOException - if an I/O error occurs
////	    		  SecurityException - If a security manager denies access to the file.
//	    	  }
//	    		  return result;
//	      }
//	    
//	    private byte[] readForkAsBytesIntern(Path path,int maxBytes, LinkOption... options) throws FileNotFoundException,IllegalArgumentException,SecurityException,UnsupportedOperationException,IOException{
//	    	try( InputStream inputStream=new BufferedInputStream(path.getFileSystem().provider().newInputStream(path, options)) ){
//				if(0==maxBytes) {
//					maxBytes=(int) readBasicFileAttributes(path).size();//// TODO works with small files, only?
//				}
//				byte[] fileBytes = new byte[maxBytes]; 
//				
////				(java.nio.file.Files.readAllBytes())
//				
//				inputStream.read(fileBytes);
//				return fileBytes;
//				
////				//Allocate buffer with 4byte = 32bit = Integer.SIZE
////				int bytesRead;
////				while ((bytesRead = inputStream.read(fileBytes)) != -1){
////				   //if bytesRead == 4 you read 1 int
////				   //do your stuff
////				}
//				
//			} catch (FileNotFoundException e) {
////				Logger.error(ErrorSet.fileNotFoundX(),file);
//			} catch (IOException e) {
////				Logger.error(ErrorSet.couldNotAccessFile(),file);
//			}
//			return null;
//		}
//	      
//	    
//
//	    public static FileTMJ systemTempDirectory() {
//		return new FileTMJ(Paths.get(AccessController.doPrivileged(new GetPropertyAction("java.io.tmpdir"))));
//	}
//
//
//	    
//	    
//	    
//	    
//	    
//	      /**
//	       Write a byte array to the given file. 
//	       Writing binary data is significantly simpler than reading it. 
//	      */
//	      void write(byte[] input, String outputFileName){
//	        log("Writing binary file...");
//	        try {
//	          OutputStream output = null;
//	          try {
//	            output = new BufferedOutputStream(new FileOutputStream(outputFileName));
//	            output.write(input);
//	          }
//	          finally {
//	            output.close();
//	          }
//	        }
//	        catch(FileNotFoundException ex){
//	          log("File not found.");
//	        }
//	        catch(IOException ex){
//	          log(ex);
//	        }
//	      }
//	      
//	      private static void log(Object thing){
//	        System.out.println(String.valueOf(thing));
//	      }
////	    } 
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
//		// Getter, Setter, Overrides, toString, hash, etc. /////////////////////////////////
//		
//	    /**
//	     * Compares two file objects lexicographically.
//	     * Returns the result of the compareTo method of the underlying {@link Path} objects.
//	     * The ordering defined by this method is provider specific, and in the case of the default
//	     * provider, platform specific. For example on UNIX systems, alphabetic case commonly is significant in comparing
//	     * pathnames, on Microsoft Windows systems it is not.
//	     * This method does not access the file system and neither file is required to exist.
//	     *
//	     * <p> This method may not be used to compare paths that are associated
//	     * with different file system providers.
//	     *
//	     * @param   file  the path compared to this path.
//	     *
//	     * @return  zero if the argument corresponds to a path that is {@link #equals equal} to this object's path,
//	     *          a value less than zero if this object's path is lexicographically less than the argument's path,
//	     *          a value greater than zero if this object's path is lexicographically greater than the argument's path.
//	     *
//	     * @throws  ClassCastException
//	     *          if the paths are associated with different providers.
//	     */
//		public int compareTo(FileTMJ file) {
//			return dataForkPath.compareTo(file.dataForkPath);
//		}
//		
//		
//		   @Override public Iterator<Path> iterator() {
//				throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
//			} 
//		
//		  
//			@Override
//			public boolean equals(Object obj) {
//				if (this == obj)
//					return true;
//				if (obj == null)
//					return false;
//				if (getClass() != obj.getClass())
//					return false;
//				FileTMJ other = (FileTMJ) obj;
//				return dataForkPath.equals(other.dataForkPath);
//			}
//		   
//	
//			public boolean sameFile(FileTMJ otherFile) throws IOException {
//				if (this == otherFile) { return true; }
//				if (null==otherFile) { return false; }
//				IOException ioException=null;
//				Path path=null;
//				try {
//					path=toCanonicalPath();
//				}catch(IOException e){
//					ioException=e;
//				}
//				Path otherPath=null;
//				try {
//					otherPath=otherFile.toCanonicalPath();
//				}catch(IOException e){
//					throw ioException;
//				}
//				if(null==path) { return false; }
//				return path.equals(otherPath); // return toCanonicalPath().equals(otherFile.toCanonicalPath());
//			}
//			
//			
//			static class CompareResult {
//				enum CompareResultDetail { EXIST_vs_DONT, DONT_vs_EXIST, SIZE_DIFFERS, CONTENT_DIFFERS, TYPE_DIFFERS, IDENTICAL, SAME_CONTENT, SAME_FILE, BOTH_EXIST, BOTH_DONT_EXIST; 
//				
//					public boolean isSame() {
//						return this==SAME_CONTENT || this==SAME_FILE ||this==BOTH_DONT_EXIST;
//					}
//				}
//				
//		
//
//				public CompareResultDetail getDataFork() {
//					return dataFork;
//				}
//				protected void setDataFork(CompareResultDetail resultDetail) {
//					this.dataFork = resultDetail;
//				}
//				public CompareResultDetail getResourceFork() {
//					return resourceFork;
//				}
//				protected void setResourceFork(CompareResultDetail resultDetail) {
//					this.resourceFork = resultDetail;
//				}
//				public CompareResultDetail getDataSizeBytes() {
//					return dataSizeBytes;
//				}
//				protected void setDataSizeBytes(CompareResultDetail resultDetail) {
//					this.dataSizeBytes = resultDetail;
//				}
//				public CompareResultDetail getResourceSizeBytes() {
//					return resourceSizeBytes;
//				}
//				protected void setResourceSizeBytes(CompareResultDetail resultDetail) {
//					this.resourceSizeBytes = resultDetail;
//				}
//				public CompareResultDetail getResultDetails() {
//					return resultDetails;
//				}
//				protected void setResultDetails(CompareResultDetail resultDetail) {
//					this.resultDetails = resultDetail;
//				}
//				public boolean isResult() {
//					return result;
//				}
//				protected void setResult(boolean result) {
//					this.result = result;
//				}
//
//				@Override
//				public int hashCode() {
//					final int prime = 31;
//					int result = 1;
//					result = prime * result + ((dataFork == null) ? 0 : dataFork.hashCode());
//					result = prime * result + ((dataSizeBytes == null) ? 0 : dataSizeBytes.hashCode());
//					result = prime * result + ((resourceFork == null) ? 0 : resourceFork.hashCode());
//					result = prime * result + ((resourceSizeBytes == null) ? 0 : resourceSizeBytes.hashCode());
//					result = prime * result + (this.result ? 1231 : 1237);
//					return result;
//				}
//				
//				@Override
//				public boolean equals(Object obj) {
//					if (this == obj)
//						return true;
//					if (obj == null)
//						return false;
//					if (getClass() != obj.getClass())
//						return false;
//					CompareResult other = (CompareResult) obj;
//					if (dataFork != other.dataFork)
//						return false;
//					if (dataSizeBytes != other.dataSizeBytes)
//						return false;
//					if (resourceFork != other.resourceFork)
//						return false;
//					if (resourceSizeBytes != other.resourceSizeBytes)
//						return false;
//					if (resultDetails != other.resultDetails)
//						return false;
//					if (result != other.result)
//						return false;
//					return true;
//				}
//
//				@Override
//				public String toString() {
//					return "CompareResult [dataFork=" + dataFork + ", resourceFork=" + resourceFork + ", dataSizeBytes="
//							+ dataSizeBytes + ", resourceSizeBytes=" + resourceSizeBytes + ", resultDetails=" + resultDetails + ", result=" + result + "]";
//				}
//				
//				
//			}
//			 //----from org.apache.commons.io.FileUtils.contentEquals(final File file1, final File file2)
//			/*
//			 * Licensed to the Apache Software Foundation (ASF) under one or more
//			 * contributor license agreements.  See the NOTICE file distributed with
//			 * this work for additional information regarding copyright ownership.
//			 * The ASF licenses this file to You under the Apache License, Version 2.0
//			 * (the "License"); you may not use this file except in compliance with
//			 * the License.  You may obtain a copy of the License at
//			 *
//			 *      http://www.apache.org/licenses/LICENSE-2.0
//			 *
//			 * Unless required by applicable law or agreed to in writing, software
//			 * distributed under the License is distributed on an "AS IS" BASIS,
//			 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//			 * See the License for the specific language governing permissions and
//			 * limitations under the License.
//			 */
//			// Origin of code: Excalibur, Alexandria, Commons-Utils
//		    /**
//		     * Compares the contents of two files to determine if they are equal or not.
//		     * <p>
//		     * This method checks to see if the two files are different lengths
//		     * or if they point to the same file, before resorting to byte-by-byte
//		     * comparison of the contents.
//		     * <p>
//		     * Code origin: Avalon
//		     *
//		     * @param file1 the first file
//		     * @param file2 the second file
//		     * @return true if the content of the files are equal or they both don't
//		     * exist, false otherwise
//		     * @throws IOException in case of an I/O error
//		     */
//			public boolean sameContent(FileTMJ otherFile) throws IOException{
//				return sameContent(otherFile,false).result;
//			}
//			public CompareResult sameContent(FileTMJ otherFile,boolean complete) throws IOException{
//				CompareResult result=new CompareResult();
//				if(hasDataFork()) {
//					if(otherFile.hasDataFork()){
//						result.setDataFork(CompareResultDetail.BOTH_EXIST);
//					}else {
//						result.setDataFork(CompareResultDetail.EXIST_vs_DONT);
//						result.setResult(false);
//						if(!complete) {
//							return result;
//						}
//					}
//				}else if(otherFile.hasDataFork()) {
//					result.setDataFork(CompareResultDetail.DONT_vs_EXIST);
//					result.setResult(false);
//					if(!complete) {
//						return result;
//					}
//				}else {
//					result.setDataFork(CompareResultDetail.BOTH_DONT_EXIST);
//				}
////				final boolean dataForkYes = hasDataFork();
////				if (dataForkYes != otherFile.hasDataFork()) {
////					return false;
////		        }
//				if(hasResourceFork()) {
//					if(otherFile.hasResourceFork()){
//						result.setResourceFork(CompareResultDetail.BOTH_EXIST);
//					}else {
//						result.setResourceFork(CompareResultDetail.EXIST_vs_DONT);
//						result.setResult(false);
//						if(!complete) {
//							return result;
//						}
//					}
//				}else if(otherFile.hasResourceFork()) {
//					result.setResourceFork(CompareResultDetail.DONT_vs_EXIST);
//					result.setResult(false);
//					if(!complete) {
//						return result;
//					}
//				}else {
//					result.setResourceFork(CompareResultDetail.BOTH_DONT_EXIST);
//				}
////				final boolean resourceForkYes = hasResourceFork();
////		        if (resourceForkYes != otherFile.hasResourceFork()) {
////		            return false;
////		        }
//				if(CompareResultDetail.BOTH_DONT_EXIST==result.getDataFork() && CompareResultDetail.BOTH_DONT_EXIST==result.getResourceFork()) {
////		        if (!dataForkYes && !resourceForkYes) {  // two not existing files are equal
//					result.setResult(true);
//		        	return result;
//		        }
//		if (isDirectory() || otherFile.isDirectory()) {
//			// don't want to compare directory contents
//			throw new IOException("Can't compare directories, only files");
//		}
////			    if (sizeBytes() != otherFile.sizeBytes()) {
//				if(CompareResultDetail.BOTH_EXIST==result.getDataFork()) {
//					if(readBasicFileAttributes(dataForkPath).size() == readBasicFileAttributes(otherFile.dataForkPath).size()) {
//						result.setDataSizeBytes(CompareResultDetail.IDENTICAL);
//					}else {
//						result.setDataSizeBytes(CompareResultDetail.SIZE_DIFFERS);
//						result.setResult(false);
//						if(!complete) {
//							return result;
//						}
//					}
//				}
//				if(CompareResultDetail.BOTH_EXIST==result.getResourceFork()) {
//					if(readBasicFileAttributes(resourceForkPath).size() == readBasicFileAttributes(otherFile.resourceForkPath).size()) {
//						result.setResourceSizeBytes(CompareResultDetail.IDENTICAL);
//					}else {
//						result.setResourceSizeBytes(CompareResultDetail.SIZE_DIFFERS);
//						result.setResult(false);
//						if(!complete) {
//							return result;
//						}
//					}
//				}
//			    if (toCanonicalPath().equals(otherFile.toCanonicalPath())) {
//			    	result.setDataFork(CompareResultDetail.SAME_FILE);
//			    	result.setResourceFork(CompareResultDetail.SAME_FILE);
//			    	return result;
//			    }
//				if(CompareResultDetail.BOTH_EXIST==result.getDataFork()) {
////			    if(dataForkYes) {
//			    	try ( InputStream inputStream = dataForkPath.getFileSystem().provider().newInputStream(dataForkPath, LinkOption.NOFOLLOW_LINKS);
//			    		  InputStream otherInputStream = otherFile.dataForkPath.getFileSystem().provider().newInputStream(otherFile.dataForkPath, LinkOption.NOFOLLOW_LINKS);
//			    		) {
//			    		if(inputStreamContentEquals(inputStream, otherInputStream)) {
//			    			result.setDataFork(CompareResultDetail.SAME_CONTENT);
//							result.setResult(true);
//			    		}else {
//			    			result.setDataFork(CompareResultDetail.CONTENT_DIFFERS);
//							result.setResult(false);
//							if(!complete) {
//								return result;
//							}
//			    		}
//			    	}
//			    }
//				if(CompareResultDetail.BOTH_EXIST==result.getResourceFork()) {
////			    if(resourceForkYes) {
//			    	try ( InputStream inputStream = resourceForkPath.getFileSystem().provider().newInputStream(resourceForkPath, LinkOption.NOFOLLOW_LINKS);
//			    		  InputStream otherInputStream = otherFile.resourceForkPath.getFileSystem().provider().newInputStream(otherFile.resourceForkPath, LinkOption.NOFOLLOW_LINKS);
//			    			) {
//			    		if(!inputStreamContentEquals(inputStream, otherInputStream)) {
//			    			result.setDataFork(CompareResultDetail.SAME_CONTENT);
//							result.setResult(true);
//			    		}else {
//			    			result.setDataFork(CompareResultDetail.CONTENT_DIFFERS);
//							result.setResult(false);
//							if(!complete) {
//								return result;
//							}
//			    		}
//			    	}
//			    }
//			    return result;   
//
////				byte[] thisBytes,otherBytes;
////				thisBytes=readDataFork();
////				otherBytes=otherFile.readDataFork();
////				boolean same=thisBytes.equals(otherBytes);//TODO dies wird man wohl selbst implementiert werden müssen.
////				if(!same) {return false;}
////				thisBytes=readResourceFork();
////				otherBytes=otherFile.readResourceFork();
////				same=thisBytes.equals(otherBytes);//TODO s.v.
////				if(!same) {return false;}
////				return true;		
//			}
//			//from org.apache.commons.io.IOUtils.contentEquals(InputStream input1, InputStream input2)
//			/*
//			 * Licensed to the Apache Software Foundation (ASF) under one or more
//			 * contributor license agreements.  See the NOTICE file distributed with
//			 * this work for additional information regarding copyright ownership.
//			 * The ASF licenses this file to You under the Apache License, Version 2.0
//			 * (the "License"); you may not use this file except in compliance with
//			 * the License.  You may obtain a copy of the License at
//			 *
//			 *      http://www.apache.org/licenses/LICENSE-2.0
//			 *
//			 * Unless required by applicable law or agreed to in writing, software
//			 * distributed under the License is distributed on an "AS IS" BASIS,
//			 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//			 * See the License for the specific language governing permissions and
//			 * limitations under the License.
//			 */
//			/*
//			 * All the methods in this class that read a stream are buffered internally.
//			 * This means that there is no cause to use a <code>BufferedInputStream</code>
//			 * or <code>BufferedReader</code>. The default buffer size of 4K has been shown
//			 * to be efficient in tests.
//			 * <p>
//			 * Origin of code: Excalibur.
//			 */
//		    /**
//		     * Compares the contents of two Streams to determine if they are equal or
//		     * not.
//		     * <p>
//		     * This method buffers the input internally using
//		     * <code>BufferedInputStream</code> if they are not already buffered.
//		     *
//		     * @param input1 the first stream
//		     * @param input2 the second stream
//		     * @return true if the content of the streams are equal or they both don't
//		     * exist, false otherwise
//		     * @throws NullPointerException if either input is null
//		     * @throws IOException          if an I/O error occurs
//		     */
//		    private static boolean inputStreamContentEquals(InputStream input1, InputStream input2) throws IOException {
//		        if (input1 == input2) {
//		            return true;
//		        }
//		        if (!(input1 instanceof BufferedInputStream)) {
//		            input1 = new BufferedInputStream(input1);
//		        }
//		        if (!(input2 instanceof BufferedInputStream)) {
//		            input2 = new BufferedInputStream(input2);
//		        }
//
//		        int ch = input1.read();
//		        while (-1 != ch) {// read returns -1 on EOF!
//		            final int ch2 = input2.read();
//		            if (ch != ch2) {
//		                return false;
//		            }
//		            ch = input1.read();
//		        }
//		        final int ch2 = input2.read();
//		        return -1==ch2;
//		    }
//			
//		   
//
//		    /**
//		     * Computes a hash code for this file object.
//		     * <p> The Computation uses the hashCode method of the underlying {@link Path} object.
//		     * and satisfies the general contract of the {@link Object#hashCode
//		     * Object.hashCode} method.
//		     *
//		     * @return  The hash-code value for this file object.
//		     */
//			@Override
//			public int hashCode() {
//				final int prime = 31;
//				return java.util.Objects.hash( 
//						prime,
//						dataForkPath
//					);
//			}
//
//		public String toString() {
//			String s= "FileTMJ [path=" + dataForkPath;
//			try {
//				s=s+ ", hasDataFork=" + hasDataFork();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				s=s+ ", hasResourceFork=" + hasResourceFork();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			s=s+ "]";
//			return s;
//		}
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
//	@Test void toNormalized() {
//		//public Path toNormalized() {
//	}
//
//	@Test void normalize() {
//		//public Path normalize() {
//	}
//
//	
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
//    
//    
//    
//    
//    public static Stream<FileTMJ> walk(int maxDepth, FileVisitOption... options) throws IOException{
//		throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
//    }
//
//    public static Stream<FileTMJ> walk(FileVisitOption... options) throws IOException {
//		throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
////        return walk(start, Integer.MAX_VALUE, options);
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
//    
// 
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
// 
//	
//   
//
//    public static FileTMJ systemTempDirectory() {
//    	return new FileTMJ(Paths.get(AccessController.doPrivileged(new GetPropertyAction("java.io.tmpdir"))));
//    }
//
//  
//    public static FileTMJ createTempFileChild(String prefix, String suffix, FileAttribute<?>... attributes) throws IOException {
//    	return new FileTMJ(tempFileHelper_create(null, prefix, suffix,false, attributes));
//    }
//    public static FileTMJ createTempFileChild(FileAttribute<?>... attributes) throws IOException {
//    	return createTempFileChild(null,null,attributes);
//    }
//   
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
//   
//    public static FileTMJ createTempDirectory(String prefix, FileAttribute<?>... attributes) throws IOException {
//    	return new FileTMJ(tempFileHelper_create(null, prefix, null, true, attributes));
//    }
//    public static FileTMJ createTempDirectory(FileAttribute<?>... attributes) throws IOException {
//    	return createTempFileChild(null,null,attributes);
//    }
//  
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
//  
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
//    
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
//	public int compareTo(FileTMJ file) {
//		return dataForkPath.compareTo(file.dataForkPath);
//	}
//	
//	   @Override public Iterator<Path> iterator() {
//			throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
//		} 
//	
//	 
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
//	  
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
//		 
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
//		
//		}
//		
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
//	   
//		@Override
//		public int hashCode() {
//			final int prime = 31;
//			return java.util.Objects.hash( 
//					prime,
//					dataForkPath
//				);
//		}
//
// 
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
//
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
//} 
//
//
