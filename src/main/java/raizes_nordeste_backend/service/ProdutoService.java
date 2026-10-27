package raizes_nordeste_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raizes_nordeste_backend.dto.ProdutoRequest;
import raizes_nordeste_backend.dto.ProdutoResponse;
import raizes_nordeste_backend.exception.RecursoNaoEncontradoException;
import raizes_nordeste_backend.model.Produto;
import raizes_nordeste_backend.model.enums.StatusCadastro;
import raizes_nordeste_backend.repository.ProdutoRepository;

@Service
@Transactional(readOnly = true)
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public ProdutoResponse cadastrar(ProdutoRequest request) {
        Produto produto = new Produto();
        produto.setNomeProduto(request.nomeProduto().strip());
        produto.setDescricao(request.descricao().strip());
        produto.setPreco(request.preco());
        Produto produtoSalvo = produtoRepository.save(produto);
        return ProdutoResponse.de(produtoSalvo);
    }

    public ProdutoResponse buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado."));
        return ProdutoResponse.de(produto);
    }

    public Page<ProdutoResponse> listar(int pagina, int tamanho, StatusCadastro status) {
        PageRequest pageable = PageRequest.of(pagina, tamanho, Sort.by("id").ascending());
        Page<Produto> produtos;

        if (status == null) {
            produtos = produtoRepository.findAll(pageable);
        } else {
            produtos = produtoRepository.findByStatus(status, pageable);
        }

        return produtos.map(ProdutoResponse::de);
    }
}
