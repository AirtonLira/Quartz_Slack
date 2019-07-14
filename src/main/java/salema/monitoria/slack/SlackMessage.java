package salema.monitoria.slack;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@AllArgsConstructor
@Builder(builderClassName = "Builder")	
@Getter
@Setter
public class SlackMessage implements Serializable {

	public String text;


}