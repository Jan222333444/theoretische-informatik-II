# Theoretische Informatik II

## Sortieren mit Merge Sort
>Beim Merge Sort werden zwei Teilarray durch sortiertes mischen zusammengeführt.
Der Merge Sort Algorithmus basiert auf dem *Teile-und-Herrsche-Paradigma*(vgl. Sedgewick et al, 2014).

>Charakteristika(N = Größe des Arrays):

	- Laufzeitverhalten: N log(N)
	- zusätzlich benötigter Speicherplatz: N

---
### Abstraktes In-Place-Mergen
>Beim In-Place-Verfahren versucht man den benötigten Speicher bei den rekursiven Methodenaufrufen so gering wie möglich zu halten.

>Dabei wird der Array im zu sortierenden Array selbst sortiert und somit einiges an Speicher eingespart

> Die Sortierung wird über eine Funktionen realisiert die jeweils den niedrigsten, den höchsten und den mittleren Index des zu sortierenden Teils des Arrays und den gesamten Array selbst übergeben bekommen. Dieser Abschnitt wird dann iterativ per merge sortiert
---
### Top-Down-Mergesort
>Der Top-Down
---
### Bottom-Up-Mergesort
>Der Bottom-Up
---
---
## Bäume

---
### Binärer Suchbaum
> Beim binären Suchbaum hat jeder Knoten maximal 2 Kindknoten, wobei das linke immer den kleineren Wert hat und der rechte immer den größeren Wert. Alle Knoten links eines Knotens sind kleiner als der Ausgangsknoten und alle Knoten rechts sind größer. Dadurch ist die maximale Zahl der Vergleiche bei der Suche in einem binären Suchbaum gleich dessen Tiefe.
---
---


## Hashing
---
## Teilstringsuche nach Boyer-Moore