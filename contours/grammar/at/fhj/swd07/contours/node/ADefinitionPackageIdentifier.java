/* This file was generated by SableCC (http://www.sablecc.org/). */

package at.fhj.swd07.contours.node;

import at.fhj.swd07.contours.analysis.*;

@SuppressWarnings("nls")
public final class ADefinitionPackageIdentifier extends PDefinitionPackageIdentifier
{
    private TDefinitionIdentifier _definitionIdentifier_;
    private TLParen _lParen_;
    private TPackageIdentifier _packageIdentifier_;
    private TRParen _rParen_;
    private TSemicolon _semicolon_;

    public ADefinitionPackageIdentifier()
    {
        // Constructor
    }

    public ADefinitionPackageIdentifier(
        @SuppressWarnings("hiding") TDefinitionIdentifier _definitionIdentifier_,
        @SuppressWarnings("hiding") TLParen _lParen_,
        @SuppressWarnings("hiding") TPackageIdentifier _packageIdentifier_,
        @SuppressWarnings("hiding") TRParen _rParen_,
        @SuppressWarnings("hiding") TSemicolon _semicolon_)
    {
        // Constructor
        setDefinitionIdentifier(_definitionIdentifier_);

        setLParen(_lParen_);

        setPackageIdentifier(_packageIdentifier_);

        setRParen(_rParen_);

        setSemicolon(_semicolon_);

    }

    @Override
    public Object clone()
    {
        return new ADefinitionPackageIdentifier(
            cloneNode(this._definitionIdentifier_),
            cloneNode(this._lParen_),
            cloneNode(this._packageIdentifier_),
            cloneNode(this._rParen_),
            cloneNode(this._semicolon_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADefinitionPackageIdentifier(this);
    }

    public TDefinitionIdentifier getDefinitionIdentifier()
    {
        return this._definitionIdentifier_;
    }

    public void setDefinitionIdentifier(TDefinitionIdentifier node)
    {
        if(this._definitionIdentifier_ != null)
        {
            this._definitionIdentifier_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._definitionIdentifier_ = node;
    }

    public TLParen getLParen()
    {
        return this._lParen_;
    }

    public void setLParen(TLParen node)
    {
        if(this._lParen_ != null)
        {
            this._lParen_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lParen_ = node;
    }

    public TPackageIdentifier getPackageIdentifier()
    {
        return this._packageIdentifier_;
    }

    public void setPackageIdentifier(TPackageIdentifier node)
    {
        if(this._packageIdentifier_ != null)
        {
            this._packageIdentifier_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._packageIdentifier_ = node;
    }

    public TRParen getRParen()
    {
        return this._rParen_;
    }

    public void setRParen(TRParen node)
    {
        if(this._rParen_ != null)
        {
            this._rParen_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rParen_ = node;
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
            + toString(this._definitionIdentifier_)
            + toString(this._lParen_)
            + toString(this._packageIdentifier_)
            + toString(this._rParen_)
            + toString(this._semicolon_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._definitionIdentifier_ == child)
        {
            this._definitionIdentifier_ = null;
            return;
        }

        if(this._lParen_ == child)
        {
            this._lParen_ = null;
            return;
        }

        if(this._packageIdentifier_ == child)
        {
            this._packageIdentifier_ = null;
            return;
        }

        if(this._rParen_ == child)
        {
            this._rParen_ = null;
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
        if(this._definitionIdentifier_ == oldChild)
        {
            setDefinitionIdentifier((TDefinitionIdentifier) newChild);
            return;
        }

        if(this._lParen_ == oldChild)
        {
            setLParen((TLParen) newChild);
            return;
        }

        if(this._packageIdentifier_ == oldChild)
        {
            setPackageIdentifier((TPackageIdentifier) newChild);
            return;
        }

        if(this._rParen_ == oldChild)
        {
            setRParen((TRParen) newChild);
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
