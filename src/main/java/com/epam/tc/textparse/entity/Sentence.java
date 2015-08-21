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
public class Sentence implements TextComponent {

    private List<TextComponent> sentence;

    public Sentence() {
        sentence = new LinkedList<>();
    }

    @Override
    public TextComponent getTextComponent(int position) {
        return sentence.get(position);
    }

    @Override
    public List<? extends TextComponent> getTextComponents()
            throws InvalidTextComponentLeafOperationException {
        return sentence;
    }

    @Override
    public int getComponentsCount() throws InvalidTextComponentLeafOperationException {
        return sentence.size();
    }

    @Override
    public void addTextComponent(TextComponent component) {
        sentence.add(component);
    }

    @Override
    public void setTextComponent(int position, TextComponent component) {
        sentence.set(position, component);
    }

    @Override
    public void removeTextComponent(int position)
            throws InvalidTextComponentLeafOperationException {
        sentence.remove(position);
    }

}
