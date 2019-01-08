//package java.nio.file;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URI;
//import java.util.Iterator;
//
///**
// * An object that may be used to locate a file in a file system. It will
// * typically represent a system dependent file path.
// *
// * <p> A {@code Path} represents a path that is hierarchical and composed of a
// * sequence of directory and file name elements separated by a special separator
// * or delimiter. A <em>root component</em>, that identifies a file system
// * hierarchy, may also be present. The name element that is <em>farthest</em>
// * from the root of the directory hierarchy is the name of a file or directory.
// * The other name elements are directory names. A {@code Path} can represent a
// * root, a root and a sequence of names, or simply one or more name elements.
// * A {@code Path} is considered to be an <i>empty path</i> if it consists
// * solely of one name element that is empty. Accessing a file using an
// * <i>empty path</i> is equivalent to accessing the default directory of the
// * file system. {@code Path} defines the {@link #getFileName() getFileName},
// * {@link #getParent getParent}, {@link #getRoot getRoot}, and {@link #subpath
// * subpath} methods to access the path components or a subsequence of its name
// * elements.
// *
// * <p> In addition to accessing the components of a path, a {@code Path} also
// * defines the {@link #resolve(Path) resolve} and {@link #resolveSibling(Path)
// * resolveSibling} methods to combine paths. The {@link #relativize relativize}
// * method that can be used to construct a relative path between two paths.
// * Paths can be {@link #compareTo compared}, and tested against each other using
// * the {@link #startsWith startsWith} and {@link #endsWith endsWith} methods.
// *
// * <p> This interface extends {@link Watchable} interface so that a directory
// * located by a path can be {@link #register registered} with a {@link
// * WatchService} and entries in the directory watched. </p>
// *
// * <p> <b>WARNING:</b> This interface is only intended to be implemented by
// * those developing custom file system implementations. Methods may be added to
// * this interface in future releases. </p>
// *
// * <h2>Accessing Files</h2>
// * <p> Paths may be used with the {@link Files} class to operate on files,
// * directories, and other types of files. For example, suppose we want a {@link
// * java.io.BufferedReader} to read text from a file "{@code access.log}". The
// * file is located in a directory "{@code logs}" relative to the current working
// * directory and is UTF-8 encoded.
// * <pre>
// *     Path path = FileSystems.getDefault().getPath("logs", "access.log");
// *     BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
// * </pre>
// *
// * <a name="interop"></a><h2>Interoperability</h2>
// * <p> Paths associated with the default {@link
// * java.nio.file.spi.FileSystemProvider provider} are generally interoperable
// * with the {@link java.io.File java.io.File} class. Paths created by other
// * providers are unlikely to be interoperable with the abstract path names
// * represented by {@code java.io.File}. The {@link java.io.File#toPath toPath}
// * method may be used to obtain a {@code Path} from the abstract path name
// * represented by a {@code java.io.File} object. The resulting {@code Path} can
// * be used to operate on the same file as the {@code java.io.File} object. In
// * addition, the {@link #toFile toFile} method is useful to construct a {@code
// * File} from the {@code String} representation of a {@code Path}.
// *
// * <h2>Concurrency</h2>
// * <p> Implementations of this interface are immutable and safe for use by
// * multiple concurrent threads.
// *
// * @since 1.7
// * @see Paths
// */
//
//public interface Path extends Watchable {
//    /**
//     * Returns a relative {@code Path} that is a subsequence of the name
//     * elements of this path.
//     *
//     * <p> The {@code beginIndex} and {@code endIndex} parameters specify the
//     * subsequence of name elements. The name that is <em>closest</em> to the root
//     * in the directory hierarchy has index {@code 0}. The name that is
//     * <em>farthest</em> from the root has index {@link #getNameCount
//     * count}{@code -1}. The returned {@code Path} object has the name elements
//     * that begin at {@code beginIndex} and extend to the element at index {@code
//     * endIndex-1}.
//     *
//     * @param   beginIndex
//     *          the index of the first element, inclusive
//     * @param   endIndex
//     *          the index of the last element, exclusive
//     *
//     * @return  a new {@code Path} object that is a subsequence of the name
//     *          elements in this {@code Path}
//     *
//     * @throws  IllegalArgumentException
//     *          if {@code beginIndex} is negative, or greater than or equal to
//     *          the number of elements. If {@code endIndex} is less than or
//     *          equal to {@code beginIndex}, or larger than the number of elements.
//     */
//    Path subpath(int beginIndex, int endIndex);
//
//
//    /**
//     * Tests if this path ends with the given path.
//     *
//     * <p> If the given path has <em>N</em> elements, and no root component,
//     * and this path has <em>N</em> or more elements, then this path ends with
//     * the given path if the last <em>N</em> elements of each path, starting at
//     * the element farthest from the root, are equal.
//     *
//     * <p> If the given path has a root component then this path ends with the
//     * given path if the root component of this path <em>ends with</em> the root
//     * component of the given path, and the corresponding elements of both paths
//     * are equal. Whether or not the root component of this path ends with the
//     * root component of the given path is file system specific. If this path
//     * does not have a root component and the given path has a root component
//     * then this path does not end with the given path.
//     *
//     * <p> If the given path is associated with a different {@code FileSystem}
//     * to this path then {@code false} is returned.
//     *
//     * @param   other
//     *          the given path
//     *
//     * @return  {@code true} if this path ends with the given path; otherwise
//     *          {@code false}
//     */
//    boolean endsWith(Path other);
//
//    /**
//     * Tests if this path ends with a {@code Path}, constructed by converting
//     * the given path string, in exactly the manner specified by the {@link
//     * #endsWith(Path) endsWith(Path)} method. On UNIX for example, the path
//     * "{@code foo/bar}" ends with "{@code foo/bar}" and "{@code bar}". It does
//     * not end with "{@code r}" or "{@code /bar}". Note that trailing separators
//     * are not taken into account, and so invoking this method on the {@code
//     * Path}"{@code foo/bar}" with the {@code String} "{@code bar/}" returns
//     * {@code true}.
//     *
//     * @param   other
//     *          the given path string
//     *
//     * @return  {@code true} if this path ends with the given path; otherwise
//     *          {@code false}
//     *
//     * @throws  InvalidPathException
//     *          If the path string cannot be converted to a Path.
//     */
//    boolean endsWith(String other);
//
//
//
//
//
//    // -- watchable --
//
//    /**
//     * Registers the file located by this path with a watch service.
//     *
//     * <p> In this release, this path locates a directory that exists. The
//     * directory is registered with the watch service so that entries in the
//     * directory can be watched. The {@code events} parameter is the events to
//     * register and may contain the following events:
//     * <ul>
//     *   <li>{@link StandardWatchEventKinds#ENTRY_CREATE ENTRY_CREATE} -
//     *       entry created or moved into the directory</li>
//     *   <li>{@link StandardWatchEventKinds#ENTRY_DELETE ENTRY_DELETE} -
//     *        entry deleted or moved out of the directory</li>
//     *   <li>{@link StandardWatchEventKinds#ENTRY_MODIFY ENTRY_MODIFY} -
//     *        entry in directory was modified</li>
//     * </ul>
//     *
//     * <p> The {@link WatchEvent#context context} for these events is the
//     * relative path between the directory located by this path, and the path
//     * that locates the directory entry that is created, deleted, or modified.
//     *
//     * <p> The set of events may include additional implementation specific
//     * event that are not defined by the enum {@link StandardWatchEventKinds}
//     *
//     * <p> The {@code modifiers} parameter specifies <em>modifiers</em> that
//     * qualify how the directory is registered. This release does not define any
//     * <em>standard</em> modifiers. It may contain implementation specific
//     * modifiers.
//     *
//     * <p> Where a file is registered with a watch service by means of a symbolic
//     * link then it is implementation specific if the watch continues to depend
//     * on the existence of the symbolic link after it is registered.
//     *
//     * @param   watcher
//     *          the watch service to which this object is to be registered
//     * @param   events
//     *          the events for which this object should be registered
//     * @param   modifiers
//     *          the modifiers, if any, that modify how the object is registered
//     *
//     * @return  a key representing the registration of this object with the
//     *          given watch service
//     *
//     * @throws  UnsupportedOperationException
//     *          if unsupported events or modifiers are specified
//     * @throws  IllegalArgumentException
//     *          if an invalid combination of events or modifiers is specified
//     * @throws  ClosedWatchServiceException
//     *          if the watch service is closed
//     * @throws  NotDirectoryException
//     *          if the file is registered to watch the entries in a directory
//     *          and the file is not a directory  <i>(optional specific exception)</i>
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the file.
//     */
//    @Override WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events, WatchEvent.Modifier... modifiers) throws IOException;
//
//    /**
//     * Registers the file located by this path with a watch service.
//     *
//     * <p> An invocation of this method behaves in exactly the same way as the
//     * invocation
//     * <pre>
//     *     watchable.{@link #register(WatchService,WatchEvent.Kind[],WatchEvent.Modifier[]) register}(watcher, events, new WatchEvent.Modifier[0]);
//     * </pre>
//     *
//     * <p> <b>Usage Example:</b>
//     * Suppose we wish to register a directory for entry create, delete, and modify
//     * events:
//     * <pre>
//     *     Path dir = ...
//     *     WatchService watcher = ...
//     *
//     *     WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
//     * </pre>
//     * @param   watcher
//     *          The watch service to which this object is to be registered
//     * @param   events
//     *          The events for which this object should be registered
//     *
//     * @return  A key representing the registration of this object with the
//     *          given watch service
//     *
//     * @throws  UnsupportedOperationException
//     *          If unsupported events are specified
//     * @throws  IllegalArgumentException
//     *          If an invalid combination of events is specified
//     * @throws  ClosedWatchServiceException
//     *          If the watch service is closed
//     * @throws  NotDirectoryException
//     *          If the file is registered to watch the entries in a directory
//     *          and the file is not a directory  <i>(optional specific exception)</i>
//     * @throws  IOException
//     *          If an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the file.
//     */
//    @Override WatchKey register(WatchService watcher, WatchEvent.Kind<?>... events) throws IOException;
//
//
//
//}
