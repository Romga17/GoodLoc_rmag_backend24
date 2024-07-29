package edu.gdlc_project.gdlc_pckgs.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int id;

    public UserDTO() {}

    public UserDTO(int id) {
        this.id = id;
    }
}
