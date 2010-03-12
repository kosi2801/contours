/* This file was generated by SableCC (http://www.sablecc.org/). */

package at.fhj.swd07.contours.node;

import at.fhj.swd07.contours.analysis.*;

@SuppressWarnings("nls")
public final class AContextNameComponentElementMultiplierOrContextName extends PComponentElementMultiplierOrContextName
{
    private PComponentElementContextName _componentElementContextName_;

    public AContextNameComponentElementMultiplierOrContextName()
    {
        // Constructor
    }

    public AContextNameComponentElementMultiplierOrContextName(
        @SuppressWarnings("hiding") PComponentElementContextName _componentElementContextName_)
    {
        // Constructor
        setComponentElementContextName(_componentElementContextName_);

    }

    @Override
    public Object clone()
    {
        return new AContextNameComponentElementMultiplierOrContextName(
            cloneNode(this._componentElementContextName_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAContextNameComponentElementMultiplierOrContextName(this);
    }

    public PComponentElementContextName getComponentElementContextName()
    {
        return this._componentElementContextName_;
    }

    public void setComponentElementContextName(PComponentElementContextName node)
    {
        if(this._componentElementContextName_ != null)
        {
            this._componentElementContextName_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._componentElementContextName_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._componentElementContextName_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._componentElementContextName_ == child)
        {
            this._componentElementContextName_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._componentElementContextName_ == oldChild)
        {
            setComponentElementContextName((PComponentElementContextName) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
