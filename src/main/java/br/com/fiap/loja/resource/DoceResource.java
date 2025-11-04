package br.com.fiap.loja.resource;

import br.com.fiap.loja.dao.DoceDao;
import br.com.fiap.loja.dto.doce.AtualizarDoceDto;
import br.com.fiap.loja.dto.doce.CadastroDoceDto;
import br.com.fiap.loja.dto.doce.DetalhesDoceDto;
import br.com.fiap.loja.exception.EntidadeNaoEncontradaException;
import br.com.fiap.loja.model.Doce;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.modelmapper.ModelMapper;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@Path("/doces")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoceResource {

    @Inject
    private DoceDao doceDao;

    @Inject
    private ModelMapper mapper;

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") int codigo) throws EntidadeNaoEncontradaException, SQLException {
        doceDao.remover(codigo);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id")int codigo, @Valid AtualizarDoceDto dto) throws EntidadeNaoEncontradaException, SQLException {
        Doce doce = mapper.map(dto, Doce.class);
        doce.setCodigo(codigo);
        doceDao.atualizar(doce);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") int codigo) throws EntidadeNaoEncontradaException, SQLException {
        Doce doce = doceDao.buscar(codigo);
        return Response.ok(mapper.map(doce, DetalhesDoceDto.class)).build();
    }

    @GET
    public List<DetalhesDoceDto> listar() throws SQLException {
        return doceDao.listar().stream().map(
                doce -> mapper.map(doce, DetalhesDoceDto.class)
        ).toList();
    }

    @POST
    public Response criar(@Valid CadastroDoceDto dto, @Context UriInfo uriInfo) throws SQLException {
        //Cria um doce com base nos dados do DTO
        Doce doce = mapper.map(dto, Doce.class);

        doceDao.cadastrar(doce);
        //Criar a URI para acessar o doce criado
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(doce.getCodigo())).build();

        return Response.created(uri).entity(mapper.map(doce, DetalhesDoceDto.class)).build(); //201
    }

}
