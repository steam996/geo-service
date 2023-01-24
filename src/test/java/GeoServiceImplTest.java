import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {

    @ParameterizedTest
    @ValueSource(strings = {GeoServiceImpl.LOCALHOST, GeoServiceImpl.MOSCOW_IP, GeoServiceImpl.NEW_YORK_IP})
    void test_byIP(String IP){
        GeoService geoService = new GeoServiceImpl();
        Country preference = geoService.byIp(IP).getCountry();
        Country expected;
        if (IP.equals(GeoServiceImpl.LOCALHOST)){
            expected = null;
        } else if (IP.equals(GeoServiceImpl.MOSCOW_IP)){
            expected = Country.RUSSIA;
        } else {
            expected = Country.USA;
        }
        Assertions.assertEquals(expected, preference);
    }

}
