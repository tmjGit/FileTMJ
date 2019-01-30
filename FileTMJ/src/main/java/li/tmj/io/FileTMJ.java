package li.tmj.io;

    import java.io.BufferedInputStream;
    import java.io.BufferedOutputStream;
    import java.io.ByteArrayOutputStream;
    import java.io.File;
    import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;   // javadoc
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.FileStoreAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.spi.FileSystemProvider;
import java.nio.file.spi.FileTypeDetector;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiPredicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import static java.security.AccessController.doPrivileged;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystemLoopException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.LinkPermission;
import java.nio.file.NoSuchFileException;
import java.nio.file.NotDirectoryException;
import java.nio.file.NotLinkException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SecureDirectoryStream;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
//import java.nio.file.CopyMoveHelper;
//import java.nio.file.TempFileHelper;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.pmw.tinylog.Logger;
import li.tmj.io.FileTMJ.CompareResult.CompareResultDetail;
import sun.security.action.GetPropertyAction;

public class FileTMJ implements Iterable<Path>, Comparable<FileTMJ>, Serializable {
	private static final long serialVersionUID = 5094284926669869769L;
	private FileSystem fileSystem;
	private Path dataForkPath;
	private Path resourceForkPath;
	
    /**
     * The system-dependent default name-separator character.  This field is
     * initialized to contain the first character of the value of the system
     * property <code>file.separator</code>.  On UNIX systems the value of this
     * field is <code>'/'</code>; on Microsoft Windows systems it is <code>'\\'</code>.
     *
     * @see     java.lang.System#getProperty(java.lang.String)
     */
    public static final char separatorChar = '?';//TODO IMPLEMENT! fs.getSeparator();

    /**
     * The system-dependent default name-separator character, represented as a
     * string for convenience.  This string contains a single character, namely
     * <code>{@link #separatorChar}</code>.
     */
    public static final String separator = "" + separatorChar;//TODO IMPLEMENT! fs.getSeparator();

    /**
     * The system-dependent path-separator character.  This field is
     * initialized to contain the first character of the value of the system
     * property <code>path.separator</code>.  This character is used to
     * separate filenames in a sequence of files given as a <em>path list</em>.
     * On UNIX systems, this character is <code>':'</code>; on Microsoft Windows systems it
     * is <code>';'</code>.
     *
     * @see     java.lang.System#getProperty(java.lang.String)
     */
    public static final char pathSeparatorChar = '?';//TODO IMPLEMENT!  fs.getPathSeparator();

    /**
     * The system-dependent path-separator character, represented as a string
     * for convenience.  This string contains a single character, namely
     * <code>{@link #pathSeparatorChar}</code>.
     */
    public static final String pathSeparator = "" + pathSeparatorChar;//TODO IMPLEMENT! fs.getSeparator();

    
    
    
	/**
	 * Constructs a FileTMJ object from this path. The resulting object is associated with the default-filesystem.
	 * @param path – the path
	 * @throws InvalidPathException - if a Path object cannot be constructed from the abstract path (see FileSystem.getPath)
	 */
	public FileTMJ(Path path) {
		init(path);
	}
	
	public FileTMJ(File file) throws InvalidPathException {
		this(file.toPath());
	}

	public FileTMJ(String filePath) {
		this(filePath,FileSystems.getDefault()); // =  return FileSystems.getDefault().getPath(first, more); = 	FileTMJ(FileSystems.getDefault(), filePath) {
	}
	
	public FileTMJ(String filePath, FileSystem fileSystem) {
		this(fileSystem.getPath(filePath));
		/**
	     * Converts a path string, or a sequence of strings that when joined form
	     * a path string, to a {@code Path}. If {@code more} does not specify any
	     * elements then the value of the {@code first} parameter is the path string
	     * to convert. If {@code more} specifies one or more elements then each
	     * non-empty string, including {@code first}, is considered to be a sequence
	     * of name elements (see {@link Path}) and is joined to form a path string.
	     * The details as to how the Strings are joined is provider specific but
	     * typically they will be joined using the {@link FileSystem#getSeparator
	     * name-separator} as the separator. For example, if the name separator is
	     * "{@code /}" and {@code getPath("/foo","bar","gus")} is invoked, then the
	     * path string {@code "/foo/bar/gus"} is converted to a {@code Path}.
	     * A {@code Path} representing an empty path is returned if {@code first}
	     * is the empty string and {@code more} does not contain any non-empty
	     * strings.
	     *
	     * <p> The {@code Path} is obtained by invoking the {@link FileSystem#getPath
	     * getPath} method of the {@link FileSystems#getDefault default} {@link
	     * FileSystem}.
	     *
	     * <p> Note that while this method is very convenient, using it will imply
	     * an assumed reference to the default {@code FileSystem} and limit the
	     * utility of the calling code. Hence it should not be used in library code
	     * intended for flexible reuse. A more flexible alternative is to use an
	     * existing {@code Path} instance as an anchor, such as:
	     * <pre>
	     *     Path dir = ...
	     *     Path path = dir.resolve("file");
	     * </pre>
	     *
	     * @param   first
	     *          the path string or initial part of the path string
	     * @param   more
	     *          additional strings to be joined to form the path string
	     *
	     * @return  the resulting {@code Path}
	     *
	     * @throws  InvalidPathException
	     *          if the path string cannot be converted to a {@code Path}
	     *
	     * @see FileSystem#getPath
	     */
	}
	


	
	private void init(Path path) {
		dataForkPath=path;
		if(null==dataForkPath) {
			fileSystem=null;
		}else {
			fileSystem=dataForkPath.getFileSystem();		
		}
		resourceForkPath=resourceFork(dataForkPath);
	}
	
	private Path resourceFork(Path path) {
//		return path.resolve("rsrc"); // ≤ 10.?
		return path.resolve("..namedfork/rsrc"); // > 10.?
	}

	public boolean hasDataFork() throws IOException {
//	    	FileSystem dataForkPath.getFileSystem())		dataForkPath.getFileSystem().getClass().getSimpleName()	"MacOSXFileSystem"
		  	//System.getProperty("os.name")		"Mac OS X"	bei macOS 10.13.6 High Sierra
		return hasFork(dataForkPath);
	}
	public boolean hasResourceFork() throws IOException {
		return hasFork(resourceForkPath);
	}
		private boolean hasFork(Path path) throws IOException {
		// Files.exists tries to read file attributes and interpretes an error as not-exists!
		// So we don't need that separated.
		try {
			return 0<readBasicFileAttributes(path, LinkOption.NOFOLLOW_LINKS).size(); // file exists and is not empty
		} catch (IOException x) { // does not exist or unable to determine if file exists
			return false;
		}
	}
	
	
    /**
     * from: Files.readAttributes und Files.provider
     * Reads a file's attributes as a bulk operation.
     *
     * <p> The {@code type} parameter is the type of the attributes required
     * and this method returns an instance of that type if supported. All
     * implementations support a basic set of file attributes and so invoking
     * this method with a  {@code type} parameter of {@code
     * BasicFileAttributes.class} will not throw {@code
     * UnsupportedOperationException}.
     *
     * <p> The {@code options} array may be used to indicate how symbolic links
     * are handled for the case that the file is a symbolic link. By default,
     * symbolic links are followed and the file attribute of the final target
     * of the link is read. If the option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is present then symbolic links are not followed.
     *
     * <p> It is implementation specific if all file attributes are read as an
     * atomic operation with respect to other file system operations.
     *
     * <p> <b>Usage Example:</b>
     * Suppose we want to read a file's attributes in bulk:
     * <pre>
     *    Path path = ...
     *    BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
     * </pre>
     * Alternatively, suppose we want to read file's POSIX attributes without
     * following symbolic links:
     * <pre>
     *    PosixFileAttributes attrs = Files.readAttributes(path, PosixFileAttributes.class, NOFOLLOW_LINKS);
     * </pre>
     *
     * @param   <A>
     *          The {@code BasicFileAttributes} type
     * @param   path
     *          the path to the file
     * @param   type
     *          the {@code Class} of the file attributes required
     *          to read
     * @param   options
     *          options indicating how symbolic links are handled
     *
     * @return  the file attributes
     *
     * @throws  UnsupportedOperationException
     *          if an attributes of the given type are not supported
     * @throws  IOException
     *          if an I/O error occurs
     * @throws  SecurityException
     *          In the case of the default provider, a security manager is
     *          installed, its {@link SecurityManager#checkRead(String) checkRead}
     *          method is invoked to check read access to the file. If this
     *          method is invoked to read security sensitive attributes then the
     *          security manager may be invoke to check for additional permissions.
     */
    private static BasicFileAttributes readBasicFileAttributes(Path path, LinkOption... options) throws IOException {
    	return path.getFileSystem().provider().readAttributes(path, BasicFileAttributes.class, options);
    }
    private static <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption... options) throws IOException {
    	return path.getFileSystem().provider().readAttributes(path, type, options);
    }

	
	


    
    /**
     * Tests whether this file/directory exists.
     *
     * @return  <code>true</code> if and only if the file or directory denoted
     *          by this abstract pathname exists; <code>false</code> otherwise
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
     *          method denies read access to the file or directory
     */
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkRead(path);
//        }
//        if (isInvalid()) {
//            return false;
//        }
//        return ((fs.getBooleanAttributes(this) & FileSystem.BA_EXISTS) != 0);
    /**
     * Tests whether the file/directory exists.
     * The {@code options} parameter may be used to indicate how symbolic links are handled for the case that the file is a symbolic link.
     * By default, symbolic links are not followed but treated as itself. If option is provided without {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} then symbolic links are followed.
     *
     * <p> Note that the result of this method is immediately outdated. If this
     * method indicates the file exists then there is no guarantee that a
     * subsequence access will succeed. Care should be taken when using this
     * method in security sensitive applications.
     *
     * @param   options
     *          options indicating how symbolic links are handled
     * 
     * @return  {@code true} if the file exists; {@code false} if the file does
     *          not exist.
     *
     * @throws  SecurityException
     *          If a {@link SecurityManager} prohibits the test.
     */
    public boolean exists() throws SecurityException{
    	return exists(LinkOption.NOFOLLOW_LINKS);
    }
    public boolean exists(LinkOption... linkOptions) throws SecurityException{
//		return Files.exists(dataForkPath, linkOptions) || Files.exists(resourceForkPath, linkOptions);
		try {
			readBasicFileAttributes(dataForkPath, linkOptions); // file exists
			return true;
		} catch (IOException e) { // does not exist or unable to determine if file exists
			try {
				readBasicFileAttributes(resourceForkPath, linkOptions); // file exists
				return true;
			} catch (IOException e2) { // does not exist or unable to determine if file exists
				return false;
			}
		}
    }

