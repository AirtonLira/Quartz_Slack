package salema.monitoria.run;

import java.io.IOException;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;



import salema.monitoria.agent.AgentBackup;




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

}

