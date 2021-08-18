package leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private LoginPage paginaDeLogin;

    @AfterEach
    public void afterEach() {
        this.paginaDeLogin.fechar();
    }

    @BeforeEach
    public void beforeEach() {
        this.paginaDeLogin = new LoginPage();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {

        paginaDeLogin.preencherFormularioDeLogin("fulano", "pass").efetuarLogin();
        Assert.assertFalse(paginaDeLogin.isPaginaDeLogin());
        Assert.assertEquals("fulano", this.paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        paginaDeLogin.preencherFormularioDeLogin("invalido", "123").efetuarLogin();

        Assert.assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());
        Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
        Assert.assertNull(paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        paginaDeLogin.navegarParaPaginaDeLances();
        Assert.assertTrue(paginaDeLogin.isPaginaDeLogin());
        Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
    }
}
