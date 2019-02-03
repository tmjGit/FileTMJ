package li.tmj.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileTMJTest {//extends TestCase{
	//	private Path dataForkPath;
	//	private Path resourceForkPath;
//	private FileTMJ fileTMJ;

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


	@Test public void separatorChar() {
		//    public static final char separatorChar = '?';
	}

	@Test public void separator() {
		//public static final String separator = "" + separatorChar;
	}

	@Test public void pathSeparatorChar() {
		//public static final char pathSeparatorChar = '?';
	}

	@Test public void pathSeparator() {
		//public static final String pathSeparator = "" + pathSeparatorChar;
	}

	@Test public void constructor() {
		//public FileTMJ(Path path) {
		//public FileTMJ(File file) throws InvalidPathException {
		//public FileTMJ(String filePath) {
		//public FileTMJ(String filePath, FileSystem fileSystem) {

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



	@Test void hasDataFork() {//TODO Mock fileTMJ for this!
		//		public boolean hasDataFork() throws IOException {
		FileTMJ fileTMJ = new FileTMJ("path");
		try {
			boolean b=fileTMJ.hasDataFork();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test void hasResourceFork() {//TODO Mock fileTMJ for this!
		//	public boolean hasResourceFork() throws IOException {
		FileTMJ fileTMJ = new FileTMJ("path");
		try {
			boolean b=fileTMJ.hasResourceFork();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test void child() {//TODO Mock fileTMJ for this!
		FileTMJ fileTMJ=new FileTMJ("/").child("xyz");
		assertNotNull(fileTMJ);
		//public FileTMJ child(String child) throws InvalidPathException{
		//public FileTMJ child(Path child) throws InvalidPathException{
	}

	@Test void childDepth() {
		//public int childDepth(FileTMJ child) {
	}


	@Test void containingFiles() {//TODO Mock fileTMJ for this!
		// Exception testen
		Assertions.assertThrows(NoSuchFileException.class, () -> {
			new FileTMJ("/wegfqbxq23tr6xbuwake").containingFiles();
		});
		//public FileTMJ[] containingFiles() throws NotDirectoryException, NoSuchFileException, SecurityException, IOException {																														

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

	@Test void createDirectory() {
		//public boolean createDirectory(FileAttribute<?>... attributes) throws UnsupportedOperationException,FileAlreadyExistsException,IOException,SecurityException {
	}

	@Test void createFileIfNotExists() {
		//public boolean createFileIfNotExists(FileAttribute<?>... attrs) throws IOException {
	}

	@Test void exists() {//TODO Mock fileTMJ for this!
		assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").exists() );
		assertTrue( new FileTMJ("/").exists() );
		//public boolean exists() throws SecurityException{
		//public boolean exists(LinkOption... linkOptions) throws SecurityException{
	}

	@Test void extension() {//TODO Mock fileTMJ for this!
		assertEquals( new FileTMJ("/test.abc").extension(), "abc" );
		assertEquals( FileTMJ.extension( Paths.get("/test.abc","") ), "abc" );
	}

	@Test void getFileName() {
		assertEquals( new FileTMJ("/test.abc").extension(), "test.abc" );
	}

	@Test void getFileSystem() {
		//public FileSystem getFileSystem(){
	}

	@Test void getHome() {
		//public static FileTMJ getHome() {
	}

	@Test void getParent() {
		assertEquals( new FileTMJ("/test.abc").extension(), new FileTMJ("/") );
	}

	@Test void getPathElementCount(){
		//public int getPathElementCount(){
	}

	@Test void getPathElementName() {
		//public Path getPathElementName(int index) throws IllegalArgumentException{
	}

	@Test void getRoot() {
		assertEquals( new FileTMJ("/test.abc").toPath(), new FileTMJ("/") );
	}

	@Test void isAbsolute() {
		//public boolean isAbsolute() {
	}

	@Test void isDirectory() {//TODO Mock fileTMJ for this!
		try {
			assertTrue( new FileTMJ("/Users").isDirectory() );
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//TODO this is environment specific!
		try {
			assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").isDirectory() );
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return isDirectory(LinkOption.NOFOLLOW_LINKS);
		//public boolean isDirectory(LinkOption... linkOptions) {
	}

	@Test void isEmpty() {//TODO Mock fileTMJ for this!
		//			assertTrue( new FileTMJ("/Applications/Mail.app").isEmpty() );
		//			assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").isEmpty() );
		//			assertFalse( new FileTMJ("/Users").isEmpty() );
	}//		public boolean isEmpty() throws SecurityException, IOException {



	@Test void isPackage() {//TODO Mock fileTMJ for this!
		assertTrue( new FileTMJ("/Applications/Mail.app").isPackage() );
		assertFalse( new FileTMJ("/wegfqbxq23tr6xbuwake").isPackage() );
		assertFalse( new FileTMJ("/Users").isPackage() );
		//public boolean isPackage() {
		//private static boolean isPackage(String extension) {
	}



	@Test void isRegularFile() {//TODO Mock fileTMJ for this!
		try {
			assertTrue( new FileTMJ("/.DS_Store").isRegularFile() );
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertFalse( new FileTMJ("/Users").isRegularFile() );
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//public boolean isRegularFile(LinkOption... linkOptions) {
		//public boolean isRegularFile() {
	}

	@Test void isSymbolicLink() {
		//public boolean isSymbolicLink() {
	}


	@Test void moveTo() {
		//public FileTMJ moveTo(FileTMJ destination) throws IOException {
		//private Path moveTo(Path target) throws UnsupportedOperationException,FileAlreadyExistsException,DirectoryNotEmptyException,AtomicMoveNotSupportedException,IOException,SecurityException   {
	}


	@Test void readSymbolicLinkTarget() {
		//public FileTMJ readSymbolicLinkTarget() throws IOException, UnsupportedOperationException,NotLinkException,SecurityException {
	}


	@Test void relativize() {
		//public Path relativize(FileTMJ other){
	}
	@Test void relativizeString() {
		//public String relativizeString(FileTMJ other){
	}


	@Test void removeIfExists() {
		//public boolean removeIfExists() throws SecurityException,IOException{
		//public boolean removeIfExists(boolean ignoreErrors) throws SecurityException,IOException{
	}

	@Test void setName() {
		//public boolean setName(String nameNew) throws IOException {
		//public boolean setName(Path nameNew) throws IOException {
	}


	@Test void sizeBytes() {
		//public long sizeBytes() throws SecurityException, IOException{
		//public long sizeBytes(LinkOption...linkOptions) throws SecurityException, IOException{
	}


	@Test void toAbsolutePath() {
		//public Path toAbsolutePath() {
	}

	@Test void absolutize() {
		//public Path absolutize() {
	}


	@Test void toCanonicalPath() {
		//public Path toCanonicalPath() throws IOException {
	}

	@Test void toCanonicalPathString() {
		//public String toCanonicalPathString() throws IOException {
	}

	@Test void toFile() {
		assertEquals( new FileTMJ("/Applications/Mail.app").toFile(), new File("/Applications/Mail.app") );
	}

	@Test void toNormalized() {
		//public Path toNormalized() {
	}

	@Test void normalize() {
		//public Path normalize() {
	}

	@Test void toPath() {
		assertEquals( new FileTMJ("/test.abc").toPath(), Paths.get("/test.abc") );
	}
	@Test void getPathString() {
		assertEquals( new FileTMJ("/test.abc").toPathString(), "/test.abc" );
	}
	
	@Test void toRealPath() {
		//public Path toRealPath(LinkOption... options) throws IOException{
	}

	@Test void realize() {
		//public Path realize(LinkOption... options) throws IOException {
	}

	@Test void toURI() {
		//public URI toURI() {
	}

	@Test void toURL() {
		//public URL toURL() throws MalformedURLException {
	}

	@Test void walk() {
		//public static Stream<FileTMJ> walk(int maxDepth, FileVisitOption... options) throws IOException{
		//public static Stream<FileTMJ> walk(FileVisitOption... options) throws IOException {
	}

	@Test void createLink() {
		//public boolean createLink(FileTMJ link,boolean override) throws UnsupportedOperationException,FileAlreadyExistsException,SecurityException,IOException  {
	}
	@Test void createLinkIfNotExist() {
		//public boolean createLinkIfNotExist(FileTMJ link) throws UnsupportedOperationException,FileAlreadyExistsException,SecurityException,IOException  {
	}

	@Test void createSymbolicLink() {
		//public boolean createSymbolicLink(FileTMJ link,boolean override, FileAttribute<?>... attributes) throws UnsupportedOperationException,SecurityException,IOException  {
	}
	@Test void createSymbolicLinkIfNotExist() {
		//public boolean createSymbolicLinkIfNotExist(FileTMJ link, FileAttribute<?>... attributes) throws UnsupportedOperationException,SecurityException,IOException  {
	}

	@Test void createTempFile() {
		//public FileTMJ createTempFile(String prefix, String suffix, FileAttribute<?>... attributes) throws IOException {
		//public FileTMJ createTempFile(FileAttribute<?>... attributes) throws IOException {
	}

	@Test void createTempFileChild() {
		//public static FileTMJ createTempFileChild(String prefix, String suffix, FileAttribute<?>... attributes) throws IOException {
		//public static FileTMJ createTempFileChild(FileAttribute<?>... attributes) throws IOException {
	}

	@Test void createTempDirectory() {
		//public static FileTMJ createTempDirectory(String prefix, FileAttribute<?>... attributes) throws IOException {
		//public static FileTMJ createTempDirectory(FileAttribute<?>... attributes) throws IOException {
	}

	@Test void createTempDirectoryChild() {
		//public FileTMJ createTempDirectoryChild(String prefix, FileAttribute<?>... attributes) throws IOException {
		//public FileTMJ createTempDirectoryChild(FileAttribute<?>... attributes) throws IOException {
	}

	@Test void systemTempDirectory() {
		//public static FileTMJ systemTempDirectory() {
	}




	@Test void compareTo() {
		//public int compareTo(FileTMJ file) {
	}

	@Test void Iterator() {
		//@Override public Iterator<Path> iterator() {
	} 

	@Test void equals() {
		FileTMJ fileTMJ=new FileTMJ("/");
		FileTMJ fileTMJ2=new FileTMJ("/Users");
		assertNotEquals(fileTMJ, fileTMJ2);
		fileTMJ=fileTMJ.child("Users");
		assertEquals(fileTMJ, fileTMJ2);
	}

	@Test void sameFile() {
		//public boolean sameFile(FileTMJ otherFile) throws IOException {
	}

	@Test void sameContent() {
		//public boolean sameContent(FileTMJ otherFile) throws IOException{
		//public CompareResult sameContent(FileTMJ otherFile,boolean complete) throws IOException{
	}



	@Test void hashCodeTest() {
		assertTrue( 1<new FileTMJ("/path").hashCode() );
	}

	@Test void toStringTest() {
		FileTMJ fileTMJ=new FileTMJ("/Users");
		String s=fileTMJ.toString();
		assertNotNull( s );
		assertNotEquals("", s);
	}






} 


