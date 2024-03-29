/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.impl.expression.parser.node;

import org.webspeclanguage.impl.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class ACompExprExpr extends PExpr
{
    private PCompExpr _compExpr_;

    public ACompExprExpr()
    {
        // Constructor
    }

    public ACompExprExpr(
        @SuppressWarnings("hiding") PCompExpr _compExpr_)
    {
        // Constructor
        setCompExpr(_compExpr_);

    }

    @Override
    public Object clone()
    {
        return new ACompExprExpr(
            cloneNode(this._compExpr_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACompExprExpr(this);
    }

    public PCompExpr getCompExpr()
    {
        return this._compExpr_;
    }

    public void setCompExpr(PCompExpr node)
    {
        if(this._compExpr_ != null)
        {
            this._compExpr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._compExpr_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._compExpr_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._compExpr_ == child)
        {
            this._compExpr_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._compExpr_ == oldChild)
        {
            setCompExpr((PCompExpr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
