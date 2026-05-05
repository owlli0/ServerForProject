package com.glucokid.server.dto;

import com.glucokid.server.domain.Child;
import com.glucokid.server.domain.Parent;
import com.glucokid.server.domain.ParentChild;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
@Data
public class ParentDTO {

    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String phoneNumber;
    private String password;
    //private List<ParentChild> children;
}
