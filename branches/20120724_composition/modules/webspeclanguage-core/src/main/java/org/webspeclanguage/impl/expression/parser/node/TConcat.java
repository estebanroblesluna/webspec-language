/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.impl.expression.parser.node;

import org.webspeclanguage.impl.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class TConcat extends Token
{
    public TConcat()
    {
        super.setText("#");
    }

    public TConcat(int line, int pos)
    {
        super.setText("#");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TConcat(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTConcat(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TConcat text.");
    }
}
