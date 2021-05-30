# Väznica app
### Desktopová aplikácia s jednoduchým GUI (grafickým rozhraním)

Aplikácia Väznica bola vytvorená v rámci semestrálnej práce v prvom ročníku na vysokej škole.  
<sub>&nbsp;&nbsp;&nbsp;&nbsp;Pozn.: Spustiteľný súbor aplikácie - out/artifacts/Vaznica_jar/run-app.bat</sub>  
<sub>&nbsp;&nbsp;&nbsp;&nbsp;Pozn.: Rozhrania IZamestnatelny a IUveznitelny nie su v aplikácii potrebné.</sub>  
  
  
## Úvod  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Väznica pozostáva z troch kompozícii – cela, strážna veža a muničný sklad, ktoré vytvorí používateľ ihneď po spustení aplikácie a následne môže vykonávať rôzne úkony 
týkajúce väzňov, zamestnancov a munície tejto väznice. Aplikácia v priebehu celého chodu programu podáva informácie v podobe textového reťazca o aktuálnom stave
jednotlivých kompozícii, aby mal používateľ neustály prehľad o väzňoch, zamestnancoch a munícií. Pre uľahčenie práce s aplikáciou, si odporúčam prečítať nižšie uvedené
informácie.  
  
---   

### Cela  
-	Väznica má iba jednu celu, v ktorej sa nachádzajú všetci väzni 
-	Táto cela má obmedzenia, ktoré sami definujeme pri vytváraní väznice 
-	Obmedzenia:  
    -	 cela má obmedzenú kapacitu väzňov  
    -	 v cele sa môžu nachádzať iba väzni s lepším zdravotným stavom ako sme inicializovali  
  
### Strážna veža  
-	Väznica má jednu strážnu vežu, v ktorej sa nachádzajú všetci zamestnanci 
-	Táto strážna veža má obmedzenia, ktoré sami definujeme pri vytváraní väznice 
-	Lekári, ako zamestnanci väznice majú možnosť sa ubytovať priamo v strážnej veži v prípade, že bývajú ďalej ako 100 kilometrov od väznice
-	V prípade potreby je možné vypísať všetkých ubytovaných lekárov do súboru 
-	Obmedzenia:  
-  V strážnej veži platí, že každému lekárovi prislúcha práve jedna ambulancia a teda, v strážnej veži môže byť len toľko lekárov, koľko je ambulancii  
    -	 Strážna veža má obmedzené množstvo lôžok pre ubytovaných lekárov  
    -	 Strážna veža ma obmedzené množstvo voľných miest pre zamestnancov na polovičný úväzok  
      
### Muničný sklad  
-	Väznica má muničný sklad, v ktorom sa nachádza munícia pre strážnikov 
-	Tento muničný sklad má obmedzenia, ktoré sami definujeme pri vytváraní väznice 
-	Obmedzenia:  
    -	 Muničný sklad má obmedzené finančné prostriedky na nákup munície  
    -	 Muničný sklad má obmedzené množstvo voľných miest pre uskladnenie granátov  
  
---  
  
### Väzeň  
-	V cele sa môžu nachádzať buď vrahovia alebo bankový lupiči
-	Každému väzňovi je potrebné určiť jedinečné poznávacie číslo, stupeň nebezpečenstva, zdravotný stav a počet rokov odsúdenia
-	Vrahovi sa okrem toho taktiež určí počet povolených návštev za mesiac
-	Bankovému lupičovi sa okrem toho určí ukradnutá čiastka peňazí z banky a počet povolených vychádzok na dvor
-	Každému väzňovi aplikácia vypočíta zdravotný stav po odpykaní trestu 
    -	 Každých 5 rokov vo väzení sa väzňovi zhorší zdravotný stav o 25 percent  
-	Väzňovi sa môže v prípade dobrého správania znížiť stupeň nebezpečenstva  
-	V prípade potreby je možné vypísať do súboru všetkých väzňov s vysokým stupňom nebezpečenstva  

