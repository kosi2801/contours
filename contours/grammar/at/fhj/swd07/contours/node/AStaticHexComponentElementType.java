/* This file was generated by SableCC (http://www.sablecc.org/). */

package at.fhj.swd07.contours.node;

import at.fhj.swd07.contours.analysis.*;

@SuppressWarnings("nls")
public final class AStaticHexComponentElementType extends PComponentElementType
{
    private THexLiteral _hexLiteral_;

    public AStaticHexComponentElementType()
    {
        // Constructor
    }

    public AStaticHexComponentElementType(
        @SuppressWarnings("hiding") THexLiteral _hexLiteral_)
    {
        // Constructor
        setHexLiteral(_hexLiteral_);

    }

    @Override
    public Object clone()
    {
        return new AStaticHexComponentElementType(
            cloneNode(this._hexLiteral_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAStaticHexComponentElementType(this);
    }

    public THexLiteral getHexLiteral()
    {
        return this._hexLiteral_;
    }

    public void setHexLiteral(THexLiteral node)
    {
        if(this._hexLiteral_ != null)
        {
            this._hexLiteral_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._hexLiteral_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._hexLiteral_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._hexLiteral_ == child)
        {
            this._hexLiteral_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._hexLiteral_ == oldChild)
        {
            setHexLiteral((THexLiteral) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
