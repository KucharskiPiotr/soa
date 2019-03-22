package com.soa.models;


import com.soa.BeerColorNotSupported;

public class EkspertPiwny {
    public enum Kolory {
        ZLOTY("zloty"),
        MIEDZIANY("miedziany"),
        CIEMNOBRAZOWY("ciemnobrazowy"),
        CZARNY_OPALIZUJACY("czarny_opalizujacy");

        String kolor;

        Kolory(String kolor) {
            this.kolor = kolor;
        }

        public String getKolor() {
            return kolor;
        }
    }

    public enum Piwa {
        AMBER_ZLOTE_LWY("Amber Zlote Lwy"),
        MANUFAKTURA_PIWNA_MIEDZIANE("Manufaktura Piwna miedzialne"),
        PLAN_T_BROWN_ALE("Plan-T Brown Ale"),
        RUSSIAN_IMPERIAL_SOUT("Russian Imperial Stout");

        String nazwa;

        Piwa(String nazwa) {
            this.nazwa = nazwa;
        }
    }

    public static String suggestBeer(Kolory kolor){
        switch (kolor) {
            case ZLOTY:
                return Piwa.AMBER_ZLOTE_LWY.nazwa;
            case MIEDZIANY:
                return Piwa.MANUFAKTURA_PIWNA_MIEDZIANE.nazwa;
            case CIEMNOBRAZOWY:
                return Piwa.PLAN_T_BROWN_ALE.nazwa;
            case CZARNY_OPALIZUJACY:
                return Piwa.RUSSIAN_IMPERIAL_SOUT.nazwa;
        }
        return null;
    }
}
