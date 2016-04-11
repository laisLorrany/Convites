package br.edu.ifpb.Convidados.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.Convidados.DAO.ConvidadoDAO;
import br.edu.ifpb.Convidados.entidade.Convidado;

@Path("convidado")
public class ConvidadoController {

	private static Logger logger = LogManager.getLogger(ConvidadoController.class);

	@GET
	@Path("/pesquisar/nome/{nome}")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Convidado> pesquisarConvidadoNome(@PathParam("nome") String nome) {
		
		logger.info("Listar Recurso: " + nome);		

		List<Convidado> convidados = ConvidadoDAO.getInstance().getByName(nome);
		
		logger.info("Convidados: " + convidados.size() + "[" + convidados + "]");

		return convidados;
	}

	@POST
	@Path("/pesquisar/qrcode")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Convidado> pesquisarConvidadoQrcode(Convidado convidado) {
		
		String code_convidado = convidado.getQrcode();
		
		logger.info("Listar Recurso: " + code_convidado);		

		List<Convidado> convidados = ConvidadoDAO.getInstance().getByCode(code_convidado);
		
		logger.info("Convidados: " + convidados.size() + "[" + convidados + "]");

		return convidados;
	}
}