# Powering Devices
**jeden reaktor dokáže napájať jedno svetlo... zatiaľ...**


## Motivácia

Vitaj pri brífingu ďalšej misie, kadet! Úvodné zahrievacie kolo máš úspešne za sebou a spolu s našim taktickým a strategickým tímom predpokladáme, že si sa určite dostatočne zahrial pri ovládaní reaktora.

Pri plnení rôznych misií v teréne sa ti však určite hodí niekoľko zručností, ktoré získaš počas dnešného tréningu. Jedná sa hlavne o zručnosť používania referenčných typov ako parametrov metód. Táto nenápadná zručnosť už zachránila život nejednému objektovému programátorovi, preto ju nepodceňuj!

Veľa šťastia pri dosahovaní cieľov dnešnej misie! Z veliteľského mostíka zdraví _Manager_.


## Ciele

- Osvojiť si použitie kľúčového slova `this` vo vlastnom kóde.
- Osvojiť si tvorbu parametrických konštruktorov.
- Porozumieť využitiu referencie na objekt ako parametra metódy.


## Krok: First tools

Ako ste si všimli, pracovať s atómovým reaktorom nie je žiadna sranda. Hlavne keď sa začne prehrievať a neexistuje spôsob, ako ho opraviť... Hádam sa nájdu nejaké užitočné nástroje.

![Diagram tried, ktorý vyjadruje vzťah tried `Hammer` a `AbstractActor`.](images/class.diagram-hammer.and.abstractactor.svg)


### Úloha
V balíku `sk.tuke.kpi.oop.game` vytvorte triedu `Hammer`, ktorá bude reprezentovať kladivo.

Animácia reprezentujúca kladivo na mape je uložená v súbore [sprite-link:hammer]. Obdobne, ako obrázok s animáciou reaktora, je aj obrázok kladiva dostupný vo vašom projekte. Kladivo bude mať členskú premennú pre počet zostávajúcich použití, ktorú inicializujte prostredníctvom konštruktora na hodnotu _1_. Premennej nastavte vhodnú viditeľnosť a prístup k nej zabezpečte patričnou `get` metódou.

![Animácia `hammer.png` (rozmery sprite-u: _16x16_)](images/hammer.png)

> Poznámka:
> V prípade, že pri vytváraní animácie používate obrázok sprite-u, ktorý pozostáva len z jedného snímku animácie (tak, ako vyššie zobrazený sprite pre kladivo), môžete použiť konštruktor triedy `Animation` len s jedným parametrom - názvom obrázka:
> ```java
> new Animation("sprites/hammer.png");
> ```
> Rozmery animácie budú v takom prípade automaticky nastavené podľa rozmerov použitého obrázka.


### Úloha
Pridajte do triedy `Hammer` metódu `use()`, ktorá bude reprezentovať použitie kladiva.

Pri každom použití je potrebné aktualizovať počet zostávajúcich použití kladiva. Keď tento počet dosiahne _0_, kladivo odstráňte z hernej _scény_ (kladivo sa zlomilo).

> Poznámka:
> Každý _aktér_ zobrazený v hre sa nachádza v aktívnej hernej scéne, na ktorú má aj dostupnú referenciu. Scéna zas má možnosť _aktérov_ pridávať aj odoberať. Využite možnosti vývojového prostredia alebo si prezrite [gamelib-doc:Actor][dokumentáciu rozhrania `Actor`] a nájdite spôsob, ako získať referenciu na hernú scénu a ako pomocou nej odstrániť použité kladivo z hry.

> Vyučujúci:
> Diskutujte so študentmi o spôsobe riešenia tejto úlohy. Môžete im ukázať, ako si v prostredí nájdu metódy dostupné nad objektom. Dostaneme sa tu už aj k použitiu `this` ako parametra metódy.


## Krok: If all you have is a hammer, everything looks like a nail

V tomto kroku sa pokúsime poškodený reaktor včas opraviť vhodným nástrojom: kladivom.

![Diagram tried, ktorý vyjadruje vzťah tried `Reactor` a `Hammer`.](images/class.diagram-hammer.and.reactor.svg)

### Úloha:
Vytvorte v triede `Reactor` metódu `repairWith()`, pomocou ktorej bude možné reaktor opraviť.

Metóda nebude vracať žiadnu hodnotu, ale bude mať jeden parameter typu `Hammer`. Jej správanie bude nasledovné:

- Metóda bude pracovať iba vtedy, ak hodnota parametra metódy obsahuje platnú referenciu na inštanciu triedy `Hammer` (a teda nie je `null`) a reaktor má hodnotu poškodenia vyššiu ako _0%_ ale ešte nie je úplne zničený.
- Použitie kladiva zníži poškodenie reaktora o _50_ percentuálnych bodov. Pozor ale na to, aby výsledná hodnota poškodenia neklesla pod _0_. Teda, ak bude reaktor poškodený, povedzme, na _92%_, po použití kladiva jeho poškodenie klesne na _42%_. A ak bude poškodený na _35%_, použitím kladiva sa úplne opraví (poškodenie _0%_).

> Poznámka:
> Keďže zvyšovaním teploty rastie poškodenie lineárne, po znížení poškodenia je potrebné odpovedajúcim spôsobom znížiť aj teplotu. K tejto časti úlohy sa vrátite neskôr v rámci doplňujúcich úloh.

> Poznámka:
> Pri implementácii nezabudnite na to, že okrem úrovne poškodenia a teploty je potrebné zmeniť aj obrázok, ktorý vizualizuje teplotu reaktora. Predíďte však duplikovaniu kódu!


### Úloha
Overte správnosť svojej implementácie.

Vytvorte na mape inštanciu reaktora a niekoľkých kladív. Následne reaktor prehrejte a pokúste sa ho opraviť použitím kladiva.


## Doplňujúce úlohy

### Úloha
V rámci opravy reaktora kladivom v metóde `repairWith()` patrične upravte aj teplotu reaktora.

Vráťte sa k úlohe 4.1 minulého cvičenia a všimnite si uvedený graf závislosti poškodenia od teploty. Ak ho invertujeme pre získanie závislosti teploty od poškodenia, tak jeho správanie v oblasti _0%_ poškodenia nie je pre výpočet teploty jednoznačné (nie je to funkcia). Preto výpočet teploty pri oprave reaktora upravte nasledovne:

- Najskôr určte vzťah teploty od poškodenia na základe časti grafu, kde je ich závislosť lineárna.
- Určte pomocnú hodnotu poškodenia (zníženú o _50_ oproti hodnote pred opravou) bez ohľadu na to, či výsledok bude kladné alebo záporné číslo.
- Získanú hodnotu dosaďte do pripraveného vzťahu a výsledok nastavte ako teplotu reaktora po oprave, avšak len ak by nová teplota reaktora bola nižšia ako tá pred opravou.

### Úloha
Vytvorte triedu pre Thorovo kladivo s názvom `Mjolnir`, odvodenú od triedy `Hammer`.

`Mjolnir` vydrží _4_ použitia (úplná oprava dvoch reaktorov), po ktorých sa, tak ako pôvodné kladivo, tiež zlomí.


## Doplňujúce zdroje

- Java Tutoriál: [What Is an Object?](http://download.oracle.com/javase/tutorial/java/concepts/object.html)
- Java Tutoriál: [What Is a Class?](http://download.oracle.com/javase/tutorial/java/concepts/class.html)
- Java Tutoriál: [What Is Inheritance?](http://docs.oracle.com/javase/tutorial/java/concepts/inheritance.html)
