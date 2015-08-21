/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ExO
 */
public class Text implements TextComponent {

    private List<TextComponent> text;

    public Text() {
        text = new LinkedList<>();
    }

    @Override
    public TextComponent getTextComponent(int position) {
        return text.get(position);
    }

    @Override
    public List<? extends TextComponent> getTextComponents()
            throws InvalidTextComponentLeafOperationException {
        return text;
    }

    @Override
    public int getComponentsCount() throws InvalidTextComponentLeafOperationException {
        return text.size();
    }

    @Override
    public void addTextComponent(TextComponent component) {
        text.add(component);
    }

    @Override
    public void setTextComponent(int position, TextComponent component) {
        text.set(position, component);
    }

    @Override
    public void removeTextComponent(int position)
            throws InvalidTextComponentLeafOperationException {
        text.remove(position);
    }

}
