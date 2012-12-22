package pl.com.astec.mdt.ant.tasks;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;


public class NormalizePath extends Task  {

	AntLogAdapter fLogAdapter;
	private String fDir = "";
	private String fSkipPoints="1";
	protected String fResultProperty="";
	
	
	/**
	 * String represents dir locations for example C:/temp/data 
	 * @param baseDir
	 */
	public void setDir(String baseDir) {
		this.fDir = baseDir;
	}
	/**
	 * Used to set return property. This property should have url like C:/temp/
	 * @param property
	 */
	public void setProperty(String property) {
		this.fResultProperty=property;
	}
	/**
	 * Number of segments to skip
	 * @param fSkipPoints
	 */
	public void setSegments(String fSkipPoints) {
		this.fSkipPoints = fSkipPoints;
	}
	
	public void execute() throws BuildException {
		try {
			fLogAdapter=new AntLogAdapter(this);
			validateAtributes();
			generateResult();
			fLogAdapter=new AntLogAdapter(null);
		} catch (Exception e) {
			throw new BuildException(e.getMessage().toString());
		}
	}

	private void validateAtributes() {
		if(fDir.length()==0){
			  throw new BuildException("Dir must be specified using the dir attribute");
		} else if(fResultProperty.length()==0){
			 throw new BuildException("Property must be specified using the property attribute.");
		}
	}
	
	public void generateResult() {
		IPath path=new Path(fDir);
		Integer segmentsToRemove = Integer.valueOf(fSkipPoints);
		IPath finalPath = path.removeLastSegments(segmentsToRemove);
		if(getProject() !=null){
			getProject().setProperty(fResultProperty, finalPath.toOSString());
		}
	}
	@Override
	public String getTaskType() {
		return "normalize.path";
	}
	
}
