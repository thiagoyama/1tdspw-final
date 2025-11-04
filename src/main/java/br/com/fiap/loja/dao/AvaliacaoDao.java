package br.com.fiap.loja.dao;

import br.com.fiap.loja.dto.avaliacao.MediaAvaliacaoDto;
import br.com.fiap.loja.model.Avaliacao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AvaliacaoDao {

    @Inject
    private DataSource dataSource;

    public MediaAvaliacaoDto buscarMedia(int codigoDoce) throws SQLException {
        try (Connection conexao = dataSource.getConnection()){
            PreparedStatement stmt = conexao.prepareStatement("select avg(vl_nota) as media, count(*) " +
                    "as qntd from t_tdspw_avaliacao where cd_doce = ?");
            stmt.setInt(1, codigoDoce);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            MediaAvaliacaoDto dto = new MediaAvaliacaoDto();
            dto.setMedia(rs.getDouble("media"));
            dto.setQuantidade(rs.getInt("qntd"));
            dto.setCodigoDoce(codigoDoce);
            return dto;
        }
    }

    public List<Avaliacao> buscarPorDoce(int codigo) throws SQLException {
        try (Connection conexao = dataSource.getConnection()){
            PreparedStatement stmt = conexao.prepareStatement("select * from " +
                    "t_tdspw_avaliacao where cd_doce = ?");
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            List<Avaliacao> lista = new ArrayList<>();
            while (rs.next()){
                Avaliacao avaliacao = parseAvaliacao(rs);
                lista.add(avaliacao);
            }
            return lista;
        }
    }

    public Avaliacao parseAvaliacao(ResultSet rs) throws SQLException {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setCodigo(rs.getInt("cd_avaliacao"));
        avaliacao.setNota(rs.getDouble("vl_nota"));
        avaliacao.setDescricao(rs.getString("ds_avaliacao"));
        avaliacao.setCodigoDoce(rs.getInt("cd_doce"));
        avaliacao.setDataCadastro(rs.getObject("dt_cadastro", LocalDateTime.class));
        return avaliacao;
    }

    public void cadastrar(Avaliacao avaliacao) throws SQLException {
        try (Connection conexao = dataSource.getConnection()){
            PreparedStatement stmt = conexao.prepareStatement("insert into t_tdspw_avaliacao (cd_avaliacao," +
                    "ds_avaliacao, vl_nota, dt_cadastro, cd_doce) values (sq_tdspw_avaliacao.nextval, ?, ?, sysdate,?)", new String[]{"cd_avaliacao"});
            stmt.setString(1, avaliacao.getDescricao());
            stmt.setDouble(2, avaliacao.getNota());
            stmt.setInt(3, avaliacao.getCodigoDoce());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next())
                avaliacao.setCodigo(rs.getInt(1));

        }
    }
}
