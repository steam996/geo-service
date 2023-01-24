import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    @Test
    void test_send_RU(){
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(geoService.byIp("172.0.32.11")).thenReturn(new Location(
                "Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl( geoService, localizationService);
        Map <String, String> addresses= new HashMap<String, String>();
        addresses.put(messageSender.IP_ADDRESS_HEADER, "172.0.32.11");
        String preference = messageSender.send(addresses);
        String expected = "Добро пожаловать";
        Assertions.assertEquals(expected, preference);
    }

    @Test
    void test_send_EN(){
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(geoService.byIp("96.44.183.149")).thenReturn(new Location(
                "New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl( geoService, localizationService);
        Map <String, String> addreses= new HashMap<String, String>();
        addreses.put(messageSender.IP_ADDRESS_HEADER, "96.44.183.149");
        String preference = messageSender.send(addreses);
        String expected = "Welcome";
        Assertions.assertEquals(expected, preference);
    }
}
