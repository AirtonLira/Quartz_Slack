package salema.monitoria.slack;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SlackUtils {
    private static String slackWebhookUrl = "https://hooks.slack.com/services/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";
	
    
	
    public static void sendMessage(SlackMessage message) {
	
        CloseableHttpClient client = HttpClients.createDefault();
	
        HttpPost httpPost = new HttpPost(slackWebhookUrl);
	
	
        try {
	
            ObjectMapper objectMapper = new ObjectMapper();
	
            String json = objectMapper.writeValueAsString(message);
	
            System.out.println(json);
            StringEntity entity = new StringEntity(json);
	
            httpPost.setEntity(entity);
	
            httpPost.setHeader("Accept", "application/json");
	
            httpPost.setHeader("Content-type", "application/json");
	

	
            CloseableHttpResponse retorno = client.execute(httpPost);
            
            System.out.println(retorno.getStatusLine());
	
            client.close();
	
        } catch (IOException e) {
	
           e.printStackTrace();
	
        }
	
    }
}
