package pl.com.astec.mdt.ant.tasks.tests;

import junit.framework.Assert;

import org.eclipse.core.runtime.Path;
import org.junit.Test;


public class NormalizePathTest {
	
	NormalizePathMock task=new NormalizePathMock();
	
	@Test
	public void testGenerateResult() {
		String baseDir = "C://temp/testdir/removed_folder";
		Path expected=new Path("C://temp/testdir");
		
		task.setDir(baseDir);
		task.setProperty("test");
		task.generateResult();
		
		Path actual=new Path(task.getResult());
		Assert.assertTrue(actual.equals(expected));
	}
}
