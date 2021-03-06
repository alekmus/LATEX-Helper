# Käyttöohje
Lataa tiedosto LATEX-Helper.jar.

## Ohjelman käynnistäminen ja konfigurointi 
Ohjelma käynnistetään komennolla:
```
java -jar LATEX-Helper.jar
```
Ohjelma odottaa, että käynnistyshakemistosta löytyy konfiguraatiotiedosto config.properties, joka määrittelee tietokannan, josta ohjelma hakee kaava-avustajan tarjoamat ehdotukset LaTeX-kaavoista. 
Tiedoston tulee olla muotoa:

    dbName = helper.db

## Käyttöliittymä
Kun ohjelma aukeaa, sen käyttöliittymä näyttää seuraavanlaiselta:

![Käyttöliittymä](https://github.com/alekmus/LATEX-Helper/blob/master/dokumentointi/latexhelperui.png)

Ohjelman käyttöliittymä koostuu viidestä osasta: tekstikentästä, LaTeX-kentästä, kaava-avustajasta, asiakirja-asetuksista ja tiedosto-asetuksista.

### Tekstikenttä
![Tekstikenttä](https://github.com/alekmus/LATEX-Helper/blob/master/dokumentointi/latexhelperuitextfield.png)

Käyttäjä syöttää tekstikenttään tekstin, jonka hän haluaa muuttaa LaTeX-asiakirjan leipätekstiksi.

Ohjelma yrittää ensisijaisesti jakaa kentän tekstin luvuiksi ja kappaleiksi.

Ohjelma tulkitsee tyhjän rivin merkitsevän luvun vaihtumista ja lyhyen tekstipätkän ennen sitä olevan luvun otsikko. Mikäli ohjelma tulkitsee, että kappale alkaa suoraan ilman otsikkoa tai annettu otsikko on liian pitkä, luvulle annetaan otsikon sijaan pelkkä numero tai otsikko jätetään tyhjäksi.

Näitä otsikoita käytetään sisällysluettelon muodostamisessa, mikäli sellaista halutaan käyttää.

Sisennetty rivi taas tulkitaan LaTeX-ohjelman kappalevaihdoksi, mutta kappaleita voi vaihtaa myös rivinvaihdolla, jolloin myös asiakirjan rivi katkeaa samasta kohdasta. Tyhjää riviä kappaleiden välissä ei käyttöohjeen kirjoittamisen aikaan tueta, mutta se on seuraava lisättävä ominaisuus.

Käyttäjän antama syöte tulisikin olla kutakuinkin muodossa:

    Otsikko

    leipätekstiä leipätekstiä
       kappalevaihtotekstiäleipätekstiä

    Toinen otsikko

    lisääleipätekstiä ja vielälisääleipätekstiä

### LaTeX-kenttä
![LaTeX-kenttä](https://github.com/alekmus/LATEX-Helper/blob/master/dokumentointi/latexhelperuitargetfield.png)

LaTeX-kentässä näkyy koodi, jonka perusteella LaTeX-asiakirja luodaan.

Käyttäjä ei voi suoraan tehdä muutoksiä tähän kenttään, vaan kenttään vaikuttaminen tapahtuu tekstikentän ja asiakirja-asetuksien kautta.

Käyttäjä voi kuitenkin halutessaan kopioida tekstin tästä kentästä ja liittää sen ulkopuoliseen LaTeX-kääntäjään, esimerkiksi, jos hänen omalle koneelleen ei ole asennettuna tarvittavaa ohjelmistoa.

### Kaava-avustaja
![Kaava-avustaja](https://github.com/alekmus/LATEX-Helper/blob/master/dokumentointi/latexhelperuiformula.png)

Kaava-avustajan avulla käyttäjä voi hakea tavallisimpien matemaattisten kaavojen LaTeX-merkintöjä ohjelman sisällä ja liittää ne oman tekstinsä sisään.

Select Formula -painike avaa pudotusvalikon, jonka takana on on kaavojen nimiä. Nimen valitseminen lisää viereiseen kenttään kaavan ja preview-painike näyttää kenttien alla, miltä kaava tulee näyttämään lopullisessa asiakirjassa.

Käyttäjä voi myös kirjoittaa tekstikenttään omia kaavojaan ja preview -painike näyttää myös näiden esikatselukuvan.

### Asiakirja-asetukset
![Asiakirja-asetukset](https://github.com/alekmus/LATEX-Helper/blob/master/dokumentointi/latexhelperuisettingspng.png)

Asiakirja-asetukset pitävät sisällään asetukset, joita käyttäjä haluaa käyttää LaTeX-asiakirjassaan.

Asetukset näyttävät seuraavalta:

![Asetukset](https://github.com/alekmus/LATEX-Helper/blob/master/dokumentointi/settings.png)

Asetuksien avulla käyttäjä voi muun muassa määrittää, mitä paketteja hän haluaa tiedostossaan käyttää, ja haluaako hän hyödyntää ohjelman valmista otsikkosivumallia.

Näistä asetuksista käyttäjä valitsee myös haluamansa otsikon asiakirjalle sekä sen kirjoittajan.

### Tiedosto-asetukset
![Tiedostoasetukset](https://github.com/alekmus/LATEX-Helper/blob/master/dokumentointi/latexhelperuimenu.png)

Tiedostoasetuksista käyttäjä voi tallentaa ohjelman tilan sekä jatkaa siitä, mihin työnteko aiemmin jäi.

Valikko näyttää seuraavalta:

![Menu](https://github.com/alekmus/LATEX-Helper/blob/master/dokumentointi/filemenu.png)

New-painike avaa kokonaan uuden asiakirjan. Painike pyyhkii tekstikentän sekä asettaa asetukset oletuksiin.

Open-painike avaa ohjelman aiemmin tallennetun tilan ja mahdollistaa asiakirjan jatkamisen.

Save-painike tallentaa tekstikentän sisällön sekä käytetyt asetukset.

Export-painike mahdollistaa asiakirjan tallentamisen sekä tex-että pdf-tiedostomuotoon. Pdf-muotoon tallentaminen kuitenkin edellyttää LaTeX-ohjelmiston asentamista käyttäjän tietokoneelle sekä sen lisäämistä polkuun.

Exit-painike sulkee sovelluksen.
