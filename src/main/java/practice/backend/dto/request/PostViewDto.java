package practice.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostViewDto {

    private Integer userId;

    private Integer postId;

    private LocalTime timeDuration;
}
