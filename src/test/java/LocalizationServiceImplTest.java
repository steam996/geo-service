import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {

    @ParameterizedTest
    @EnumSource(Country.class)
    void test_locale(Country country){
        LocalizationService localizationService = new LocalizationServiceImpl();
        String preference = localizationService.locale(country);
        String expected;
        if ("RUSSIA".equals(country.toString())){
            expected = "Добро пожаловать";
        } else expected = "Welcome";
        Assertions.assertEquals(expected, preference);
    }
}
