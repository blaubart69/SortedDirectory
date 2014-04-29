package at.spindi;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class testSortedDir {

	private TemporaryFolder tempFold1;
	private TemporaryFolder tempFold2;

	@Before
	public void setUp() throws Exception {

		tempFold1 = new TemporaryFolder();
		tempFold1.create();
		
		tempFold2 = new TemporaryFolder(tempFold1.getRoot());
		tempFold2.create();
				
	}
	
	@After
	public void tearDown() {
	
		tempFold1.delete();
		
	}

	@Test
	public void testSimple() throws IOException {

		tempFold1.newFile("c.txt").setLastModified( 1 );
		tempFold1.newFile("b.txt").setLastModified( 1000 );
		tempFold1.newFile("a.txt").setLastModified( 2000 );
		tempFold1.newFile("0.txt").setLastModified( 3000 );

		// create a file in a subdir. should not be listed
		tempFold2.newFile("bumsti.txt");
		
		Collection<String> files = new SortedDirectory().getFilesSortedByLastModified(
				tempFold1.getRoot().getAbsolutePath());
		
		assertArrayEquals(
				new String[] { "c.txt", "b.txt", "a.txt", "0.txt" }, 
				files.toArray(new String[files.size()]));
		
	}

}
