package br.com.ppgi.unirio.luiz.clustering.analyser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.pf.tools.cda.base.model.ClassInformation;

import com.googlecode.d2j.dex.Dex2jar;
import com.googlecode.d2j.dex.DexExceptionHandler;
import com.googlecode.d2j.reader.DexFileReader;

import br.com.ppgi.unirio.luiz.clustering.calculator.MQCalculator;
import br.com.ppgi.unirio.luiz.clustering.model.Dependency;
import br.com.ppgi.unirio.luiz.clustering.model.Project;
import br.com.ppgi.unirio.luiz.clustering.model.ProjectClass;
import br.com.ppgi.unirio.luiz.clustering.model.ProjectPackage;
import br.com.ppgi.unirio.luiz.clustering.mojo.MoJo;
import br.com.ppgi.unirio.marlon.smc.experiment.algorithm.LNSParameterTest;
import br.com.ppgi.unirio.marlon.smc.instance.file.InstanceParseException;

@SuppressWarnings("unused")
public class MainProgram
{
	private static final int CYCLES = 10;	
	
	private static List<String> instanceFilenames = new ArrayList<String>();
			
	private static Vector<Project> readJarInstances(List<String> instanceFilenamesArray) throws Exception
	{
		Vector<Project> instances = new Vector<Project>();
		
		CDAJarReader reader = new CDAJarReader();
				
//		reader.framework.add("android");
		reader.framework.add("android.accessibilityservice");
		reader.framework.add("android.accounts");
		reader.framework.add("android.animation");
		reader.framework.add("android.annotation");
		reader.framework.add("android.app");
		reader.framework.add("android.appwidget");
		reader.framework.add("android.arch.core.executor.testing");
		reader.framework.add("android.arch.core.util");
		reader.framework.add("android.arch.lifecycle");
		reader.framework.add("android.arch.paging");
		reader.framework.add("android.arch.persistence.db");
		reader.framework.add("android.arch.persistence.room");
		reader.framework.add("android.bluetooth");
		reader.framework.add("android.bluetooth.le");
		reader.framework.add("android.companion");
		reader.framework.add("android.content");
		reader.framework.add("android.database");
		reader.framework.add("android.drm");
		reader.framework.add("android.gesture");
		reader.framework.add("android.graphics");
		reader.framework.add("android.hardware");
		reader.framework.add("android.icu.lang");
		reader.framework.add("android.icu.math");
		reader.framework.add("android.icu.text");
		reader.framework.add("android.icu.util");
		reader.framework.add("android.inputmethodservice");
		reader.framework.add("android.location");
		reader.framework.add("android.media");
		reader.framework.add("android.mtp");
		reader.framework.add("android.net");
		reader.framework.add("android.nfc");
		reader.framework.add("android.opengl");
		reader.framework.add("android.os");
		reader.framework.add("android.preference");
		reader.framework.add("android.print");
		reader.framework.add("android.print.pdf");
		reader.framework.add("android.printservice");
		reader.framework.add("android.provider");
		reader.framework.add("android.renderscript");
		reader.framework.add("android.sax");
		reader.framework.add("android.se.omapi");
		reader.framework.add("android.security");
		reader.framework.add("android.service.autofill");
		reader.framework.add("android.service.carrier");
		reader.framework.add("android.service.chooser");
		reader.framework.add("android.service.dreams");
		reader.framework.add("android.service.media");
		reader.framework.add("android.service.notification");
		reader.framework.add("android.service.quicksettings");
		reader.framework.add("android.service.restrictions");
		reader.framework.add("android.service.textservice");
		reader.framework.add("android.service.voice");
		reader.framework.add("android.service.vr");
		reader.framework.add("android.service.wallpaper");
		reader.framework.add("android.speech");
		reader.framework.add("android.support");
		reader.framework.add("android.system");
		reader.framework.add("android.taobao.windvane"); 
		reader.framework.add("android.telecom");
		reader.framework.add("android.telephony");
		reader.framework.add("android.test");
		reader.framework.add("android.text");
		reader.framework.add("android.transition");
		reader.framework.add("android.util");
		reader.framework.add("android.view");
		reader.framework.add("android.webkit");
		reader.framework.add("android.widget");
		reader.framework.add("androidx.browser.browseractions");
		reader.framework.add("androidx.heifwriter");
		reader.framework.add("androidx.recyclerview.selection");
		reader.framework.add("androidx.slice");
		reader.framework.add("androidx.textclassifier");
		reader.framework.add("com.android.billingclient.api");
		reader.framework.add("com.android.billingclient.util");
		reader.framework.add("com.android.email");
		reader.framework.add("com.android.ex");				
		reader.framework.add("com.android.exchange");
		reader.framework.add("com.android.inputmethod");
		reader.framework.add("com.android.internal.util");
		reader.framework.add("com.android.mail");
		reader.framework.add("com.baidu.simeji");
		reader.framework.add("com.bumptech");
		reader.framework.add("com.bumptech");
		reader.framework.add("com.chad.library");
		reader.framework.add("com.commerce");
		reader.framework.add("com.facebook");
		reader.framework.add("com.fasterxml");
		reader.framework.add("com.github");
		reader.framework.add("com.google.ads");
		reader.framework.add("com.google.ads");
		reader.framework.add("com.google.android");
		reader.framework.add("com.google.api");
		reader.framework.add("com.google.common");
		reader.framework.add("com.google.firebase");
		reader.framework.add("com.google.gson");
		reader.framework.add("com.google.zxing");
		reader.framework.add("com.inmobi");
		reader.framework.add("com.startapp.android");
		reader.framework.add("com.twitter");
		reader.framework.add("cz.msebera.android.httpclient");
		reader.framework.add("dalvik.annotation");
		reader.framework.add("dalvik.bytecode");
		reader.framework.add("dalvik.system");
		reader.framework.add("java.awt.font");
		reader.framework.add("java.beans");
		reader.framework.add("java.io");
		reader.framework.add("java.lang");
		reader.framework.add("java.math");
		reader.framework.add("java.net");
		reader.framework.add("java.nio");
		reader.framework.add("java.security");
		reader.framework.add("java.sql");
		reader.framework.add("java.text");
		reader.framework.add("java.time");
		reader.framework.add("java.util");
		reader.framework.add("javax");
		reader.framework.add("junit.framework");
		reader.framework.add("junit.runner");
		reader.framework.add("org.apache");
		reader.framework.add("org.checkerframework");
		reader.framework.add("org.codehaus.jackson");
		reader.framework.add("org.jasypt");
		reader.framework.add("org.json");
		reader.framework.add("org.jsoup");
		reader.framework.add("org.junit");
		reader.framework.add("org.mozilla");
		reader.framework.add("org.qiyi");
		reader.framework.add("org.simpleframework");
		reader.framework.add("org.w3c.dom");
		reader.framework.add("org.xml.sax");
		reader.framework.add("org.xmlpull.v1");
		reader.framework.add("retrofit");
		
		reader.framework.add("com.android.vending.billing");
		reader.framework.add("com.crashlytics");
		reader.framework.add("io.fabric");
		reader.framework.add("com.mopub");
		reader.framework.add("com.chartboost");
		reader.framework.add("com.duapps.ad");
		reader.framework.add("com.unity3d");
		reader.framework.add("com.appodeal");
	
		for (String filename : instanceFilenamesArray)
			if (filename.length() > 0)
				instances.add (reader.execute(filename));
		
		return instances;
	}	
	
