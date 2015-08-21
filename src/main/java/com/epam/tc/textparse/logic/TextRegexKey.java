/*
 * Copyright (C) 2015 Pavel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.epam.tc.textparse.logic;

/**
 *
 * @author Pavel
 */
public enum TextRegexKey {

    REGEX_PARAGRAPH("regex.paragraph"),
    REGEX_CODE("regex.code"),
    REGEX_SENTENCE("regex.sentence"),
    REGEX_WORD("regex.word"),
    REGEX_GLYPH("regex.glyph");

    private final String key;

    private TextRegexKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
