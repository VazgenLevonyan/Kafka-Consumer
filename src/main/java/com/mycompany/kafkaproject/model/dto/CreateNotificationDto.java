package com.mycompany.kafkaproject.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNotificationDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