#### &nbsp;&nbsp;&nbsp;&nbsp;Bankový lupič  
-	Bankový lupič ma možnosť vrátiť aspoň časť ukradnutých peňazí banke, ktorú vylúpil a to prácou vo väzení  
-	Každému bankovému lupičovi aplikácia vypočíta  
    -	 koľko už vrátil banke  
    -	 koľko je ešte banke dlžný  
    -	 Koľko hodín a minút musí ešte odpracovať, aby splatil dlh banke 
    
#### &nbsp;&nbsp;&nbsp;&nbsp;Vrah  
-	V prípade dobrého správania môžeme vrahovi zvýšiť počet povolených návštev 
-	Vo väzení môže nastať situácia, že vrah napadne strážnika a dôjde k bitke, no v tom prípade čaká daného vraha trest 
    -	 zvýši sa mu stupeň nebezpečenstva 
    -	 zvýši sa mu počet rokov odsúdenia o 4 roky 
    -	 zrušia sa mu všetky povolené návštevy v mesiaci
    -	 zníži sa mu zdravotný stav o 10 percent 

---  
  
### Zamestnanec  
-	V strážnej veži môže byť zamestnaný strážnik alebo lekár  
-	Každému zamestnancovi je potrebné určiť identifikačné číslo, typ zamestnania a počet rokov praxe  
-	Strážnikovi sa okrem toho taktiež určí hodnosť  
-	Lekárovi sa okrem toho taktiež určí vzdialenosť bydliska od väznice  
-	V prípade, že lekár býva viac ako 100 km od väznice je ubytovaný v strážnej veži, aby nemusel dochádzať   
    -	 V prípade, že lekár býva menej ako 100 km od väznice, sú mu preplácané náklady na dopravu v hodnote 7 eur / 100 km  
    -	 Na základe zadaných parametrov aplikácia vypočíta každému zamestnancovi hrubú hodinovú mzdu podľa nižšie uvedených pravidiel väznice  
    
#### &nbsp;&nbsp;&nbsp;&nbsp;Strážnik  
-	Aby strážnik zarobil peniaze musí najskôr odpracovať určité množstvo hodín  
-	Každému strážnikovi aplikácie vypočíta množstvo zarobených peňazí na základe jeho hrubej hodinovej mzdy a počtu odpracovaných hodín  	

#### &nbsp;&nbsp;&nbsp;&nbsp;Lekár  
-	Lekárom sa zvyšuje hrubá hodinová mzda za každých 50 ošetrených väzňov podľa nižšie uvedených pravidiel väznice   
  
---  

### Výzbroj/Munícia  
-	Do muničného skladu môžeme kúpiť granát alebo samopal  
-	Na základe ceny danej munície aplikácia určí, či je daný tovar drahší ako štandardná cena ak áno, uvedie o koľko  
    -	Štandardná cena samopalu je 300 eur  
    -	Štandardná cena granátu je 80 eur  
  
---  

## Pravidlá väznice  
-	Maximálny počet povolených návštev za mesiac pre vraha je 5
-	Maximálny zdravotný stav väzňa je 100 percent 
-	Minimálny počet rokov odsúdenia väzňa je 5 rokov 
-	Maximálny počet povolených vychádzok na dvor pre bankového lupiča je 10
-	Hodinová mzda za prácu bankového lupiča vo väznici je 1,5 eur/hodina  

### Platy zamestnancov 
-	Zamestnanec na polovičný uväzok – 3,2 eur/hodina
-	Zamestnanec na plný uväzok – 4,1 eur/hodina  
  
### Priplatky k hodinovej mzde  
-	Príplatok pre každého zamestnanca za 10 ročnú prax – 0,5 eur/hodina
-	Príplatok pre strážnika k hodnosti kapitán – 1,1 eur/hodina
-	Príplatok pre lekára za každých 50 ošetrených väzňov – 0,9 eur/hodina




  
  
