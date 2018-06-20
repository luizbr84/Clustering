package br.com.ppgi.unirio.marlon.smc.experiment.algorithm;

import br.com.ppgi.unirio.luiz.clustering.analyser.LNSInterpreter;
import br.com.ppgi.unirio.marlon.smc.experiment.ExperimentBase;
import br.com.ppgi.unirio.marlon.smc.experiment.output.ResultWriter;
import br.com.ppgi.unirio.marlon.smc.mdg.ClusterMetrics;
import br.com.ppgi.unirio.marlon.smc.mdg.ModuleDependencyGraph;
import br.com.ppgi.unirio.marlon.smc.mdg.simplifier.MDGSimplifier;
import br.com.ppgi.unirio.marlon.smc.solution.algorithm.construtive.InitialSolutionFactory;
import br.com.ppgi.unirio.marlon.smc.solution.algorithm.heuristic.lns.LNSConfiguration;
import br.com.ppgi.unirio.marlon.smc.solution.algorithm.heuristic.lns.LNSConfigurationBuilderFixedRandom;
import br.com.ppgi.unirio.marlon.smc.solution.algorithm.heuristic.lns.LNSConfigurationBuilderRandom;
import br.com.ppgi.unirio.marlon.smc.solution.algorithm.heuristic.lns.LargeNeighborhoodSearch;
import br.com.ppgi.unirio.marlon.smc.solution.algorithm.heuristic.lns.methods.builder.DestrutiveMethodFactory;
import br.com.ppgi.unirio.marlon.smc.solution.algorithm.heuristic.lns.methods.builder.RepairMethodFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LNSParameterTest extends ExperimentBase{

    private static final boolean simplify = true;
    
    private static final int RANDONS_CONFIGS_TO_TEST = 1;//2000//1000;//10000
    private final String PREFIX_NAME;
    
    private final Map<LNSConfigurationBuilderRandom.FILTER_NAMES,Object[]> FIXED_VALUES;
    private final LNSConfigurationBuilderRandom.FILTER_NAMES COMPARE_PARAM;
    private final Object[] COMPARE_PARAM_VALUES;
    
    {
        FIXED_VALUES = new HashMap<>();
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.USE_SIMMULATED_ANNEALING, new Object[]{false});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.TIME_LIMIT, new Object[]{-1});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT,new Object[]{500});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.NO_IMPROVEMENT_LIMIT,new Object[]{-1});
        
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.INITIAL_SOLUTION_METHOD, new Object[]{InitialSolutionFactory.CREATION_METHOD.CAMQ});//experimento 01
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.REPAIR_METHOD, new Object[]{RepairMethodFactory.REPAIR_METHOD.RGBI, RepairMethodFactory.REPAIR_METHOD.RGBIR});//experimento 02
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUTIVE_METHOD, new Object[]{DestrutiveMethodFactory.DESTRUTIVE_METHOD.DR});//experimento 03




//        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUTIVE_METHOD, new Object[]{DestrutiveMethodFactory.DESTRUTIVE_METHOD.DR});//parametro 4
  //      FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR, new Object[]{0.05f/*, 0.1f*/});//parametro 6
    //    FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.INITIAL_TEMPERATURE_RATIO, new Object[]{0.01f/*, 0.025f*/});//parametro 7
      //  FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.COOOLING_RATE, new Object[]{0.8f});//parametro 8
