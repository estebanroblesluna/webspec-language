/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.expression.parser.node;

import org.webspeclanguage.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class AParensValue extends PValue
{
    private TLeftParen _leftParen_;
    private PExpr _expr_;
    private TRightParen _rightParen_;

    public AParensValue()
    {
        // Constructor
    }

    public AParensValue(
        @SuppressWarnings("hiding") TLeftParen _leftParen_,
        @SuppressWarnings("hiding") PExpr _expr_,
        @SuppressWarnings("hiding") TRightParen _rightParen_)
    {
        // Constructor
        setLeftParen(_leftParen_);

        setExpr(_expr_);

        setRightParen(_rightParen_);

    }

    @Override
    public Object clone()
    {
        return new AParensValue(
            cloneNode(this._leftParen_),
            cloneNode(this._expr_),
            cloneNode(this._rightParen_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAParensValue(this);
    }

    public TLeftParen getLeftParen()
    {
        return this._leftParen_;
    }

    public void setLeftParen(TLeftParen node)
    {
        if(this._leftParen_ != null)
        {
            this._leftParen_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._leftParen_ = node;
    }

    public PExpr getExpr()
    {
        return this._expr_;
    }

    public void setExpr(PExpr node)
    {
        if(this._expr_ != null)
        {
            this._expr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expr_ = node;
    }

    public TRightParen getRightParen()
    {
        return this._rightParen_;
    }

    public void setRightParen(TRightParen node)
    {
        if(this._rightParen_ != null)
        {
            this._rightParen_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rightParen_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._leftParen_)
            + toString(this._expr_)
            + toString(this._rightParen_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._leftParen_ == child)
        {
            this._leftParen_ = null;
            return;
        }

        if(this._expr_ == child)
        {
            this._expr_ = null;
            return;
        }

        if(this._rightParen_ == child)
        {
            this._rightParen_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._leftParen_ == oldChild)
        {
            setLeftParen((TLeftParen) newChild);
            return;
        }

        if(this._expr_ == oldChild)
        {
            setExpr((PExpr) newChild);
            return;
        }

        if(this._rightParen_ == oldChild)
        {
            setRightParen((TRightParen) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
