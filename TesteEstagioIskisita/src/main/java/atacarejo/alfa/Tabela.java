package atacarejo.alfa;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.*;

public class Tabela {

    private final String [] mov = {"ENT","SAI"};
    private final String [] dest_ori = {"MAT","DEP1","DEP2"};
    private final String [] nomeCaminhao = {"C1","C2","C3","C4","C5"};
    private final String [] nomePlacas = {"C1C1C1","C2C2C2","C3C3C3","C4C4C4","C5C5C5"};
    private final String [] numLacres = {"20","19","18","21","31","41",
            "22","32","42","23","59","48",
            "24","35","28","25","49","61"};

    private String nomeTabela;
    private String movimento ;
    private String caminhao ;
    private String placa ;
    private Long odometro ;
    private String motorista ;
    private String destino_ou_origem;
    private String lacres ;
    private String usuario ;
    private String data_e_hora;

    Tabela(int opc){
        if (opc==1){this.nomeTabela = "MAT";}
        if (opc==2){this.nomeTabela = "DEP1";}
        if (opc==3){this.nomeTabela = "DEP2";}
    }

    public void insert() throws SQLException {

        Conect con = new Conect();

        String QUERY = "(movimento,caminhao,placa,odometro,motorista,destino_ou_origem,lacres,usuario,data_e_hora) VALUES(?,?,?,?,?,?,?,?,?)";
        String SQL = String.format("INSERT INTO %s "+QUERY,this.nomeTabela);
        PreparedStatement stmt = con.conexao.prepareStatement(SQL);

        this.movimento = setMovimento();
        this.caminhao = setCaminhao();
        this.placa = setPlaca();
        this.odometro = setOdometro();
        this.motorista = setMotorista();
        this.destino_ou_origem = setDestino_ou_origem();
        this.lacres = setLacres();
        this.usuario = setUsuario();
        this.data_e_hora  =  setData_Hora();

        stmt.setString(1,this.movimento);
        stmt.setString(2,this.caminhao);
        stmt.setString(3,this.placa);
        stmt.setLong(4,this.odometro);
        stmt.setString(5,this.motorista);
        stmt.setString(6,this.destino_ou_origem);
        stmt.setString(7,this.lacres);
        stmt.setString(8,this.usuario);
        stmt.setString(9,this.data_e_hora);
        stmt.execute();

        con.conexao.close();
    }

    private String setMovimento() throws SQLException {

        System.out.println("Informe se o caminhao esta ENTRANDO OU SAINDO: ENT/SAI");
        Scanner input = new Scanner(System.in);
        String str = input.next().toUpperCase();

        boolean verify = false;

        for(int i = 0; i < mov.length ;i++){
            if(str.contentEquals(mov[i])){
                verify =true;
            }
        }

        if(verify){
            return str;
        }
        else{
            return null;
        }
    }

    private String setCaminhao() throws SQLException {

        System.out.println("Informe o nome do caminhao:");
        Scanner input = new Scanner(System.in);
        String str = input.next().toUpperCase();

        boolean verify = false;

        for(int i = 0; i < nomeCaminhao.length ;i++){
            if(str.contentEquals(nomeCaminhao[i])){
                verify =true;
            }
        }

        if(verify){
            return str;
        }
        else{
            return null;
        }
    }

    private String setPlaca() throws SQLException {

        System.out.println("Informe a placa caminhao:");
        Scanner input = new Scanner(System.in);
        String str = input.next().toUpperCase();

        boolean verify = false;

        for(int i = 0; i < nomePlacas.length ;i++){
            if(str.contentEquals(nomePlacas[i])){
                verify =true;
            }
        }

        if(verify){
            return str;
        }
        else{
            return null;
        }
    }

    private boolean verificaLetra(String str) {

        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVXYWZ";

        for (int i = 0; i < str.length(); i++) {
            char letra = str.charAt(i);//pega cada letra
            if (alfabeto.contains(String.valueOf(letra))) {
                return true;
            }
        }
        return false;
    }
    private Long setOdometro() throws SQLException {

        System.out.println("Informe o odometro caminhao:");
        Scanner input = new Scanner(System.in);
        String str = input.next().toUpperCase();

        char[] verify = str.toCharArray();

        if(verify[0] =='\0'|| verify[0] =='\n' || verify[0] ==' ' || verificaLetra(str)){
            return (long) -1;
        }
        else{
            return Long.valueOf(str);
        }
    }

    private String setMotorista() throws SQLException {

        System.out.println("Informe o nome do motorista:");
        Scanner input = new Scanner(System.in);
        String str = input.next().toUpperCase();

        char[] verify = str.toCharArray();

        if(verify[0] =='\0'|| verify[0] =='\n' || verify[0] ==' '){
            return null;
        }
        else{
            return str;
        }
    }

    private String setDestino_ou_origem() throws SQLException {

        System.out.println("Informe o destino ou origem:");
        Scanner input = new Scanner(System.in);
        String str = input.next().toUpperCase();

        boolean verify = false;

        for(int i = 0; i < dest_ori.length ;i++){
            if(str.contentEquals(dest_ori[i])){
                verify =true;
            }
        }

        if(verify){
            return str;
        }
        else{
            return null;
        }
    }

    private String setLacres() throws SQLException {
        System.out.println("Informe os lacres caminhao:");

        String [] aux = new String[3];

        Scanner input = new Scanner(System.in);
        for(int i=0;i < aux.length;i++ ){
            String str = String.format("Digite o numero %d :",i+1);
            System.out.println(str);
            aux[i] = input.next();
        }


        boolean verify = false;
        int cont=0;

        for(int i = 0; i < aux.length ;i++) {
            for(int j = 0; j < numLacres.length ;j++) {
                if (aux[i].contentEquals(numLacres[j])) {
                    cont++;
                }
            }
        }

        if(cont == aux.length){
            String str = aux[0] + "/" + aux[1] + "/" +aux[2];
            return str;
        }
        else{
            return null;
        }
    }

    private String setUsuario() throws SQLException {

        System.out.println("Informe o seu nome de usuario:");
        Scanner input = new Scanner(System.in);
        String str = input.next().toUpperCase();

        char[] verify = str.toCharArray();

        if(verify[0] =='\0'|| verify[0] =='\n' || verify[0] ==' '){
            return null;
        }
        else{
            return str;
        }
    }

    private String setData_Hora() throws SQLException {
        System.out.println("Data e hora salvas automaticamente.");
        LocalDateTime dataHora = java.time.LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return dataHora.format(formatter);
    }

}