/**/
        
        
        //valores que serão comaprados
        /*
        PREFIX_NAME = "PARAM_01";
        COMPARE_PARAM = LNSConfigurationBuilderRandom.FILTER_NAMES.INITIAL_SOLUTION_METHOD;
        COMPARE_PARAM_VALUES = new Object[InitialSolutionFactory.totalKnownAlgorithms()];
        for( int i=0;i< InitialSolutionFactory.totalKnownAlgorithms();i++){
            COMPARE_PARAM_VALUES[i] = InitialSolutionFactory.getMethod(i);
        }/**/
        
        /*
        PREFIX_NAME = "PARAM_02";
        COMPARE_PARAM = LNSConfigurationBuilderRandom.FILTER_NAMES.REPAIR_METHOD;
        COMPARE_PARAM_VALUES = new Object[RepairMethodFactory.totalKnownAlgorithms()];
        for( int i=0;i< RepairMethodFactory.totalKnownAlgorithms();i++){
            COMPARE_PARAM_VALUES[i] = RepairMethodFactory.getMethod(i);
        }/**/
        
        /*
        PREFIX_NAME = "PARAM_03";
        COMPARE_PARAM = LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUTIVE_METHOD;
        COMPARE_PARAM_VALUES = new Object[DestrutiveMethodFactory.totalKnownAlgorithms()];
        for( int i=0;i< DestrutiveMethodFactory.totalKnownAlgorithms();i++){
            COMPARE_PARAM_VALUES[i] = DestrutiveMethodFactory.getMethod(i);
        }/**/
        
        /*
        PREFIX_NAME = "PARAM_04";
        COMPARE_PARAM = LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR;
        COMPARE_PARAM_VALUES = LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES;
        /**/
        
        /*
        //busca variavel... (quase um ALNS)
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{100});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR, new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[1], LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[2]});//experimento 04 - 0,1 e 0,15
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR_PROBABILITY, new Object[]{8,2});//experimento 04 - 0,1 e 0,15
        PREFIX_NAME = "ALL_FIXED_01_MIXED";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
        /**/
        
         /*
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{-1});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR, new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[1]});
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.REPAIR_METHOD);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.REPAIR_METHOD, new Object[]{RepairMethodFactory.REPAIR_METHOD.RGBIR});
        PREFIX_NAME = "ALL_FIXED_02_RGBIR_010";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
        /**/
        
          /*
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{-1});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR, new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[2]});
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.REPAIR_METHOD);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.REPAIR_METHOD, new Object[]{RepairMethodFactory.REPAIR_METHOD.RGBIR});
        PREFIX_NAME = "ALL_FIXED_03_RGBIR_015";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
        /**/
        
        /*
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{-1});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR, new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[2]});
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.REPAIR_METHOD);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.REPAIR_METHOD, new Object[]{RepairMethodFactory.REPAIR_METHOD.RGBI});
        PREFIX_NAME = "ALL_FIXED_04_RGBI_015";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
        /**/
        
        /*
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{-1});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR, new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[1]});
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.REPAIR_METHOD);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.REPAIR_METHOD, new Object[]{RepairMethodFactory.REPAIR_METHOD.RGBI});
        PREFIX_NAME = "ALL_FIXED_05_RGBI_010";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
        /**/
        
        /*
        //busca variavel... (quase um ALNS)
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{200});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR, new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[1], LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[2]});//experimento 04 - 0,1 e 0,15
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR_PROBABILITY, new Object[]{8,2});//
        PREFIX_NAME = "ALL_FIXED_06_MIXED";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
        /**/
        
        /*
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT,new Object[]{-1});//sem limite de iteração
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{100});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR,new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[1]});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR_PROBABILITY, new Object[]{1});
        PREFIX_NAME = "ALL_FIXED_07_MIXED_100_NO_LIMIT";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
        /**/
        
        /*
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT,new Object[]{-1});//sem limite de iteração
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{150});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR,new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[1]});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR_PROBABILITY, new Object[]{1});
        PREFIX_NAME = "ALL_FIXED_08_MIXED_150_NO_LIMIT";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
        /**/
        
        /*
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT,new Object[]{-1});//sem limite de iteração
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{200});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR,new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[1]});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR_PROBABILITY, new Object[]{1});
        PREFIX_NAME = "ALL_FIXED_09_MIXED_200_NO_LIMIT";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
                /**/
        /*
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT,new Object[]{-1});//sem limite de iteração
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{300});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR,new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[1]});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR_PROBABILITY, new Object[]{1});
        PREFIX_NAME = "ALL_FIXED_10_MIXED_300_NO_LIMIT";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
                /**/
        /*
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT,new Object[]{-1});//sem limite de iteração
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{400});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR,new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[1]});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR_PROBABILITY, new Object[]{1});
        PREFIX_NAME = "ALL_FIXED_11_MIXED_400_NO_LIMIT";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
                /**/
        /*
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT,new Object[]{-1});//sem limite de iteração
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{500});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR,new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[1]});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR_PROBABILITY, new Object[]{1});
        PREFIX_NAME = "ALL_FIXED_12_MIXED_500_NO_LIMIT";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
                /**/
        
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT,new Object[]{-1});//sem limite de iteração
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{1000});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR,new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[1]});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR_PROBABILITY, new Object[]{1});
        //PREFIX_NAME = "ALL_FIXED_13_MIXED_1000_NO_LIMIT";
        //PREFIX_NAME = "ALL_FIXED_18_MIXED_1000_NO_LIMIT";
        PREFIX_NAME = "ALL_FIXED_18_MIXED_1000_NO_LIMIT_DETAILS";
        
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
                /**/
        
        /*
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT,new Object[]{1000});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{-1});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR, new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[1]});
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.REPAIR_METHOD);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.REPAIR_METHOD, new Object[]{RepairMethodFactory.REPAIR_METHOD.RGBIR});
        PREFIX_NAME = "ALL_FIXED_14_RGBIR_010_1000";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
        /**/
        
        /*
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT,new Object[]{1000});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{-1});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR, new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[1]});
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.REPAIR_METHOD);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.REPAIR_METHOD, new Object[]{RepairMethodFactory.REPAIR_METHOD.RGBI});
        PREFIX_NAME = "ALL_FIXED_15_RGBI_010_1000";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
        /**/
        
        /*
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT,new Object[]{-1});//sem limite de iteração
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{1000});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR,new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[1]});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR_PROBABILITY, new Object[]{1});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.MIXED_RESTART,new Object[]{false});
        PREFIX_NAME = "ALL_FIXED_16_MIXED_1000_NO_RESTART_";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
        /**/
        /*
        FIXED_VALUES.remove(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT);
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ITERATION_LIMIT,new Object[]{-1});//sem limite de iteração
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT,new Object[]{1000});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR,new Object[]{LNSConfigurationBuilderFixedRandom.DESTRUCTION_FACTOR_VALUES[1]});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.DESTRUCTION_FACTOR_PROBABILITY, new Object[]{1});
        FIXED_VALUES.put(LNSConfigurationBuilderRandom.FILTER_NAMES.ALGORITHM_NO_IMPROVEMENT_LIMIT_REDUCTION_FACTOR,new Object[]{0.5});
        PREFIX_NAME = "ALL_FIXED_17_MIXED_1000_RESTART_FACTOR_0.5";
        COMPARE_PARAM=LNSConfigurationBuilderRandom.FILTER_NAMES.NONE;
        COMPARE_PARAM_VALUES=new Object[] {""};
        /**/
        
        
        //config 18 está junto com a 13
        
    }
    public LNSParameterTest(){
        RUN_TIMES = 100;//100;//30
        //BEGIN_INSTANCE = 18;
        //TOTAL_INSTANCE = 1;
    }
    
    @Override
    protected int[] runAlgorithm(ModuleDependencyGraph mdg) {
   
    	
    	LNSInterpreter lnsI = new LNSInterpreter(); //luiz antonio
    	
        LNSConfigurationBuilderRandom configurationBuilder = new LNSConfigurationBuilderFixedRandom();
        String fixedParam = COMPARE_PARAM.toString();
        try {
            String simplifiedS = "";
            if(simplify){
                simplifiedS = "_SIMPLIFIED";
            }
            String outPath = PREFIX_NAME+"_"+fixedParam+simplifiedS+"/"+"data";
            out = ResultWriter.configureResultWriter(OUTPUT_TO,outPath, mdg.getName());
        } catch (IOException ex) {
        }
        
        System.out.println(mdg.getName());
        if(simplify){
            MDGSimplifier mDGSimplifier = MDGSimplifier.simplify(mdg);
            mdg = mDGSimplifier.getMdg();
            
            
            lnsI.setmDGSimplifier(mDGSimplifier);//luiz antonio
//            System.out.println(mDGSimplifier.getRemovedAsString());//luiz antonio
        }
        
        for (int configN=0;configN<RANDONS_CONFIGS_TO_TEST;configN++){
            LNSConfiguration config = configurationBuilder.buildRandomConfiguration(mdg, FIXED_VALUES);
            System.out.print("CONFIG: "+configN);
            
            for (Object currentValue : COMPARE_PARAM_VALUES) {//executar para cada configuração especifica
                configurationBuilder.changeParameterValue(config, COMPARE_PARAM, currentValue);//acertar a config com o parametro atual    
                System.out.print(" PARAM: "+currentValue.toString());
                for(int execution=0;execution<RUN_TIMES;execution++){                    
                    //System.out.println(execution);
                    LargeNeighborhoodSearch lns = new LargeNeighborhoodSearch(config);
                    lns.execute();//executa o algoritmo -> serão salvos os status
                    saveSearchStatus(out, mdg, lns, configN, execution, currentValue);
                    
                    lnsI.addLNS(lns, config);//luiz antonio
//                    System.out.println(lns.getBestSolutionFound().getSolutionAsString());//luiz antonio
                }
            }
            System.out.println("");
        }
        try {
			lnsI.generate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        out.close();
        return null;
    }
    
    private void saveSearchStatus(ResultWriter out, ModuleDependencyGraph mdg, LargeNeighborhoodSearch lns, int configN, int executionN, Object currentValue){
        ClusterMetrics cm = lns.getBestSolutionFound();
        out.writeLine(COMPARE_PARAM.toString(), currentValue.toString(), lns.getConfig().toString(), configN+"", executionN+"",lns.getInitialSolutionCost()+"", lns.getBestCost()+"", lns.getBestSolutionIteration()+"", lns.getBiggestNoImprovementGap()+"", lns.getTimeElapsed()+"", lns.getClusterMetrics().getTotalClusteres()+"",lns.getLastIteration()+""
                ,cm.getBiggestClusterSize()+"",cm.getSmallestClusterSize()+"",cm.getIsolatedClusterCount()+"",cm.smallestClusterMF()+"",cm.biggestClusterMF()+""
        ,cm.getSolutionAsString());
    }
    
    
  
    @Override
    protected String testName() {
        return "LNS_PARAMETER_TEST";
    }

}
