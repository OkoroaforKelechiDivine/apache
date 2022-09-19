package practice.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import practice.backend.model.reaction.Reactions;
import practice.backend.model.roleType.UserType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostDto {

    private String content;

    private UserType author;

    private Integer adminId;

    private Reactions reactions;
}
