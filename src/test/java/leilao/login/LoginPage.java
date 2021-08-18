package leilao.login;

import leilao.PageObject;
import leilao.leiloes.LeiloesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage extends PageObject {

    private static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        super(null);
        this.browser.navigate().to(URL_LOGIN);
    }

    public LoginPage preencherFormularioDeLogin(String username, String password) {
        this.browser.findElement(By.name("username")).sendKeys(username);
        this.browser.findElement(By.name("password")).sendKeys(password);
        return this;
    }

    public LeiloesPage efetuarLogin() {
        this.browser.findElement(By.id("login-form")).submit();
        return new LeiloesPage(this.browser);
    }

    public boolean isPaginaDeLogin() {
        return browser.getCurrentUrl().contains(URL_LOGIN);
    }

    public Object getNomeUsuarioLogado() {
        try {
            return this.browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public LoginPage navegarParaPaginaDeLances() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
        return this;
    }

    public boolean contemTexto(String texto) {
        return this.browser.getPageSource().contains(texto);
    }

    public boolean isPaginaDeLoginComDadosInvalidos() {
        return browser.getCurrentUrl().equals(URL_LOGIN + "?error");
    }
}
