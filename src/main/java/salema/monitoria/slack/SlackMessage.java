package salema.monitoria.slack;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@SuppressWarnings("serial")
@AllArgsConstructor
@Builder(builderClassName = "Builder")	
@Getter
@Setter
public class SlackMessage implements Serializable {

	public SlackMessage() {
		// TODO Auto-generated constructor stub
	}

	public String text;


}