## Rakenne

Ennen UI:n k�ynnistymist� ohjelma aluksi luo uuden satunnaismatriisin sek� projisoi AT&T-tietokannan satunnaismatriisin viritt�m�lle
aliavaruudelle. T�m� tapahtuu vain ensimm�isen ajon aikana, jolloin ohjelma tallentaa satunnaismatriisin sek� onnistuneesti projisoidut
kasvot .csv -tiedostoiksi k�ytt�j�n koneelle. Tallentaminen nopeuttaa ohjelman suoritusta jatkossa huomattavasti, sill� matriisien
kertolasku on melko raskas toimenpide suorittaa jokaisella ohjelman ajokerralla. Varsinkin suurissa kasvom��riss� tallentamisominaisuus
nousee t�rke��n rooliin.

Kun matriisit on luotu/ladattu taulukoihin, avautuu UI k�ytt�j�lle. K�ytt�j� sy�tt�� polun kasvoihin, joita haluaa tunnistaa. T�m�n j�lkeen
ohjelma lukee kuvatiedoston taulukkoon ja projisoi taulukon (vektorin) samalle aliavaruudelle kuin aikaisemmin ladatut AT&T -kasvot. 
T�ss� vaiheessa kaikki kasvoja edustavat vektorit kuuluvat samaan 500-ulotteiseen reaalilukujen joukkoon, joten niit� pystyy vertaamaan kesken��n.

Lopuksi siirrymme etsim��n tunnistettavia kasvoja l�hinn� olevat kasvot ClosestMatch -luokassa. Metodi shortestEuclideanDistance selvitt��
lyhyimm�n et�isyyden. Kaikkia et�isyyksi� ei tarvitse laskea kokonaan: laskutoimenpide katkaistaan, kun vektorin et�isyys ylitt�� viimeksi havaitun
lyhyimm�n et�isyyden. Onnistuneiden tunnistusten et�isyys on yleens� 50 000 - 60 000 luokkaa, joten kaikkia tunnistuksia, jotka ylitt�v�t
et�isyyden 70 000 rajan, on syyt� ep�ill� ep�onnistuneiksi.

## Aika- ja tilavaativuudet

Ohjelman uusia kasvoja ja satunnaismatriisin luova osuus on aikavaativuudeltaan O(n^2). T�t� osaa koodista on vaikeaa toteuttaa
mitenk��n muuten: juuri t�st� syyst� kasvojentunnistuksessa suuresti k�ytetty p��komponenttianalyysi n�ytt�� heikkoutensa varsinkin,
kun puhutaan suurista m��rist� kasvoja. P��komponenttianalyysi k�ytt�� tunnistuksessa ominaisvektoreita, jotka joudutaan m��ritt�m��n 
uudelleen aina, kun kasvokirjastoon lis�t��n uusia kasvoja. Satunnaisprojektiot ovat t�ss� tapauksessa hy�dyllisi�, koska
jo kertaalleen k�sitelty� dataa ei tarvitse k�sitell� uudestaan. Toisin sanoen ohjelman ensimm�inen ajo on hidas, mutta nopeutuu
my�hemm�ss� k�yt�ss�.

## Puutteet ja parannusehdotukset

Ohjelman ClosestMatch -logiikkaa olisi syyt� parantaa. Itse en keksinyt parempaa ratkaisua t�h�n ongelmaan, sill� ei voida etuk�teen 
tiet��, millaisia et�isyydet ovat. Vektoreita ei oikein voi lajitellakaan t�m�n mukaan, sill� et�isyydet ovat suhteessa tutkittavaan
vektoriin ja vaihtelevat paljon.

Uskoisin, ett� jollain keinolla pystytt�isiin luomaan dataklustereita vektoreista, jotka ovat l�hell� toisiaan. T�ll�in ohjelman suoritusaika
voitaisiin pudottaa tasolle O(log n). Tunnistustodenn�k�isyys kuitenkin tippuisi t�ss� tapauksessa erityisesti silloin, kun k�yt�ss�
on suuri m��r� kasvoja. 

Tapa, jolla .csv -tiedostoja luetaan, voisi olla my�s optimaalisempi. Ehk�p� t�h�n l�ytyisi ratkaisu jostakin toisesta tiedostomuodosta.

## L�hteet

[Satunnaisprojektiomenetelm� kasvojentunnistuksessa.](https://www.dropbox.com/s/hcmxbqr4y076cvl/RPFR2016.pdf?dl=0) (2016) Maria Yli-Luukko
