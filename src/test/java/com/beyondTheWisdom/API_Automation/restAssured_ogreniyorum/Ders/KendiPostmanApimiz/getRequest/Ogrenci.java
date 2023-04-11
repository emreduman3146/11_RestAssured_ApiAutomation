package com.beyondTheWisdom.API_Automation.restAssured_ogreniyorum.Ders.KendiPostmanApimiz.getRequest;

import java.util.Arrays;

public class Ogrenci
{
    String isim;
    int yas;
    String address;

    public Ogrenci(String isim, int yas, String address) {
        this.isim = isim;
        this.yas = yas;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Ogrenci{" +
                "isim='" + isim + '\'' +
                ", yas=" + yas +
                ", address='" + address + '\'' +
                '}';
    }

    public static void main(String[] args) {

        Ogrenci ayse=new Ogrenci("Ayse",20,"Ankara");
        Ogrenci ahmet=new Ogrenci("Ahmet",20,"Ankara");
        Ogrenci meryem=new Ogrenci("Meryem",20,"Ankara");

        //Int Array - String array
        String[] stringArray_ogrenciIsimleri=new String[]{"Ayse","Ahmet","Meryem"};



        //ARRAYIM OLSUN AMA IINDE oGRENCI cLASSINA AIT oBJELER OLSUN
        Ogrenci[] ogrenciArrayi=new Ogrenci[]{ayse,ahmet,meryem};
        System.out.println(Arrays.toString(ogrenciArrayi));

        //DEV--->SPRING HIBERNATE ILE ----> MYSQL DATABASE


        //API TESTER->> Resassured-Java(Get Request)---> JSON DATA



    }
}
