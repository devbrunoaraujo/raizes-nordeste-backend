package raizes_nordeste_backend.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProdutoRequest(
    @NotBlank(message = "Informe o nome do produto.")
    @Size(max = 100, message = "O nome deve ter até 100 caracteres.")
    String nomeProduto,

    @NotBlank(message = "Informe a descrição.")
    @Size(max = 500, message = "A descrição deve ter até 500 caracteres.")
    String descricao,

    @NotNull(message = "Informe o preço.")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero.")
    @Digits(integer = 10, fraction = 2, message = "Use até 10 dígitos inteiros e 2 casas decimais.")
    BigDecimal preco) {
}
