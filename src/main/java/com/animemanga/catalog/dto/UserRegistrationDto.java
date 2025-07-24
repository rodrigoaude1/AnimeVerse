package com.animemanga.catalog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistrationDto {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String name;
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ter um formato válido")
    private String email;
    
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 100, message = "Senha deve ter pelo menos 6 caracteres")
    private String password;
    
    @NotNull(message = "Aceitação dos termos é obrigatória")
    private Boolean acceptsTerms;
    
    // Constructors
    public UserRegistrationDto() {}
    
    public UserRegistrationDto(String name, String email, String password, Boolean acceptsTerms) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.acceptsTerms = acceptsTerms;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Boolean getAcceptsTerms() {
        return acceptsTerms;
    }
    
    public void setAcceptsTerms(Boolean acceptsTerms) {
        this.acceptsTerms = acceptsTerms;
    }
}

