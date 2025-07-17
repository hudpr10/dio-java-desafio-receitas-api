package dio.app.controller.dto;


import dio.app.domain.model.PreparationTime;

public record PreparationTimeDTO(Long id, String stage, String description) {
    public PreparationTimeDTO(PreparationTime model) {
        this(model.getId(), model.getStage(), model.getDescription());
    }

    public PreparationTime toModel() {
        PreparationTime model = new PreparationTime();
        model.setId(this.id);
        model.setStage(this.stage);
        model.setDescription(this.description);

        return model;
    }
}
