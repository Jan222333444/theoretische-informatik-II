# Theoretische Informatik II

## Sortieren mit Merge Sort


Beim Merge Sort werden zwei Teilarrays durch sortiertes Mischen zusammengeführt. Der Algorithmus teilt dabei den Array solange auf bis nur noch pro Teilarray maximal 1 Element übrig ist. Diese Teilarrays werden zusammengeführt indem immer die ersten beiden Elemente zweier Teilarrays verglichen werden. Ist ein Wert kleiner  so wird er in den Ausgabearray geschrieben und man betrachtet im nächsten Vergleich das darauf folgende Element. Dies wird so lange durchgeführt bis man das Ende beider Teilarrays erreicht ist. Die gegebenen Teilarrays müssen bereits geordnet sein, weshalb die zuvor angesprochene Teilung bis zu Arrays mit maximal einem Element notwendig ist, da Listen mit einem Element automatisch sortiert sind.
> Der Merge Sort Algorithmus basiert auf dem *Teile-und-Herrsche-Paradigma* (vgl. Sedgewick et al, 2014).


Charakteristika(N = Größe des Arrays):
* Laufzeitverhalten: N log(N)
* zusätzlich benötigter Speicherplatz: N


Einfaches Sortieren mit Mergesort

||   8 1 2 6 3 5 9 7 4  |  |   | | |
|----| --- | ----| ---| ---|---|
| 8 1 2 6 || 3 5 9 7 4 |||Array wird in zwei neue Arrays gespeichert|
| 8 1 | 2 6 | 3 5 | 9 7 4||
|1 8 | 2 6 | 3 5 | 9 7 | 4 |Erstes Sortieren|
|1 8 | 2 6 | 3 5 | 7 9 | 4 |Maximale Teilung
|1 2 6 8 || 3 5 | 4 7 9 ||Erstes Mergen 
|1 2 6 8 || 3 4 5 7 9 |
||1 2 3 4 5 6 7 8 9 |

![Sortieren Grafisch](/Mergesort.jpg "Graphische Darstellung")

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


Beim In-Place-Verfahren versucht man den benötigten Speicher bei den rekursiven Methodenaufrufen so gering wie möglich zu halten.
Dabei wird der Array im zu sortierenden Array selbst sortiert und somit einiges an Speicher eingespart


Die Sortierung wird über eine Funktionen realisiert die jeweils den niedrigsten, den höchsten und den mittleren Index des zu sortierenden Teils des Arrays und den gesamten Array selbst übergeben bekommen. Dieser Abschnitt wird dann iterativ per merge sortiert


---
### Top-Down-Mergesort


Beim Top-Down-Mergesort wird ein weiterer Sortieralgorithmus (z.B. Insertionsort oder Selectionsort) zur Hilfe genommen. Die weiteren Sortieralgorithmen werden für das Sortieren kleinerer Teilarrays genutzt, da z.B. der Insertionsort bei kleinen Arrays effizienter ist als der Mergesort.
Das bedeutet, dass die Aufteilung des Arrays, wie sie beim Mergesort vorgesehen ist, nur bis zu einem gewissen Grad durchgeführt wird und dann die restliche Sortierung der Arrayfragmente durch einen für kleine Arrays effizienteren Algorithmus durchgeführt wird.


>Der Top-Down-Mergesort Algorithmus hat ein Laufzeit verhalten zwischen 1/2 N log(N) und N log(N). 


---
### Bottom-Up-Mergesort


Der Bottom-Up-Mergesort verzichtet auf Rekursion. Der Algorithmus iteriert über den Array wobei er beim ersten Durchlauf je zwei aufeinander folgende Elemente sortiert. Die entstehenden Gruppen werden dann beim nächsten Durchlauf wieder mit je zu zweit Gruppe in eine neue Gruppe gemerget. Dieser Prozess läuft so lange bis der Array sortiert ist. Die Anzahl der benötigten Vergleiche ist gleich wie bei der rekursiven Variante, jedoch kann es beim Bottom-Up-Mergesort, passieren, dass ein Element lange Zeit als eigene Gruppe betrachtet wird und erst bei der vorletzten Iteration mit einer sehr großen Gruppe gemergt. 


---
---
## Bäume

---
### Binärer Suchbaum


Beim binären Suchbaum hat jeder Knoten maximal 2 Kindknoten, wobei das linke immer den kleineren Wert hat und der rechte immer den größeren Wert. Alle Knoten links eines Knotens sind kleiner als der Ausgangsknoten und alle Knoten rechts sind größer. Dadurch ist die maximale Zahl der Vergleiche bei der Suche in einem binären Suchbaum gleich dessen Höhe ist. Auf jeden Knoten referenziert genau ein Elternknoten, außer beim Wurzelknoten auf den kein anderer Knoten referenziert. 

![Binärer-Suchbaum-Vollständig](/Binärer_Suchbaum.png "Binärer Suchbaum")

Beim Einfügen eines Knoten wird dieser in der Regel als ein Blatt des Baumes eingefügt.

Das Löschen eines Knoten muss in mehrere Fälle unterschieden werden. Ist der zu entfernente Knoten ein Blatt des Baumes so muss lediglich dessen Referenz gelöscht werden. Hat der zu löschende Knoten genau ein Kindknoten so wird dieser als Referenz an den Elternknoten des zu löschenden Knotens gegeben. 


---

### AVL-Bäume


Der AVL-Baum ist besonders gut geeignet, wenn häufig schnell auf Daten zugegriffen werden muss.
Der AVL-Baum ist ein binärer Suchbaum, bei dem per Rotation beim Einfügen dafür gesorgt wird, dass er balanciert bleibt und dadurch maximal log N Schlüssel verglichen werden müssen, um ein Objekt zu finden. 
Damit der Baum balanciert bleibt wird bei dem Einfügen mithilfe des Balance-Faktors rotiert, wodurch die balancierte Struktur des binären Suchbaums erhalten bleibt.
Der Balance-Faktor kann für jeden Knoten des Baumes berechnet werden. Dabei wird die Höhe des linken Teilbaums des Knotens von der Höhe des rechten Teilbaums abgezogen.


> BF(t) := Height(tr) - Height(tl)

Ein binärer Suchbaum ist dann ein AVL-Baum, wenn der Balance-Faktor jedes Knotens kleiner gleich 1 und größer gleich -1 ist.

> -1 <= BF(t) <= 1

Ist der Balance-Faktor eines Knoten negativ so ist der linke Teilbaum höher als der rechte.

![AVL-Baum-Vollständig](/AVL-Baum.png "AVL-Baum")

Um aus eine binären Suchbaum einen AVL-Baum zu machen, wird die Rotation bei allen Knoten mit | BF(t) | >= 2 eingesetzt bis alle Knoten einen Balance-Faktor zwischen 1 und -1 haben.

Beim Einfügen wird zuerst wie im binären Suchbaum der Knoten eingefügt, danach wird der Baum ggf. wieder zu einem AVL-Baum umgewandelt.


---
---


## Hashing


### Hashfunktionen

Hashfunktionen bilden mit Eingaben einen Hashwert der, je nach angewendeter Funktion, nahezu eindeutig ist. 

---
---

## Teilstringsuche nach Boyer-Moore

Bei der Teilstringsuche nach Boyer-Moore

### Vorkommensheuristik



---
---
