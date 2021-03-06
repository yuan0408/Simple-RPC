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
public class User implements Serializable {
    private static final long serialVersionUID = -4170563383973205947L;

    private int id;
    private String name;
    private boolean sex;
}
