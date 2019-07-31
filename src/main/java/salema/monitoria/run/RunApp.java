package salema.monitoria.run;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import salema.monitoria.agent.AgentBackup;
import salema.monitoria.agent.Servidores;


public class RunApp {


	public static void main(String[] args) throws IOException, SchedulerException {
        
	        // Monta o job com base na classe que possui o execute de start
	        AgentBackup objJob = new AgentBackup();
	        JobDetail job1 = JobBuilder.newJob(objJob.getClass()).build();


	             
	        
	        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("greattrigger", "mygroup")
	        		    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)
	        	    		.repeatForever()
	        	    		.withIntervalInSeconds(5)).build();
	 	        
	        
	        //Cria o scheduler oficial que recebe o job e o agendamento
	        Scheduler scheduleoficial = new StdSchedulerFactory().getScheduler();
	        
	       

	        scheduleoficial.start();
	        scheduleoficial.scheduleJob(job1, trigger);

		 
	}
	
	public static Servidores xmlToProduto(List<String> xml) {
	    XStream xstream = new XStream();
		xstream.addPermission(AnyTypePermission.ANY);
		
	 
	    String totalxml = "";
	    xstream.alias("servidores", Servidores.class);
	    for(int x = 0; x < xml.size(); x++) {
	    	totalxml += xml.get(x);
	    }
	    return (Servidores) xstream.fromXML(totalxml);
	}
	
	

}

