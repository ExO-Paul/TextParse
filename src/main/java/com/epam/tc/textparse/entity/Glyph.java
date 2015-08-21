/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.tc.textparse.entity;

/**
 *
 * @author ExO
 */
public class Glyph extends TextLeaf implements SentencePart {

    private String glyph = "";

    public Glyph(String glyph) {
        this.glyph = glyph;
    }

    @Override
    public String getValue() {
        return glyph;
    }

}
