package com.retrojuegos.retrojuegos.util;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.*;

public class ExportadorXML {

    public void exportarInventario(Connection conexion) throws Exception {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            Document doc = implementation.createDocument(null, "retro_inventario", null);
            Element root = doc.getDocumentElement();
            root.setAttribute("version", "1.0");
            root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            root.setAttribute("xsi:noNamespaceSchemaLocation", "esquema.xsd");


            Element usuariosNode = doc.createElement("usuarios");
            root.appendChild(usuariosNode);

            Statement stmtUser = conexion.createStatement();
            ResultSet rsUser = stmtUser.executeQuery("SELECT * FROM usuarios");

            while (rsUser.next()) {
                Element u = doc.createElement("usuario");
                u.setAttribute("id", "u" + rsUser.getInt("id_usuario"));

                Element nombre = doc.createElement("nombre");
                nombre.setTextContent(rsUser.getString("nombre"));
                u.appendChild(nombre);

                Element apellidos = doc.createElement("apellidos");
                apellidos.setTextContent(rsUser.getString("apellidos"));
                u.appendChild(apellidos);

                Element email = doc.createElement("email");
                email.setTextContent(rsUser.getString("email"));
                u.appendChild(email);

                usuariosNode.appendChild(u);
            }


            Element catalogoNode = doc.createElement("catalogo");
            root.appendChild(catalogoNode);

        String sqlJuegos = "SELECT v.*, c.fecha_compra AS fecha_registro, p.nombre_plataforma, g.nombre_genero " +
                "FROM videojuegos v " +
                "JOIN plataformas p ON v.id_plataforma = p.id_plataforma " +
                "JOIN generos g ON v.id_genero = g.id_genero " +
                "JOIN compras c ON v.id_juego = c.id_juego";

            Statement stmtJuegos = conexion.createStatement();
            ResultSet rsJuegos = stmtJuegos.executeQuery(sqlJuegos);

            while (rsJuegos.next()) {
                Element v = doc.createElement("videojuego");
                v.setAttribute("id", "v" + rsJuegos.getInt("id_juego"));
                v.setAttribute("usuarioReg", "u" + rsJuegos.getInt("id_usuario_registro"));

                Element titulo = doc.createElement("titulo");
                titulo.setTextContent(rsJuegos.getString("titulo"));
                v.appendChild(titulo);

                Element plataforma = doc.createElement("plataforma");
                plataforma.setTextContent(rsJuegos.getString("nombre_plataforma"));
                v.appendChild(plataforma);

                Element genero = doc.createElement("genero");
                genero.setTextContent(rsJuegos.getString("nombre_genero"));
                v.appendChild(genero);

                Element precios = doc.createElement("precios");
                Element compra = doc.createElement("compra");
                compra.setAttribute("moneda", "EUR");
                compra.setTextContent(String.valueOf(rsJuegos.getDouble("precio_compra")));
                precios.appendChild(compra);

                Element venta = doc.createElement("venta_estimado");
                venta.setAttribute("moneda", "EUR");
                venta.setTextContent(String.valueOf(rsJuegos.getDouble("precio_venta_estimado")));
                precios.appendChild(venta);
                v.appendChild(precios);

                Element stockInfo = doc.createElement("stock_info");
                Element tipo = doc.createElement("tipo");
                tipo.setTextContent(rsJuegos.getString("tipo_stock"));
                stockInfo.appendChild(tipo);

                Element estado = doc.createElement("estado");
                estado.setTextContent(rsJuegos.getString("estado"));
                stockInfo.appendChild(estado);
                v.appendChild(stockInfo);

                Element fecha = doc.createElement("fecha_registro");
                fecha.setTextContent(rsJuegos.getDate("fecha_registro").toString());
                v.appendChild(fecha);

                catalogoNode.appendChild(v);
            }


            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File("src/main/resources/com/retrojuegos/retrojuegos/xml/videojuegos_exportados.xml"));
            transformer.transform(source, result);


    }
}