package com.jihan.moni_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * @author Jihan
 * @date 2019/8/17
 */
public enum  EcIcons implements Icon {

    icon_scan('\u0310');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
