import org.junit.jupiter.api.*;

public class MainTest {

    @Disabled
    @Test
    @Timeout(value = 22)
    public void testMainTimeWorking() throws Exception {
        Main.main(null);
    }
}
