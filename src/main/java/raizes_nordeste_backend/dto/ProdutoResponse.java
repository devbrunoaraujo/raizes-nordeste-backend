package raizes_nordeste_backend.dto;

import java.math.BigDecimal;
import raizes_nordeste_backend.model.Produto;
import raizes_nordeste_backend.model.enums.StatusCadastro;

public record ProdutoResponse(Long id, String nomeProduto, String descricao,
                              BigDecimal preco, StatusCadastro status) {
    public static ProdutoResponse de(Produto produto) {
        return new ProdutoResponse(produto.getId(), produto.getNomeProduto(),
                produto.getDescricao(), produto.getPreco(), produto.getStatus());
    }
}
