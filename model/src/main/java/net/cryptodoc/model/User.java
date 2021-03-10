package net.cryptodoc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Document(collection = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Indexed
    private String firstName;

    private String lastName;

    private String email;

    private Boolean isSignatureReady;

    @PrePersist
    private void setIsSignatureReady() {
        this.isSignatureReady = false;
    }
}
