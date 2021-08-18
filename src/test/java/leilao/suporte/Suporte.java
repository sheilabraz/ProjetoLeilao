package leilao.suporte;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Suporte {

    public static String retornarDataAtual()
    {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
