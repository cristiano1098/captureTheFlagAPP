/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

/**
 *
 * @author crist
 */
public interface NetworkADT<T> extends GraphADT<T>{
    
    public void addEdge(T vertex1, T vertex2, double weight);
    
    public double shortestPathWeight(T vertex1, T vertex2);
}
