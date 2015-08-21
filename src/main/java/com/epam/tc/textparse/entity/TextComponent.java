/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.entity;

import java.util.List;

/**
 *
 * @author ExO
 */
public interface TextComponent {
    
    TextComponent getTextComponent(int position) 
            throws InvalidTextComponentLeafOperationException;
    
    List<? extends TextComponent> getTextComponents()
            throws InvalidTextComponentLeafOperationException;
    
    int getComponentsCount() 
            throws InvalidTextComponentLeafOperationException;
    
    void addTextComponent(TextComponent component) 
            throws InvalidTextComponentLeafOperationException;
    
    void setTextComponent(int position, TextComponent component)
            throws InvalidTextComponentLeafOperationException;
    
    void removeTextComponent(int position)
            throws InvalidTextComponentLeafOperationException;
}
