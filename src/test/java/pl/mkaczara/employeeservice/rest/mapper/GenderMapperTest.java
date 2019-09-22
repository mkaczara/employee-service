package pl.mkaczara.employeeservice.rest.mapper;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import pl.mkaczara.employeeservice.repository.entity.Gender;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class GenderMapperTest {

    @InjectMocks
    private GenderMapper genderMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Parameters(method = "paramsToTestMapToEncoded")
    public void shouldMapToEncoded(Gender input, Integer expected) {
        Integer result = genderMapper.map(input);

        assertEquals(expected, result);
    }

    @Test
    @Parameters(method = "paramsToTestMapToGender")
    public void shouldMapToGender(Integer input, Gender expected) {
        Gender result = genderMapper.map(input);

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenmapToGenderAndUnsupportedEncodedValue() {
        genderMapper.map(56);
    }

    private Object[][] paramsToTestMapToEncoded() {
        return new Object[][]{
                { Gender.MALE, GenderMapper.GENDER_MALE },
                { Gender.FEMALE, GenderMapper.GENDER_FEMALE },
                { Gender.CROSS, GenderMapper.GENDER_CROSS }
        };
    }

    private Object[][] paramsToTestMapToGender() {
        return new Object[][]{
                { GenderMapper.GENDER_MALE, Gender.MALE },
                { GenderMapper.GENDER_FEMALE, Gender.FEMALE },
                { GenderMapper.GENDER_CROSS, Gender.CROSS }
        };
    }
}