package dio.app.controller.dto;

import dio.app.domain.model.Instruction;

public record InstructionDTO(Long id, String stage, String description) {
    public InstructionDTO(Instruction model) {
        this(model.getId(), model.getStage(), model.getDescription());
    }

    public Instruction toModel() {
        Instruction model = new Instruction();
        model.setId(this.id);
        model.setStage(this.stage);
        model.setDescription(this.description);

        return model;
    }
}
