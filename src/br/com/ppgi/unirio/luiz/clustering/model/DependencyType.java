package br.com.ppgi.unirio.luiz.clustering.model;

/**
 * Tipo de depend�ncia entre duas classes
 * 
 * @author Marcio Barros
 */
public enum DependencyType
{
	USES ("uses"),
	IMPLEMENTS ("implements"),
	ANNOTATION ("annotation"),
	EXTENDS ("extends");
	

	private final String identifier;

	/**
	 * Inicializa um tipo de depend�ncia
	 */
	DependencyType(String id)
	{
		this.identifier = id;
	}

	/**
	 * Retorna o identificador do tipo de depend�ncia
	 */
	public String getIdentifier()
	{
		return identifier;
	}
	
	/**
	 * Retorna um tipo de depend�ncia, dado um identificado
	 */
	public static DependencyType fromIdentifier(String id)
	{
		for (DependencyType type: DependencyType.values())
			if (type.getIdentifier().compareToIgnoreCase(id) == 0)
				return type;
		
		return null;
	}
}