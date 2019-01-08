//package java.io;
//
//import java.net.URI;
//import java.net.URL;
//import java.net.MalformedURLException;
//import java.net.URISyntaxException;
//import java.util.List;
//import java.util.ArrayList;
//import java.security.AccessController;
//import java.security.SecureRandom;
//import java.nio.file.Path;
//import java.nio.file.FileSystems;
//import sun.security.action.GetPropertyAction;
//
///**
// * An abstract representation of file and directory pathnames.
// *
// * <p> User interfaces and operating systems use system-dependent <em>pathname
// * strings</em> to name files and directories.  This class presents an
// * abstract, system-independent view of hierarchical pathnames.  An
// * <em>abstract pathname</em> has two components:
// *
// * <ol>
// * <li> An optional system-dependent <em>prefix</em> string,
// *      such as a disk-drive specifier, <code>"/"</code>&nbsp;for the UNIX root
// *      directory, or <code>"\\\\"</code>&nbsp;for a Microsoft Windows UNC pathname, and
// * <li> A sequence of zero or more string <em>names</em>.
// * </ol>
// *
// * The first name in an abstract pathname may be a directory name or, in the
// * case of Microsoft Windows UNC pathnames, a hostname.  Each subsequent name
// * in an abstract pathname denotes a directory; the last name may denote
// * either a directory or a file.  The <em>empty</em> abstract pathname has no
// * prefix and an empty name sequence.
// *
// * <p> The conversion of a pathname string to or from an abstract pathname is
// * inherently system-dependent.  When an abstract pathname is converted into a
// * pathname string, each name is separated from the next by a single copy of
// * the default <em>separator character</em>.  The default name-separator
// * character is defined by the system property <code>file.separator</code>, and
// * is made available in the public static fields <code>{@link
// * #separator}</code> and <code>{@link #separatorChar}</code> of this class.
// * When a pathname string is converted into an abstract pathname, the names
// * within it may be separated by the default name-separator character or by any
// * other name-separator character that is supported by the underlying system.
// *
// * <p> A pathname, whether abstract or in string form, may be either
// * <em>absolute</em> or <em>relative</em>.  An absolute pathname is complete in
// * that no other information is required in order to locate the file that it
// * denotes.  A relative pathname, in contrast, must be interpreted in terms of
// * information taken from some other pathname.  By default the classes in the
// * <code>java.io</code> package always resolve relative pathnames against the
// * current user directory.  This directory is named by the system property
// * <code>user.dir</code>, and is typically the directory in which the Java
// * virtual machine was invoked.
// *
// * <p> The <em>parent</em> of an abstract pathname may be obtained by invoking
// * the {@link #getParent} method of this class and consists of the pathname's
// * prefix and each name in the pathname's name sequence except for the last.
// * Each directory's absolute pathname is an ancestor of any <tt>File</tt>
// * object with an absolute abstract pathname which begins with the directory's
// * absolute pathname.  For example, the directory denoted by the abstract
// * pathname <tt>"/usr"</tt> is an ancestor of the directory denoted by the
// * pathname <tt>"/usr/local/bin"</tt>.
// *
// * <p> The prefix concept is used to handle root directories on UNIX platforms,
// * and drive specifiers, root directories and UNC pathnames on Microsoft Windows platforms,
// * as follows:
// *
// * <ul>
// *
// * <li> For UNIX platforms, the prefix of an absolute pathname is always
// * <code>"/"</code>.  Relative pathnames have no prefix.  The abstract pathname
// * denoting the root directory has the prefix <code>"/"</code> and an empty
// * name sequence.
// *
// * <li> For Microsoft Windows platforms, the prefix of a pathname that contains a drive
// * specifier consists of the drive letter followed by <code>":"</code> and
// * possibly followed by <code>"\\"</code> if the pathname is absolute.  The
// * prefix of a UNC pathname is <code>"\\\\"</code>; the hostname and the share
// * name are the first two names in the name sequence.  A relative pathname that
// * does not specify a drive has no prefix.
// *
// * </ul>
// *
// * <p> Instances of this class may or may not denote an actual file-system
// * object such as a file or a directory.  If it does denote such an object
// * then that object resides in a <i>partition</i>.  A partition is an
// * operating system-specific portion of storage for a file system.  A single
// * storage device (e.g. a physical disk-drive, flash memory, CD-ROM) may
// * contain multiple partitions.  The object, if any, will reside on the
// * partition <a name="partName">named</a> by some ancestor of the absolute
// * form of this pathname.
// *
// * <p> A file system may implement restrictions to certain operations on the
// * actual file-system object, such as reading, writing, and executing.  These
// * restrictions are collectively known as <i>access permissions</i>.  The file
// * system may have multiple sets of access permissions on a single object.
// * For example, one set may apply to the object's <i>owner</i>, and another
// * may apply to all other users.  The access permissions on an object may
// * cause some methods in this class to fail.
// *
// * <p> Instances of the <code>File</code> class are immutable; that is, once
// * created, the abstract pathname represented by a <code>File</code> object
// * will never change.
// *
// * <h3>Interoperability with {@code java.nio.file} package</h3>
// *
// * <p> The <a href="../../java/nio/file/package-summary.html">{@code java.nio.file}</a>
// * package defines interfaces and classes for the Java virtual machine to access
// * files, file attributes, and file systems. This API may be used to overcome
// * many of the limitations of the {@code java.io.File} class.
// * The {@link #toPath toPath} method may be used to obtain a {@link
// * Path} that uses the abstract path represented by a {@code File} object to
// * locate a file. The resulting {@code Path} may be used with the {@link
// * java.nio.file.Files} class to provide more efficient and extensive access to
// * additional file operations, file attributes, and I/O exceptions to help
// * diagnose errors when an operation on a file fails.
// *
// * @author  unascribed
// * @since   JDK1.0
// */
//
//public class File {
//    /* -- Constructors -- */
//
//    /**
//     * Internal constructor for already-normalized pathname strings.
//     */
//    private File(String pathname, int prefixLength) {
//    	throw new RuntimeException("Method not implemented!");//TODO IMPLEMENT!    this.path = pathname;
////        this.prefixLength = prefixLength;
//    }
//
//    /**
//     * Internal constructor for already-normalized pathname strings.
//     * The parameter order is used to disambiguate this method from the
//     * public(File, String) constructor.
//     */
//    private File(String child, File parent) {
//    	throw new RuntimeException("Method not implemented!");//TODO IMPLEMENT!    assert parent.path != null;
////        assert (!parent.path.equals(""));
////        this.path = fs.resolve(parent.path, child);
////        this.prefixLength = parent.prefixLength;
//    }
//
//    /**
//     * Creates a new <code>File</code> instance by converting the given
//     * pathname string into an abstract pathname.  If the given string is
//     * the empty string, then the result is the empty abstract pathname.
//     *
//     * @param   pathname  A pathname string
//     * @throws  NullPointerException
//     *          If the <code>pathname</code> argument is <code>null</code>
//     */
//    public File(String pathname) {
//    	throw new RuntimeException("Method not implemented!");//TODO IMPLEMENT!    if (pathname == null) {
////            throw new NullPointerException();
////        }
////        this.path = fs.normalize(pathname);
////        this.prefixLength = fs.prefixLength(this.path);
//    }
//
//    /* Note: The two-argument File constructors do not interpret an empty
//       parent abstract pathname as the current user directory.  An empty parent
//       instead causes the child to be resolved against the system-dependent
//       directory defined by the FileSystem.getDefaultParent method.  On Unix
//       this default is "/", while on Microsoft Windows it is "\\".  This is required for
//       compatibility with the original behavior of this class. */
//
//    /**
//     * Creates a new <code>File</code> instance from a parent pathname string
//     * and a child pathname string.
//     *
//     * <p> If <code>parent</code> is <code>null</code> then the new
//     * <code>File</code> instance is created as if by invoking the
//     * single-argument <code>File</code> constructor on the given
//     * <code>child</code> pathname string.
//     *
//     * <p> Otherwise the <code>parent</code> pathname string is taken to denote
//     * a directory, and the <code>child</code> pathname string is taken to
//     * denote either a directory or a file.  If the <code>child</code> pathname
//     * string is absolute then it is converted into a relative pathname in a
//     * system-dependent way.  If <code>parent</code> is the empty string then
//     * the new <code>File</code> instance is created by converting
//     * <code>child</code> into an abstract pathname and resolving the result
//     * against a system-dependent default directory.  Otherwise each pathname
//     * string is converted into an abstract pathname and the child abstract
//     * pathname is resolved against the parent.
//     *
//     * @param   parent  The parent pathname string
//     * @param   child   The child pathname string
//     * @throws  NullPointerException
//     *          If <code>child</code> is <code>null</code>
//     */
//    public File(String parent, String child) {
//    	throw new RuntimeException("Method not implemented!");//TODO IMPLEMENT!      if (child == null) {
////            throw new NullPointerException();
////        }
////        if (parent != null) {
////            if (parent.equals("")) {
////                this.path = fs.resolve(fs.getDefaultParent(),
////                                       fs.normalize(child));
////            } else {
////                this.path = fs.resolve(fs.normalize(parent),
////                                       fs.normalize(child));
////            }
////        } else {
////            this.path = fs.normalize(child);
////        }
////        this.prefixLength = fs.prefixLength(this.path);
//    }
//
//    /**
//     * Creates a new <code>File</code> instance from a parent abstract
//     * pathname and a child pathname string.
//     *
//     * <p> If <code>parent</code> is <code>null</code> then the new
//     * <code>File</code> instance is created as if by invoking the
//     * single-argument <code>File</code> constructor on the given
//     * <code>child</code> pathname string.
//     *
//     * <p> Otherwise the <code>parent</code> abstract pathname is taken to
//     * denote a directory, and the <code>child</code> pathname string is taken
//     * to denote either a directory or a file.  If the <code>child</code>
//     * pathname string is absolute then it is converted into a relative
//     * pathname in a system-dependent way.  If <code>parent</code> is the empty
//     * abstract pathname then the new <code>File</code> instance is created by
//     * converting <code>child</code> into an abstract pathname and resolving
//     * the result against a system-dependent default directory.  Otherwise each
//     * pathname string is converted into an abstract pathname and the child
//     * abstract pathname is resolved against the parent.
//     *
//     * @param   parent  The parent abstract pathname
//     * @param   child   The child pathname string
//     * @throws  NullPointerException
//     *          If <code>child</code> is <code>null</code>
//     */
//    public File(File parent, String child) {
//    	throw new RuntimeException("Method not implemented!");//TODO IMPLEMENT!     if (child == null) {
////            throw new NullPointerException();
////        }
////        if (parent != null) {
////            if (parent.path.equals("")) {
////                this.path = fs.resolve(fs.getDefaultParent(),
////                                       fs.normalize(child));
////            } else {
////                this.path = fs.resolve(parent.path,
////                                       fs.normalize(child));
////            }
////        } else {
////            this.path = fs.normalize(child);
////        }
////        this.prefixLength = fs.prefixLength(this.path);
//    }
//
//    /**
//     * Creates a new <tt>File</tt> instance by converting the given
//     * <tt>file:</tt> URI into an abstract pathname.
//     *
//     * <p> The exact form of a <tt>file:</tt> URI is system-dependent, hence
//     * the transformation performed by this constructor is also
//     * system-dependent.
//     *
//     * <p> For a given abstract pathname <i>f</i> it is guaranteed that
//     *
//     * <blockquote><tt>
//     * new File(</tt><i>&nbsp;f</i><tt>.{@link #toURI() toURI}()).equals(</tt><i>&nbsp;f</i><tt>.{@link #getAbsoluteFile() getAbsoluteFile}())
//     * </tt></blockquote>
//     *
//     * so long as the original abstract pathname, the URI, and the new abstract
//     * pathname are all created in (possibly different invocations of) the same
//     * Java virtual machine.  This relationship typically does not hold,
//     * however, when a <tt>file:</tt> URI that is created in a virtual machine
//     * on one operating system is converted into an abstract pathname in a
//     * virtual machine on a different operating system.
//     *
//     * @param  uri
//     *         An absolute, hierarchical URI with a scheme equal to
//     *         <tt>"file"</tt>, a non-empty path component, and undefined
//     *         authority, query, and fragment components
//     *
//     * @throws  NullPointerException
//     *          If <tt>uri</tt> is <tt>null</tt>
//     *
//     * @throws  IllegalArgumentException
//     *          If the preconditions on the parameter do not hold
//     *
//     * @see #toURI()
//     * @see java.net.URI
//     * @since 1.4
//     */
//    public File(URI uri) {
//    	throw new RuntimeException("Method not implemented!");//TODO IMPLEMENT!    
////        // Check our many preconditions
////        if (!uri.isAbsolute())
////            throw new IllegalArgumentException("URI is not absolute");
////        if (uri.isOpaque())
////            throw new IllegalArgumentException("URI is not hierarchical");
////        String scheme = uri.getScheme();
////        if ((scheme == null) || !scheme.equalsIgnoreCase("file"))
////            throw new IllegalArgumentException("URI scheme is not \"file\"");
////        if (uri.getAuthority() != null)
////            throw new IllegalArgumentException("URI has an authority component");
////        if (uri.getFragment() != null)
////            throw new IllegalArgumentException("URI has a fragment component");
////        if (uri.getQuery() != null)
////            throw new IllegalArgumentException("URI has a query component");
////        String p = uri.getPath();
////        if (p.equals(""))
////            throw new IllegalArgumentException("URI path component is empty");
////
////        // Okay, now initialize
////        p = fs.fromURIPath(p);
////        if (File.separatorChar != '/')
////            p = p.replace('/', File.separatorChar);
////        this.path = fs.normalize(p);
////        this.prefixLength = fs.prefixLength(this.path);
//    }
//
//
//
//    /* -- Attribute accessors -- */
//
//    /**
//     * Tests whether the application can read the file denoted by this
//     * abstract pathname. On some platforms it may be possible to start the
//     * Java virtual machine with special privileges that allow it to read
//     * files that are marked as unreadable. Consequently this method may return
//     * {@code true} even though the file does not have read permissions.
//     *
//     * @return  <code>true</code> if and only if the file specified by this
//     *          abstract pathname exists <em>and</em> can be read by the
//     *          application; <code>false</code> otherwise
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its <code>{@link
//     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
//     *          method denies read access to the file
//     */
//    public boolean canRead() {
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkRead(path);
//        }
//        if (isInvalid()) {
//            return false;
//        }
//        return fs.checkAccess(this, FileSystem.ACCESS_READ);
//    }
//
//    /**
//     * Tests whether the application can modify the file denoted by this
//     * abstract pathname. On some platforms it may be possible to start the
//     * Java virtual machine with special privileges that allow it to modify
//     * files that are marked read-only. Consequently this method may return
//     * {@code true} even though the file is marked read-only.
//     *
//     * @return  <code>true</code> if and only if the file system actually
//     *          contains a file denoted by this abstract pathname <em>and</em>
//     *          the application is allowed to write to the file;
//     *          <code>false</code> otherwise.
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its <code>{@link
//     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
//     *          method denies write access to the file
//     */
//    public boolean canWrite() {
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkWrite(path);
//        }
//        if (isInvalid()) {
//            return false;
//        }
//        return fs.checkAccess(this, FileSystem.ACCESS_WRITE);
//    }
//
//
//    
//
//    /**
//     * Tests whether the file named by this abstract pathname is a hidden
//     * file.  The exact definition of <em>hidden</em> is system-dependent.  On
//     * UNIX systems, a file is considered to be hidden if its name begins with
//     * a period character (<code>'.'</code>).  On Microsoft Windows systems, a file is
//     * considered to be hidden if it has been marked as such in the filesystem.
//     *
//     * @return  <code>true</code> if and only if the file denoted by this
//     *          abstract pathname is hidden according to the conventions of the
//     *          underlying platform
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its <code>{@link
//     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
//     *          method denies read access to the file
//     *
//     * @since 1.2
//     */
//    public boolean isHidden() {
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkRead(path);
//        }
//        if (isInvalid()) {
//            return false;
//        }
//        return ((fs.getBooleanAttributes(this) & FileSystem.BA_HIDDEN) != 0);
//    }
//
//    /**
//     * Returns the time that the file denoted by this abstract pathname was
//     * last modified.
//     *
//     * <p> Where it is required to distinguish an I/O exception from the case
//     * where {@code 0L} is returned, or where several attributes of the
//     * same file are required at the same time, or where the time of last
//     * access or the creation time are required, then the {@link
//     * java.nio.file.Files#readAttributes(Path,Class,LinkOption[])
//     * Files.readAttributes} method may be used.
//     *
//     * @return  A <code>long</code> value representing the time the file was
//     *          last modified, measured in milliseconds since the epoch
//     *          (00:00:00 GMT, January 1, 1970), or <code>0L</code> if the
//     *          file does not exist or if an I/O error occurs
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its <code>{@link
//     *          java.lang.SecurityManager#checkRead(java.lang.String)}</code>
//     *          method denies read access to the file
//     */
//    public long lastModified() {
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkRead(path);
//        }
//        if (isInvalid()) {
//            return 0L;
//        }
//        return fs.getLastModifiedTime(this);
//    }
//
//    
//
//
//    /* -- File operations -- */
//
//    
//
//    
//
//    /**
//     * Requests that the file or directory denoted by this abstract
//     * pathname be deleted when the virtual machine terminates.
//     * Files (or directories) are deleted in the reverse order that
//     * they are registered. Invoking this method to delete a file or
//     * directory that is already registered for deletion has no effect.
//     * Deletion will be attempted only for normal termination of the
//     * virtual machine, as defined by the Java Language Specification.
//     *
//     * <p> Once deletion has been requested, it is not possible to cancel the
//     * request.  This method should therefore be used with care.
//     *
//     * <P>
//     * Note: this method should <i>not</i> be used for file-locking, as
//     * the resulting protocol cannot be made to work reliably. The
//     * {@link java.nio.channels.FileLock FileLock}
//     * facility should be used instead.
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its <code>{@link
//     *          java.lang.SecurityManager#checkDelete}</code> method denies
//     *          delete access to the file
//     *
//     * @see #delete
//     *
//     * @since 1.2
//     */
//    public void deleteOnExit() {
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkDelete(path);
//        }
//        if (isInvalid()) {
//            return;
//        }
//        DeleteOnExitHook.add(path);
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
//    /**
//     * Sets the last-modified time of the file or directory named by this
//     * abstract pathname.
//     *
//     * <p> All platforms support file-modification times to the nearest second,
//     * but some provide more precision.  The argument will be truncated to fit
//     * the supported precision.  If the operation succeeds and no intervening
//     * operations on the file take place, then the next invocation of the
//     * <code>{@link #lastModified}</code> method will return the (possibly
//     * truncated) <code>time</code> argument that was passed to this method.
//     *
//     * @param  time  The new last-modified time, measured in milliseconds since
//     *               the epoch (00:00:00 GMT, January 1, 1970)
//     *
//     * @return <code>true</code> if and only if the operation succeeded;
//     *          <code>false</code> otherwise
//     *
//     * @throws  IllegalArgumentException  If the argument is negative
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its <code>{@link
//     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
//     *          method denies write access to the named file
//     *
//     * @since 1.2
//     */
//    public boolean setLastModified(long time) {
//        if (time < 0) throw new IllegalArgumentException("Negative time");
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkWrite(path);
//        }
//        if (isInvalid()) {
//            return false;
//        }
//        return fs.setLastModifiedTime(this, time);
//    }
//
//    /**
//     * Marks the file or directory named by this abstract pathname so that
//     * only read operations are allowed. After invoking this method the file
//     * or directory will not change until it is either deleted or marked
//     * to allow write access. On some platforms it may be possible to start the
//     * Java virtual machine with special privileges that allow it to modify
//     * files that are marked read-only. Whether or not a read-only file or
//     * directory may be deleted depends upon the underlying system.
//     *
//     * @return <code>true</code> if and only if the operation succeeded;
//     *          <code>false</code> otherwise
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its <code>{@link
//     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
//     *          method denies write access to the named file
//     *
//     * @since 1.2
//     */
//    public boolean setReadOnly() {
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkWrite(path);
//        }
//        if (isInvalid()) {
//            return false;
//        }
//        return fs.setReadOnly(this);
//    }
//
//    /**
//     * Sets the owner's or everybody's write permission for this abstract
//     * pathname. On some platforms it may be possible to start the Java virtual
//     * machine with special privileges that allow it to modify files that
//     * disallow write operations.
//     *
//     * <p> The {@link java.nio.file.Files} class defines methods that operate on
//     * file attributes including file permissions. This may be used when finer
//     * manipulation of file permissions is required.
//     *
//     * @param   writable
//     *          If <code>true</code>, sets the access permission to allow write
//     *          operations; if <code>false</code> to disallow write operations
//     *
//     * @param   ownerOnly
//     *          If <code>true</code>, the write permission applies only to the
//     *          owner's write permission; otherwise, it applies to everybody.  If
//     *          the underlying file system can not distinguish the owner's write
//     *          permission from that of others, then the permission will apply to
//     *          everybody, regardless of this value.
//     *
//     * @return  <code>true</code> if and only if the operation succeeded. The
//     *          operation will fail if the user does not have permission to change
//     *          the access permissions of this abstract pathname.
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its <code>{@link
//     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
//     *          method denies write access to the named file
//     *
//     * @since 1.6
//     */
//    public boolean setWritable(boolean writable, boolean ownerOnly) {
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkWrite(path);
//        }
//        if (isInvalid()) {
//            return false;
//        }
//        return fs.setPermission(this, FileSystem.ACCESS_WRITE, writable, ownerOnly);
//    }
//
//    /**
//     * A convenience method to set the owner's write permission for this abstract
//     * pathname. On some platforms it may be possible to start the Java virtual
//     * machine with special privileges that allow it to modify files that
//     * disallow write operations.
//     *
//     * <p> An invocation of this method of the form <tt>file.setWritable(arg)</tt>
//     * behaves in exactly the same way as the invocation
//     *
//     * <pre>
//     *     file.setWritable(arg, true) </pre>
//     *
//     * @param   writable
//     *          If <code>true</code>, sets the access permission to allow write
//     *          operations; if <code>false</code> to disallow write operations
//     *
//     * @return  <code>true</code> if and only if the operation succeeded.  The
//     *          operation will fail if the user does not have permission to
//     *          change the access permissions of this abstract pathname.
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its <code>{@link
//     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
//     *          method denies write access to the file
//     *
//     * @since 1.6
//     */
//    public boolean setWritable(boolean writable) {
//        return setWritable(writable, true);
//    }
//
//    /**
//     * Sets the owner's or everybody's read permission for this abstract
//     * pathname. On some platforms it may be possible to start the Java virtual
//     * machine with special privileges that allow it to read files that are
//     * marked as unreadable.
//     *
//     * <p> The {@link java.nio.file.Files} class defines methods that operate on
//     * file attributes including file permissions. This may be used when finer
//     * manipulation of file permissions is required.
//     *
//     * @param   readable
//     *          If <code>true</code>, sets the access permission to allow read
//     *          operations; if <code>false</code> to disallow read operations
//     *
//     * @param   ownerOnly
//     *          If <code>true</code>, the read permission applies only to the
//     *          owner's read permission; otherwise, it applies to everybody.  If
//     *          the underlying file system can not distinguish the owner's read
//     *          permission from that of others, then the permission will apply to
//     *          everybody, regardless of this value.
//     *
//     * @return  <code>true</code> if and only if the operation succeeded.  The
//     *          operation will fail if the user does not have permission to
//     *          change the access permissions of this abstract pathname.  If
//     *          <code>readable</code> is <code>false</code> and the underlying
//     *          file system does not implement a read permission, then the
//     *          operation will fail.
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its <code>{@link
//     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
//     *          method denies write access to the file
//     *
//     * @since 1.6
//     */
//    public boolean setReadable(boolean readable, boolean ownerOnly) {
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkWrite(path);
//        }
//        if (isInvalid()) {
//            return false;
//        }
//        return fs.setPermission(this, FileSystem.ACCESS_READ, readable, ownerOnly);
//    }
//
//    /**
//     * A convenience method to set the owner's read permission for this abstract
//     * pathname. On some platforms it may be possible to start the Java virtual
//     * machine with special privileges that allow it to read files that that are
//     * marked as unreadable.
//     *
//     * <p>An invocation of this method of the form <tt>file.setReadable(arg)</tt>
//     * behaves in exactly the same way as the invocation
//     *
//     * <pre>
//     *     file.setReadable(arg, true) </pre>
//     *
//     * @param  readable
//     *          If <code>true</code>, sets the access permission to allow read
//     *          operations; if <code>false</code> to disallow read operations
//     *
//     * @return  <code>true</code> if and only if the operation succeeded.  The
//     *          operation will fail if the user does not have permission to
//     *          change the access permissions of this abstract pathname.  If
//     *          <code>readable</code> is <code>false</code> and the underlying
//     *          file system does not implement a read permission, then the
//     *          operation will fail.
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its <code>{@link
//     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
//     *          method denies write access to the file
//     *
//     * @since 1.6
//     */
//    public boolean setReadable(boolean readable) {
//        return setReadable(readable, true);
//    }
//
//    /**
//     * Sets the owner's or everybody's execute permission for this abstract
//     * pathname. On some platforms it may be possible to start the Java virtual
//     * machine with special privileges that allow it to execute files that are
//     * not marked executable.
//     *
//     * <p> The {@link java.nio.file.Files} class defines methods that operate on
//     * file attributes including file permissions. This may be used when finer
//     * manipulation of file permissions is required.
//     *
//     * @param   executable
//     *          If <code>true</code>, sets the access permission to allow execute
//     *          operations; if <code>false</code> to disallow execute operations
//     *
//     * @param   ownerOnly
//     *          If <code>true</code>, the execute permission applies only to the
//     *          owner's execute permission; otherwise, it applies to everybody.
//     *          If the underlying file system can not distinguish the owner's
//     *          execute permission from that of others, then the permission will
//     *          apply to everybody, regardless of this value.
//     *
//     * @return  <code>true</code> if and only if the operation succeeded.  The
//     *          operation will fail if the user does not have permission to
//     *          change the access permissions of this abstract pathname.  If
//     *          <code>executable</code> is <code>false</code> and the underlying
//     *          file system does not implement an execute permission, then the
//     *          operation will fail.
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its <code>{@link
//     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
//     *          method denies write access to the file
//     *
//     * @since 1.6
//     */
//    public boolean setExecutable(boolean executable, boolean ownerOnly) {
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkWrite(path);
//        }
//        if (isInvalid()) {
//            return false;
//        }
//        return fs.setPermission(this, FileSystem.ACCESS_EXECUTE, executable, ownerOnly);
//    }
//
//    /**
//     * A convenience method to set the owner's execute permission for this
//     * abstract pathname. On some platforms it may be possible to start the Java
//     * virtual machine with special privileges that allow it to execute files
//     * that are not marked executable.
//     *
//     * <p>An invocation of this method of the form <tt>file.setExcutable(arg)</tt>
//     * behaves in exactly the same way as the invocation
//     *
//     * <pre>
//     *     file.setExecutable(arg, true) </pre>
//     *
//     * @param   executable
//     *          If <code>true</code>, sets the access permission to allow execute
//     *          operations; if <code>false</code> to disallow execute operations
//     *
//     * @return   <code>true</code> if and only if the operation succeeded.  The
//     *           operation will fail if the user does not have permission to
//     *           change the access permissions of this abstract pathname.  If
//     *           <code>executable</code> is <code>false</code> and the underlying
//     *           file system does not implement an execute permission, then the
//     *           operation will fail.
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its <code>{@link
//     *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code>
//     *          method denies write access to the file
//     *
//     * @since 1.6
//     */
//    public boolean setExecutable(boolean executable) {
//        return setExecutable(executable, true);
//    }
//
//    /**
//     * Tests whether the application can execute the file denoted by this
//     * abstract pathname. On some platforms it may be possible to start the
//     * Java virtual machine with special privileges that allow it to execute
//     * files that are not marked executable. Consequently this method may return
//     * {@code true} even though the file does not have execute permissions.
//     *
//     * @return  <code>true</code> if and only if the abstract pathname exists
//     *          <em>and</em> the application is allowed to execute the file
//     *
//     * @throws  SecurityException
//     *          If a security manager exists and its <code>{@link
//     *          java.lang.SecurityManager#checkExec(java.lang.String)}</code>
//     *          method denies execute access to the file
//     *
//     * @since 1.6
//     */
//    public boolean canExecute() {
//        SecurityManager security = System.getSecurityManager();
//        if (security != null) {
//            security.checkExec(path);
//        }
//        if (isInvalid()) {
//            return false;
//        }
//        return fs.checkAccess(this, FileSystem.ACCESS_EXECUTE);
//    }
//
//
//    /* -- Filesystem interface -- */
//
//    /**
//     * List the available filesystem roots.
//     *
//     * <p> A particular Java platform may support zero or more
//     * hierarchically-organized file systems.  Each file system has a
//     * {@code root} directory from which all other files in that file system
//     * can be reached.  Windows platforms, for example, have a root directory
//     * for each active drive; UNIX platforms have a single root directory,
//     * namely {@code "/"}.  The set of available filesystem roots is affected
//     * by various system-level operations such as the insertion or ejection of
//     * removable media and the disconnecting or unmounting of physical or
//     * virtual disk drives.
//     *
//     * <p> This method returns an array of {@code File} objects that denote the
//     * root directories of the available filesystem roots.  It is guaranteed
//     * that the canonical pathname of any file physically present on the local
//     * machine will begin with one of the roots returned by this method.
//     *
//     * <p> The canonical pathname of a file that resides on some other machine
//     * and is accessed via a remote-filesystem protocol such as SMB or NFS may
//     * or may not begin with one of the roots returned by this method.  If the
//     * pathname of a remote file is syntactically indistinguishable from the
//     * pathname of a local file then it will begin with one of the roots
//     * returned by this method.  Thus, for example, {@code File} objects
//     * denoting the root directories of the mapped network drives of a Windows
//     * platform will be returned by this method, while {@code File} objects
//     * containing UNC pathnames will not be returned by this method.
//     *
//     * <p> Unlike most methods in this class, this method does not throw
//     * security exceptions.  If a security manager exists and its {@link
//     * SecurityManager#checkRead(String)} method denies read access to a
//     * particular root directory, then that directory will not appear in the
//     * result.
//     *
//     * @return  An array of {@code File} objects denoting the available
//     *          filesystem roots, or {@code null} if the set of roots could not
//     *          be determined.  The array will be empty if there are no
//     *          filesystem roots.
//     *
//     * @since  1.2
//     * @see java.nio.file.FileStore
//     */
//    public static File[] listRoots() {
//        return fs.listRoots();
//    }
//
//
//    /* -- Disk usage -- */
//
//    /**
//     * Returns the size of the partition <a href="#partName">named</a> by this
//     * abstract pathname.
//     *
//     * @return  The size, in bytes, of the partition or <tt>0L</tt> if this
//     *          abstract pathname does not name a partition
//     *
//     * @throws  SecurityException
//     *          If a security manager has been installed and it denies
//     *          {@link RuntimePermission}<tt>("getFileSystemAttributes")</tt>
//     *          or its {@link SecurityManager#checkRead(String)} method denies
//     *          read access to the file named by this abstract pathname
//     *
//     * @since  1.6
//     */
//    public long getTotalSpace() {
//        SecurityManager sm = System.getSecurityManager();
//        if (sm != null) {
//            sm.checkPermission(new RuntimePermission("getFileSystemAttributes"));
//            sm.checkRead(path);
//        }
//        if (isInvalid()) {
//            return 0L;
//        }
//        return fs.getSpace(this, FileSystem.SPACE_TOTAL);
//    }
//
//    /**
//     * Returns the number of unallocated bytes in the partition <a
//     * href="#partName">named</a> by this abstract path name.
//     *
//     * <p> The returned number of unallocated bytes is a hint, but not
//     * a guarantee, that it is possible to use most or any of these
//     * bytes.  The number of unallocated bytes is most likely to be
//     * accurate immediately after this call.  It is likely to be made
//     * inaccurate by any external I/O operations including those made
//     * on the system outside of this virtual machine.  This method
//     * makes no guarantee that write operations to this file system
//     * will succeed.
//     *
//     * @return  The number of unallocated bytes on the partition or <tt>0L</tt>
//     *          if the abstract pathname does not name a partition.  This
//     *          value will be less than or equal to the total file system size
//     *          returned by {@link #getTotalSpace}.
//     *
//     * @throws  SecurityException
//     *          If a security manager has been installed and it denies
//     *          {@link RuntimePermission}<tt>("getFileSystemAttributes")</tt>
//     *          or its {@link SecurityManager#checkRead(String)} method denies
//     *          read access to the file named by this abstract pathname
//     *
//     * @since  1.6
//     */
//    public long getFreeSpace() {
//        SecurityManager sm = System.getSecurityManager();
//        if (sm != null) {
//            sm.checkPermission(new RuntimePermission("getFileSystemAttributes"));
//            sm.checkRead(path);
//        }
//        if (isInvalid()) {
//            return 0L;
//        }
//        return fs.getSpace(this, FileSystem.SPACE_FREE);
//    }
//
//    /**
//     * Returns the number of bytes available to this virtual machine on the
//     * partition <a href="#partName">named</a> by this abstract pathname.  When
//     * possible, this method checks for write permissions and other operating
//     * system restrictions and will therefore usually provide a more accurate
//     * estimate of how much new data can actually be written than {@link
//     * #getFreeSpace}.
//     *
//     * <p> The returned number of available bytes is a hint, but not a
//     * guarantee, that it is possible to use most or any of these bytes.  The
//     * number of unallocated bytes is most likely to be accurate immediately
//     * after this call.  It is likely to be made inaccurate by any external
//     * I/O operations including those made on the system outside of this
//     * virtual machine.  This method makes no guarantee that write operations
//     * to this file system will succeed.
//     *
//     * @return  The number of available bytes on the partition or <tt>0L</tt>
//     *          if the abstract pathname does not name a partition.  On
//     *          systems where this information is not available, this method
//     *          will be equivalent to a call to {@link #getFreeSpace}.
//     *
//     * @throws  SecurityException
//     *          If a security manager has been installed and it denies
//     *          {@link RuntimePermission}<tt>("getFileSystemAttributes")</tt>
//     *          or its {@link SecurityManager#checkRead(String)} method denies
//     *          read access to the file named by this abstract pathname
//     *
//     * @since  1.6
//     */
//    public long getUsableSpace() {
//        SecurityManager sm = System.getSecurityManager();
//        if (sm != null) {
//            sm.checkPermission(new RuntimePermission("getFileSystemAttributes"));
//            sm.checkRead(path);
//        }
//        if (isInvalid()) {
//            return 0L;
//        }
//        return fs.getSpace(this, FileSystem.SPACE_USABLE);
//    }
//
//    
//
//
//}
