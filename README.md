# LaTeX-Helper
Sovelluksen tarkoituksena on auttaa käyttäjää luomaan yksinkertainen tekstipainotteinen LATEX-dokumentti, joka mukailee tekstikentän muotoilua. Tavoitteena on myös mahdollistaa yleisimmin käytettyjen matemaattisten kaavojen etsiminen.

Tavoitteena on helpottaa LATEX-dokumenttien laatimista sellaiselle käyttäjälle, joka tarvitsee ladontaohjelmaa vain yksinkertaisen dokumentin laatimiseksi ja jonka ei siten kannata opetella koko ladontajärjestelmän syntaksia.
## Release
[Viikon 5 release](https://github.com/alekmus/LATEX-Helper/releases/tag/Viikko5)

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/alekmus/LATEX-Helper/blob/master/dokumentointi/Vaatimusm%C3%A4%C3%A4rittely.md)

[Työaikakirjanpito](https://github.com/alekmus/LATEX-Helper/blob/master/dokumentointi/ty%C3%B6aikakirjanpito.md)

[Arkkitehtuuri](https://github.com/alekmus/LATEX-Helper/blob/master/dokumentointi/arkkitehtuuri.md)

[Käyttöohje](https://github.com/alekmus/LATEX-Helper/blob/master/dokumentointi/k%C3%A4ytt%C3%B6ohje.md)

[Testausdokumentti](https://github.com/alekmus/LATEX-Helper/blob/master/dokumentointi/testausdokumentti.md)

## Komentorivitoiminnot
Testit suoritetaan komennolla
```
mvn test
```

Testikattavuusraportti luodaan komennolla
```
mvn jacoco:report
```

JavaDoc luodaan komennolla
```
mvn javadoc:javadoc
```
