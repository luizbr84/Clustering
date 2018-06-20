package br.com.ppgi.unirio.luiz.clustering.analyser;

import br.com.ppgi.unirio.luiz.clustering.calculator.MQCalculator;
import br.com.ppgi.unirio.luiz.clustering.model.Project;
import jmetal.base.Problem;
import jmetal.base.Solution;
import jmetal.base.Variable;
import jmetal.base.solutionType.IntSolutionType;
import jmetal.util.JMException;

/**
 * Classe que representa o problema de cálculo de acoplamento em um projeto
 * 
 * @author Marcio Barros
 */
public class ClusteringProblem extends Problem
{
	private MQCalculator calculator;
	private int evaluations;

	/**
	 * Inicializa o problema de minimização de acoplamento
	 * @throws Exception 
	 */
	public ClusteringProblem(Project project) throws Exception
	{
		this.calculator = new MQCalculator(project);
		this.evaluations = 0;

		numberOfObjectives_ = 1;
		numberOfConstraints_ = 0;
		numberOfVariables_ = project.getClassCount();
		setVariableLimits(project.getPackageCount());
		
		solutionType_ = new IntSolutionType(this);
		variableType_ = new Class[numberOfVariables_];
		length_ = new int[numberOfVariables_];
		length_[0] = numberOfVariables_;
	}

	/**
	 * Define os limites das variáveis que representa o problema
	 */
	private void setVariableLimits(int maxPackages)
	{
		upperLimit_ = new double[numberOfVariables_];
		lowerLimit_ = new double[numberOfVariables_];

		for (int i = 0; i < numberOfVariables_; i++)
		{
			lowerLimit_[i] = 0;
			upperLimit_[i] = maxPackages - 1;
		}
	}
	
	/**
	 * Aplica uma solução sobre o calculador
	 */
	public void applySolutionToCalculator(Solution solution) throws JMException
	{
		Variable[] sequence = solution.getDecisionVariables();
		calculator.reset();

		for (int i = 0; i < sequence.length; i++)
		{
			int packageIndex = (int) sequence[i].getValue();
			calculator.moveClass(i, packageIndex);
		}
	}

	/**
	 * Calcula os objetivos com uma determinada solução
	 */
	@Override
	public void evaluate(Solution solution) throws JMException
	{
		applySolutionToCalculator(solution);
		solution.setObjective(0, -calculator.calculateModularizationQuality());
		solution.setLocation(evaluations++);
	}
}