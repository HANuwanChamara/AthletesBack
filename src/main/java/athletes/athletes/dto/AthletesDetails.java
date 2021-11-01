package athletes.athletes.dto;

import lombok.Data;

@Data
public class AthletesDetails {
    private String firstName;

    private String lastName;

    private String gender;

    private String country;

    private Integer age;

    private String result;

    private String event;
}
