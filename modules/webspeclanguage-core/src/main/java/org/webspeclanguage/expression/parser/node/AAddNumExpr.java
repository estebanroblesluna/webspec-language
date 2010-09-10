/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.expression.parser.node;

import org.webspeclanguage.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class AAddNumExpr extends PNumExpr
{
    private PNumExpr _left_;
    private TAdd _add_;
    private PFactor _right_;

    public AAddNumExpr()
    {
        // Constructor
    }

    public AAddNumExpr(
        @SuppressWarnings("hiding") PNumExpr _left_,
        @SuppressWarnings("hiding") TAdd _add_,
        @SuppressWarnings("hiding") PFactor _right_)
    {
        // Constructor
        setLeft(_left_);

        setAdd(_add_);

        setRight(_right_);

    }

    @Override
    public Object clone()
    {
        return new AAddNumExpr(
            cloneNode(this._left_),
            cloneNode(this._add_),
            cloneNode(this._right_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAddNumExpr(this);
    }

    public PNumExpr getLeft()
    {
        return this._left_;
    }

    public void setLeft(PNumExpr node)
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

    public TAdd getAdd()
    {
        return this._add_;
    }

    public void setAdd(TAdd node)
    {
        if(this._add_ != null)
        {
            this._add_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._add_ = node;
    }

    public PFactor getRight()
    {
        return this._right_;
    }

    public void setRight(PFactor node)
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
            + toString(this._add_)
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

        if(this._add_ == child)
        {
            this._add_ = null;
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
            setLeft((PNumExpr) newChild);
            return;
        }

        if(this._add_ == oldChild)
        {
            setAdd((TAdd) newChild);
            return;
        }

        if(this._right_ == oldChild)
        {
            setRight((PFactor) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
