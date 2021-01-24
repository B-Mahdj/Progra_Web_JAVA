package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import client.Abonne;
import document.DVD;
import document.Document;

class TestAbonne {

    @Test
    void testNumeroAbonne() {
        Abonne a1=new Abonne(LocalDate.of(2001, 7, 23));
        Abonne a2=new Abonne(LocalDate.of(2001, 7, 23));
        Abonne a3=new Abonne(LocalDate.of(2001, 7, 23));
        assertEquals(1,a1.getNumero());
        assertEquals(2,a2.getNumero());
        assertEquals(3,a3.getNumero());
        String s = "Abonne n°" + 1+ " ne le " + "2001-07-23";
    	assertEquals(s, a1.toString());
    }
    
    @Test
    void testInstanciation() {
    	Abonne a4=new Abonne(LocalDate.of(2001, 7, 23));
    	assertEquals(LocalDate.of(2001, 7, 23),a4.getDateNaissance());
        assertEquals(4,a4.getNumero());
    }

}
