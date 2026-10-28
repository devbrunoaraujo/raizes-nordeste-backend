package raizes_nordeste_backend.service;

import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import raizes_nordeste_backend.dto.ProdutoRequest;
import raizes_nordeste_backend.dto.ProdutoResponse;
import raizes_nordeste_backend.exception.RecursoNaoEncontradoException;
import raizes_nordeste_backend.model.Produto;
import raizes_nordeste_backend.model.enums.StatusCadastro;
import raizes_nordeste_backend.repository.ProdutoRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTests {
    @Mock ProdutoRepository produtoRepository;

    @Test
    void cadastroRemoveEspacosEPreservaPrecoComStatusAtivo() {
        when(produtoRepository.save(any(Produto.class))).thenAnswer(invocation -> invocation.getArgument(0));
        ProdutoResponse resposta = new ProdutoService(produtoRepository).cadastrar(
                new ProdutoRequest(" Tapioca ", " Queijo ", new BigDecimal("15.00")));
        assertEquals("Tapioca", resposta.nomeProduto());
        assertEquals("Queijo", resposta.descricao());
        assertEquals(new BigDecimal("15.00"), resposta.preco());
        assertEquals(StatusCadastro.ATIVO, resposta.status());
        verify(produtoRepository).save(any(Produto.class));
    }

    @Test
    void buscaInexistenteLancaExcecao() {
        when(produtoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RecursoNaoEncontradoException.class,
                () -> new ProdutoService(produtoRepository).buscarPorId(99L));
    }

    @Test
    void listagemSemFiltroBuscaTodosComOrdemEstavel() {
        PageRequest pageable = PageRequest.of(0, 20, Sort.by("id").ascending());
        when(produtoRepository.findAll(pageable)).thenReturn(Page.empty(pageable));
        assertTrue(new ProdutoService(produtoRepository).listar(0, 20, null).isEmpty());
        verify(produtoRepository).findAll(pageable);
    }

    @Test
    void listagemFiltradaConsultaStatus() {
        PageRequest pageable = PageRequest.of(1, 10, Sort.by("id").ascending());
        when(produtoRepository.findByStatus(StatusCadastro.INATIVO, pageable)).thenReturn(Page.empty(pageable));
        new ProdutoService(produtoRepository).listar(1, 10, StatusCadastro.INATIVO);
        verify(produtoRepository).findByStatus(StatusCadastro.INATIVO, pageable);
    }
}
