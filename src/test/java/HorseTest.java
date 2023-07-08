import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
 import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HorseTest {
    private Horse horse;

    @Test
    public void testConstructorNullNameException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 2.2, 0));
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    public void testConstructorNullNameExceptionMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 2.2, 0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    public void testConstructorEmptyNameException(String str) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(str, 2.2, 0));
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    public void testConstructorEmptyNameExceptionMessage(String str) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(str, 2.2, 0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void testConstructorNegativeSpeedException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Буцефал", -2.2, 0));
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    public void testConstructorNegativeSpeedExceptionMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Буцефал", -2.2, 0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void testConstructorNegativeDistanceException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Буцефал", 2.2, -1));
        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    public void testConstructorNegativeDistanceExceptionMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Буцефал", 2.2, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void testGetDistanceDoubleParametersConstructor() {
        Horse horse = new Horse("Буцефал", 2.2);
        double expected = 0;
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @BeforeAll
    public void setUp() {
        horse = new Horse("Буцефал", 2.2, 1);
    }

    @Test
    public void testGetName() {
        String expected = "Буцефал";
        String actual = horse.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSpeed() {
        double expected = 2.2;
        double actual = horse.getSpeed();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDistance() {
        double expected = 1;
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    public void testMoveCollingGetRandomDouble() {
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)){
            horse.move();
            mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.3})
    public void testMoveDistanceByFormula(double mockedRandom) {
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)){
            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockedRandom);
            double expected = horse.getDistance() + horse.getSpeed() * mockedRandom;
            horse.move();
            assertEquals(expected, horse.getDistance());
        }
    }
}