	public static final void main(String[] args) throws Exception
	{				
//		Vector<Project> projectInstances = new Vector<Project>();
//		projectInstances = runProjectsReading();
//		runProjectClassCombinationExport(projectInstances, false);
	
//		runLNSPExperiment();
//		runMOJOComparison("data\\2-originalCombination\\joda-money-0.12-originalCombination-17042018183342", "data\\3-finalCombination\\joda-money", "-fm");
//		runMOJOComparison("data\\2-originalCombination\\joda-money-0.12-originalCombination-17042018183342", "data\\2-originalCombination\\joda-money-0.12-originalCombination-17042018183342", "-fm");
//		runMOJOComparison("data\\3-finalCombination\\joda-money", "data\\3-finalCombination\\joda-money", "-fm");
		runMOJOComparison("data\\2-originalCombination\\distra2.rsf", "data\\2-originalCombination\\distra1.rsf", "-fm");
		
//		runProjectOverallAnalisis(projectInstances);		
//		runProjectDependencyExport(projectInstances);
	}

	private static void runProjectOverallAnalisis(Vector<Project> projectsInstances) throws Exception {
		FileOutputStream fos = new FileOutputStream("data\\output" + getStringTime() + ".txt" );
		OutputStreamWriter bw = new OutputStreamWriter(fos);
		
		for (int i = 0; i < projectsInstances.size(); i++)
		{	
			bw.write(System.getProperty("line.separator"));
			bw.write(System.getProperty("line.separator"));
			String s1 = projectsInstances.get(i).getName() + "\t" + "ClassCount: " + "\t" +  projectsInstances.get(i).getClassCount() + "\t" +"MQ:" +"\t" +new MQCalculator(projectsInstances.get(i)).calculateModularizationQuality();
			bw.write("+" + s1 + System.getProperty("line.separator"));
			bw.flush();
			System.out.println(s1);
			for (ProjectPackage projectPackage : projectsInstances.get(i).getPackages()) {
				String s2 = projectPackage.getName();
				bw.write(s2 + System.getProperty("line.separator"));
				bw.flush();
				System.out.println(projectPackage.getName());	
			}
		}
		bw.close();
	}
	
