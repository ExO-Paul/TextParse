/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.entity;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ExO
 */
public class Word implements SentencePart {

    private List<TextComponent> word;

    public Word() {
        word = new LinkedList<>();
    }

    @Override
    public TextComponent getTextComponent(int position) {
        return word.get(position);
    }

    @Override
    public List<? extends TextComponent> getTextComponents()
            throws InvalidTextComponentLeafOperationException {
        return word;
    }

    @Override
    public int getComponentsCount() throws InvalidTextComponentLeafOperationException {
        return word.size();
    }

    @Override
    public void addTextComponent(TextComponent component) {
        word.add(component);
    }

    @Override
    public void setTextComponent(int position, TextComponent component) {
        word.set(position, component);
    }

    @Override
    public void removeTextComponent(int position)
            throws InvalidTextComponentLeafOperationException {
        word.remove(position);
    }

}
