package br.com.ppgi.unirio.luiz.clustering.analyser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import br.com.ppgi.unirio.luiz.clustering.model.Project;
import br.com.ppgi.unirio.luiz.clustering.model.ProjectClass;
import br.com.ppgi.unirio.luiz.clustering.model.ProjectPackage;
import br.com.ppgi.unirio.marlon.smc.mdg.simplifier.MDGSimplifier;
import br.com.ppgi.unirio.marlon.smc.solution.algorithm.heuristic.lns.LNSConfiguration;
import br.com.ppgi.unirio.marlon.smc.solution.algorithm.heuristic.lns.LargeNeighborhoodSearch;

public class LNSInterpreter {
	
	private MDGSimplifier mDGSimplifier;
	private Vector<LargeNeighborhoodSearch> lns;
	private Vector<Integer> cluster;

	
	public LNSInterpreter()
	{
		this.lns = new Vector<LargeNeighborhoodSearch>();
		this.cluster = new Vector<Integer>();
	}
	
	public LNSInterpreter(MDGSimplifier mDGSimplifier)
	{
		this.setmDGSimplifier(mDGSimplifier);
		this.lns = new Vector<LargeNeighborhoodSearch>();	
		
	}
	
	
	/**
	 * Adiciona um pacote na aplicação
	 */
	public LargeNeighborhoodSearch addLNS(LargeNeighborhoodSearch lnsAdd, LNSConfiguration config)
	{
		LargeNeighborhoodSearch aLns = new LargeNeighborhoodSearch(config);
		aLns = lnsAdd;
		lns.add (aLns);
		return aLns;
	}

	
	public void generate() throws IOException
	{
		cluster.setSize(mDGSimplifier.getRemovedModules().size() + lns.get(0).getBestSolutionFound().getSolution().length);
		for(int i=0; i<(mDGSimplifier.getRemovedModules().size()); i++){
			cluster.set(mDGSimplifier.getRemovedModules().get(i), -1);
		}
		int solution = 0;
		
		for(int i=0; i<(cluster.size()); i++){
			if( cluster.get(i)==null) {
				cluster.set(i, lns.get(0).getBestSolutionFound().getSolution()[solution]);
				solution++;
			}
		}

//		System.out.println(cluster.toString());
		
		Project project = new Project (lns.get(0).getClusterMetrics().getMdg().getName());
		
		for(int j=0; j<(cluster.size()); j++){
			if (project.getPackageName("PKG" + cluster.get(j)) == null) {
				project.addPackage("PKG" + cluster.get(j));
			}
			ProjectClass projectClass = new ProjectClass(lns.get(0).getClusterMetrics().getMdg().getModuleNames().get(j), project.getPackageName("PKG" + cluster.get(j)));
			project.addClass(projectClass);
		}
		        
		StringBuilder sb = new StringBuilder();
	    
//	    for(ProjectPackage projectPackage: project.getPackages()) {
//	    	Vector<ProjectClass>  projectClasses1 = project.getClasses(projectPackage);
//	    	Vector<ProjectClass>  projectClasses2 = project.getClasses(projectPackage);
//	    	for(ProjectClass projectClass1: projectClasses1) {
//	    		projectClasses2.remove(projectClass1);
//	    		for(ProjectClass projectClass2: projectClasses2) {
//	    			sb.append(projectClass1.getName() + " " + projectClass2.getName());
//	    			sb.append(System.lineSeparator());
//	    		}	    		
//	    	}
//	    }

	    
	    for(ProjectPackage projectPackage: project.getPackages()) {
	    	for(ProjectClass projectClass1: project.getClasses(projectPackage)) {
    			sb.append(projectPackage.getName() + " " + projectClass1.getName());
    			sb.append(System.lineSeparator());	    		
	    	}
	    }
	    
	    	    		    
	    File file = new File("data//3-finalCombination//"+ lns.get(0).getClusterMetrics().getMdg().getName());
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    try {
	        writer.write(sb.toString());	    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   
		} finally {
			writer.close();
		}
		
		
		
		
		//processo para criar o arquivo com a relação de dependências. OLD
//		BufferedReader br = null;
//		try {
//			br = new BufferedReader(new FileReader("data//depTextFiles//"+ lns.get(0).getClusterMetrics().getMdg().getName()));
//			StringBuilder sb = new StringBuilder();
//		    String line = br.readLine();
//		    
//		    while (line != null) {
//		    	String [] a = line.split("  ");
//		    	if (lns.get(0).getClusterMetrics().getMdg().getModuleNames().indexOf(a[0])!=-1) {
//		    		a[0] = "PKG" + cluster.get(lns.get(0)	.getClusterMetrics().getMdg().getModuleNames().indexOf(a[0])) + "."+ a[0];	
//		    	}else a[0] = "PKG-1."+ a[0];
//		    	
//		    	if (lns.get(0).getClusterMetrics().getMdg().getModuleNames().indexOf(a[1])!=-1) {
//		    		a[1] = "PKG" + cluster.get(lns.get(0).getClusterMetrics().getMdg().getModuleNames().indexOf(a[1])) + "."+ a[1];	
//		    	}else a[1] = "PKG-1."+ a[1];
//		    	
//		    	sb.append(a[0] + "  " + a[1]);
//		    	
//		        sb.append(System.lineSeparator());
//		        line = br.readLine();
//		    }		    
//		    
//		    File file = new File("data//clusterdepfile//"+ lns.get(0).getClusterMetrics().getMdg().getName());
//		    BufferedWriter writer = null;
//	        writer = new BufferedWriter(new FileWriter(file));
//	        writer.write(sb.toString());
//	    
//	        if (writer != null) writer.close();
//		    
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		   
//		} finally {
//		    br.close();
//		}
	}
	
	public MDGSimplifier getmDGSimplifier() {
		return mDGSimplifier;
	}


	public void setmDGSimplifier(MDGSimplifier mDGSimplifier) {
		this.mDGSimplifier = mDGSimplifier;
	}

	public List<Integer> getCluster() {
		return cluster;
	}

	public void setCluster(Vector<Integer> cluster) {
		this.cluster = cluster;
	}
}
