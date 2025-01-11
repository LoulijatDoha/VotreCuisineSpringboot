package com.dohaproject.VotreCuisine.dto;

import java.util.List;

public record RecetteDTO(
        String titre,
        int categoryId,
        String tempsPreparation,
        List<String>ingredients,
        String etapesPreparation
) {
}
