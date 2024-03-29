/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.impl.expression.parser.node;

import org.webspeclanguage.impl.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class TStringType extends Token
{
    public TStringType()
    {
        super.setText("String");
    }

    public TStringType(int line, int pos)
    {
        super.setText("String");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TStringType(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTStringType(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TStringType text.");
    }
}
