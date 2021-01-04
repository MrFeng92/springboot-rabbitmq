package com.feng.springbootrabbitmq.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
//@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Book implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String bookName;
    private String author;

    public Book()
    {
    }

    public Book(String bookName, String author)
    {
        this.bookName = bookName;
        this.author = author;
    }

    public String getBookName()
    {
        return bookName;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    @Override
    public String toString()
    {
        return "Book{" + "bookName='" + bookName + '\'' + ", author='" + author + '\'' + '}';
    }
}
