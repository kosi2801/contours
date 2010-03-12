/* This file was generated by SableCC (http://www.sablecc.org/). */

package at.fhj.swd07.contours.node;

import at.fhj.swd07.contours.analysis.*;

@SuppressWarnings("nls")
public final class AIdComponentDefinition extends PComponentDefinition
{
    private TIdentifier _identifier_;
    private TDefinitionSeparator _definitionSeparator_;
    private PComponentDefinitionList _componentDefinitionList_;
    private TSemicolon _semicolon_;

    public AIdComponentDefinition()
    {
        // Constructor
    }

    public AIdComponentDefinition(
        @SuppressWarnings("hiding") TIdentifier _identifier_,
        @SuppressWarnings("hiding") TDefinitionSeparator _definitionSeparator_,
        @SuppressWarnings("hiding") PComponentDefinitionList _componentDefinitionList_,
        @SuppressWarnings("hiding") TSemicolon _semicolon_)
    {
        // Constructor
        setIdentifier(_identifier_);

        setDefinitionSeparator(_definitionSeparator_);

        setComponentDefinitionList(_componentDefinitionList_);

        setSemicolon(_semicolon_);

    }

    @Override
    public Object clone()
    {
        return new AIdComponentDefinition(
            cloneNode(this._identifier_),
            cloneNode(this._definitionSeparator_),
            cloneNode(this._componentDefinitionList_),
            cloneNode(this._semicolon_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIdComponentDefinition(this);
    }

    public TIdentifier getIdentifier()
    {
        return this._identifier_;
    }

    public void setIdentifier(TIdentifier node)
    {
        if(this._identifier_ != null)
        {
            this._identifier_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._identifier_ = node;
    }

    public TDefinitionSeparator getDefinitionSeparator()
    {
        return this._definitionSeparator_;
    }

    public void setDefinitionSeparator(TDefinitionSeparator node)
    {
        if(this._definitionSeparator_ != null)
        {
            this._definitionSeparator_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._definitionSeparator_ = node;
    }

    public PComponentDefinitionList getComponentDefinitionList()
    {
        return this._componentDefinitionList_;
    }

    public void setComponentDefinitionList(PComponentDefinitionList node)
    {
        if(this._componentDefinitionList_ != null)
        {
            this._componentDefinitionList_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._componentDefinitionList_ = node;
    }

    public TSemicolon getSemicolon()
    {
        return this._semicolon_;
    }

    public void setSemicolon(TSemicolon node)
    {
        if(this._semicolon_ != null)
        {
            this._semicolon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._semicolon_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._identifier_)
            + toString(this._definitionSeparator_)
            + toString(this._componentDefinitionList_)
            + toString(this._semicolon_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._identifier_ == child)
        {
            this._identifier_ = null;
            return;
        }

        if(this._definitionSeparator_ == child)
        {
            this._definitionSeparator_ = null;
            return;
        }

        if(this._componentDefinitionList_ == child)
        {
            this._componentDefinitionList_ = null;
            return;
        }

        if(this._semicolon_ == child)
        {
            this._semicolon_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._identifier_ == oldChild)
        {
            setIdentifier((TIdentifier) newChild);
            return;
        }

        if(this._definitionSeparator_ == oldChild)
        {
            setDefinitionSeparator((TDefinitionSeparator) newChild);
            return;
        }

        if(this._componentDefinitionList_ == oldChild)
        {
            setComponentDefinitionList((PComponentDefinitionList) newChild);
            return;
        }

        if(this._semicolon_ == oldChild)
        {
            setSemicolon((TSemicolon) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
