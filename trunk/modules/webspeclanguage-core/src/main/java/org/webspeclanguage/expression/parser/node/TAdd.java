/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.expression.parser.node;

import org.webspeclanguage.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class TAdd extends Token
{
    public TAdd()
    {
        super.setText("+");
    }

    public TAdd(int line, int pos)
    {
        super.setText("+");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TAdd(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTAdd(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TAdd text.");
    }
}
