/* This file was generated by SableCC (http://www.sablecc.org/). */

package at.fhj.swd07.contours.node;

import at.fhj.swd07.contours.analysis.*;

@SuppressWarnings("nls")
public final class AStaticMultiplierComponentElementMultiplier extends PComponentElementMultiplier
{
    private TLParen _lParen_;
    private TIntegerLiteral _integerLiteral_;
    private TRParen _rParen_;

    public AStaticMultiplierComponentElementMultiplier()
    {
        // Constructor
    }

    public AStaticMultiplierComponentElementMultiplier(
        @SuppressWarnings("hiding") TLParen _lParen_,
        @SuppressWarnings("hiding") TIntegerLiteral _integerLiteral_,
        @SuppressWarnings("hiding") TRParen _rParen_)
    {
        // Constructor
        setLParen(_lParen_);

        setIntegerLiteral(_integerLiteral_);

        setRParen(_rParen_);

    }

    @Override
    public Object clone()
    {
        return new AStaticMultiplierComponentElementMultiplier(
            cloneNode(this._lParen_),
            cloneNode(this._integerLiteral_),
            cloneNode(this._rParen_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAStaticMultiplierComponentElementMultiplier(this);
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

    public TIntegerLiteral getIntegerLiteral()
    {
        return this._integerLiteral_;
    }

    public void setIntegerLiteral(TIntegerLiteral node)
    {
        if(this._integerLiteral_ != null)
        {
            this._integerLiteral_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._integerLiteral_ = node;
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._lParen_)
            + toString(this._integerLiteral_)
            + toString(this._rParen_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._lParen_ == child)
        {
            this._lParen_ = null;
            return;
        }

        if(this._integerLiteral_ == child)
        {
            this._integerLiteral_ = null;
            return;
        }

        if(this._rParen_ == child)
        {
            this._rParen_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._lParen_ == oldChild)
        {
            setLParen((TLParen) newChild);
            return;
        }

        if(this._integerLiteral_ == oldChild)
        {
            setIntegerLiteral((TIntegerLiteral) newChild);
            return;
        }

        if(this._rParen_ == oldChild)
        {
            setRParen((TRParen) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
