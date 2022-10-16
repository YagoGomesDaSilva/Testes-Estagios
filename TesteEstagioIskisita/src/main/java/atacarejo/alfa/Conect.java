package atacarejo.alfa;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conect {

    private  String usuario;
    private String senha;
    private String url;
    public Connection conexao;

    Conect(){
        this.usuario = "postgres";
        this.senha = "80526134";
        this.url = "jdbc:postgresql://localhost:5432/postgres";

        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(this.url,this.usuario,this.senha);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}