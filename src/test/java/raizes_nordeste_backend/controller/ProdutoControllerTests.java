package raizes_nordeste_backend.controller;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import raizes_nordeste_backend.dto.ProdutoResponse;
import raizes_nordeste_backend.exception.RecursoNaoEncontradoException;
import raizes_nordeste_backend.model.enums.StatusCadastro;
import raizes_nordeste_backend.service.ProdutoService;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTests {
    @Autowired MockMvc mvc;
    @MockitoBean ProdutoService produtoService;

    private ProdutoResponse produto() {
        return new ProdutoResponse(1L, "Tapioca", "Tapioca de queijo",
                new BigDecimal("15.00"), StatusCadastro.ATIVO);
    }

    @Test
    void cadastrarRetorna201ComProdutoCriado() throws Exception {
        when(produtoService.cadastrar(any())).thenReturn(produto());
        mvc.perform(post("/api/produtos").contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"nomeProduto":"Tapioca","descricao":"Tapioca de queijo","preco":15.00}
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeProduto").value("Tapioca"))
                .andExpect(jsonPath("$.status").value("ATIVO"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "1.001", "10000000000", "null"})
    void rejeitaPrecoInvalido(String preco) throws Exception {
        mvc.perform(post("/api/produtos").contentType(MediaType.APPLICATION_JSON)
                .content("{\"nomeProduto\":\"Tapioca\",\"descricao\":\"Queijo\",\"preco\":" + preco + "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.campos.preco").exists());
        verifyNoInteractions(produtoService);
    }

    @Test
    void rejeitaCamposEmBranco() throws Exception {
        mvc.perform(post("/api/produtos").contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"nomeProduto":" ","descricao":"","preco":15}
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.campos.nomeProduto").exists())
                .andExpect(jsonPath("$.campos.descricao").exists());
        verifyNoInteractions(produtoService);
    }

    @Test
    void buscarProdutoRetorna200() throws Exception {
        when(produtoService.buscarPorId(1L)).thenReturn(produto());
        mvc.perform(get("/api/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void produtoInexistenteRetorna404() throws Exception {
        when(produtoService.buscarPorId(99L)).thenThrow(new RecursoNaoEncontradoException("Produto não encontrado."));
        mvc.perform(get("/api/produtos/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.detail").value("Produto não encontrado."));
    }

    @Test
    void listarRetornaPaginaComFiltro() throws Exception {
        when(produtoService.listar(0, 20, StatusCadastro.ATIVO))
                .thenReturn(new PageImpl<>(List.of(produto()), PageRequest.of(0, 20), 1));
        mvc.perform(get("/api/produtos").param("status", "ATIVO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.conteudo[0].id").value(1))
                .andExpect(jsonPath("$.totalElementos").value(1))
                .andExpect(jsonPath("$.pagina").value(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"?pagina=-1", "?tamanho=0", "?tamanho=101", "?status=INVALIDO", "/0", "/abc"})
    void rejeitaParametrosInvalidos(String caminho) throws Exception {
        mvc.perform(get("/api/produtos" + caminho)).andExpect(status().isBadRequest());
        verifyNoInteractions(produtoService);
    }

    @Test
    void rejeitaJsonMalformado() throws Exception {
        mvc.perform(post("/api/produtos").contentType(MediaType.APPLICATION_JSON).content("{"))
                .andExpect(status().isBadRequest());
        verifyNoInteractions(produtoService);
    }
}
