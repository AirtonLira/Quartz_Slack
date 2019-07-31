package salema.monitoria.agent;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import salema.monitoria.run.HttpURLConnectionStatus;
import salema.monitoria.run.RunApp;
import salema.monitoria.slack.SlackMessage;
import salema.monitoria.slack.SlackUtils;

public class AgentBackup implements Job{
	
	@SuppressWarnings("static-access")
	public void execute(JobExecutionContext arg0) throws JobExecutionException{
		SlackMessage myMessage = new SlackMessage();
		Calendar c = Calendar.getInstance();
		
		
	     String file ="src/main/resources/config.xml";
	      
	     List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     
	     Servidores oxml = new Servidores();
	     oxml = RunApp.xmlToProduto(lines);
	     String slackend = oxml.slack;
		
		HttpURLConnectionStatus requisicao = new HttpURLConnectionStatus();
		for (int x = 0; x < oxml.endereco.size(); x++) {
			try {
				if (!requisicao.sendGET(oxml.endereco.get(x))) {
					myMessage.text = oxml.descricao_off.get(x)+" "+c.getTime();
					SlackUtils.sendMessage(myMessage,"https://hooks.slack.com/services/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");
				}else {
					myMessage.text = oxml.descricao_on.get(x)+" "+c.getTime();
					SlackUtils.sendMessage(myMessage,"https://hooks.slack.com/services/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");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	
		
	}

}
