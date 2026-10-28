package raizes_nordeste_backend.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raizes_nordeste_backend.dto.PaginaResponse;
import raizes_nordeste_backend.dto.ProdutoRequest;
import raizes_nordeste_backend.dto.ProdutoResponse;
import raizes_nordeste_backend.model.enums.StatusCadastro;
import raizes_nordeste_backend.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> cadastrar(@Valid @RequestBody ProdutoRequest request) {
        ProdutoResponse produto = produtoService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<PaginaResponse<ProdutoResponse>> listar(
            @RequestParam(defaultValue = "0") @Min(0) int pagina,
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int tamanho,
            @RequestParam(required = false) StatusCadastro status) {
        PaginaResponse<ProdutoResponse> produtos = PaginaResponse.de(
                produtoService.listar(pagina, tamanho, status));
        return ResponseEntity.ok(produtos);
    }
}
