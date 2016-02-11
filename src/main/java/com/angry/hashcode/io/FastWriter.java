package com.angry.hashcode.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by valerii.ryzhuk on 2/11/2016.
 */
public class FastWriter extends PrintWriter
{
    private String fileName;

    public FastWriter(String fileName) throws FileNotFoundException
    {
        super(new OutputStreamWriter(new FileOutputStream(fileName)));
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
}
