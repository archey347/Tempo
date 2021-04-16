package tempo.icons;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.gui.IconFetcher;

public class IconTest {
    public String icons[] = {
        "clock.png",
        "cog.png",
        "hourglass.png",
        "house.png",
        "project.png",
        "tempo_w.png",
        "tempo.png"
    };

    /**
     * Test all the icons needed exist
     */
    @Test
    public void testIcons() {
        for (String file: icons) {
            assertTrue(IconFetcher.getIcon(file) != null);
        }
    }
}
