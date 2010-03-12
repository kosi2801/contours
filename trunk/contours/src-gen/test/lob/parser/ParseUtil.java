package test.lob.parser;

import java.io.*;
import test.lob.exception.ParseException;

final class ParseUtil { 

        static final byte[] readBytes(final InputStream is, final ParseContext context, final int size) throws IOException, ParseException {
            final byte[] attr0 = new byte[size]; 
            final long readBytes = is.read(attr0, 0, size); 
            if(readBytes < size) {
                throw new ParseException("Tried to read " + Integer.toString(size) + " bytes but hit EOF instead.", context.position);
            }
            context.position += size;
            return attr0;
        }

        static final int readInt(final InputStream is, final ParseContext context) throws IOException, ParseException {
            final byte[] intBytes = readBytes(is, context, 4); 
            return ((int)intBytes[0] << 24)
                + (((int)intBytes[1] & 0xFF) << 16)
                + (((int)intBytes[2] & 0xFF) << 8)
                +  ((int)intBytes[3] & 0xFF);
        }

        static final long readLong(final InputStream is, final ParseContext context) throws IOException, ParseException {
            final byte[] intBytes = readBytes(is, context, 8); 
            return ((long)intBytes[0] << 56)
                + (((long)intBytes[1] & 0xFF) << 48)
                + (((long)intBytes[2] & 0xFF) << 40)
                + (((long)intBytes[3] & 0xFF) << 32)
                + (((long)intBytes[4] & 0xFF) << 24)
                + (((long)intBytes[5] & 0xFF) << 16)
                + (((long)intBytes[6] & 0xFF) << 8)
                +  ((long)intBytes[7] & 0xFF);
        }

        static final String readBcd(final InputStream is, final ParseContext context, final int size) throws IOException, ParseException {
            final byte[] bcdBytes = readBytes(is, context, size);

            final StringBuilder buf = new StringBuilder(bcdBytes.length * 2);
            for(final byte b : bcdBytes) {
                // higher nibble
                if((b & 0xf0) < 0xa0) {
                    buf.append((char)(((b & 0xf0) >> 4) + '0'));
                } else {
                    buf.append(' ');
                }
                // lower nibble
                if((b & 0x0f) < 0x0a) {
                    buf.append((char)((b & 0x0f) + '0'));
                } else {
                    buf.append(' ');
                }
            }
            return buf.toString().trim();
        }

        static final void skipBytes(final InputStream is, final ParseContext context, final int size) throws IOException, ParseException {
            final long availableBytes = is.available(); 
            final long readBytes = is.skip(size); 
            /* also check available bytes before skip, as FileInputStream may skip beyond EOF without error.
               http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6294974 */
            if((readBytes < size) || (availableBytes < size)) {
                throw new ParseException("Tried to skip " + Integer.toString(size) + " bytes but hit EOF instead.", context.position);
            }
            context.position += size;
        }

};
