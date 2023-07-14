package com.example.quiz3.web.model.events;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class rolesDTO {
    @NotNull
    @Size()
    private int Id;
    private String name;
}
