//package li.tmj.io;
//
//import java.io.IOException;
//import java.net.URI;
//import java.nio.channels.SeekableByteChannel;
//import java.nio.file.AccessMode;
//import java.nio.file.CopyOption;
//import java.nio.file.DirectoryStream;
//import java.nio.file.DirectoryStream.Filter;
//import java.nio.file.FileStore;
//import java.nio.file.FileSystem;
//import java.nio.file.LinkOption;
//import java.nio.file.OpenOption;
//import java.nio.file.Path;
//import java.nio.file.PathMatcher;
//import java.nio.file.WatchService;
//import java.nio.file.attribute.BasicFileAttributes;
//import java.nio.file.attribute.FileAttribute;
//import java.nio.file.attribute.FileAttributeView;
//import java.nio.file.attribute.UserPrincipalLookupService;
//import java.nio.file.spi.FileSystemProvider;
//import java.util.Map;
//import java.util.Set;
//
//public class AppleScriptFileSystem extends FileSystem{
//	FileSystemProvider fileSystemProvider=new AppleScriptFileSystemProvider();//TODO correct?
//
//	@Override
//	public FileSystemProvider provider() {
//		// TODO Auto-generated method stub
//		return fileSystemProvider;
//	}
//
//	@Override
//	public void close() throws IOException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean isOpen() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isReadOnly() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public String getSeparator() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Iterable<Path> getRootDirectories() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Iterable<FileStore> getFileStores() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Set<String> supportedFileAttributeViews() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Path getPath(String first, String... more) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public PathMatcher getPathMatcher(String syntaxAndPattern) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public UserPrincipalLookupService getUserPrincipalLookupService() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public WatchService newWatchService() throws IOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	
//}