	private static Vector<Project> runProjectsReading() throws Exception {
		//
		/*setting the folder for app.jar file after the convertion from .apk to .jar */
		File jarFilesFolder = new File("data\\1-input-jar\\"); // current directory
			
		/*starting the extraction of dependency relationship */
		try {
			/*loading the files from the specified folder for the jar files folder*/
			File[] files = jarFilesFolder.listFiles(new FilenameFilter(){
				
				/*filtering the files to guarantee that only .jar files will be listed */
				@Override
				public boolean accept(File dir, String name) {
					boolean result;
					if(name.endsWith(".jar")){
						result=true;
					}
					else{
						result=false;
					}
					return result;
				}
			});
			/*looping throw the files to add the .jar files*/
			for (File file : files) {
				if (!file.isDirectory()) {
					instanceFilenames.add(file.getCanonicalPath());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*creating a vector of project to load the instance jar file data*/
		Vector<Project> instances = new Vector<Project>();
		instances.addAll(readJarInstances(instanceFilenames));
		
		return instances;
	}
	
	private static void runLNSPExperiment() throws InstanceParseException, IOException {
		LNSParameterTest LNSP = new LNSParameterTest();
		LNSP.runExperiment();
	}
	
	private static void runProjectDependencyExport(Vector<Project> projectsInstances) throws InstanceParseException, IOException {		
		for (int i = 0; i < projectsInstances.size(); i++)
		{
			FileOutputStream fos = new FileOutputStream("data\\depTextFiles\\" + projectsInstances.get(i).getName() + ".depTextFiles");
			OutputStreamWriter osw = new OutputStreamWriter(fos);

			for (ProjectClass projectClass : projectsInstances.get(i).getClasses()) {
				for (Dependency dependencyProjectClass : projectClass.getDependencies()){
					osw.write( projectClass.getName() + "  "  + dependencyProjectClass.getElementName()   + System.getProperty("line.separator"));
					osw.flush();
//					System.out.println(projectPackage.getName());			
				}
			}
			osw.close();
		}
	}
	
	private static void runProjectClassCombinationExport(Vector<Project> projectsInstances, boolean packageName) throws InstanceParseException, IOException {		
		for (int i = 0; i < projectsInstances.size(); i++){
			StringBuilder sb = new StringBuilder();
		
//		    for(ProjectPackage projectPackage: projectsInstances.get(i).getPackages()) {
//		    	Vector<ProjectClass>  projectClasses1 = projectsInstances.get(i).getClasses(projectPackage);
//		    	Vector<ProjectClass>  projectClasses2 = projectsInstances.get(i).getClasses(projectPackage);
//		    	for(ProjectClass projectClass1: projectClasses1) {
//		    		projectClasses2.remove(projectClass1);
//		    		for(ProjectClass projectClass2: projectClasses2) {
//		    			if (packageName) {
//		    				sb.append(projectPackage.getName() +" " + projectClass1.getName() + " "+ projectClass2.getName());	
//		    			}
//		    			else {
//		    				sb.append(projectClass1.getName() + " "+ projectClass2.getName());
//		    			}
//		    			sb.append(System.lineSeparator());
//		    		}	    		
//		    	}
//		    }
		    
		    for(ProjectPackage projectPackage: projectsInstances.get(i).getPackages()) {
		    	for(ProjectClass projectClass1: projectsInstances.get(i).getClasses(projectPackage)) {		    			
					sb.append(projectPackage.getName() + " " + projectClass1.getName());
					sb.append(System.lineSeparator());
		    				
		    	}
		    }
			
		    File file = new File("data//2-originalCombination//"+ projectsInstances.get(i).getName()+ "-originalCombination-" + getStringTime());
		    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		    try {
		        writer.write(sb.toString());	    
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   
			} finally {
				writer.close();
			}
		}
	}

	
	private static void runMOJOComparison(String file1, String file2, String param) throws InstanceParseException, IOException {
		System.out.println("runMOJOComparison");
		String[] args1 = new String[3];
		args1[0] = file1;
		args1[1] = file2;
		args1[2] = param; 
		MoJo.main(args1);
	}
	
	
	private static String getStringTime() {
		Calendar data;
		data = Calendar.getInstance();
		int segundo = data.get(Calendar.SECOND);
        int minuto = data.get(Calendar.MINUTE);
        int hora = data.get(Calendar.HOUR_OF_DAY);
        int dia = data.get(Calendar.DAY_OF_MONTH);	
        int mes = data.get(Calendar.MONTH);;	
        int ano = data.get(Calendar.YEAR);;		
		return  String.format("%02d", dia) + String.format("%02d", mes) + String.format("%04d", ano) +String.format("%02d", hora) + String.format("%02d", minuto) + String.format("%02d", segundo);
	}
}