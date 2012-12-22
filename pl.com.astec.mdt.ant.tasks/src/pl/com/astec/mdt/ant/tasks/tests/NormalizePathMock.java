package pl.com.astec.mdt.ant.tasks.tests;

import org.apache.tools.ant.Project;

import pl.com.astec.mdt.ant.tasks.NormalizePath;

public class NormalizePathMock extends NormalizePath{
	
	private Project tempProject;

	@Override
	public Project getProject() {
		if(tempProject==null)
			tempProject = new Project();
		return tempProject;
	}
	
	public String getResult() {
		if(tempProject!=null && fResultProperty!=null){
			return tempProject.getProperty(fResultProperty);
		}
		return "";
	}
}
