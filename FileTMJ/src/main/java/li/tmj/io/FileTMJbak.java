//package li.tmj.io;
//
//    import java.io.BufferedInputStream;
//    import java.io.BufferedOutputStream;
//    import java.io.ByteArrayOutputStream;
//    import java.io.File;
//    import java.io.FileInputStream;
//    import java.io.FileNotFoundException;
//    import java.io.FileOutputStream;
//    import java.io.IOException;
//    import java.io.InputStream;
//    import java.io.OutputStream;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.Closeable;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.Reader;
//import java.io.UncheckedIOException;
//import java.io.Writer;
//import java.nio.channels.Channels;
//import java.nio.channels.FileChannel;
//import java.nio.channels.SeekableByteChannel;
//import java.nio.charset.Charset;
//import java.nio.charset.CharsetDecoder;
//import java.nio.charset.CharsetEncoder;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.attribute.BasicFileAttributeView;
//import java.nio.file.attribute.BasicFileAttributes;
//import java.nio.file.attribute.DosFileAttributes;   // javadoc
//import java.nio.file.attribute.FileAttribute;
//import java.nio.file.attribute.FileAttributeView;
//import java.nio.file.attribute.FileOwnerAttributeView;
//import java.nio.file.attribute.FileStoreAttributeView;
//import java.nio.file.attribute.FileTime;
//import java.nio.file.attribute.PosixFileAttributeView;
//import java.nio.file.attribute.PosixFileAttributes;
//import java.nio.file.attribute.PosixFilePermission;
//import java.nio.file.attribute.UserPrincipal;
//import java.nio.file.spi.FileSystemProvider;
//import java.nio.file.spi.FileTypeDetector;
//import java.security.AccessController;
//import java.security.PrivilegedAction;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.EnumSet;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.ServiceLoader;
//import java.util.Set;
//import java.util.Spliterator;
//import java.util.Spliterators;
//import java.util.function.BiPredicate;
//import java.util.stream.Stream;
//import java.util.stream.StreamSupport;
//
//import static java.security.AccessController.doPrivileged;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileFilter;
//import java.io.FileNotFoundException;
//import java.io.FilenameFilter;
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.nio.file.Files;
//import java.nio.file.LinkOption;
//import java.nio.file.NoSuchFileException;
//import java.nio.file.NotDirectoryException;
//import java.nio.file.Path;
//import java.nio.file.attribute.BasicFileAttributes;
//import java.util.stream.Stream;
//
////	public FileTMJ(String uncFilePath) {//macOS X
//	// Windows: \\Servername\Freigabename\Pfad
//	// unixoid: //Servername/Freigabename/Pfad
//	//Servername auch IP-Nr., IPv6-Syntax dann statt 2001:db8::2468:1357:abcd:3456 -> 2001-db8--2468-1357-abcd-3456.ipv6-literal.net
////		
////	}
//	
//	static abstract class SpecificPath{
//		protected Path path;
//		public SpecificPath(String path) {
//			this(FileSystems.getDefault(),path);
//		}
//		public SpecificPath(FileSystem fileSystem,String path) {
//			if(null==path) {
//				this.path=null;
//			}else {
//				String pathStr=convert(path);
//				this.path=fileSystem.getPath(pathStr, new String[] {});
//			}
//		}
//		protected abstract String convert(String path);
//
//		public Path getPath() {
//			return path;
//		}
//	}
//	
//	static class WindowsPath extends SpecificPath{//TODO untested!
//		private static Pattern regexDriveLetter = Pattern.compile("^[a-zA-Z]:");
//		private static Pattern regexSeparator = Pattern.compile("\\\\");
////		private static Pattern regexUnc = Pattern.compile("^//");
//		public WindowsPath(String path) {
//			super(path);
//		}
//		public WindowsPath(FileSystem fileSystem,String path) {
//			super(fileSystem,path);
//		}
//		@Override
//		protected String convert(String path) {
//			String pathStr=path;
//			pathStr = regexDriveLetter.matcher(pathStr).replaceAll("");
//			pathStr = regexSeparator.matcher(pathStr).replaceAll("/");
////			pathStr = regexUnc.matcher(pathStr).replaceAll("/mnt/");
//			return pathStr;
//		}
//	}
//	
//	static class MacOSclassicFilePath extends SpecificPath{//appleScriptFilePath //TODO untested!
////		private static Pattern regexDriveLetter = Pattern.compile("^[a-zA-Z]:");
//		private static Pattern regexSeparator = Pattern.compile(":");
////		private static Pattern regexUnc = Pattern.compile("^//");
//		public MacOSclassicFilePath(String path) {
//			super(path);
//		}
//		public MacOSclassicFilePath(FileSystem fileSystem,String path) {
//			super(fileSystem,path);
//		}
//		@Override
//		protected String convert(String path) {
//			String pathStr=path;
////			pathStr = regexDriveLetter.matcher(pathStr).replaceAll("");
//			pathStr = regexSeparator.matcher(pathStr).replaceAll("/");
////			pathStr = regexUnc.matcher(pathStr).replaceAll("/mnt/");
//			return pathStr;
//		}
//	}
//	
////	package com.christianfries.test;
////
////	import java.io.File;
////	import java.net.MalformedURLException;
////	import java.net.URI;
////	import java.net.URISyntaxException;
////	import java.net.URL;
////
////	public class UNCPathTest {
////	    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
////	        UNCPathTest upt = new UNCPathTest();
////	        upt.testURL("file://server/dir/file.txt");  // Windows UNC Path
////	        upt.testURL("file:///Z:/dir/file.txt");     // Windows drive letter path
////	        upt.testURL("file:///dir/file.txt");        // Unix (absolute) path
////	    }
////	    private void testURL(String urlString) throws MalformedURLException, URISyntaxException {
////	        URL url = new URL(urlString);
////	        System.out.println("URL is: " + url.toString());
////	        URI uri = url.toURI();
////	        System.out.println("URI is: " + uri.toString());
////	        if(uri.getAuthority() != null && uri.getAuthority().length() > 0) {
////	            // Hack for UNC Path
////	            uri = (new URL("file://" + urlString.substring("file:".length()))).toURI();
////	        }
////	        File file = new File(uri);
////	        System.out.println("File is: " + file.toString());
////	        String parent = file.getParent();
////	        System.out.println("Parent is: " + parent);
////	    }
////	}
//
////Building on @SotiriosDelimanolis's comment, here a method to deal with URLs (such as file:...) and non-URLs (such as C:...), using Spring's FileSystemResource:
////public FileSystemResource get(String file) {
////    try {     // First try to resolve as URL (file:...)
////        Path path = Paths.get(new URL(file).toURI());
////        FileSystemResource resource = new FileSystemResource(path.toFile());
////        return resource;
////    } catch (URISyntaxException | MalformedURLException e) {
////        // If given file string isn't an URL, fall back to using a normal file 
////        return new FileSystemResource(file);
////    }
////}
//
//
////Java (at least 5 and 6, java 7 Paths solved most) has a problem with UNC and URI. Eclipse team wrapped it up here : http://wiki.eclipse.org/Eclipse/UNC_Paths
////From java.io.File javadocs, the UNC prefix is "////", and java.net.URI handles file:////host/path (four slashes).
////More details on why this happens and possible problems it causes in other URI and URL methods can be found in the list of bugs at the end of the link given above.
////Using these informations, Eclipse team developed org.eclipse.core.runtime.URIUtil class, which source code can probably help out when dealing with UNC paths.
//
//	
//	public FileTMJbak(SpecificPath path) {
//		this(path.getPath());
//	}
//	
//	
//	public FileTMJbak(FileSystem fileSystem,String filePath) {
//		this(fileSystem.getPath(filePath, ""));
////	public FileTMJ(FileSystem fileSystem,String... filePathElements) {
////		if(null==fileSystem) {
////			new NullPointerException("Error: null is no acceptable FileSystem!");
////		}
////		if(null==filePathElements) {
////			new NullPointerException("Error: null is no acceptable filePath Element!");
////		}
////		if(1>filePathElements.length) {
////			new IllegalArgumentException("Error: at least one filePath Element must be provided!");
////		}
/////**		Converts a path string, or a sequence of strings that when joined form a path string, to a Path. 
//// * If more does not specify any elements then the value of the first parameter is the path string to convert.
//// *  If more specifies one or more elements then each non-empty string, including first, is considered to be a sequence of name elements (see Path) and is joined to form a path string. 
//// *  The details as to how the Strings are joined is provider specific but typically they will be joined using the name-separator as the separator. For example, if the name separator is "/" and getPath("/foo","bar","gus") is invoked, then the path string "/foo/bar/gus" is converted to a Path. 
//// *  A Path representing an empty path is returned if first is the empty string and more does not contain any non-empty strings.
////		The parsing and conversion to a path object is inherently implementation dependent. In the simplest case, the path string is rejected, and InvalidPathException thrown, if the path string contains characters that cannot be converted to characters that are legal to the file store. 
////		For example, on UNIX systems, the NUL (\u0000) character is not allowed to be present in a path. An implementation may choose to reject path strings that contain names that are longer than those allowed by any file store, and where an implementation supports a complex path syntax, 
////		it may choose to reject path strings that are badly formed.
////
////		In the case of the default provider, path strings are parsed based on the definition of paths at the platform or virtual file system level. 
////		For example, an operating system may not allow specific characters to be present in a file name, but a specific underlying file store may impose different or additional restrictions on the set of legal characters.
////
////		This method throws InvalidPathException when the path string cannot be converted to a path. 
////		Where possible, and where applicable, the exception is created with an index value indicating the first position in the path parameter that caused the path string to be rejected.
////
////		Parameters:
////		first the path string or initial part of the path string
////		more additional strings to be joined to form the path string
////		Returns:
////		the resulting Path
////		Throws:
////		InvalidPathException - If the path string cannot be converted */
////		if(2<filePathElements.length) {
////			fileSystem.getPath(filePathElements[0], new String[] {});
////		}else {
////			fileSystem.getPath(filePathElements[0], filePathElements[1 bis ende]);
////		}
//	}
//	
//	 /**
//	   * Return a {@code Path} object by converting the given {@link URI}. The
//	   * resulting {@code Path} is associated with a {@link FileSystem} that
//	   * already exists or is constructed automatically.
//	   *
//	   * <p> The exact form of the URI is file system provider dependent. In the
//	   * case of the default provider, the URI scheme is {@code "file"} and the
//	   * given URI has a non-empty path component, and undefined query, and
//	   * fragment components. The resulting {@code Path} is associated with the
//	   * default {@link FileSystems#getDefault default} {@code FileSystem}.
//	   *
//	   * <p> If a security manager is installed then a provider implementation
//	   * may require to check a permission. In the case of the {@link
//	   * FileSystems#getDefault default} file system, no permission check is
//	   * required.
//	   *
//	   * @param   uri
//	   *          The URI to convert
//	   *
//	   * @return  The resulting {@code Path}
//	   *
//	   * @throws  IllegalArgumentException
//	   *          If the URI scheme does not identify this provider or other
//	   *          preconditions on the uri parameter do not hold
//	   * @throws  FileSystemNotFoundException
//	   *          The file system, identified by the URI, does not exist and
//	   *          cannot be created automatically
//	   * @throws  SecurityException
//	   *          If a security manager is installed and it denies an unspecified
//	   *          permission.
//	   */
//	public FileTMJbak(FileSystemProvider fileSystemProvider, URI uri) { // public abstract Path getPath(URI uri);
//		this(uri.getPath()); // this(fileSystemProvider.getPath(uri)); <- warum wird in FileSystemPropvider diese abstrakte Methode definiert, wo es doch direkt über die URI geht?
//	}
//	
//	
//	
//    
//
//    
//	/**
//	   * ignores the duality of the macOS forks.
//	   * Returns a URI to represent this path.
//	   *
//	   * <p> This method constructs an absolute {@link URI} with a {@link
//	   * URI#getScheme() scheme} equal to the URI scheme that identifies the
//	   * provider. The exact form of the scheme specific part is highly provider
//	   * dependent.
//	   *
//	   * <p> In the case of the default provider, the URI is hierarchical with
//	   * a {@link URI#getPath() path} component that is absolute. The query and
//	   * fragment components are undefined. Whether the authority component is
//	   * defined or not is implementation dependent. There is no guarantee that
//	   * the {@code URI} may be used to construct a {@link java.io.File java.io.File}.
//	   * In particular, if this path represents a Universal Naming Convention (UNC)
//	   * path, then the UNC server name may be encoded in the authority component
//	   * of the resulting URI. In the case of the default provider, and the file
//	   * exists, and it can be determined that the file is a directory, then the
//	   * resulting {@code URI} will end with a slash.
//	   *
//	   * <p> The default provider provides a similar <em>round-trip</em> guarantee
//	   * to the {@link java.io.File} class. For a given {@code Path} <i>p</i> it
//	   * is guaranteed that
//	   * <blockquote><tt>
//	   * {@link Paths#get(URI) Paths.get}(</tt><i>p</i><tt>.toUri()).equals(</tt><i>p</i>
//	   * <tt>.{@link #toAbsolutePath() toAbsolutePath}())</tt>
//	   * </blockquote>
//	   * so long as the original {@code Path}, the {@code URI}, and the new {@code
//	   * Path} are all created in (possibly different invocations of) the same
//	   * Java virtual machine. Whether other providers make any guarantees is
//	   * provider specific and therefore unspecified.
//	   *
//	   * <p> When a file system is constructed to access the contents of a file
//	   * as a file system then it is highly implementation specific if the returned
//	   * URI represents the given path in the file system or it represents a
//	   * <em>compound</em> URI that encodes the URI of the enclosing file system.
//	   * A format for compound URIs is not defined in this release; such a scheme
//	   * may be added in a future release.
//	   *
//	   * @return  the URI representing this path
//	   *
//	   * @throws  java.io.IOError
//	   *          if an I/O error occurs obtaining the absolute path, or where a
//	   *          file system is constructed to access the contents of a file as
//	   *          a file system, and the URI of the enclosing file system cannot be
//	   *          obtained
//	   *
//	   * @throws  SecurityException
//	   *          In the case of the default provider, and a security manager
//	   *          is installed, the {@link #toAbsolutePath toAbsolutePath} method
//	   *          throws a security exception.
//	   */
//	  /**
//	   * Constructs a <tt>file:</tt> URI that represents this abstract pathname.
//	   *
//	   * <p> The exact form of the URI is system-dependent.  If it can be
//	   * determined that the file denoted by this abstract pathname is a
//	   * directory, then the resulting URI will end with a slash.
//	   *
//	   * <p> For a given abstract pathname <i>f</i>, it is guaranteed that
//	   *
//	   * <blockquote><tt>
//	   * new {@link #File(java.net.URI) File}(</tt><i>&nbsp;f</i><tt>.toURI()).equals(</tt><i>&nbsp;f</i><tt>.{@link #getAbsoluteFile() getAbsoluteFile}())
//	   * </tt></blockquote>
//	   *
//	   * so long as the original abstract pathname, the URI, and the new abstract
//	   * pathname are all created in (possibly different invocations of) the same
//	   * Java virtual machine.  Due to the system-dependent nature of abstract
//	   * pathnames, however, this relationship typically does not hold when a
//	   * <tt>file:</tt> URI that is created in a virtual machine on one operating
//	   * system is converted into an abstract pathname in a virtual machine on a
//	   * different operating system.
//	   *
//	   * <p> Note that when this abstract pathname represents a UNC pathname then
//	   * all components of the UNC (including the server name component) are encoded
//	   * in the {@code URI} path. The authority component is undefined, meaning
//	   * that it is represented as {@code null}. The {@link Path} class defines the
//	   * {@link Path#toUri toUri} method to encode the server name in the authority
//	   * component of the resulting {@code URI}. The {@link #toPath toPath} method
//	   * may be used to obtain a {@code Path} representing this abstract pathname.
//	   *
//	   * @return  An absolute, hierarchical URI with a scheme equal to
//	   *          <tt>"file"</tt>, a path representing this abstract pathname,
//	   *          and undefined authority, query, and fragment components
//	   * @throws SecurityException If a required system property value cannot
//	   * be accessed.
//	   *
//	   * @see #File(java.net.URI)
//	   * @see java.net.URI
//	   * @see java.net.URI#toURL()
//	   * @since 1.4
//	   */
//	  /**
//	   * Returns a {@link java.net.URI} equivalent to this URL.
//	   * This method functions in the same way as {@code new URI (this.toString())}.
//	   * <p>Note, any URL instance that complies with RFC 2396 can be converted
//	   * to a URI. However, some URLs that are not strictly in compliance
//	   * can not be converted to a URI.
//	   *
//	   * @exception URISyntaxException if this URL is not formatted strictly according to
//	   *            to RFC2396 and cannot be converted to a URI.
//	   *
//	   * @return    a URI instance equivalent to this URL.
//	   * @since 1.5
//	   */
//	//  public URI toURI() throws URISyntaxException {
//	    public URI toUri() {
//	    	return dataForkPath.toUri();
//	    }
//	
//
//	
//
//	
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
//	
//
//    
//	
//	public boolean equalsBinary(FileTMJbak file) throws IOException {
//		return equalsBinary(this,file);
//	}
//	/**
//	 * Compares the content of this file with the content of the given object.
//	 * The method must be able to open both of the files and read their content.
//	 * @param file - Object this file should be compared with.
//	 * @return - {@code true} if the contents are identically, {@code false} otherwise.
//	 * @throws IOException 
//	 */
//	public static boolean equalsBinary(FileTMJbak file1,FileTMJbak file2) throws IOException {
//		Logger.trace("file1={}, file2={}",file1,file2);
//		if(null==file1) { // if the first is null, we return true if all are null.
//			if(null==file2) {
//				return true;
//			}else {
//				return false;
//			}
//		}
//		if(null==file2) {
//			return false;
//		}
////		for (File f:files)
////			if(!isSuitable(f))
////				return false;
//		boolean b=compareConcreteIntern(file1,file2);
//		Logger.trace("return {}",b);
//		return b;
//	}
//	private static boolean compareConcreteIntern(FileTMJbak file1,FileTMJbak file2) throws IOException {
//		Logger.trace("compareConcreteIntern...");
////		if(files.length<2) // super class checked this
////			return false;
////		if (files[0]==null ^ files[1]==null)
////			return false;
////		if(files[0]==null)
////			return true;
////		byte[][] fileBytes=new byte[files.length][];
//		if(file1.hasDataFork()!=file2.hasDataFork()) { return false;}
//		if(file1.hasResourceFork()!=file2.hasResourceFork()) {return false;}
//		if(file1.hasDataFork() && !compareConcreteIntern(file1.dataForkPath,file2.dataForkPath)){return false;}
//		if(file1.hasResourceFork() && !compareConcreteIntern(file1.resourceForkPath, file2.resourceForkPath)) {return false;}
//		return true;
//	}
//	/**
//	 * Parameters must not be null
//	 * @param file1
//	 * @param file2
//	 * @return
//	 * @throws IOException 
//	 */
//	private static boolean compareConcreteIntern(Path file1,Path file2) throws IOException {
//		int internalBufferSize=1024;
//		Logger.trace("compareConcreteIntern...");
//		if(Files.size(file1)!=Files.size(file2)) {
//			return false;
//		}
//		
///*		 options parameter determines how the file is opened:
// * no are present = READ option. 
// * In addition, may additional implementationspecific options.
//		Throws:IllegalArgumentException - if an invalid combination of options is specified
//		UnsupportedOperationException - if an unsupported option is specified */
//		try( InputStream inputStream1=Files.newInputStream(file1);//, OpenOption.READ);
//			BufferedInputStream bufferedInputStream1=new BufferedInputStream(inputStream1);
//			InputStream inputStream2=Files.newInputStream(file2);//, OpenOption.READ);
//			BufferedInputStream bufferedInputStream2=new BufferedInputStream(inputStream2);){
//			int maxBytes,maxBytes2;		
//			while(true) {
//				/* estimate number bytes that can be read without blocking.
//				Throws: IOException - if this input stream is closed or an I/O error occurs.	*/
//				maxBytes=bufferedInputStream1.available();
//				maxBytes2=bufferedInputStream2.available();
//				if(maxBytes!=maxBytes2) {
//					return false;
//				}
//				if(1>maxBytes) {
//					break;
//				}
//				if(internalBufferSize<maxBytes) {
//					maxBytes=internalBufferSize;
//				}
//				/*  Returns: number bytes read into the buffer, or -1 if end of stream.
//				Throws:IOException - if an I/O error occurs.  */
//				byte[] fileBytes1=new byte[maxBytes];
//				bufferedInputStream1.read(fileBytes1);
//				byte[] fileBytes2=new byte[maxBytes];//Only available1 is correct for both!
//				bufferedInputStream2.read(fileBytes2);
//				if (!Arrays.equals(fileBytes1, fileBytes2)) { //TODO effektiver?
//					return false;
//				}
//			}
//		} catch (FileNotFoundException e) {
//			Logger.error("File \"{}\" could not be found!",file1);
//		} catch (IOException e) {
//			//by Files.newInputStream if an I/O error occurs
//			Logger.error("Error: Could not access file \"{}\"!",file2);
//		} catch (SecurityException e) {
//			//by Files.newInputStream if read access to the file is denied.
//			Logger.error("Error: Could not access file \"{}\"!",file2);
//		}
//
//		return true;
//	}
//
//	
//	/////// from Files ///////////
//	
//    /**
//     * Tests whether a file exists.
//     *
//     * <p> The {@code options} parameter may be used to indicate how symbolic links
//     * are handled for the case that the file is a symbolic link. By default,
//     * symbolic links are followed. If the option {@link LinkOption#NOFOLLOW_LINKS
//     * NOFOLLOW_LINKS} is present then symbolic links are not followed.
//     *
//     * <p> Note that the result of this method is immediately outdated. If this
//     * method indicates the file exists then there is no guarantee that a
//     * subsequence access will succeed. Care should be taken when using this
//     * method in security sensitive applications.
//     *
//     * @param   path
//     *          the path to the file to test
//     * @param   options
//     *          options indicating how symbolic links are handled
//     * .
//     * @return  {@code true} if the file exists; {@code false} if the file does
//     *          not exist or its existence cannot be determined.
//     *
//     * @throws  SecurityException
//     *          In the case of the default provider, the {@link
//     *          SecurityManager#checkRead(String)} is invoked to check
//     *          read access to the file.
//     *
//     * @see #notExists
//     */
//    public static boolean exists(Path path, LinkOption... options) {
//        try {
//            if (followLinks(options)) {
//                provider(path).checkAccess(path);
//            } else {
//                // attempt to read attributes without following links
//                readAttributes(path, BasicFileAttributes.class,
//                               LinkOption.NOFOLLOW_LINKS);
//            }
//            // file exists
//            return true;
//        } catch (IOException x) {
//            // does not exist or unable to determine if file exists
//            return false;
//        }
//
//    }
//
//	
//
