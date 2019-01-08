//package java.nio.file;
//
//import java.io.IOException;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.Closeable;
//import java.io.File;
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
///**
// * This class consists exclusively of static methods that operate on files,
// * directories, or other types of files.
// *
// * <p> In most cases, the methods defined here will delegate to the associated
// * file system provider to perform the file operations.
// *
// * @since 1.7
// */
//public final class Files {
//    /**
//     * Opens a file, returning an input stream to read from the file. The stream
//     * will not be buffered, and is not required to support the {@link
//     * InputStream#mark mark} or {@link InputStream#reset reset} methods. The
//     * stream will be safe for access by multiple concurrent threads. Reading
//     * commences at the beginning of the file. Whether the returned stream is
//     * <i>asynchronously closeable</i> and/or <i>interruptible</i> is highly
//     * file system provider specific and therefore not specified.
//     *
//     * <p> The {@code options} parameter determines how the file is opened.
//     * If no options are present then it is equivalent to opening the file with
//     * the {@link StandardOpenOption#READ READ} option. In addition to the {@code
//     * READ} option, an implementation may also support additional implementation
//     * specific options.
//     *
//     * @param   path
//     *          the path to the file to open
//     * @param   options
//     *          options specifying how the file is opened
//     *
//     * @return  a new input stream
//     *
//     * @throws  IllegalArgumentException
//     *          if an invalid combination of options is specified
//     * @throws  UnsupportedOperationException
//     *          if an unsupported option is specified
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the file.
//     */
//    public static InputStream newInputStream(Path path, OpenOption... options) throws IOException {
//        return provider(path).newInputStream(path, options);
//    }
//
//    /**
//     * Opens or creates a file, returning an output stream that may be used to
//     * write bytes to the file. The resulting stream will not be buffered. The
//     * stream will be safe for access by multiple concurrent threads. Whether
//     * the returned stream is <i>asynchronously closeable</i> and/or
//     * <i>interruptible</i> is highly file system provider specific and
//     * therefore not specified.
//     *
//     * <p> This method opens or creates a file in exactly the manner specified
//     * by the {@link #newByteChannel(Path,Set,FileAttribute[]) newByteChannel}
//     * method with the exception that the {@link StandardOpenOption#READ READ}
//     * option may not be present in the array of options. If no options are
//     * present then this method works as if the {@link StandardOpenOption#CREATE
//     * CREATE}, {@link StandardOpenOption#TRUNCATE_EXISTING TRUNCATE_EXISTING},
//     * and {@link StandardOpenOption#WRITE WRITE} options are present. In other
//     * words, it opens the file for writing, creating the file if it doesn't
//     * exist, or initially truncating an existing {@link #isRegularFile
//     * regular-file} to a size of {@code 0} if it exists.
//     *
//     * <p> <b>Usage Examples:</b>
//     * <pre>
//     *     Path path = ...
//     *
//     *     // truncate and overwrite an existing file, or create the file if
//     *     // it doesn't initially exist
//     *     OutputStream out = Files.newOutputStream(path);
//     *
//     *     // append to an existing file, fail if the file does not exist
//     *     out = Files.newOutputStream(path, APPEND);
//     *
//     *     // append to an existing file, create file if it doesn't initially exist
//     *     out = Files.newOutputStream(path, CREATE, APPEND);
//     *
//     *     // always create new file, failing if it already exists
//     *     out = Files.newOutputStream(path, CREATE_NEW);
//     * </pre>
//     *
//     * @param   path
//     *          the path to the file to open or create
//     * @param   options
//     *          options specifying how the file is opened
//     *
//     * @return  a new output stream
//     *
//     * @throws  IllegalArgumentException
//     *          if {@code options} contains an invalid combination of options
//     * @throws  UnsupportedOperationException
//     *          if an unsupported option is specified
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkWrite(String) checkWrite}
//     *          method is invoked to check write access to the file. The {@link
//     *          SecurityManager#checkDelete(String) checkDelete} method is
//     *          invoked to check delete access if the file is opened with the
//     *          {@code DELETE_ON_CLOSE} option.
//     */
//    public static OutputStream newOutputStream(Path path, OpenOption... options) throws IOException {
//        return provider(path).newOutputStream(path, options);
//    }
//
//    /**
//     * Opens or creates a file, returning a seekable byte channel to access the
//     * file.
//     *
//     * <p> The {@code options} parameter determines how the file is opened.
//     * The {@link StandardOpenOption#READ READ} and {@link
//     * StandardOpenOption#WRITE WRITE} options determine if the file should be
//     * opened for reading and/or writing. If neither option (or the {@link
//     * StandardOpenOption#APPEND APPEND} option) is present then the file is
//     * opened for reading. By default reading or writing commence at the
//     * beginning of the file.
//     *
//     * <p> In the addition to {@code READ} and {@code WRITE}, the following
//     * options may be present:
//     *
//     * <table border=1 cellpadding=5 summary="Options">
//     * <tr> <th>Option</th> <th>Description</th> </tr>
//     * <tr>
//     *   <td> {@link StandardOpenOption#APPEND APPEND} </td>
//     *   <td> If this option is present then the file is opened for writing and
//     *     each invocation of the channel's {@code write} method first advances
//     *     the position to the end of the file and then writes the requested
//     *     data. Whether the advancement of the position and the writing of the
//     *     data are done in a single atomic operation is system-dependent and
//     *     therefore unspecified. This option may not be used in conjunction
//     *     with the {@code READ} or {@code TRUNCATE_EXISTING} options. </td>
//     * </tr>
//     * <tr>
//     *   <td> {@link StandardOpenOption#TRUNCATE_EXISTING TRUNCATE_EXISTING} </td>
//     *   <td> If this option is present then the existing file is truncated to
//     *   a size of 0 bytes. This option is ignored when the file is opened only
//     *   for reading. </td>
//     * </tr>
//     * <tr>
//     *   <td> {@link StandardOpenOption#CREATE_NEW CREATE_NEW} </td>
//     *   <td> If this option is present then a new file is created, failing if
//     *   the file already exists or is a symbolic link. When creating a file the
//     *   check for the existence of the file and the creation of the file if it
//     *   does not exist is atomic with respect to other file system operations.
//     *   This option is ignored when the file is opened only for reading. </td>
//     * </tr>
//     * <tr>
//     *   <td > {@link StandardOpenOption#CREATE CREATE} </td>
//     *   <td> If this option is present then an existing file is opened if it
//     *   exists, otherwise a new file is created. This option is ignored if the
//     *   {@code CREATE_NEW} option is also present or the file is opened only
//     *   for reading. </td>
//     * </tr>
//     * <tr>
//     *   <td > {@link StandardOpenOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} </td>
//     *   <td> When this option is present then the implementation makes a
//     *   <em>best effort</em> attempt to delete the file when closed by the
//     *   {@link SeekableByteChannel#close close} method. If the {@code close}
//     *   method is not invoked then a <em>best effort</em> attempt is made to
//     *   delete the file when the Java virtual machine terminates. </td>
//     * </tr>
//     * <tr>
//     *   <td>{@link StandardOpenOption#SPARSE SPARSE} </td>
//     *   <td> When creating a new file this option is a <em>hint</em> that the
//     *   new file will be sparse. This option is ignored when not creating
//     *   a new file. </td>
//     * </tr>
//     * <tr>
//     *   <td> {@link StandardOpenOption#SYNC SYNC} </td>
//     *   <td> Requires that every update to the file's content or metadata be
//     *   written synchronously to the underlying storage device. (see <a
//     *   href="package-summary.html#integrity"> Synchronized I/O file
//     *   integrity</a>). </td>
//     * </tr>
//     * <tr>
//     *   <td> {@link StandardOpenOption#DSYNC DSYNC} </td>
//     *   <td> Requires that every update to the file's content be written
//     *   synchronously to the underlying storage device. (see <a
//     *   href="package-summary.html#integrity"> Synchronized I/O file
//     *   integrity</a>). </td>
//     * </tr>
//     * </table>
//     *
//     * <p> An implementation may also support additional implementation specific
//     * options.
//     *
//     * <p> The {@code attrs} parameter is optional {@link FileAttribute
//     * file-attributes} to set atomically when a new file is created.
//     *
//     * <p> In the case of the default provider, the returned seekable byte channel
//     * is a {@link java.nio.channels.FileChannel}.
//     *
//     * <p> <b>Usage Examples:</b>
//     * <pre>
//     *     Path path = ...
//     *
//     *     // open file for reading
//     *     ReadableByteChannel rbc = Files.newByteChannel(path, EnumSet.of(READ)));
//     *
//     *     // open file for writing to the end of an existing file, creating
//     *     // the file if it doesn't already exist
//     *     WritableByteChannel wbc = Files.newByteChannel(path, EnumSet.of(CREATE,APPEND));
//     *
//     *     // create file with initial permissions, opening it for both reading and writing
//     *     {@code FileAttribute<Set<PosixFilePermission>> perms = ...}
//     *     SeekableByteChannel sbc = Files.newByteChannel(path, EnumSet.of(CREATE_NEW,READ,WRITE), perms);
//     * </pre>
//     *
//     * @param   path
//     *          the path to the file to open or create
//     * @param   options
//     *          options specifying how the file is opened
//     * @param   attrs
//     *          an optional list of file attributes to set atomically when
//     *          creating the file
//     *
//     * @return  a new seekable byte channel
//     *
//     * @throws  IllegalArgumentException
//     *          if the set contains an invalid combination of options
//     * @throws  UnsupportedOperationException
//     *          if an unsupported open option is specified or the array contains
//     *          attributes that cannot be set atomically when creating the file
//     * @throws  FileAlreadyExistsException
//     *          if a file of that name already exists and the {@link
//     *          StandardOpenOption#CREATE_NEW CREATE_NEW} option is specified
//     *          <i>(optional specific exception)</i>
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the path if the file is
//     *          opened for reading. The {@link SecurityManager#checkWrite(String)
//     *          checkWrite} method is invoked to check write access to the path
//     *          if the file is opened for writing. The {@link
//     *          SecurityManager#checkDelete(String) checkDelete} method is
//     *          invoked to check delete access if the file is opened with the
//     *          {@code DELETE_ON_CLOSE} option.
//     *
//     * @see java.nio.channels.FileChannel#open(Path,Set,FileAttribute[])
//     */
//    public static SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options,<FileAttribute<?>... attrs) throws IOException{
//        return provider(path).newByteChannel(path, options, attrs);
//    }
//
//    /**
//     * Opens or creates a file, returning a seekable byte channel to access the
//     * file.
//     *
//     * <p> This method opens or creates a file in exactly the manner specified
//     * by the {@link #newByteChannel(Path,Set,FileAttribute[]) newByteChannel}
//     * method.
//     *
//     * @param   path
//     *          the path to the file to open or create
//     * @param   options
//     *          options specifying how the file is opened
//     *
//     * @return  a new seekable byte channel
//     *
//     * @throws  IllegalArgumentException
//     *          if the set contains an invalid combination of options
//     * @throws  UnsupportedOperationException
//     *          if an unsupported open option is specified
//     * @throws  FileAlreadyExistsException
//     *          if a file of that name already exists and the {@link
//     *          StandardOpenOption#CREATE_NEW CREATE_NEW} option is specified
//     *          <i>(optional specific exception)</i>
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the path if the file is
//     *          opened for reading. The {@link SecurityManager#checkWrite(String)
//     *          checkWrite} method is invoked to check write access to the path
//     *          if the file is opened for writing. The {@link
//     *          SecurityManager#checkDelete(String) checkDelete} method is
//     *          invoked to check delete access if the file is opened with the
//     *          {@code DELETE_ON_CLOSE} option.
//     *
//     * @see java.nio.channels.FileChannel#open(Path,OpenOption[])
//     */
//    public static SeekableByteChannel newByteChannel(Path path, OpenOption... options) throws IOException {
//        Set<OpenOption> set = new HashSet<OpenOption>(options.length);
//        Collections.addAll(set, options);
//        return newByteChannel(path, set);
//    }
//
//    // -- Directories --
//
//    /**
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
//    public static DirectoryStream<Path> newDirectoryStream(Path dir)        throws IOException   {
//        return provider(dir).newDirectoryStream(dir, AcceptAllFilter.FILTER);
//    }
//
//    /**
//     * Opens a directory, returning a {@link DirectoryStream} to iterate over
//     * the entries in the directory. The elements returned by the directory
//     * stream's {@link DirectoryStream#iterator iterator} are of type {@code
//     * Path}, each one representing an entry in the directory. The {@code Path}
//     * objects are obtained as if by {@link Path#resolve(Path) resolving} the
//     * name of the directory entry against {@code dir}. The entries returned by
//     * the iterator are filtered by matching the {@code String} representation
//     * of their file names against the given <em>globbing</em> pattern.
//     *
//     * <p> For example, suppose we want to iterate over the files ending with
//     * ".java" in a directory:
//     * <pre>
//     *     Path dir = ...
//     *     try (DirectoryStream&lt;Path&gt; stream = Files.newDirectoryStream(dir, "*.java")) {
//     *         :
//     *     }
//     * </pre>
//     *
//     * <p> The globbing pattern is specified by the {@link
//     * FileSystem#getPathMatcher getPathMatcher} method.
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
//     * @param   glob
//     *          the glob pattern
//     *
//     * @return  a new and open {@code DirectoryStream} object
//     *
//     * @throws  java.util.regex.PatternSyntaxException
//     *          if the pattern is invalid
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
//    public static DirectoryStream<Path> newDirectoryStream(Path dir, String glob)     throws IOException   {
//        // avoid creating a matcher if all entries are required.
//        if (glob.equals("*"))
//            return newDirectoryStream(dir);
//
//        // create a matcher and return a filter that uses it.
//        FileSystem fs = dir.getFileSystem();
//        final PathMatcher matcher = fs.getPathMatcher("glob:" + glob);
//        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
//            @Override
//            public boolean accept(Path entry)  {
//                return matcher.matches(entry.getFileName());
//            }
//        };
//        return fs.provider().newDirectoryStream(dir, filter);
//    }
//
//    /**
//     * Opens a directory, returning a {@link DirectoryStream} to iterate over
//     * the entries in the directory. The elements returned by the directory
//     * stream's {@link DirectoryStream#iterator iterator} are of type {@code
//     * Path}, each one representing an entry in the directory. The {@code Path}
//     * objects are obtained as if by {@link Path#resolve(Path) resolving} the
//     * name of the directory entry against {@code dir}. The entries returned by
//     * the iterator are filtered by the given {@link DirectoryStream.Filter
//     * filter}.
//     *
//     * <p> When not using the try-with-resources construct, then directory
//     * stream's {@code close} method should be invoked after iteration is
//     * completed so as to free any resources held for the open directory.
//     *
//     * <p> Where the filter terminates due to an uncaught error or runtime
//     * exception then it is propagated to the {@link Iterator#hasNext()
//     * hasNext} or {@link Iterator#next() next} method. Where an {@code
//     * IOException} is thrown, it results in the {@code hasNext} or {@code
//     * next} method throwing a {@link DirectoryIteratorException} with the
//     * {@code IOException} as the cause.
//     *
//     * <p> When an implementation supports operations on entries in the
//     * directory that execute in a race-free manner then the returned directory
//     * stream is a {@link SecureDirectoryStream}.
//     *
//     * <p> <b>Usage Example:</b>
//     * Suppose we want to iterate over the files in a directory that are
//     * larger than 8K.
//     * <pre>
//     *     DirectoryStream.Filter&lt;Path&gt; filter = new DirectoryStream.Filter&lt;Path&gt;() {
//     *         public boolean accept(Path file) throws IOException {
//     *             return (Files.size(file) &gt; 8192L);
//     *         }
//     *     };
//     *     Path dir = ...
//     *     try (DirectoryStream&lt;Path&gt; stream = Files.newDirectoryStream(dir, filter)) {
//     *         :
//     *     }
//     * </pre>
//     *
//     * @param   dir
//     *          the path to the directory
//     * @param   filter
//     *          the directory stream filter
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
//    public static DirectoryStream<Path> newDirectoryStream(Path dir, DirectoryStream.Filter<? super Path> filter) throws IOException {
//        return provider(dir).newDirectoryStream(dir, filter);
//    }
//
//
//    // -- Copying and moving files --
//
//    /**
//     * Copy a file to a target file.
//     *
//     * <p> This method copies a file to the target file with the {@code
//     * options} parameter specifying how the copy is performed. By default, the
//     * copy fails if the target file already exists or is a symbolic link,
//     * except if the source and target are the {@link #isSameFile same} file, in
//     * which case the method completes without copying the file. File attributes
//     * are not required to be copied to the target file. If symbolic links are
//     * supported, and the file is a symbolic link, then the final target of the
//     * link is copied. If the file is a directory then it creates an empty
//     * directory in the target location (entries in the directory are not
//     * copied). This method can be used with the {@link #walkFileTree
//     * walkFileTree} method to copy a directory and all entries in the directory,
//     * or an entire <i>file-tree</i> where required.
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
//     *   <td> {@link StandardCopyOption#COPY_ATTRIBUTES COPY_ATTRIBUTES} </td>
//     *   <td> Attempts to copy the file attributes associated with this file to
//     *     the target file. The exact file attributes that are copied is platform
//     *     and file system dependent and therefore unspecified. Minimally, the
//     *     {@link BasicFileAttributes#lastModifiedTime last-modified-time} is
//     *     copied to the target file if supported by both the source and target
//     *     file stores. Copying of file timestamps may result in precision
//     *     loss. </td>
//     * </tr>
//     * <tr>
//     *   <td> {@link LinkOption#NOFOLLOW_LINKS NOFOLLOW_LINKS} </td>
//     *   <td> Symbolic links are not followed. If the file is a symbolic link,
//     *     then the symbolic link itself, not the target of the link, is copied.
//     *     It is implementation specific if file attributes can be copied to the
//     *     new link. In other words, the {@code COPY_ATTRIBUTES} option may be
//     *     ignored when copying a symbolic link. </td>
//     * </tr>
//     * </table>
//     *
//     * <p> An implementation of this interface may support additional
//     * implementation specific options.
//     *
//     * <p> Copying a file is not an atomic operation. If an {@link IOException}
//     * is thrown, then it is possible that the target file is incomplete or some
//     * of its file attributes have not been copied from the source file. When
//     * the {@code REPLACE_EXISTING} option is specified and the target file
//     * exists, then the target file is replaced. The check for the existence of
//     * the file and the creation of the new file may not be atomic with respect
//     * to other file system activities.
//     *
//     * <p> <b>Usage Example:</b>
//     * Suppose we want to copy a file into a directory, giving it the same file
//     * name as the source file:
//     * <pre>
//     *     Path source = ...
//     *     Path newdir = ...
//     *     Files.copy(source, newdir.resolve(source.getFileName());
//     * </pre>
//     *
//     * @param   source
//     *          the path to the file to copy
//     * @param   target
//     *          the path to the target file (may be associated with a different
//     *          provider to the source path)
//     * @param   options
//     *          options specifying how the copy should be done
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
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the source file, the
//     *          {@link SecurityManager#checkWrite(String) checkWrite} is invoked
//     *          to check write access to the target file. If a symbolic link is
//     *          copied the security manager is invoked to check {@link
//     *          LinkPermission}{@code ("symbolic")}.
//     */
//    public static Path copy(Path source, Path target, CopyOption... options) throws IOException {
//        FileSystemProvider provider = provider(source);
//        if (provider(target) == provider) {
//            // same provider
//            provider.copy(source, target, options);
//        } else {
//            // different providers
//            CopyMoveHelper.copyToForeignTarget(source, target, options);
//        }
//        return target;
//    }
//
//   
//
//    // -- Miscellenous --
//
//    
//
//    /**
//     * Returns the {@link FileStore} representing the file store where a file
//     * is located.
//     *
//     * <p> Once a reference to the {@code FileStore} is obtained it is
//     * implementation specific if operations on the returned {@code FileStore},
//     * or {@link FileStoreAttributeView} objects obtained from it, continue
//     * to depend on the existence of the file. In particular the behavior is not
//     * defined for the case that the file is deleted or moved to a different
//     * file store.
//     *
//     * @param   path
//     *          the path to the file
//     *
//     * @return  the file store where the file is stored
//     *
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the file, and in
//     *          addition it checks {@link RuntimePermission}<tt>
//     *          ("getFileStoreAttributes")</tt>
//     */
//    public static FileStore getFileStore(Path path) throws IOException {
//        return provider(path).getFileStore(path);
//    }
//
//    /**
//     * Tells whether or not a file is considered <em>hidden</em>. The exact
//     * definition of hidden is platform or provider dependent. On UNIX for
//     * example a file is considered to be hidden if its name begins with a
//     * period character ('.'). On Windows a file is considered hidden if it
//     * isn't a directory and the DOS {@link DosFileAttributes#isHidden hidden}
//     * attribute is set.
//     *
//     * <p> Depending on the implementation this method may require to access
//     * the file system to determine if the file is considered hidden.
//     *
//     * @param   path
//     *          the path to the file to test
//     *
//     * @return  {@code true} if the file is considered hidden
//     *
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the file.
//     */
//    public static boolean isHidden(Path path) throws IOException {
//        return provider(path).isHidden(path);
//    }
//
//
//    /**
//     * Probes the content type of a file.
//     *
//     * <p> This method uses the installed {@link FileTypeDetector} implementations
//     * to probe the given file to determine its content type. Each file type
//     * detector's {@link FileTypeDetector#probeContentType probeContentType} is
//     * invoked, in turn, to probe the file type. If the file is recognized then
//     * the content type is returned. If the file is not recognized by any of the
//     * installed file type detectors then a system-default file type detector is
//     * invoked to guess the content type.
//     *
//     * <p> A given invocation of the Java virtual machine maintains a system-wide
//     * list of file type detectors. Installed file type detectors are loaded
//     * using the service-provider loading facility defined by the {@link ServiceLoader}
//     * class. Installed file type detectors are loaded using the system class
//     * loader. If the system class loader cannot be found then the extension class
//     * loader is used; If the extension class loader cannot be found then the
//     * bootstrap class loader is used. File type detectors are typically installed
//     * by placing them in a JAR file on the application class path or in the
//     * extension directory, the JAR file contains a provider-configuration file
//     * named {@code java.nio.file.spi.FileTypeDetector} in the resource directory
//     * {@code META-INF/services}, and the file lists one or more fully-qualified
//     * names of concrete subclass of {@code FileTypeDetector } that have a zero
//     * argument constructor. If the process of locating or instantiating the
//     * installed file type detectors fails then an unspecified error is thrown.
//     * The ordering that installed providers are located is implementation
//     * specific.
//     *
//     * <p> The return value of this method is the string form of the value of a
//     * Multipurpose Internet Mail Extension (MIME) content type as
//     * defined by <a href="http://www.ietf.org/rfc/rfc2045.txt"><i>RFC&nbsp;2045:
//     * Multipurpose Internet Mail Extensions (MIME) Part One: Format of Internet
//     * Message Bodies</i></a>. The string is guaranteed to be parsable according
//     * to the grammar in the RFC.
//     *
//     * @param   path
//     *          the path to the file to probe
//     *
//     * @return  The content type of the file, or {@code null} if the content
//     *          type cannot be determined
//     *
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          If a security manager is installed and it denies an unspecified
//     *          permission required by a file type detector implementation.
//     */
//    public static String probeContentType(Path path) throws IOException {
//        // try installed file type detectors
//        for (FileTypeDetector detector: FileTypeDetectors.installeDetectors) {
//            String result = detector.probeContentType(path);
//            if (result != null)
//                return result;
//        }
//
//        // fallback to default
//        return FileTypeDetectors.defaultFileTypeDetector.probeContentType(path);
//    }
//
//    // -- File Attributes --
//
//    /**
//     * Returns a file attribute view of a given type.
//     *
//     * <p> A file attribute view provides a read-only or updatable view of a
//     * set of file attributes. This method is intended to be used where the file
//     * attribute view defines type-safe methods to read or update the file
//     * attributes. The {@code type} parameter is the type of the attribute view
//     * required and the method returns an instance of that type if supported.
//     * The {@link BasicFileAttributeView} type supports access to the basic
//     * attributes of a file. Invoking this method to select a file attribute
//     * view of that type will always return an instance of that class.
//     *
//     * <p> The {@code options} array may be used to indicate how symbolic links
//     * are handled by the resulting file attribute view for the case that the
//     * file is a symbolic link. By default, symbolic links are followed. If the
//     * option {@link LinkOption#NOFOLLOW_LINKS NOFOLLOW_LINKS} is present then
//     * symbolic links are not followed. This option is ignored by implementations
//     * that do not support symbolic links.
//     *
//     * <p> <b>Usage Example:</b>
//     * Suppose we want read or set a file's ACL, if supported:
//     * <pre>
//     *     Path path = ...
//     *     AclFileAttributeView view = Files.getFileAttributeView(path, AclFileAttributeView.class);
//     *     if (view != null) {
//     *         List&lt;AclEntry&gt; acl = view.getAcl();
//     *         :
//     *     }
//     * </pre>
//     *
//     * @param   <V>
//     *          The {@code FileAttributeView} type
//     * @param   path
//     *          the path to the file
//     * @param   type
//     *          the {@code Class} object corresponding to the file attribute view
//     * @param   options
//     *          options indicating how symbolic links are handled
//     *
//     * @return  a file attribute view of the specified type, or {@code null} if
//     *          the attribute view type is not available
//     */
//    public static <V extends FileAttributeView> V getFileAttributeView(Path path,
//                                                                       Class<V> type,
//                                                                       LinkOption... options)
//    {
//        return provider(path).getFileAttributeView(path, type, options);
//    }
//
//    /**
//     * Reads a file's attributes as a bulk operation.
//     *
//     * <p> The {@code type} parameter is the type of the attributes required
//     * and this method returns an instance of that type if supported. All
//     * implementations support a basic set of file attributes and so invoking
//     * this method with a  {@code type} parameter of {@code
//     * BasicFileAttributes.class} will not throw {@code
//     * UnsupportedOperationException}.
//     *
//     * <p> The {@code options} array may be used to indicate how symbolic links
//     * are handled for the case that the file is a symbolic link. By default,
//     * symbolic links are followed and the file attribute of the final target
//     * of the link is read. If the option {@link LinkOption#NOFOLLOW_LINKS
//     * NOFOLLOW_LINKS} is present then symbolic links are not followed.
//     *
//     * <p> It is implementation specific if all file attributes are read as an
//     * atomic operation with respect to other file system operations.
//     *
//     * <p> <b>Usage Example:</b>
//     * Suppose we want to read a file's attributes in bulk:
//     * <pre>
//     *    Path path = ...
//     *    BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
//     * </pre>
//     * Alternatively, suppose we want to read file's POSIX attributes without
//     * following symbolic links:
//     * <pre>
//     *    PosixFileAttributes attrs = Files.readAttributes(path, PosixFileAttributes.class, NOFOLLOW_LINKS);
//     * </pre>
//     *
//     * @param   <A>
//     *          The {@code BasicFileAttributes} type
//     * @param   path
//     *          the path to the file
//     * @param   type
//     *          the {@code Class} of the file attributes required
//     *          to read
//     * @param   options
//     *          options indicating how symbolic links are handled
//     *
//     * @return  the file attributes
//     *
//     * @throws  UnsupportedOperationException
//     *          if an attributes of the given type are not supported
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, a security manager is
//     *          installed, its {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the file. If this
//     *          method is invoked to read security sensitive attributes then the
//     *          security manager may be invoke to check for additional permissions.
//     */
//    public static <A extends BasicFileAttributes> A readAttributes(Path path,
//                                                                   Class<A> type,
//                                                                   LinkOption... options)
//        throws IOException
//    {
//        return provider(path).readAttributes(path, type, options);
//    }
//
//    /**
//     * Sets the value of a file attribute.
//     *
//     * <p> The {@code attribute} parameter identifies the attribute to be set
//     * and takes the form:
//     * <blockquote>
//     * [<i>view-name</i><b>:</b>]<i>attribute-name</i>
//     * </blockquote>
//     * where square brackets [...] delineate an optional component and the
//     * character {@code ':'} stands for itself.
//     *
//     * <p> <i>view-name</i> is the {@link FileAttributeView#name name} of a {@link
//     * FileAttributeView} that identifies a set of file attributes. If not
//     * specified then it defaults to {@code "basic"}, the name of the file
//     * attribute view that identifies the basic set of file attributes common to
//     * many file systems. <i>attribute-name</i> is the name of the attribute
//     * within the set.
//     *
//     * <p> The {@code options} array may be used to indicate how symbolic links
//     * are handled for the case that the file is a symbolic link. By default,
//     * symbolic links are followed and the file attribute of the final target
//     * of the link is set. If the option {@link LinkOption#NOFOLLOW_LINKS
//     * NOFOLLOW_LINKS} is present then symbolic links are not followed.
//     *
//     * <p> <b>Usage Example:</b>
//     * Suppose we want to set the DOS "hidden" attribute:
//     * <pre>
//     *    Path path = ...
//     *    Files.setAttribute(path, "dos:hidden", true);
//     * </pre>
//     *
//     * @param   path
//     *          the path to the file
//     * @param   attribute
//     *          the attribute to set
//     * @param   value
//     *          the attribute value
//     * @param   options
//     *          options indicating how symbolic links are handled
//     *
//     * @return  the {@code path} parameter
//     *
//     * @throws  UnsupportedOperationException
//     *          if the attribute view is not available
//     * @throws  IllegalArgumentException
//     *          if the attribute name is not specified, or is not recognized, or
//     *          the attribute value is of the correct type but has an
//     *          inappropriate value
//     * @throws  ClassCastException
//     *          if the attribute value is not of the expected type or is a
//     *          collection containing elements that are not of the expected
//     *          type
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, its {@link SecurityManager#checkWrite(String) checkWrite}
//     *          method denies write access to the file. If this method is invoked
//     *          to set security sensitive attributes then the security manager
//     *          may be invoked to check for additional permissions.
//     */
//    public static Path setAttribute(Path path, String attribute, Object value,
//                                    LinkOption... options)
//        throws IOException
//    {
//        provider(path).setAttribute(path, attribute, value, options);
//        return path;
//    }
//
//    /**
//     * Reads the value of a file attribute.
//     *
//     * <p> The {@code attribute} parameter identifies the attribute to be read
//     * and takes the form:
//     * <blockquote>
//     * [<i>view-name</i><b>:</b>]<i>attribute-name</i>
//     * </blockquote>
//     * where square brackets [...] delineate an optional component and the
//     * character {@code ':'} stands for itself.
//     *
//     * <p> <i>view-name</i> is the {@link FileAttributeView#name name} of a {@link
//     * FileAttributeView} that identifies a set of file attributes. If not
//     * specified then it defaults to {@code "basic"}, the name of the file
//     * attribute view that identifies the basic set of file attributes common to
//     * many file systems. <i>attribute-name</i> is the name of the attribute.
//     *
//     * <p> The {@code options} array may be used to indicate how symbolic links
//     * are handled for the case that the file is a symbolic link. By default,
//     * symbolic links are followed and the file attribute of the final target
//     * of the link is read. If the option {@link LinkOption#NOFOLLOW_LINKS
//     * NOFOLLOW_LINKS} is present then symbolic links are not followed.
//     *
//     * <p> <b>Usage Example:</b>
//     * Suppose we require the user ID of the file owner on a system that
//     * supports a "{@code unix}" view:
//     * <pre>
//     *    Path path = ...
//     *    int uid = (Integer)Files.getAttribute(path, "unix:uid");
//     * </pre>
//     *
//     * @param   path
//     *          the path to the file
//     * @param   attribute
//     *          the attribute to read
//     * @param   options
//     *          options indicating how symbolic links are handled
//     *
//     * @return  the attribute value
//     *
//     * @throws  UnsupportedOperationException
//     *          if the attribute view is not available
//     * @throws  IllegalArgumentException
//     *          if the attribute name is not specified or is not recognized
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, its {@link SecurityManager#checkRead(String) checkRead}
//     *          method denies read access to the file. If this method is invoked
//     *          to read security sensitive attributes then the security manager
//     *          may be invoked to check for additional permissions.
//     */
//    public static Object getAttribute(Path path, String attribute,
//                                      LinkOption... options)
//        throws IOException
//    {
//        // only one attribute should be read
//        if (attribute.indexOf('*') >= 0 || attribute.indexOf(',') >= 0)
//            throw new IllegalArgumentException(attribute);
//        Map<String,Object> map = readAttributes(path, attribute, options);
//        assert map.size() == 1;
//        String name;
//        int pos = attribute.indexOf(':');
//        if (pos == -1) {
//            name = attribute;
//        } else {
//            name = (pos == attribute.length()) ? "" : attribute.substring(pos+1);
//        }
//        return map.get(name);
//    }
//
//    /**
//     * Reads a set of file attributes as a bulk operation.
//     *
//     * <p> The {@code attributes} parameter identifies the attributes to be read
//     * and takes the form:
//     * <blockquote>
//     * [<i>view-name</i><b>:</b>]<i>attribute-list</i>
//     * </blockquote>
//     * where square brackets [...] delineate an optional component and the
//     * character {@code ':'} stands for itself.
//     *
//     * <p> <i>view-name</i> is the {@link FileAttributeView#name name} of a {@link
//     * FileAttributeView} that identifies a set of file attributes. If not
//     * specified then it defaults to {@code "basic"}, the name of the file
//     * attribute view that identifies the basic set of file attributes common to
//     * many file systems.
//     *
//     * <p> The <i>attribute-list</i> component is a comma separated list of
//     * zero or more names of attributes to read. If the list contains the value
//     * {@code "*"} then all attributes are read. Attributes that are not supported
//     * are ignored and will not be present in the returned map. It is
//     * implementation specific if all attributes are read as an atomic operation
//     * with respect to other file system operations.
//     *
//     * <p> The following examples demonstrate possible values for the {@code
//     * attributes} parameter:
//     *
//     * <blockquote>
//     * <table border="0" summary="Possible values">
//     * <tr>
//     *   <td> {@code "*"} </td>
//     *   <td> Read all {@link BasicFileAttributes basic-file-attributes}. </td>
//     * </tr>
//     * <tr>
//     *   <td> {@code "size,lastModifiedTime,lastAccessTime"} </td>
//     *   <td> Reads the file size, last modified time, and last access time
//     *     attributes. </td>
//     * </tr>
//     * <tr>
//     *   <td> {@code "posix:*"} </td>
//     *   <td> Read all {@link PosixFileAttributes POSIX-file-attributes}. </td>
//     * </tr>
//     * <tr>
//     *   <td> {@code "posix:permissions,owner,size"} </td>
//     *   <td> Reads the POSX file permissions, owner, and file size. </td>
//     * </tr>
//     * </table>
//     * </blockquote>
//     *
//     * <p> The {@code options} array may be used to indicate how symbolic links
//     * are handled for the case that the file is a symbolic link. By default,
//     * symbolic links are followed and the file attribute of the final target
//     * of the link is read. If the option {@link LinkOption#NOFOLLOW_LINKS
//     * NOFOLLOW_LINKS} is present then symbolic links are not followed.
//     *
//     * @param   path
//     *          the path to the file
//     * @param   attributes
//     *          the attributes to read
//     * @param   options
//     *          options indicating how symbolic links are handled
//     *
//     * @return  a map of the attributes returned; The map's keys are the
//     *          attribute names, its values are the attribute values
//     *
//     * @throws  UnsupportedOperationException
//     *          if the attribute view is not available
//     * @throws  IllegalArgumentException
//     *          if no attributes are specified or an unrecognized attributes is
//     *          specified
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, its {@link SecurityManager#checkRead(String) checkRead}
//     *          method denies read access to the file. If this method is invoked
//     *          to read security sensitive attributes then the security manager
//     *          may be invoke to check for additional permissions.
//     */
//    public static Map<String,Object> readAttributes(Path path, String attributes,
//                                                    LinkOption... options)
//        throws IOException
//    {
//        return provider(path).readAttributes(path, attributes, options);
//    }
//
//    /**
//     * Returns a file's POSIX file permissions.
//     *
//     * <p> The {@code path} parameter is associated with a {@code FileSystem}
//     * that supports the {@link PosixFileAttributeView}. This attribute view
//     * provides access to file attributes commonly associated with files on file
//     * systems used by operating systems that implement the Portable Operating
//     * System Interface (POSIX) family of standards.
//     *
//     * <p> The {@code options} array may be used to indicate how symbolic links
//     * are handled for the case that the file is a symbolic link. By default,
//     * symbolic links are followed and the file attribute of the final target
//     * of the link is read. If the option {@link LinkOption#NOFOLLOW_LINKS
//     * NOFOLLOW_LINKS} is present then symbolic links are not followed.
//     *
//     * @param   path
//     *          the path to the file
//     * @param   options
//     *          options indicating how symbolic links are handled
//     *
//     * @return  the file permissions
//     *
//     * @throws  UnsupportedOperationException
//     *          if the associated file system does not support the {@code
//     *          PosixFileAttributeView}
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, a security manager is
//     *          installed, and it denies {@link RuntimePermission}<tt>("accessUserInformation")</tt>
//     *          or its {@link SecurityManager#checkRead(String) checkRead} method
//     *          denies read access to the file.
//     */
//    public static Set<PosixFilePermission> getPosixFilePermissions(Path path,
//                                                                   LinkOption... options)
//        throws IOException
//    {
//        return readAttributes(path, PosixFileAttributes.class, options).permissions();
//    }
//
//    /**
//     * Sets a file's POSIX permissions.
//     *
//     * <p> The {@code path} parameter is associated with a {@code FileSystem}
//     * that supports the {@link PosixFileAttributeView}. This attribute view
//     * provides access to file attributes commonly associated with files on file
//     * systems used by operating systems that implement the Portable Operating
//     * System Interface (POSIX) family of standards.
//     *
//     * @param   path
//     *          The path to the file
//     * @param   perms
//     *          The new set of permissions
//     *
//     * @return  The path
//     *
//     * @throws  UnsupportedOperationException
//     *          if the associated file system does not support the {@code
//     *          PosixFileAttributeView}
//     * @throws  ClassCastException
//     *          if the sets contains elements that are not of type {@code
//     *          PosixFilePermission}
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, it denies {@link RuntimePermission}<tt>("accessUserInformation")</tt>
//     *          or its {@link SecurityManager#checkWrite(String) checkWrite}
//     *          method denies write access to the file.
//     */
//    public static Path setPosixFilePermissions(Path path,
//                                               Set<PosixFilePermission> perms)
//        throws IOException
//    {
//        PosixFileAttributeView view =
//            getFileAttributeView(path, PosixFileAttributeView.class);
//        if (view == null)
//            throw new UnsupportedOperationException();
//        view.setPermissions(perms);
//        return path;
//    }
//
//    /**
//     * Returns the owner of a file.
//     *
//     * <p> The {@code path} parameter is associated with a file system that
//     * supports {@link FileOwnerAttributeView}. This file attribute view provides
//     * access to a file attribute that is the owner of the file.
//     *
//     * @param   path
//     *          The path to the file
//     * @param   options
//     *          options indicating how symbolic links are handled
//     *
//     * @return  A user principal representing the owner of the file
//     *
//     * @throws  UnsupportedOperationException
//     *          if the associated file system does not support the {@code
//     *          FileOwnerAttributeView}
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, it denies {@link RuntimePermission}<tt>("accessUserInformation")</tt>
//     *          or its {@link SecurityManager#checkRead(String) checkRead} method
//     *          denies read access to the file.
//     */
//    public static UserPrincipal getOwner(Path path, LinkOption... options) throws IOException {
//        FileOwnerAttributeView view =
//            getFileAttributeView(path, FileOwnerAttributeView.class, options);
//        if (view == null)
//            throw new UnsupportedOperationException();
//        return view.getOwner();
//    }
//
//    /**
//     * Updates the file owner.
//     *
//     * <p> The {@code path} parameter is associated with a file system that
//     * supports {@link FileOwnerAttributeView}. This file attribute view provides
//     * access to a file attribute that is the owner of the file.
//     *
//     * <p> <b>Usage Example:</b>
//     * Suppose we want to make "joe" the owner of a file:
//     * <pre>
//     *     Path path = ...
//     *     UserPrincipalLookupService lookupService =
//     *         provider(path).getUserPrincipalLookupService();
//     *     UserPrincipal joe = lookupService.lookupPrincipalByName("joe");
//     *     Files.setOwner(path, joe);
//     * </pre>
//     *
//     * @param   path
//     *          The path to the file
//     * @param   owner
//     *          The new file owner
//     *
//     * @return  The path
//     *
//     * @throws  UnsupportedOperationException
//     *          if the associated file system does not support the {@code
//     *          FileOwnerAttributeView}
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, it denies {@link RuntimePermission}<tt>("accessUserInformation")</tt>
//     *          or its {@link SecurityManager#checkWrite(String) checkWrite}
//     *          method denies write access to the file.
//     *
//     * @see FileSystem#getUserPrincipalLookupService
//     * @see java.nio.file.attribute.UserPrincipalLookupService
//     */
//    public static Path setOwner(Path path, UserPrincipal owner)
//        throws IOException
//    {
//        FileOwnerAttributeView view =
//            getFileAttributeView(path, FileOwnerAttributeView.class);
//        if (view == null)
//            throw new UnsupportedOperationException();
//        view.setOwner(owner);
//        return path;
//    }
//
//
//   
//
//
//    /**
//     * Returns a file's last modified time.
//     *
//     * <p> The {@code options} array may be used to indicate how symbolic links
//     * are handled for the case that the file is a symbolic link. By default,
//     * symbolic links are followed and the file attribute of the final target
//     * of the link is read. If the option {@link LinkOption#NOFOLLOW_LINKS
//     * NOFOLLOW_LINKS} is present then symbolic links are not followed.
//     *
//     * @param   path
//     *          the path to the file
//     * @param   options
//     *          options indicating how symbolic links are handled
//     *
//     * @return  a {@code FileTime} representing the time the file was last
//     *          modified, or an implementation specific default when a time
//     *          stamp to indicate the time of last modification is not supported
//     *          by the file system
//     *
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, its {@link SecurityManager#checkRead(String) checkRead}
//     *          method denies read access to the file.
//     *
//     * @see BasicFileAttributes#lastModifiedTime
//     */
//    public static FileTime getLastModifiedTime(Path path, LinkOption... options)
//        throws IOException
//    {
//        return readAttributes(path, BasicFileAttributes.class, options).lastModifiedTime();
//    }
//
//    /**
//     * Updates a file's last modified time attribute. The file time is converted
//     * to the epoch and precision supported by the file system. Converting from
//     * finer to coarser granularities result in precision loss. The behavior of
//     * this method when attempting to set the last modified time when it is not
//     * supported by the file system or is outside the range supported by the
//     * underlying file store is not defined. It may or not fail by throwing an
//     * {@code IOException}.
//     *
//     * <p> <b>Usage Example:</b>
//     * Suppose we want to set the last modified time to the current time:
//     * <pre>
//     *    Path path = ...
//     *    FileTime now = FileTime.fromMillis(System.currentTimeMillis());
//     *    Files.setLastModifiedTime(path, now);
//     * </pre>
//     *
//     * @param   path
//     *          the path to the file
//     * @param   time
//     *          the new last modified time
//     *
//     * @return  the path
//     *
//     * @throws  IOException
//     *          if an I/O error occurs
//     * @throws  SecurityException
//     *          In the case of the default provider, the security manager's {@link
//     *          SecurityManager#checkWrite(String) checkWrite} method is invoked
//     *          to check write access to file
//     *
//     * @see BasicFileAttributeView#setTimes
//     */
//    public static Path setLastModifiedTime(Path path, FileTime time)
//        throws IOException
//    {
//        getFileAttributeView(path, BasicFileAttributeView.class)
//            .setTimes(time, null, null);
//        return path;
//    }
//
//    
//
//    // -- Accessibility --
//
//
//
//    /**
//     * Tests whether a file is readable. This method checks that a file exists
//     * and that this Java virtual machine has appropriate privileges that would
//     * allow it open the file for reading. Depending on the implementation, this
//     * method may require to read file permissions, access control lists, or
//     * other file attributes in order to check the effective access to the file.
//     * Consequently, this method may not be atomic with respect to other file
//     * system operations.
//     *
//     * <p> Note that the result of this method is immediately outdated, there is
//     * no guarantee that a subsequent attempt to open the file for reading will
//     * succeed (or even that it will access the same file). Care should be taken
//     * when using this method in security sensitive applications.
//     *
//     * @param   path
//     *          the path to the file to check
//     *
//     * @return  {@code true} if the file exists and is readable; {@code false}
//     *          if the file does not exist, read access would be denied because
//     *          the Java virtual machine has insufficient privileges, or access
//     *          cannot be determined
//     *
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          is invoked to check read access to the file.
//     */
//    public static boolean isReadable(Path path) {
//        return isAccessible(path, AccessMode.READ);
//    }
//
//    /**
//     * Tests whether a file is writable. This method checks that a file exists
//     * and that this Java virtual machine has appropriate privileges that would
//     * allow it open the file for writing. Depending on the implementation, this
//     * method may require to read file permissions, access control lists, or
//     * other file attributes in order to check the effective access to the file.
//     * Consequently, this method may not be atomic with respect to other file
//     * system operations.
//     *
//     * <p> Note that result of this method is immediately outdated, there is no
//     * guarantee that a subsequent attempt to open the file for writing will
//     * succeed (or even that it will access the same file). Care should be taken
//     * when using this method in security sensitive applications.
//     *
//     * @param   path
//     *          the path to the file to check
//     *
//     * @return  {@code true} if the file exists and is writable; {@code false}
//     *          if the file does not exist, write access would be denied because
//     *          the Java virtual machine has insufficient privileges, or access
//     *          cannot be determined
//     *
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkWrite(String) checkWrite}
//     *          is invoked to check write access to the file.
//     */
//    public static boolean isWritable(Path path) {
//        return isAccessible(path, AccessMode.WRITE);
//    }
//
//    /**
//     * Tests whether a file is executable. This method checks that a file exists
//     * and that this Java virtual machine has appropriate privileges to {@link
//     * Runtime#exec execute} the file. The semantics may differ when checking
//     * access to a directory. For example, on UNIX systems, checking for
//     * execute access checks that the Java virtual machine has permission to
//     * search the directory in order to access file or subdirectories.
//     *
//     * <p> Depending on the implementation, this method may require to read file
//     * permissions, access control lists, or other file attributes in order to
//     * check the effective access to the file. Consequently, this method may not
//     * be atomic with respect to other file system operations.
//     *
//     * <p> Note that the result of this method is immediately outdated, there is
//     * no guarantee that a subsequent attempt to execute the file will succeed
//     * (or even that it will access the same file). Care should be taken when
//     * using this method in security sensitive applications.
//     *
//     * @param   path
//     *          the path to the file to check
//     *
//     * @return  {@code true} if the file exists and is executable; {@code false}
//     *          if the file does not exist, execute access would be denied because
//     *          the Java virtual machine has insufficient privileges, or access
//     *          cannot be determined
//     *
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkExec(String)
//     *          checkExec} is invoked to check execute access to the file.
//     */
//    public static boolean isExecutable(Path path) {
//        return isAccessible(path, AccessMode.EXECUTE);
//    }
//
//    // -- Recursive operations --
//
//    /**
//     * Walks a file tree.
//     *
//     * <p> This method walks a file tree rooted at a given starting file. The
//     * file tree traversal is <em>depth-first</em> with the given {@link
//     * FileVisitor} invoked for each file encountered. File tree traversal
//     * completes when all accessible files in the tree have been visited, or a
//     * visit method returns a result of {@link FileVisitResult#TERMINATE
//     * TERMINATE}. Where a visit method terminates due an {@code IOException},
//     * an uncaught error, or runtime exception, then the traversal is terminated
//     * and the error or exception is propagated to the caller of this method.
//     *
//     * <p> For each file encountered this method attempts to read its {@link
//     * java.nio.file.attribute.BasicFileAttributes}. If the file is not a
//     * directory then the {@link FileVisitor#visitFile visitFile} method is
//     * invoked with the file attributes. If the file attributes cannot be read,
//     * due to an I/O exception, then the {@link FileVisitor#visitFileFailed
//     * visitFileFailed} method is invoked with the I/O exception.
//     *
//     * <p> Where the file is a directory, and the directory could not be opened,
//     * then the {@code visitFileFailed} method is invoked with the I/O exception,
//     * after which, the file tree walk continues, by default, at the next
//     * <em>sibling</em> of the directory.
//     *
//     * <p> Where the directory is opened successfully, then the entries in the
//     * directory, and their <em>descendants</em> are visited. When all entries
//     * have been visited, or an I/O error occurs during iteration of the
//     * directory, then the directory is closed and the visitor's {@link
//     * FileVisitor#postVisitDirectory postVisitDirectory} method is invoked.
//     * The file tree walk then continues, by default, at the next <em>sibling</em>
//     * of the directory.
//     *
//     * <p> By default, symbolic links are not automatically followed by this
//     * method. If the {@code options} parameter contains the {@link
//     * FileVisitOption#FOLLOW_LINKS FOLLOW_LINKS} option then symbolic links are
//     * followed. When following links, and the attributes of the target cannot
//     * be read, then this method attempts to get the {@code BasicFileAttributes}
//     * of the link. If they can be read then the {@code visitFile} method is
//     * invoked with the attributes of the link (otherwise the {@code visitFileFailed}
//     * method is invoked as specified above).
//     *
//     * <p> If the {@code options} parameter contains the {@link
//     * FileVisitOption#FOLLOW_LINKS FOLLOW_LINKS} option then this method keeps
//     * track of directories visited so that cycles can be detected. A cycle
//     * arises when there is an entry in a directory that is an ancestor of the
//     * directory. Cycle detection is done by recording the {@link
//     * java.nio.file.attribute.BasicFileAttributes#fileKey file-key} of directories,
//     * or if file keys are not available, by invoking the {@link #isSameFile
//     * isSameFile} method to test if a directory is the same file as an
//     * ancestor. When a cycle is detected it is treated as an I/O error, and the
//     * {@link FileVisitor#visitFileFailed visitFileFailed} method is invoked with
//     * an instance of {@link FileSystemLoopException}.
//     *
//     * <p> The {@code maxDepth} parameter is the maximum number of levels of
//     * directories to visit. A value of {@code 0} means that only the starting
//     * file is visited, unless denied by the security manager. A value of
//     * {@link Integer#MAX_VALUE MAX_VALUE} may be used to indicate that all
//     * levels should be visited. The {@code visitFile} method is invoked for all
//     * files, including directories, encountered at {@code maxDepth}, unless the
//     * basic file attributes cannot be read, in which case the {@code
//     * visitFileFailed} method is invoked.
//     *
//     * <p> If a visitor returns a result of {@code null} then {@code
//     * NullPointerException} is thrown.
//     *
//     * <p> When a security manager is installed and it denies access to a file
//     * (or directory), then it is ignored and the visitor is not invoked for
//     * that file (or directory).
//     *
//     * @param   start
//     *          the starting file
//     * @param   options
//     *          options to configure the traversal
//     * @param   maxDepth
//     *          the maximum number of directory levels to visit
//     * @param   visitor
//     *          the file visitor to invoke for each file
//     *
//     * @return  the starting file
//     *
//     * @throws  IllegalArgumentException
//     *          if the {@code maxDepth} parameter is negative
//     * @throws  SecurityException
//     *          If the security manager denies access to the starting file.
//     *          In the case of the default provider, the {@link
//     *          SecurityManager#checkRead(String) checkRead} method is invoked
//     *          to check read access to the directory.
//     * @throws  IOException
//     *          if an I/O error is thrown by a visitor method
//     */
//    public static Path walkFileTree(Path start,
//                                    Set<FileVisitOption> options,
//                                    int maxDepth,
//                                    FileVisitor<? super Path> visitor)
//        throws IOException
//    {
//        /**
//         * Create a FileTreeWalker to walk the file tree, invoking the visitor
//         * for each event.
//         */
//        try (FileTreeWalker walker = new FileTreeWalker(options, maxDepth)) {
//            FileTreeWalker.Event ev = walker.walk(start);
//            do {
//                FileVisitResult result;
//                switch (ev.type()) {
//                    case ENTRY :
//                        IOException ioe = ev.ioeException();
//                        if (ioe == null) {
//                            assert ev.attributes() != null;
//                            result = visitor.visitFile(ev.file(), ev.attributes());
//                        } else {
//                            result = visitor.visitFileFailed(ev.file(), ioe);
//                        }
//                        break;
//
//                    case START_DIRECTORY :
//                        result = visitor.preVisitDirectory(ev.file(), ev.attributes());
//
//                        // if SKIP_SIBLINGS and SKIP_SUBTREE is returned then
//                        // there shouldn't be any more events for the current
//                        // directory.
//                        if (result == FileVisitResult.SKIP_SUBTREE ||
//                            result == FileVisitResult.SKIP_SIBLINGS)
//                            walker.pop();
//                        break;
//
//                    case END_DIRECTORY :
//                        result = visitor.postVisitDirectory(ev.file(), ev.ioeException());
//
//                        // SKIP_SIBLINGS is a no-op for postVisitDirectory
//                        if (result == FileVisitResult.SKIP_SIBLINGS)
//                            result = FileVisitResult.CONTINUE;
//                        break;
//
//                    default :
//                        throw new AssertionError("Should not get here");
//                }
//
//                if (Objects.requireNonNull(result) != FileVisitResult.CONTINUE) {
//                    if (result == FileVisitResult.TERMINATE) {
//                        break;
//                    } else if (result == FileVisitResult.SKIP_SIBLINGS) {
//                        walker.skipRemainingSiblings();
//                    }
//                }
//                ev = walker.next();
//            } while (ev != null);
//        }
//
//        return start;
//    }
//
//    /**
//     * Walks a file tree.
//     *
//     * <p> This method works as if invoking it were equivalent to evaluating the
//     * expression:
//     * <blockquote><pre>
//     * walkFileTree(start, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, visitor)
//     * </pre></blockquote>
//     * In other words, it does not follow symbolic links, and visits all levels
//     * of the file tree.
//     *
//     * @param   start
//     *          the starting file
//     * @param   visitor
//     *          the file visitor to invoke for each file
//     *
//     * @return  the starting file
//     *
//     * @throws  SecurityException
//     *          If the security manager denies access to the starting file.
//     *          In the case of the default provider, the {@link
//     *          SecurityManager#checkRead(String) checkRead} method is invoked
//     *          to check read access to the directory.
//     * @throws  IOException
//     *          if an I/O error is thrown by a visitor method
//     */
//    public static Path walkFileTree(Path start, FileVisitor<? super Path> visitor)
//        throws IOException
//    {
//        return walkFileTree(start,
//                            EnumSet.noneOf(FileVisitOption.class),
//                            Integer.MAX_VALUE,
//                            visitor);
//    }
//
//
//    // -- Utility methods for simple usages --
//
//    /**
//     * Opens a file for reading, returning a {@code BufferedReader} that may be
//     * used to read text from the file in an efficient manner. Bytes from the
//     * file are decoded into characters using the specified charset. Reading
//     * commences at the beginning of the file.
//     *
//     * <p> The {@code Reader} methods that read from the file throw {@code
//     * IOException} if a malformed or unmappable byte sequence is read.
//     *
//     * @param   path
//     *          the path to the file
//     * @param   cs
//     *          the charset to use for decoding
//     *
//     * @return  a new buffered reader, with default buffer size, to read text
//     *          from the file
//     *
//     * @throws  IOException
//     *          if an I/O error occurs opening the file
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the file.
//     *
//     * @see #readAllLines
//     */
//    public static BufferedReader newBufferedReader(Path path, Charset cs)
//        throws IOException
//    {
//        CharsetDecoder decoder = cs.newDecoder();
//        Reader reader = new InputStreamReader(newInputStream(path), decoder);
//        return new BufferedReader(reader);
//    }
//
//    /**
//     * Opens a file for reading, returning a {@code BufferedReader} to read text
//     * from the file in an efficient manner. Bytes from the file are decoded into
//     * characters using the {@link StandardCharsets#UTF_8 UTF-8} {@link Charset
//     * charset}.
//     *
//     * <p> This method works as if invoking it were equivalent to evaluating the
//     * expression:
//     * <pre>{@code
//     * Files.newBufferedReader(path, StandardCharsets.UTF_8)
//     * }</pre>
//     *
//     * @param   path
//     *          the path to the file
//     *
//     * @return  a new buffered reader, with default buffer size, to read text
//     *          from the file
//     *
//     * @throws  IOException
//     *          if an I/O error occurs opening the file
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the file.
//     *
//     * @since 1.8
//     */
//    public static BufferedReader newBufferedReader(Path path) throws IOException {
//        return newBufferedReader(path, StandardCharsets.UTF_8);
//    }
//
//    /**
//     * Opens or creates a file for writing, returning a {@code BufferedWriter}
//     * that may be used to write text to the file in an efficient manner.
//     * The {@code options} parameter specifies how the the file is created or
//     * opened. If no options are present then this method works as if the {@link
//     * StandardOpenOption#CREATE CREATE}, {@link
//     * StandardOpenOption#TRUNCATE_EXISTING TRUNCATE_EXISTING}, and {@link
//     * StandardOpenOption#WRITE WRITE} options are present. In other words, it
//     * opens the file for writing, creating the file if it doesn't exist, or
//     * initially truncating an existing {@link #isRegularFile regular-file} to
//     * a size of {@code 0} if it exists.
//     *
//     * <p> The {@code Writer} methods to write text throw {@code IOException}
//     * if the text cannot be encoded using the specified charset.
//     *
//     * @param   path
//     *          the path to the file
//     * @param   cs
//     *          the charset to use for encoding
//     * @param   options
//     *          options specifying how the file is opened
//     *
//     * @return  a new buffered writer, with default buffer size, to write text
//     *          to the file
//     *
//     * @throws  IOException
//     *          if an I/O error occurs opening or creating the file
//     * @throws  UnsupportedOperationException
//     *          if an unsupported option is specified
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkWrite(String) checkWrite}
//     *          method is invoked to check write access to the file.
//     *
//     * @see #write(Path,Iterable,Charset,OpenOption[])
//     */
//    public static BufferedWriter newBufferedWriter(Path path, Charset cs,
//                                                   OpenOption... options)
//        throws IOException
//    {
//        CharsetEncoder encoder = cs.newEncoder();
//        Writer writer = new OutputStreamWriter(newOutputStream(path, options), encoder);
//        return new BufferedWriter(writer);
//    }
//
//    /**
//     * Opens or creates a file for writing, returning a {@code BufferedWriter}
//     * to write text to the file in an efficient manner. The text is encoded
//     * into bytes for writing using the {@link StandardCharsets#UTF_8 UTF-8}
//     * {@link Charset charset}.
//     *
//     * <p> This method works as if invoking it were equivalent to evaluating the
//     * expression:
//     * <pre>{@code
//     * Files.newBufferedWriter(path, StandardCharsets.UTF_8, options)
//     * }</pre>
//     *
//     * @param   path
//     *          the path to the file
//     * @param   options
//     *          options specifying how the file is opened
//     *
//     * @return  a new buffered writer, with default buffer size, to write text
//     *          to the file
//     *
//     * @throws  IOException
//     *          if an I/O error occurs opening or creating the file
//     * @throws  UnsupportedOperationException
//     *          if an unsupported option is specified
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkWrite(String) checkWrite}
//     *          method is invoked to check write access to the file.
//     *
//     * @since 1.8
//     */
//    public static BufferedWriter newBufferedWriter(Path path, OpenOption... options) throws IOException {
//        return newBufferedWriter(path, StandardCharsets.UTF_8, options);
//    }
//
//    /**
//     * Copies all bytes from an input stream to a file. On return, the input
//     * stream will be at end of stream.
//     *
//     * <p> By default, the copy fails if the target file already exists or is a
//     * symbolic link. If the {@link StandardCopyOption#REPLACE_EXISTING
//     * REPLACE_EXISTING} option is specified, and the target file already exists,
//     * then it is replaced if it is not a non-empty directory. If the target
//     * file exists and is a symbolic link, then the symbolic link is replaced.
//     * In this release, the {@code REPLACE_EXISTING} option is the only option
//     * required to be supported by this method. Additional options may be
//     * supported in future releases.
//     *
//     * <p>  If an I/O error occurs reading from the input stream or writing to
//     * the file, then it may do so after the target file has been created and
//     * after some bytes have been read or written. Consequently the input
//     * stream may not be at end of stream and may be in an inconsistent state.
//     * It is strongly recommended that the input stream be promptly closed if an
//     * I/O error occurs.
//     *
//     * <p> This method may block indefinitely reading from the input stream (or
//     * writing to the file). The behavior for the case that the input stream is
//     * <i>asynchronously closed</i> or the thread interrupted during the copy is
//     * highly input stream and file system provider specific and therefore not
//     * specified.
//     *
//     * <p> <b>Usage example</b>: Suppose we want to capture a web page and save
//     * it to a file:
//     * <pre>
//     *     Path path = ...
//     *     URI u = URI.create("http://java.sun.com/");
//     *     try (InputStream in = u.toURL().openStream()) {
//     *         Files.copy(in, path);
//     *     }
//     * </pre>
//     *
//     * @param   in
//     *          the input stream to read from
//     * @param   target
//     *          the path to the file
//     * @param   options
//     *          options specifying how the copy should be done
//     *
//     * @return  the number of bytes read or written
//     *
//     * @throws  IOException
//     *          if an I/O error occurs when reading or writing
//     * @throws  FileAlreadyExistsException
//     *          if the target file exists but cannot be replaced because the
//     *          {@code REPLACE_EXISTING} option is not specified <i>(optional
//     *          specific exception)</i>
//     * @throws  DirectoryNotEmptyException
//     *          the {@code REPLACE_EXISTING} option is specified but the file
//     *          cannot be replaced because it is a non-empty directory
//     *          <i>(optional specific exception)</i>     *
//     * @throws  UnsupportedOperationException
//     *          if {@code options} contains a copy option that is not supported
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkWrite(String) checkWrite}
//     *          method is invoked to check write access to the file. Where the
//     *          {@code REPLACE_EXISTING} option is specified, the security
//     *          manager's {@link SecurityManager#checkDelete(String) checkDelete}
//     *          method is invoked to check that an existing file can be deleted.
//     */
//    public static long copy(InputStream in, Path target, CopyOption... options)
//        throws IOException
//    {
//        // ensure not null before opening file
//        Objects.requireNonNull(in);
//
//        // check for REPLACE_EXISTING
//        boolean replaceExisting = false;
//        for (CopyOption opt: options) {
//            if (opt == StandardCopyOption.REPLACE_EXISTING) {
//                replaceExisting = true;
//            } else {
//                if (opt == null) {
//                    throw new NullPointerException("options contains 'null'");
//                }  else {
//                    throw new UnsupportedOperationException(opt + " not supported");
//                }
//            }
//        }
//
//        // attempt to delete an existing file
//        SecurityException se = null;
//        if (replaceExisting) {
//            try {
//                deleteIfExists(target);
//            } catch (SecurityException x) {
//                se = x;
//            }
//        }
//
//        // attempt to create target file. If it fails with
//        // FileAlreadyExistsException then it may be because the security
//        // manager prevented us from deleting the file, in which case we just
//        // throw the SecurityException.
//        OutputStream ostream;
//        try {
//            ostream = newOutputStream(target, StandardOpenOption.CREATE_NEW,
//                                              StandardOpenOption.WRITE);
//        } catch (FileAlreadyExistsException x) {
//            if (se != null)
//                throw se;
//            // someone else won the race and created the file
//            throw x;
//        }
//
//        // do the copy
//        try (OutputStream out = ostream) {
//            return copy(in, out);
//        }
//    }
//
//    /**
//     * Copies all bytes from a file to an output stream.
//     *
//     * <p> If an I/O error occurs reading from the file or writing to the output
//     * stream, then it may do so after some bytes have been read or written.
//     * Consequently the output stream may be in an inconsistent state. It is
//     * strongly recommended that the output stream be promptly closed if an I/O
//     * error occurs.
//     *
//     * <p> This method may block indefinitely writing to the output stream (or
//     * reading from the file). The behavior for the case that the output stream
//     * is <i>asynchronously closed</i> or the thread interrupted during the copy
//     * is highly output stream and file system provider specific and therefore
//     * not specified.
//     *
//     * <p> Note that if the given output stream is {@link java.io.Flushable}
//     * then its {@link java.io.Flushable#flush flush} method may need to invoked
//     * after this method completes so as to flush any buffered output.
//     *
//     * @param   source
//     *          the  path to the file
//     * @param   out
//     *          the output stream to write to
//     *
//     * @return  the number of bytes read or written
//     *
//     * @throws  IOException
//     *          if an I/O error occurs when reading or writing
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the file.
//     */
//    public static long copy(Path source, OutputStream out) throws IOException {
//        // ensure not null before opening file
//        Objects.requireNonNull(out);
//
//        try (InputStream in = newInputStream(source)) {
//            return copy(in, out);
//        }
//    }
//
//    /**
//     * Reads all the bytes from a file. The method ensures that the file is
//     * closed when all bytes have been read or an I/O error, or other runtime
//     * exception, is thrown.
//     *
//     * <p> Note that this method is intended for simple cases where it is
//     * convenient to read all bytes into a byte array. It is not intended for
//     * reading in large files.
//     *
//     * @param   path
//     *          the path to the file
//     *
//     * @return  a byte array containing the bytes read from the file
//     *
//     * @throws  IOException
//     *          if an I/O error occurs reading from the stream
//     * @throws  OutOfMemoryError
//     *          if an array of the required size cannot be allocated, for
//     *          example the file is larger that {@code 2GB}
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the file.
//     */
//    public static byte[] readAllBytes(Path path) throws IOException {
//        try (SeekableByteChannel sbc = Files.newByteChannel(path);
//             InputStream in = Channels.newInputStream(sbc)) {
//            long size = sbc.size();
//            if (size > (long)MAX_BUFFER_SIZE)
//                throw new OutOfMemoryError("Required array size too large");
//
//            return read(in, (int)size);
//        }
//    }
//
//    /**
//     * Read all lines from a file. This method ensures that the file is
//     * closed when all bytes have been read or an I/O error, or other runtime
//     * exception, is thrown. Bytes from the file are decoded into characters
//     * using the specified charset.
//     *
//     * <p> This method recognizes the following as line terminators:
//     * <ul>
//     *   <li> <code>&#92;u000D</code> followed by <code>&#92;u000A</code>,
//     *     CARRIAGE RETURN followed by LINE FEED </li>
//     *   <li> <code>&#92;u000A</code>, LINE FEED </li>
//     *   <li> <code>&#92;u000D</code>, CARRIAGE RETURN </li>
//     * </ul>
//     * <p> Additional Unicode line terminators may be recognized in future
//     * releases.
//     *
//     * <p> Note that this method is intended for simple cases where it is
//     * convenient to read all lines in a single operation. It is not intended
//     * for reading in large files.
//     *
//     * @param   path
//     *          the path to the file
//     * @param   cs
//     *          the charset to use for decoding
//     *
//     * @return  the lines from the file as a {@code List}; whether the {@code
//     *          List} is modifiable or not is implementation dependent and
//     *          therefore not specified
//     *
//     * @throws  IOException
//     *          if an I/O error occurs reading from the file or a malformed or
//     *          unmappable byte sequence is read
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the file.
//     *
//     * @see #newBufferedReader
//     */
//    public static List<String> readAllLines(Path path, Charset cs) throws IOException {
//        try (BufferedReader reader = newBufferedReader(path, cs)) {
//            List<String> result = new ArrayList<>();
//            for (;;) {
//                String line = reader.readLine();
//                if (line == null)
//                    break;
//                result.add(line);
//            }
//            return result;
//        }
//    }
//
//    /**
//     * Read all lines from a file. Bytes from the file are decoded into characters
//     * using the {@link StandardCharsets#UTF_8 UTF-8} {@link Charset charset}.
//     *
//     * <p> This method works as if invoking it were equivalent to evaluating the
//     * expression:
//     * <pre>{@code
//     * Files.readAllLines(path, StandardCharsets.UTF_8)
//     * }</pre>
//     *
//     * @param   path
//     *          the path to the file
//     *
//     * @return  the lines from the file as a {@code List}; whether the {@code
//     *          List} is modifiable or not is implementation dependent and
//     *          therefore not specified
//     *
//     * @throws  IOException
//     *          if an I/O error occurs reading from the file or a malformed or
//     *          unmappable byte sequence is read
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the file.
//     *
//     * @since 1.8
//     */
//    public static List<String> readAllLines(Path path) throws IOException {
//        return readAllLines(path, StandardCharsets.UTF_8);
//    }
//
//    /**
//     * Writes bytes to a file. The {@code options} parameter specifies how the
//     * the file is created or opened. If no options are present then this method
//     * works as if the {@link StandardOpenOption#CREATE CREATE}, {@link
//     * StandardOpenOption#TRUNCATE_EXISTING TRUNCATE_EXISTING}, and {@link
//     * StandardOpenOption#WRITE WRITE} options are present. In other words, it
//     * opens the file for writing, creating the file if it doesn't exist, or
//     * initially truncating an existing {@link #isRegularFile regular-file} to
//     * a size of {@code 0}. All bytes in the byte array are written to the file.
//     * The method ensures that the file is closed when all bytes have been
//     * written (or an I/O error or other runtime exception is thrown). If an I/O
//     * error occurs then it may do so after the file has created or truncated,
//     * or after some bytes have been written to the file.
//     *
//     * <p> <b>Usage example</b>: By default the method creates a new file or
//     * overwrites an existing file. Suppose you instead want to append bytes
//     * to an existing file:
//     * <pre>
//     *     Path path = ...
//     *     byte[] bytes = ...
//     *     Files.write(path, bytes, StandardOpenOption.APPEND);
//     * </pre>
//     *
//     * @param   path
//     *          the path to the file
//     * @param   bytes
//     *          the byte array with the bytes to write
//     * @param   options
//     *          options specifying how the file is opened
//     *
//     * @return  the path
//     *
//     * @throws  IOException
//     *          if an I/O error occurs writing to or creating the file
//     * @throws  UnsupportedOperationException
//     *          if an unsupported option is specified
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkWrite(String) checkWrite}
//     *          method is invoked to check write access to the file.
//     */
//    public static Path write(Path path, byte[] bytes, OpenOption... options)
//        throws IOException
//    {
//        // ensure bytes is not null before opening file
//        Objects.requireNonNull(bytes);
//
//        try (OutputStream out = Files.newOutputStream(path, options)) {
//            int len = bytes.length;
//            int rem = len;
//            while (rem > 0) {
//                int n = Math.min(rem, BUFFER_SIZE);
//                out.write(bytes, (len-rem), n);
//                rem -= n;
//            }
//        }
//        return path;
//    }
//
//    /**
//     * Write lines of text to a file. Each line is a char sequence and is
//     * written to the file in sequence with each line terminated by the
//     * platform's line separator, as defined by the system property {@code
//     * line.separator}. Characters are encoded into bytes using the specified
//     * charset.
//     *
//     * <p> The {@code options} parameter specifies how the the file is created
//     * or opened. If no options are present then this method works as if the
//     * {@link StandardOpenOption#CREATE CREATE}, {@link
//     * StandardOpenOption#TRUNCATE_EXISTING TRUNCATE_EXISTING}, and {@link
//     * StandardOpenOption#WRITE WRITE} options are present. In other words, it
//     * opens the file for writing, creating the file if it doesn't exist, or
//     * initially truncating an existing {@link #isRegularFile regular-file} to
//     * a size of {@code 0}. The method ensures that the file is closed when all
//     * lines have been written (or an I/O error or other runtime exception is
//     * thrown). If an I/O error occurs then it may do so after the file has
//     * created or truncated, or after some bytes have been written to the file.
//     *
//     * @param   path
//     *          the path to the file
//     * @param   lines
//     *          an object to iterate over the char sequences
//     * @param   cs
//     *          the charset to use for encoding
//     * @param   options
//     *          options specifying how the file is opened
//     *
//     * @return  the path
//     *
//     * @throws  IOException
//     *          if an I/O error occurs writing to or creating the file, or the
//     *          text cannot be encoded using the specified charset
//     * @throws  UnsupportedOperationException
//     *          if an unsupported option is specified
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkWrite(String) checkWrite}
//     *          method is invoked to check write access to the file.
//     */
//    public static Path write(Path path, Iterable<? extends CharSequence> lines, Charset cs, OpenOption... options) throws IOException {
//        // ensure lines is not null before opening file
//        Objects.requireNonNull(lines);
//        CharsetEncoder encoder = cs.newEncoder();
//        OutputStream out = newOutputStream(path, options);
//        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, encoder))) {
//            for (CharSequence line: lines) {
//                writer.append(line);
//                writer.newLine();
//            }
//        }
//        return path;
//    }
//
//    /**
//     * Write lines of text to a file. Characters are encoded into bytes using
//     * the {@link StandardCharsets#UTF_8 UTF-8} {@link Charset charset}.
//     *
//     * <p> This method works as if invoking it were equivalent to evaluating the
//     * expression:
//     * <pre>{@code
//     * Files.write(path, lines, StandardCharsets.UTF_8, options);
//     * }</pre>
//     *
//     * @param   path
//     *          the path to the file
//     * @param   lines
//     *          an object to iterate over the char sequences
//     * @param   options
//     *          options specifying how the file is opened
//     *
//     * @return  the path
//     *
//     * @throws  IOException
//     *          if an I/O error occurs writing to or creating the file, or the
//     *          text cannot be encoded as {@code UTF-8}
//     * @throws  UnsupportedOperationException
//     *          if an unsupported option is specified
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkWrite(String) checkWrite}
//     *          method is invoked to check write access to the file.
//     *
//     * @since 1.8
//     */
//    public static Path write(Path path,
//                             Iterable<? extends CharSequence> lines,
//                             OpenOption... options)
//        throws IOException
//    {
//        return write(path, lines, StandardCharsets.UTF_8, options);
//    }
//
//    // -- Stream APIs --
//
//  
//
//    /**
//     * Read all lines from a file as a {@code Stream}. Unlike {@link
//     * #readAllLines(Path, Charset) readAllLines}, this method does not read
//     * all lines into a {@code List}, but instead populates lazily as the stream
//     * is consumed.
//     *
//     * <p> Bytes from the file are decoded into characters using the specified
//     * charset and the same line terminators as specified by {@code
//     * readAllLines} are supported.
//     *
//     * <p> After this method returns, then any subsequent I/O exception that
//     * occurs while reading from the file or when a malformed or unmappable byte
//     * sequence is read, is wrapped in an {@link UncheckedIOException} that will
//     * be thrown from the
//     * {@link java.util.stream.Stream} method that caused the read to take
//     * place. In case an {@code IOException} is thrown when closing the file,
//     * it is also wrapped as an {@code UncheckedIOException}.
//     *
//     * <p> The returned stream encapsulates a {@link Reader}.  If timely
//     * disposal of file system resources is required, the try-with-resources
//     * construct should be used to ensure that the stream's
//     * {@link Stream#close close} method is invoked after the stream operations
//     * are completed.
//     *
//     *
//     * @param   path
//     *          the path to the file
//     * @param   cs
//     *          the charset to use for decoding
//     *
//     * @return  the lines from the file as a {@code Stream}
//     *
//     * @throws  IOException
//     *          if an I/O error occurs opening the file
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the file.
//     *
//     * @see     #readAllLines(Path, Charset)
//     * @see     #newBufferedReader(Path, Charset)
//     * @see     java.io.BufferedReader#lines()
//     * @since   1.8
//     */
//    public static Stream<String> lines(Path path, Charset cs) throws IOException {
//        BufferedReader br = Files.newBufferedReader(path, cs);
//        try {
//            return br.lines().onClose(asUncheckedRunnable(br));
//        } catch (Error|RuntimeException e) {
//            try {
//                br.close();
//            } catch (IOException ex) {
//                try {
//                    e.addSuppressed(ex);
//                } catch (Throwable ignore) {}
//            }
//            throw e;
//        }
//    }
//
//    /**
//     * Read all lines from a file as a {@code Stream}. Bytes from the file are
//     * decoded into characters using the {@link StandardCharsets#UTF_8 UTF-8}
//     * {@link Charset charset}.
//     *
//     * <p> This method works as if invoking it were equivalent to evaluating the
//     * expression:
//     * <pre>{@code
//     * Files.lines(path, StandardCharsets.UTF_8)
//     * }</pre>
//     *
//     * @param   path
//     *          the path to the file
//     *
//     * @return  the lines from the file as a {@code Stream}
//     *
//     * @throws  IOException
//     *          if an I/O error occurs opening the file
//     * @throws  SecurityException
//     *          In the case of the default provider, and a security manager is
//     *          installed, the {@link SecurityManager#checkRead(String) checkRead}
//     *          method is invoked to check read access to the file.
//     *
//     * @since 1.8
//     */
//    public static Stream<String> lines(Path path) throws IOException {
//        return lines(path, StandardCharsets.UTF_8);
//    }
//}
