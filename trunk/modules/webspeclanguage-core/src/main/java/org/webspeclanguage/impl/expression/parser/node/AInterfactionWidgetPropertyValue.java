/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.impl.expression.parser.node;

import org.webspeclanguage.impl.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class AInterfactionWidgetPropertyValue extends PValue
{
    private TIdentifier _interaction_;
    private TPoint _point_;
    private PWidgetOrWidgetAccessListWithProperty _widgets_;
    private TPoint _p2_;
    private TIdentifier _property_;

    public AInterfactionWidgetPropertyValue()
    {
        // Constructor
    }

    public AInterfactionWidgetPropertyValue(
        @SuppressWarnings("hiding") TIdentifier _interaction_,
        @SuppressWarnings("hiding") TPoint _point_,
        @SuppressWarnings("hiding") PWidgetOrWidgetAccessListWithProperty _widgets_,
        @SuppressWarnings("hiding") TPoint _p2_,
        @SuppressWarnings("hiding") TIdentifier _property_)
    {
        // Constructor
        setInteraction(_interaction_);

        setPoint(_point_);

        setWidgets(_widgets_);

        setP2(_p2_);

        setProperty(_property_);

    }

    @Override
    public Object clone()
    {
        return new AInterfactionWidgetPropertyValue(
            cloneNode(this._interaction_),
            cloneNode(this._point_),
            cloneNode(this._widgets_),
            cloneNode(this._p2_),
            cloneNode(this._property_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInterfactionWidgetPropertyValue(this);
    }

    public TIdentifier getInteraction()
    {
        return this._interaction_;
    }

    public void setInteraction(TIdentifier node)
    {
        if(this._interaction_ != null)
        {
            this._interaction_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._interaction_ = node;
    }

    public TPoint getPoint()
    {
        return this._point_;
    }

    public void setPoint(TPoint node)
    {
        if(this._point_ != null)
        {
            this._point_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._point_ = node;
    }

    public PWidgetOrWidgetAccessListWithProperty getWidgets()
    {
        return this._widgets_;
    }

    public void setWidgets(PWidgetOrWidgetAccessListWithProperty node)
    {
        if(this._widgets_ != null)
        {
            this._widgets_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._widgets_ = node;
    }

    public TPoint getP2()
    {
        return this._p2_;
    }

    public void setP2(TPoint node)
    {
        if(this._p2_ != null)
        {
            this._p2_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._p2_ = node;
    }

    public TIdentifier getProperty()
    {
        return this._property_;
    }

    public void setProperty(TIdentifier node)
    {
        if(this._property_ != null)
        {
            this._property_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._property_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._interaction_)
            + toString(this._point_)
            + toString(this._widgets_)
            + toString(this._p2_)
            + toString(this._property_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._interaction_ == child)
        {
            this._interaction_ = null;
            return;
        }

        if(this._point_ == child)
        {
            this._point_ = null;
            return;
        }

        if(this._widgets_ == child)
        {
            this._widgets_ = null;
            return;
        }

        if(this._p2_ == child)
        {
            this._p2_ = null;
            return;
        }

        if(this._property_ == child)
        {
            this._property_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._interaction_ == oldChild)
        {
            setInteraction((TIdentifier) newChild);
            return;
        }

        if(this._point_ == oldChild)
        {
            setPoint((TPoint) newChild);
            return;
        }

        if(this._widgets_ == oldChild)
        {
            setWidgets((PWidgetOrWidgetAccessListWithProperty) newChild);
            return;
        }

        if(this._p2_ == oldChild)
        {
            setP2((TPoint) newChild);
            return;
        }

        if(this._property_ == oldChild)
        {
            setProperty((TIdentifier) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}