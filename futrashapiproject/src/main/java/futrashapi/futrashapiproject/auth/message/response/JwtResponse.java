package futrashapi.futrashapiproject.auth.message.response;

import java.util.List;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String name, String email, String phone ,List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone=phone;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }
}
