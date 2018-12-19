# Testausdokumentti
Ohjelma on testattu automatisoitujen JUnit yksikkö- ja integraatiotestien avulla. Vastaavia testejä on lisäksi tehty manuaalisesti testikäyttöliittymän avulla.

Järjestelmätason testausta on tehty myös manuaalisesti.

## Yksikkö- ja integraatiotestaus
### Sovelluslogiikka
Automatisoitu testaaminen keskittyy pääasiassa LTXParser- ja ParserCollection-luokkiin niiden muodostaessa ohjelman ydintoiminnallisuuden.

ParserTest-luokan testit testaavat yksittäisten LTXParser-olioiden toiminnallisuutta, kun taas ParserCollectionTest-luokka testaa pääasiassa ParserCollection-olioiden hierarkianhallintaa.

Kuitenkin myös ParserCollection-olioiden parseDoc-metodia testataan lyhyesti, vaikka sen toiminnallisuus tuleekin LTXParser-olioilta.

Integraatiotestit tapahtuvat pääasiassa ParserCollectionTest- ja LTXCodeDocTest-luokissa, niiden kokoavan luonteen vuoksi.

### MathDao ja DocExporter
MathDao-luokkaa testattiin testitietokannalla, joka on rajoitettu versio ohjelman mukana tulevasta helper.db-oletustietokannasta. 

DocExporter-luokan testaamisessa hyödynnettiin JUnitin temporaryfolder-ruleja luotujen tiedostojen luomisen tarkastamiseen.

###Testauskattavuus
Käyttöliittymä poislukien sovelluksen testauksen rivikattavuus on 83% ja haarautumiskattavuus 75%. 

![Jacoco report](https://github.com/alekmus/LATEX-Helper/blob/master/dokumentointi/testikattavuus.png)

## Järjestelmätestaus
Sovelluksen järjestelmätason testaus on suoritettu manuaalisesti. Tavanomainen toiminta dokumenttien luomisesta tallentamiseen erimuotoihin sekä tallennettujen tiedostojen avaaminen on testattu.

Ohjelman asentaminen on testattu windows-ympäristössä sekä oletustietokannalla, että vaihtamalla se toiseen config.properties-konfiguraatiotiedoston avulla.

## Toiminnallisuudet
Määrittelydokumentin ja käyttöohjeen toiminnallisuudet on käyty läpi. Kenttiin on syötetty virheellistä tietoa ja tietokantaan on yritetty injektoida dao:n ulkopuolisia käskyjä.
