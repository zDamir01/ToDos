/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import modeli.KorisnikModel;

/**
 *
 * @author PC
 */
public class Sesija {
    private static KorisnikModel ulogovaniKorisnik;

    public static void setUlogovaniKorisnik(KorisnikModel korisnik) {
        ulogovaniKorisnik = korisnik;
    }

    public static KorisnikModel getUlogovaniKorisnik() {
        return ulogovaniKorisnik;
    }
}
