package br.com.ppgi.unirio.luiz.clustering.model;

import org.pfsw.odem.DependencyClassification;

/**
 * Classe que representa uma depend�ncia entre duas classes
 * 
 * @author Marcio Barros
 */
public class Dependency
{
//	private String elementName;
//	private DependencyType type;
	
	private String elementName;
	private DependencyClassification type;

	/**
	 * Inicializa uma depend�ncia de uma classe para outra
	 */
	public Dependency(String name, DependencyClassification type)
	{
		this.elementName = name;
		this.type = type;
	}
	
	/**
	 * Retorna o nome da classe dependida
	 */
	public String getElementName()
	{
		return elementName;
	}
	
	/**
	 * Retorna o tipo da depend�ncia
	 */
	public DependencyClassification getType()
	{
		return type;
	}
}