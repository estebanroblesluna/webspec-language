/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.expression.parser.node;

import org.webspeclanguage.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class AImpliesExpr extends PExpr
{
    private PExpr _left_;
    private TImplies _implies_;
    private PCompExpr _right_;

    public AImpliesExpr()
    {
        // Constructor
    }

    public AImpliesExpr(
        @SuppressWarnings("hiding") PExpr _left_,
        @SuppressWarnings("hiding") TImplies _implies_,
        @SuppressWarnings("hiding") PCompExpr _right_)
    {
        // Constructor
        setLeft(_left_);

        setImplies(_implies_);

        setRight(_right_);

    }

    @Override
    public Object clone()
    {
        return new AImpliesExpr(
            cloneNode(this._left_),
            cloneNode(this._implies_),
            cloneNode(this._right_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAImpliesExpr(this);
    }

    public PExpr getLeft()
    {
        return this._left_;
    }

    public void setLeft(PExpr node)
    {
        if(this._left_ != null)
        {
            this._left_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._left_ = node;
    }

    public TImplies getImplies()
    {
        return this._implies_;
    }

    public void setImplies(TImplies node)
    {
        if(this._implies_ != null)
        {
            this._implies_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._implies_ = node;
    }

    public PCompExpr getRight()
    {
        return this._right_;
    }

    public void setRight(PCompExpr node)
    {
        if(this._right_ != null)
        {
            this._right_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._right_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._left_)
            + toString(this._implies_)
            + toString(this._right_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._left_ == child)
        {
            this._left_ = null;
            return;
        }

        if(this._implies_ == child)
        {
            this._implies_ = null;
            return;
        }

        if(this._right_ == child)
        {
            this._right_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._left_ == oldChild)
        {
            setLeft((PExpr) newChild);
            return;
        }

        if(this._implies_ == oldChild)
        {
            setImplies((TImplies) newChild);
            return;
        }

        if(this._right_ == oldChild)
        {
            setRight((PCompExpr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
