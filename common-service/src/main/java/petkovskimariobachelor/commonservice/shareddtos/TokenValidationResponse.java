package petkovskimariobachelor.commonservice.shareddtos;

import lombok.Getter;

@Getter
public class TokenValidationResponse {
    private String email;
    private String id;
    private Boolean isValid;

    public TokenValidationResponse(String email, String id, Boolean isValid) {
        this.email = email;
        this.id = id;
        this.isValid = isValid;
    }
}
