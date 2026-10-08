package gui;

import aplicacion.FachadaAplicacion;
import javax.swing.JOptionPane;

//clase principal da interface gráfica que actúa como controlador inicial e centraliza o manexo de erros visuais
public class FachadaGui {

    private FachadaAplicacion fa;
    private VLogin1 vLogin;

    public FachadaGui(FachadaAplicacion fa) {
        //gardamos a referencia á fachada central da aplicación para poder pasarlle as peticións dos botóns cara á base de datos
        this.fa = fa;
    }

    //método de arranque que se chama dende o main principal para instanciar e facer visible a primeira ventá do programa (o login)
    public void iniciaVista() {
        vLogin = new VLogin1(fa);
        vLogin.setVisible(true);
    }

    //método centralizado para amosar xanelas emerxentes de erro en pantalla dende calquera ventá ou DAO do proxecto
    public void muestraExcepcion(String e) {
        JOptionPane.showMessageDialog(null, e, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}