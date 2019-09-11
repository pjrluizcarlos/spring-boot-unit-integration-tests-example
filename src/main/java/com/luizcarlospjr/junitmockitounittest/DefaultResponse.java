package com.luizcarlospjr.junitmockitounittest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponse {

    private String message;
    private LocalDateTime datetime;

}
