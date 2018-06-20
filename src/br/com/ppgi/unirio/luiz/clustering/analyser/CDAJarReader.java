package br.com.ppgi.unirio.luiz.clustering.analyser;

import java.util.ArrayList;
import org.pf.tools.cda.base.model.ClassInformation;
import org.pf.tools.cda.base.model.ClassPackage;
import org.pf.tools.cda.base.model.GenericClassContainer;
import org.pf.tools.cda.base.model.IAnalyzableElement;
import org.pf.tools.cda.base.model.Workset;
import org.pf.tools.cda.base.model.util.StringFilter;
import org.pf.tools.cda.base.model.workset.ClasspathPartDefinition;
import org.pf.tools.cda.core.dependency.analyzer.DependencyAnalyzer;
import org.pf.tools.cda.core.dependency.analyzer.model.DependencyInfo;
import org.pf.tools.cda.core.init.WorksetInitializer;
import org.pf.tools.cda.core.processing.IProgressMonitor;
import org.pfsw.odem.DependencyClassification;

import br.com.ppgi.unirio.luiz.clustering.model.Project;
import br.com.ppgi.unirio.luiz.clustering.model.ProjectClass;
import br.com.ppgi.unirio.luiz.clustering.model.ProjectPackage;

public class CDAJarReader
{
	
	private static final boolean IS_MONITORING = false;
//	public ArrayList<String> framework;
	public ArrayList<String> framework = new ArrayList<String>() ;
	/**
	 * Carrega uma aplicação a partir de um arquivo no formato XML ODEM
	 */
	
	public CDAJarReader() {
		
		framework.clear();
	}
	
	
	
	public Project execute(String filename) throws Exception {
		
		Workset workset = createWorkset(filename);
		initializeWorkset(workset);
		
//		showAllDependenciesOf(workset);
		
		return loadProject (workset) ;
	}
	
	
	private Project loadProject(Workset workset) throws Exception
	{
		Project project = new Project(workset.getName());
		loadContainers(project, workset);
		
		return project;
	}
	
	
	private void loadContainers(Project project, Workset workset) throws Exception
	{
		for (GenericClassContainer classContainer : workset.getClassContainers()) {
			loadNamespaces(project, classContainer);
		}
	}
		
	private void loadNamespaces(Project project, GenericClassContainer classContainer) throws Exception
	{
//		if (classContainer.isThirdPartyLibrary()){
//			System.out.println("third part");
//			System.out.println( classContainer.getName());		
//		}
				
		for (ClassPackage classpackage : classContainer.getPackages()) {
			if (!isFramework(classpackage.getName())){ 
				ProjectPackage apackage = project.addPackage(classpackage.getName());
					
				loadClasses(project, apackage, classpackage);
			}
		}		
	}
	
	private boolean isFramework(String packageName) {
		for (int i = 0; i < framework.size()-1; i++) {
			 if (framework.get(i).equals(packageName.substring(0, Integer.min(framework.get(i).length(), (packageName.length() == 0 ? 0 : packageName.length()))))) {
				 return true;
			 }; 
		}
		return false;
	}
		
	private void loadClasses(Project project, ProjectPackage apackage, ClassPackage classpackage) throws Exception
	{
		for (ClassInformation classinformation : classpackage.getClassInfos()) {
			if (!isFramework(classinformation.getPackageName())) {
				ProjectClass aClass = new ProjectClass(classinformation.getName(),classinformation.getClassification(), classinformation.getVisibility() , classinformation.isAbstract());
				aClass.setPackage(apackage);
				project.addClass(aClass);
				loadDependencies(aClass, classinformation);
			}
		}	
	}
	
	private void loadDependencies(ProjectClass aClass, ClassInformation classinformation) throws Exception
	{
		for (ClassInformation depClassInformation : classinformation.getReferredClasses()) {
//			Coloquei para que todas as relações de dependência seja do tipo extension
			if (!isFramework(depClassInformation.getPackageName())) {
				aClass.addDependency(depClassInformation.getName(),  DependencyClassification.EXTENSION);
			}
		}
	}
	
	
	protected Workset createWorkset(String filename)
	  {
	    Workset workset;
	    
	    workset = new Workset(filename.substring(filename.lastIndexOf("\\")+1, filename.indexOf(".jar")));
	    
	    ClasspathPartDefinition partDefinition;
	    
	    partDefinition = new ClasspathPartDefinition(filename);
	    workset.addClasspathPartDefinition(partDefinition);
	    workset.addIgnoreFilter(new StringFilter("*"));
	    return workset;
	  }
	
	
	  protected void initializeWorkset(Workset workset)
	  {
	    WorksetInitializer wsInitializer;
	    IProgressMonitor monitor = null;

	    if (IS_MONITORING)
	    {      
	      monitor = new ConsoleMonitor();
	    }
	    
	    wsInitializer = new WorksetInitializer(workset);
	    
	    // Running with no progress monitor (null) is okay as well.
	    wsInitializer.initializeWorksetAndWait(monitor); 
	  }
	
	
	
	  protected void showAllDependenciesOf(Workset workset) 
	  {
	    ClassInformation classInfo;
	    ClassInformation[] classes;
	    
	    for (ClassInformation classInfo1 : workset.getAllContainedClasses()) {
	    
		    // Lookup the class of interest
		    classInfo = workset.getClassInfo(classInfo1.getClassName());
	
		    IAnalyzableElement elementToAnalyze = classInfo;
		    DependencyAnalyzer analyzer = new DependencyAnalyzer(elementToAnalyze);
		    analyzer.analyze();
		    DependencyInfo result = analyzer.getResult();
	
		    classes = result.getAllReferredClasses();    
		    System.out.println(classInfo.getName() + " dependes on " + classes.length + " classes:");
	    }
	  }
}