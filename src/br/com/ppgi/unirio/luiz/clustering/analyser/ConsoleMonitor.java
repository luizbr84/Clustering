/*
 * Created on 23.03.2014
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.com.ppgi.unirio.luiz.clustering.analyser;

import org.pf.tools.cda.core.processing.IProgressMonitor;
import org.pf.util.StopWatch;

class ConsoleMonitor implements IProgressMonitor
{
  private StopWatch stopWatch = new StopWatch();
  
  public ConsoleMonitor()
  {
    super();
  }
  
  @Override
  public void startProgressMonitor()
  {
    stopWatch.start();
    System.out.println("=== Start of workset processing ===");
  }
  @Override
  public void terminateProgressMonitor()
  {
    stopWatch.stop();
    System.out.println("=== End of workset processing. Duration: " + stopWatch.getDuration() + " ms ===");
    System.out.flush();
  }
  @Override
  public boolean showProgress(int value, Object[] info)
  {
    for (Object object : info)
    {          
      System.out.print("Processing: ");
      System.out.println(object);
    }
    return true;
  }
}