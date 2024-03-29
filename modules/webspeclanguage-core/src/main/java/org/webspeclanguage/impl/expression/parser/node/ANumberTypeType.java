/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.webspeclanguage.impl.expression.parser.node;

import org.webspeclanguage.impl.expression.parser.analysis.*;

@SuppressWarnings("nls")
public final class ANumberTypeType extends PType
{
    private TNumberType _numberType_;

    public ANumberTypeType()
    {
        // Constructor
    }

    public ANumberTypeType(
        @SuppressWarnings("hiding") TNumberType _numberType_)
    {
        // Constructor
        setNumberType(_numberType_);

    }

    @Override
    public Object clone()
    {
        return new ANumberTypeType(
            cloneNode(this._numberType_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANumberTypeType(this);
    }

    public TNumberType getNumberType()
    {
        return this._numberType_;
    }

    public void setNumberType(TNumberType node)
    {
        if(this._numberType_ != null)
        {
            this._numberType_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._numberType_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._numberType_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._numberType_ == child)
        {
            this._numberType_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._numberType_ == oldChild)
        {
            setNumberType((TNumberType) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
