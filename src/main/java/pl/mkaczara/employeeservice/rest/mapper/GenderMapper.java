package pl.mkaczara.employeeservice.rest.mapper;

import org.springframework.stereotype.Component;
import pl.mkaczara.employeeservice.repository.entity.Gender;

@Component
public class GenderMapper {

    public static final int GENDER_MALE = 0;
    public static final int GENDER_FEMALE = 1;
    public static final int GENDER_CROSS = 2;

    public Integer map(Gender gender) {
        Integer encodedGender;
        switch (gender) {
            case MALE:
                encodedGender = GENDER_MALE;
                break;
            case FEMALE:
                encodedGender = GENDER_FEMALE;
                break;
            case CROSS:
                encodedGender = GENDER_CROSS;
                break;
            default:
                throw new IllegalArgumentException("Unsupported gender: " + gender.name());
        }
        return encodedGender;
    }

    public Gender map(Integer encodedGender) {
        Gender gender;
        switch (encodedGender) {
            case GENDER_MALE:
                gender = Gender.MALE;
                break;
            case GENDER_FEMALE:
                gender = Gender.FEMALE;
                break;
            case GENDER_CROSS:
                gender = Gender.CROSS;
                break;
            default:
                throw new IllegalArgumentException("Unsupported encoded gender: " + encodedGender);
        }
        return gender;
    }
}
