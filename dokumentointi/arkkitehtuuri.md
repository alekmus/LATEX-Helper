# Arkkitehtuurikuvaus

## Rakenne
Ohjelma rakentuu kahdesta tasosta. Käyttöliittymästä ja ohjelmalogiikasta. Pakkausrakenne ja luokkien suhteet ovat seuraavanlaiset:

![pakkauskaavio](https://github.com/alekmus/LaTex-Helper/blob/master/dokumentointi/packagediagram.png)

 Pakkaus helper.ui sisältää JavaFX:llä toteutetun käyttöliittymän (sekä kurssiteknisistä syistä testikäyttöliittymän ennen viimeistä palautusta), helper.domain sisältää merkkijonojen LaTex-formaattiin muuttamisesta vastaavan ohjelmistologiikan ja helper.dao pitää sisällään tietokantayhteyksistä vastaavat luokat.

## Käyttöliittymä
Käyttöliittymä pitää sisällään yhden näkymän.

Käyttöliittymä on eriytetty sovelluslogiikasta mahdollisimman pitkällä, ja se kutsuu helper.domain sekä helper.dao pakkausten olioiden metodeja.

LTXCodeDoc-olio vastaa merkkijonon käsittelystä, MathDao-olio yhteydestä tallennettuihin LaTex-kaavoihin ja DocExporter-olio ohjelman tilan tallentamisesta sekä merkkijonon viemisestä pdf- ja tex-tiedostomuotoihin.

Käyttöliittymä kutsuu lisäksi ulkopuolisen JLaTexmath-pakkauksen TeXFormula olion metodeja näyttääkseen esikatselukuvat halutuista LaTex-kaavoista sovelluksen sisällä.

Kun käyttäjä muuttaa käyttöliittymäikkunan vasemman reunan asetuksia tai kirjoittaa ikkunan keskellä olevaan tekstikenttään, tiedot lisätään LTXCodeDociin ja vasemman reunan tekstikenttä päivittyy niiden mukaiseksi.

Keskellä alhaalla olevan kaavavalitsemen arvon muuttaminen kutsuu MathDao-luokan metodia "find", joka etsii valitun arvon helper.db-tietokannasta. Painike preview taas luo JLaTexmath-pakkauksen TeXFormula-olion ja käyttää sen konstruktoria sekä createBufferedImage-metodia luomaan kuvan ImageView-olentoon keskiosan alareunassa.

Ikkunan yläosassa oleva menubar-olio kutsuu DocExporter-olion metodeja yllä mainituilla tavoilla.

## Sovelluslogiikka

Ydinsovelluslogiikan muodostaa LTXCodeDoc-luokka ja erityisesti sen Parsercollection-oliomuuttuja.

LTXCodeDoc kokoaa oliomuuttujat Header-, ParserCollection- ja LTXTitlePage-luokista yhden luokan alle ja hallinnoi niiden suhteita. Luokka pitää sisällään myös merkkijonomuotoisen tiedon alkuperäisestä tekstistä ja sen perusteella laaditusta LaTex-leipätekstistä.

Header ja LTXTitlePage -luokat säilyttävät ja sääntelevät merkkijonomuotoista tietoa LaTex-asiakirjan ennalta määrätyistä osista; Header asiakirjan määrittelyihin liittyvistä asioista ja LTXTitlePage asiakirjan otsikkosivusta.

ParserCollection taas hallinnoi LTXParser-olioiden hierarkiaa ja kutsuu niiden parse-metodeja muokatakseen alkuperäisen tekstin LaTex-muotoon.

ParserCollection-olion parseDoc-metodi toimii seuraavalla tavalla:

![sekvenssikaavio](https://github.com/alekmus/LaTex-Helper/blob/master/dokumentointi/Parsercollection%20parseDoc(doc).png)

### Micro- ja MacroParser
MicroParser- ja MacroParser-rajapinnat perivät LTXParser-rajapinnan. LTXParser-rajapintaa suoraan käyttäviä olioita ei luoda.

MacroParser-rajapinnan toteuttavat oliot jakavat merkkijonon kunkin olion parse-metodin toteutuksen perusteella listaksi merkkijonoja, joka kuvastaa tulkittua toivottua asiakirjan rakennetta. Palautetun listan merkkijonot voidaan antaa MicroParser-olioille käsiteltäväksi.

MicroParser-rajapinnan toteuttavat oliot muokkaavat merkkijonoja, tyypillisesti vaihtamalla osajonoja toisiin, ja palauttavat merkkijonon.

## Tietojen tallennus
Tietojen tallennuksesta vastaa DocExporter-luokka.

Vaikka Dao-rajapinnan toteuttavilla olioilla on pääsy ohjelman helper.db-tietokantatiedostoon, niitä käytetään vain tallennetun tiedon hakemiseen.

DocExporter-olion save- ja open-metodit hyödyntävät LTXCodeDoc-luokan käyttämää Serializable-rajapintaa tallentaen LTXCodeDoc-oliot tiedostoon.

ExportToTeX-metodi taas hyödyntää FileWriter-luokkaa, sillä tex-tiedostot voidaan muuttaa txt tiedostoista yksinkertaisesti tiedostopäätettä muokkaamalla.

Huomionarvoisinta tallennuksen yhteydessä on kuitenkin exportToPDF-metodi, joka edellyttää käyttäjän asentamaa LaTex-ohjelmistoa. Metodi kutsuu javan runtime-ympäristössä "pdfLaTex" komentoa, joka luo tex-tiedoston perusteella pdf-tiedoston ja tarvittavat lisätiedostot. Mikäli käyttäjä ei ole asentanut tarvittavaa ohjelmistoa tai lisännyt sitä polkuun, metodi ei tee mitään.

## Päätoiminnallisuus
### Tekstin muuntaminen LaTex-asiakirjaksi
Kun käyttäjä kirjoittaa ikkunan keskellä olevaan tekstikenttään tekstiä, tapahtumankuuntelija rekisteröi muutoksen kentän arvossa ja tallentaa uuden arvon LTXCodeDoc-olion text- ja doc-oliomuutujien arvoksi.

Käyttöliittymä kutsuu tämän jälkeen LTXCodeDoc-olion parse-metodia, joka yrittää mukailla käyttäjän syöttämän tekstin muotoilua ja luoda sen perusteella LaTex-asiakirjan leipätekstiä käyttöliittymäikkunan oikean reunan tekstikenttään. Muita dokumentin muodostamiseen tarvittavia osia hallinnoidaan vasemman reunan valikosta.

### Kaavojen hakeminen
Kun käyttäjä valitsee pudotusvalikosta haluamansa kaavan nimen, valikkoon liitetty tapahtumankuuntelija aktivoituu. Kuuntelija kutsuu viereisen tekstialueen setText-metodia, joka taas kutsuu MathDao-olion metodia find. Alla oleva esikatselukuva ImageView-oliossa tyhjennetään.

## Ohjelman heikkoudet
## Käyttöliittymä
Graafisen käyttöliittymän rakentaminen on toteutettu kokonaan HelperUI-luokan start-metodissa, ja vaikka käyttöliittymäelementit pääasiassa yksinkertaisesti syöttävät tietoa LTXCodeDoc-oliolle, on metodi pitkä ja raskaslukuinen.

Kolmisarakkeisen käyttöliittymän rakentaminen kannattaisikin jakaa esimerkiksi kolmeen erilliseen metodiin, mikäli ohjelmaa halutaan jatkokehittää.

## ParserCollection
Tekstin muotoilun seuraaminen on tällä hetkellä suhteellisen rajoittunutta, eikä ParserCollection-oliot tunnista kovin monimutkaisia asetteluja.

Toisaalta ohjelma on tarkoitettu lyhyiden ja yksinkertaisten LaTeX-asiakirjojen luomiseen ihmisille, jotka eivät osaa LaTeXin perusteita. Alusta asti ohjelman suunnittelussa on oletettu, että monimutkaisia muotoiluja tarvitsevat käyttäjät eivät ole sovelluksen kohdeyleisöä. Ohjelman luomiselle annetun aikarajan puitteissa, ei voida olettaa, että se kykenisi tulkitsemaan kaikkea mitä koko LaTeX-ladontakielellä pystytään tekemään.

Samalla kuitenkin tulkintojen parantaminen laskee kohdeyleisönä olevien käyttäjien kynnystä, sillä tällä hetkellä heidän täytyy opetella joitakin yksinkertaisia rakenteita, jotta käyttö on sujuvaa.

Ongelmaa voidaan tulevaisuudessa ratkaista lisäämällä LTXParser-olioita tai muutamalla nykyisten vastuuta.

## DocExporter
On oma ongelmansa, että DocExporter-luokka käyttää runtime-ympäristöä ulkopuolisen ohjelman kutsumiseen pdf-tiedostojen luonnin yhteydessä ja tarvitsee siten toimiakseen LaTeXin asennettuna käyttäjän tietokoneelle.

Käyttövarmuuden ja tietoturvallisuuden vuoksi olisi parempi, että tiedoston tallentaminen tapahtuisi ohjelman sisällä. Tämä kuitenkin vaatisi LaTeX-kääntäjän ohjelmointia javalla, mitä ei käsittääkseni ole ennen tehty ja joka olisi itsessään huomattavasti laajempi projekti, kuin koko nyt luotu LATEX-helper-ohjelma.

Jatkokehitysmielessä voisi selvittää, voisiko pdflatex.exe-ohjelman sisällyttää jar-tiedostoon, jolloin sen kutsuminen ei tapahtuisi java-ohjelman ulkopuolella.
