package br.com.fiap.loja.resource;

import br.com.fiap.loja.dao.AvaliacaoDao;
import br.com.fiap.loja.dto.avaliacao.CadastroAvaliacaoDto;
import br.com.fiap.loja.dto.avaliacao.DetalhesAvaliacaoDto;
import br.com.fiap.loja.dto.avaliacao.MediaAvaliacaoDto;
import br.com.fiap.loja.model.Avaliacao;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;

@Path("/doces/{codigoDoce}/avaliacoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AvaliacaoResource {

    @Inject
    private AvaliacaoDao avaliacaoDao;

    @Inject
    private ModelMapper mapper;

    @GET
    @Path("/status")
    public Response media(@PathParam("codigoDoce") int codigoDoce) throws SQLException {
        MediaAvaliacaoDto status = avaliacaoDao.buscarMedia(codigoDoce);
        return Response.ok(status).build();
    }

    @GET
    public List<DetalhesAvaliacaoDto> buscar(@PathParam("codigoDoce") int codigoDoce) throws SQLException {
        return avaliacaoDao.buscarPorDoce(codigoDoce).stream().map(d -> mapper.map(d, DetalhesAvaliacaoDto.class)).toList();
    }

    @POST
    public Response create(@PathParam("codigoDoce") int codigoDoce,
                           @Valid CadastroAvaliacaoDto dto, @Context UriInfo uriInfo) throws SQLException {

        Avaliacao avaliacao = mapper.map(dto, Avaliacao.class);
        avaliacao.setCodigoDoce(codigoDoce);

        avaliacaoDao.cadastrar(avaliacao);

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(String.valueOf(avaliacao.getCodigo()));

        return Response.created(uriBuilder.build()).entity(mapper.map(avaliacao, DetalhesAvaliacaoDto.class)).build();
    }

}
