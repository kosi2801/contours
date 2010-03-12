/* This file was generated by SableCC (http://www.sablecc.org/). */

package at.fhj.swd07.contours.node;

import at.fhj.swd07.contours.analysis.*;

@SuppressWarnings("nls")
public final class TIntegerLiteral extends Token
{
    public TIntegerLiteral(String text)
    {
        setText(text);
    }

    public TIntegerLiteral(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TIntegerLiteral(getText(), getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTIntegerLiteral(this);
    }
}
