/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.impl.expression.parser.node;

import org.webspeclanguage.impl.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class ANotEqualCompExpr extends PCompExpr
{
    private PCompExpr _left_;
    private TNotEqual _notEqual_;
    private PNumExpr _right_;

    public ANotEqualCompExpr()
    {
        // Constructor
    }

    public ANotEqualCompExpr(
        @SuppressWarnings("hiding") PCompExpr _left_,
        @SuppressWarnings("hiding") TNotEqual _notEqual_,
        @SuppressWarnings("hiding") PNumExpr _right_)
    {
        // Constructor
        setLeft(_left_);

        setNotEqual(_notEqual_);

        setRight(_right_);

    }

    @Override
    public Object clone()
    {
        return new ANotEqualCompExpr(
            cloneNode(this._left_),
            cloneNode(this._notEqual_),
            cloneNode(this._right_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANotEqualCompExpr(this);
    }

    public PCompExpr getLeft()
    {
        return this._left_;
    }

    public void setLeft(PCompExpr node)
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

    public TNotEqual getNotEqual()
    {
        return this._notEqual_;
    }

    public void setNotEqual(TNotEqual node)
    {
        if(this._notEqual_ != null)
        {
            this._notEqual_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._notEqual_ = node;
    }

    public PNumExpr getRight()
    {
        return this._right_;
    }

    public void setRight(PNumExpr node)
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
            + toString(this._notEqual_)
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

        if(this._notEqual_ == child)
        {
            this._notEqual_ = null;
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
            setLeft((PCompExpr) newChild);
            return;
        }

        if(this._notEqual_ == oldChild)
        {
            setNotEqual((TNotEqual) newChild);
            return;
        }

        if(this._right_ == oldChild)
        {
            setRight((PNumExpr) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