//    /**
//     * Tests whether the file located by this path does not exist. This method
//     * is intended for cases where it is required to take action when it can be
//     * confirmed that a file does not exist.
//     *
//     * <p> The {@code options} parameter may be used to indicate how symbolic links
//     * are handled for the case that the file is a symbolic link. By default,
//     * symbolic links are followed. If the option {@link LinkOption#NOFOLLOW_LINKS
//     * NOFOLLOW_LINKS} is present then symbolic links are not followed.
//     *
//     * <p> Note that this method is not the complement of the {@link #exists
//     * exists} method. Where it is not possible to determine if a file exists
//     * or not then both methods return {@code false}. As with the {@code exists}
//     * method, the result of this method is immediately outdated. If this
//     * method indicates the file does exist then there is no guarantee that a
//     * subsequence attempt to create the file will succeed. Care should be taken
//     * when using this method in security sensitive applications.
//     *
//     * @param   path
//     *          the path to the file to test
//     * @param   options
//     *          options indicating how symbolic links are handled
//     *
//     * @return  {@code true} if the file does not exist; {@code false} if the
//     *          file exists or its existence cannot be determined
//     *
//     * @throws  SecurityException
//     *          In the case of the default provider, the {@link
//     *          SecurityManager#checkRead(String)} is invoked to check
//     *          read access to the file.
//     */
//    public static boolean notExists(Path path, LinkOption... options) {
//    	throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
////        try {
////            if (followLinks(options)) {
////                provider(path).checkAccess(path);
////            } else {
////                // attempt to read attributes without following links
////                readAttributes(path, BasicFileAttributes.class,
////                               LinkOption.NOFOLLOW_LINKS);
////            }
////            // file exists
////            return false;
////        } catch (NoSuchFileException x) {
////            // file confirmed not to exist
////            return true;
////        } catch (IOException x) {
////            return false;
////        }
//    }
    
    /**
     * Tests whether the object is an existing directory.
     * The {@code options} parameter may be used to indicate how symbolic links are handled for the case that the file is a symbolic link.
     * By default, symbolic links are not followed but treated as itself. If option is provided without {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} then symbolic links are followed.
     *
     * <p> Where it is required to distinguish an I/O exception from the case
     * that the file is not a directory, or where several attributes of the
     * same file are required at the same time, then the {@link
     * java.nio.file.Files#readAttributes(Path,Class,LinkOption[])
     * Files.readAttributes} method may be used.
     *
     * @param   options
     *          options indicating how symbolic links are handled
     *
     * @return  {@code true} if the file an existing directory,
     *          {@code false} otherwise
     *
     * @throws  SecurityException
     *          If a security manager denies read access to the file
     */
	public boolean isDirectory() {
		return isDirectory(LinkOption.NOFOLLOW_LINKS);
	}
	public boolean isDirectory(LinkOption... linkOptions) {
//		return Files.isDirectory(dataForkPath, linkOptions);
        try {
            return readAttributes(dataForkPath, BasicFileAttributes.class, linkOptions).isDirectory();
        } catch (IOException ioe) {
            return false;
        }
    }

    /**
     * Tests whether the object is an existing directory.
     * The {@code options} parameter may be used to indicate how symbolic links are handled for the case that the file is a symbolic link.
     * By default, symbolic links are not followed but treated as itself. If option is provided without {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} then symbolic links are followed.
     *
     * <p> Where it is required to distinguish an I/O exception from the case
     * that the file is not a regular file then the file attributes can be
     * read with the {@link #readAttributes(Path,Class,LinkOption[])
     * readAttributes} method and the file type tested with the {@link
     * BasicFileAttributes#isRegularFile} method.
     *
     * @param   options
     *          options indicating how symbolic links are handled
     *
     * @return  {@code true} if the file an existing regular file,
     *          {@code false} otherwise
     *
     * @throws  SecurityException
     *          If a security manager denies read access to the file
     */
	public boolean isRegularFile() {
		return isRegularFile(LinkOption.NOFOLLOW_LINKS);
	}
	public boolean isRegularFile(LinkOption... linkOptions) {
//		return Files.isRegularFile(dataForkPath, linkOptions);
        try {
            return readAttributes(dataForkPath, BasicFileAttributes.class, linkOptions).isRegularFile();
        } catch (IOException ioe) {
            return false;
        }
    }
    
	public String extension() {
		return extension(dataForkPath);
	}
	public static String extension(Path path) { // returns extension, if any
		Path p=path.getFileName();
		if(null==p) {
			return null;
		}
		int i = p.toString().lastIndexOf('.');
		if(i<0) {return "";}
	    return p.toString().substring(i+1);
	}

	/**
	 * file system directory that is normally displayed to the user by the Finder as if it were a single file. 
	 * com.apple.package
	 * .rtfd, bundle .app, dashboard widget, .download, FCP .fcarch, project files GarageBand, Keynote, Pages, Numbers, iMovie, Xcode; Installer packages
	 * To register a document as a package, you must modify the document type information in your application’s information property list (Info.plist) file.
	 * The CFBundleDocumentTypes key stores information about the document types your application supports. For each document package type, include the 
	 * LSTypeIsPackage key with an appropriate value. The presence of this key tells the Finder and Launch Services to treat directories with the given file 
	 * extension as a package. For more information about Info.plist keys, see Information Property List Key Reference.
	 * Document packages should always have an extension to identify them—even though that extension may be hidden by the user. The extension allows the Finder 
	 * to identify your document directory and treat it as a package. You should never associate a document package with a MIME type or 4-byte OS type.
	 * 
	 * package if any of:
	 * has a known filename extension.
	 * has an extension of registered Document Packages.
	 * has its package bit set.
	 */
	public boolean isPackage() {
		return isPackage( extension() );
	}
	
	private static boolean isPackage(String extension) {
		switch(extension) {
			case "": return false; // no package
			case "app": // known package extensions
			case "bundle":
			case "framework":
			case "plugin":
			case "kext":
			case "lproj": // localization files
				return true; 
			default: return false; // no package found
		}
	}

	public boolean isEmpty() throws SecurityException, IOException {
		return 0==sizeBytes();
	}
    
    
    
    
    
    
    
    
    
    
    

    /**
	 * Returns the <em>root</em> {@code FileTMJ} of this file,
	 * or {@code null} if there is none.
	 *
	 * @return  a FileTMJ representing the root component of this file, or {@code null}
	 */
	public FileTMJ getRoot(){
		Path root=dataForkPath.getRoot();
		if(null==root) {
			return null;
		}
		return new FileTMJ(root);
	}

	/**
	 * Returns the <em>name</em> of the file or directory denoted by this file as a
	 * {@code String} object. The file name is the <em>farthest</em> element from
	 * the root in the directory hierarchy.
	 *
	 * @return  a String representing the name of the file or directory, or
	 *          {@code null} if this file has zero elements
	 */
	public String getFileName(){
		return dataForkPath.getFileName().toString();// root -> null
//		return dataForkPath.toFile().getName();//root -> ""
	}
  /**
   * @return  The name of the file or directory denoted by this abstract
   *          pathname, or the empty string if this pathname's name sequence
   *          is empty
   */

  /**
   * Ignores the macOS Fork-Duality.
   * Returns a {@link Path} representing this file's path. The resulting path is associated with the
   * {@link java.nio.file.FileSystems#getDefault default-filesystem}.
   * The resulting
   * string uses the {@link #separator default name-separator character} to
   * separate the names in the name sequence.
   *
   * @return  The string form of this abstract pathname
   * @throws  java.nio.file.InvalidPathException
   *          if a {@code Path} object cannot be constructed from the abstract
   *          file (see {@link java.nio.file.FileSystem#getPath FileSystem.getPath})
   */
  public Path getPath() {
	  return dataForkPath;
  }
  public String getPathString() {
	  return dataForkPath.toString();
  }
  
  
  /**
   * Returns a path that is this path with redundant name elements eliminated.
   *
   * <p> The precise definition of this method is implementation dependent but
   * in general it derives from this path, a path that does not contain
   * <em>redundant</em> name elements. In many file systems, the "{@code .}"
   * and "{@code ..}" are special names used to indicate the current directory
   * and parent directory. In such file systems all occurrences of "{@code .}"
   * are considered redundant. If a "{@code ..}" is preceded by a
   * non-"{@code ..}" name then both names are considered redundant (the
   * process to identify such names is repeated until it is no longer
   * applicable).
   *
   * <p> This method does not access the file system; the path may not locate
   * a file that exists. Eliminating "{@code ..}" and a preceding name from a
   * path may result in the path that locates a different file than the original
   * path. This can arise when the preceding name is a symbolic link.
   *
   * @return  the resulting path or this path if it does not contain
   *          redundant name elements; an empty path is returned if this path
   *          does have a root component and all name elements are redundant
   *
   * @see #getParent
   * @see #toRealPath
   */
  /**
   * Normalizes this URI's path.
   *
   * <p> If this URI is opaque, or if its path is already in normal form,
   * then this URI is returned.  Otherwise a new URI is constructed that is
   * identical to this URI except that its path is computed by normalizing
   * this URI's path in a manner consistent with <a
   * href="http://www.ietf.org/rfc/rfc2396.txt">RFC&nbsp;2396</a>,
   * section&nbsp;5.2, step&nbsp;6, sub-steps&nbsp;c through&nbsp;f; that is:
   * </p>
   *
   * <ol>
   *
   *   <li><p> All {@code "."} segments are removed. </p></li>
   *
   *   <li><p> If a {@code ".."} segment is preceded by a non-{@code ".."}
   *   segment then both of these segments are removed.  This step is
   *   repeated until it is no longer applicable. </p></li>
   *
   *   <li><p> If the path is relative, and if its first segment contains a
   *   colon character ({@code ':'}), then a {@code "."} segment is
   *   prepended.  This prevents a relative URI with a path such as
   *   {@code "a:b/c/d"} from later being re-parsed as an opaque URI with a
   *   scheme of {@code "a"} and a scheme-specific part of {@code "b/c/d"}.
   *   <b><i>(Deviation from RFC&nbsp;2396)</i></b> </p></li>
   *
   * </ol>
   *
   * <p> A normalized path will begin with one or more {@code ".."} segments
   * if there were insufficient non-{@code ".."} segments preceding them to
   * allow their removal.  A normalized path will begin with a {@code "."}
   * segment if one was inserted by step 3 above.  Otherwise, a normalized
   * path will not contain any {@code "."} or {@code ".."} segments. </p>
   *
   * @return  A URI equivalent to this URI,
   *          but whose path is in normal form
   */
  public Path toNormalized() {
	  return dataForkPath.normalize();
  }
  /**
   * Returns {@code toNormalized} and changes the internal path representation of this FileTMJ accordingly.
   * @return
   */
  public Path normalize() {
	  init(toNormalized());
	  return dataForkPath;
  }



  /**
   * Tells whether or not this path is absolute.
   *
   * <p> An absolute path is complete in that it doesn't need to be combined
   * with other path information in order to locate a file.
   *
   * @return  {@code true} if, and only if, this path is absolute
   */
  /**
   * Tests whether this abstract pathname is absolute.  The definition of
   * absolute pathname is system dependent.  On UNIX systems, a pathname is
   * absolute if its prefix is <code>"/"</code>.  On Microsoft Windows systems, a
   * pathname is absolute if its prefix is a drive specifier followed by
   * <code>"\\"</code>, or if its prefix is <code>"\\\\"</code>.
   *
   * @return  <code>true</code> if this abstract pathname is absolute,
   *          <code>false</code> otherwise
   */
  /**
   * Tells whether or not this URI is absolute.
   *
   * <p> A URI is absolute if, and only if, it has a scheme component. </p>
   *
   * @return  {@code true} if, and only if, this URI is absolute
   */
  public boolean isAbsolute() {
	  return dataForkPath.isAbsolute();
//      return scheme != null;
  }
  

  
  /**
   * Returns a {@code Path} object representing the absolute path of this
   * path.
   *
   * <p> If this path is already {@link Path#isAbsolute absolute} then this
   * method simply returns this path. Otherwise, this method resolves the path
   * in an implementation dependent manner, typically by resolving the path
   * against a file system default directory. Depending on the implementation,
   * this method may throw an I/O error if the file system is not accessible.
   *
   * @return  a {@code Path} object representing the absolute path
   *
   * @throws  java.io.IOError
   *          if an I/O error occurs
   * @throws  SecurityException
   *          In the case of the default provider, a security manager
   *          is installed, and this path is not absolute, then the security
   *          manager's {@link SecurityManager#checkPropertyAccess(String)
   *          checkPropertyAccess} method is invoked to check access to the
   *          system property {@code user.dir}
   */
  /**
   * Returns the absolute pathname string of this abstract pathname.
   *
   * <p> If this abstract pathname is already absolute, then the pathname
   * string is simply returned as if by the <code>{@link #getPath}</code>
   * method.  If this abstract pathname is the empty abstract pathname then
   * the pathname string of the current user directory, which is named by the
   * system property <code>user.dir</code>, is returned.  Otherwise this
   * pathname is resolved in a system-dependent way.  On UNIX systems, a
   * relative pathname is made absolute by resolving it against the current
   * user directory.  On Microsoft Windows systems, a relative pathname is made absolute
   * by resolving it against the current directory of the drive named by the
   * pathname, if any; if not, it is resolved against the current user
   * directory.
   *
   * @return  The absolute pathname string denoting the same file or
   *          directory as this abstract pathname
   *
   * @throws  SecurityException
   *          If a required system property value cannot be accessed.
   *
   * @see     java.io.File#isAbsolute()
   */
  /**
   * Returns the absolute form of this abstract pathname.  Equivalent to
   * <code>new&nbsp;File(this.{@link #getAbsolutePath})</code>.
   *
   * @return  The absolute abstract pathname denoting the same file or
   *          directory as this abstract pathname
   *
   * @throws  SecurityException
   *          If a required system property value cannot be accessed.
   *
   * @since 1.2
   */
  public Path toAbsolutePath() {
	  return dataForkPath.toAbsolutePath();
  }
  /**
   * Returns {@code toAbsolutePath} and changes the internal path representation of this FileTMJ accordingly.
   * @return
   */
  public Path absolutize() {
	  init(toAbsolutePath());
	  return dataForkPath;
  }

  /**
   * Returns the <em>real</em> path of an existing file.
   *
   * <p> The precise definition of this method is implementation dependent but
   * in general it derives from this path, an {@link #isAbsolute absolute}
   * path that locates the {@link Files#isSameFile same} file as this path, but
   * with name elements that represent the actual name of the directories
   * and the file. For example, where filename comparisons on a file system
   * are case insensitive then the name elements represent the names in their
   * actual case. Additionally, the resulting path has redundant name
   * elements removed.
   *
   * <p> If this path is relative then its absolute path is first obtained,
   * as if by invoking the {@link #toAbsolutePath toAbsolutePath} method.
   *
   * <p> The {@code options} array may be used to indicate how symbolic links
   * are handled. By default, symbolic links are resolved to their final
   * target. If the option {@link LinkOption#NOFOLLOW_LINKS NOFOLLOW_LINKS} is
   * present then this method does not resolve symbolic links.
   *
   * Some implementations allow special names such as "{@code ..}" to refer to
   * the parent directory. When deriving the <em>real path</em>, and a
   * "{@code ..}" (or equivalent) is preceded by a non-"{@code ..}" name then
   * an implementation will typically cause both names to be removed. When
   * not resolving symbolic links and the preceding name is a symbolic link
   * then the names are only removed if it guaranteed that the resulting path
   * will locate the same file as this path.
   *
   * @param   options
   *          options indicating how symbolic links are handled
   *
   * @return  an absolute path represent the <em>real</em> path of the file
   *          located by this object
   *
   * @throws  IOException
   *          if the file does not exist or an I/O error occurs
   * @throws  SecurityException
   *          In the case of the default provider, and a security manager
   *          is installed, its {@link SecurityManager#checkRead(String) checkRead}
   *          method is invoked to check read access to the file, and where
   *          this path is not absolute, its {@link SecurityManager#checkPropertyAccess(String)
   *          checkPropertyAccess} method is invoked to check access to the
   *          system property {@code user.dir}
   */
  public Path toRealPath(LinkOption... options) throws IOException{
	  return dataForkPath.toRealPath(options);
}
/**
 * Returns {@code toRealPath} and changes the internal path representation of this FileTMJ accordingly.
 * @return
 * @throws IOException 
 */
  public Path realize(LinkOption... options) throws IOException {
	  init(toRealPath(options));
	  return dataForkPath;
  }

/**
 * Returns the canonical pathname string of this abstract pathname.
 *
 * <p> A canonical pathname is both absolute and unique.  The precise
 * definition of canonical form is system-dependent.  This method first
 * converts this pathname to absolute form if necessary, as if by invoking the
 * {@link #getAbsolutePath} method, and then maps it to its unique form in a
 * system-dependent way.  This typically involves removing redundant names
 * such as <tt>"."</tt> and <tt>".."</tt> from the pathname, resolving
 * symbolic links (on UNIX platforms), and converting drive letters to a
 * standard case (on Microsoft Windows platforms).
 *
 * <p> Every pathname that denotes an existing file or directory has a
 * unique canonical form.  Every pathname that denotes a nonexistent file
 * or directory also has a unique canonical form.  The canonical form of
 * the pathname of a nonexistent file or directory may be different from
 * the canonical form of the same pathname after the file or directory is
 * created.  Similarly, the canonical form of the pathname of an existing
 * file or directory may be different from the canonical form of the same
 * pathname after the file or directory is deleted.
 *
 * @return  The canonical pathname string denoting the same file or
 *          directory as this abstract pathname
 *
 * @throws  IOException
 *          If an I/O error occurs, which is possible because the
 *          construction of the canonical pathname may require
 *          filesystem queries
 *
 * @throws  SecurityException
 *          If a required system property value cannot be accessed, or
 *          if a security manager exists and its <code>{@link
 *          java.lang.SecurityManager#checkRead}</code> method denies
 *          read access to the file
 *
 * @since   JDK1.1
 * @see     Path#toRealPath
 */
/**
 * Returns the canonical form of this abstract pathname.  Equivalent to
 * <code>new&nbsp;File(this.{@link #getCanonicalPath})</code>.
 *
 * @return  The canonical pathname string denoting the same file or
 *          directory as this abstract pathname
 *
 * @throws  IOException
 *          If an I/O error occurs, which is possible because the
 *          construction of the canonical pathname may require
 *          filesystem queries
 *
 * @throws  SecurityException
 *          If a required system property value cannot be accessed, or
 *          if a security manager exists and its <code>{@link
 *          java.lang.SecurityManager#checkRead}</code> method denies
 *          read access to the file
 *
 * @since 1.2
 * @see     Path#toRealPath
 */
  public Path toCanonicalPath() throws IOException {
//	  return Paths.get(toCanonicalPathString());
	  return fileSystem.getPath(toCanonicalPathString());
//    if (isInvalid()) {
//        throw new IOException("Invalid file path");
//    }
//    return fs.canonicalize(fs.resolve(this));
//    String canonPath = getCanonicalPath();
//    return new File(canonPath, fs.prefixLength(canonPath));
  }
  public String toCanonicalPathString() throws IOException {
	  return dataForkPath.toFile().getCanonicalPath();
  }
 
