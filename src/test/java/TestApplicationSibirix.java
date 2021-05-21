import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestApplicationSibirix {


    @Test
    public void sendApplication() {
        var playwright = Playwright.create();
        var browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        var browserContext = browser.newContext();
        var page = browserContext.newPage();
        page.navigate("http://test-form.sibirix.ru/");

        page.fill(".form-request > label:nth-child(1) > input:nth-child(2)", "Иван");
        page.fill(".is-email", "ivan@mail.ru");
        page.fill(".form-request > label:nth-child(3) > input:nth-child(2)", "88005553535");
        page.fill(".form-request > label:nth-child(4) > textarea:nth-child(2)", "Тестирование");
        page.click(".submit");

        String text = page.innerText(".form-wrapper > h1:nth-child(1)");
        assertEquals("Сообщение успешно отправлено!", text);

    }
}