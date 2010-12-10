/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.impl.expression.parser.node;

import org.webspeclanguage.impl.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class TArrayIndex extends Token
{
    public TArrayIndex(String text)
    {
        setText(text);
    }

    public TArrayIndex(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TArrayIndex(getText(), getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTArrayIndex(this);
    }
}
