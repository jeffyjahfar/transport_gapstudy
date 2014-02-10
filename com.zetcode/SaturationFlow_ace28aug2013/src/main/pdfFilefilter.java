package main;


import java.io.File;
import javax.swing.filechooser.FileFilter;

public class pdfFilefilter extends FileFilter
{
/* accept directories and files with ".pdf" extension */
public boolean accept(File f)
{
return f.getName().toLowerCase().endsWith(".pdf")
| f.isDirectory();
}
/* description of file type */
public String getDescription()
{
return "Portable Document Format (.pdf)";
}
}