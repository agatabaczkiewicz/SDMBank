package com.put.sdm.bank.raport;

import java.util.List;

public interface Raport {

    String getRaportHeader();

    List<String> getRaportRows();

    String getRaportFooter();


    default String generateRaport(){
        String rows = String.join("", getRaportRows());

        return getRaportHeader().concat(rows).concat(getRaportFooter());
    }
}
