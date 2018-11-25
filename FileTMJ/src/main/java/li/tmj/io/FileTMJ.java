package li.tmj.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileTMJ {
	private Path dataForkPath;
	private Path resourceForkPath;
	
	public static void main(String[] args) throws IOException {
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
	
	/**
	 * Constructs a FileTMJ object from this path. The resulting object is associated with the default-filesystem.
	 * @param path – the path
	 * @throws InvalidPathException - if a Path object cannot be constructed from the abstract path (see FileSystem.getPath)
	 */
	public FileTMJ(Path path) {
		dataForkPath=path;
		resourceForkPath=resourceFork(dataForkPath);
	}
	
	public FileTMJ(File file) throws InvalidPathException {
		this(file.toPath());
	}
	
	public FileTMJ(String filePath) {
		this(Paths.get(filePath));
	}
	
	private Path resourceFork(Path path) {
//		return path.resolve("rsrc"); // ≤ 10.?
		return path.resolve("..namedfork/rsrc"); // > 10.?
	}
	
    public boolean hasDataFork() throws IOException {
//    	FileSystem dataForkPath.getFileSystem())		dataForkPath.getFileSystem().getClass().getSimpleName()	"MacOSXFileSystem"
    	//System.getProperty("os.name")		"Mac OS X"	bei macOS 10.13.6 High Sierra
    	return hasFork(dataForkPath);
    }
    public boolean hasResourceFork() throws IOException {
    	return hasFork(resourceForkPath);
    }
	private boolean hasFork(Path path) throws IOException {
		return Files.exists(path, LinkOption.NOFOLLOW_LINKS)
        		&& Files.size(path)>0;
	}
	
    public FileTMJ child(String child) {
    	return new FileTMJ(dataForkPath.resolve(child));
//        return new FileTMJ(new File(dataFork,child));
    }
    
	public FileTMJ[] containingFiles() throws NotDirectoryException, SecurityException, IOException {
		return containingFilesStream().toArray(FileTMJ[]::new);
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
	 */
	public Stream<FileTMJ> containingFilesStream() throws IOException,SecurityException,NotDirectoryException{
		return Files.list(dataForkPath).map(f->new FileTMJ(f));
	}
	public FileTMJ[] containingFiles(FilenameFilter filter) throws NotDirectoryException, SecurityException, IOException {
		return containingFilesStream()
			.filter( f->filter.accept( f.dataForkPath.getParent().toFile(), f.dataForkPath.getFileName().toString() ) )
			.toArray(FileTMJ[]::new);

	}

    /**
     * Tests whether a file exists.
The options parameter may be used to indicate how symbolic links are handled for the case that the file is a symbolic link. 
By default, symbolic links are followed. If the option NOFOLLOW_LINKS is present then symbolic links are not followed.

Note that the result of this method is immediately outdated. If this method indicates the file exists then there is no guarantee that a subsequence access will succeed.
Care should be taken when using this method in security sensitive applications.

Parameters:
path the path to the file to test
options options indicating how symbolic links are handled .
Returns:
true if the file exists; false if the file does not exist or its existence cannot be determined.
Throws:
SecurityException - In the case of the default provider, the SecurityManager.checkRead(String) is invoked to check read access to the file.
See Also:
notExists

    		long java.nio.file.Files.size(Path path) throws IOException

    		Returns the size of a file (in bytes). The size may differ from the actual size on the file system due to compression, support for sparse files, or other reasons. The size of files that are not regular files is implementation specific and therefore unspecified.

    		Parameters:
    		path the path to the file
    		Returns:
    		the file size, in bytes
    		Throws:
    		IOException - if an I/O error occurs
    		SecurityException - In the case of the default provider, and a security manager is installed, its checkRead method denies read access to the file.
    		See Also:
    		BasicFileAttributes.size
     * @throws IOException 

	 symbolic links are followed. If the option NOFOLLOW_LINKS is present then symbolic links are not followed.
	    Parameters:
	    path the path to the file to test
	    options options indicating how symbolic links are handled .
	    Returns:
	    true if the file exists; false if the file does not exist or its existence cannot be determined.
	    Throws:
	    SecurityException - In the case of the default provider, the SecurityManager.checkRead(String) is invoked to check read access to the file.
	    See Also:
	    notExists
	    */
	public boolean exists() {
		return Files.exists(dataForkPath, LinkOption.NOFOLLOW_LINKS) || Files.exists(resourceForkPath, LinkOption.NOFOLLOW_LINKS);
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
	
	public boolean isDirectory(LinkOption linkOption) {
		return Files.isDirectory(dataForkPath, linkOption);
	}
	public boolean isDirectory() {
		return isDirectory(LinkOption.NOFOLLOW_LINKS);
	}
	
	public boolean isRegularFile(LinkOption linkOption) {
		return Files.isRegularFile(dataForkPath, linkOption);
	}
	public boolean isRegularFile() {
		return isRegularFile(LinkOption.NOFOLLOW_LINKS);
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
	
	public FileTMJ moveTo(FileTMJ destination) throws IOException {
		Path path=Files.move(dataForkPath, destination.dataForkPath, StandardCopyOption.ATOMIC_MOVE);
		return new FileTMJ(path);
	}
	
	/** returns true, if the object's name was sucessfully changed or need not to be changed, false otherwise. 
	 * nameNew must not be null
	 * @throws IOException 
	 * */
	public boolean setName(String nameNew) throws IOException {
//			if(dataForkPath.getFileName().toString().equals(nameNew)){
//				return true;
//			}
			Path parent=dataForkPath.getParent();
			// try{
			Path dest=parent.resolve(nameNew);
			// }
			if( Files.exists(dest, LinkOption.NOFOLLOW_LINKS) ){
				throw new FileAlreadyExistsException(dest.toString());//TODO
				//tell me=display dialog "This name is already taken, please rename." default answer nameNew buttons {"Cancel", "Skip", "OK"} default button 3
				//copy the result as list={nameNew, button_pressed}
				//if( the button_pressed is "Skip" ){ return 0
				//my setFilesystemObjectName(FilesystemObject, nameNew)
			}// if( not (exists item (the parent_container_path + nameNew)) ){
//					try{
//			if(simulate) { return false; }
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
	 * Returns the size of a file (in bytes). 
	 * The size may differ from the actual size on the file system due to compression, support for sparse files, or other reasons. 
	 * The size of files that are not regular files is implementation specific and therefore unspecified.
	 * @return size in bytes, unspecified if directory
	 * @throws SecurityException - If a security manager exists and its java.lang.SecurityManager.checkRead(java.lang.String) method denies read access to the file
	 * @throws IOException - if an I/O error occurs
	 * 
	 * long java.nio.file.Files.size(Path path) throws IOException
	 */
	public long sizeBytes() throws SecurityException, IOException{
		long dataSize=0;
		long resourceSize=0;
		if(!Files.exists(dataForkPath, LinkOption.NOFOLLOW_LINKS) && !Files.exists(resourceForkPath,LinkOption.NOFOLLOW_LINKS)) {
			throw new FileNotFoundException();
		}else {
			if(Files.exists(dataForkPath, LinkOption.NOFOLLOW_LINKS)) {
				dataSize=Files.size(dataForkPath);
			}
			if(Files.exists(resourceForkPath,LinkOption.NOFOLLOW_LINKS)) {
				resourceSize=Files.size(resourceForkPath);
			}
		}
		return dataSize+resourceSize;
	}
	

	

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

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		FileTMJ other = (FileTMJ) obj;
		return true;
	}

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
