/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import aplicacion.UtilidadesSeguridade;
/**
 *
 * @author alumnogreibd
 */
public class GenerarHashes {
    public static void main(String[] args) {
        String[] claves = {"hash1", "hash2", "hash3", "hash4", "hash5", "hash6"};
        for (String c : claves) {
            System.out.println(c + " -> " + UtilidadesSeguridade.sha256(c));
        }
    }
}