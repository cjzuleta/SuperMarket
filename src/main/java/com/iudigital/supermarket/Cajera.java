/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iudigital.supermarket;

import java.util.List;

/**
 *
 * @author Cj Zuleta
 */

/*Clase cajera q representa a una cajera que procesa la compra de un cliente 
extiende de Thread para que cada instancia de la cajera se ejecute en un hilo dfte*/
public class Cajera extends Thread{
    private String nombre;
    private List<Cliente> clientes;
    private double tiempoTotalCobro;
    private double tiempoTotalCobroCliente;
    
    //Constructor de la clase
    public Cajera(String nombre, List<Cliente> clientes) {
        this.nombre = nombre;
        this.clientes = clientes;
        this.tiempoTotalCobro = 0;
    }
    
    //metodo que define el comportamiento del hilo cuando se ejecuta para procesar las compras de los clientes
    public void run() {
        for (Cliente cliente : clientes) {
            tiempoTotalCobro += procesarCompra(cliente);
        }
    }
    
    //metodos para calcular los tiempos
    public double getTiempoTotalCobro() {
        return tiempoTotalCobro;
    }
    
    public double getTiempoTotalCobroCliente() {
        return tiempoTotalCobroCliente;
    }
    
    //metodo para procesar la compra
    private double procesarCompra(Cliente cliente) {
        System.out.println("La cajera " + this.nombre + " Comienza a procesar la compra del cliente " + cliente.getNombre());

        // Simular el tiempo de procesamiento por cada producto
        double tiempoTotal = 0;
        for (Producto producto : cliente.getProductos()) {
            System.out.println("Procesando producto: " + producto.getNombre());
            tiempoTotal += producto.getPrecio();
            try {
                Thread.sleep(1000); // Pausa de 1 segundo para simular el procesamiento del producto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

       
        System.out.println("La compra de " + cliente.getNombre() + " ha terminado de procesar.");

        // Mostrar detalles de productos comprados y costo total de cada producto
        System.out.println("Detalles de la compra de " + cliente.getNombre() + ":");
        for (Producto producto : cliente.getProductos()) {
            System.out.println("Producto: " + producto.getNombre() + ", Precio: $" + producto.getPrecio());
        }
        System.out.println("Costo total de la compra: $" + tiempoTotal);
        
        // Guardar el tiempo de cobro para este cliente
        tiempoTotalCobroCliente += tiempoTotal;
        
        System.out.println("Tiempo de cobro para el cliente " + cliente.getNombre() + ": " + tiempoTotal + " segundos\n");

        return tiempoTotal;
    }
}
