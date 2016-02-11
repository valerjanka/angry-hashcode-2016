package com.angry.hashcode.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

/**
 * Created by valerii.ryzhuk on 2/11/2016.
 */
public class FastReader
{
    BufferedReader in;
    StringTokenizer tok;
    String line;
    String fileName;

    public FastReader(String fileName) throws FileNotFoundException
    {
        this.fileName = fileName;
        in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
    }

    public int nextInt() throws IOException
    {
        return parseInt(next());
    }

    public long nextLong() throws IOException
    {
        return parseLong(next());
    }

    public double nextDouble() throws IOException
    {
        return parseDouble(next());
    }

    public String curLine() {
        return line;
    }

    public String next() throws IOException
    {
        while ( tok == null || !tok.hasMoreTokens() )
        {
            line = in.readLine();
            tok = new StringTokenizer(line.trim());
        }
        return tok.nextToken();
    }

    public String nextLine() throws IOException
    {
        if ( tok == null || !tok.hasMoreTokens() )
        {
            line = in.readLine();
        }
        return line;
    }

    public boolean hasNext() throws Exception
    {
        if ( tok == null || !tok.hasMoreTokens() )
        {
            line = in.readLine();
            if ( line != null )
            {
                tok = new StringTokenizer(line.trim());
                return hasNext();
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    public void close()
    {
        try
        {
            in.close();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

    public String getFileName()
    {
        return fileName;
    }
}
