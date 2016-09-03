## Rakenne

Ennen UI:n käynnistymistä ohjelma aluksi luo uuden satunnaismatriisin sekä projisoi AT&T-tietokannan satunnaismatriisin virittämälle
aliavaruudelle. Tämä tapahtuu vain ensimmäisen ajon aikana, jolloin ohjelma tallentaa satunnaismatriisin sekä onnistuneesti projisoidut
kasvot .csv -tiedostoiksi käyttäjän koneelle. Tallentaminen nopeuttaa ohjelman suoritusta jatkossa huomattavasti, sillä matriisien
kertolasku on melko raskas toimenpide suorittaa jokaisella ohjelman ajokerralla. Varsinkin suurissa kasvomäärissä tallentamisominaisuus
nousee tärkeään rooliin.

Kun matriisit on luotu/ladattu taulukoihin, avautuu UI käyttäjälle. Käyttäjä syöttää polun kasvoihin, joita haluaa tunnistaa. Tämän jälkeen
ohjelma lukee kuvatiedoston taulukkoon ja projisoi taulukon (vektorin) samalle aliavaruudelle kuin aikaisemmin ladatut AT&T -kasvot. 
Tässä vaiheessa kaikki kasvoja edustavat vektorit kuuluvat samaan 500-ulotteiseen reaalilukujen joukkoon, joten niitä pystyy vertaamaan keskenään.

Lopuksi siirrymme etsimään tunnistettavia kasvoja lähinnä olevat kasvot ClosestMatch -luokassa. Metodi shortestEuclideanDistance selvittää
lyhyimmän etäisyyden. Kaikkia etäisyyksiä ei tarvitse laskea kokonaan: laskutoimenpide katkaistaan, kun vektorin etäisyys ylittää viimeksi havaitun
lyhyimmän etäisyyden. Onnistuneiden tunnistusten etäisyys on yleensä 50 000 - 60 000 luokkaa, joten kaikkia tunnistuksia, jotka ylittävät
etäisyyden 70 000 rajan, on syytä epäillä epäonnistuneiksi.

## Aika- ja tilavaativuudet

Ohjelman uusia kasvoja ja satunnaismatriisin luova osuus on aikavaativuudeltaan O(n^2). Tätä osaa koodista on vaikeaa toteuttaa
mitenkään muuten: juuri tästä syystä kasvojentunnistuksessa suuresti käytetty pääkomponenttianalyysi näyttää heikkoutensa varsinkin,
kun puhutaan suurista määristä kasvoja. Pääkomponenttianalyysi käyttää tunnistuksessa ominaisvektoreita, jotka joudutaan määrittämään 
uudelleen aina, kun kasvokirjastoon lisätään uusia kasvoja. Satunnaisprojektiot ovat tässä tapauksessa hyödyllisiä, koska
jo kertaalleen käsiteltyä dataa ei tarvitse käsitellä uudestaan. Toisin sanoen ohjelman ensimmäinen ajo on hidas, mutta nopeutuu
myöhemmässä käytössä.

## Puutteet ja parannusehdotukset

Ohjelman ClosestMatch -logiikkaa olisi syytä parantaa. Itse en keksinyt parempaa ratkaisua tähän ongelmaan, sillä ei voida etukäteen 
tietää, millaisia etäisyydet ovat. Vektoreita ei oikein voi lajitellakaan tämän mukaan, sillä etäisyydet ovat suhteessa tutkittavaan
vektoriin ja vaihtelevat paljon.

Uskoisin, että jollain keinolla pystyttäisiin luomaan dataklustereita vektoreista, jotka ovat lähellä toisiaan. Tällöin ohjelman suoritusaika
voitaisiin pudottaa tasolle O(log n). Tunnistustodennäköisyys kuitenkin tippuisi tässä tapauksessa erityisesti silloin, kun käytössä
on suuri määrä kasvoja. 

Tapa, jolla .csv -tiedostoja luetaan, voisi olla myös optimaalisempi. Ehkäpä tähän löytyisi ratkaisu jostakin toisesta tiedostomuodosta.

## Lähteet

[Satunnaisprojektiomenetelmä kasvojentunnistuksessa.](https://www.dropbox.com/s/hcmxbqr4y076cvl/RPFR2016.pdf?dl=0) (2016) Maria Yli-Luukko
