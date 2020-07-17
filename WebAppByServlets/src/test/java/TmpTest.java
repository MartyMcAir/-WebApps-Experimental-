import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TmpTest {
    private static Tmp tmp;

    @BeforeAll
    static void init() {
        tmp = new Tmp();
    }

    @Test
    void multiplyTest1() {
        assertEquals(6, tmp.multiply(3));
    }

    @Test
    void multiplyTest2() {
        assertTrue(13 > tmp.multiply(3));
    }
}
