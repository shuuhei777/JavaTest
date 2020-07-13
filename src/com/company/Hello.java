/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;

import java.sql.Connection;
import java.sql.Statement;
import utils.DbUtils;
import utils.Utils;

/**
 *
 * @author shuhei
 */
public class Hello {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for(int i=0;i<255;i++)
            System.out.println("Hello World!");
    }

}
