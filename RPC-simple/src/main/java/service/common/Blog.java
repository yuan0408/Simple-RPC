package service.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Blog implements Serializable {
    private static final long serialVersionUID = -8842232088648669997L;

    private Integer id;
    private String title;
    private String author;
    private String content;
}
