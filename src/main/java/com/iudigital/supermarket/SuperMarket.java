/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.iudigital.supermarket;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cj Zuleta
 */

//Clase supermercado representa al supermercado y coordina el proceso de cobro.
public class SuperMarket {
    
    private List<Cajera> cajeras;
    private List<Cliente> clientes;
    
    public SuperMarket(List<Cajera> cajeras, List<Cliente> clientes) {
        this.cajeras = cajeras;
        this.clientes = clientes;
    }
    
    //metodo para simular el cobro
    public void simularCobro() {
        List<Thread> hilosCajeras = new ArrayList<>();

        for (Cajera cajera : cajeras) {
            Thread hiloCajera = new Thread(cajera);
            hilosCajeras.add(hiloCajera);
            hiloCajera.start();
        }

        // Esperar a que todos los hilos de cajeras terminen
        for (Thread hiloCajera : hilosCajeras) {
            try {
                hiloCajera.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
          // Calcular el tiempo total de cobro para todas las compras
        double tiempoTotalCobro = 0;
        for (Cajera cajera : cajeras) {
            tiempoTotalCobro += cajera.getTiempoTotalCobro();
        }

        System.out.println("\nTiempo total de cobro para todas las compras: " + tiempoTotalCobro + " segundos");
    }
    
    public static void main(String[] args) {
        
        // Crear productos
        Producto leche = new Producto("Leche", 5);
        Producto pan = new Producto("Pan", 2);
        Producto huevos = new Producto("Huevos", 3);

        // Crear clientes
        List<Producto> productosCliente1 = new ArrayList<>();
        productosCliente1.add(leche);
        productosCliente1.add(pan);
        Cliente cliente1 = new Cliente("Cliente 1", productosCliente1);

        List<Producto> productosCliente2 = new ArrayList<>();
        productosCliente2.add(huevos);
        productosCliente2.add(huevos);
        productosCliente2.add(leche);
        productosCliente2.add(pan);
        Cliente cliente2 = new Cliente("Cliente 2", productosCliente2);

        // Crear cajeras
        List<Cliente> clientesCajera1 = new ArrayList<>();
        clientesCajera1.add(cliente1);
        Cajera cajera1 = new Cajera("Cajera 1", clientesCajera1);

        List<Cliente> clientesCajera2 = new ArrayList<>();
        clientesCajera2.add(cliente2);
        Cajera cajera2 = new Cajera("Cajera 2", clientesCajera2);

        List<Cajera> cajeras = new ArrayList<>();
        cajeras.add(cajera1);
        cajeras.add(cajera2);
        
        // instanciar el supermercado
        SuperMarket supermercado = new SuperMarket(cajeras, null);

        // Llamar a la simulación de cobro
        supermercado.simularCobro();
    } 
}
