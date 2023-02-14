package com.example.springboothw28.DTO;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusTdo {
    @Pattern(regexp = "new||inProgress||completed")
    private String status;
}
