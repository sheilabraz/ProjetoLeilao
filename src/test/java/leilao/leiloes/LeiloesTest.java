package leilao.leiloes;

import leilao.login.LoginPage;
import leilao.suporte.Suporte;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;
    private CadastroLeilaoPage paginaDeCadastro;

    @BeforeEach
    public void beforeEach() {
        LoginPage paginaDeLogin = new LoginPage();
        this.paginaDeLeiloes = paginaDeLogin.preencherFormularioDeLogin("fulano", "pass").efetuarLogin();
        this.paginaDeCadastro = this.paginaDeLeiloes.carregarFormulario();
    }

    @AfterEach
    public void afterEach() {
        this.paginaDeLeiloes.fechar();
    }

    @Test
    public void deveriaCadastrarLeilao() {
        String hoje = Suporte.retornarDataAtual();
        String nome = "Leilao do dia " + hoje;
        String valor = "500.00";

        paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);

        Assert.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));

    }

    @Test
    public void deveriaValidarCadastroDeLeilao() {
        paginaDeCadastro.cadastrarLeilao("", "", "");
        Assert.assertFalse(this.paginaDeCadastro.isPaginaAtual());
        Assert.assertTrue(this.paginaDeLeiloes.isPaginaAtual());
        Assert.assertTrue(this.paginaDeCadastro.isMensagensDeValidacaoVisiveis());

    }


}
