# Vaatimusmäärittely

## Sovelluksen tarkoitus
Sovelluksen tarkoituksena on auttaa käyttäjää luomaan yksinkertainen tekstipainotteinen LATEX-dokumentti, joka mukailee tekstikentän muotoilua. 

Ohjelma tarjoaa myös mahdollisuuden etsiä tyypillisesti käytettäviä matemaattisten kaavoja. 

Tavoitteena on helpottaa LATEX-dokumenttien laatimista sellaiselle käyttäjälle, joka tarvitsee ladontaohjelmaa vain yksinkertaisen dokumentin laatimiseksi ja jonka ei siten kannata opetella koko ladontajärjestelmän syntaksia. 

## Käyttöliittymäluonnos
![ui-luonnos](https://raw.githubusercontent.com/alekmus/LATEX-Helper/master/dokumentointi/Latexhelperui_1.jpg)

## Toiminnallisuudet
### Ydintoiminnallisuus
* Käyttäjä voi kirjoittaa tekstikenttään tekstiä, jonka perusteella ohjelma luo toiseen tekstikenttään LaTeX-dokumentin, joka yrittää mukailla käyttäjän syötteen muotoilua
  * Käyttäjä voi kopioida LaTeX-dokumentin kolmannen osapuolen ohjelmaan, joka tulkitsee koodin ja tulostaa pdf-dokumentin
  * Käyttäjä voi myös tallentaa LaTeX-dokumentin ohjelman sisällä tex-tiedostomuotoon tai viemään sen pdf-muotoon hyödyntäen LaTeX-ohjelmistojakelun kääntäjää
  * Käyttäjä voi tallentaa edistymisensä tiedostoon ja avata tallennettuja tiedostoja
* Ohjelma pystyy erottamaan kappalejaot sekä otsikot
* Ohjelma tulkitsee toivotut palstajaot oikein
* Ohjelma tuo dokumentin vaatimat oletuspaketit/-syntaksin
* Ohjelma muuttaa lainausmerkit oikeanmalliseksi
* Ohjelma muuttaa ääkköset ja muut erikoismerkit yhteensopivaan LaTeX-muotoon

### Lisätoiminnallisuus
* Käyttäjä voi etsiä yleisimpien matemaattisten kaavojen LATEX-muotoiluja ja nähdä ne esikatselussa
* Käyttäjä voi valita haluamansa fonttikoot, asiakirjatyypit ja paketit valikosta

### Jatkokehitys
* Käyttäjä voi tallentaa profiileja, joihin on liitetty tietyt paketit tai muita asetuksia
* Käyttäjä voi käyttää ohjelmaa latomaan poikkeuksellisia muotoiluja tavanomaisten tekstidokumenttien lisäks
