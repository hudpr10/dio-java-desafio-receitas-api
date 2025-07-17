package dio.app.controller;

import dio.app.controller.dto.RecipeDTO;
import dio.app.domain.model.Recipe;
import dio.app.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeService service;

    @GetMapping
    @Operation(summary = "Obter todas as receitas", description = "Obtem um Array com todas as receitas cadastradas no banco.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realizado com sucesso.")
    })
    public ResponseEntity<List<RecipeDTO>> getAll() {
        List<Recipe> recipeList = service.findAll();
        List<RecipeDTO> recipeDTOList = recipeList.stream().map(RecipeDTO::new).toList();

        return ResponseEntity.ok(recipeDTOList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter uma receita pelo ID", description = "Obtem uma única receita informando seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada.")
    })
    public ResponseEntity<RecipeDTO> getById(@PathVariable("id") Long id) {
        Recipe recipe = service.findById(id);
        RecipeDTO recipeDTO = new RecipeDTO(recipe);

        return ResponseEntity.ok(recipeDTO);
    }

    @PostMapping
    @Operation(summary = "Adiciona uma nova receita", description = "Cria uma nova receita e obtém seu retorno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Realizado com sucesso."),
            @ApiResponse(responseCode = "409", description = "Dados únicos sendo repetidos."),
    })
    public ResponseEntity<RecipeDTO> post(@RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = service.create(recipeDTO.toModel());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(recipe.getId())
                .toUri();

        return ResponseEntity.created(location).body(new RecipeDTO(recipe));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma receita", description = "Atualiza uma receita já existente com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realizado com sucesso."),
            @ApiResponse(responseCode = "409", description = "Dados únicos sendo repetidos."),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada."),
    })
    public ResponseEntity<RecipeDTO> update(@PathVariable("id") Long id, @RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = service.update(id, recipeDTO.toModel());
        return ResponseEntity.ok(new RecipeDTO(recipe));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apaga uma receita", description = "Apaga uma receita do banco de dados por meio do seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada.")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
