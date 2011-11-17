/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.impl.expression.parser.node;

import org.webspeclanguage.impl.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class TLeftBrace extends Token
{
    public TLeftBrace(String text)
    {
        setText(text);
    }

    public TLeftBrace(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TLeftBrace(getText(), getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTLeftBrace(this);
    }
}