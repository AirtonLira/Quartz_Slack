package salema.monitoria.agent;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;

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
		
		
		//Listas com endereços do properties
		ArrayList<String> slacks = new ArrayList<String>();
		ArrayList<String> urls = new ArrayList<String>();
		ArrayList<String> message_on = new ArrayList<String>();
		ArrayList<String> message_off = new ArrayList<String>();
		
		try (InputStream input = new FileInputStream("config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // Obtem os valores do properties
            String arrsurls = prop.getProperty("url.servico");
            String arrslacks = prop.getProperty("url.slack");
            
            String arrsmessage_on = prop.getProperty("message_on");
            String arrsmessage_off = prop.getProperty("message_off");
            
            // Converter para uma lista separando por virgula
            slacks = new ArrayList<String>(Arrays.asList(arrslacks.split(",")));
            urls = new ArrayList<String>(Arrays.asList(arrsurls.split(",")));
            
            // Converter para uma lista separando por ponto virgula mensagens
            message_on = new ArrayList<String>(Arrays.asList(arrsmessage_on.split(";")));
            message_off = new ArrayList<String>(Arrays.asList(arrsmessage_off.split(";")));


        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		HttpURLConnectionStatus requisicao = new HttpURLConnectionStatus();
		for (int x = 0; x < urls.size(); x++) {
			try {
				if (!requisicao.sendGET(urls.get(x))) {
					myMessage.text = message_off.get(x)+" "+c.getTime();
					SlackUtils.sendMessage(myMessage,slacks.get(x));
				}else {
					myMessage.text = message_on.get(x)+" "+c.getTime();
					SlackUtils.sendMessage(myMessage,slacks.get(x));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	
		
	}

}