/**
 * ignores the duality of the macOS forks.
 * @return
 */
  public File toFile(){
		return dataForkPath.toFile();
  }
 
  /**
   * ignores the duality of the macOS forks.
   * @return
   */
	public Path toPath() {
		return dataForkPath;
	}


	 /**
     * Returns a URI to represent this file/directory.
     *
     * <p> This method constructs an absolute {@link URI} with a {@link
     * URI#getScheme() scheme} equal to the URI scheme that identifies the
     * provider. The exact form of the scheme specific part is highly provider
     * dependent.
     *
     * <p> In the case of the default provider, the URI is hierarchical with
     * a {@link URI#getPath() path} component that is absolute. The query and
     * fragment components are undefined. Whether the authority component is
     * defined or not is implementation dependent. There is no guarantee that
     * the {@code URI} may be used to construct a {@link java.io.File java.io.File}.
     * In particular, if this path represents a Universal Naming Convention (UNC)
     * path, then the UNC server name may be encoded in the authority component
     * of the resulting URI. In the case of the default provider, and the file
     * exists, and it can be determined that the file is a directory, then the
     * resulting {@code URI} will end with a slash.
     *
     * <p> The default provider provides a similar <em>round-trip</em> guarantee
     * to the {@link java.io.File} class. For a given {@code Path} <i>p</i> it
     * is guaranteed that
     * <blockquote><tt>
     * {@link Paths#get(URI) Paths.get}(</tt><i>p</i><tt>.toUri()).equals(</tt><i>p</i>
     * <tt>.{@link #toAbsolutePath() toAbsolutePath}())</tt>
     * </blockquote>
     * so long as the original {@code Path}, the {@code URI}, and the new {@code
     * Path} are all created in (possibly different invocations of) the same
     * Java virtual machine. Whether other providers make any guarantees is
     * provider specific and therefore unspecified.
     *
     * <p> When a file system is constructed to access the contents of a file
     * as a file system then it is highly implementation specific if the returned
     * URI represents the given path in the file system or it represents a
     * <em>compound</em> URI that encodes the URI of the enclosing file system.
     * A format for compound URIs is not defined in this release; such a scheme
     * may be added in a future release.
     *
     * @return  the URI representing this path
     *
     * @throws  java.io.IOError
     *          if an I/O error occurs obtaining the absolute path, or where a
     *          file system is constructed to access the contents of a file as
     *          a file system, and the URI of the enclosing file system cannot be
     *          obtained
     *
     * @throws  SecurityException
     *          In the case of the default provider, and a security manager
     *          is installed, the {@link #toAbsolutePath toAbsolutePath} method
     *          throws a security exception.
     */
	public URI toURI() {
		return dataForkPath.toUri();
	}

    /**
     * Constructs a URL from this URI.
     *
     * <p> This convenience method works as if invoking it were equivalent to
     * evaluating the expression {@code new URL(this.toString())} after
     * first checking that this URI is absolute. </p>
     *
     * @return  A URL constructed from this URI
     *
     * @throws  IllegalArgumentException
     *          If this URL is not absolute
     *
     * @throws  MalformedURLException
     *          If a protocol handler for the URL could not be found,
     *          or if some other error occurred while constructing the URL
     */
    public URL toURL() throws MalformedURLException {
	  	return toURI().toURL();
    }
    
    
  /**
   */
  public static FileTMJ getHome() {
	  	throw new RuntimeException("Method not implemented!");//TODO IMPLEMENT!       return path;
	    /**
	     * <p> If this abstract pathname is the empty abstract pathname then this
	     * method returns a {@code Path} that may be used to access the current
	     * user directory.
	     *
	     */
//	  Path result ;//= filePath;
//	  if (result == null) {
//	      synchronized (this) {
//	          result = filePath;
//	          if (result == null) {
//	              result = FileSystems.getDefault().getPath(path);
//	              result = FileSystems.getDefault().getPath("");
//	              filePath = result;
//	          }
//	      }
//	  }
//	  return new FileTMJ(result);

	  
  }
  
  /**
   * Returns the file system that created this file/directory.
   * This file/directory must exist to make this work.
   *
   * @return  the file system that created this object or null if the object does not exist.
   */
  public FileSystem getFileSystem(){
	  return fileSystem;
	}


  
	/**
	 * Returns the <em>parent</em> {@code FileTMJ} or {@code null} if there is none.
	 *
	 * <p> The parent of this file consists of this file's root
	 * component, if any, and each element in the file path except for the
	 * <em>farthest</em> from the root in the directory hierarchy. This method
	 * does not access the file system; the file or its parent may not exist.
	 * Furthermore, this method does not eliminate special names such as "."
	 * and ".." that may be used in some implementations. On UNIX for example,
	 * the parent of "{@code /a/b/c}" is "{@code /a/b}", and the parent of
	 * {@code "x/y/.}" is "{@code x/y}". This method may be used with the {@link
	 * #normalize normalize} method, to eliminate redundant names, for cases where
	 * <em>shell-like</em> navigation is required.
	 * <p> If this file has one or more elements, and no root component, then
	 * this method is equivalent to evaluating the expression:
	 * <blockquote><pre>
	 * subpath(0,&nbsp;getNameCount()-1);
	 * </pre></blockquote>
	 *
	 * @return  a FileTMJ representing the file's parent
	 */
	public FileTMJ getParent(){
		Path parent=dataForkPath.getParent();
		if(null==parent) {
			return null;
		}
		return new FileTMJ(parent);
//      if (p == null) return null;
//      return new File(p, this.prefixLength);

//      if (index < prefixLength) {
//      if ((prefixLength > 0) && (path.length() > prefixLength))
//          return path.substring(0, prefixLength);
//      return null;
//  }
//  return path.substring(0, index);
	}



    /**
     * Resolve the given path against this path.
     *
     * <p> If the {@code other} parameter is an {@link #isAbsolute() absolute}
     * path then this method trivially returns {@code other}. If {@code other}
     * is an <i>empty path</i> then this method trivially returns this path.
     * Otherwise this method considers this path to be a directory and resolves
     * the given path against this path. In the simplest case, the given path
     * does not have a {@link #getRoot root} component, in which case this method
     * <em>joins</em> the given path to this path and returns a resulting path
     * that {@link #endsWith ends} with the given path. Where the given path has
     * a root component then resolution is highly implementation dependent and
     * therefore unspecified.
     *
     * Converts a given path string to a {@code Path} and resolves it against
     * this {@code FileTMJ} in exactly the manner specified by the {@link
     * #resolve(Path) resolve} method. For example, suppose that the name
     * separator is "{@code /}" and a path represents "{@code foo/bar}", then
     * invoking this method with the path string "{@code gus}" will result in
     * a FileTMJ representing the path "{@code foo/bar/gus}".
     *
     * @param   other
     *          the path string to resolve against this file
     *
     * @return  the resulting path
     *
     * @throws  InvalidPathException
     *          if the path string cannot be converted to a Path.
     *
     * @see FileSystem#getPath
     */
    public FileTMJ child(String child) throws InvalidPathException{
	  	return new FileTMJ(dataForkPath.resolve(child));
	}
    public FileTMJ child(Path child) throws InvalidPathException{
	  	return new FileTMJ(dataForkPath.resolve(child));
	}
    
    /**
	 * Returns the number of elements in this file's path.
	 *
	 * @return  number of path elements, or {@code 0} if this file
	 *          only represents a root component
	 */
	public int getPathElementCount(){
		return dataForkPath.getNameCount();
	}

	/**
	 * Returns an element of this file's path as a {@code String} object.
	 *
	 * <p> The {@code index} parameter is the index of the element to return.
	 * The element that is <em>closest</em> to the root in the directory hierarchy
	 * has index {@code 0}. The element that is <em>farthest</em> from the root
	 * has index {@link #getNameCount count}{@code -1}.
	 *
	 * @param   index
	 *          the index of the element
	 *
	 * @return  element
	 *
	 * @throws  IllegalArgumentException
	 *          if {@code index} is negative or {@code index} is greater than or
	 *          equal to the number of elements.
	 */
	public Path getPathElementName(int index) throws IllegalArgumentException{
		return dataForkPath.getName(index);
	}

    /**
     * Constructs a relative path between this path and a given path.
     *
     * <p> Relativization is the inverse of {@link #resolve(Path) resolution}.
     * This method attempts to construct a {@link #isAbsolute relative} path
     * that when {@link #resolve(Path) resolved} against this path, yields a
     * path that locates the same file as the given path. For example, on UNIX,
     * if this path is {@code "/a/b"} and the given path is {@code "/a/b/c/d"}
     * then the resulting relative path would be {@code "c/d"}. Where this
     * path and the given path do not have a {@link #getRoot root} component,
     * then a relative path can be constructed. A relative path cannot be
     * constructed if only one of the paths have a root component. Where both
     * paths have a root component then it is implementation dependent if a
     * relative path can be constructed. If this path and the given path are
     * {@link #equals equal} then an <i>empty path</i> is returned.
     *
     * <p> For any two {@link #normalize normalized} paths <i>p</i> and
     * <i>q</i>, where <i>q</i> does not have a root component,
     * <blockquote>
     *   <i>p</i><tt>.relativize(</tt><i>p</i><tt>.resolve(</tt><i>q</i><tt>)).equals(</tt><i>q</i><tt>)</tt>
     * </blockquote>
     *
     * <p> When symbolic links are supported, then whether the resulting path,
     * when resolved against this path, yields a path that can be used to locate
     * the {@link Files#isSameFile same} file as {@code other} is implementation
     * dependent. For example, if this path is  {@code "/a/b"} and the given
     * path is {@code "/a/x"} then the resulting relative path may be {@code
     * "../x"}. If {@code "b"} is a symbolic link then is implementation
     * dependent if {@code "a/b/../x"} would locate the same file as {@code "/a/x"}.
     *
     * @param   other
     *          the path to relativize against this path
     *
     * @return  the resulting relative path, or an empty path if both paths are
     *          equal
     *
     * @throws  IllegalArgumentException
     *          if {@code other} is not a {@code Path} that can be relativized
     *          against this path
     */
	/**
     * Relativizes the given URI against this URI.
     *
     * <p> The relativization of the given URI against this URI is computed as
     * follows: </p>
     *
     * <ol>
     *
     *   <li><p> If either this URI or the given URI are opaque, or if the
     *   scheme and authority components of the two URIs are not identical, or
     *   if the path of this URI is not a prefix of the path of the given URI,
     *   then the given URI is returned. </p></li>
     *
     *   <li><p> Otherwise a new relative hierarchical URI is constructed with
     *   query and fragment components taken from the given URI and with a path
     *   component computed by removing this URI's path from the beginning of
     *   the given URI's path. </p></li>
     *
     * </ol>
     *
     * @param  uri  The URI to be relativized against this URI
     * @return The resulting URI
     *
     * @throws  NullPointerException
     *          If {@code uri} is {@code null}
     */
    public Path relativize(FileTMJ other){
    	return dataForkPath.relativize(other.dataForkPath);
	}
    public String relativizeString(FileTMJ other){
    	return relativize(other).toString();
	}
	
    /**
     * Returns the hierarchy distance of the child {@link FileTMJ} whithin this FileTMJ.
     * Returns {@code 0} if the files are equal and therefor at the same level.
     * Returns {@code -1} if this file's path does not contain the child.
     * If the given child is a true child of this file then a positive Integer is returned representing the hierarchical distance of the paths.
     * 
     * <p> Whether or not the root component of this file's path starts with the root
     * component of the given child's path is file system specific. If only one of them
     * has a root component then {@code -1} is returned.
     *
     * <p> If the files are associated with different {@code FileSystem}s then {@code -1} is returned.
     *
     * @param   child
     *          the given file
     *
     * @return  Integer representing the hierarchical distance of the paths, -1 otherwise.
     */
    public int childDepth(FileTMJ child) {
		throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
	}

    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Returns an array of strings naming the files and directories in the
     * directory denoted by this abstract pathname.
     *
     * <p> If this abstract pathname does not denote a directory, then this
     * method returns {@code null}.  Otherwise an array of strings is
     * returned, one for each file or directory in the directory.  Names
     * denoting the directory itself and the directory's parent directory are
     * not included in the result.  Each string is a file name rather than a
     * complete path.
     *
     * <p> There is no guarantee that the name strings in the resulting array
     * will appear in any specific order; they are not, in particular,
     * guaranteed to appear in alphabetical order.
     *
     * <p> Note that the {@link java.nio.file.Files} class defines the {@link
     * java.nio.file.Files#newDirectoryStream(Path) newDirectoryStream} method to
     * open a directory and iterate over the names of the files in the directory.
     * This may use less resources when working with very large directories, and
     * may be more responsive when working with remote directories.
     *
     * @return  An array of strings naming the files and directories in the
     *          directory denoted by this abstract pathname.  The array will be
     *          empty if the directory is empty.  Returns {@code null} if
     *          this abstract pathname does not denote a directory, or if an
     *          I/O error occurs.
     *
     * @throws  SecurityException
     *          If a security manager exists and its {@link
     *          SecurityManager#checkRead(String)} method denies read access to
     *          the directory
     */
    /**
     * Returns an array of abstract pathnames denoting the files in the
     * directory denoted by this abstract pathname.
     *
     * <p> If this abstract pathname does not denote a directory, then this
     * method returns {@code null}.  Otherwise an array of {@code File} objects
     * is returned, one for each file or directory in the directory.  Pathnames
     * denoting the directory itself and the directory's parent directory are
     * not included in the result.  Each resulting abstract pathname is
     * constructed from this abstract pathname using the {@link #File(File,
     * String) File(File,&nbsp;String)} constructor.  Therefore if this
     * pathname is absolute then each resulting pathname is absolute; if this
     * pathname is relative then each resulting pathname will be relative to
     * the same directory.
     *
     * <p> There is no guarantee that the name strings in the resulting array
     * will appear in any specific order; they are not, in particular,
     * guaranteed to appear in alphabetical order.
     *
     * <p> Note that the {@link java.nio.file.Files} class defines the {@link
     * java.nio.file.Files#newDirectoryStream(Path) newDirectoryStream} method
     * to open a directory and iterate over the names of the files in the
     * directory. This may use less resources when working with very large
     * directories.
     *
     * @return  An array of abstract pathnames denoting the files and
     *          directories in the directory denoted by this abstract pathname.
     *          The array will be empty if the directory is empty.  Returns
     *          {@code null} if this abstract pathname does not denote a
     *          directory, or if an I/O error occurs.
     *
     * @throws  SecurityException
     *          If a security manager exists and its {@link
     *          SecurityManager#checkRead(String)} method denies read access to
     *          the directory
     *
     * @since  1.2
     */
	public FileTMJ[] containingFiles() throws NotDirectoryException, NoSuchFileException, SecurityException, IOException {
		return containingFilesStream().toArray(FileTMJ[]::new);
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkRead(path);
//        }
//        if (isInvalid()) {
//            return null;
//        }
//        return fs.list(this);
//        String[] ss = list();
//        if (ss == null) return null;
//        int n = ss.length;
//        File[] fs = new File[n];
//        for (int i = 0; i < n; i++) {
//            fs[i] = new File(ss[i], this);
//        }
//        return fs;
    }

   
	private static class AcceptAllFilter implements DirectoryStream.Filter<Path> {
		private AcceptAllFilter() { }
		@Override public boolean accept(Path entry) { return true; }
		static final AcceptAllFilter FILTER = new AcceptAllFilter();
	}
	/**
     * Opens a directory, returning a {@link DirectoryStream} to iterate over
     * all entries in the directory. The elements returned by the directory
     * stream's {@link DirectoryStream#iterator iterator} are of type {@code
     * Path}, each one representing an entry in the directory. The {@code Path}
     * objects are obtained as if by {@link Path#resolve(Path) resolving} the
     * name of the directory entry against {@code dir}.
     *
     * <p> When not using the try-with-resources construct, then directory
     * stream's {@code close} method should be invoked after iteration is
     * completed so as to free any resources held for the open directory.
     *
     * <p> When an implementation supports operations on entries in the
     * directory that execute in a race-free manner then the returned directory
     * stream is a {@link SecureDirectoryStream}.
     *
     * @param   dir
     *          the path to the directory
     *
     * @return  a new and open {@code DirectoryStream} object
     *
     * @throws  NotDirectoryException
     *          if the file could not otherwise be opened because it is not
     *          a directory <i>(optional specific exception)</i>
     * @throws  IOException
     *          if an I/O error occurs
     * @throws  SecurityException
     *          In the case of the default provider, and a security manager is
     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
     *          method is invoked to check read access to the directory.
     */
    public static DirectoryStream<Path> newDirectoryStream(Path dir) throws IOException {
    	return dir.getFileSystem().provider().newDirectoryStream(dir, AcceptAllFilter.FILTER);
    }
    /**
     * Convert a Closeable to a Runnable by converting checked IOException
     * to UncheckedIOException
     */
    private static Runnable asUncheckedRunnable(Closeable c) {
        return () -> {
            try {
                c.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }
    /**
	 * From Files.list
     * Return a lazily populated {@code Stream}, the elements of
     * which are the entries in the directory.  The listing is not recursive.
     *
     * <p> The elements of the stream are {@link Path} objects that are
     * obtained as if by {@link Path#resolve(Path) resolving} the name of the
     * directory entry against {@code dir}. Some file systems maintain special
     * links to the directory itself and the directory's parent directory.
     * Entries representing these links are not included.
     *
     * <p> The stream is <i>weakly consistent</i>. It is thread safe but does
     * not freeze the directory while iterating, so it may (or may not)
     * reflect updates to the directory that occur after returning from this
     * method.
     *
     * <p> The returned stream encapsulates a {@link DirectoryStream}.
     * If timely disposal of file system resources is required, the
     * {@code try}-with-resources construct should be used to ensure that the
     * stream's {@link Stream#close close} method is invoked after the stream
     * operations are completed.
     *
     * <p> Operating on a closed stream behaves as if the end of stream
     * has been reached. Due to read-ahead, one or more elements may be
     * returned after the stream has been closed.
     *
     * <p> If an {@link IOException} is thrown when accessing the directory
     * after this method has returned, it is wrapped in an {@link
     * UncheckedIOException} which will be thrown from the method that caused
     * the access to take place.
     *
     * @param   dir  The path to the directory
     *
     * @return  The {@code Stream} describing the content of the
     *          directory
     *
     * @throws  NotDirectoryException
     *          if the file could not otherwise be opened because it is not
     *          a directory <i>(optional specific exception)</i>
     * @throws  IOException
     *          if an I/O error occurs when opening the directory
     * @throws  SecurityException
     *          In the case of the default provider, and a security manager is
     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
     *          method is invoked to check read access to the directory.
     *
     * @see     #newDirectoryStream(Path)
     * @since   1.8
     */
    public static Stream<Path> list(Path dir) throws IOException {
        DirectoryStream<Path> ds = Files.newDirectoryStream(dir);
        try {
            final Iterator<Path> delegate = ds.iterator();

            // Re-wrap DirectoryIteratorException to UncheckedIOException
            Iterator<Path> it = new Iterator<Path>() {
                @Override
                public boolean hasNext() {
                    try {
                        return delegate.hasNext();
                    } catch (DirectoryIteratorException e) {
                        throw new UncheckedIOException(e.getCause());
                    }
                }
                @Override
                public Path next() {
                    try {
                        return delegate.next();
                    } catch (DirectoryIteratorException e) {
                        throw new UncheckedIOException(e.getCause());
                    }
                }
            };

            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(it, Spliterator.DISTINCT), false)
                                .onClose(asUncheckedRunnable(ds));
        } catch (Error|RuntimeException e) {
            try {
                ds.close();
            } catch (IOException ex) {
                try {
                    e.addSuppressed(ex);
                } catch (Throwable ignore) {}
            }
            throw e;
        }
    }
    /**
			 * Stream<Path> java.nio.file.Files.list(Path dir) throws IOException
		Return a lazily populated Stream, the elements of which are the entries in the directory. The listing is not recursive.
		The elements of the stream are Path objects that are obtained as if by resolving the name of the directory entry against dir. Some file systems maintain special links to the directory itself and the directory's parent directory. Entries representing these links are not included.
		The stream is weakly consistent. It is thread safe but does not freeze the directory while iterating, so it may (or may not) reflect updates to the directory that occur after returning from this method.
		The returned stream encapsulates a DirectoryStream. If timely disposal of file system resources is required, the try-with-resources construct should be used to ensure that the stream's close method is invoked after the stream operations are completed.
		Operating on a closed stream behaves as if the end of stream has been reached. Due to read-ahead, one or more elements may be returned after the stream has been closed.
		If an IOException is thrown when accessing the directory after this method has returned, it is wrapped in an UncheckedIOException which will be thrown from the method that caused the access to take place.
		Parameters:
		dir The path to the directory
		Returns:
		The Stream describing the content of the directory
		Throws:
		NotDirectoryException - if the file could not otherwise be opened because it is not a directory (optional specific exception)
		IOException - if an I/O error occurs when opening the directory
		SecurityException - In the case of the default provider, and a security manager is installed, the checkRead method is invoked to check read access to the directory.
		Since:
		1.8
		See Also:
		newDirectoryStream(Path)
			 * @throws IOException,SecurityException,NotDirectoryException
			 * 		NoSuchFileException - if this FileTMJ does not exist and so cannot contain anything
			 */
			public Stream<FileTMJ> containingFilesStream() throws IOException,SecurityException,NotDirectoryException,NoSuchFileException{
				return Files.list(dataForkPath).map(path->new FileTMJ(path));
			}
	/**
     * Returns an array of abstract pathnames denoting the files and
     * directories in the directory denoted by this abstract pathname that
     * satisfy the specified filter.  The behavior of this method is the same
     * as that of the {@link #listFiles()} method, except that the pathnames in
     * the returned array must satisfy the filter.  If the given {@code filter}
     * is {@code null} then all pathnames are accepted.  Otherwise, a pathname
     * satisfies the filter if and only if the value {@code true} results when
     * the {@link FilenameFilter#accept
     * FilenameFilter.accept(File,&nbsp;String)} method of the filter is
     * invoked on this abstract pathname and the name of a file or directory in
     * the directory that it denotes.
     *
     * @param  filter
     *         A filename filter
     *
     * @return  An array of abstract pathnames denoting the files and
     *          directories in the directory denoted by this abstract pathname.
     *          The array will be empty if the directory is empty.  Returns
     *          {@code null} if this abstract pathname does not denote a
     *          directory, or if an I/O error occurs.
     *
     * @throws  SecurityException
     *          If a security manager exists and its {@link
     *          SecurityManager#checkRead(String)} method denies read access to
     *          the directory
     *
     * @since  1.2
     * @see java.nio.file.Files#newDirectoryStream(Path,String)
     */
	public FileTMJ[] containingFiles(FilenameFilter filter) throws NotDirectoryException, NoSuchFileException, SecurityException, IOException {
		return Files.list(dataForkPath)
			.filter(path->filter.accept(path.toFile(), path.getFileName().toString()))
			.toArray(FileTMJ[]::new);
	}
	public FileTMJ[] containingFiles(FileFilter filter) throws NotDirectoryException, NoSuchFileException, SecurityException, IOException {
		return containingFilesStream()
			.filter( path->filter.accept( path.toFile() ))
			.toArray(FileTMJ[]::new);
	}
	public Stream<FileTMJ> containingFilesStream(FilenameFilter filter) throws IOException,SecurityException,NotDirectoryException,NoSuchFileException{
		return Files.list(dataForkPath)
			.filter(path->filter.accept(path.toFile(), path.getFileName().toString()))
			.map(path->new FileTMJ(path));
	}  
	public Stream<FileTMJ> containingFilesStream(FileFilter filter) throws IOException,SecurityException,NotDirectoryException,NoSuchFileException{
		return Files.list(dataForkPath)
			.filter( path->filter.accept( path.toFile() ))
			.map(path->new FileTMJ(path));
	}
	


   
  
    /**
     * Return a {@code Stream} that is lazily populated with {@code
     * Path} by walking the file tree rooted at a given starting file.  The
     * file tree is traversed <em>depth-first</em>, the elements in the stream
     * are {@link Path} objects that are obtained as if by {@link
     * Path#resolve(Path) resolving} the relative path against {@code start}.
     *
     * <p> The {@code stream} walks the file tree as elements are consumed.
     * The {@code Stream} returned is guaranteed to have at least one
     * element, the starting file itself. For each file visited, the stream
     * attempts to read its {@link BasicFileAttributes}. If the file is a
     * directory and can be opened successfully, entries in the directory, and
     * their <em>descendants</em> will follow the directory in the stream as
     * they are encountered. When all entries have been visited, then the
     * directory is closed. The file tree walk then continues at the next
     * <em>sibling</em> of the directory.
     *
     * <p> The stream is <i>weakly consistent</i>. It does not freeze the
     * file tree while iterating, so it may (or may not) reflect updates to
     * the file tree that occur after returned from this method.
     *
     * <p> By default, symbolic links are not automatically followed by this
     * method. If the {@code options} parameter contains the {@link
     * FileVisitOption#FOLLOW_LINKS FOLLOW_LINKS} option then symbolic links are
     * followed. When following links, and the attributes of the target cannot
     * be read, then this method attempts to get the {@code BasicFileAttributes}
     * of the link.
     *
     * <p> If the {@code options} parameter contains the {@link
     * FileVisitOption#FOLLOW_LINKS FOLLOW_LINKS} option then the stream keeps
     * track of directories visited so that cycles can be detected. A cycle
     * arises when there is an entry in a directory that is an ancestor of the
     * directory. Cycle detection is done by recording the {@link
     * java.nio.file.attribute.BasicFileAttributes#fileKey file-key} of directories,
     * or if file keys are not available, by invoking the {@link #isSameFile
     * isSameFile} method to test if a directory is the same file as an
     * ancestor. When a cycle is detected it is treated as an I/O error with
     * an instance of {@link FileSystemLoopException}.
     *
     * <p> The {@code maxDepth} parameter is the maximum number of levels of
     * directories to visit. A value of {@code 0} means that only the starting
     * file is visited, unless denied by the security manager. A value of
     * {@link Integer#MAX_VALUE MAX_VALUE} may be used to indicate that all
     * levels should be visited.
     *
     * <p> When a security manager is installed and it denies access to a file
     * (or directory), then it is ignored and not included in the stream.
     *
     * <p> The returned stream encapsulates one or more {@link DirectoryStream}s.
     * If timely disposal of file system resources is required, the
     * {@code try}-with-resources construct should be used to ensure that the
     * stream's {@link Stream#close close} method is invoked after the stream
     * operations are completed.  Operating on a closed stream will result in an
     * {@link java.lang.IllegalStateException}.
     *
     * <p> If an {@link IOException} is thrown when accessing the directory
     * after this method has returned, it is wrapped in an {@link
     * UncheckedIOException} which will be thrown from the method that caused
     * the access to take place.
     *
     * @param   start
     *          the starting file
     * @param   maxDepth
     *          the maximum number of directory levels to visit
     * @param   options
     *          options to configure the traversal
     *
     * @return  the {@link Stream} of {@link Path}
     *
     * @throws  IllegalArgumentException
     *          if the {@code maxDepth} parameter is negative
     * @throws  SecurityException
     *          If the security manager denies access to the starting file.
     *          In the case of the default provider, the {@link
     *          SecurityManager#checkRead(String) checkRead} method is invoked
     *          to check read access to the directory.
     * @throws  IOException
     *          if an I/O error is thrown when accessing the starting file.
     * @since   1.8
     */
    public static Stream<FileTMJ> walk(int maxDepth, FileVisitOption... options) throws IOException{
		throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
//        FileTreeIterator iterator = new FileTreeIterator(start, maxDepth, options);
//        try {
//            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.DISTINCT), false)
//                                .onClose(iterator::close)
//                                .map(entry -> entry.file());
//        } catch (Error|RuntimeException e) {
//            iterator.close();
//            throw e;
//        }
    }

    /**
     * Return a {@code Stream} that is lazily populated with {@code
     * Path} by walking the file tree rooted at a given starting file.  The
     * file tree is traversed <em>depth-first</em>, the elements in the stream
     * are {@link Path} objects that are obtained as if by {@link
     * Path#resolve(Path) resolving} the relative path against {@code start}.
     *
     * <p> This method works as if invoking it were equivalent to evaluating the
     * expression:
     * <blockquote><pre>
     * walk(start, Integer.MAX_VALUE, options)
     * </pre></blockquote>
     * In other words, it visits all levels of the file tree.
     *
     * <p> The returned stream encapsulates one or more {@link DirectoryStream}s.
     * If timely disposal of file system resources is required, the
     * {@code try}-with-resources construct should be used to ensure that the
     * stream's {@link Stream#close close} method is invoked after the stream
     * operations are completed.  Operating on a closed stream will result in an
     * {@link java.lang.IllegalStateException}.
     *
     * @param   start
     *          the starting file
     * @param   options
     *          options to configure the traversal
     *
     * @return  the {@link Stream} of {@link Path}
     *
     * @throws  SecurityException
     *          If the security manager denies access to the starting file.
     *          In the case of the default provider, the {@link
     *          SecurityManager#checkRead(String) checkRead} method is invoked
     *          to check read access to the directory.
     * @throws  IOException
     *          if an I/O error is thrown when accessing the starting file.
     *
     * @see     #walk(Path, int, FileVisitOption...)
     * @since   1.8
     */
    public static Stream<FileTMJ> walk(FileVisitOption... options) throws IOException {
		throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
//        return walk(start, Integer.MAX_VALUE, options);
    }

    /**
     * Return a {@code Stream} that is lazily populated with {@code
     * Path} by searching for files in a file tree rooted at a given starting
     * file.
     *
     * <p> This method walks the file tree in exactly the manner specified by
     * the {@link #walk walk} method. For each file encountered, the given
     * {@link BiPredicate} is invoked with its {@link Path} and {@link
     * BasicFileAttributes}. The {@code Path} object is obtained as if by
     * {@link Path#resolve(Path) resolving} the relative path against {@code
     * start} and is only included in the returned {@link Stream} if
     * the {@code BiPredicate} returns true. Compare to calling {@link
     * java.util.stream.Stream#filter filter} on the {@code Stream}
     * returned by {@code walk} method, this method may be more efficient by
     * avoiding redundant retrieval of the {@code BasicFileAttributes}.
     *
     * <p> The returned stream encapsulates one or more {@link DirectoryStream}s.
     * If timely disposal of file system resources is required, the
     * {@code try}-with-resources construct should be used to ensure that the
     * stream's {@link Stream#close close} method is invoked after the stream
     * operations are completed.  Operating on a closed stream will result in an
     * {@link java.lang.IllegalStateException}.
     *
     * <p> If an {@link IOException} is thrown when accessing the directory
     * after returned from this method, it is wrapped in an {@link
     * UncheckedIOException} which will be thrown from the method that caused
     * the access to take place.
     *
     * @param   start
     *          the starting file
     * @param   maxDepth
     *          the maximum number of directory levels to search
     * @param   matcher
     *          the function used to decide whether a file should be included
     *          in the returned stream
     * @param   options
     *          options to configure the traversal
     *
     * @return  the {@link Stream} of {@link Path}
     *
     * @throws  IllegalArgumentException
     *          if the {@code maxDepth} parameter is negative
     * @throws  SecurityException
     *          If the security manager denies access to the starting file.
     *          In the case of the default provider, the {@link
     *          SecurityManager#checkRead(String) checkRead} method is invoked
     *          to check read access to the directory.
     * @throws  IOException
     *          if an I/O error is thrown when accessing the starting file.
     *
     * @see     #walk(Path, int, FileVisitOption...)
     * @since   1.8
     */
//    public static Stream<FileTMJ> find(int maxDepth, BiPredicate<FileTMJ, BasicFileAttributes> matcher, FileVisitOption... options) throws IOException {
//		throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
////        FileTreeIterator iterator = new FileTreeIterator(start, maxDepth, options);
////        try {
////            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.DISTINCT), false)
////                                .onClose(iterator::close)
////                                .filter(entry -> matcher.test(entry.file(), entry.attributes()))
////                                .map(entry -> entry.file());
////        } catch (Error|RuntimeException e) {
////            iterator.close();
////            throw e;
////        }
//    }
    
    
    
    
    
    /**
     * Reads the target of a symbolic link <i>(optional operation)</i>.
     *
     * <p> If the file system supports <a href="package-summary.html#links">symbolic
     * links</a> then this method is used to read the target of the link, failing
     * if the file is not a symbolic link. The target of the link need not exist.
     * The returned {@code Path} object will be associated with the same file
     * system as {@code link}.
     *
     * @param   link
     *          the path to the symbolic link
     *
     * @return  a {@code Path} object representing the target of the link
     *
     * @throws  UnsupportedOperationException
     *          if the implementation does not support symbolic links
     * @throws  NotLinkException
     *          if the target could otherwise not be read because the file
     *          is not a symbolic link <i>(optional specific exception)</i>
     * @throws  IOException
     *          if an I/O error occurs
     * @throws  SecurityException
     *          In the case of the default provider, and a security manager
     *          is installed, it checks that {@code FilePermission} has been
     *          granted with the "{@code readlink}" action to read the link.
     */
    public FileTMJ readSymbolicLinkTarget() throws IOException {
//    	return new FileTMJ(Files.readSymbolicLink(dataForkPath));
        return new FileTMJ(dataForkPath.getFileSystem().provider().readSymbolicLink(dataForkPath));
    }
    
    /**
     * Tests whether a file is a symbolic link.
     *
     * <p> Where it is required to distinguish an I/O exception from the case
     * that the file is not a symbolic link then the file attributes can be
     * read with the {@link #readAttributes(Path,Class,LinkOption[])
     * readAttributes} method and the file type tested with the {@link
     * BasicFileAttributes#isSymbolicLink} method.
     *
     * @param   path  The path to the file
     *
     * @return  {@code true} if the file is a symbolic link; {@code false} if
     *          the file does not exist, is not a symbolic link, or it cannot
     *          be determined if the file is a symbolic link or not.
     *
     * @throws  SecurityException
     *          In the case of the default provider, and a security manager is
     *          installed, its {@link SecurityManager#checkRead(String) checkRead}
     *          method denies read access to the file.
     */
    public boolean isSymbolicLink() {
//    	return Files.isSymbolicLink(dataForkPath);
        try {
            return readAttributes(dataForkPath,
                                  BasicFileAttributes.class,
                                  LinkOption.NOFOLLOW_LINKS).isSymbolicLink();
        } catch (IOException ioe) {
            return false;
        }
    }
    
   
    
	/**
	 * From Files.exists
	 * Returns the size of a file (in bytes). 
	 * The size may differ from the actual size on the file system due to compression, support for sparse files, or other reasons. 
	 * The size of files that are not regular files is implementation specific and therefore unspecified.
     * @param   options
     *          options indicating how symbolic links are handled
     * 
	 * @return size in bytes, unspecified if directory
	 * @throws SecurityException - If a security manager exists and its java.lang.SecurityManager.checkRead(java.lang.String) method denies read access to the file
	 * @throws IOException - if an I/O error occurs
	 * 
	 * long java.nio.file.Files.size(Path path) throws IOException
	 */
	public long sizeBytes() throws SecurityException, IOException{
		return sizeBytes(LinkOption.NOFOLLOW_LINKS);
	}
	public long sizeBytes(LinkOption...linkOptions) throws SecurityException, IOException{
		long dataSize=0;
		long resourceSize=0;
//		if(!exists(linkOptions)){
//		}else {
			// Files.exists tries to read file attributes and interpretes an error as not-exists!
			// So we don't need that separated.
			try {
				dataSize=readBasicFileAttributes(dataForkPath, linkOptions).size(); // file exists
			} catch (IOException e2) { // does not exist or unable to determine if file exists
				dataSize=-1;
			}
			try {
				resourceSize=readBasicFileAttributes(resourceForkPath, linkOptions).size(); // file exists
			} catch (IOException e2) { // does not exist or unable to determine if file exists
				resourceSize=-1;
			}
			if(0>dataSize) {
				if(0>resourceSize) {
					throw new FileNotFoundException();
				}else {
					return resourceSize;
				}
			}else if(0>resourceSize){
				return dataSize;
			}else {
				return dataSize+resourceSize;
			}
//		}
//		if(!Files.exists(dataForkPath, linkOptions) && !Files.exists(resourceForkPath,linkOptions)) {
//			throw new FileNotFoundException();
//		}else {
//			if(Files.exists(dataForkPath, linkOptions)) {
//				dataSize=Files.size(dataForkPath);
//			}
//			if(Files.exists(resourceForkPath,linkOptions)) {
//				resourceSize=Files.size(resourceForkPath);
//			}
//		}
//		return dataSize+resourceSize;
	}
    
    
	/** returns true, if the object's name was sucessfully changed or need not to be changed, false otherwise. 
	 * nameNew must not be null
	 * @throws IOException 
	 * */
	public boolean setName(String nameNew) throws IOException {
		return setName(Paths.get(nameNew));
	}
	public boolean setName(Path nameNew) throws IOException {
//			if(dataForkPath.getFileName().toString().equals(nameNew)){
//				return true;
//			}
			Path parent=dataForkPath.getParent();
			// try{
			Path dest=parent.resolve(nameNew);
			// }
			
//			if( Files.exists(dest, LinkOption.NOFOLLOW_LINKS) ){
			try {
				readBasicFileAttributes(dest, LinkOption.NOFOLLOW_LINKS); // file exists
				throw new FileAlreadyExistsException(dest.toString());//TODO
				//tell me=display dialog "This name is already taken, please rename." default answer nameNew buttons {"Cancel", "Skip", "OK"} default button 3
				//copy the result as list={nameNew, button_pressed}
				//if( the button_pressed is "Skip" ){ return 0
				//my setFilesystemObjectName(FilesystemObject, nameNew)
			} catch (IOException e) { // does not exist or unable to determine if file exists
			}

			try {
				dataForkPath=Files.move(dataForkPath, dest, StandardCopyOption.ATOMIC_MOVE);//Dies wirkt sich auch korrekt auf den Ressource Fork aus, jedenfalls
				// unter macOS 10.13.6 und HFS+j
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
//			 }catch(Error e){ the error_message number the error_number
//				if( the error_number is -59 ){
//					the error_message="This name contains improper characters, such as a colon (:)."
//				}else{ // the suggested name is too long
//					the error_message=error_message // "The name is more than 31 characters long."
//				}
//				tell me=display dialog the error_message default answer nameNew buttons {"Cancel", "Skip", "OK"} default button 3
//				copy the result as list={nameNew, button_pressed}
//				if( the button_pressed is "Skip" ){ return 0
//				my setFilesystemObjectName(filesystemObject, nameNew)
//			}
		return false;
		}
	
	
    /**
     * Renames the file denoted by this abstract pathname.
     *
     * <p> Many aspects of the behavior of this method are inherently
     * platform-dependent: The rename operation might not be able to move a
     * file from one filesystem to another, it might not be atomic, and it
     * might not succeed if a file with the destination abstract pathname
     * already exists.  The return value should always be checked to make sure
     * that the rename operation was successful.
     *
     * <p> Note that the {@link java.nio.file.Files} class defines the {@link
     * java.nio.file.Files#move move} method to move or rename a file in a
     * platform independent manner.
     *
     * @param  dest  The new abstract pathname for the named file
     *
     * @return  <code>true</code> if and only if the renaming succeeded;
     *          <code>false</code> otherwise
     *
     * @throws  SecurityException
     *          If a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
     *          method denies write access to either the old or new pathnames
     *
     * @throws  NullPointerException
     *          If parameter <code>dest</code> is <code>null</code>
     */
    /**
     * Move or rename a file to a target file.
     *
     * <p> By default, this method attempts to move the file to the target
     * file, failing if the target file exists except if the source and
     * target are the {@link #isSameFile same} file, in which case this method
     * has no effect. If the file is a symbolic link then the symbolic link
     * itself, not the target of the link, is moved. This method may be
     * invoked to move an empty directory. In some implementations a directory
     * has entries for special files or links that are created when the
     * directory is created. In such implementations a directory is considered
     * empty when only the special entries exist. When invoked to move a
     * directory that is not empty then the directory is moved if it does not
     * require moving the entries in the directory.  For example, renaming a
     * directory on the same {@link FileStore} will usually not require moving
     * the entries in the directory. When moving a directory requires that its
     * entries be moved then this method fails (by throwing an {@code
     * IOException}). To move a <i>file tree</i> may involve copying rather
     * than moving directories and this can be done using the {@link
     * #copy copy} method in conjunction with the {@link
     * #walkFileTree Files.walkFileTree} utility method.
     *
     * <p> The {@code options} parameter may include any of the following:
     *
     * <table border=1 cellpadding=5 summary="">
     * <tr> <th>Option</th> <th>Description</th> </tr>
     * <tr>
     *   <td> {@link StandardCopyOption#REPLACE_EXISTING REPLACE_EXISTING} </td>
     *   <td> If the target file exists, then the target file is replaced if it
     *     is not a non-empty directory. If the target file exists and is a
     *     symbolic link, then the symbolic link itself, not the target of
     *     the link, is replaced. </td>
     * </tr>
     * <tr>
     *   <td> {@link StandardCopyOption#ATOMIC_MOVE ATOMIC_MOVE} </td>
     *   <td> The move is performed as an atomic file system operation and all
     *     other options are ignored. If the target file exists then it is
     *     implementation specific if the existing file is replaced or this method
     *     fails by throwing an {@link IOException}. If the move cannot be
     *     performed as an atomic file system operation then {@link
     *     AtomicMoveNotSupportedException} is thrown. This can arise, for
     *     example, when the target location is on a different {@code FileStore}
     *     and would require that the file be copied, or target location is
     *     associated with a different provider to this object. </td>
     * </table>
     *
     * <p> An implementation of this interface may support additional
     * implementation specific options.
     *
     * <p> Moving a file will copy the {@link
     * BasicFileAttributes#lastModifiedTime last-modified-time} to the target
     * file if supported by both source and target file stores. Copying of file
     * timestamps may result in precision loss. An implementation may also
     * attempt to copy other file attributes but is not required to fail if the
     * file attributes cannot be copied. When the move is performed as
     * a non-atomic operation, and an {@code IOException} is thrown, then the
     * state of the files is not defined. The original file and the target file
     * may both exist, the target file may be incomplete or some of its file
     * attributes may not been copied from the original file.
     *
     * <p> <b>Usage Examples:</b>
     * Suppose we want to rename a file to "newname", keeping the file in the
     * same directory:
     * <pre>
     *     Path source = ...
     *     Files.move(source, source.resolveSibling("newname"));
     * </pre>
     * Alternatively, suppose we want to move a file to new directory, keeping
     * the same file name, and replacing any existing file of that name in the
     * directory:
     * <pre>
     *     Path source = ...
     *     Path newdir = ...
     *     Files.move(source, newdir.resolve(source.getFileName()), REPLACE_EXISTING);
     * </pre>
     *
     * @param   target
     *          the path to the target file (may be associated with a different
     *          provider to the source path)
     * @param   options
     *          options specifying how the move should be done
     *
     * @return  the path to the target file
     *
     * @throws  UnsupportedOperationException
     *          if the array contains a copy option that is not supported
     * @throws  FileAlreadyExistsException
     *          if the target file exists but cannot be replaced because the
     *          {@code REPLACE_EXISTING} option is not specified <i>(optional
     *          specific exception)</i>
     * @throws  DirectoryNotEmptyException
     *          the {@code REPLACE_EXISTING} option is specified but the file
     *          cannot be replaced because it is a non-empty directory
     *          <i>(optional specific exception)</i>
     * @throws  AtomicMoveNotSupportedException
     *          if the options array contains the {@code ATOMIC_MOVE} option but
     *          the file cannot be moved as an atomic file system operation.
     * @throws  IOException
     *          if an I/O error occurs
     * @throws  SecurityException
     *          In the case of the default provider, and a security manager is
     *          installed, the {@link SecurityManager#checkWrite(String) checkWrite}
     *          method is invoked to check write access to both the source and
     *          target file.
     */
//        public boolean moveTo(File dest, CopyOption... options)throws IOException {
//        FileSystemProvider provider = provider(source);
//        if (provider(target) == provider) {
//            // same provider
//            provider.move(source, target, options);
//        } else {
//            // different providers
//            CopyMoveHelper.moveToForeignTarget(source, target, options);
//        }
//        return target;
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkWrite(path);
//            security.checkWrite(dest.path);
//        }
//        if (dest == null) {
//            throw new NullPointerException();
//        }
//        if (this.isInvalid() || dest.isInvalid()) {
//            return false;
//        }
//        return fs.rename(this, dest);
		public FileTMJ moveTo(FileTMJ destination) throws IOException {
			Path path=Files.move(dataForkPath, destination.dataForkPath, StandardCopyOption.ATOMIC_MOVE);
			return new FileTMJ(path);
////			public static Path move(Path source, Path target, CopyOption... options) throws IOException  {
//			        FileSystemProvider provider = dataForkPath.getFileSystem().provider();
//			        if (destination.getFileSystem().provider() == provider) {
//			            // same provider
//			            provider.move(dataForkPath, destination.dataForkPath, StandardCopyOption.ATOMIC_MOVE);
//			        } else {
//			            // different providers
////			        	java.nio.file.CopyMoveHelper.moveToForeignTarget(dataForkPath, destination, StandardCopyOption.ATOMIC_MOVE);
//			        	java.nio.file.CopyMoveHelper.moveToForeignTarget(dataForkPath, destination, StandardCopyOption.ATOMIC_MOVE);
//			 
//     copyToForeignTarget(dataForkPath, destination.dataForkPath, convertMoveToCopyOptions(StandardCopyOption.ATOMIC_MOVE));
//     Files.delete(dataForkPath);
// }
//			        }
//			        return new FileTMJ(destination);
////			    }
		}

    
    
    /**
     * Remove this file/directory from the file system.
     * 
     * In this is a directory, its contents will be removed, first. Consequently this 
     * method may not be atomic with respect to other file system operations.
	 * If this is a symbolic link then the link itself is deleted, not its target.
     *
     * @return  {@code true} – if the file/directory was successfully deleted,
     *          {@code false} – if it could not be deleted because it did not exist
     * @throws  IOException – if an I/O error occurs
     * @throws  SecurityException – if a security manager denies the operation
     */
	    public boolean removeIfExists() throws SecurityException,IOException{
	    		return removeIfExists(false);
	    }
    public boolean removeIfExists(boolean ignoreErrors) throws SecurityException,IOException{
    	if(isDirectory()) {
    		removeDirContent(ignoreErrors);
    	}
    	return dataForkPath.getFileSystem().provider().deleteIfExists(dataForkPath);
//    	return Files.deleteIfExists(dataForkPath);
//		 * java.nio.file.Files.delete(path);
//       * = path.getFileSystem().provider().delete(path);
//		 * boolean java.nio.file.Files.deleteIfExists(path);
//		 * = path.getFileSystem().provider().deleteIfExists(path)
//	     * May require to examine if file is a directory. A directory must be empty.
//	     * This method can be used with the {@link #walkFileTree walkFileTree}
//	     * method to delete a directory and all entries in the directory, or an
//	     * entire <i>file-tree</i> where required.
//    	 * Consequently this method may not be atomic with respect to other file system operations.  
//	     * If the file is a symbolic link then the link itself is deleted, not its target.
//	     * @throws  DirectoryNotEmptyException
//	     * @throws  IOException
//	     * @throws  SecurityException
//	     *          If a security manager denies the operation.
//	     *          {@link SecurityManager#checkDelete(String)} method
//	     *          is invoked to check delete access to the file
	    
//		 * boolean java.io.File.delete();
//		 * = SecurityManager security = System.getSecurityManager();
//        if (security != null) { security.checkDelete(path); } // throws SecurityException
//        if (isInvalid()) { return false; } // if Pfad == char(0)
//        return fs.delete(this);
//	     * If a directory, then it must be empty.
//	     * @return  <code>true</code> if successfully deleted; <code>false</code> otherwise
//	     * @throws  SecurityException
    }
    private void removeDirContent(boolean ignoreErrors) throws SecurityException, IOException {
    	FileTMJ[] files=containingFiles();
    	for(FileTMJ file:files) {
    		if(!file.removeIfExists() && !ignoreErrors) {
    			throw new IOException("File tree has changed during operation.");
    		}
    	}
    }
    
    
    /**
     * Atomically creates a new, empty file addressed by this object if a file with 
     * this name does not yet exist. The check for the
     * existence of the file and the creation of the file if it does not exist
     * are a single operation that is atomic with respect to all other
     * filesystem activities that might affect the file.
     * <p>Missing directories in the file's path will be create, first, if nesseccary.
     * <p> The {@code attrs} parameter is optional {@link FileAttribute
     * file-attributes} to set atomically when creating the file. Each attribute
     * is identified by its {@link FileAttribute#name name}. If more than one
     * attribute of the same name is included in the array then all but the last
     * occurrence is ignored.
     * <P>
     * Note: this method should <i>not</i> be used for file-locking, as
     * the resulting protocol cannot be made to work reliably. The
     * {@link java.nio.channels.FileLock FileLock}
     * facility should be used instead.
     *
     * @param   attrs – optional list of file attributes
     * @return  {@code true} if the file was successfully created, {@code false} if it already existed
     * @throws  UnsupportedOperationException – if the array contains an attribute that 
     *          cannot be set atomically when creating the file
     * @throws  IOException – if an I/O error occurs
     * @throws  SecurityException – if a security manager denies the write access
     */
    public boolean createFileIfNotExists(FileAttribute<?>... attrs) throws IOException {
    	try {
    		FileTMJ file=getParent();
    		if(null!=file) {
    			file.createDirectory(attrs);
    		}
    		// from java.nio.file.Files:
    		getFileSystem().provider().newByteChannel(
    			dataForkPath
    			, EnumSet.<StandardOpenOption>of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)
    			, attrs
    		).close();
    		return true;
    	}catch(FileAlreadyExistsException e) {
    		return false;
    	}
    }

    /**
     * Creates a directory by creating all nonexistent parent directories first.
     * Unlike the {@link #createDirectory createDirectory} method, an exception
     * is not thrown if the directory could not be created because it already
     * exists.
     *
     * <p> The {@code attrs} parameter is optional {@link FileAttribute
     * file-attributes} to set atomically when creating the nonexistent
     * directories. Each file attribute is identified by its {@link
     * FileAttribute#name name}. If more than one attribute of the same name is
     * included in the array then all but the last occurrence is ignored.
     *
     * <p> If this method fails, then it may do so after creating some, but not
     * all, of the parent directories.
     *
     * @param   attrs
     *          an optional list of file attributes to set atomically when
     *          creating the directory
     * @return  <code>true</code> if and only if the directory was created,
     *          along with all necessary parent directories; <code>false</code>
     *          otherwise
     * @throws  UnsupportedOperationException
     *          if the array contains an attribute that cannot be set atomically
     *          when creating the directory
     * @throws  FileAlreadyExistsException
     *          if {@code dir} exists but is not a directory <i>(optional specific
     *          exception)</i>
     * @throws  IOException
     *          if an I/O error occurs
     * @throws  SecurityException
     *          in the case of the default provider, and a security manager is
     *          installed, the {@link SecurityManager#checkWrite(String) checkWrite}
     *          method is invoked prior to attempting to create a directory and
     *          its {@link SecurityManager#checkRead(String) checkRead} is
     *          invoked for each parent directory that is checked. If {@code
     *          dir} is not an absolute path then its {@link Path#toAbsolutePath
     *          toAbsolutePath} may need to be invoked to get its absolute path.
     *          This may invoke the security manager's {@link
     *          SecurityManager#checkPropertyAccess(String) checkPropertyAccess}
     *          method to check access to the system property {@code user.dir}
     */
    public boolean createDirectory(FileAttribute<?>... attributes) throws UnsupportedOperationException,FileAlreadyExistsException,IOException,SecurityException {
		Path path=Files.createDirectories(dataForkPath,attributes);
		if(null!=path) {
			init(path);
			return true;
		}
		return false;
        // attempt to create the directory
//        try {
//            createAndCheckIsDirectory(dir, attrs);
//            return dir;
//        } catch (FileAlreadyExistsException x) {
//            // file exists and is not a directory
//            throw x;
//        } catch (IOException x) {
//            // parent may not exist or other reason
//        }
//        SecurityException se = null;
//        try {
//            dir = dir.toAbsolutePath();
//        } catch (SecurityException x) {
//            // don't have permission to get absolute path
//            se = x;
//        }
//        // find a decendent that exists
//        Path parent = dir.getParent();
//        while (parent != null) {
//            try {
//                provider(parent).checkAccess(parent);
//                break;
//            } catch (NoSuchFileException x) {
//                // does not exist
//            }
//            parent = parent.getParent();
//        }
//        if (parent == null) {
//            // unable to find existing parent
//            if (se == null) {
//                throw new FileSystemException(dir.toString(), null,
//                    "Unable to determine if root directory exists");
//            } else {
//                throw se;
//            }
//        }
//
//        // create directories
//        Path child = parent;
//        for (Path name: parent.relativize(dir)) {
//            child = child.resolve(name);
//            createAndCheckIsDirectory(child, attrs);
//        }
//        return dir;
    }
//    /**
//     * Used by createDirectories to attempt to create a directory. A no-op
//     * if the directory already exists.
//     */
//    private static void createAndCheckIsDirectory(Path dir, FileAttribute<?>... attrs) throws IOException{
//        try {
//            dir.getFileSystem().provider().createDirectory(dir, attrs);
//        } catch (FileAlreadyExistsException x) {
//	boolean b=false;
//    try{
//    	b=!dir.getFileSystem().provider().readAttributes(dir, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS).isDirectory();
//    }catch(IOEXception e) {
//    	b=true
//    }   
//            if (b)
//                throw x;
//        }
//    }
    
    
    public static FileTMJ systemTempDirectory() {
    	return new FileTMJ(Paths.get(doPrivileged(new GetPropertyAction("java.io.tmpdir"))));
    }

    /**
     * Creates a new empty file in the system's temporary directory.
     * Uses prefix and suffix strings to generate its name.
     *
     * <p> The details as to how the name of the file is constructed is
     * implementation dependent and therefore not specified. Where possible
     * the {@code prefix} and {@code suffix} are used to construct candidate
     * names in the same manner as the {@link
     * java.io.File#createTempFile(String,String,File)} method.
     *
     * <p> Where used as a <em>work files</em>,
     * the resulting file may be opened using the {@link
     * StandardOpenOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} option so that the
     * file is deleted when the appropriate {@code close} method is invoked.
     * Alternatively, a {@link Runtime#addShutdownHook shutdown-hook}, or the
     * {@link java.io.File#deleteOnExit} mechanism may be used to delete the
     * file automatically.
     *
     * <p> The {@code attributes} parameter is optional {@link FileAttribute
     * file-attributes} to set atomically when creating the file. Each attribute
     * is identified by its {@link FileAttribute#name name}. If more than one
     * attribute of the same name is included in the array then all but the last
     * occurrence is ignored. When no file attributes are specified, then the
     * resulting file may have more restrictive access permissions to files
     * created by the {@link java.io.File#createTempFile(String,String,File)}
     * method.
     *
     * @param   prefix – prefix used in generating the file's name;
     *          may be {@code null}
     * @param   suffix – suffix used in generating the file's name;
     *          may be {@code null}, in which case "{@code .tmp}" is used
     * @param   attributes – optional list of file attributes to set atomically when
     *          creating the file
     * @return  newly created file that did not exist before
     * @throws  IllegalArgumentException – if the prefix or suffix parameters cannot be used to generate
     *          a candidate file name
     * @throws  UnsupportedOperationException – if the array contains an attribute that cannot be set atomically
     *          when creating the directory
     * @throws  IOException – if an I/O error occurs
     * @throws  SecurityException – if a security manager exists and denies the write access
     */
    public static FileTMJ createTempFileChild(String prefix, String suffix, FileAttribute<?>... attributes) throws IOException {
    	return new FileTMJ(Files.createTempFile(prefix, suffix, attributes));
    }
    public static FileTMJ createTempFileChild(FileAttribute<?>... attributes) throws IOException {
    	return createTempFileChild(null,null,attributes);
    }
    /**
     * Creates a new empty file. If this is a file creates the file next to it.
     * If this is a directory creates it inside of it.
     * Uses prefix and suffix strings to generate its name.
     *
     * <p> The details as to how the name of the file is constructed is
     * implementation dependent and therefore not specified. Where possible
     * the {@code prefix} and {@code suffix} are used to construct candidate
     * names in the same manner as the {@link
     * java.io.File#createTempFile(String,String,File)} method.
     *
     * <p> Where used as a <em>work files</em>,
     * the resulting file may be opened using the {@link
     * StandardOpenOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} option so that the
     * file is deleted when the appropriate {@code close} method is invoked.
     * Alternatively, a {@link Runtime#addShutdownHook shutdown-hook}, or the
     * {@link java.io.File#deleteOnExit} mechanism may be used to delete the
     * file automatically.
     *
     * <p> The {@code attributes} parameter is optional {@link FileAttribute
     * file-attributes} to set atomically when creating the file. Each attribute
     * is identified by its {@link FileAttribute#name name}. If more than one
     * attribute of the same name is included in the array then all but the last
     * occurrence is ignored. When no file attributes are specified, then the
     * resulting file may have more restrictive access permissions to files
     * created by the {@link java.io.File#createTempFile(String,String,File)}
     * method.
     *
     * @param   prefix – prefix used in generating the file's name;
     *          may be {@code null}
     * @param   suffix – suffix used in generating the file's name;
     *          may be {@code null}, in which case "{@code .tmp}" is used
     * @param   attributes – optional list of file attributes to set atomically when
     *          creating the file
     * @return  newly created file that did not exist before
     * @throws  IllegalArgumentException – if the prefix or suffix parameters cannot be used to generate
     *          a candidate file name
     * @throws  UnsupportedOperationException – if the array contains an attribute that cannot be set atomically
     *          when creating the directory
     * @throws  IOException – if an I/O error occurs
     * @throws  SecurityException – if a security manager exists and denies the write access
     */
    public FileTMJ createTempFile(String prefix, String suffix, FileAttribute<?>... attributes) throws IOException {
    	if(isDirectory()) {
        	return new FileTMJ(Files.createTempFile(dataForkPath,prefix, suffix, attributes));
    	}else {
        	return new FileTMJ(Files.createTempFile(dataForkPath.getParent(),prefix, suffix, attributes));
    	}
    }
    public FileTMJ createTempFile(FileAttribute<?>... attributes) throws IOException {
    	return createTempFile(null,null,attributes);
    }




    /**
     * Creates a new directory in the system's temporary directory.
     * Uses the prefix string to generate its name.
     *
     * <p> The details as to how the name of the directory is constructed is
     * implementation dependent and therefore not specified. Where possible
     * the {@code prefix} is used to construct candidate
     * names.
     *
     * <p> The {@code attributes} parameter is optional {@link FileAttribute
     * file-attributes} to set atomically when creating the file. Each attribute
     * is identified by its {@link FileAttribute#name name}. If more than one
     * attribute of the same name is included in the array then all but the last
     * occurrence is ignored.
     *
     * @param   prefix – prefix used in generating the file's name;
     *          may be {@code null}
     * @param   attributes – optional list of file attributes to set atomically when
     *          creating the file
     * @return  newly created directory that did not exist before
     * @throws  IllegalArgumentException – if the prefix or suffix parameters cannot be used to generate
     *          a candidate file name
     * @throws  UnsupportedOperationException – if the array contains an attribute that cannot be set atomically
     *          when creating the directory
     * @throws  IOException – if an I/O error occurs
     * @throws  SecurityException – if a security manager exists and denies the write access
     */
    public static FileTMJ createTempDirectory(String prefix, FileAttribute<?>... attributes) throws IOException {
    	return new FileTMJ(Files.createTempDirectory(prefix, attributes));
    }
    public static FileTMJ createTempDirectory(FileAttribute<?>... attributes) throws IOException {
    	return createTempFileChild(null,null,attributes);
    }
    /**
     * Creates a new directory. If this is a file creates the directory next to it.
     * If this is a directory creates the new one inside of it.
     * Uses prefix and suffix strings to generate its name.
     *
     * <p> The details as to how the name of the file is constructed is
     * implementation dependent and therefore not specified. Where possible
     * the {@code prefix} and {@code suffix} are used to construct candidate
     * names in the same manner as the {@link
     * java.io.File#createTempFile(String,String,File)} method.
     *
     * <p> Where used as a <em>work files</em>,
     * the resulting file may be opened using the {@link
     * StandardOpenOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} option so that the
     * file is deleted when the appropriate {@code close} method is invoked.
     * Alternatively, a {@link Runtime#addShutdownHook shutdown-hook}, or the
     * {@link java.io.File#deleteOnExit} mechanism may be used to delete the
     * file automatically.
     *
     * <p> The {@code attrs} parameter is optional {@link FileAttribute
     * file-attributes} to set atomically when creating the file. Each attribute
     * is identified by its {@link FileAttribute#name name}. If more than one
     * attribute of the same name is included in the array then all but the last
     * occurrence is ignored. When no file attributes are specified, then the
     * resulting file may have more restrictive access permissions to files
     * created by the {@link java.io.File#createTempFile(String,String,File)}
     * method.
     *
     * @param   prefix – prefix used in generating the file's name;
     *          may be {@code null}
     * @param   attributes – optional list of file attributes to set atomically when
     *          creating the file
     * @return  newly created file that did not exist before
     * @throws  IllegalArgumentException – if the prefix or suffix parameters cannot be used to generate
     *          a candidate file name
     * @throws  UnsupportedOperationException – if the array contains an attribute that cannot be set atomically
     *          when creating the directory
     * @throws  IOException – if an I/O error occurs
     * @throws  SecurityException – if a security manager exists and denies the write access
     */
    public FileTMJ createTempDirectoryChild(String prefix, FileAttribute<?>... attributes) throws IOException {
    	if(isDirectory()) {
        	return new FileTMJ(Files.createTempDirectory(dataForkPath,prefix, attributes));
    	}else {
        	return new FileTMJ(Files.createTempDirectory(dataForkPath.getParent(),prefix, attributes));
    	}
    }
    public FileTMJ createTempDirectoryChild(FileAttribute<?>... attributes) throws IOException {
    	return createTempDirectoryChild(null,attributes);
    }

    /**
     * Creates a symbolic link to a target.
     *
     * <p> The {@code target} parameter is the target of the link.
     *
     * <p> The {@code attributes} parameter is optional {@link FileAttribute
     * attributes} to set atomically when creating the link. Each attribute is
     * identified by its {@link FileAttribute#name name}. If more than one attribute
     * of the same name is included in the array then all but the last occurrence
     * is ignored.
     *
     * <p> Where symbolic links are supported, but the underlying {@link FileStore}
     * does not support symbolic links, then this may fail with an {@link
     * IOException}. Additionally, some operating systems may require that the
     * Java virtual machine be started with implementation specific privileges to
     * create symbolic links, in which case this method may throw {@code IOException}.
     *
     * @param   link – the symbolic link file to create
     * @param   target – target of the symbolic link
     * @param   attributes – optional array of attributes to set atomically when creating the
     *          symbolic link
     * @return  {@code true} – if the link was successfully created, {@code false} otherwise
     * @throws  UnsupportedOperationException – if the implementation does not support symbolic links or the
     *          array contains an attribute that cannot be set atomically when
     *          creating the symbolic link
     * @throws  IOException – if an I/O error occurs
     * @throws  SecurityException – if a security manager denies the write access.
     */
    public boolean createSymbolicLink(FileTMJ link,boolean override, FileAttribute<?>... attributes) throws UnsupportedOperationException,SecurityException,IOException  {
    	if(override) {
    		link.removeIfExists();
    	}else if(link.exists()) {
    		return false;
    	}
    	try {
    		getFileSystem().provider().createSymbolicLink(link.dataForkPath, dataForkPath, attributes);
    		return true;
    	}catch(FileAlreadyExistsException e) {
    		throw new IOException(e); // if this happens, there was a unregular problem with the existence.
    	}
    }
    public boolean createSymbolicLinkIfNotExist(FileTMJ link, FileAttribute<?>... attributes) throws UnsupportedOperationException,SecurityException,IOException  {
    	return createSymbolicLink(link,false,attributes);
    }

    /**
     * Creates a new link (directory entry) for an existing file <i>(optional
     * operation)</i>.
     *
     * <p> The {@code link} parameter locates the directory entry to create.
     * The {@code existing} parameter is the path to an existing file. This
     * method creates a new directory entry for the file so that it can be
     * accessed using {@code link} as the path. On some file systems this is
     * known as creating a "hard link". Whether the file attributes are
     * maintained for the file or for each directory entry is file system
     * specific and therefore not specified. Typically, a file system requires
     * that all links (directory entries) for a file be on the same file system.
     * Furthermore, on some platforms, the Java virtual machine may require to
     * be started with implementation specific privileges to create hard links
     * or to create links to directories.
     *
     * @param   link – the link (directory entry) to create
     * @param   override – if true and there is a file/directory with the same path as link,
     *          then that one will be removed first. Otherwise nothing special happens.
     * @return  {@code true} – if the link was successfully created, {@code false} otherwise
     * @throws  UnsupportedOperationException – if the implementation does not support symbolic links or the
     *          array contains an attribute that cannot be set atomically when
     *          creating the symbolic link
     * @throws  IOException – if an I/O error occurs
     * @throws  SecurityException – if a security manager denies the write access.
     */
    public boolean createLink(FileTMJ link,boolean override) throws UnsupportedOperationException,FileAlreadyExistsException,SecurityException,IOException  {
    	if(override) {
    		link.removeIfExists();
    	}else if(link.exists()) {
    		return false;
    	}
    	try {
    		getFileSystem().provider().createLink(link.dataForkPath, dataForkPath);
    		return true;
    	}catch(FileAlreadyExistsException e) {
    		throw new IOException(e); // if this happens, there was a unregular problem with the existence.
    	}
    }
    public boolean createLinkIfNotExist(FileTMJ link) throws UnsupportedOperationException,FileAlreadyExistsException,SecurityException,IOException  {
    	return createLink(link,false);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


      
    public byte[] readDataFork(LinkOption... options) throws FileNotFoundException,IllegalArgumentException,SecurityException,UnsupportedOperationException,IOException{
//    	if(!exists()) {
//    		throw new FileNotFoundException(dataForkPath+" does not exist!");
//    	}
    	if(!hasDataFork()) {
    		return null;
    	}
    	return readFork(dataForkPath, options);
    }
    public byte[] readResourceFork(LinkOption... options) throws FileNotFoundException,IllegalArgumentException,SecurityException,UnsupportedOperationException,IOException{
    	if(!hasResourceFork()) {
    		return null;
    	}
    	return readFork(resourceForkPath, options);
    }
    /** Read the given binary file, and return its contents as a byte array.
     * @throws IOException */ 
    private byte[] readFork(Path path, LinkOption... options) throws FileNotFoundException,IllegalArgumentException,SecurityException,UnsupportedOperationException,IOException{
    	int len=(int)Files.size(path);
    	byte[] result = new byte[len];//TODO unchecked casting?!
    	int totalBytesRead = 0;
    	try( InputStream inputStream=new BufferedInputStream(Files.newInputStream(path, options)) ){
    		while(totalBytesRead < result.length){
    			int bytesRemaining = result.length - totalBytesRead;
    			//bufferedInputStream.read() returns -1, 0, or more :
    			  int bytesRead = inputStream.read(result, totalBytesRead, bytesRemaining); 
    			  if (bytesRead > 0){
    				  totalBytesRead = totalBytesRead + bytesRead;
    			  }
    		  }
    			  /* the above style is a bit tricky: it places bytes into the 'result' array; 
                   'result' is an output parameter; the while loop usually has a single iteration only.   */
//    			  log("Num bytes read: " + totalBytesRead);
    	  }catch(RuntimeException e) {
//    	  catch (FileNotFoundException ex) {
//    			 Throws:IllegalArgumentException - if an invalid combination of options is specified
//    		  UnsupportedOperationException - if an unsupported option is specified
//    		  IOException - if an I/O error occurs
//    		  SecurityException - If a security manager denies access to the file.
    	  }
    		  return result;
      }
    
    private byte[] readForkAsBytesIntern(Path path,int maxBytes, LinkOption... options) throws FileNotFoundException,IllegalArgumentException,SecurityException,UnsupportedOperationException,IOException{
    	try( InputStream inputStream=new BufferedInputStream(Files.newInputStream(path, options)) ){
			if(0==maxBytes) {
				maxBytes=(int) Files.size(path);//// TODO works with small files, only?
			}
			byte[] fileBytes = new byte[maxBytes]; 
			
//			(java.nio.Files.readAllBytes())
			
			inputStream.read(fileBytes);
			return fileBytes;
			
//			//Allocate buffer with 4byte = 32bit = Integer.SIZE
//			int bytesRead;
//			while ((bytesRead = inputStream.read(fileBytes)) != -1){
//			   //if bytesRead == 4 you read 1 int
//			   //do your stuff
//			}
			
		} catch (FileNotFoundException e) {
//			Logger.error(ErrorSet.fileNotFoundX(),file);
		} catch (IOException e) {
//			Logger.error(ErrorSet.couldNotAccessFile(),file);
		}
		return null;
	}
      
      /**
       Write a byte array to the given file. 
       Writing binary data is significantly simpler than reading it. 
      */
      void write(byte[] input, String outputFileName){
        log("Writing binary file...");
        try {
          OutputStream output = null;
          try {
            output = new BufferedOutputStream(new FileOutputStream(outputFileName));
            output.write(input);
          }
          finally {
            output.close();
          }
        }
        catch(FileNotFoundException ex){
          log("File not found.");
        }
        catch(IOException ex){
          log(ex);
        }
      }
      
      /** Read the given binary file, and return its contents as a byte array.*/ 
      byte[] readAlternateImpl(String inputFileName){
        log("Reading in binary file named : " + inputFileName);
        File file = new File(inputFileName);
        log("File size: " + file.length());
        byte[] result = null;
        try {
          InputStream input =  new BufferedInputStream(new FileInputStream(file));
          result = readAndClose(input);
        }
        catch (FileNotFoundException ex){
          log(ex);
        }
        return result;
      }
      
      /**
       Read an input stream, and return it as a byte array.  
       Sometimes the source of bytes is an input stream instead of a file. 
       This implementation closes aInput after it's read.
      */
      byte[] readAndClose(InputStream input){
        //carries the data from input to output :    
        byte[] bucket = new byte[32*1024]; 
        ByteArrayOutputStream result = null; 
        try  {
          try {
            //Use buffering? No. Buffering avoids costly access to disk or network;
            //buffering to an in-memory stream makes no sense.
            result = new ByteArrayOutputStream(bucket.length);
            int bytesRead = 0;
            while(bytesRead != -1){
              //aInput.read() returns -1, 0, or more :
              bytesRead = input.read(bucket);
              if(bytesRead > 0){
                result.write(bucket, 0, bytesRead);
              }
            }
          }
          finally {
            input.close();
            //result.close(); this is a no-operation for ByteArrayOutputStream
          }
        }
        catch (IOException ex){
          log(ex);
        }
        return result.toByteArray();
      }
      
      private static void log(Object thing){
        System.out.println(String.valueOf(thing));
      }
//    } 
    
    
    
    
    
    
      
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	// Getter, Setter, Overrides, toString, hash, etc. /////////////////////////////////
	
    /**
     * Compares two file objects lexicographically.
     * Returns the result of the compareTo method of the underlying {@link Path} objects.
     * The ordering defined by this method is provider specific, and in the case of the default
     * provider, platform specific. For example on UNIX systems, alphabetic case commonly is significant in comparing
     * pathnames, on Microsoft Windows systems it is not.
     * This method does not access the file system and neither file is required to exist.
     *
     * <p> This method may not be used to compare paths that are associated
     * with different file system providers.
     *
     * @param   file  the path compared to this path.
     *
     * @return  zero if the argument corresponds to a path that is {@link #equals equal} to this object's path,
     *          a value less than zero if this object's path is lexicographically less than the argument's path,
     *          a value greater than zero if this object's path is lexicographically greater than the argument's path.
     *
     * @throws  ClassCastException
     *          if the paths are associated with different providers.
     */
	public int compareTo(FileTMJ file) {
		return dataForkPath.compareTo(file.dataForkPath);
	}
	
	/**
     * Returns an iterator over the name elements of this path.
    *
    * <p> The first element returned by the iterator represents the name
    * element that is closest to the root in the directory hierarchy, the
    * second element is the next closest, and so on. The last element returned
    * is the name of the file or directory denoted by this path. The {@link
    * #getRoot root} component, if present, is not returned by the iterator.
    *
    * @return  an iterator over the name elements of this path.
    */
	   @Override public Iterator<Path> iterator() {
			throw new RuntimeException("Method not implemented, yet!"); //TODO IMPLEMENT!
		} 
	
	   /**
	     * Tests this file object for substantial equality with the given object.
	     * Returns the result of the equals method of the underlying {@link Path} objects.
	     * @param   obj   The object to be compared with
	     * @return  {@code true} if the objects are the same;
	     *          {@code false} otherwise.
	     */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			FileTMJ other = (FileTMJ) obj;
			return dataForkPath.equals(other.dataForkPath);
		}
	   
	   /**
	     * Compares two URLs, excluding the fragment component.<p>
	     *
	     * Returns {@code true} if this {@code URL} and the
	     * {@code other} argument are equal without taking the
	     * fragment component into consideration.
	     *
	     * @param   other   the {@code URL} to compare against.
	     * @return  {@code true} if they reference the same remote object;
	     *          {@code false} otherwise.
	     */
	   /**
	     * Tests if two paths locate the same file.
	     *
	     * <p> If both {@code Path} objects are {@link Path#equals(Object) equal}
	     * then this method returns {@code true} without checking if the file exists.
	     * If the two {@code Path} objects are associated with different providers
	     * then this method returns {@code false}. Otherwise, this method checks if
	     * both {@code Path} objects locate the same file, and depending on the
	     * implementation, may require to open or access both files.
	     *
	     * <p> If the file system and files remain static, then this method implements
	     * an equivalence relation for non-null {@code Paths}.
	     * <ul>
	     * <li>It is <i>reflexive</i>: for {@code Path} {@code f},
	     *     {@code isSameFile(f,f)} should return {@code true}.
	     * <li>It is <i>symmetric</i>: for two {@code Paths} {@code f} and {@code g},
	     *     {@code isSameFile(f,g)} will equal {@code isSameFile(g,f)}.
	     * <li>It is <i>transitive</i>: for three {@code Paths}
	     *     {@code f}, {@code g}, and {@code h}, if {@code isSameFile(f,g)} returns
	     *     {@code true} and {@code isSameFile(g,h)} returns {@code true}, then
	     *     {@code isSameFile(f,h)} will return return {@code true}.
	     * </ul>
	     *
	     * @param   path
	     *          one path to the file
	     * @param   path2
	     *          the other path
	     *
	     * @return  {@code true} if, and only if, the two paths locate the same file
	     *
	     * @throws  IOException
	     *          if an I/O error occurs
	     * @throws  SecurityException
	     *          In the case of the default provider, and a security manager is
	     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
	     *          method is invoked to check read access to both files.
	     *
	     * @see java.nio.file.attribute.BasicFileAttributes#fileKey
	     */
//	    public static boolean isSameFile(Path path, Path path2) throws IOException {
//	        return provider(path).isSameFile(path, path2);
		public boolean sameFile(FileTMJ otherFile) throws IOException {
			if (this == otherFile) { return true; }
			if (null==otherFile) { return false; }
			IOException ioException=null;
			Path path=null;
			try {
				path=toCanonicalPath();
			}catch(IOException e){
				ioException=e;
			}
			Path otherPath=null;
			try {
				otherPath=otherFile.toCanonicalPath();
			}catch(IOException e){
				throw ioException;
			}
			if(null==path) { return false; }
			return path.equals(otherPath); // return toCanonicalPath().equals(otherFile.toCanonicalPath());
		}
		
		
		static class CompareResult {
			enum CompareResultDetail { EXIST_vs_DONT, DONT_vs_EXIST, SIZE_DIFFERS, CONTENT_DIFFERS, TYPE_DIFFERS, IDENTICAL, SAME_CONTENT, SAME_FILE, BOTH_EXIST, BOTH_DONT_EXIST; 
			
				public boolean isSame() {
					return this==SAME_CONTENT || this==SAME_FILE ||this==BOTH_DONT_EXIST;
				}
			}
			
			private CompareResultDetail dataFork;
			private CompareResultDetail resourceFork;
			private CompareResultDetail dataSizeBytes;
			private CompareResultDetail resourceSizeBytes;
//			private CompareResultDetail sizeBytes;
//			private CompareResultDetail sizeBytes;
			private CompareResultDetail resultDetails;
			private boolean result;

			public CompareResultDetail getDataFork() {
				return dataFork;
			}
			protected void setDataFork(CompareResultDetail resultDetail) {
				this.dataFork = resultDetail;
			}
			public CompareResultDetail getResourceFork() {
				return resourceFork;
			}
			protected void setResourceFork(CompareResultDetail resultDetail) {
				this.resourceFork = resultDetail;
			}
			public CompareResultDetail getDataSizeBytes() {
				return dataSizeBytes;
			}
			protected void setDataSizeBytes(CompareResultDetail resultDetail) {
				this.dataSizeBytes = resultDetail;
			}
			public CompareResultDetail getResourceSizeBytes() {
				return resourceSizeBytes;
			}
			protected void setResourceSizeBytes(CompareResultDetail resultDetail) {
				this.resourceSizeBytes = resultDetail;
			}
			public CompareResultDetail getResultDetails() {
				return resultDetails;
			}
			protected void setResultDetails(CompareResultDetail resultDetail) {
				this.resultDetails = resultDetail;
			}
			public boolean isResult() {
				return result;
			}
			protected void setResult(boolean result) {
				this.result = result;
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((dataFork == null) ? 0 : dataFork.hashCode());
				result = prime * result + ((dataSizeBytes == null) ? 0 : dataSizeBytes.hashCode());
				result = prime * result + ((resourceFork == null) ? 0 : resourceFork.hashCode());
				result = prime * result + ((resourceSizeBytes == null) ? 0 : resourceSizeBytes.hashCode());
				result = prime * result + (this.result ? 1231 : 1237);
				return result;
			}
			
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				CompareResult other = (CompareResult) obj;
				if (dataFork != other.dataFork)
					return false;
				if (dataSizeBytes != other.dataSizeBytes)
					return false;
				if (resourceFork != other.resourceFork)
					return false;
				if (resourceSizeBytes != other.resourceSizeBytes)
					return false;
				if (resultDetails != other.resultDetails)
					return false;
				if (result != other.result)
					return false;
				return true;
			}

			@Override
			public String toString() {
				return "CompareResult [dataFork=" + dataFork + ", resourceFork=" + resourceFork + ", dataSizeBytes="
						+ dataSizeBytes + ", resourceSizeBytes=" + resourceSizeBytes + ", resultDetails=" + resultDetails + ", result=" + result + "]";
			}
			
			
		}
		 //----from org.apache.commons.io.FileUtils.contentEquals(final File file1, final File file2)
		/*
		 * Licensed to the Apache Software Foundation (ASF) under one or more
		 * contributor license agreements.  See the NOTICE file distributed with
		 * this work for additional information regarding copyright ownership.
		 * The ASF licenses this file to You under the Apache License, Version 2.0
		 * (the "License"); you may not use this file except in compliance with
		 * the License.  You may obtain a copy of the License at
		 *
		 *      http://www.apache.org/licenses/LICENSE-2.0
		 *
		 * Unless required by applicable law or agreed to in writing, software
		 * distributed under the License is distributed on an "AS IS" BASIS,
		 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
		 * See the License for the specific language governing permissions and
		 * limitations under the License.
		 */
		// Origin of code: Excalibur, Alexandria, Commons-Utils
	    /**
	     * Compares the contents of two files to determine if they are equal or not.
	     * <p>
	     * This method checks to see if the two files are different lengths
	     * or if they point to the same file, before resorting to byte-by-byte
	     * comparison of the contents.
	     * <p>
	     * Code origin: Avalon
	     *
	     * @param file1 the first file
	     * @param file2 the second file
	     * @return true if the content of the files are equal or they both don't
	     * exist, false otherwise
	     * @throws IOException in case of an I/O error
	     */
		public boolean sameContent(FileTMJ otherFile) throws IOException{
			return sameContent(otherFile,false).result;
		}
		public CompareResult sameContent(FileTMJ otherFile,boolean complete) throws IOException{
			CompareResult result=new CompareResult();
			if(hasDataFork()) {
				if(otherFile.hasDataFork()){
					result.setDataFork(CompareResultDetail.BOTH_EXIST);
				}else {
					result.setDataFork(CompareResultDetail.EXIST_vs_DONT);
					result.setResult(false);
					if(!complete) {
						return result;
					}
				}
			}else if(otherFile.hasDataFork()) {
				result.setDataFork(CompareResultDetail.DONT_vs_EXIST);
				result.setResult(false);
				if(!complete) {
					return result;
				}
			}else {
				result.setDataFork(CompareResultDetail.BOTH_DONT_EXIST);
			}
//			final boolean dataForkYes = hasDataFork();
//			if (dataForkYes != otherFile.hasDataFork()) {
//				return false;
//	        }
			if(hasResourceFork()) {
				if(otherFile.hasResourceFork()){
					result.setResourceFork(CompareResultDetail.BOTH_EXIST);
				}else {
					result.setResourceFork(CompareResultDetail.EXIST_vs_DONT);
					result.setResult(false);
					if(!complete) {
						return result;
					}
				}
			}else if(otherFile.hasResourceFork()) {
				result.setResourceFork(CompareResultDetail.DONT_vs_EXIST);
				result.setResult(false);
				if(!complete) {
					return result;
				}
			}else {
				result.setResourceFork(CompareResultDetail.BOTH_DONT_EXIST);
			}
//			final boolean resourceForkYes = hasResourceFork();
//	        if (resourceForkYes != otherFile.hasResourceFork()) {
//	            return false;
//	        }
			if(CompareResultDetail.BOTH_DONT_EXIST==result.getDataFork() && CompareResultDetail.BOTH_DONT_EXIST==result.getResourceFork()) {
//	        if (!dataForkYes && !resourceForkYes) {  // two not existing files are equal
				result.setResult(true);
	        	return result;
	        }
	if (isDirectory() || otherFile.isDirectory()) {
		// don't want to compare directory contents
		throw new IOException("Can't compare directories, only files");
	}
//		    if (sizeBytes() != otherFile.sizeBytes()) {
			if(CompareResultDetail.BOTH_EXIST==result.getDataFork()) {
				if(Files.size(dataForkPath) == Files.size(otherFile.dataForkPath)) {
					result.setDataSizeBytes(CompareResultDetail.IDENTICAL);
				}else {
					result.setDataSizeBytes(CompareResultDetail.SIZE_DIFFERS);
					result.setResult(false);
					if(!complete) {
						return result;
					}
				}
			}
			if(CompareResultDetail.BOTH_EXIST==result.getResourceFork()) {
				if(Files.size(resourceForkPath) == Files.size(otherFile.resourceForkPath)) {
					result.setResourceSizeBytes(CompareResultDetail.IDENTICAL);
				}else {
					result.setResourceSizeBytes(CompareResultDetail.SIZE_DIFFERS);
					result.setResult(false);
					if(!complete) {
						return result;
					}
				}
			}
		    if (toCanonicalPath().equals(otherFile.toCanonicalPath())) {
		    	result.setDataFork(CompareResultDetail.SAME_FILE);
		    	result.setResourceFork(CompareResultDetail.SAME_FILE);
		    	return result;
		    }
			if(CompareResultDetail.BOTH_EXIST==result.getDataFork()) {
//		    if(dataForkYes) {
		    	try ( InputStream inputStream = Files.newInputStream(dataForkPath, LinkOption.NOFOLLOW_LINKS);
		    		  InputStream otherInputStream = Files.newInputStream(otherFile.dataForkPath, LinkOption.NOFOLLOW_LINKS)
		    		) {
		    		if(inputStreamContentEquals(inputStream, otherInputStream)) {
		    			result.setDataFork(CompareResultDetail.SAME_CONTENT);
						result.setResult(true);
		    		}else {
		    			result.setDataFork(CompareResultDetail.CONTENT_DIFFERS);
						result.setResult(false);
						if(!complete) {
							return result;
						}
		    		}
		    	}
		    }
			if(CompareResultDetail.BOTH_EXIST==result.getResourceFork()) {
//		    if(resourceForkYes) {
		    	try ( InputStream inputStream = Files.newInputStream(resourceForkPath, LinkOption.NOFOLLOW_LINKS);
		    		  InputStream otherInputStream = Files.newInputStream(otherFile.resourceForkPath, LinkOption.NOFOLLOW_LINKS)
		    			) {
		    		if(!inputStreamContentEquals(inputStream, otherInputStream)) {
		    			result.setDataFork(CompareResultDetail.SAME_CONTENT);
						result.setResult(true);
		    		}else {
		    			result.setDataFork(CompareResultDetail.CONTENT_DIFFERS);
						result.setResult(false);
						if(!complete) {
							return result;
						}
		    		}
		    	}
		    }
		    return result;   

//			byte[] thisBytes,otherBytes;
//			thisBytes=readDataFork();
//			otherBytes=otherFile.readDataFork();
//			boolean same=thisBytes.equals(otherBytes);//TODO dies wird man wohl selbst implementiert werden müssen.
//			if(!same) {return false;}
//			thisBytes=readResourceFork();
//			otherBytes=otherFile.readResourceFork();
//			same=thisBytes.equals(otherBytes);//TODO s.v.
//			if(!same) {return false;}
//			return true;		
		}
		
		//from org.apache.commons.io.IOUtils.contentEquals(InputStream input1, InputStream input2)
		/*
		 * Licensed to the Apache Software Foundation (ASF) under one or more
		 * contributor license agreements.  See the NOTICE file distributed with
		 * this work for additional information regarding copyright ownership.
		 * The ASF licenses this file to You under the Apache License, Version 2.0
		 * (the "License"); you may not use this file except in compliance with
		 * the License.  You may obtain a copy of the License at
		 *
		 *      http://www.apache.org/licenses/LICENSE-2.0
		 *
		 * Unless required by applicable law or agreed to in writing, software
		 * distributed under the License is distributed on an "AS IS" BASIS,
		 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
		 * See the License for the specific language governing permissions and
		 * limitations under the License.
		 */
		/*
		 * All the methods in this class that read a stream are buffered internally.
		 * This means that there is no cause to use a <code>BufferedInputStream</code>
		 * or <code>BufferedReader</code>. The default buffer size of 4K has been shown
		 * to be efficient in tests.
		 * <p>
		 * Origin of code: Excalibur.
		 */
	    /**
	     * Compares the contents of two Streams to determine if they are equal or
	     * not.
	     * <p>
	     * This method buffers the input internally using
	     * <code>BufferedInputStream</code> if they are not already buffered.
	     *
	     * @param input1 the first stream
	     * @param input2 the second stream
	     * @return true if the content of the streams are equal or they both don't
	     * exist, false otherwise
	     * @throws NullPointerException if either input is null
	     * @throws IOException          if an I/O error occurs
	     */
	    private static boolean inputStreamContentEquals(InputStream input1, InputStream input2) throws IOException {
	        if (input1 == input2) {
	            return true;
	        }
	        if (!(input1 instanceof BufferedInputStream)) {
	            input1 = new BufferedInputStream(input1);
	        }
	        if (!(input2 instanceof BufferedInputStream)) {
	            input2 = new BufferedInputStream(input2);
	        }

	        int ch = input1.read();
	        while (-1 != ch) {// read returns -1 on EOF!
	            final int ch2 = input2.read();
	            if (ch != ch2) {
	                return false;
	            }
	            ch = input1.read();
	        }
	        final int ch2 = input2.read();
	        return -1==ch2;
	    }
		
	   

	    /**
	     * Computes a hash code for this file object.
	     * <p> The Computation uses the hashCode method of the underlying {@link Path} object.
	     * and satisfies the general contract of the {@link Object#hashCode
	     * Object.hashCode} method.
	     *
	     * @return  The hash-code value for this file object.
	     */
		@Override
		public int hashCode() {
			final int prime = 31;
			return java.util.Objects.hash( 
					prime,
					dataForkPath
				);
		}

  /**
   * Returns the {@code String} representation of this {@code FileTMJ}.
   *
   * <p> The returned string uses the default name {@link
   * FileSystem#getSeparator separator} to separate names in the path.
   *
     * @return  a string representation of this object.
     */
    @Override
	public String toString() {
		String s= "FileTMJ [path=" + dataForkPath;
		try {
			s=s+ ", hasDataFork=" + hasDataFork();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			s=s+ ", hasResourceFork=" + hasResourceFork();
		} catch (IOException e) {
			e.printStackTrace();
		}
		s=s+ "]";
		return s;
	}

}