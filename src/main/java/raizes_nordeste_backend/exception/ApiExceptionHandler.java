package raizes_nordeste_backend.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ProblemDetail naoEncontrado(RecursoNaoEncontradoException exception) {
        return problema(HttpStatus.NOT_FOUND, "Recurso não encontrado", exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail dadosInvalidos(MethodArgumentNotValidException exception) {
        Map<String, String> campos = new LinkedHashMap<>();
        for (FieldError erro : exception.getBindingResult().getFieldErrors()) {
            campos.putIfAbsent(erro.getField(), erro.getDefaultMessage());
        }
        ProblemDetail resposta = problema(HttpStatus.BAD_REQUEST, "Dados inválidos",
                "Confira os campos informados.");
        resposta.setProperty("campos", campos);
        return resposta;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail corpoInvalido(HttpMessageNotReadableException exception) {
        return problema(HttpStatus.BAD_REQUEST, "Corpo inválido",
                "Envie um JSON válido com os tipos esperados.");
    }

    @ExceptionHandler({HandlerMethodValidationException.class, MethodArgumentTypeMismatchException.class})
    public ProblemDetail parametroInvalido(Exception exception) {
        return problema(HttpStatus.BAD_REQUEST, "Parâmetro inválido",
                "Confira o ID, o status e a paginação: página a partir de 0 e tamanho entre 1 e 100.");
    }

    private ProblemDetail problema(HttpStatus status, String titulo, String detalhe) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(status, detalhe);
        problema.setTitle(titulo);
        return problema;
    }
}
