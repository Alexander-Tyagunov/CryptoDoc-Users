package net.cryptodoc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean isSignatureReady;

    @PrePersist
    private void setIsSignatureReady() {
        this.isSignatureReady = false;
    }
}
