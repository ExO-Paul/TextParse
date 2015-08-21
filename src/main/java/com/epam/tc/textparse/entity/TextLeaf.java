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
public abstract class TextLeaf implements TextComponent {
    
    public abstract String getValue();
    
    private static final String LEAF_EX_MESSAGE = "Tried to execute TextLeaf's method for components";

    @Override
    public final TextComponent getTextComponent(int position)
            throws InvalidTextComponentLeafOperationException {
        throw new InvalidTextComponentLeafOperationException(LEAF_EX_MESSAGE);
    }

    @Override
    public final List<? extends TextComponent> getTextComponents()
            throws InvalidTextComponentLeafOperationException {
        throw new InvalidTextComponentLeafOperationException(LEAF_EX_MESSAGE);
    }

    @Override
    public int getComponentsCount() 
        throws InvalidTextComponentLeafOperationException {
        throw new InvalidTextComponentLeafOperationException(LEAF_EX_MESSAGE);
    }
    

    @Override
    public final void addTextComponent(TextComponent component)
            throws InvalidTextComponentLeafOperationException {
        throw new InvalidTextComponentLeafOperationException(LEAF_EX_MESSAGE);
    }

    @Override
    public void setTextComponent(int position, TextComponent component)
            throws InvalidTextComponentLeafOperationException {
        throw new InvalidTextComponentLeafOperationException(LEAF_EX_MESSAGE);
    }

    @Override
    public void removeTextComponent(int position) 
            throws InvalidTextComponentLeafOperationException {
        throw new InvalidTextComponentLeafOperationException(LEAF_EX_MESSAGE);
    }
    
    
    
}
