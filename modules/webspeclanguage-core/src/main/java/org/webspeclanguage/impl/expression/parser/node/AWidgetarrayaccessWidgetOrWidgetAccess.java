/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.impl.expression.parser.node;

import org.webspeclanguage.impl.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class AWidgetarrayaccessWidgetOrWidgetAccess extends PWidgetOrWidgetAccess
{
    private TIdentifier _widget_;
    private TLeftBlock _leftBlock_;
    private PExpr _expr_;
    private TRightBlock _rightBlock_;

    public AWidgetarrayaccessWidgetOrWidgetAccess()
    {
        // Constructor
    }

    public AWidgetarrayaccessWidgetOrWidgetAccess(
        @SuppressWarnings("hiding") TIdentifier _widget_,
        @SuppressWarnings("hiding") TLeftBlock _leftBlock_,
        @SuppressWarnings("hiding") PExpr _expr_,
        @SuppressWarnings("hiding") TRightBlock _rightBlock_)
    {
        // Constructor
        setWidget(_widget_);

        setLeftBlock(_leftBlock_);

        setExpr(_expr_);

        setRightBlock(_rightBlock_);

    }

    @Override
    public Object clone()
    {
        return new AWidgetarrayaccessWidgetOrWidgetAccess(
            cloneNode(this._widget_),
            cloneNode(this._leftBlock_),
            cloneNode(this._expr_),
            cloneNode(this._rightBlock_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAWidgetarrayaccessWidgetOrWidgetAccess(this);
    }

    public TIdentifier getWidget()
    {
        return this._widget_;
    }

    public void setWidget(TIdentifier node)
    {
        if(this._widget_ != null)
        {
            this._widget_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._widget_ = node;
    }

    public TLeftBlock getLeftBlock()
    {
        return this._leftBlock_;
    }

    public void setLeftBlock(TLeftBlock node)
    {
        if(this._leftBlock_ != null)
        {
            this._leftBlock_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._leftBlock_ = node;
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

    public TRightBlock getRightBlock()
    {
        return this._rightBlock_;
    }

    public void setRightBlock(TRightBlock node)
    {
        if(this._rightBlock_ != null)
        {
            this._rightBlock_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rightBlock_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._widget_)
            + toString(this._leftBlock_)
            + toString(this._expr_)
            + toString(this._rightBlock_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._widget_ == child)
        {
            this._widget_ = null;
            return;
        }

        if(this._leftBlock_ == child)
        {
            this._leftBlock_ = null;
            return;
        }

        if(this._expr_ == child)
        {
            this._expr_ = null;
            return;
        }

        if(this._rightBlock_ == child)
        {
            this._rightBlock_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._widget_ == oldChild)
        {
            setWidget((TIdentifier) newChild);
            return;
        }

        if(this._leftBlock_ == oldChild)
        {
            setLeftBlock((TLeftBlock) newChild);
            return;
        }

        if(this._expr_ == oldChild)
        {
            setExpr((PExpr) newChild);
            return;
        }

        if(this._rightBlock_ == oldChild)
        {
            setRightBlock((TRightBlock) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
