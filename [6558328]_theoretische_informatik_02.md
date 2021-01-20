# Theoretische Informatik II

## Sortieren mit Merge Sort
>Beim Merge Sort werden zwei Teilarray durch sortiertes mischen zusammengeführt. Der Algorithmus teilt dabei den Array solange auf bis nur noch pro Teilarray maximal 1 Element übrig ist. Diese Teilarrays werden zusammengeführt indem immer die ersten beiden Elemente zweier Teilarrays verglichen werden. Ist ein Wert kleiner  so wird er in den Ausgabearray geschrieben und man betrachtet im nächsten Vergleich das darauf folgende Element. Dies wird so lange durchgeführt bis man das Ende beider Teilarrays erreicht ist. Die gegebenen Teilarrays müssen bereits geordnet sein, weshalb die zuvor angesprochene Teilung bis zu Arrays mit maximal einem Element notwendig ist, da Listen mit einem Element automatisch sortiert sind.
Der Merge Sort Algorithmus basiert auf dem *Teile-und-Herrsche-Paradigma*(vgl. Sedgewick et al, 2014).

>Charakteristika(N = Größe des Arrays):

	- Laufzeitverhalten: N log(N)
	- zusätzlich benötigter Speicherplatz: N

Einfaches Sortieren mit Mergesort

||   8 1 2 6 3 5 9 7 4  |  |   | | |
|----| --- | ----| ---| ---|---|
| 8 1 2 6 || 3 5 9 7 4 |||Array wird in zwei neue Arrays gespeichert|
| 8 1 | 2 6 | 3 5 | 9 7 4||
|1 8 | 2 6 | 3 5 | 9 | 7 4 |Erstes Sortieren|
|1 8 | 2 6 | 3 5 | 9 | 4 7 |Maximale Teilung
|1 2 6 8 || 3 5 | 4 7 9 ||Erstes Mergen 
|1 2 6 8 || 3 4 5 7 9 |
||1 2 3 4 5 6 7 8 9 |


Einfacher Mergesort in Java

```Java
public static Comparable[] merge(Comparable[] input){
	Comparable[] part1 = new Comparable[input.length/2];
	Comparable[] part2 = new Comparable[input.length/2];
	if(input.length > 2){
		System.arraycopy(input, 0, part1, 0, part1.length);
		System.arraycopy(input, part1.length, part2, 0, part2.length);
		part1 = merge(part1);
		part2 = merge(part2);
	}else{
		part1 = new Comparable[1];
		part2 = new Comparable[1];
		part1[0] = input[0];
		part2[0] = input[1];
	}
	int left = 0;
	int right = 0;
	int out = 0;
	Comparable[] output = new Comparable[input.length];
	if(part1.length == 1 && part2.length == 1){
		int result = part1[left].compareTo(part2[right]);
		if(result <= 0){
			output[0] = part2[0];
			output[1] = part1[0];
		} else {
			output[0] = part1[0];
			output[1] = part2[0];
		}
		return output;
	}
	while(left < part1.length || right < part2.length){
		int result = part1[left].compareTo(part2[right]);
		if( result == 0){
			if( part1[left] == null && part2[right] == null){
				break;
			}
				output[out] = part1[left];
				out++;
				output[out] = part2[right];
				out++;
				left++;
				right++;
		}
		else if(result < 0){
			output[out] = part2[right];
			right++;
			out++;
		}else{
			output[out] = part1[left];
			left++;
			out++;
		}
	}
	return output;
}
```

---
### Abstraktes In-Place-Mergen
>Beim In-Place-Verfahren versucht man den benötigten Speicher bei den rekursiven Methodenaufrufen so gering wie möglich zu halten.
Dabei wird der Array im zu sortierenden Array selbst sortiert und somit einiges an Speicher eingespart

> Die Sortierung wird über eine Funktionen realisiert die jeweils den niedrigsten, den höchsten und den mittleren Index des zu sortierenden Teils des Arrays und den gesamten Array selbst übergeben bekommen. Dieser Abschnitt wird dann iterativ per merge sortiert

Sortieren Abstraktes In-Place-Mergen
8 1 2 6 3 5 9 7 4
|8|1|2|6|3|5|9|7|4|
|---|---|----|---|---|---|---|---|---|
|
|


Abstrakter In-Place-Merge in Java
```Java
public void merge(Comparable[] input, int low, int mid, int high){

}
```
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
---
## Teilstringsuche nach Boyer-Moore