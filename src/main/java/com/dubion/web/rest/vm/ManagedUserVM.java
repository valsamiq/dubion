package com.dubion.web.rest.vm;

import com.dubion.service.dto.UserDTO;
import javax.validation.constraints.Size;

import java.time.Instant;
import java.util.Set;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    private String nombre;
    public String getNombre(){ return nombre;}

    private String apellido;
    public String getApellido(){ return apellido;}

    private String location;
    public String getLocation() {
        return location;
    }

    private Double latitude;
    public Double getLatitude() {
        return latitude;
    }

    private Double longitude;
    public Double getLongitude() {
        return longitude;
    }

    public String gender;
    public String getGender() { return  gender;}

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    public ManagedUserVM(Long id, String login, String password, String firstName, String lastName,
                         String email, boolean activated, String imageUrl, String langKey,
                         String createdBy, Instant createdDate, String lastModifiedBy, Instant lastModifiedDate,
                        Set<String> authorities, String location,Double latitude,Double longitude, String nombre, String apellido, String gender) {

        super(id, login, firstName, lastName, email, activated, imageUrl, langKey,
            createdBy, createdDate, lastModifiedBy, lastModifiedDate,  authorities);
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "ManagedUserVM{" +
            "} " + super.toString();
    }
}
