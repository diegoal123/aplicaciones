/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/Websocket")

public class websocket {
    conexionBD con;
    private static Set<Session> personas = Collections.synchronizedSet(new HashSet<Session>());
    @OnOpen
    public void OnOpen(Session sesion) throws IOException, ClassNotFoundException, SQLException{
        con = new conexionBD();
        System.out.println(sesion.getId() + " ha abierto una conexion");
        for (int i = 0; i < con.i; i++) {
            sesion.getBasicRemote().sendText(con.datos[i]);
        }
        //sesion.getBasicRemote().sendText("Conexión establecida ");
        personas.add(sesion);
    }
    @OnMessage
    public void OnMessage(String mensaje, Session sesion) throws IOException, ClassNotFoundException, SQLException{
        System.out.println("mensaje: " + sesion.getId() + ": " + mensaje);
        for (Session p : personas) {
            p.getBasicRemote().sendText(mensaje);
        }
    }
    @OnClose
    public void OnClose(Session sesion){
        System.out.println("Sesión " + sesion.getId() + " ha terminado");
        personas.remove(sesion);
    }
}
