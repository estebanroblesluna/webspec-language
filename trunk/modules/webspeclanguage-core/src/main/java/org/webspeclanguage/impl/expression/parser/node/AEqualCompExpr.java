/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.impl.expression.parser.node;

import org.webspeclanguage.impl.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class AEqualCompExpr extends PCompExpr
{
    private PCompExpr _left_;
    private TEqual _equal_;
    private PNumExpr _right_;

    public AEqualCompExpr()
    {
        // Constructor
    }

    public AEqualCompExpr(
        @SuppressWarnings("hiding") PCompExpr _left_,
        @SuppressWarnings("hiding") TEqual _equal_,
        @SuppressWarnings("hiding") PNumExpr _right_)
    {
        // Constructor
        setLeft(_left_);

        setEqual(_equal_);

        setRight(_right_);

    }

    @Override
    public Object clone()
    {
        return new AEqualCompExpr(
            cloneNode(this._left_),
            cloneNode(this._equal_),
            cloneNode(this._right_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAEqualCompExpr(this);
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

    public TEqual getEqual()
    {
        return this._equal_;
    }

    public void setEqual(TEqual node)
    {
        if(this._equal_ != null)
        {
            this._equal_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._equal_ = node;
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
            + toString(this._equal_)
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

        if(this._equal_ == child)
        {
            this._equal_ = null;
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

        if(this._equal_ == oldChild)
        {
            setEqual((TEqual) newChild);
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
