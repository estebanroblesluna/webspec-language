/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.impl.expression.parser.node;

import org.webspeclanguage.impl.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class AGreaterEqualCompExpr extends PCompExpr
{
    private PCompExpr _left_;
    private TGreaterEqual _greaterEqual_;
    private PNumExpr _right_;

    public AGreaterEqualCompExpr()
    {
        // Constructor
    }

    public AGreaterEqualCompExpr(
        @SuppressWarnings("hiding") PCompExpr _left_,
        @SuppressWarnings("hiding") TGreaterEqual _greaterEqual_,
        @SuppressWarnings("hiding") PNumExpr _right_)
    {
        // Constructor
        setLeft(_left_);

        setGreaterEqual(_greaterEqual_);

        setRight(_right_);

    }

    @Override
    public Object clone()
    {
        return new AGreaterEqualCompExpr(
            cloneNode(this._left_),
            cloneNode(this._greaterEqual_),
            cloneNode(this._right_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAGreaterEqualCompExpr(this);
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

    public TGreaterEqual getGreaterEqual()
    {
        return this._greaterEqual_;
    }

    public void setGreaterEqual(TGreaterEqual node)
    {
        if(this._greaterEqual_ != null)
        {
            this._greaterEqual_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._greaterEqual_ = node;
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
            + toString(this._greaterEqual_)
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

        if(this._greaterEqual_ == child)
        {
            this._greaterEqual_ = null;
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

        if(this._greaterEqual_ == oldChild)
        {
            setGreaterEqual((TGreaterEqual) newChild);
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