package br.com.ppgi.unirio.luiz.clustering.model;

/**
 * Tipo de um elemento em um programa
 * 
 * @author Marcio Barros
 */
public enum ElementType
{
	CLASS ("class"),
	ENUM ("enum"),
	INTERFACE ("interface"),
	ANNOTATION ("annotation");

	private final String identifier;

	/**
	 * Inicializa um tipo de elemento
	 */
	ElementType(String id)
	{
		this.identifier = id;
	}

	/**
	 * Retorna o identificador do tipo de elemento
	 */
	public String getIdentifier()
	{
		return identifier;
	}
	
	/**
	 * Retorna um tipo de elemento, dado um identificador
	 */
	public static ElementType fromIdentifier(String id)
	{
		for (ElementType type: ElementType.values())
			if (type.getIdentifier().compareToIgnoreCase(id) == 0)
				return type;
		
		return null;
	}
}