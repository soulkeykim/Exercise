package com.soulkey.androidexercise.Struct;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

/**
 * Created by Soulkey Kim on 6/02/15.
 */
@Table(name = "ECRow")
public class ECRow extends Model implements Serializable {
    @Column(name = "Description")
    public String description;

    @Column(name = "Title")
    public String title;

    @Column(name = "imageHref")
    public String imageHref;

    public ECRow()
    {
    }
}