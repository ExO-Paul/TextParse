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
public class Paragraph implements TextBlock {

    private List<TextComponent> paragraph;

    public Paragraph() {
        paragraph = new LinkedList<>();
    }

    @Override
    public TextComponent getTextComponent(int position) {
        return paragraph.get(position);
    }

    @Override
    public List<? extends TextComponent> getTextComponents()
            throws InvalidTextComponentLeafOperationException {
        return paragraph;
    }

    @Override
    public int getComponentsCount() throws InvalidTextComponentLeafOperationException {
        return paragraph.size();
    }

    @Override
    public void addTextComponent(TextComponent component) {
        paragraph.add(component);
    }

    @Override
    public void setTextComponent(int position, TextComponent component) {
        paragraph.set(position, component);
    }

    @Override
    public void removeTextComponent(int position)
            throws InvalidTextComponentLeafOperationException {
        paragraph.remove(position);
    }

}
