package at.spindi;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class SortedDirectory {

	public Collection<String> getFilesSortedByLastModified(final String directory) throws IOException {
		
		final SortedMap<FileTime, String> sortedMap = new TreeMap<>();
		
		Files.walkFileTree(
				Paths.get(directory), 
				new HashSet<FileVisitOption>(),
				1,
				new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file,	BasicFileAttributes attrs) throws IOException {
						if ( attrs.isRegularFile() ) {
							//sortedMap.put( attrs.lastModifiedTime(), file.toString() );
							sortedMap.put(attrs.lastModifiedTime(), file.toFile().getName() );
						}
						return FileVisitResult.CONTINUE;
					}
				});
				
		return sortedMap.values();
		
	}
	
}
