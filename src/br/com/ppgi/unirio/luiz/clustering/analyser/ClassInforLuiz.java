package br.com.ppgi.unirio.luiz.clustering.analyser;

import org.pf.tools.cda.base.model.ClassInformation;
import org.pf.tools.cda.base.model.ClassPackage;

/**
 * Tipo de dependência entre duas classes
 * 
 * @author Marcio Barros
 */
public  abstract class ClassInforLuiz extends ClassInformation
{

	protected ClassInforLuiz(String className, ClassPackage containingPackage) {
		super(className, containingPackage);
		// TODO Auto-generated constructor stub
	}
	
	
}