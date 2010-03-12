/* This file was generated by SableCC (http://www.sablecc.org/). */

package at.fhj.swd07.contours.node;

import at.fhj.swd07.contours.analysis.*;

@SuppressWarnings("nls")
public final class TLParen extends Token
{
    public TLParen()
    {
        super.setText("(");
    }

    public TLParen(int line, int pos)
    {
        super.setText("(");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TLParen(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTLParen(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TLParen text.");
    }
}