package salema.monitoria.agent;

import java.io.IOException;
import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import salema.monitoria.run.HttpURLConnectionStatus;
import salema.monitoria.slack.SlackMessage;
import salema.monitoria.slack.SlackUtils;

public class AgentBackup implements Job{
	
	@SuppressWarnings("static-access")
	public void execute(JobExecutionContext arg0) throws JobExecutionException{
		SlackMessage myMessage = new SlackMessage();
		Calendar c = Calendar.getInstance();
		myMessage.text = "*[ATENCAO]!!!!!!Servico de backup*: esta offline -"+c.getTime();

		String GET_URL = "http://localhost:8080/servicobackup/backupstatus";
		
		
		HttpURLConnectionStatus requisicao = new HttpURLConnectionStatus();
		try {
			if (!requisicao.sendGET(GET_URL)) {
				SlackUtils.sendMessage(myMessage);
			}else {
				myMessage.text = "*Servico de backup*: esta online -"+c.getTime();
				SlackUtils.sendMessage(myMessage);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
