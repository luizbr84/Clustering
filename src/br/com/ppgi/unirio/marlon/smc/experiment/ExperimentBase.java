package br.com.ppgi.unirio.marlon.smc.experiment;

import br.com.ppgi.unirio.marlon.smc.experiment.output.ResultWriter;
import br.com.ppgi.unirio.marlon.smc.instance.file.InstanceFileWorker;
import br.com.ppgi.unirio.marlon.smc.instance.file.InstanceParseException;
import br.com.ppgi.unirio.marlon.smc.instance.file.bunch.BunchInstanceFileWorker;
import br.com.ppgi.unirio.marlon.smc.mdg.ModuleDependencyGraph;
import java.io.File;
import java.io.IOException;
//import org.junit.Test;
import random.number.generator.RandomWrapper;


/**
 * Configurações base para execução dos experimentos com os algoritmos
 * @author kiko
 */
public abstract class ExperimentBase {
        
    protected static final ResultWriter.OUTPUT OUTPUT_TO = ResultWriter.OUTPUT.FILE;
    protected static final String[] INSTANCES_FOLDERS = new String[]{"_monografia_base","_all","_all_alexandre","_mitchell_examples","_unirio","perdi_para_alexandre","usadas-em-qq-ref", "_marica_fanpa","_formark","fake"};
    
//    protected String INSTANCES_FOLDER  = INSTANCES_FOLDERS[1];//8
    protected String INSTANCES_FOLDER  = "data\\depTextFiles\\";//8
    
    protected final InstanceFileWorker<ModuleDependencyGraph> INSTANCE_WORKER = new BunchInstanceFileWorker(INSTANCES_FOLDER);
    
    protected int RUN_TIMES;
    protected int BEGIN_INSTANCE;
    protected int TOTAL_INSTANCE;
    
    protected  ResultWriter out;
    
    public ExperimentBase() {
        this.BEGIN_INSTANCE = 0;
        this.TOTAL_INSTANCE = Integer.MAX_VALUE;
        this.RUN_TIMES = 1;
    }
	

    protected long beginTestTimestamp;
//    @Test
    public void runExperiment() throws InstanceParseException, IOException{
        beginTestTimestamp = System.currentTimeMillis();
        File[] instances = INSTANCE_WORKER.retrieveAllInstanceFiles();//leitura das instancias
        
        System.out.println("INSTANCE;MQ;TEMPO");
        for(int index=BEGIN_INSTANCE;index<instances.length && index-BEGIN_INSTANCE < TOTAL_INSTANCE;index++){//para cada instancia
            ModuleDependencyGraph mdg = INSTANCE_WORKER.readInstanceFile(instances[index]);
            runAlgorithm(mdg);
            
            afterEachInstance();
        }
        
        afterAll();
    }

    /**
     * Executado após o final de cada instância
     */
    protected void afterEachInstance(){
        System.out.println("REINICIANDO RANDOM");
        RandomWrapper.restart();
        //coolDown(2);
    }
    
    /**
     * Execucado após o final de cada repetição do processo
     */	
    protected void afterEachTime(){
        //coolDown(1);
    }
    
    protected void afterAll(){
        
    }
    
    protected abstract int[] runAlgorithm(ModuleDependencyGraph mdg);
    protected abstract String testName();
    
    /**
     * Deixa o aplicativo dormindo por time segundos
     * @param time - segundos que a aplicação ficará dormindo
     */
    private void coolDown(int time){
        try{
            System.out.println("DORMINDO por: " + time + " segundos");
            Thread.sleep(time * 1000);
        }catch(Exception e){
            
        }
    }
    
}
