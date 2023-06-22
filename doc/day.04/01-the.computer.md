# The Computer
**O preťažovaní (nie len) počítačov**

## Motivácia

## Ciele


## Krok: Intermezzo - Method Overloading

Teraz, keď už vieš, ako vytvoriť triedu a jej metódy, ukážeme ti jednu z užitočných vlastností _polymorfizmu_ (ku ktorému sa ešte viackrát vrátime). Tou vlastnosťou je _preťaženie metód_ (anglicky _method overloading_).


### Úloha
V balíku `sk.tuke.kpi.oop.game` vytvorte triedu `Computer` ako potomka triedy `AbstractActor`.

Ako animáciu použite sprite obrázok [sprite-link:computer].

![Animácia `computer.png` (rozmery sprite-u: _80x48_, trvanie snímku: _0.2_)](images/computer.png)


### Úloha
V triede `Computer` vytvorte metódy pre vykonanie základných aritmetických operácií `add()` a `sub()` pre číselné údajové typy `int` a `float`.

Každá z týchto metód bude mať 2 parametre rovnakého typu pre operandy danej aritmetickej operácie. Keďže raz to budú parametre typu `int`, raz `float`, implementáciou takýchto metód nastane ich tzv. _preťaženie_.

> Poznámka:
> To, ktorá konkrétna metóda z dvoch možných pre meno `add` (a taktiež `sub`) bude zavolaná, je určené staticky počas kompilácie na základe typov argumentov dodaných vo volaní metódy.

Implementáciu si môžete overiť po spustení hry pomocou nástroja _Inšpektor_.

