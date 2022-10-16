package atacarejo.alfa;

import java.sql.SQLException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws SQLException {

        int opc ;

        do{
            opc = menu();
            switch (opc) {
                case 1 -> MAT(opc);
                case 2 -> DEP1(opc);
                case 3 -> DEP2(opc);
            }
        }while (opc != 0);

    }
    public static int menu() {
        System.out.println("====== INFORME SUA UNIDADE ======");
        System.out.println("1 - MAT ");
        System.out.println("2 - DEP1 ");
        System.out.println("3 - DEP2 ");
        System.out.print("\nDigite 0 para sair do programa:");

        Scanner opc = new Scanner(System.in);
        return opc.nextInt();
    }

    public static void MAT(int opc) throws SQLException {
        System.out.println("Voce esta na MATRIZ");
        Tabela MAT = new Tabela(opc);
        MAT.insert();
        System.out.println("Fim do cadastro;");
    }

    public static void DEP1(int opc) throws SQLException {
        System.out.println("Voce esta na DEPOSITO 1");
        Tabela DEP1 = new Tabela(opc);
        DEP1.insert();
        System.out.println("Fim do cadastro;");
    }

    public static void DEP2(int opc) throws SQLException {
        System.out.println("Voce esta na DEPOSITO 2");
        Tabela DEP2 = new Tabela(opc);
        DEP2.insert();
        System.out.println("Fim do cadastro;");
    }

}