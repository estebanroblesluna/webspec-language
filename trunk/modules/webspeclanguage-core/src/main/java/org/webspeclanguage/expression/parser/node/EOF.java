/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.expression.parser.node;

import org.webspeclanguage.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class EOF extends Token
{
    public EOF()
    {
        setText("");
    }

    public EOF(int line, int pos)
    {
        setText("");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
        return new EOF(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseEOF(this);
    }
}
