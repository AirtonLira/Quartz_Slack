package salema.monitoria.agent;


import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("servidores")
public class Servidores {
	public List<String> endereco;
	public List<String> descricao_on;
	public List<String> descricao_off;
	public String slack;
	
	
 
	
	
}


