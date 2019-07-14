package salema.monitoria.run;

import java.io.IOException;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;



import salema.monitoria.agent.AgentBackup;




public class RunApp {


	public static void main(String[] args) throws IOException, SchedulerException {

        
	        // Monta o job com base na classe que possui o execute de start
	        AgentBackup objJob = new AgentBackup();
	        JobDetail job1 = JobBuilder.newJob(objJob.getClass()).build();


	        
	        //Cria agendamento do job que será executado para sempre de 3 em 3 segundos
	        SimpleScheduleBuilder agendamento = SimpleScheduleBuilder.simpleSchedule();
	        agendamento.withIntervalInMinutes(5);
	        agendamento.repeatForever();
	        
	        // Cria o disparo com base no agendamento
	        Trigger disparo = TriggerBuilder.newTrigger().withIdentity("TrgBackup").withSchedule(agendamento).build();
	        
	        
	        //Cria o scheduler oficial que recebe o job e o agendamento
	        Scheduler scheduleoficial = new StdSchedulerFactory().getScheduler();
	        
	       

	        scheduleoficial.start();
	        scheduleoficial.scheduleJob(job1, disparo);

		 
	}

}

